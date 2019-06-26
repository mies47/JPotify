import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    String message;
    Socket socket;
    public Client() throws IOException, ClassNotFoundException {
        socket = new Socket("localhost" , 3000);
        ClientObj temp = new ClientObj(new File("C:\\Users\\behesht\\IdeaProjects\\Jpotify\\member.txt"));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(temp);
//        DataInputStream sc = new DataInputStream(socket.getInputStream());
//        System.out.println(sc.readUTF());
//        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
//        ArrayList<objectInputStream.readObject();
        objectOutputStream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Client();
    }
}
