import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.sound.sampled.*;

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
        JLabel l=new JLabel();
        l.setBackground(Color.BLACK);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("",Font.LAYOUT_NO_LIMIT_CONTEXT,20));
        l.setText("50"+"%");
        Image img = ImageIO.read(getClass().getResource("speaker.png"));
        Image img2=img.getScaledInstance(40,40,Image.SCALE_SMOOTH);//changing the scale of icon
        btn.setIcon(new ImageIcon(img2));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.BLACK);
        JSlider b=new JSlider();
        b.setMinimum(0);
        b.setMaximum(100);
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
                    b.setValue(0);
                    //Audio audio=new Audio();
                    Audio.setMasterOutputVolume(0f);
                    l.setText("0"+"%");
                }else{
                    Image img17 = null;
                    try {
                        img17 = ImageIO.read(getClass().getResource("speaker.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image img18 = img17.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    btn.setIcon(new ImageIcon(img18));
                    Audio.setMasterOutputVolume(1f);
                    b.setValue(100);
                    l.setText("100"+"%");
                }
            }
        });
        b.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Audio.setMasterOutputVolume(b.getValue()/(float)100);
                l.setText(b.getValue()+"%");
                if (b.getValue()== 0){

                    try {
                        btn.setIcon(new ImageIcon((ImageIO.read(getClass().getResource("mute (1).png"))).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }else{
                    try {
                        btn.setIcon(new ImageIcon((ImageIO.read(getClass().getResource("speaker.png"))).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        Box box= Box.createHorizontalBox();//set position to left of panel
        box.add(btn);
        box.add(b);
        box.add(Box.createHorizontalGlue());
        b.setPaintTrack(true);
        b.setPaintTicks(true);
        b.setPaintLabels(true);
        b.setBackground(Color.BLACK);
        b.setBorder(null);
        btn.setBorder(null);
        box.setBorder(new EmptyBorder(10,0,0,0));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(box);
        this.add(l);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }
}
