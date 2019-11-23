package comm;

import logic.Player;

import java.io.PrintWriter;

public interface ServerListener {
    public void clientConnected(Player client, PrintWriter out);
    public void clientDisconnected(Player client);
    public void recievedInput(Player client, Object msg);
    public void serverClosed();
}
