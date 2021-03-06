import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * showing the name an picture of this user
 * @author milad
 */
public class UserPanel extends JButton {
    volatile String name;
    Image img;

    /**
     * @param name the name of user shown on top
     * @param img the profile image of user
     */
    public UserPanel(String name , Image img){
        super(name);
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setBorder(null);
        this.setPic(img);
    }
    public void setPic(Image img){
        this.img = img.getScaledInstance(20 , 20 , Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(this.img));
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    public void setUserName(String name){
        this.name = name;
        this.setText(name);
    }

}
