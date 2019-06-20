import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Login2 extends JFrame {
    public Login2() throws IOException {
        super("Login");
        JLabel l=new JLabel("...Welcome to JPotify...");
        l.setBackground(Color.DARK_GRAY);
        l.setForeground(Color.WHITE);
        l.setOpaque(true);
        l.setFont(new Font("",Font.BOLD,30));
        JLabel l1=new JLabel("Username:           ");
        l1.setBackground(Color.DARK_GRAY);
        l1.setForeground(Color.WHITE);
        l1.setOpaque(true);
        JLabel l2=new JLabel("Password:            ");
        l2.setBackground(Color.DARK_GRAY);
        l2.setForeground(Color.WHITE);
        l2.setOpaque(true);
        JPasswordField JP=new JPasswordField();
        JP.setBackground(Color.WHITE);
        JP.setForeground(Color.BLACK);
        JP.setOpaque(true);
        JP.setVisible(true);
        JTextField JU=new JTextField();
        JU.setBackground(Color.WHITE);
        JU.setForeground(Color.BLACK);
        JU.setOpaque(true);
        JU.setVisible(true);
        JButton btn1=new JButton(" Login ");
        btn1.setBackground(Color.DARK_GRAY);
        btn1.setForeground(Color.WHITE);
        btn1.setBorder(null);
        btn1.setBorder(new EmptyBorder(0,0,0,50));
        Image img = ImageIO.read(getClass().getResource("a1.png"));
        Image img2=img.getScaledInstance(50,50,Image.SCALE_SMOOTH);//changing the scale of icon
        btn1.setIcon(new ImageIcon(img2));
        btn1.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    GUI all =new GUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        JButton btn2=new JButton("Sign up");
        btn2.setBackground(Color.DARK_GRAY);
        btn2.setForeground(Color.WHITE);
        btn2.setBorder(null);
        Image img3 = ImageIO.read(getClass().getResource("a2.png"));
        Image img4=img3.getScaledInstance(50,50,Image.SCALE_SMOOTH);//changing the scale of icon
        btn2.setIcon(new ImageIcon(img4));
        Box box0= Box.createHorizontalBox();//set position to left of panel
        box0.add(l1);
        box0.add(JU);
        box0.add(Box.createHorizontalGlue());
        Box box= Box.createHorizontalBox();//set position to left of panel
        box.add(l2);
        box.add(JP);
        box.add(Box.createHorizontalGlue());
        JPanel p2=new JPanel();
        p2.setLayout(new BoxLayout(p2,BoxLayout.Y_AXIS));
        p2.add(box0);
        p2.add(box);
        p2.setBackground(Color.DARK_GRAY);
        p2.setVisible(true);
        JPanel p3=new JPanel();
        p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));
        p3.add(btn1);
        p3.add(btn2);
        p3.setBackground(Color.DARK_GRAY);
        p3.setBorder(new EmptyBorder(100,0,50,0));
        p3.setVisible(true);
        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
        p.add(p2);
        p.add(p3);
        p.setBackground(Color.DARK_GRAY);
        p.setVisible(true);
        l.setBorder(new EmptyBorder(0,100,30,0));
        this.setLayout(new BorderLayout());
        this.add(l,BorderLayout.PAGE_START);
        this.add(p,BorderLayout.CENTER);
        setSize(600, 400);
        setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(null);
        //this.setBackground(Color.DARK_GRAY);
        this.setVisible(true);
    }
}