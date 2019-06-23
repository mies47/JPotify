import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * a jpanel that includes the song thumbnail and its description
 * @author Milad
 */
public class SongPlaylist extends JPanel {
    private JLabel pic;
    private JLabel description;

    public void setNewSong(SetSong newSong) {
        this.newSong = newSong;
    }

    private SetSong newSong;
    private String songDir;
    /**
     *
     * @param s set directory of song
     * @throws IOException if the directory given does not exist
     */
    public SongPlaylist(String s) throws IOException, InvalidDataException, UnsupportedTagException {
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
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                newSong.set(songDir);
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
}
