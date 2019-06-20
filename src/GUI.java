import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author naha
 *Completed Form of GUI
 * compare all JPanel
 */
public class GUI extends JFrame {
    public GUI() throws IOException {
        JFrame j=new JFrame("Jpotify");
        j.setLayout(new BorderLayout());
        j.add(new BtmofGUI(),BorderLayout.PAGE_END);
        j.add(new leftofGUI(),BorderLayout.WEST);
        j.setMinimumSize(new Dimension(900, 900));
        j.setLocationRelativeTo(null);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);
        j.setVisible(true);
    }
}
