import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author naha
 * compare and complete bottom of gui
 * compare three JPanel
 */
public class BtmofGUI extends JPanel {
    nameLabel nL;
    PlayStop PS;
    /**
     * @throws IOException if not find icon throws exception
     */
    public BtmofGUI(JFrame jFrame , File file) throws IOException, InterruptedException {
        nL=new nameLabel(" "," ");
        PS=new PlayStop(jFrame , file);
        Volume v=new Volume();
        this.setLayout(new BorderLayout());
        this.add(nL,BorderLayout.WEST);
        this.add(PS,BorderLayout.CENTER);
        this.add(v,BorderLayout.EAST);
        PS.setBorder(new EmptyBorder(0,100,20,0));
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }
}
