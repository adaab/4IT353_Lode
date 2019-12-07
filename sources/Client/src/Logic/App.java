package Logic;

import Logic.Observer;
import Logic.Subject;
import UI.HomeController;
import UI.InfoController;
import comm.ServerDto;
import logic.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import comm.ServerDto;

public class App implements Subject {
    private Set<Observer> Observers;
    public Client server;
    public String player;
    public Game.GameState gameState;
    public Integer playerPoints;
    public ArrayList playerField;
    public String opponentId;
    public Integer opponentPoints;
    public ArrayList opponentField;
    public Stage stage;
    public HomeController controller;


    public App(Stage stage, HomeController controller) {
        Observers = new HashSet<>();
        server = new Client("localhost", 8888, this);
        this.stage = stage;
        this.controller = controller;
    }

    @Override
    public Observer register(Observer observer) {
        boolean vysledek = Observers.add(observer);
        if (vysledek) return observer;
        return null;
    }

    /**
     * metoda umožňuje odebrat pozorovatele
     *
     * @param observer
     * @return pozorovatel, nebo null při neúspěšné registraci
     */
    @Override
    public Observer unregister(Observer observer) {
        boolean vysledek = Observers.remove(observer);
        if (vysledek) return observer;
        return null;
    }

    /**
     * metoda upozorní pozorovatele na změnu
     */
    @Override
    public void notifyObservers() {
        for (Observer pozorovatel : Observers) {
            pozorovatel.update();
        }
    }

    public Client getServer() {
        return server;
    }

    public void processResponse(ServerDto dto) throws IOException {
        if (dto.error != null) {
            //TODO somehow handle the error
        } else {
            if (dto.gameState.equals(Game.GameState.WAITING_FOR_OTHER_PLAYER)) {
                this.player = dto.id;
                getInfoScreen();
                //TODO inicializace obrazovky "čekám"
            } else {
                if (dto.gameState.equals("NEW")) {
                    this.gameState = dto.gameState;
                    this.player = dto.id;
                    this.playerPoints = dto.playerPoints;
                    this.playerField = dto.playerFields;
                    this.opponentId = dto.opponentId;
                    this.opponentPoints = dto.opponentPoints;
                    this.opponentField = dto.opponentField;
                    //TODO inicializuje novou hru - zadávání svých lodí
                } else {
                    if (dto.gameState.equals("PLAYING")) {
                        this.playerPoints = dto.playerPoints;
                        this.playerField = dto.playerFields;
                        this.opponentPoints = dto.opponentPoints;
                        this.opponentField = dto.opponentField;
                    } else {
                        if (dto.gameState.equals("WIN")) {
                            //TODO inicializuje obrazovku konec a zobrazí výhru
                        } else {
                            if (dto.gameState.equals("LOSS")) {
                                //TODO inicializuje obrazovku konec a zobrazí prohru
                            }
                        }
                    }
                }
            }
        }
    }

    public void getInfoScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/info.fxml"));
        VBox root = loader.load();
        InfoController controller = loader.getController();

        Scene scene = new Scene(root, 1040, 800);
        stage.setScene(scene);
        stage.setTitle("Battleship");
        scene.getStylesheets().add("styles.css");
        controller.inicializuj(this);
        controller.waitForOtherPlayer();
        }

        public void getGameScreen() throws IOException{

        }
}

