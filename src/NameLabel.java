import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author naha
 * show two label of btm
 */
public class NameLabel extends JPanel {
    private String Song="Song: ";
    private String Artist="Artist: ";
    private int keyPress2=0;
    JLabel l1;
    JLabel l2;

    /**
     * @param s1 set name of song
     * @param s2 set name of artist
     */
    public NameLabel(String s1, String s2)  {
        l1=new JLabel();
        l2=new JLabel();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        l1.setText(Song+s1);
        l2.setText(Artist+s2);
        l1.setBackground(Color.BLACK);
        l2.setBackground(Color.BLACK);
        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l1.setFont(new Font("",Font.PLAIN,20));
        l2.setFont(new Font("",Font.PLAIN,20));
        l1.setOpaque(true);
        l2.setOpaque(true);
        l1.setBorder(new EmptyBorder(0,0,10,0));
        this.add(l1);
        this.add(l2);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }
}
