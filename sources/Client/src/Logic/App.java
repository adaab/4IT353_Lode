package Logic;

import Logic.Observer;
import Logic.Subject;
import UI.GameController;
import UI.HomeController;
import UI.InfoController;
import comm.Error;
import comm.ServerDto;
import logic.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import comm.ServerDto;
import logic.GameField;

/**
 *  Třída App - obsluhuje a uchovává aktuální stav hry,
 *  rozhoduje o spouštění odpovídajících obrazovek,
 *  logicky zpracovává příchozí zprávy ze serveru
 *
 *
 *@author     Ada
 *@version    1.0
 *@created    prosinec 2019
 */
public class App implements Subject {
    private Set<Observer> Observers;
    public Client server;
    public String player;
    public Game.GameState gameState;
    public Integer playerPoints;
    public ArrayList<GameField> playerField;
    public String opponentId;
    public Integer opponentPoints;
    public ArrayList<GameField> opponentField;
    public Stage stage;
    public HomeController controller;
    public GameController gameController;
    public Integer gameId;
    public Boolean isMyTurn;
    public InfoController infoController;
    public Error error;

    public App(Stage stage, HomeController controller) {
        this.stage = stage;
        this.controller = controller;

        Observers = new HashSet<>();
        server = new Client("localhost", 8889, this);
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

    /**
     * metoda zpracuje příchozí dto ze serveru, aktualizuje potřebné datové atributy a spustí/aktualizuje potřebnou obrazovku
     *
     * @param dto

     */
    public void processResponse(ServerDto dto) throws IOException {
        if (dto.error != null) {
            this.error = dto.error;
            System.out.println("TTTT ERRROR  " + error.descr);
            if(error.type.equals(Error.Code.userExists)){
                controller.handleUserExists();
            } else {
                switch (error.type){
                    case fieldNotExists:
                        break;
                    case fieldAlreadyHit:
                        break;
                }
            }

        } else {
            switch (dto.gameState) {
                case INITIALIZED:
                    this.gameId = dto.gameId;
                    break;
                case WAITING_FOR_OTHER_PLAYER:
                    System.out.println("WAITING FOR OTHER PLAYER");
                    this.player = dto.id;
                    this.gameId = dto.gameId;
                    this.gameState = dto.gameState;
                    getInfoScreen();
                    //TODO inicializace obrazovky "čekám"
                    break;
                case NEW:
                    this.gameId = dto.gameId;
                    this.gameState = dto.gameState;
                    this.player = dto.id;
                    //this.playerPoints = dto.playerPoints;
                    //this.playerField = dto.playerFields;
                    this.opponentId = dto.opponentId;
                    //this.opponentPoints = dto.opponentPoints;
                    //this.opponentField = dto.opponentField;
                    getGameScreen();
                    //TODO inicializuje novou hru - zadávání svých lodí
                    break;
                case PLAYING:
                    getGameScreen();
                    this.gameState = dto.gameState;
                    this.playerPoints = dto.playerPoints;
                    this.playerField = dto.playerFields;
                    this.opponentPoints = dto.opponentPoints;
                    this.opponentField = dto.opponentField;
                    for (GameField field : dto.playerFields) {
                        System.out.println("PLAYER FIELDS: " + field.getX() + " " + field.getY() + " " + field.getFieldState());
                    }
                    for (GameField field : dto.playerFields) {
                        System.out.println("OPPONENT FIELDS: " + field.getX() + " " + field.getY() + " " + field.getFieldState());
                    }
                    this.isMyTurn = dto.isMyTurn;
                    this.gameController.updateGame();
                    this.gameController.setPlayingScene();
                    break;
                case WIN:
                    this.gameState = dto.gameState;
                    this.playerPoints = dto.playerPoints;
                    this.opponentPoints = dto.opponentPoints;
                    getInfoScreen();
                    break;
                case LOSS:
                    this.gameState = dto.gameState;
                    this.playerPoints = dto.playerPoints;
                    this.opponentPoints = dto.opponentPoints;
                    getInfoScreen();
            }
        }
    }

    /**
     * metoda inicializuje Info screen - obrazovku zobrazující se v případě stavů "Waiting for other player", "win" nebo "loss"
     *

     */
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

        if (gameState.equals(Game.GameState.WAITING_FOR_OTHER_PLAYER)) {
            controller.waitForOtherPlayer();
        } else {
            if (gameState.equals(Game.GameState.WIN)) {
                controller.youWon();
            } else {
                controller.youLost();
            }
        }
        this.infoController = infoController;
    }

    /**
     * metoda inicializuje Game screen - obrazovku zobrazující se v případě stavů "Waiting for other player", "win" nebo "loss"
     *

     */
        public void getGameScreen() throws IOException{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/game.fxml"));
            VBox root = loader.load();
            GameController gameController = loader.getController();
            Scene scene = new Scene(root, 1040, 800);
            stage.setScene(scene);
            stage.setTitle("Battleship");
            scene.getStylesheets().add("styles.css");
            gameController.inicializuj(this);
            gameController.prepareNewGame();
            this.gameController = gameController;
        }

    public ArrayList<GameField> getPlayerField() {
        return playerField;
    }

    public ArrayList<GameField> getOpponentField() {
        return opponentField;
    }

    /**
     * metoda inicializuje novou hru - vrací na přihlašovací obrazovku
     *

     */
    public void initNewGame() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/home.fxml"));
        VBox root = loader.load();
        HomeController controller = loader.getController();

        stage.setTitle("Battleship");
        Scene scene = new Scene(root, 1040, 800);
        stage.setScene(scene);
        scene.getStylesheets().add("styles.css");

        controller.inicializuj(this);
    }

}