/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author usuario
 */
public class Centrar_Cabeceras implements TableCellRenderer{

    TableCellRenderer tcr;
    public Centrar_Cabeceras(TableCellRenderer tcr){
        this.tcr = tcr;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        javax.swing.JComponent wth = (javax.swing.JComponent) tcr.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        javax.swing.JLabel label = (javax.swing.JLabel)wth;
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        return wth;
    }
    
}
