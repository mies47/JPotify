import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Graphical part of Search
 * Using a JTextArea as searchField
 * and a Button to hold the icon
 * @author milad
 */
public class SearchArea extends JPanel {
    JTextArea searchArea;
    private final String DEFAULTTEXT = "   Search ...";
    private Icon searchIcon;
    private JButton btn;

    /**
     * setting the default text of search field
     * setting the icon as preferred size
     */
    public SearchArea(){
        searchArea = new JTextArea(DEFAULTTEXT);
        searchArea.setForeground(Color.LIGHT_GRAY);
        searchArea.setFocusable(true);
        searchArea.setEditable(true);
        searchArea.setLineWrap(true);
        btn = new JButton();
        btn.setBackground(Color.WHITE);
        btn.setBorder(null);
        try {
            Image seeech = ImageIO.read(getClass().getResource("search.png"));
            Image search2 = seeech.getScaledInstance(20 , 20 , Image.SCALE_SMOOTH);
            searchIcon = new ImageIcon(search2);
            btn.setIcon(searchIcon);
        } catch (IOException e) {
            System.out.println(e);
        }
        this.setLayout(new BorderLayout());
        this.add(btn , BorderLayout.WEST);
        this.add(searchArea , BorderLayout.CENTER);
    }

}

