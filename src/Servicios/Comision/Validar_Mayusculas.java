/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios.Comision;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author CRS
 */
public class Validar_Mayusculas extends PlainDocument{
    private JTextField editor;
    
    private int NumeroCaracteres;

    public Validar_Mayusculas(JTextField editor, int NumeroCaracteres) {
        this.editor = editor;
        this.NumeroCaracteres = NumeroCaracteres;
    }
    public void insertString(int arg0, String arg1, AttributeSet arg2) throws BadLocationException 
    { 
        if ((editor.getText().length()+arg1.length())>this.NumeroCaracteres) 
            return; 
        if(Character.isLowerCase(arg1.charAt(0))){
            arg1=arg1.toUpperCase();
        }
        super.insertString(arg0, arg1, arg2); 
    } 
    
    
}
