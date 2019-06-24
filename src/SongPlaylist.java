import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * a jpanel that includes the song thumbnail and its description
 * @author Milad
 */
public class SongPlaylist extends JPanel {
    private JLabel pic;
    private JLabel description;
    private PlayAddedSong playAddedSong;

    public void setNewSong(SetSong newSong) {
        this.newSong = newSong;
    }

    private SetSong newSong;
    private Boolean isFavorite;
    private String songDir;
    /**
     *
     * @param s set directory of song
     * @throws IOException if the directory given does not exist
     */
    public SongPlaylist(String s,String user) throws IOException, InvalidDataException, UnsupportedTagException {
        //File songFile=new File(songDir);
        songDir=s;
        Mp3File mp3File = new Mp3File(songDir,true);
        String song = "Unknown";
        String album = "Unknown";
        String date = "No date";
        BufferedImage image = null;
        if(mp3File.getId3v2Tag() != null){
            if(mp3File.getId3v2Tag().getAlbumImage()!= null){
                image =ImageIO.read(new ByteArrayInputStream(mp3File.getId3v2Tag().getAlbumImage()));
            }
            if(mp3File.getId3v2Tag().getTrack() != null){
                song = mp3File.getId3v2Tag().getTrack();
            }
            if(mp3File.getId3v2Tag().getAlbum() != null){
                album = mp3File.getId3v2Tag().getAlbum();
            }
            if(mp3File.getId3v2Tag().getTrack() != null){
                date = mp3File.getId3v2Tag().getDate();
            }

        }else if(mp3File.getId3v1Tag() != null){
            if(mp3File.getId3v1Tag().getTrack() != null){
                song = mp3File.getId3v2Tag().getTrack();
            }
            if(mp3File.getId3v1Tag().getAlbum() != null){
                album = mp3File.getId3v2Tag().getAlbum();
            }
            if(mp3File.getId3v1Tag().getTrack() != null){
                date = mp3File.getId3v2Tag().getDate();
            }
        }
//        songPlaylist.addSong(new SongPlaylist(image , "Song: " + song + "<br>" +"Album: " + album
//                + "<br>" + "Date: " + date));
        Image img1 = image;
        if(img1 == null) {
            img1 = ImageIO.read(getClass().getResource("musicLogo.jpg"));
        }
        img1 = img1.getScaledInstance(200 , 150 , Image.SCALE_SMOOTH);
        pic = new JLabel();
        pic.setIcon(new ImageIcon(img1));
        this.description = new JLabel("<html>"+ "Song: " + song + "<br>" +"Album: " + album + "<br>" + "Date: " + date+"</html>");//to show the text in multiple lines
        this.description.setForeground(Color.LIGHT_GRAY);
        this.description.setBorder(BorderFactory.createMatteBorder(1 , 0 , 0 , 0 , Color.GRAY));
        this.setLayout(new BorderLayout());
        this.add(pic , BorderLayout.CENTER);
        this.add(this.description , BorderLayout.PAGE_END);
        JPopupMenu menu = new JPopupMenu("More");
        menu.setBackground(Color.BLACK);
        menu.setForeground(Color.white);
        JMenuItem item = new JMenuItem("add to PlayList");
        item.setBackground(Color.BLACK);
        item.setForeground(Color.white);
        JMenuItem item2 = new JMenuItem("add to Favorite");
        item2.setBackground(Color.BLACK);
        item2.setForeground(Color.white);
        JMenuItem item3 = new JMenuItem("Remove");
        item3.setBackground(Color.BLACK);
        item3.setForeground(Color.white);
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                BufferedWriter fav = null;
                try {
                    fav = new BufferedWriter(new FileWriter(user + "favorite", true));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                isFavorite=false;
                Scanner scannerSong = null;
                try {
                    scannerSong = new Scanner(new File(user+"favorite"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while(scannerSong.hasNextLine()){
                    if(songDir.equals( scannerSong.nextLine()))
                        isFavorite=true;
                }
                if(!isFavorite) {
                    try {
                        //open append mode
                        if (System.getProperty("os.name").contains("Windows")) {
                            fav.write(songDir + "\r\n");
                        } else {
                            fav.write(songDir + "\n");
                        }
                        fav.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                File f=new File(user+"songs");
                ArrayList<String> allSong=new ArrayList<String>();
                String songPath;
                Scanner scannerSong = null;
                try {
                    scannerSong = new Scanner(f);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while(scannerSong.hasNextLine()){
                    songPath=scannerSong.nextLine();
                    if(!songPath.equals(songDir)){
                        allSong.add(songPath);
                    }
                }

                BufferedWriter song = null;//open append mode
                try {
                    song = new BufferedWriter(new FileWriter(user+"songs"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (String s:allSong) {
                    if(System.getProperty("os.name").contains("Windows")) {
                        try {
                            song.write( s+ "\r\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {
                        try {
                            song.write(s + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    song.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setVisible(false);
                File favTemp=new File(user+"favorite");
                ArrayList<String> allFav=new ArrayList<String>();
                String favPath;
                Scanner scannerFav = null;
                try {
                    scannerFav = new Scanner(favTemp);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                while(scannerFav.hasNextLine()){
                    favPath=scannerFav.nextLine();
                    if(!favPath.equals(songDir)){
                        allFav.add(favPath);
                    }
                }
                BufferedWriter fav = null;//open append mode
                try {
                    fav = new BufferedWriter(new FileWriter(user+"favorite"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                for (String s:allFav) {
                    if(System.getProperty("os.name").contains("Windows")) {
                        try {
                            fav.write( s+ "\r\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else {
                        try {
                            fav.write(s + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    fav.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        menu.add(item);
        menu.add(item2);
        menu.add(item3);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(SwingUtilities.isRightMouseButton(mouseEvent))
                    menu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if(SwingUtilities.isLeftMouseButton(mouseEvent)){
                    newSong.set(songDir);
                    try {
                        playAddedSong.playAddedSong(true);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    } catch (UnsupportedTagException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InvalidDataException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
                setCursor(cursor);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(200 + this.description.getWidth() , 200 + this.description.getHeight()));
        this.setBorder(BorderFactory.createSoftBevelBorder(0 , Color.GRAY , Color.LIGHT_GRAY));

    }

    public void setPlayAddedSong(PlayAddedSong playAddedSong) {
        this.playAddedSong = playAddedSong;
    }
}
