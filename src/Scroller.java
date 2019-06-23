import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * indicates list of playlist in scroller
 */
public class Scroller extends JPanel implements ChangeName {
    DefaultListModel<String> DLM;

    /**
     * use Jlist and DefaultListModel
     * constructor of Scroller
     * show list of Play list as string
     */
    public Scroller(){

        DLM=new DefaultListModel<>();
        JList<String> JL=new JList<>(DLM);
        JL.setBackground(Color.BLACK);
        JL.setForeground(Color.WHITE);
        JScrollPane scrollable= new JScrollPane(JL);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//make just vertical scroll
        scrollable.setBackground(Color.BLACK);
        scrollable.setOpaque(true);
        scrollable.setForeground(Color.WHITE);
        UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(Color.BLACK));
        UIManager.put("ScrollBar.thumb", new ColorUIResource(Color.BLACK));//edit color of thumb of scroller
        UIManager.put("ScrollBar.track", new ColorUIResource(Color.BLACK));
        scrollable.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        this.setLayout(new BorderLayout());
        this.add(scrollable,BorderLayout.CENTER);
        setVisible(true);
    }
    public void add(String s){
        DLM.addElement(s);
    }

    @Override
    public void change(String s) {
        DLM.addElement(s);
    }
}
