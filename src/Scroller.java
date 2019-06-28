import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * indicates list of playlist in scroller
 */
public class Scroller extends JPanel implements ChangeName , RemoveDLM {
    Vector<String> DLM;
    JList<String> JL;
    /**
     * use Jlist and DefaultListModel
     * constructor of Scroller
     * show list of Play list as string
     */
    public Scroller(JFrame frame) {
        DLM = new Vector<>();
        JL = new JList<>(DLM);
        JL.setBackground(Color.BLACK);
        JL.setForeground(Color.WHITE);
        JScrollPane scrollable = new JScrollPane(JL);
        scrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//make just vertical scroll
        scrollable.setBackground(Color.BLACK);
        scrollable.setOpaque(true);
        scrollable.setForeground(Color.WHITE);
        UIManager.put("ScrollBar.thumbShadow", new ColorUIResource(Color.BLACK));
        UIManager.put("ScrollBar.thumb", new ColorUIResource(Color.BLACK));//edit color of thumb of scroller
        UIManager.put("ScrollBar.track", new ColorUIResource(Color.BLACK));
        scrollable.getVerticalScrollBar().setUI(new BasicScrollBarUI());
        this.setLayout(new BorderLayout());
        this.add(scrollable, BorderLayout.CENTER);
        setVisible(true);
    }

    public void add(String s) {
        DLM.addElement(s);
    }

    @Override
    public void change(String s) {
        DLM.add(s);
        JL.setListData(DLM);
    }

    public void addActionListener(final ActionListener al) {

        JL.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    al.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "ENTER"));
                }
            }
        });

        JL.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    al.actionPerformed(new ActionEvent(e.getSource(), e.getID(), "ENTER"));
                }
            }
        });

    }

    @Override
    public void remove(String s) {
        System.out.println(s);
        DLM.remove(s);
        JL.setListData(DLM);
        JL.revalidate();
        JL.repaint();
    }
}
