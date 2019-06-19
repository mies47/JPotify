import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * profile panel for user to change the preferences
 * @author milad
 */
public class ProfilePanel extends JFrame {
    Image profileImg;// profile image
    JButton signOutBTN;// button for signing out
    private JButton password;// button for changing password
    private JPanel userName;//panel includes user , userChange
    private JPanel passwordPanel;// panel includes jpwd , password
    private JLabel status;// label to show if the password entered is right or wrong
    public JTextField user;//textfield to enter preferred username
    private JButton userChange;//button to change username
    private JFileChooser fileChooser;//filechooser for choosing th profile pic
    private JButton chooser;//button to invoke filechooser
    public JPasswordField jpwd;// field for entering the password
    String finalPass;// the final password after changing
    String finalUsername;// the final username after changing
    int keyPressed = 0;//how many times "password" was pressed for changing password
    public ProfilePanel(String username , Image img , String pass){
        super("Profile");
        finalPass = pass;
        finalUsername = username;
        this.profileImg = img;
        this.password = new JButton("  Change your password  ");
        password.setBorder(null);
        password.setBackground(Color.BLACK);
        password.setForeground(Color.WHITE);
        userName = new JPanel();
        userName.setBorder(null);
        user = new JTextField(username);
        user.setBorder(null);
        user.setFocusable(true);
        user.setForeground(Color.LIGHT_GRAY);
        jpwd = new JPasswordField("Enter your current password");
        jpwd.setBorder(null);
        jpwd.setEchoChar((char) 0);//for showing the real characters instead of '*'
        jpwd.setForeground(Color.LIGHT_GRAY);
        userChange = new JButton("  Change your username  ");
        userChange.setBorder(null);
        userChange.setForeground(Color.WHITE);
        userChange.setBackground(Color.BLACK);
        user.setEditable(true);
        signOutBTN = new JButton("Sign Out");
        Image img2 = null;
        try {
            img2 = ImageIO.read(ProfilePanel.class.getResource("exit.png"));//signout image
        } catch (IOException e) {
            e.printStackTrace();
        }
        img2 = img2.getScaledInstance(30 , 30 , Image.SCALE_SMOOTH);
        signOutBTN.setIcon(new ImageIcon(img2));
        signOutBTN.setBorder(null);
        signOutBTN.setBackground(Color.BLACK);
        signOutBTN.setForeground(Color.WHITE);
        status = new JLabel(" ");
        status.setBorder(null);
        status.setBackground(Color.BLACK);
        status.setOpaque(true);
        status.setForeground(Color.WHITE);
        chooser = new JButton("Choose your Pic");
        chooser.setBorder(null);
        passwordPanel = new JPanel();
        passwordPanel.setLayout(new BoxLayout(passwordPanel , BoxLayout.X_AXIS));
        passwordPanel.add(jpwd);
        passwordPanel.add(password);
        passwordPanel.setBackground(Color.BLACK);
        passwordPanel.setOpaque(true);
        JPanel tempforstatus = new JPanel();//panel for ordering the status label and signout button
        tempforstatus.setLayout(new BorderLayout());
        tempforstatus.add(status , BorderLayout.PAGE_START);
        userName.setLayout(new BorderLayout());
        userName.add(user , BorderLayout.CENTER);
        userName.add(userChange , BorderLayout.EAST);
        Box picPanel = Box.createHorizontalBox();
        Box userPart = Box.createHorizontalBox();
        userPart.setBorder(BorderFactory.createMatteBorder(0 ,0 ,1 ,0 ,Color.LIGHT_GRAY));
        Box passwordBox = Box.createHorizontalBox();
        passwordBox.setBorder(BorderFactory.createMatteBorder(0 ,0 ,1 ,0 ,Color.LIGHT_GRAY));
        Box signoutBox = Box.createHorizontalBox();
        tempforstatus.add(signOutBTN , BorderLayout.CENTER);
        signoutBox.add(tempforstatus);
        signoutBox.add(Box.createHorizontalGlue());
        passwordBox.add(passwordPanel);
        passwordBox.add(Box.createHorizontalGlue());
        userPart.add(userName);
        userPart.add(Box.createHorizontalGlue());
        chooser.setBorder(null);
        picPanel.setOpaque(true);
        picPanel.setBackground(Color.BLACK);
        JLabel jpic = new JLabel();//container of the profile pic
        jpic.setIcon(new ImageIcon(img));
        jpic.setBorder(new EmptyBorder(0 , 0 , 0 ,120));
        picPanel.add(jpic);
        picPanel.add(chooser);
        chooser.setBackground(Color.BLACK);
        chooser.setForeground(Color.WHITE);
        picPanel.add(Box.createHorizontalGlue());
        picPanel.setBorder(BorderFactory.createMatteBorder(0 ,0 ,1 ,0 ,Color.LIGHT_GRAY));
        JPanel temp = new JPanel();
        temp.add(picPanel);
        temp.add(userPart);
        temp.add(passwordBox);
        temp.setLayout(new BoxLayout(temp , BoxLayout.Y_AXIS));
        this.setLayout(new BorderLayout());
        this.add(temp , BorderLayout.CENTER);
        this.add(signoutBox , BorderLayout.PAGE_END);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(350 , 300);
        chooser.addActionListener(new ActionListener() {//invokes when choose profile pic is clicked
            @Override
            public void actionPerformed(ActionEvent e) {//creating jfilecooser
                fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("png", "png"));
                int i=fileChooser.showOpenDialog(ProfilePanel.this);
                if(i==JFileChooser.APPROVE_OPTION){
                    File f=fileChooser.getSelectedFile();
                    String filepath=f.getPath();
                    Image img = null;
                    try {
                        img = ImageIO.read(f);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    img = img.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
                    jpic.setIcon(new ImageIcon(img));//set the jpic label to chosen file
                    try{
                        BufferedReader br=new BufferedReader(new FileReader(filepath));
                        String s1="",s2="";
                        while((s1=br.readLine())!=null){
                            s2+=s1+"\n";
                        }
                        br.close();
                    }catch (Exception ex) {ex.printStackTrace();  }
                }
            }
        });
        user.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                user.setText("");
                user.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED, Color.RED , Color.RED));
                user.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(userChange.getModel().isPressed()){//if the change user btn was pressed
                    finalUsername = user.getText();
                }
                user.setText(finalUsername);
                user.setBorder(null);
                user.setForeground(Color.LIGHT_GRAY);
            }
        });
        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalUsername = user.getText();
            }
        });

        jpwd.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                jpwd.setText("");
                jpwd.setEchoChar('*');
                jpwd.setForeground(Color.BLACK);
                jpwd.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED, Color.RED , Color.RED));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(password.getModel().isPressed()){
                    keyPressed++;
                    if(keyPressed%2 == 1){
                        if(jpwd.getText().equals(finalPass)) {

                            jpwd.setText("");
                            status.setText("Enter your new password");
                        }else{
                            status.setText("Wrong password , try again");
                            keyPressed--;
                        }
                    }else{
                        finalPass =jpwd.getText();
                        jpwd.setText("");
                        status.setText("Your password has successfully changed!");
                    }
                }
                    jpwd.setEchoChar((char) 0);
                    jpwd.setText("Enter your current password");
                    jpwd.setForeground(Color.LIGHT_GRAY);
                    jpwd.setBorder(null);
            }
        });
        jpwd.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    keyPressed++;
                    if(keyPressed%2 == 1){
                        if(jpwd.getText().equals(finalPass)) {
                            jpwd.setText("");
                            status.setText("Enter your new password");
                        }else{
                            status.setText("Wrong password , try again");
                            keyPressed--;
                        }
                    }else{
                        finalPass =jpwd.getText();
                        jpwd.setText("");
                        status.setText("Your password has successfully changed!");
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    keyPressed++;
                    if(keyPressed%2 == 1){
                        if(jpwd.getText().equals(finalPass)) {
                            jpwd.setText("");
                            status.setText("Enter your new password");

                        }else{
                            status.setText("Wrong password , try again");
                            keyPressed--;
                        }
                    }else{
                        finalPass =jpwd.getText();
                        jpwd.setText("");
                        status.setText("Your password has successfully changed!");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        signOutBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//close this panel if sign out button was pressed
                ProfilePanel.this.dispose();
            }
        });
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
}