package test;

import logic.Game;
import logic.GameField;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameFieldTest {

    @Test
    public void testEquals() {
        Game g = new Game(1);
        GameField gf = new GameField("A","1", GameField.FieldState.empty);
        GameField gfTrue = new GameField("A","1", GameField.FieldState.empty);
        GameField gfFalse = new GameField("A","2", GameField.FieldState.empty);

        Boolean resultFalse1 = gf.equals(g);
        Boolean resultFalse2 = gf.equals(gfFalse);
        Boolean resultTrue = gf.equals(gfTrue);

        Assert.assertEquals(false, resultFalse1);
        Assert.assertEquals(false, resultFalse2);
        Assert.assertEquals(true, resultTrue);

    }

    @Test
    public void getFieldFromArrayByPosition() {

    }
}