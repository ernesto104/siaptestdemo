package Presentacion.historialMov;

import Servicios.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class IU_FiltrarFecha extends javax.swing.JFrame {

    public IU_FiltrarFecha() {
        initComponents();
        agregarOyente();
        rb_desde.setSelected(true);
        tx_desde.setText(new SimpleDateFormat("dd/MM/yyyy").format(cal_desde.getDate()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rb_desde = new javax.swing.JRadioButton();
        rb_hasta = new javax.swing.JRadioButton();
        cal_hasta = new com.toedter.calendar.JCalendar();
        cal_desde = new com.toedter.calendar.JCalendar();
        tx_hasta = new javax.swing.JTextField();
        tx_desde = new javax.swing.JTextField();
        bt_filtrar = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccionar Rango de Fecha"));

        rb_desde.setText("Desde");
        rb_desde.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb_desdeItemStateChanged(evt);
            }
        });

        rb_hasta.setText("Hasta");
        rb_hasta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb_hastaItemStateChanged(evt);
            }
        });

        cal_hasta.setEnabled(false);

        tx_hasta.setEnabled(false);

        tx_desde.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rb_desde)
                        .addGap(18, 18, 18)
                        .addComponent(tx_desde))
                    .addComponent(cal_desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cal_hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rb_hasta)
                        .addGap(18, 18, 18)
                        .addComponent(tx_hasta, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_desde)
                    .addComponent(rb_hasta)
                    .addComponent(tx_hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cal_hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cal_desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        bt_filtrar.setText("Filtrar");
        bt_filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_filtrarActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(bt_filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(132, 132, 132))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void rb_hastaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb_hastaItemStateChanged
        if (rb_hasta.isSelected()) {
            cal_hasta.setEnabled(true);
            tx_hasta.setText(new SimpleDateFormat("dd/MM/yyyy").format(cal_hasta.getDate()));
        } else {
            cal_hasta.setEnabled(false);
            tx_hasta.setText("");
        }
    }//GEN-LAST:event_rb_hastaItemStateChanged

    private void rb_desdeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb_desdeItemStateChanged
        if (rb_desde.isSelected()) {
            cal_desde.setEnabled(true);
        } else {
            cal_desde.setEnabled(false);
            tx_desde.setText("");
        }
    }//GEN-LAST:event_rb_desdeItemStateChanged

    private void bt_filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_filtrarActionPerformed
        if (tx_desde.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Debe elegir una fecha de inicio");
        } else {
            Date inicio = cal_desde.getDate();
            Date fin = cal_hasta.getDate();
            
            if(!rb_hasta.isSelected()){
                IU_SeleccionRepuesto selRep = new IU_SeleccionRepuesto();
                selRep.setLocationRelativeTo(null);
                selRep.setVisible(true);
                selRep.setFechaInicio(inicio);
                dispose();
            }else{
                System.out.println(inicio.compareTo(fin));
                if(inicio.compareTo(fin)<0 || CompararFechas(inicio, fin)==0){
                    IU_SeleccionRepuesto selRep = new IU_SeleccionRepuesto();
                    selRep.setLocationRelativeTo(null);
                    selRep.setVisible(true);
                    selRep.setFechaInicio(inicio);
                    selRep.setFechaFin(fin);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "La fecha Final debe ser mayor que la Inicial");
                }
            }
        }
    }//GEN-LAST:event_bt_filtrarActionPerformed

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed
   
    private int CompararFechas(Date inicio,Date Fin){
        Util util = new Util();
        int Dia_Inicio=util.getDia(inicio);
        int Mes_Inicio=util.getMes(inicio);
        int Anio_Inicio=util.getAnio(inicio);
        int Dia_Fin=util.getDia(Fin);
        int Mes_Fin=util.getMes(Fin);
        int Anio_Fin=util.getAnio(Fin);
        
        if(Dia_Inicio==Dia_Fin && Mes_Inicio==Mes_Fin && Anio_Fin == Anio_Inicio)return 0;
        else return -1;
        
    }
    
    
    private void agregarOyente() {
        cal_desde.getDayChooser().addPropertyChangeListener(
                new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if (evt.getPropertyName().compareTo("day") == 0) {
                    SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                    tx_desde.setText(formatoDeFecha.format(cal_desde.getDate()));
                }
            }
        });
        cal_hasta.getDayChooser().addPropertyChangeListener(
                new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if (evt.getPropertyName().compareTo("day") == 0) {
                    SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                    tx_hasta.setText(formatoDeFecha.format(cal_hasta.getDate()));
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_filtrar;
    private javax.swing.JButton bt_salir;
    private com.toedter.calendar.JCalendar cal_desde;
    private com.toedter.calendar.JCalendar cal_hasta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton rb_desde;
    private javax.swing.JRadioButton rb_hasta;
    private javax.swing.JTextField tx_desde;
    private javax.swing.JTextField tx_hasta;
    // End of variables declaration//GEN-END:variables
}
