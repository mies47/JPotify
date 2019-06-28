import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * indicate playlist
 */
public class PlayList extends JPanel {
    Scroller scroller;

    /**
     * @throws IOException if not Find icon throw exception
     */
    public PlayList(JFrame frame, String user) throws IOException {
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
        }
        scroller = new Scroller(frame);
        for (String s : ldapContent.keySet()) {
            scroller.change(s);
        }
        JPanel p1 = new JPanel();
        JLabel l = new JLabel("PlayList");
        l.setBackground(Color.BLACK);
        l.setFont(new Font("", Font.PLAIN, 20));
        l.setOpaque(true);//show background color
        l.setForeground(Color.WHITE);
        Box box = Box.createHorizontalBox();//set position to left of panel
        box.add(l);
        box.add(Box.createHorizontalGlue());
        JButton btn = new JButton("ADD PlayList");
        btn.setBorder(null);
        btn.setFont(new Font("", Font.PLAIN, 17));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        Image img = ImageIO.read(getClass().getResource("download.png"));//get icon
        Image img2 = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);//changing the scale of icon
        btn.setIcon(new ImageIcon(img2));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PlayListFrame p = new PlayListFrame(user);
                p.setNewName(scroller);

            }
        });
        Box box2 = Box.createHorizontalBox();//set position to left of panel
        box2.add(btn);
        box2.add(Box.createHorizontalGlue());
        box.setBorder(new EmptyBorder(0, 0, 10, 0));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(box);
        this.add(scroller);//get list of playlist
        this.add(box2);
        setBackground(Color.BLACK);
        setVisible(true);
    }
}
