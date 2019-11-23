package logic;

import java.util.ArrayList;

public class Game {
    private ArrayList<GameField> fields;
    private Player playerA;
    private Player playerB;

    public Game() {
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
