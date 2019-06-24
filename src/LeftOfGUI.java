import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * indicates left of gui
 */
public class LeftOfGUI extends JPanel {
    JLabel l;

    public Homepanel getHomepanel() {
        return homepanel;
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
                    try {
                        songs.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("music-player.png")).getScaledInstance(
                                300 , 300 , Image.SCALE_SMOOTH)));
                        playList.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("playlist.png")).getScaledInstance(
                                300 , 300 , Image.SCALE_SMOOTH)));
                        albums.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("music-album.png")).getScaledInstance(
                                300 , 300 , Image.SCALE_SMOOTH)));
                        favorites.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("favourites.png")).getScaledInstance(
                                300 , 300 , Image.SCALE_SMOOTH)));
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
