package logic;

import java.io.Serializable;

public class GameField implements Serializable {
    private String x;
    private String y;
    private FieldState fieldState;
    public enum FieldState {
        empty,
        ship,
        shipHit,
        missed
    }

    public GameField(String x, String y, FieldState fieldState){
        this.x = x;
        this.y = y;
        this.fieldState = fieldState;
    }

    public String getPosition(){
        return x+y;
    }


    public String getX() {
        return x;
    }


    public String getY() {
        return y;
    }

    public FieldState getFieldState() {
        return fieldState;
    }

    public void setFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
    }
}
