package logic;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Player assigned to one game, contains his ships and fields
 *
 * @author  chot2
 */
public class Player {
    public InetAddress ip;
    public Integer port;
    private Integer gameId;
    private String id;
    private Integer points;
    private List<Ship> ships;
    private ArrayList<GameField> fields;

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

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
        updatePlayerFields();
    }

    /**
     * Constructor - initializes players board
     *
     * @param ip
     * @param port
     * @param out
     */
    public Player(InetAddress ip, Integer port, ObjectOutputStream out) {
        ships = new ArrayList<>();
        this.ip = ip;
        this.port = port;
        this.out = out;
        initPlayerFields();
    }

    /**
     * Inits player game field x line A-P and y line 0 - 12
     *
     * @author  chot02
     */
    private void initPlayerFields() {
        fields = new ArrayList<>();
        for (int i = 0 ; i < 16 ; i++){
            for (int j = 0 ; j < 13 ; j++){
                fields.add(new GameField(Game.BOARD_LETTERS[i], String.valueOf(j), GameField.FieldState.empty));
            }
        }
    }

    /**
     * Takes states from player ships fields and updates player fields on board accordingly
     *
     * @author  chot02
     */
    public void updatePlayerFields() {
        for (GameField field : this.fields) {
            for (Ship s : ships) {
                for (GameField shipField : s.getPositions()) {
                    if (field.equals(shipField)) {
                        field.setFieldState(shipField.getFieldState());
                    }
                }
            }
        }
    }

    /**
     * Checks whether any of player ship is still alive
     *
     * @author  chot02
     *
     * @return Boolean
     */
    public Boolean isAlive() {
        Boolean alive = false;
        for (Ship s : ships) {
            if (s.getAlive()) {
               alive = true;
               break;
            }
        }
        return alive;
    }

    /**
     * Returns all player ships that are not alive
     *
     * @author  chot02
     *
     * @return ArrayList<Ship> of player destroyed ships
     */
    public ArrayList<Ship> destroyedShips() {
        ArrayList<Ship> result = new ArrayList<>();
        for (Ship s : ships) {
            if (!s.getAlive()) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Checks whether player has all needed properties for playing set
     *
     * @author  chot02
     *
     * @return Boolean - true if player has all needed properties for playing set
     */
    public Boolean readyToPlay() {
        return (ip != null && port != null && gameId != null && id != null && ships.size() > 0);
    }

    public String toString() {
        return this.ip + " " + port + " ";
    }

    /**
     * equals - true if id, ip and port of players are equal
     *
     * @author  chot02
     *
     * @param obj
     * @return Boolean - true if id, ip and port of players are equal
     */
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
