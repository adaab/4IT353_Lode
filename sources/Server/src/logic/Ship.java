package logic;

import java.util.Set;

public class Ship {
    private String name;
    private Integer size;
    private Boolean isAlive;

    public Boolean getAlive() {
        return isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    Set<GameField> positions;
}
