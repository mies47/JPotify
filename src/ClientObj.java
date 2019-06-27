import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ClientObj implements Serializable {
    private File member;
    private HashMap<User , ArrayList<File>> userSongs = new HashMap<>();
    private HashMap<User ,ArrayList<File>> userFavorites = new HashMap<>();
    private HashMap<User ,ArrayList<File>> userRecents = new HashMap<>();
    User thisUser;

    public ClientObj(File member) throws IOException {
        this.member = member;
        Scanner scUser = new Scanner(member);
        while (scUser.hasNextLine()){
            String user = scUser.nextLine();
            Scanner userSc = new Scanner(user);
            String pass = null;
            Image img = null;
            if(userSc.hasNextLine())
                pass=userSc.nextLine();
            while (userSc.hasNextLine()){
                img = ImageIO.read(new File(userSc.nextLine()));
            }
            thisUser = new User(user ,pass , img );
            ArrayList<File> songs = new ArrayList<>();
            Scanner scSongs = new Scanner(user + "songs");
            while (scSongs.hasNextLine()){
                songs.add(new File(scSongs.nextLine()));
            }
            userSongs.put(thisUser , songs);
            ArrayList<File> favorite = new ArrayList<>();
            Scanner scFavorite = new Scanner(user + "favorite");
            while (scFavorite.hasNextLine()){
                favorite.add(new File(scFavorite.nextLine()));
            }
            userFavorites.put(thisUser , favorite);
            ArrayList<File> userRecentSongs = new ArrayList<>();
            Scanner scRecent = new Scanner(user + "Recentsongs");
            while (scRecent.hasNextLine()){
                userRecentSongs.add(new File(scRecent.nextLine()));
            }
            userRecents.put(thisUser , userRecentSongs);
        }

    }
}
