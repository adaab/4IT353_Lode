package main;

import comm.ServerListener;
import comm.TCPServer;

public class Main {

    public static void main(String[] args){
        TCPServer server = new TCPServer(8888);
    }
}
