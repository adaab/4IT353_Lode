package comm;

import logic.Player;

import java.io.ObjectOutputStream;

public interface ServerListener {
    public void clientConnected(Player client, ObjectOutputStream out);
    public void clientDisconnected(Player client);
    public void recievedInput(Player client, Object msg);
    public void serverClosed();
}
