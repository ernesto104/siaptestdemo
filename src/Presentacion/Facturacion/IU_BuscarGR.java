package Presentacion.Facturacion;

import Entidades.Cabeces;
import Entidades.Detallees;
import Entidades.Usuarios;
import Servicios.Servicio_BuscarGR;
import Servicios.facturacion.Servicio_Documentos;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class IU_BuscarGR extends javax.swing.JFrame {

    private Servicio_BuscarGR servicio;
    private String tipoSelec;
    private DateFormat df;
    Usuarios u;
    private Servicio_Documentos servicioDoc;

    public IU_BuscarGR(Usuarios u) {
        initComponents();
//        System.out.println("IU_BuscarGR iniciando...");
        servicio = new Servicio_BuscarGR(tab_GuiaRemision);
        df = new SimpleDateFormat("dd/MM/yyyy");
        setCombo();
        this.u = u;
        servicioDoc = new Servicio_Documentos();
    }

    private void setCombo() {
        cb_Tipo.addItem("Guía de Remisión");
        cb_Tipo.addItem("Razón Social");
        cb_Tipo.addItem("Fecha Emisión");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cb_Tipo = new javax.swing.JComboBox();
        btn_Buscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tab_GuiaRemision = new javax.swing.JTable();
        btn_Aceptar = new javax.swing.JButton();
        btn_Cancelar = new javax.swing.JButton();
        txt_Buscar = new javax.swing.JTextField();
        btn_Fecha = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Tipo de búsqueda:");

        cb_Tipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_TipoItemStateChanged(evt);
            }
        });

        btn_Buscar.setText("Buscar");
        btn_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BuscarActionPerformed(evt);
            }
        });

        tab_GuiaRemision.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tab_GuiaRemision);

        btn_Aceptar.setText("Aceptar");
        btn_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AceptarActionPerformed(evt);
            }
        });

        btn_Cancelar.setText("Salir");
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarActionPerformed(evt);
            }
        });

        btn_Fecha.setText("Fecha");
        btn_Fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FechaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cb_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
                        .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(297, 297, 297)
                .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122)
                .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cb_Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb_TipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_TipoItemStateChanged
        if ( evt.getStateChange() == ItemEvent.SELECTED ) {
            tipoSelec = (String) evt.getItem();
            if ( tipoSelec.equals("Fecha Emisión") ) {
                txt_Buscar.setEnabled(false);
                btn_Fecha.setEnabled(true);
                
            } else {
                txt_Buscar.setEnabled(true);
                btn_Fecha.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cb_TipoItemStateChanged

    private void btn_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BuscarActionPerformed
        String cadena = txt_Buscar.getText();
        switch ( tipoSelec ) {
            case "Guía de Remisión":
                try {
                    Integer i = Integer.valueOf(cadena);
                    System.out.println("i:" + i);
                    servicio.filtraTabla(0, i);
                } catch(NumberFormatException e) { }
                break;
            case "Razón Social":
                servicio.filtraTabla(1, cadena);
                break;
            case "Fecha Emisión":
                servicio.filtraTabla(2, cadena);
                break;
        }
    }//GEN-LAST:event_btn_BuscarActionPerformed

    private void btn_FechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FechaActionPerformed
        final JDateChooser dc = new JDateChooser();
        dc.setCalendar(new GregorianCalendar());
        JFrame fechaFrame = new JFrame();
        fechaFrame.add(dc.getJCalendar());
        fechaFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(df.format(dc.getCalendar().getTime()));
                txt_Buscar.setText(df.format(dc.getDate()));
            }
        });
        fechaFrame.pack();
        fechaFrame.setLocationRelativeTo(null);
        fechaFrame.setVisible(true);
    }//GEN-LAST:event_btn_FechaActionPerformed

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        Cabeces cGR = servicio.procesar();
        System.out.println("En IU_BuscarGR.java....");
//        System.out.println("> tipotra : " + cGR.getId().getTipotra());
//        System.out.println("> tipodoc : " + cGR.getId().getTipodoc());
//        System.out.println("> nroSerie : " + cGR.getId().getNrorserie());
//        System.out.println("> nroDoc : " + cGR.getId().getNrodocumento());
//        System.out.println("> N° Guia:" + cGR.getNroguia());
        
        // Obtener Detallees de la Cabeces generada a partir de GR (x consinación)
        List<Detallees> lstDGR = servicioDoc.getDetalleGuia(cGR);
        System.out.println("dGR:" + lstDGR);
//        System.out.println("N° elementos :" + lstDGR.size());
        
        if ( cGR == null ) {
            JOptionPane.showMessageDialog(this, "Complete los campos");
            
        } else {
            IU_Facturacion iu = new IU_Facturacion(cGR, lstDGR);
//            iu.setUsuario(u);
            iu.setLocationRelativeTo(null);
            iu.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_btn_AceptarActionPerformed

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btn_CancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Aceptar;
    private javax.swing.JButton btn_Buscar;
    private javax.swing.JButton btn_Cancelar;
    private javax.swing.JButton btn_Fecha;
    private javax.swing.JComboBox cb_Tipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tab_GuiaRemision;
    private javax.swing.JTextField txt_Buscar;
    // End of variables declaration//GEN-END:variables
}
