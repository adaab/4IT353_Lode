import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements ClientListener{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private boolean open = true;
    public Client(String ip, int port){
        try{
            socket=new Socket(ip, port);
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream(), true);
            Thread clientThread = new Thread(new Runnable(){
                public void run(){
                    while(open){
                        try{
                            String s = in.readLine();
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
            clientThread.setName("Client Connection");
            clientThread.setDaemon(true);
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
    public void dispose(){
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
    }
    public void send(String msg){ if(open)out.println(msg); }
    public boolean isConnected(){ return open; }

    @Override
    public void unknownHost() {

    }

    @Override
    public void couldNotConnect() {

    }

    @Override
    public void recivedInput(String msg) {

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