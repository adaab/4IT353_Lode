package logic;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Ship implements Serializable {
    private String name;
    private Integer size;
    private Boolean isAlive;
    HashSet<GameField> positions;

    public Ship(String name, Integer size, Boolean isAlive, HashSet<GameField> positions) {
        this.name = name;
        this.size = size;
        this.isAlive = isAlive;
        this.positions = positions;
    }

    public Boolean getAlive() {
        return isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public HashSet<GameField> getPositions() {
        return positions;
    }

    public void setPositions(HashSet<GameField> positions) {
        this.positions = positions;
    }
}
