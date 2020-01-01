package UI;

import Logic.App;
import Logic.Client;
import Logic.Observer;
import comm.ClientDto;
import comm.ServerDto;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.io.IOException;

import static logic.Game.GameState.NEW;
import static logic.Game.GameState.WAITING_FOR_OTHER_PLAYER;


public class HomeController implements Observer {

@FXML
public Button vstoupit;
@FXML
public TextField name;
@FXML
public ProgressIndicator contentLoader;
@FXML
public Pane contentLoaderPane;

    private App app;

    @Override
    public void update() {
    }

    public void inicializuj(App app){
        this.app = app;
        app.register(this);
        update();
    }

    public void logIn() throws IOException {
        vstoupit.disableProperty();
        name.disableProperty();
        contentLoaderPane.setVisible(true);
        contentLoader.setVisible(true);
        String jmeno = name.getText();
        ClientDto logIn = new ClientDto();
        logIn.id = jmeno;
        logIn.gameId = app.gameId;

        /* TODO AFTER SERVER CAN RESPOND
        try{
            app.getServer().send(logIn);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        ServerDto dto = new ServerDto();
        dto.id = "Pepa";
        dto.gameState = NEW;
        app.processResponse(dto);
    }
}
