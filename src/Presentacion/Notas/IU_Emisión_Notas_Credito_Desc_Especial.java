package Presentacion.Notas;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Clientes;
import Entidades.Control;
import static Entidades.Otros.Constante.NOTA_CREDITO;
import Entidades.Usuarios;
import Mantenimiento.ControlDAO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JViewport;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Lesly Aguilar
 */
public class IU_Emisión_Notas_Credito_Desc_Especial extends javax.swing.JFrame {

    ControlDAO controlDAO;
    int nroLineaNotaCred;
    Cabeces cabeces;
    CabecesId cabecesId;
    private DefaultTableModel modelo;
    double totalNota = 0.0;
    double igv;
    String descripcion;
    String valorTotal;
    int k = 0;
    boolean hayvalor;
    Clientes cliente;
    TablaNotas2Columnas tabla;
    Editor editor;
    Render render;
    Usuarios usuario;
    
    String fechaEmision;
    String concepto;
    int modalidadNC;

    public IU_Emisión_Notas_Credito_Desc_Especial(Usuarios u, CabecesId cabecesId, Cabeces cabeces, Clientes cliente, String fechaEmision,
                                                  int modalidadNC) {
        tabla = new TablaNotas2Columnas(this);
        initComponents();
        this.modalidadNC = modalidadNC;
        this.hayvalor = false;
        txtimportetotal.setText(cabeces.getTotal().toString());
        this.setLocationRelativeTo(null);
        this.cabecesId = cabecesId;
        this.cabeces = cabeces;
        this.cliente = cliente;
        this.usuario = u;
        iniciar();
        this.concepto = "Descuento";
        
        setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(false);
            }
        });
        this.fechaEmision = fechaEmision;
    }

    public void iniciar() {
        txtIGV.setEditable(false);
        txtNroDocumento.setEditable(false);
        txtSubTotal.setEditable(false);
        txtTipoDocumento.setEditable(false);
        txtTotal.setEditable(false);

        txtNroDocumento.setText("" + cabecesId.getNrodocumento());

        if ( cabecesId.getTipodoc().equals("01") ) {
            txtTipoDocumento.setText("Factura");
            
        } else {
            if ( cabecesId.getTipodoc().equals("02") ) {
                txtTipoDocumento.setText("Boleta");
            }
        }

        controlDAO = new ControlDAO(this);
        Control control;
        control = controlDAO.Obtener_Objeto(1);
        nroLineaNotaCred = control.getNrolineanc();
        igv = control.getImpuestoigv();
        setAnchoColumnas();
        tblNotaCredito.setCellSelectionEnabled(true);
//           BorrarTabla();
        tabla.asignarFilas(nroLineaNotaCred);
    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tblNotaCredito.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tblNotaCredito.getColumnModel();
        TableColumn columnaTabla;
        
        for ( int i = 0; i < tblNotaCredito.getColumnCount(); i++ ) {
            columnaTabla = modeloColumna.getColumn(i);
            
            switch ( i ) {
                case 0:
                    anchoColumna = (60 * ancho) / 100;
                    break;
                    
                case 1:
                    anchoColumna = (40 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    public boolean TablaVacia() {
        for ( int i = 0; i < tblNotaCredito.getRowCount(); i++ ) {
            for ( int j = 0; j < tblNotaCredito.getColumnCount(); j++ ) {
                if ( tblNotaCredito.getValueAt(i, j) != null ) {
                    return false;
                }
            }
        }
        return true;
    }

    private void BorrarTabla() {
        int numRows = tblNotaCredito.getRowCount();
        for ( int i = 0; i < numRows; i++ ) {
            tabla.eliminar(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEmisionNotasCreditoDesct = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNroDocumento = new javax.swing.JTextField();
        txtTipoDocumento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtimportetotal = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNotaCredito = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtIGV = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnContinuar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NOTA DE CRÉDITO POR DESCUENTO ESPECIAL");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Documento"));

        jLabel2.setText("Tipo de Documento:");

        jLabel3.setText("Nº de Documento:");

        jLabel7.setText("Importe Total (US$): ");

        txtimportetotal.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtimportetotal))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtNroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtimportetotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Descripción"));

        tblNotaCredito.setModel(tabla);
        tblNotaCredito.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblNotaCredito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNotaCreditoMouseClicked(evt);
            }
        });
        tblNotaCredito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblNotaCreditoKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblNotaCredito);

        jLabel4.setText("Sub-Total (US$) :");

        jLabel5.setText("I.G.V (US$) :");

        jLabel6.setText("Total (US$) :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 423, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Salir");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEmisionNotasCreditoDesctLayout = new javax.swing.GroupLayout(panelEmisionNotasCreditoDesct);
        panelEmisionNotasCreditoDesct.setLayout(panelEmisionNotasCreditoDesctLayout);
        panelEmisionNotasCreditoDesctLayout.setHorizontalGroup(
            panelEmisionNotasCreditoDesctLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmisionNotasCreditoDesctLayout.createSequentialGroup()
                .addGroup(panelEmisionNotasCreditoDesctLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmisionNotasCreditoDesctLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelEmisionNotasCreditoDesctLayout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelEmisionNotasCreditoDesctLayout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelEmisionNotasCreditoDesctLayout.setVerticalGroup(
            panelEmisionNotasCreditoDesctLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmisionNotasCreditoDesctLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 377, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEmisionNotasCreditoDesctLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEmisionNotasCreditoDesct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEmisionNotasCreditoDesct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
//        IU_Emisión_Notas_Credito iu = new IU_Emisión_Notas_Credito(usuario);
//        iu.setVisible(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tblNotaCreditoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblNotaCreditoKeyTyped
        int cod = (int) evt.getKeyChar();
     //   System.out.println("keychar: " + cod);
        if ( cod >= 'a' && cod <= 'z' ) {
            evt.setKeyChar((char) (cod - 32));
        }
    }//GEN-LAST:event_tblNotaCreditoKeyTyped

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        double importe = Double.parseDouble(txtimportetotal.getText());
        double total = Double.parseDouble(txtTotal.getText());
        
        if ( total < importe ) {
            if ( TablaVacia() == true ) {
                JOptionPane.showMessageDialog(null, "La tabla de Nota de Crédito esta vacía", "Error", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                IU_Generar_Notas iu_generar = null;
                try {
//                    System.out.println("tablaaa:" + tabla.getRowCount());   
                    iu_generar = new IU_Generar_Notas(usuario, NOTA_CREDITO, cliente, cabeces, tabla, null, true, fechaEmision, 
                                                      "", "", "Descuento",
                                                      modalidadNC); // Descuento especial
                            // Revisar tipo de documento (en Nota de credito al imprimir), nroFactura
                } catch ( ParseException ex ) {
                    Logger.getLogger(IU_Emisión_Notas_Credito_Desc_Especial.class.getName()).log(Level.SEVERE, null, ex);
                }
                iu_generar.setVisible(true);
                this.setVisible(false);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El monto total no puede ser mayor que el importe", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnContinuarActionPerformed

    private void tblNotaCreditoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNotaCreditoMouseClicked
    }//GEN-LAST:event_tblNotaCreditoMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelEmisionNotasCreditoDesct;
    private javax.swing.JTable tblNotaCredito;
    public javax.swing.JTextField txtIGV;
    private javax.swing.JTextField txtNroDocumento;
    public javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTipoDocumento;
    public javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtimportetotal;
    // End of variables declaration//GEN-END:variables
}