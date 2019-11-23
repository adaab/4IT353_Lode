package comm;

public class ServerDto {
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
