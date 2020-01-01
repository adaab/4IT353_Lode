package Logic;

import java.io.Serializable;

public class GameField implements Serializable {
    private String x;
    private String y;
    private logic.GameField.FieldState fieldState;


    public GameField(String x, String y){
        this.x = x;
        this.y = y;
    }
}
