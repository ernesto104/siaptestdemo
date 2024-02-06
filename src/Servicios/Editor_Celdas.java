/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Cristian
 */
public class Editor_Celdas extends DefaultCellEditor implements TableCellEditor {

    JComponent component;
    boolean editando;
    public Editor_Celdas(JTextField textField) {
        super(textField);
        component = textField;
        editando = false;   
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int rowIndex, int vColIndex) {
        if(!editando)
            ((JTextField) component).setText("");
        else{
            ((JTextField) component).setText(String.valueOf(value));
        }
        editando = false;
        return component;
    }
    
    @Override
    public boolean isCellEditable(EventObject anEvent) {
        if (anEvent instanceof MouseEvent) {
            MouseEvent evento = (MouseEvent)anEvent;
            if(evento.getClickCount()>=clickCountToStart){
                editando=true;
                return true;
            }
            return false;
        }
        return true;
    }
    
    @Override
    public Object getCellEditorValue() {
        return ((JTextField) component).getText();
    }

}
