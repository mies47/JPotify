import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * indicates left of gui
 */
public class LeftOfGUI extends JPanel {
    JLabel l;


    public Homepanel getHomepanel() {
        return homepanel;
    }
    FavoriteOrSong favoriteOrSong;

    public void setRecentOrSong(RecentOrSong recentOrSong) {
        this.recentOrSong = recentOrSong;
    }

    RecentOrSong recentOrSong;
    public void setFavoriteOrSong(FavoriteOrSong favoriteOrSong) {
        this.favoriteOrSong = favoriteOrSong;
    }
    Homepanel homepanel;

    public LibGUI getLibGUI() {
        return libGUI;
    }

    LibGUI libGUI;
    /**
     * @throws IOException if not find icon throw exception
     * constructor of leftofgui
     */
    public LeftOfGUI(JFrame frame,String s,BtmofGUI b) throws IOException {
        l=new JLabel();
        homepanel = new Homepanel();
        libGUI = new LibGUI(frame,s,b);
        Image img = ImageIO.read(getClass().getResource("images (2).png"));
        l.setIcon(new ImageIcon(img));
        Box box4= Box.createHorizontalBox();//set position to left of panel
        box4.add(l);
        box4.add(Box.createHorizontalGlue());
        Box box= Box.createHorizontalBox();//set position to left of panel
        box.add(homepanel);
        box.add(Box.createHorizontalGlue());
        Box box2= Box.createHorizontalBox();//set position to left of panel
        box2.add(libGUI);
        box2.add(Box.createHorizontalGlue());
        Box box3= Box.createHorizontalBox();//set position to left of panel
        box3.add(new PlayList());
        box3.add(Box.createHorizontalGlue());
        box.setBorder(new EmptyBorder(0,0,20,0));//space between home and library
        box2.setBorder(new EmptyBorder(0,0,15,0));//space between play list and library
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(box);
        this.add(box2);
        this.add(box3);
        this.add(box4);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        homepanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                recentOrSong.changeRecent(false);
                favoriteOrSong.changeFav(false);
                Component c =frame.getRootPane().getContentPane().getComponent(2);
                if(c instanceof MiddleGUI){
                    ((MiddleGUI) c).jPanel.removeAll();
                    JButton songs;
                    JButton playList;
                    JButton albums;
                    JButton favorites;
                    songs = new JButton("Songs");
                    playList = new JButton("Playlist");
                    albums = new JButton("Albums");
                    favorites = new JButton("Favorites");
                    songs.setBackground(Color.BLACK);
                    albums.setBackground(Color.BLACK);
                    playList.setBackground(Color.BLACK);
                    favorites.setBackground(Color.BLACK);
                    songs.setForeground(Color.WHITE);
                    albums.setForeground(Color.WHITE);
                    playList.setForeground(Color.WHITE);
                    favorites.setForeground(Color.WHITE);
                    albums.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            favoriteOrSong.changeFav(false);
                            Component c = frame.getRootPane().getContentPane().getComponent(2);
                            Component c1 = frame.getRootPane().getContentPane().getComponent(0);
                            if (c instanceof MiddleGUI) {
                                ((MiddleGUI) c).jPanel.removeAll();
                                ((MiddleGUI) c).jPanel.setLayout(new WrapLayout(FlowLayout.LEFT));
                                ArrayList<Album> list = new ArrayList<>();
//                                ArrayList<String> temp=new ArrayList<>();
//                                temp.add("/home/naha/Downloads/Reza_Bahram-Atash_Gratomic.com.mp3");
//                                temp.add("/home/naha/Downloads/Mohsen Ebrahimzadeh - Emshab.mp3");
//                                try {
//                                    list.add(new Album(s,frame));
//                                } catch (InvalidDataException e) {
//                                    e.printStackTrace();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                } catch (UnsupportedTagException e) {
//                                    e.printStackTrace();
//                                }
//                    String songDir;
//                    for (String s:a) {
//                        try {
//                            list.add(new Album(s,user));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (InvalidDataException e) {
//                            e.printStackTrace();
//                        } catch (UnsupportedTagException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
                                for (int i = 0; i < list.size(); i++) {
                                    ((MiddleGUI) c).jPanel.add(list.get(i));
                                }
                                ((MiddleGUI) c).jPanel.setBackground(Color.BLACK);
                                ((MiddleGUI) c).jPanel.setVisible(true);
                                frame.validate();
                                frame.invalidate();
                                frame.repaint();
                            }
                        }
                    });
                    favorites.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            favoriteOrSong.changeFav(true);
                            Component c =frame.getRootPane().getContentPane().getComponent(2);
                            if(c instanceof MiddleGUI){
                                ((MiddleGUI) c).jPanel.removeAll();
                                ((MiddleGUI) c).jPanel.setLayout(new WrapLayout(FlowLayout.LEFT));
                                ArrayList<SongPlaylist> list = new ArrayList<>();
                                String songDir;
                                File f=new File(s+"favorite");
                                Scanner scannerSong = null;
                                try {
                                    scannerSong = new Scanner(f);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                while(scannerSong.hasNextLine()){
                                    songDir=scannerSong.nextLine();
                                    try {
                                        list.add(new SongPlaylist(songDir,s,frame));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (InvalidDataException e) {
                                        e.printStackTrace();
                                    } catch (UnsupportedTagException e) {
                                        e.printStackTrace();
                                    }
                                }
                                for (int i = 0; i < list.size(); i++) {
                                    list.get(i).setNewSong(b);
                                    list.get(i).setPlayAddedSong(b.PS);
                                }
                                for (int i = 0 ; i < list.size() ; i++){
                                    ((MiddleGUI) c).jPanel.add(list.get(i));
                                }
                                ((MiddleGUI) c).jPanel.setBackground(Color.BLACK);
                                ((MiddleGUI) c).jPanel.setVisible(true);
                                frame.validate();
                                frame.invalidate();
                                frame.repaint();
                            }
                        }
                    });
                    songs.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            favoriteOrSong.changeFav(false);
                            Component c =frame.getRootPane().getContentPane().getComponent(2);
                            if(c instanceof MiddleGUI){
                                ((MiddleGUI) c).jPanel.removeAll();
                                ((MiddleGUI) c).jPanel.setLayout(new WrapLayout(FlowLayout.LEFT));
                                ArrayList<SongPlaylist> list = new ArrayList<>();
                                String songDir;
                                File f=new File(s+"songs");
                                Scanner scannerSong = null;
                                try {
                                    scannerSong = new Scanner(f);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                while(scannerSong.hasNextLine()){
                                    songDir=scannerSong.nextLine();
                                    try {
                                        list.add(new SongPlaylist(songDir,s,frame));
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (InvalidDataException e) {
                                        e.printStackTrace();
                                    } catch (UnsupportedTagException e) {
                                        e.printStackTrace();
                                    }
                                }
                                for (int i = 0; i < list.size(); i++) {
                                    list.get(i).setNewSong(b);
                                    list.get(i).setPlayAddedSong(b.PS);
                                }
                                for (int i = 0 ; i < list.size() ; i++){
                                    ((MiddleGUI) c).jPanel.add(list.get(i));
                                }
                                ((MiddleGUI) c).jPanel.setBackground(Color.BLACK);
                                ((MiddleGUI) c).jPanel.setVisible(true);
                                frame.validate();
                                frame.invalidate();
                                frame.repaint();
                            }
                        }
                    });
//                    songs.setBorder(null);
//                    albums.setBorder(null);
//                    playList.setBorder(null);
//                    favorites.setBorder(null);
                    try {
                        songs.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("allsong2.jpeg")).getScaledInstance(
                                600 , 430 , Image.SCALE_SMOOTH)));
                        playList.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("playlist.jpeg")).getScaledInstance(
                                630 , 430 , Image.SCALE_SMOOTH)));
                        albums.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("album.jpg")).getScaledInstance(
                                590 , 430 , Image.SCALE_SMOOTH)));
                        favorites.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("download (1).jpeg")).getScaledInstance(
                                620 , 430 , Image.SCALE_SMOOTH)));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    ((MiddleGUI) c).jPanel.setLayout(new WrapLayout(FlowLayout.LEFT));
                    ((MiddleGUI) c).jPanel.add(songs);
                    ((MiddleGUI) c).jPanel.add(playList);
                    ((MiddleGUI) c).jPanel.add(albums);
                    ((MiddleGUI) c).jPanel.add(favorites);
                    ((MiddleGUI) c).jPanel.setBackground(Color.BLACK);
                    ((MiddleGUI) c).jPanel.setVisible(true);
                    frame.validate();
                    frame.invalidate();
                    frame.repaint();
                }
            }
        });
    }

    public void setL(Image img) {
        l.setIcon(new ImageIcon(img.getScaledInstance(275, 225 , Image.SCALE_SMOOTH)));
    }
}
