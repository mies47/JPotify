import javax.swing.*;
import java.awt.*;

public class OtherUsersSongs extends JPanel {
    private String userName;
    private String song;
    private String artist;
    private Image img;
    private String time;
    private JLabel status;
    private JLabel picture;
    public OtherUsersSongs(String userName  ,String time , String song , String artist , Image img){
        this.userName = userName;
        this.time = time;
        this.song = song;
        this.artist = artist;
        this.img = img;
    }
}
