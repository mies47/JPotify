import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.BaseStream;

public class ClientHandler implements Runnable {
    Socket socket;
    ClientObj clientObj;
    volatile HashMap<Socket , ObjectInputStream> allSocketsInput;
    volatile HashMap<Socket , ObjectOutputStream> allSocketsOutput;
    volatile HashMap<Socket , ClientObj> allClientObjects;
    ArrayList<ClientObj> clientObjs = new ArrayList<>();
    Thread th = null;

    public ClientHandler(Socket socket1, HashMap<Socket , ObjectInputStream> sockets , HashMap<Socket , ObjectOutputStream> socket2
            , HashMap<Socket , ClientObj> allClientObjects) {
        allSocketsInput = sockets;
        allSocketsOutput = socket2;
        this.allClientObjects = allClientObjects;
        socket = socket1;
    }

    @Override
    public void run() {
        ObjectInputStream thisSocketInput = allSocketsInput.get(socket);
        ObjectOutputStream arrayListWriter = allSocketsOutput.get(socket);


        clientObj = allClientObjects.get(socket);
        clientObjs.add(clientObj);
//            for (Socket s : allSocketsInput.keySet()) {
//                System.out.println(allSocketsInput.size());
//                if (!s.equals(socket)) {
//                    try {
//                        allSocketsOutput.get(s).writeObject(new Flag(true));
//                        allSocketsOutput.get(s).flush();
//                        clientObjs.add((ClientObj) allSocketsInput.get(s).readObject());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }else {
//                    try {
//                        allSocketsOutput.get(s).writeObject(new Flag(false));
//                        allSocketsOutput.get(s).flush();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
        for(Socket s: allClientObjects.keySet()){
            if(s != socket){
                clientObjs.add(allClientObjects.get(s));
            }
        }

        for (Socket s : allSocketsOutput.keySet()){
            th = new Thread(new Runnable() {
                @Override
                public void run() {
                    if(s != socket){
                        try {
                            System.out.println(clientObjs);
                            allSocketsOutput.get(s).writeObject(clientObjs);
                            allSocketsOutput.get(s).flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        try {
                            System.out.println("FFFFFFFFFF");
                            arrayListWriter.writeObject(clientObjs);
                            arrayListWriter.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            synchronized (th) {
                th.start();
            }
        }
//            clientObj.getFrame().addWindowListener(new WindowListener() {
//                @Override
//                public void windowOpened(WindowEvent e) {
//
//                }
//
//                @Override
//                public void windowClosing(WindowEvent e) {
//                    clientObj.thisUser.setTime(Time.valueOf(LocalTime.now()));
//                    try {
//                        socket.close();
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void windowClosed(WindowEvent e) {
//                    clientObj.thisUser.setTime(Time.valueOf(LocalTime.now()));
//                    try {
//                        socket.close();
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void windowIconified(WindowEvent e) {
//
//                }
//
//                @Override
//                public void windowDeiconified(WindowEvent e) {
//
//                }
//
//                @Override
//                public void windowActivated(WindowEvent e) {
//
//                }
//
//                @Override
//                public void windowDeactivated(WindowEvent e) {
//
//                }
//            });
//            try {
//                thisSocketInput.close();
//                arrayListWriter.close();
//                socket.close();
//                break;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


    }
}
