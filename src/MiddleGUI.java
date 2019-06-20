import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * the middle of the main frame
 * contains a jpanel with flow layout
 * @author Milad
 */
public class MiddleGUI extends JPanel {
    /**
     * @param list all of the songs or playlists added
     */
    public MiddleGUI(ArrayList<SongPlaylist> list){
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        for (int i = 0 ; i < list.size() ; i+=4){
            this.add(list.get(i));
        }
        this.setBackground(Color.BLACK);
    }
}
