package UI;

import Logic.App;
import Logic.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class InfoController implements Observer {

    @FXML
    public Label instructions;
    @FXML
    public Button newGame;
    @FXML
    public Label loggedUser;
    @FXML
    public ImageView image;


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

    public void youWon(){
        instructions.setText("Gratulujeme, vyhráváte! Úspěšně jste potopil všechny lodě soupeře "+app.opponentId);
        image.setImage(new Image("/icons/win.png"));
        newGame.setVisible(true);
    }

    public void youLost(){
        instructions.setText("Bohužel, soupeř "+app.opponentId+" potopil všechny Vaše lodě, hru prohráváte.");
        image.setImage(new Image("/icons/fire.png"));
        newGame.setVisible(true);
    }

    public void playNewGame() throws IOException {
        app.initNewGame();
    }
}
