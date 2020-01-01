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
    private ArrayList<Ship> ships;

    private ArrayList<GameField> playerFields;
    private ArrayList<GameField> foundOutOpponentFields;
    public ObjectOutputStream out;

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public ArrayList<GameField> getPlayerFields() {
        return playerFields;
    }

    public ArrayList<GameField> getFoundOutOpponentFields() {
        return foundOutOpponentFields;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
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

    public ArrayList<Ship> destroyedShips() {
        ArrayList<Ship> result = new ArrayList<>();
        for (Ship s : ships) {
            if (!s.getAlive()) {
                result.add(s);
            }
        }
        return result;
    }

    public Boolean readyToPlay() {
        return (ip != null && port != null && gameId != null && id != null && ships.size() > 0);
    }

    public String toString() {
        return this.ip + " " + port + " ";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (obj instanceof Player) {
            Player p = (Player) obj;
            if (id.equals(p.getId()) || (ip.equals(p.ip) && port.equals(p.port))) {
                return true;
            }
        }
        return false;
    }
}
