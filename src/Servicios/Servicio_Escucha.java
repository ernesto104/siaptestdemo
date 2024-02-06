/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author fabrica2
 */
public class Servicio_Escucha implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Object fuente = e.getSource();
        if (fuente instanceof JButton) {
            JButton btn = (JButton) fuente;
            

        }
        if (fuente instanceof JTextField) {
            JTextField txt = (JTextField) fuente;
            if (txt.getText().isEmpty()) {
                System.out.println("VACIO");
            }
        }
        if (fuente instanceof JTable) {
            JTable tab = (JTable) fuente;
            System.out.println("tabla");
        }
    }

}
