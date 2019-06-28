import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements Runnable {
    String message;
    Socket socket;
    ObjectInputStream mainObjectInputStream = null;
    ObjectOutputStream objectOutputStream = null;
    volatile ArrayList<ClientObj> objs = null;
    Thread th = null;

    public Client() throws IOException, ClassNotFoundException, InterruptedException {

    }

    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            socket = new Socket("localhost", 3543);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            mainObjectInputStream = new ObjectInputStream(socket.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("SSSSSSSS");
            objectOutputStream.writeObject(new ClientObj(new File("member.txt"), frame));
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int counter2 = 0;
//            try {
//                Flag flag = (Flag) mainObjectInputStream.readObject();
//                System.out.println(flag.flag);
//                if(flag.flag){
//                    objectOutputStream.writeObject(tempSend);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
        while (true){
            try {
                objs = (ArrayList<ClientObj>)mainObjectInputStream.readObject();
            } catch (IOException e) {
                continue;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("kir");
            th = new Thread(new Runnable() {
                @Override
                public void run() {

                    if(objs != null) {
                        ArrayList<ClientObj> clientObjs;
                        clientObjs = objs;
                        for (ClientObj clientObj : clientObjs) {

                            File member = new File("member.txt");
                            try (PrintStream memberWriter = new PrintStream(new FileOutputStream(member, true), true)) {
                                byte[] memberInByte = clientObj.getMember();
                                for (byte b : memberInByte) {
                                    memberWriter.print((char) b);
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            for (User user : clientObj.getUserSongs().keySet()) {
                                try (PrintStream outputStream = new PrintStream(new FileOutputStream(new File(user.name)), true)) {
                                    outputStream.println(user.password);
                                    File f = new File(user.name + "Pic");
                                    FileUtils.writeByteArrayToFile(f, user.image);
                                    outputStream.println(f.getName());
                                    FileUtils.writeByteArrayToFile(new File(user.name + "Recentsongs"), clientObj.getUserRecents().get(user));
                                    FileUtils.writeByteArrayToFile(new File(user.name + "favorites"), clientObj.getUserFavorites().get(user));
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                File userSong = new File(user.name + "songs");
                                File userRecent = new File("new " + user.name + "RecentSongs");
                                File userFavorite = new File("new" + user.name + "Favorites");
                                File mainUserRecent = new File(user.name + "Recentsongs");
                                File mainUserFavorite = new File(user.name + "favorites");
                                PrintStream recentWriter = null;
                                try {
                                    recentWriter = new PrintStream(new FileOutputStream(userRecent), true);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                PrintStream favoriteWriter = null;
                                try {
                                    favoriteWriter = new PrintStream(new FileOutputStream(userFavorite), true);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                try (PrintStream songWriter = new PrintStream(new FileOutputStream(userSong), true)) {
                                    int counter = 0;
                                    for (byte[] bytes : clientObj.getUserSongs().get(user)) {
                                        File temp = new File(clientObj.getAllSongNames().get(user).get(counter));
                                        FileUtils.writeByteArrayToFile(temp, bytes);
                                        songWriter.println(clientObj.getAllSongNames().get(user).get(counter));
                                        if (FileUtils.readFileToString(mainUserFavorite).contains(clientObj.getAllSongNames().get(user).get(counter))) {
                                            favoriteWriter.println(clientObj.getAllSongNames().get(user).get(counter));
                                        }
                                        if (FileUtils.readFileToString(mainUserRecent).contains(clientObj.getAllSongNames().get(user).get(counter))) {
                                            recentWriter.println(clientObj.getAllSongNames().get(user).get(counter));
                                        }
                                        counter++;
                                    }
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                PrintStream finalRecentWriter = recentWriter;
                                PrintStream finalFavoriteWriter = favoriteWriter;
//                    frame.addWindowListener(new WindowListener() {
//                        @Override
//                        public void windowOpened(WindowEvent e) {
//
//                        }
//
//                        @Override
//                        public void windowClosing(WindowEvent e) {
//                            try {
//                                objectOutputStream.close();
//                                mainObjectInputStream.close();
//                                FileUtils.forceDelete(mainUserFavorite);
//                                FileUtils.forceDelete(mainUserRecent);
//                                finalRecentWriter.close();
//                                finalFavoriteWriter.close();
//                                Files.move(userRecent.toPath(), new File(user.name + "Recentsongs").toPath(), StandardCopyOption.REPLACE_EXISTING);
//                                Files.move(userFavorite.toPath(), new File(user.name + "favorite").toPath(), StandardCopyOption.REPLACE_EXISTING);
//                                socket.close();
//                            } catch (IOException e1) {
//                                e1.printStackTrace();
//                            }
//                            return;
//                        }
//
//                        @Override
//                        public void windowClosed(WindowEvent e) {
//                            try {
//                                objectOutputStream.close();
//                                mainObjectInputStream.close();
//                                FileUtils.forceDelete(mainUserFavorite);
//                                FileUtils.forceDelete(mainUserRecent);
//                                finalRecentWriter.close();
//                                finalFavoriteWriter.close();
//                                Files.move(userRecent.toPath(), new File(user.name + "Recentsongs").toPath(), StandardCopyOption.REPLACE_EXISTING);
//                                Files.move(userFavorite.toPath(), new File(user.name + "favorite").toPath(), StandardCopyOption.REPLACE_EXISTING);
//                                socket.close();
//                            } catch (IOException e1) {
//                                e1.printStackTrace();
//                            }
//                            return;
//                        }
//
//                        @Override
//                        public void windowIconified(WindowEvent e) {
//
//                        }
//
//                        @Override
//                        public void windowDeiconified(WindowEvent e) {
//
//                        }
//
//                        @Override
//                        public void windowActivated(WindowEvent e) {
//
//                        }
//
//                        @Override
//                        public void windowDeactivated(WindowEvent e) {
//
//                        }
//                    });
                                try {
                                    FileUtils.forceDelete(mainUserFavorite);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    FileUtils.forceDelete(mainUserRecent);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                recentWriter.close();
                                favoriteWriter.close();
                                try {
                                    Files.move(userRecent.toPath(), new File(user.name + "Recentsongs").toPath(), StandardCopyOption.REPLACE_EXISTING);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    Files.move(userFavorite.toPath(), new File(user.name + "favorite").toPath(), StandardCopyOption.REPLACE_EXISTING);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }

                }
            });
            synchronized (th) {
                th.start();
            }
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Thread th = new Thread(new Client());
//        synchronized (th) {
        th.start();
//        }
    }
}
