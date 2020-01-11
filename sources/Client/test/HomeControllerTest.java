import Logic.App;
import UI.HomeController;
import comm.Error;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.*;

public class HomeControllerTest extends ApplicationTest{
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

    @Test
    public void handleUserExists() {
        Platform.runLater(() -> {
        Error error = new Error(Error.Code.userExists,"user exists");
        app.error = error;
        app.controller.handleUserExists();

        assertTrue(app.controller.instructions.isVisible());
        assertFalse(app.controller.name.isDisabled());
        assertFalse(app.controller.vstoupit.isDisabled());
        });
    }
}