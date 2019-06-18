import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ToolBar extends JPanel {
    SearchArea searchArea;
    UserPanel userPanel;

    /**
     * setting the top toolbar
     * @param name the user's name
     * @param dir directory of image chosen by user
     */
    public ToolBar(String name , String dir){
        searchArea = new SearchArea();
        try {
            Image img = ImageIO.read(getClass().getResource(dir));
            userPanel = new UserPanel(name + "  " , img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setLayout(new BorderLayout());
        this.add(searchArea , BorderLayout.WEST);
        this.add(userPanel , BorderLayout.EAST);
        this.setBackground(Color.BLACK);
    }
}
