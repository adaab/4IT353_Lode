import Logic.App;
import UI.HomeController;
import comm.ServerDto;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Game;
import logic.GameField;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AppTest {
    private App app;
    private ServerDto dto;
    private static Stage stage;
    private static HomeController controller;

    @BeforeClass
    public static void prepareToolikt() {
        Platform.startup(new Runnable() {
            @Override
            public void run() {
                stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/home.fxml"));
                VBox root = null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                controller = loader.getController();

                stage.setTitle("Battleship");
                Scene scene = new Scene(root, 1040, 800);
                stage.setScene(scene);
                scene.getStylesheets().add("styles.css");
                stage.show();
            }
        });
    }

    @Before
    public void prepare(){

        app = new App(stage, controller);
        controller.inicializuj(app);
        dto = new ServerDto();
        app.stage = stage;
        app.controller = controller;
    }

    @Test
    public void processResponseINIT() {
        dto.gameState = Game.GameState.INITIALIZED;
        dto.gameId = 456;
        try {
            app.processResponse(dto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(app.gameId.equals(456));
    }

    @Test
    public void processResponseNEW() {
        dto.gameId = 456;
        dto.gameState = Game.GameState.NEW;
        dto.opponentId = "TEST";
        dto.id = "TEST2";
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    app.processResponse(dto);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assertTrue(app.opponentId.equals("TEST"));
                assertTrue(app.player.equals("TEST2"));
                assertNotNull(app.gameController);
            }
            });

    }

    @Test
    public void processResponseGAME() {
        dto.gameId = 456;
        dto.gameState = Game.GameState.PLAYING;
        dto.opponentId = "TEST";
        dto.id = "TEST2";

        ArrayList<GameField> playerFields = new ArrayList<>();
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
        for (int i = 0; i < 16; i++) {
            for (int j = 1; j <= 12; j++) {
                playerFields.add(new GameField(letters[i], String.valueOf(j), GameField.FieldState.empty));
            }
        }
        playerFields.get(25).setFieldState(GameField.FieldState.ship);
        playerFields.get(5).setFieldState(GameField.FieldState.shipHit);
        dto.playerFields = playerFields;
        dto.opponentPoints = 25;
        dto.playerPoints = 45;

        ArrayList<GameField> opponentFields = new ArrayList<>();

        String[] letters2 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
        for (int i = 0; i < 16; i++) {
            for (int j = 1; j <= 12; j++) {
                opponentFields.add(new GameField(letters2[i], String.valueOf(j), GameField.FieldState.empty));
            }
        }
        opponentFields.get(25).setFieldState(GameField.FieldState.missed);
        opponentFields.get(47).setFieldState(GameField.FieldState.shipHit);

        dto.opponentField = opponentFields;
        dto.isMyTurn = true;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    app.processResponse(dto);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assertNotNull(app.gameController);
                assertTrue(app.playerPoints.equals(45));
                assertTrue(app.opponentPoints.equals(25));
            }});
    }

    @Test
    public void processResponseWIN(){
        dto.gameId = 456;
        dto.gameState = Game.GameState.WIN;
        dto.opponentId = "TEST";
        dto.id = "TEST2";
        dto.opponentPoints = 48;
        dto.playerPoints = 220;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    app.processResponse(dto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                assertNotNull(app.infoController);
            }});
    }

}