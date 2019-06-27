import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Album extends JPanel{
    private JLabel pic;
    private JLabel description;

//    public void add(String s){
//        a.add(s);
//    }
    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    private String albumName;
    public Album(ArrayList<String> a,String user, JFrame frame) throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3File = new Mp3File(a.get(0),true);
        String Artist = "Unknown";
        String album = "Unknown";
        String date = "No date";
        BufferedImage image = null;
        if(mp3File.getId3v2Tag() != null){
            if(mp3File.getId3v2Tag().getAlbumImage()!= null){
                image = ImageIO.read(new ByteArrayInputStream(mp3File.getId3v2Tag().getAlbumImage()));
            }
            if(mp3File.getId3v2Tag().getTrack() != null){
                Artist = mp3File.getId3v2Tag().getArtist();
            }
            if(mp3File.getId3v2Tag().getAlbum() != null){
                album = mp3File.getId3v2Tag().getAlbum();
            }
            if(mp3File.getId3v2Tag().getTrack() != null){
                date = mp3File.getId3v2Tag().getDate();
            }

        }else if(mp3File.getId3v1Tag() != null){
            if(mp3File.getId3v1Tag().getTrack() != null){
                Artist = mp3File.getId3v2Tag().getArtist();
            }
            if(mp3File.getId3v1Tag().getAlbum() != null){
                album = mp3File.getId3v2Tag().getAlbum();
            }
            if(mp3File.getId3v1Tag().getTrack() != null){
                date = mp3File.getId3v2Tag().getDate();
            }
        }
        albumName=album;
//        songPlaylist.addSong(new SongPlaylist(image , "Song: " + song + "<br>" +"Album: " + album
//                + "<br>" + "Date: " + date));
        Image img1 = image;
        if(img1 == null) {
            img1 = ImageIO.read(getClass().getResource("musicLogo.jpg"));
        }
        img1 = img1.getScaledInstance(200 , 150 , Image.SCALE_SMOOTH);
        pic = new JLabel();
        pic.setIcon(new ImageIcon(img1));
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                Component c = frame.getRootPane().getContentPane().getComponent(2);
                Component c1 = frame.getRootPane().getContentPane().getComponent(0);
                if (c instanceof MiddleGUI) {
                    ((MiddleGUI) c).jPanel.removeAll();
                    ((MiddleGUI) c).jPanel.setLayout(new WrapLayout(FlowLayout.LEFT));
                    ArrayList<SongPlaylist> list = new ArrayList<>();
                    String songDir;
                    for (String s:a) {
                        try {
                            list.add(new SongPlaylist(s,user,frame));
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
                        list.get(i).setNewSong((BtmofGUI)c1);
                        list.get(i).setPlayAddedSong(((BtmofGUI)c1).PS);
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

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        this.description = new JLabel("<html>"+ "Artist: " + Artist + "<br>" +"Album: " + album + "<br>" + "Date: " + date+"</html>");//to show the text in multiple lines
        this.description.setForeground(Color.LIGHT_GRAY);
        this.description.setBackground(Color.BLACK);
        this.description.setOpaque(true);
        this.description.setBorder(BorderFactory.createMatteBorder(1 , 0 , 0 , 0 , Color.GRAY));
        this.setLayout(new BorderLayout());
        this.add(pic , BorderLayout.CENTER);
        this.add(this.description , BorderLayout.PAGE_END);
    }

}
