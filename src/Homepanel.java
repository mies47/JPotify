import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * home button
 *
 */
public class Homepanel extends JButton {

    /**
     * set button text to "Home"
     * set button icon to a specified icon
     */
    public Homepanel(){
        super(" Home");
        try {

            Image img = ImageIO.read(getClass().getResource("images.png"));
            Image img2=img.getScaledInstance(30,30,Image.SCALE_SMOOTH);//changing the scale of icon
            setIcon(new ImageIcon(img2));

        } catch (Exception ex) {
            System.out.println(ex);
        }
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setFont(new Font("dialog",Font.LAYOUT_NO_LIMIT_CONTEXT,20));
        this.setBorder(null);
    }


}
