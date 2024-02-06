package Presentacion.Importaciones;

import Entidades.Importaciones.BarraProgresoActSug;
import Servicios.Importaciones.Servicio_CabSugerido;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import Servicios.Importaciones.Servicio_DetSugerido;
import Servicios.Importaciones.Servicio_Repuestos;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class IU_ElegirSugerido extends javax.swing.JFrame {
    IU_ActualizarSugerido iuActSugerido;
    static FREP024 menu;
    Servicio_CabSugerido scs;
    int opcion;
    static FREP025 menuImportaciones;
    private BarraProgresoActSug pane;
    
    public IU_ElegirSugerido( FREP024 m, int opc ) {
        initComponents();
        menu = m;
        scs = new Servicio_CabSugerido();
        
        if ( opc == 2 ) {
            scs.listarCabSugeridosParaActualizar(cboCabSug);
        } else {
            scs.listarCabSugeridosOrdenesCompra(cboCabSug);
        }
        
        if ( opc == 3 ) {
            btnAccion.setText("ELIMINAR");
        }
        opcion = opc;
    }
    
    public IU_ElegirSugerido( FREP025 menuImport, int opc ) {
        initComponents();
        menuImportaciones = menuImport;
        scs = new Servicio_CabSugerido();
        opcion = opc;
        btnAccion.setText("IMPRIMIR"); 
        
        if ( opc == 4 || opc == 6 ) {
            this.setTitle("Impresión de Sugerido");
            scs.listarCabSugerido(cboCabSug);
        }
        
        if ( opc == 5 ) {
            this.setTitle("Impresión de Pedido Oficial");
            scs.listarCabSugeridosOrdenesCompra(cboCabSug);
        }
    }
    
    public void actualizar(){
        long time_start, time_end;
        time_start = System.currentTimeMillis();
        String membrete = btnAccion.getText();
        
        if ( cboCabSug.getSelectedIndex() != -1 ) {
            int idCabSug = Integer.parseInt(String.valueOf(cboCabSug.getSelectedItem()));
            
            if ( membrete.equals("ACTUALIZAR") ) {
                JFrame frame = new JFrame( "ACTUALIZACION DE SUGERIDO EN PROGRESO" );
                pane = new BarraProgresoActSug(frame, menu, this, idCabSug);
                frame.setAlwaysOnTop(true);
                frame.setUndecorated(true);
                frame.getContentPane().add(pane, BorderLayout.CENTER );
                frame.setSize(400,150);
                frame.setLocationRelativeTo(null);
                frame.setVisible( true );
                frame.setDefaultCloseOperation(0);
                this.dispose();
                
            } else if ( membrete.equals("ELIMINAR") ) {
                
                if ( JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el sugerido Nº " + idCabSug +
                                                   "?", "Confirmacion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION ) {
                    Servicio_DetSugerido sds = new Servicio_DetSugerido(iuActSugerido);
                    sds.eliminarDetallesSugerido(idCabSug);
                    Servicio_CabSugerido scabs = new Servicio_CabSugerido();
                    
                    if ( scabs.eliminarCabSugerido(idCabSug) ) {
                        JOptionPane jop = new JOptionPane();
                        jop.showMessageDialog(null,"Sugerido eliminado con éxito","EXITO",JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(false);
                    }
                    this.dispose();
                }
                
            } else if ( opcion == 4 ) {
                IU_ImpSugerido printSug = new IU_ImpSugerido(menuImportaciones, idCabSug);
                printSug.setVisible(true);
                this.dispose();
                
            } else if ( opcion == 5 ) {
                IU_ImpPedido printPed = new IU_ImpPedido(menuImportaciones, idCabSug);
                printPed.setVisible(true);
                this.dispose();
                
            } else if ( opcion == 6 ) {
                IU_ImpSugeridoProforma printSugProf = new IU_ImpSugeridoProforma(menuImportaciones,idCabSug);
                printSugProf.setVisible(true);
                this.dispose();
            }
        }
        time_end = System.currentTimeMillis();
        calcularTiempoProceso(time_end, time_start);
    }
    
    private void calcularTiempoProceso(long tiempoF, long tiempoI){
        long milisegundos = tiempoF - tiempoI;        
        milisegundos = milisegundos / 3170;
        long hora = milisegundos / 3600000;
        long restohora = milisegundos % 3600000;
        long minuto = restohora / 60000;
        long restominuto = restohora % 60000;
        long segundo = restominuto / 1000;
        long restosegundo = restominuto % 1000;        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cboCabSug = new javax.swing.JComboBox();
        btnSalir = new javax.swing.JButton();
        btnAccion = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("SELECCIONAR SUGERIDO");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        cboCabSug.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboCabSugKeyPressed(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnAccion.setText("ACTUALIZAR");
        btnAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccionActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("N° SUGERIDO DE IMPORTACIÓN:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(btnAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboCabSug, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboCabSug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAccion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33))
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
        dispose();
    }//GEN-LAST:event_formWindowClosing
    private void cboCabSugKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboCabSugKeyPressed
        if ( evt.getKeyCode() == KeyEvent.VK_ENTER ) {
            actualizar();
        }
    }//GEN-LAST:event_cboCabSugKeyPressed
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private void btnAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccionActionPerformed
        actualizar();
    }//GEN-LAST:event_btnAccionActionPerformed
    public int getNumRegASugerido() {
        int numReg = 0;
        Servicio_Repuestos sr = new Servicio_Repuestos();
        java.util.List listaRep = sr.getRepuestosASugerido();
        
        if ( listaRep != null ) {
            numReg = listaRep.size();
        }
        return numReg;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccion;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cboCabSug;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}