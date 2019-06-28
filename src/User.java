import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;

public class User implements Serializable {
    String name;
    String password;
    byte[] image;
    Time time = null;
    public User(String name , String password , File img) throws IOException {
        this.name = name;
        this.password = password;
        this.image = Files.readAllBytes(Paths.get(img.getPath()));
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
