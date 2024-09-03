package Presentacion.Facturacion;

import Entidades.Equipos;
import Entidades.Marcas;
import Entidades.Modelos;
import static Entidades.Otros.Constante.COL_MODELO_REPUESTOS;
import static Entidades.Otros.Constante.DOLAR;
import static Entidades.Otros.Constante.DOLAR_COMBO;
import static Entidades.Otros.Constante.SAL_REGULARIZACION;
import static Entidades.Otros.Constante.SOL;
import static Entidades.Otros.Constante.SOL_COMBO;
import Entidades.Repuestos;
import Entidades.Usuarios;
import Presentacion.IU_Paquete_Repuestos;
import Presentacion.IngresoSalidas.IU_DetalleIngSal;
import Presentacion.IngresoSalidas.IU_NuevoRepuesto;
import Servicios.Comision.Validar_Mayusculas;
import Servicios.Servicio_Control_Sistema;
import Servicios.Servicio_Equipos;
import Servicios.Servicio_Maestros;
import Servicios.Servicio_Marcas;
import Servicios.Servicio_Modelos;
import Servicios.Servicio_Usuarios;
import Servicios.Util;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
        String titulosFactura[] = { "ID", "Equipo", "Marca", "Modelo", 
                                    "N° de Serie", // Modelo
                                    "Descripcion", "Stock", "Precio Lista" };
        
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
        llenar_equipos();
        llenar_usuarios();
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
        tb_rep.getColumnModel().getColumn(6).setCellRenderer(tcr);
        tb_rep.getColumnModel().getColumn(7).setCellRenderer(tcr);
        
        
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
        llenar_equipos();
        t = (DefaultTableModel) tb_rep.getModel();
        
//        String titulosIngresoSalida[]={"Linea", "Nro Parte", "Descripcion", "Modelo", "Precio Costo", "Stock", "Unidad Venta"};
        String titulosIngresoSalida[] = {"ID", "Equipo", "Marca", "Modelo", 
                                         "Nro de Serie", // Modelo
                                         "Descripcion", "Stock", "Precio de Lista"};
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
        llenar_equipos();
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
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 3: // Descripción
                    anchoColumna = (15 * ancho) / 100;
                    break;
                case 4: // Aplicación (Modelo)
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 5: // Precio Costo
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 6: // Stock
                    anchoColumna = (3 * ancho) / 100;
                    break;
                case 7: // Precio lista
                    anchoColumna = (5 * ancho) / 100;
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
    
    private void llenar_equipos() {
        Servicio_Equipos sr = new Servicio_Equipos(null);
        Iterator it = sr.getList().iterator();

        while ( it.hasNext() ) {
            Equipos l = (Equipos) it.next();
            cb_equipo.addItem(l.getIdequipo() + " - " + l.getDescripcion()); // llenar combobox de arriba

        }
    }
        
    
    private void llenar_usuarios() {
        Servicio_Usuarios su = new Servicio_Usuarios(null);
        Iterator it = su.getList().iterator();

        while ( it.hasNext() ) {
            Usuarios l = (Usuarios) it.next();
            System.out.println();
            cbUsuarios.addItem(l.getIdusuario() + " - " + l.getNombre()); // llenar combobox de arriba

        }
    }
    
    public void AgregarFila(Object[] repuesto) {        
        Object[] row = new Object[9];
        
        row[0] = Integer.parseInt(String.valueOf(repuesto[0])); // Id Producto
        row[1] = String.valueOf(repuesto[1]); // Equipo 

//      row[2] = String.valueOf(repuesto[2]); // codigo Sec
//        System.out.println("cod repuesto:" + row[1]);
        row[2] = ( repuesto[2] == null ) ? "" : String.valueOf(repuesto[2]); // Marca        
        row[3] = String.valueOf(repuesto[3]); // Modelo
        row[4] = ( repuesto[4] == null ) ? "" : String.valueOf(repuesto[4]); // Numero de Serie
                                                                              // descrmodelo (aplicación)
        if ( this.f != null ) { // Proviene de IU_Facturacion
            //row[5] = util.DosDecimales(Double.parseDouble(String.valueOf(repuesto[4]))); // preciolista
            row[5] = ( repuesto[5] == null ) ? "" : String.valueOf(repuesto[5]);  // Desripcion
            row[6] = ( repuesto[6] == null ) ? "" : String.valueOf(repuesto[6]); // stock
            row[7] = ( repuesto[7] == null ) ? "" : util.DosDecimales(Double.parseDouble(String.valueOf(repuesto[7]))); //precio lista
            //row[7] = ( repuesto[7] == null ) ? "" : String.valueOf(repuesto[7]); // unidadVenta
//                    util.DosDecimales(Double.parseDouble(String.valueOf(repuesto[5]))); // costopromedio
            
        } else if ( this.det != null ) { // Proviene de IU_DetalleIngSal
            //row[5] = ( repuesto[5] == null ) ? "" : util.DosDecimales(Double.parseDouble(String.valueOf(repuesto[5]))); // costopromedio
            row[5] = ( repuesto[5] == null ) ? "" : String.valueOf(repuesto[5]); // Desripcion
            row[6] = ( repuesto[6] == null ) ? "" : Integer.parseInt(String.valueOf(repuesto[6])); // stock
            row[7] = ( repuesto[7] == null ) ? "": util.DosDecimales(Double.parseDouble(String.valueOf(repuesto[7]))); //precio lista
            //row[7] = ( repuesto[7] == null ) ? "" : String.valueOf(repuesto[7]); // unidadVenta            
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
        cb_equipo = new javax.swing.JComboBox();
        cb_marca = new javax.swing.JComboBox();
        cb_modelo = new javax.swing.JComboBox();
        bt_buscar1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtFechaRegistroInicial = new com.toedter.calendar.JDateChooser();
        jLabel45 = new javax.swing.JLabel();
        txtFechaRegistroFinal = new com.toedter.calendar.JDateChooser();
        bt_buscar2 = new javax.swing.JButton();
        cbUsuarios = new javax.swing.JComboBox();
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

        cb_busqueda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nro Serie", "Descripcion", "Anotacion 1", "Aplicacion 1" }));
        cb_busqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_busquedaActionPerformed(evt);
            }
        });

        cb_equipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Equipos" }));
        cb_equipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_equipoItemStateChanged(evt);
            }
        });

        cb_marca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Marcas" }));
        cb_marca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_marcaItemStateChanged(evt);
            }
        });
        cb_marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_marcaActionPerformed(evt);
            }
        });

        cb_modelo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Modelos" }));
        cb_modelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_modeloActionPerformed(evt);
            }
        });

        bt_buscar1.setText("Buscar");
        bt_buscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscar1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha Registro:");

        txtFechaRegistroInicial.setDateFormatString("dd-MM-yyyy");

        jLabel45.setText("a");

        txtFechaRegistroFinal.setDateFormatString("dd-MM-yyyy");

        bt_buscar2.setText("Buscar");
        bt_buscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscar2ActionPerformed(evt);
            }
        });

        cbUsuarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cb_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tx_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(cbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaRegistroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cb_equipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFechaRegistroFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_buscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(cb_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cb_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bt_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bt_buscar2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tx_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lb_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_equipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel45)
                            .addComponent(txtFechaRegistroFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaRegistroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tb_rep.setAutoCreateRowSorter(true);
        tb_rep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Equipo", "Marca", "Modelo", "N° de Serie", "Descripcion", "Aplicacion 1", "Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
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
        jLabel1.setText("SELECCIÓN DE PRODUCTOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1079, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(333, 333, 333))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(bt_nuevoRep, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addGap(0, 0, Short.MAX_VALUE))
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
                String idRep = String.valueOf(t.getValueAt(i, 0));
//                System.out.println("codRep:" + codRep);
                Repuestos rep = sm.GetRepuesto_Id(Integer.valueOf(idRep));
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

    private void cb_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_marcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_marcaActionPerformed

    private void cb_modeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_modeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_modeloActionPerformed

    private void bt_buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscar1ActionPerformed
        // TODO add your handling code here:
        
        
        int caso;
        String criterioBusque;
        /*if ("ADMINISTRADOR".equals(roltemporal)){        
            caso = 0;
        } else {
            caso = 6;
        }*/
        
        if(cb_equipo.getSelectedIndex()==0){
            caso=4;
            criterioBusque="";
        }
        else if(cb_marca.getSelectedIndex()==0){
            caso=4;
            criterioBusque=String.valueOf(cb_equipo.getSelectedItem());
            criterioBusque=criterioBusque.split(" - ")[0];
            //System.out.print(criterioBusque);
        }
        else if(cb_modelo.getSelectedIndex()==0){
            caso=5;
            criterioBusque=String.valueOf(cb_marca.getSelectedItem());
            criterioBusque=criterioBusque.split(" - ")[0];
            //System.out.print(criterioBusque);
        } else {
            caso =6;
            criterioBusque=String.valueOf(cb_modelo.getSelectedItem());
            criterioBusque=criterioBusque.split(" - ")[0];
            //System.out.print(criterioBusque);
        }
        
      
        BorrarTabla();
        
            System.out.println("cb_busqueda.getSelectedIndex()" + caso);
            ListarRep(sm.BuscarRep_por_(caso, criterioBusque).iterator());
         
            
            /////////////
            //List lista = sm.BuscarRepMaestro(caso, criterioBusque);
    }//GEN-LAST:event_bt_buscar1ActionPerformed

    private void cb_equipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_equipoItemStateChanged
        // TODO add your handling code here
        
        String equipoDescrElement = String.valueOf(cb_equipo.getSelectedItem());
        String equipoId ="0";
        Marcas marcaselec = new Marcas();
        //System.out.println(equipselec);
        //textidmarca.setText("");
        if (cb_equipo.getSelectedIndex() != 0) {
            equipoId = equipoDescrElement.split(" - ")[0];
            //visibilidadElementosMarca(true);
        }
            Servicio_Equipos servequip = new Servicio_Equipos(null);
            Equipos equipselec = servequip.getEquipos_por_codigo(Integer.parseInt(equipoId));
            //System.out.println(equipselec.getIdequipo());
            llenar_marcasXequipo(equipselec);        
            llenar_modelosXmarca(marcaselec);
        
            cb_marca.setSelectedIndex(0);
            cb_modelo.setSelectedIndex(0);
        
    }//GEN-LAST:event_cb_equipoItemStateChanged

    private void cb_marcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_marcaItemStateChanged
        // TODO add your handling code here:
        String marcaoDescrElement = String.valueOf(cb_marca.getSelectedItem());
        String marcaId ="0";
        //System.out.println(equipselec);
        //textidmarca.setText("");
        if (cb_marca.getSelectedIndex() != 0) {
            marcaId = marcaoDescrElement.split(" - ")[0];
        

        }
            Servicio_Marcas servmarca = new Servicio_Marcas(null);
            Marcas marcaselec = servmarca.getMarcas_por_codigo(Integer.parseInt(marcaId));
            //System.out.print(marcaId);
            llenar_modelosXmarca(marcaselec);
            //visibilidadElementosMarca(true);
        
        
            cb_modelo.setSelectedIndex(0);
    }//GEN-LAST:event_cb_marcaItemStateChanged

    private void bt_buscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscar2ActionPerformed
        // TODO add your handling code here:
        
        BorrarTabla();
        
        ListarRep(sm.Consultar_Repuestos_xFechas(txtFechaRegistroInicial.getDate(), txtFechaRegistroFinal.getDate()).iterator());
    }//GEN-LAST:event_bt_buscar2ActionPerformed

    
    private void llenar_marcasXequipo(Equipos equipo) {
        
       DefaultComboBoxModel modelMarcCombo = new DefaultComboBoxModel();
       Servicio_Marcas sm1 = new Servicio_Marcas(null);
       
       modelMarcCombo.addElement("Marcas");
       if(equipo != null){
        List<Marcas> listaMarc = sm1.buscarMarcasx_Equipo(equipo);
         for (int i = 0; i < listaMarc.size(); i++) {
             modelMarcCombo.addElement(listaMarc.get(i).getIdmarca()+" - "+listaMarc.get(i).getDescripcion());
         }
       }
        cb_marca.setModel(modelMarcCombo); 
    }
    
    
    private void llenar_modelosXmarca(Marcas marca) {
        
       DefaultComboBoxModel modelModeloCombo = new DefaultComboBoxModel();
       Servicio_Modelos smodelos = new Servicio_Modelos(null);
       
       modelModeloCombo.addElement("Modelos");
       if(marca != null) {          // si la marca existe o no esta vacia
        List<Modelos> listaModelos = smodelos.buscarModelosx_Marca(marca);
         for (int i = 0; i < listaModelos.size(); i++) {
             modelModeloCombo.addElement(listaModelos.get(i).getIdmodelo()+" - "+listaModelos.get(i).getDescripcion());
         }
       }
       cb_modelo.setModel(modelModeloCombo); 
    }
    
    
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
    private javax.swing.JButton bt_buscar1;
    private javax.swing.JButton bt_buscar2;
    private javax.swing.JButton bt_nuevoRep;
    private javax.swing.JButton bt_salir;
    private javax.swing.JComboBox cbUsuarios;
    public javax.swing.JComboBox cb_busqueda;
    public javax.swing.JComboBox cb_equipo;
    public javax.swing.JComboBox cb_marca;
    public javax.swing.JComboBox cb_modelo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_mensaje;
    public javax.swing.JTable tb_rep;
    private javax.swing.JTextField tx_busqueda;
    private com.toedter.calendar.JDateChooser txtFechaRegistroFinal;
    private com.toedter.calendar.JDateChooser txtFechaRegistroInicial;
    // End of variables declaration//GEN-END:variables
}