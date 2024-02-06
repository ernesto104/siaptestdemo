package Presentacion.Notas;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author CRS
 */
public class Editor extends AbstractCellEditor implements TableCellEditor{
    
    private Boolean currentValue;
    @Override
    public Object getCellEditorValue() {
        return currentValue;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return (JComponent)value;
    }
    
}
