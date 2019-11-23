package logic;

import java.net.InetAddress;
import java.util.List;

public class Player {
    public InetAddress ip;
    public Integer port;
    private String id;
    private Integer points;
    private List<Ship> ships;

    public Player(InetAddress ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }
}
