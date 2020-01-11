package logic;

import comm.ClientDto;
import org.junit.Assert;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void startNewGame() {
        Game game = new Game(1);
        try {
            Player pA = new Player();
            pA.setId("pA");
            pA.ip = InetAddress.getLocalHost();
            pA.port = 1;
            Player pB = new Player();
            pB.setId("pB");
            pB.ip = InetAddress.getLocalHost();
            pB.port = 2;
            game.setPlayerA(pA);
            game.setPlayerB(pB);
            game.startNewGame();
            assertEquals(pA, game.getCurrentlyPlaying());
            assertEquals(Game.GameState.NEW, game.getCurrentGameState());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCurrentlyNotPlayingPlayer() {
        Game game = new Game(1);
        try {
            Player pA = new Player();
            pA.setId("pA");
            pA.ip = InetAddress.getLocalHost();
            pA.port = 1;
            Player pB = new Player();
            pB.setId("pB");
            pB.ip = InetAddress.getLocalHost();
            pB.port = 2;
            game.setPlayerA(pA);
            game.setPlayerB(pB);
            game.setCurrentlyPlaying(pB);
            assertEquals(pA, game.getCurrentlyNotPlayingPlayer());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getOpponentForPlayer() {
        Game game = new Game(1);
        try {
            Player pA = new Player();
            pA.setId("pA");
            pA.ip = InetAddress.getLocalHost();
            pA.port = 1;
            Player pB = new Player();
            pB.setId("pB");
            pB.ip = InetAddress.getLocalHost();
            pB.port = 2;
            game.setPlayerA(pA);
            game.setPlayerB(pB);
            assertEquals(pA, game.getOpponentForPlayer(pB));
            assertEquals(pB, game.getOpponentForPlayer(pA));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processClientMessage_setPlayerId() {
        //ClientDto =  new
    }
}