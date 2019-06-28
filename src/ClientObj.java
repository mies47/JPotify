import com.sun.jna.platform.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ClientObj implements Serializable {
    private byte[] member;
    private HashMap<User , ArrayList<String>> allSongNames = new HashMap<>();
    private HashMap<User , ArrayList<byte[]>> userSongs = new HashMap<>();
    private File userFavorite;
    private File userRecent;
    private HashMap<User ,byte[]> userFavorites = new HashMap<>();
    private HashMap<User ,byte[]> userRecents = new HashMap<>();
    private JFrame frame;
    User thisUser;
    public ClientObj(File member , JFrame jFrame) throws IOException {
        frame = jFrame;
        this.member = Files.readAllBytes(Paths.get(member.getPath()));
        Scanner scUser = new Scanner(member);
        while (scUser.hasNextLine()){
            ArrayList<byte[]> songs = new ArrayList<>();
            String user = scUser.nextLine();
            File f=new File(user);
            Scanner userSc = new Scanner(f);
            String pass = null;
            File img = new File("C:\\Users\\behesht\\IdeaProjects\\Jpotify\\src\\DefaultPhotoPic.jpg");
            if(userSc.hasNextLine()) {
                pass = userSc.nextLine();
                while (userSc.hasNextLine()) {
                    img = new File(userSc.nextLine());
                }
            }
            thisUser = new User(user ,pass , img );
            Scanner songSC = new Scanner(new File(thisUser.name + "songs"));
            ArrayList<String> eachUserSongs = new ArrayList<>();
            while (songSC.hasNextLine()){
                String path = songSC.nextLine();
                eachUserSongs.add(new File(path).getName());
                songs.add(Files.readAllBytes(Paths.get(new File(path).getPath())));
            }
            userSongs.put(thisUser , songs);
            allSongNames.put(thisUser , eachUserSongs);
            userFavorites.put(thisUser , Files.readAllBytes(Paths.get(thisUser.name + "favorite")));
            userRecents.put(thisUser , Files.readAllBytes(Paths.get(thisUser.name + "Recentsongs")));
        }
    }
    public byte[] getMember() {
        return member;
    }

    public HashMap<User, ArrayList<byte[]>> getUserSongs() {
        return userSongs;
    }

    public HashMap<User, byte[]> getUserFavorites() {
        return userFavorites;
    }

    public HashMap<User, byte[]> getUserRecents() {
        return userRecents;
    }
    public HashMap<User, ArrayList<String>> getAllSongNames() {
        return allSongNames;
    }

    public JFrame getFrame() {
        return frame;
    }


}
