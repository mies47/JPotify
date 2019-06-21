import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author naha
 *Completed Form of GUI
 * compare all JPanel
 */
public class GUI extends JFrame {
    BtmofGUI btmofGUI;
    public GUI() throws IOException {
        JFrame j=new JFrame("Jpotify");
        j.setLayout(new BorderLayout());
        btmofGUI= new BtmofGUI();
        j.add(btmofGUI,BorderLayout.PAGE_END);
        j.add(new leftofGUI(),BorderLayout.WEST);
        ArrayList<SongPlaylist> temp = new ArrayList<>();
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        j.add(new MiddleGUI(temp , new ToolBar("Milad" , "" , "1234")));
        ArrayList<OtherUsersSongs> list = new ArrayList<>();
        list.add(new OtherUsersSongs("Milad" , "0" , "fuuuck" , "Amir" , ImageIO.read(getClass().getResource("index.jpg"))));
        j.add(new FriendsActivity(list) , BorderLayout.EAST);
        j.setMinimumSize(new Dimension(900, 900));
        j.setLocationRelativeTo(null);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);
        j.setVisible(true);
    }
}
