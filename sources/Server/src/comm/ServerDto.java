package comm;

import logic.Game;
import logic.GameField;
import logic.Ship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Dto used for sending information server client to client
 */
public class ServerDto implements Serializable {
    public String id;
    public Integer playerPoints;
    public Game.GameState gameState;
    public ArrayList<GameField> playerFields;
    public List<Ship> ships;
    public Exception e;
    public Error error;
    public String opponentId; //jméno soupeře
    public Integer opponentPoints; //body soupeře
    public ArrayList<GameField> opponentField; //pole soupeře pro aktualizaci klienta
    public ArrayList<Ship> destroyedOpponentsShips; //destroyed opponents ships - for client update
    public Integer gameId;
    public boolean isMyTurn; //should current client wait or play?
}
