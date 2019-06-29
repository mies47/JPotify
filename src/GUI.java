import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
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
        j.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
            temp.add(new SongPlaylist(songDir,user,this));
        }
        for (int i = 0; i < temp.size(); i++) {
            temp.get(i).setNewSong(btmofGUI);
        }
        LeftOfGUI lGUI = new LeftOfGUI(j,user,btmofGUI);
        lGUI.getLibGUI().setFavoriteOrSong(btmofGUI.PS);

        lGUI.getLibGUI().setRecentOrSong(btmofGUI.PS);
        lGUI.setRecentOrSong(btmofGUI.PS);
        lGUI.setFavoriteOrSong(btmofGUI.PS);
        mGUI = new MiddleGUI(temp , toolBar);
        for(SongPlaylist sp : mGUI.songPlaylists){
            sp.setPlayAddedSong(btmofGUI.PS);
        }
        lGUI.getLibGUI().setSongPlaylist(mGUI);
        j.add(lGUI,BorderLayout.WEST);


        j.add(mGUI , BorderLayout.CENTER);
//        lGUI.setSetMiddlePanel(mGUI);

        ArrayList<OtherUsersSongs> list = new ArrayList<>();
        File file = new File("member.txt");
        Scanner scanner3 = null;
        try {
            scanner3 = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Boolean a=false;
        while (scanner3.hasNextLine()) {
            String line = scanner3.nextLine();
            for (int i = 0; i < list.size(); i++) {
                if(line.equals(list.get(i).getUser()))
                    a=true;
            }
            if(a==true){
                continue;
            }
            if(line.equals(user))
                continue;
            File fileImg = new File(line);
            Scanner scanner2 = new Scanner(fileImg);
            String imgDir="";
            int trash=scanner2.nextInt();
            //int time=scanner2.nextInt();
            while(scanner2.hasNextLine()) {
                imgDir = scanner2.nextLine();
            }
            String lastSong="";
            if(imgDir.equals("")){
                File fileLastSong = new File(line+"Recentsongs");
                Scanner scannerLast = new Scanner(fileLastSong);
                Mp3File m=null;
                if(scannerLast.hasNextLine()){
                    lastSong=scannerLast.nextLine();
                    m=new Mp3File(new File(lastSong));
                }
                //System.out.println(m);
                if(m!=null && m.hasId3v1Tag()) {
                    list.add(new OtherUsersSongs(line, "0", m.getId3v1Tag().getTitle(), m.getId3v1Tag().getArtist(), ImageIO.read(getClass().getResource("DefaultPhotoPic.jpg")),lastSong,j));
                }else if(m!=null && m.hasId3v2Tag())
                    list.add(new OtherUsersSongs(line , "0" , m.getId3v2Tag().getTitle() , m.getId3v2Tag().getArtist() , ImageIO.read(getClass().getResource("DefaultPhotoPic.jpg")),lastSong,j));
                else
                    list.add(new OtherUsersSongs(line , "0" , "                                                 " ,"                                                        " , ImageIO.read(getClass().getResource("DefaultPhotoPic.jpg")),"",j));
            }else{
                File fileLastSong = new File(line+"Recentsongs");
                Scanner scannerLast = new Scanner(fileLastSong);
                Mp3File m=null;
                if(scannerLast.hasNextLine()){
                    lastSong=scannerLast.nextLine();
                    m=new Mp3File(new File(lastSong));
                }

                if(m!=null && m.hasId3v1Tag()) {
                    System.out.println(m.getId3v1Tag().getTitle());
                    list.add(new OtherUsersSongs(line, "0", m.getId3v1Tag().getTitle(), m.getId3v1Tag().getArtist(), ImageIO.read(new File(imgDir)),lastSong,j));
                }else if(m!=null && m.hasId3v2Tag())
                    list.add(new OtherUsersSongs(line , "0" , m.getId3v2Tag().getTitle() , m.getId3v2Tag().getArtist() , ImageIO.read(new File(imgDir)),lastSong,j));
                else
                    list.add(new OtherUsersSongs(line , "0" ,"                                                  ","                                                     " , ImageIO.read(new File(imgDir)),"",j));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setNewSong(btmofGUI);
            list.get(i).setPlayAddedSong(btmofGUI.PS);
        }
        j.add(new FriendsActivity(list) , BorderLayout.EAST);
        j.setMinimumSize(new Dimension(1100, 900));
        j.setLocationRelativeTo(null);
        j.setExtendedState(JFrame.MAXIMIZED_BOTH);
        j.setVisible(true);
    }

    public MiddleGUI getmGUI() {
        return mGUI;
    }

}
