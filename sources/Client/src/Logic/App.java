package Logic;

import Logic.Observer;
import Logic.Subject;

import java.util.HashSet;
import java.util.Set;

public class App implements Subject {
    private Set<Observer> Observers;
    public Client server;

    public App(){
        Observers = new HashSet<>();
        server = new Client("localhost", 8888);
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

    public Client getServer(){
        return server;
    }
}
