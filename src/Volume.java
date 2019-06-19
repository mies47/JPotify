import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author naha
 * keyPress for time of click
 */
public class Volume extends JPanel {
    private int keyPress2=0;

    /**
     * one slider for control volume
     * @throws IOException if not find icon throws exception
     */
    public Volume() throws IOException {
        JButton btn=new JButton();
        Image img = ImageIO.read(getClass().getResource("speaker.png"));
        Image img2=img.getScaledInstance(40,40,Image.SCALE_SMOOTH);//changing the scale of icon
        btn.setIcon(new ImageIcon(img2));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.BLACK);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keyPress2++;
                if(keyPress2%2==1) {
                    Image img15 = null;
                    try {
                        img15 = ImageIO.read(getClass().getResource("mute (1).png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image img16 = img15.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    btn.setIcon(new ImageIcon(img16));
                }else{
                    Image img17 = null;
                    try {
                        img17 = ImageIO.read(getClass().getResource("speaker.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image img18 = img17.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    btn.setIcon(new ImageIcon(img18));
                }
            }
        });
        JSlider b=new JSlider();
        b.setPaintTrack(true);
        b.setPaintTicks(true);
        b.setPaintLabels(true);
        b.setBackground(Color.BLACK);
        b.setBorder(null);
        btn.setBorder(null);
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(btn);
        this.add(b);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }
}