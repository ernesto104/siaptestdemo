package Presentacion.Notas;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Clientes;
import Entidades.Control;
import Entidades.Detallees;
import static Entidades.Otros.Constante.NOTA_CREDITO;
import Entidades.Repuestos;
import Entidades.Usuarios;
import Mantenimiento.ControlDAO;
import Servicios.Servicio_DetalleEs;
import Servicios.Servicio_Repuesto;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import static Entidades.Otros.Constante.COLUMNA_CANT_INI;
import static Entidades.Otros.Constante.COLUMNA_VV_INI;

/**
 *
 * @author Lesly Aguilar
 * @author modified: Ledis Rivera Ch.
 */
public final class IU_Emisión_Notas_Credito_Devolución extends javax.swing.JFrame {

    Cabeces cabeces;
    CabecesId cabecesId;
    Servicio_DetalleEs servicioDetalleEs;
    Servicio_Repuesto servicioRepuesto;
    private DefaultTableModel modelo;
    Detallees detalleSeleccionado;
    String NParte;
    String cantidad;
    String descripcion;
    String valorTotal;
    int i = 0;
    Double cantAnts;
    Double valorTotAnts;
    Double totalNota;
    ControlDAO controlDAO;
    int nroLineaNotaCred;
    Clientes clientes;
    TablaNotas4Columnas tabla;
    double precioUnitario;
    int cantidAntes;
    Usuarios usuario;
    
    String fechaEmision;
    String tipoDocumento;
    String nroFactura;
    
    String concepto;
    int modalidadNC;
    
    public IU_Emisión_Notas_Credito_Devolución(Usuarios u, CabecesId cabecesId, Cabeces cabeces, Clientes cliente, String fechaEmision,
                                               String tipoDocumento, String nroFactura, String concepto,
                                               int modalidadNC) {
        this.modalidadNC = modalidadNC;
        this.concepto = concepto;
        this.totalNota = 0.0;
        this.tipoDocumento = tipoDocumento;
        this.nroFactura = nroFactura;
        tabla = new TablaNotas4Columnas(this);
        initComponents();
        this.setLocationRelativeTo(null);
        this.cabecesId = cabecesId;
        this.cabeces = cabeces;
        usuario = u;
        
        servicioDetalleEs = new Servicio_DetalleEs(tblDetalleFactura);
        servicioRepuesto = new Servicio_Repuesto();
        detalleSeleccionado = new Detallees();

        modelo = (DefaultTableModel) tblDetalleFactura.getModel();
        ((JLabel) tblDetalleFactura.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        this.clientes = cliente;

        iniciar();
        this.fechaEmision = fechaEmision;
        alinearColumnaDerecha();
        
        setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(false);
            }
        });
        ocultarColumnas();
    }
    
    private void ocultarColumnas() {
        ocultarColumna(COLUMNA_CANT_INI);
        ocultarColumna(COLUMNA_VV_INI);
    }
    
    private void ocultarColumna(int columna) {
        tblNotaCredito.getColumnModel().getColumn(columna).setMaxWidth(0);
        tblNotaCredito.getColumnModel().getColumn(columna).setMinWidth(0);
        tblNotaCredito.getColumnModel().getColumn(columna).setPreferredWidth(0);
    }
    
    private void alinearColumnaDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tblDetalleFactura.getColumnModel().getColumn(6).setCellRenderer(tcr); // Valor Total
        tblNotaCredito.getColumnModel().getColumn(3).setCellRenderer(tcr); // Valor de Venta
    }

    public int getCantidAntes() {
        return cantidAntes;
    }

    public void setCantidAntes(int cantidAntes) {
        this.cantidAntes = cantidAntes;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void iniciar() {
        txtTotalDetalleFactura.setEditable(false);
        txtTotalNotaCredito.setEditable(false);
        txtTipoDocumento.setEditable(false);
        txtNumeroDocumento.setEditable(false);

        txtNumeroDocumento.setText("" + cabecesId.getNrodocumento());

        if ( cabecesId.getTipodoc().equals("01") ) {
            txtTipoDocumento.setText("Factura");
            
        } else {
            if ( cabecesId.getTipodoc().equals("02") ) {
                txtTipoDocumento.setText("Boleta");
            }
        }

        // Listar detalle de Factura
        servicioDetalleEs.Listar_DetalleEs(modelo, cabecesId);
        
        String glosaMoneda = ( "1".equals(cabeces.getMoneda()) ? " (S/.):": " (US$):");
        lblTotal.setText(lblTotal.getText() + glosaMoneda);
        txtTotalDetalleFactura.setText("" + tabla.RedondearConCeros(cabeces.getTotal(), 2));
        lblTotalNC.setText(lblTotalNC.getText() + glosaMoneda);

        controlDAO = new ControlDAO(this);
        Control control;
        control = controlDAO.Obtener_Objeto(1);
        nroLineaNotaCred = control.getNrolineanc();
    }

    public TablaNotas4Columnas getTabla() {
        return tabla;
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

    public boolean buscarExistItem(String NParte) {
        boolean existe = false;
        
        if ( TablaVacia() == false ) {
            for ( int i = 0; i < tblNotaCredito.getRowCount(); i++ ) {
                if ( tblNotaCredito.getValueAt(i, 0).equals(NParte) ) {
                    return true;
                }
            }
        }return existe;
    }

        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        panelEmisionNotasCreditoDev = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNumeroDocumento = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleFactura = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        txtTotalDetalleFactura = new javax.swing.JTextField();
        txtTipoDocumento = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnContinuar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNotaCredito = new javax.swing.JTable();
        lblTotalNC = new javax.swing.JLabel();
        txtTotalNotaCredito = new javax.swing.JTextField();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NOTA DE CRÉDITO POR DEVOLUCIÓN");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de Factura"));

        jLabel2.setText("Tipo de Documento:");

        jLabel3.setText("Número de Documento:");

        tblDetalleFactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Linea", "Nº de Parte", "Descripción", "Cantidad", "Dscto. 1", "Dscto. 2", "Valor Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDetalleFactura.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblDetalleFactura.getTableHeader().setReorderingAllowed(false);
        tblDetalleFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleFacturaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblDetalleFacturaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetalleFactura);
        if (tblDetalleFactura.getColumnModel().getColumnCount() > 0) {
            tblDetalleFactura.getColumnModel().getColumn(0).setMinWidth(40);
            tblDetalleFactura.getColumnModel().getColumn(0).setMaxWidth(40);
        }

        lblTotal.setText("Total");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(530, 530, 530)
                        .addComponent(lblTotal)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalDetalleFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(txtTotalDetalleFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Salir");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnContinuar.setText("Continuar");
        btnContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinuarActionPerformed(evt);
            }
        });

        tblNotaCredito.setModel(tabla);
        tblNotaCredito.getTableHeader().setReorderingAllowed(false);
        tblNotaCredito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblNotaCreditoMouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tblNotaCredito);

        lblTotalNC.setText("Total Nota de Crédito");

        javax.swing.GroupLayout panelEmisionNotasCreditoDevLayout = new javax.swing.GroupLayout(panelEmisionNotasCreditoDev);
        panelEmisionNotasCreditoDev.setLayout(panelEmisionNotasCreditoDevLayout);
        panelEmisionNotasCreditoDevLayout.setHorizontalGroup(
            panelEmisionNotasCreditoDevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmisionNotasCreditoDevLayout.createSequentialGroup()
                .addGroup(panelEmisionNotasCreditoDevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmisionNotasCreditoDevLayout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEmisionNotasCreditoDevLayout.createSequentialGroup()
                        .addGap(312, 312, 312)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEmisionNotasCreditoDevLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(panelEmisionNotasCreditoDevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEmisionNotasCreditoDevLayout.createSequentialGroup()
                                .addGroup(panelEmisionNotasCreditoDevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelEmisionNotasCreditoDevLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblTotalNC)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTotalNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3))
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27))
            .addGroup(panelEmisionNotasCreditoDevLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelEmisionNotasCreditoDevLayout.setVerticalGroup(
            panelEmisionNotasCreditoDevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmisionNotasCreditoDevLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelEmisionNotasCreditoDevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmisionNotasCreditoDevLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEmisionNotasCreditoDevLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEmisionNotasCreditoDevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalNC)
                    .addComponent(txtTotalNotaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(panelEmisionNotasCreditoDevLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(panelEmisionNotasCreditoDev);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
//        IU_Emisión_Notas_Credito iu = new IU_Emisión_Notas_Credito(usuario);
//        iu.setVisible(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tblDetalleFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleFacturaMouseClicked
    }//GEN-LAST:event_tblDetalleFacturaMouseClicked

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if ( (tblNotaCredito.getRowCount() == 0) || (nroLineaNotaCred >= tblNotaCredito.getRowCount()) ) {
            tblDetalleFactura.setRowSelectionAllowed(false);
            boolean exist = buscarExistItem(NParte);
            int valor = 0;
            valor = tblDetalleFactura.getSelectedRow();
            
            if ( valor == -1 ) {
                JOptionPane.showMessageDialog(null, "Seleccione el repuesto a añadir", "Error", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                if ( exist == true ) {
                    JOptionPane.showMessageDialog(null, "El repuesto seleccionado ya ha sido añadido", "Error", JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    int cantidadd = (int) tblDetalleFactura.getValueAt(valor,3);
                    String descripcionn = (String) tblDetalleFactura.getValueAt(valor,2);
                    String nroPartee = (String) tblDetalleFactura.getValueAt(valor,1);
//                    double valorVentaa = (double) tblDetalleFactura.getValueAt(valor,6);
                    String valorVentaa = (String) tblDetalleFactura.getValueAt(valor,6);
                    tabla.agregar(cantidadd, descripcionn, nroPartee, valorVentaa);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Se ha excedido el número máximo de filas definido para la Nota de Crédito", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        if ( tblNotaCredito.getRowCount() == 0 ) {
            JOptionPane.showMessageDialog(null, "La tabla de Nota de Crédito esta vacía", "Error", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
            if ( tblNotaCredito.getSelectedRow() == -1 ) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un repuesto a eliminar", "Error", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
                tabla.eliminar(tblNotaCredito.getSelectedRow()+1);  
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinuarActionPerformed
        if ( tblNotaCredito.getRowCount() == 0 ) {
            JOptionPane.showMessageDialog(null, "La tabla de Nota de Crédito esta vacía", "Error", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
            IU_Generar_Notas generarNota = null;
            try {
//                System.out.println("moneda en BD de factura:" + cabeces.getMoneda());
                boolean enDolares = "2".equals(cabeces.getMoneda()) ? true : false;
//                System.out.println("enDolares:::" + enDolares);
                generarNota = new IU_Generar_Notas(usuario, NOTA_CREDITO, clientes, cabeces, null, tabla, enDolares, fechaEmision,
                                                   tipoDocumento, nroFactura, concepto, modalidadNC);
            } catch ( ParseException ex ) {
                Logger.getLogger(IU_Emisión_Notas_Credito_Devolución.class.getName()).log(Level.SEVERE, null, ex);
            }
            generarNota.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnContinuarActionPerformed
    
    private void tblDetalleFacturaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleFacturaMouseReleased
        tblDetalleFactura.setRowSelectionAllowed(true);
        tblNotaCredito.setRowSelectionAllowed(false);

        NParte = "" + tblDetalleFactura.getValueAt(tblDetalleFactura.getSelectedRow(), 1);
        cantidad = "" + tblDetalleFactura.getValueAt(tblDetalleFactura.getSelectedRow(), 3);
        descripcion = "" + tblDetalleFactura.getValueAt(tblDetalleFactura.getSelectedRow(), 2);
        valorTotal = "" + tblDetalleFactura.getValueAt(tblDetalleFactura.getSelectedRow(), 6);
    }//GEN-LAST:event_tblDetalleFacturaMouseReleased

    private void tblNotaCreditoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNotaCreditoMouseReleased
        String nroParte = (String) tblNotaCredito.getValueAt(tblNotaCredito.getSelectedRow(),0);
        int cant = (int) tblNotaCredito.getValueAt(tblNotaCredito.getSelectedRow(), 1);
        this.setCantidAntes(cant);
        Repuestos r = servicioRepuesto.obtenerRepuestoPorNombre(nroParte);
        this.setPrecioUnitario(r.getPreciolista());
    }//GEN-LAST:event_tblNotaCreditoMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnContinuar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalNC;
    public javax.swing.JPanel panelEmisionNotasCreditoDev;
    private javax.swing.JTable tblDetalleFactura;
    public javax.swing.JTable tblNotaCredito;
    private javax.swing.JTextField txtNumeroDocumento;
    private javax.swing.JTextField txtTipoDocumento;
    private javax.swing.JTextField txtTotalDetalleFactura;
    public javax.swing.JTextField txtTotalNotaCredito;
    // End of variables declaration//GEN-END:variables
}