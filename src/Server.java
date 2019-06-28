import javazoom.jl.decoder.JavaLayerException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

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
    }
}
