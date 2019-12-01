package UI;

import Logic.App;
import Logic.Client;
import Logic.Observer;
import comm.ClientDto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;


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

    public void logIn(){
        vstoupit.disableProperty();
        name.disableProperty();
        contentLoaderPane.setVisible(true);
        contentLoader.setVisible(true);
        String jmeno = name.getText();
        ClientDto logIn = new ClientDto();
        logIn.id = jmeno;
        try{
            app.getServer().send(logIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
