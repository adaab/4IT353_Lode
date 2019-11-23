package comm;

import logic.Ship;

import java.util.List;

public class ClientDto {
    public String id;
    public String password;
    public Boolean playerReadyToPlay;
    public List<Ship> playerShips;
    public Integer shotX;
    public Integer shotY;
}
