import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;

/**
 * a class showing the information of each user
 * implments serializable to transfer via socket
 */
public class User implements Serializable {
    String name;
    String password;
    byte[] image;
    Time time = null;

    /**
     * @param name username
     * @param password password of user
     * @param img user's profile image
     * @throws IOException if the image file was not found
     */
    public User(String name , String password , File img) throws IOException {
        this.name = name;
        this.password = password;
        this.image = Files.readAllBytes(Paths.get(img.getPath()));
    }

    /**
     * @param time the time when user signed out and disconnected from server
     */
    public void setTime(Time time) {
        this.time = time;
    }
}
