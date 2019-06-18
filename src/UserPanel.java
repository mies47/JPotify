import javax.swing.*;
import java.awt.*;

public class UserPanel extends JButton {
    String name;
    Image img;

    /**
     * showing the name an picture of this user
     * @param name the name of user shown on top
     * @param img the profile image of user
     */
    public UserPanel(String name , Image img){
        super(name);
        this.setBackground(Color.BLACK);
        this.img = img.getScaledInstance(20 , 20 , Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(this.img));
        this.setForeground(Color.WHITE);
        this.setBorder(null);
    }
}
