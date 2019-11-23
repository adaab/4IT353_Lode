package logic;

import java.util.ArrayList;

public class Game {
    private Integer gameId;
    private ArrayList<GameField> fields;
    private Player playerA;
    private Player playerB;
    private Boolean isGameRunning;

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
    }

    public Player getPlayerB() {
        return playerB;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
        this.playerB.setGameId(this.gameId);
    }

    public Game(Integer gameId) {
        this.gameId = gameId;
        initGameBoard();
    }

    private void initGameBoard() {
        fields = new ArrayList<>();
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (int i = 0 ; i < 10 ; i++){
            for (int j = 1 ; j <= 10 ; j++){
                this.fields.add(new GameField(letters[i], String.valueOf(j)));
            }
        }
    }
}
