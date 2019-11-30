package Logic;

public interface ClientListener{
    public void unknownHost();
    public void couldNotConnect();
    public void recivedInput(Object msg);
    public void serverClosed();
    public void disconnected();
    public void connectedToServer();
}