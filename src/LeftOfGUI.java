import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

/**
 * indicates left of gui
 */
public class LeftOfGUI extends JPanel {
    JLabel l;
    /**
     * @throws IOException if not find icon throw exception
     * constructor of leftofgui
     */
    public LeftOfGUI() throws IOException {
        l=new JLabel();
        Image img = ImageIO.read(getClass().getResource("images (2).png"));
        l.setIcon(new ImageIcon(img));
        Box box4= Box.createHorizontalBox();//set position to left of panel
        box4.add(l);
        box4.add(Box.createHorizontalGlue());
        Box box= Box.createHorizontalBox();//set position to left of panel
        box.add(new Homepanel());
        box.add(Box.createHorizontalGlue());
        Box box2= Box.createHorizontalBox();//set position to left of panel
        box2.add(new LibGUI());
        box2.add(Box.createHorizontalGlue());
        Box box3= Box.createHorizontalBox();//set position to left of panel
        box3.add(new PlayList());
        box3.add(Box.createHorizontalGlue());
        box.setBorder(new EmptyBorder(0,0,20,0));//space between home and library
        box2.setBorder(new EmptyBorder(0,0,15,0));//space between play list and library
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(box);
        this.add(box2);
        this.add(box3);
        this.add(box4);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }

    public void setL(Image img) {
        l.setIcon(new ImageIcon(img.getScaledInstance(275, 225 , Image.SCALE_SMOOTH)));
    }
}
