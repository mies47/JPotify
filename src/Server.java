//import javazoom.jl.decoder.JavaLayerException;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.*;
//import java.net.InetSocketAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.nio.channels.SelectionKey;
//import java.nio.channels.Selector;
//import java.nio.channels.ServerSocketChannel;
//import java.nio.channels.SocketChannel;
//
//public class Server {
//    public Server() throws IOException, ClassNotFoundException, InterruptedException {
//        ServerSocket serverSocket = new ServerSocket(3000);
//        Socket client;
//        while (true) {
//            client = serverSocket.accept();
//            Thread thread = new Thread(new ClientHandler(client));
//            thread.start();
//            if(client.isClosed()){
//                thread.stop();
//                System.out.println("disconnected");
//            }
//        }
//
////        User user = client.read()
//    }
//
//    public static void main(String[] args) throws IOException, JavaLayerException, ClassNotFoundException, InterruptedException {
//        new Server();
////        for (int i = 0 ; i < 6 ; i++){
////            new Client();
////        }
//    }
//}
