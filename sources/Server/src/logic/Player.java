package logic;

import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Player {
    public InetAddress ip;
    public Integer port;
    private Integer gameId;
    private String id;
    private Integer points;
    private List<Ship> ships;
    public ObjectOutputStream out;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Player(InetAddress ip, Integer port, ObjectOutputStream out) {
        ships = new ArrayList<>();
        this.ip = ip;
        this.port = port;
        this.out = out;
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

    public Boolean readyToPlay() {
        return (ip != null && port != null && gameId != null && id != null && ships.size() > 0);
    }

    public String toString() {
        return this.ip + " " + port + " ";
    }
}
