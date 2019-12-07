package UI;

import Logic.App;
import Logic.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class InfoController implements Observer {

    @FXML
    public Label instructions;
    @FXML
    public Button newGame;
    @FXML
    public Label loggedUser;

    private App app;

    @Override
    public void update() {
    }

    public void inicializuj(App app){
        this.app = app;
        app.register(this);
        update();
    }

    public void waitForOtherPlayer(){
        instructions.setText("Prosím, vyčkejte na soupeře");
        loggedUser.setText(app.player);
    }
}
