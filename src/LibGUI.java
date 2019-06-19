import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

/**
 * library show manage
 * manage add songs
 * mange song and albums Graphics show
 */
public class LibGUI extends JPanel {
    static JLabel l;
    static JButton b1,b2,b3;

    /**
     * 1 label and 3 button
     * @throws IOException if not find icon throws exception
     */
    public LibGUI() throws IOException {
        l=new JLabel("YOUR LIBRARY: ");
        l.setBackground(Color.BLACK);
        l.setFont(new Font("",Font.PLAIN,20));
        l.setOpaque(true);//show background color
        l.setForeground(Color.WHITE);
        b1=new JButton("Songs");
        b1.setBorder(null);
        b1.setFont(new Font("",Font.PLAIN,17));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b2=new JButton("Albums");
        b2.setFont(new Font("",Font.PLAIN,17));
        b2.setBorder(null);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b3=new JButton("ADD");
        b3.setBorder(null);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        Image img = ImageIO.read(getClass().getResource("images (1).png"));
        Image img2=img.getScaledInstance(30,30,Image.SCALE_SMOOTH);//changing the scale of icon
        b3.setIcon(new ImageIcon(img2));
        Box box= Box.createHorizontalBox();//set position to left of panel
        box.add(b2);
        box.add(Box.createHorizontalGlue());
        box.setBorder(new EmptyBorder(5,0,0,0));//space between two rows
        Box box2= Box.createHorizontalBox();//set position to left of panel
        box2.add(b1);
        box2.add(Box.createHorizontalGlue());
        box2.setBorder(new EmptyBorder(10,0,0,0));//space between two rows
        Box box3= Box.createHorizontalBox();//set position to left of panel
        box3.add(l);
        box3.add(b3);
        box3.add(Box.createHorizontalGlue());
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(box3);
        this.add(box2);
        this.add(box);
        setBackground(Color.BLACK);
        setVisible(true);
    }
}
