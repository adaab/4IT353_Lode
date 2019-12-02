package logic;

import java.io.Serializable;

public class GameField implements Serializable {
    private String x;
    private String y;
    private String fieldState;
    public enum FieldState {
        empty,
        ship,
        shipHit,
        missed
    }

    public GameField(String x, String y, String fieldState){
        this.x = x;
        this.y = y;
        this.fieldState = fieldState;
    }
}
