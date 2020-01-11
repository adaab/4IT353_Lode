import logic.GameField;
import logic.Player;
import logic.Ship;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PlayerTest {



    @Test
    public void updatePlayerFields() {
        Player p = new Player();
        HashSet<GameField> fieldsToTest = new HashSet<>();
        fieldsToTest.add(new GameField("A","1", GameField.FieldState.ship));
        fieldsToTest.add(new GameField("A","2", GameField.FieldState.ship));
        fieldsToTest.add(new GameField("A","3", GameField.FieldState.shipHit));
        ArrayList<Ship> playerShips = new ArrayList<Ship>();
        playerShips.add(new Ship("s1", 3, true, fieldsToTest));
        p.setShips(playerShips);
        p.updatePlayerFields();
        Assert.assertEquals(GameField.FieldState.ship, GameField.getFieldFromArrayByPosition(p.getFields(),"A","1").getFieldState());
        Assert.assertEquals(GameField.FieldState.ship, GameField.getFieldFromArrayByPosition(p.getFields(),"A","2").getFieldState());
        Assert.assertEquals(GameField.FieldState.shipHit, GameField.getFieldFromArrayByPosition(p.getFields(),"A","3").getFieldState());
        Assert.assertEquals(GameField.FieldState.empty, GameField.getFieldFromArrayByPosition(p.getFields(),"A","4").getFieldState());
    }

    @Test
    public void isAlive_true() {
        Player p = new Player();
        HashSet<GameField> fieldsToTest = new HashSet<>();
        fieldsToTest.add(new GameField("A","1", GameField.FieldState.ship));
        fieldsToTest.add(new GameField("A","2", GameField.FieldState.ship));
        fieldsToTest.add(new GameField("A","3", GameField.FieldState.shipHit));
        ArrayList<Ship> playerShips = new ArrayList<Ship>();
        playerShips.add(new Ship("s1", 3, true, fieldsToTest));
        p.setShips(playerShips);
        Boolean isAlive = p.isAlive();
        Assert.assertEquals(true, isAlive);
    }

    @Test
    public void isAlive_false() {
        Player p = new Player();
        HashSet<GameField> fieldsToTest = new HashSet<>();
        fieldsToTest.add(new GameField("A","1", GameField.FieldState.shipHit));
        fieldsToTest.add(new GameField("A","2", GameField.FieldState.shipHit));
        fieldsToTest.add(new GameField("A","3", GameField.FieldState.shipHit));
        ArrayList<Ship> playerShips = new ArrayList<Ship>();
        playerShips.add(new Ship("s1", 3, true, fieldsToTest));
        p.setShips(playerShips);
        Boolean isAlive = p.isAlive();
        Assert.assertEquals(false, isAlive);
    }


    @Test
    public void readyToPlay() {
        Player p = new Player();
        Boolean readyToPlay = p.readyToPlay();
        Assert.assertEquals(false, readyToPlay);
    }

    @Test
    public void testToString() {
        Player p = new Player();
        p.setId("Test");
        String toString = p.toString();
        Assert.assertEquals("null null Test", toString);
    }

    @Test
    public void testEquals() {
        GameField gameField = new GameField("A","1", GameField.FieldState.ship);
        try {
            Player p = new Player();
            p.ip = InetAddress.getLocalHost();
            p.port = 1;
            Player pTrue = new Player();
            pTrue.ip = InetAddress.getLocalHost();
            pTrue.port = 1;
            Player pFalse = new Player();
            pFalse.ip = InetAddress.getLocalHost();
            pFalse.port = 2;
            Boolean resultGameField = p.equals(gameField);
            Boolean resultTrue = p.equals(pTrue);
            Boolean resultFalse = p.equals(pFalse);
            Assert.assertEquals(false, resultGameField);
            Assert.assertEquals(true, resultTrue);
            Assert.assertEquals(false, resultFalse);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}