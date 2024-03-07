 package Presentacion.Facturacion;

import Entidades.*;
 
import static Entidades.Otros.Constante.BD_CONTADO;
import static Entidades.Otros.Constante.BD_CREDITO;
import static Entidades.Otros.Constante.BD_LETRA;
 
import static Entidades.Otros.Constante.CBO_CONTADO;
import static Entidades.Otros.Constante.CBO_CREDITO;
import static Entidades.Otros.Constante.CBO_BOLETA;
import static Entidades.Otros.Constante.CBO_FACTURA;
import static Entidades.Otros.Constante.CBO_LETRA;
import static Entidades.Otros.Constante.CBO_PROFORMA;
import static Entidades.Otros.Constante.CBO_SALIDAS_VARIAS;
//import static Entidades.Otros.Constante.COLUMNA_CODSEC;
import static Entidades.Otros.Constante.COLUMNA_MODELO;
import static Entidades.Otros.Constante.COLUMNA_DISPONIBILIDAD;
import static Entidades.Otros.Constante.COLUMNA_DSCTO3;
import static Entidades.Otros.Constante.COLUMNA_DSCTO4;
import static Entidades.Otros.Constante.COLUMNA_STOCK;
import static Entidades.Otros.Constante.DOLAR_COMBO;
import static Entidades.Otros.Constante.ESTADO_PROFORMA_PENDIENTE;
import static Entidades.Otros.Constante.FACTURA_BOLETA;
import static Entidades.Otros.Constante.FACTURA_NORMAL;
import static Entidades.Otros.Constante.FACT_XGR;
import static Entidades.Otros.Constante.GENERAR_GR;
import static Entidades.Otros.Constante.GENERA_GR;
import Entidades.Otros.DatosProforma;
import Entidades.Otros.DetalleFactura;
import Mantenimiento.CabecesDAO;
import Mantenimiento.ClienteDAO;
import Mantenimiento.ControlDAO;
import Mantenimiento.DetallesDAO;
import Presentacion.FREP002;
import Presentacion.FREP006;
import Servicios.Comision.Validar_Mayusculas;
import Servicios.Constantes;
import static Servicios.Constantes.GLOSA_DOLARES;
import static Entidades.Otros.Constante.ORIGEN_NEXT_FACTURA;
import static Entidades.Otros.Constante.ORIGEN_PROFORMA;
import static Entidades.Otros.Constante.PROFORMA;
import static Entidades.Otros.Constante.PROVIENE_GR_ANTES_DE_FACT;
import static Entidades.Otros.Constante.PROVIENE_GR_DESPUES_DE_FACT;
import static Entidades.Otros.Constante.PROVIENE_PROFORMA;
import static Entidades.Otros.Constante.SISTEMA_FACT_FICTICIA;
import static Entidades.Otros.Constante.SOL_BD;
import static Entidades.Otros.Constante.SOL_COMBO;
import Entidades.Otros.ImpresionExcel;
import Mantenimiento.Facturacion.ProformaDAO;
import Mantenimiento.VendedorDAO;
import Presentacion.MENU001;
import Servicios.CuentasxCobrar.Render_Letra;
import Servicios.Editor_Celdas;
import Servicios.Servicio_Banco;
import Servicios.Servicio_Cabeces;
import Servicios.Servicio_Sistema;
import Servicios.TipoMensaje;
import Servicios.facturacion.Servicio_Documentos;
import Servicios.facturacion.Servicio_Impresion;
import Servicios.facturacion.tabla;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Pagos;
import Servicios.Servicio_Vendedor;
import Servicios.Util;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import Servicios.Servicio_Ubigeo;
import java.awt.FontFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

/**
 *
 * @author Administrador
 */
public final class IU_Facturacion extends javax.swing.JFrame implements Constantes {

    TipoMensaje tm;
    tabla tablaFacturacion;
    Servicio_Documentos sf;
    private JComponent[] comp;
    private JButton[] botones;
    private boolean prof;
    private boolean cabecw;
    private boolean guia;
    private boolean SK;
    private boolean Calculo_Total_Proforma;
    
    private int MaxLineas;
    private String TipoDocumento;
    
    Tipocambio t;
    int igv;
    public Clientes csel;
    private Cabecproformas cabP;
    
    // Lista de cabeceras proformas seleccionadas de la grilla de Proformas a convertir a Factura
    private List<Cabecproformas> listaCabP = new ArrayList<Cabecproformas>();
    
    public static ArrayList<Cabecproformas> cabProforma = new ArrayList<Cabecproformas>();
    private static ArrayList<Detalleproformas> detalleProf = new ArrayList<Detalleproformas>();
    
    private Cabecweb cabw;
    private Cabeces guiaremision;
    private Cabecsalvar cabSalvar;
    Sistema sis;
    List listaSucursales;
    IU_FMaestro maestros;
    IU_Descuentos descuentos;
    IU_Demanda demanda;
    Usuarios usuario;
    int UltimoNumero;
    int NumeroSerie;
    Editor_Celdas editor;
    JTextField edit_field;

    Render_Letra rend;
    CabecesDAO cabdao;
    Servicio_Cabeces scab;
    ControlDAO controlDao;

    DetallesDAO detallesdao;
    ClienteDAO cdao;
    
    Servicio_Sistema ss;
    boolean esProformaAFacturar = false;
    Servicio_Excel servicio_Excel;
    public static JTable tblProformas;
    private boolean canjeFact;
    
    public int monedaControlRepuestos;
    private int idProforma;
    private int provieneDeProf; // Facturación proviene de... { 
                                // 0 = Factura, 
                                // 1 = Proforma, 
                                // 2 = Guía de Remisión (Factura a partir de GR, por consignación)
                                // 3 = Generar GR por consignación (No es factura, sólo GR con factura ficticia)}

    // Agregando para facturar de un (grupo de "X" items) en (grupo "X" de items) para una proforma con mas de "X" items
    private static int punteroGrupo = 0;
    private static int totGrupo;
    private Control control;
    
    private String valorVenta;
    public int provieneDe; // Facturación proviene de... { 
                           // 0 = Factura, 
                           // 1 = Proforma, 
                           // 2 = Guía de Remisión (Factura a partir de GR, por consignación), 
                           // 3 = Generar GR por consignación (No es factura, sólo GR con factura ficticia)}
    public String monedaDeProforma;
//  private int largoDescripcion;
    
    private Servicio_Ubigeo servicioUbigeo;
    private DefaultComboBoxModel modelDptoCombo;
    private DefaultComboBoxModel modelProvCombo;
    private DefaultComboBoxModel modelDistCombo;
    private List listaDptos;
    private List listaProv;
    private List listaDist;
    
    private Cabeces cabecesGuia;
    private List<Detallees> lstDetGR;
    
    public JPanel panelCentral;
    public MENU001 menu001;

    private Vendedores vendSelect;
    
    private String dscto3;
    private String dscto4;
    private String rutaExcel;
    public String CostoTotal;
    
    // boton para el detalle
    public IU_Facturacion(String valorVenta, int opcion, JPanel panelPadre, MENU001 menu001, Usuarios usuario) {
        this.panelCentral = panelPadre;
//        largoDescripcion = 12;
        this.valorVenta = valorVenta;
        this.menu001 = menu001;
        this.usuario = usuario;
    
        rend = new Render_Letra();
        sf = new Servicio_Documentos();
        scab = new Servicio_Cabeces();
        cabdao = new CabecesDAO();
        cdao = new ClienteDAO();
        controlDao = new ControlDAO();
        control = controlDao.Obtener_Objeto(1);
        monedaControlRepuestos = control.getMonedarepuestos() == SOL_COMBO ? 0 : DOLAR_COMBO;
        
        tablaFacturacion = new tabla(valorVenta, monedaControlRepuestos);
        initComponents();
        
// aumente esto de prueba

        
        cargarComboMoneda();
        
        String docPorDefecto = control.getDocfactdefecto();
        cb_doc.setSelectedItem(docPorDefecto);
        
        String dos = String.valueOf(usuario.getRoles());
        System.out.println("dos : " + dos);        
        if ( "ADMINISTRADOR".equals(dos) ){
            cb_doc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Boleta", "Proforma", "Salidas Varias" }));
        } else {
            cb_doc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Boleta", "Proforma" }));
        }
        InitOtherComponents();
//        setOperacionContado();
        alinearColumnaDerecha();
        // Se reemplazó este comentado por siguiente línea abajo:
//        ocultarCodsecStockTabla();
        ocultarStockDispo();
        
//        ocultarColumnaDscto3Dscto4();
//        ocultarColumnaCodSec();
        
        if ( opcion == GENERAR_GR ) {
            this.provieneDe = 3;
            this.provieneDeProf = 3;
            
            jPanel2.setBorder(
                    javax.swing.BorderFactory.createTitledBorder(
                            null, 
                            " GUÍA DE REMISIÓN (Por consignación) ",
                            javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.DEFAULT_POSITION,
                            new java.awt.Font("Tahoma", 1, 20)
                    )
            );
            
            mostrarCabeceraGenerarGR(false);
            
        } else if ( opcion == FACTURA_NORMAL ) {
            this.provieneDe = 0;
            this.provieneDeProf = 0;
//            mostrarCabFactGenerarGR(true);
        }
        this.setLocationRelativeTo(null);
//        inicializarOperacionPorDefault();
        
    }
    
    private void inicializarOperacionPorDefault() {
        String operacionDefault = control.getOpedef();
        
        if ( BD_CREDITO.equals(operacionDefault) ) {
            cb_operacion.setSelectedItem(CBO_CREDITO);
            lb_venc.setVisible(true);
            dt_venc.setVisible(true);
            
        } else if ( BD_CONTADO.equals(operacionDefault)) {
            cb_operacion.setSelectedItem(CBO_CONTADO);
            
        } else if ( BD_LETRA.equals(operacionDefault) ) {
            cb_operacion.setSelectedItem(CBO_LETRA);
        }
    }
    
    private void mostrarCabeceraGenerarGR(boolean mostrar) {
        // CABECERA de formulario "Guía de Remisión (reemplazo de Facturación)"
        lblDocumento.setVisible(mostrar);
        cb_doc.setVisible(mostrar);
        lblOperacion.setVisible(mostrar);
        cb_operacion.setVisible(mostrar);
        
        // FOOTER de formulario "Guía de Remisión (reemplazo de Facturación)"
        lb_documento.setVisible(mostrar);
        tx_numSerie.setVisible(mostrar);
        tx_doc.setVisible(mostrar); // MOSTRAR 2017
    }
    
    private void ocultarColumnaDscto3Dscto4() { 
        tb_factura.getColumnModel().getColumn(COLUMNA_DSCTO3).setMaxWidth(0);
        tb_factura.getColumnModel().getColumn(COLUMNA_DSCTO3).setMinWidth(0);
        tb_factura.getColumnModel().getColumn(COLUMNA_DSCTO3).setPreferredWidth(0);
        
        tb_factura.getColumnModel().getColumn(COLUMNA_DSCTO4).setMaxWidth(0);
        tb_factura.getColumnModel().getColumn(COLUMNA_DSCTO4).setMinWidth(0);
        tb_factura.getColumnModel().getColumn(COLUMNA_DSCTO4).setPreferredWidth(0);
    }
    
    private void inicializarMonedaPorDefecto() {
        int monedaRepuesto = control.getMonedarepuestos() == SOL_COMBO ? 0 : DOLAR_COMBO;
        cb_moneda.setSelectedIndex(monedaRepuesto);
    }
    
    private void cargarComboMoneda() {
        cb_moneda.addItem("Soles");
        cb_moneda.addItem("Dolares");
    }
    
    private void setOperacionContado() {
        if ( "N".equals(control.getVercontoperfact()) ) {
            cb_operacion.removeItemAt(0);
        }
    }
    
    private void alinearColumnaDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tb_factura.getColumnModel().getColumn(5).setCellRenderer(tcr);      // Stock
        tb_factura.getColumnModel().getColumn(6).setCellRenderer(tcr);      // Precio Lista
        tb_factura.getColumnModel().getColumn(9).setCellRenderer(tcr);      // Precio
        tb_factura.getColumnModel().getColumn(10).setCellRenderer(tcr);     // Descuento 1
        tb_factura.getColumnModel().getColumn(11).setCellRenderer(tcr);     // Descuento 2
        tb_factura.getColumnModel().getColumn(12).setCellRenderer(tcr);     // Descuento 3
        tb_factura.getColumnModel().getColumn(13).setCellRenderer(tcr);     // Descuento 4
        tb_factura.getColumnModel().getColumn(14).setCellRenderer(tcr);     // Total
        
        tb_factura.getColumnModel().getColumn(15).setCellRenderer(tcr);     // Disponibilidad
    }
    
//    private void ocultarCodsecTabla() {
    private void ocultarModeloTabla() {
//        tb_factura.getColumnModel().getColumn(COLUMNA_CODIGO_SEC).setMaxWidth(0);
//        tb_factura.getColumnModel().getColumn(COLUMNA_CODIGO_SEC).setMinWidth(0);
//        tb_factura.getColumnModel().getColumn(COLUMNA_CODIGO_SEC).setPreferredWidth(0);
        tb_factura.getColumnModel().getColumn(COLUMNA_MODELO).setMaxWidth(0);
        tb_factura.getColumnModel().getColumn(COLUMNA_MODELO).setMinWidth(0);
        tb_factura.getColumnModel().getColumn(COLUMNA_MODELO).setPreferredWidth(0);
    }
//    private void ocultarColumnaCodSec() {
//        tb_factura.getColumnModel().getColumn(COLUMNA_CODSEC).setMaxWidth(0);
//        tb_factura.getColumnModel().getColumn(COLUMNA_CODSEC).setMinWidth(0);
//        tb_factura.getColumnModel().getColumn(COLUMNA_CODSEC).setPreferredWidth(0);
//    }
    
    private void ocultarStockTabla() {
        tb_factura.getColumnModel().getColumn(COLUMNA_STOCK).setMaxWidth(0);
        tb_factura.getColumnModel().getColumn(COLUMNA_STOCK).setMinWidth(0);
        tb_factura.getColumnModel().getColumn(COLUMNA_STOCK).setPreferredWidth(0);
    }
    
    private void ocultarDisponibilidadTabla() {
        tb_factura.getColumnModel().getColumn(COLUMNA_DISPONIBILIDAD).setMaxWidth(0);
        tb_factura.getColumnModel().getColumn(COLUMNA_DISPONIBILIDAD).setMinWidth(0);
        tb_factura.getColumnModel().getColumn(COLUMNA_DISPONIBILIDAD).setPreferredWidth(0);
    }
    
    private void ocultarStockDispo() {
        ocultarStockTabla();
        ocultarDisponibilidadTabla();
    }

    public IU_Facturacion(List Paq) {
        tablaFacturacion = new tabla();
        sf = new Servicio_Documentos();
        initComponents();
        InitOtherComponents();
        String moneda = String.valueOf(cb_moneda.getSelectedItem());
        tablaFacturacion.agregarPR((ArrayList<PaquetesRepuestos>) Paq, moneda);
        alinearColumnaDerecha();
        this.provieneDeProf = 0;
    }
    
    private void inicializarMonedaDeProforma(ArrayList<Cabecproformas> cps) {
        Cabecproformas cp = cps.get(0);
        monedaControlRepuestos = Integer.parseInt(cp.getMoneda());
//        System.out.println("monedaControlRepuestos:" + monedaControlRepuestos);
        int mon = (monedaControlRepuestos == 1 ? DOLAR_COMBO : (monedaControlRepuestos == 2 ? SOL_COMBO : 0) );
//        System.out.println("mon:" + mon);
        cb_moneda.setSelectedIndex(mon);
    }
    
    public IU_Facturacion(ArrayList<Cabecproformas> cabs, JTable tblProformas, 
                          String valorVenta, String monedaDeProforma) { // Proformas -> Factura
        this.provieneDe = 1;
        this.monedaDeProforma = monedaDeProforma;
        this.valorVenta = valorVenta;
        punteroGrupo = 0;
        
        esProformaAFacturar = true;
        tablaFacturacion = new tabla(valorVenta, monedaControlRepuestos);
        sf = new Servicio_Documentos();
        this.cabProforma = cabs;
        initComponents();
        
        InitOtherComponents();
        inicializarDocumento(cabs);
        cb_doc.setSelectedItem("Factura");
        int i = 0;
        
        controlDao = new ControlDAO();
        control = controlDao.Obtener_Objeto(1);
        int maxItemsFact = control.getNrolineafac();
        int maxItemsGR = control.getNrolineagr();
        int cantMaximaItemsFacturaGR = ( maxItemsFact < maxItemsGR ? maxItemsFact : maxItemsGR);
        
        detalleProf = (ArrayList<Detalleproformas>) sf.getDetalleProf(cabs.get(i));
        totGrupo = detalleProf.size() / cantMaximaItemsFacturaGR; // Reemplazo de '10' por cantMaximaItemsFacturaGR 
        if ( totGrupo % cantMaximaItemsFacturaGR != 0 ) {
            totGrupo++;
        }
        cargarComboMoneda();
        inicializarMonedaDeProforma(cabProforma);
        facturarNItems(ORIGEN_PROFORMA, cantMaximaItemsFacturaGR);
        
        prof = true;
        cb_doc.removeItem("Proforma");
        cb_doc.removeItem("Salidas Varias");
        canjeFact = false;
        
        cb_cliente.setEnabled(true);
        ActivarBotones(true);
        bt_regCliente.setEnabled(true);
        btn_Ultdesc.setEnabled(true);
        bt_imprimir.setEnabled(true);
        bt_cancelar.setEnabled(true);
        alinearColumnaDerecha();
        
        // Se reemplazó este comentado por siguiente línea abajo:
//        ocultarCodsecStockTabla();
        ocultarStockDispo();
        
        this.tblProformas = tblProformas;
        
        int filaSeleccionada = tblProformas.getSelectedRow();
        this.idProforma = Integer.parseInt(String.valueOf(tblProformas.getValueAt(filaSeleccionada, 0)));
        this.provieneDeProf = 1;

        int punteroGrupo = this.cabProforma.get(0).getPunterogrupo();
        if ( punteroGrupo != 0 ) {
            Cabeces cabFacturaAnterior = new Cabeces();
            cabFacturaAnterior = new CabecesDAO().obtenerCabeces(idProforma);
            String siniestroAnterior = cabFacturaAnterior.getSiniestro();
            tx_siniestro.setText(siniestroAnterior);
        }
    }
    
    /* Para que se imprima sólo la cantidad ajustada de items según formato de impresión del documento de Factura
       Entre la Factura y GR, ambas deben cuadrar en cantidad de items, por tanto se toma la que tenga menos items como 
       MAXIMA CANTIDAD ITEMS. */
    public void facturarNItems(int opcionProviene, int cantMaximaItemsFacturaGR) {
        int j = 0;
        if ( ORIGEN_PROFORMA == opcionProviene ) {
            int punteroGrupo = this.cabProforma.get(0).getPunterogrupo();
            j = punteroGrupo; // Proviene de último valor de punteroGrupo en tabla Cabecproforma de la BD
            
        } else if ( ORIGEN_NEXT_FACTURA == opcionProviene ) {
            j = punteroGrupo;
        }
        
        // Se coge cantidad de detalles de una proforma en grupos según la cantidad asignada para factura y GR (mínima entre ambas)
        ArrayList<Detalleproformas> detProfGrupoPorGrupo = new ArrayList<Detalleproformas>();
                
        for ( int k = 0; k < cantMaximaItemsFacturaGR ; k++ ) {
            int indice = (cantMaximaItemsFacturaGR * j) + k;
            
            if ( indice < this.detalleProf.size() ) {
                detProfGrupoPorGrupo.add(this.detalleProf.get(indice));
                
            } else {
                break;
            }
        }
        tablaFacturacion.agregarDP(this.cabProforma.get(0), detProfGrupoPorGrupo);
        Cabecproformas cp = (Cabecproformas) this.cabProforma.get(0);
        listaCabP.add(cp);
        this.punteroGrupo++;
    }
    
    public void aumentarPunteroGrupo() {
        this.punteroGrupo++;
    }

    public int getPunteroGrupo() {
        return punteroGrupo;
    }
    
    public IU_Facturacion(ArrayList<Cabecproformas> cabs) { // Facturar 'N' siguientes items de Proforma
        monedaControlRepuestos = SOL_COMBO;
        esProformaAFacturar = true;
        tablaFacturacion = new tabla();
        sf = new Servicio_Documentos();
        initComponents();
        InitOtherComponents();
        
        inicializarDocumento(cabs);
        
        int maxItemsFact = control.getNrolineafac();
        int maxItemsGR = control.getNrolineagr();
        int cantMaximaItemsFacturaGR = ( maxItemsFact < maxItemsGR ? maxItemsFact : maxItemsGR);
        
        facturarNItems(ORIGEN_NEXT_FACTURA, cantMaximaItemsFacturaGR);
        
        prof = true;
        cb_doc.removeItem("Proforma");
        cb_doc.removeItem("Salidas Varias");
        canjeFact = false;
        
        cb_cliente.setEnabled(true);
        ActivarBotones(true);
        bt_regCliente.setEnabled(true);
        btn_Ultdesc.setEnabled(true);
        bt_imprimir.setEnabled(true);
        bt_cancelar.setEnabled(true);
        alinearColumnaDerecha();
        
        // Se reemplazó este comentado por siguiente línea abajo:
//        ocultarCodsecStockTabla();
        ocultarStockDispo();
        
        int filaSeleccionada = this.tblProformas.getSelectedRow();
        this.idProforma = Integer.parseInt(String.valueOf(tblProformas.getValueAt(filaSeleccionada, 0)));
        this.provieneDeProf = 1;
        
        Cabeces cabFacturaAnterior = new Cabeces();
        cabFacturaAnterior = new CabecesDAO().obtenerCabeces(idProforma);
        String siniestroAnterior = cabFacturaAnterior.getSiniestro();
        tx_siniestro.setText(siniestroAnterior);
    }

    public IU_Facturacion(Cabecweb cw) {//Pedido Web
        tablaFacturacion = new tabla();
        sf = new Servicio_Documentos();
        initComponents();
        InitOtherComponents();
        GenerarFac(cw);
        cabecw = true;
        alinearColumnaDerecha();
        this.provieneDeProf = 0;
    }
    
    public void inicializarMonedaDeGR(Cabeces cabecGuia) {
        monedaControlRepuestos = Integer.parseInt(cabecGuia.getMoneda());
        int mon = (monedaControlRepuestos == 1 ? DOLAR_COMBO : (monedaControlRepuestos == 2 ? SOL_COMBO : 0) );
        cb_moneda.setSelectedIndex(mon);
    }
    
    // Agregar detalle de factura a partir de GR(x consignacion)
//    tablaFacturacion.agregarDP(this.cabProforma.get(0), detProfGrupoPorGrupo);

    public IU_Facturacion(Cabeces cabecGR, List<Detallees> lstDetGR) { // Facturar a partir de una Guía de Remisión (Por consignaci+pn)
        this.cabecesGuia = cabecGR;
        this.lstDetGR = lstDetGR;
//        System.out.println("constructor de IU_Facturacion con cabecGuia...");
        
        tablaFacturacion = new tabla(this, cabecGR.getFechaemigr());
        sf = new Servicio_Documentos();
        initComponents();
        
        cargarComboMoneda();
        inicializarMonedaDeGR(cabecGR);
        
        jPanel2.setBorder(
                javax.swing.BorderFactory.createTitledBorder(
                        null, 
                        " Facturación por GUÍA DE REMISIÓN (Consignación) ", 
                        javax.swing.border.TitledBorder.CENTER, 
                        javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                        new java.awt.Font("Tahoma", 1, 20)
                )
        );
//        ocultarCamposFactura(false);
        habilitarCamposDesdeGR(false);
        
        igv = sf.getIGV();
        GenerarFac(cabecGR, igv); // Agregar detalle de Facturación a partir de GR
        InitOtherComponents();
        
        guia = true;
        alinearColumnaDerecha();
        this.provieneDeProf = 2;
        this.provieneDe = 2;
    }
    
    private void habilitarCamposDesdeGR(boolean habilita) {
        cb_moneda.setEnabled(habilita);
        cb_cliente.setEnabled(habilita);
        tx_direccion.setEnabled(habilita);
        bt_regCliente.setEnabled(habilita);
        cbDepartamentos.setEnabled(habilita);
        cbProvincias.setEnabled(habilita);
        cbDistritos.setEnabled(habilita);
        tx_ruc.setEnabled(habilita);
    }
    
    private void ocultarCamposFactura(boolean ocultar) {
        lblDocumento.setVisible(ocultar);
        cb_doc.setVisible(ocultar);

        lblOperacion.setVisible(ocultar);
        cb_operacion.setVisible(ocultar);
    }

    public IU_Facturacion(Clientes cliente, ArrayList<Detallees> detalles, Cabecsalvar csalv) {//Salidas Varias
        cabSalvar = csalv;
        detallesdao = new DetallesDAO();
        tablaFacturacion = new tabla();
        sf = new Servicio_Documentos();
        initComponents();
        InitOtherComponents();
        cb_doc.removeItem("Salidas Varias");
        cb_doc.removeItem("Proforma");
        csel = cliente;
        cb_cliente.setEnabled(false);
        ActivarBotones(false);
        bt_imprimir.setEnabled(true);
        bt_cancelar.setEnabled(true);
        tablaFacturacion.agregarSV(cliente, detalles);
        cb_cliente.setSelectedItem(csel.getNombre());
        SK = true;
        alinearColumnaDerecha();
        this.provieneDeProf = 0;
    }

    private void InitOtherComponents() {  // GENERAL
        cb_sucursales.setVisible(false);
        lb_sucursales.setVisible(false);
        lbReferencia.setVisible(false);
        tx_referencia.setVisible(false);
        tx_siniestro.setVisible(true);
        prof = false;
        cabP = null;
        guia = false;
        SK = false;
        tm = new TipoMensaje();
        t = sf.getTipoCambio(new GregorianCalendar().getTime());
        igv = sf.getIGV();
//        System.out.println("t:" + t);
//        System.out.println("igv:" + igv);
        tablaFacturacion.setIgv(igv);
        tablaFacturacion.setT(t);
        lb_tipoCambio.setText("Moneda : " + cb_moneda.getSelectedItem());
        
        String tipoDoc = String.valueOf(cb_doc.getSelectedItem());
//        System.out.println("tipoDoc(combo):" + tipoDoc);
        if ( "Factura".equals(tipoDoc) ) {
            TipoDocumento = "Factura";
            MaxLineas = sf.getMaxLineas(1);
            
        } else if ( "Boleta".equals(tipoDoc) ){
            TipoDocumento = "Boleta";
            MaxLineas = sf.getMaxLineas(2);
        }
        
        tablaFacturacion.setF(this);
        Componentes();
        bt_cancelar.setEnabled(true);
        cb_doc.setEnabled(true);
        
        setLocationRelativeTo(null);
        dt_fecha.setDate(new GregorianCalendar().getTime());
        ListarVendedores();
        ListarSoloClientes();
        
        servicioUbigeo = new Servicio_Ubigeo();
        Listar_Departamentos();
        
        String nombre = String.valueOf(cb_cliente.getSelectedItem());
        csel = new ClienteDAO().getxNombre(nombre);
        Ubigeo ubigeo = csel.getUbigeo();
        
        Listar_Provincias(ubigeo.getProvincia());
        Listar_Distritos(ubigeo.getDepartamento(), ubigeo.getProvincia());
        
        mostrarUbigeo(csel);
        
        lb_venc.setVisible(false);
        dt_venc.setVisible(false);
        tb_factura.setCellSelectionEnabled(true);
        PanelConfirmacion(false);
        
        SetGlosa(cb_moneda.getSelectedIndex());
        Calculo_Total_Proforma = false;
        convertirAMayuscula();
        SetEditor();
        mostrarConfirmacionProforma();
        // Si es proforma, no debe mostrar serie, ni motor
        lb_serie.setVisible(false);
        tx_serie.setVisible(false);
        lb_motor.setVisible(false);
        tx_motor.setVisible(false);
        
//        largoDescripcion = 25; //18
        setAnchoColumnas(/*largoDescripcion*/);
        
//        ocultarColumnaDscto3Dscto4();
//        ocultarColumnaCodSec();
    }
    
    private void Listar_Departamentos() {
        modelDptoCombo = new DefaultComboBoxModel();
        listaDptos = servicioUbigeo.Listar_Departamentos_Order_Asc_Nombre();
        for (int i = 0; i < listaDptos.size(); i++) {
            modelDptoCombo.addElement(listaDptos.get(i));
        }
        modelDptoCombo.addElement("");
        cbDepartamentos.setModel(modelDptoCombo);
    }
    
    private void Listar_Provincias(String departamento) {
        modelProvCombo = new DefaultComboBoxModel();
        listaProv = servicioUbigeo.Listar_Provincias_Order_Asc_Nombre(departamento);
        for (int i = 0; i < listaProv.size(); i++) {
            modelProvCombo.addElement(listaProv.get(i));
        }
        modelProvCombo.addElement("");
        cbProvincias.setModel(modelProvCombo);
    }
    
    private void Listar_Distritos(String departamento, String provincia) {
        modelDistCombo = new DefaultComboBoxModel();
        listaDist = servicioUbigeo.Listar_Distritos_Order_Asc_Nombre(departamento, provincia);
        for (int i = 0; i < listaDist.size(); i++) {
            modelDistCombo.addElement(listaDist.get(i));
        }
        modelDistCombo.addElement("");
        cbDistritos.setModel(modelDistCombo);
    }
    
    private void mostrarUbigeo(Clientes cli) {
        Ubigeo ubigeo = cli.getUbigeo();
        String dpto = ubigeo.getDepartamento();
        String prov = ubigeo.getProvincia();
        String dist = ubigeo.getDistrito();
        cbDepartamentos.setSelectedItem(dpto);
        cbProvincias.setSelectedItem(prov);
        cbDistritos.setSelectedItem(dist);
    }
    
    private void convertirAMayuscula() {
        tx_serie.setDocument(new Validar_Mayusculas(tx_serie, 25));
        tx_orden.setDocument(new Validar_Mayusculas(tx_orden, 50));
        tx_nroGuia.setDocument(new Validar_Mayusculas(tx_nroGuia, 30));
        tx_placa.setDocument(new Validar_Mayusculas(tx_placa, 10));
        tx_marca.setDocument(new Validar_Mayusculas(tx_marca, 30));
        tx_modelo.setDocument(new Validar_Mayusculas(tx_modelo, 40));
        tx_motor.setDocument(new Validar_Mayusculas(tx_motor, 20));
        tx_siniestro.setDocument(new Validar_Mayusculas(tx_siniestro, 20));
    }

    private void SetEditor() {
        edit_field = new JTextField();
        editor = new Editor_Celdas(edit_field);
        editor.setClickCountToStart(2);
        for (int i = 0; i < tablaFacturacion.getColumnCount(); i++) {
            tb_factura.getColumnModel().getColumn(i).setCellEditor(editor);
        }
    }

    private void Componentes() {
//        comp = new JComponent[12];
        comp = new JComponent[15];
        comp[0] = cb_doc;
        comp[1] = cb_vend;
        comp[2] = bt_regVend;
        comp[3] = cb_moneda;
        comp[4] = dt_fecha;
        comp[5] = cb_cliente;
        comp[6] = bt_regCliente;
        comp[7] = tx_direccion;
        comp[8] = tx_ruc;
        comp[9] = cb_operacion;
        comp[10] = lbReferencia;
        comp[11] = tx_referencia;
        
        comp[12] = cbDepartamentos;
        comp[13] = cbProvincias;
        comp[14] = cbDistritos;
        
//        comp[15] = tx_siniestro;
        
        botones = new JButton[7];
        botones[0] = bt_maestro;
        botones[1] = bt_quitar;
        botones[2] = bt_descuento;
        botones[3] = bt_demanda;
        botones[4] = bt_imprimir;
        botones[5] = bt_cancelar;
        
        botones[6] = btn_Ultdesc;
    }

    private void PanelConfirmacion(boolean act) {
        lb_documento.setVisible(act);
        lb_placa.setVisible(act);
        lb_marca.setVisible(act);
        
        lb_guia.setVisible(act);
        lb_orden.setVisible(act);
        lb_modelo.setVisible(act);
        
        tx_numSerie.setVisible(act);
        tx_doc.setVisible(act);
        
        tx_placa.setVisible(act);
        tx_marca.setVisible(act);
        
        tx_nroGuia.setVisible(act);
        tx_orden.setVisible(act);
        tx_modelo.setVisible(act);
        
        bt_confirmar.setVisible(act);
        bt_cancel.setVisible(act);
        
        mostrarConfirmacionProforma();
    }
    
    private void mostrarConfirmacionProforma() {
        boolean mostrar = true;
        if ( cb_doc.getSelectedIndex() == 2 ) {
            mostrar = true;
        } else {
            mostrar = false;
        }
        lb_serie.setVisible(mostrar);
        tx_serie.setVisible(mostrar);
        lb_motor.setVisible(mostrar);
        tx_motor.setVisible(mostrar);
    }

    public final void setAnchoColumnas(/*int largoDescripcion*/) {
        JViewport scroll = (JViewport) tb_factura.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tb_factura.getColumnModel();
        TableColumn columnaTabla;
        
        if ( cb_doc.getSelectedIndex() == 2 ) {
            for (int i = 0; i < tb_factura.getColumnCount(); i++) {
                columnaTabla = modeloColumna.getColumn(i);
                switch (i) {
                    case 0: // Item
                        anchoColumna = (3 * ancho) / 100;
                        break;
                    case 1: // Linea
                        anchoColumna = (3 * ancho) / 100;
                        break;
                    case 2: // Nro Parte (5)
                        anchoColumna = (5 * ancho) / 100;
                        break;
//                    case 3: // Cod. Secundario
//                        anchoColumna = (6 * ancho) / 100;
//                        break;
                    case 3: // Descripción
                        anchoColumna = (10 * ancho) / 100;
                        break;
                    case 4: // Modelo = Aplicación
                        anchoColumna = (14 * ancho) / 100;
                        break;
                    case 5: // Stock
                        anchoColumna = (5 * ancho) / 100;
                        break;
                    case 6: // P. Lista
                        anchoColumna = (5 * ancho) / 100;
                        break;
                    case 7: // Cant. pedida
                        anchoColumna = (5 * ancho) / 100;
                        break;
                    case 8: // Cant. entregada
                        anchoColumna = (5 * ancho) / 100;
                        break;
                    case 9: // Precio
                        anchoColumna = (6 * ancho) / 100;
                        break;
                    case 10: // Dcto1
                        anchoColumna = (4 * ancho) / 100;
                        break;
                    case 11: // Dcto2
                        anchoColumna = (4 * ancho) / 100;
                        break;
                    case 12: // Dcto3
                        anchoColumna = (5 * ancho) / 100;
                        break;
                    case 13: // Dcto4
                        anchoColumna = (5 * ancho) / 100;
                        break;
                    case 14: // Total
                        anchoColumna = (7 * ancho) / 100;
                        break;
                    case 15: // Disponibilidad
                        anchoColumna = (11 * ancho) / 100;
                        break;
                }
                columnaTabla.setMaxWidth(anchoColumna);
                columnaTabla.setMinWidth(anchoColumna);
                columnaTabla.setPreferredWidth(anchoColumna);
            }
        } else {
            for (int i = 0; i < tb_factura.getColumnCount(); i++) {
                columnaTabla = modeloColumna.getColumn(i);
                switch (i) {
                    case 0: // Item
                        anchoColumna = (3 * ancho) / 100;
                        break;
                    case 1: // Linea
                        anchoColumna = (3 * ancho) / 100;
                        break;
                    case 2: // Nro Parte (7)
                        anchoColumna = (10 * ancho) / 100;
                        break;
//                    case 3: // Cod. Secundario
//                        anchoColumna = (8 * ancho) / 100;
//                        break;
                    case 3: // Descripción (13)
                        anchoColumna = (13 * ancho) / 100; // 10
                        break;
                    case 4: // Modelo = Aplicación
                        anchoColumna = (20 * ancho) / 100; // 14
                        break;
                    case 5: // Stock
                        anchoColumna = (5 * ancho) / 100;
                        break;
                    case 6: // P. Lista
                        anchoColumna = (5 * ancho) / 100;
                        break;
                    case 7: // Cant. pedida
                        anchoColumna = (5 * ancho) / 100;
                        break;
                    case 8: // Cant. entregada
                        anchoColumna = (5 * ancho) / 100;
                        break;
                    case 9: // Precio
                        anchoColumna = (6 * ancho) / 100;
                        break;
                    case 10: // Dcto1
                        anchoColumna = (4 * ancho) / 100;
                        break;
                    case 11: // Dcto2
                        anchoColumna = (4 * ancho) / 100;
                        break;
                    case 12: // Dcto3
                        anchoColumna = (5 * ancho) / 100;
                        break;
                    case 13: // Dcto4
                        anchoColumna = (5 * ancho) / 100;
                        break;
                    case 14: // Total
                        anchoColumna = (7 * ancho) / 100;
                        break;
                    case 15: // Disponibilidad
                        anchoColumna = (0 * ancho) / 100;
                        break;
                }
                columnaTabla.setMaxWidth(anchoColumna);
                columnaTabla.setMinWidth(anchoColumna);
                columnaTabla.setPreferredWidth(anchoColumna);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGenerarGR = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblDocumento = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cb_doc = new javax.swing.JComboBox();
        cb_vend = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cb_moneda = new javax.swing.JComboBox();
        bt_regVend = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblOperacion = new javax.swing.JLabel();
        cb_cliente = new javax.swing.JComboBox();
        tx_direccion = new javax.swing.JTextField();
        tx_ruc = new javax.swing.JTextField();
        cb_operacion = new javax.swing.JComboBox();
        bt_regCliente = new javax.swing.JButton();
        dt_fecha = new com.toedter.calendar.JDateChooser();
        lb_tipoCambio = new javax.swing.JLabel();
        lb_venc = new javax.swing.JLabel();
        dt_venc = new com.toedter.calendar.JDateChooser();
        lb_sucursales = new javax.swing.JLabel();
        cb_sucursales = new javax.swing.JComboBox();
        lb_mensaje = new javax.swing.JLabel();
        lbReferencia = new javax.swing.JLabel();
        tx_referencia = new javax.swing.JTextField();
        lbSiniestro = new javax.swing.JLabel();
        tx_siniestro = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbDepartamentos = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbProvincias = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        cbDistritos = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_factura = new javax.swing.JTable();
        lb_Total = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pnl_doc = new javax.swing.JPanel();
        tx_numSerie = new javax.swing.JTextField();
        lb_orden = new javax.swing.JLabel();
        tx_doc = new javax.swing.JTextField();
        lb_documento = new javax.swing.JLabel();
        tx_nroGuia = new javax.swing.JTextField();
        lb_modelo = new javax.swing.JLabel();
        tx_serie = new javax.swing.JTextField();
        lb_marca = new javax.swing.JLabel();
        tx_orden = new javax.swing.JTextField();
        lb_placa = new javax.swing.JLabel();
        lb_guia = new javax.swing.JLabel();
        lb_serie = new javax.swing.JLabel();
        lb_motor = new javax.swing.JLabel();
        tx_placa = new javax.swing.JTextField();
        tx_motor = new javax.swing.JTextField();
        tx_marca = new javax.swing.JTextField();
        tx_modelo = new javax.swing.JTextField();
        bt_confirmar = new javax.swing.JButton();
        bt_cancel = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lb_glosa = new javax.swing.JLabel();
        lb_ValorVenta = new javax.swing.JLabel();
        lb_IGV = new javax.swing.JLabel();
        lb_TotalBruto = new javax.swing.JLabel();
        lb_glosa1 = new javax.swing.JLabel();
        lb_glosa2 = new javax.swing.JLabel();
        lb_glosa3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        bt_maestro = new javax.swing.JButton();
        bt_quitar = new javax.swing.JButton();
        bt_descuento = new javax.swing.JButton();
        btn_Ultdesc = new javax.swing.JButton();
        bt_demanda = new javax.swing.JButton();
        bt_imprimir = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        lb_CostoTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        panelGenerarGR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                panelGenerarGRMouseReleased(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FACTURACIÓN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 20))); // NOI18N
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel2MouseExited(evt);
            }
        });

        lblDocumento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDocumento.setText("Documento");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Vendedor");

        cb_doc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_doc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura", "Boleta", "Proforma", "Salidas Varias" }));
        cb_doc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_docItemStateChanged(evt);
            }
        });
        cb_doc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_docActionPerformed(evt);
            }
        });

        cb_vend.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Moneda");

        cb_moneda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_moneda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_monedaItemStateChanged(evt);
            }
        });
        cb_moneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_monedaActionPerformed(evt);
            }
        });

        bt_regVend.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_regVend.setText("Registrar");
        bt_regVend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_regVendActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Cliente");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Direccion");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("RUC");

        lblOperacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblOperacion.setText("Operacion");

        cb_cliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_cliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_clienteItemStateChanged(evt);
            }
        });

        tx_direccion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tx_direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tx_direccionActionPerformed(evt);
            }
        });

        tx_ruc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cb_operacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_operacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Contado", "Credito", "Letras" }));
        cb_operacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_operacionItemStateChanged(evt);
            }
        });

        bt_regCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_regCliente.setText("Registrar");
        bt_regCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_regClienteActionPerformed(evt);
            }
        });

        dt_fecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lb_tipoCambio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_tipoCambio.setText("Moneda : ");

        lb_venc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_venc.setText("Vencimiento");

        dt_venc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lb_sucursales.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_sucursales.setText("Sucursal");

        cb_sucursales.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cb_sucursales.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cb_sucursalesItemStateChanged(evt);
            }
        });

        lb_mensaje.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N

        lbReferencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbReferencia.setText("Referencia");

        tx_referencia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lbSiniestro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSiniestro.setText("Siniestro");

        tx_siniestro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Departamento");

        cbDepartamentos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbDepartamentos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDepartamentosItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Provincia");

        cbProvincias.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbProvincias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProvinciasItemStateChanged(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Distrito");

        cbDistritos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("(*)");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("(*)");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("(*)");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("(*)");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("(*)");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Fecha");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Punto de Venta");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setMinimumSize(new java.awt.Dimension(32, 23));
        jComboBox1.setPreferredSize(new java.awt.Dimension(32, 23));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDocumento)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cb_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cb_doc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(26, 26, 26)
                        .addComponent(cb_vend, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_regVend))
                    .addComponent(lb_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(cbDistritos, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(90, 90, 90)
                                        .addComponent(tx_referencia, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(91, 91, 91)
                                        .addComponent(tx_siniestro, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(cb_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(bt_regCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(tx_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel20))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbProvincias, 0, 148, Short.MAX_VALUE)
                            .addComponent(cbDepartamentos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbReferencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbSiniestro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_operacion, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_tipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(lb_sucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cb_sucursales, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(lb_venc)
                            .addGap(18, 18, 18)
                            .addComponent(dt_venc, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(bt_regCliente))
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tx_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(34, 34, 34))
                                    .addComponent(jLabel18))
                                .addGap(2, 2, 2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(cbDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)
                                    .addComponent(tx_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbSiniestro, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(cbProvincias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tx_siniestro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lbReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tx_referencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbDistritos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11)))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cb_doc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cb_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_regVend)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cb_vend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_operacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOperacion, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_venc, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dt_venc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_sucursales)
                            .addComponent(cb_sucursales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lb_tipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tb_factura.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tb_factura.setModel(tablaFacturacion);
        tb_factura.getTableHeader().setReorderingAllowed(false);
        tb_factura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tb_facturaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tb_factura);

        lb_Total.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lb_Total.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Valor Venta");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Total");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("IGV");

        pnl_doc.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tx_numSerie.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pnl_doc.add(tx_numSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 40, -1));

        lb_orden.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_orden.setText("N° Orden (OT)");
        pnl_doc.add(lb_orden, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, 20));

        tx_doc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pnl_doc.add(tx_doc, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 110, -1));

        lb_documento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_documento.setText("Nº de Documento");
        pnl_doc.add(lb_documento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 20));

        tx_nroGuia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pnl_doc.add(tx_nroGuia, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, 130, -1));

        lb_modelo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_modelo.setText("Modelo");
        pnl_doc.add(lb_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 100, 20));

        tx_serie.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pnl_doc.add(tx_serie, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 160, -1));

        lb_marca.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_marca.setText("Marca");
        pnl_doc.add(lb_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 110, 20));

        tx_orden.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pnl_doc.add(tx_orden, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 130, -1));

        lb_placa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_placa.setText("N° de Placa");
        pnl_doc.add(lb_placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 110, 20));

        lb_guia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_guia.setText("N° Guia Remisión");
        pnl_doc.add(lb_guia, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, 20));

        lb_serie.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_serie.setText("Serie");
        pnl_doc.add(lb_serie, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 110, 20));

        lb_motor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lb_motor.setText("Motor");
        pnl_doc.add(lb_motor, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 100, 20));

        tx_placa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pnl_doc.add(tx_placa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 160, -1));

        tx_motor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pnl_doc.add(tx_motor, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, 130, -1));

        tx_marca.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pnl_doc.add(tx_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 160, -1));

        tx_modelo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pnl_doc.add(tx_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 130, -1));

        bt_confirmar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_confirmar.setText("Confirmar");
        bt_confirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_confirmarActionPerformed(evt);
            }
        });
        pnl_doc.add(bt_confirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 130, 40));

        bt_cancel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_cancel.setText("Cancelar");
        bt_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelActionPerformed(evt);
            }
        });
        pnl_doc.add(bt_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 130, 40));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Total Costo");

        lb_glosa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_ValorVenta.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lb_ValorVenta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lb_IGV.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lb_IGV.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lb_TotalBruto.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lb_TotalBruto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lb_glosa1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_glosa2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_glosa3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        bt_maestro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_maestro.setText("Maestro");
        bt_maestro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_maestroActionPerformed(evt);
            }
        });

        bt_quitar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_quitar.setText("Quitar");
        bt_quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_quitarActionPerformed(evt);
            }
        });

        bt_descuento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_descuento.setText("Descuento");
        bt_descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_descuentoActionPerformed(evt);
            }
        });

        btn_Ultdesc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Ultdesc.setText("Ultimo Descuento x Producto");
        btn_Ultdesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UltdescActionPerformed(evt);
            }
        });

        bt_demanda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_demanda.setText("Demanda");
        bt_demanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_demandaActionPerformed(evt);
            }
        });

        bt_imprimir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_imprimir.setText("Imprimir");
        bt_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imprimirActionPerformed(evt);
            }
        });

        bt_cancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bt_cancelar.setText("Salir");
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(bt_maestro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(bt_quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bt_descuento, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Ultdesc)
                .addGap(18, 18, 18)
                .addComponent(bt_demanda, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(bt_imprimir, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(bt_demanda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Ultdesc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_descuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_quitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_maestro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lb_CostoTotal.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lb_CostoTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout panelGenerarGRLayout = new javax.swing.GroupLayout(panelGenerarGR);
        panelGenerarGR.setLayout(panelGenerarGRLayout);
        panelGenerarGRLayout.setHorizontalGroup(
            panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGenerarGRLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGenerarGRLayout.createSequentialGroup()
                        .addGroup(panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnl_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelGenerarGRLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelGenerarGRLayout.createSequentialGroup()
                                        .addGroup(panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lb_glosa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lb_glosa3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lb_glosa2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panelGenerarGRLayout.createSequentialGroup()
                                        .addGap(93, 93, 93)
                                        .addComponent(lb_glosa1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGenerarGRLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))
                        .addGroup(panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_IGV, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_ValorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_TotalBruto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_CostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(140, 140, 140))
        );
        panelGenerarGRLayout.setVerticalGroup(
            panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGenerarGRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGenerarGRLayout.createSequentialGroup()
                        .addGroup(panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_glosa1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_TotalBruto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelGenerarGRLayout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelGenerarGRLayout.createSequentialGroup()
                                .addComponent(lb_glosa2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(lb_glosa3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(lb_glosa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelGenerarGRLayout.createSequentialGroup()
                                .addComponent(lb_ValorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(lb_IGV, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(lb_Total, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_CostoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelGenerarGRLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnl_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGenerarGR, javax.swing.GroupLayout.PREFERRED_SIZE, 1210, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelGenerarGR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_confirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_confirmarActionPerformed
        String numDoc = tx_doc.getText();
        String numSerie = tx_numSerie.getText();
//        System.out.println("Tras confirmar...");
//        System.out.println(">> N° documento : " + doc);
//        System.out.println(">> N° serie : " + ser);
        int numeroItemsBrowse = tablaFacturacion.getRowCount();
        
//        System.out.println("TipoDocumento (ACTUALIZADO) : " + TipoDocumento);
        
        if ( numeroItemsBrowse > MaxLineas ) {
            tm.Error("MAXIMO N° REPUESTOS PARA " + TipoDocumento + ": " + MaxLineas + " items."
                    + "\nQuite mínimo " + Math.abs(MaxLineas - numeroItemsBrowse) + " items para continuar");
        } else {
            try {
//                Cabeces cab = new Cabeces();
                String tipoTransaccion = "V";
                
                if ( provieneDeProf == 2 || provieneDeProf == 3 ) { // 2 = Factura por GR, 3 = Generar GR por consignación (Caso contrario 0 = Boleta o Factura, 1 = Proforma)
                    TipoDocumento = "FACTURA FICTICIA";
                }
//                System.out.println("En confirmar(TipoDocumento):" + TipoDocumento);
                String tipoDoc = new Servicio_Sistema().getSistemas_por_descripcion(TipoDocumento.toUpperCase()).getTipodoc();
//                System.out.println("-> tipoTransaccion:" + tipoTransaccion);
//                System.out.println("-> tipoDoc:" + tipoDoc);
//                System.out.println("-> N°Serie:" + ser);
//                System.out.println("-> N°Doc:" + doc);
                
                // Buscar una cabecera de tipoDoc y tipoTransaccion, numSerie y numDoc existente
                Cabeces cab = new Servicio_Cabeces().obtenerCabecera(tipoTransaccion, tipoDoc, numSerie, numDoc);
//                System.out.println("cab:" + cab);

                // VALIDACIÓN: Si no existe anterior N° Documento, solicitar confirmacion de impresión y guardado en la BD (ES NUEVO).
                if ( cab == null ) {
//                if ( !doc.equals("") && !ser.equals("") && Integer.parseInt(doc) >= UltimoNumero ) {
                    int op = tm.Confirmacion(tm.PREGUNTA_OPERACION);
                    if ( op == tm.SI ) {
                        try {
//                            System.out.println("imprimir.... : ");
                            Imprimir();
                        } catch (FontFormatException ex) {
                            Logger.getLogger(IU_Facturacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    tm.Error("ERROR, FACTURA YA EXISTE");
                }
                    
            } catch ( NumberFormatException e ) {
                e.printStackTrace();
                tm.Error(tm.NUMERO_ERRONEO);

            } catch ( FileNotFoundException ex ) {
                Logger.getLogger(IU_Facturacion.class.getName()).log(Level.SEVERE, null, ex);

            } catch ( BiffException ex ) {
                Logger.getLogger(IU_Facturacion.class.getName()).log(Level.SEVERE, null, ex);
    //            System.out.println("Error(IU_Facturacion):" + ex.getMessage());
            } catch ( IOException ex ) {
                Logger.getLogger(IU_Facturacion.class.getName()).log(Level.SEVERE, null, ex);

            } catch ( WriteException ex ) {
                Logger.getLogger(IU_Facturacion.class.getName()).log(Level.SEVERE, null, ex);

            } catch ( ParseException ex ) {
                Logger.getLogger(IU_Facturacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_bt_confirmarActionPerformed

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed
        if ( tablaFacturacion.getRowCount() > 0 ) {
            int op = tm.Confirmacion("ADVERTENCIA \nSE PERDERA TODO EL CONTENIDO ¿DESEA CONTINUAR?");
            if ( op == tm.SI ) {
                dispose();
            }
        } else {
            dispose();
        }
    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void habilitarCamposFooterApartirGR(boolean habilitar) {
        tx_nroGuia.setEditable(habilitar);
        tx_orden.setEditable(habilitar);
    }
    
    private void mostrarFooterGenerarGR(boolean mostrar) {
        lb_documento.setVisible(mostrar);
        tx_numSerie.setVisible(mostrar);
        tx_doc.setVisible(mostrar);
    }
    
    private void bt_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imprimirActionPerformed
        String validacion = "";
//        System.out.println(">> indiceTipoDoc:" + cb_doc.getSelectedIndex());
//        System.out.println(">>(OJO) tipo de documento seleccionado:" + cb_doc.getSelectedItem());
            
        switch ( cb_doc.getSelectedIndex() ) {
            case 0:
            case 1:
                validacion = validarFacBol();
                break;
            case 2:
                validacion = validarProf();
                break;
            case 3:
                validacion = validarSK();
                break;
        }
        if ( validacion.equals(tm.VALIDO) ) {
            int op = tm.Confirmacion(tm.PREGUNTA_OPERACION);
            
            int indiceTipoDoc = cb_doc.getSelectedIndex();
            
            System.out.println(">> indiceTipoDoc:" + indiceTipoDoc); 
            System.out.println(">>(OJO) tipo de documento seleccionado:" + cb_doc.getSelectedItem());
    // Si la funcionalidad de la que proviene formulario "Facturación" es "Generación de GR por consignación" (provieneDe = 3)
    // el cb_doc.getSelectedIndex() por defecto aparece con valor "0" (a pesar que no sea visible en el formulario)
    // pues para Generar GR no se necesita "Tipo de documento" (dado que creará una FACTURA FICTICIA), puede ser BOLETA O FACTURA FICTICIA
            
            if ( op == tm.SI ) {
                String descrSist = String.valueOf(cb_doc.getSelectedItem());
                System.out.println(">> descrSist:" + descrSist);
                System.out.println("provieneDeProf:" + provieneDeProf);
                
//                System.out.println("Antes(TipoDocumento):" + TipoDocumento);
                
                if ( provieneDeProf == PROVIENE_GR_ANTES_DE_FACT ) { // Si es "Generación de GR por consignación"
//                    System.out.println("entraaaaa aki");
                    descrSist = SISTEMA_FACT_FICTICIA; // Factura Ficticia (nroDocumento va en aumento en NEGATIVO)
                    sis = sf.getSis(descrSist);
                    UltimoNumero = sis.getUltimonumero() - 1;
                    TipoDocumento = "Factura";
                    
                } else {
//                    System.out.println("proviene de Factura Normal");
                    sis = sf.getSis(descrSist);
                    UltimoNumero = sis.getUltimonumero() + 1;
                }
                NumeroSerie = sis.getSerie();
                
//                System.out.println(">> sis:" + sis);
//                System.out.println("AHORA(TipoDocumento):" + TipoDocumento);
//                System.out.println(">> UltimoNumero:" + UltimoNumero);
//                System.out.println(">> NumeroSerie:" + NumeroSerie);
                
                if ( provieneDeProf == PROVIENE_PROFORMA ) { // Factura que proviene de proforma
                    Cabecproformas proforma = new ProformaDAO().Obtener_Objeto(idProforma);
                    tx_placa.setText(proforma.getPlaca());
                    
                    tx_orden.setText(proforma.getTransportista()); // Por ahora N° orden se está guardando en transportista
                    
                    tx_marca.setText(proforma.getMarca());
                    tx_modelo.setText(proforma.getModelo());
                    
                    habilitarCamposFooterApartirGR(true);
//                    System.out.println("if 1");
                    
                } else if ( provieneDeProf == PROVIENE_GR_DESPUES_DE_FACT ) { // Factura que proviene de guía de remisión (por consignación)
                    habilitarCamposFooterApartirGR(false);
//                    System.out.println("if 2");
                    
                } else { // Factura, Boleta o Generación de GR por consignación
                    habilitarCamposFooterApartirGR(true);
//                    System.out.println("if 3");
                }

//                System.out.println("indiceTipoDoc :: " + indiceTipoDoc);
                // Factura / Boleta (o Generar GR por consignación con Factura Ficticia)
                if ( indiceTipoDoc == CBO_FACTURA || indiceTipoDoc == CBO_BOLETA ) { // 0 = Factura, 1 = Boleta
                    System.out.println("Elegiste Factura o Boleta");
                    System.out.println("UltimoNumero:" + UltimoNumero);
                    desactivarAlConfirmar();
                    setDocumento();
                    
                    tx_nroGuia.setText(String.valueOf(new Servicio_Cabeces().getSis("GUIA DE REMISION").getUltimonumero() + 1));
                    tx_siniestro.setEnabled(false);
                    
                } else if ( indiceTipoDoc == CBO_PROFORMA ) { // (Solo Proformas, no Salidas Varias)
//                  System.out.println("Elegiste Proforma");
                    desactivarAlConfirmar();
                    setDocumento();
                    
                } else if ( indiceTipoDoc == CBO_SALIDAS_VARIAS ) {
                    System.out.println("Elegiste Salidas Varias");
                    desactivarAlConfirmar();
                    setDocumento();
                }
            }
        } else {
            tm.Error(validacion);
        }
        if ( provieneDe == 3) {
            mostrarFooterGenerarGR(false);
        }
    }//GEN-LAST:event_bt_imprimirActionPerformed
    
    private void setDocumento() {
        tx_doc.setText(String.valueOf(UltimoNumero));
        tx_numSerie.setText(String.valueOf(NumeroSerie));
    }
    
    private void desactivarAlConfirmar() {
        ActivarComponentes(false);
        ActivarBotones(false);
        tb_factura.clearSelection();
        tb_factura.setEnabled(false);
        PanelConfirmacion(true);
        
        System.out.println("Panel de confirmacion de impresion SK");
        
        bt_confirmar.requestFocus();
    }
    
    private void bt_demandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_demandaActionPerformed
        if ( demanda == null ) {
            int filat = tb_factura.getSelectedRow();
            
            if ( filat >= 0 ) {
                Repuestos rep = tablaFacturacion.GetIdRep(filat);
                demanda = new IU_Demanda(rep);
                
                demanda.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        demanda = null;
                    }
                });
                
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un repuesto", "No seleccionado", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            demanda.setVisible(true);
        }
    }//GEN-LAST:event_bt_demandaActionPerformed

    private void bt_descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_descuentoActionPerformed
        if ( descuentos == null ) {
            descuentos = new IU_Descuentos();
            
            descuentos.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if ( descuentos.isAplicar() ) {
                        aplicarDct(descuentos.getDes1(), descuentos.getDes2(), descuentos.getDes3(), descuentos.getDes4());
                    }
                    descuentos = null;
                }
            });
        } else {
            descuentos.setVisible(true);
        }
    }//GEN-LAST:event_bt_descuentoActionPerformed

    private void bt_quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_quitarActionPerformed
        int[] filasSel = tb_factura.getSelectedRows();
        if ( filasSel.length > 0 ) {
            int op = tm.Confirmacion("¿DESEA QUITAR LOS REPUESTOS SELECCIONADOS");
            
            if ( op == tm.SI ) {
                for ( int i = filasSel.length - 1; i >= 0; i-- ) {
                    tablaFacturacion.borrar(filasSel[i]);
                }
                tb_factura.clearSelection();
            }
        } else {
            tm.Error("NINGUN REPUESTO SELECCIONADO");
        }
    }//GEN-LAST:event_bt_quitarActionPerformed

    private void bt_maestroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_maestroActionPerformed
        tb_factura.clearSelection();
        if ( maestros == null ) {
            maestros = new IU_FMaestro(this, true, TipoDocumento);
            
            maestros.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    maestros = null;
                }
            });
            maestros.setMaxLineas(MaxLineas, TipoDocumento);
            
        } else {
            maestros.setVisible(true);
        }
    }//GEN-LAST:event_bt_maestroActionPerformed

    private void bt_regClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_regClienteActionPerformed
        FREP002 ic = new FREP002();
        ic.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                cb_cliente.removeAllItems();
                ListarSoloClientes();
//                System.out.println("evento windowClosed");
            }
        });
        ic.setVisible(true);
        ic.setLocationRelativeTo(null);
    }//GEN-LAST:event_bt_regClienteActionPerformed

    private void cb_operacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_operacionItemStateChanged
        if ( evt.getStateChange() == ItemEvent.SELECTED ) {
            if ( cb_operacion.getSelectedIndex() == 1 ) {
                lb_venc.setVisible(true);
                dt_venc.setVisible(true);
                
            } else {
                lb_venc.setVisible(false);
                dt_venc.setVisible(false);
                dt_venc.setDate(null);
            }
        }
    }//GEN-LAST:event_cb_operacionItemStateChanged

    private void cb_clienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_clienteItemStateChanged
        if ( evt.getStateChange() == ItemEvent.SELECTED ) {
            if ( cb_cliente.getSelectedIndex() >= 0 ) {
                String cliente = String.valueOf(cb_cliente.getSelectedItem());
                csel = new ClienteDAO().getxNombre(cliente);
                tx_direccion.setText(csel.getDireccion());
                mostrarUbigeo(csel);
                tx_ruc.setText(csel.getRuc());
                ListarSucursales();
                
            } else {
                tx_direccion.setText("");
                tx_ruc.setText("");
            }
        }
    }//GEN-LAST:event_cb_clienteItemStateChanged

    private void bt_regVendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_regVendActionPerformed
        FREP006 iv = new FREP006();
        iv.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
//                System.out.println("evento windowssClosed...");
                cb_vend.removeAllItems();
                ListarVendedores();
            }
        });
        iv.setVisible(true);
        iv.setLocationRelativeTo(null);
    }//GEN-LAST:event_bt_regVendActionPerformed

    private void cb_monedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_monedaItemStateChanged
//        System.out.println("ingresando a combo MONEDA");
        if ( evt.getStateChange() == ItemEvent.SELECTED ) {
            SetGlosa(cb_moneda.getSelectedIndex());
            System.out.println("cb_moneda.getSelectedIndex:" + cb_moneda.getSelectedIndex());
            System.out.println("monedaControlRepuestos:" + monedaControlRepuestos);
            
            System.out.println("cb_moneda.getSelectedIndex:" + cb_moneda.getSelectedIndex());  

            
            switch ( cb_moneda.getSelectedIndex() ) {
                case 0:
//                  System.out.println("cambiar a SOLES");
                    tablaFacturacion.cambiarASoles();
                    lb_tipoCambio.setText("Moneda : Soles");
                    break;
                    
                case 1:
//                  System.out.println("cambiar a Dolares");
                    tablaFacturacion.cambiarADolares();
                    lb_tipoCambio.setText("Moneda : Dolares");
                    break;


            }
        }
    }//GEN-LAST:event_cb_monedaItemStateChanged
    
    private void cb_docItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_docItemStateChanged
        
        if ( evt.getStateChange() == ItemEvent.SELECTED ) {
            switch ( cb_doc.getSelectedIndex() ) {
                case 0: // Factura
//                    System.out.println("Fact...");
//                    System.out.println("FACTURA");
                    mostrarCamposProforma(false);
                    mostrarSiniestro(true);
                    MaxLineas = sf.getMaxLineas(1);
                    ActivarComponentes(true);
                    ActivarBotones(true);
                    Calculo_Total_Proforma = false;
                    tablaFacturacion.SetCalculoProforma(Calculo_Total_Proforma);
                    inicializarMonedaPorDefecto();
//                    habilitarColumnaDisponibilidad(false);
                    break;
                    
                case 1://Boleta
                    if ( tablaFacturacion.getRowCount() >= MaxLineas ) {
//                        lb_mensaje.setText("MAXIMO DE REPUESTOS SELECCIONADOS(Máx. " + TipoDocumento + " items)");
                        TipoDocumento = String.valueOf(cb_doc.getSelectedItem());
//                        System.out.println("MaxLineas:" + MaxLineas);
//                        System.out.println("TipoDocumento::" + TipoDocumento);
                        lb_mensaje.setText("MAXIMO N° REPUESTOS PARA " + TipoDocumento + ": " + MaxLineas + " items");
                        
                    } else {
//                        System.out.println("BOLETA");
                        mostrarCamposProforma(false);
                        mostrarSiniestro(false);
                        MaxLineas = sf.getMaxLineas(2);
                        ActivarComponentes(true);
                        ActivarBotones(true);
                        Calculo_Total_Proforma = false;
                        tablaFacturacion.SetCalculoProforma(Calculo_Total_Proforma);
                        inicializarMonedaPorDefecto();
//                        habilitarColumnaDisponibilidad(false);
                    }
                    break;
                    
                case 2://Proformas
//                    System.out.println("PROFORMA");
                    mostrarCamposProforma(true);
                    mostrarSiniestro(false);
                    MaxLineas = sf.getMaxLineas(1);
                    ActivarComponentes(true);
                    ActivarBotones(true);
                    cb_operacion.setEnabled(false);
                    cb_vend.setEnabled(true);
                    bt_regVend.setEnabled(false);
                    cb_moneda.setEnabled(false);
                    Calculo_Total_Proforma = true;
                    tablaFacturacion.SetCalculoProforma(Calculo_Total_Proforma);
                    inicializarMonedaPorDefecto();
                    cb_moneda.setEnabled(true);
//                    habilitarColumnaDisponibilidad(true);
                    break;
                    
                case 3://SK
//                    System.out.println("SK");
                    mostrarCamposProforma(false);
                    mostrarSiniestro(false);
                    MaxLineas = sf.getMaxLineas(1);
                    ActivarComponentes(true);
                    ActivarBotones(true);
                    inicializarMonedaPorDefecto();
                    cb_moneda.setEnabled(false);
                    Calculo_Total_Proforma = false;
                    tablaFacturacion.SetCalculoProforma(Calculo_Total_Proforma);
//                    habilitarColumnaDisponibilidad(false);
            }
            TipoDocumento = String.valueOf(cb_doc.getSelectedItem());
//            System.out.println("tipoDoc(evento):" + TipoDocumento);
//            ocultarColumnaDscto3Dscto4();
//            ocultarColumnaCodSec();
        }
    }//GEN-LAST:event_cb_docItemStateChanged

    private void habilitarColumnaDisponibilidad(boolean habilitar) { // LEDIS
//        setAnchoColumnas(largoDescripcion);
        setAnchoColumnas();
        int ancho = 0;
        if ( habilitar ) {
            ancho = (10 * ancho) / 100;
        }
        tb_factura.getColumnModel().getColumn(COLUMNA_DISPONIBILIDAD).setMaxWidth(ancho);
        tb_factura.getColumnModel().getColumn(COLUMNA_DISPONIBILIDAD).setMinWidth(ancho);
        tb_factura.getColumnModel().getColumn(COLUMNA_DISPONIBILIDAD).setPreferredWidth(ancho);
    }
    
    private void mostrarCamposProforma(boolean mostrar) {
        lbReferencia.setVisible(mostrar);
        tx_referencia.setVisible(mostrar);
    }
    
    private void mostrarSiniestro(boolean mostrar) {
        tx_siniestro.setVisible(mostrar);
        lbSiniestro.setVisible(mostrar);
    }
    
    private void deshabilitarLetras() {
        String opcionDocumento = String.valueOf(cb_doc.getSelectedItem());
        if ( "Salidas Varias".equals(opcionDocumento) ){
            cb_operacion.removeItemAt(2); // Obviar opcion Letras (Solo contado y credito)
        }
    }
    
    private void tb_facturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_facturaKeyTyped
        int filat = tb_factura.getSelectedRow();
        if ( filat >= 0 ) {
            if ( evt.getKeyChar() == 127 ) { // delete
                bt_quitar.doClick();
            } else {
                if ( evt.getKeyChar() == 27 ) {
                    cb_doc.requestFocus();
                }
            }

        }
    }//GEN-LAST:event_tb_facturaKeyTyped

    private void bt_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelActionPerformed
        tx_numSerie.setText("");
        tx_doc.setText("");
        tx_serie.setText("");
        tx_orden.setText("");
        PanelConfirmacion(false);
        ActivarComponentes(true);
        ActivarBotones(true);
        if ( prof || SK ) {
            cb_cliente.setEnabled(false);
        }
        lb_serie.setVisible(false);
        tx_serie.setVisible(false);
        lb_motor.setVisible(false);
        tx_motor.setVisible(false);
        tb_factura.setEnabled(true);
        
        tx_siniestro.setEnabled(true);
        
        if ( this.provieneDe == 2 ) {
            habilitarCamposDesdeGR(false);
            
        } else if ( this.provieneDe == 3 ) {
            mostrarFooterGenerarGR(false);
        }
    }//GEN-LAST:event_bt_cancelActionPerformed

    private void cb_sucursalesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cb_sucursalesItemStateChanged
        if ( evt.getStateChange() == ItemEvent.SELECTED ) {
            Sucursales temp = (Sucursales) cb_sucursales.getSelectedItem();
            tx_direccion.setText(temp.getDireccion());
        }
    }//GEN-LAST:event_cb_sucursalesItemStateChanged

    private void btn_UltdescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UltdescActionPerformed
        String clientePlaza = String.valueOf(cb_cliente.getSelectedItem());
//        String[] datosCliente = clientePlaza.split("-");
        Clientes cli = new ClienteDAO().getxNombre(clientePlaza);
//        Clientes cli = cdao.getxNombrePlaza(datosCliente[0], datosCliente[1]);
        
        int filat = tb_factura.getSelectedRow();
        if ( filat >= 0 ) {
            Repuestos rep = tablaFacturacion.GetIdRep(filat);
//            System.out.println("rep"+rep.getCodrepuesto());
            IU_UltimoDescuento ul = new IU_UltimoDescuento(rep, cli);
            ul.setVisible(true);
        }
    }//GEN-LAST:event_btn_UltdescActionPerformed

    private void cbProvinciasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProvinciasItemStateChanged
        String dpto = String.valueOf(cbDepartamentos.getSelectedItem());
        String prov = String.valueOf(cbProvincias.getSelectedItem());
//        System.out.println("(Prov)dpto:" + dpto);
//        System.out.println("(Prov)prov:" + prov);
        Listar_Distritos(dpto, prov);
        cbDistritos.setSelectedItem("");
    }//GEN-LAST:event_cbProvinciasItemStateChanged

    private void cbDepartamentosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDepartamentosItemStateChanged
        String dpto = String.valueOf(cbDepartamentos.getSelectedItem());
//        System.out.println("(Dpto)dpto:" + dpto);
        Listar_Provincias(dpto);
        cbProvincias.setSelectedItem("");
        cbDistritos.setSelectedItem("");
    }//GEN-LAST:event_cbDepartamentosItemStateChanged

    private void cb_docActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_docActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_docActionPerformed

    private void tx_direccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tx_direccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tx_direccionActionPerformed

    private void cb_monedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_monedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_monedaActionPerformed

    private void jPanel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseExited

    private void panelGenerarGRMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelGenerarGRMouseReleased
        // TODO add your handling code here:
        if ( tablaFacturacion.getRowCount() > 0 ) {
            int op = tm.Confirmacion("ADVERTENCIA \nSE PERDERA TODO EL CONTENIDO ¿DESEA CONTINUAR?");
            if ( op == tm.SI ) {
                dispose();
            }
        } else {
            dispose();
        }
    }//GEN-LAST:event_panelGenerarGRMouseReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    // Validaciones para los documentos
    private String validarFacBol() {
        if ( dt_venc.isVisible() ) {
            
            if ( dt_venc.getDate() == null ) {
                return tm.CAMPOS_INCOMPLETOS;
            }
        }

        int k = 0;
        for ( JComponent j : comp ) {
            if ( j instanceof JTextField ) {
                JTextField tx = (JTextField) j;
                
                if ( tx.getText().equals("") && tx != tx_numSerie && tx != tx_doc && k != 11 ) {
                    return tm.CAMPOS_INCOMPLETOS;
                }
            }
            k++;
        }

        if ( tablaFacturacion.getRowCount() <= 0 ) {
            return tm.TABLA_VACIA;
        }
        for ( int i = 0; i < tablaFacturacion.getRowCount(); i++ ) {

//            System.out.println("P.Costo : " + tx_PcostoUltimo);
            
            if ( (int) tablaFacturacion.getValueAt(i, 7) <= 0 ) {
                return "COMPLETE LOS PEDIDOS (Cantidad pedida diferente de 0)";
            }
        }
        return tm.VALIDO;
    }

    private String validarProf() {
        if ( tx_direccion.getText().equals("") || tx_ruc.getText().equals("") ) {
            return tm.CAMPOS_INCOMPLETOS;
        }
        if ( tablaFacturacion.getRowCount() <= 0 ) {
            return tm.TABLA_VACIA;
        }
        for ( int i = 0; i < tablaFacturacion.getRowCount(); i++ ) {
            if ( (int) tablaFacturacion.getValueAt(i, 7) <= 0 ) {
                return "COMPLETE LOS PEDIDOS";
            }
            String disponibilidad = String.valueOf(tablaFacturacion.getValueAt(i, 15));
            if ( "".equals(disponibilidad) ) {
                return "AGREGUE DISPONIBILIDAD";
            }
        }
        return tm.VALIDO;
    }

    private String validarSK() {
        if ( tx_direccion.getText().equals("") || tx_ruc.getText().equals("") || cb_vend.getSelectedItem() == null ) {
            return tm.CAMPOS_INCOMPLETOS;
        }
        if ( tablaFacturacion.getRowCount() <= 0 ) {
            return tm.TABLA_VACIA;
        }
        for ( int i = 0; i < tablaFacturacion.getRowCount(); i++ ) {
            if ( (int) tablaFacturacion.getValueAt(i, 7) <= 0 ) {
                return "COMPLETE LOS PEDIDOS";
            }
        }
        return tm.VALIDO;
    }

    // Setear los Detalles para los documentos
    private ArrayList<Detallees> SetDet(Object c, ArrayList<DetalleFactura> dt) {
//        System.out.println("setear detalle....");
//        System.out.println(">> SetDet(IU_Facturacion.java)...");
        ArrayList<Detallees> detmod = new ArrayList<>();
        Cabeces cab;
        Cabecsalvar cabs;
        Clientes cliente = null;
        
        for ( int i = 0; i < dt.size(); i++ ) {
            Detallees detalle = dt.get(i).getD(); // Aquí obtiene el Detallees de BD(preciolista en BD de Repuesto a preciolista de Detallees)
            
            if ( c instanceof Cabeces ) {
                System.out.println("es Cabeces");
                cab = (Cabeces) c;
                detalle.setCabeces(cab);
                cliente = cab.getClientes();
                detalle.setOperaciones(sis.getOperaciones()); // Salida por Venta
                
            } else if ( c instanceof Cabecsalvar ) {
                System.out.println("es Cabecsalvar");
                cabs = ((Cabecsalvar) c);
                detalle.setCabecsalvar(cabs);
                cliente = cabs.getClientes();
                detalle.setOperaciones(sis.getOperaciones()); // Salidas Varias
            }
            detalle.setAlmacen(1);//por mientras
            detalle.setCostopromedio(detalle.getRepuestos().getCostopromedio());
            detalle.setUltimocosto(detalle.getRepuestos().getPcostoultimo());
            detalle.setCodsecundario(detalle.getRepuestos().getCodigoseg());
            detalle.setNroalternativo(detalle.getRepuestos().getNroalternativo());
            detalle.setPreciolista(dt.get(i).getD().getPreciolista());
            detalle.setClientes(cliente);
            detalle.setFecha(dt_fecha.getDate());
            
//            if ( detalle.getCabeces() != null ) {
//                String tipotra = detalle.getCabeces().getId().getTipotra();
//                String tipodoc = detalle.getCabeces().getId().getTipodoc();
//                String nroserie= detalle.getCabeces().getId().getNrorserie();
//                String nrodoc = detalle.getCabeces().getId().getNrodocumento();
//
//                System.out.println(">> tipoTra : " + tipotra);
//                System.out.println(">> tipoDoc : " + tipodoc);
//                System.out.println(">> N° Serie: " + nroserie);
//                System.out.println(">> N° Doc: " + nrodoc);
//                System.out.println(">>> N° Guia:" + detalle.getNroguia());
//            }
            
            if ( provieneDe == GENERA_GR ) { // Generar GR por consignación
                int nroGuia = Integer.parseInt(tx_nroGuia.getText());
                System.out.println("nroGuia(seteando en Detalle):" + nroGuia);
                detalle.setNroguia(nroGuia);
                detalle.setFechaemigr(dt_fecha.getDate());
            }

            detmod.add(detalle);
        }
        return detmod;
    }

    private ArrayList<Detalleproformas> SetDetProf(Cabecproformas cpm, ArrayList<DetalleFactura> dt) {
        ArrayList<Detalleproformas> detprof = new ArrayList<>();
        
        for ( int i = 0; i < dt.size(); i++ ) {
            Detallees detalle = dt.get(i).getD();
                    
            Detalleproformas dpm = new Detalleproformas();
            dpm.setCabecproformas(cpm);
            dpm.setCantidad(detalle.getCantpedida());
            dpm.setRepuestos(detalle.getRepuestos());
            dpm.setCodrepuesto(detalle.getRepuestos().getCodrepuesto());
//            double precioSoles = dt.get(i).getPrecioRepuesto();
//            double precioDolares = dt.get(i).getPrecioRepuestoDolar();
            double precioLista = dt.get(i).getPrecioRepuesto();
            
            if ( SOL_BD.equals(cpm.getMoneda()) ) {
                precioLista = dt.get(i).getPrecioRepuesto() / Double.parseDouble(valorVenta);
            }
            dpm.setPreciolista(precioLista);
            dpm.setDescuento1(detalle.getDescuento1());
            dpm.setDescuento2(detalle.getDescuento2());
            dpm.setDescuento3(detalle.getDescuento3());
            dpm.setDescuento4(detalle.getDescuento4());
            dpm.setDisponibilidad(detalle.getDisponibilidad());
            detprof.add(dpm);
        }
        return detprof;
    }

    // Guardar e Imprimir los documentos
    private void Imprimir() throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        if ( provieneDeProf == GENERA_GR ) { // Generar GR por consignación (con factura ficticia)
            ImprimirFactura();
            
        } else {
//            System.out.println("cb_doc(.getSelectedIndex):" + cb_doc.getSelectedIndex());
            switch (cb_doc.getSelectedIndex()) {
                case 0: // Boleta
                case 1: // Factura
                    ImprimirFactura();
                    break;
                case 2:
                    System.out.println("Imprime formato Boleta");
                    ImprimirProforma();
                    break;
                case 3:
                    ImprimirSK();
                    break;
            }
        }
    }

    private void ImprimirProforma() throws FileNotFoundException, BiffException {
        Cabecproformas cabecP = new Cabecproformas();
        String cliente = String.valueOf(cb_cliente.getSelectedItem());
        Clientes c = new ClienteDAO().getxNombre(cliente);
        cabecP.setClientes(c);
        cabecP.setFecha(dt_fecha.getDate());
        
        String moneda = String.valueOf(cb_moneda.getSelectedIndex() == 0 ? SOLES : DOLARES);
        cabecP.setMoneda(moneda);
        cabecP.setTotal(Double.parseDouble(lb_Total.getText()));
        cabecP.setEstado(ESTADO_PROFORMA_PENDIENTE);
        cabecP.setCodigocabproforma(String.valueOf(UltimoNumero));
        sis.setUltimonumero(UltimoNumero);
        
        Cabeces cabeces = new Cabeces();
        CabecesId cabecesId = new CabecesId();
        cabecesId.setTipotra(VENTA);
        cabecesId.setTipodoc(sis.getTipodoc());
        cabecesId.setNrorserie(String.valueOf(NumeroSerie));
        cabecesId.setNrodocumento(String.valueOf(UltimoNumero));
        
        cabeces.setId(cabecesId);
        cabecP.setCabeces(cabeces);
        
        // POR AJUSTE DE REQUERIMIENTOS DE ORBIT, se toma el campo "Transportista" como "nroOrden". (para Proforma es O/C (Orden de Compra))
        cabecP.setTransportista(tx_orden.getText());
        
        // Nuevos campos ingresados para ORBIT PARTS en Cabecproforma
        cabecP.setPlaca(tx_placa.getText());
        cabecP.setMarca(tx_marca.getText());
        cabecP.setModelo(tx_modelo.getText());
        cabecP.setSerie(tx_serie.getText());
        cabecP.setMotor(tx_motor.getText());
        String vend = String.valueOf(cb_vend.getSelectedItem());
        Vendedores v = new VendedorDAO().Obtener_Vendedor_PorNombre(vend);
        cabecP.setVendedores(v);
        cabecP.setPunterogrupo(0);
        
        ArrayList<Detalleproformas> detProf = SetDetProf(cabecP, tablaFacturacion.getDt());
        
        if ( sf.GuardarProforma(cabecP, detProf) ) {
            sf.actualizarSis(sis);
            generarExcelProforma();
            tm.Mensaje(tm.EXITO_AGREGAR);
            dispose();
//          Imprimir_Proforma(cabecP, detProf);
            
        } else {
            tm.Error(tm.ERROR_GUARDAR);
        }
    }
    
    private void generarExcelProforma() throws FileNotFoundException, BiffException {
        servicio_Excel = new Servicio_Excel(tb_factura, this);
        DatosProforma proforma = new DatosProforma();
        proforma = llenarDatosProforma(proforma);
        servicio_Excel.Exportar_Excel_CabecDet_Proforma(proforma);
    }
    
    private DatosProforma llenarDatosProforma(DatosProforma proforma) {
        proforma.setNroSerie(tx_numSerie.getText());
        proforma.setNroDocumento(tx_doc.getText());
        proforma.setRutaProgramas(control.getRutaprogramas());
        
        proforma.setEmpresa(control.getNombrempresa());
        proforma.setRuc(control.getRuc());
        proforma.setDireccion1(control.getNombrealmacen());
        proforma.setDireccion2(control.getNombrealmacen2());
        proforma.setTelefono(control.getTelefonos());
        
        Clientes cliente = buscarCliente();
        proforma.setCliente(cliente.getNombre());
        proforma.setAtencion(cliente.getContacto());
        proforma.setTelefonoFax(cliente.getTelefonocel());
        proforma.setReferencia(tx_referencia.getText());
        
        Vendedores vendedor = buscarVendedor();
        proforma.setVendedor(vendedor.getNombre());
        proforma.setEmail(control.getCorreo());
        
        proforma.setMoneda(getMonedaEnLetras(lb_tipoCambio.getText()));
        proforma.setMarca(tx_marca.getText());
        
        proforma.setPlaca(tx_placa.getText());
        proforma.setModelo(tx_modelo.getText());
        
        proforma.setSerie(tx_serie.getText());
        proforma.setMotor(tx_motor.getText());
        
        proforma.setSubtotal(lb_ValorVenta.getText());
        proforma.setIgv(lb_IGV.getText());
        proforma.setTotal(lb_Total.getText());
//        mostrarProforma(proforma);
        return proforma;
    }
    
    private String getMonedaEnLetras(String tipoCambio) {
        String monedaEnLetras = tipoCambio;
        String[] mon = monedaEnLetras.split(":");
        monedaEnLetras = mon[1].toUpperCase().trim();
        return monedaEnLetras;
    }
    
//    private void mostrarProforma(DatosProforma prof) {
//        System.out.println("MOSTRAR PROFORMA::");
//        
//        System.out.println("N° Serie(Proforma):" + prof.getNroSerie());
//        System.out.println("N° Documento(Proforma):" + prof.getNroDocumento());
//        
//        System.out.println("Empresa:" + prof.getEmpresa());
//        System.out.println("Ruc:" + prof.getRuc());
//        System.out.println("Dir1:" + prof.getDireccion1());
//        System.out.println("Dir2:" + prof.getDireccion2());
//        System.out.println("Tel:" + prof.getTelefono());
//        
//        System.out.println("Cliente:" + prof.getCliente());
//        System.out.println("Atencion:" + prof.getAtencion());
//        System.out.println("TelefonoFax:" + prof.getTelefonoFax());
//        System.out.println("Ref:" + prof.getReferencia());
//        System.out.println("Vend:" + prof.getVendedor());
//        System.out.println("Email:" + prof.getEmail());
//        
//        System.out.println("Mon:" + prof.getMoneda());
//        System.out.println("Marca:" + prof.getMarca());
//        System.out.println("Placa:" + prof.getPlaca());
//        System.out.println("Modelo:" + prof.getModelo());
//        System.out.println("Serie:" + prof.getSerie());
//        System.out.println("Motor:" + prof.getMotor());
//        System.out.println("SubTotal:" + prof.getSubtotal());
//        System.out.println("Igv:" + prof.getIgv());
//        System.out.println("Total:" + prof.getTotal());
//    }
    
//    private static String formatearGuiaRemision(String numSerie, String numCorrelativo) {
//        return formatearCeros(numSerie, 3) + "-" + formatearCeros(numCorrelativo, 8);
//    }
    
    private void Imprimir_Proforma(Cabecproformas cabProf, ArrayList<Detalleproformas> detProforma) {
        Servicio_Impresion impresion = new Servicio_Impresion(MaxLineas);
        impresion.imprimeProforma(cabProf, detProforma, dt_fecha.getDate());
    }

    private void ImprimirFactura() throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        boolean credito = false;
        Date fecha_venc = null;
        
        if ( dt_venc.isVisible() ) {
            fecha_venc = dt_venc.getDate();
            credito = true;
        }

        String tipoTra = VENTA;
        String tipoDoc = sis.getTipodoc();
        
        int nroserie = Integer.parseInt(tx_numSerie.getText());
        int nrodocumento = Integer.parseInt(tx_doc.getText());
        
        if ( provieneDe != GENERA_GR ) { // Generar GR por consignación (con factura ficticia)
            // ACTUALIZAR NRODOCUMENTO en tabla Sistema(BD), tras ingreso manual por USUARIO en el formulario de Facturación.
            // nrodocumento aparece con el N° para Actual Documento (el de la BD + 1) el correlativo a la BD.
            
            int numDocActualEnBD = sis.getUltimonumero();
            /* Escenario: Ante facturación pendiente, se reservan N° Documentos de Factura, por lo que Usuario ingresa el N° Documento de
               forma manual en el formulario de Facturación (a pesar que el Sistema retorna el N° Documento generado por defecto de forma
               correlativa al último número de la tabla Sistema). */
            
//            System.out.println("nrodocumento(de Formulario SIAR):" + nrodocumento);
//            System.out.println("numDocActualEnBD:" + numDocActualEnBD);
        
            /* Si el N° Documento ingresado por el usuario es válido(Mayor a N° Documento de BD), se actualiza el N° Documento en tabla
               Sistema con éste número ingresado por usuario. Sólo si es mayor al actual en BD se realiza esta actualización. */
            if( nrodocumento >= numDocActualEnBD + 1) { // CORRELATIVO
                numDocActualEnBD = nrodocumento;   
            }
            sis.setUltimonumero(numDocActualEnBD); // NUEVO N° DE DOCUMENTO(ÚLTIMO NÚMERO) A ACTUALIZAR EN SISTEMA
//            System.out.println("numDocActualEnBD(que se va Actualizar en BD):" + numDocActualEnBD);
        }

        Clientes c = buscarCliente();
        c.setDireccion(tx_direccion.getText());
        c.setRuc(tx_ruc.getText());
        
        String nombreVendedor = String.valueOf(cb_vend.getSelectedItem());
        Vendedores vend = new Servicio_Vendedor().obtener_Vendedor_Nombre(nombreVendedor);
        Date fecha = dt_fecha.getDate();

        int TipoOp = cb_operacion.getSelectedIndex() + 1;

        /*
         * Tipo Operacion :  1 => Contado
         *                   2 => Credito
         *                   3 => Letras
         * Forma Pago :      E => Efectivo
         * Moneda  :         1 => Soles
         *                   2 => Dolares
         */
        Double impBruto = Double.parseDouble(lb_TotalBruto.getText());
        Double valorVenta = Double.parseDouble(lb_ValorVenta.getText());
        Double total = Double.parseDouble(lb_Total.getText());
        Double costoTotal = Double.parseDouble(lb_CostoTotal.getText());
        String moneda = String.valueOf(cb_moneda.getSelectedIndex() == 0 ? SOLES : DOLARES);
        Double costototal = costoTotal;
        
        Cabeces cab = new Cabeces();
        CabecesId cabID = new CabecesId();
//        System.out.println("En Imprimir...");
//        System.out.println(">> tipoTra = " + tipoTra);
//        System.out.println(">> tipoDoc = " + tipoDoc);
//        System.out.println(">> N°Serie = " + nroserie);
//        System.out.println(">> n°Docum = " + nrodocumento);
        cabID.setTipotra(tipoTra);
        cabID.setTipodoc(tipoDoc);
        cabID.setNrorserie(String.valueOf(nroserie));
        cabID.setNrodocumento(String.valueOf(nrodocumento));

        cab.setId(cabID);
        cab.setClientes(c);
        cab.setVendedores(vend);
//        System.out.println("t:" + t);
//        System.out.println("t-fecha:" + t.getFecha());
//        System.out.println("t-venta:" + t.getValorventa());
//        System.out.println("t-compra:" + t.getValorcompra());
        cab.setTipocambio(t);
        cab.setImportebruto(impBruto);

        ////  nuevo
        cab.setTotal(total);
        cab.setIgv(Double.parseDouble(lb_IGV.getText()));
        cab.setValorventa(valorVenta);
//        cab.setCostototal(tablaFacturacion.getCostoTotal());
        cab.setCostototal(Double.parseDouble(lb_CostoTotal.getText()));        
        cab.setTipoperacion(String.valueOf(TipoOp));
        cab.setTipocambio_1(t.getValorventa());
        cab.setUsuarios(usuario);
        
        /// Nuevos campos agregados para Siar Orbit
        cab.setMarca(tx_marca.getText());
        cab.setPlaca(tx_placa.getText());
        cab.setModelo(tx_modelo.getText());
        cab.setOrdenTransportista(tx_orden.getText());
        cab.setSiniestro(tx_siniestro.getText());
        
        if ( cb_sucursales.isVisible() && cb_sucursales.getSelectedIndex() > 0 ) {
            cab.setSucursales((Sucursales) cb_sucursales.getSelectedItem());
        }
        cab.setMoneda(moneda);
        if ( credito ) {
            cab.setFechavencimiento(fecha_venc);
        }
        
        Pagos pago = null;
        if ( cb_doc.getSelectedIndex() == 0 || cb_doc.getSelectedIndex() == 1 ) { // Tipo doc (Factura o Boleta)
            if ( cb_operacion.getSelectedIndex() == 0 ) { // Al contado
                pago = new Pagos();
                pago.setCabeces(cab);
//                System.out.println("fecha(factura normal):" + fecha);
                pago.setFecha(fecha);
//                pago.setFecha(new Date());
                pago.setImporte(total);
                pago.setForma(EFECTIVO);   
            }
            cab.setEstado("");
        }
        
        // Preparando el Detallees de la Factura
        ArrayList<Detallees> detalles = SetDet(cab, tablaFacturacion.getDt());

        if ( cb_operacion.getSelectedIndex() == 2 ) { // Tipo operacion letras
//            System.out.println(">> LETRAS...");
            bt_confirmar.setEnabled(false);
            bt_cancel.setEnabled(false);
            cab.setTipoperacion(LETRAS);
            cab.setEstado("");
            final IU_FLetras let = new IU_FLetras(cab, detalles);
            let.setServicioDoc(sf);
            let.setSisFac(sis);
            
            if ( prof ) {
                let.setCab_act(cabP);
                
            } else if ( SK ) {
                let.setCab_act(cabSalvar);
            }
            //Validacion en caso de q se cierre la ventana de letras

            let.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
//                    System.out.println("windowsClosed(2)");
                    if ( let.isGuardado() ) {
                        dispose();
                        
                    } else {
                        sis.setUltimonumero(sis.getUltimonumero() - 1);
                        bt_confirmar.setEnabled(true);
                        bt_cancel.setEnabled(true);
                        bt_cancel.doClick();
                    }
                }
            });

        } else { // Al contado o crédito
            Date fechaEmision = dt_fecha.getDate();
            if ( SK ) { // Salida Varia
                System.out.println(">> SK(en PRINT FACTURA)...");
                cab.setId(cabID);
                cab.setBancos(null);
                cab.setCostototal(total);
                cabdao = new CabecesDAO();
                // Se crea la cabecera con los datos de la SK

                List<Detallees> detsalv = detallesdao.listarporSK(cabSalvar);
                if ( sf.ActualizarCabecSalvar(cabSalvar, cab, detsalv) ) {
                    for ( Detallees detallees : detsalv ) {
                        detallees.setCabeces(cab);
                        detallesdao.Modificar_Objeto(detallees);
                    }
                    
                    Servicio_Pagos sp= new Servicio_Pagos();
                    Servicio_Banco sb= new Servicio_Banco(null);

                    Pagos pag = new Pagos();
                    pag.setCabecsalvar(cabSalvar);
                    pag.setFecha(new Date());
                    pag.setImporte(cabSalvar.getTotal());
                    pag.setForma("E");
                    pag.setUsuarios(usuario);
                    pag.setBancos(sb.getBanco_Nombre("CANJE POR FACTURA"));
                    pag.setNrocheque("CxF");
                    sp.GuardarPagos(pag);

                    sf.actualizarSis(sis);
                    tm.Mensaje(tm.EXITO_AGREGAR);
                    Imprimir_Documento(cab, detalles, cb_doc.getSelectedIndex(), fechaEmision);
                    dispose();
                    
                } else {
                    tm.Error("ERROR, FACTURA CON DOCUMENTO INGRESADO YA EXISTE");
                }

            } else { // Facturar una o más proformas
                String dpto = String.valueOf(cbDepartamentos.getSelectedItem());
                String prov = String.valueOf(cbProvincias.getSelectedItem());
                String dist = String.valueOf(cbDistritos.getSelectedItem());
                Ubigeo ubigeo = servicioUbigeo.buscarUbigeo(dpto, prov, dist);
//                System.out.println("ubigeo seleccionado(:D):" + ubigeo.getIdubigeo());
                cab.setUbigeo(ubigeo);
                    
                boolean esFactura = false;
                String vendedor = String.valueOf(cb_vend.getSelectedItem());
                String condicion = String.valueOf(cb_operacion.getSelectedItem());
                String tipoCambio = lb_tipoCambio.getText();
                
//                System.out.println(">> FACTURAS...");
//                System.out.println(">>>> provieneDeProf:" + provieneDeProf);
                
                String telfEmpresa = control.getTelefonos();
                
                // (MODALIDAD #1: FACTURA generada a partir de 1 o más proformas)
                if ( provieneDeProf == PROFORMA ) {
//                if ( prof ) { // 1° Versión M&D
//                    asignarCabeceraFactura(cab); // 1° Versión M&D
                    cab.setCabecproformas(this.cabProforma.get(0));
                    
                    /* Actualizando datos en CabecProformas */
                    List<Cabecproformas> listaCP = new ArrayList<Cabecproformas>();
                    for ( int i = 0; i < listaCabP.size(); i++ ) {
                        Cabecproformas cp = (Cabecproformas) listaCabP.get(i);
                        actualizarCabeceraProforma(cp);
                        listaCP.add(cp);
                    }
                    
                    if ( sf.guardar_actualizar_FacBol_Proforma(cab, detalles, pago, listaCP) ) {
                        sf.actualizarSis(sis);
                        tm.Mensaje(tm.EXITO_AGREGAR);
                        
                        // EXPORTAR Factura con Jasper(Sólo M&D)
//                        Imprimir_Documento(cab, detalles, cb_doc.getSelectedIndex(), fechaEmision);
                        
                        // EXPORTAR Factura A EXCEL, luego IMPRIMIR factura desde el EXCEL DE FACTURA (Impresión por Excel para Printers LX-300, x prob. Jasper cambia letras)
                        esFactura = true;
                        String nombreExcelFactura = new Servicio_Excel(tb_factura, this).generarExcelDocumento(cab, detalles, tx_serie.getText(), tx_orden.getText(), 
                                                                                                               tx_nroGuia.getText(), fechaEmision, tx_siniestro.getText(),
                                                                                                               tx_marca.getText(),tx_placa.getText(), tx_modelo.getText(), 
                                                                                                               tx_orden.getText(), esFactura, tx_numSerie.getText(),
                                                                                                               tx_doc.getText(), vendedor, condicion, tipoCambio, 
                                                                                                               tablaFacturacion, usuario);
                        Control control = new ControlDAO().Obtener_Objeto(1);
                        String rutaBDFacturas = control.getRutaprogramas();
                        String rutaExcelFacturas = rutaBDFacturas.replace("/", "\\");
                        String nombreExcelFact = rutaExcelFacturas + "\\" + nombreExcelFactura;
                        new ImpresionExcel().imprimirDesdeExcel(nombreExcelFact);
                        
                        if ( esProformaAFacturar ) {
                            TipoMensaje tm = new TipoMensaje();
                            
                            if ( tm.Confirmacion("¿Desea Exportar a Excel?") == tm.SI ) {
                                servicio_Excel = new Servicio_Excel(tblProformas, this);
                                servicio_Excel.Exportar_Excel_PF();
                            }
                        }
                        
                        ImprimirGuiaRemision(cab, detalles, usuario);
                        dispose();
                        
                    } else {
//                        System.out.println("2-2");
                        tm.Error("ERROR, FACTURA YA EXISTE");
                    }
                    
                } else { // Facturas
                    boolean crearFactura = false, modificarFactura = false, pendienteCrearGR = false;
                    
                    if ( provieneDe == FACTURA_BOLETA ) {
//                        System.out.println("1.FACTURA_BOLETA");
                        // (MODALIDAD #2: FACTURA O BOLETA generada directamente, factura no proveniente de proforma, creada sin proforma previa)
                        crearFactura = sf.guardarFactura(cab, detalles, pago);
                        
                    } else if ( provieneDe == FACT_XGR ) {
//                        System.out.println("2.FACT_XGR");
                        // (MODALIDAD #3: FACTURA generada a partir de una Guía de Remisión, por consignación)
                        //  No modificar STOCK, pues éste ya se modificó al GENERAR GUÍA DE REMISIÓN
                        String nroGuia = String.valueOf(cabecesGuia.getNroguia());
//                        System.out.println("nroGuia:" + nroGuia);
                        cab.setNroguia(Integer.parseInt(nroGuia));
                        
                        modificarFactura = sf.actualizarFactura(cab, detalles, pago);
//                        System.out.println("modificarFactura:" + modificarFactura);
                        
                        if ( modificarFactura ) {
                            boolean eliminados = sf.eliminarFacturaFicticia(this.cabecesGuia, this.lstDetGR);
//                            System.out.println("eliminados:" + eliminados);
                        }
                    } else if ( provieneDe == GENERA_GR ) {
//                        System.out.println("3.GENERA_GR");
//                        System.out.println("proviene de GENERAR GR X CONSIGNACION (ULTIMO XD)");
                        pendienteCrearGR = true;
//                        System.out.println(">> cab : " + cab);
//                        System.out.println(">> N° guia : " + tx_nroGuia.getText());
                        cab.setNroguia(Integer.parseInt(tx_nroGuia.getText()));
                        cab.setFechaemigr(dt_fecha.getDate());
                    }
                    
                    if ( crearFactura || modificarFactura ) { // crearFactura (Factura Normal), modificarFactura (Factura a partir de GR x consignación)
                        
//                        System.out.println("Si se creó o modificó factura....");
//                        System.out.println("tipoDoc:" + sis.getDescripcion());
//                        System.out.println("Nuevo último N° Documento:" + sis.getUltimonumero());
                        
                        sf.actualizarSis(sis); // Actualizar último número de documento en tabla "Sistema" (BD)
//                        System.out.println("actualiza Sistema");
                        tm.Mensaje(tm.EXITO_AGREGAR);
                        
                        /// Imprimir con excel (2° Versión)
                        Control control = new ControlDAO().Obtener_Objeto(1);
                        String rutaBDFacturas = control.getRutaprogramas();
                        String rutaExcel = rutaBDFacturas.replace("/", "\\");
                        String nombreExcelBoleta = "", nombreExcelFactura = "";
                        
                        if ( "Boleta".equals(cb_doc.getSelectedItem()) ) { // No tiene GR que imprimir
                            esFactura = false; // Boleta
                            
                            // IMPRIMIR CON JASPER (1° Versión de M&D)
//                            Imprimir_Documento(cab, detalles, cb_doc.getSelectedIndex(), fechaEmision);
                            
                            // EXPORTAR A EXCEL, luego IMPRIMIR EXCEL DE BOLETA
                            nombreExcelBoleta = new Servicio_Excel(tb_factura, this).generarExcelDocumento(cab, detalles, tx_serie.getText(), tx_orden.getText(),
                                                                                                           null, fechaEmision, null, null, null, null, 
                                                                                                           tx_orden.getText(), esFactura, tx_numSerie.getText(),
                                                                                                           tx_doc.getText(), vendedor, condicion, tipoCambio, 
                                                                                                           tablaFacturacion, usuario);
                            nombreExcelBoleta = rutaExcel + "\\" + nombreExcelBoleta;
                            new ImpresionExcel().imprimirDesdeExcel(nombreExcelBoleta);
                            dispose();
                            
                        } else if ( "Factura".equals(cb_doc.getSelectedItem()) ) { // Para Factura Normal o Factura a partir de GR
                            esFactura = true;
                            // IMPRIMIR CON JASPER (1° Versión de M&D)
//                            Imprimir_Documento(cab, detalles, cb_doc.getSelectedIndex(), fechaEmision);
                            
                            // EXPORTAR A EXCEL, luego IMPRIMIR EXCEL DE FACTURA
                            nombreExcelFactura = new Servicio_Excel(tb_factura, this).generarExcelDocumento(cab, detalles, tx_serie.getText(), tx_orden.getText(),
                                                                                                            tx_nroGuia.getText(), fechaEmision, tx_siniestro.getText(),
                                                                                                            tx_marca.getText(),tx_placa.getText(), tx_modelo.getText(),
                                                                                                            tx_orden.getText(), esFactura, tx_numSerie.getText(),
                                                                                                            tx_doc.getText(), vendedor, condicion, tipoCambio, 
                                                                                                            tablaFacturacion, usuario);
                            nombreExcelFactura = rutaExcel + "\\" + nombreExcelFactura;
                            new ImpresionExcel().imprimirDesdeExcel(nombreExcelFactura);
                            
                            if ( provieneDeProf != FACT_XGR ) { // Para Factura Normal
                                System.out.println("Imprimir GR para Factura Normal");
//                                System.out.println("Preparando IMPRESION de GR");
                                ImprimirGuiaRemision(cab, detalles, usuario);
                            }
                            dispose();
                        }
                        
                    } else if ( pendienteCrearGR ) { // Para Generar GR por consignación
//                        System.out.println("Generar GR(de IU_Facturacion.java) -> IU_ImprimirGR.java");
                        dispose();
                        ImprimirGuiaRemision(cab, detalles, usuario);
                        
                    } else {
//                        System.out.println("Error documento existente...");
                        tm.Error("ERROR, DOCUMENTO YA EXISTE");
                    }
                }
            }
        }
    }
    
    private Clientes buscarCliente() {
        String cliente = String.valueOf(cb_cliente.getSelectedItem());
//        String[] datosCliente = clientePlaza.split("-");
        Clientes c = new ClienteDAO().getxNombre(cliente);
        return c;
    }
    
    private Vendedores buscarVendedor() {
        String vendedor = String.valueOf(cb_vend.getSelectedItem());
        Vendedores v = new VendedorDAO().Obtener_Vendedor_PorNombre(vendedor);
        return v;
    }
    
    private void actualizarCabeceraProforma(Cabecproformas cp) {
        String nomPlaza = String.valueOf(cb_cliente.getSelectedItem());
        String nombrePlaza[] = nomPlaza.split("-");
        Clientes c = new ClienteDAO().getxNombre(nombrePlaza[0]);
        cp.setClientefact(c.getNombre());
        
        Double impBruto = Double.parseDouble(lb_TotalBruto.getText());
        if ( cp.getImportebrutofact() != null ) {
            impBruto += cp.getImportebrutofact();
        }
        impBruto = new Util().Redondear2Decimales(impBruto);
        cp.setImportebrutofact(impBruto);
        
        Double valorVenta = Double.parseDouble(lb_ValorVenta.getText());
        if ( cp.getValorventafact() != null ) {
            valorVenta += cp.getValorventafact();
        }
        cp.setValorventafact(valorVenta);
        
        Double importeIGV = Double.parseDouble(lb_IGV.getText());
        if ( cp.getIgvfact() != null ) {
            importeIGV += cp.getIgvfact();
        }
        cp.setIgvfact(importeIGV);
        
        Double totalFact = Double.parseDouble(lb_Total.getText());
        if ( cp.getTotalfact() != null ) {
            totalFact += cp.getTotalfact();
        }
        cp.setTotalfact(totalFact);

        cp.setFechafact(dt_fecha.getDate()); 
        /* Fecha de ultima facturacion que se realizó de la proforma (no significa que se terminó de facturar proforma,
           si no es la ultima factura k se realizó) */
        
        totalFact = new Util().Redondear2Decimales(totalFact);
        double tot = cp.getTotal();
        
        if ( tot == totalFact ) {
            cp.setEstado("FACTURADO");
        } else {
            cp.setEstado("PENDIENTE");
        }
//        System.out.println("punteroGrupo:::" + this.punteroGrupo);
        cp.setPunterogrupo(this.punteroGrupo);
    }
    
    private void asignarCabeceraFactura(Cabeces cab) {
        for ( Cabecproformas cabProf : listaCabP ) {
            cabProf.setCabeces(cab); // Asignar a cada proforma seleccionada la cabecera de la factura que se generará
        }
    }

    private void ImprimirSK() throws FileNotFoundException, BiffException, IOException, WriteException, ParseException, FontFormatException {
        System.out.println("ImprimirSK...");
        Double valorVenta = Double.parseDouble(lb_ValorVenta.getText());
        Double total = Double.parseDouble(lb_Total.getText());
        Double IGV = Double.parseDouble(lb_IGV.getText());
        Cabecsalvar cabsal = new Cabecsalvar();
        cabsal.setCodigosalida(String.valueOf(UltimoNumero));
        sis.setUltimonumero(UltimoNumero);
        cabsal.setClientes(csel);
        cabsal.setFecha(dt_fecha.getDate());
        
        String moneda = String.valueOf(cb_moneda.getSelectedIndex() == 0 ? SOLES : DOLARES);
        cabsal.setMoneda(moneda);
        
//        cabsal.setVendedores((Vendedores) cb_vend.getSelectedItem());
        Vendedores vendedor = buscarVendedor();
        cabsal.setVendedores(vendedor);
        
        cabsal.setValorventa(valorVenta);
        cabsal.setIgv(IGV);
        cabsal.setTotal(total);
        cabsal.setUsuarios(usuario);
        cabsal.setObservacion("");
        cabsal.setTipotrask(VENTA);
        
        Sistema sistema = new Servicio_Sistema().obtener_por_nombre(SALIDAS_VARIAS);
        cabsal.setTipodocsk(sistema.getTipodoc());
        String nroSerie = String.valueOf(sistema.getSerie() + 1);
        cabsal.setNroseriesk(nroSerie);
        
        String nroDoc = String.valueOf(sistema.getUltimonumero() + 1);
        
//        cabsal.setTipotra(VENTA);
//        cabsal.setTipodoc(sistema.getTipodoc());
//        cabsal.setNrorserie(nroSerie);
//        cabsal.setNrodocumento(nroDoc);
        
        CabecesId cabId = new CabecesId();
        cabId.setTipotra(VENTA);
        cabId.setTipodoc(sistema.getTipodoc());
        cabId.setNrorserie(nroSerie);        
        cabId.setNrodocumento(nroDoc);
        
        Cabeces cabeces = new Cabeces();
        cabeces.setId(cabId);
        cabsal.setCabeces(cabeces);
        
//        if ( cb_sucursales.isVisible() && cb_sucursales.getSelectedIndex() > 0 ) {
//            System.out.println("sucursales 1");
//            cabsal.setSucursales((Sucursales) cb_sucursales.getSelectedItem());
//            
//        } else {
//            System.out.println("sucursales 2");
//            cabsal.setSucursales(null);
//        }
        
        // Preparando el Detallees de la Factura
        ArrayList<Detallees> detalles = SetDet(cabsal, tablaFacturacion.getDt());
        
        Pagos pago = null;
//        System.out.println("cb_doc(item):" + cb_doc.getSelectedItem());
//        System.out.println("cb_doc(index):" + cb_doc.getSelectedIndex());
//        
//        System.out.println("cb_operacion(item):" + cb_operacion.getSelectedItem());
//        System.out.println("cb_operacion(index):" + cb_operacion.getSelectedIndex());
        
        if ( cb_doc.getSelectedIndex() == 3 ) { // SK
//            System.out.println("opcion 0 o 1 de cb_doc");
            if ( cb_operacion.getSelectedIndex() == 0 ) { // Al contado
                pago = new Pagos();
                pago.setCabecsalvar(cabsal);
                
                Date fecha = dt_fecha.getDate();
                pago.setFecha(fecha);
                
                pago.setImporte(total);
                pago.setForma(EFECTIVO);   
            }
//            cab.setEstado("");
        }
//        System.out.println("pago:" + pago);
        
        if ( sf.GuardarSK(cabsal, detalles, pago) ) {
            sf.actualizarSis(sis);
            tm.Mensaje(tm.EXITO_AGREGAR);
            TipoMensaje tm = new TipoMensaje();
            
            if ( tm.Confirmacion("¿Desea Imprimir el Documento?") == tm.SI ) {
                // Impresión por Excel 1° Versión del M&D
//                ArrayList lista = SetLista();
//                Control control = new ControlDAO().Obtener_Objeto(1);
//                String rutaBDSK = control.getRutaprogramas();
//                String rutaExcel = rutaBDSK.replace("/", "\\");
                
                Date fechaEmision = dt_fecha.getDate();
                String condicion = String.valueOf(cb_operacion.getSelectedItem());
                String tipoCambio = lb_tipoCambio.getText();
                String vendedorString = vendedor.getNombre();
                
                // Impresión por Excel 1° Versión del M&D
                String nombreExcelSK = new Servicio_Excel(tb_factura, this).generarExcelDocumento(cabsal, detalles, tx_serie.getText(), tx_orden.getText(),
                                                                                                  tx_nroGuia.getText(), fechaEmision, tx_siniestro.getText(),
                                                                                                  tx_marca.getText(),tx_placa.getText(), tx_modelo.getText(),
                                                                                                  tx_orden.getText(), tx_numSerie.getText(),
                                                                                                  tx_doc.getText(), vendedorString, condicion, tipoCambio, 
                                                                                                  tablaFacturacion);
                nombreExcelSK = rutaExcel + "\\" + nombreExcelSK;
                new ImpresionExcel().imprimirDesdeExcel(nombreExcelSK);
//-------------------------------                
                ArrayList lista = SetLista();
                Map<String, Object> parametros = SetParametros(cabsal);
                String ruta = new ControlDAO().Obtener_Objeto(1).getRutareportes();
                
//                Servicio_Impresion.exporta(15, lista, parametros, "plantillas/Salida_Varia.pdf");
                
                System.out.println("Termino Servicio_Impresion de SK ");
                
            }
            dispose();
        } else {
            System.out.println("No se pudo guardar SK");
            tm.Error(tm.ERROR_AGREGAR);
        }
    }

    private ArrayList SetLista() {
        ArrayList<DetalleSK> list = new ArrayList<>();
        ArrayList<DetalleFactura> lista = tablaFacturacion.getDt();
        
        for ( DetalleFactura dt : lista ) {
            Detallees det = dt.getD();
            Repuestos r = det.getRepuestos();

            String nroParte = r.getCodrepuesto();
            String descripcion = r.getDescripcion();
            String precio = String.valueOf(dt.getPrecioRepuesto());
            String cantidad = String.valueOf(det.getCantentregada());
            String total = String.valueOf(dt.getTotal());
//            System.out.println("nroParte:" + nroParte);
//            System.out.println("descripcion:" + descripcion);
//            System.out.println("precio:" + precio);
//            System.out.println("cantidad:" + cantidad);
//            System.out.println("total:" + total);

            list.add(new DetalleSK(nroParte, descripcion, precio, cantidad, total));
        }

        return list;
    }

    private Map SetParametros(Cabecsalvar cabs) {
        Map<String, Object> mapa = new HashMap<>();
        String empresa = new ControlDAO().Obtener_Objeto(1).getNombrempresa();
        mapa.put("empresa", empresa);
        mapa.put("nroSalida", cabs.getCodigosalida());
        
//        String clientePlaza = String.valueOf(cb_cliente.getSelectedItem());
//        String[] datosCliente = clientePlaza.split("-");
//        Clientes cliente = new ClienteDAO().getxNombrePlaza(datosCliente[0], datosCliente[1]);
        
        String cli = String.valueOf(cb_cliente.getSelectedItem());
//        System.out.println("cli:" + cli);
        Clientes cliente = new ClienteDAO().getxNombre(cli);
        
        mapa.put("cliente", cliente.getNombre());
        mapa.put("igv", GLOSA_DOLARES + " " + String.valueOf(cabs.getIgv()));
        mapa.put("total", GLOSA_DOLARES + " " + String.valueOf(cabs.getTotal()));
        
//        System.out.println("empresa:" + empresa);
//        System.out.println("nroSalida:" + cabs.getCodigosalida());
//        System.out.println("cliente:" + cliente.getNombre());
//        System.out.println("igv:" + String.valueOf(cabs.getIgv()));
//        System.out.println("total:" + String.valueOf(cabs.getTotal()));
        return mapa;
    }

    private void ImprimirGuiaRemision(Cabeces cab, ArrayList<Detallees> det, Usuarios usuario) {
        if ( cb_doc.getSelectedIndex() == 0 ) { //Factura Normal o Factura Ficticia(Generar GR x consignación)
//            System.out.println("guia:" + guia);
//            System.out.println("sk:" + SK);
            
            if ( guia == false && SK == false ) { // no guia por factura generada de guia de remision o salidas varias
                int opcion = tm.Confirmacion("¿DESEA GENERAR GUÍA DE REMISIÓN?");
                
                if ( opcion == tm.SI ) {
//                    System.out.println("entra 1(Print GR)");
                    
                    // Impresión por Jasper (1° Versión de M&D)
//                    IU_ImprimirGR gr = new IU_ImprimirGR(cab, det, MaxLineas, tx_nroGuia.getText(), 
//                                                         dt_fecha.getDate(), tx_orden.getText(), this); // "tx_orden" en vez de "tx_pedido"
                    
                    // Impresión por Excel (2° Versión de C&S)
                    IU_ImprimirGR gr = new IU_ImprimirGR(this, cab, det, MaxLineas, tx_nroGuia.getText(), 
                                                         dt_fecha.getDate(), control, provieneDe
                                                         , usuario);
                    gr.setLocationRelativeTo(null);
                    gr.setVisible(true);
                    
                } else {
//                    System.out.println("entra 2(Print GR)");
//                    System.out.println("cabProforma:" + cabProforma);
//                    System.out.println("cabProforma:" + cabProforma.size());
//                    System.out.println("¿es vacio? " + !cabProforma.isEmpty());
                    if ( !cabProforma.isEmpty() ) {
                        if ( "PENDIENTE".equals(cabProforma.get(0).getEstado()) ) {
                            IU_Facturacion iuf10En10 = new IU_Facturacion(this.cabProforma);
                            iuf10En10.setUsuario(this.usuario);
                            iuf10En10.setLocationRelativeTo(null);
                            iuf10En10.setVisible(true);
                        }
                    }
                }
            }
        } else {
            dispose();
        }
    }
    
    private void inicializarDocumento(ArrayList<Cabecproformas> cps) {
        Clientes cli = cps.get(0).getClientes();
        cb_cliente.setEnabled(false);
        
//        String clientePlaza = cli.getNombre() + "-" + cli.getPlaza();
        cb_cliente.setSelectedItem(cli.getNombre());
        
        tx_direccion.setText(cli.getDireccion());
        tx_ruc.setText(cli.getRuc());
        
        Vendedores v = cps.get(0).getVendedores();
        String vendedor = v.getNombre();
        cb_vend.setSelectedItem(vendedor);
    }

    // Generar Detalle de Factura o Boleta a partir de otros documentos
//    private void GenerarFac(ArrayList<Cabecproformas> cps) {
//        inicializarDocumento(cps);
//        for (int i = 0; i < cps.size(); i++) {
//            ArrayList<Detalleproformas> detalleProf = (ArrayList<Detalleproformas>) sf.getDetalleProf(cps.get(i));
//            tablaFacturacion.agregarDP(cps.get(i), detalleProf);
//            Cabecproformas cp = (Cabecproformas) cps.get(i);
//            listaCabP.add(cp);
//        }
//    }
//    
    // Generar Detalle de Factura o Boleta a partir de otros documentos (ORIGINAL GenerarFac)
//    private void GenerarFac(ArrayList<Cabecproformas> cps) {
//        inicializarDocumento(cps);
//        
//        for (int i = 0; i < cps.size(); i++) {
//            ArrayList<Detalleproformas> detalleProf = (ArrayList<Detalleproformas>) sf.getDetalleProf(cps.get(i));
//     
//            //Agrega datos de una cabeceraproforma y su listado de detalleproforma a tablaFacturacion de este formulario
//            tablaFacturacion.agregarDP(cps.get(i), detalleProf);
//            Cabecproformas cp = (Cabecproformas) cps.get(i);
//            listaCabP.add(cp);
//        }
//    }

    private void GenerarFac(Cabecweb cw) {
        cabw = cw;
        cb_cliente.removeAllItems();
        
        Clientes cliente = cw.getClientes();
        String clientePlaza = cliente.getNombre() + "-" + cliente.getPlaza();
        cb_cliente.addItem(clientePlaza);
        
        cb_cliente.setEnabled(false);
        tx_direccion.setText(cw.getClientes().getDireccion());
        tx_ruc.setText(cw.getClientes().getRuc());
        ArrayList<Pedidoweb> detalleCabec = (ArrayList<Pedidoweb>) sf.getDetalleWeb(cw);
        tablaFacturacion.agregarPW(cw, detalleCabec);
    }

    private void GenerarFac(Cabeces guia, int igv) {
//        System.out.println(">>> GenerarFac a partir de GR...");
        guiaremision = guia;
        cb_cliente.removeAllItems();
        
        Clientes cliente = guia.getClientes();
        cb_cliente.addItem(cliente.getNombre());
        
        cb_cliente.setEnabled(false);
        tx_direccion.setText(guia.getClientes().getDireccion());
        tx_ruc.setText(guia.getClientes().getRuc());
        
        List<Detallees> detalleGuia = (List<Detallees>) sf.getDetalleGuia(guia);
        tablaFacturacion.agregarGuia(guia, detalleGuia, igv); // Agregar detalle de Facturación a partir de GR
    }

    private void ListarSucursales() {
//        String[] datosCliente = clientePlaza.split("-");
        String cli = String.valueOf(cb_cliente.getSelectedItem());
        Clientes cliente = new ClienteDAO().getxNombre(cli);
        
        listaSucursales = sf.GetSucursales(cliente.getIdcliente());
        if ( listaSucursales.size() > 0 ) {
            cb_sucursales.setVisible(true);
            lb_sucursales.setVisible(true);
            Sucursales principal = new Sucursales();
            principal.setClientes(cliente);
            principal.setDireccion(cliente.getDireccion());
            principal.setPlaza("SEDE PRINCIPAL");
            cb_sucursales.removeAllItems();
            cb_sucursales.addItem(principal);
            
            for ( Iterator it = listaSucursales.iterator(); it.hasNext(); ) {
                Sucursales sucursal = (Sucursales) it.next();
                cb_sucursales.addItem(sucursal);
            }
        } else {
            cb_sucursales.setVisible(false);
            lb_sucursales.setVisible(false);
        }
    }

    private void ListarVendedores() {
        Iterator<Vendedores> it = sf.getVendedoresOrdenados().iterator();
        while ( it.hasNext() ) {
            Vendedores v = it.next();
            cb_vend.addItem(v.getNombre());
        }
    }

    private void ListarSoloClientes() {
        Iterator<Clientes> it = sf.getSoloClientes().iterator();
        while ( it.hasNext() ) {
            Clientes c = it.next();
            String clientePlaza = c.getNombre();
            cb_cliente.addItem(clientePlaza);
        }
    }

    private void ActivarComponentes(boolean act) {
        for ( JComponent j : comp ) {
            j.setEnabled(act);
        }
    }

    private void aplicarDct(double d1, double d2, double d3, double d4) {
        tablaFacturacion.aplicarDctos(d1, d2, d3, d4);
    }

    private void ActivarBotones(boolean act) {
        for ( JComponent j : botones ) {
            j.setEnabled(act);
        }
    }

    private void SetGlosa(int i) {
        if ( i == 0 ) { // i = index del combo cb_moneda (0 = Dolares, 1 = Soles)
            lb_glosa.setText(GLOSA_SOLES);
            lb_glosa1.setText(GLOSA_SOLES);
            lb_glosa2.setText(GLOSA_SOLES);
            lb_glosa3.setText(GLOSA_SOLES);                        
        } else {
            lb_glosa.setText(GLOSA_DOLARES);
            lb_glosa1.setText(GLOSA_DOLARES);
            lb_glosa2.setText(GLOSA_DOLARES);
            lb_glosa3.setText(GLOSA_DOLARES);            
        }
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    private void Imprimir_Documento(Cabeces c, ArrayList<Detallees> detalles, int tipo, Date fechaEmision) {
        Servicio_Impresion impresion = new Servicio_Impresion(MaxLineas);
        
        switch ( tipo ) {
            case 0:
                System.out.println("imprimir factura...");
                impresion.imprimeFactura(c, detalles, tx_serie.getText(), tx_orden.getText(), 
                                         tx_nroGuia.getText(), fechaEmision, tx_siniestro.getText(),
                                         tx_marca.getText(),tx_placa.getText(), tx_modelo.getText(), tx_orden.getText());
                break;
                
            case 1:
                impresion.imprimeBoleta(c, detalles, fechaEmision);
                break;
        }
    }

    public int getMonedaControlRepuestos() {
        return monedaControlRepuestos;
    }

    public void setMonedaControlRepuestos(int monedaControlRepuestos) {
        this.monedaControlRepuestos = monedaControlRepuestos;
    }
    
    public tabla getTablaFacturacion() {
        return tablaFacturacion;
    }

    public void setTablaFacturacion(tabla tablaFacturacion) {
        this.tablaFacturacion = tablaFacturacion;
    }
    
    public int getIgv() {
        return igv;
    }

    public void setIgv(int igv) {
        this.igv = igv;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cancel;
    public javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_confirmar;
    private javax.swing.JButton bt_demanda;
    private javax.swing.JButton bt_descuento;
    private javax.swing.JButton bt_imprimir;
    private javax.swing.JButton bt_maestro;
    private javax.swing.JButton bt_quitar;
    private javax.swing.JButton bt_regCliente;
    private javax.swing.JButton bt_regVend;
    private javax.swing.JButton btn_Ultdesc;
    public javax.swing.JComboBox cbDepartamentos;
    public javax.swing.JComboBox cbDistritos;
    public javax.swing.JComboBox cbProvincias;
    private javax.swing.JComboBox cb_cliente;
    public javax.swing.JComboBox cb_doc;
    public javax.swing.JComboBox cb_moneda;
    public javax.swing.JComboBox cb_operacion;
    private javax.swing.JComboBox cb_sucursales;
    private javax.swing.JComboBox cb_vend;
    public com.toedter.calendar.JDateChooser dt_fecha;
    private com.toedter.calendar.JDateChooser dt_venc;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbReferencia;
    private javax.swing.JLabel lbSiniestro;
    public javax.swing.JLabel lb_CostoTotal;
    public javax.swing.JLabel lb_IGV;
    public javax.swing.JLabel lb_Total;
    public javax.swing.JLabel lb_TotalBruto;
    public javax.swing.JLabel lb_ValorVenta;
    private javax.swing.JLabel lb_documento;
    public javax.swing.JLabel lb_glosa;
    public javax.swing.JLabel lb_glosa1;
    public javax.swing.JLabel lb_glosa2;
    public javax.swing.JLabel lb_glosa3;
    private javax.swing.JLabel lb_guia;
    private javax.swing.JLabel lb_marca;
    public javax.swing.JLabel lb_mensaje;
    private javax.swing.JLabel lb_modelo;
    private javax.swing.JLabel lb_motor;
    private javax.swing.JLabel lb_orden;
    private javax.swing.JLabel lb_placa;
    private javax.swing.JLabel lb_serie;
    private javax.swing.JLabel lb_sucursales;
    private javax.swing.JLabel lb_tipoCambio;
    private javax.swing.JLabel lb_venc;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblOperacion;
    public javax.swing.JPanel panelGenerarGR;
    private javax.swing.JPanel pnl_doc;
    public javax.swing.JTable tb_factura;
    public javax.swing.JTextField tx_direccion;
    public javax.swing.JTextField tx_doc;
    public javax.swing.JTextField tx_marca;
    private javax.swing.JTextField tx_modelo;
    public javax.swing.JTextField tx_motor;
    private javax.swing.JTextField tx_nroGuia;
    public javax.swing.JTextField tx_numSerie;
    public javax.swing.JTextField tx_orden;
    public javax.swing.JTextField tx_placa;
    private javax.swing.JTextField tx_referencia;
    private javax.swing.JTextField tx_ruc;
    private javax.swing.JTextField tx_serie;
    public javax.swing.JTextField tx_siniestro;
    // End of variables declaration//GEN-END:variables

    public class lb_CostoTotal {

        public lb_CostoTotal() {
        }
    }

}
