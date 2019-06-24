import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author naha
 * compare and complete bottom of gui
 * compare three JPanel
 */
public class BtmofGUI extends JPanel implements SetSong {
    NameLabel nL;
    PlayStop PS;
    File fileName;
    /**
     * @throws IOException if not find icon throws exception
     */
    public BtmofGUI(JFrame jFrame,File file , String user) throws IOException, InterruptedException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        fileName=file;
        nL=new NameLabel(" "," ");
        PS=new PlayStop(jFrame , fileName , user);
        Volume v=new Volume(fileName);
        this.setLayout(new BorderLayout());
        this.add(nL,BorderLayout.WEST);
        this.add(PS,BorderLayout.CENTER);
        this.add(v,BorderLayout.EAST);
        PS.setBorder(new EmptyBorder(0,100,20,0));
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }

    public void setFile(File file) {
        this.fileName = file;
    }

    @Override
    public void set(String s) {
        fileName = new File(s);
        PS.setFile(fileName);
    }
}
