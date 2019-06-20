import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Login extends JFrame {
    public Login() throws IOException, InterruptedException {
        super("JPotify");
        JLabel l=new JLabel();
        Image img = ImageIO.read(getClass().getResource("music.png"));
        Image img2=img.getScaledInstance(600,400,Image.SCALE_SMOOTH);
        l.setIcon(new ImageIcon(img2));
        this.setLayout(new BorderLayout());
        this.add(l,BorderLayout.CENTER);
        setSize(600, 400);
        setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(null);
        this.setVisible(true);
        TimeUnit.SECONDS.sleep(3);
        this.dispose();
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        Login l=new Login();
        Login2 l2=new Login2();
    }
}
