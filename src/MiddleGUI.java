import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.ArrayList;

/**
 * the middle of the main frame
 * contains a jpanel with flow layout
 * @author Milad
 */
public class MiddleGUI extends JPanel{
    /**
     * @param list all of the songs or playlists added
     */
    public MiddleGUI(ArrayList<SongPlaylist> list , ToolBar toolBar){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new WrapLayout(WrapLayout.LEFT));
        for (int i = 0 ; i < list.size() ; i++){
            jPanel.add(list.get(i));
        }
        jPanel.setBackground(Color.BLACK);
        this.setLayout(new BorderLayout());
        this.add(toolBar , BorderLayout.PAGE_START);
        this.add(jPanel , BorderLayout.CENTER);
        this.setBackground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(jPanel);
        UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(Color.BLACK));
        UIManager.put("ScrollBar.thumb", new ColorUIResource(Color.BLACK));//edit color of thumb of scroller
        UIManager.put("ScrollBar.track", new ColorUIResource(Color.BLACK));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane);
    }


}
