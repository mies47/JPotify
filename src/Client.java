import org.apache.commons.io.FileUtils;

//import com.sun.jna.platform.FileUtils;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;

public class Client implements Runnable {
    String message;
    Socket socket;
    ObjectInputStream mainObjectInputStream = null;
    ObjectOutputStream objectOutputStream = null;
    volatile ArrayList<ClientObj> objs = null;
    Thread th = null;

    public Client() {

    }

    @Override
    public void run() {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    socket.close();
                    System.out.println(Time.valueOf(LocalTime.now()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                return;
            }
        });
        try {
            socket = new Socket("192.168.43.130", 5000);
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
            objectOutputStream.writeObject(new ClientObj(new File("member.txt"), frame));
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                objs = (ArrayList<ClientObj>) mainObjectInputStream.readObject();
            } catch (IOException e) {
                continue;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            th = new Thread(new Runnable() {
                @Override
                public void run() {

                    if (objs != null) {
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
                                    outputStream.println(user.time);
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

    public static void main(String[] args) {
        Thread th = new Thread(new Client());
        th.start();
    }
}
