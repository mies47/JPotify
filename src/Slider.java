import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;

/**
 * @author naha
 * show Slider control time of songs
 */

public class Slider extends  JPanel{

    JLabel l1;
    JLabel l2;

    JProgressBar b;

    /**
     * two label and one JSlider
     */
    public Slider(String time1,String time2){
        l1=new JLabel(time1);
        l2=new JLabel(time2);
        l1.setBackground(Color.BLACK);
        l2.setBackground(Color.BLACK);
        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l1.setOpaque(true);
        l2.setOpaque(true);
        b=new JProgressBar();
        b.setValue(0);
//        b.setPaintTrack(true);
//        b.setPaintTicks(true);
//        b.setPaintLabels(true);
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

    public void setL1(String timeLeft) {
        l1.setText(timeLeft);
    }
    public void setL2(String timeRight) {
        l2.setText(timeRight);
    }

    public void changeLabel(int current, int total) {
        int currentMin = current / 60;
        int currentSec = current - currentMin * 60;
        setL1(String.valueOf(currentMin) + ":" + String.valueOf(currentSec));
        int totalMin = total / 60;
        int totalSec = total - totalMin * 60;
        setL2(String.valueOf(totalMin) + ":" + String.valueOf(totalSec));
    }
}
