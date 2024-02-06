package Presentacion.Reimpresion;

import Entidades.Cabeces;
import Entidades.Clientes;
import Entidades.Detallees;
import static Entidades.Otros.Constante.NOTA_ING_AJUSTES;
import static Entidades.Otros.Constante.NOTA_ING_COMPRA_LOCAL;
import static Entidades.Otros.Constante.NOTA_SALIDA;
import Entidades.Sistema;
import Mantenimiento.DetallesDAO;
import Mantenimiento.Facturacion.SistemaDAO;
import Servicios.Servicio_Cabeces;
import Servicios.Servicio_Documentos;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Lesly Aguilar
 */
public class FREP021 extends javax.swing.JFrame {

    public FREP021() {
        initComponents();
        inicializarNroSerie();
        txtNroDocum.setText("");
        
        this.setLocationRelativeTo(null);
        comboTipoDoc.requestFocus();
    }
    
    private void inicializarNroSerie() {
        Sistema sis = new SistemaDAO().getActualNroSerie("01");
        txtNroSerie.setText(String.valueOf(sis.getSerie()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCalendarBeanInfo1 = new com.toedter.calendar.JCalendarBeanInfo();
        panelReImpresion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboTipoDoc = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtNroDocum = new javax.swing.JTextField();
        btnContinuar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtNroSerie = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REIMPRESIÓN DE DOCUMENTOS");

        jLabel2.setText("<html>Tipo de <br />Documento</html>");

        comboTipoDoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Boleta", "Guía de Remisión", "Nota de Crédito", "Nota de Débito", "Nota de Ingreso por Compra Local", "Nota de Ingreso por Ajustes", "Nota de Salida" }));
        comboTipoDoc.setNextFocusableComponent(txtNroDocum);
        comboTipoDoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboTipoDocItemStateChanged(evt);
            }
        });

        jLabel3.setText("N° Documento:");

        txtNroDocum.setNextFocusableComponent(btnContinuar);
        txtNroDocum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroDocumKeyTyped(evt);
            }
        });

        btnContinuar.setText("Continuar");
        btnContinuar.setNextFocusableComponent(btnCancelar);
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Salir");
        btnCancelar.setNextFocusableComponent(comboTipoDoc);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel4.setText("N° Serie:");

        javax.swing.GroupLayout panelReImpresionLayout = new javax.swing.GroupLayout(panelReImpresion);
        panelReImpresion.setLayout(panelReImpresionLayout);
        panelReImpresionLayout.setHorizontalGroup(
            panelReImpresionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReImpresionLayout.createSequentialGroup()
                .addGroup(panelReImpresionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelReImpresionLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelReImpresionLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(panelReImpresionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelReImpresionLayout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(comboTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelReImpresionLayout.createSequentialGroup()
                                .addGroup(panelReImpresionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(panelReImpresionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNroDocum, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                                    .addComponent(txtNroSerie))))))
                .addContainerGap(122, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelReImpresionLayout.setVerticalGroup(
            panelReImpresionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelReImpresionLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addGroup(panelReImpresionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(panelReImpresionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtNroSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelReImpresionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNroDocum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(panelReImpresionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelReImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelReImpresion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean validarDocumento() {
        if ( "".equals(txtNroSerie.getText()) || "".equals(txtNroDocum.getText()) ) {
            return false;
            
        } else {
            return true;
        }
    }
    
    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed

        if ( validarDocumento() ) {
            Servicio_Documentos s = new Servicio_Documentos();
            Cabeces c = new Cabeces();

            //Factura
            if ( comboTipoDoc.getSelectedIndex() == 0 ) {
                // String tipoTransaccion, String tipoDocumento, String numeroSerie, String numeroDocumento
                c = s.obtenerCabeceraPorDocumento("V", "01", txtNroSerie.getText(), txtNroDocum.getText());

                if ( c == null ) {
                    JOptionPane.showMessageDialog(null, "No existe ese número de documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
//                    txtNroSerie.setText("");
                    txtNroDocum.setText("");

                } else {
                    IU_Reimpresion_B_F_GR iu = new IU_Reimpresion_B_F_GR(c,0, this);
                    this.setVisible(false);
                    iu.setVisible(true);
//                    txtNroSerie.setText("");
                    txtNroDocum.setText("");
                }
            } else {

                //Boleta
                if ( comboTipoDoc.getSelectedIndex() == 1 ) {
//                    c = s.obtenerCabeceraPorDocumento(txtNroDocum.getText(), "02");
                    c = s.obtenerCabeceraPorDocumento("V", "02", txtNroSerie.getText(), txtNroDocum.getText());

                    if ( c == null ) {
                        JOptionPane.showMessageDialog(null, "No existe ese número de documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
//                        txtNroSerie.setText("");
                        txtNroDocum.setText("");

                    } else {
                        IU_Reimpresion_B_F_GR iu = new IU_Reimpresion_B_F_GR(c,1, this);
                        this.setVisible(false);
                        iu.setVisible(true);
                    }
                } else {

                    //Guia de Remision
                    if ( comboTipoDoc.getSelectedIndex() == 2 ) {
                        Servicio_Cabeces sc = new Servicio_Cabeces();
                        int nroGuia = Integer.parseInt(txtNroDocum.getText());
                        c = sc.obtenerCabecera(nroGuia);

                        if ( c == null ) {
                            JOptionPane.showMessageDialog(null, "No existe ese número de documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
//                            txtNroSerie.setText("");
                            txtNroDocum.setText("");

                        } else {
                            IU_Reimpresion_B_F_GR iu = new IU_Reimpresion_B_F_GR(c,2, this);
                            this.setVisible(false);
                            iu.setVisible(true);
                        }
                    } else {

                        //Nota de Credito (CORREGIR)
                        if ( comboTipoDoc.getSelectedIndex() == 3 ) {
//                            c = s.obtenerCabeceraPorDocumento(txtNroDocum.getText(), "03");
                            // String tipoTransaccion, String tipoDocumento, String numeroSerie, String numeroDocumento
                            c = s.obtenerCabeceraPorDocumento("V", "03", txtNroSerie.getText(), txtNroDocum.getText());

                            if ( c == null ) {
                                JOptionPane.showMessageDialog(null, "No existe ese número de documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
//                                txtNroSerie.setText("");
                                txtNroDocum.setText("");

                            } else {
                                try {
                                    if ( c.getId().getNrorserie().equals("") ) {
                                        IU_Reimpresion_Nota_Sin_Serie iu = new IU_Reimpresion_Nota_Sin_Serie(c.getClientes(), c, "Nota de Crédito por Descuento", true);
                                        this.setVisible(false);
                                        iu.setVisible(true);

                                    } else {
                                        IU_Reimpresion_Nota_Con_Serie iu = new IU_Reimpresion_Nota_Con_Serie(c.getClientes(), c, "Nota de Crédito por Devolución", 
                                                                                                             txtNroDocum.getText(), this);
                                        this.setVisible(false);
                                        iu.setVisible(true);
                                    }

                                } catch (ParseException ex) {
                                    Logger.getLogger(FREP021.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        } else {

                            //Nota de Debito (NO REVISADO)
                            if (comboTipoDoc.getSelectedIndex() == 4) {
                                c = s.obtenerCabeceraPorDocumento(txtNroDocum.getText(), "04");
                                if (c == null) {
                                    JOptionPane.showMessageDialog(null, "No existe ese número de documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
//                                    txtNroSerie.setText("");
                                    txtNroDocum.setText("");
                                } else {
                                    IU_Reimpresion_Nota_Sin_Serie iu = null;
                                    try {
                                        iu = new IU_Reimpresion_Nota_Sin_Serie(c.getClientes(), c, "Nota de Débito", false);
                                        
                                    } catch (ParseException ex) {
                                        Logger.getLogger(FREP021.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    this.setVisible(false);
                                    iu.setVisible(true);
                                }
                            } else {
                                //Nota de Ingreso
                                IU_Reimpresion_Ingreso_Salida iu;
                                List<Detallees> l;
                                int seleccion = comboTipoDoc.getSelectedIndex();
                                String tipo = String.valueOf(seleccion);
                                Clientes proveedorNotIng = null;
                                String nroDocProveedor = "";
                                
                                if ( NOTA_ING_COMPRA_LOCAL.equals(tipo) || NOTA_ING_AJUSTES.equals(tipo) || NOTA_SALIDA.equals(tipo) ) {
                                    DetallesDAO d = new DetallesDAO();
                                    
                                    if ( NOTA_SALIDA.equals(tipo) ) {
                                        l = d.getListaDetalleEs_Salida(Integer.parseInt(txtNroDocum.getText()));
                                        
                                    } else if ( NOTA_ING_COMPRA_LOCAL.equals(tipo) ) {
                                        l = d.getListaDetalleEs_IngCompraLocal(Integer.parseInt(txtNroDocum.getText()));
                                        
                                    } else {
                                        l = d.getListaDetalleEs_IngAjuste(Integer.parseInt(txtNroDocum.getText()));
                                    }
                                    
                                    if ( !l.isEmpty() ) {
                                        
                                        if ( NOTA_ING_COMPRA_LOCAL.equals(tipo) ) {
//                                            String nrodoc = l.get(0).getNrodocumento();
//                                            System.out.println("nrodoc::>>" + nrodoc);

                                            proveedorNotIng = l.get(0).getClientes();
                                            System.out.println("proveedorNotIng::" + proveedorNotIng);
                                            System.out.println("nombre de proveedor:" + proveedorNotIng.getNombre());
                                            System.out.println("idProveedor:" + proveedorNotIng.getIdcliente());
                                            System.out.println("cabeces:" + l.get(0).getCabeces());

                                            nroDocProveedor = l.get(0).getCabeces().getId().getNrodocumento();
                                        }
                                        String motivoCompraLocal = l.get(0).getMotivo();
                                        
                                        try {
                                            iu = new IU_Reimpresion_Ingreso_Salida(Integer.parseInt(txtNroDocum.getText()), tipo, proveedorNotIng, nroDocProveedor, motivoCompraLocal);
                                            iu.setVisible(true);
                                            this.setVisible(false);
                                            
                                        } catch (ParseException ex) {
                                            Logger.getLogger(FREP021.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No existe ese número de documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
//                                        txtNroSerie.setText("");
                                        txtNroDocum.setText("");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese valores no vacíos para N° Serie y N° Documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void txtNroDocumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroDocumKeyTyped
        int cod = (int) evt.getKeyChar();
        if (cod == 10) {
            btnContinuar.doClick();
        }
    }//GEN-LAST:event_txtNroDocumKeyTyped

    private void comboTipoDocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTipoDocItemStateChanged
        // Obtener el último N° Serie de tabla Sistema para mostrar por defecto al buscar reimpresión
        String tipoDoc = String.valueOf(comboTipoDoc.getSelectedItem());
        String nroSerie = "", tipoDocSis = null;
        Sistema sis = null;
        txtNroDocum.setText("");
        
        switch ( tipoDoc ) {
            case "Factura":
                tipoDocSis = "01";
                break;
                
            case "Boleta":
                tipoDocSis = "02";
                break;
                
            case "Guía de Remisión":
                tipoDocSis = "05";
                break;
                
            case "Nota de Crédito":
                tipoDocSis = "03";
                break;
               
            case "Nota de Débito":
                tipoDocSis = "04";
                break;
                
            case "Nota de Ingreso por Compra Local":
                tipoDocSis = "08";
                break;
                
            case "Nota de Ingreso por Ajustes":
                tipoDocSis = "11";
                break;
                
            case "Nota de Salida":
                tipoDocSis = "14";
                break;
        }
        sis = new SistemaDAO().getActualNroSerie(tipoDocSis);
        nroSerie = String.valueOf(sis.getSerie());
        txtNroSerie.setText(nroSerie);
    }//GEN-LAST:event_comboTipoDocItemStateChanged

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCancelar;
    public javax.swing.JButton btnContinuar;
    public javax.swing.JComboBox comboTipoDoc;
    public com.toedter.calendar.JCalendarBeanInfo jCalendarBeanInfo1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JPanel panelReImpresion;
    public javax.swing.JTextField txtNroDocum;
    public javax.swing.JTextField txtNroSerie;
    // End of variables declaration//GEN-END:variables
}
