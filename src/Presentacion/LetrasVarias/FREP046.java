package Presentacion.LetrasVarias;

import Entidades.Clientes;
import Servicios.Servicio_Cliente;
import java.util.Iterator;
import javax.swing.DefaultListModel;

/**
 *
 * @author CRS
 */
public class FREP046 extends javax.swing.JFrame {
    protected Servicio_Cliente sc;
    DefaultListModel modelo;
    public FREP046() {
        initComponents();
        sc = new Servicio_Cliente(null);
        Listar_Clientes(sc.get_ListaClientes().iterator());        
        
        grupo.add(rb_RUC);
        grupo.add(rb_nombre);
        rb_nombre.setSelected(true);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo = new javax.swing.ButtonGroup();
        panelGenerarLetrasV = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rb_nombre = new javax.swing.JRadioButton();
        rb_RUC = new javax.swing.JRadioButton();
        tx_nombre = new javax.swing.JTextField();
        tx_ruc = new javax.swing.JTextField();
        bt_buscar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lst_lista = new javax.swing.JList();
        bt_filtrar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda"));

        rb_nombre.setText("Nombre");
        rb_nombre.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb_nombreItemStateChanged(evt);
            }
        });

        rb_RUC.setText("RUC");
        rb_RUC.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rb_RUCItemStateChanged(evt);
            }
        });

        tx_nombre.setEnabled(false);
        tx_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx_nombrekeyTypedEvent(evt);
            }
        });

        tx_ruc.setEnabled(false);
        tx_ruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tx_ruckeyTypedEvent(evt);
            }
        });

        bt_buscar.setText("Buscar");
        bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rb_nombre)
                            .addComponent(rb_RUC))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tx_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_nombre)
                    .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_RUC)
                    .addComponent(tx_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista Clientes"));

        lst_lista.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(lst_lista);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        bt_filtrar.setText("Aceptar Cliente");
        bt_filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_filtrarActionPerformed(evt);
            }
        });

        btn_Salir.setText("Salir");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("GENERACION DE LETRAS VARIAS");

        javax.swing.GroupLayout panelGenerarLetrasVLayout = new javax.swing.GroupLayout(panelGenerarLetrasV);
        panelGenerarLetrasV.setLayout(panelGenerarLetrasVLayout);
        panelGenerarLetrasVLayout.setHorizontalGroup(
            panelGenerarLetrasVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGenerarLetrasVLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGenerarLetrasVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGenerarLetrasVLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGenerarLetrasVLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(131, 131, 131))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGenerarLetrasVLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bt_filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193))
        );
        panelGenerarLetrasVLayout.setVerticalGroup(
            panelGenerarLetrasVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGenerarLetrasVLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(panelGenerarLetrasVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelGenerarLetrasVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelGenerarLetrasV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelGenerarLetrasV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rb_nombreItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb_nombreItemStateChanged
        if(rb_nombre.isSelected())tx_nombre.setEnabled(true);
        else{
            tx_nombre.setText("");
            tx_nombre.setEnabled(false);
        }
    }//GEN-LAST:event_rb_nombreItemStateChanged

    private void rb_RUCItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rb_RUCItemStateChanged
        if(rb_RUC.isSelected())tx_ruc.setEnabled(true);
        else{
            tx_ruc.setText("");
            tx_ruc.setEnabled(false);
        }
    }//GEN-LAST:event_rb_RUCItemStateChanged

    private void tx_nombrekeyTypedEvent(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_nombrekeyTypedEvent
        if(evt.getKeyChar() == 10){
            bt_buscar.doClick();
        }
    }//GEN-LAST:event_tx_nombrekeyTypedEvent

    private void tx_ruckeyTypedEvent(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_ruckeyTypedEvent
        if(evt.getKeyChar() == 10){
            bt_buscar.doClick();
        }
    }//GEN-LAST:event_tx_ruckeyTypedEvent

    private void bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscarActionPerformed
        if(rb_nombre.isSelected()){
            Listar_Clientes(sc.getCliente_Nombre(tx_nombre.getText()).iterator());
        }else{
            Listar_Clientes(sc.getCliente_RUC(tx_ruc.getText()).iterator());
        }
    }//GEN-LAST:event_bt_buscarActionPerformed

    private void bt_filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_filtrarActionPerformed
        if(lst_lista.getSelectedIndex()>=0){
            Clientes c = sc.getxNombre(String.valueOf(lst_lista.getSelectedValue()));
            IU_Canje canje = new IU_Canje(c);
            dispose();
        }
    }//GEN-LAST:event_bt_filtrarActionPerformed
    private void Listar_Clientes(Iterator it) {
        int i = 1;
        modelo = new DefaultListModel();
        while (it.hasNext()) {
            Object[] res = (Object[]) it.next();
            modelo.addElement(res[1]);
        }
        lst_lista.setModel(modelo);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_buscar;
    protected javax.swing.JButton bt_filtrar;
    public javax.swing.JButton btn_Salir;
    public javax.swing.ButtonGroup grupo;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JScrollPane jScrollPane2;
    protected javax.swing.JList lst_lista;
    public javax.swing.JPanel panelGenerarLetrasV;
    public javax.swing.JRadioButton rb_RUC;
    public javax.swing.JRadioButton rb_nombre;
    public javax.swing.JTextField tx_nombre;
    public javax.swing.JTextField tx_ruc;
    // End of variables declaration//GEN-END:variables
}
