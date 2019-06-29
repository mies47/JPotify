import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientHandler implements Runnable {
    Socket socket;
    ClientObj clientObj;
    volatile HashMap<Socket, ObjectInputStream> allSocketsInput;
    volatile HashMap<Socket, ObjectOutputStream> allSocketsOutput;
    volatile HashMap<Socket, ClientObj> allClientObjects;
    ArrayList<ClientObj> clientObjs = new ArrayList<>();
    Thread th = null;

    public ClientHandler(Socket socket1, HashMap<Socket, ObjectInputStream> sockets, HashMap<Socket, ObjectOutputStream> socket2
            , HashMap<Socket, ClientObj> allClientObjects) {
        allSocketsInput = sockets;
        allSocketsOutput = socket2;
        this.allClientObjects = allClientObjects;
        socket = socket1;
    }

    @Override
    public void run() {
        ObjectOutputStream arrayListWriter = allSocketsOutput.get(socket);
        clientObj = allClientObjects.get(socket);
        clientObjs.add(clientObj);
        for (Socket s : allClientObjects.keySet()) {
            if (s != socket) {
                clientObjs.add(allClientObjects.get(s));
            }
        }

        for (Socket s : allSocketsOutput.keySet()) {
            th = new Thread(new Runnable() {
                @Override
                public void run() {
                    if (s != socket) {
                        try {
                            System.out.println(clientObjs);
                            allSocketsOutput.get(s).writeObject(clientObjs);
                            allSocketsOutput.get(s).flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
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
        clientObj.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                clientObj.thisUser.setTime(Time.valueOf(LocalTime.now()));
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }
}
