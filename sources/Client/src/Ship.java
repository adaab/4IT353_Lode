import java.io.Serializable;
import java.util.Set;

public class Ship implements Serializable {
    private String name;
    private Integer size;
    private Boolean isAlive;
    Set<GameField> positions;

    public Boolean getAlive() {
        return isAlive;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }
}
