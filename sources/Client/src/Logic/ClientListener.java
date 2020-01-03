package Logic;

import java.io.IOException;

public interface ClientListener{
    public void unknownHost();
    public void couldNotConnect();
    public void recivedInput(Object msg) throws IOException;
    public void serverClosed();
    public void disconnected();
    public void connectedToServer();
}