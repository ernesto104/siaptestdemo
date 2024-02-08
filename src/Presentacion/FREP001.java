package Presentacion;

import Entidades.Control;
import Entidades.Estratificacion;
import Entidades.Equipos;
//import static Entidades.Otros.Constante.STOCK_MAXIMO;
import static Entidades.Otros.Constante.STOCK_MINIMO;
import Entidades.Repuestos;
import Entidades.RepuestosId;
import Entidades.Usuarios;
import Mantenimiento.Navegacion;
import Servicios.HibernateUtil;
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
import static java.lang.Double.parseDouble;

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
    public boolean seleccionada;
    JTextField[] componentes;
    public final ArrayList<Integer> numMaximo;
    public final ArrayList<String> tipoDato;
    public Navegacion navegacion;

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
        llenar_lineas();
        llenar_Movimientos();
        numMaximo = new ArrayList<>();
        tipoDato = new ArrayList<>();
        crearArrayComponente();
        crearArrayNumMax();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, boton_Salir);
        asignarEvento();
        
        ajustarAnchoColumnas();
        alinearColumnaDerecha();
        centrarColumna();
        
        btnAddImg.setEnabled(!habilitaBtnAdd);
        btnDelImg.setEnabled(habilitaBtnAdd);
        setearImagenVacia();

 //      Botones habilitados solo para el ADMINISTRADOR
        System.out.println("usuario : " + username);

        if ("ADMINISTRADOR".equals(rolElegido)) {
            boton_Agregar.setEnabled(true);
            boton_Modificar.setEnabled(true);            
            boton_Eliminar.setEnabled(true);     
            boton_Exportar.setEnabled(true);
        } else {
            boton_Agregar.setEnabled(false);
            boton_Modificar.setEnabled(false);            
            boton_Eliminar.setEnabled(false);            
            boton_Exportar.setEnabled(false);            
        }

    }

  
    private void alinearColumnaDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
//      tablaRepuestos.getColumnModel().getColumn(4).setCellRenderer(tcr);  // Stock
        tablaRepuestos.getColumnModel().getColumn(5).setCellRenderer(tcr);  // Precio Lista
//      tablaRepuestos.getColumnModel().getColumn(6).setCellRenderer(tcr);  // Marca
        tablaRepuestos.getColumnModel().getColumn(7).setCellRenderer(tcr);  // FOBo
//      tablaRepuestos.getColumnModel().getColumn(8).setCellRenderer(tcr);  // Costo Promedio
        tablaRepuestos.getColumnModel().getColumn(8).setCellRenderer(tcr);  // Ultimo Costo
        tablaRepuestos.getColumnModel().getColumn(9).setCellRenderer(tcr);  // Precio Lista                
        
    }

    private void centrarColumna(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tablaRepuestos.getColumnModel().getColumn(5).setCellRenderer(tcr);  // Stock
       
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
        txtId.setText("" + (sm.getUltimo_id() + 1));
        } else {
        cant_rep = sm.listarRepuestos2(modelo);
        cant_rep++;
        txtId.setText("" + (sm.getUltimo_id() + 1));                
                }
    }

    private void crearArrayNumMax() {
        numMaximo.add(17);
        numMaximo.add(40);
        numMaximo.add(40);
        numMaximo.add(40);
        numMaximo.add(10);
        numMaximo.add(40); // Cambio de "6" a "40"
        numMaximo.add(6);
        numMaximo.add(6);
        numMaximo.add(5);
        numMaximo.add(10);
        numMaximo.add(10);
        numMaximo.add(10);
        numMaximo.add(10);
        numMaximo.add(6);
        numMaximo.add(17);
        numMaximo.add(6);
        numMaximo.add(10);
        numMaximo.add(10);
        numMaximo.add(6);
        numMaximo.add(6);
        numMaximo.add(14);
        numMaximo.add(15);
        numMaximo.add(20);
    }

    private void crearArrayTipoDato() {
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("I");
        tipoDato.add("I");
        tipoDato.add("I");

        tipoDato.add("D");
        tipoDato.add("D");
        tipoDato.add("D");
        tipoDato.add("D");

        tipoDato.add("I");

        tipoDato.add("S");
        tipoDato.add("D");
        tipoDato.add("S");
        tipoDato.add("D");

        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("S");        

    }

    private void asignarEvento() {
        for ( int i = 0; i < componentes.length; i++ ) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    private void crearArrayComponente() {
        componentes = new JTextField[23];
        componentes[0] = txtnumParte;
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
        componentes[21] = txtMarcaProveedor;
        componentes[22] = txtNumAlternativo1;        
 
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
        comboLinea = new javax.swing.JComboBox();
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
        txtMarcaProveedor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtnumParte = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        comboMovimiento = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtFechaPrecioCosto = new com.toedter.calendar.JDateChooser();
        txtFechaRegistro = new com.toedter.calendar.JDateChooser();
        jLabel32 = new javax.swing.JLabel();
        txtDescripLista2 = new javax.swing.JTextField();
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
        jLabel36 = new javax.swing.JLabel();
        txtFiltroAplica1 = new javax.swing.JTextField();
        filtrar_aplica1 = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        filtrar_parte1 = new javax.swing.JButton();
        B_Parte1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del Repuesto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 1, 14))); // NOI18N

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

        jLabel2.setText("ID Repuesto");

        jLabel3.setText("Cod Secundario");

        jLabel4.setText("Descripción");

        jLabel5.setText("Aplicación 1");

        jLabel6.setText("Anotación 1");

        jLabel8.setText("Inventario");

        txtDescripLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripListaActionPerformed(evt);
            }
        });

        jLabel9.setText("ID Linea");

        jLabel10.setText("Stock Central");

        txtStock.setEnabled(false);
        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });

        comboLinea.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione una Linea" }));
        comboLinea.setToolTipText("");
        comboLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLineaActionPerformed(evt);
            }
        });

        jLabel11.setText("N° Alternativo");

        jLabel12.setText("Precio Lista");

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

        txtPrecioLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioListaActionPerformed(evt);
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

        jLabel25.setText("Marca");

        jLabel1.setText("Nº de Parte");

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

        txtDescripLista2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripLista2ActionPerformed(evt);
            }
        });

        jLabel35.setText("Anotación 2");

        jLabel7.setText("Aplicación 2");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel12))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtDescripModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtAplicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDescripLista, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDescripLista2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(48, 48, 48)
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
                                                .addComponent(txtUnidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtFOBUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtFechaPrecioCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addGap(83, 83, 83)
                                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(115, 115, 115))))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(comboLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel32)
                                            .addGap(0, 518, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                    .addComponent(txtPrecioLista, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jLabel31))
                                                .addComponent(txtCostoPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel17)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel25)
                                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel23)
                                                .addComponent(jLabel20))))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtPrecioCostoUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(566, 566, 566)
                                .addComponent(jLabel3)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUbicacionAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPartidaArancel, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarcaProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumAlternativo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumAlternativo1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodSegundo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtnumParte, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29)))
                .addGap(0, 0, Short.MAX_VALUE)
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
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtnumParte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescripModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(9, 9, 9)
                .addComponent(txtMarcaProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
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
                                            .addComponent(txtUnidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(txtAplicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel6)
                                            .addComponent(txtDescripLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtDescripLista2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel35))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel31))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(txtNumAlternativo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtFechaPrecioCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrecioLista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtNumAlternativo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel38))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCodSegundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel9)
                                                    .addComponent(comboLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel32))
                                                .addGap(11, 11, 11)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtCostoPromedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel13)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(txtFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel10))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtPrecioCostoUltimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel39)
                                            .addComponent(jLabel40)))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanelConFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddImg)
                            .addComponent(btnDelImg)
                            .addComponent(btnAmpImg1))))
                .addContainerGap())
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        tablaRepuestos.setAutoCreateRowSorter(true);
        tablaRepuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº de Parte", "Codigo Sec", "Descripción", "Aplicación 1", "Anotación 1", "Stock", "Marca", "FOB", "P. Costo Ultimo", "Precio Lista"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaRepuestos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaRepuestos.getTableHeader().setReorderingAllowed(false);
        tablaRepuestos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaRepuestosMouseClicked(evt);
            }
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
            tablaRepuestos.getColumnModel().getColumn(0).setPreferredWidth(12);
            tablaRepuestos.getColumnModel().getColumn(2).setPreferredWidth(21);
            tablaRepuestos.getColumnModel().getColumn(3).setPreferredWidth(21);
            tablaRepuestos.getColumnModel().getColumn(4).setPreferredWidth(21);
            tablaRepuestos.getColumnModel().getColumn(5).setPreferredWidth(5);
            tablaRepuestos.getColumnModel().getColumn(7).setPreferredWidth(5);
            tablaRepuestos.getColumnModel().getColumn(8).setPreferredWidth(5);
            tablaRepuestos.getColumnModel().getColumn(9).setPreferredWidth(5);
        }

        boton_Agregar.setText("Agregar");
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
        jLabel28.setText("MAESTRO DE REPUESTOS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Búsqueda por : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        jLabel33.setText("Nº de Parte : ");

        B_Parte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_ParteActionPerformed(evt);
            }
        });
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

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel36.setText("Aplicación 1:");

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

        jLabel37.setText("Codigo Sec:");

        filtrar_parte1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        filtrar_parte1.setText("Filtrar");
        filtrar_parte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrar_parte1ActionPerformed(evt);
            }
        });

        B_Parte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Parte1ActionPerformed(evt);
            }
        });
        B_Parte1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                B_Parte1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B_Parte, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(filtrar_parte, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B_Parte1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filtrar_parte1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(B_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(filtrar_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addGap(2, 2, 2)
                .addComponent(txtFiltroAplica1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(filtrar_aplica1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(B_Parte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel34)
                .addComponent(B_Desc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(filtrar_parte, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(filtrar_desc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel33)
                .addComponent(jLabel36)
                .addComponent(txtFiltroAplica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(filtrar_aplica1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel37)
                .addComponent(filtrar_parte1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(B_Parte1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelMaestrosLayout = new javax.swing.GroupLayout(panelMaestros);
        panelMaestros.setLayout(panelMaestrosLayout);
        panelMaestrosLayout.setHorizontalGroup(
            panelMaestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelMaestrosLayout.createSequentialGroup()
                .addGroup(panelMaestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMaestrosLayout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(boton_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(boton_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(boton_Eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(boton_Exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(boton_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(boton_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelMaestrosLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(panelMaestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelMaestrosLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panelMaestrosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelMaestrosLayout.setVerticalGroup(
            panelMaestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMaestrosLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMaestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMaestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(boton_Limpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(boton_Salir))
                    .addComponent(boton_Exportar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMaestrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(boton_Modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(boton_Eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(boton_Agregar, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMaestros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMaestros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String validarEntradasxAgregar() {
        String mensaje = "";

        if ( txtnumParte.getText().equals("") ) {
            mensaje = mensaje + "FALTA NUMERO DE PARTE";
        }
        if ( txtDescripcion.getText().equals("") ) {
            mensaje = mensaje + "\nFALTA DESCRIPCION";
        }
        if ( comboLinea.getSelectedIndex() == 0 ) {
            mensaje = mensaje + "\nFALTA SELECCIONAR LINEA";
        }
        if ( txtPrecioLista.getText().equals("") ) {
            mensaje = mensaje + "\nFALTA INGRESAR PRECIO LISTA";
        }

// JSP 16.12.2023
        kkk = parseDouble(txtPrecioLista.getText());
        ttt = parseDouble(txtFOBUltimo.getText());
        mmm = parseDouble(txtPrecioCostoUltimo.getText());

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

        if ( txtnumParte.getText().equals("")) {
            mensaje = mensaje + "FALTA NUMERO DE PARTE";
        }
        if ( txtDescripcion.getText().equals("") ) {
            mensaje = mensaje + "\nFALTA DESCRIPCION";
        }

        if ( comboLinea.getSelectedIndex() == 0 ) {
            mensaje = mensaje + "\nFALTA SELECCIONAR LINEA";
        }
//        if ( comboMovimiento.getSelectedIndex() == 0 || comboMovimiento.getSelectedIndex() == 1 ) {
//            mensaje = mensaje + "\nFALTA SELECCIONAR MOVIMIENTO";
//        }
//        if ( txtCostoPromedio.getText().equals("") ) {
//            mensaje = mensaje + "\nFALTA INGRESAR COSTO PROMEDIO";
//        }
        
        if ( txtPrecioLista.getText().equals("")) {
            mensaje = mensaje + "\nFALTA INGRESAR PRECIO LISTA";
        }


        
// JSP 16.12.2023
        kkk = parseDouble(txtPrecioLista.getText());
        ttt = parseDouble(txtFOBUltimo.getText());
        mmm = parseDouble(txtPrecioCostoUltimo.getText());
        
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

    private boolean esRepuestoRepetido(String numeroParte) {
        return ( sm.GetRepuesto_Codigo(numeroParte) != null );
    }
    
    private void boton_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_AgregarActionPerformed

        if ( seleccionada == false ) {
            String validacion = validarEntradasxAgregar();

            if ( !validacion.equals("") ) {
                JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);
                
            } else if ( esRepuestoRepetido(txtnumParte.getText()) ) {
                JOptionPane.showMessageDialog(null, "El N° de Parte ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                
            } else {
                int verif;
                verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operación?", "CONFIRMACIÓN", 0);
                
                if ( verif == 0 ) {

                    String id = txtId.getText();
                    String a = txtnumParte.getText();
                    String b = txtDescripcion.getText();
                    String c = txtDescripModelo.getText();
                    String d = txtDescripLista.getText();
                    String d2 = txtDescripLista2.getText();
                    String e = txtCodSegundo.getText();
                    String f = txtAplicacion.getText();
                    String g = txtInventario.getText();

                    String h = comboLinea.getSelectedItem().toString();
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
                    String x = txtMarcaProveedor.getText();
                    Date fechaRegistro = txtFechaRegistro.getDate();
                    String xx = txtNumAlternativo1.getText();
                    
                    String stock = txtStock.getText();
//                    System.out.println("stock:" + stock);

                    Servicio_Equipos li = new Servicio_Equipos(null);
                    Servicio_Estratificacion es = new Servicio_Estratificacion();
                    h = h.split(" - ", 2)[1];
                    Equipos equipos = li.buscarEquiposx_Nombre(h);
                    Estratificacion estr = es.getEstratificacion_por_nombre("N");

                    int numlin = equipos.getIdequipo();
                    Repuestos rep = new Repuestos();
                    RepuestosId repid = new RepuestosId();

                    repid.setIdequipo(numlin);
                    repid.setIdrepuesto(Integer.parseInt(id));

                    rep.setId(repid);
                    rep.setCodrepuesto(a);
                    rep.setDescripcion(b);
                    rep.setDescrmodelo(c);
                    rep.setDesclista(d);
                    rep.setDesclista2(d2);
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
                    }                        
 
                    rep.setUbicalmacen(u);
                    rep.setUnidadventa(v);
                    rep.setPartidarancel(w);
                    rep.setMarca(x);
                    rep.setFecharegistro(fechaRegistro);
                    
                    String img = null;
                    if ( !"".equals(txtImagen.getText()) ) {
                        img = txtImagen.getText();
                    }
                    rep.setImagen(img);
                    rep.setUsuario(xx);

                    if ( sm.addRepuestos(rep) ) {
                        JOptionPane.showMessageDialog(null, "Se registró el repuesto");
                        Object[] row = {a, e, b, c, d, stock, x, t, m, k};
                        //Object[] row = {a, e, b, stock, k, l, m, n};
                        modelo.insertRow(0, row);
                        clean();
                        cargarImagen(null); // Desaparece imagen y limpia su nombre
                        txtId.setText(String.valueOf(sm.nextId()));
                        
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
        txtnumParte.setEditable(false);
        if ( seleccionada == false ) {
            comboMovimiento.setEnabled(true);
            txtStock.setEnabled(true);
            txtNumAlternativo1.setEnabled(true);
        }
        fila = tablaRepuestos.getSelectedRow();

        String a = tablaRepuestos.getValueAt(fila, 0).toString();
        Repuestos rep = sm.getRepuesto_CodRep(a);
        int id = sm.getRepuesto_CodRep(a).getId().getIdrepuesto();
        txtId.setText(String.valueOf(id));
        txtnumParte.setText(a);
        txtDescripcion.setText(tablaRepuestos.getValueAt(fila, 1).toString());
        txtDescripModelo.setText(rep.getDescrmodelo());
        txtDescripLista.setText(rep.getDesclista());
        txtDescripLista2.setText(rep.getDesclista2());
        
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

        if ( tablaRepuestos.getValueAt(fila, 4) == null ) {
            txtStock.setText(String.valueOf(0));
        } else {
            txtStock.setText(tablaRepuestos.getValueAt(fila, 4).toString());
        }

        String linea;
        int codlin;
        if ( rep.getEquipos() == null ) {
            comboLinea.setSelectedIndex(0);
        } else {
            linea = rep.getEquipos().getDescripcion();
            codlin = rep.getEquipos().getIdequipo();
            comboLinea.setSelectedItem(codlin + "-" + linea);
        }

        if (rep.getNroalternativo() == null) {
            txtNumAlternativo.setText(String.valueOf(0));
        } else {
            txtNumAlternativo.setText(String.valueOf(rep.getNroalternativo()));
        }
        //Precio Lista
        if ( tablaRepuestos.getValueAt(fila, 5) == null ) {
            txtPrecioLista.setText(String.valueOf(0.0));
        } else {
            txtPrecioLista.setText(tablaRepuestos.getValueAt(fila, 5).toString());
        }
        //Costo Promedio
        if ( tablaRepuestos.getValueAt(fila, 6) == null) {
            txtCostoPromedio.setText(String.valueOf(0.0));
        } else {
            txtCostoPromedio.setText(tablaRepuestos.getValueAt(fila, 6).toString());
        }
         if ("ADMINISTRADOR".equals(roltemporal)){   
            txtCostoPromedio.setText(String.valueOf(0.0));
         }
        
        //Precio Costo Ultimo
        if ( tablaRepuestos.getValueAt(fila, 7) == null ) {
            txtPrecioCostoUltimo.setText(String.valueOf(0.0));
        } else {
            txtPrecioCostoUltimo.setText(tablaRepuestos.getValueAt(fila, 7).toString());
        }
         if ("ADMINISTRADOR".equals(roltemporal)){   
            txtPrecioCostoUltimo.setText(String.valueOf(0.0));
         }        

        txtFechaPrecioCosto.setDate(rep.getFechapcosto());

        // FOB
        if ( tablaRepuestos.getValueAt(fila, 8) == null ) {
            txtFOBUltimo.setText(String.valueOf(0.0));
        } else {
            txtFOBUltimo.setText(tablaRepuestos.getValueAt(fila, 8).toString());
        }
         if ("ADMINISTRADOR".equals(roltemporal)){   
            txtFOBUltimo.setText(String.valueOf(0.0));
         }        
        
        //Precio Costo Temporal
        txtPrecioCostoTemp.setText(String.valueOf(rep.getPcostotemporal()));
//        if ( tablaRepuestos.getValueAt(fila, 7) == null ) {
//            txtPrecioCostoTemp.setText(String.valueOf(0.0));
//        } else {
//            txtPrecioCostoTemp.setText(tablaRepuestos.getValueAt(fila, 7).toString());
//        }

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
        txtMarcaProveedor.setText(rep.getMarca());
        txtFechaRegistro.setDate(rep.getFecharegistro());

        System.out.println("Aqui puede ser : " + nombreUsuario);
        if (rep.getUsuario() == null) {
            txtNumAlternativo1.setText(String.valueOf(0));
        } else {
            txtNumAlternativo1.setText(rep.getUsuario());
        }
        
        
        // Cargar imagen
//        System.out.println("(tablaRepuestosKeyReleased)");
        cargarImagen(rep);
       
        seleccionada = true;
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

                    String a = txtnumParte.getText();
                    String b = txtDescripcion.getText();
                    String c = txtDescripModelo.getText();
                    String d = txtDescripLista.getText();
                    String d2 = txtDescripLista2.getText();
                    String e = txtCodSegundo.getText();
                    String f = txtAplicacion.getText();
                    String g = txtInventario.getText();
                    String stock = txtStock.getText();
                    String equipo = comboLinea.getSelectedItem().toString();
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
                    String x = txtMarcaProveedor.getText();
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

                    Repuestos rep = sm.getRepuesto_CodRep(a);

                    rep.setCodrepuesto(a);
                    rep.setDescripcion(b);
                    rep.setDescrmodelo(c);
                    rep.setDesclista(d);
                    rep.setDesclista2(d2);
                    rep.setCodigoseg(e);
                    rep.setAplicacion(f);
                    rep.setInventario(Integer.parseInt(g));
                    //rep.setStock(Integer.parseInt(stock));
                    rep.setEquipos(li);
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
                    rep.setMarca(x);

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
                        mod.setValueAt(a, fila, 0);                         // N° de Parte
                        mod.setValueAt(b, fila, 1);                         // Descripción
                        mod.setValueAt(c, fila, 2);                         // Aplicacion 1
                        mod.setValueAt(d, fila, 3);                         // Anotación 1
                        mod.setValueAt(stock, fila, 4);                     // Stock
                        mod.setValueAt(k, fila, 5);                         // Precio Lista
                        mod.setValueAt(l, fila, 6);                         // Costo Promedio
                        mod.setValueAt(m, fila, 7);                         // P.Costo Último
                        mod.setValueAt(t, fila, 8);                         // FOB
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
        txtnumParte.setEditable(false);
        
        if ( seleccionada == false ) {
            comboMovimiento.setEnabled(true);
        }
        fila = tablaRepuestos.getSelectedRow();
        String a = tablaRepuestos.getValueAt(fila, 0).toString();
        Repuestos rep = sm.getRepuesto_CodRep(a);

        int id = rep.getId().getIdrepuesto();
        txtNumAlternativo1.setText(rep.getUsuario());

        txtId.setText(String.valueOf(id));
        txtnumParte.setText(a);
        txtDescripcion.setText(tablaRepuestos.getValueAt(fila, 2).toString());
        
        txtDescripModelo.setText(rep.getDescrmodelo());

        txtDescripLista.setText(rep.getDesclista());
        txtDescripLista2.setText(rep.getDesclista2());
        
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

     
        if ( !tablaRepuestos.getValueAt(fila, 5).toString().equals("") ) {
            txtStock.setText(tablaRepuestos.getValueAt(fila, 5).toString());
            
        } else {
            txtStock.setText(String.valueOf(0));
        }

        String linea;
        int codlin;
        if ( rep.getEquipos() == null ) {
            comboLinea.setSelectedIndex(0);
            
        } else {
            linea = rep.getEquipos().getDescripcion();
            codlin = rep.getEquipos().getIdequipo();
            comboLinea.setSelectedItem(codlin + " - " + linea);
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
        txtPrecioCostoTemp.setText(String.valueOf(0.0));
//        if ( tablaRepuestos.getValueAt(fila, 7)!=null ) {
//            txtPrecioCostoTemp.setText(tablaRepuestos.getValueAt(fila, 7).toString());
//            
//        } else {
//            txtPrecioCostoTemp.setText(String.valueOf(0.0));
//        }

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
        txtMarcaProveedor.setText(rep.getMarca());
        txtFechaRegistro.setDate(rep.getFecharegistro());
        
        // Cargar imagen
        //System.out.println("(tablaRepuestosMouseReleased)");
        seleccionada = true;
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

    private void txtPrecioListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioListaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioListaActionPerformed

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
                if ( esRepuestoRepetido(txtnumParte.getText()) ) {
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

    private void B_ParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_ParteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_ParteActionPerformed

    private void B_DescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_DescActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_DescActionPerformed

    private void B_Parte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Parte1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_B_Parte1ActionPerformed

    private void B_Parte1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_B_Parte1KeyTyped
        if ( evt.getKeyChar() == 10 ) {
            filtrar_parte1.doClick();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_B_Parte1KeyTyped

    private void filtrar_parte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrar_parte1ActionPerformed
        int caso = 0;
        if ("ADMINISTRADOR".equals(roltemporal)){        
            caso = 5;
        } else {
            caso = 7;
        }
        List lista = sm.BuscarRepMaestro(caso, B_Parte1.getText());
        BorrarTabla();
        Iterator it = lista.iterator();
        
        while ( it.hasNext() ) {
            Object[] repuesto = (Object[]) it.next();
            table.addRow(repuesto);
        }

    }//GEN-LAST:event_filtrar_parte1ActionPerformed

    private void txtFiltroAplica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroAplica1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiltroAplica1ActionPerformed

    private void txtDescripLista2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripLista2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripLista2ActionPerformed

    private void txtImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImagenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImagenActionPerformed

    private void btnAmpImg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmpImg1ActionPerformed
        try {
            String ruta = c.getRutaimagenes();
            String codRep = txtnumParte.getText();
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
        
//        excel.exportarExcel("Maestro de Artículos", listaCabecera, 1);
        servicio_Excel.Exportar_Excel(3);
    }//GEN-LAST:event_boton_ExportarActionPerformed

    private void tablaRepuestosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRepuestosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaRepuestosMouseClicked

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

    private void comboLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLineaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboLineaActionPerformed


    private void eliminarImagen() {
//        System.out.println("eliminarImagen...");
        
        String ruta = c.getRutaimagenes();
        String codRep = txtnumParte.getText();
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
            String codRep = txtnumParte.getText();
            String nombreImagen = codRep;
            int fila = tablaRepuestos.getSelectedRow();
            Repuestos rep = null;

            if ( fila == -1 ) { // No hay repuesto seleccionado del JTable (agregando imagen para nuevo repuesto)
                rep = new Repuestos();
                
            } else {
                rep = sm.GetRepuesto_Codigo(codRep);
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
            String path = rutaImagen + "\\" + nombreImagen + "." + extension;
            path = path.replaceAll(" ", "");
            path = path.replaceAll("/", "");
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
        comboLinea.setSelectedIndex(1);
        comboMovimiento.setSelectedIndex(1);
        tablaRepuestos.clearSelection();
        txtnumParte.setEditable(true);
        
        txtImagen.setText("");
    }

    private void llenar_lineas() {
        Servicio_Equipos sr = new Servicio_Equipos(null);
        Iterator it = sr.getList().iterator();

        while ( it.hasNext() ) {
            Equipos l = (Equipos) it.next();
            comboLinea.addItem(l.getIdequipo() + " - " + l.getDescripcion());
        }
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
    public javax.swing.JTextField B_Parte1;
    public javax.swing.JButton boton_Agregar;
    public javax.swing.JButton boton_Eliminar;
    public javax.swing.JButton boton_Exportar;
    public javax.swing.JButton boton_Limpiar;
    public javax.swing.JButton boton_Modificar;
    public javax.swing.JButton boton_Salir;
    public javax.swing.JButton btnAddImg;
    public javax.swing.JButton btnAmpImg1;
    public javax.swing.JButton btnDelImg;
    public javax.swing.ButtonGroup buttonGroup1;
    public javax.swing.JComboBox comboLinea;
    public javax.swing.JComboBox comboMovimiento;
    public javax.swing.JButton filtrar_aplica1;
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
    public javax.swing.JTextField txtDescripLista2;
    public javax.swing.JTextField txtDescripModelo;
    public javax.swing.JTextField txtDescripcion;
    public javax.swing.JTextField txtFOBUltimo;
    public com.toedter.calendar.JDateChooser txtFechaPrecioCosto;
    public com.toedter.calendar.JDateChooser txtFechaRegistro;
    public javax.swing.JTextField txtFiltroAplica1;
    public javax.swing.JTextField txtId;
    public javax.swing.JTextField txtIdAnterior;
    public javax.swing.JTextField txtImagen;
    public javax.swing.JTextField txtInventario;
    public javax.swing.JTextField txtMarcaProveedor;
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
    public javax.swing.JTextField txtnumParte;
    // End of variables declaration//GEN-END:variables

    private Icon newImageIcon(URL resource) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
