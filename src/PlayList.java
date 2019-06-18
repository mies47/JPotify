import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * indicate playlist
 */
public class PlayList extends JPanel {
    /**
     * @throws IOException if not Find icon throw exception
     */
    public PlayList() throws IOException {
        JPanel p1=new JPanel();
        JLabel l=new JLabel("PlayList");
        l.setBackground(Color.BLACK);
        l.setFont(new Font("",Font.PLAIN,20));
        l.setOpaque(true);//show background color
        l.setForeground(Color.WHITE);
        JButton btn=new JButton("ADD PlayList");
        btn.setBorder(null);
        btn.setFont(new Font("",Font.PLAIN,17));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        Image img = ImageIO.read(getClass().getResource("download.png"));//get icon
        Image img2=img.getScaledInstance(30,30,Image.SCALE_SMOOTH);//changing the scale of icon
        btn.setIcon(new ImageIcon(img2));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(l);
        this.add(new Scroller());//get list of playlist
        this.add(btn);
        setBackground(Color.BLACK);
        setVisible(true);
    }


}
