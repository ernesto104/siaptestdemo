package Presentacion.Repuestos;

import Entidades.Equipos;
import Servicios.Servicio_Equipos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author Lesly Aguilar
 */
public class IU_Repuestos_Filtro extends javax.swing.JFrame {

    private DefaultComboBoxModel modelComboLineas;
    Servicio_Equipos servicioLineas;
    List<Equipos> listaLin;
    
    public IU_Repuestos_Filtro() {
        initComponents();
        this.setLocationRelativeTo(null);
       
        ListarEquipos();
        
        setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(false);
            }
        });

    }

    public void ListarEquipos() {
        modelComboLineas = new DefaultComboBoxModel();
        servicioLineas = new Servicio_Equipos(null);
        listaLin = servicioLineas.listar_Equipos();
        for (int i = 0; i < listaLin.size(); i++) {
            //        modelTranspCombo.addElement(listaTrans.get(i));
            modelComboLineas.addElement(listaLin.get(i).getIdequipo());

        }
        listaLinea.setModel(modelComboLineas);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        combBoxMarca = new javax.swing.JRadioButton();
        txtMarca = new javax.swing.JTextField();
        combBoxGrupoArticulos = new javax.swing.JRadioButton();
        txtGrupoArticulo = new javax.swing.JTextField();
        combBoxLinea = new javax.swing.JRadioButton();
        listaLinea = new javax.swing.JComboBox();
        btnFiltrar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("FILTRO DE REPUESTOS");

        buttonGroup1.add(combBoxMarca);
        combBoxMarca.setSelected(true);
        combBoxMarca.setText("Marca");
        combBoxMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combBoxMarcaMouseClicked(evt);
            }
        });

        buttonGroup1.add(combBoxGrupoArticulos);
        combBoxGrupoArticulos.setText("Grupo de Articulos");
        combBoxGrupoArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combBoxGrupoArticulosMouseClicked(evt);
            }
        });

        buttonGroup1.add(combBoxLinea);
        combBoxLinea.setText("Linea");
        combBoxLinea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combBoxLineaMouseClicked(evt);
            }
        });

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(combBoxGrupoArticulos)
                                    .addComponent(combBoxMarca)
                                    .addComponent(combBoxLinea)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMarca)
                                .addComponent(txtGrupoArticulo, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                .addComponent(listaLinea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel1)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combBoxMarca)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combBoxGrupoArticulos)
                    .addComponent(txtGrupoArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combBoxLinea)
                    .addComponent(listaLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed

        if (combBoxGrupoArticulos.isSelected() == false && combBoxLinea.isSelected() == false && combBoxMarca.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Seleccione una opción", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
        } else {

            //Grupo Articulos
            if (combBoxGrupoArticulos.isSelected()) {
                if (txtGrupoArticulo.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Ingrese grupo de articulo", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                } else {
                     IU_Repuestos_Ver iu = new IU_Repuestos_Ver("",txtGrupoArticulo.getText(),0);
                     iu.setVisible(true);
                     this.setVisible(false);
                }

            } else {
                //Lineas
                if (combBoxLinea.isSelected()) {
                     IU_Repuestos_Ver iu = new IU_Repuestos_Ver("","",(int)listaLinea.getSelectedItem());
                     iu.setVisible(true);
                     this.setVisible(false);
                }
                //Marca
                else {
                    if (txtMarca.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Ingrese marca", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        IU_Repuestos_Ver iu = new IU_Repuestos_Ver(txtMarca.getText(), "",0);
                        iu.setVisible(true);
                        this.setVisible(false);
                    }
                }
            }
        }

    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void combBoxMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combBoxMarcaMouseClicked
        
    }//GEN-LAST:event_combBoxMarcaMouseClicked

    private void combBoxGrupoArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combBoxGrupoArticulosMouseClicked
        
    }//GEN-LAST:event_combBoxGrupoArticulosMouseClicked

    private void combBoxLineaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combBoxLineaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_combBoxLineaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton combBoxGrupoArticulos;
    private javax.swing.JRadioButton combBoxLinea;
    private javax.swing.JRadioButton combBoxMarca;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox listaLinea;
    private javax.swing.JTextField txtGrupoArticulo;
    private javax.swing.JTextField txtMarca;
    // End of variables declaration//GEN-END:variables
}
