package logic;

import java.io.Serializable;
import java.util.List;

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

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        GameField field = (GameField) o;
        // field comparison
        return (this.x.equals(field.getX()) && this.y.equals(field.getY()));
    }

    public static GameField getFieldFromArrayByPosition(List<GameField> list, String x, String y) {
        for (GameField field : list) {
            if (x.equals(field.getX()) && y.equals(field.getY())) {
                return field;
            }
        }
        return null;
    }
}
