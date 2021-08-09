package libreria;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ioriyagamy
 */
public class RenderCheckBox extends JCheckBox implements TableCellRenderer {

    private final JComponent component = new JCheckBox();

    public RenderCheckBox() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        ((JCheckBox) component).setBackground(new Color(0, 200, 0));
        boolean booleanDate = ((Boolean) o).booleanValue();
        ((JCheckBox) component).setSelected(booleanDate);
        return ((JCheckBox) component);
    }

}
