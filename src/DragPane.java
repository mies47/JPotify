import java.awt.Color;
import java.awt.Dimension;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import javax.swing.JPanel;

public class DragPane extends JPanel {

    private DragGestureRecognizer dgr;
    private DragGestureHandler dragGestureHandler;

    public DragPane() {
        System.out.println("DragPane = " + this.hashCode());
        setBackground(Color.RED);
        dragGestureHandler = new DragGestureHandler(this);
        dgr = DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, dragGestureHandler);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(50, 50);
    }

}