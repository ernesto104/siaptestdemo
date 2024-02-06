package Presentacion.Facturacion;

import static Entidades.Otros.Constante.COL_MODELO_REPUESTOS;
import static Entidades.Otros.Constante.DOLAR;
import static Entidades.Otros.Constante.DOLAR_COMBO;
import static Entidades.Otros.Constante.SAL_REGULARIZACION;
import static Entidades.Otros.Constante.SOL;
import static Entidades.Otros.Constante.SOL_COMBO;
import Entidades.Repuestos;
import Presentacion.IU_Paquete_Repuestos;
import Presentacion.IngresoSalidas.IU_DetalleIngSal;
import Presentacion.IngresoSalidas.IU_NuevoRepuesto;
import Servicios.Comision.Validar_Mayusculas;
import Servicios.Servicio_Control_Sistema;
import Servicios.Servicio_Maestros;
import Servicios.Util;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Administrador
 */
public final class IU_FMaestro extends javax.swing.JFrame {

    boolean sel;
    Servicio_Maestros sm;
    DefaultTableModel t;
    public IU_Facturacion f;
    public IU_DetalleIngSal det;
    IU_Paquete_Repuestos paq;
    Repuestos r;
    boolean presionado;
    
    private int MaxLineas;
    private String TipoDocumento;
    
    private char verStock0;
    Util util;
    private boolean desdeFacturacion;

    public IU_FMaestro(IU_Facturacion f, boolean desdeFacturacion, String tipoDocumento) { // desdeFacturacion o desdeIngXCompraLocal
        this.TipoDocumento = tipoDocumento;
        this.desdeFacturacion = desdeFacturacion;
        sm = new Servicio_Maestros(null);
        sel = true;
        this.f = f;
        det = null;
        paq = null;
        initComponents();
        t = (DefaultTableModel) tb_rep.getModel();
        
//        String titulosFactura[] = { "Linea", "Nro Parte", "Descripcion", "Modelo", "Precio Lista", "Stock", "Unidad Venta" };
        String titulosFactura[] = { "Línea", "Nro Parte", "Codigo Sec", "Descripción", 
                                    "Aplicación", // Modelo
                                    "Precio Lista", "Stock", "Unidad Venta" };
        
        switch( f.monedaControlRepuestos ) {
            case DOLAR_COMBO:
                titulosFactura[4] = titulosFactura[4] + "(" + DOLAR + ")";
                break;
                
            case SOL_COMBO:
                titulosFactura[4] = titulosFactura[4] + "(" + SOL + ")";
                break;
        }
        t.setColumnIdentifiers(titulosFactura);
        
        initOtherComponents();
//        System.out.println("verStock0:" + verStock0);
        if ( verStock0 == 'S' ) {
            ListarRep(sm.getList().iterator());
            
        } else {
            ListarRep(sm.getRepuestos_conStock().iterator());
        }
        presionado = false;
        bt_nuevoRep.setVisible(false);
        tx_busqueda.setDocument(new Validar_Mayusculas(tx_busqueda, 15));
        alinearDerecha();
//        ocultarModeloMaestroRepuestos();
    }
    
    private void ocultarModeloMaestroRepuestos() {
        tb_rep.getColumnModel().getColumn(COL_MODELO_REPUESTOS).setMaxWidth(0);
        tb_rep.getColumnModel().getColumn(COL_MODELO_REPUESTOS).setMinWidth(0);
        tb_rep.getColumnModel().getColumn(COL_MODELO_REPUESTOS).setPreferredWidth(0);
    }
    
    private void alinearDerecha() {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tb_rep.getColumnModel().getColumn(5).setCellRenderer(tcr);
        tb_rep.getColumnModel().getColumn(6).setCellRenderer(tcr);
        
        
//        System.out.println(" 8 : " + tb_rep.getColumnModel().getColumn(8));
        
        
        
    }

    public IU_FMaestro(IU_DetalleIngSal detalle, boolean desdeFacturacion) {
        this.desdeFacturacion = desdeFacturacion;
        sm = new Servicio_Maestros(null);
        sel = true;
        this.det = detalle;
        f = null;
        paq = null;
        initComponents();
        t = (DefaultTableModel) tb_rep.getModel();
        
//        String titulosIngresoSalida[]={"Linea", "Nro Parte", "Descripcion", "Modelo", "Precio Costo", "Stock", "Unidad Venta"};
        String titulosIngresoSalida[] = {"Línea", "Nro Parte", "Codigo Sec", "Descripción", 
                                         "Aplicación", // Modelo
                                         "Precio Costo", "Stock", "Unidad Venta"};
        t.setColumnIdentifiers(titulosIngresoSalida);
        
        setLocationRelativeTo(null);
        initOtherComponents();
        
        if ( detalle.getTipo() == SAL_REGULARIZACION ) {
            if ( verStock0 == 'S' ) {
                System.out.println("1111");
                ListarRep(sm.getList().iterator());
                
            } else {
                System.out.println("2222");
                ListarRep(sm.getRepuestos_conStock().iterator());
            }
        } else {
            ListarRep(sm.getList().iterator());
        }
        presionado = false;
        alinearDerecha();
    }

    public IU_FMaestro(IU_Paquete_Repuestos paqrep) {
        sm = new Servicio_Maestros(null);
        sel = true;
        this.paq = paqrep;
        f = null;
        det = null;
        initComponents();
        t = (DefaultTableModel) tb_rep.getModel();
        setLocationRelativeTo(null);
        initOtherComponents();
        ListarRep(sm.getList().iterator());
        presionado = false;
        bt_nuevoRep.setVisible(false);
        alinearDerecha();
    }

    private void initOtherComponents() {
        tx_busqueda.setEnabled(true);
        setAnchoColumnas();
        setLocationRelativeTo(null);
        setVisible(true);
        verStock0 = new Servicio_Control_Sistema(null).obtener_Unico_Control().getStock0();
        util = new Util();
    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tb_rep.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tb_rep.getColumnModel();
        TableColumn columnaTabla;
        
        for ( int i = 0; i < tb_rep.getColumnCount(); i++ ) {
            columnaTabla = modeloColumna.getColumn(i);
            switch ( i ) {
                case 0: // Línea
                    anchoColumna = (5 * ancho) / 100;
                    break;
                case 1: // Nro Parte
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 2: // Codigo SEc.
                    anchoColumna = (8 * ancho) / 100;
                    break;
                case 3: // Descripción
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 4: // Aplicación (Modelo)
                    anchoColumna = (24 * ancho) / 100;
                    break;
                case 5: // Precio Costo
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 6: // Stock
                    anchoColumna = (8 * ancho) / 100;
                    break;
                case 7: // Unidad Venta
                    anchoColumna = (8 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    public void ListarRep(Iterator it) {
        while ( it.hasNext() ) {
            Object[] result = (Object[]) it.next();
            AgregarFila(result);
        }
    }
    
    public void AgregarFila(Object[] repuesto) {        
        Object[] row = new Object[9];
        
        row[0] = Integer.parseInt(String.valueOf(repuesto[0])); // idLinea
        row[1] = String.valueOf(repuesto[1]); // codRepuesto o NroParte

//      row[2] = String.valueOf(repuesto[2]); // codigo Sec
//        System.out.println("cod repuesto:" + row[1]);
        row[2] = ( repuesto[8] == null ) ? "" : String.valueOf(repuesto[8]); // codigo. sec.        
        row[3] = String.valueOf(repuesto[2]); // descripcion
        row[4] = ( repuesto[3] == null ) ? "" : String.valueOf(repuesto[3]); // descrmodelo (aplicación)
        
        if ( this.f != null ) { // Proviene de IU_Facturacion
            row[5] = util.DosDecimales(Double.parseDouble(String.valueOf(repuesto[4]))); // preciolista
            row[6] = ( repuesto[6] == null ) ? "" : String.valueOf(repuesto[6]); // stock
            row[7] = ( repuesto[7] == null ) ? "" : String.valueOf(repuesto[7]); // unidadVenta
//                    util.DosDecimales(Double.parseDouble(String.valueOf(repuesto[5]))); // costopromedio
            
        } else if ( this.det != null ) { // Proviene de IU_DetalleIngSal
            row[5] = ( repuesto[5] == null ) ? "" : util.DosDecimales(Double.parseDouble(String.valueOf(repuesto[5]))); // costopromedio
            row[6] = Integer.parseInt(String.valueOf(repuesto[6])); // stock
            row[7] = ( repuesto[7] == null ) ? "" : String.valueOf(repuesto[7]); // unidadVenta            
        }

        t.addRow(row);
    }
    
//    private void ListarRep(Iterator it) {
//        while (it.hasNext()) {
//            AgregarFila((Repuestos) it.next());
//        }
//    }

//    private void AgregarFila(Repuestos r) {
//        Object[] row = new Object[7];
//        row[0] = r.getLineas().getIdlinea();
//        row[1] = r.getCodrepuesto();
//        row[2] = r.getDescrmodelo();
//        row[3] = r.getDescripcion();
//        if ( this.f != null ) { // Proviene de IU_Facturacion
//            row[4] = util.DosDecimales(r.getPreciolista());
//            
//        } else if ( this.det != null ) { // Proviene de IU_DetalleIngSal
//            row[4] = util.DosDecimales(r.getCostopromedio());
//        }
//        row[5] = r.getStock();
//        row[6] = r.getUnidadventa();
//        t.addRow(row);
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tx_busqueda = new javax.swing.JTextField();
        bt_buscar = new javax.swing.JButton();
        lb_mensaje = new javax.swing.JLabel();
        cb_busqueda = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_rep = new javax.swing.JTable();
        bt_agregar = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        bt_nuevoRep = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Busqueda por"));

        tx_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tx_busquedaActionPerformed(evt);
            }
        });
        tx_busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tx_busquedaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tx_busquedaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                busqueda(evt);
            }
        });

        bt_buscar.setText("Buscar");
        bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscarActionPerformed(evt);
            }
        });

        lb_mensaje.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        cb_busqueda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nro Parte", "Descripcion", "Modelo", "Precio Lista" }));
        cb_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_busquedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cb_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tx_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(lb_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cb_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tx_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tb_rep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Línea", "Nro Parte", "Codigo Sec", "Descripción", "Aplicación", "Precio Lista", "Stock", "Unidad Venta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_rep.getTableHeader().setReorderingAllowed(false);
        tb_rep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tb_repKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tb_rep);

        bt_agregar.setText("Agregar");
        bt_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_agregarActionPerformed(evt);
            }
        });
        bt_agregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bt_agregarKeyTyped(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        bt_nuevoRep.setText("Registrar Nuevo Repuesto");
        bt_nuevoRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_nuevoRepActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SELECCIÓN DE REPUESTOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(bt_nuevoRep, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 872, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_nuevoRep, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscarActionPerformed
        String filtro = tx_busqueda.getText();
        BorrarTabla();
        
//        if ( "".equals(filtro) ) {
//            JOptionPane.showMessageDialog(null, "Ingresar el valor para la búsqueda");
//            
//        } else {  
        
            System.out.println("cb_busqueda.getSelectedIndex()" + cb_busqueda.getSelectedIndex());
            ListarRep(sm.BuscarRep_por_(cb_busqueda.getSelectedIndex(), filtro).iterator());
//        }
    }//GEN-LAST:event_bt_buscarActionPerformed

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void bt_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_agregarActionPerformed
        int[] rows = tb_rep.getSelectedRows();
//        System.out.println("N° filas seleccionadas:" + rows.length);
        ArrayList<Repuestos> repuestos = new ArrayList<>();
        
        if ( rows.length > 0 ) {
            for ( int i : rows ) {
                if ( presionado && rows.length == 1 ) {
                    i--;
                }
                if ( i == -1 ) {
                    i = tb_rep.getRowCount() - 1;
                }
                String codRep = String.valueOf(t.getValueAt(i, 1));
//                System.out.println("codRep:" + codRep);
                Repuestos rep = sm.getRepuesto_CodRep(codRep);
//                System.out.println("rep:" + rep);
//                System.out.println("rep.codigo:" + rep.getCodrepuesto());
                repuestos.add(rep);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione al menos un item");
        }
//        System.out.println("det:" + det);
        
        if ( f != null ) {
//            System.out.println("1.agregar factura...");
            Agregar_Factura(repuestos);
            
        } else {
            if ( det != null ) {
//                System.out.println("2.agregar salida...");
                Agregar_IngresoSalida(repuestos);
                
            } else {
//                System.out.println("3.agregar paquete repuesto...");
                Agregar_PaqueteRepuestos(repuestos);
            }
        }
    }//GEN-LAST:event_bt_agregarActionPerformed

    private void busqueda(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busqueda
        if ( evt.getKeyChar() == 10 ) {
            bt_buscar.doClick();
        }
    }//GEN-LAST:event_busqueda

    private void tb_repKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_repKeyTyped
        if ( evt.getKeyChar() == 10 ) {
            presionado = true;
            bt_agregar.doClick();
            
        } else {
            if ( evt.getKeyChar() == 27 ) {
                bt_salir.doClick();
            }
        }

    }//GEN-LAST:event_tb_repKeyTyped

    private void bt_nuevoRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_nuevoRepActionPerformed
        IU_NuevoRepuesto nr = new IU_NuevoRepuesto(this);
        nr.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
//                recargarMaestro();
            }
        });
    }//GEN-LAST:event_bt_nuevoRepActionPerformed

    public void recargarMaestro() {
        System.out.println("recargar IU_FMaestro");
        BorrarTabla();
//        List lst = sm.getList();
//        if ( !lst.isEmpty() ) {
//            System.out.println("tamaño de lista:" + lst.size());
//        }
//        ListarRep(lst.iterator());
        ListarRep(sm.getList().iterator());
    }
    
    private void bt_agregarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bt_agregarKeyTyped
        if ( evt.getKeyChar() == 27 ) {
            bt_salir.doClick();
        }
    }//GEN-LAST:event_bt_agregarKeyTyped

    private void tx_busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_busquedaKeyPressed
        if ( evt.getKeyChar() == 10 ) {
            bt_buscar.doClick();
        }
    }//GEN-LAST:event_tx_busquedaKeyPressed

    private void tx_busquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tx_busquedaKeyReleased
        if ( evt.getKeyChar() == 10 ) {
            bt_buscar.doClick();
        }
    }//GEN-LAST:event_tx_busquedaKeyReleased

    private void cb_busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_busquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_busquedaActionPerformed

    private void tx_busquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx_busquedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx_busquedaActionPerformed

    public void BorrarTabla() {
        int numRows = t.getRowCount();
        for ( int i = 0; i < numRows; i++ ) {
            t.removeRow(0);
        }
    }

    private void Agregar_Factura(ArrayList<Repuestos> repuestos) {
        for ( Repuestos repuesto : repuestos ) {
            if ( f.tablaFacturacion.getRowCount() >= MaxLineas ) {
                lb_mensaje.setText("MAXIMO N° REPUESTOS PARA " + TipoDocumento + ": " + MaxLineas + " items");
//                lb_mensaje.setText("MAXIMO DE REPUESTOS SELECCIONADOS(Máx. " + TipoDocumento + " items)");
                break;
            } else {
//                System.out.println("Agregar_Factura(agregar repuesto)...XD");
                if ( f.tablaFacturacion.agregar(repuesto, f.csel/*, moneda*/) ) {
                    lb_mensaje.setText("");
                } else {
                    lb_mensaje.setText("EL REPUESTO YA HA SIDO AGREGADO");
                }
            }
        }
    }

    private void Agregar_IngresoSalida(ArrayList<Repuestos> repuestos) {
        if ( !repuestos.isEmpty() ) {
            System.out.println("N° repuestos::" + repuestos.size());
        }
        for ( Repuestos repuesto : repuestos ) {
            if ( det.tabla.getRowCount() >= MaxLineas ) {
                lb_mensaje.setText("MAXIMO DE REPUESTOS INGRESADOS");
                break;
                
            } else {
//                System.out.println("repuesto:" + repuesto);
//                System.out.println("repuesto.getId():" + repuesto.getId());
//                System.out.println("idRepuesto:" + repuesto.getId().getIdrepuesto());
                
                if ( det.tabla.AgregarRepuesto(repuesto, det.Proveedor) ) {
                    lb_mensaje.setText("");
                    
                } else {
                    lb_mensaje.setText("EL REPUESTO YA HA SIDO AGREGADO");
                }
            }
        }
    }

    private void Agregar_PaqueteRepuestos(ArrayList<Repuestos> repuestos) {
        for ( Repuestos repuesto : repuestos ) {
            if ( paq.Agregar(repuesto) ) {
                lb_mensaje.setText("");
            } else {
                lb_mensaje.setText("EL REPUESTO YA HA SIDO AGREGADO");
            }
        }
    }

    public JButton getBt_agregar() {
        return bt_agregar;
    }

    public JTable getTb_rep() {
        return tb_rep;
    }

    public int getMaxLineas() {
        return MaxLineas;
    }

    public void setMaxLineas(int MaxLineas, String tipoDocumento) {
        this.TipoDocumento = TipoDocumento;
        this.MaxLineas = MaxLineas;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_agregar;
    private javax.swing.JButton bt_buscar;
    private javax.swing.JButton bt_nuevoRep;
    private javax.swing.JButton bt_salir;
    public javax.swing.JComboBox cb_busqueda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_mensaje;
    public javax.swing.JTable tb_rep;
    private javax.swing.JTextField tx_busqueda;
    // End of variables declaration//GEN-END:variables
}