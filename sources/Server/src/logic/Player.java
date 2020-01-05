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
    private ArrayList<GameField> fields;

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

    public ArrayList<GameField> getFields() {
        return fields;
    }

    private void initPlayerFields() {
        System.out.println("TTTT INIT");
        fields = new ArrayList<>();
        for (int i = 0 ; i < 16 ; i++){
            for (int j = 0 ; j < 13 ; j++){
                fields.add(new GameField(Game.BOARD_LETTERS[i], String.valueOf(j), null));
            }
        }
    }

    public void updatePlayerFields() {
        for (GameField field : this.fields) {
            for (Ship s : ships) {
                for (GameField shipField : s.getPositions()) {
                    if (field.equals(shipField)) {
                        System.out.println("updating fields " + field.getPosition() + " " + shipField.getFieldState());
                        field.setFieldState(shipField.getFieldState());
                    }
                }
            }
        }
        System.out.println("A0 STATE PLAYER: " + this.fields.get(0).getFieldState());
    }

    public ArrayList<GameField> getFoundOutOpponentFields() {
        return foundOutOpponentFields;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
        updatePlayerFields();
    }

    public Player(InetAddress ip, Integer port, ObjectOutputStream out) {
        ships = new ArrayList<>();
        this.ip = ip;
        this.port = port;
        this.out = out;
        initPlayerFields();
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
            if ((id != null && id.equals(p.getId())) || (ip.equals(p.ip) && port.equals(p.port))) {
                return true;
            }
        }
        return false;
    }
}
