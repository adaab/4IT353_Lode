package logic;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents one Player's ship on board, it occupies several GameFields
 *
 * @author chot02
 */
public class Ship implements Serializable {
    private String name;
    private Integer size;
    private Boolean isAlive;
    HashSet<GameField> positions;

    /**
     * Constructor
     *
     * @param name type of the ship
     * @param size how big is the ship is
     * @param isAlive determines whether ship is still alive or not
     * @param positions GameFields which ships occupies
     */
    public Ship(String name, Integer size, Boolean isAlive, HashSet<GameField> positions) {
        this.name = name;
        this.size = size;
        this.isAlive = isAlive;
        this.positions = positions;
    }

    /**
     * @return state of ship
     */
    public Boolean getAlive() {
        return isAlive;
    }

    /**
     * @param alive determines whether ship is still alive or not
     */
    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    /**
     * @return type of the ship
     */
    public String getName() {
        return name;
    }

    /**
     * @param name type of the ship
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return how big is the ship is
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size how big is the ship is
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return  GameFields which ships occupies
     */
    public HashSet<GameField> getPositions() {
        return positions;
    }

    /**
     * @param positions GameFields which ships occupies
     */
    public void setPositions(HashSet<GameField> positions) {
        this.positions = positions;
    }
}
