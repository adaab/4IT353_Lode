package Logic;

import Logic.ClientListener;
import comm.ClientDto;
import comm.ServerDto;
import javafx.application.Platform;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client implements ClientListener {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean open = true;
    private App app;
    private Thread main;
    public Client(String ip, int port, App app, Thread main){
        this.app = app;
        this.main = main;
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
    public void send(ClientDto msg) throws IOException {
        if(open){
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