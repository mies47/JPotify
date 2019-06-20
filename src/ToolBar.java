import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * setting the top toolbar
 * @author milad
 */
public class ToolBar extends JPanel {
    SearchArea searchArea;
    UserPanel userPanel;
    Image img = null;
    String userName;
    String passWord;
    ProfilePanel profilePanel;

    /**
     * setting the top toolbar
     * @param name the user's name
     * @param dir directory of image chosen by user
     * @param pass the password of user
     */
    public ToolBar(String name , String dir , String pass , JFrame frame){
        searchArea = new SearchArea();
        userName = name;
        passWord = pass;
        /*
          check to see if the directory was empty to set profile picture
          to default picture
          initializing the userpanel for showing user basic info
          */
        try {
            if(dir.equals("")){
                img = ImageIO.read(getClass().getResource("DefaultPhotoPic.jpg"));
            }else {
                img = ImageIO.read(getClass().getResource(dir));
            }
            userPanel = new UserPanel(userName + "  " , img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setLayout(new BorderLayout());
        this.add(searchArea , BorderLayout.WEST);
        this.add(userPanel , BorderLayout.EAST);
        this.setBackground(Color.BLACK);
        /*
          check to see if userPanel was clicked
          to open the profile panel
         */
        userPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profilePanel = new ProfilePanel(userName , img.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH) , passWord , frame);
            }
        });
    }
}
