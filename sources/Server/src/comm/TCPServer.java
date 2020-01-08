package comm;

import logic.Game;
import logic.Player;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TCPServer implements ServerListener{
    private int port;
    private boolean open = true;
    private ServerSocket ss;
    private ArrayList<Socket> clients = new ArrayList<>();
    private HashMap<Integer, Game> games = new HashMap<>();
    private Integer lastInitiatedGameId;
    private Random rnd = new Random();
    public TCPServer(int port){
        try{
            ss=new ServerSocket(port);
            if(this.port==0)this.port=ss.getLocalPort();
            else this.port=port;
            Thread serverThread = new Thread(new Runnable(){
                public void run(){
                    while(open){
                        try{
                            @SuppressWarnings("resource")final Socket s = ss.accept();
                            Thread clientThread = new Thread(new Runnable(){
                                public void run(){
                                    try{
                                        clients.add(s);
                                        // get the input stream from the connected socket
                                        ObjectInputStream in = new ObjectInputStream(s.getInputStream());
                                        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
                                        Player client = new Player(s.getInetAddress(), s.getPort(), out);
                                        clientConnected(client, out);
                                        while(open){
                                            try {
                                                recievedInput(client, in.readObject());
                                            }catch(IOException e){
                                                clientDisconnected(client);
                                                try{
                                                    if(!s.isClosed()){
                                                        s.shutdownOutput();
                                                        s.close();
                                                    }
                                                }catch(Exception exception){ exception.printStackTrace(); }
                                                clients.remove(s);
                                                return;
                                            }
                                        }
                                    }catch(Exception exception){ exception.printStackTrace(); }
                                    try{ s.close();
                                    }catch(Exception exception){ exception.printStackTrace(); }
                                    clients.remove(s);
                                }
                            });
                            clientThread.setDaemon(true);
                            clientThread.setName("Client "+s.getInetAddress().toString());
                            clientThread.start();
                        }catch(SocketException e){  System.out.println("EXCEPT " + e.getMessage());
                        }catch(IOException e){ e.printStackTrace(); }
                    }
                }
            });
            serverThread.setDaemon(false);
            serverThread.setName("Server");
            serverThread.start();
        }catch(IOException e){ e.printStackTrace(); }
    }
    public void dispose(){
        open=false;
        try {
            ss.close();
        }catch(IOException e){ e.printStackTrace(); }
        for(Socket s : clients){
            try{ s.close();
            }catch(Exception exception){ exception.printStackTrace(); }
        }
        clients.clear();
        clients=null;
        ss=null;
        serverClosed();
    }
    public String getIp(){
        try{ ss.getInetAddress();
            return InetAddress.getLocalHost().getHostAddress();
        }catch(UnknownHostException e){ e.printStackTrace(); }
        return null;
    }
    @SuppressWarnings("resource")public void kickClient(Player client){
        Socket s;
        for(int i = 0; i<clients.size(); i++){
            s=clients.get(i);
            if(client.ip==s.getInetAddress()&&s.getPort()==client.port){
                try{
                    s.shutdownOutput();
                    s.close();
                }catch(IOException e){ e.printStackTrace(); }
                return;
            }
        }
    }

    @Override
    public void clientConnected(Player client, ObjectOutputStream out) {
        System.out.println("CLIENT " + client + " connected");
        handlePlayerSplitIntoGames(client);
    }

    @Override
    public void clientDisconnected(Player client) {
        System.out.println("CLIENT " + client + " disconnected");
    }

    @Override
    public void recievedInput(Player client, Object msg) {
        ClientDto dto = (ClientDto) msg;
        System.out.println("DTO " + client + " msg: " + dto.id);
        games.get(dto.gameId).processClientMessage(client, dto);
    }

    @Override
    public void serverClosed() {
        System.out.println("SERVER CLOSED");
    }

    private void handlePlayerSplitIntoGames(Player client) {
        if (games.get(lastInitiatedGameId) == null || games.get(lastInitiatedGameId).getPlayerB() != null) {
            Integer nextGameId = generateNextGameId();
            Game newGame = new Game(nextGameId);
            newGame.setPlayerA(client);
            games.put(nextGameId, newGame);
            lastInitiatedGameId = nextGameId;
            System.out.println("started new game " + lastInitiatedGameId);
        } else {
            games.get(lastInitiatedGameId).setPlayerB(client);
            System.out.println("player b assigned to a game: " + lastInitiatedGameId);
        }
        client.setGameId(lastInitiatedGameId);
        sendInitServerDto(client, lastInitiatedGameId);
        System.out.println("PLAYER: " + client);
    }

    private Integer generateNextGameId() {
        //TODO HANDLE MAX GAMES
        Integer gameId = rnd.nextInt(100);
        if (games.containsKey(gameId)) {
            return generateNextGameId();
        } else {
            return gameId;
        }
    }

    private void sendInitServerDto(Player client, Integer gameId) {
        ServerDto dto = new ServerDto();
        dto.id = client.getId();
        dto.playerPoints = client.getPoints();
        dto.gameState = Game.GameState.INITIALIZED;
        dto.gameId = gameId;
        try {
            client.out.writeObject(dto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
