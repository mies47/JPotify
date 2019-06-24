import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import com.mpatric.mp3agic.*;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author naha and milad
 * 3 int param for time
 */
public class PlayStop extends JPanel implements PlayAddedSong {
    private volatile int keyPress = 0;
    private int keyPress2 = 0;
    private int keyPress4 = 0;
    private int keyPress3 = 0;
    private Thread finalThread;
    final Thread[] finalTh = new Thread[1];
    private Thread newSongThread;
    private int num = 0;
    private NewPlayer np;
    private int total;
    private volatile boolean oneRepeatFlag = false;
    private volatile boolean newSong = false;
    private volatile boolean nextOrPreviousSong = false;
    private ArrayList<File> allMp3Files = new ArrayList<>();
    final PausablePlayer[] finalPlayer = new PausablePlayer[1];
    Slider slider;
    JButton b3;
    JFrame jFrame;
    volatile File file;
    volatile String userName;
    volatile Mp3File mp3File;
    volatile int counter;
    /**
     * @throws IOException if not find icon throw exception
     */
    public PlayStop(JFrame jFrame, File fileName , String userName) throws IOException, InterruptedException, JavaLayerException {
        file=fileName;
        this.userName = userName;
        this.jFrame = jFrame;
        JButton b1, b2, b4, b5;
        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        b4 = new JButton();
        b5 = new JButton();
        slider = new Slider("0:00", "0:00");
        Image img = ImageIO.read(getClass().getResource("shuffle (1).png"));
        Image img2 = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
        b1.setIcon(new ImageIcon(img2));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.BLACK);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {//Action for shuffle button
                keyPress2++;
                if (keyPress2 % 2 == 1) {
                    Image img15 = null;
                    try {
                        img15 = ImageIO.read(getClass().getResource("shuffle (2).png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image img16 = img15.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    b1.setIcon(new ImageIcon(img16));
                } else {
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
        Image img4 = img3.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
        b2.setIcon(new ImageIcon(img4));
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayStop.this.nextOrPreviousSong = true;
                if(finalPlayer[0] != null){
                    finalPlayer[0].close();
                }
                counter = 0;
                for(File f : allMp3Files){
                    if(file.getPath().equals(f.getPath())){
                        break;
                    }
                    ++counter;
                }
                if(counter <= 0){
                    counter = allMp3Files.size();
                }
                try {
                    finalPlayer[0] = new PausablePlayer(new FileInputStream(allMp3Files.get(--counter)));
                    file = allMp3Files.get(counter);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                try {
                    finalPlayer[0].play();
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
                slider.b.setValue(0);
                try {
                    mp3File = new Mp3File(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedTagException e1) {
                    e1.printStackTrace();
                } catch (InvalidDataException e1) {
                    e1.printStackTrace();
                }
                keyPress = 0;
                PlayStop.this.b3.doClick();
            }
        });
        Image img5 = ImageIO.read(getClass().getResource("play3.png"));
        Image img6 = img5.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b3.setIcon(new ImageIcon(img6));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.BLACK);
        PausablePlayer player = null;
        try {
            if(file!=null)
                finalPlayer[0] = new PausablePlayer(new FileInputStream(file)); // a pauseable player for the file given

        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

        final Thread[] th = {null};
        final boolean[] flag = {true};
        mp3File = null;
        try {
            if(file!=null)
                mp3File = new Mp3File(file);
        } catch (IOException | UnsupportedTagException | InvalidDataException e) {
            e.printStackTrace();
        }
        Mp3File finalMp3File = mp3File;

        final Thread[] finalTh = {th[0]};
//        while (true) {
//            if(newSong){
//                keyPress = 0;
//                finalPlayer[0].close();
//                if(finalTh[0]!= null){
//                    finalTh[0].interrupt();
//                }
//                b3.doClick();
//                newSong = false;
//
//            }else {
//                break;
//            }
//        }
        Mp3File finalMp3File1 = mp3File;
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                if(newSong){
//                    keyPress = 0;
//                }
//                if(newSong){
//                    newSong = false;
//                    jFrame.invalidate();
//                    jFrame.validate();
//                    jFrame.repaint();
//                }
                keyPress++;
                if (keyPress % 2 == 1) {
                    if(finalTh[0] != null && (newSong||nextOrPreviousSong)){
                        finalTh[0].interrupt();
                    }
                    if (finalThread == null || !finalThread.isAlive()) {//check if the thread for seek is dead
                        Image img11 = null;
                        finalTh[0] = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                flag[0] = true;
                                while (true) {
                                    slider.b.setMaximum((int) mp3File.getLengthInMilliseconds() / 1000);
                                    slider.b.setValue(slider.b.getValue() + 1);//moving the progressbar forward
                                    PlayStop.this.slider.changeLabel((int)(slider.b.getValue() * mp3File.getLengthInMilliseconds() / (1000 *
                                            slider.b.getMaximum())) , (int)mp3File.getLengthInMilliseconds() / 1000);
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        return;
                                    }
                                    if (slider.b.getMaximum() == slider.b.getValue()) {//if the file ended
                                        slider.b.setValue(0);
                                        PlayStop.this.slider.changeLabel((int)(slider.b.getValue() * mp3File.getLengthInMilliseconds() / (1000 *
                                                slider.b.getMaximum())) , (int)mp3File.getLengthInMilliseconds() / 1000);
                                        try {
                                            b3.setIcon(new ImageIcon((ImageIO.read(getClass().getResource("play3.png"))).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        flag[0] = false;
                                        finalPlayer[0].close();
                                        if (np != null) {
                                            np.getNewFile().delete();
                                        }
                                        keyPress = 0;
                                        try {
                                            finalPlayer[0] = new PausablePlayer(new FileInputStream(file));
                                        } catch (JavaLayerException e) {
                                            e.printStackTrace();
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                        if(oneRepeatFlag){
                                            try {
                                                finalPlayer[0].play();
                                                b3.setIcon(new ImageIcon((ImageIO.read(getClass().getResource("pause2.png"))).getScaledInstance(40, 40, Image.SCALE_SMOOTH)));
                                                keyPress = 1;
                                                newSong = false;
                                                jFrame.invalidate();
                                                jFrame.validate();
                                                jFrame.repaint();

                                            } catch (JavaLayerException e) {
                                                e.printStackTrace();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }else {
                                            break;
                                        }
                                    }
                                }
                            }
                        });
                        try {
                            if(finalPlayer[0] != null) {
                                finalPlayer[0].play();
                            }
                            synchronized (finalTh[0]) {//start the progressbar thread
                                finalTh[0].start();
                            }
                            Component[] components = jFrame.getRootPane().getContentPane().getComponents();
                            for (Component c : components) {
                                if (c instanceof BtmofGUI) {
                                    if (mp3File.hasId3v1Tag()) {
                                        if (mp3File.getId3v1Tag().getTrack() != null) {
                                            ((BtmofGUI) c).nL.l1.setText("Song: " + mp3File.getId3v1Tag().getTrack());
                                            if (mp3File.getId3v1Tag().getArtist() != null) {
                                                ((BtmofGUI) c).nL.l2.setText("Artist: " + mp3File.getId3v1Tag().getArtist());
                                            } else {
                                                ((BtmofGUI) c).nL.l2.setText("Artist: Unknown ");
                                            }
                                        } else {
                                            ((BtmofGUI) c).nL.l1.setText("Song: Unknown");
                                            if (mp3File.getId3v1Tag().getArtist() != null) {
                                                ((BtmofGUI) c).nL.l2.setText("Artist: " + mp3File.getId3v1Tag().getArtist());
                                            } else {
                                                ((BtmofGUI) c).nL.l2.setText("Artist: Unknown ");
                                            }
                                        }
                                    } else if (mp3File.hasId3v2Tag()) {
                                        if (mp3File.getId3v2Tag().getTrack() != null) {
                                            ((BtmofGUI) c).nL.l1.setText("Song: " + mp3File.getId3v2Tag().getTrack());
                                            if (mp3File.getId3v2Tag().getArtist() != null) {
                                                ((BtmofGUI) c).nL.l2.setText("Artist: " + mp3File.getId3v2Tag().getArtist());
                                            } else {
                                                ((BtmofGUI) c).nL.l2.setText("Artist: Unknown ");
                                            }
                                        } else {
                                            ((BtmofGUI) c).nL.l1.setText("Song: Unknown");
                                            if (mp3File.getId3v2Tag().getArtist() != null) {
                                                ((BtmofGUI) c).nL.l2.setText("Artist: " + mp3File.getId3v2Tag().getArtist());
                                            } else {
                                                ((BtmofGUI) c).nL.l2.setText("Artist: Unknown ");
                                            }
                                        }
                                    } else {
                                        ((BtmofGUI) c).nL.l2.setText("Artist: Unknown ");
                                        ((BtmofGUI) c).nL.l1.setText("Song: Unknown ");
                                    }
                                }
                                if (c instanceof LeftOfGUI) {
                                    if (mp3File.hasId3v2Tag()) {
                                        if (mp3File.getId3v2Tag().getAlbumImage() != null) {
                                            BufferedImage image = ImageIO.read(new ByteArrayInputStream(mp3File.getId3v2Tag().getAlbumImage()));
                                            ((LeftOfGUI) c).setL(image);
                                        }
                                    }
                                }
                            }
                        } catch (JavaLayerException | IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            img11 = ImageIO.read(getClass().getResource("pause2.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Image img12 = img11.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                        b3.setIcon(new ImageIcon(img12));
                    }
                } else {
                    Image img13;
                    if (finalPlayer[0] != null) {
                        finalPlayer[0].pause();
                    }
                    try {
                        img13 = ImageIO.read(getClass().getResource("play3.png"));
                        Image img14 = img13.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                        b3.setIcon(new ImageIcon(img14));
                        finalTh[0].interrupt();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        Image img7 = ImageIO.read(getClass().getResource("right-arrow.png"));
        Image img8 = img7.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
        File userNameFile = new File(userName + "songs");
        try {
            Scanner userScanner = new Scanner(userNameFile);
            while (userScanner.hasNextLine()){
                allMp3Files.add(new File(userScanner.nextLine()));
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        b4.setIcon(new ImageIcon(img8));
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayStop.this.nextOrPreviousSong = true;
                if(finalPlayer[0] != null){
                    finalPlayer[0].close();
                }
                int counter = 0;
                for(File f : allMp3Files){
                    if(file.getPath().equals(f.getPath())){
                        break;
                    }
                    counter++;
                }
                if(counter >= allMp3Files.size() - 1){
                    counter = -1;
                }
                try {
                    finalPlayer[0] = new PausablePlayer(new FileInputStream(allMp3Files.get(++counter)));
                    file = allMp3Files.get(counter);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                try {
                    finalPlayer[0].play();
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
                slider.b.setValue(0);
                try {
                    mp3File = new Mp3File(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedTagException e1) {
                    e1.printStackTrace();
                } catch (InvalidDataException e1) {
                    e1.printStackTrace();
                }
                keyPress = 0;
                PlayStop.this.b3.doClick();
            }
        });
        Image img9 = ImageIO.read(getClass().getResource("repeat.png"));
        Image img10 = img9.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
        b5.setIcon(new ImageIcon(img10));
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.BLACK);
        b5.addActionListener(new ActionListener() {//Action listener for repeat button
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                keyPress3++;
                if (keyPress3 % 3 == 1) {
                    Image img19 = null;
                    try {
                        img19 = ImageIO.read(getClass().getResource("repeat2.png"));
                        oneRepeatFlag = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image img20 = img19.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    b5.setIcon(new ImageIcon(img20));
                } else if (keyPress3 % 3 == 2) {
                    Image img23 = null;
                    try {
                        img23 = ImageIO.read(getClass().getResource("repeat3.png"));
                        oneRepeatFlag = false;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image img24 = img23.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    b5.setIcon(new ImageIcon(img24));
                } else {
                    Image img21 = null;
                    try {
                        img21 = ImageIO.read(getClass().getResource("repeat.png"));
                        oneRepeatFlag = false;
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
        JButton btn = new JButton();
        Image imgbtn = ImageIO.read(getClass().getResource("favorite (2).png"));
        Image imgbtn2 = imgbtn.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
        btn.setIcon(new ImageIcon(imgbtn2));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) { // favorite button
                keyPress4++;
                if (keyPress4 % 2 == 1) {
                    Image imgbtn15 = null;
                    try {
                        imgbtn15 = ImageIO.read(getClass().getResource("favorite (3).png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Image imgbtn16 = imgbtn15.getScaledInstance(40, 40, Image.SCALE_SMOOTH);//changing the scale of icon
                    btn.setIcon(new ImageIcon(imgbtn16));
                } else {
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
        b2.setBorder(new EmptyBorder(0, 13, 0, 13));
        //b3.setBorder(new EmptyBorder(0,0,0,13));
        b4.setBorder(new EmptyBorder(0, 13, 0, 13));
        btn.setBorder(new EmptyBorder(0, 13, 0, 13));
        JPanel box = new JPanel();//set position to left of panel
        box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS));
        box.add(b1);
        box.add(b2);
        box.add(b3);
        box.add(b4);
        box.add(b5);
        box.add(btn);
        box.setBackground(Color.BLACK);
        box.setBorder(new EmptyBorder(0, 0, 20, 0));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(box);
        this.add(slider);
        slider.b.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                num = e.getX();
                slider.b.setValue(num * slider.b.getMaximum() / slider.b.getWidth());
                PlayStop.this.slider.changeLabel((int)(slider.b.getValue() * mp3File.getLengthInMilliseconds() / (1000 *
                        slider.b.getMaximum())) , (int)mp3File.getLengthInMilliseconds() / 1000);
                finalPlayer[0].close();
                super.mouseClicked(e);
                finalThread = new Thread(() -> {
                    try {
                        synchronized (finalPlayer[0]) {
                            np = new NewPlayer(file, (int) (num * mp3File.getLengthInMilliseconds() / (slider.b.getWidth() * 1000)));
                            finalPlayer[0] = new PausablePlayer(new FileInputStream(np.getNewFile()));
                            finalPlayer[0].play();
                        }
                    } catch (JavaLayerException | UnsupportedTagException | IOException | InvalidDataException e1) {
                        e1.printStackTrace();
                    }
                });
                finalThread.start();


            }
        });

        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setMp3File(Mp3File mp3File) {
        this.mp3File = mp3File;
    }
    @Override
    public void playAddedSong(boolean newSong) throws IOException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        this.newSong = newSong;
        if(finalPlayer[0] != null){
            finalPlayer[0].close();
        }
        finalPlayer[0] = new PausablePlayer(new FileInputStream(file));
        finalPlayer[0].play();
        slider.b.setValue(0);
        mp3File = new Mp3File(file);
        keyPress = 0;
        this.b3.doClick();

    }
}
