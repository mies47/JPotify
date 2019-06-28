import javax.swing.*;
import java.awt.datatransfer.DataFlavor;

public class PanelDataFlavor extends DataFlavor {
    // This saves me having to make lots of copies of the same thing
    public static final PanelDataFlavor SHARED_INSTANCE = new PanelDataFlavor();

    public PanelDataFlavor() {

        super(JPanel.class, null);

    }

}

