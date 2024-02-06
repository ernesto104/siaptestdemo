package Presentacion.Anulacion;

import Entidades.Cabeces;
import Entidades.Detallees;
import Entidades.Detallenota;
import Entidades.Otros.Monetario;
import Entidades.Usuarios;
import Mantenimiento.DetalleNotaDAO;
import Mantenimiento.DetallesDAO;
import Presentacion.Reimpresion.IU_Reimpresion_Ingreso_Salida;
import Servicios.Servicio_Documentos;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Lesly Aguilar
 */
public class FREP023 extends javax.swing.JFrame {

    Cabeces c;
    List<Detallees> l;
    List<Detallenota> lN;
    boolean mov;
    String tipo;
    boolean aument;
    Usuarios usuario;

    public FREP023(Usuarios usuario) {
        initComponents();
        this.setLocationRelativeTo(null);
        c = new Cabeces();
        iniciar();
        comboTipoDoc.requestFocus();
        
        btnContinuar.setEnabled(false);
        this.usuario = usuario;
    }

    public void iniciar() {
        txtCliente.setEditable(false);
        txtFechaEmision.setEditable(false);
        txtImporte.setEditable(false);
    }

    public void Limpiar_Campos(){
        txtCliente.setText("");
        txtFechaEmision.setText("");
        txtImporte.setText("");
        txtNroDocum.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAnulacion = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNroDocum = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        comboTipoDoc = new javax.swing.JComboBox();
        btnAceptar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtFechaEmision = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        txtlabel1 = new javax.swing.JLabel();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ANULACIÓN DE DOCUMENTOS");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Anulación de Documentos"));

        jLabel3.setText("Número de Documento:");

        txtNroDocum.setNextFocusableComponent(btnAceptar);
        txtNroDocum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNroDocumKeyTyped(evt);
            }
        });

        jLabel2.setText("Tipo de Documento:");

        comboTipoDoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Boleta", "Guía de Remisión", "Nota de Crédito", "Nota de Débito", "Nota de Ingreso/Salida" }));
        comboTipoDoc.setNextFocusableComponent(txtNroDocum);

        btnAceptar.setText("Aceptar");
        btnAceptar.setNextFocusableComponent(btnContinuar);
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        btnAceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnAceptarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboTipoDoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNroDocum))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(238, 238, 238))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNroDocum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Documento"));

        jLabel4.setText("Fecha de emisión:");

        jLabel5.setText("Cliente:");

        jLabel6.setText("Importe");

        txtlabel1.setText("(US$):");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtlabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(257, 257, 257)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtlabel1))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        btnContinuar.setText("Continuar");
        btnContinuar.setEnabled(false);
        btnContinuar.setNextFocusableComponent(btnSalir);
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.setNextFocusableComponent(comboTipoDoc);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAnulacionLayout = new javax.swing.GroupLayout(panelAnulacion);
        panelAnulacion.setLayout(panelAnulacionLayout);
        panelAnulacionLayout.setHorizontalGroup(
            panelAnulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAnulacionLayout.createSequentialGroup()
                .addGroup(panelAnulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAnulacionLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(panelAnulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelAnulacionLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelAnulacionLayout.setVerticalGroup(
            panelAnulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAnulacionLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelAnulacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelAnulacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAnulacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        Servicio_Documentos s = new Servicio_Documentos();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DetallesDAO d = new DetallesDAO();
        DetalleNotaDAO dN = new DetalleNotaDAO();

        if ( !txtNroDocum.getText().equals("") ) {

            //Factura
            if ( comboTipoDoc.getSelectedIndex() == 0 ) {
                c = s.obtenerCabeceraPorDocumento(txtNroDocum.getText(), "01");
                if ( c == null ) {
                    JOptionPane.showMessageDialog(null, "No existe ese número de documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    txtCliente.setText("");
                    txtFechaEmision.setText("");
                    txtImporte.setText("");
                    txtNroDocum.setText("");
                    btnContinuar.setEnabled(false);
                            
                } else {
                    if ( c.getMoneda().equalsIgnoreCase("2") ) {
                        txtlabel1.setText("(US$):");
                        
                    } else {
                        txtlabel1.setText("(S/.):");
                    }
                    
                    l = d.getListaDetalleEs(c.getId());
                    txtFechaEmision.setText(sdf.format(c.getTipocambio().getFecha()));
                    if ( c.getClientes() != null ) {
                        txtCliente.setText(c.getClientes().getNombre());
                    }
                    String i = Monetario.formatearMonetario(String.valueOf(c.getTotal()), 2);
                    txtImporte.setText(i);
                    btnContinuar.setEnabled(true);
                    this.mov = true;
                    this.aument = true;
                }
            } else {
                //Boleta
                if ( comboTipoDoc.getSelectedIndex() == 1 ) {
                    c = s.obtenerCabeceraPorDocumento(txtNroDocum.getText(), "02");
                    
                    if ( c == null ) {
                        JOptionPane.showMessageDialog(null, "No existe ese número de documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                        txtCliente.setText("");
                        txtFechaEmision.setText("");
                        txtImporte.setText("");
                        txtNroDocum.setText("");
                        btnContinuar.setEnabled(false);
                        
                    } else {
                        l = d.getListaDetalleEs(c.getId());
                        txtFechaEmision.setText(sdf.format(c.getTipocambio().getFecha()));
                        if ( c.getClientes() != null ) {
                            txtCliente.setText(c.getClientes().getNombre());
                        }
                        String i = Monetario.formatearMonetario(String.valueOf(c.getTotal()), 2);
                        txtImporte.setText(i);
                        btnContinuar.setEnabled(true);
                        this.mov = true;
                        this.aument = true;
                    }
                } else {
                    //Guia de Remision
                    if ( comboTipoDoc.getSelectedIndex() == 2 ) {
                        c = s.obtenerCabeceraPorDocumento(txtNroDocum.getText(), "05");
                        
                        if ( c == null ) {
                            JOptionPane.showMessageDialog(null, "No existe ese número de documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                            txtCliente.setText("");
                            txtFechaEmision.setText("");
                            txtImporte.setText("");
                            txtNroDocum.setText("");    
                            btnContinuar.setEnabled(false);
                        } else {
                            l = d.getListaDetalleEs(c.getId());
                            txtFechaEmision.setText(sdf.format(c.getTipocambio().getFecha()));
                            
                            if ( c.getClientes() != null ) {
                                txtCliente.setText(c.getClientes().getNombre());
                            }
                            String i = Monetario.formatearMonetario(String.valueOf(c.getTotal()), 2);
                            txtImporte.setText(i);
                            btnContinuar.setEnabled(true);
                            this.mov = true;
                            this.aument = true;
                        }
                    } else {
                        //Nota de Credito
                        if ( comboTipoDoc.getSelectedIndex() == 3 ) {
                            c = s.obtenerCabeceraPorDocumento(txtNroDocum.getText(), "03");
                            
                            if ( c == null ) {
                                JOptionPane.showMessageDialog(null, "No existe ese número de documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                                txtCliente.setText("");
                                txtFechaEmision.setText("");
                                txtImporte.setText("");
                                txtNroDocum.setText("");
                                btnContinuar.setEnabled(false);
                            } else {
                                //Nota de credito por descuento
                                if ( c.getId().getTipotra().equals("") ) {
                                    txtFechaEmision.setText(sdf.format(c.getTipocambio().getFecha()));
                                    
                                    if ( c.getClientes() != null ) {
                                        txtCliente.setText(c.getClientes().getNombre());
                                    }
                                    String i = Monetario.formatearMonetario(String.valueOf(c.getTotal()), 2);
                                    txtImporte.setText(i);
                                    btnContinuar.setEnabled(true);
                                    this.mov = false;
                                    
                                } else {
                                    //Nota de credito por devolucion
                                    lN = dN.getListaDetallePorDocumento(c.getId().getNrodocumento());
                                    txtFechaEmision.setText(sdf.format(c.getTipocambio().getFecha()));
                                    
                                    if ( c.getClientes() != null ) {
                                        txtCliente.setText(c.getClientes().getNombre());
                                    }
                                    String i = Monetario.formatearMonetario(String.valueOf(c.getTotal()), 2);
                                    txtImporte.setText(i);
                                    btnContinuar.setEnabled(true);
                                    this.mov = true;
                                    this.aument = false;
                                }
                            }
                        } else {
                            //Nota de Debito
                            if ( comboTipoDoc.getSelectedIndex() == 4 ) {
                                c = s.obtenerCabeceraPorDocumento(txtNroDocum.getText(), "04");
                                
                                if ( c == null ) {
                                    JOptionPane.showMessageDialog(null, "No existe ese número de documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                                    txtCliente.setText("");
                                    txtFechaEmision.setText("");
                                    txtImporte.setText("");
                                    txtNroDocum.setText("");
                                    btnContinuar.setEnabled(false);
                                    
                                } else {
                                    lN = dN.getListaDetallePorDocumento(c.getId().getNrodocumento());
                                    txtFechaEmision.setText(sdf.format(c.getTipocambio().getFecha()));
                                    if ( c.getClientes() != null ) {
                                        txtCliente.setText(c.getClientes().getNombre());
                                    }
                                    String i = Monetario.formatearMonetario(String.valueOf(c.getTotal()), 2);
                                    txtImporte.setText(i);
                                    btnContinuar.setEnabled(true);
                                    this.mov = false;
                                }
                            } else {
                                //Nota de Ingreso y Salidas
                                IU_Reimpresion_Ingreso_Salida iu;
                                int seleccion = comboTipoDoc.getSelectedIndex();
                                
                                if ( seleccion == 5 || seleccion == 6 ) {
                                    //Nota de Ingreso
                                    if ( seleccion == 5 ) {
                                        l = d.getListaDetalleEs_Ingreso(Integer.parseInt(txtNroDocum.getText()));
                                        tipo = "I";
                                        this.mov = true;
                                        this.aument = false;
                                        //Nota de Salida
                                    } else {
                                        l = d.getListaDetalleEs_Salida(Integer.parseInt(txtNroDocum.getText()));
                                        tipo = "S";
                                        this.mov = true;
                                        this.aument = true;
                                    }
                                    if ( l.size() != 0 ) {
                                        c = null;
                                        btnContinuar.setEnabled(true);
                                        
                                    } else {
                                        JOptionPane.showMessageDialog(null, "No existe ese número de documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                                        txtCliente.setText("");
                                        txtFechaEmision.setText("");
                                        txtImporte.setText("");
                                        txtNroDocum.setText("");
                                        btnContinuar.setEnabled(false);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese un número de documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        if ( c != null ) {
            if ( c.getEstado().equalsIgnoreCase("") ) {
                IU_Anulacion_Documento iu = new IU_Anulacion_Documento(c, l, lN, mov, aument, tipo, usuario);
                iu.setVisible(true);
                this.setVisible(false);
                
            } else {
                JOptionPane.showMessageDialog(null, "Este documento se encuentra anulado", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                Limpiar_Campos();
            }
        } else {
            if ( l.get(0).getMotivo() != null ) {
                JOptionPane.showMessageDialog(null, "Este documento se encuentra anulado", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                Limpiar_Campos();
                
            } else {
                IU_Anulacion_Documento iu = new IU_Anulacion_Documento(c, l,lN, mov, aument, tipo, usuario);
                iu.setVisible(true);
                this.setVisible(false);
                Limpiar_Campos();
            }
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtNroDocumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNroDocumKeyTyped
        int cod = (int) evt.getKeyChar();
        if ( cod == 10 ) {
            btnAceptar.doClick();
            btnAceptar.requestFocus();
        }
    }//GEN-LAST:event_txtNroDocumKeyTyped

    private void btnAceptarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAceptarKeyTyped
        int cod = (int) evt.getKeyChar();
        if ( cod == 10 ) {
            btnContinuar.requestFocus();
        }
    }//GEN-LAST:event_btnAceptarKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAceptar;
    public javax.swing.JButton btnContinuar;
    public javax.swing.JButton btnSalir;
    public javax.swing.JComboBox comboTipoDoc;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel panelAnulacion;
    public javax.swing.JTextField txtCliente;
    public javax.swing.JTextField txtFechaEmision;
    public javax.swing.JTextField txtImporte;
    public javax.swing.JTextField txtNroDocum;
    public javax.swing.JLabel txtlabel1;
    // End of variables declaration//GEN-END:variables
}