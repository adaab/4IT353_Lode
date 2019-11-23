import java.io.Serializable;

public class GameField implements Serializable {
    private String x;
    private String y;

    public GameField(String x, String y){
        this.x = x;
        this.y = y;
    }
}
