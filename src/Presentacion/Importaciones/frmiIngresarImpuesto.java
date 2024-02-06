package Presentacion.Importaciones;

import Entidades.Cabecsugerido;
import Entidades.Tramites;
import Mantenimiento.Importaciones.TramitesDAO;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Servicios.Importaciones.FullSelectorListener;
import Servicios.Importaciones.Servicio_CabSugerido;
import Servicios.Importaciones.Servicio_Tramites;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class frmiIngresarImpuesto extends javax.swing.JFrame {
    static private IU_Valorizacion iuvaloriza;
    Servicio_Tramites st;
    int opcion;
    int filaSel;
    private int limConcepto;
    private int limImporte;
    
    public frmiIngresarImpuesto(IU_Valorizacion iuval,int opc) {
        initComponents();
        limConcepto = 50;
        limImporte = 9;
        iuvaloriza = iuval;
        iuvaloriza.setEnabled(false);
        st = new Servicio_Tramites(iuvaloriza);
        txtConcepto.addFocusListener( new FullSelectorListener() );
        txtImporte.addFocusListener( new FullSelectorListener() );
        opcion = opc;
        
        if ( opcion == 1 ) {
            this.setTitle("INGRESAR IMPUESTO");
            
        } else if ( opcion == 2 ) {
            filaSel = iuval.getFilaSeleccionadaTabla();
            String glosa = String.valueOf(iuval.tbImpuestos.getModel().getValueAt(filaSel,0));
            txtConcepto.setText(glosa);
            String importe = String.valueOf(iuval.tbImpuestos.getModel().getValueAt(filaSel,1));
            txtImporte.setText(quitaComa(importe));
            this.setTitle("MODIFICAR IMPUESTO");
            btnAgregar.setText("GUARDAR");
        }
    }
    
    public String quitaComa(String numero){
        int c = numero.length();
        String cad = "";
        for ( int contador = 0; contador < c; contador++ ) {
            if ( numero.charAt(contador) == ',' ) {
                numero.replace(',',' ');
                
            } else {
                cad += numero.charAt(contador);
            }
        }
        return cad;
    }
    
    private boolean validarNuevoImpuesto(String concepto, String importe,int filaSeleccionada){
        boolean val = false;
        int idcab = Integer.parseInt(iuvaloriza.txtNumPedido.getText());
        JOptionPane jop = new JOptionPane();
        
        if ( concepto.equals("") ) {
            jop.showMessageDialog(null, "Por favor, ingrese el concepto del impuesto","CAMPO INCOMPLETO", JOptionPane.OK_OPTION);
            txtConcepto.grabFocus();
            
        } else if ( importe.equals("") ) {
            jop.showMessageDialog(null, "Por favor, ingrese el importe del impuesto","CAMPO INCOMPLETO", JOptionPane.OK_OPTION);
            txtImporte.grabFocus();
            
        } else if ( st.verificarGlosaRepetida(idcab,txtConcepto.getText()) && opcion == 1 ) {
            jop.showMessageDialog(null, "Por favor, ingrese otro concepto para el impuesto","CONCEPTO REGISTRADO", JOptionPane.OK_OPTION);
            txtConcepto.grabFocus();
            
        } else if ( opcion == 2 && concepto.equals(iuvaloriza.tbImpuestos.getValueAt(filaSeleccionada,0)) ) {//Modificar
            val = true;
            
        } else {
            val = true;
        }
        return val;
    }  
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        txtConcepto = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("AGREGAR IMPUESTO");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnAgregar.setText("AGREGAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel1.setText("CONCEPTO:");

        jLabel2.setText("IMPORTE:");

        txtImporte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtImporteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImporteKeyTyped(evt);
            }
        });

        txtConcepto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConceptoKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtConceptoKeyTyped(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        iuvaloriza.setEnabled(true);
        dispose();
    }//GEN-LAST:event_formWindowClosing
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if ( opcion == 1 ) { 
            agregar(); 
        } else if ( opcion == 2 ) { 
            modificar();
        }
    }//GEN-LAST:event_btnAgregarActionPerformed
    private void txtImporteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            if ( opcion == 1 ) { 
                agregar();
                
            } else if ( opcion == 2 ) { 
                modificar();
            }
        }
    }//GEN-LAST:event_txtImporteKeyPressed
    private void txtImporteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImporteKeyTyped
        if ( txtImporte.getText().length() == limImporte ) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        char caracter = evt.getKeyChar();
        if ( ((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter!='.') ) {
            evt.consume();
        }
    }//GEN-LAST:event_txtImporteKeyTyped
    private void txtConceptoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConceptoKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            txtImporte.grabFocus();
        }
    }//GEN-LAST:event_txtConceptoKeyPressed
    private void txtConceptoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConceptoKeyTyped
        if ( txtConcepto.getText().length() == limConcepto ) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        char c = evt.getKeyChar();
        if ( c >= 'a' && c <= 'z' ) {
            evt.setKeyChar((char) (c - 32));
        }
    }//GEN-LAST:event_txtConceptoKeyTyped
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        iuvaloriza.setEnabled(true);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    
    public void agregar() {
        String concepto = txtConcepto.getText();
        String importe = txtImporte.getText();
        
        if ( validarNuevoImpuesto(concepto, importe,-1) ) {
            JOptionPane jop = new JOptionPane();
            
            if ( JOptionPane.showConfirmDialog(this, "¿Está seguro de registrar el impuesto?", "Confirmacion", 
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Tramites tram = new Tramites();
                Servicio_CabSugerido scs = new Servicio_CabSugerido();
                Cabecsugerido cs = new Cabecsugerido();
                cs = scs.getCabSugerido(Integer.parseInt(iuvaloriza.getNumPedido()));
                tram.setCabecsugerido(cs);
                tram.setGlosa(concepto);
                TramitesDAO tramDao = new TramitesDAO();
                tram.setIdtramite(tramDao.nextId());
                tram.setImporte(Double.parseDouble(importe));
                
                if ( st.registrarTramite(tram) ) {
                    agregarFilaATabla(tram);
                    iuvaloriza.setEnabled(true);
                    iuvaloriza.actualizarTotalImportacion();
                    jop.showMessageDialog(null, "Impuesto registrado con éxito","EXITO", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    
                } else {
                    jop.showMessageDialog(null, "No se pudo registrar el impuesto en la BD", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                iuvaloriza.setEnabled(true);
                dispose();
            }
        }
    }
    
    private void formatearDecimalDerechaEnTextField(JTextField txt,double valor){
        String moneyCad = "";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        moneyCad = formateador.format(valor);
        int indice = moneyCad.indexOf(".");
        
        if ( indice == -1 ) {
            moneyCad = moneyCad + ".00";
            
        } else if ( moneyCad.length() - indice == 2 ) {
            moneyCad = moneyCad + "0";
        }
        txt.setText(moneyCad);
    }
    
    private void agregarFilaATabla(Tramites t) {
        Object row[] = new Object[2];
        row[0] = t.getGlosa();
        double impuesto = t.getImporte();
        row[1] = impuesto;
        DefaultTableModel temp = (DefaultTableModel) iuvaloriza.tbImpuestos.getModel();
        temp.addRow(row);
        formatearColumnaDerecha(impuesto,temp.getRowCount()-1, 1);
    }
    
    public void modificar() {
        String glosa1 = String.valueOf(iuvaloriza.tbImpuestos.getValueAt(filaSel,0));
        String glosa2 = txtConcepto.getText();
        String importe2 = txtImporte.getText();
        
        if ( validarNuevoImpuesto(glosa2, importe2, filaSel) ) {
            if ( JOptionPane.showConfirmDialog(this,"¿Está seguro de guardar cambios?", 
                "Confirmacion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ) {
                int idsugerido = Integer.parseInt(iuvaloriza.txtNumPedido.getText());
                int idImpuesto = st.getIdTramiteXGlosa(idsugerido,glosa1);
                
                if ( idImpuesto != -1 ) {
                    if ( st.actualizarImpuesto(idImpuesto,Double.parseDouble(importe2),glosa2) ) {
                        formatearDecimalDerechaEnTextField(txtImporte, Double.parseDouble(importe2));
                        iuvaloriza.tbImpuestos.setValueAt(glosa2, filaSel, 0);
                        iuvaloriza.tbImpuestos.setValueAt(importe2, filaSel, 1);
                        iuvaloriza.setEnabled(true);
                        iuvaloriza.actualizarTotalImportacion();
                        formatearColumnaDerecha(Double.parseDouble(importe2), filaSel, 1);
                    }
                }
                dispose();
            }
        }
    }
    
    private void formatearColumnaDerecha(double valor, int fila, int columna){
        String moneyCad = "";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        moneyCad = formateador.format(valor);
        int indice = moneyCad.indexOf(".");
        if ( indice == -1 ) {
            moneyCad = moneyCad + ".00";
            
        } else if (moneyCad.length() - indice == 2 ) {
            moneyCad = moneyCad + "0";
        }
        iuvaloriza.tbImpuestos.setValueAt(moneyCad, fila, columna);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtConcepto;
    private javax.swing.JTextField txtImporte;
    // End of variables declaration//GEN-END:variables
}