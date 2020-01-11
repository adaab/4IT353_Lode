import Logic.App;
import UI.GameController;
import UI.HomeController;
import comm.ServerDto;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Game;
import logic.GameField;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

import static logic.Game.GameState.NEW;
import static logic.Game.GameState.PLAYING;
import static org.junit.Assert.*;

public class GameControllerTest extends ApplicationTest {
    private App app;
    private Stage stage;

    @Override
    public void start (Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/home.fxml"));
        VBox root = loader.load();
        HomeController controller = loader.getController();

        stage.setTitle("Battleship");
        Scene scene = new Scene(root, 1040, 800);
        stage.setScene(scene);
        scene.getStylesheets().add("styles.css");
        stage.show();

        app = new App(stage, controller);
        controller.inicializuj(app);
        this.stage = stage;
    }

    @Before
    public void prepare() {
        app.stage = stage;
        app.player = "TEST1";
        app.opponentId = "TEST2";
    }

    @Test
    public void handleFieldSelect() {
        app.gameState = NEW;
        Platform.runLater(() -> {
            try {
                app.getGameScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }

            app.gameController.source = app.gameController.A5;
            app.gameController.handleFieldSelectNewField("A5");

            assertTrue(app.gameController.currentShip.getName().equals("ship1"));
            assertEquals(1,app.gameController.buttonsWithShips.size());
            assertEquals(1,app.gameController.ship1.getPositions().size());
        });

    }

    @Test
    public void handleFieldSelectNewField() {
        app.gameState = NEW;
        Platform.runLater(() -> {
            try {
                app.getGameScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }

            app.gameController.source = app.gameController.D9;
            app.gameController.handleFieldSelectNewField("D9");
            app.gameController.source = app.gameController.E9;
            app.gameController.handleFieldSelectNewField("E9");

            assertTrue(app.gameController.currentShip.getName().equals("ship1"));
            assertEquals(2,app.gameController.buttonsWithShips.size());
            assertEquals(2,app.gameController.ship1.getPositions().size());

        });
    }

    @Test
    public void setPlayingScene() {
        Platform.runLater(() -> {
            try {
                app.getGameScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
            app.gameController.setPlayingScene();

            assertFalse(app.gameController.boatSelect.isVisible());
            assertTrue(app.gameController.viewField.isVisible() && app.gameController.viewField.isDisabled());
            assertTrue(app.gameController.score.isVisible());
        });
    }

    @Test
    public void updateGame() {
        app.gameState = PLAYING;

        Platform.runLater(() -> {
            try {
                app.getGameScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
            app.gameController.setPlayingScene();

            ArrayList<GameField> playerFields = new ArrayList<>();
            String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j <= 12; j++) {
                    playerFields.add(new GameField(letters[i], String.valueOf(j), GameField.FieldState.empty));
                }
            }
            playerFields.get(25).setFieldState(GameField.FieldState.ship);
            playerFields.get(47).setFieldState(GameField.FieldState.shipHit);
            playerFields.get(55).setFieldState(GameField.FieldState.missed);
            app.playerField = playerFields;
            app.opponentPoints = 25;
            app.playerPoints = 45;

            ArrayList<GameField> opponentFields = new ArrayList<>();

            String[] letters2 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j <= 12; j++) {
                    opponentFields.add(new GameField(letters2[i], String.valueOf(j), GameField.FieldState.empty));
                }
            }
            opponentFields.get(25).setFieldState(GameField.FieldState.missed);
            opponentFields.get(47).setFieldState(GameField.FieldState.shipHit);

            app.opponentField = opponentFields;
            app.isMyTurn = true;

            app.gameController.updateGame();

            assertTrue(app.gameController.mainFieldButtons.get(25).getStyle().equals("-fx-background-color: #968d8d"));
            assertTrue(app.gameController.mainFieldButtons.get(47).getStyle().equals("-fx-background-color: #216164"));

            assertTrue(app.gameController.viewFieldButtons.get(25).getStyle().equals("-fx-background-color: #216164"));
            assertTrue(app.gameController.viewFieldButtons.get(47).getStyle().equals("-fx-background-color: #9E4751"));
            assertTrue(app.gameController.viewFieldButtons.get(55).getStyle().equals("-fx-background-color: #968d8d"));
        });
    }

    @Test
    public void validateShip() {
        app.gameState = NEW;
        Platform.runLater(() -> {
            try {
                app.getGameScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }

            app.gameController.source = app.gameController.D9;
            app.gameController.handleFieldSelectNewField("D9");
            app.gameController.source = app.gameController.D10;
            app.gameController.handleFieldSelectNewField("D10");
            app.gameController.source = app.gameController.D11;
            app.gameController.handleFieldSelectNewField("D11");

            assertTrue(app.gameController.currentShip.getName().equals("ship1"));
            assertEquals(3,app.gameController.buttonsWithShips.size());
            assertEquals(3,app.gameController.ship1.getPositions().size());
            assertEquals(1,app.gameController.ships.size());

            app.gameController.source = app.gameController.A1;
            app.gameController.handleFieldSelectNewField("A1");
            app.gameController.source = app.gameController.E9;
            app.gameController.handleFieldSelectNewField("E9");
            app.gameController.source = app.gameController.M3;
            app.gameController.handleFieldSelectNewField("M3");

            assertTrue(app.gameController.currentShip.getName().equals("ship2"));
            assertEquals(6,app.gameController.buttonsWithShips.size());
            assertEquals(3,app.gameController.ship2.getPositions().size());

            assertEquals(1,app.gameController.ships.size());
            assertTrue(app.gameController.score.getText().equals("Vybraná pole netvoří tvar požadované lodi, prosím zvolte správná pole"));

        });
    }
}