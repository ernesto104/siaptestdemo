package Presentacion.InventarioValorizado;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Keily Mendiolaza
 */
public class FREP036 extends javax.swing.JFrame {

    public boolean bandera;

    public FREP036() {
        initComponents();
        this.setLocationRelativeTo(null);
        valorizacionPorDefecto();
    }
    
    private void valorizacionPorDefecto() {
        cbTipoVal.setSelectedIndex(3);
    }

    public String fechasistema() {
        Date ahora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(ahora);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelInventario = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtdatefecha = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        cbTipoVal = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        rbAlafecha = new javax.swing.JRadioButton();
        rbAunafecha = new javax.swing.JRadioButton();
        btnContinuar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de Valorización", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        txtdatefecha.setDateFormatString("dd-MM-yyyy");
        txtdatefecha.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLabel1.setText("Valorización : ");

        cbTipoVal.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbTipoVal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Costo Unitario", "Ultimo Costo", "Precio Lista", "Costo Unitario + Precio Venta" }));

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText("Seleccione una fecha para mostrar el Inventario de Repuestos:");

        buttonGroup1.add(rbAlafecha);
        rbAlafecha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbAlafecha.setSelected(true);
        rbAlafecha.setText("A LA FECHA");
        rbAlafecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbAlafechaMouseReleased(evt);
            }
        });
        rbAlafecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlafechaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbAunafecha);
        rbAunafecha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbAunafecha.setText("A UNA FECHA ");
        rbAunafecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbAunafechaMouseReleased(evt);
            }
        });
        rbAunafecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAunafechaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbAlafecha)
                    .addComponent(jLabel6)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rbAunafecha)
                        .addGap(32, 32, 32)
                        .addComponent(txtdatefecha, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTipoVal, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel6)
                .addGap(29, 29, 29)
                .addComponent(rbAlafecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbAunafecha)
                    .addComponent(txtdatefecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbTipoVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
        );

        btnContinuar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel2.setText("INVENTARIO VALORIZADO");

        jSeparator1.setForeground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout panelInventarioLayout = new javax.swing.GroupLayout(panelInventario);
        panelInventario.setLayout(panelInventarioLayout);
        panelInventarioLayout.setHorizontalGroup(
            panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInventarioLayout.createSequentialGroup()
                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelInventarioLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelInventarioLayout.createSequentialGroup()
                                .addGap(128, 128, 128)
                                .addComponent(jLabel2))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelInventarioLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        panelInventarioLayout.setVerticalGroup(
            panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInventarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(16, 16, 16)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelInventario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        String TipoFecha = "";
        String Fecha = "";
        String otro = "";
        
        if ( rbAlafecha.isSelected() ) {
            Fecha = fechasistema();
            TipoFecha = "A la fecha";
            String valorizacion = cbTipoVal.getSelectedItem().toString();
            System.out.println("Tipo Fecha 1 : " + TipoFecha);
            IU_MostrarInventario mv = new IU_MostrarInventario(TipoFecha, Fecha, valorizacion, otro);
            mv.setVisible(true);
        } else {
//            System.out.println("A una fecha");
            if ( txtdatefecha.getDate() != null ) {
                Date fe = txtdatefecha.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
                Fecha = sdf.format(fe);
                otro = s.format(fe);
                TipoFecha = "A una fecha";
                String valorizacion = cbTipoVal.getSelectedItem().toString();
                
                System.out.println("Tipo Fecha 2 : " + TipoFecha);                
                IU_MostrarInventario mv = new IU_MostrarInventario(TipoFecha, Fecha, valorizacion, otro);
                mv.setVisible(true);
                
                System.out.println("Tambien aqui....");            
                
            } else {
                JOptionPane.showMessageDialog(null, "Favor Ingresar Fecha", "Fecha no selecccionada", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void rbAunafechaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbAunafechaMouseReleased
        txtdatefecha.setEnabled(true);
    }//GEN-LAST:event_rbAunafechaMouseReleased

    private void rbAlafechaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbAlafechaMouseReleased
        txtdatefecha.setEnabled(false);
    }//GEN-LAST:event_rbAlafechaMouseReleased

    private void rbAunafechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAunafechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbAunafechaActionPerformed

    private void rbAlafechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlafechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbAlafechaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnContinuar;
    public javax.swing.JButton btnSalir;
    public javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox cbTipoVal;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JSeparator jSeparator1;
    public javax.swing.JPanel panelInventario;
    public javax.swing.JRadioButton rbAlafecha;
    public javax.swing.JRadioButton rbAunafecha;
    public com.toedter.calendar.JDateChooser txtdatefecha;
    // End of variables declaration//GEN-END:variables
}