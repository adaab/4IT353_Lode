package UI;

import Logic.App;
import Logic.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class InfoController implements Observer {

    @FXML
    public Label instructions;
    @FXML
    public Button newGame;
    @FXML
    public Label loggedUser;
    @FXML
    public ImageView image;

    /**
     *  Třída InfoController - třída obsluhující obrazovku
     *  zajišťující zobrazování stavů "waiting for other player", "win" a "loss"
     *
     *
     *@author     Ada
     *@version    1.0
     *@created    prosinec 2019
     */

    private App app;

    @Override
    public void update() {
    }

    /**
     * metoda přiřadí aplikaci kontroleru a zaregistuje
     *
     * @param app
     */
    public void inicializuj(App app){
        this.app = app;
        app.register(this);
        update();
    }

    /**
     * metoda upraví obrazovku do stavu "waiting for other player"
     *
     */
    public void waitForOtherPlayer(){
        instructions.setText("Prosím, vyčkejte na soupeře");
        loggedUser.setText(app.player);
    }

    /**
     * metoda upraví obrazovku do stavu "won" a nabídne možnost spustit novou hru
     *
     */
    public void youWon(){
        instructions.setText("Gratulujeme, vyhráváte! Úspěšně jste potopil všechny lodě soupeře "+app.opponentId);
        image.setImage(new Image("/icons/win.png"));
        loggedUser.setText(app.player);
        newGame.setVisible(true);
    }

    /**
     * metoda upraví obrazovku do stavu "loss" a nabídne možnost spustit novou hru
     *
     */
    public void youLost(){
        instructions.setText("Bohužel, soupeř "+app.opponentId+" potopil všechny Vaše lodě, hru prohráváte.");
        image.setImage(new Image("/icons/fire.png"));
        loggedUser.setText(app.player);
        newGame.setVisible(true);
    }

    /**
     * metoda spouštěná po zvolení "Nová hra" - nechá inicializovat novou hru
     *
     */
    public void playNewGame() throws IOException {
        app.initNewGame();
    }

    public void showHelp(){
        if (Desktop.isDesktopSupported()) {
            try {
                URL url = getClass().getResource("/userGuide.pdf");
                File myFile = new File(url.toURI());
                Desktop.getDesktop().open(myFile);
            } catch (IOException | URISyntaxException ex) {
                // no application registered for PDFs
            }
        }
    }
}
