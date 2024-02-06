package Presentacion.ActualizarPrecios;
import Entidades.Equipos;
import Servicios.Importaciones.Servicio_Repuestos;
import Servicios.Importaciones.Servicio_Equipos;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;

public class FREP031 extends javax.swing.JFrame {
    private int tipoFiltro;
    private String valorFiltro;
    
    public FREP031() {
        initComponents();
        cargarLineas();
    }
    
    private void cargarLineas() {       
        Servicio_Equipos sl = new Servicio_Equipos();
        List lista = sl.listar_Equipos();
        Iterator ite = lista.iterator();
        
        while ( ite.hasNext() ) {
            Equipos lin = (Equipos)ite.next();
            cboLineas.addItem(lin.getIdequipo() + " - " + lin.getDescripcion());
        }
        tipoFiltro = 1;
        valorFiltro = "1";
        cboMarcas.setEnabled(false);
    }
    
    private void cargarMarcas() {
        Servicio_Repuestos sr = new Servicio_Repuestos();
        List lista = sr.obtenerMarcas();
        Iterator iteRep = lista.iterator(); 
        
        while ( iteRep.hasNext() ) {
            Object objRep = (Object) iteRep.next();
            cboMarcas.addItem(objRep);
        }
        tipoFiltro = 2;
    }
    
    public int getTipoFiltro(){
        return tipoFiltro;
    }
    
    public String getValorFiltro(){
        return valorFiltro;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrupo = new javax.swing.ButtonGroup();
        panelActualizarPrecios = new javax.swing.JPanel();
        rbLinea = new javax.swing.JRadioButton();
        rbMarca = new javax.swing.JRadioButton();
        cboLineas = new javax.swing.JComboBox();
        btnFiltrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        cboMarcas = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FILTRAR REPUESTOS PARA ACTUALIZAR");

        btnGrupo.add(rbLinea);
        rbLinea.setSelected(true);
        rbLinea.setText("LINEA");
        rbLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLineaActionPerformed(evt);
            }
        });

        btnGrupo.add(rbMarca);
        rbMarca.setText("MARCA");
        rbMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMarcaActionPerformed(evt);
            }
        });

        btnFiltrar.setText("FILTRAR");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        btnFiltrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnFiltrarKeyPressed(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelActualizarPreciosLayout = new javax.swing.GroupLayout(panelActualizarPrecios);
        panelActualizarPrecios.setLayout(panelActualizarPreciosLayout);
        panelActualizarPreciosLayout.setHorizontalGroup(
            panelActualizarPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActualizarPreciosLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(panelActualizarPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelActualizarPreciosLayout.createSequentialGroup()
                        .addGroup(panelActualizarPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rbMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelActualizarPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboLineas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelActualizarPreciosLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        panelActualizarPreciosLayout.setVerticalGroup(
            panelActualizarPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelActualizarPreciosLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(panelActualizarPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbLinea)
                    .addComponent(cboLineas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(panelActualizarPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbMarca)
                    .addComponent(cboMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelActualizarPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelActualizarPrecios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelActualizarPrecios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        filtrar();
    }//GEN-LAST:event_btnFiltrarActionPerformed
    private void rbMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMarcaActionPerformed
        cboLineas.setEnabled(false);
        cboMarcas.setEnabled(true);
        cargarMarcas();
    }//GEN-LAST:event_rbMarcaActionPerformed
    private void rbLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLineaActionPerformed
        cboLineas.setEnabled(true);
        cboMarcas.setEnabled(false);
        cargarLineas();
    }//GEN-LAST:event_rbLineaActionPerformed
    
    private void filtrar() {
        if ( rbLinea.isSelected() ) {
            tipoFiltro = 1;
            
        } else if ( rbMarca.isSelected() ) {
            tipoFiltro = 2;
        }
        
        boolean validar = false;
        if ( tipoFiltro == 1 ) {
            valorFiltro = String.valueOf(cboLineas.getSelectedItem().toString().substring(0,2));
            
            if ( cboLineas.getSelectedIndex() != -1 ) {
                validar = true;
            }
        } else if ( tipoFiltro == 2 ) {
            valorFiltro = String.valueOf(cboMarcas.getSelectedItem());
            
            if ( cboMarcas.getSelectedIndex() != -1 ) {
                validar = true;
            }
        }
        
        if( validar ) {
            IU_MantenimientoRepuestos actPrice = new IU_MantenimientoRepuestos(this);
            actPrice.setVisible(true);
        }
    }
    
    private void btnFiltrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFiltrarKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            filtrar();
        }
    }//GEN-LAST:event_btnFiltrarKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.ButtonGroup btnGrupo;
    public javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cboLineas;
    private javax.swing.JComboBox cboMarcas;
    public javax.swing.JPanel panelActualizarPrecios;
    private javax.swing.JRadioButton rbLinea;
    private javax.swing.JRadioButton rbMarca;
    // End of variables declaration//GEN-END:variables
}