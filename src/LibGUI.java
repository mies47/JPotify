import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import java.util.Scanner;

/**
 * library show manage
 * manage add songs
 * mange song and albums Graphics show
 */
public class LibGUI extends JPanel {
    static JLabel l;
    static JButton b1, b2, b3;

    FavoriteOrSong favoriteOrSong;

    SongPlaylist songPlay;
    AddSongPlaylist songPlaylist;

    public void setRecentOrSong(RecentOrSong recentOrSong) {
        this.recentOrSong = recentOrSong;
    }
    RecentOrSong recentOrSong;
    /**
     * 1 label and 3 button
     *
     * @throws IOException if not find icon throws exception
     */
    public LibGUI(JFrame frame, String user, BtmofGUI b) throws IOException {
        l = new JLabel("YOUR LIBRARY: ");
        l.setBackground(Color.BLACK);
        l.setFont(new Font("", Font.PLAIN, 20));
        l.setOpaque(true);//show background color
        l.setForeground(Color.WHITE);
        b1 = new JButton("Songs");
        b1.setBorder(null);
        b1.setFont(new Font("", Font.PLAIN, 17));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                favoriteOrSong.changeFav(false);
                recentOrSong.changeRecent(false);

                Component c = frame.getRootPane().getContentPane().getComponent(2);
                if (c instanceof MiddleGUI) {
                    ((MiddleGUI) c).jPanel.removeAll();
                    ((MiddleGUI) c).jPanel.setLayout(new WrapLayout(FlowLayout.LEFT));
                    ArrayList<SongPlaylist> list = new ArrayList<>();
                    String songDir;
                    File f = new File(user + "songs");
                    Scanner scannerSong = null;
                    try {
                        scannerSong = new Scanner(f);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    while (scannerSong.hasNextLine()) {
                        songDir = scannerSong.nextLine();
                        try {

                            list.add(new SongPlaylist(songDir, user,frame));

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InvalidDataException e) {
                            e.printStackTrace();
                        } catch (UnsupportedTagException e) {
                            e.printStackTrace();
                        }
                    }
                    //songPlaylist.addSong(songPlay);
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setNewSong(b);
                        list.get(i).setPlayAddedSong(b.PS);
                    }
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
        b2 = new JButton("Albums");
        b2.setFont(new Font("", Font.PLAIN, 17));
        b2.setBorder(null);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                favoriteOrSong.changeFav(false);
                recentOrSong.changeRecent(false);
                Component c = frame.getRootPane().getContentPane().getComponent(2);
                Component c1 = frame.getRootPane().getContentPane().getComponent(0);
                if (c instanceof MiddleGUI) {
                    ((MiddleGUI) c).jPanel.removeAll();
                    ((MiddleGUI) c).jPanel.setLayout(new WrapLayout(FlowLayout.LEFT));
                    ArrayList<Album> list = new ArrayList<>();
                    HashMap<String,ArrayList<String>> a=new HashMap<>();
                    File f=new File(user+"songs");
                    Scanner s=null;
                    try {
                        s=new Scanner(f);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    String trash;
                    while(s.hasNextLine()) {
                        trash = s.nextLine();
                        Mp3File mp3File = null;
                        try {
                            mp3File = new Mp3File(trash);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (UnsupportedTagException e) {
                            e.printStackTrace();
                        } catch (InvalidDataException e) {
                            e.printStackTrace();
                        }
                        if (mp3File.hasId3v1Tag()){
                            if (a.containsKey(mp3File.getId3v1Tag().getAlbum())) {
                                a.get(mp3File.getId3v1Tag().getAlbum()).add(trash);
                            } else {
                                ArrayList<String> rate=new ArrayList<>();
                                rate.add(trash);
                                a.put(mp3File.getId3v1Tag().getAlbum(),rate);
                            }
                        }
                        else if(mp3File.hasId3v2Tag()){
                            if (a.containsKey(mp3File.getId3v2Tag().getAlbum())) {
                                a.get(mp3File.getId3v2Tag().getAlbum()).add(trash);
                            } else {
                                ArrayList<String> rate=new ArrayList<>();
                                rate.add(trash);
                                a.put(mp3File.getId3v2Tag().getAlbum(),rate);
                            }
                        }
                    }
                    for(String p:a.keySet()){
                        try {
                            Album album=new Album(a.get(p),user,frame);
                            list.add(album);
                        } catch (InvalidDataException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (UnsupportedTagException e) {
                            e.printStackTrace();
                        }
                    }
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
        JButton bFavorite = new JButton("Favorite");
        bFavorite.setBorder(null);
        bFavorite.setFont(new Font("", Font.PLAIN, 17));
        bFavorite.setBackground(Color.BLACK);
        bFavorite.setForeground(Color.WHITE);
        bFavorite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                recentOrSong.changeRecent(false);

                favoriteOrSong.changeFav(true);
                Component c = frame.getRootPane().getContentPane().getComponent(2);
                if (c instanceof MiddleGUI) {
                    ((MiddleGUI) c).jPanel.removeAll();
                    ((MiddleGUI) c).jPanel.setLayout(new WrapLayout(FlowLayout.LEFT));
                    ArrayList<SongPlaylist> list = new ArrayList<>();
                    String songDir;
                    File f = new File(user + "favorite");
                    Scanner scannerSong = null;
                    try {
                        scannerSong = new Scanner(f);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    while (scannerSong.hasNextLine()) {
                        songDir = scannerSong.nextLine();
                        try {
                            list.add(new SongPlaylist(songDir, user,frame));

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
        b3 = new JButton("ADD");
        b3.setBorder(null);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setDialogTitle("Multiple file and directory selection:");
                jfc.setMultiSelectionEnabled(true);
                jfc.addChoosableFileFilter(new FileNameExtensionFilter("mp3" , "mp3"));
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int returnValue = jfc.showDialog(null, "choose");
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File[] files = jfc.getSelectedFiles();
                    Arrays.asList(files).forEach(x -> {
                        if (x.isFile()) {
                            File f =x;
                            String filepath = f.getPath();
                            String songDir;
                            File fileSong = new File(user + "songs");
                            Boolean fileIsExist = false;
                            BufferedWriter out = null;//open append mode
                            try {
                                out = new BufferedWriter(new FileWriter(user + "songs", true));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            PrintStream out2 = null;//open append mode
                            try {
                                out2 = new PrintStream(new FileOutputStream(user + "Recentsongs", true));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            try {
                                Scanner scannerSong = new Scanner(fileSong);
                                while (scannerSong.hasNextLine()) {
                                    songDir = scannerSong.nextLine();
                                    if (filepath.contains(songDir))
                                        fileIsExist = true;
                                }
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            }
                            if (!fileIsExist) {
                                out2.println(filepath);
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
                                    songPlay = new SongPlaylist(filepath, user,frame);
                                    songPlay.setNewSong(b);
                                    songPlay.setPlayAddedSong(b.PS);
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

                    })
                    ;
                }

//                JFileChooser fileChooser = new JFileChooser();
//                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("mp3", "mp3"));
//                int i=fileChooser.showOpenDialog(frame);
//                if(i==JFileChooser.APPROVE_OPTION){
//                    File f=fileChooser.getSelectedFile();
//                    String filepath=f.getPath();
//                    String songDir;
//                    File fileSong=new File(user+"songs");
//                    Boolean fileIsExist=false;
//                    BufferedWriter out = null;//open append mode
//                    try {
//                        out = new BufferedWriter(new FileWriter(user+"songs", true));
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                    PrintStream out2 = null;//open append mode
//                    try {
//                        out2 = new PrintStream(new FileOutputStream(user + "Recentsongs",true));
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                    try {
//                        Scanner scannerSong = new Scanner(fileSong);
//                        while(scannerSong.hasNextLine()){
//                            songDir=scannerSong.nextLine();
//                            if(filepath.equals(songDir))
//                                fileIsExist=true;
//                        }
//                    } catch (FileNotFoundException ex) {
//                        ex.printStackTrace();
//                    }
//                    if(!fileIsExist) {
//                        out2.println(filepath);
//                        if (System.getProperty("os.name").contains("Windows")) {
//                            try {
//                                out.write(filepath + "\r\n");
//                            } catch (IOException ex) {
//                                ex.printStackTrace();
//                            }
//                        } else {
//                            try {
//                                out.write(filepath + "\n");
//                            } catch (IOException ex) {
//                                ex.printStackTrace();
//                            }
//                        }
//                        try {
//                            songPlay=new SongPlaylist(filepath,user);
//                            songPlay.setNewSong(b);
//                            songPlay.setPlayAddedSong(b.PS);
//                            songPlaylist.addSong(songPlay);
//                        } catch (IOException ex) {
//                            ex.printStackTrace();
//                        } catch (InvalidDataException ex) {
//                            ex.printStackTrace();
//                        } catch (UnsupportedTagException ex) {
//                            ex.printStackTrace();
//                        }
//                    }
//                    try {
//                        out.close();
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//
//                    frame.invalidate();
//                    frame.validate();
//                    frame.repaint();
//
//                }
            }
        });
        JButton bPlayMovie = new JButton("Recntly Played");
        bPlayMovie.setFont(new Font("", Font.PLAIN, 17));
        bPlayMovie.setBorder(null);
        bPlayMovie.setBackground(Color.BLACK);
        bPlayMovie.setForeground(Color.WHITE);
        bPlayMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                recentOrSong.changeRecent(true);
                favoriteOrSong.changeFav(false);
                File recent = new File(user + "Recentsongs");
                ArrayList<String> a = new ArrayList<>();
                Scanner scannerRecent = null;
                try {
                    scannerRecent = new Scanner(recent);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while (scannerRecent.hasNextLine()) {
                    a.add(scannerRecent.nextLine());
                }
//                int p = a.indexOf(file.getPath());
//                a.add(0, a.get(a.indexOf(file.getPath())));
//                a.remove(p + 1);
                PrintStream out = null;//open append mode
                try {
                    out = new PrintStream(new FileOutputStream(user + "Recentsongs"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                for (String s : a) {
                    if (System.getProperty("os.name").contains("Windows")) {
                        out.println(s);
                    } else {
                        out.print(s + "\n");
                    }
                }
                Component cTemp = frame.getRootPane().getContentPane().getComponent(2);
                Component cBtm = frame.getRootPane().getContentPane().getComponent(0);
                if (cTemp instanceof MiddleGUI) {
                    ((MiddleGUI) cTemp).jPanel.removeAll();
                    ((MiddleGUI) cTemp).jPanel.setLayout(new WrapLayout(FlowLayout.LEFT));
                    ArrayList<SongPlaylist> list = new ArrayList<>();
                    String songDir;
                    File f = new File(user + "Recentsongs");
                    Scanner scannerSong = null;
                    try {
                        scannerSong = new Scanner(f);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    while (scannerSong.hasNextLine()) {
                        songDir = scannerSong.nextLine();
                        try {
                            list.add(new SongPlaylist(songDir, user,frame));

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InvalidDataException e) {
                            e.printStackTrace();
                        } catch (UnsupportedTagException e) {
                            e.printStackTrace();
                        }
                    }
//                    int p = a.indexOf(((BtmofGUI)cBtm).fileName.getPath());
//                    a.add(0, a.get(a.indexOf(((BtmofGUI)cBtm).fileName.getPath())));
//                    a.remove(p + 1);
                    //songPlaylist.addSong(songPlay);
                    for (int i = 0; i < list.size(); i++) {
                        Component c = frame.getRootPane().getContentPane().getComponent(0);
                        list.get(i).setNewSong((BtmofGUI) c);
                        list.get(i).setPlayAddedSong(((BtmofGUI) c).PS);
                    }
                    for (int i = 0; i < list.size(); i++) {
                        ((MiddleGUI) cTemp).jPanel.add(list.get(i));
                    }
                    ((MiddleGUI) cTemp).jPanel.setBackground(Color.BLACK);
                    ((MiddleGUI) cTemp).jPanel.setVisible(true);
                    frame.validate();
                    frame.invalidate();
                    frame.repaint();
                }
            }
        });
        Image img = ImageIO.read(getClass().getResource("images (1).png"));
        Image img2 = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);//changing the scale of icon
        b3.setIcon(new ImageIcon(img2));
        Box box = Box.createHorizontalBox();//set position to left of panel
        box.add(b2);
        box.add(Box.createHorizontalGlue());
        Box boxF = Box.createHorizontalBox();//set position to left of panel
        boxF.add(bFavorite);
        boxF.add(Box.createHorizontalGlue());
        Box boxPlayMovie = Box.createHorizontalBox();//set position to left of panel
        boxPlayMovie.add(bPlayMovie);
        boxPlayMovie.add(Box.createHorizontalGlue());
        box.setBorder(new EmptyBorder(5, 0, 0, 0));//space between two rows
        Box box2 = Box.createHorizontalBox();//set position to left of panel
        box2.add(b1);
        box2.add(Box.createHorizontalGlue());
        box2.setBorder(new EmptyBorder(10, 0, 0, 0));//space between two rows
        Box box3 = Box.createHorizontalBox();//set position to left of panel
        box3.add(l);
        box3.add(b3);
        box3.add(Box.createHorizontalGlue());
        boxF.setBorder(new EmptyBorder(5, 0, 0, 0));//space between two rows
        boxPlayMovie.setBorder(new EmptyBorder(5, 0, 0, 0));//space between two rows
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
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

    public void setFavoriteOrSong(FavoriteOrSong favoriteOrSong) {
        this.favoriteOrSong = favoriteOrSong;
    }
}
