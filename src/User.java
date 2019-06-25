import java.awt.*;
import java.io.Serializable;
import java.sql.Time;

public class User implements Serializable {
    String name;
    String password;
    Image image;
    Time time = null;
    public User(String name , String password , Image img){
        this.name = name;
        this.password = password;
        this.image = img;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
