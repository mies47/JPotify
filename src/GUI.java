import javax.swing.*;
import java.awt.*;

public class GUI  {

    public static void main(String args[]){
        JFrame jFrame=new JFrame("Jpotify");
        jFrame.setLayout(new BorderLayout());
        jFrame.add(new Homepanel(),BorderLayout.PAGE_END);
        jFrame.setSize(200,200);
        jFrame.setVisible(true);

    }
}
