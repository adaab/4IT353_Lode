package logic;

import comm.ClientDto;
import comm.CommunicationDtosService;
import logic.GameField.FieldState;
import java.util.ArrayList;

public class Game {
    private static final String[] BOARD_LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
    private Integer gameId;
    private ArrayList<GameField> fields;
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

    public Game(Integer gameId) {
        this.gameId = gameId;
        this.currentGameState = GameState.INITIALIZED;
    }

    public void startNewGame() {
        initGameBoard();
        currentlyPlaying = playerA;
        this.currentGameState = GameState.NEW;
    }

    public Player getCurrentlyNotPlayingPlayer() {
        if (currentlyPlaying.getId().equals(playerA.getId())) {
            return playerB;
        } else {
            return playerA;
        }
    }

    public Player getOpponentForPlayer(Player p) {
        System.out.println("Player" + p);
        System.out.println("p.getId()" + p.getId());
        if (p.getId().equals(playerA.getId())) {
            return playerB;
        } else {
            return playerA;
        }
    }

    private void initGameBoard() {
        fields = new ArrayList<>();

        for (int i = 0 ; i < 16 ; i++){
            for (int j = 1 ; j <= 12 ; j++){
                this.fields.add(new GameField(BOARD_LETTERS[i], String.valueOf(j), FieldState.empty));
            }
        }
    }

    public void informPlayers() {
        CommunicationDtosService.informPlayers(this);
    }

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
                        }
                        //TODO  handle players turns

                }
            }
            informPlayers();
        }
    }

    private void handlePlayerShot(ClientDto dto) {
        GameField field = findOpponentShipPosition(dto.shotX, dto.shotY);
        if (field != null) {
            field.setFieldState(FieldState.shipHit);
        }
    }

    private GameField findOpponentShipPosition(String x, String y) {
        GameField field = null;
        Player opponent = getOpponentForPlayer(currentlyPlaying);
        for (Ship s : opponent.getShips()) {
            for (GameField gameField : s.getPositions()) {
                if (x.equals(gameField.getX()) && y.equals(gameField.getY())) {
                    field = gameField;
                }
            }
        }
        return field;
    }
}
