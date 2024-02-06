package Presentacion.Notas;

import Entidades.Cabeces;
import static Entidades.Otros.Constante.NC_DEVOLUCION;
import static Entidades.Otros.Constante.NC_DSCTO_ESPECIAL;
import Entidades.Usuarios;
import Servicios.Servicio_Documentos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author Lesly Aguilar
 * @author modified: Ledis Rivera Ch.
 */
public class IU_Emisión_Notas_Credito extends javax.swing.JFrame {

    Servicio_Documentos servicio_Documentos;
    Cabeces cabecEs;
    Usuarios usuario;

    public IU_Emisión_Notas_Credito(Usuarios u) {
        initComponents();
        this.setLocationRelativeTo(null);
        Iniciar();
        servicio_Documentos = new Servicio_Documentos();
        usuario = u;
        setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(false);
            }
        });
        
        combBoxTipoDoc.requestFocus();
    }

    public void Iniciar() {
        txtDireccionCliente.setEditable(false);
        txtFechaEmision.setEditable(false);
        txtIGV.setEditable(false);
        txtNombreCliente.setEditable(false);
        txtNumeroDocumento.setEditable(false);
        txtRUC.setEditable(false);
        txtTipoDocumento.setEditable(false);
        txtTotal.setEditable(false);
        txtValorVenta.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEmisionNotasCredito = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        combBoxTipoDoc = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtNumeroDocumentoBusc = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNumeroDocumento = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDireccionCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtFechaEmision = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtRUC = new javax.swing.JTextField();
        lblValVenta = new javax.swing.JLabel();
        txtValorVenta = new javax.swing.JTextField();
        lblValIGV = new javax.swing.JLabel();
        txtIGV = new javax.swing.JTextField();
        lblValTot = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtTipoDocumento = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnDevolucion = new javax.swing.JButton();
        btnDescEspecial = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGarantia = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EMISIÓN DE NOTAS DE CRÉDITO ");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de Documento"));

        jLabel2.setText("Tipo de Documento:");

        combBoxTipoDoc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Boleta" }));
        combBoxTipoDoc.setNextFocusableComponent(txtNumeroDocumentoBusc);

        jLabel3.setText("Número de Documento:");

        txtNumeroDocumentoBusc.setNextFocusableComponent(btnBuscar);
        txtNumeroDocumentoBusc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroDocumentoBuscKeyTyped(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.setNextFocusableComponent(btnDevolucion);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addComponent(combBoxTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 329, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtNumeroDocumentoBusc, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(combBoxTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtNumeroDocumentoBusc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel4.setText("Tipo de Documento:");

        jLabel5.setText("Número de Documento:");

        jLabel6.setText("Nombre del Cliente:");

        jLabel7.setText("Dirección del Cliente:");

        jLabel8.setText("Fecha de Emisión:");

        jLabel9.setText("Número de R.U.C:");

        lblValVenta.setText("Valor de Venta");

        lblValIGV.setText("Valor de I.G.V");

        lblValTot.setText("Valor Total");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblValTot))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblValIGV)
                            .addComponent(lblValVenta))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtValorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtDireccionCliente)
                        .addGap(33, 33, 33))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 519, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreCliente)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValVenta)
                    .addComponent(txtValorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValIGV)
                    .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValTot)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Generar Nota"));

        jLabel13.setText("Nota de Crédito por:");

        btnDevolucion.setText("Devolución");
        btnDevolucion.setNextFocusableComponent(btnDescEspecial);
        btnDevolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolucionActionPerformed(evt);
            }
        });

        btnDescEspecial.setText("Descuento especial");
        btnDescEspecial.setNextFocusableComponent(btnCancelar);
        btnDescEspecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescEspecialActionPerformed(evt);
            }
        });

        btnCancelar.setText("Salir");
        btnCancelar.setNextFocusableComponent(combBoxTipoDoc);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGarantia.setText("Garantía");
        btnGarantia.setNextFocusableComponent(btnCancelar);
        btnGarantia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGarantiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel13)
                        .addGap(39, 39, 39)
                        .addComponent(btnDevolucion, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGarantia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDescEspecial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(286, 286, 286)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(btnDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDescEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGarantia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelEmisionNotasCreditoLayout = new javax.swing.GroupLayout(panelEmisionNotasCredito);
        panelEmisionNotasCredito.setLayout(panelEmisionNotasCreditoLayout);
        panelEmisionNotasCreditoLayout.setHorizontalGroup(
            panelEmisionNotasCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmisionNotasCreditoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelEmisionNotasCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelEmisionNotasCreditoLayout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(22, 22, 22))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelEmisionNotasCreditoLayout.setVerticalGroup(
            panelEmisionNotasCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmisionNotasCreditoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEmisionNotasCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEmisionNotasCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
        //  IU_Emisión_Notas iuEmision = new IU_Emisión_Notas();
        //  iuEmision.setVisible(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnDevolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolucionActionPerformed
        if ( txtNumeroDocumentoBusc.getText().equals("") ) {
            JOptionPane.showMessageDialog(null, "Ingrese el Número de Documento", 
                                          "Error", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
            String fechaEmision = txtFechaEmision.getText();
            String tipoDocumento = String.valueOf(combBoxTipoDoc.getSelectedItem());
            String nroFactura = txtNumeroDocumentoBusc.getText();
            IU_Emisión_Notas_Credito_Devolución dev = 
                    new IU_Emisión_Notas_Credito_Devolución(usuario, cabecEs.getId(), cabecEs, 
                                                            cabecEs.getClientes(), fechaEmision,
                                                            tipoDocumento,
                                                            nroFactura,
                                                            "Devolución de Mercadería",
                                                            NC_DEVOLUCION);
            dev.setVisible(true);
        }
    }//GEN-LAST:event_btnDevolucionActionPerformed
   
    
    private void btnDescEspecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescEspecialActionPerformed
        if ( txtNumeroDocumentoBusc.getText().equals("") ) {
            JOptionPane.showMessageDialog(null, "Ingrese el Número de Documento", "Error", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
            IU_Emisión_Notas_Credito_Desc_Especial dev = new IU_Emisión_Notas_Credito_Desc_Especial(usuario, cabecEs.getId(), cabecEs, 
                                                                                                    cabecEs.getClientes(),
                                                                                                    txtFechaEmision.getText(),
                                                                                                    NC_DSCTO_ESPECIAL);
            dev.setVisible(true);
        }
    }//GEN-LAST:event_btnDescEspecialActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if ( txtNumeroDocumentoBusc.getText().equals("") ) {
            JOptionPane.showMessageDialog(null, "Ingrese el Número de Documento", "Error", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
            cabecEs = new Cabeces();
            String tipoDocumento = "";
            
            if ( combBoxTipoDoc.getSelectedIndex() == 0 ) {
                tipoDocumento = "01";
                
            } else {
                tipoDocumento = "02";
            }
            cabecEs = servicio_Documentos.obtenerCabeceraPorDocumento(txtNumeroDocumentoBusc.getText(), tipoDocumento);

            if ( cabecEs == null ) {
                JOptionPane.showMessageDialog(null, "No existe ese número de documento", "Error", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                // En BD 1 = Soles, 2 = Dolares
                String glosaMoneda = ( "1".equals(cabecEs.getMoneda()) ? " (S/.):": " (US$):");
                lblValIGV.setText("Valor de I.G.V" + glosaMoneda);
                txtIGV.setText("" + cabecEs.getIgv());
                
                if ( cabecEs.getClientes() != null ) {
                    System.out.println("cabecEs:" + cabecEs);
                    System.out.println("cabecEs.getCClientes():" + cabecEs.getClientes());
                    System.out.println("cabecEs.getClientes().getDireccion():" + cabecEs.getClientes().getDireccion());
                    txtDireccionCliente.setText(cabecEs.getClientes().getDireccion());
                    txtNombreCliente.setText(cabecEs.getClientes().getNombre());
                    txtRUC.setText(cabecEs.getClientes().getRuc());
                }
                Date fecha = cabecEs.getTipocambio().getFecha();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                txtFechaEmision.setText(sdf.format(fecha));
                txtNumeroDocumento.setText(txtNumeroDocumentoBusc.getText());
                txtTipoDocumento.setText((String) combBoxTipoDoc.getSelectedItem());
                txtTotal.setText("" + cabecEs.getTotal());
                lblValTot.setText("Valor Total" + glosaMoneda);
                txtValorVenta.setText("" + cabecEs.getValorventa());
                lblValVenta.setText("Valor de Venta" + glosaMoneda);
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtNumeroDocumentoBuscKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroDocumentoBuscKeyTyped
        int cod = (int) evt.getKeyChar();
        
        if ( cod == 10 ) {
            btnBuscar.doClick();
            btnDevolucion.requestFocus();
        }
    }//GEN-LAST:event_txtNumeroDocumentoBuscKeyTyped

    private void btnGarantiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGarantiaActionPerformed
        if ( txtNumeroDocumentoBusc.getText().equals("") ) {
            JOptionPane.showMessageDialog(null, "Ingrese el Número de Documento", 
                                          "Error", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
            String fechaEmision = txtFechaEmision.getText();
            String tipoDocumento = String.valueOf(combBoxTipoDoc.getSelectedItem());
            String nroFactura = txtNumeroDocumentoBusc.getText();
            IU_Emisión_Notas_Credito_Devolución dev = 
                    new IU_Emisión_Notas_Credito_Devolución(usuario, cabecEs.getId(), cabecEs, 
                                                            cabecEs.getClientes(), fechaEmision,
                                                            tipoDocumento,
                                                            nroFactura,
                                                            "Garantía",
                                                            NC_DEVOLUCION);
            dev.setVisible(true);
        }
    }//GEN-LAST:event_btnGarantiaActionPerformed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDescEspecial;
    private javax.swing.JButton btnDevolucion;
    private javax.swing.JButton btnGarantia;
    private javax.swing.JComboBox combBoxTipoDoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblValIGV;
    private javax.swing.JLabel lblValTot;
    private javax.swing.JLabel lblValVenta;
    public javax.swing.JPanel panelEmisionNotasCredito;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtFechaEmision;
    private javax.swing.JTextField txtIGV;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNumeroDocumento;
    private javax.swing.JTextField txtNumeroDocumentoBusc;
    private javax.swing.JTextField txtRUC;
    private javax.swing.JTextField txtTipoDocumento;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtValorVenta;
    // End of variables declaration//GEN-END:variables
}