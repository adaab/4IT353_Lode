package Logic;

import Logic.Observer;
import Logic.Subject;
import comm.ServerDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class App implements Subject {
    private Set<Observer> Observers;
    public Client server;
    public String player;
    public String gameState;
    public Integer playerPoints;
    public ArrayList playerField;
    public String opponentId;
    public Integer opponentPoints;
    public ArrayList opponentField;


    public App() {
        Observers = new HashSet<>();
        server = new Client("localhost", 8888, this);
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

    public void processResponse(ServerDto dto) {
        if (dto.error != null) {
            //TODO somehow handle the error
        } else {
            if (dto.gameState.equals("WAITING_FOR_OTHER_PLAYER")) {
                this.player = dto.id;
                //TODO inicializace obrazovky "čekám"
            } else {
                if (dto.gameState.equals("NEW")) {
                    this.gameState = dto.gameState;
                    this.player = dto.id;
                    this.playerPoints = dto.playerPoints;
                    this.playerField = dto.playerField;
                    this.opponentId = dto.opponentId;
                    this.opponentPoints = dto.opponentPoints;
                    this.opponentField = dto.opponentField;
                    //TODO inicializuje novou hru - zadávání svých lodí
                } else {
                    if (dto.gameState.equals("PLAYING")) {
                        this.playerPoints = dto.playerPoints;
                        this.playerField = dto.playerField;
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
}