import java.io.Serializable;

public class ServerDto implements Serializable {
    public String id;
    public Boolean shotResult;
    public Integer playerPoints;
    public enum gameState {
        WIN,
        LOSS,
        WAITING_FOR_OTHER_PLAYER
    }
    public Exception e;
}
