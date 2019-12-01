package comm;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerDto implements Serializable {
    public String id;
    public Boolean shotResult;
    public Integer playerPoints;
    public String gameState;
    public ArrayList playerField;
    public enum gameState {
        NEW, //uživatel se přihlásil ok, může začít nová hra - zadání svých lodí apod.
        WIN,
        LOSS,
        WAITING_FOR_OTHER_PLAYER
    }
    public Exception e;
    public Error error;
    public String opponentId; //jméno soupeře
    public Integer opponentPoints; //body soupeře
    public ArrayList opponentField;
}
