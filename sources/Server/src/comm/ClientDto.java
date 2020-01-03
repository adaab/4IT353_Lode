package comm;

import logic.Ship;

import java.io.Serializable;
import java.util.List;

public class ClientDto implements Serializable {
    public String id;
    public String password;
    public Boolean playerReadyToPlay;
    public List<Ship> playerShips;
    public String shotX;
    public String shotY;
    public Integer gameId;
}
