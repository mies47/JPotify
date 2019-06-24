import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author naha
 *Completed Form of GUI
 * compare all JPanel
 */
public class GUI extends JFrame {
    private ToolBar toolBar;
    private BtmofGUI btmofGUI;
    private MiddleGUI mGUI;
    public ToolBar getToolBar() {
        return toolBar;
    }
    public BtmofGUI getBtmofGUI() {
        return btmofGUI;
    }
    public GUI(String user , String pass, String dir) throws IOException, InterruptedException, JavaLayerException, InvalidDataException, UnsupportedTagException {
        JFrame j=new JFrame("Jpotify");
        j.setLayout(new BorderLayout());
        BtmofGUI btmofGUI =new BtmofGUI(j , null , user);
        j.add(btmofGUI,BorderLayout.PAGE_END);
        ArrayList<SongPlaylist> temp = new ArrayList<>();
        toolBar = new ToolBar(user , dir , pass , j);
        String songDir;
        File f=new File(user+"songs");
        Scanner scannerSong = new Scanner(f);
        while(scannerSong.hasNextLine()){
            songDir=scannerSong.nextLine();
            temp.add(new SongPlaylist(songDir,user));
        }
        for (int i = 0; i < temp.size(); i++) {
            temp.get(i).setNewSong(btmofGUI);
        }
        LeftOfGUI lGUI = new LeftOfGUI(j,user,btmofGUI);
        mGUI = new MiddleGUI(temp , toolBar);
        for(SongPlaylist sp : mGUI.songPlaylists){
            sp.setPlayAddedSong(btmofGUI.PS);
        }
        lGUI.getLibGUI().setSongPlaylist(mGUI);
        j.add(lGUI,BorderLayout.WEST);


        j.add(mGUI , BorderLayout.CENTER);

        ArrayList<OtherUsersSongs> list = new ArrayList<>();
        File file = new File("member.txt");
        Scanner scanner3 = null;
        try {
            scanner3 = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner3.hasNextLine()) {
            String line = scanner3.nextLine();
            if(line.equals(user))
                continue;
            File fileImg = new File(line);
            Scanner scanner2 = new Scanner(fileImg);
            String imgDir="";
            int trash=scanner2.nextInt();
            while(scanner2.hasNextLine()) {
                imgDir = scanner2.nextLine();
            }
            if(imgDir.equals("")){
                list.add(new OtherUsersSongs(line , "0" , "fuuuck" , "Amir" , ImageIO.read(getClass().getResource("DefaultPhotoPic.jpg"))));
            }else{
                list.add(new OtherUsersSongs(line , "0" , "fuuuck" , "Amir" , ImageIO.read(new File(imgDir))));
            }
        }
        j.add(new FriendsActivity(list) , BorderLayout.EAST);
        j.setMinimumSize(new Dimension(900, 900));
        j.setLocationRelativeTo(null);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);
        j.setVisible(true);
    }

    public MiddleGUI getmGUI() {
        return mGUI;
    }

}
