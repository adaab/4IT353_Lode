package Logic;

import Logic.ClientListener;
import comm.ClientDto;
import comm.ServerDto;
import javafx.application.Platform;
import logic.Game;
import logic.GameField;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import static logic.Game.GameState.LOSS;
import static logic.Game.GameState.WIN;

/**
 *  Třída Client - obsluhuje vlákno zajišťující
 *  přijmutí zprávy ze serveru, zajišťuje i odesílání zpráv na server
 *
 *
 *@author     Ada
 *@version    1.0
 *@created    prosinec 2019
 */

public class Client implements ClientListener {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean open = true;
    private App app;
    public Client(String ip, int port, App app){
        this.app = app;
        try{
            socket=new Socket(ip, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in =new ObjectInputStream(socket.getInputStream());
            Thread clientThread = new Thread(new Runnable(){
                public void run(){
                    //TODO SEND INIT, this is just for testing purposes
                    /*
                    ClientDto dto = new ClientDto();
                    dto.id = "MOJE Jméno";
                    try {
                        send(dto);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    while(open){
                        try{
                            Object s = in.readObject();
                            if(s==null){
                                open=false;
                                disconnected();
                                try{ if(socket!=null)socket.close();
                                }catch(Exception exception){ exception.printStackTrace(); }
                                try{ if(in!=null)in.close();
                                }catch(Exception exception){ exception.printStackTrace(); }
                                try{ if(out!=null)out.close();
                                }catch(Exception exception){ exception.printStackTrace(); }
                                return;
                            }
                            recivedInput(s);
                        }catch(IOException exception){
                            open=false;
                            serverClosed();
                            try{ socket.close();
                            }catch(Exception exception1){ exception.printStackTrace(); }
                            try{ in.close();
                            }catch(Exception exception1){ exception.printStackTrace(); }
                            try{ out.close();
                            }catch(Exception exception1){ exception.printStackTrace(); }
                            return;
                        }catch(Exception exception){ exception.printStackTrace(); }
                    }
                }
            });
            clientThread.setName("Logic.Client Connection");
            clientThread.setDaemon(false);
            clientThread.start();
            connectedToServer();
        }catch(UnknownHostException exception){
            open=false;
            unknownHost();
        }catch(IOException exception){
            open=false;
            couldNotConnect();
        }catch(Exception exception){
            open=false;
            exception.printStackTrace();
        }
    }
    /*public void dispose(){
        try{
            if(open){
                open=false;
                socket.close();
                in.close();
                out.close();
                disconnected();
            }
            socket=null;
            in=null;
            out=null;
        }catch(Exception exception){ exception.printStackTrace();}
    }*/

    /**
     * metoda odesílá klientskou zprávu na server
     *
     * @param msg
     * @author Ada
     */
    public void send(ClientDto msg) throws IOException {
        //TODO this is just for testing purposes
        /*if(app.gameState.equals(Game.GameState.NEW)){
            ServerDto dto = new ServerDto();
            dto.gameState = LOSS;
            dto.playerPoints = 28;
            dto.opponentPoints = 44;
            app.processResponse(dto);
        }

        if (app.gameState.equals(Game.GameState.NEW)) {
            ServerDto dto = new ServerDto();
            dto.gameState = Game.GameState.PLAYING;
            ArrayList<GameField> playerFields = new ArrayList<>();
            String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
            for (int i = 0; i < 16; i++) {
                for (int j = 1; j <= 12; j++) {
                    playerFields.add(new GameField(letters[i], String.valueOf(j), GameField.FieldState.empty));
                }
            }
            playerFields.get(25).setFieldState(GameField.FieldState.ship);
            playerFields.get(38).setFieldState(GameField.FieldState.ship);
            playerFields.get(44).setFieldState(GameField.FieldState.ship);
            playerFields.get(11).setFieldState(GameField.FieldState.ship);
            playerFields.get(5).setFieldState(GameField.FieldState.shipHit);
            playerFields.get(27).setFieldState(GameField.FieldState.shipHit);
            playerFields.get(47).setFieldState(GameField.FieldState.shipHit);
            dto.playerFields = playerFields;
            dto.opponentPoints = 25;
            dto.playerPoints = 45;

            ArrayList<GameField> opponentFields = new ArrayList<>();

            String[] letters2 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P"};
            for (int i = 0; i < 16; i++) {
                for (int j = 1; j <= 12; j++) {
                    opponentFields.add(new GameField(letters2[i], String.valueOf(j), GameField.FieldState.empty));
                }
            }
            opponentFields.get(25).setFieldState(GameField.FieldState.missed);
            opponentFields.get(38).setFieldState(GameField.FieldState.missed);
            opponentFields.get(44).setFieldState(GameField.FieldState.missed);
            opponentFields.get(11).setFieldState(GameField.FieldState.missed);
            opponentFields.get(5).setFieldState(GameField.FieldState.shipHit);
            opponentFields.get(27).setFieldState(GameField.FieldState.shipHit);
            opponentFields.get(47).setFieldState(GameField.FieldState.shipHit);

            dto.opponentField = opponentFields;
            dto.isMyTurn = true;

            app.processResponse(dto);
        } else {*/
            if(open) {
            out.writeObject(msg);
            out.flush();
            System.out.println("FLUSHED");
        }
    }

    public boolean isConnected(){ return open; }

    @Override
    public void unknownHost() {

    }

    @Override
    public void couldNotConnect() {

    }

    /**
     * metoda přijme a zaloguje nově příchozí zprávu ze serveru, spustí nové vlákno, které provede zpracování odpovědi
     *
     * @author Ada
     * @param msg
     */
    @Override
    public void recivedInput(Object msg) throws IOException {
        ServerDto dto = (ServerDto) msg;
        System.out.println("DTO " + dto + " msg: " + dto.playerPoints);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    app.processResponse(dto);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void serverClosed() {

    }

    @Override
    public void disconnected() {

    }

    @Override
    public void connectedToServer() {

    }
}