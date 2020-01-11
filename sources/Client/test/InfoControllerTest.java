import Logic.App;
import UI.HomeController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Game;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.IOException;

import static org.junit.Assert.*;

public class InfoControllerTest extends ApplicationTest {
    private App app;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
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
    public void prepare(){
        app.player = "TEST";
        app.playerPoints = 4550;
        app.opponentId = "TEST2";
        app.opponentPoints = 0;
    }

    @Test
    public void waitForOtherPlayer() {
        Platform.runLater(() ->{
            app.gameState = Game.GameState.WAITING_FOR_OTHER_PLAYER;

            try {
                app.getInfoScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }

            assertTrue(app.infoController.instructions.getText().equals("Prosím, vyčkejte na soupeře"));
            assertFalse(app.infoController.newGame.isVisible());

        });
    }

    @Test
    public void youWon() {
        Platform.runLater(() ->{
            app.gameState = Game.GameState.WIN;

            try {
                app.getInfoScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }

            assertTrue(app.infoController.instructions.getText().equals("Gratulujeme, vyhráváte! Úspěšně jste potopil všechny lodě soupeře TEST2"));
            assertTrue(app.infoController.newGame.isVisible());
        });
    }

    @Test
    public void youLost() {
        Platform.runLater(() ->{
            app.gameState = Game.GameState.LOSS;

            try {
                app.getInfoScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }

            assertTrue(app.infoController.instructions.getText().equals("Bohužel, soupeř TEST2 potopil všechny Vaše lodě, hru prohráváte."));
            assertTrue(app.infoController.newGame.isVisible());
        });
    }

}