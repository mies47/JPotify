import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class Server {
    volatile HashMap<Socket , ObjectInputStream> allSocketsInput = new HashMap<>();
    volatile HashMap<Socket , ObjectOutputStream> allSocketsOutput = new HashMap<>();
    volatile HashMap<Socket , ClientObj> allClientOBjects = new HashMap<>();
    public Server() throws IOException, ClassNotFoundException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(3543);
        Socket client;
        while (true) {
            client = serverSocket.accept();

            allSocketsInput.put(client  , new ObjectInputStream(client.getInputStream()));
            allSocketsOutput.put(client , new ObjectOutputStream(client.getOutputStream()));
            allClientOBjects.put(client , (ClientObj) allSocketsInput.get(client).readObject());
            Thread thread = new Thread(new ClientHandler(client , allSocketsInput , allSocketsOutput , allClientOBjects));

            thread.start();

//            for (int i = 0 ; i < allSocketsConnected.size() ; i++){
//                if(allSocketsConnected.get().isClosed()){
//                    allSocketsConnected.remove(i);
//                    i--;
//                }
//            }
        }

//        User user = client.read()
    }

    public static void main(String[] args) throws IOException, JavaLayerException, ClassNotFoundException, InterruptedException {
        new Server();
//        for (int i = 0 ; i < 6 ; i++){
//            new Client();
//        }
    }
}
