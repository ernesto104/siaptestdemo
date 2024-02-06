/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author maverick225
 */
public class Limitador extends PlainDocument {

    private int limite;

    public Limitador(int l) {
        super();
        limite = l;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if ((getLength() + str.length()) <= limite) {
            super.insertString(offset, str, attr);
        }
    }
}
