package Mantenimiento;

import Servicios.TipoMensaje;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Administrador
 */
public class Navegacion extends KeyAdapter {

    JFrame frame;
    JTextField[] compNombre;
    ArrayList<Integer> numMax;    
    int size;
    TipoMensaje tipoMensaje;
    JButton boton;
    ArrayList<String> tipoDato;

    public Navegacion(JTextField[] comp, ArrayList<Integer> numMax, ArrayList<String> tipoDato, JButton boton) {
        this.compNombre = comp;
        size = comp.length;
        this.numMax = numMax;
        tipoMensaje = new TipoMensaje();
        this.boton = boton;
        this.tipoDato = tipoDato;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        for ( int i = 0; i < size; i++ ) {
            if ( e.getSource() == compNombre[i] ) {
                int cod = (int) e.getKeyChar();
                char c = e.getKeyChar();//sacamos el caracter que se ha presionado
                
                //Para ENTER
                if ( cod == 10 ) {
                    if ( i == size - 1 ) {
                        boton.requestFocus();
                        
                    } else {
                        compNombre[(i + 1) % size].requestFocus();
                    }
                    break;
                }
                
                if ( c >= 'a' && c <= 'z' ) {
                    e.setKeyChar((char) (c - 32));
                }

                if ( tipoDato.get(i).equals("D") ) { // Decimal
                    if ( ((cod < '0') || (cod > '9')) && (cod != '.') && (cod != '\b') ) /*corresponde a BACK_SPACE*/ {
                        if ( cod == ',' ) {
                            e.consume();
                            tipoMensaje.manejarMensajes(tipoMensaje.INGRESAR_COMA);
                            
                        } else {
                            e.consume();  // ignorar el evento de teclado
                            tipoMensaje.manejarMensajes(tipoMensaje.INGRESAR_NO_ENTERO);
                        }
                    }
                    
                } else if ( tipoDato.get(i).equals("I") ) { // Integer
                    if ( ((cod < '0') || (cod > '9')) && (cod != '\b') ) /*corresponde a BACK_SPACE*/ {
                        e.consume();
                        tipoMensaje.manejarMensajes(tipoMensaje.INGRESAR_NO_ENTERO);
                    }
                } // Los demás son String ("S")
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for ( int i = 0; i < size; i++ ) {
            if ( e.getSource() == compNombre[i] ) {
                if ( compNombre[i].getText().length() >= numMax.get(i)-1 && e.getKeyChar()!='\b' &&
                        e.getKeyChar()!=127 && e.getKeyCode()!=37 && e.getKeyCode()!=39 ) { //suprimir
                    e.consume();
                    if ( i == size-1 ) boton.requestFocus();
                    else
                        compNombre[(i + 1) % size].requestFocus();
                    break;
                }
            }
        }
    }
}