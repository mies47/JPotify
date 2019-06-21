import javax.imageio.ImageIO;
import javax.media.Player;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import com.mpatric.mp3agic.*;
import javazoom.jl.decoder.JavaLayerException;

/**
 * @author naha and milad
 * 3 int param for time
 */
public class PlayStop extends JPanel {
    private int keyPress = 0;
    private int keyPress2 = 0;
    private int keyPress4 = 0;
    private int keyPress3 = 0;

    /**
     * @throws IOException if not find icon throw exception
     */
    public PlayStop(JFrame jFrame , File file) throws IOException {
        JButton b1,b2,b3,b4,b5;
        b1=new JButton();
        b2=new JButton();
        b3=new JButton();
        b4=new JButton();
        b5=new JButton();
        Image img = ImageIO.read(getClass().getResource("shuffle (1).png"));
        Image img2=img.getScaledInstance(40,40,Image.SCALE_SMOOTH);//changing the scale of icon
        b1.setIcon(new ImageIcon(img2));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.BLACK);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keyPress2++;
                if(keyPress2%2==1) {
                    Image img15 = null;
                    try {
                        img15 = ImageIO.read(getClass().getResource("shuffle (2).png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image img16 = img15.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    b1.setIcon(new ImageIcon(img16));
                }else{
                    Image img17 = null;
                    try {
                        img17 = ImageIO.read(getClass().getResource("shuffle (1).png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image img18 = img17.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    b1.setIcon(new ImageIcon(img18));
                }
            }
        });
        Image img3 = ImageIO.read(getClass().getResource("left-arrow.png"));
        Image img4=img3.getScaledInstance(40,40,Image.SCALE_SMOOTH);//changing the scale of icon
        b2.setIcon(new ImageIcon(img4));
        Image img5 = ImageIO.read(getClass().getResource("play3.png"));
        Image img6=img5.getScaledInstance(40,40,Image.SCALE_SMOOTH);//changing the scale of icon
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b3.setIcon(new ImageIcon(img6));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.BLACK);
        PausablePlayer player = null;
        try {
            player = new PausablePlayer(new FileInputStream(file)); // a pauseable player for the file given

        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

        PausablePlayer finalPlayer = player;
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keyPress++;

                if(keyPress%2==1) {
                    Image img11 = null;
                    try {
                        finalPlayer.play();
                        Mp3File mp3File = new Mp3File(file);
                        Component[] components =jFrame.getRootPane().getContentPane().getComponents();
                        for (Component c : components){
                            if(c instanceof BtmofGUI){
                                if(mp3File.hasId3v1Tag()){
                                    if(mp3File.getId3v1Tag().getTrack() != null) {
                                        ((BtmofGUI) c).nL.l1.setText("Song: "+mp3File.getId3v1Tag().getTrack());
                                        if(mp3File.getId3v1Tag().getArtist() != null) {
                                            ((BtmofGUI) c).nL.l2.setText("Artist: " + mp3File.getId3v1Tag().getArtist());
                                        }else {
                                            ((BtmofGUI) c).nL.l2.setText("Artist: Unknown ");
                                        }
                                    }else{
                                        ((BtmofGUI) c).nL.l1.setText("Song: Unknown");
                                        if(mp3File.getId3v1Tag().getArtist() != null) {
                                            ((BtmofGUI) c).nL.l2.setText("Artist: " + mp3File.getId3v1Tag().getArtist());
                                        }else {
                                            ((BtmofGUI) c).nL.l2.setText("Artist: Unknown ");
                                        }
                                    }
                                }else if(mp3File.hasId3v2Tag()){
                                    if(mp3File.getId3v2Tag().getTrack() != null) {
                                        ((BtmofGUI) c).nL.l1.setText("Song: "+mp3File.getId3v2Tag().getTrack());
                                        if(mp3File.getId3v2Tag().getArtist() != null) {
                                            ((BtmofGUI) c).nL.l2.setText("Artist: " + mp3File.getId3v2Tag().getArtist());
                                        }else {
                                            ((BtmofGUI) c).nL.l2.setText("Artist: Unknown ");
                                        }
                                    }else{
                                        ((BtmofGUI) c).nL.l1.setText("Song: Unknown");
                                        if(mp3File.getId3v2Tag().getArtist() != null) {
                                            ((BtmofGUI) c).nL.l2.setText("Artist: " + mp3File.getId3v2Tag().getArtist());
                                        }else {
                                            ((BtmofGUI) c).nL.l2.setText("Artist: Unknown ");
                                        }
                                    }
                                }else{
                                    ((BtmofGUI) c).nL.l2.setText("Artist: Unknown ");
                                    ((BtmofGUI) c).nL.l1.setText("Song: Unknown ");
                                }
                            }
                            if(c instanceof leftofGUI){
                                if(mp3File.hasId3v2Tag()) {
                                    if(mp3File.getId3v2Tag().getAlbumImage() != null) {
                                        BufferedImage image =ImageIO.read(new ByteArrayInputStream(mp3File.getId3v2Tag().getAlbumImage()));
                                        ((leftofGUI) c).setL(image);
                                    }
                                }
                            }
                        }
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    } catch (UnsupportedTagException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InvalidDataException e) {
                        e.printStackTrace();
                    }
                    try {
                        img11 = ImageIO.read(getClass().getResource("pause2.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image img12 = img11.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    b3.setIcon(new ImageIcon(img12));
                }else{
                    Image img13 = null;
                    if (finalPlayer != null) {
                        finalPlayer.pause();
                    }
                    try {
                        img13 = ImageIO.read(getClass().getResource("play3.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image img14 = img13.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    b3.setIcon(new ImageIcon(img14));
                }
            }
        });
        Image img7 = ImageIO.read(getClass().getResource("right-arrow.png"));
        Image img8=img7.getScaledInstance(40,40,Image.SCALE_SMOOTH);//changing the scale of icon
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        b4.setIcon(new ImageIcon(img8));
        Image img9 = ImageIO.read(getClass().getResource("repeat.png"));
        Image img10=img9.getScaledInstance(40,40,Image.SCALE_SMOOTH);//changing the scale of icon
        b5.setIcon(new ImageIcon(img10));
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.BLACK);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keyPress3++;
                if(keyPress3%3==1) {
                    Image img19 = null;
                    try {
                        img19 = ImageIO.read(getClass().getResource("repeat2.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image img20 = img19.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    b5.setIcon(new ImageIcon(img20));
                }else if(keyPress3%3==2){
                    Image img23 = null;
                    try {
                        img23 = ImageIO.read(getClass().getResource("repeat3.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image img24 = img23.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    b5.setIcon(new ImageIcon(img24));
                }else{
                    Image img21 = null;
                    try {
                        img21 = ImageIO.read(getClass().getResource("repeat.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image img22 = img21.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    b5.setIcon(new ImageIcon(img22));
                }
            }
        });
        b1.setBorder(null);
        b2.setBorder(null);
        b3.setBorder(null);
        b4.setBorder(null);
        b5.setBorder(null);
        JButton btn=new JButton();
        Image imgbtn = ImageIO.read(getClass().getResource("favorite (2).png"));
        Image imgbtn2=imgbtn.getScaledInstance(40,40,Image.SCALE_SMOOTH);//changing the scale of icon
        btn.setIcon(new ImageIcon(imgbtn2));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keyPress4++;
                if(keyPress4%2==1) {
                    Image imgbtn15 = null;
                    try {
                        imgbtn15 = ImageIO.read(getClass().getResource("favorite (3).png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image imgbtn16 = imgbtn15.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    btn.setIcon(new ImageIcon(imgbtn16));
                }else{
                    Image imgbtn17 = null;
                    try {
                        imgbtn17 = ImageIO.read(getClass().getResource("favorite (2).png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image imgbtn18 = imgbtn17.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    btn.setIcon(new ImageIcon(imgbtn18));
                }
            }
        });
        btn.setBackground(Color.BLACK);
        btn.setBorder(null);
        //b1.setBorder(new EmptyBorder(0,70,0,0));
        b2.setBorder(new EmptyBorder(0,13,0,13));
        //b3.setBorder(new EmptyBorder(0,0,0,13));
        b4.setBorder(new EmptyBorder(0,13,0,13));
        btn.setBorder(new EmptyBorder(0,13,0,13));
        JPanel box= new JPanel();//set position to left of panel
        box.setLayout(new BoxLayout(box,BoxLayout.X_AXIS));
        box.add(b1);
        box.add(b2);
        box.add(b3);
        box.add(b4);
        box.add(b5);
        box.add(btn);
        box.setBackground(Color.BLACK);
        box.setBorder(new EmptyBorder(0,0,20,0));
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(box);
        this.add(new Slider());
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }

}
