import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

/**
 * Each part of friend's activity
 * @author Milad
 */
public class OtherUsersSongs extends JPanel {
    private JLabel userName;
    private JLabel song;
    private JLabel artist;
    private Image img;
    private String time;//Last time that user was online if it's "0" user is online
    private JLabel status;//if the user is online or not
    private JLabel picture;//profile pic

    public String getUser() {
        return user;
    }

    private String user;
    /**
     * @param userName user name
     * @param time Last time that user was online if it's "0" user is online
     * @param song name of the song
     * @param artist name of the artist
     * @param img the user's profile image
     * @throws IOException if the icon image for online user is not found
     */
    public OtherUsersSongs(String userName  ,String time , String song , String artist , Image img) throws IOException {
        this.user=userName;
        this.userName = new JLabel(user);
        this.userName.setOpaque(true);
        this.userName.setBackground(Color.BLACK);
        this.userName.setForeground(Color.WHITE);
        this.userName.setFont(new Font("hi" , Font.BOLD , 10));
        this.song = new JLabel(song);
        this.song.setBackground(Color.BLACK);
        this.song.setForeground(Color.WHITE);
        this.song.setFont(new Font("hi" , Font.BOLD , 7));
        this.artist = new JLabel(artist);
        this.artist.setBackground(Color.BLACK);
        this.artist.setForeground(Color.WHITE);
        this.artist.setFont(new Font("hi" , Font.BOLD , 7));
        this.time = time;
        this.img = img.getScaledInstance(30 , 40 , Image.SCALE_SMOOTH);
        picture = new JLabel();
        picture.setIcon(new ImageIcon(this.img));
        status = new JLabel();
        status.setForeground(Color.LIGHT_GRAY);
        status.setFont(new Font("hi" , Font.BOLD , 10));
        status.setBorder(new EmptyBorder(0 , 40, 0, 0));
        if(time.equals("0")){//if user was online show speaker icon
            Image image = ImageIO.read(getClass().getResource("speaker.png"));
            image = image.getScaledInstance(15 , 15 , Image.SCALE_SMOOTH);
            status.setIcon(new ImageIcon(image));
        }else {//show the last time user was online
            status.setText(time);
        }
        JPanel statusHolder = new JPanel();
        statusHolder.setLayout(new BorderLayout());
        statusHolder.add(status , BorderLayout.PAGE_START);
        statusHolder.setBackground(Color.BLACK);
        Box labelHolder = Box.createVerticalBox();
        labelHolder.add(Box.createVerticalGlue());
        labelHolder.add(this.userName);
        labelHolder.add(this.song);
        labelHolder.add(this.artist);
        labelHolder.setBorder(new EmptyBorder(0 ,10, 0, 0));
        Box labelPic = Box.createHorizontalBox();
        labelPic.add(Box.createHorizontalGlue());
        labelPic.add(picture);
        labelPic.add(labelHolder);
        labelPic.add(status);
        this.setLayout(new BorderLayout());
        this.add(labelPic , BorderLayout.PAGE_START);
        this.setBackground(Color.BLACK);
    }
}
