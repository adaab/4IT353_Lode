package logic;

import comm.ClientDto;
import comm.CommunicationDtosService;
import logic.GameField.FieldState;

import java.util.ArrayList;

/**
 * Game - contains logic for playing the game, also processes responses from client
 *
 * @author  chot2
 */
public class Game {
    public static final String[] BOARD_LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
    private Integer gameId;
    private Player playerA;
    private Player playerB;
    private Player currentlyPlaying;
    private Boolean isGameRunning;

    private Boolean lastShotResult;
    public enum GameState {
        INITIALIZED,
        WAITING_FOR_OTHER_PLAYER,
        NEW, //uživatelé se přihlásili ok, může začít nová hra - zadání svých lodí apod.
        PLAYING, //hra probíhá - dto jen pro aktualizaci polí po výstřelu
        WIN,
        LOSS
    }
    private GameState currentGameState;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Player getPlayerA() {
        return playerA;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
        this.playerA.setGameId(this.gameId);
        this.currentGameState = GameState.WAITING_FOR_OTHER_PLAYER;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
        this.playerB.setGameId(this.gameId);
        startNewGame();
    }

    public Player getCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public void setCurrentlyPlaying(Player currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public Boolean getLastShotResult() {
        return lastShotResult;
    }

    /**
     * Constructor - sets gameId and set currentGameState to INITIALIZED
     *
     * @param gameId
     *
     * @author  chot2
     */
    public Game(Integer gameId) {
        this.gameId = gameId;
        this.currentGameState = GameState.INITIALIZED;
    }

    /**
     * sets starting player to playerA (the one that connected first) and sets currentGameState to NEW
     *
     * @author  chot2
     */
    public void startNewGame() {
        currentlyPlaying = playerA;
        this.currentGameState = GameState.NEW;
    }

    /**
     * returns Player that is currently not on turn
     *
     * @author  chot2
     *
     * @return Player
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
     * @author  chot02
     *
     * @param p
     * @return Player
     */
    public Player getOpponentForPlayer(Player p) {
        if (p.getId().equals(playerA.getId())) {
            return playerB;
        } else if (p.getId().equals(playerB.getId())) {
            return playerA;
        } else {
            return null;
        }
    }

    /**
     * sends responses from game to both players
     *
     * @author  chot2
     */
    public void informPlayers() {
        CommunicationDtosService.informPlayers(this);
    }

    /**
     * processes message from player -
     *      if his id is not set - sets it
     *      if game state is NEW (setting ships) sets his ships according to ships from message
     *      if game state is PLAYING - handles his shot on field XY
     * informs both players about game after processing message
     *
     * @author  chot2
     *
     * @param   p Player from which message came
     * @param   dto message in form of ClientDto
     */
    public void processClientMessage(Player p, ClientDto dto) {
        Player messagingPlayer = null;
        if (p.equals(playerA)) {
            messagingPlayer = playerA;
        } else if (p.equals(playerB)) {
            messagingPlayer = playerB;
        }
        if (messagingPlayer != null) {
            if (messagingPlayer.getId() == null) {
                messagingPlayer.setId(dto.id);
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
            informPlayers();
        }
    }

    /**
     * Handles player shot from message - if opponent has ship on that position sets that opponent field to shipHit and
     * updates opponents fields
     *
     * @author  chot2
     *
     * @param dto message from currently playing player
     */
    private void handlePlayerShot(ClientDto dto) {
        GameField field = findOpponentShipPosition(dto.shotX, dto.shotY);
        if (field != null) {
            field.setFieldState(FieldState.shipHit);
        }
        getOpponentForPlayer(currentlyPlaying).updatePlayerFields();
    }

    /**
     * finds GameField if opponent of currently playing player has ship on it
     *
     * @author  chot2
     *
     * @param x x position of field
     * @param y y position of field
     * @return GameField - returns null if opponent has no ship on provided coordination
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

}
