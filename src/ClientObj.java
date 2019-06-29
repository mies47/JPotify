import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * an object that contains all of each user's songs and playlist and
 * information fetched with User class
 */
public class  ClientObj implements Serializable {
    private byte[] member;
    private HashMap<User , ArrayList<String>> allSongNames = new HashMap<>();
    private HashMap<User , ArrayList<byte[]>> userSongs = new HashMap<>();
    private HashMap<User ,byte[]> userFavorites = new HashMap<>();
    private HashMap<User ,byte[]> userRecents = new HashMap<>();

    public HashMap<User, byte[]> getUserPlayList() {
        return userPlayList;
    }

    private HashMap<User , byte[]>userPlayList = new HashMap<>();
    private JFrame frame;
    User thisUser;

    /**
     * @param member the member.txt file in client's PC to know all users signed up
     *               in this PC
     * @param jFrame the frame user is currently in when exits saves user's local time
     * @throws IOException if cannot find the member.txt file
     */
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
                File file = new File(path);
                eachUserSongs.add(file.getName());
                songs.add(Files.readAllBytes(Paths.get(file.getPath())));
                file = null;
            }
            userSongs.put(thisUser , songs);
            allSongNames.put(thisUser , eachUserSongs);
            userFavorites.put(thisUser , Files.readAllBytes(Paths.get(thisUser.name + "favorite")));
            userRecents.put(thisUser , Files.readAllBytes(Paths.get(thisUser.name + "Recentsongs")));
            userPlayList.put(thisUser , Files.readAllBytes(Paths.get(thisUser.name + "PLay")));
        }
    }

    /**
     * @return member.txt file as an array of bytes
     */
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
