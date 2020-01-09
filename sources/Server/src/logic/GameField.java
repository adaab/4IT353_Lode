package logic;

import java.io.Serializable;
import java.util.List;

/**
 * Represents one game field on a board, can be empty, contain ship, death ship or can be missed after previous shot
 *
 *
 */
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

    /**
     * Constructor
     *
     * @param x x coordinate of field on board
     * @param y y coordinate of field on board
     * @param fieldState initiating state of the field
     */
    public GameField(String x, String y, FieldState fieldState){
        this.x = x;
        this.y = y;
        this.fieldState = fieldState;
    }

    /**
     * @return position of the field on board
     */
    public String getPosition(){
        return x+y;
    }

    /**
     * @return x coordinate of field on board
     */
    public String getX() {
        return x;
    }

    /**
     * @return y coordinate of field on board
     */
    public String getY() {
        return y;
    }

    /**
     * @return current state of the field (enum GameField.FieldState)
     */
    public FieldState getFieldState() {
        return fieldState;
    }


    /**
     * @param fieldState current state of the field (enum GameField.FieldState)
     */
    public void setFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
    }

    /**
     * equals - returns true if x and y coordinates of game fields are the same
     *
     * @param o object to compare
     * @return true if x and y coordinates of game fields are the same otherwise false
     */
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

    /**
     * returns first GameField from list based on provided x and y coordinates
     *
     * @param list list from which we want to retrieve GameField
     * @param x x coordinate of field on board
     * @param y y coordinate of field on board
     * @return GameField from List based on provided coordinates
     *
     * @author chot02
     */
    public static GameField getFieldFromArrayByPosition(List<GameField> list, String x, String y) {
        for (GameField field : list) {
            if (x.equals(field.getX()) && y.equals(field.getY())) {
                return field;
            }
        }
        return null;
    }
}
