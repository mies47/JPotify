import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * a jpanel that includes the song thumbnail and its description
 * @author Milad
 */
public class SongPlaylist extends JPanel {
    private JLabel pic;
    private JLabel description;

    /**
     * @param img picture
     * @param description description of song or playlist
     * @throws IOException if the directory given does not exist
     */
    public SongPlaylist(Image img , String description) throws IOException {
        Image img1 = img;
        if(img == null) {
            img = ImageIO.read(getClass().getResource("musicLogo.jpg"));
        }
        img = img.getScaledInstance(200 , 200 , Image.SCALE_SMOOTH);
        pic = new JLabel();
        pic.setIcon(new ImageIcon(img));
        this.description = new JLabel("<html>"+description+"</html>");//to show the text in multiple lines
        this.description.setForeground(Color.LIGHT_GRAY);
        this.description.setBorder(BorderFactory.createMatteBorder(1 , 0 , 0 , 0 , Color.GRAY));
        this.setLayout(new BorderLayout());
        this.add(pic , BorderLayout.CENTER);
        this.add(this.description , BorderLayout.PAGE_END);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(200 + this.description.getWidth() , 200 + this.description.getHeight()));
        this.setBorder(BorderFactory.createSoftBevelBorder(0 , Color.GRAY , Color.LIGHT_GRAY));

    }
}
