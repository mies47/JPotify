import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayListFinal extends JPanel {
    private JLabel pic;
    private JLabel description;
    public void setRemoveDLM(RemoveDLM removeDLM) {
        this.removeDLM = removeDLM;
    }

//    public void setPlayListChange(PlayListChange playListChange) {
//        this.playListChange = playListChange;
//    }
//
//    PlayListChange playListChange;
    RemoveDLM removeDLM;

    public PlayListFinal(ArrayList<String> a, String user, JFrame frame, String name) throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3File = null;
        if (a.size() != 0) {
            mp3File = new Mp3File(a.get(0), true);
        }
        BufferedImage image = null;
        if (mp3File != null)
            if (mp3File.getId3v2Tag() != null) {
                if (mp3File.getId3v2Tag().getAlbumImage() != null) {
                    image = ImageIO.read(new ByteArrayInputStream(mp3File.getId3v2Tag().getAlbumImage()));
                }
            }
        Image img1 = image;
        if (img1 == null) {
            img1 = ImageIO.read(getClass().getResource("musicLogo.jpg"));
        }
        img1 = img1.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        pic = new JLabel();
        pic.setIcon(new ImageIcon(img1));
        JPopupMenu menu = new JPopupMenu("More");
        menu.setBackground(Color.BLACK);
        menu.setForeground(Color.white);
        JMenuItem item = new JMenuItem("Remove this PlayList");
        menu.add(item);
        item.setBackground(Color.BLACK);
        item.setForeground(Color.white);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!name.equals("SharedPlayList")) {
                    HashMap<String, ArrayList<String>> ldapContent = new HashMap<>();
                    File toRead = new File(user + "PLay");
                    if (toRead.exists()) {
//            FileOutputStream fos = new FileOutputStream(toRead);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
                        FileInputStream fis = null;
                        try {
                            fis = new FileInputStream(toRead);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        ObjectInputStream ois = null;
                        try {
                            ois = new ObjectInputStream(fis);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            ldapContent = (HashMap<String, ArrayList<String>>) ois.readObject();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        try {
                            ois.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        ldapContent.remove(name);
                        File fileOne = new File(user + "PLay");
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(fileOne);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        ObjectOutputStream oos = null;
                        try {
                            oos = new ObjectOutputStream(fos);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            System.out.println(ldapContent);
                            oos.writeObject(ldapContent);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            oos.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            oos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        removeDLM.remove(name);
                        setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null,"You can't remove SharedPlayList","Wrong",JOptionPane.INFORMATION_MESSAGE);
                    }
                    JOptionPane.showMessageDialog(null,"You can't remove SharedPlayList","Wrong",JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
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
                    Component c = frame.getRootPane().getContentPane().getComponent(2);
                    Component c1 = frame.getRootPane().getContentPane().getComponent(0);
                    ((BtmofGUI) c1).PS.isClickedPlay=true;
                    ((BtmofGUI) c1).PS.name=name;
                    if (c instanceof MiddleGUI) {
                        ((MiddleGUI) c).jPanel.removeAll();
                        ((MiddleGUI) c).jPanel.setLayout(new WrapLayout(FlowLayout.LEFT));
                        ArrayList<SongPlaylist> list = new ArrayList<>();
                        String songDir;
                        for (String s : a) {
                            try {
                                list.add(new SongPlaylist(s, user, frame));
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
                            list.get(i).setNewSong((BtmofGUI) c1);
                            list.get(i).setPlayAddedSong(((BtmofGUI) c1).PS);
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
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        this.description = new JLabel("<html>" + "Name: " + name + "<br>" + "</html>");//to show the text in multiple lines
        this.description.setForeground(Color.LIGHT_GRAY);
        this.description.setBackground(Color.BLACK);
        this.description.setOpaque(true);
        this.description.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY));
        this.setLayout(new BorderLayout());
        this.add(pic, BorderLayout.CENTER);
        this.add(this.description, BorderLayout.PAGE_END);
        this.setPreferredSize(new Dimension(200 + this.description.getWidth(), 200 + this.description.getHeight()));
        this.setBorder(BorderFactory.createSoftBevelBorder(0, Color.GRAY, Color.LIGHT_GRAY));
    }
}
