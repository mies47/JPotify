import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
//import sun.tools.jps.Jps;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Graphical part of Search
 * Using a JTextArea as searchField
 * and a Button to hold the icon
 * @author milad
 */
public class SearchArea extends JPanel {
    JTextField searchArea;
    private final String DEFAULTTEXT = "   Search ...                    ";
    private Icon searchIcon;
    private JButton btn;

    /**
     * setting the default text of search field
     * setting the icon as preferred size
     */
    public SearchArea(String name,JFrame frame){
        searchArea = new JTextField(DEFAULTTEXT);
        searchArea.setForeground(Color.LIGHT_GRAY);
        searchArea.setFocusable(true);
        searchArea.setEditable(true);
        searchArea.setMinimumSize(new Dimension(500 , 100));
        btn = new JButton();
        btn.setBackground(Color.WHITE);
        btn.setBorder(null);
        try {
            Image seeech = ImageIO.read(getClass().getResource("search.png"));
            Image search2 = seeech.getScaledInstance(20 , 20 , Image.SCALE_SMOOTH);
            searchIcon = new ImageIcon(search2);
            btn.setIcon(searchIcon);
        } catch (IOException e) {
            System.out.println(e);
        }
        this.setLayout(new BorderLayout());
        this.add(btn , BorderLayout.WEST);
        this.add(searchArea , BorderLayout.CENTER);
        searchArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchtxt = searchArea.getText();
                //search in songs
            }
        });
        searchArea.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                searchArea.setText("");
                searchArea.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED , Color.CYAN , Color.CYAN));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(btn.getModel().isPressed()){
                    String searchtxt = searchArea.getText();
                    File f=new File(name+"songs");
                    ArrayList<String> a=new ArrayList<>();
                    Scanner scanner = null;
                    try {
                        scanner = new Scanner(f);
                    } catch (FileNotFoundException e3) {
                        e3.printStackTrace();
                    }
                    while (scanner.hasNextLine()) {
                        String s=scanner.nextLine();
                        a.add(s);
                    }
                    HashMap<Mp3File,String>artistSearch=new HashMap<>();
                    HashMap<Mp3File,String>songsSearch=new HashMap<>();
                    for (String m:a) {
                        Mp3File temp= null;
                        try {
                            temp = new Mp3File(m);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (UnsupportedTagException ex) {
                            ex.printStackTrace();
                        } catch (InvalidDataException ex) {
                            ex.printStackTrace();
                        }
                        if(temp.hasId3v1Tag()){
                            if((temp.getId3v1Tag().getArtist()).toLowerCase().contains(searchtxt.toLowerCase())){
                                artistSearch.put(temp,m);
                            }
                        }else{
                            if((temp.getId3v1Tag().getArtist()).toLowerCase().contains(searchtxt.toLowerCase())){
                                artistSearch.put(temp,m);
                            }
                        }
                    }
                    for (String m:a) {
                        Mp3File temp= null;
                        try {
                            temp = new Mp3File(m);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (UnsupportedTagException ex) {
                            ex.printStackTrace();
                        } catch (InvalidDataException ex) {
                            ex.printStackTrace();
                        }
                        if(temp.hasId3v1Tag()){
                            if((temp.getId3v1Tag().getTitle()).toLowerCase().contains(searchtxt.toLowerCase())){
                                songsSearch.put(temp,m);
                            }
                        }else{
                            if((temp.getId3v1Tag().getTitle()).toLowerCase().contains(searchtxt.toLowerCase())){
                                songsSearch.put(temp,m);
                            }
                        }
                    }Component c = frame.getRootPane().getContentPane().getComponent(2);
                    if (c instanceof MiddleGUI) {
                        JLabel l=new JLabel("Search in Name Songs:");
                        l.setBackground(Color.BLACK);
                        l.setForeground(Color.WHITE);
                        l.setOpaque(true);
                        JLabel l2=new JLabel("Search in Name Artists:");
                        l2.setBackground(Color.BLACK);
                        l2.setForeground(Color.WHITE);
                        l2.setOpaque(true);
                        JPanel all=new JPanel();
                        all.setLayout(new BoxLayout(all,BoxLayout.Y_AXIS));
                        all.setBackground(Color.BLACK);
                        all.setForeground(Color.WHITE);
                        all.setVisible(true);
                        JPanel p=new JPanel();
                        p.setLayout(new WrapLayout(WrapLayout.LEFT));
                        p.setBackground(Color.BLACK);
                        p.setForeground(Color.WHITE);
                        p.setVisible(true);
                        JPanel pv2=new JPanel();
                        pv2.setLayout(new BorderLayout());
                        pv2.setBackground(Color.BLACK);
                        pv2.setForeground(Color.WHITE);
                        pv2.setVisible(true);
                        JPanel p2=new JPanel();
                        p2.setLayout(new WrapLayout(WrapLayout.LEFT));
                        p2.setBackground(Color.BLACK);
                        p2.setForeground(Color.WHITE);
                        p2.setVisible(true);
                        JPanel p2v2=new JPanel();
                        p2v2.setLayout(new BorderLayout());
                        p2v2.setBackground(Color.BLACK);
                        p2v2.setForeground(Color.WHITE);
                        p2v2.setVisible(true);
                        ((MiddleGUI) c).jPanel.removeAll();
                        ((MiddleGUI) c).jPanel.setLayout(new BorderLayout());
                        ArrayList<SongPlaylist> list = new ArrayList<>();
                        ArrayList<SongPlaylist> list2 = new ArrayList<>();
                        for (String value:artistSearch.values()){
                            try {
                                list.add(new SongPlaylist(value,name));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (InvalidDataException ex) {
                                ex.printStackTrace();
                            } catch (UnsupportedTagException ex) {
                                ex.printStackTrace();
                            }
                        }
                        for (String value:songsSearch.values()){
                            try {
                                list2.add(new SongPlaylist(value,name));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            } catch (InvalidDataException ex) {
                                ex.printStackTrace();
                            } catch (UnsupportedTagException ex) {
                                ex.printStackTrace();
                            }
                        }
                        Component c1 = frame.getRootPane().getContentPane().getComponent(0);
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setNewSong(((BtmofGUI)c1));
                            list.get(i).setPlayAddedSong(((BtmofGUI)c1).PS);

                        }
                        for (int i = 0; i < list2.size(); i++) {
                            list2.get(i).setNewSong(((BtmofGUI)c1));
                            list2.get(i).setPlayAddedSong(((BtmofGUI)c1).PS);

                        }
                        for (int i = 0; i < list.size(); i++) {
                            p.add(list.get(i));
                        }
                        for (int i = 0; i < list2.size(); i++) {
                            p2.add(list2.get(i));
                        }
                        pv2.add(l,BorderLayout.PAGE_START);
                        pv2.add(p2,BorderLayout.CENTER);
                        p2v2.add(l2,BorderLayout.PAGE_START);
                        p2v2.add(p,BorderLayout.CENTER);
                        all.add(pv2);
                        all.add(p2v2);
                        ((MiddleGUI) c).jPanel.add(all);
                        //((MiddleGUI) c).jPanel.add(p2,BorderLayout.CENTER);
                        ((MiddleGUI) c).jPanel.setBackground(Color.BLACK);
                        ((MiddleGUI) c).jPanel.setVisible(true);

                    }
                }
                frame.validate();
                frame.invalidate();
                frame.repaint();
                searchArea.setText(DEFAULTTEXT);
                searchArea.setBorder(null);
            }
        });
    }

}

