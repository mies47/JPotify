import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SharedFrame extends JFrame {
    public SharedFrame(String name, String songDir, JFrame frame) throws InvalidDataException, IOException, UnsupportedTagException {
        HashMap<String, ArrayList<String>> ldapContent = new HashMap<>();
        File toRead = new File(name + "PLay");
        if (toRead.exists()) {
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
            ArrayList<String> b = new ArrayList<>();
            b=ldapContent.get("SharedPlayList");
            JLabel label=new JLabel(name+"SharedPlayList");
            label.setBackground(Color.BLACK);
            label.setForeground(Color.WHITE);
            label.setOpaque(true);
            //JPanel jPanel=new JPanel();
            //jPanel.setLayout(new WrapLayout( WrapLayout.LEFT));
            ArrayList<SongSharePlay> list=new ArrayList<>();
            Component c = frame.getRootPane().getContentPane().getComponent(0);
            for (int i = 0 ; i < b.size() ; i++){
                SongSharePlay temp=new SongSharePlay(b.get(i),frame);
                list.add(temp);
                //for (int i = 0; i < list.size(); i++) {
                list.get(i).setNewSong((BtmofGUI) c);
                list.get(i).setPlayAddedSong(((BtmofGUI) c).PS);

                this.add(temp);
            }
            //jPanel.setVisible(true);
            this.setLayout(new WrapLayout(WrapLayout.LEFT));
            //this.add(label,BorderLayout.PAGE_START);
            //this.add(label,BorderLayout.CENTER);
            this.setMinimumSize(new Dimension(500, 500));
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        }
    }
}
