import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Each part of friend's activity
 *
 * @author Milad
 */
public class OtherUsersSongs extends JPanel {
    private JLabel userName;
    private JLabel song;
    private JLabel artist;
    private Image img;
    private String time;//Last time that user was online if it's "0" user is online
    private JLabel status;//if the user is online or not
    private JLabel picture;//profile pic
    private PlayAddedSong playAddedSong;

    public String getUser() {
        return user;
    }

    public void setNewSong(SetSong newSong) {
        this.newSong = newSong;
    }

    private SetSong newSong;
    private String user;

    /**
     * @param userName user name
     * @param time     Last time that user was online if it's "0" user is online
     * @param song     name of the song
     * @param artist   name of the artist
     * @param img      the user's profile image
     * @throws IOException if the icon image for online user is not found
     */
    public OtherUsersSongs(String userName, String time, String song, String artist, Image img, String songDir, JFrame frame) throws IOException {
        this.user = userName;
        this.userName = new JLabel(user);
        this.userName.setOpaque(true);
        this.userName.setBackground(Color.BLACK);
        this.userName.setForeground(Color.WHITE);
        this.userName.setFont(new Font("hi", Font.BOLD, 10));
        this.song = new JLabel(song);
        this.song.setBackground(Color.BLACK);
        this.song.setForeground(Color.WHITE);
        this.song.setFont(new Font("hi", Font.BOLD, 7));
        this.artist = new JLabel(artist);
        this.artist.setBackground(Color.BLACK);
        this.artist.setForeground(Color.WHITE);
        this.artist.setFont(new Font("hi", Font.BOLD, 7));
        this.time = time;
        this.img = img.getScaledInstance(30, 40, Image.SCALE_SMOOTH);
        picture = new JLabel();
        picture.setIcon(new ImageIcon(this.img));
        status = new JLabel();
        status.setForeground(Color.LIGHT_GRAY);
        status.setFont(new Font("hi", Font.BOLD, 10));
        status.setBorder(new EmptyBorder(0, 40, 0, 0));
        if (time.equals("0")) {//if user was online show speaker icon
            Image image = ImageIO.read(getClass().getResource("speaker.png"));
            image = image.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            status.setIcon(new ImageIcon(image));
        } else {//show the last time user was online
            status.setText(time);
        }
        JPanel statusHolder = new JPanel();
        statusHolder.setLayout(new BorderLayout());
        statusHolder.add(status, BorderLayout.PAGE_START);
        statusHolder.setBackground(Color.BLACK);
        Box labelHolder = Box.createVerticalBox();
        labelHolder.add(Box.createVerticalGlue());
        labelHolder.add(this.userName);
        labelHolder.add(this.song);
        labelHolder.add(this.artist);
        labelHolder.setBorder(new EmptyBorder(0, 10, 0, 0));
        Box labelPic = Box.createHorizontalBox();
        labelPic.add(Box.createHorizontalGlue());
        labelPic.add(picture);
        labelPic.add(labelHolder);
        labelPic.add(status);
        JPopupMenu menu = new JPopupMenu("More");
        menu.setBackground(Color.BLACK);
        menu.setForeground(Color.white);
        JMenuItem item = new JMenuItem("Show SharedPlayList");
        item.setBackground(Color.BLACK);
        item.setForeground(Color.white);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    SharedFrame all=new SharedFrame(userName,songDir,frame);
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                }
            }
        });
       // this.setMaximumSize(new Dimension(200,80));
        menu.add(item);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (SwingUtilities.isRightMouseButton(mouseEvent))
                    menu.show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                if (SwingUtilities.isLeftMouseButton(mouseEvent)) {
                    if (!songDir.equals("") && songDir != null) {
                        Component c = frame.getRootPane().getContentPane().getComponent(0);
                        ((BtmofGUI) c).PS.other = true;
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
        this.setLayout(new BorderLayout());
        this.add(labelPic, BorderLayout.PAGE_START);
        this.setBackground(Color.BLACK);

    }

    public void setPlayAddedSong(PlayAddedSong playAddedSong) {
        this.playAddedSong = playAddedSong;
    }
}
