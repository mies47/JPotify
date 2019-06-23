import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * @author naha
 * make new frame to add Play list on Jlist in leftofGUI
 */
public class PlayListFrame extends JFrame{
    public void setNewName(ChangeName newName) {
        this.newName = newName;
    }

    String nameOfPlayList;
    ChangeName newName;
    public PlayListFrame() {
        this.setTitle("PlayList");
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        JTextField namePlayList = new JTextField();
        JLabel label = new JLabel();
        btn1.setBackground(Color.BLACK);
        btn2.setBackground(Color.BLACK);
        btn1.setForeground(Color.WHITE);
        btn1.setText("Apply");
        btn2.setForeground(Color.WHITE);
        btn2.setText("Cancel");
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setText("Enter Your Name of PlayList: ");
        label.setFont(new Font("", Font.BOLD, 20));
        namePlayList.setBackground(Color.WHITE);

        this.setLayout(new BorderLayout());
        this.add(label, BorderLayout.PAGE_START);
        this.add(namePlayList, BorderLayout.CENTER);
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(btn1, BorderLayout.WEST);
        p.add(btn2, BorderLayout.EAST);
        p.setBackground(Color.BLACK);
        p.setForeground(Color.WHITE);
        p.setVisible(true);
        this.add(p, BorderLayout.PAGE_END);
        this.setBackground(Color.BLACK);
        namePlayList.setText("PlayList");
        namePlayList.setForeground(Color.LIGHT_GRAY);
        namePlayList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                nameOfPlayList = namePlayList.getText();
                newName.change(nameOfPlayList);
                dispose();
            }
        });
                namePlayList.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent focusEvent) {
                        namePlayList.setText("");
                        namePlayList.setBorder(BorderFactory.createSoftBevelBorder(SoftBevelBorder.LOWERED, Color.CYAN, Color.CYAN));
                        namePlayList.setForeground(Color.BLACK);
                    }

                    @Override
                    public void focusLost(FocusEvent focusEvent) {
                        if (btn1.getModel().isPressed()) {
                            nameOfPlayList = namePlayList.getText();
                            newName.change(nameOfPlayList);
                        }
                    }
                });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        setSize(400, 200);
        setMinimumSize(new Dimension(400, 200));
        setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
