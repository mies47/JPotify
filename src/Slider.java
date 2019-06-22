import javax.swing.*;
import java.awt.*;

/**
 * @author naha
 * show Slider control time of songs
 */
public class Slider extends  JPanel {
    JSlider b;
    /**
     * two label and one JSlider
     */
    public Slider( ){
        JLabel l1=new JLabel("0:00");
        JLabel l2=new JLabel("0:00");
        l1.setBackground(Color.BLACK);
        l2.setBackground(Color.BLACK);
        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l1.setOpaque(true);
        l2.setOpaque(true);
        b=new JSlider();
        b.setValue(0);
        b.setPaintTrack(true);
        b.setPaintTicks(true);
        b.setPaintLabels(true);
        //b.setVisible(true);
        b.setBackground(Color.BLACK);
        this.setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
        l1.setBorder(null);
        l2.setBorder(null);
        this.add(l1);
        this.add(b);
        this.add(l2);
        this.setBackground(Color.BLACK);
        setVisible(true);
    }
}
