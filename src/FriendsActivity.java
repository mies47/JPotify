import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.ArrayList;

/**
 * showing the friend's activity
 * adding a scrollpane to jpanel
 * includes all other user's songs instances
 * @author Milad
 */
public class FriendsActivity extends JPanel {
    /**
     * @param list list of all other users
     */
    public FriendsActivity(ArrayList<OtherUsersSongs> list){
        JPanel container = new JPanel();
        for (OtherUsersSongs temp : list){
            container.add(temp);
        }
        container.setLayout(new BoxLayout(container , WrapLayout.CENTER));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);
        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);//always show scrollbar
        UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(Color.BLACK));
        UIManager.put("ScrollBar.thumb", new ColorUIResource(Color.BLACK));//edit color of thumb of scroller
        UIManager.put("ScrollBar.track", new ColorUIResource(Color.BLACK));
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        //this.setMaximumSize(new Dimension());
        this.add(scrollPane , BorderLayout.CENTER);
    }
}
