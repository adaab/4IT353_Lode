package UI;

import Logic.App;
import Logic.Observer;


public class HomeController implements Observer {

    private App app;

    @Override
    public void update() {
    }

    public void inicializuj(App app){
        this.app = app;
        app.register(this);
        update();
    }
}
