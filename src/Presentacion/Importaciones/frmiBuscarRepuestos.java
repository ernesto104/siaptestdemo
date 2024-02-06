package Presentacion.Importaciones;
import Entidades.Importaciones.OperacionesTabla;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import Servicios.Importaciones.FullSelectorListener;
public class frmiBuscarRepuestos extends javax.swing.JFrame {
    static private FREP027 maestro;
    private int limNumParte;
    private int limCodSec;
    private int limDescripcion;
    public frmiBuscarRepuestos(FREP027 m) {
        initComponents();
        limNumParte=17;
        limCodSec=10;
        limDescripcion=40;
        maestro=m;
        maestro.setEnabled(false);
        txtNumParte.grabFocus();
        txtCodSec.setEnabled(false);
        txtDescripcion.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgTipoBusqueda = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbNumParte = new javax.swing.JRadioButton();
        rbCodSec = new javax.swing.JRadioButton();
        btnSalir = new javax.swing.JButton();
        txtNumParte = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        txtCodSec = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        rbDescripcion = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("BUSCAR REPUESTOS");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bgTipoBusqueda.add(rbNumParte);
        rbNumParte.setSelected(true);
        rbNumParte.setText("NUMERO DE PARTE");
        rbNumParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNumParteActionPerformed(evt);
            }
        });

        bgTipoBusqueda.add(rbCodSec);
        rbCodSec.setText("COD. SECUNDARIO");
        rbCodSec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCodSecActionPerformed(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        txtNumParte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumParteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumParteKeyTyped(evt);
            }
        });

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtCodSec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodSecKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodSecKeyTyped(evt);
            }
        });

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        bgTipoBusqueda.add(rbDescripcion);
        rbDescripcion.setText("DESCRIPCION");
        rbDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDescripcionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbNumParte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumParte, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rbCodSec)
                                .addComponent(rbDescripcion)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodSec)
                                    .addComponent(txtDescripcion)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)))))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbNumParte)
                    .addComponent(txtNumParte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbCodSec)
                    .addComponent(txtCodSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbDescripcion)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
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
        maestro.setEnabled(true);
        dispose();
    }//GEN-LAST:event_formWindowClosing
    private void rbNumParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNumParteActionPerformed
        txtNumParte.setEnabled(true);
        txtCodSec.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtCodSec.setText("");
        txtDescripcion.setText("");
        txtNumParte.grabFocus();
    }//GEN-LAST:event_rbNumParteActionPerformed
    private void rbCodSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCodSecActionPerformed
        System.out.println("aquii");
        txtNumParte.setEnabled(false);
        txtCodSec.setEnabled(true);
        txtDescripcion.setEnabled(false);
        txtNumParte.setText("");
        txtDescripcion.setText("");
        txtCodSec.grabFocus();
    }//GEN-LAST:event_rbCodSecActionPerformed
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        maestro.setEnabled(true);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private void txtNumParteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumParteKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            buscar();
        }
    }//GEN-LAST:event_txtNumParteKeyPressed
    private void txtNumParteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumParteKeyTyped
        if (txtNumParte.getText().length()== limNumParte){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtNumParteKeyTyped
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed
    private void txtCodSecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodSecKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            buscar();
        }
    }//GEN-LAST:event_txtCodSecKeyPressed
    private void txtCodSecKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodSecKeyTyped
        if (txtCodSec.getText().length()== limCodSec){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtCodSecKeyTyped
    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            buscar();
        }
    }//GEN-LAST:event_txtDescripcionKeyPressed
    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        if (txtDescripcion.getText().length()== limDescripcion){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtDescripcionKeyTyped
    private void rbDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDescripcionActionPerformed
        txtNumParte.setEnabled(false);
        txtCodSec.setEnabled(false);
        txtDescripcion.setEnabled(true);
        txtNumParte.setText("");
        txtCodSec.setText("");
        txtDescripcion.grabFocus();
    }//GEN-LAST:event_rbDescripcionActionPerformed
    public void buscar(){
        OperacionesTabla op=new OperacionesTabla(maestro);
        op.limpiarTabla();
        String valor="";
        JOptionPane jop = new JOptionPane();
        int opc=0;
        if(rbNumParte.isSelected()){
            valor=txtNumParte.getText();
            if(!valor.equals("")){
                opc=1;
                if(op.insertarRepuestosATabla(valor,opc)>0){
                    maestro.setEnabled(true);
                    dispose();
                }else{
                    jop.showMessageDialog(null, "No se encontraron repuestos con el Nº Parte "+valor+" en la BD",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
                    txtNumParte.grabFocus();
                    txtNumParte.addFocusListener( new FullSelectorListener() );
                }
            }else{
                jop.showMessageDialog(null, "Ingrese el Nº Parte del repuesto","ERROR", JOptionPane.ERROR_MESSAGE);
                txtNumParte.grabFocus();
            }
        }else if(rbCodSec.isSelected()){
            valor=txtCodSec.getText();
            if(!valor.equals("")){
                opc=2;
                if(op.insertarRepuestosATabla(valor,opc)>0){
                    maestro.setEnabled(true);
                    dispose();
                }else{
                    jop.showMessageDialog(null, "No se encontraron repuestos con código secundario "+valor+" en la BD",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
                    txtCodSec.grabFocus();
                    txtCodSec.addFocusListener( new FullSelectorListener() );
                }
            }else{
                jop.showMessageDialog(null, "Ingrese el código secundario del repuesto","ERROR", JOptionPane.ERROR_MESSAGE);
                txtCodSec.grabFocus();
            }
        }else if(rbDescripcion.isSelected()){
            valor=txtDescripcion.getText();
            if(!valor.equals("")){
                opc=3;
                if(op.insertarRepuestosATabla(valor,opc)>0){
                    maestro.setEnabled(true);
                    dispose();
                }else{
                    jop.showMessageDialog(null, "No se encontraron repuestos con descripción "+valor+" en la BD",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
                    txtDescripcion.grabFocus();
                    txtDescripcion.addFocusListener( new FullSelectorListener() );
                }
            }else{
                jop.showMessageDialog(null, "Ingrese la descripción del repuesto","ERROR", JOptionPane.ERROR_MESSAGE);
                txtDescripcion.grabFocus();
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgTipoBusqueda;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbCodSec;
    private javax.swing.JRadioButton rbDescripcion;
    private javax.swing.JRadioButton rbNumParte;
    private javax.swing.JTextField txtCodSec;
    private javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtNumParte;
    // End of variables declaration//GEN-END:variables
}