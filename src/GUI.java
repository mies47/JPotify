import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author naha
 *Completed Form of GUI
 * compare all JPanel
 */
public class GUI extends JFrame {
    private ToolBar toolBar;

    public ToolBar getToolBar() {
        return toolBar;
    }

    public GUI() throws IOException, InterruptedException {
        JFrame j=new JFrame("Jpotify");
        j.setLayout(new BorderLayout());
        j.add(new BtmofGUI(j , new File("C:\\Users\\behesht\\Downloads\\Telegram Desktop\\Billie Eilish - When I Was Older.mp3")),BorderLayout.PAGE_END);
        j.add(new LeftOfGUI(),BorderLayout.WEST);
        ArrayList<SongPlaylist> temp = new ArrayList<>();
        toolBar = new ToolBar("Milad" , "" , "1234" , j);
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        temp.add(new SongPlaylist("" , "dgdfbejhfksehfsfsdjf uifheskfskdhf"));
        j.add(new MiddleGUI(temp , toolBar));
        ArrayList<OtherUsersSongs> list = new ArrayList<>();
        list.add(new OtherUsersSongs("Milad" , "0" , "fuuuck" , "Amir" , ImageIO.read(getClass().getResource("index.jpg"))));
        j.add(new FriendsActivity(list) , BorderLayout.EAST);
        j.setMinimumSize(new Dimension(900, 900));
        j.setLocationRelativeTo(null);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);
        j.setVisible(true);
    }
}
