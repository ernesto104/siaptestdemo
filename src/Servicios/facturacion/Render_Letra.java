/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.facturacion;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author CRS
 */
public class Render_Letra implements TableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return (JComponent) table.getValueAt(row, column);
    }
    
}
