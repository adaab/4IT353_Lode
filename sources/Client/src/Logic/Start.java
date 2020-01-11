package Logic;

import Logic.App;
import UI.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Start extends Application {
    public static void main(String[] args){
        Start.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Thread main = Thread.currentThread();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/home.fxml"));
        VBox root = loader.load();
        HomeController controller = loader.getController();

        primaryStage.setTitle("Battleship");
        Scene scene = new Scene(root, 1040, 800);
        primaryStage.setScene(scene);
        scene.getStylesheets().add("styles.css");
        primaryStage.show();

        App app = new App(primaryStage, controller);
        controller.inicializuj(app);
    }

}
