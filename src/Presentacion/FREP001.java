package Presentacion;

import Entidades.Control;
import Entidades.Detallees;
import Entidades.Estratificacion;
import Entidades.Equipos;
import Entidades.Marcas;
import Entidades.Modelos;
import static Entidades.Otros.Constante.INGRESO_POR_REGULARIZACION;
//import static Entidades.Otros.Constante.STOCK_MAXIMO;
import static Entidades.Otros.Constante.STOCK_MINIMO;
import Entidades.Otros.DetalleFactura;
import Entidades.Repuestos;
import Entidades.RepuestosId;
import Entidades.Sistema;
import Entidades.Usuarios;
import Mantenimiento.Navegacion;
import Servicios.HibernateUtil;
import Servicios.IngresoSalidas.Servicio_IngresoSalida;
import Servicios.Servicio_Control_Sistema;
import Servicios.Servicio_Estratificacion;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Maestros;
import Servicios.Servicio_Equipos;
import Servicios.TipoMensaje;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Marcas;
import Servicios.Servicio_Modelos;
import static java.lang.Double.parseDouble;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Keily I.
 */
public class FREP001 extends javax.swing.JFrame {


    int cant_rep;
    public int fila;
    public DefaultTableModel table;
    TipoMensaje tm;
    public Servicio_Maestros sm;
    public DefaultTableModel modelo;
    private DefaultComboBoxModel modelMarcCombo;
    private DefaultComboBoxModel modelModeloCombo;
    public boolean seleccionada;
    JTextField[] componentes;
    public final ArrayList<Integer> numMaximo;
    public final ArrayList<String> tipoDato;
    public Navegacion navegacion;
    Servicio_IngresoSalida sis;
    Sistema sistema;

    Servicio_Control_Sistema servicioControl;
    Control c = new Control();

    Servicio_Excel excel;
    
    File imagenGuardar;
    boolean habilitaBtnAdd = false;
    private JButton btnFicha1;
    private JTextField txtUsuario;
    private String nombreUsuario;
    public String roltemporal;
//    private String l;
//    private String m;
//    private String t;
    private double ttt;
    private double kkk;
    private double mmm;

    public FREP001(String username, String rolElegido) {

        nombreUsuario = username;
        roltemporal = rolElegido;
        
        System.out.println("nombre usuario : " + username);
        System.out.println("rol Elegido : " + rolElegido);
       
        initComponents();
        
        servicioControl = new Servicio_Control_Sistema(this);
        c = servicioControl.obtener_Unico_Control();
        modelo = (DefaultTableModel) tablaRepuestos.getModel();
//      txtStock.setText(STOCK_MAXIMO); 
        txtStock.setText(STOCK_MINIMO); // A petición de clientes C&S con Javier
        this.setLocationRelativeTo(null);
        tm = new TipoMensaje();
        sm = new Servicio_Maestros(this);
        Listar_Repuestos();
        table = (DefaultTableModel) tablaRepuestos.getModel();
        crearArrayComponente();
        llenar_equipos();
        llenar_Movimientos();
        numMaximo = new ArrayList<>();
        tipoDato = new ArrayList<>();
        crearArrayComponente();
        crearArrayNumMax();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, boton_Salir);
        asignarEvento();
        sis = new Servicio_IngresoSalida();
        
        //ajustarAnchoColumnas();
        alinearColumnaDerecha();
        centrarColumna();
        
        btnAddImg.setEnabled(!habilitaBtnAdd);
        btnDelImg.setEnabled(habilitaBtnAdd);
        setearImagenVacia();

 //      Botones habilitados solo para el ADMINISTRADOR
        System.out.println("usuario : " + username);

        if ("ADMINISTRADOR".equals(rolElegido) || "ALMACEN".equals(rolElegido) ||  "IMPORTACION".equals(rolElegido)) {
            boton_Agregar.setEnabled(true);
            boton_Modificar.setEnabled(true);            
            boton_Eliminar.setEnabled(true);     
            boton_Exportar.setEnabled(true);
        } else {
            /*boton_Agregar.setEnabled(false);
            boton_Modificar.setEnabled(false);            
            boton_Eliminar.setEnabled(false);            
            boton_Exportar.setEnabled(false);    */     
            boton_Agregar.setEnabled(true);
            boton_Modificar.setEnabled(true);            
            boton_Eliminar.setEnabled(true);            
            boton_Exportar.setEnabled(true);
        }
        
        txtFiltroAplica1.setVisible(false);
        filtrar_aplica1.setVisible(false);

    }

  
    private void alinearColumnaDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
//      tablaRepuestos.getColumnModel().getColumn(4).setCellRenderer(tcr);  // 
        tablaRepuestos.getColumnModel().getColumn(6).setCellRenderer(tcr);  // Stock
//      tablaRepuestos.getColumnModel().getColumn(6).setCellRenderer(tcr);  // Equipos
        tablaRepuestos.getColumnModel().getColumn(7).setCellRenderer(tcr);  // Nuevo FOB
//      tablaRepuestos.getColumnModel().getColumn(8).setCellRenderer(tcr);  // Costo Promedio
        tablaRepuestos.getColumnModel().getColumn(8).setCellRenderer(tcr);  // Precio Ultimo Costo
        tablaRepuestos.getColumnModel().getColumn(9).setCellRenderer(tcr);  // Precio Lista                
        
    }

    private void centrarColumna(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tablaRepuestos.getColumnModel().getColumn(6).setCellRenderer(tcr);  // Stock
       
    }
    
    public final void ajustarAnchoColumnas() {
        JViewport scroll = (JViewport) tablaRepuestos.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tablaRepuestos.getColumnModel();
        TableColumn columnaTabla;

        for (int i = 0; i < tablaRepuestos.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0: // N° Parte
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 1: // Codigo Sec.
                    anchoColumna = (7 * ancho) / 100;
                    break;
                case 2: // Descripción
                    anchoColumna = (16 * ancho) / 100;
                    break;
                case 3: // Aplicación 1
                    anchoColumna = (16 * ancho) / 100;
                    break;
                case 4: // Anotación 1
                    anchoColumna = (16 * ancho) / 100;
                    break;
                case 5: // Stock
                    anchoColumna = (5 * ancho) / 100;
                    break;
                case 6: // Precio Lista
                    anchoColumna = (7 * ancho) / 100;
                    break;
//              case 7: // Costo Promedio
//                  anchoColumna = (7 * ancho) / 100;
//                  break;
                case 7: // Precio Costo Ultimo
                    anchoColumna = (7 * ancho) / 100;
                    break;
                case 8: // FOB
                    anchoColumna = (7 * ancho) / 100;
                    break;
            }
            columnaTabla.setMaxWidth(anchoColumna);
            columnaTabla.setMinWidth(anchoColumna);
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }
    
    private void limpiarTabla() {
//        tablaRepuestos.setModel(new DefaultTableModel());
       for (int i = 0; i < tablaRepuestos.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
    }

    private void Listar_Repuestos() {
        limpiarTabla();

        if ("ADMINISTRADOR".equals(roltemporal)){        
        cant_rep = sm.listarRepuestos(modelo);
        cant_rep++;
        //txtId.setText("" + (sm.getUltimo_id() + 1));
        txtId.setText("" + (sm.nextId()));
        } else {
        cant_rep = sm.listarRepuestos2(modelo);
        cant_rep++;
        //txtId.setText("" + (sm.getUltimo_id() + 1));
        txtId.setText("" + (sm.nextId()));
                }
    }

    private void crearArrayNumMax() {
        numMaximo.add(30); //txtnumParte
        numMaximo.add(120);  //txtDescripcion
        numMaximo.add(60);  //txtDescripModelo
        numMaximo.add(60);  //txtDescripLista
        numMaximo.add(10);  //txtCodSegundo
        numMaximo.add(40); // Cambio de "6" a "40"  //txtAplicacion
        numMaximo.add(6);   //txtInventario
        numMaximo.add(6);   //txtStock
        numMaximo.add(5);   //txtNumAlternativo
        numMaximo.add(10);  //txtPrecioLista
        numMaximo.add(10);  //txtCostoPromedio  /txtPartidaArancel
        numMaximo.add(10);  //txtPrecioCostoUltimo /txtPartidaArancel
        numMaximo.add(10);  //txtPrecioCostoTemp
        numMaximo.add(6);   //txtStockMinimo
        numMaximo.add(17);  //txtIdAnterior
        numMaximo.add(6);   //txtMargenUtil
        numMaximo.add(10);  //txtUnidadMedida
        numMaximo.add(10);  //txtFOBUltimo /txtPartidaArancel
        numMaximo.add(6);   //txtUbicacionAlmacen
        numMaximo.add(6);   //txtUnidadVenta
        numMaximo.add(14);  //txtPartidaArancel
        //numMaximo.add(15);  //txtMarcaProveedor (eliminado)
        numMaximo.add(20);  //txtNumAlternativo1
    }

    private void crearArrayTipoDato() {
        tipoDato.add("S"); //txtnumParte
        tipoDato.add("S"); //txtDescripcion
        tipoDato.add("S");  //txtDescripModelo
        tipoDato.add("S");   //txtDescripLista
        tipoDato.add("S");   //txtCodSegundo
        tipoDato.add("S");   //txtAplicacion
        tipoDato.add("I");   //txtInventario
        tipoDato.add("I");   //txtStock
        tipoDato.add("I");   //txtNumAlternativo

        tipoDato.add("D");   //txtPrecioLista
        tipoDato.add("D");   //txtCostoPromedio  /txtPartidaArancel 
        tipoDato.add("D");   //txtPrecioCostoUltimo /txtPartidaArancel
        tipoDato.add("D");   //txtPrecioCostoTemp

        tipoDato.add("I");   //txtStockMinimo

        tipoDato.add("S");  //txtIdAnterior
        tipoDato.add("D");  //txtMargenUtil
        tipoDato.add("S");  //txtUnidadMedida
        tipoDato.add("D");  //txtFOBUltimo /txtPartidaArancel

        tipoDato.add("S");  //txtUbicacionAlmacen
        tipoDato.add("S");  //txtUnidadVenta
        tipoDato.add("S");  //txtPartidaArancel
        //tipoDato.add("S");  //txtMarcaProveedor (eliminado)
        tipoDato.add("S");  //txtNumAlternativo1      

    }

    private void asignarEvento() {
        for ( int i = 0; i < componentes.length; i++ ) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    private void crearArrayComponente() {
        componentes = new JTextField[22];
        componentes[0] = txtnumSerie;
        componentes[1] = txtDescripcion;
        componentes[2] = txtDescripModelo;
        componentes[3] = txtDescripLista;
        componentes[4] = txtCodSegundo;
        componentes[5] = txtAplicacion;
        componentes[6] = txtInventario;
        componentes[7] = txtStock;
        componentes[8] = txtNumAlternativo;
        componentes[9] = txtPrecioLista;
        
        if ("ADMINISTRADOR".equals(roltemporal)){   
         componentes[10] = txtCostoPromedio;
         } else {
         componentes[10] = txtPartidaArancel;
         }
         
        if ("ADMINISTRADOR".equals(roltemporal)){   
         componentes[11] = txtPrecioCostoUltimo;
         } else {
         componentes[11] = txtPartidaArancel;
         }        

        componentes[12] = txtPrecioCostoTemp;
        componentes[13] = txtStockMinimo;
        componentes[14] = txtIdAnterior;
        componentes[15] = txtMargenUtil;
        componentes[16] = txtUnidadMedida;

        if ("ADMINISTRADOR".equals(roltemporal)){   
         componentes[17] = txtFOBUltimo;
         } else {
         componentes[17] = txtPartidaArancel;
         }        
        
        componentes[18] = txtUbicacionAlmacen;
        componentes[19] = txtUnidadVenta;
        componentes[20] = txtPartidaArancel;
        //componentes[21] = txtMarcaProveedor;
        componentes[21] = txtNumAlternativo1;        
 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelMaestros = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtId = new javax.swing.JTextField();
        txtCodSegundo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDescripModelo = new javax.swing.JTextField();
        txtDescripLista = new javax.swing.JTextField();
        txtInventario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        comboEquipo = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtNumAlternativo = new javax.swing.JTextField();
        txtPrecioLista = new javax.swing.JTextField();
        txtCostoPromedio = new javax.swing.JTextField();
        txtPrecioCostoUltimo = new javax.swing.JTextField();
        txtPrecioCostoTemp = new javax.swing.JTextField();
        txtStockMinimo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtIdAnterior = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtMargenUtil = new javax.swing.JTextField();
        txtUnidadMedida = new javax.swing.JTextField();
        txtPartidaArancel = new javax.swing.JTextField();
        txtFOBUltimo = new javax.swing.JTextField();
        txtUbicacionAlmacen = new javax.swing.JTextField();
        txtUnidadVenta = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtnumSerie = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        comboMovimiento = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtFechaPrecioCosto = new com.toedter.calendar.JDateChooser();
        txtFechaRegistro = new com.toedter.calendar.JDateChooser();
        jLabel32 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtAplicacion = new javax.swing.JTextField();
        btnAddImg = new javax.swing.JButton();
        btnDelImg = new javax.swing.JButton();
        txtImagen = new javax.swing.JTextField();
        btnAmpImg1 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        txtNumAlternativo1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        comboMarca = new javax.swing.JComboBox();
        comboModelo = new javax.swing.JComboBox();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRepuestos = new javax.swing.JTable();
        boton_Agregar = new javax.swing.JButton();
        boton_Modificar = new javax.swing.JButton();
        boton_Eliminar = new javax.swing.JButton();
        boton_Exportar = new javax.swing.JButton();
        boton_Salir = new javax.swing.JButton();
        boton_Limpiar = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        B_Parte = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        B_Desc = new javax.swing.JTextField();
        filtrar_parte = new javax.swing.JButton();
        filtrar_desc = new javax.swing.JButton();
        txtFiltroAplica1 = new javax.swing.JTextField();
        filtrar_aplica1 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        filtrar_parte1 = new javax.swing.JButton();
        cbEquipoFiltro = new javax.swing.JComboBox();
        cbMarcaFiltro = new javax.swing.JComboBox();
        cbModeloFiltro = new javax.swing.JComboBox();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        filtrar_aplica2 = new javax.swing.JButton();
        txtFechaRegistroInicial = new com.toedter.calendar.JDateChooser();
        jLabel36 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtFechaRegistroFinal = new com.toedter.calendar.JDateChooser();
        boton_Agregar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

        txtId.setEnabled(false);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        txtCodSegundo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodSegundoActionPerformed(evt);
            }
        });

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });

        jLabel2.setText("ID Producto");

        jLabel3.setText("Cod Secundario");

        jLabel4.setText("ID Equipo");

        jLabel5.setText("ID Marca");

        jLabel6.setText("Descripcion");

        jLabel8.setText("Inventario");

        txtDescripLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripListaActionPerformed(evt);
            }
        });

        jLabel9.setText("Precio Lista");

        jLabel10.setText("Stock Central");

        txtStock.setEnabled(false);
        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });

        comboEquipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un Equipo" }));
        comboEquipo.setToolTipText("");
        comboEquipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboEquipoItemStateChanged(evt);
            }
        });

        jLabel11.setText("N° Alternativo");

        jLabel12.setText("Aplicacion 1");

        jLabel13.setText("Costo Promedio");

        jLabel14.setText("Precio Costo Ultimo");

        jLabel15.setText("Fecha P. Costo");

        jLabel16.setText("P.Costo Temporal");

        jLabel17.setText("Stock Mínimo");

        txtNumAlternativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumAlternativoActionPerformed(evt);
            }
        });

        txtCostoPromedio.setText("0");

        txtPrecioCostoUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioCostoUltimoActionPerformed(evt);
            }
        });

        txtPrecioCostoTemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioCostoTempActionPerformed(evt);
            }
        });

        jLabel18.setText("ID Anterior ");

        jLabel19.setText("Margen Util");

        jLabel20.setText("Unidad Medida");

        jLabel21.setText("Partida Arancel");

        jLabel22.setText("FOB Ultimo");

        jLabel23.setText("Ubicación");

        jLabel24.setText("Unidad de Venta");

        txtFOBUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFOBUltimoActionPerformed(evt);
            }
        });

        txtUbicacionAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUbicacionAlmacenActionPerformed(evt);
            }
        });

        jLabel25.setText("Aplicacion 2");

        jLabel1.setText("Nº de Serie");

        jLabel26.setText("Movimiento");

        jLabel27.setText("Fecha Registro");

        comboMovimiento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un movimiento" }));
        comboMovimiento.setEnabled(false);

        jLabel29.setText("( * )");

        jLabel30.setText("( * )");

        jLabel31.setText("( * )");

        txtFechaPrecioCosto.setDateFormatString("dd-MM-yyyy");

        txtFechaRegistro.setDateFormatString("dd-MM-yyyy");

        jLabel32.setText("( * )");

        jLabel35.setText("Anotacion 1");

        jLabel7.setText("ID Modelo");

        btnAddImg.setText("Agregar");
        btnAddImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddImgActionPerformed(evt);
            }
        });

        btnDelImg.setText("Eliminar");
        btnDelImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelImgActionPerformed(evt);
            }
        });

        txtImagen.setEditable(false);
        txtImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImagenActionPerformed(evt);
            }
        });

        btnAmpImg1.setText("Copia Foto");
        btnAmpImg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAmpImg1ActionPerformed(evt);
            }
        });

        jLabel38.setText("Usuario Ult. : ");

        txtNumAlternativo1.setEnabled(false);
        txtNumAlternativo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumAlternativo1ActionPerformed(evt);
            }
        });

        jPanelConFondo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanelConFondoLayout = new javax.swing.GroupLayout(jPanelConFondo);
        jPanelConFondo.setLayout(jPanelConFondoLayout);
        jPanelConFondoLayout.setHorizontalGroup(
            jPanelConFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelConFondoLayout.setVerticalGroup(
            jPanelConFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 209, Short.MAX_VALUE)
        );

        jLabel39.setText("Stock Videna");

        jLabel40.setText("Stock Gozoli");

        comboMarca.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione una Marca" }));
        comboMarca.setToolTipText("");
        comboMarca.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboMarcaItemStateChanged(evt);
            }
        });

        comboModelo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un Modelo" }));
        comboModelo.setToolTipText("");

        jLabel41.setText("( * )");

        jLabel42.setText("( * )");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel9)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addGap(1, 1, 1))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtPrecioLista, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel31))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(comboEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel32))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtnumSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel29))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(comboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel41))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel30))
                                    .addComponent(txtDescripModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDescripLista, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(comboModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel42)))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel26)
                                                .addComponent(jLabel18))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addGap(2, 2, 2))
                                            .addComponent(jLabel19))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtIdAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtPrecioCostoTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(comboMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(63, 63, 63))
                                            .addComponent(txtMargenUtil, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtUnidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtFOBUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtFechaPrecioCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addGap(83, 83, 83)
                                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(115, 115, 115))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel38)
                                                    .addComponent(jLabel3)))))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCostoPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 576, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel20)))))
                    .addComponent(txtPrecioCostoUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUbicacionAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumAlternativo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumAlternativo1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodSegundo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPartidaArancel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtAplicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(txtImagen))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnAddImg)
                                .addGap(18, 18, 18)
                                .addComponent(btnAmpImg1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDelImg)))
                        .addContainerGap())
                    .addComponent(jPanelConFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanelConFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddImg)
                            .addComponent(btnDelImg)
                            .addComponent(btnAmpImg1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(76, 76, 76)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel19)
                                                    .addComponent(txtMargenUtil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel22)
                                                    .addComponent(txtFOBUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(9, 9, 9)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel24)
                                                    .addComponent(txtUnidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel7)
                                                    .addComponent(comboModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel42))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel30)
                                                        .addComponent(jLabel6)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel35)
                                                    .addComponent(txtDescripLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(9, 9, 9)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(87, 87, 87)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel25)
                                            .addComponent(txtAplicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(txtNumAlternativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtFechaPrecioCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtnumSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrecioCostoTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20)
                                    .addComponent(txtUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel23)
                                            .addComponent(txtUbicacionAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel18)
                                                .addComponent(txtIdAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel21)
                                                .addComponent(txtPartidaArancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(comboEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel32))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5)
                                            .addComponent(comboMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel41))))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDescripModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodSegundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtCostoPromedio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel9)
                                            .addComponent(txtPrecioLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel31))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPrecioCostoUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel39)
                                    .addComponent(jLabel40)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtNumAlternativo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel38))))))
                .addContainerGap())
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tablaRepuestos.setAutoCreateRowSorter(true);
        tablaRepuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Equipo", "Marca", "Modelo", "Nº de Serie", "Descripción", "Stock", "FOB", "P. Costo Ultimo", "Precio Lista"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaRepuestos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaRepuestos.getTableHeader().setReorderingAllowed(false);
        tablaRepuestos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaRepuestosMouseReleased(evt);
            }
        });
        tablaRepuestos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaRepuestosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaRepuestos);
        if (tablaRepuestos.getColumnModel().getColumnCount() > 0) {
            tablaRepuestos.getColumnModel().getColumn(0).setPreferredWidth(80);
            tablaRepuestos.getColumnModel().getColumn(0).setMaxWidth(80);
            tablaRepuestos.getColumnModel().getColumn(4).setPreferredWidth(120);
            tablaRepuestos.getColumnModel().getColumn(4).setMaxWidth(120);
            tablaRepuestos.getColumnModel().getColumn(5).setPreferredWidth(180);
            tablaRepuestos.getColumnModel().getColumn(6).setPreferredWidth(100);
            tablaRepuestos.getColumnModel().getColumn(6).setMaxWidth(100);
            tablaRepuestos.getColumnModel().getColumn(7).setPreferredWidth(100);
            tablaRepuestos.getColumnModel().getColumn(7).setMaxWidth(100);
            tablaRepuestos.getColumnModel().getColumn(8).setPreferredWidth(100);
            tablaRepuestos.getColumnModel().getColumn(8).setMaxWidth(100);
            tablaRepuestos.getColumnModel().getColumn(9).setPreferredWidth(100);
            tablaRepuestos.getColumnModel().getColumn(9).setMaxWidth(100);
        }

        boton_Agregar.setText("Registrar");
        boton_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_AgregarActionPerformed(evt);
            }
        });

        boton_Modificar.setText("Modificar");
        boton_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_ModificarActionPerformed(evt);
            }
        });

        boton_Eliminar.setText("Eliminar");
        boton_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_EliminarActionPerformed(evt);
            }
        });

        boton_Exportar.setText("Exportar");
        boton_Exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_ExportarActionPerformed(evt);
            }
        });

        boton_Salir.setText("Salir");
        boton_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_SalirActionPerformed(evt);
            }
        });

        boton_Limpiar.setText("Limpiar");
        boton_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_LimpiarActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("MAESTRO DE PRODUCTOS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda por : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel33.setText("Nº de Serie : ");

        B_Parte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                B_ParteKeyTyped(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel34.setText("Descripción : ");

        B_Desc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_DescActionPerformed(evt);
            }
        });
        B_Desc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                B_DescKeyTyped(evt);
            }
        });

        filtrar_parte.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        filtrar_parte.setText("Filtrar");
        filtrar_parte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrar_parteActionPerformed(evt);
            }
        });

        filtrar_desc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        filtrar_desc.setText("Filtrar");
        filtrar_desc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrar_descActionPerformed(evt);
            }
        });

        txtFiltroAplica1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroAplica1ActionPerformed(evt);
            }
        });
        txtFiltroAplica1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFiltroAplica1KeyTyped(evt);
            }
        });

        filtrar_aplica1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        filtrar_aplica1.setText("Filtrar");
        filtrar_aplica1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrar_aplica1ActionPerformed(evt);
            }
        });

        jLabel37.setText("Equipo:");

        filtrar_parte1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        filtrar_parte1.setText("Filtrar");
        filtrar_parte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrar_parte1ActionPerformed(evt);
            }
        });

        cbEquipoFiltro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un Equipo" }));
        cbEquipoFiltro.setToolTipText("");
        cbEquipoFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEquipoFiltroItemStateChanged(evt);
            }
        });

        cbMarcaFiltro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione una Marca" }));
        cbMarcaFiltro.setToolTipText("");
        cbMarcaFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMarcaFiltroItemStateChanged(evt);
            }
        });

        cbModeloFiltro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione un Modelo" }));
        cbModeloFiltro.setToolTipText("");
        cbModeloFiltro.setMinimumSize(new java.awt.Dimension(128, 20));
        cbModeloFiltro.setPreferredSize(new java.awt.Dimension(128, 20));

        jLabel43.setText("Marca:");

        jLabel44.setText("Modelo:");

        filtrar_aplica2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        filtrar_aplica2.setText("Filtrar");
        filtrar_aplica2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrar_aplica2ActionPerformed(evt);
            }
        });

        txtFechaRegistroInicial.setDateFormatString("dd-MM-yyyy");

        jLabel36.setText("Fecha de Registro:   De ");

        jLabel45.setText("a");

        txtFechaRegistroFinal.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaRegistroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFechaRegistroFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(filtrar_aplica2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtFiltroAplica1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filtrar_aplica1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(813, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B_Parte, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filtrar_parte, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addGap(2, 2, 2)
                .addComponent(cbEquipoFiltro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel43)
                .addGap(2, 2, 2)
                .addComponent(cbMarcaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbModeloFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filtrar_parte1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(filtrar_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_Parte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(B_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filtrar_parte, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filtrar_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel37)
                    .addComponent(filtrar_parte1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEquipoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMarcaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbModeloFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel36))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel45)
                                    .addComponent(txtFechaRegistroFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFechaRegistroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFiltroAplica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(filtrar_aplica1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(filtrar_aplica2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        boton_Agregar1.setText("Agregar e Ingresar");
        boton_Agregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_Agregar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMaestrosLayout = new javax.swing.GroupLayout(panelMaestros);
        panelMaestros.setLayout(panelMaestrosLayout);
        panelMaestrosLayout.setHorizontalGroup(
            panelMaestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMaestrosLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelMaestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(panelMaestrosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMaestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMaestrosLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(boton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(boton_Agregar1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(boton_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(boton_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(boton_Exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(boton_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(boton_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        panelMaestrosLayout.setVerticalGroup(
            panelMaestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMaestrosLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel28)
                .addGap(40, 40, 40)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addGroup(panelMaestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMaestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(boton_Limpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(boton_Salir))
                    .addComponent(boton_Exportar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(boton_Eliminar, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMaestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(boton_Agregar)
                        .addComponent(boton_Agregar1)
                        .addComponent(boton_Modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMaestros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelMaestros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String validarEntradasxAgregar() {
        String mensaje = "";

        
        if ( txtnumSerie.getText().equals("") ) {
            mensaje = mensaje + "FALTA NUMERO DE SERIE";
        }
        
        if ( sm.GetRepuesto_Codigo(txtnumSerie.getText())!= null ) {
            mensaje = mensaje + "EL NUMERO DE SERIE YA EXISTE";
        }
        
        if ( txtDescripcion.getText().equals("") ) {
            mensaje = mensaje + "\nFALTA DESCRIPCION";
        }
        if ( comboEquipo.getSelectedIndex() == 0 ) {
            mensaje = mensaje + "\nFALTA SELECCIONAR EQUIPO";
        }
        if ( comboMarca.getSelectedIndex() == 0 ) {
            mensaje = mensaje + "\nFALTA SELECCIONAR MARCA";
        }
        if ( comboModelo.getSelectedIndex() == 0 ) {
            mensaje = mensaje + "\nFALTA SELECCIONAR MODELO";
        }
        if ( txtPrecioLista.getText().equals("") ) {
            mensaje = mensaje + "\nFALTA INGRESAR PRECIO LISTA";
            kkk = 0;
        } else {
            kkk = parseDouble(txtPrecioLista.getText());
        }

// JSP 16.12.2023
        
        if(txtFOBUltimo.getText().equals("")) {
            ttt = 0;
        } else {
        ttt = parseDouble(txtFOBUltimo.getText());
        }
        
        if(txtPrecioCostoUltimo.getText().equals("")) {
            mmm = 0;
        } else {
        mmm = parseDouble(txtPrecioCostoUltimo.getText());
        }
        

        if ( kkk ==  0) {
            mensaje = mensaje + "\nPRECIO DE LISTA NO PUEDE SER 0";
        }                                
        if ( kkk < ttt) {
            mensaje = mensaje + "\nPRECIO FOB NO PUEDE SER MAYOR AL PRECIO LISTA";
        }                
        if ( kkk < (mmm * 1.23)) {  // (Factor brindado por Eduardo)        
            mensaje = mensaje + "\nPRECIO COSTO + 23% NO PUEDE SER MAYOR AL PRECIO LISTA";
        }                
        if ( mmm < (ttt * 1.25)) {  // (Factor brindado por Eduardo)        
            mensaje = mensaje + "\nPRECIO FOB + 25% NO PUEDE SER MAYOR AL PRECIO COSTO";
        }                        
        if ( mmm ==  0) {
            mensaje = mensaje + "\nPRECIO DE COSTO NO PUEDE SER 0";
        }                        
// JSP 16.12.2023 (FIN)

//        if (txtCostoPromedio.getText().equals("")) {
//            mensaje = mensaje + "\nFALTA INGRESAR COSTO PROMEDIO";
//        }

        return mensaje;
    }

    private String validarEntradasxModificar() {
        String mensaje = "";

        if ( txtnumSerie.getText().equals("")) {
            mensaje = mensaje + "FALTA NUMERO DE PARTE";
        }
        
        
        if ( txtDescripcion.getText().equals("") ) {
            mensaje = mensaje + "\nFALTA DESCRIPCION";
        }

        if ( comboEquipo.getSelectedIndex() == 0 ) {
            mensaje = mensaje + "\nFALTA SELECCIONAR EQUIPO";
        }
        
        if ( comboMarca.getSelectedIndex() == 0 ) {
            mensaje = mensaje + "\nFALTA SELECCIONAR MARCA";
        }
        if ( comboModelo.getSelectedIndex() == 0 ) {
            mensaje = mensaje + "\nFALTA SELECCIONAR MODELO";
//        if ( comboMovimiento.getSelectedIndex() == 0 || comboMovimiento.getSelectedIndex() == 1 ) {
//            mensaje = mensaje + "\nFALTA SELECCIONAR MOVIMIENTO";
//        }
//        if ( txtCostoPromedio.getText().equals("") ) {
//            mensaje = mensaje + "\nFALTA INGRESAR COSTO PROMEDIO";
//        }
        }
        if ( txtPrecioLista.getText().equals("")) {
            mensaje = mensaje + "\nFALTA INGRESAR PRECIO LISTA";
        kkk = 0;
        } else {
            kkk = parseDouble(txtPrecioLista.getText());
        }


        
// JSP 16.12.2023
        if(txtFOBUltimo.getText().equals("")) {
            ttt = 0;
        } else {
        ttt = parseDouble(txtFOBUltimo.getText());
        }
        
        if(txtPrecioCostoUltimo.getText().equals("")) {
            mmm = 0;
        } else {
        mmm = parseDouble(txtPrecioCostoUltimo.getText());
        }
        
        if ( kkk ==  0) {
            mensaje = mensaje + "\nPRECIO DE LISTA NO PUEDE SER 0";
        }                                
        if ( kkk < ttt) {
            mensaje = mensaje + "\nPRECIO FOB NO PUEDE SER MAYOR AL PRECIO LISTA";
        }                
        if ( kkk < (mmm * 1.23)) {  // (Factor brindado por Eduardo)        
            mensaje = mensaje + "\nPRECIO COSTO + 23% NO PUEDE SER MAYOR AL PRECIO LISTA";
        }                
        if ( mmm < (ttt * 1.25)) {  // (Factor brindado por Eduardo)        
            mensaje = mensaje + "\nPRECIO FOB + 25% NO PUEDE SER MAYOR AL PRECIO COSTO";
        }                                
        if ( mmm ==  0) {
            mensaje = mensaje + "\nPRECIO DE COSTO NO PUEDE SER 0";
        }                        
// JSP 16.12.2023 (FIN)
        
//                    if ( k == null ) {
//                        rep.setPreciolista(0.00);
//                    } else {
//                        rep.setPreciolista(Double.parseDouble(k));
//                    }        
        
        
        return mensaje;
    }
    
    private String ValidarEntradasxFechas() {
        String mensaje = "VALIDO";
        
        if(txtFechaRegistroFinal.getDate() == null || txtFechaRegistroInicial.getDate() == null) {
             mensaje = "\n-SELECCIONE FECHA DE INICIAL Y FECHA FINAL";
        }
        
        else if( txtFechaRegistroInicial.getDate().compareTo(txtFechaRegistroFinal.getDate()) > 0   ) {
            mensaje = "\n-LA FECHA INICIAL DEBE SER MENOR O IGUAL QUE LA FINAL";
        }
        
        
        return mensaje;
    }

    private boolean esRepuestoRepetido(String numeroParte) {
        return ( sm.GetRepuesto_Codigo(numeroParte) != null );
    }
    
    public String fechasistema() {
        Date ahora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(ahora);
    }
    
    public Date fechasistemaDate() {
        
        Calendar today = Calendar.getInstance();
        today.set(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);
        System.out.println(today);
        //Date ahora = today.getTime();
        
        Date ahora = new Date();
        System.out.println(ahora);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return ahora;
    }
    
    private void boton_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_AgregarActionPerformed

        if ( seleccionada == false ) {
            String validacion = validarEntradasxAgregar();

            if ( !validacion.equals("") ) {
                JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);
                
            }/* else if ( esRepuestoRepetido(txtnumParte.getText()) ) {
                JOptionPane.showMessageDialog(null, "El N° de Serie ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                // Obviar porque en equipos se pueden repetir
            } */else {
                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operación?", "CONFIRMACIÓN", 0);
                
                if ( verif == 0 ) {

                    String id = txtId.getText();
                    String a = txtnumSerie.getText();
                    String b = txtDescripcion.getText();
                    String c = txtDescripModelo.getText();
                    String d = txtDescripLista.getText();
                    //String d2 = txtDescripLista2.getText();
                    String e = txtCodSegundo.getText();
                    String f = txtAplicacion.getText();
                    String g = txtInventario.getText();

                    String h = comboEquipo.getSelectedItem().toString();
                    String j = txtNumAlternativo.getText();
                    String k = txtPrecioLista.getText();
                    String l = txtCostoPromedio.getText();
                    String m = txtPrecioCostoUltimo.getText();
                    Date fechaPreCosto = txtFechaPrecioCosto.getDate();
                    String n = txtPrecioCostoTemp.getText();
                    String o = txtStockMinimo.getText();
                    String p = comboMovimiento.getSelectedItem().toString();
                    String q = txtIdAnterior.getText();
                    String r = txtMargenUtil.getText();
                    String s = txtUnidadMedida.getText();
                    String t = txtFOBUltimo.getText();
                    String u = txtUbicacionAlmacen.getText();
                    String v = txtUnidadVenta.getText();
                    String w = txtPartidaArancel.getText();
                    //String x = txtMarcaProveedor.getText();
                    txtFechaRegistro.setDate(fechasistemaDate());
                    Date fechaRegistro = txtFechaRegistro.getDate();
                    String xx = txtNumAlternativo1.getText();
                    xx = nombreUsuario;
                    
                    String stock = txtStock.getText();
//                    System.out.println("stock:" + stock);

                    Servicio_Equipos li = new Servicio_Equipos(null);
                    Servicio_Estratificacion es = new Servicio_Estratificacion();
                    h = h.split(" - ", 2)[0];
                    Equipos equipos = li.getEquipos_por_codigo(Integer.valueOf(h));
                    Estratificacion estr = es.getEstratificacion_por_nombre("N");
                    
                    Servicio_Marcas ma = new Servicio_Marcas(null);
                    String hi = comboMarca.getSelectedItem().toString();
                    hi = hi.split(" - ", 2)[0];
                    Marcas marcas = ma.getMarcas_por_codigo(Integer.valueOf(hi)); 
                    
                    Servicio_Modelos mo = new Servicio_Modelos(null);
                    String hj = comboModelo.getSelectedItem().toString();
                    hj = hj.split(" - ", 2)[0];
                    Modelos modelos = mo.getModelos_por_codigo(Integer.valueOf(hj));
                    

                    int numlin = equipos.getIdequipo();
                    int nummarc = marcas.getIdmarca();
                    int nummodel = modelos.getIdmodelo();
                    Repuestos rep = new Repuestos();
                    RepuestosId repid = new RepuestosId();

                    repid.setIdequipo(numlin);
                    repid.setIdmarca(nummarc);
                    repid.setIdmodelo(nummodel);
                    repid.setIdrepuesto(Integer.parseInt(id));

                    rep.setId(repid);
                    rep.setCodrepuesto(a);
                    rep.setDescripcion(b);
                    rep.setDescrmodelo(c);
                    rep.setDesclista(d);
                    rep.setMarca(marcas.getDescripcion());
                    //rep.setDesclista2(d2);
                    rep.setCodigoseg(e);
                    rep.setAplicacion(f);
                    
                    if ( !g.equals("") ) {
                        rep.setInventario(Integer.parseInt(g));
                    }
                    
//                    if ( STOCK_MAXIMO.equals(stock) ) {
//                    if ( STOCK_MINIMO.equals(stock) ) {
//                        rep.setStock(Integer.parseInt(stock));
//                    }
// Javier Salas (25/06/2022) - Tema de actualiza Stock en MyD
//                    else {
                    rep.setStock(0);
//                    }

                    if ( !j.equals("") ) {
                        rep.setNroalternativo(Integer.parseInt(j));
                    }

                    rep.setEquipos(equipos);
                    rep.setMarcas(marcas);
                    rep.setModelos(modelos);

                    if ( !k.equals("") ) {
                        rep.setPreciolista(Double.parseDouble(k));
                    }
                    
                    if ( !l.equals("") ) {
                        rep.setCostopromedio(Double.parseDouble(l));
                        
                    } else {
                        rep.setCostopromedio(0.00);
                    }
                    
                    if ( !m.equals("") ) {
                        rep.setPcostoultimo(Double.parseDouble(m));
                    }

                    rep.setFechapcosto(fechaPreCosto);
                    if ( !n.equals("") ) {
                        rep.setPcostotemporal(Double.parseDouble(n));
                    }
                    
                    if ( !o.equals("") ) {
                        rep.setStockminimo(Integer.parseInt(o));
                    }

                    rep.setEstratificacion(estr);
                    rep.setIdrepuestoant(q);
                    if ( !r.equals("") ) {
                        rep.setMargenutil(Double.parseDouble(r));
                    }
                    rep.setUnidadmedida(s);
                    if ( !t.equals("") ) {
                        rep.setFobultimo(Double.parseDouble(t));
                     } else {
                        rep.setFobultimo(0.00);
                        t="0.0";
                    }                        
 
                    rep.setUbicalmacen(u);
                    rep.setUnidadventa(v);
                    rep.setPartidarancel(w);
                    //rep.setMarca(x);
                    rep.setFecharegistro(fechaRegistro);
                    
                    String img = null;
                    if ( !"".equals(txtImagen.getText()) ) {
                        img = txtImagen.getText();
                    }
                    rep.setImagen(img);
                    rep.setUsuario(xx);
                    
                    System.out.println(fechaRegistro.toString());
                    System.out.println(rep.getFecharegistro().toString());
                    
                    if ( sm.addRepuestos(rep) ) {
                        JOptionPane.showMessageDialog(null, "Se registró el repuesto");
                        Object[] row = {rep.getId().getIdrepuesto(), equipos.getDescripcion(), marcas.getDescripcion(), modelos.getDescripcion(),a, b, stock,  t, m, k};
                        //Object[] row = {a, e, b, stock, k, l, m, n};
                        modelo.insertRow(0, row);
                        clean();
                        cargarImagen(null); // Desaparece imagen y limpia su nombre
                        txtId.setText(String.valueOf(sm.nextId()));
                        txtNumAlternativo1.setText(rep.getUsuario());
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en la inserción");
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "No se puede registrar un repuesto que ya existe!", "Error al agregar", 0);
        }

    }//GEN-LAST:event_boton_AgregarActionPerformed

    private void tablaRepuestosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaRepuestosKeyReleased
        //txtnumSerie.setEditable(false);
        
       seleccionada = true;
        if ( seleccionada == false ) {
            comboMovimiento.setEnabled(true);
            txtStock.setEnabled(true);
            txtNumAlternativo1.setEnabled(true);
        }
        fila = tablaRepuestos.getSelectedRow();

        String a = tablaRepuestos.getValueAt(fila, 0).toString();
        Repuestos rep = sm.GetRepuesto_Id(Integer.parseInt(a));
        //int id = sm.GetRepuesto_Id(Integer.parseInt(a)).getId().getIdrepuesto();
        int id =rep.getId().getIdrepuesto();
        txtId.setText(String.valueOf(id));
        txtnumSerie.setText(rep.getCodrepuesto());
        txtDescripcion.setText(tablaRepuestos.getValueAt(fila, 5).toString());
        txtDescripModelo.setText(rep.getDescrmodelo());
        txtDescripLista.setText(rep.getDesclista());
        //txtDescripLista2.setText(rep.getDesclista2());
        
//        if ( tablaRepuestos.getValueAt(fila, 1) == null ) {
//            txtCodSegundo.setText("");
//        } else {
            String codSec = rep.getCodigoseg();
            //txtCodSegundo.setText(tablaRepuestos.getValueAt(fila, 1).toString());
            txtCodSegundo.setText(codSec);
//        }
        
        txtAplicacion.setText(rep.getAplicacion());
        if ( rep.getInventario() == null ) {
            txtInventario.setText(String.valueOf(0));
        } else {
            txtInventario.setText(String.valueOf(rep.getInventario()));
        }

        if ( tablaRepuestos.getValueAt(fila,6) == null ) {
            txtStock.setText(String.valueOf(0));
        } else {
            txtStock.setText(tablaRepuestos.getValueAt(fila, 6).toString());
        }

        String linea;
        int codlin;
        int codmarca;
        String marca;
        int codmodelo;
        String modelo1;
        if ( rep.getEquipos() == null ) {
            comboEquipo.setSelectedIndex(0);
        } else {
            linea = rep.getEquipos().getDescripcion();
            codlin = rep.getEquipos().getIdequipo();
            
            codmarca = rep.getMarcas().getIdmarca();
            marca = rep.getMarcas().getDescripcion();
            
            codmodelo = rep.getModelos().getIdmodelo();
            modelo1 = rep.getModelos().getDescripcion();
            comboEquipo.setSelectedItem(codlin + " - " + linea);
            comboMarca.setSelectedItem(codmarca + " - " + marca);
            comboModelo.setSelectedItem(codmodelo + " - " + modelo1);
            //comboModelo.setSelectedItem(codmodelo + "-" + modelo1);
            //System.out.print(comboEquipo.getSelectedItem().toString());
            /*System.out.print(codlin + " - " + linea);
            System.out.print("\n"+codmarca + " - " + marca);
            System.out.print("\n"+codmodelo + " - " + modelo1);*/
        }
        

        if (rep.getNroalternativo() == null) {
            txtNumAlternativo.setText(String.valueOf(0));
        } else {
            txtNumAlternativo.setText(String.valueOf(rep.getNroalternativo()));
        }
        //Precio Lista
        if ( tablaRepuestos.getValueAt(fila, 9) == null ) {
            txtPrecioLista.setText(String.valueOf(0.0));
        } else {
            txtPrecioLista.setText(tablaRepuestos.getValueAt(fila, 9).toString());
        }
        //Costo Promedio
        if ( rep.getCostopromedio() == null) {
            txtCostoPromedio.setText(String.valueOf(0.0));
        } else {
            //System.out.print(rep.getCostopromedio());
            txtCostoPromedio.setText(String.valueOf(rep.getCostopromedio()));     
        }

        if ("ADMINISTRADOR".equals(roltemporal)){
        
        }else{   
            txtCostoPromedio.setText(String.valueOf(0.0));
         }
        
        //Precio Costo Ultimo
        if ( rep.getPcostoultimo() == null ) {
            txtPrecioCostoUltimo.setText(String.valueOf(0.0));
        } else {
            txtPrecioCostoUltimo.setText(String.valueOf(rep.getPcostoultimo()));
        }
         if ("ADMINISTRADOR".equals(roltemporal))
         {
         } else
         {   
            txtPrecioCostoUltimo.setText(String.valueOf(0.0));
         }        

        txtFechaPrecioCosto.setDate(rep.getFechapcosto());

        // FOB
        if ( tablaRepuestos.getValueAt(fila, 7) == null ) {
            txtFOBUltimo.setText(String.valueOf(0.0));
        } else {
            txtFOBUltimo.setText(tablaRepuestos.getValueAt(fila, 7).toString());
        }
         if ("ADMINISTRADOR".equals(roltemporal)){
         }else{   
            txtFOBUltimo.setText(String.valueOf(0.0));
         }        
        
        //Precio Costo Temporal
        //txtPrecioCostoTemp.setText(String.valueOf(rep.getPcostotemporal()));
        if ( rep.getPcostotemporal() == null ) {
            txtPrecioCostoTemp.setText(String.valueOf(0.0));
        } else {
           txtPrecioCostoTemp.setText(String.valueOf(rep.getPcostotemporal()));
        }

        if ( rep.getStockminimo() == null ) {
            txtStockMinimo.setText(String.valueOf(0));
        } else {
            txtStockMinimo.setText(String.valueOf(rep.getStockminimo()));
        }

        String mov;
        String codmov;
        if ( rep.getEstratificacion() == null ) {
            mov = "";
            comboMovimiento.setSelectedIndex(0);
        } else {
            mov = rep.getEstratificacion().getDescripcion();
            codmov = rep.getEstratificacion().getCodigoestratificacion();
            comboMovimiento.setSelectedItem(codmov + " - " + mov);
        }

        txtIdAnterior.setText(rep.getIdrepuestoant());

        if ( rep.getMargenutil() == null ) {
            txtMargenUtil.setText(String.valueOf(0.0));
        } else {
            txtMargenUtil.setText(String.valueOf(rep.getMargenutil()));
        }

        txtUnidadMedida.setText(rep.getUnidadmedida());

        if ( rep.getFobultimo() == null ) {
            txtFOBUltimo.setText(String.valueOf(0.0));
        } else {
            txtFOBUltimo.setText(String.valueOf(rep.getFobultimo()));
        }

        txtUbicacionAlmacen.setText(rep.getUbicalmacen());
        txtUnidadVenta.setText(rep.getUnidadventa());
        txtPartidaArancel.setText(rep.getPartidarancel());
        //txtMarcaProveedor.setText(rep.getMarca());
        txtFechaRegistro.setDate(rep.getFecharegistro());

        //System.out.println("Aqui puede ser : " + rep.getModelos().getDescripcion());
        if (rep.getUsuario() == null) {
            txtNumAlternativo1.setText(String.valueOf(0));
        } else {
            txtNumAlternativo1.setText(rep.getUsuario());
        }
        
        
        // Cargar imagen
//        System.out.println("(tablaRepuestosKeyReleased)");
        cargarImagen(rep);
       

    }//GEN-LAST:event_tablaRepuestosKeyReleased

    private void cargarImagen(Repuestos rep) {
        String nombreImagen = null;
        if ( rep != null ) {
            nombreImagen = rep.getImagen();
        }

        if ( !"null".equals(nombreImagen) ) {
            String rutaImagen = c.getRutaimagenes() + "\\" + nombreImagen;
            Image imagenExterna = new ImageIcon(rutaImagen).getImage();
            jPanelConFondo.setImagen2(imagenExterna);
            jPanelConFondo.revalidate();
            jPanelConFondo.repaint();
            jPanelConFondo.updateUI();
            repaint();

            txtImagen.setText(nombreImagen);
        }

//        System.out.println("nombreImagen:" + nombreImagen);
//        System.out.println("nombreImagen != null:" + ("".equals(nombreImagen)));
//        System.out.println("nombreImagen != null:" + (nombreImagen != null));
        if ( nombreImagen != null ) {
            habilitaBtnAdd = false;

        } else {
            setearImagenVacia();
            habilitaBtnAdd = true;
        }
        setBotonesImagen(habilitaBtnAdd);
    }
    
    private void setearImagenVacia() {
        String rutaImagen = c.getRutaimagenes() + "\\vacio.jpg";
        //System.out.println("rutaImagen:" + rutaImagen);

        Image imagenExterna = new ImageIcon(rutaImagen).getImage();
        jPanelConFondo.setImagen2(imagenExterna);
        jPanelConFondo.revalidate();
        jPanelConFondo.repaint();
        jPanelConFondo.updateUI();
        repaint();

        txtImagen.setText("");
    }
    
    private void setBotonesImagen(boolean habilita) {
        btnAddImg.setEnabled(habilita);
        btnDelImg.setEnabled(!habilita);
    }
    
    private void boton_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_LimpiarActionPerformed
        clean();
        tablaRepuestos.clearSelection();
        comboMovimiento.setEnabled(false);
        txtStock.setEnabled(false);
        txtNumAlternativo1.setEnabled(false);
        txtStock.setText("0");
        txtId.setText(String.valueOf(sm.nextId()));
        
        B_Parte.setText("");
        B_Desc.setText("");
        
        Equipos equipo = new Equipos();
        Marcas marca = new Marcas();
        //Modelos modelo1 = new Modelos();
        
        cbEquipoFiltro.setSelectedIndex(0);
        cbMarcaFiltro.setSelectedIndex(0);
        cbModeloFiltro.setSelectedIndex(0);
        
        llenar_marcasXequipoxFiltro(equipo);
        llenar_modelosXmarcaxFiltro(marca);
        
        seleccionada = false;
        
        setearImagenVacia();
//        String ruta = c.getRutaimagenes();
//        String rutaImgVacia = ruta + "\\vacio.jpg";
//        Image imagenExterna = new ImageIcon(rutaImgVacia).getImage();
//        jPanelConFondo.setImagen2(imagenExterna); //null
//        jPanelConFondo.revalidate();
//        jPanelConFondo.repaint();
//        jPanelConFondo.updateUI();
//        repaint();

        txtImagen.setText("");
        
        habilitaBtnAdd = true;
        setBotonesImagen(habilitaBtnAdd);
        Listar_Repuestos();
    }//GEN-LAST:event_boton_LimpiarActionPerformed

//    private boolean codRepuestoModificado() {
//        boolean modificado = false;
//        fila = tablaRepuestos.getSelectedRow();
//        String codRepSelect = tablaRepuestos.getValueAt(fila, 0).toString();
//        
//        String numParteNuevo = txtnumParte.getText();
//        if ( !codRepSelect.equals(numParteNuevo) ) {
//            modificado = true;
//        }
//        return modificado;
//    }
    private void boton_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_ModificarActionPerformed
        if ( seleccionada == true ) {
            
//          System.out.println(" " + txtRolUsuario);
            
            txtStock.setEditable(true);
            txtNumAlternativo1.setEditable(true);                                
            String validacion = validarEntradasxModificar();

            if ( !validacion.equals("") ) {
                JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);

            } else {
                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operación?", "CONFIRMACIÓN", 0);
                if ( verif == 0 ) {
                    DefaultTableModel mod = (DefaultTableModel) tablaRepuestos.getModel();

                    int oa = Integer.parseInt(txtId.getText());
                    String a = txtnumSerie.getText();
                    String b = txtDescripcion.getText();
                    String c = txtDescripModelo.getText();
                    String d = txtDescripLista.getText();
                    //String d2 = txtDescripLista2.getText();
                    String e = txtCodSegundo.getText();
                    String f = txtAplicacion.getText();
                    String g = txtInventario.getText();
                    String stock = txtStock.getText();
                    String equipo = comboEquipo.getSelectedItem().toString();
                    String j = txtNumAlternativo.getText();
                    String k = txtPrecioLista.getText();
                    
                    String l = txtCostoPromedio.getText();
                    String m = txtPrecioCostoUltimo.getText();
                                        
                    Date fechaPreCosto = txtFechaPrecioCosto.getDate();
                    String n = txtPrecioCostoTemp.getText();
                    String o = txtStockMinimo.getText();
                    String p = comboMovimiento.getSelectedItem().toString();
                    String q = txtIdAnterior.getText();
                    String r = txtMargenUtil.getText();
                    String s = txtUnidadMedida.getText();
                    
                    String t = txtFOBUltimo.getText();
                    
                    String u = txtUbicacionAlmacen.getText();
                    String v = txtUnidadVenta.getText();
                    String w = txtPartidaArancel.getText();
                    //String x = txtMarcaProveedor.getText();
                    Date fechaRegistro = txtFechaRegistro.getDate();
                    String xx = txtNumAlternativo1.getText();                    
                    
                    Servicio_Estratificacion es = new Servicio_Estratificacion();

                    if ( !p.equalsIgnoreCase("Seleccione un movimiento") ) {
                        p = p.split(" - ", 2)[1];                        
                    }

                    Servicio_Equipos lin = new Servicio_Equipos(null);
                    equipo = equipo.split(" - ", 2)[1];

                    Estratificacion estr = es.getEstratificacion_por_descripcion(p);
                    Equipos li = lin.buscarEquiposx_Nombre(equipo);

                    Servicio_Marcas ma = new Servicio_Marcas(null);
                    String hi = comboMarca.getSelectedItem().toString();
                    hi = hi.split(" - ", 2)[0];
                    int inthi = Integer.valueOf(hi);
                    Marcas marcas = ma.getMarcas_por_codigo(inthi); 
                    
                    Servicio_Modelos mo = new Servicio_Modelos(null);
                    String hj = comboModelo.getSelectedItem().toString();
                    hj = hj.split(" - ", 2)[1];
                    Modelos modelos = mo.buscarModelosx_Nombre(hj);
                    
                    
                    Repuestos rep = sm.GetRepuesto_Id(oa);
                    
                    rep.setCodrepuesto(a);
                    rep.setDescripcion(b);
                    rep.setDescrmodelo(c);
                    rep.setDesclista(d);
                    //rep.setDesclista2(d2);
                    rep.setCodigoseg(e);
                    rep.setAplicacion(f);
                    //rep.setMarca(marcas.getDescripcion());
                    rep.setInventario(Integer.parseInt(g));
                    //rep.setStock(Integer.parseInt(stock));
                    //rep.setEquipos(li);
                    //rep.setMarcas(marcas);
                    //rep.setModelos(modelos);
                    rep.setNroalternativo(Integer.parseInt(j));
                    
                    if ( k == null ) {
                        rep.setPreciolista(0.00);
                    } else {
                        rep.setPreciolista(Double.parseDouble(k));
                    }

                    if ( !l.equals("") ) {
                        rep.setCostopromedio(Double.parseDouble(l));
                    } else {
                        rep.setCostopromedio(0.00);
                    }

                    if ( !m.equals("") ) {
                        rep.setPcostoultimo(Double.parseDouble(m));
                    }

                    if ( fechaPreCosto != null ) {
                        rep.setFechapcosto(fechaPreCosto);
                    }
                    /*
                    System.out.println("n:" + n);
                    System.out.println("verdad 1?" + ( !" ".equals(n) ));
                    System.out.println("verdad 2?" + ( !"".equals(n) ));
                    */
                    
                    if ( !" ".equals(n) && !"".equals(n)) {
                    //if ( !n.equals("") ) {
                        rep.setPcostotemporal(Double.parseDouble(n));
                    }

                    rep.setStockminimo(Integer.parseInt(o));

                    if ( estr != null ) {
                        rep.setEstratificacion(estr);
                    }

                    rep.setIdrepuestoant(q);
                    rep.setMargenutil(Double.parseDouble(r));
                    rep.setUnidadmedida(s);
                    rep.setFobultimo(Double.parseDouble(t));
                    rep.setUbicalmacen(u);
                    rep.setUnidadventa(v);
                    rep.setPartidarancel(w);
                    //rep.setMarca(x);

                    if ( fechaRegistro != null ) {
                        rep.setFecharegistro(fechaRegistro);
                    }
                    
                    String img = null;
                    if ( !"".equals(txtImagen.getText()) ) {
                        img = txtImagen.getText();
                    }
                    rep.setImagen(img);
                    
                    rep.setUsuario(nombreUsuario);
                    

                    if ( sm.actualizarRepuestos(rep) ) {
                        JOptionPane.showMessageDialog(null, "Se modificó el Repuesto");
                        mod.setValueAt(rep.getId().getIdrepuesto(), fila, 0);  //Id Producto
                        mod.setValueAt(rep.getEquipos().getDescripcion(), fila, 1);                         // Equipo
                        mod.setValueAt(rep.getMarca(), fila, 2);                         // Marca
                        mod.setValueAt(rep.getModelos().getDescripcion(), fila, 3);                         // Modelo
                        mod.setValueAt(a, fila, 4);                         // N° de Parte (Serie)
                        mod.setValueAt(b, fila, 5);                     // Descripcion
                        //mod.setValueAt(d, fila, 5);                         // Anotación 1
                        //mod.setValueAt(c, fila, 6);                         // Aplicacion 1
                        mod.setValueAt(stock, fila, 6);                         // Stock
                        mod.setValueAt(t, fila, 7);                         // FOB
                        mod.setValueAt(m, fila, 8);                     //P.Costo Último
                        mod.setValueAt(k, fila, 9);                     //Precio Lista
                        seleccionada = false;
                        Listar_Repuestos();
                        txtNumAlternativo1.setText(nombreUsuario);

                    } else {
                        JOptionPane.showMessageDialog(null, "Error en la actualización");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN REPUESTO", "", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_boton_ModificarActionPerformed

    private void boton_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_EliminarActionPerformed
        if ( seleccionada == true ) {
            if ( JOptionPane.showConfirmDialog(this, "¿ Desea continuar con la Operación ?", "Confirmación", JOptionPane.YES_NO_OPTION)
                    == JOptionPane.YES_OPTION ) {

                int idRep = Integer.parseInt(txtId.getText());
                Repuestos r = sm.GetRepuesto_Id(idRep);

                if ( sm.borrarRepuestos(r) ) {
                    JOptionPane.showMessageDialog(null, "Operación exitosa");
                    table.removeRow(fila);
                    tablaRepuestos.clearSelection();
                    comboMovimiento.setEnabled(false);
                    txtStock.setEnabled(false);
                    txtNumAlternativo1.setEnabled(false);
                    seleccionada = false;
                    clean();
                    Listar_Repuestos();
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR EN LA OPERACIÓN");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONE UN REPUESTO", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_boton_EliminarActionPerformed

    public boolean eliminar(int id, int idlinea) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        RepuestosId r = new RepuestosId();
        r.setIdequipo(idlinea);
        r.setIdrepuesto(id);
        Repuestos re = new Repuestos();
        re.setId(r);

        try {
            session.beginTransaction();
            session.delete(re);
            session.getTransaction().commit();
            return true;
            
        } catch (HibernateException e) {
            session.beginTransaction().rollback();
            return false;
        }
    }

    private void tablaRepuestosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRepuestosMouseReleased
        //txtnumSerie.setEditable(false);
        
        seleccionada = true;
        if ( seleccionada == false ) {
            comboMovimiento.setEnabled(true);
            txtStock.setEnabled(true);
            txtNumAlternativo1.setEnabled(true);
        }
        fila = tablaRepuestos.getSelectedRow();
        String a = tablaRepuestos.getValueAt(fila, 0).toString();
        Repuestos rep = sm.GetRepuesto_Id(Integer.parseInt(a));
        System.out.print("Id del repuesto seleccionado "+rep);
        
        int id = rep.getId().getIdrepuesto();
        txtNumAlternativo1.setText(rep.getUsuario());
        
        txtId.setText(String.valueOf(id));
        txtnumSerie.setText(rep.getCodrepuesto());
        txtDescripcion.setText(tablaRepuestos.getValueAt(fila, 5).toString());
        
        txtDescripModelo.setText(rep.getDescrmodelo());

        txtDescripLista.setText(rep.getDesclista());
        //txtDescripLista2.setText(rep.getDesclista2());
        
        txtCodSegundo.setText(rep.getCodigoseg());
//        if ( tablaRepuestos.getValueAt(fila, 1) != null ) {
//            txtCodSegundo.setText(tablaRepuestos.getValueAt(fila, 1).toString());
//        }

        txtAplicacion.setText(rep.getAplicacion());
        if ( rep.getInventario() != null ) {
            txtInventario.setText(String.valueOf(rep.getInventario()));
            
        } else {
            txtInventario.setText(String.valueOf(0));
        }

     
        if ( !tablaRepuestos.getValueAt(fila, 6).toString().equals("") ) {
            txtStock.setText(tablaRepuestos.getValueAt(fila, 6).toString());
            
        } else {
            txtStock.setText(String.valueOf(0));
        }

        String linea;
        int codlin;
        String marca;
        int codmar;
        String modelo1;
        int codmodelo;
        if ( rep.getEquipos() == null ) {
            comboEquipo.setSelectedIndex(0);
            
        } else {
            linea = rep.getEquipos().getDescripcion();
            codlin = rep.getEquipos().getIdequipo();
            
            codmar = rep.getMarcas().getIdmarca();
            marca= rep.getMarcas().getDescripcion();
            
            codmodelo = rep.getModelos().getIdmodelo();
            modelo1 = rep.getModelos().getDescripcion();
            comboEquipo.setSelectedItem(codlin + " - " + linea);
            comboMarca.setSelectedItem(codmar + " - " + marca);
            comboModelo.setSelectedItem(codmodelo + " - " + modelo1);
        }

        if ( rep.getNroalternativo() != null ) {
            txtNumAlternativo.setText(String.valueOf(rep.getNroalternativo()));
            
        } else {
            txtNumAlternativo.setText(String.valueOf(0));
        }

        //Precio Lista

        if ( !tablaRepuestos.getValueAt(fila, 9).toString().equals("") ) {
            txtPrecioLista.setText(tablaRepuestos.getValueAt(fila, 9).toString());
            
        } else {
            txtPrecioLista.setText(String.valueOf(0.0));
        }
        //Costo Promedio
        if ( rep.getCostopromedio() != null ) {
            txtCostoPromedio.setText(String.valueOf(rep.getCostopromedio()));
        } else {
            txtCostoPromedio.setText(String.valueOf(0.0));
        }
         if ("ADMINISTRADOR".equals(roltemporal)){   
         }   else {
            txtCostoPromedio.setText(String.valueOf(0.0));             
         }     
        
        
        //Precio Costo Ultimo
        if ( tablaRepuestos.getValueAt(fila, 8)!=null ) {
            txtPrecioCostoUltimo.setText(tablaRepuestos.getValueAt(fila, 8).toString());
            
        } else {
            txtPrecioCostoUltimo.setText(String.valueOf(0.0));
        }
         if ("ADMINISTRADOR".equals(roltemporal)){   
         }   else {
            txtPrecioCostoUltimo.setText(String.valueOf(0.0));             
         }     

        txtFechaPrecioCosto.setDate(rep.getFechapcosto());

        //Precio Costo Temporal
        
        if ( rep.getPcostotemporal()!=null ) {
            txtPrecioCostoTemp.setText(String.valueOf(rep.getPcostotemporal()));
            
        } else {
            txtPrecioCostoTemp.setText(String.valueOf(0.0));
        }

        if ( rep.getStockminimo() != null ) {
            txtStockMinimo.setText(String.valueOf(rep.getStockminimo()));
            
        } else {
            txtStockMinimo.setText(String.valueOf(0));
        }

        String mov;
        String codmov;
        if ( rep.getEstratificacion() == null ) {
            mov = "";
            comboMovimiento.setSelectedIndex(0);
            
        } else {
            mov = rep.getEstratificacion().getDescripcion();
            codmov = rep.getEstratificacion().getCodigoestratificacion();
            comboMovimiento.setSelectedItem(codmov + " - " + mov);
        }

        txtIdAnterior.setText(rep.getIdrepuestoant());

        if ( rep.getMargenutil() != null ) {
            txtMargenUtil.setText(String.valueOf(rep.getMargenutil()));
            
        } else {
            txtMargenUtil.setText(String.valueOf(0.0));
        }

        txtUnidadMedida.setText(rep.getUnidadmedida());

        if ( rep.getFobultimo() != null ) {
            txtFOBUltimo.setText(String.valueOf(rep.getFobultimo()));
            
        } else {
            txtFOBUltimo.setText(String.valueOf(0.0));
        }
         if ("ADMINISTRADOR".equals(roltemporal)){   
         }   else {
            txtFOBUltimo.setText(String.valueOf(0.0));             
         }             

        txtUbicacionAlmacen.setText(rep.getUbicalmacen());
        txtUnidadVenta.setText(rep.getUnidadventa());
        txtPartidaArancel.setText(rep.getPartidarancel());
        //txtMarcaProveedor.setText(rep.getMarca());
        txtFechaRegistro.setDate(rep.getFecharegistro());
        
        // Cargar imagen
        //System.out.println("(tablaRepuestosMouseReleased)");
        
        cargarImagen(rep);        
    }//GEN-LAST:event_tablaRepuestosMouseReleased

    private void BorrarTabla() {
        int numRows = table.getRowCount();
        for ( int i = 0; i < numRows; i++ ) {
            table.removeRow(0);
        }
    }

    private void filtrar_parteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrar_parteActionPerformed
        int caso = 0;
        if ("ADMINISTRADOR".equals(roltemporal)){        
            caso = 0;
        } else {
            caso = 6;
        }
            List lista = sm.BuscarRepMaestro(caso, B_Parte.getText());
            BorrarTabla();
            Iterator it = lista.iterator();
            while ( it.hasNext() ) {
                Object[] row = (Object[]) it.next();
                table.addRow(row);
            }
            seleccionada = false;
    }//GEN-LAST:event_filtrar_parteActionPerformed

    private void filtrar_descActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrar_descActionPerformed
        int caso = 0;
        if ("ADMINISTRADOR".equals(roltemporal)){        
            caso = 1;
        } else {
            caso = 8;
        }
        List lista = sm.BuscarRepMaestro(caso, B_Desc.getText());
        BorrarTabla();
        Iterator it = lista.iterator();
        
        while ( it.hasNext() ) {
            Object[] repuesto = (Object[]) it.next();
            table.addRow(repuesto);
        }
    }//GEN-LAST:event_filtrar_descActionPerformed

    private void B_ParteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B_ParteKeyTyped
        if ( evt.getKeyChar() == 10 ) {
            filtrar_parte.doClick();
        }
    }//GEN-LAST:event_B_ParteKeyTyped

    private void B_DescKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B_DescKeyTyped
        if ( evt.getKeyChar() == 10 ) {
            filtrar_desc.doClick();
        }
    }//GEN-LAST:event_B_DescKeyTyped

    private void boton_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_SalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton_SalirActionPerformed

    private void txtFiltroAplica1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroAplica1KeyTyped
        if ( evt.getKeyChar() == 10 ) {
            filtrar_aplica1.doClick();
        }
    }//GEN-LAST:event_txtFiltroAplica1KeyTyped

    private void filtrar_aplica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrar_aplica1ActionPerformed
        int caso = 0;
        if ("ADMINISTRADOR".equals(roltemporal)){        
            caso = 4;
        } else {
            caso = 9;
        }
        List lista = sm.BuscarRepMaestro(caso, txtFiltroAplica1.getText());
        BorrarTabla();
        Iterator it = lista.iterator();
        
        while ( it.hasNext() ) {
            Object[] repuesto = (Object[]) it.next();
            table.addRow(repuesto);
        }
    }//GEN-LAST:event_filtrar_aplica1ActionPerformed
 
    
    private void txtUbicacionAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUbicacionAlmacenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacionAlmacenActionPerformed

    private void txtFOBUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFOBUltimoActionPerformed

    }//GEN-LAST:event_txtFOBUltimoActionPerformed

    private void txtPrecioCostoUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioCostoUltimoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioCostoUltimoActionPerformed

    private void txtDescripListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripListaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripListaActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed

    }//GEN-LAST:event_txtIdActionPerformed

    private void btnAddImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddImgActionPerformed
        String validacion = validarEntradasxAgregar();
        int fila = tablaRepuestos.getSelectedRow();
        
        if ( !validacion.equals("") ) {
            JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);

        } else {
            boolean repuestoModificado = true;
            if ( fila != -1 ) { // Hay repuesto seleccionado del JTable (modificando imagen del repuesto seleccionado)
                System.out.println("Modificando rep...");
                if ( seleccionarImagen() ) {
                    System.out.println("MyD guardarImagen : " + guardarImagen(repuestoModificado));
                    if ( guardarImagen(repuestoModificado) ) {
                        habilitaBtnAdd = false;
                        setBotonesImagen(habilitaBtnAdd);
                    }   
                }
                
            } else {
                if ( esRepuestoRepetido(txtnumSerie.getText()) ) {
                    JOptionPane.showMessageDialog(null, "El N° de Parte ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    System.out.println("Agregando rep...");
                    repuestoModificado = false; // nuevo (repuesto para agregar)
                    if ( seleccionarImagen() ) {
                        if ( guardarImagen(repuestoModificado) ) {
                            habilitaBtnAdd = false;
                            setBotonesImagen(habilitaBtnAdd);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnAddImgActionPerformed

    private void btnDelImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelImgActionPerformed

    int resp=JOptionPane.showConfirmDialog(null,"Seguro de Eliminar la imagen Seleccionada?");
        if (JOptionPane.OK_OPTION == resp){
            eliminarImagen();            
            habilitaBtnAdd = true;
            setBotonesImagen(habilitaBtnAdd);            
    }
      else{
    System.out.println("No selecciona una opción afirmativa");
    }        

    }//GEN-LAST:event_btnDelImgActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void txtPrecioCostoTempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioCostoTempActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioCostoTempActionPerformed

    private void B_DescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_DescActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_DescActionPerformed

    private void filtrar_parte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrar_parte1ActionPerformed
        int caso = 0;
        String criterioBusque;
        /*if ("ADMINISTRADOR".equals(roltemporal)){        
            caso = 0;
        } else {
            caso = 6;
        }*/
        
        if(cbEquipoFiltro.getSelectedIndex()==0){
            caso=10;
            criterioBusque="";
        }
        else if(cbMarcaFiltro.getSelectedIndex()==0){
            caso=10;
            criterioBusque=String.valueOf(cbEquipoFiltro.getSelectedItem());
            criterioBusque=criterioBusque.split(" - ")[0];
            //System.out.print(criterioBusque);
        }
        else if(cbModeloFiltro.getSelectedIndex()==0){
            caso=11;
            criterioBusque=String.valueOf(cbMarcaFiltro.getSelectedItem());
            criterioBusque=criterioBusque.split(" - ")[0];
            //System.out.print(criterioBusque);
        } else {
            caso =12;
            criterioBusque=String.valueOf(cbModeloFiltro.getSelectedItem());
            criterioBusque=criterioBusque.split(" - ")[0];
            //System.out.print(criterioBusque);
        }
        
       /*switch (caso) {
            
            case 1:
                ;
            case 2:
                ;
            case 3:
                ;
            
            default:
                ;
              break;
        }*/
        
        
            List lista = sm.BuscarRepMaestro(caso, criterioBusque);
            BorrarTabla();
            Iterator it = lista.iterator();
            while ( it.hasNext() ) {
                Object[] row = (Object[]) it.next();
                table.addRow(row);
            }
            seleccionada = false;
        
       /* List lista = sm.BuscarRepMaestro(caso, B_Parte1.getText());
        BorrarTabla();
        Iterator it = lista.iterator();
        
        while ( it.hasNext() ) {
            Object[] repuesto = (Object[]) it.next();
            table.addRow(repuesto);
        }*/

    }//GEN-LAST:event_filtrar_parte1ActionPerformed

    private void txtFiltroAplica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroAplica1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiltroAplica1ActionPerformed

    private void txtImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImagenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImagenActionPerformed

    private void btnAmpImg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmpImg1ActionPerformed
        try {
            String ruta = c.getRutaimagenes();
            String codRep = txtnumSerie.getText();
            Repuestos rep = sm.GetRepuesto_Codigo(codRep);
            String nombreImagen = rep.getImagen();
            
            String rutaImagen = ruta + "\\" + nombreImagen;
            imagenGuardar = new File(rutaImagen);

            
            String desktopPath;
            Map<String, String> env = System.getenv();
            if (env.containsKey("ONEDRIVE"))
            {
                desktopPath = env.get("ONEDRIVE") + "\\desktop";
            } else
            { desktopPath = env.get("USERPROFILE") + "\\Desktop";
  
            }
            desktopPath = desktopPath + "\\Copia-whatsapp.jpg";
            
//            String rutaDestino = "C:\\Users\\JAVIER SALAS\\Desktop\\Copia-whatsapp.jpg";
            String rutaDestino = desktopPath;
            
            File src = new File(rutaImagen);
            File dest = new File(rutaDestino);
            
            File file
            = new File(rutaDestino);
 
            if (file.delete()) {
                System.out.println("File deleted successfully");
            }
            else {
                System.out.println("Failed to delete the file");
            }            
            
            Files.copy(src.toPath(), dest.toPath());
            
            JOptionPane.showMessageDialog(null, "Su Imagen se Copio a su Escritorio");
            
            // TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(FREP001.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAmpImg1ActionPerformed

    private void jPanelConFondoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jPanelConFondoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelConFondoPropertyChange

    private void boton_ExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_ExportarActionPerformed
// TODO add your handling code here:
//      excel.Exportar_Excel();
        List listaCabecera = new ArrayList();
//        listaCabecera.add("ITEM"); // N°
//        listaCabecera.add("LINEA");
        
        System.out.println("Inicio ListaCabecera : " + listaCabecera);
        
        listaCabecera.add("NRO PARTE");
        listaCabecera.add("DESCRIPCIÓN");
        listaCabecera.add("APLICACIÓN"); // MODELO
//        listaCabecera.add("ANOTACIONES");
        listaCabecera.add("ANOTACIÓN");
//        listaCabecera.add("STOCK");
//        listaCabecera.add("COSTO UNIT.");
        listaCabecera.add("MARCA");
        listaCabecera.add("PRECIO");
//        listaCabecera.add("MARCA");
        
        System.out.println("listaCabecera : " + listaCabecera);
        excel = new Servicio_Excel(this.tablaRepuestos, this );
        excel.exportarExcel("Maestro de Artículos", listaCabecera, 1);
        //servicio_Excel.Exportar_Excel(3);
    }//GEN-LAST:event_boton_ExportarActionPerformed

    private void txtNumAlternativo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumAlternativo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumAlternativo1ActionPerformed

    private void txtNumAlternativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumAlternativoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumAlternativoActionPerformed

    private void txtCodSegundoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodSegundoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodSegundoActionPerformed

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockActionPerformed

    private void comboEquipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboEquipoItemStateChanged
        // TODO add your handling code here:
        String equipoDescrElement = String.valueOf(comboEquipo.getSelectedItem());
        String equipoId ="0";
        Marcas marcaselec = new Marcas();
        //System.out.println(equipselec);
        //textidmarca.setText("");
        if (comboEquipo.getSelectedIndex() != 0) {
            equipoId = equipoDescrElement.split(" - ")[0];
            //visibilidadElementosMarca(true);
        }
            Servicio_Equipos servequip = new Servicio_Equipos(null);
            Equipos equipselec = servequip.getEquipos_por_codigo(Integer.parseInt(equipoId));
            llenar_marcasXequipo(equipselec);        
            llenar_modelosXmarca(marcaselec);
        
            comboMarca.setSelectedIndex(0);
            comboModelo.setSelectedIndex(0);
    }//GEN-LAST:event_comboEquipoItemStateChanged

    private void comboMarcaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboMarcaItemStateChanged
        // TODO add your handling code here:
        
        String marcaoDescrElement = String.valueOf(comboMarca.getSelectedItem());
        String marcaId ="0";
        //System.out.println(equipselec);
        //textidmarca.setText("");
        if (comboMarca.getSelectedIndex() != 0) {
            marcaId = marcaoDescrElement.split(" - ")[0];
        

        }
            Servicio_Marcas servmarca = new Servicio_Marcas(null);
            Marcas marcaselec = servmarca.getMarcas_por_codigo(Integer.parseInt(marcaId));
            llenar_modelosXmarca(marcaselec);
            //System.out.println(marcaselec);
            //visibilidadElementosMarca(true);
        
        
            comboModelo.setSelectedIndex(0);
    }//GEN-LAST:event_comboMarcaItemStateChanged

    private void cbEquipoFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEquipoFiltroItemStateChanged
        // TODO add your handling code here:
        String equipoDescrElement = String.valueOf(cbEquipoFiltro.getSelectedItem());
        String equipoId ="0";
        Marcas marcaselec = new Marcas();
        
        if (cbEquipoFiltro.getSelectedIndex() != 0) {
            equipoId = equipoDescrElement.split(" - ")[0];

        }
            Servicio_Equipos servequip = new Servicio_Equipos(null);
            Equipos equipselec = servequip.getEquipos_por_codigo(Integer.parseInt(equipoId));
            llenar_marcasXequipoxFiltro(equipselec);
            llenar_modelosXmarcaxFiltro(marcaselec);
            //visibilidadElementosMarca(true);
        
            cbMarcaFiltro.setSelectedIndex(0);
            cbModeloFiltro.setSelectedIndex(0);
    }//GEN-LAST:event_cbEquipoFiltroItemStateChanged

    private void cbMarcaFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMarcaFiltroItemStateChanged
        // TODO add your handling code here:
        String marcaoDescrElement = String.valueOf(cbMarcaFiltro.getSelectedItem());
        String marcaId ="0";
        
        //System.out.println(equipselec);
        //textidmarca.setText("");
        if (cbMarcaFiltro.getSelectedIndex() != 0) {
            marcaId = marcaoDescrElement.split(" - ")[0];
        

        }
            Servicio_Marcas servmarca = new Servicio_Marcas(null);
            Marcas marcaselec = servmarca.getMarcas_por_codigo(Integer.parseInt(marcaId));
            llenar_modelosXmarcaxFiltro(marcaselec);
            //visibilidadElementosMarca(true);
        
            cbModeloFiltro.setSelectedIndex(0);
    }//GEN-LAST:event_cbMarcaFiltroItemStateChanged

    private void filtrar_aplica2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrar_aplica2ActionPerformed
        // TODO add your handling code here:
        int caso = 0;
        if ("ADMINISTRADOR".equals(roltemporal)){        
            caso = 0;
        } else {
            caso = 6;
        }
        
        
        String validacion = ValidarEntradasxFechas();
        if (!validacion.equals("VALIDO")){
            JOptionPane.showMessageDialog(null, validacion, "Error de ingreso de datos", JOptionPane.ERROR_MESSAGE);
            }
            else {
                List lista = sm.Consultar_Repuestos_xFechas(txtFechaRegistroInicial.getDate(), txtFechaRegistroFinal.getDate());
                BorrarTabla();
                Iterator it = lista.iterator();
                while ( it.hasNext() ) {
                    Object[] row = (Object[]) it.next();
                    table.addRow(row);
                }        
                    
             }    
            
            seleccionada = false;
        
    }//GEN-LAST:event_filtrar_aplica2ActionPerformed

    private void boton_Agregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_Agregar1ActionPerformed
        // TODO add your handling code here:
        if ( seleccionada == false ) {
            String validacion = validarEntradasxAgregar();

            if ( !validacion.equals("") ) {
                JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);
                
            }/* else if ( esRepuestoRepetido(txtnumParte.getText()) ) {
                JOptionPane.showMessageDialog(null, "El N° de Serie ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                // Obviar porque en equipos se pueden repetir
            } */else {
                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operación?", "CONFIRMACIÓN", 0);
                
                if ( verif == 0 ) {

                    String id = txtId.getText();
                    String a = txtnumSerie.getText();
                    String b = txtDescripcion.getText();
                    String c = txtDescripModelo.getText();
                    String d = txtDescripLista.getText();
                    //String d2 = txtDescripLista2.getText();
                    String e = txtCodSegundo.getText();
                    String f = txtAplicacion.getText();
                    String g = txtInventario.getText();

                    String h = comboEquipo.getSelectedItem().toString();
                    String j = txtNumAlternativo.getText();
                    String k = txtPrecioLista.getText();
                    String l = txtCostoPromedio.getText();
                    String m = txtPrecioCostoUltimo.getText();
                    Date fechaPreCosto = txtFechaPrecioCosto.getDate();
                    String n = txtPrecioCostoTemp.getText();
                    String o = txtStockMinimo.getText();
                    String p = comboMovimiento.getSelectedItem().toString();
                    String q = txtIdAnterior.getText();
                    String r = txtMargenUtil.getText();
                    String s = txtUnidadMedida.getText();
                    String t = txtFOBUltimo.getText();
                    String u = txtUbicacionAlmacen.getText();
                    String v = txtUnidadVenta.getText();
                    String w = txtPartidaArancel.getText();
                    //String x = txtMarcaProveedor.getText();
                    txtFechaRegistro.setDate(fechasistemaDate());
                    Date fechaRegistro = txtFechaRegistro.getDate();
                    String xx = txtNumAlternativo1.getText();
                    xx = nombreUsuario;
                    
                    String stock = txtStock.getText();
//                    System.out.println("stock:" + stock);

                    Servicio_Equipos li = new Servicio_Equipos(null);
                    Servicio_Estratificacion es = new Servicio_Estratificacion();
                    h = h.split(" - ", 2)[0];
                    Equipos equipos = li.getEquipos_por_codigo(Integer.valueOf(h));
                    Estratificacion estr = es.getEstratificacion_por_nombre("N");
                    
                    Servicio_Marcas ma = new Servicio_Marcas(null);
                    String hi = comboMarca.getSelectedItem().toString();
                    hi = hi.split(" - ", 2)[0];
                    Marcas marcas = ma.getMarcas_por_codigo(Integer.valueOf(hi)); 
                    
                    Servicio_Modelos mo = new Servicio_Modelos(null);
                    String hj = comboModelo.getSelectedItem().toString();
                    hj = hj.split(" - ", 2)[0];
                    Modelos modelos = mo.getModelos_por_codigo(Integer.valueOf(hj));
                    

                    int numlin = equipos.getIdequipo();
                    int nummarc = marcas.getIdmarca();
                    int nummodel = modelos.getIdmodelo();
                    Repuestos rep = new Repuestos();
                    RepuestosId repid = new RepuestosId();

                    repid.setIdequipo(numlin);
                    repid.setIdmarca(nummarc);
                    repid.setIdmodelo(nummodel);
                    repid.setIdrepuesto(Integer.parseInt(id));

                    rep.setId(repid);
                    rep.setCodrepuesto(a);
                    rep.setDescripcion(b);
                    rep.setDescrmodelo(c);
                    rep.setDesclista(d);
                    rep.setMarca(marcas.getDescripcion());
                    //rep.setDesclista2(d2);
                    rep.setCodigoseg(e);
                    rep.setAplicacion(f);
                    
                    if ( !g.equals("") ) {
                        rep.setInventario(Integer.parseInt(g));
                    }
                    
//                    if ( STOCK_MAXIMO.equals(stock) ) {
//                    if ( STOCK_MINIMO.equals(stock) ) {
//                        rep.setStock(Integer.parseInt(stock));
//                    }
// Javier Salas (25/06/2022) - Tema de actualiza Stock en MyD
//                    else {
                    rep.setStock(0);
//                    }

                    if ( !j.equals("") ) {
                        rep.setNroalternativo(Integer.parseInt(j));
                    }

                    rep.setEquipos(equipos);
                    rep.setMarcas(marcas);
                    rep.setModelos(modelos);

                    if ( !k.equals("") ) {
                        rep.setPreciolista(Double.parseDouble(k));
                    }
                    
                    if ( !l.equals("") ) {
                        rep.setCostopromedio(Double.parseDouble(l));
                        
                    } else {
                        rep.setCostopromedio(0.00);
                    }
                    
                    if ( !m.equals("") ) {
                        rep.setPcostoultimo(Double.parseDouble(m));
                    }

                    rep.setFechapcosto(fechaPreCosto);
                    if ( !n.equals("") ) {
                        rep.setPcostotemporal(Double.parseDouble(n));
                    }
                    
                    if ( !o.equals("") ) {
                        rep.setStockminimo(Integer.parseInt(o));
                    }

                    rep.setEstratificacion(estr);
                    rep.setIdrepuestoant(q);
                    if ( !r.equals("") ) {
                        rep.setMargenutil(Double.parseDouble(r));
                    }
                    rep.setUnidadmedida(s);
                    if ( !t.equals("") ) {
                        rep.setFobultimo(Double.parseDouble(t));
                     } else {
                        rep.setFobultimo(0.00);
                        t="0.0";
                    }                        
 
                    rep.setUbicalmacen(u);
                    rep.setUnidadventa(v);
                    rep.setPartidarancel(w);
                    //rep.setMarca(x);
                    rep.setFecharegistro(fechaRegistro);
                    
                    String img = null;
                    if ( !"".equals(txtImagen.getText()) ) {
                        img = txtImagen.getText();
                    }
                    rep.setImagen(img);
                    rep.setUsuario(xx);
                    
                    System.out.println(fechaRegistro.toString());
                    System.out.println(rep.getFecharegistro().toString());
                    
                    sistema = sis.getSis(INGRESO_POR_REGULARIZACION);
                    
                    if ( sm.addRepuestos(rep)  ) {
                        JOptionPane.showMessageDialog(null, "Se registró el repuesto");

                        
                        if(sis.guardarIngreso(SetDetalles(rep))) {
                            sis.actualizarSistema(sistema);
                            System.out.println("ultimo numero despues de ACTUALIZADO:" + sistema.getUltimonumero());
                            tm.Mensaje("ÉXITO EN LA OPERACIÓN");
                            
                            JOptionPane.showMessageDialog(null, "Se registró el ingreso del repuesto");
                            
                        } else {
                           System.out.println("Error INGRESO GuardarIngSalVarias(IU_DetalleIngSal.java)");
                           tm.Mensaje("ERROR EN LA OPERACIÓN");
                           
                           JOptionPane.showMessageDialog(null, "Error en el ingreso del ingreso del repuesto");
                        }
                        
                        Object[] row = {rep.getId().getIdrepuesto(), equipos.getDescripcion(), marcas.getDescripcion(), modelos.getDescripcion(),a, b, rep.getStock(),  t, m, k};
                        //Object[] row = {a, e, b, stock, k, l, m, n};
                        modelo.insertRow(0, row);
                        clean();
                        cargarImagen(null); // Desaparece imagen y limpia su nombre
                        txtId.setText(String.valueOf(sm.nextId()));
                        txtNumAlternativo1.setText(rep.getUsuario());
                        
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en la inserción");
                    }
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "No se puede registrar un repuesto que ya existe!", "Error al agregar", 0);
        }
    }//GEN-LAST:event_boton_Agregar1ActionPerformed

        private ArrayList<Detallees> SetDetalles(Repuestos rep) {
        ArrayList<Detallees> d = new ArrayList();
        System.out.println("Numero de nota ingreso x importacion : " + sistema.getUltimonumero());
        
        int nroIngreso = sistema.getUltimonumero() + 1;
        String motivo = "INGRESO AUTOMATICO POR MAESTRO";
        
        
            Detallees detalle = new Detallees();
            double precRep = 0.00;
            if ( rep.getCostopromedio() != null ) {
                precRep = rep.getCostopromedio();
            }
            detalle.setRepuestos(rep);
            detalle.setCantpedida(1);
            detalle.setCantentregada(1);
            detalle.setDescuento1(0.0);
            detalle.setDescuento2(0.0);
            detalle.setDescuento3(0.0);
            detalle.setDescuento4(0.0);
            detalle.setPreciolista(precRep);
            
            
            
            
            detalle.setFecha(rep.getFecharegistro());
            detalle.setOperaciones(sistema.getOperaciones());
            detalle.setNroingreso(nroIngreso);
            d.add(detalle);
            detalle.setMotivo(motivo);
        
        sistema.setUltimonumero(nroIngreso); // ACTUALIZA SISTEMA para "Ingresos por Regularización"
        return d;
    }

    private void eliminarImagen() {
//        System.out.println("eliminarImagen...");
        
        String ruta = c.getRutaimagenes();
        String codRep = txtnumSerie.getText();
        Repuestos rep = sm.GetRepuesto_Codigo(codRep);
        String nombreImagen = rep.getImagen();
        //String nombreImagen = "vacio.jpg";
        
        String rutaImagen = ruta + "\\" + nombreImagen;
        System.out.println("(eliminarImagen)rutaImagen:" + rutaImagen);
        imagenGuardar = new File(rutaImagen);

        if ( !imagenGuardar.exists() ) {
//            System.out.println("La imagen no existe.");
            JOptionPane.showMessageDialog(null, "La imagen a eliminar no existe", "Información", JOptionPane.ERROR_MESSAGE);
            
        } else {
            imagenGuardar.delete();
            rep.setImagen(null);
            
            if ( sm.actualizarRepuestos(rep) ) {
                String rutaImgVacia = ruta + "\\vacio.jpg";
                Image imagenExterna = new ImageIcon(rutaImgVacia).getImage();
                jPanelConFondo.setImagen2(imagenExterna); //null
                jPanelConFondo.revalidate();
                jPanelConFondo.repaint();
                jPanelConFondo.updateUI();
                repaint();
                txtImagen.setText("");
        
//                System.out.println("La imagen fue eliminada.");
                JOptionPane.showMessageDialog(null, "Imagen eliminada correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                
            } else {
//                System.out.println("La imagen no fue eliminada.");
                JOptionPane.showMessageDialog(null, "No se pudo eliminar imagen", "Información", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private boolean guardarImagen(boolean modificado) {
        boolean guardarImagen = false;
        System.out.println("Modificado::" + modificado);
//        System.out.println("guardarImagen...");
        if( imagenGuardar != null ) {
            Repuestos rep = cargarImagen();
            
            if ( modificado ) {
                if ( sm.actualizarRepuestos(rep) ) {
                    cargarImagen(rep);
                    guardarImagen = true;
                    JOptionPane.showMessageDialog(null, "Imagen actualizada correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar imagen", "Información", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                cargarImagen(rep);
                guardarImagen = true;
            }
        }
        return guardarImagen;
    }
    
    public static String getExtension(String filename) {
        int index = filename.lastIndexOf('.');
        if ( index == -1 ) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }
    
    public Repuestos cargarImagen() {
    //public String cargarImagen() {
        try {
            String idRep = txtId.getText();
            String codRep = txtnumSerie.getText();
            String nombreImagen = codRep;
            int fila = tablaRepuestos.getSelectedRow();
            Repuestos rep = null;

            if ( fila == -1 ) { // No hay repuesto seleccionado del JTable (agregando imagen para nuevo repuesto)
                rep = new Repuestos();
                
            } else {
                rep = sm.GetRepuesto_Id(Integer.parseInt(idRep));
                nombreImagen = rep.getCodrepuesto();
            }
            
            String extension = getExtension(imagenGuardar.getName());
            String rutaImagen = c.getRutaimagenes();
//            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
//            String fechaCheque = formatoFecha.format(txtfec.getDate());
//            System.out.println("formato de fecha cheque:" + fechaCheque);

//            DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
//            Date date = new Date();
//            String horaActual = formatoHora.format(date);
//            horaActual = horaActual.replaceAll(":", "_");
//            System.out.println("formato de hora actual:" + horaActual);

            //String path = RUTA_IMAGENES + "NroDoc." + txt_numDoc.getText() + ".IdPago." + idpago + ".Fech." + fechaCheque + "." + horaActual + "." + getExtension(imagenGuardar.getName());
            nombreImagen = nombreImagen.replaceAll(" ", "");
            nombreImagen = nombreImagen.replaceAll("/", "");
            String path = rutaImagen + "\\" + nombreImagen + "." + extension;
            /*path = path.replaceAll(" ", "");
            path = path.replaceAll("/", "");*/
            System.out.println("PATH FINAL::::" + path);

            File prueba = new File(path);
            prueba.getParentFile().mkdirs();
            prueba.createNewFile();
            copyFileUsingJava7Files(imagenGuardar, prueba);

            rep.setImagen(nombreImagen + "." + extension);
            txtImagen.setText(nombreImagen + "." + extension);
            return rep;
            //return nombreImagen + "." + extension;
            
        } catch (IOException ex) {
            System.out.println("Excepcion:" + ex.getMessage());
            JOptionPane.showMessageDialog(null, "No se guardó la imagen del repuesto", "Información", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    private static void copyFileUsingJava7Files(File source, File dest) {
        try {
            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };
            Files.copy(source.toPath(), dest.toPath(), options);
            
        } catch (IOException ioe) {
            System.out.println("Excepcion:" + ioe.getMessage());
        }
    }
    
    private boolean seleccionarImagen() {
        boolean imgSeleccionada = false;
        JFileChooser file = new JFileChooser();
        imagenGuardar = null;
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        file.setFileFilter(filtroImagen);
        int r = file.showSaveDialog(this);
        
        if ( r == JFileChooser.APPROVE_OPTION ) {
            imagenGuardar = file.getSelectedFile();

            if ( imagenGuardar != null ) {
                JOptionPane.showMessageDialog(null, "La imagen se ha seleccionado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                imgSeleccionada = true;
                
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo obtener la imagen", "Información", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado una imagen", "Información", JOptionPane.ERROR_MESSAGE);
        }
//        System.out.println("file:" + file);
        System.out.println("imagenGuardar:" + imagenGuardar);
        return imgSeleccionada;
    }
    
    private void clean() {
        for ( JTextField j : componentes ) {
            
            j.setText("");
        }
        txtDescripcion.setText("RAM / SSD");
        
        txtPrecioLista.setText("1");
        txtPrecioCostoUltimo.setText("0.6");
        //comboEquipo.setSelectedIndex(0);
        comboMovimiento.setSelectedIndex(1);
        tablaRepuestos.clearSelection();
        txtnumSerie.setEditable(true);
        
        txtImagen.setText("");
    }

    private void llenar_equipos() {
        Servicio_Equipos sr = new Servicio_Equipos(null);
        Iterator it = sr.getList().iterator();

        while ( it.hasNext() ) {
            Equipos l = (Equipos) it.next();
            comboEquipo.addItem(l.getIdequipo() + " - " + l.getDescripcion()); // llenar combobox de arriba
            cbEquipoFiltro.addItem(l.getIdequipo() + " - " + l.getDescripcion()); // llenar combobox de filtros
        }
    }

    private void llenar_marcasXequipo(Equipos equipo) {
        
       modelMarcCombo = new DefaultComboBoxModel();
       Servicio_Marcas sm = new Servicio_Marcas(null);
       
       modelMarcCombo.addElement("Seleccione una Marca");
       if(equipo != null){
        List<Marcas> listaMarc = sm.buscarMarcasx_Equipo(equipo);
         for (int i = 0; i < listaMarc.size(); i++) {
             modelMarcCombo.addElement(listaMarc.get(i).getIdmarca()+" - "+listaMarc.get(i).getDescripcion());
         }
       }
        comboMarca.setModel(modelMarcCombo); 
    }
    
    private void llenar_marcasXequipoxFiltro(Equipos equipo) {
        
       modelMarcCombo = new DefaultComboBoxModel();
       Servicio_Marcas sm = new Servicio_Marcas(null);
       
      modelMarcCombo.addElement("Seleccione una Marca");
       if (equipo != null) {
        List<Marcas> listaMarc = sm.buscarMarcasx_Equipo(equipo);
         for (int i = 0; i < listaMarc.size(); i++) {
             modelMarcCombo.addElement(listaMarc.get(i).getIdmarca()+" - "+listaMarc.get(i).getDescripcion());
         }
       }
        cbMarcaFiltro.setModel(modelMarcCombo); 
    }
    
    
    
     private void llenar_modelosXmarca(Marcas marca) {
        
       modelModeloCombo = new DefaultComboBoxModel();
       Servicio_Modelos smodelos = new Servicio_Modelos(null);
       
       modelModeloCombo.addElement("Seleccione un Modelo");
       if(marca != null) {          // si la marca existe o no esta vacia
        List<Modelos> listaModelos = smodelos.buscarModelosx_Marca(marca);
         for (int i = 0; i < listaModelos.size(); i++) {
             modelModeloCombo.addElement(listaModelos.get(i).getIdmodelo()+" - "+listaModelos.get(i).getDescripcion());
         }
       }
        comboModelo.setModel(modelModeloCombo); 
 
    }
     
     private void llenar_modelosXmarcaxFiltro(Marcas marca) {
        
       modelModeloCombo = new DefaultComboBoxModel();
       Servicio_Modelos smodelos = new Servicio_Modelos(null);
       
       modelModeloCombo.addElement("Seleccione un Modelo");
       if (marca !=null){
        List<Modelos> listaModelos = smodelos.buscarModelosx_Marca(marca);
         for (int i = 0; i < listaModelos.size(); i++) {
             modelModeloCombo.addElement(listaModelos.get(i).getIdmodelo()+" - "+listaModelos.get(i).getDescripcion());
         }
       } 
        cbModeloFiltro.setModel(modelModeloCombo); 
 
    }
    
    private void llenar_Movimientos() {
        Servicio_Estratificacion es = new Servicio_Estratificacion(null);
        Iterator it = es.getList().iterator();

        while ( it.hasNext() ) {
            Estratificacion e = (Estratificacion) it.next();
            comboMovimiento.addItem(e.getCodigoestratificacion() + " - " + e.getDescripcion());
        }
    }

    public String DateToFecha(String fecha) {
        String[] val = fecha.split("-");
        String f = val[2] + "/" + val[1] + "/" + val[1];
        return f;
    }

    public Date convertiraDate(String fechaconvertir) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha = sdf.parse(fechaconvertir);
        return fecha;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField B_Desc;
    public javax.swing.JTextField B_Parte;
    public javax.swing.JButton boton_Agregar;
    public javax.swing.JButton boton_Agregar1;
    public javax.swing.JButton boton_Eliminar;
    public javax.swing.JButton boton_Exportar;
    public javax.swing.JButton boton_Limpiar;
    public javax.swing.JButton boton_Modificar;
    public javax.swing.JButton boton_Salir;
    public javax.swing.JButton btnAddImg;
    public javax.swing.JButton btnAmpImg1;
    public javax.swing.JButton btnDelImg;
    public javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox cbEquipoFiltro;
    public javax.swing.JComboBox cbMarcaFiltro;
    public javax.swing.JComboBox cbModeloFiltro;
    public javax.swing.JComboBox comboEquipo;
    public javax.swing.JComboBox comboMarca;
    public javax.swing.JComboBox comboModelo;
    public javax.swing.JComboBox comboMovimiento;
    public javax.swing.JButton filtrar_aplica1;
    public javax.swing.JButton filtrar_aplica2;
    public javax.swing.JButton filtrar_desc;
    public javax.swing.JButton filtrar_parte;
    public javax.swing.JButton filtrar_parte1;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel23;
    public javax.swing.JLabel jLabel24;
    public javax.swing.JLabel jLabel25;
    public javax.swing.JLabel jLabel26;
    public javax.swing.JLabel jLabel27;
    public javax.swing.JLabel jLabel28;
    public javax.swing.JLabel jLabel29;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel30;
    public javax.swing.JLabel jLabel31;
    public javax.swing.JLabel jLabel32;
    public javax.swing.JLabel jLabel33;
    public javax.swing.JLabel jLabel34;
    public javax.swing.JLabel jLabel35;
    public javax.swing.JLabel jLabel36;
    public javax.swing.JLabel jLabel37;
    public javax.swing.JLabel jLabel38;
    public javax.swing.JLabel jLabel39;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel40;
    public javax.swing.JLabel jLabel41;
    public javax.swing.JLabel jLabel42;
    public javax.swing.JLabel jLabel43;
    public javax.swing.JLabel jLabel44;
    public javax.swing.JLabel jLabel45;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private final ar.lefunes.jpanelconfondo.JPanelConFondo jPanelConFondo = new ar.lefunes.jpanelconfondo.JPanelConFondo();
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelMaestros;
    public javax.swing.JTable tablaRepuestos;
    public javax.swing.JTextField txtAplicacion;
    public javax.swing.JTextField txtCodSegundo;
    public javax.swing.JTextField txtCostoPromedio;
    public javax.swing.JTextField txtDescripLista;
    public javax.swing.JTextField txtDescripModelo;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtFOBUltimo;
    public com.toedter.calendar.JDateChooser txtFechaPrecioCosto;
    public com.toedter.calendar.JDateChooser txtFechaRegistro;
    public com.toedter.calendar.JDateChooser txtFechaRegistroFinal;
    public com.toedter.calendar.JDateChooser txtFechaRegistroInicial;
    public javax.swing.JTextField txtFiltroAplica1;
    public javax.swing.JTextField txtId;
    public javax.swing.JTextField txtIdAnterior;
    public javax.swing.JTextField txtImagen;
    public javax.swing.JTextField txtInventario;
    public javax.swing.JTextField txtMargenUtil;
    public javax.swing.JTextField txtNumAlternativo;
    public javax.swing.JTextField txtNumAlternativo1;
    public javax.swing.JTextField txtPartidaArancel;
    public javax.swing.JTextField txtPrecioCostoTemp;
    public javax.swing.JTextField txtPrecioCostoUltimo;
    public javax.swing.JTextField txtPrecioLista;
    public javax.swing.JTextField txtStock;
    public javax.swing.JTextField txtStockMinimo;
    public javax.swing.JTextField txtUbicacionAlmacen;
    public javax.swing.JTextField txtUnidadMedida;
    public javax.swing.JTextField txtUnidadVenta;
    public javax.swing.JTextField txtnumSerie;
    // End of variables declaration//GEN-END:variables

    private Icon newImageIcon(URL resource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
