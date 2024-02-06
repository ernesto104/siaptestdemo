package Presentacion.Facturacion;

import Entidades.Cabecproformas;
import static Entidades.Otros.Constante.COLUMNA_ID_PROF;
import Entidades.Usuarios;
import Mantenimiento.ControlDAO;
import static Servicios.Constantes.DOLARES;
import static Servicios.Constantes.GLOSA_DOLARES;
import static Servicios.Constantes.GLOSA_SOLES;
import static Servicios.Constantes.SOLES;
import Servicios.TipoMensaje;
import Servicios.facturacion.Servicio_Documentos;
import Servicios.facturacion.Servicio_Impresion;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Servicios.Util;
import java.util.HashMap;
import javax.swing.JTable;

public class IU_FProforma extends javax.swing.JFrame {

    Servicios.facturacion.Servicio_Documentos spr;
    DefaultTableModel tab;
    Cabecproformas cabp;
    Usuarios u;

    TipoMensaje tm;
    IU_EstadoProforma estadoProforma;
    
    IU_FiltrarProformas filtroProformas;
    IU_DatosFacturaProforma datosFacturaProforma;
    
    Cabecproformas cabpSeleccionada;
    
    private String valorVenta;
    
    public IU_FProforma(Usuarios u, String valorVenta) {
        
        this.valorVenta = valorVenta;
        tm = new TipoMensaje();
        
        cabp = null;
        spr = new Servicio_Documentos();
        initComponents();
        tab = (DefaultTableModel) tbProformas.getModel();
        
        List lista = spr.filtrarCabecproformas("PENDIENTE");
//        ch_cliente.setSelected(true);
        Listar(lista);
        alinearColumnaDerecha();
        this.u=u;
        
        bgBusqueda.add(rbCliente);
        bgBusqueda.add(rbNumFactura);
        rbCliente.setSelected(true);
        ocultarColumnaIdCabProforma();
    }
    
    private void ocultarColumnaIdCabProforma() {
        tbProformas.getColumnModel().getColumn(COLUMNA_ID_PROF).setMaxWidth(0);
        tbProformas.getColumnModel().getColumn(COLUMNA_ID_PROF).setMinWidth(0);
        tbProformas.getColumnModel().getColumn(COLUMNA_ID_PROF).setPreferredWidth(0);
    }

    private void alinearColumnaDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
//        tbProformas.getColumnModel().getColumn(5).setCellRenderer(tcr);//3
//        tbProformas.getColumnModel().getColumn(6).setCellRenderer(tcr);//4
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgBusqueda = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        rbCliente = new javax.swing.JRadioButton();
        tx_cliente = new javax.swing.JTextField();
        bt_buscar = new javax.swing.JButton();
        rbNumFactura = new javax.swing.JRadioButton();
        txtNumeroProforma = new javax.swing.JTextField();
        btnVerDirectorioProf = new javax.swing.JButton();
        btnVerProfExcel = new javax.swing.JButton();
        bt_agregar = new javax.swing.JButton();
        bt_cambiar_estado = new javax.swing.JButton();
        bt_imprimir = new javax.swing.JButton();
        bt_filtrar = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProformas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtTotalSoles = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTotalDolares = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar proformas por"));

        bgBusqueda.add(rbCliente);
        rbCliente.setText("Cliente");
        rbCliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbClienteItemStateChanged(evt);
            }
        });

        bt_buscar.setText("Buscar");
        bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscarActionPerformed(evt);
            }
        });

        bgBusqueda.add(rbNumFactura);
        rbNumFactura.setText("Nº Proforma");
        rbNumFactura.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbNumFacturaEventoGrupoOpciones(evt);
            }
        });

        txtNumeroProforma.setEditable(false);
        txtNumeroProforma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroProformaKeyTyped(evt);
            }
        });

        btnVerDirectorioProf.setText("Directorio Proformas");
        btnVerDirectorioProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDirectorioProfActionPerformed(evt);
            }
        });

        btnVerProfExcel.setText("Ver Proforma (Excel)");
        btnVerProfExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerProfExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbNumFactura)
                    .addComponent(rbCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tx_cliente, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                    .addComponent(txtNumeroProforma))
                .addGap(18, 18, 18)
                .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnVerDirectorioProf, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(btnVerProfExcel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbCliente)
                    .addComponent(tx_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerDirectorioProf, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumeroProforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_buscar))
                        .addComponent(rbNumFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnVerProfExcel))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        bt_agregar.setText("Agregar (Fact)");
        bt_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_agregarActionPerformed(evt);
            }
        });

        bt_cambiar_estado.setText("Cambiar Estado");
        bt_cambiar_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cambiar_estadoActionPerformed(evt);
            }
        });

        bt_imprimir.setText("Imprimir");
        bt_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imprimirActionPerformed(evt);
            }
        });

        bt_filtrar.setText("Filtrar");
        bt_filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_filtrarActionPerformed(evt);
            }
        });

        bt_cancelar.setText("Salir");
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        tbProformas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idProforma", "Nº Proforma", "Cliente", "Fecha Proforma", "Tipo Moneda", "Total", "Estado", "Seleccionar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbProformas.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbProformasPropertyChange(evt);
            }
        });
        tbProformas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbProformasKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tbProformas);
        if (tbProformas.getColumnModel().getColumnCount() > 0) {
            tbProformas.getColumnModel().getColumn(1).setPreferredWidth(30);
            tbProformas.getColumnModel().getColumn(2).setPreferredWidth(250);
            tbProformas.getColumnModel().getColumn(3).setPreferredWidth(10);
            tbProformas.getColumnModel().getColumn(4).setPreferredWidth(10);
            tbProformas.getColumnModel().getColumn(5).setPreferredWidth(20);
            tbProformas.getColumnModel().getColumn(6).setPreferredWidth(15);
            tbProformas.getColumnModel().getColumn(7).setPreferredWidth(25);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(bt_cambiar_estado)
                        .addGap(18, 18, 18)
                        .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 628, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_filtrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_cancelar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_cambiar_estado))
                    .addComponent(bt_imprimir, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel3.setText("TOTAL S/. :");

        txtTotalSoles.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel2.setText("TOTAL $ :");

        txtTotalDolares.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotalSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalDolares, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalDolares, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtTotalSoles))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbClienteItemStateChanged
        if ( rbCliente.isSelected() ) {
            tx_cliente.setEditable(true);
            
            txtNumeroProforma.setText("");
            txtNumeroProforma.setEditable(false);
            
        } else if ( rbNumFactura.isSelected() ) {
            tx_cliente.setEditable(false);
            
            txtNumeroProforma.setText("");
            txtNumeroProforma.setEditable(true);
        }
    }//GEN-LAST:event_rbClienteItemStateChanged

    private void bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscarActionPerformed
        Iterator it = null;
        if ( rbCliente.isSelected()) {
            System.out.println("busca 1");
            it = spr.getxCliente(tx_cliente.getText()).iterator();
            
        } else if ( rbNumFactura.isSelected() ) {
            System.out.println("busca 2");
            it = spr.getxNumeroFactura(txtNumeroProforma.getText()).iterator();
        }
        BorrarTabla();
        
        if ( it != null ) {
            while ( it.hasNext() ) {
                Cabecproformas cpfm = (Cabecproformas) it.next();
                AgregarFila(cpfm);
            }
        }
    }//GEN-LAST:event_bt_buscarActionPerformed

    private void rbNumFacturaEventoGrupoOpciones(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbNumFacturaEventoGrupoOpciones

        if ( rbCliente.isSelected() ) {
            tx_cliente.setEditable(true);

            txtNumeroProforma.setText("");
            txtNumeroProforma.setEditable(false);

        } else if ( rbNumFactura.isSelected() ) {
            tx_cliente.setEditable(false);

            txtNumeroProforma.setText("");
            txtNumeroProforma.setEditable(true);
        }
    }//GEN-LAST:event_rbNumFacturaEventoGrupoOpciones

    private void txtNumeroProformaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroProformaKeyTyped
        char caracter = evt.getKeyChar();

        if ( caracter == 10 ) {
            bt_buscar.doClick();

        } else {

            if ( caracter >= 'a' && caracter <= 'z' ) {
                evt.setKeyChar( (char) (caracter - 32) );
            }
        }
    }//GEN-LAST:event_txtNumeroProformaKeyTyped

    private void bt_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_agregarActionPerformed

        int numFilas = tbProformas.getRowCount();

        if ( numFilas != 0 ) {
            ArrayList<Cabecproformas> selec = new ArrayList<Cabecproformas>();
            selec.clear();

            for ( int fila = 0; fila < numFilas; fila++ ) {
                Object seleccionado = tbProformas.getModel().getValueAt(fila, 7);
                Object estado = tbProformas.getModel().getValueAt(fila, 6);
                boolean proformaSeleccionada = false;
                String estadoSeleccionado = "";

                if ( seleccionado != null && estado != null ) {
                    proformaSeleccionada = (boolean) seleccionado;
                    estadoSeleccionado = String.valueOf(estado);

                    if ( proformaSeleccionada ) {
                        if ( "PENDIENTE".equals(estadoSeleccionado) ) {
                            // Obteniendo cabeceras de proforma por idCabProforma
                            cabp = spr.GetxCodigoCabProforma(String.valueOf(tbProformas.getModel().getValueAt(fila, 1)));
                            selec.add(cabp);

                        } else {
                            selec = null;
                            break;
                        }
                    }
                }
            }

            if ( selec == null ) {
                tm.Error("Seleccione sólo proformas PENDIENTES");

            } else {
                if ( !selec.isEmpty() ) {
                    // A partir de selec, se llama a servicio para obtener detalle de proformas y separar de 10 en 10 items al facturar
                    System.out.println("llamar a facturacion");
                    
                    int filaSeleccionada = tbProformas.getSelectedRow();
                    System.out.println("filaSeleccionada:" + filaSeleccionada);
                    String monedaDeProforma = String.valueOf(tbProformas.getModel().getValueAt(filaSeleccionada, 4));
                    System.out.println("moneda desde PROFORMA:" + monedaDeProforma);

                    System.out.println("Selec : " + selec);
                    System.out.println("tbProformas : " + tbProformas);
                    System.out.println("Valor Venta : " + valorVenta);
                    System.out.println("Moneda Proforma : " + monedaDeProforma);
                    
                    IU_Facturacion itf = new IU_Facturacion(selec, tbProformas, valorVenta, monedaDeProforma);
                    itf.setUsuario(u);
                    itf.setLocationRelativeTo(null);
                    itf.setVisible(true);
                    dispose();

                } else {
                    tm.Error("<html>Marque la(s) proforma(s) PENDIENTES en<br /> la columna \'Seleccionar\' de la tabla</html>");
                }
            }

        } else {
            tm.Error(tm.TABLA_VACIA);
        }
    }//GEN-LAST:event_bt_agregarActionPerformed

    private void bt_cambiar_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cambiar_estadoActionPerformed

        int proformasEnTabla = tbProformas.getRowCount();

        if ( proformasEnTabla == 0 ) {
            tm.Error(tm.TABLA_VACIA);

        } else {
            int filaSeleccionada = tbProformas.getSelectedRow();    // Franja azul de selección sobre browse
            int numSeleccionadas = numeroProformasSeleccionadas();

            if ( numSeleccionadas == 1 || filaSeleccionada != -1 ) {
                String estadoActual = String.valueOf(tbProformas.getModel().getValueAt(filaSeleccionada, 6));
                if ( "FACTURADO".equals(estadoActual) ) {
                    tm.Error("Las proformas facturadas no pueden cambiar de estado");

                } else {
                    String codigoProforma = String.valueOf(tbProformas.getModel().getValueAt(filaSeleccionada, 1));
                    cabp = spr.GetxCodigoCabProforma(codigoProforma);
                    estadoProforma = new IU_EstadoProforma(cabp, tbProformas.getModel(), filaSeleccionada);

                    estadoProforma.addWindowListener(new WindowAdapter(){
                        @Override
                        public void windowClosed(WindowEvent e) {
                            estadoProforma = null;
                        }
                    });
                    estadoProforma.setVisible(true);
                    estadoProforma.setLocationRelativeTo(null);
                }

            } else if (numSeleccionadas == 0 || numSeleccionadas > 1 ) {
                tm.Error("Seleccione solo una proforma");
            }
        }
    }//GEN-LAST:event_bt_cambiar_estadoActionPerformed

    private void bt_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imprimirActionPerformed

        int proformasEnTabla = tbProformas.getRowCount();

        if ( proformasEnTabla == 0 ) {
            tm.Error(tm.TABLA_VACIA);

        } else {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de imprimir listado de proformas?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
            if ( confirmacion == JOptionPane.YES_OPTION ) {
                ArrayList lista = obtenerProformasTabla();
                Map<String, Object> parametros = obtenerParametros();
                Servicio_Impresion.exporta(21, lista, parametros, "plantillas/Impresion_ProformasFact.pdf");
            }
        }
    }//GEN-LAST:event_bt_imprimirActionPerformed

    private void bt_filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_filtrarActionPerformed

        filtroProformas = new IU_FiltrarProformas(this);
        filtroProformas.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosed(WindowEvent e) {
                filtroProformas = null;
            }

        });
        filtroProformas.setVisible(true);
        filtroProformas.setLocationRelativeTo(null);
    }//GEN-LAST:event_bt_filtrarActionPerformed

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed
        dispose();
    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void tbProformasPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbProformasPropertyChange

        int proformasEnTabla = tbProformas.getRowCount();

        if ( proformasEnTabla != 0 ) {

            double totalDolaresProformas = 0.00;
            double totalSolesProformas = 0.00;

            for ( int fila = 0; fila < proformasEnTabla; fila++ ) {
                Object seleccionado = tbProformas.getModel().getValueAt(fila, 7);

                if ( seleccionado != null ) {
                    boolean proformaSeleccionada = (boolean) seleccionado;

                    if ( proformaSeleccionada ) {
                        String tipoMoneda = String.valueOf(tbProformas.getModel().getValueAt(fila, 4));
                        String totalDolaresOSoles = String.valueOf(tbProformas.getModel().getValueAt(fila, 5));

                        double totalDoubleDolOSol = Double.parseDouble(totalDolaresOSoles);
                        if  ( GLOSA_DOLARES.equals(tipoMoneda) ) {
                            totalDolaresProformas += totalDoubleDolOSol;

                        } else if ( GLOSA_SOLES.equals(tipoMoneda) ) {
                            totalSolesProformas += totalDoubleDolOSol;
                        }
                    }
                }
            }
            Util util = new Util();
            txtTotalSoles.setText(util.DosDecimales(totalSolesProformas));
            txtTotalDolares.setText(util.DosDecimales(totalDolaresProformas));
        }
    }//GEN-LAST:event_tbProformasPropertyChange

    private void tbProformasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbProformasKeyTyped

        if ( tbProformas.getSelectedRow() >= 0 ) {

            if ( evt.getKeyChar() == 10 ) {
                bt_agregar.doClick();
            }
        }

    }//GEN-LAST:event_tbProformasKeyTyped

    private void btnVerDirectorioProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDirectorioProfActionPerformed
        llamarExploradorWindows();
    }//GEN-LAST:event_btnVerDirectorioProfActionPerformed

    private void btnVerProfExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerProfExcelActionPerformed
        int numFilas = tbProformas.getRowCount();
        
        if ( numFilas != 0 ) {
            int filaSeleccionada = tbProformas.getSelectedRow();    // Franja azul de selección sobre browse
            if ( filaSeleccionada != -1 ) {
                Object seleccionado = tbProformas.getModel().getValueAt(filaSeleccionada, 7);
                String select = String.valueOf(seleccionado);

                if ( "true".equals(select) ) {
                    Runtime r = Runtime.getRuntime();
                    Process p = null;

                    try {
                        int idCabProforma = Integer.parseInt(String.valueOf(tbProformas.getModel().getValueAt(filaSeleccionada, 0)));
                        Cabecproformas cabProforma = new Servicio_Documentos().GetxIdCabProforma(idCabProforma);
//                        String nroSerie = cabProforma.getSerie();
//                        System.out.println("nroSerie:" + nroSerie);
                        String codigoCabProf = cabProforma.getCodigocabproforma();
//                        System.out.println("codigoCabProf:" + codigoCabProf);

                        String nroSer = cabProforma.getCabeces().getId().getNrorserie();
//                        System.out.println("nroSer:" + nroSer);
//                        String nroDocumento = cabProforma.getCabeces().getId().getNrodocumento();
//                        System.out.println("nroDocumento:" + nroDocumento);

                        String rutaBD = new ControlDAO().Obtener_Objeto(1).getRutaprogramas() + "\\Proforma_" + nroSer + "_" + codigoCabProf + ".xls";
//                        System.out.println("rutaBD:" + rutaBD);

                        String rutaProformasExcel = rutaBD.replace("/", "\\");
//                        System.out.println("rutaProformasExcel:" + rutaProformasExcel);
                        p = r.exec("explorer.exe " + rutaProformasExcel);

                    } catch (Exception e) {
                        System.out.println("Error al ejecutar" + e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Seleccione una proforma", "PROFORMA", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Seleccione una proforma", "PROFORMA", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            tm.Error(tm.TABLA_VACIA);
        }
    }//GEN-LAST:event_btnVerProfExcelActionPerformed

    private void llamarExploradorWindows() {
        Runtime r = Runtime.getRuntime();
        Process p = null;
        try {
            String rutaBD = new ControlDAO().Obtener_Objeto(1).getRutaprogramas();
            String rutaProformasExcel = rutaBD.replace("/", "\\");
            p = r.exec("explorer.exe " + rutaProformasExcel);
        } catch (Exception e) {
            System.out.println("Error al ejecutar" + e.getMessage());
        }
    }
    
    private ArrayList obtenerProformasTabla() {
        
        ArrayList<FilaProformaFacturar> lista = new ArrayList<FilaProformaFacturar>();
        DefaultTableModel dtm = (DefaultTableModel) tbProformas.getModel();
        
        for ( int i = 0; i < tbProformas.getRowCount(); i++ ) {
            String idCabProforma = String.valueOf(dtm.getValueAt(i, 0));
            String numeroProforma = String.valueOf(dtm.getValueAt(i, 1));
            String cliente = String.valueOf(dtm.getValueAt(i, 2));
            String fechaProforma = String.valueOf(dtm.getValueAt(i, 3));
            String tipoMoneda = String.valueOf(dtm.getValueAt(i, 4));
            String total = String.valueOf(dtm.getValueAt(i, 5));
            String estado = String.valueOf(dtm.getValueAt(i, 6));
            
            lista.add(new FilaProformaFacturar(idCabProforma, numeroProforma, cliente, fechaProforma, tipoMoneda, total, estado));
        }
        return lista;
    }
    
    private Map obtenerParametros() {
        Map<String, Object> mapa = new HashMap<>();
        String empresa = new ControlDAO().Obtener_Objeto(1).getNombrempresa();
        mapa.put("empresa", empresa);
        return mapa;
    }
    
    private void BorrarTabla() {
        int numRows = tab.getRowCount();
        for (int i = 0; i < numRows; i++) {
            tab.removeRow(0);
        }
    }
    
    private int numeroProformasSeleccionadas() {
        
        int numSeleccionadas = 0;
        int numFilas = tbProformas.getRowCount();
        
        for ( int fila = 0; fila < numFilas; fila++ ) {
            Object seleccionado = tbProformas.getModel().getValueAt(fila, 7);
            
            if ( seleccionado != null ) {
                boolean proformaSeleccionada = (boolean) seleccionado;
                
                if ( proformaSeleccionada ) {
                    numSeleccionadas++;
                }
            }
        }
        return numSeleccionadas;
    }

    private void AgregarFila(Cabecproformas cpm) {
        Object[] row = new Object[7];
        row[0] = cpm.getIdcabproforma();
        row[1] = cpm.getCodigocabproforma();
        row[2] = cpm.getClientes().getNombre();
        row[3] = cpm.getFecha();
        
        if ( DOLARES.equals(cpm.getMoneda()) ) {
            row[4] = GLOSA_DOLARES;
            
        } else if ( SOLES.equals(cpm.getMoneda()) ) {
            row[4] = GLOSA_SOLES;
        }
        
        double totalFact = 0.00;
        if ( cpm.getTotalfact() != null ) {
            totalFact = cpm.getTotalfact();
        }
        double totalEnProforma = new Util().Redondear2Decimales(cpm.getTotal() - totalFact);
        row[5] = formatearTotal(totalEnProforma);
        row[6] = cpm.getEstado();
        tab.addRow(row);
    }

    private String formatearTotal(double total) {
        String totalFormateado = String.valueOf(total);
        int posicion = totalFormateado.indexOf("."); // 35.1
        if ( posicion != -1 ) {
            int digitosDecimales = totalFormateado.length() - (posicion + 1);
            if ( digitosDecimales == 1 ) {
                totalFormateado += "0";
            }
        } else { // totalFormateado = 35 (digitosDecimales = 2-0 = 2)
            totalFormateado += ".00";
        }
        return totalFormateado;
    }
//    private void Listar() {
//        List lista = spr.getxCliente(tx_cliente.getText());
//        if (lista != null) {
//            Iterator it = lista.iterator();
//            if (it != null) {
//                while (it.hasNext()) {
//                    Cabecproformas cpfm = (Cabecproformas) it.next();
//                    AgregarFila(cpfm);
//                }
//            }
//        }
//    }
    public void Listar(List lista) {
        
        if ( lista != null ) {
            
            Iterator it = lista.iterator();
            limpiarTabla(tbProformas);

            if ( it != null ) {
                while ( it.hasNext() ) {
                    Cabecproformas cpfm = (Cabecproformas) it.next();
                    AgregarFila(cpfm);
                }
            }
            bt_agregar.setEnabled(true);
            bt_cambiar_estado.setEnabled(true);
            bt_imprimir.setEnabled(true);
            bt_filtrar.setEnabled(true);
            
        } else {
            bt_agregar.setEnabled(false);
            bt_cambiar_estado.setEnabled(false);
            bt_imprimir.setEnabled(false);
            bt_filtrar.setEnabled(false);
        }
    }
    
    private void limpiarTabla(JTable tabla){
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        int filas = tabla.getRowCount();
        for ( int i = 0; filas > i; i++ ) {
            modelo.removeRow(0);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgBusqueda;
    private javax.swing.JButton bt_agregar;
    private javax.swing.JButton bt_buscar;
    private javax.swing.JButton bt_cambiar_estado;
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_filtrar;
    private javax.swing.JButton bt_imprimir;
    private javax.swing.JButton btnVerDirectorioProf;
    private javax.swing.JButton btnVerProfExcel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbCliente;
    private javax.swing.JRadioButton rbNumFactura;
    public javax.swing.JTable tbProformas;
    private javax.swing.JTextField tx_cliente;
    private javax.swing.JTextField txtNumeroProforma;
    private javax.swing.JTextField txtTotalDolares;
    private javax.swing.JTextField txtTotalSoles;
    // End of variables declaration//GEN-END:variables
}
