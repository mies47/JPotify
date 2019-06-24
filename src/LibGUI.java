import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * library show manage
 * manage add songs
 * mange song and albums Graphics show
 */
public class LibGUI extends JPanel {
    static JLabel l;
    static JButton b1,b2,b3;


    SongPlaylist songPlay;
    AddSongPlaylist songPlaylist;

    /**
     * 1 label and 3 button
     * @throws IOException if not find icon throws exception
     */
    public LibGUI(JFrame frame,String user) throws IOException {
        l=new JLabel("YOUR LIBRARY: ");
        l.setBackground(Color.BLACK);
        l.setFont(new Font("",Font.PLAIN,20));
        l.setOpaque(true);//show background color
        l.setForeground(Color.WHITE);
        b1=new JButton("Songs");
        b1.setBorder(null);
        b1.setFont(new Font("",Font.PLAIN,17));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b2=new JButton("Albums");
        b2.setFont(new Font("",Font.PLAIN,17));
        b2.setBorder(null);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        JButton bFavorite=new JButton("Favorite");
        bFavorite.setBorder(null);
        bFavorite.setFont(new Font("",Font.PLAIN,17));
        bFavorite.setBackground(Color.BLACK);
        bFavorite.setForeground(Color.WHITE);
        b3=new JButton("ADD");
        b3.setBorder(null);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("mp3", "mp3"));
                int i=fileChooser.showOpenDialog(frame);
                if(i==JFileChooser.APPROVE_OPTION){
                    File f=fileChooser.getSelectedFile();
                    String filepath=f.getPath();
                    String songDir;
                    File fileSong=new File(user+"songs");
                    Boolean fileIsExist=false;
                    BufferedWriter out = null;//open append mode
                    try {
                        out = new BufferedWriter(new FileWriter(user+"songs", true));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        Scanner scannerSong = new Scanner(fileSong);
                        while(scannerSong.hasNextLine()){
                            songDir=scannerSong.nextLine();
                            if(filepath.equals(songDir))
                                fileIsExist=true;
                        }
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    if(!fileIsExist) {
                        if (System.getProperty("os.name").contains("Windows")) {
                            try {
                                out.write(filepath + "\r\n");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            try {
                                out.write(filepath + "\n");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        try {
                            System.out.println(filepath);
                            songPlay=new SongPlaylist(filepath);
                            songPlaylist.addSong(songPlay);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (InvalidDataException ex) {
                            ex.printStackTrace();
                        } catch (UnsupportedTagException ex) {
                            ex.printStackTrace();
                        }
                    }
                    try {
                        out.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    frame.invalidate();
                    frame.validate();
                    frame.repaint();

                }
            }
        });
        JButton bPlayMovie=new JButton("PlayMovie");
        bPlayMovie.setFont(new Font("",Font.PLAIN,17));
        bPlayMovie.setBorder(null);
        bPlayMovie.setBackground(Color.BLACK);
        bPlayMovie.setForeground(Color.WHITE);
        Image img = ImageIO.read(getClass().getResource("images (1).png"));
        Image img2=img.getScaledInstance(30,30,Image.SCALE_SMOOTH);//changing the scale of icon
        b3.setIcon(new ImageIcon(img2));
        Box box= Box.createHorizontalBox();//set position to left of panel
        box.add(b2);
        box.add(Box.createHorizontalGlue());
        Box boxF= Box.createHorizontalBox();//set position to left of panel
        boxF.add(bFavorite);
        boxF.add(Box.createHorizontalGlue());
        Box boxPlayMovie= Box.createHorizontalBox();//set position to left of panel
        boxPlayMovie.add(bPlayMovie);
        boxPlayMovie.add(Box.createHorizontalGlue());
        box.setBorder(new EmptyBorder(5,0,0,0));//space between two rows
        Box box2= Box.createHorizontalBox();//set position to left of panel
        box2.add(b1);
        box2.add(Box.createHorizontalGlue());
        box2.setBorder(new EmptyBorder(10,0,0,0));//space between two rows
        Box box3= Box.createHorizontalBox();//set position to left of panel
        box3.add(l);
        box3.add(b3);
        box3.add(Box.createHorizontalGlue());
        boxF.setBorder(new EmptyBorder(5,0,0,0));//space between two rows
        boxPlayMovie.setBorder(new EmptyBorder(5,0,0,0));//space between two rows
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(box3);
        this.add(box2);
        this.add(box);
        this.add(boxF);
        this.add(boxPlayMovie);
        setBackground(Color.BLACK);
        setVisible(true);
    }
    public void setSongPlaylist(AddSongPlaylist songPlaylist) {
        this.songPlaylist = songPlaylist;
    }
}
