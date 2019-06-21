import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

/**
 * @author naha
 * compare and complete bottom of gui
 * compare three JPanel
 */
public class BtmofGUI extends JPanel {
    PlayStop PS;
    /**
     * @throws IOException if not find icon throws exception
     */
    public BtmofGUI() throws IOException {
        nameLabel nL=new nameLabel(" "," ");
        PS=new PlayStop();
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
