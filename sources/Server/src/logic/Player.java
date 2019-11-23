package logic;

import java.net.InetAddress;
import java.util.List;

public class Player {
    public InetAddress ip;
    public Integer port;
    private Integer gameId;
    private String id;
    private Integer points;
    private List<Ship> ships;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Player(InetAddress ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public Boolean isAlive() {
        Boolean alive = false;
        for (Ship s : ships) {
            if (s.getAlive()) {
               alive = true;
            }
        }
        return alive;
    }
}
