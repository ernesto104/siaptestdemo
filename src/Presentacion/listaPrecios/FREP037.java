package Presentacion.listaPrecios;

import Entidades.Equipos;
import Mantenimiento.EquipoDAO;
import Servicios.Comision.Validar_Mayusculas;
import java.util.List;

/**
 *
 * @author CRS
 */
public class FREP037 extends javax.swing.JFrame {
    int TipoOpcion;
    String opcion;
    int TipoMoneda;
    int TipoStock;
    
    public FREP037() {
        initComponents();
        initOtherComponents();
        ListarEquipos();
        setLocationRelativeTo(null);        
    }

    private void initOtherComponents() {
        grupo_moneda.add(rb_soles);
        grupo_moneda.add(rb_dolares);
        grupo_opciones.add(rb_todo);
        grupo_opciones.add(rb_articulos);
//        grupo_opciones.add(rb_articulos1);        
        grupo_opciones.add(rb_linea);

        rb_dolares.setSelected(true);
        rb_todo.setSelected(true);

        tx_grupoArt.setEnabled(false);
        cb_linea.setEnabled(false);
        tx_grupoArt.setDocument(new Validar_Mayusculas(tx_grupoArt, 20));
        
//        tx_grupoArt1.setEnabled(false);
        cb_linea.setEnabled(false);        
//        tx_grupoArt1.setDocument(new Validar_Mayusculas(tx_grupoArt1, 20));
    }
    private void ListarEquipos(){
        List<Equipos> equipos = new EquipoDAO().Obtener_Lista_Objetos_OrderNombre();
        for ( Equipos l : equipos ) {
            cb_linea.addItem(l.getDescripcion());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo_opciones = new javax.swing.ButtonGroup();
        grupo_moneda = new javax.swing.ButtonGroup();
        panelListaPre = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        rb_linea = new javax.swing.JRadioButton();
        rb_articulos = new javax.swing.JRadioButton();
        rb_todo = new javax.swing.JRadioButton();
        cb_linea = new javax.swing.JComboBox();
        tx_grupoArt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rb_dolares = new javax.swing.JRadioButton();
        rb_soles = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        ch_stock = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        bt_continuar = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccione", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Seleccionar opciones de la Lista de Precios");

        rb_linea.setText("Por Linea");
        rb_linea.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EventoGrupoOpciones(evt);
            }
        });
        rb_linea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_lineaActionPerformed(evt);
            }
        });

        rb_articulos.setText("Grupo de Articulos");
        rb_articulos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EventoGrupoOpciones(evt);
            }
        });
        rb_articulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_articulosActionPerformed(evt);
            }
        });

        rb_todo.setText("Todos");
        rb_todo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EventoGrupoOpciones(evt);
            }
        });
        rb_todo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_todoActionPerformed(evt);
            }
        });

        cb_linea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_lineaActionPerformed(evt);
            }
        });

        tx_grupoArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tx_grupoArtActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tipo de Moneda");

        rb_dolares.setText("Dolares");

        rb_soles.setText("Soles");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Con / Sin Stock");

        ch_stock.setText("Mostrar solo productos con Stock");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rb_dolares)
                        .addGap(18, 18, 18)
                        .addComponent(rb_soles))
                    .addComponent(rb_todo)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ch_stock)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rb_linea)
                            .addComponent(rb_articulos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tx_grupoArt, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cb_linea, javax.swing.GroupLayout.Alignment.LEADING, 0, 197, Short.MAX_VALUE))))
                .addContainerGap(179, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_linea)
                    .addComponent(cb_linea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_articulos)
                    .addComponent(tx_grupoArt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rb_todo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rb_soles)
                    .addComponent(rb_dolares))
                .addGap(26, 26, 26)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_stock)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("OPCIONES DE REPORTE");

        bt_continuar.setText("Continuar");
        bt_continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_continuarActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelListaPreLayout = new javax.swing.GroupLayout(panelListaPre);
        panelListaPre.setLayout(panelListaPreLayout);
        panelListaPreLayout.setHorizontalGroup(
            panelListaPreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaPreLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListaPreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelListaPreLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListaPreLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(panelListaPreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListaPreLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListaPreLayout.createSequentialGroup()
                                .addComponent(bt_continuar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(104, 104, 104)
                                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(118, 118, 118))))))
        );
        panelListaPreLayout.setVerticalGroup(
            panelListaPreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaPreLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(panelListaPreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_continuar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListaPre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelListaPre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EventoGrupoOpciones(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EventoGrupoOpciones
        if ( rb_linea.isSelected() ) {
            cb_linea.setEnabled(true);
            tx_grupoArt.setText("");
            tx_grupoArt.setEnabled(false);
            rb_articulos.setSelected(false);
            rb_todo.setSelected(false);
//            tx_grupoArt1.setText("");
//            tx_grupoArt1.setEnabled(false);
        } else {
            if ( rb_articulos.isSelected() ) {
                rb_linea.setSelected(false);
                tx_grupoArt.setEnabled(true);
                rb_todo.setSelected(false);
                cb_linea.setEnabled(false);
//                tx_grupoArt1.setText("");
//                tx_grupoArt1.setEnabled(false);                
            } else {
//            if ( rb_articulos1.isSelected() ) {                
 //               rb_linea.setSelected(false);
 //               tx_grupoArt1.setEnabled(true);                
//                rb_todo.setSelected(false);                                
//                cb_linea.setEnabled(false);                
//                tx_grupoArt.setText("");
//                tx_grupoArt.setEnabled(false);                

//            }
            }
    }//GEN-LAST:event_EventoGrupoOpciones
    }
    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void bt_continuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_continuarActionPerformed
        if(rb_linea.isSelected()){
            TipoOpcion = 1;
            opcion = String.valueOf(cb_linea.getSelectedItem());
        }else if(rb_articulos.isSelected()){
            TipoOpcion = 2;
            opcion = tx_grupoArt.getText();            
        }else {
//            if(rb_articulos1.isSelected()){
//            TipoOpcion = 3;
//            opcion = tx_grupoArt1.getText();     
//        }else{
            TipoOpcion = 3;
            opcion = "";             
        }
                
        if(rb_dolares.isSelected()){
            TipoMoneda = 1;
        }else{
            TipoMoneda = 2;
        }
        if(ch_stock.isSelected()){
            TipoStock = 1;
        }else{
            TipoStock = 0;
        }
//        System.out.println("TipoOpcion:" + TipoOpcion);
//        System.out.println("opcion:" + opcion);
//        System.out.println("TipoMoneda: " + TipoMoneda);
//        System.out.println("TipoStock:" + TipoStock);
        IU_ListaPrecios lista = new IU_ListaPrecios(TipoOpcion,opcion,TipoMoneda,TipoStock);
        dispose();
    }//GEN-LAST:event_bt_continuarActionPerformed

    private void rb_lineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_lineaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_lineaActionPerformed

    private void rb_todoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_todoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_todoActionPerformed

    private void cb_lineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_lineaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_lineaActionPerformed

    private void tx_grupoArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx_grupoArtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx_grupoArtActionPerformed

    private void rb_articulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_articulosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rb_articulosActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_continuar;
    public javax.swing.JButton bt_salir;
    public javax.swing.JComboBox cb_linea;
    public javax.swing.JCheckBox ch_stock;
    public javax.swing.ButtonGroup grupo_moneda;
    public javax.swing.ButtonGroup grupo_opciones;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel panelListaPre;
    public javax.swing.JRadioButton rb_articulos;
    public javax.swing.JRadioButton rb_dolares;
    public javax.swing.JRadioButton rb_linea;
    public javax.swing.JRadioButton rb_soles;
    public javax.swing.JRadioButton rb_todo;
    public javax.swing.JTextField tx_grupoArt;
    // End of variables declaration//GEN-END:variables
}