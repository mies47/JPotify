import javazoom.jl.decoder.JavaLayerException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
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

    public ToolBar getToolBar() {
        return toolBar;
    }

    public GUI(String user ,String pass,String dir) throws IOException, InterruptedException, JavaLayerException {
        JFrame j=new JFrame("Jpotify");

        j.setLayout(new BorderLayout());
        j.add(new BtmofGUI(j , new File("/home/naha/Downloads/Alexiane - A Million on My Soul.mp3")),BorderLayout.PAGE_END);
        ArrayList<SongPlaylist> temp = new ArrayList<>();
        toolBar = new ToolBar(user , dir , pass , j);
        String songDir;
        Scanner scannerSong = new Scanner(user+"songs");
        while(scannerSong.hasNextLine())
            songDir=scannerSong.nextLine();
        te
        LeftOfGUI lGUI = new LeftOfGUI(j,user);
        MiddleGUI mGUI = new MiddleGUI(temp , toolBar);
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
}
