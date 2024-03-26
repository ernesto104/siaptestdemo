package Presentacion.Facturacion;

import Entidades.Cabeces;
import Entidades.Clientes;
import Entidades.Control;
import Entidades.Detallees;
import static Entidades.Otros.Constante.GENERA_GR;
import static Entidades.Otros.Constante.SISTEMA_FACT_FICTICIA;
import static Entidades.Otros.Constante.SISTEMA_GR;
import static Entidades.Otros.Constante.USUARIO_LUCY;
import Entidades.Otros.DatosGR;
import Entidades.Otros.Fecha;
import Entidades.Otros.ImpresionExcel;
import Entidades.Sistema;
import Entidades.Transportistas;
import Entidades.Usuarios;
import Mantenimiento.ControlDAO;
import Mantenimiento.Navegacion;
import Mantenimiento.SistemaDAO;
import Servicios.Comision.Validar_Mayusculas;
import Servicios.Limitador;
import Servicios.Servicio_Cabeces;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Transportista;
import Servicios.TipoMensaje;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import jxl.read.biff.BiffException;
import Servicios.facturacion.Servicio_Documentos;
import Servicios.facturacion.Servicio_Impresion;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class IU_ImprimirGR extends javax.swing.JFrame {

    int maxlineas;
    private Clientes cliente;
    private Cabeces cabec;
    private DateFormat df;
    private Date fecha;
    private String motivoSelec;
    private Servicio_Transportista servicioTranspor;
    private Servicio_Cabeces servicioCabeces;
    private List<Transportistas> listaTranspor;
    private Transportistas transporSelec;
    private Sistema sis;
    private ArrayList<Detallees> detalles;
    String nroGuia;
    
    Servicio_Excel servicio_Excel;
    IU_Facturacion iuf;
    Control control;

    // Para transportista
    TipoMensaje tm = new TipoMensaje();
//    Servicio_Transportista servicioTrans = new Servicio_Transportista(null);
    public Transportistas trans;
    
    // Configurar validacion de tipo y N° de caracteres para componentes de Transportista en interfaz de usuario
    public JTextField[] componentes;
    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo = new ArrayList<>();
    private final ArrayList<String> tipoDato = new ArrayList<>();
    
    private int provieneDe;
    private Servicio_Documentos sf;
    private Servicio_Documentos sf2;
    
    // Para Impresión Jasper (1° Versión de M&D)
    private String numPedido;
    private ControlDAO controlDao;
    
    private Usuarios usuario;
    
    // Impresión de GR por Jasper(1° Versión de M&D)
//    public IU_ImprimirGR(Cabeces ca, ArrayList<Detallees> det, int maxlineas, String NumeroGuia, Date fechaEmisionFactura,
//                         String numPedido, IU_Facturacion iuf) {
        
    // Impresión de GR por Excel(2° Versión de C&S)
    public IU_ImprimirGR(IU_Facturacion iuf, Cabeces ca, ArrayList<Detallees> det, int maxlineas, 
                         String NumeroGuia, Date fechaEmisionFactura, Control control,
                         int provieneDe,
                         Usuarios usuario
                        ) {
//                         , String numPedido) { // Para Impresión Jasper (1° Versión de M&D)
        this();
        this.usuario = usuario;
        // Inicio para impresiones por Excel:
        this.iuf = iuf;
        this.control = control;
        // Fin para impresiones por Excel
        
        this.maxlineas = maxlineas;
        
        // Para Impresión Jasper (1° Versión de M&D)
//        this.numPedido = numPedido;
        
        // Inicio para impresiones por Excel:
        this.provieneDe = provieneDe;
        // Fin para impresiones por Excel
        
        sf = new Servicio_Documentos();
        sf2 = new Servicio_Documentos();
        
        cliente = ca.getClientes();
        cabec = ca;
        detalles = det;
        df = new SimpleDateFormat("dd/MM/yyyy");
        
//        fecha = new Date();
        // CAMBIAR FECHA POR FECHA DE FACTURA???
        fecha = fechaEmisionFactura;
                
        Limitador lim = new Limitador(11);
        txt_RucTransportista.setDocument(lim);
        txt_Ruc_TransNuevo.setDocument(lim);
        
        servicioTranspor = new Servicio_Transportista();
        servicioCabeces = new Servicio_Cabeces();
        listaTranspor = servicioTranspor.Listar_Tranportistas();
        nroGuia = NumeroGuia;
        sis = servicioCabeces.getSis(SISTEMA_GR);

        convertirAMayuscula();
        //txt_PuntoPartida.setDocument(new Validar_Mayusculas(txt_PuntoPartida, 20));
//        txt_PuntoPartida.setText("AV. SAN BORJA NORTE 563 SOTANO  - SAN BORJA");
        
        controlDao = new ControlDAO();
        control = controlDao.Obtener_Objeto(1);
        txt_PuntoPartida.setText(control.getNombrealmacen());
        txt_PuntoLlegada.setText(iuf.tx_direccion.getText());
        seteaCliente();
        seteaListaTransportista();
        limpiar();
        
        // Configurando validacion de tipo y cantidad de caracteres a escribir
        crearArrayComponente();
        crearArrayNumMax();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, btn_Registrar);
        asignarEvento();
    }
    
    private void crearArrayComponente() {
        componentes = new JTextField[5];
        componentes[0] = txt_NomTransportista;
        componentes[1] = txt_DirTransportista;
        componentes[2] = txt_Ruc_TransNuevo;
        componentes[3] = txt_Telefono1;
        componentes[4] = txt_Telefono2;
    }
    
    private void crearArrayNumMax() {
        numMaximo.add(50); //nombre
        numMaximo.add(50); //direccion
        numMaximo.add(11); //ruc
        numMaximo.add(20); //telefono1
        numMaximo.add(20); //telefono2
    }

    private void crearArrayTipoDato() {
        tipoDato.add("S"); //nombre
        tipoDato.add("S"); //direccion
        tipoDato.add("I"); //ruc
        tipoDato.add("S"); //telefono1
        tipoDato.add("S"); //telefono2
    }

    private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }
    
    private void convertirAMayuscula() {
        txt_NomTransportista.setDocument(new Validar_Mayusculas(txt_NomTransportista, 50));
        txt_DirTransportista.setDocument(new Validar_Mayusculas(txt_DirTransportista, 50));
    }

    public IU_ImprimirGR() {
        initComponents();
    }

    private void seteaCliente() {
        txt_NomCliente.setEnabled(false);
        String direccionPartida = control.getNombrealmacen();
        txt_PuntoPartida.setText(direccionPartida);
        txt_DirCliente.setEnabled(false);
        txt_RucCliente.setEnabled(false);
        txt_Fecha.setEnabled(false);
        txt_NomCliente.setText(cliente.getNombre());
        txt_DirCliente.setText(cliente.getDireccion());// + " - " + cliente.getPlaza());
        txt_RucCliente.setText(cliente.getRuc());
        txt_Fecha.setText(df.format(fecha));
        //ID
        txt_NumeroGuia.setText(nroGuia);
        
        //Campos nuevos provenientes de Factura Siar Orbit
        txt_Placa.setText(cabec.getObservaciones());
        txt_Marca.setText(cabec.getMarca());
        txt_OT.setText(cabec.getOrdenTransportista());
        txt_Siniestro.setText(cabec.getSiniestro());
        
        txt_Placa.setEnabled(false);
        txt_Marca.setEnabled(false);
        txt_OT.setEnabled(false);
        txt_Siniestro.setEnabled(false);
    }

    private void seteaListaTransportista() {
        cb_SelecTransportista.addItem("<Seleccione>");
        for (Transportistas t : listaTranspor) {
            cb_SelecTransportista.addItem(t.getNombre());
        }
    }

    private void habilitaTransportista(boolean op) {
        txt_NomTransportista.setEnabled(op);
        txt_DirTransportista.setEnabled(op);
        txt_Telefono1.setEnabled(op);
        txt_Telefono2.setEnabled(op);
    }

    private void seteaTransportista() {
        if ( transporSelec != null ) {
            txt_NomTransportista.setText(transporSelec.getNombre());
            txt_DirTransportista.setText(transporSelec.getDireccion());
            txt_RucTransportista.setText(transporSelec.getRuc());
            txt_Ruc_TransNuevo.setText(transporSelec.getRuc());
            txt_Telefono1.setText(transporSelec.getTelefono());
            txt_Telefono2.setText(transporSelec.getTelefono2());
            
        } else {
            txt_NomTransportista.setText("");
            txt_DirTransportista.setText("");
            txt_Ruc_TransNuevo.setText("");
            txt_Telefono1.setText("");
            txt_Telefono2.setText("");
        }
    }

    private void limpiar() {
        cb_MotivoTraslado.setSelectedIndex(0);
        cb_SelecTransportista.setSelectedIndex(0);
        txt_RucTransportista.setText("");
        transporSelec = null;
        seteaTransportista();
//        habilitaTransportista(false);
        habilitaTransportista(true);
//        btn_Registrar.setEnabled(false);
        btn_Registrar.setEnabled(true);
        String direccionPartida = control.getNombrealmacen();
        txt_PuntoPartida.setText(direccionPartida);
    }

    private Transportistas buscaTransportistaNombre(String nom) {
        for (Transportistas t : listaTranspor) {
            if (t.getNombre().equals(nom)) {
                return t;
            }
        }
        return null;
    }

    private Transportistas buscaTransportistaRuc(String ruc) {
        for (Transportistas t : listaTranspor) {
            if (t.getRuc().equals(ruc)) {
                return t;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_Aceptar = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_NumeroGuia = new javax.swing.JTextField();
        txt_RucCliente = new javax.swing.JTextField();
        txt_Fecha = new javax.swing.JTextField();
        txt_PuntoPartida = new javax.swing.JTextField();
        txt_NomCliente = new javax.swing.JTextField();
        txt_DirCliente = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_Placa = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txt_Marca = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txt_OT = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txt_Siniestro = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txt_PuntoLlegada = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cb_MotivoTraslado = new javax.swing.JComboBox();
        cb_SelecTransportista = new javax.swing.JComboBox();
        txt_RucTransportista = new javax.swing.JTextField();
        btn_Limpiar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_NomTransportista = new javax.swing.JTextField();
        txt_DirTransportista = new javax.swing.JTextField();
        txt_Telefono1 = new javax.swing.JTextField();
        txt_Telefono2 = new javax.swing.JTextField();
        btn_Registrar = new javax.swing.JButton();
        btn_Limpiar_Trans = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txt_Ruc_TransNuevo = new javax.swing.JTextField();
        btn_Cancelar = new javax.swing.JButton();
        btn_Imprimir = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Número de guía");

        jLabel3.setText("Punto de partida");

        jLabel4.setText("Nombre Cliente");

        jLabel5.setText("Dirección");

        jLabel6.setText("Número RUC");

        jLabel7.setText("Fecha");

        txt_NumeroGuia.setEnabled(false);

        txt_PuntoPartida.setEditable(false);

        jLabel19.setText("Placa");

        jLabel20.setText("Marca");

        jLabel21.setText("Orden Transporte");

        jLabel22.setText("Siniestro");

        jLabel23.setText("Punto de llegada");

        txt_PuntoLlegada.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_DirCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                    .addComponent(txt_RucCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_Fecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NumeroGuia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NomCliente, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_PuntoPartida))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(txt_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel21)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_OT, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel22)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_Siniestro, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(296, 296, 296))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel23)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_Placa, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_PuntoLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_NumeroGuia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_PuntoPartida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(txt_PuntoLlegada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_NomCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_DirCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_RucCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txt_Placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txt_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txt_OT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txt_Siniestro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setText("Traslado");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("Motivo de traslado");

        jLabel11.setText("Seleccione transportista");

        jLabel12.setText("RUC");

        cb_MotivoTraslado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Venta", "Venta x confirmar", "Compra", "Consignación", "Devolución", "Traslado de la misma empresa", "Transformación" }));
        cb_MotivoTraslado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_MotivoTrasladoItemStateChanged(evt);
            }
        });

        cb_SelecTransportista.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_SelecTransportistaItemStateChanged(evt);
            }
        });

        txt_RucTransportista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_RucTransportistaKeyPressed(evt);
            }
        });

        btn_Limpiar.setText("Limpiar");
        btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LimpiarActionPerformed(evt);
            }
        });

        jLabel17.setText("(Presione ENTER para buscar)");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 34, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cb_MotivoTraslado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_SelecTransportista, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_RucTransportista, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(btn_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cb_MotivoTraslado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cb_SelecTransportista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_RucTransportista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(btn_Limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel9.setText("Datos Transportista");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setText("Nombre(*)");

        jLabel14.setText("Dirección(*)");

        jLabel15.setText("Teléfono 1");

        jLabel16.setText("Teléfono 2");

        btn_Registrar.setText("Registrar");
        btn_Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegistrarActionPerformed(evt);
            }
        });

        btn_Limpiar_Trans.setText("Limpiar");
        btn_Limpiar_Trans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Limpiar_TransActionPerformed(evt);
            }
        });

        jLabel1.setText("RUC(*)");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(39, 39, 39)
                                .addComponent(txt_Telefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addComponent(btn_Registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(39, 39, 39)
                                .addComponent(txt_Telefono2))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btn_Limpiar_Trans, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 116, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(txt_Ruc_TransNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_NomTransportista)
                            .addComponent(txt_DirTransportista))))
                .addGap(34, 34, 34))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_NomTransportista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txt_DirTransportista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_Ruc_TransNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txt_Telefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txt_Telefono2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_Limpiar_Trans, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btn_Registrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        btn_Cancelar.setText("Salir");
        btn_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelarActionPerformed(evt);
            }
        });

        btn_Imprimir.setText("Imprimir");
        btn_Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ImprimirActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("GUIA DE REMISIÓN");

        javax.swing.GroupLayout btn_AceptarLayout = new javax.swing.GroupLayout(btn_Aceptar);
        btn_Aceptar.setLayout(btn_AceptarLayout);
        btn_AceptarLayout.setHorizontalGroup(
            btn_AceptarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_AceptarLayout.createSequentialGroup()
                .addGap(424, 424, 424)
                .addComponent(btn_Imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(btn_AceptarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btn_AceptarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(btn_AceptarLayout.createSequentialGroup()
                        .addGroup(btn_AceptarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, btn_AceptarLayout.createSequentialGroup()
                                .addGroup(btn_AceptarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(btn_AceptarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        btn_AceptarLayout.setVerticalGroup(
            btn_AceptarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_AceptarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(btn_AceptarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btn_AceptarLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_AceptarLayout.createSequentialGroup()
                        .addGap(0, 8, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(btn_AceptarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cb_MotivoTrasladoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_MotivoTrasladoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            motivoSelec = String.valueOf(evt.getItem());
        }
    }//GEN-LAST:event_cb_MotivoTrasladoItemStateChanged

    private void cb_SelecTransportistaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_SelecTransportistaItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String nom = String.valueOf(evt.getItem());
            if (!nom.equals("<Seleccione>")) {
//                System.out.println("nombre del transportista:" + nom);
                transporSelec = buscaTransportistaNombre(nom); // Obtiene el objeto Transportista seleccionado del Combo
//                System.out.println("transporSelec:" + transporSelec);
                seteaTransportista();
            }
        }
    }//GEN-LAST:event_cb_SelecTransportistaItemStateChanged

    private void txt_RucTransportistaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_RucTransportistaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String ruc = txt_RucTransportista.getText();
            transporSelec = buscaTransportistaRuc(ruc);
            seteaTransportista();
            if (transporSelec == null) {
                habilitaTransportista(true);
                btn_Registrar.setEnabled(true);
            } else {
                btn_Registrar.setEnabled(true);
//                btn_Registrar.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txt_RucTransportistaKeyPressed

    private void btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btn_LimpiarActionPerformed

    private void btn_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btn_CancelarActionPerformed

    private static Date stringToDate(String dateInString) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	try {
            date = formatter.parse(dateInString);
	} catch (ParseException e) {
            e.printStackTrace();
	}
        return date;
    }
    
    public static Date toDate(String fechaString) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formato.parse(fechaString);
        } catch ( Exception ex ) {
            System.out.println("Excepcion: "+ ex.getMessage());
            return null;
        }
    }
    
    private void btn_ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ImprimirActionPerformed
        // FALTA IMPLEMENTAR, MEJORA
        // Condicionar pregunta para generar GR:
        // 1.Si es desde Factura Normal o Factura a partir de GR x consignación: preguntar normal
        // 2.Si es Generar GR por consignación: no hacer la pregunta y mostrar formulario GR
        if ( provieneDe == GENERA_GR ) {
            imprimirGR();
            
        } else {
            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la Operación?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if ( opcion == JOptionPane.YES_OPTION ) {
                imprimirGR();
                
            } else {
                System.out.println("No deseo continuar con la operación para confirmar generar guía de remisión");
            }
        }
    }//GEN-LAST:event_btn_ImprimirActionPerformed

    private void imprimirGR() {
        if ( transporSelec == null ) {
            JOptionPane.showMessageDialog(this, "Complete los campos");

        } else {
            Integer numero = Integer.valueOf(txt_NumeroGuia.getText());
            cabec.setTransportistas(transporSelec);
            cabec.setNroguia(numero);
            cabec.setPuntollegada(txt_PuntoLlegada.getText());

            // Impresión por Jasper (1° Versión de M&D)
            String fechaEmision = txt_Fecha.getText();
            Date fechaDate = stringToDate(fechaEmision);
            Servicio_Impresion impresion = new Servicio_Impresion(maxlineas);
                
                
            if ( provieneDe == GENERA_GR ) {
                // CASO #1: Imprimir GR (Generar GR por consignación, con Factura Ficticia)
                System.out.println("nroGuia:" + cabec.getNroguia());
//                System.out.println("fecha Emision(GR) String : " + txt_Fecha.getText());
//                System.out.println("fecha Emision(GR) Date : " + cabec.getFechaemigr());

                boolean crearGR = sf.guardarGRXConsignacion(cabec, detalles); // Guardar GR
                sis.setUltimonumero(numero);
                
                if ( crearGR ) {
                    // Actualiza GR
                    boolean actualizaGR = sf.actualizarSistema(sis);
//                    System.out.println("Actualiza GR:" + actualizaGR);
//                    System.out.println("actualizando N° GR = " + sis.getUltimonumero());
                    
                    // Actualiza Factura Ficticia
                    Sistema sistema2 = servicioCabeces.getSis(SISTEMA_FACT_FICTICIA);
                    sistema2.setUltimonumero(Integer.parseInt(this.iuf.tx_doc.getText()));
                    boolean actualizaFF = sf2.actualizarSistema(sistema2);
                    System.out.println("Actualiza FF:" + actualizaFF);
//                    System.out.println("actualizando N° Factura Ficticia = " + sistema2.getUltimonumero());
                }
//                System.out.println("crearGR(x consignacion):" + crearGR);

                List<Detallees> lstDet = sf.getDetalleNroGuia(cabec.getNroguia());
                for ( Detallees det : lstDet ) {
                    System.out.println("DETALLE GR(despues de actualizar STOCK):");
//                    System.out.println("id Repuesto : " + det.getRepuestos().getId().getIdrepuesto());
//                    System.out.println("Repuesto    : " + det.getRepuestos().getCodrepuesto());
//                    System.out.println("STOCK       : " + det.getRepuestos().getStock());
//                    System.out.println("Fecha Emision GR : " + det.getFechaemigr());
//
//                    System.out.println("CABECERA GR:");
//                    System.out.println("N° guia(Cabeces) : " + det.getCabeces().getNroguia());
//                    System.out.println("Fecha Emision GR(Cabeces) : " + det.getCabeces().getFechaemigr());
                }
                
                // Impresión por Excel (2° Versión de C&S)
                imprimirGRExcel();
                
                // Impresión por Jasper (1° Versión de M&D)
//                impresion.imprimeGuiaRemisiono(cabec, detalles, txt_PuntoPartida.getText(), fechaDate, 
//                                               txt_DirTransportista.getText(), txt_Telefono1.getText(), txt_Telefono2.getText(),
//                                               numPedido);

            } else {
                // CASO #2: Imprimir GR a partir de una Factura o Boleta Normal
                if ( servicioCabeces.guardarGuiaRemision(cabec) ) { // Actualiza cabeces con Transportista, N° guía y punto de llegada
                    // Impresión por Excel (2° Versión de C&S)
                    imprimirGRExcel();
                    
                    // Impresión por Jasper (1° Versión de M&D)
//                    impresion.imprimeGuiaRemisiono(cabec, detalles, txt_PuntoPartida.getText(), fechaDate, 
//                                                   txt_DirTransportista.getText(), txt_Telefono1.getText(), txt_Telefono2.getText(),
//                                                   numPedido);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Error en la Operación, intente de nuevo");
                    txt_NumeroGuia.setText(String.valueOf(nroGuia));
                }
            }
        }
    }
    
    private void imprimirGRExcel() {
        try {
            JOptionPane.showMessageDialog(null, "Éxito en la Operación");
            sis.setUltimonumero(Integer.parseInt(nroGuia));
            boolean actualizoSistGR = servicioCabeces.actualizarSis(sis); // Actualizar N° documento de Guía de Remisión
//                        System.out.println("actualizó GR en Sistema:" + actualizoSistGR);
//                        Servicio_Impresion impresion = new Servicio_Impresion(maxlineas);

            String fechaEmision = txt_Fecha.getText();
            Date dateEmision = stringToDate(fechaEmision);

            // EXPORTAR A EXCEL, luego IMPRIMIR EXCEL DE GUIA DE REMISIÓN
            String nombreExcelGR = generarExcelGR(cabec, detalles, dateEmision, txt_DirTransportista.getText());

            Control control = new ControlDAO().Obtener_Objeto(1);
            String rutaBDGR = control.getRutaprogramas();
            String rutaExcelGR = rutaBDGR.replace("/", "\\");
            String nombreExcelGuia = rutaExcelGR + "\\" + nombreExcelGR;
            new ImpresionExcel().imprimirDesdeExcel(nombreExcelGuia);
            
            if ( provieneDe == GENERA_GR ) {
                limpiarFormGRConFactFic();
                System.out.println("se limpió tabla");
            }
            dispose();

        } catch ( FileNotFoundException ex ) {
            Logger.getLogger(IU_ImprimirGR.class.getName()).log(Level.SEVERE, null, ex);

        } catch ( BiffException ex ) {
            Logger.getLogger(IU_ImprimirGR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void limpiarFormGRConFactFic() {
        this.iuf.tx_siniestro.setText("");
        System.out.println("Ultimo N° documento:" + this.iuf.tx_doc.getText());
        System.out.println("N° filas:" + this.iuf.tb_factura.getRowCount());
        this.iuf.menu001.limpiarFormGRFactFict(iuf);
    }
    
    private void btn_RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegistrarActionPerformed
        String valido = validar();

        if ( valido.equals(tm.VALIDO) ) {
            trans = new Transportistas();
            trans.setNombre(txt_NomTransportista.getText());
            trans.setDireccion(txt_DirTransportista.getText());
            trans.setRuc(txt_RucTransportista.getText());
            trans.setTelefono(txt_Telefono1.getText());
            trans.setTelefono2(txt_Telefono2.getText());

            servicioTranspor = new Servicio_Transportista();
            Transportistas encontrado = servicioTranspor.Obtener_Transportista_Por_Ruc(txt_Ruc_TransNuevo.getText());

            if ( encontrado == null ) {
                if ( servicioTranspor.addTransportista(trans) ) {
                    tm.Mensaje(tm.EXITO_AGREGAR);
                    recargarTransportistas(trans);

                } else {
                    trans = null;
                    tm.Error(tm.ERROR_AGREGAR);
                }
            } else {
                if ( servicioTranspor.actualizarTransportista(trans) ) {
                    tm.Mensaje(tm.EXITO_MODIFICAR);
                    recargarTransportistas(trans);
                    
                } else {
                    trans = null;
                    tm.Error(tm.ERROR_AGREGAR);
                }
            }
        } else {
            tm.Error(valido);
        }
    }//GEN-LAST:event_btn_RegistrarActionPerformed

    private void recargarTransportistas(Transportistas seleccionado) {
        cb_SelecTransportista.removeAllItems();
        ListarTransportistas();
        
        // actualizar listaTranspor
        servicioCabeces = new Servicio_Cabeces();
        listaTranspor = servicioTranspor.Listar_Tranportistas();
        
        if ( seleccionado != null ) {
            cb_SelecTransportista.setSelectedItem(seleccionado.getNombre());
        }
    }
    
    private void ListarTransportistas() {
        Iterator<Transportistas> it = servicioTranspor.Listar_Tranportistas().iterator();
        while (it.hasNext()) {
            Transportistas t = it.next();
            cb_SelecTransportista.addItem(t.getNombre());
        }
    }
    
    private String validar() {
        String error = "ERROR";
        if (txt_NomTransportista.getText().equals("")) {
            error += "\n-  COMPLETE EL CAMPO NOMBRE";
        }
        if (txt_DirTransportista.getText().equals("")) {
            error += "\n-  COMPLETE EL CAMPO DIRECCION";
        }
        if (txt_Ruc_TransNuevo.getText().equals("")) {
            error += "\n-  COMPLETE EL CAMPO RUC";
        } else {
            try {
                double number = Double.parseDouble(txt_RucTransportista.getText());
                if (txt_RucTransportista.getText().length() != 11) {
                    error += "\n-  LONGITUD DEL NUMERO DE RUC INCORRECTA";
                }
            } catch (NumberFormatException e) {
                error += "\n-  INGRESE UN NUMERO RUC CORRECTO";
            }

        }
        if (error.equals("ERROR")) {
            return tm.VALIDO;
        } else {
            return error;
        }
    }
    
    private void btn_Limpiar_TransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Limpiar_TransActionPerformed
        txt_NomTransportista.setText("");
        txt_DirTransportista.setText("");
        txt_Ruc_TransNuevo.setText("");
        txt_Telefono1.setText("");
        txt_Telefono2.setText("");
    }//GEN-LAST:event_btn_Limpiar_TransActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btn_Aceptar;
    private javax.swing.JButton btn_Cancelar;
    private javax.swing.JButton btn_Imprimir;
    private javax.swing.JButton btn_Limpiar;
    private javax.swing.JButton btn_Limpiar_Trans;
    private javax.swing.JButton btn_Registrar;
    private javax.swing.JComboBox cb_MotivoTraslado;
    private javax.swing.JComboBox cb_SelecTransportista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField txt_DirCliente;
    public javax.swing.JTextField txt_DirTransportista;
    private javax.swing.JTextField txt_Fecha;
    private javax.swing.JTextField txt_Marca;
    private javax.swing.JTextField txt_NomCliente;
    public javax.swing.JTextField txt_NomTransportista;
    private javax.swing.JTextField txt_NumeroGuia;
    private javax.swing.JTextField txt_OT;
    private javax.swing.JTextField txt_Placa;
    private javax.swing.JTextField txt_PuntoLlegada;
    private javax.swing.JTextField txt_PuntoPartida;
    private javax.swing.JTextField txt_RucCliente;
    private javax.swing.JTextField txt_RucTransportista;
    public javax.swing.JTextField txt_Ruc_TransNuevo;
    private javax.swing.JTextField txt_Siniestro;
    public javax.swing.JTextField txt_Telefono1;
    public javax.swing.JTextField txt_Telefono2;
    // End of variables declaration//GEN-END:variables
    
    private String generarExcelGR(Cabeces c, ArrayList<Detallees> det, Date fechaEmision, String direcTransp) 
            throws FileNotFoundException, BiffException {
        
        servicio_Excel = new Servicio_Excel(this.iuf.tb_factura, this);
        DatosGR guia = new DatosGR();
        guia = llenarDatosGR(guia, c, det, fechaEmision, direcTransp, this.iuf);
        servicio_Excel.Exportar_Excel_CabecDet_GR(guia, det);
        return guia.getNombreExcelGR();
    }
    
    private String formatearNroSerie(String serie) {
        String nroSerie = "";
        int largo = 3 - serie.length();;
        for ( int i = 0; i < largo; i++ ) {
            nroSerie = nroSerie + "0";
        }
        nroSerie = nroSerie + serie;
        return nroSerie;
    }

    public DatosGR llenarDatosGR(DatosGR guia, Cabeces c, ArrayList<Detallees> det, Date fechaEmision, String dirTransp, IU_Facturacion iuf) {
        Sistema sistema = new SistemaDAO().Obtener_Sistema_TipoDoc("05");
        int numeroSerie = sistema.getSerie();
        String nroSerieGR = formatearNroSerie(String.valueOf(numeroSerie));
        String nroDocGR = txt_NumeroGuia.getText(); // N° Guía de Remisión
        
        guia.setNroSerie(nroSerieGR);
        guia.setNroDocumento(nroDocGR);
        
        System.out.println("tipo impresora:" + usuario.getImpresora());
        String nombreExcelGR = "\\GuiaRemision_" + numeroSerie + "_" + nroDocGR + ".xls";
        
        if ( USUARIO_LUCY.equals(usuario.getNombre()) ) {
//            nombreExcelDoc += "FacturaC_" + nroSerie + "_" + nroDoc + ".xls";
            nombreExcelGR = "\\GuiaRemisionC_" + numeroSerie + "_" + nroDocGR + ".xls";
        }
//        String nombreExcelGR = "\\GuiaRemision_" + nroSerieGR + "_" + nroDocGR + ".xls";
        
        guia.setNombreExcelGR(nombreExcelGR);

        String tipoDocumento = String.valueOf(iuf.cb_doc.getSelectedItem());
        guia.setTipoDocumento(tipoDocumento); // No es necesario agregarlo a guía, pero por siacaso
        String tipDoc = tipoDocumento.substring(0,1);
        
        String nroSerie = iuf.tx_numSerie.getText(); // N° Serie de Factura
        String nroDoc = iuf.tx_doc.getText(); // N° Factura
        guia.setNroFactura(tipDoc + formatearNroSerie(nroSerie) + "-" + nroDoc);
        
        Control control = new ControlDAO().Obtener_Objeto(1);
        guia.setRutaProgramas(control.getRutaprogramas());
        
        guia.setDireccionEmpresaPartida(txt_PuntoPartida.getText()); // Dirección de la empresa DLN
        guia.setDistPartida(control.getUbidist()); // Distrito de la sede central de DLN
        guia.setProvPartida(control.getUbiprov()); // Provincia de la sede central de  DLN
        guia.setDptoPartida(control.getUbidpto()); // Departamento de la sede central de DLN
        
        guia.setDireccionEmpresaLlegada(txt_PuntoLlegada.getText());
        String dptoLlegada = String.valueOf(iuf.cbDepartamentos.getSelectedItem());
        String provLlegada = String.valueOf(iuf.cbProvincias.getSelectedItem());
        String distLlegada = String.valueOf(iuf.cbDistritos.getSelectedItem());
        
        guia.setDptoLlegada(dptoLlegada);
        guia.setProvLlegada(provLlegada);
        guia.setDistLlegada(distLlegada);
        
        guia.setNombreEmpresa(txt_NomCliente.getText());
        guia.setDireccionEmpresa(txt_DirCliente.getText());
        guia.setRucEmpresa(txt_RucCliente.getText());
        
        String transp = String.valueOf(cb_SelecTransportista.getSelectedItem());
        guia.setNombreTransportista(transp);
        guia.setDireccionTransportista(dirTransp);
        guia.setRucTransportista(txt_Ruc_TransNuevo.getText());
//        guia.setRucTransportista(txt_RucTransportista.getText());
        
        Date f = c.getTipocambio().getFecha();
        guia.setDia(Fecha.getDia(f));
        guia.setMesLetras(Fecha.getMes(f));
        guia.setAño(Fecha.getAnio(f));
        
        guia.setPlaca(iuf.tx_observaciones.getText());
        guia.setOrdenTransporte(iuf.tx_orden.getText());
        guia.setMarca(iuf.tx_marca.getText());
        guia.setSiniestro(iuf.tx_siniestro.getText());
        
        return guia;
    }
}