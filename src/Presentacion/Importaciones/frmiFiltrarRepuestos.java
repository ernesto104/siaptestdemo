package Presentacion.Importaciones;
import Entidades.Importaciones.OperacionesTabla;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import Servicios.Importaciones.FullSelectorListener;
public class frmiFiltrarRepuestos extends javax.swing.JFrame {
    static private FREP027 maestro;
    private int limStock;
    private int limMarca;
    public frmiFiltrarRepuestos(FREP027 m) {
        initComponents();
        limMarca=15;
        limStock=6;
        maestro=m;
        maestro.setEnabled(false);
        txtMarca.grabFocus();
        txtStock.setEnabled(false);
    }
    public frmiFiltrarRepuestos() {
        initComponents();
        txtMarca.grabFocus();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgTipoFiltro = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbStock = new javax.swing.JRadioButton();
        txtStock = new javax.swing.JTextField();
        rbMarca = new javax.swing.JRadioButton();
        txtMarca = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        rbTodos = new javax.swing.JRadioButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("FILTRAR REPUESTOS");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bgTipoFiltro.add(rbStock);
        rbStock.setText("POR STOCK");
        rbStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbStockActionPerformed(evt);
            }
        });

        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStockKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockKeyTyped(evt);
            }
        });

        bgTipoFiltro.add(rbMarca);
        rbMarca.setSelected(true);
        rbMarca.setText("POR MARCA");
        rbMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMarcaActionPerformed(evt);
            }
        });

        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMarcaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaKeyTyped(evt);
            }
        });

        btnAceptar.setText("FILTRAR");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        bgTipoFiltro.add(rbTodos);
        rbTodos.setText("TODOS");
        rbTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTodosActionPerformed(evt);
            }
        });
        rbTodos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbTodosKeyPressed(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbStock)
                            .addComponent(rbMarca)
                            .addComponent(rbTodos))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtStock)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbMarca)
                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbStock)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(rbTodos)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
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
    private void filtrar(){
        OperacionesTabla op=new OperacionesTabla(maestro);
        op.limpiarTabla();
        String valor="";
        JOptionPane jop = new JOptionPane();
        int opc=0;
        if(rbMarca.isSelected()){
            valor=txtMarca.getText();
            if(!valor.equals("")){
                opc=4;
                if(op.insertarRepuestosATabla(valor,opc)>0){
                    maestro.setEnabled(true);
                    dispose();
                }else{
                    jop.showMessageDialog(null, "No se encontraron repuestos con la marca "+valor+" en la BD",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
                    txtMarca.grabFocus();
                    txtMarca.addFocusListener( new FullSelectorListener() );
                }
            }else{
                jop.showMessageDialog(null, "Ingrese la marca del repuesto","ERROR", JOptionPane.ERROR_MESSAGE);
                txtMarca.grabFocus();
            }
        }else if(rbStock.isSelected()){
            valor=txtStock.getText();
            if(!valor.equals("")){
                opc=5;
                if(op.insertarRepuestosATabla(valor,opc)>0){
                    maestro.setEnabled(true);
                    dispose();
                }else{
                    jop.showMessageDialog(null, "No se encontraron repuestos con stock de "+valor+" en la BD",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
                    txtStock.grabFocus();
                    txtStock.addFocusListener( new FullSelectorListener() );
                }
            }else{
                jop.showMessageDialog(null, "Ingrese el stock del repuesto","ERROR", JOptionPane.ERROR_MESSAGE);
                txtStock.grabFocus();
            }
        }else if(rbTodos.isSelected()){
            opc=6;
            if(op.insertarRepuestosATabla(valor,opc)>0){
                maestro.setEnabled(true);
                dispose();
            }else{
                jop.showMessageDialog(null, "No hay repuestos registrados",
                "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        maestro.setEnabled(true);
        dispose();
    }//GEN-LAST:event_formWindowClosing
    private void rbStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbStockActionPerformed
        txtMarca.setEnabled(false);
        txtMarca.setText("");
        txtStock.setEnabled(true);
        txtStock.grabFocus();
    }//GEN-LAST:event_rbStockActionPerformed
    private void txtStockKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            filtrar();
        }
    }//GEN-LAST:event_txtStockKeyPressed
    private void txtStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyTyped
        if (txtStock.getText().length()== limStock){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        char caracter = evt.getKeyChar();
        if(((caracter < '0') || (caracter > '9') || (caracter=='.')) && (caracter != '\b')){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtStockKeyTyped
    private void rbMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMarcaActionPerformed
        txtMarca.setEnabled(true);
        txtMarca.grabFocus();
        txtStock.setEnabled(false);
        txtStock.setText("");
    }//GEN-LAST:event_rbMarcaActionPerformed
    private void txtMarcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            filtrar();
        }
    }//GEN-LAST:event_txtMarcaKeyPressed
    private void txtMarcaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyTyped
        if (txtMarca.getText().length()== limMarca){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtMarcaKeyTyped
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        filtrar();
    }//GEN-LAST:event_btnAceptarActionPerformed
    private void rbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTodosActionPerformed
        txtMarca.setEnabled(false);
        txtStock.setEnabled(false);
        txtMarca.setText("");
        txtStock.setText("");
    }//GEN-LAST:event_rbTodosActionPerformed
    private void rbTodosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbTodosKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            filtrar();
        }
    }//GEN-LAST:event_rbTodosKeyPressed
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        maestro.setEnabled(true);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgTipoFiltro;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbMarca;
    private javax.swing.JRadioButton rbStock;
    private javax.swing.JRadioButton rbTodos;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}