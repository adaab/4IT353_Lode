package logic;

import comm.ClientDto;
import comm.CommunicationDtosService;
import comm.Error;
import comm.TCPServer;
import logic.GameField.FieldState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Game - contains logic for playing the game, also processes responses from client
 *
 * @author chot2
 */
public class Game {
    static final Logger LOG = LoggerFactory.getLogger(Game.class);
    public static final String[] BOARD_LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
    private Integer gameId;
    private Player playerA;
    private Player playerB;
    private Player currentlyPlaying;

    private Boolean isGameRunning;

    public enum GameState {
        INITIALIZED,
        WAITING_FOR_OTHER_PLAYER,
        NEW, //uživatelé se přihlásili ok, může začít nová hra - zadání svých lodí apod.
        PLAYING, //hra probíhá - dto jen pro aktualizaci polí po výstřelu
        WIN,
        LOSS
    }

    private GameState currentGameState;

    /**
     * @return has game ended?
     */
    public Boolean getGameRunning() {
        return isGameRunning;
    }

    /**
     * @return game id (integer)
     */
    public Integer getGameId() {
        return gameId;
    }

    /**
     * @param gameId
     */
    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * @return first player of game
     */
    public Player getPlayerA() {
        return playerA;
    }

    /**
     * @param playerA
     */
    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
        this.playerA.setGameId(this.gameId);
        this.currentGameState = GameState.WAITING_FOR_OTHER_PLAYER;
    }

    /**
     * @return second player of game
     */
    public Player getPlayerB() {
        return playerB;
    }

    /**
     * @param playerB
     */
    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
        this.playerB.setGameId(this.gameId);
        startNewGame();
    }

    /**
     * @return player that is currently on turn
     */
    public Player getCurrentlyPlaying() {
        return currentlyPlaying;
    }

    /**
     * @param currentlyPlaying
     */
    public void setCurrentlyPlaying(Player currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }

    /**
     * @return state in which game currently is (enum Game.GameState)
     */
    public GameState getCurrentGameState() {
        return currentGameState;
    }

    /**
     * Constructor - sets gameId and set currentGameState to INITIALIZED
     *
     * @param gameId
     * @author chot2
     */
    public Game(Integer gameId) {
        this.gameId = gameId;
        this.currentGameState = GameState.INITIALIZED;
        this.isGameRunning = true;
    }

    /**
     * sets starting player to playerA (the one that connected first) and sets currentGameState to NEW
     *
     * @author chot2
     */
    public void startNewGame() {
        currentlyPlaying = playerA;
        this.currentGameState = GameState.NEW;
    }

    /**
     * returns Player that is currently not on turn
     *
     * @return Player
     * @author chot2
     */
    public Player getCurrentlyNotPlayingPlayer() {
        if (currentlyPlaying.getId().equals(playerA.getId())) {
            return playerB;
        } else {
            return playerA;
        }
    }

    /**
     * for provided player, returns his opponent
     *
     * @param p
     * @return Player
     * @author chot02
     */
    public Player getOpponentForPlayer(Player p) {
        if (p.equals(playerA)) {
            return playerB;
        } else if (p.equals(playerB)) {
            return playerA;
        } else {
            return null;
        }
    }

    /**
     * sends responses from game to both players
     *
     * @author chot2
     */
    public void informPlayers() {
        CommunicationDtosService.informPlayers(this);
    }

    /**
     * processes message from player -
     * if his id is not set - sets it
     * if game state is NEW (setting ships) sets his ships according to ships from message
     * if game state is PLAYING - handles his shot on field XY
     * informs both players about game after processing message
     *
     * @param p   Player from which message came
     * @param dto message in form of ClientDto
     * @author chot2
     */
    public void processClientMessage(Player p, ClientDto dto) {
        try {
            Player messagingPlayer = null;
            if (p.equals(playerA)) {
                messagingPlayer = playerA;
            } else if (p.equals(playerB)) {
                messagingPlayer = playerB;
            }
            if (messagingPlayer != null) {
                if (messagingPlayer.getId() == null) {
                    setPlayerId(p, dto);
                } else {
                    switch (this.currentGameState) {
                        case NEW:
                            messagingPlayer.setShips(dto.playerShips);
                            if (playerA.readyToPlay() && playerB.readyToPlay()) {
                                this.currentGameState = GameState.PLAYING;
                            }
                            break;
                        case PLAYING:
                            if (currentlyPlaying.getId().equals(messagingPlayer.getId())) {
                                handlePlayerShot(dto);
                                currentlyPlaying = getOpponentForPlayer(currentlyPlaying);
                            }
                    }
                }
            }
            informPlayers();
        } catch (Exception e) {
            LOG.error("GameId : " + gameId + " Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Handles player shot from message - if opponent has ship on that position sets that opponent field to shipHit and
     * updates opponents fields
     *
     * @param dto message from currently playing player
     * @author chot2
     */
    private void handlePlayerShot(ClientDto dto) {
        GameField fieldWithOpponentShip = findOpponentShipPosition(dto.shotX, dto.shotY);
        if (fieldWithOpponentShip != null) {
            fieldWithOpponentShip.setFieldState(FieldState.shipHit);
            currentlyPlaying.setPoints(currentlyPlaying.getPoints() + 10);

        } else {
            GameField playerField = GameField.getFieldFromArrayByPosition(getOpponentForPlayer(currentlyPlaying).getFields(), dto.shotX, dto.shotY);
            if (playerField != null) {
                playerField.setFieldState(FieldState.missed);
            }
        }
        getOpponentForPlayer(currentlyPlaying).updatePlayerFields();
        checkGameOver();
    }

    /**
     * finds GameField if opponent of currently playing player has ship on it
     *
     * @param x x position of field
     * @param y y position of field
     * @return GameField - returns null if opponent has no ship on provided coordination
     * @author chot2
     */
    private GameField findOpponentShipPosition(String x, String y) {
        GameField field = null;
        Player opponent = getOpponentForPlayer(currentlyPlaying);
        for (Ship s : opponent.getShips()) {
            field = GameField.getFieldFromArrayByPosition(new ArrayList<GameField>(s.getPositions()), x, y);
            if (field != null) {
                break;
            }
        }
        return field;
    }

    /**
     * sets game running property to false if not currently playing player lost all ships
     *
     * @author chot02
     */
    private void checkGameOver() {
        isGameRunning = getOpponentForPlayer(currentlyPlaying).isAlive();
        if (!isGameRunning) {
            String endMsg = "Game " + gameId + " ended. Winner was: ";
            if(playerA.isAlive()) {
                endMsg += " Player A: " + playerA.getId();
            } else {
                endMsg += " Player B: " + playerB.getId();
            }
            LOG.info(endMsg);
        }
    }

    /**
     * sets player id if player with this id doesn't already exists in game, if so, adds error to player
     *
     * @param player messaging player
     * @param dto data from client
     */
    private void setPlayerId(Player player, ClientDto dto) {
        if (player.getId() == null) {
            if (playerB == null || getOpponentForPlayer(player) == null || getOpponentForPlayer(player).getId() == null
                    || !getOpponentForPlayer(player).getId().equals(dto.id)) {
                player.setId(dto.id);
                LOG.error("GameId : " + gameId + " Player id set: " + player.toString());
            } else {
                LOG.error("GameId : " + gameId + " Player id already exists: " + dto.id);
                player.error = new Error(Error.Code.userExists, "User with this Id is already registered into the game.");
            }
        }
    }

}
