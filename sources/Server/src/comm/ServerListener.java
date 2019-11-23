package comm;

import logic.Player;

import java.io.PrintWriter;

public interface ServerListener {
    public void clientConnected(Player client, PrintWriter out);
    public void clientDisconnected(Player client);
    public void recivedInput(Player client, String msg);
    public void serverClosed();
}
