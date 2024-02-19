package Presentacion;

import Entidades.Accesos;
import Entidades.Control;
import static Entidades.Otros.Constante.GENERAR_GR;
import Entidades.Programas;
import Entidades.Roles;
import Entidades.Tipocambio;
import Mantenimiento.RolesDAO;
import Mantenimiento.UsuarioDAO;
import Presentacion.ActualizarPrecios.FREP031;
import Presentacion.Anulacion.FREP023;
import Presentacion.CanjeLetrasProtestadas.FREP0100;
import Presentacion.Comisiones.FREP034;
import Presentacion.ConsultarClientes.FREP038;
import Presentacion.CuentasxCobrarFacturas.FREP042;
import Presentacion.CuentasxCobrarLetras.FREP043;
import Presentacion.Reportes.FREP055;
import Presentacion.Estratificacion.FREP030;
import Presentacion.Facturacion.FREP019;
import java.awt.CardLayout;
import Presentacion.FREP002.*;
import Presentacion.Importaciones.FREP025;
import Presentacion.Importaciones.FREP027;
import Presentacion.Importaciones.FREP024;
import Presentacion.Importaciones.FREP026;

import Presentacion.IngresoSalidas.FREP028;
import Presentacion.Inventario.FREP052;
import Presentacion.Inventario.FREP048;
import Presentacion.Inventario.FREP051;
import Presentacion.Inventario.FREP050;
import Presentacion.Inventario.FREP049;
import Presentacion.InventarioValorizado.FREP036;
import Presentacion.LetrasVarias.FREP046;
import Presentacion.ModifAnulaSalidasV.FREP029;
import Presentacion.Notas.FREP020;
import Presentacion.Reimpresion.FREP021;
import Presentacion.Repuestos.FREP032;
import Presentacion.RepuestosSegunEstratificacion.FREP039;
import Presentacion.CanjexLetras.FREP045;
import Presentacion.CuentasxCobrarSalidas.FREP070;
import Presentacion.EmisionPlanillas.FREP033;
import Presentacion.Facturacion.IU_Facturacion;
import Presentacion.FacturacionElectronica.FREP054;
import Presentacion.ResumenGeneralCliente.FREP047;

import Presentacion.Stock_Minimo.FREP041;
import Presentacion.historialMov.FREP035;
import Presentacion.listaPrecios.FREP037;
import Servicios.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;
import Servicios.HoraSistema;
import Servicios.Servicio_Accesos;
import Servicios.Servicio_Control;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Keily Mendiolaza
 */
public class MENU001 extends javax.swing.JFrame {

    Tipocambio tipocambio;
    FREP011 camb;
    String nombreUsuario;
    Control c = new Control();
    // probando reinicio de vistas
    FREP0055 vista_marcas;
    FREP0056 vista_modelos;
    //
    String rolElegido;
    
    // Botones Pagar
    boolean btnPagar_CCF;
    boolean btnPagar_CCL;
    boolean btnPagar_CCSA;

    public MENU001(String nombre, String rol, double valorcompra, double valorventa) {
//        System.out.println("constructor de MENU001(Facturación(Proformas y Web))");
        initComponents();
        dispose();
        float escalarAlto = 0.95F; // una ventana al 50% del tamaño de la pantalla
        float escalarAncho = 0.9999F;
        int ancho = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * escalarAncho);
        int alto = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * escalarAlto);
        this.setSize(ancho, alto);

        this.setLocationRelativeTo(null);
        camb = new FREP011(this);
        HoraSistema horasistema = new HoraSistema(txtHora, txtFecha);
        horasistema.start();
        txtNombreUsuario.setText(nombre);

        txtRolUsuario.setText(rol);
        rolElegido = rol;
        tipocambio = new Tipocambio();
        nombreUsuario = nombre;
        iniciarTipoCambio();
        
//        validarEntradasegunRol();
        configurarOpcionesMenu();
        ocultarOpcionesMenu();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea salir del sistema", "Salida del Sistema", 2);
                if ( opcion == 0 ) {
                    System.exit(0);
                } else {
                    return;
                }
            }
        });
        
//        int rgbElegido = -16711681;
//        panelInicio.setBackground(new Color(rgbElegido));
    }
    
    private void ocultarOpcionesMenu() {
        menu_Inventario.setVisible(false);
    }
    
    private void resetearOpcionesMenu() {
        menu_Tablas.setVisible(false);
        FREP001.setVisible(false);
        item_Clientes.setVisible(false);
        item_CodigosEquipos.setVisible(false);
        item_Marca.setVisible(false);
        item_Modelo.setVisible(false);
        item_Operaciones.setVisible(false);
        item_Transportistas.setVisible(false);
        item_Vendedores.setVisible(false);
        item_Importadores.setVisible(false);
        item_Paquetes.setVisible(false);
        item_Bancos.setVisible(false);
        item_Estratificacion.setVisible(false);
        item_TipoCambio.setVisible(false);
        item_Control.setVisible(false);
        item_Sistema.setVisible(false);
        item_Usuario.setVisible(false);
        item_Programas.setVisible(false);
        item_PuntosVenta.setVisible(false);
        item_Roles.setVisible(false);
        item_Accesos.setVisible(false);

        menu_FacturacionProforma.setVisible(false);
        item_facturacion.setVisible(false);
        item_emision_notas.setVisible(false);
        item_ReImpresionFBN.setVisible(false);
        item_guias_remision.setVisible(false);
        item_Anulacion_Documentos.setVisible(false);

        menu_Importaciones.setVisible(false);
        item_pedido.setVisible(false);
        item_impPedido.setVisible(false);
        item_valoracion.setVisible(false);
        item_consultaDem.setVisible(false);

        menu_Movimientos.setVisible(false);
        item_ingressalidas.setVisible(false);
        item_modificaAnul_Salidas.setVisible(false);
        item_estrat_repuestos.setVisible(false);
        jMenuItem2.setVisible(false);

        menu_ConsultasReportes.setVisible(false);
        item_ConsultaMaestroRep.setVisible(false);
        item_emision_planillas.setVisible(false);
        item_comisiones.setVisible(false);
        item_HistorialMovimiento.setVisible(false);
        item_inventarioAl.setVisible(false);
        item_listaprecios.setVisible(false);
        item_consultacli.setVisible(false);
        item_Rep_Estratificacion.setVisible(false);
        item_StockMin.setVisible(false);
        item_flujo_comparativo.setVisible(false);

        menu_CuentasCorrientes.setVisible(false);
        item_CuentasCobrar_Facturas.setVisible(false);
        item_CuentasCobrar_Letras.setVisible(false);
        item_CuentasCobrar_Almacen.setVisible(false);
        item_Canje_Fact_Letras.setVisible(false);
        item_Gener_LetrasVarias.setVisible(false);
        item_ResumCliente.setVisible(false);
        
        btnPagar_CCF = false;
        btnPagar_CCL = false;
        btnPagar_CCSA = false;

        menu_Inventario.setVisible(false);
        item_creacionarchivo.setVisible(false);
        item_reporteinventario.setVisible(false);
        item_DigitacionInvent.setVisible(false);
        item_DiferenciaInvent.setVisible(false);
        item_cierreinventario.setVisible(false);
    }
    
    private void configurarOpcionesMenu() {
        resetearOpcionesMenu();
        Roles rol = new Roles();
        rol = new RolesDAO().Obtener_Objeto_por_nombre(rolElegido);
        int idRol = rol.getIdrol();
        List<Accesos> accesos = new ArrayList<Accesos>();
        accesos = new Servicio_Accesos().listarAccesos(idRol);
        
        boolean menuTabla = false;
        boolean menu_Facturacion = false;
        boolean menu_Importacion = false;
        boolean menu_Movimiento = false;
        boolean menu_Consultas = false;
        boolean menu_Cuentas = false;
        boolean menu_Invent = false;
        
        for ( Accesos acceso : accesos ) {
            Programas programa = acceso.getProgramas();
            String descripcionPrograma = programa.getDescripcion();
            // OPCIONES DEL MENU TABLAS
            if ( "Repuestos".equals(descripcionPrograma) ) {
                menuTabla = true;
                FREP001.setVisible(true);
            }
            if ( "Clientes".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_Clientes.setVisible(true);
            }
            if ( "Equipos".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_CodigosEquipos.setVisible(true);
            } 
            if ("Marca".equals(descripcionPrograma)) {
                menuTabla = true;
                item_Marca.setVisible(true);
                }
            
            if ("Modelo".equals(descripcionPrograma)) {
                menuTabla = true;
                item_Modelo.setVisible(true);
 
            }
            if ( "Operaciones".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_Operaciones.setVisible(true);
            }
            if ( "Transportistas".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_Transportistas.setVisible(true);
            }
            if ( "Vendedores".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_Vendedores.setVisible(true);
            }
            if ( "Importadores".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_Importadores.setVisible(true);
            }
            if ( "Paquetes".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_Paquetes.setVisible(true);
            }
            if ( "Bancos".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_Bancos.setVisible(true);
            }
            if ( "Estratificación".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_Estratificacion.setVisible(true);
            }
            if ( "Tipo de Cambio".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_TipoCambio.setVisible(true);
            }
            if ( "Control".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_Control.setVisible(true);
            }
            if ( "Sistema".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_Sistema.setVisible(true);
            }
            if ( "Usuario".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_Usuario.setVisible(true);
            }
            if ( "Programas".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_Programas.setVisible(true);
            }
            if ("Puntos de Venta".equals(descripcionPrograma)) {
                menuTabla = true;
                item_PuntosVenta.setVisible(true);
 
            }
            if ( "Roles".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_Roles.setVisible(true);
            }
            if ( "Accesos".equals(descripcionPrograma) ) {
                menuTabla = true;
                item_Accesos.setVisible(true);
            }
            if ( menuTabla == true ) {
                menu_Tablas.setVisible(true);
            }
            
            // OPCIONES DEL MENU FACTURACION Y PROFORMAS
            if ( "Facturación (Proformas / Web)".equals(descripcionPrograma) ) {
                menu_Facturacion = true;
                item_facturacion.setVisible(true);
            }
            if ( "Emisión de Notas".equals(descripcionPrograma) ) {
                menu_Facturacion = true;
                item_emision_notas.setVisible(true);
            }
            if ( "Re-Impresión (Factura - Boleta - Nota)".equals(descripcionPrograma) ) {
                menu_Facturacion = true;
                item_ReImpresionFBN.setVisible(true);
            }
            if ( "Guías de Remisión".equals(descripcionPrograma) ) {
                menu_Facturacion = true;
                item_guias_remision.setVisible(true);
            }
            if ( "Anulación de Documentos".equals(descripcionPrograma) ) {
                menu_Facturacion = true;
                item_Anulacion_Documentos.setVisible(true);
            }
            if ( menu_Facturacion == true ) {
                menu_FacturacionProforma.setVisible(true);
            }
            
            // OPCIONES DEL MENU IMPORTACIONES
            if ( "Pedidos de Importación".equals(descripcionPrograma) ) {
                menu_Importacion =true;
                item_pedido.setVisible(true);
            }
            if ( "Impresión de Pedido".equals(descripcionPrograma) ) {
                menu_Importacion =true;
                item_impPedido.setVisible(true);
            }
            if ( "Valoración de Pedido + Resumen + Ingreso".equals(descripcionPrograma) ) {
                menu_Importacion =true;
                item_valoracion.setVisible(true);
            }
            if ( "Consulta de Demandas".equals(descripcionPrograma) ) {
                menu_Importacion =true;
                item_consultaDem.setVisible(true);
            }
            if ( menu_Importacion == true ) {
                menu_Importaciones.setVisible(true);
            }
            
            // OPCIONES DEL MENU MOVIMIENTOS
            if ( "Ingresos / Salidas Almacén".equals(descripcionPrograma) ) {
                menu_Movimiento =true;
                item_ingressalidas.setVisible(true);
            }
            if ( "Modifica / Anula Salida Almacén".equals(descripcionPrograma) ) {
                menu_Movimiento =true;
                item_modificaAnul_Salidas.setVisible(true);
            }
            if ( "Estratificación de Repuestos".equals(descripcionPrograma) ) {
                menu_Movimiento =true;
                item_estrat_repuestos.setVisible(true);
            }
            if ( "Cambio de Precios".equals(descripcionPrograma) ) {
                menu_Movimiento =true;
                jMenuItem2.setVisible(true);
            }
            if ( menu_Movimiento == true ) {
                menu_Movimientos.setVisible(true);
            }
            
            // OPCIONES DEL MENU CONSULTAS Y REPORTES
            if ( "Maestro de Repuestos".equals(descripcionPrograma) ) {
                menu_Consultas =true;
                item_ConsultaMaestroRep.setVisible(true);
            }
            if ( "Emisión de Planilla (Ventas/Varias/Compras)".equals(descripcionPrograma) ) {
                menu_Consultas =true;
                item_emision_planillas.setVisible(true);
            }
            if ( "Comisiones (Vendedores)".equals(descripcionPrograma) ) {
                menu_Consultas =true;
                item_comisiones.setVisible(true);
            }
            if ( "Historial de Movimiento".equals(descripcionPrograma) ) {
                menu_Consultas =true;
                item_HistorialMovimiento.setVisible(true);
            }
            if ( "Inventario Valorizado".equals(descripcionPrograma) ) {
                menu_Consultas =true;
                item_inventarioAl.setVisible(true);
            }
            if ( "Lista de Precios".equals(descripcionPrograma) ) {
                menu_Consultas =true;
                item_listaprecios.setVisible(true);
            }
            if ( "Relacion Clientes / Proveedores".equals(descripcionPrograma) ) {
                menu_Consultas =true;
                item_consultacli.setVisible(true);
            }
            if ( "Repuestos según Estratificación".equals(descripcionPrograma) ) {
                menu_Consultas =true;
                item_Rep_Estratificacion.setVisible(true);
            }
            if ( "Stocks Mínimos".equals(descripcionPrograma) ) {
                menu_Consultas =true;
                item_StockMin.setVisible(true);
            }
            if ("Flujo Comparativo de Ventas".equals(descripcionPrograma)) {
                menu_Consultas = true;
                item_flujo_comparativo.setVisible(true);
            }
            if ( menu_Consultas == true ) {
                menu_ConsultasReportes.setVisible(true);
            }
            
            // OPCIONES DEL MENU CUENTAS CORRIENTES
            if ( "Cuentas x Cobrar - Facturas".equals(descripcionPrograma) ) {
                menu_Cuentas =true;
                item_CuentasCobrar_Facturas.setVisible(true);
            }
            if ( "Cuentas x Cobrar - Letras".equals(descripcionPrograma) ) {
                menu_Cuentas =true;
                item_CuentasCobrar_Letras.setVisible(true);
            }
            if ( "Cuentas x Cobrar - Salidas Almacén".equals(descripcionPrograma) ) {
                menu_Cuentas =true;
                item_CuentasCobrar_Almacen.setVisible(true);
            }
            if ( "Canje de Facturas x Letras".equals(descripcionPrograma) ) {
                menu_Cuentas =true;
                item_Canje_Fact_Letras.setVisible(true);
            }
            if ( "Generación de Letras Varias".equals(descripcionPrograma) ) {
                menu_Cuentas =true;
                item_Gener_LetrasVarias.setVisible(true);
            }
            if ( "Resumen General de Cliente".equals(descripcionPrograma) ) {
                menu_Cuentas =true;
                item_ResumCliente.setVisible(true);
            }
            
            // Botones Pagar
            if ( "Cuentas x Cobrar - Facturas (Pagar)".equals(descripcionPrograma) ) {
                btnPagar_CCF = true;
            }
            if ( "Cuentas x Cobrar - Letras (Pagar)".equals(descripcionPrograma) ) {
                btnPagar_CCL = true;
            }
            if ( "Cuentas x Cobrar - Salidas Almacén (Pagar)".equals(descripcionPrograma) ) {
                btnPagar_CCSA = true;
            }
            
            if ( menu_Cuentas == true ) {
                menu_CuentasCorrientes.setVisible(true);
            }
            
            // OCPIONES DEL MENU INVENTARIO FISICO
            if ( "Creación Archivo - Toma Inventario".equals(descripcionPrograma) ) {
                menu_Invent = true;
                item_creacionarchivo.setVisible(true);
            }
            if ( "Reporte para toma de Inventario".equals(descripcionPrograma) ) {
                menu_Invent = true;
                item_reporteinventario.setVisible(true);
            }
            if ( "Digitación de Inventario".equals(descripcionPrograma) ) {
                menu_Invent = true;
                item_DigitacionInvent.setVisible(true);
            }
            if ( "Diferencias de Inventario".equals(descripcionPrograma) ) {
                menu_Invent = true;
                item_DiferenciaInvent.setVisible(true);
            }
            if ( "Cierre de Inventario".equals(descripcionPrograma) ) {
                menu_Invent = true;
                item_cierreinventario.setVisible(true);
            }
            if ( menu_Invent == true ) {
                menu_Inventario.setVisible(true);
            }
            
        }
        
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        panelSuperior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtRolUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtHora = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtValorCompra = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtValorVenta = new javax.swing.JTextField();
        panelCentral = new javax.swing.JPanel();
        panelInicio = new javax.swing.JPanel();
        nombreEmpresa = new javax.swing.JLabel();
        scrollInicio = new javax.swing.JScrollPane();
        panelSet = new javax.swing.JPanel();
        scrollMaestroRepuestos = new javax.swing.JScrollPane();
        panelMaestroRepuestos = new javax.swing.JPanel();
        scrollClientes = new javax.swing.JScrollPane();
        panelClientes = new javax.swing.JPanel();
        scrollCodigoEquipos = new javax.swing.JScrollPane();
        panelCodigoEquipos = new javax.swing.JPanel();
        scrollOperaciones = new javax.swing.JScrollPane();
        panelOperaciones = new javax.swing.JPanel();
        scrollTransportistas = new javax.swing.JScrollPane();
        panelTransportistas = new javax.swing.JPanel();
        scrollVendedores = new javax.swing.JScrollPane();
        panelVendedores = new javax.swing.JPanel();
        scrollImportadores = new javax.swing.JScrollPane();
        panelImportadores = new javax.swing.JPanel();
        scrollPaquetes = new javax.swing.JScrollPane();
        panelPaquetes = new javax.swing.JPanel();
        scrollBanco = new javax.swing.JScrollPane();
        panelBanco = new javax.swing.JPanel();
        scrollEstratificacion = new javax.swing.JScrollPane();
        panelEstratificacion = new javax.swing.JPanel();
        scrollTipoCambio = new javax.swing.JScrollPane();
        panelTipoCambio = new javax.swing.JPanel();
        scrollControl = new javax.swing.JScrollPane();
        panelControl = new javax.swing.JPanel();
        scrollSistema = new javax.swing.JScrollPane();
        panelSistema = new javax.swing.JPanel();
        scrollUsuario = new javax.swing.JScrollPane();
        panelUsuario = new javax.swing.JPanel();
        scrollProgramas = new javax.swing.JScrollPane();
        panelProgramas = new javax.swing.JPanel();
        scrollRoles = new javax.swing.JScrollPane();
        panelRoles = new javax.swing.JPanel();
        scrollAccesos = new javax.swing.JScrollPane();
        panelAccesos = new javax.swing.JPanel();
        scrollFacturacion = new javax.swing.JScrollPane();
        panelFacturacion = new javax.swing.JPanel();
        scrollEmision_Notas = new javax.swing.JScrollPane();
        panelEmision_Notas = new javax.swing.JPanel();
        scrollReImpresion = new javax.swing.JScrollPane();
        panelReImpresion = new javax.swing.JPanel();
        scrollGuias_Remision = new javax.swing.JScrollPane();
        panelGuias_Remision = new javax.swing.JPanel();
        scrollAnulacion_Documentos = new javax.swing.JScrollPane();
        panelAnulacion_Documentos = new javax.swing.JPanel();
        scrollPedidos_Importacion = new javax.swing.JScrollPane();
        panelPedidos_Importacion = new javax.swing.JPanel();
        scrollImpresion_Pedido = new javax.swing.JScrollPane();
        panelImpresion_Pedido = new javax.swing.JPanel();
        scrollValoracion_Pedido = new javax.swing.JScrollPane();
        panelValoracion_Pedido = new javax.swing.JPanel();
        scrollConsulta_Demanda = new javax.swing.JScrollPane();
        panelConsulta_Demanda = new javax.swing.JPanel();
        scrollIngresos_Salidas = new javax.swing.JScrollPane();
        panelIngresos_Salidas = new javax.swing.JPanel();
        scrollModifica_Anula_Almacen = new javax.swing.JScrollPane();
        panelModifica_Anula_Almacen = new javax.swing.JPanel();
        scrollEstratificarRepuestos = new javax.swing.JScrollPane();
        panelEstratificar_Repuestos = new javax.swing.JPanel();
        scrollCambioPrecios = new javax.swing.JScrollPane();
        panelCambioPrecios = new javax.swing.JPanel();
        scrollconsulta_maestro_repuestos = new javax.swing.JScrollPane();
        panelConsulta_maestro_repuestos = new javax.swing.JPanel();
        scrollEmision_planilla = new javax.swing.JScrollPane();
        panelEmision_planilla = new javax.swing.JPanel();
        scrollComisiones = new javax.swing.JScrollPane();
        panelComisiones = new javax.swing.JPanel();
        scrollHistorial_movimiento = new javax.swing.JScrollPane();
        panelHistorial_movimiento = new javax.swing.JPanel();
        scrollInventario_almacen = new javax.swing.JScrollPane();
        panelInventario_almacen = new javax.swing.JPanel();
        scrollLista_precios = new javax.swing.JScrollPane();
        panelLista_precios = new javax.swing.JPanel();
        scrollConsulta_clientes = new javax.swing.JScrollPane();
        panelConsulta_clientes = new javax.swing.JPanel();
        scrollRepuestos_estratificacion = new javax.swing.JScrollPane();
        panelRepuestos_estratificacion = new javax.swing.JPanel();
        scrollResumen_anual_ventas = new javax.swing.JScrollPane();
        panelResumen_anual_ventas = new javax.swing.JPanel();
        scrollStocks_minimos = new javax.swing.JScrollPane();
        panelStocks_minimos = new javax.swing.JPanel();
        scrollCuentas_cobrar_facturas = new javax.swing.JScrollPane();
        panelCuentas_cobrar_facturas = new javax.swing.JPanel();
        scrollCuentas_cobrar_letras = new javax.swing.JScrollPane();
        panelCuentas_cobrar_letras = new javax.swing.JPanel();
        scrollCuentas_cobrar_salidas = new javax.swing.JScrollPane();
        panelCuentas_cobrar_salidas = new javax.swing.JPanel();
        scrollCanje_facturas_letras = new javax.swing.JScrollPane();
        panelCanje_facturas_letras = new javax.swing.JPanel();
        scrollGeneracion_letras = new javax.swing.JScrollPane();
        panelGeneracion_letras = new javax.swing.JPanel();
        scrollResumenCliente = new javax.swing.JScrollPane();
        panelResumen_cliente = new javax.swing.JPanel();
        scrollCreacion_archivo = new javax.swing.JScrollPane();
        panelCreacion_archivo = new javax.swing.JPanel();
        panelReporte_inventario = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        scrollDigitacion_inventario = new javax.swing.JScrollPane();
        panelDigitacion_inventario = new javax.swing.JPanel();
        scrollDiferencias_inventario = new javax.swing.JScrollPane();
        panelDiferencias_inventario = new javax.swing.JPanel();
        scrollCierre_inventario = new javax.swing.JScrollPane();
        panelCierre_inventario = new javax.swing.JPanel();
        scrollGenerarGR = new javax.swing.JScrollPane();
        panelGenerarGR = new javax.swing.JPanel();
        scrollFactura_electronica = new javax.swing.JScrollPane();
        panelFactura_electronica = new javax.swing.JPanel();
        scrollFlujo_comparativo = new javax.swing.JScrollPane();
        panelFlujo_comparativo = new javax.swing.JPanel();
        scrollMarcas = new javax.swing.JScrollPane();
        panelMarcas = new javax.swing.JPanel();
        scrollModelos = new javax.swing.JScrollPane();
        panelModelos = new javax.swing.JPanel();
        scrollPuntos_venta = new javax.swing.JScrollPane();
        panelPuntos_venta = new javax.swing.JPanel();
        panelInferior = new javax.swing.JPanel();
        btnClientes = new javax.swing.JButton();
        btnRepuestos = new javax.swing.JButton();
        botonLineas = new javax.swing.JButton();
        botonDemandas = new javax.swing.JButton();
        botonVendedores = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_Tablas = new javax.swing.JMenu();
        FREP001 = new javax.swing.JMenuItem();
        item_Clientes = new javax.swing.JMenuItem();
        item_CodigosEquipos = new javax.swing.JMenuItem();
        item_Marca = new javax.swing.JMenuItem();
        item_Modelo = new javax.swing.JMenuItem();
        item_Operaciones = new javax.swing.JMenuItem();
        item_Transportistas = new javax.swing.JMenuItem();
        item_Vendedores = new javax.swing.JMenuItem();
        item_Importadores = new javax.swing.JMenuItem();
        item_Paquetes = new javax.swing.JMenuItem();
        item_Bancos = new javax.swing.JMenuItem();
        item_Estratificacion = new javax.swing.JMenuItem();
        item_TipoCambio = new javax.swing.JMenuItem();
        item_Control = new javax.swing.JMenuItem();
        item_Sistema = new javax.swing.JMenuItem();
        item_Usuario = new javax.swing.JMenuItem();
        item_Programas = new javax.swing.JMenuItem();
        item_Roles = new javax.swing.JMenuItem();
        item_Accesos = new javax.swing.JMenuItem();
        item_PuntosVenta = new javax.swing.JMenuItem();
        menu_FacturacionProforma = new javax.swing.JMenu();
        item_facturacion = new javax.swing.JMenuItem();
        item_emision_notas = new javax.swing.JMenuItem();
        item_ReImpresionFBN = new javax.swing.JMenuItem();
        item_guias_remision = new javax.swing.JMenuItem();
        item_Anulacion_Documentos = new javax.swing.JMenuItem();
        item_factelect = new javax.swing.JMenuItem();
        menu_Importaciones = new javax.swing.JMenu();
        item_pedido = new javax.swing.JMenuItem();
        item_impPedido = new javax.swing.JMenuItem();
        item_valoracion = new javax.swing.JMenuItem();
        item_consultaDem = new javax.swing.JMenuItem();
        menu_Movimientos = new javax.swing.JMenu();
        item_ingressalidas = new javax.swing.JMenuItem();
        item_modificaAnul_Salidas = new javax.swing.JMenuItem();
        item_estrat_repuestos = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        menu_ConsultasReportes = new javax.swing.JMenu();
        item_ConsultaMaestroRep = new javax.swing.JMenuItem();
        item_emision_planillas = new javax.swing.JMenuItem();
        item_comisiones = new javax.swing.JMenuItem();
        item_HistorialMovimiento = new javax.swing.JMenuItem();
        item_inventarioAl = new javax.swing.JMenuItem();
        item_listaprecios = new javax.swing.JMenuItem();
        item_consultacli = new javax.swing.JMenuItem();
        item_Rep_Estratificacion = new javax.swing.JMenuItem();
        item_StockMin = new javax.swing.JMenuItem();
        item_flujo_comparativo = new javax.swing.JMenuItem();
        menu_CuentasCorrientes = new javax.swing.JMenu();
        item_CuentasCobrar_Facturas = new javax.swing.JMenuItem();
        item_CuentasCobrar_Letras = new javax.swing.JMenuItem();
        item_CuentasCobrar_Almacen = new javax.swing.JMenuItem();
        item_Canje_Fact_Letras = new javax.swing.JMenuItem();
        item_Gener_LetrasVarias = new javax.swing.JMenuItem();
        item_Letras_Protestadas = new javax.swing.JMenuItem();
        item_ResumCliente = new javax.swing.JMenuItem();
        menu_Inventario = new javax.swing.JMenu();
        item_creacionarchivo = new javax.swing.JMenuItem();
        item_reporteinventario = new javax.swing.JMenuItem();
        item_DigitacionInvent = new javax.swing.JMenuItem();
        item_DiferenciaInvent = new javax.swing.JMenuItem();
        item_cierreinventario = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        boton_Salir_Sistema = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("SISTEMA DE INVENTARIOS Y REPUESTOS - SIAR");
        getContentPane().setLayout(new java.awt.BorderLayout(1, 0));

        panelSuperior.setBackground(javax.swing.UIManager.getDefaults().getColor("InternalFrame.activeTitleGradient"));
        panelSuperior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        panelSuperior.setPreferredSize(new java.awt.Dimension(1628, 50));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setText("Usuario: ");

        txtNombreUsuario.setEditable(false);

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setText("Tipo de Usuario: ");

        txtRolUsuario.setEditable(false);

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setText("Fecha:");

        txtFecha.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel4.setText("Hora:");

        txtHora.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel5.setText("Valor Compra:");

        txtValorCompra.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel6.setText("Valor Venta: ");

        txtValorVenta.setEditable(false);

        javax.swing.GroupLayout panelSuperiorLayout = new javax.swing.GroupLayout(panelSuperior);
        panelSuperior.setLayout(panelSuperiorLayout);
        panelSuperiorLayout.setHorizontalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSuperiorLayout.createSequentialGroup()
                .addContainerGap(292, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRolUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtValorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        panelSuperiorLayout.setVerticalGroup(
            panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtRolUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtValorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtValorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(panelSuperior, java.awt.BorderLayout.PAGE_START);

        panelCentral.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        panelCentral.setAutoscrolls(true);
        panelCentral.setLayout(new java.awt.CardLayout());

        panelInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        nombreEmpresa.setFont(new java.awt.Font("Cambria", 1, 36));
        nombreEmpresa.setText(obtenerNombre());

        javax.swing.GroupLayout panelInicioLayout = new javax.swing.GroupLayout(panelInicio);
        panelInicio.setLayout(panelInicioLayout);
        panelInicioLayout.setHorizontalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInicioLayout.createSequentialGroup()
                .addContainerGap(373, Short.MAX_VALUE)
                .addComponent(nombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 1176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        panelInicioLayout.setVerticalGroup(
            panelInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInicioLayout.createSequentialGroup()
                .addContainerGap(361, Short.MAX_VALUE)
                .addComponent(nombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(505, 505, 505))
        );

        panelCentral.add(panelInicio, "card48");
        panelInicio.getAccessibleContext().setAccessibleName("");
        panelInicio.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout panelSetLayout = new javax.swing.GroupLayout(panelSet);
        panelSet.setLayout(panelSetLayout);
        panelSetLayout.setHorizontalGroup(
            panelSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelSetLayout.setVerticalGroup(
            panelSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollInicio.setViewportView(panelSet);

        panelCentral.add(scrollInicio, "scrollInicio");

        javax.swing.GroupLayout panelMaestroRepuestosLayout = new javax.swing.GroupLayout(panelMaestroRepuestos);
        panelMaestroRepuestos.setLayout(panelMaestroRepuestosLayout);
        panelMaestroRepuestosLayout.setHorizontalGroup(
            panelMaestroRepuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelMaestroRepuestosLayout.setVerticalGroup(
            panelMaestroRepuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollMaestroRepuestos.setViewportView(panelMaestroRepuestos);

        panelCentral.add(scrollMaestroRepuestos, "scrollMaestroRepuestos");

        javax.swing.GroupLayout panelClientesLayout = new javax.swing.GroupLayout(panelClientes);
        panelClientes.setLayout(panelClientesLayout);
        panelClientesLayout.setHorizontalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelClientesLayout.setVerticalGroup(
            panelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollClientes.setViewportView(panelClientes);

        panelCentral.add(scrollClientes, "scrollClientes");

        javax.swing.GroupLayout panelCodigoEquiposLayout = new javax.swing.GroupLayout(panelCodigoEquipos);
        panelCodigoEquipos.setLayout(panelCodigoEquiposLayout);
        panelCodigoEquiposLayout.setHorizontalGroup(
            panelCodigoEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelCodigoEquiposLayout.setVerticalGroup(
            panelCodigoEquiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollCodigoEquipos.setViewportView(panelCodigoEquipos);

        panelCentral.add(scrollCodigoEquipos, "scrollCodigoEquipos");

        javax.swing.GroupLayout panelOperacionesLayout = new javax.swing.GroupLayout(panelOperaciones);
        panelOperaciones.setLayout(panelOperacionesLayout);
        panelOperacionesLayout.setHorizontalGroup(
            panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelOperacionesLayout.setVerticalGroup(
            panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollOperaciones.setViewportView(panelOperaciones);

        panelCentral.add(scrollOperaciones, "scrollOperaciones");

        javax.swing.GroupLayout panelTransportistasLayout = new javax.swing.GroupLayout(panelTransportistas);
        panelTransportistas.setLayout(panelTransportistasLayout);
        panelTransportistasLayout.setHorizontalGroup(
            panelTransportistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelTransportistasLayout.setVerticalGroup(
            panelTransportistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollTransportistas.setViewportView(panelTransportistas);

        panelCentral.add(scrollTransportistas, "scrollTransportistas");

        javax.swing.GroupLayout panelVendedoresLayout = new javax.swing.GroupLayout(panelVendedores);
        panelVendedores.setLayout(panelVendedoresLayout);
        panelVendedoresLayout.setHorizontalGroup(
            panelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelVendedoresLayout.setVerticalGroup(
            panelVendedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollVendedores.setViewportView(panelVendedores);

        panelCentral.add(scrollVendedores, "scrollVendedores");

        javax.swing.GroupLayout panelImportadoresLayout = new javax.swing.GroupLayout(panelImportadores);
        panelImportadores.setLayout(panelImportadoresLayout);
        panelImportadoresLayout.setHorizontalGroup(
            panelImportadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelImportadoresLayout.setVerticalGroup(
            panelImportadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollImportadores.setViewportView(panelImportadores);

        panelCentral.add(scrollImportadores, "scrollImportadores");

        javax.swing.GroupLayout panelPaquetesLayout = new javax.swing.GroupLayout(panelPaquetes);
        panelPaquetes.setLayout(panelPaquetesLayout);
        panelPaquetesLayout.setHorizontalGroup(
            panelPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelPaquetesLayout.setVerticalGroup(
            panelPaquetesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollPaquetes.setViewportView(panelPaquetes);

        panelCentral.add(scrollPaquetes, "scrollPaquetes");

        javax.swing.GroupLayout panelBancoLayout = new javax.swing.GroupLayout(panelBanco);
        panelBanco.setLayout(panelBancoLayout);
        panelBancoLayout.setHorizontalGroup(
            panelBancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelBancoLayout.setVerticalGroup(
            panelBancoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollBanco.setViewportView(panelBanco);

        panelCentral.add(scrollBanco, "scrollBanco");

        javax.swing.GroupLayout panelEstratificacionLayout = new javax.swing.GroupLayout(panelEstratificacion);
        panelEstratificacion.setLayout(panelEstratificacionLayout);
        panelEstratificacionLayout.setHorizontalGroup(
            panelEstratificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelEstratificacionLayout.setVerticalGroup(
            panelEstratificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollEstratificacion.setViewportView(panelEstratificacion);

        panelCentral.add(scrollEstratificacion, "scrollEstratificacion");

        javax.swing.GroupLayout panelTipoCambioLayout = new javax.swing.GroupLayout(panelTipoCambio);
        panelTipoCambio.setLayout(panelTipoCambioLayout);
        panelTipoCambioLayout.setHorizontalGroup(
            panelTipoCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelTipoCambioLayout.setVerticalGroup(
            panelTipoCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollTipoCambio.setViewportView(panelTipoCambio);

        panelCentral.add(scrollTipoCambio, "scrollTipoCambio");

        javax.swing.GroupLayout panelControlLayout = new javax.swing.GroupLayout(panelControl);
        panelControl.setLayout(panelControlLayout);
        panelControlLayout.setHorizontalGroup(
            panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelControlLayout.setVerticalGroup(
            panelControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollControl.setViewportView(panelControl);

        panelCentral.add(scrollControl, "scrollControl");

        javax.swing.GroupLayout panelSistemaLayout = new javax.swing.GroupLayout(panelSistema);
        panelSistema.setLayout(panelSistemaLayout);
        panelSistemaLayout.setHorizontalGroup(
            panelSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelSistemaLayout.setVerticalGroup(
            panelSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollSistema.setViewportView(panelSistema);

        panelCentral.add(scrollSistema, "scrollSistema");

        javax.swing.GroupLayout panelUsuarioLayout = new javax.swing.GroupLayout(panelUsuario);
        panelUsuario.setLayout(panelUsuarioLayout);
        panelUsuarioLayout.setHorizontalGroup(
            panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelUsuarioLayout.setVerticalGroup(
            panelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollUsuario.setViewportView(panelUsuario);

        panelCentral.add(scrollUsuario, "scrollUsuario");

        javax.swing.GroupLayout panelProgramasLayout = new javax.swing.GroupLayout(panelProgramas);
        panelProgramas.setLayout(panelProgramasLayout);
        panelProgramasLayout.setHorizontalGroup(
            panelProgramasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelProgramasLayout.setVerticalGroup(
            panelProgramasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollProgramas.setViewportView(panelProgramas);

        panelCentral.add(scrollProgramas, "scrollProgramas");

        javax.swing.GroupLayout panelRolesLayout = new javax.swing.GroupLayout(panelRoles);
        panelRoles.setLayout(panelRolesLayout);
        panelRolesLayout.setHorizontalGroup(
            panelRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelRolesLayout.setVerticalGroup(
            panelRolesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollRoles.setViewportView(panelRoles);

        panelCentral.add(scrollRoles, "scrollRoles");

        javax.swing.GroupLayout panelAccesosLayout = new javax.swing.GroupLayout(panelAccesos);
        panelAccesos.setLayout(panelAccesosLayout);
        panelAccesosLayout.setHorizontalGroup(
            panelAccesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelAccesosLayout.setVerticalGroup(
            panelAccesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollAccesos.setViewportView(panelAccesos);

        panelCentral.add(scrollAccesos, "scrollAccesos");

        javax.swing.GroupLayout panelFacturacionLayout = new javax.swing.GroupLayout(panelFacturacion);
        panelFacturacion.setLayout(panelFacturacionLayout);
        panelFacturacionLayout.setHorizontalGroup(
            panelFacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelFacturacionLayout.setVerticalGroup(
            panelFacturacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollFacturacion.setViewportView(panelFacturacion);

        panelCentral.add(scrollFacturacion, "scrollFacturacion");

        javax.swing.GroupLayout panelEmision_NotasLayout = new javax.swing.GroupLayout(panelEmision_Notas);
        panelEmision_Notas.setLayout(panelEmision_NotasLayout);
        panelEmision_NotasLayout.setHorizontalGroup(
            panelEmision_NotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelEmision_NotasLayout.setVerticalGroup(
            panelEmision_NotasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollEmision_Notas.setViewportView(panelEmision_Notas);

        panelCentral.add(scrollEmision_Notas, "scrollEmision_Notas");

        javax.swing.GroupLayout panelReImpresionLayout = new javax.swing.GroupLayout(panelReImpresion);
        panelReImpresion.setLayout(panelReImpresionLayout);
        panelReImpresionLayout.setHorizontalGroup(
            panelReImpresionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelReImpresionLayout.setVerticalGroup(
            panelReImpresionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollReImpresion.setViewportView(panelReImpresion);

        panelCentral.add(scrollReImpresion, "scrollReImpresion");

        javax.swing.GroupLayout panelGuias_RemisionLayout = new javax.swing.GroupLayout(panelGuias_Remision);
        panelGuias_Remision.setLayout(panelGuias_RemisionLayout);
        panelGuias_RemisionLayout.setHorizontalGroup(
            panelGuias_RemisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelGuias_RemisionLayout.setVerticalGroup(
            panelGuias_RemisionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollGuias_Remision.setViewportView(panelGuias_Remision);

        panelCentral.add(scrollGuias_Remision, "scrollGuias_Remision");

        javax.swing.GroupLayout panelAnulacion_DocumentosLayout = new javax.swing.GroupLayout(panelAnulacion_Documentos);
        panelAnulacion_Documentos.setLayout(panelAnulacion_DocumentosLayout);
        panelAnulacion_DocumentosLayout.setHorizontalGroup(
            panelAnulacion_DocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelAnulacion_DocumentosLayout.setVerticalGroup(
            panelAnulacion_DocumentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollAnulacion_Documentos.setViewportView(panelAnulacion_Documentos);

        panelCentral.add(scrollAnulacion_Documentos, "scrollAnulacion_Documentos");

        javax.swing.GroupLayout panelPedidos_ImportacionLayout = new javax.swing.GroupLayout(panelPedidos_Importacion);
        panelPedidos_Importacion.setLayout(panelPedidos_ImportacionLayout);
        panelPedidos_ImportacionLayout.setHorizontalGroup(
            panelPedidos_ImportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelPedidos_ImportacionLayout.setVerticalGroup(
            panelPedidos_ImportacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollPedidos_Importacion.setViewportView(panelPedidos_Importacion);

        panelCentral.add(scrollPedidos_Importacion, "scrollPedidos_Importacion");

        javax.swing.GroupLayout panelImpresion_PedidoLayout = new javax.swing.GroupLayout(panelImpresion_Pedido);
        panelImpresion_Pedido.setLayout(panelImpresion_PedidoLayout);
        panelImpresion_PedidoLayout.setHorizontalGroup(
            panelImpresion_PedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelImpresion_PedidoLayout.setVerticalGroup(
            panelImpresion_PedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollImpresion_Pedido.setViewportView(panelImpresion_Pedido);

        panelCentral.add(scrollImpresion_Pedido, "scrollImpresion_Pedido");

        javax.swing.GroupLayout panelValoracion_PedidoLayout = new javax.swing.GroupLayout(panelValoracion_Pedido);
        panelValoracion_Pedido.setLayout(panelValoracion_PedidoLayout);
        panelValoracion_PedidoLayout.setHorizontalGroup(
            panelValoracion_PedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelValoracion_PedidoLayout.setVerticalGroup(
            panelValoracion_PedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollValoracion_Pedido.setViewportView(panelValoracion_Pedido);

        panelCentral.add(scrollValoracion_Pedido, "scrollValoracion_Pedido");

        javax.swing.GroupLayout panelConsulta_DemandaLayout = new javax.swing.GroupLayout(panelConsulta_Demanda);
        panelConsulta_Demanda.setLayout(panelConsulta_DemandaLayout);
        panelConsulta_DemandaLayout.setHorizontalGroup(
            panelConsulta_DemandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelConsulta_DemandaLayout.setVerticalGroup(
            panelConsulta_DemandaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollConsulta_Demanda.setViewportView(panelConsulta_Demanda);

        panelCentral.add(scrollConsulta_Demanda, "scrollConsulta_Demanda");

        javax.swing.GroupLayout panelIngresos_SalidasLayout = new javax.swing.GroupLayout(panelIngresos_Salidas);
        panelIngresos_Salidas.setLayout(panelIngresos_SalidasLayout);
        panelIngresos_SalidasLayout.setHorizontalGroup(
            panelIngresos_SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelIngresos_SalidasLayout.setVerticalGroup(
            panelIngresos_SalidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollIngresos_Salidas.setViewportView(panelIngresos_Salidas);

        panelCentral.add(scrollIngresos_Salidas, "scrollIngresos_Salidas");

        javax.swing.GroupLayout panelModifica_Anula_AlmacenLayout = new javax.swing.GroupLayout(panelModifica_Anula_Almacen);
        panelModifica_Anula_Almacen.setLayout(panelModifica_Anula_AlmacenLayout);
        panelModifica_Anula_AlmacenLayout.setHorizontalGroup(
            panelModifica_Anula_AlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelModifica_Anula_AlmacenLayout.setVerticalGroup(
            panelModifica_Anula_AlmacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollModifica_Anula_Almacen.setViewportView(panelModifica_Anula_Almacen);

        panelCentral.add(scrollModifica_Anula_Almacen, "scrollModifica_Anula_Almacen");

        javax.swing.GroupLayout panelEstratificar_RepuestosLayout = new javax.swing.GroupLayout(panelEstratificar_Repuestos);
        panelEstratificar_Repuestos.setLayout(panelEstratificar_RepuestosLayout);
        panelEstratificar_RepuestosLayout.setHorizontalGroup(
            panelEstratificar_RepuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelEstratificar_RepuestosLayout.setVerticalGroup(
            panelEstratificar_RepuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollEstratificarRepuestos.setViewportView(panelEstratificar_Repuestos);

        panelCentral.add(scrollEstratificarRepuestos, "scrollEstratificar_Repuestos");

        javax.swing.GroupLayout panelCambioPreciosLayout = new javax.swing.GroupLayout(panelCambioPrecios);
        panelCambioPrecios.setLayout(panelCambioPreciosLayout);
        panelCambioPreciosLayout.setHorizontalGroup(
            panelCambioPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelCambioPreciosLayout.setVerticalGroup(
            panelCambioPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollCambioPrecios.setViewportView(panelCambioPrecios);

        panelCentral.add(scrollCambioPrecios, "scrollCambio_Precios");

        javax.swing.GroupLayout panelConsulta_maestro_repuestosLayout = new javax.swing.GroupLayout(panelConsulta_maestro_repuestos);
        panelConsulta_maestro_repuestos.setLayout(panelConsulta_maestro_repuestosLayout);
        panelConsulta_maestro_repuestosLayout.setHorizontalGroup(
            panelConsulta_maestro_repuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelConsulta_maestro_repuestosLayout.setVerticalGroup(
            panelConsulta_maestro_repuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollconsulta_maestro_repuestos.setViewportView(panelConsulta_maestro_repuestos);

        panelCentral.add(scrollconsulta_maestro_repuestos, "scrollConsulta_maestro_repuestos");

        javax.swing.GroupLayout panelEmision_planillaLayout = new javax.swing.GroupLayout(panelEmision_planilla);
        panelEmision_planilla.setLayout(panelEmision_planillaLayout);
        panelEmision_planillaLayout.setHorizontalGroup(
            panelEmision_planillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelEmision_planillaLayout.setVerticalGroup(
            panelEmision_planillaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollEmision_planilla.setViewportView(panelEmision_planilla);

        panelCentral.add(scrollEmision_planilla, "scrollEmision_planilla");

        javax.swing.GroupLayout panelComisionesLayout = new javax.swing.GroupLayout(panelComisiones);
        panelComisiones.setLayout(panelComisionesLayout);
        panelComisionesLayout.setHorizontalGroup(
            panelComisionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelComisionesLayout.setVerticalGroup(
            panelComisionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollComisiones.setViewportView(panelComisiones);

        panelCentral.add(scrollComisiones, "scrollComisiones");

        javax.swing.GroupLayout panelHistorial_movimientoLayout = new javax.swing.GroupLayout(panelHistorial_movimiento);
        panelHistorial_movimiento.setLayout(panelHistorial_movimientoLayout);
        panelHistorial_movimientoLayout.setHorizontalGroup(
            panelHistorial_movimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelHistorial_movimientoLayout.setVerticalGroup(
            panelHistorial_movimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollHistorial_movimiento.setViewportView(panelHistorial_movimiento);

        panelCentral.add(scrollHistorial_movimiento, "scrollHistorial_movimiento");

        javax.swing.GroupLayout panelInventario_almacenLayout = new javax.swing.GroupLayout(panelInventario_almacen);
        panelInventario_almacen.setLayout(panelInventario_almacenLayout);
        panelInventario_almacenLayout.setHorizontalGroup(
            panelInventario_almacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelInventario_almacenLayout.setVerticalGroup(
            panelInventario_almacenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollInventario_almacen.setViewportView(panelInventario_almacen);

        panelCentral.add(scrollInventario_almacen, "scrollInventario_almacen");

        javax.swing.GroupLayout panelLista_preciosLayout = new javax.swing.GroupLayout(panelLista_precios);
        panelLista_precios.setLayout(panelLista_preciosLayout);
        panelLista_preciosLayout.setHorizontalGroup(
            panelLista_preciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelLista_preciosLayout.setVerticalGroup(
            panelLista_preciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollLista_precios.setViewportView(panelLista_precios);

        panelCentral.add(scrollLista_precios, "scrollLista_precios");

        javax.swing.GroupLayout panelConsulta_clientesLayout = new javax.swing.GroupLayout(panelConsulta_clientes);
        panelConsulta_clientes.setLayout(panelConsulta_clientesLayout);
        panelConsulta_clientesLayout.setHorizontalGroup(
            panelConsulta_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelConsulta_clientesLayout.setVerticalGroup(
            panelConsulta_clientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollConsulta_clientes.setViewportView(panelConsulta_clientes);

        panelCentral.add(scrollConsulta_clientes, "scrollConsulta_clientes");

        javax.swing.GroupLayout panelRepuestos_estratificacionLayout = new javax.swing.GroupLayout(panelRepuestos_estratificacion);
        panelRepuestos_estratificacion.setLayout(panelRepuestos_estratificacionLayout);
        panelRepuestos_estratificacionLayout.setHorizontalGroup(
            panelRepuestos_estratificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelRepuestos_estratificacionLayout.setVerticalGroup(
            panelRepuestos_estratificacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollRepuestos_estratificacion.setViewportView(panelRepuestos_estratificacion);

        panelCentral.add(scrollRepuestos_estratificacion, "scrollRepuestos_estratificacion");

        javax.swing.GroupLayout panelResumen_anual_ventasLayout = new javax.swing.GroupLayout(panelResumen_anual_ventas);
        panelResumen_anual_ventas.setLayout(panelResumen_anual_ventasLayout);
        panelResumen_anual_ventasLayout.setHorizontalGroup(
            panelResumen_anual_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelResumen_anual_ventasLayout.setVerticalGroup(
            panelResumen_anual_ventasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollResumen_anual_ventas.setViewportView(panelResumen_anual_ventas);

        panelCentral.add(scrollResumen_anual_ventas, "scrollResumen_anual_ventas");

        javax.swing.GroupLayout panelStocks_minimosLayout = new javax.swing.GroupLayout(panelStocks_minimos);
        panelStocks_minimos.setLayout(panelStocks_minimosLayout);
        panelStocks_minimosLayout.setHorizontalGroup(
            panelStocks_minimosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelStocks_minimosLayout.setVerticalGroup(
            panelStocks_minimosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollStocks_minimos.setViewportView(panelStocks_minimos);

        panelCentral.add(scrollStocks_minimos, "scrollStocks_minimos");

        javax.swing.GroupLayout panelCuentas_cobrar_facturasLayout = new javax.swing.GroupLayout(panelCuentas_cobrar_facturas);
        panelCuentas_cobrar_facturas.setLayout(panelCuentas_cobrar_facturasLayout);
        panelCuentas_cobrar_facturasLayout.setHorizontalGroup(
            panelCuentas_cobrar_facturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelCuentas_cobrar_facturasLayout.setVerticalGroup(
            panelCuentas_cobrar_facturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollCuentas_cobrar_facturas.setViewportView(panelCuentas_cobrar_facturas);

        panelCentral.add(scrollCuentas_cobrar_facturas, "scrollCuentas_cobrar_facturas");

        javax.swing.GroupLayout panelCuentas_cobrar_letrasLayout = new javax.swing.GroupLayout(panelCuentas_cobrar_letras);
        panelCuentas_cobrar_letras.setLayout(panelCuentas_cobrar_letrasLayout);
        panelCuentas_cobrar_letrasLayout.setHorizontalGroup(
            panelCuentas_cobrar_letrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelCuentas_cobrar_letrasLayout.setVerticalGroup(
            panelCuentas_cobrar_letrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollCuentas_cobrar_letras.setViewportView(panelCuentas_cobrar_letras);

        panelCentral.add(scrollCuentas_cobrar_letras, "scrollCuentas_cobrar_letras");

        javax.swing.GroupLayout panelCuentas_cobrar_salidasLayout = new javax.swing.GroupLayout(panelCuentas_cobrar_salidas);
        panelCuentas_cobrar_salidas.setLayout(panelCuentas_cobrar_salidasLayout);
        panelCuentas_cobrar_salidasLayout.setHorizontalGroup(
            panelCuentas_cobrar_salidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelCuentas_cobrar_salidasLayout.setVerticalGroup(
            panelCuentas_cobrar_salidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollCuentas_cobrar_salidas.setViewportView(panelCuentas_cobrar_salidas);

        panelCentral.add(scrollCuentas_cobrar_salidas, "scrollCuentas_cobrar_salidas");

        javax.swing.GroupLayout panelCanje_facturas_letrasLayout = new javax.swing.GroupLayout(panelCanje_facturas_letras);
        panelCanje_facturas_letras.setLayout(panelCanje_facturas_letrasLayout);
        panelCanje_facturas_letrasLayout.setHorizontalGroup(
            panelCanje_facturas_letrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelCanje_facturas_letrasLayout.setVerticalGroup(
            panelCanje_facturas_letrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollCanje_facturas_letras.setViewportView(panelCanje_facturas_letras);

        panelCentral.add(scrollCanje_facturas_letras, "scrollCanje_facturas_letras");

        javax.swing.GroupLayout panelGeneracion_letrasLayout = new javax.swing.GroupLayout(panelGeneracion_letras);
        panelGeneracion_letras.setLayout(panelGeneracion_letrasLayout);
        panelGeneracion_letrasLayout.setHorizontalGroup(
            panelGeneracion_letrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelGeneracion_letrasLayout.setVerticalGroup(
            panelGeneracion_letrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollGeneracion_letras.setViewportView(panelGeneracion_letras);

        panelCentral.add(scrollGeneracion_letras, "scrollGeneracion_letras");

        javax.swing.GroupLayout panelResumen_clienteLayout = new javax.swing.GroupLayout(panelResumen_cliente);
        panelResumen_cliente.setLayout(panelResumen_clienteLayout);
        panelResumen_clienteLayout.setHorizontalGroup(
            panelResumen_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelResumen_clienteLayout.setVerticalGroup(
            panelResumen_clienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollResumenCliente.setViewportView(panelResumen_cliente);

        panelCentral.add(scrollResumenCliente, "scrollResumen_cliente");

        javax.swing.GroupLayout panelCreacion_archivoLayout = new javax.swing.GroupLayout(panelCreacion_archivo);
        panelCreacion_archivo.setLayout(panelCreacion_archivoLayout);
        panelCreacion_archivoLayout.setHorizontalGroup(
            panelCreacion_archivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelCreacion_archivoLayout.setVerticalGroup(
            panelCreacion_archivoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollCreacion_archivo.setViewportView(panelCreacion_archivo);

        panelCentral.add(scrollCreacion_archivo, "scrollCreacion_archivo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        panelReporte_inventario.setViewportView(jPanel2);

        panelCentral.add(panelReporte_inventario, "scrollReporte_inventario");

        javax.swing.GroupLayout panelDigitacion_inventarioLayout = new javax.swing.GroupLayout(panelDigitacion_inventario);
        panelDigitacion_inventario.setLayout(panelDigitacion_inventarioLayout);
        panelDigitacion_inventarioLayout.setHorizontalGroup(
            panelDigitacion_inventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelDigitacion_inventarioLayout.setVerticalGroup(
            panelDigitacion_inventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollDigitacion_inventario.setViewportView(panelDigitacion_inventario);

        panelCentral.add(scrollDigitacion_inventario, "scrollDigitacion_inventario");

        javax.swing.GroupLayout panelDiferencias_inventarioLayout = new javax.swing.GroupLayout(panelDiferencias_inventario);
        panelDiferencias_inventario.setLayout(panelDiferencias_inventarioLayout);
        panelDiferencias_inventarioLayout.setHorizontalGroup(
            panelDiferencias_inventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelDiferencias_inventarioLayout.setVerticalGroup(
            panelDiferencias_inventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollDiferencias_inventario.setViewportView(panelDiferencias_inventario);

        panelCentral.add(scrollDiferencias_inventario, "scrollDiferencias_inventario");

        javax.swing.GroupLayout panelCierre_inventarioLayout = new javax.swing.GroupLayout(panelCierre_inventario);
        panelCierre_inventario.setLayout(panelCierre_inventarioLayout);
        panelCierre_inventarioLayout.setHorizontalGroup(
            panelCierre_inventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelCierre_inventarioLayout.setVerticalGroup(
            panelCierre_inventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollCierre_inventario.setViewportView(panelCierre_inventario);

        panelCentral.add(scrollCierre_inventario, "scrollCierre_inventario");

        javax.swing.GroupLayout panelGenerarGRLayout = new javax.swing.GroupLayout(panelGenerarGR);
        panelGenerarGR.setLayout(panelGenerarGRLayout);
        panelGenerarGRLayout.setHorizontalGroup(
            panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelGenerarGRLayout.setVerticalGroup(
            panelGenerarGRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollGenerarGR.setViewportView(panelGenerarGR);

        panelCentral.add(scrollGenerarGR, "scrollGenerarGR");

        javax.swing.GroupLayout panelFactura_electronicaLayout = new javax.swing.GroupLayout(panelFactura_electronica);
        panelFactura_electronica.setLayout(panelFactura_electronicaLayout);
        panelFactura_electronicaLayout.setHorizontalGroup(
            panelFactura_electronicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelFactura_electronicaLayout.setVerticalGroup(
            panelFactura_electronicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollFactura_electronica.setViewportView(panelFactura_electronica);

        panelCentral.add(scrollFactura_electronica, "scrollFactura_electronica");

        javax.swing.GroupLayout panelFlujo_comparativoLayout = new javax.swing.GroupLayout(panelFlujo_comparativo);
        panelFlujo_comparativo.setLayout(panelFlujo_comparativoLayout);
        panelFlujo_comparativoLayout.setHorizontalGroup(
            panelFlujo_comparativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelFlujo_comparativoLayout.setVerticalGroup(
            panelFlujo_comparativoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollFlujo_comparativo.setViewportView(panelFlujo_comparativo);

        panelCentral.add(scrollFlujo_comparativo, "scrollFlujo_comparativo");

        javax.swing.GroupLayout panelMarcasLayout = new javax.swing.GroupLayout(panelMarcas);
        panelMarcas.setLayout(panelMarcasLayout);
        panelMarcasLayout.setHorizontalGroup(
            panelMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelMarcasLayout.setVerticalGroup(
            panelMarcasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollMarcas.setViewportView(panelMarcas);

        panelCentral.add(scrollMarcas, "scrollMarcas");

        javax.swing.GroupLayout panelModelosLayout = new javax.swing.GroupLayout(panelModelos);
        panelModelos.setLayout(panelModelosLayout);
        panelModelosLayout.setHorizontalGroup(
            panelModelosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelModelosLayout.setVerticalGroup(
            panelModelosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollModelos.setViewportView(panelModelos);

        panelCentral.add(scrollModelos, "scrollModelos");

        javax.swing.GroupLayout panelPuntos_ventaLayout = new javax.swing.GroupLayout(panelPuntos_venta);
        panelPuntos_venta.setLayout(panelPuntos_ventaLayout);
        panelPuntos_ventaLayout.setHorizontalGroup(
            panelPuntos_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1641, Short.MAX_VALUE)
        );
        panelPuntos_ventaLayout.setVerticalGroup(
            panelPuntos_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 925, Short.MAX_VALUE)
        );

        scrollPuntos_venta.setViewportView(panelPuntos_venta);

        panelCentral.add(scrollPuntos_venta, "scrollPuntos_venta");

        getContentPane().add(panelCentral, java.awt.BorderLayout.CENTER);

        panelInferior.setBackground(javax.swing.UIManager.getDefaults().getColor("InternalFrame.activeTitleGradient"));
        panelInferior.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        panelInferior.setPreferredSize(new java.awt.Dimension(1628, 70));

        btnClientes.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/los-usuarios-de-foro-de-la-familia-icono-4429-32.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.setToolTipText("Tabla de Clientes");
        btnClientes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnClientesMouseMoved(evt);
            }
        });
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnRepuestos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRepuestos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Repuestos.png"))); // NOI18N
        btnRepuestos.setText("Repuestos");
        btnRepuestos.setToolTipText("Tabla de Repuestos");
        btnRepuestos.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btnRepuestosMouseMoved(evt);
            }
        });
        btnRepuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRepuestosActionPerformed(evt);
            }
        });

        botonLineas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonLineas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Codigo de Lineas.png"))); // NOI18N
        botonLineas.setText("Lineas");
        botonLineas.setToolTipText("Tabla de Líneas");
        botonLineas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botonLineasMouseMoved(evt);
            }
        });
        botonLineas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLineasActionPerformed(evt);
            }
        });

        botonDemandas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonDemandas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tablas-graficos-icono-8272-32.png"))); // NOI18N
        botonDemandas.setText("Consulta de Demandas");
        botonDemandas.setToolTipText("Consultar tabla de Demandas");
        botonDemandas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botonDemandasMouseMoved(evt);
            }
        });
        botonDemandas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonDemandasActionPerformed(evt);
            }
        });

        botonVendedores.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonVendedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Usuario.png"))); // NOI18N
        botonVendedores.setText("Vendedores");
        botonVendedores.setToolTipText("Tabla de Vendedores");
        botonVendedores.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                botonVendedoresMouseMoved(evt);
            }
        });
        botonVendedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVendedoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInferiorLayout = new javax.swing.GroupLayout(panelInferior);
        panelInferior.setLayout(panelInferiorLayout);
        panelInferiorLayout.setHorizontalGroup(
            panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInferiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonLineas, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botonDemandas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonVendedores, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(794, Short.MAX_VALUE))
        );
        panelInferiorLayout.setVerticalGroup(
            panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInferiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(btnRepuestos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonLineas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonDemandas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonVendedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(panelInferior, java.awt.BorderLayout.PAGE_END);

        jMenuBar1.setPreferredSize(new java.awt.Dimension(941, 50));

        menu_Tablas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tabla.png"))); // NOI18N
        menu_Tablas.setText("<html> <center>Tablas</center> </html>");
        menu_Tablas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        menu_Tablas.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menu_TablasMouseMoved(evt);
            }
        });

        FREP001.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        FREP001.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        FREP001.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Repuestos.png"))); // NOI18N
        FREP001.setText("Repuestos");
        FREP001.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FREP001ActionPerformed(evt);
            }
        });
        menu_Tablas.add(FREP001);

        item_Clientes.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/los-usuarios-de-foro-de-la-familia-icono-4429-32.png"))); // NOI18N
        item_Clientes.setText("Clientes");
        item_Clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ClientesActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Clientes);

        item_CodigosEquipos.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_CodigosEquipos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Codigo de Lineas.png"))); // NOI18N
        item_CodigosEquipos.setText("Equipos");
        item_CodigosEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_CodigosEquiposActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_CodigosEquipos);

        item_Marca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Marca.png"))); // NOI18N
        item_Marca.setText("Marca");
        item_Marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_MarcaActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Marca);

        item_Modelo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Modelo.png"))); // NOI18N
        item_Modelo.setText("Modelo");
        item_Modelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ModeloActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Modelo);

        item_Operaciones.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Operaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sistema-de-ejecucion-icono-8776-32.png"))); // NOI18N
        item_Operaciones.setText("Operaciones");
        item_Operaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_OperacionesActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Operaciones);

        item_Transportistas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Transportistas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/transporte-de-camiones-vehiculos-de-color-amarillo-icono-4957-32.png"))); // NOI18N
        item_Transportistas.setText("Transportistas");
        item_Transportistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_TransportistasActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Transportistas);

        item_Vendedores.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Vendedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Usuario.png"))); // NOI18N
        item_Vendedores.setText("Vendedores");
        item_Vendedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_VendedoresActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Vendedores);

        item_Importadores.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Importadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/popular-usuarios-foro-icono-8942-32.png"))); // NOI18N
        item_Importadores.setText("Importadores");
        item_Importadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ImportadoresActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Importadores);

        item_Paquetes.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Paquetes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/paquete-icono-9711-32.png"))); // NOI18N
        item_Paquetes.setText("Paquetes");
        item_Paquetes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_PaquetesActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Paquetes);

        item_Bancos.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Bancos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Banco.png"))); // NOI18N
        item_Bancos.setText("Bancos");
        item_Bancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_BancosActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Bancos);

        item_Estratificacion.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Estratificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tablas-graficos-icono-8272-32.png"))); // NOI18N
        item_Estratificacion.setText("Estratificación");
        item_Estratificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_EstratificacionActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Estratificacion);

        item_TipoCambio.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_TipoCambio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Tipo de Cambio.png"))); // NOI18N
        item_TipoCambio.setText("Tipo de Cambio");
        item_TipoCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_TipoCambioActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_TipoCambio);

        item_Control.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Control.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Control.png"))); // NOI18N
        item_Control.setText("Control");
        item_Control.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ControlActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Control);

        item_Sistema.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Sistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Sistema.png"))); // NOI18N
        item_Sistema.setText("Sistema");
        item_Sistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_SistemaActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Sistema);

        item_Usuario.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/olvidaste-tu-contrasena-de-usuario-de-oficina-de-las-preferencias-de-icono-4565-32.png"))); // NOI18N
        item_Usuario.setText("Usuario");
        item_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_UsuarioActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Usuario);

        item_Programas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Programas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/seleccione-ver-icono-6736-32.png"))); // NOI18N
        item_Programas.setText("Programas");
        item_Programas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ProgramasActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Programas);

        item_Roles.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Roles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/plan-de-organizacion-de-la-red-de-sitio-icono-5788-32.png"))); // NOI18N
        item_Roles.setText("Roles");
        item_Roles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_RolesActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Roles);

        item_Accesos.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Accesos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Accesos.png"))); // NOI18N
        item_Accesos.setText("Accesos");
        item_Accesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_AccesosActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_Accesos);

        item_PuntosVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Punto de Venta.png"))); // NOI18N
        item_PuntosVenta.setText("Punto de Venta");
        item_PuntosVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_PuntosVentaActionPerformed(evt);
            }
        });
        menu_Tablas.add(item_PuntosVenta);

        jMenuBar1.add(menu_Tablas);

        menu_FacturacionProforma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/facturacionproformas.png"))); // NOI18N
        menu_FacturacionProforma.setText("<html> <center> Facturación y<br />Proforma</center> </html>");
        menu_FacturacionProforma.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        menu_FacturacionProforma.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menu_FacturacionProformaMouseMoved(evt);
            }
        });

        item_facturacion.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_facturacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Proforma.png"))); // NOI18N
        item_facturacion.setText("Facturación (Proformas / Web)");
        item_facturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_facturacionActionPerformed(evt);
            }
        });
        menu_FacturacionProforma.add(item_facturacion);

        item_emision_notas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_emision_notas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/emisionNotas.png"))); // NOI18N
        item_emision_notas.setText("Emisión de Notas");
        item_emision_notas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_emision_notasActionPerformed(evt);
            }
        });
        menu_FacturacionProforma.add(item_emision_notas);

        item_ReImpresionFBN.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_ReImpresionFBN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nota-icono-6644-32.png"))); // NOI18N
        item_ReImpresionFBN.setText("Re-Impresión (Factura - Boleta - Nota)");
        item_ReImpresionFBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ReImpresionFBNActionPerformed(evt);
            }
        });
        menu_FacturacionProforma.add(item_ReImpresionFBN);

        item_guias_remision.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_guias_remision.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guiremision.png"))); // NOI18N
        item_guias_remision.setText("Guias de Remisión");
        item_guias_remision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_guias_remisionActionPerformed(evt);
            }
        });
        menu_FacturacionProforma.add(item_guias_remision);

        item_Anulacion_Documentos.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Anulacion_Documentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/anulacion.png"))); // NOI18N
        item_Anulacion_Documentos.setText("Anulación de Documentos");
        item_Anulacion_Documentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_Anulacion_DocumentosActionPerformed(evt);
            }
        });
        menu_FacturacionProforma.add(item_Anulacion_Documentos);

        item_factelect.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_factelect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/factelectronica.jpg"))); // NOI18N
        item_factelect.setText("Facturación Electrónica");
        item_factelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_factelectActionPerformed(evt);
            }
        });
        menu_FacturacionProforma.add(item_factelect);

        jMenuBar1.add(menu_FacturacionProforma);

        menu_Importaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/importacion.png"))); // NOI18N
        menu_Importaciones.setText("<html> <center>Importaciones</center> </html>");
        menu_Importaciones.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        menu_Importaciones.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menu_ImportacionesMouseMoved(evt);
            }
        });

        item_pedido.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_pedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pedido importac.png"))); // NOI18N
        item_pedido.setText("Pedidos de Importación");
        item_pedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_pedidoActionPerformed(evt);
            }
        });
        menu_Importaciones.add(item_pedido);

        item_impPedido.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_impPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/impresion pedido.png"))); // NOI18N
        item_impPedido.setText("Impresión de Pedido");
        item_impPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_impPedidoActionPerformed(evt);
            }
        });
        menu_Importaciones.add(item_impPedido);

        item_valoracion.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_valoracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/valorizacion-resumen-ingre.png"))); // NOI18N
        item_valoracion.setText("Valoracion de Pedido + Resumen +Ingreso");
        item_valoracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_valoracionActionPerformed(evt);
            }
        });
        menu_Importaciones.add(item_valoracion);

        item_consultaDem.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_consultaDem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/estadisticas-icono-6536-32.png"))); // NOI18N
        item_consultaDem.setText("Consulta de Demandas");
        item_consultaDem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_consultaDemActionPerformed(evt);
            }
        });
        menu_Importaciones.add(item_consultaDem);

        jMenuBar1.add(menu_Importaciones);

        menu_Movimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/movimiento.png"))); // NOI18N
        menu_Movimientos.setText("<html> <center>Movimientos</center> </html>");
        menu_Movimientos.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        menu_Movimientos.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menu_MovimientosMouseMoved(evt);
            }
        });

        item_ingressalidas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_ingressalidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ingresoy salida.png"))); // NOI18N
        item_ingressalidas.setText("Ingresos / Salidas Almacén");
        item_ingressalidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ingressalidasActionPerformed(evt);
            }
        });
        menu_Movimientos.add(item_ingressalidas);

        item_modificaAnul_Salidas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_modificaAnul_Salidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/modificarsalida.png"))); // NOI18N
        item_modificaAnul_Salidas.setText("Modifica / Anula Salida Almacén");
        item_modificaAnul_Salidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_modificaAnul_SalidasActionPerformed(evt);
            }
        });
        menu_Movimientos.add(item_modificaAnul_Salidas);

        item_estrat_repuestos.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_estrat_repuestos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/anadir-a-favoritos-de-estrellas-tipo-de-barra-de-herramientas-de-evaluacion-icono-8890-32.png"))); // NOI18N
        item_estrat_repuestos.setText("Estratificación de Repuestos");
        item_estrat_repuestos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_estrat_repuestosActionPerformed(evt);
            }
        });
        menu_Movimientos.add(item_estrat_repuestos);

        jMenuItem2.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cambio_precios.jpg"))); // NOI18N
        jMenuItem2.setText("Cambio de Precios");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menu_Movimientos.add(jMenuItem2);

        jMenuBar1.add(menu_Movimientos);

        menu_ConsultasReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/consultas_reportes.png"))); // NOI18N
        menu_ConsultasReportes.setText("<html> <center>Consultas y<br />Reportes</center> </html>");
        menu_ConsultasReportes.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        menu_ConsultasReportes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menu_ConsultasReportesMouseMoved(evt);
            }
        });

        item_ConsultaMaestroRep.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_ConsultaMaestroRep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Repuestos.png"))); // NOI18N
        item_ConsultaMaestroRep.setText("Maestro de Repuestos");
        item_ConsultaMaestroRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ConsultaMaestroRepActionPerformed(evt);
            }
        });
        menu_ConsultasReportes.add(item_ConsultaMaestroRep);

        item_emision_planillas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_emision_planillas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/emisionplanillass.png"))); // NOI18N
        item_emision_planillas.setText("Emisión de Planilla (Ventas/Varias/Compras)");
        item_emision_planillas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_emision_planillasActionPerformed(evt);
            }
        });
        menu_ConsultasReportes.add(item_emision_planillas);

        item_comisiones.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_comisiones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/negocios-monedas-de-plata-metalica-de-pago-dolar-icono-8061-32.png"))); // NOI18N
        item_comisiones.setText("Comisiones (Vendedores)");
        item_comisiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_comisionesActionPerformed(evt);
            }
        });
        menu_ConsultasReportes.add(item_comisiones);

        item_HistorialMovimiento.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_HistorialMovimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/historial_movimiento.jpg"))); // NOI18N
        item_HistorialMovimiento.setText("Historial de Movimiento");
        item_HistorialMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_HistorialMovimientoActionPerformed(evt);
            }
        });
        menu_ConsultasReportes.add(item_HistorialMovimiento);

        item_inventarioAl.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_inventarioAl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/inventario_almacen.png"))); // NOI18N
        item_inventarioAl.setText("Inventario Valorizado");
        item_inventarioAl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_inventarioAlActionPerformed(evt);
            }
        });
        menu_ConsultasReportes.add(item_inventarioAl);

        item_listaprecios.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_listaprecios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ico-listaprecios.png"))); // NOI18N
        item_listaprecios.setText("Lista de Precios");
        item_listaprecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_listapreciosActionPerformed(evt);
            }
        });
        menu_ConsultasReportes.add(item_listaprecios);

        item_consultacli.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_consultacli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/clients-32531.jpg"))); // NOI18N
        item_consultacli.setText("Relacion Clientes / Proveedores");
        item_consultacli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_consultacliActionPerformed(evt);
            }
        });
        menu_ConsultasReportes.add(item_consultacli);

        item_Rep_Estratificacion.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Rep_Estratificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/repuestos_estratificacion.jpg"))); // NOI18N
        item_Rep_Estratificacion.setText("Repuestos según Estratificación");
        item_Rep_Estratificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_Rep_EstratificacionActionPerformed(evt);
            }
        });
        menu_ConsultasReportes.add(item_Rep_Estratificacion);

        item_StockMin.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_StockMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/stocks_minim.jpg"))); // NOI18N
        item_StockMin.setText("Stocks Mínimos");
        item_StockMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_StockMinActionPerformed(evt);
            }
        });
        menu_ConsultasReportes.add(item_StockMin);

        item_flujo_comparativo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_flujo_comparativo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tabla.png"))); // NOI18N
        item_flujo_comparativo.setText("Flujo Comparativo de Ventas");
        item_flujo_comparativo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_flujo_comparativoActionPerformed(evt);
            }
        });
        menu_ConsultasReportes.add(item_flujo_comparativo);

        jMenuBar1.add(menu_ConsultasReportes);

        menu_CuentasCorrientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cuentas_corrientes.jpg"))); // NOI18N
        menu_CuentasCorrientes.setText("<html> <center>Cuentas<br/>Corrientes</center> </html>");
        menu_CuentasCorrientes.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        menu_CuentasCorrientes.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menu_CuentasCorrientesMouseMoved(evt);
            }
        });

        item_CuentasCobrar_Facturas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_CuentasCobrar_Facturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cuentasxcobrar.jpg"))); // NOI18N
        item_CuentasCobrar_Facturas.setText("Cuentas x Cobrar - Facturas");
        item_CuentasCobrar_Facturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_CuentasCobrar_FacturasActionPerformed(evt);
            }
        });
        menu_CuentasCorrientes.add(item_CuentasCobrar_Facturas);

        item_CuentasCobrar_Letras.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_CuentasCobrar_Letras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cuentasxcobrar.jpg"))); // NOI18N
        item_CuentasCobrar_Letras.setText("Cuentas x Cobrar - Letras");
        item_CuentasCobrar_Letras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_CuentasCobrar_LetrasActionPerformed(evt);
            }
        });
        menu_CuentasCorrientes.add(item_CuentasCobrar_Letras);

        item_CuentasCobrar_Almacen.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_CuentasCobrar_Almacen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cuentasxcobrar.jpg"))); // NOI18N
        item_CuentasCobrar_Almacen.setText("Cuentas x Cobrar - Salidas Almacén");
        item_CuentasCobrar_Almacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_CuentasCobrar_AlmacenActionPerformed(evt);
            }
        });
        menu_CuentasCorrientes.add(item_CuentasCobrar_Almacen);

        item_Canje_Fact_Letras.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Canje_Fact_Letras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/canje_letras.jpg"))); // NOI18N
        item_Canje_Fact_Letras.setText("Canje de Facturas x Letras");
        item_Canje_Fact_Letras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_Canje_Fact_LetrasActionPerformed(evt);
            }
        });
        menu_CuentasCorrientes.add(item_Canje_Fact_Letras);

        item_Gener_LetrasVarias.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Gener_LetrasVarias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/letras_varias.jpg"))); // NOI18N
        item_Gener_LetrasVarias.setText("Generación de Letras Varias");
        item_Gener_LetrasVarias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_Gener_LetrasVariasActionPerformed(evt);
            }
        });
        menu_CuentasCorrientes.add(item_Gener_LetrasVarias);

        item_Letras_Protestadas.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_Letras_Protestadas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/protestosMin.jpg"))); // NOI18N
        item_Letras_Protestadas.setText("Canje de Deudas de Cliente");
        item_Letras_Protestadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_Letras_ProtestadasActionPerformed(evt);
            }
        });
        menu_CuentasCorrientes.add(item_Letras_Protestadas);

        item_ResumCliente.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_ResumCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/resumen-cliente.png"))); // NOI18N
        item_ResumCliente.setText("Resumen General de Cliente");
        item_ResumCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_ResumClienteActionPerformed(evt);
            }
        });
        menu_CuentasCorrientes.add(item_ResumCliente);

        jMenuBar1.add(menu_CuentasCorrientes);

        menu_Inventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/inventario_quickbooks.jpg"))); // NOI18N
        menu_Inventario.setText("<html> <center>Inventario<br />Fisico</center> </html>");
        menu_Inventario.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        menu_Inventario.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                menu_InventarioMouseMoved(evt);
            }
        });

        item_creacionarchivo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_creacionarchivo.setText("Creación Archivo – Toma Inventario");
        item_creacionarchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_creacionarchivoActionPerformed(evt);
            }
        });
        menu_Inventario.add(item_creacionarchivo);

        item_reporteinventario.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_reporteinventario.setText("Reporte para toma de Inventario");
        item_reporteinventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_reporteinventarioActionPerformed(evt);
            }
        });
        menu_Inventario.add(item_reporteinventario);

        item_DigitacionInvent.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_DigitacionInvent.setText("Digitación de Inventario");
        item_DigitacionInvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_DigitacionInventActionPerformed(evt);
            }
        });
        menu_Inventario.add(item_DigitacionInvent);

        item_DiferenciaInvent.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_DiferenciaInvent.setText("Diferencias de Inventario");
        item_DiferenciaInvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_DiferenciaInventActionPerformed(evt);
            }
        });
        menu_Inventario.add(item_DiferenciaInvent);

        item_cierreinventario.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        item_cierreinventario.setText("Cierre de Inventario");
        item_cierreinventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                item_cierreinventarioActionPerformed(evt);
            }
        });
        menu_Inventario.add(item_cierreinventario);

        jMenuBar1.add(menu_Inventario);

        jMenu3.setText("          " + obtenerNombre() + "          ");
        jMenu3.setFont(new java.awt.Font("Cambria", 1, 36)); // NOI18N
        jMenuBar1.add(jMenu3);

        boton_Salir_Sistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/liberacion-de-la-puerta-icono-9156-48.png"))); // NOI18N
        boton_Salir_Sistema.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boton_Salir_SistemaMouseClicked(evt);
            }
        });
        jMenuBar1.add(boton_Salir_Sistema);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String obtenerNombre() {
        Servicio_Control sc = new Servicio_Control();
        return sc.listar_control().get(0).getNombrempresa();
    }

    public String mostrarAccesos_segunRol(int rol) {

        switch (rol) {
            case 1:
                return "dd";

            default:
                return null;

        }

    }

    public void validarEntradasegunRol() {
        FREP001.setName("FREP001");
        System.out.println("botoncito es :" + FREP001.getName());

        if (rolElegido.equals("Facturador")) {
            menu_Tablas.setVisible(false);
            FREP001.setEnabled(false);
            btnRepuestos.setEnabled(false);
            item_CodigosEquipos.setEnabled(false);
            botonLineas.setEnabled(false);
            item_Operaciones.setEnabled(false);
            item_Importadores.setEnabled(false);
            item_Bancos.setEnabled(false);
            item_Estratificacion.setEnabled(false);
            item_TipoCambio.setEnabled(false);
            item_Control.setEnabled(false);
            item_Sistema.setEnabled(false);
            item_Usuario.setEnabled(false);
            item_Programas.setEnabled(false);
            item_Roles.setEnabled(false);
            item_Accesos.setEnabled(false);
            item_CuentasCobrar_Facturas.setEnabled(false);
            item_CuentasCobrar_Letras.setEnabled(false);
            item_CuentasCobrar_Almacen.setEnabled(false);
            item_Canje_Fact_Letras.setEnabled(false);
            item_Gener_LetrasVarias.setEnabled(false);
            item_ResumCliente.setEnabled(false);
            item_creacionarchivo.setEnabled(false);
            item_reporteinventario.setEnabled(false);
            item_DigitacionInvent.setEnabled(false);
            item_DiferenciaInvent.setEnabled(false);
            item_cierreinventario.setEnabled(false);

        }

        if (rolElegido.equals("Almacenero")) {
            FREP001.setEnabled(false);
            btnRepuestos.setEnabled(false);
            item_Clientes.setEnabled(false);
            btnClientes.setEnabled(false);
            item_CodigosEquipos.setEnabled(false);
            botonLineas.setEnabled(false);
            item_Operaciones.setEnabled(false);
            item_Transportistas.setEnabled(false);
            item_Vendedores.setEnabled(false);
            botonVendedores.setEnabled(false);
            item_Importadores.setEnabled(false);
            item_Paquetes.setEnabled(false);
            item_Bancos.setEnabled(false);
            item_Estratificacion.setEnabled(false);
            item_TipoCambio.setEnabled(false);
            item_Control.setEnabled(false);
            item_Sistema.setEnabled(false);
            item_Usuario.setEnabled(false);
            item_Programas.setEnabled(false);
            item_Roles.setEnabled(false);
            item_Accesos.setEnabled(false);
            item_facturacion.setEnabled(false);
            item_emision_notas.setEnabled(false);
            item_ReImpresionFBN.setEnabled(false);
            item_guias_remision.setEnabled(false);
            item_Anulacion_Documentos.setEnabled(false);
            item_ingressalidas.setEnabled(false);
            item_modificaAnul_Salidas.setEnabled(false);
            item_estrat_repuestos.setEnabled(false);
            item_emision_planillas.setEnabled(false);
            item_comisiones.setEnabled(false);
            item_inventarioAl.setEnabled(false);
            item_listaprecios.setEnabled(false);            
            item_CuentasCobrar_Facturas.setEnabled(false);
            item_CuentasCobrar_Letras.setEnabled(false);
            item_CuentasCobrar_Almacen.setEnabled(false);
            item_Canje_Fact_Letras.setEnabled(false);
            item_Gener_LetrasVarias.setEnabled(false);
            item_ResumCliente.setEnabled(false);
            item_creacionarchivo.setEnabled(false);
            item_reporteinventario.setEnabled(false);
            item_DigitacionInvent.setEnabled(false);
            item_DiferenciaInvent.setEnabled(false);
            item_cierreinventario.setEnabled(false);

        }

        if (rolElegido.equals("Finanzas")) {
            FREP001.setEnabled(false);
            btnRepuestos.setEnabled(false);
            item_Clientes.setEnabled(false);
            btnClientes.setEnabled(false);
            item_CodigosEquipos.setEnabled(false);
            botonLineas.setEnabled(false);
            item_Operaciones.setEnabled(false);
            item_Transportistas.setEnabled(false);
            item_Vendedores.setEnabled(false);
            botonVendedores.setEnabled(false);
            item_Importadores.setEnabled(false);
            item_Paquetes.setEnabled(false);
            item_Estratificacion.setEnabled(false);
            item_TipoCambio.setEnabled(false);
            item_Control.setEnabled(false);
            item_Sistema.setEnabled(false);
            item_Usuario.setEnabled(false);
            item_Programas.setEnabled(false);
            item_Roles.setEnabled(false);
            item_Accesos.setEnabled(false);
            item_facturacion.setEnabled(false);
            item_emision_notas.setEnabled(false);
            item_ReImpresionFBN.setEnabled(false);
            item_guias_remision.setEnabled(false);
            item_Anulacion_Documentos.setEnabled(false);
            item_ingressalidas.setEnabled(false);
            item_modificaAnul_Salidas.setEnabled(false);
            item_estrat_repuestos.setEnabled(false);
            item_ConsultaMaestroRep.setEnabled(false);
            item_emision_planillas.setEnabled(false);
            item_comisiones.setEnabled(false);
            item_HistorialMovimiento.setEnabled(false);
            item_inventarioAl.setEnabled(false);
            item_listaprecios.setEnabled(false);
            item_consultacli.setEnabled(false);
            item_Rep_Estratificacion.setEnabled(false);            
            item_StockMin.setEnabled(false);
            item_creacionarchivo.setEnabled(false);
            item_reporteinventario.setEnabled(false);
            item_DigitacionInvent.setEnabled(false);
            item_DiferenciaInvent.setEnabled(false);
            item_cierreinventario.setEnabled(false);

        }

        if (rolElegido.equals("CLIENTE WEB")) {
            FREP001.setEnabled(false);
            btnRepuestos.setEnabled(false);
            item_Clientes.setEnabled(false);
            btnClientes.setEnabled(false);
            item_CodigosEquipos.setEnabled(false);
            botonLineas.setEnabled(false);
            item_Operaciones.setEnabled(false);
            item_Transportistas.setEnabled(false);
            item_Vendedores.setEnabled(false);
            botonVendedores.setEnabled(false);
            item_Importadores.setEnabled(false);
            item_Paquetes.setEnabled(false);
            item_Bancos.setEnabled(false);
            item_Estratificacion.setEnabled(false);
            item_TipoCambio.setEnabled(false);
            item_Control.setEnabled(false);
            item_Sistema.setEnabled(false);
            item_Usuario.setEnabled(false);
            item_Programas.setEnabled(false);
            item_Roles.setEnabled(false);
            item_Accesos.setEnabled(false);
            item_facturacion.setEnabled(false);
            item_emision_notas.setEnabled(false);
            item_ReImpresionFBN.setEnabled(false);
            item_guias_remision.setEnabled(false);
            item_Anulacion_Documentos.setEnabled(false);
            item_ingressalidas.setEnabled(false);
            item_modificaAnul_Salidas.setEnabled(false);
            item_estrat_repuestos.setEnabled(false);
            item_ConsultaMaestroRep.setEnabled(false);
            item_emision_planillas.setEnabled(false);
            item_comisiones.setEnabled(false);
            item_HistorialMovimiento.setEnabled(false);
            item_inventarioAl.setEnabled(false);
            item_listaprecios.setEnabled(false);
            item_consultacli.setEnabled(false);
            item_Rep_Estratificacion.setEnabled(false);            
            item_StockMin.setEnabled(false);
            item_CuentasCobrar_Facturas.setEnabled(false);
            item_CuentasCobrar_Letras.setEnabled(false);
            item_CuentasCobrar_Almacen.setEnabled(false);
            item_Canje_Fact_Letras.setEnabled(false);
            item_Gener_LetrasVarias.setEnabled(false);
            item_ResumCliente.setEnabled(false);
            item_creacionarchivo.setEnabled(false);
            item_reporteinventario.setEnabled(false);
            item_DigitacionInvent.setEnabled(false);
            item_DiferenciaInvent.setEnabled(false);
            item_cierreinventario.setEnabled(false);
        }
    }

    public void iniciarTipoCambio() {

        if (existetipocambio() == true) {
            double valorcomp = Tipocambio().get(0).getValorcompra();
            double valorvent = Tipocambio().get(0).getValorventa();
            txtValorCompra.setText(String.valueOf(valorcomp));
            txtValorVenta.setText(String.valueOf(valorvent));
        }
        if (existetipocambio() == false) {
            validar();
        }
    }

    public ArrayList<Tipocambio> Tipocambio() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Tipocambio where fecha = '" + fechasistema() + "' and valorcompra!=null and valorventa!=null");
        ArrayList lista = (ArrayList) q.list();
        session.close();
        return lista;

    }

    public boolean existetipocambio() {
        int tamaño = Tipocambio().size();
        if (tamaño != 0) {
            return true;
        } else {
            return false;
        }
    }

    public String fechasistema() {
        Date ahora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(ahora);

    }

    private void item_emision_notasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_emision_notasActionPerformed
        final FREP020 emision = new FREP020(new UsuarioDAO().Obtener_Objeto_por_nombre(nombreUsuario));
        emision.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelEmision_Notas.removeAll();
                emision.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel p = emision.panelTipoNota;
        p.setLocation(0, 0);
        panelEmision_Notas.add(p);
        panelEmision_Notas.updateUI();
        cambiarPanelesCambiante("scrollEmision_Notas");
    }//GEN-LAST:event_item_emision_notasActionPerformed

    private void item_Anulacion_DocumentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_Anulacion_DocumentosActionPerformed
        final FREP023 anulacion = new FREP023(new UsuarioDAO().Obtener_Objeto_por_nombre(nombreUsuario));
        anulacion.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelAnulacion_Documentos.removeAll();
                anulacion.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel p = anulacion.panelAnulacion;
        p.setLocation(0, 0);
        panelAnulacion_Documentos.add(p);
        panelAnulacion_Documentos.updateUI();
        cambiarPanelesCambiante("scrollAnulacion_Documentos");

    }//GEN-LAST:event_item_Anulacion_DocumentosActionPerformed

    private void item_ReImpresionFBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ReImpresionFBNActionPerformed
        final FREP021 reip = new FREP021();
        reip.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelReImpresion.removeAll();
                reip.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel p = reip.panelReImpresion;
        p.setLocation(0, 0);
        panelReImpresion.add(p);
        panelReImpresion.updateUI();
        cambiarPanelesCambiante("scrollReImpresion");
    }//GEN-LAST:event_item_ReImpresionFBNActionPerformed

    private void item_StockMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_StockMinActionPerformed
        final FREP041 min = new FREP041();
        min.bt_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelStocks_minimos.removeAll();
                min.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel p = min.panelStockMinimo;
        p.setLocation(0, 0);
        panelStocks_minimos.add(p);
        panelStocks_minimos.updateUI();
        cambiarPanelesCambiante("scrollStocks_minimos");
    }//GEN-LAST:event_item_StockMinActionPerformed

    private void item_consultacliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_consultacliActionPerformed
        final FREP038 consult = new FREP038(new UsuarioDAO().Obtener_Objeto_por_nombre(nombreUsuario));
        consult.bt_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelConsulta_clientes.removeAll();
                consult.dispose();
                panelCentral.updateUI();
            }
        });

        JPanel p = consult.panelConCli;
        p.setLocation(0, 0);
        panelConsulta_clientes.add(p);
        panelConsulta_clientes.updateUI();
        cambiarPanelesCambiante("scrollConsulta_clientes");
    }//GEN-LAST:event_item_consultacliActionPerformed

    private void item_Rep_EstratificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_Rep_EstratificacionActionPerformed
        final FREP039 crep = new FREP039();
        crep.bt_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelRepuestos_estratificacion.removeAll();
                crep.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel p = crep.panelRepEstra;
        p.setLocation(0, 0);
        panelRepuestos_estratificacion.add(p);
        panelRepuestos_estratificacion.updateUI();
        cambiarPanelesCambiante("scrollRepuestos_estratificacion");
    }//GEN-LAST:event_item_Rep_EstratificacionActionPerformed

    private void item_creacionarchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_creacionarchivoActionPerformed
        final FREP048 crea = new FREP048();
        crea.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSet.removeAll();
                crea.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel p = crea.panelCrearArchivo;
        p.setLocation(0, 0);
        panelSet.add(p);
        panelSet.updateUI();
        cambiarPanelesCambiante("scrollInicio");
    }//GEN-LAST:event_item_creacionarchivoActionPerformed

    private void item_facturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_facturacionActionPerformed
        final FREP019 menu = new FREP019(new UsuarioDAO().Obtener_Objeto_por_nombre(nombreUsuario), txtValorVenta.getText());
        menu.bt_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFacturacion.removeAll();
                menu.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel p = menu.panelFact;
        p.setLocation(0, 0);
        panelFacturacion.add(p);
        panelFacturacion.updateUI();
        cambiarPanelesCambiante("scrollFacturacion");
    }//GEN-LAST:event_item_facturacionActionPerformed

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed

        item_Clientes.doClick();

    }//GEN-LAST:event_btnClientesActionPerformed

    private void btnRepuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRepuestosActionPerformed
        FREP001.doClick();
    }//GEN-LAST:event_btnRepuestosActionPerformed

    private void botonLineasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLineasActionPerformed
        item_CodigosEquipos.doClick();
    }//GEN-LAST:event_botonLineasActionPerformed

    private void botonVendedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVendedoresActionPerformed
        item_Vendedores.doClick();
    }//GEN-LAST:event_botonVendedoresActionPerformed

    private void btnClientesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClientesMouseMoved
        validarHabilitacion(btnClientes);
    }//GEN-LAST:event_btnClientesMouseMoved

    private void btnRepuestosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRepuestosMouseMoved
        validarHabilitacion(btnRepuestos);
    }//GEN-LAST:event_btnRepuestosMouseMoved

    private void botonLineasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonLineasMouseMoved
        validarHabilitacion(botonLineas);
    }//GEN-LAST:event_botonLineasMouseMoved

    private void botonDemandasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonDemandasMouseMoved
        validarHabilitacion(botonDemandas);
    }//GEN-LAST:event_botonDemandasMouseMoved

    private void botonVendedoresMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonVendedoresMouseMoved
        validarHabilitacion(botonVendedores);
    }//GEN-LAST:event_botonVendedoresMouseMoved

    private void item_ingressalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ingressalidasActionPerformed
        final FREP028 men = new FREP028();
        men.bt_cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelIngresos_Salidas.removeAll();
                men.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = men.panelIngSal;
        panel.setLocation(0, 0);
        panelIngresos_Salidas.add(panel);
        panelIngresos_Salidas.updateUI();
        cambiarPanelesCambiante("scrollIngresos_Salidas");
    }//GEN-LAST:event_item_ingressalidasActionPerformed

    private void item_estrat_repuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_estrat_repuestosActionPerformed
        final FREP030 re = new FREP030();
        re.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelEstratificar_Repuestos.removeAll();
                re.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = re.panelRealizarEstrat;
        panel.setLocation(0, 0);
        panelEstratificar_Repuestos.add(panel);
        panelEstratificar_Repuestos.updateUI();
        cambiarPanelesCambiante("scrollEstratificar_Repuestos");
    }//GEN-LAST:event_item_estrat_repuestosActionPerformed

    private void item_emision_planillasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_emision_planillasActionPerformed
        final FREP033 sp = new FREP033(nombreUsuario, rolElegido);
        sp.btn_Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelEmision_planilla.removeAll();
                sp.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = sp.panelEmisionPla;
        panel.setLocation(0, 0);
        panelEmision_planilla.add(panel);
        panelEmision_planilla.updateUI();
        cambiarPanelesCambiante("scrollEmision_planilla");
    }//GEN-LAST:event_item_emision_planillasActionPerformed

    private void item_comisionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_comisionesActionPerformed
        final FREP034 gc = new FREP034();
        gc.bt_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelComisiones.removeAll();
                gc.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = gc.panelComisiones;
        panel.setLocation(0, 0);
        panelComisiones.add(panel);
        panelComisiones.updateUI();
        cambiarPanelesCambiante("scrollComisiones");
    }//GEN-LAST:event_item_comisionesActionPerformed

    private void item_HistorialMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_HistorialMovimientoActionPerformed
        final FREP035 mh = new FREP035();
        mh.bt_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHistorial_movimiento.removeAll();
                mh.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = mh.panelHistorialMov;
        panel.setLocation(0, 0);
        panelHistorial_movimiento.add(panel);
        panelHistorial_movimiento.updateUI();
        cambiarPanelesCambiante("scrollHistorial_movimiento");
    }//GEN-LAST:event_item_HistorialMovimientoActionPerformed

    private void item_listapreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_listapreciosActionPerformed
        final FREP037 or = new FREP037();
        or.bt_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelLista_precios.removeAll();
                or.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = or.panelListaPre;
        panel.setLocation(0, 0);
        panelLista_precios.add(panel);
        panelLista_precios.updateUI();
        cambiarPanelesCambiante("scrollLista_precios");
    }//GEN-LAST:event_item_listapreciosActionPerformed

    private void item_ResumClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ResumClienteActionPerformed
        final FREP047 bc = new FREP047();
        
        panelSet.removeAll();
        bc.dispose();
        panelCentral.updateUI();
        
        bc.bt_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSet.removeAll();
                bc.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = bc.panelResumenCliente;
        panel.setLocation(0, 0);
        panelSet.add(panel);
        panelSet.updateUI();
        cambiarPanelesCambiante("scrollInicio");
    }//GEN-LAST:event_item_ResumClienteActionPerformed

    private void item_CuentasCobrar_FacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_CuentasCobrar_FacturasActionPerformed
        final FREP042 lc = new FREP042(new UsuarioDAO().Obtener_Objeto_por_nombre(nombreUsuario), rolElegido, btnPagar_CCF);
        lc.botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCuentas_cobrar_facturas.removeAll();
                lc.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = lc.panelCobrarFac;
        panel.setLocation(0, 0);
        panelCuentas_cobrar_facturas.add(panel);
        panelCuentas_cobrar_facturas.updateUI();
        cambiarPanelesCambiante("scrollCuentas_cobrar_facturas");
    }//GEN-LAST:event_item_CuentasCobrar_FacturasActionPerformed

    private void item_pedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_pedidoActionPerformed
        final FREP024 mi = new FREP024();
        mi.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPedidos_Importacion.removeAll();
                mi.dispose();
                panelCentral.updateUI();
            }
        });

        JPanel panel = mi.jpMenuImportaciones;
        panel.setLocation(0, 0);
        panelPedidos_Importacion.add(panel);
        panelPedidos_Importacion.updateUI();
        cambiarPanelesCambiante("scrollPedidos_Importacion");
    }//GEN-LAST:event_item_pedidoActionPerformed

    private void item_ConsultaMaestroRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ConsultaMaestroRepActionPerformed
        final FREP032 lc = new FREP032();
        lc.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelConsulta_maestro_repuestos.removeAll();
                lc.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = lc.panelConsultaRep;
        panel.setLocation(0, 0);
        panelConsulta_maestro_repuestos.add(panel);
        panelConsulta_maestro_repuestos.updateUI();
        cambiarPanelesCambiante("scrollConsulta_maestro_repuestos");
    }//GEN-LAST:event_item_ConsultaMaestroRepActionPerformed

    private void item_reporteinventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_reporteinventarioActionPerformed
        final FREP049 crea = new FREP049();
        crea.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSet.removeAll();
                crea.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel p = crea.panelTomaInventario;
        p.setLocation(0, 0);
        panelSet.add(p);
        panelSet.updateUI();
        cambiarPanelesCambiante("scrollInicio");
    }//GEN-LAST:event_item_reporteinventarioActionPerformed

    private void item_CuentasCobrar_LetrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_CuentasCobrar_LetrasActionPerformed
        final FREP043 lc = new FREP043(new UsuarioDAO().Obtener_Objeto_por_nombre(nombreUsuario), rolElegido, btnPagar_CCL);
        lc.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCuentas_cobrar_letras.removeAll();
                lc.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = lc.panelCCLetras;
        panel.setLocation(0, 0);
        panelCuentas_cobrar_letras.add(panel);
        panelCuentas_cobrar_letras.updateUI();
        cambiarPanelesCambiante("scrollCuentas_cobrar_letras");
    }//GEN-LAST:event_item_CuentasCobrar_LetrasActionPerformed

    private void item_CuentasCobrar_AlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_CuentasCobrar_AlmacenActionPerformed
        final FREP070 lc = new FREP070(new UsuarioDAO().Obtener_Objeto_por_nombre(nombreUsuario), rolElegido, btnPagar_CCF);
        lc.botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCuentas_cobrar_salidas.removeAll();
                lc.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = lc.panelCobrarSal;
        panel.setLocation(0, 0);
        panelCuentas_cobrar_salidas.add(panel);
        panelCuentas_cobrar_salidas.updateUI();
        cambiarPanelesCambiante("scrollCuentas_cobrar_salidas");
//        final FREP042 lc = new FREP042(new UsuarioDAO().Obtener_Objeto_por_nombre(nombreUsuario));
//        lc.botonSalir.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                panelSet.removeAll();
//                lc.dispose();
//                panelCentral.updateUI();
//            }
//        });
//        JPanel panel = lc.panelCobrarFac;
//        panel.setLocation(0, 0);
//        panelSet.add(panel);
//        panelSet.updateUI();
//        cambiarPanelesCambiante("scrollInicio");
    }//GEN-LAST:event_item_CuentasCobrar_AlmacenActionPerformed


    private void item_Canje_Fact_LetrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_Canje_Fact_LetrasActionPerformed
        final FREP045 bc = new FREP045();
        
        panelSet.removeAll();
        bc.dispose();
        panelCentral.updateUI();
        
        bc.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSet.removeAll();
                bc.dispose();
                panelCentral.updateUI();
            }
        });

        JPanel panel = bc.panelCanjexLe;
        panel.setLocation(0, 0);
        panelSet.add(panel);
        panelSet.updateUI();
        cambiarPanelesCambiante("scrollInicio");
    }//GEN-LAST:event_item_Canje_Fact_LetrasActionPerformed

    private void item_Gener_LetrasVariasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_Gener_LetrasVariasActionPerformed
        final FREP046 lc = new FREP046();
        
        panelSet.removeAll();
        lc.dispose();
        panelCentral.updateUI();
        
        lc.btn_Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSet.removeAll();
                lc.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = lc.panelGenerarLetrasV;
        panel.setLocation(0, 0);
        panelSet.add(panel);
        panelSet.updateUI();
        cambiarPanelesCambiante("scrollInicio");
    }//GEN-LAST:event_item_Gener_LetrasVariasActionPerformed

    private void item_DigitacionInventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_DigitacionInventActionPerformed
        final FREP050 lc = new FREP050();
        lc.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSet.removeAll();
                lc.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = lc.panelDigitacionInventario;
        panel.setLocation(0, 0);
        panelSet.add(panel);
        panelSet.updateUI();
        cambiarPanelesCambiante("scrollInicio");
    }//GEN-LAST:event_item_DigitacionInventActionPerformed

    private void item_inventarioAlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_inventarioAlActionPerformed
        final FREP036 iv = new FREP036();
        iv.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelInventario_almacen.removeAll();
                iv.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = iv.panelInventario;
        panel.setLocation(0, 0);
        panelInventario_almacen.add(panel);
        panelInventario_almacen.updateUI();
        cambiarPanelesCambiante("scrollInventario_almacen");
    }//GEN-LAST:event_item_inventarioAlActionPerformed

    private void item_DiferenciaInventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_DiferenciaInventActionPerformed
        final FREP051 crea = new FREP051();
        crea.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSet.removeAll();
                crea.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel p = crea.panelDiferenciasInvent;
        crea.setLocation(0, 0);
        panelSet.add(p);
        panelSet.updateUI();
        cambiarPanelesCambiante("scrollInicio");
    }//GEN-LAST:event_item_DiferenciaInventActionPerformed

    private void item_modificaAnul_SalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_modificaAnul_SalidasActionPerformed
        final FREP029 crea = new FREP029();
        crea.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelModifica_Anula_Almacen.removeAll();
                crea.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel p = crea.panelModifAnulSV;
        p.setLocation(0, 0);
        panelModifica_Anula_Almacen.add(p);
        panelModifica_Anula_Almacen.updateUI();
        cambiarPanelesCambiante("scrollModifica_Anula_Almacen");
    }//GEN-LAST:event_item_modificaAnul_SalidasActionPerformed

    private void item_cierreinventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_cierreinventarioActionPerformed
        final FREP052 crea = new FREP052();
        crea.btn_Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSet.removeAll();
                crea.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel p = crea.panelCierreInventario;
        p.setLocation(0, 0);
        panelSet.add(p);
        panelSet.updateUI();
        cambiarPanelesCambiante("scrollInicio");
    }//GEN-LAST:event_item_cierreinventarioActionPerformed

    private void botonDemandasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDemandasActionPerformed
        item_consultaDem.doClick();
    }//GEN-LAST:event_botonDemandasActionPerformed

    private void item_consultaDemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_consultaDemActionPerformed
        final FREP027 m = new FREP027();
        m.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelConsulta_Demanda.removeAll();
                m.dispose();
                panelCentral.updateUI();
            }
        });

        JPanel panel = m.panelMaestro;
        panel.setLocation(0, 0);
        panelConsulta_Demanda.add(panel);
        panelConsulta_Demanda.updateUI();
        cambiarPanelesCambiante("scrollConsulta_Demanda");
    }//GEN-LAST:event_item_consultaDemActionPerformed

    private void item_impPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_impPedidoActionPerformed
        final FREP025 im = new FREP025();
        im.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelImpresion_Pedido.removeAll();
                im.dispose();
                panelCentral.updateUI();
            }
        });

        JPanel panel = im.panelPrintImportaciones;
        panel.setLocation(0, 0);
        panelImpresion_Pedido.add(panel);
        panelImpresion_Pedido.updateUI();
        cambiarPanelesCambiante("scrollImpresion_Pedido");
    }//GEN-LAST:event_item_impPedidoActionPerformed

    private void item_valoracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_valoracionActionPerformed
        final FREP026 eop = new FREP026();
        eop.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelValoracion_Pedido.removeAll();
                eop.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = eop.panelPedidoAValorizar;
        panel.setLocation(0, 0);
        panelValoracion_Pedido.add(panel);
        panelValoracion_Pedido.updateUI();
        cambiarPanelesCambiante("scrollValoracion_Pedido");
    }//GEN-LAST:event_item_valoracionActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        final FREP031 eop = new FREP031();
        eop.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCambioPrecios.removeAll(); // panel del JFrame externo (contenedor padre)
                eop.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = eop.panelActualizarPrecios; // panel del JFrame interno (contenido hijo)
        panel.setLocation(0, 0);
        panelCambioPrecios.add(panel);
        panelCambioPrecios.updateUI();
        cambiarPanelesCambiante("scrollCambio_Precios"); // scroll del contenedor padre
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void item_guias_remisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_guias_remisionActionPerformed
//        IU_Facturacion eop = new IU_Facturacion(txtValorVenta.getText(), GENERAR_GR);
//        f.setUsuario(usuario);
//        eop.setLocationRelativeTo(null);
//        eop.setVisible(true);
//        dispose();        
        final IU_Facturacion eop = new IU_Facturacion(txtValorVenta.getText(), GENERAR_GR, panelCentral, this,
                                                      new UsuarioDAO().Obtener_Objeto_por_nombre(nombreUsuario));
//        f.setUsuario(usuario);
        eop.bt_cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelGenerarGR.removeAll(); // panel del JFrame externo (contenedor padre)
                eop.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = eop.panelGenerarGR;
        panel.setLocation(0, 0);
        panelGenerarGR.add(panel);
        panelGenerarGR.updateUI();
        cambiarPanelesCambiante("scrollGenerarGR"); // scroll del contenedor padre
    }//GEN-LAST:event_item_guias_remisionActionPerformed
    
    public void limpiarFormGRFactFict(IU_Facturacion eop) {
        panelGenerarGR.removeAll(); // panel del JFrame externo (contenedor padre)
        eop.dispose();
        panelCentral.updateUI();
    }
    
    public void iniciar(IU_Facturacion eop) {
        JPanel panel = eop.panelGenerarGR;
        panel.setLocation(0, 0);
        panelGenerarGR.add(panel);
        panelGenerarGR.updateUI();
        cambiarPanelesCambiante("scrollGenerarGR");
    }
    
    private void menu_FacturacionProformaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_FacturacionProformaMouseMoved
        if (existetipocambio() == true) {
            menu_FacturacionProforma.setEnabled(true);
        } else {
            menu_FacturacionProforma.setEnabled(false);
        }
    }//GEN-LAST:event_menu_FacturacionProformaMouseMoved

    private void menu_ImportacionesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_ImportacionesMouseMoved
        if (existetipocambio() == true) {
            menu_Importaciones.setEnabled(true);
        } else {
            menu_Importaciones.setEnabled(false);
        }
    }//GEN-LAST:event_menu_ImportacionesMouseMoved

    private void menu_MovimientosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_MovimientosMouseMoved
        if (existetipocambio() == true) {
            menu_Movimientos.setEnabled(true);
        } else {
            menu_Movimientos.setEnabled(false);
        }
    }//GEN-LAST:event_menu_MovimientosMouseMoved

    private void menu_ConsultasReportesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_ConsultasReportesMouseMoved
        if (existetipocambio() == true) {
            menu_ConsultasReportes.setEnabled(true);
        } else {
            menu_ConsultasReportes.setEnabled(false);
        }
    }//GEN-LAST:event_menu_ConsultasReportesMouseMoved

    private void menu_CuentasCorrientesMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_CuentasCorrientesMouseMoved
        if (existetipocambio() == true) {
            menu_CuentasCorrientes.setEnabled(true);
        } else {
            menu_CuentasCorrientes.setEnabled(false);
        }
    }//GEN-LAST:event_menu_CuentasCorrientesMouseMoved

    private void menu_InventarioMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_InventarioMouseMoved
        if (existetipocambio() == true) {
            menu_Inventario.setEnabled(true);
        } else {
            menu_Inventario.setEnabled(false);
        }
    }//GEN-LAST:event_menu_InventarioMouseMoved

    private void boton_Salir_SistemaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton_Salir_SistemaMouseClicked
        int opcion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea salir del sistema", "Salida del Sistema", 2);
        if (opcion == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_boton_Salir_SistemaMouseClicked

    private void item_AccesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_AccesosActionPerformed
        final FREP018 ac = new FREP018();
        ac.bt_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelAccesos.removeAll();
                ac.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel p = ac.panelAcceso;
        p.setLocation(0, 0);
        panelAccesos.add(p);
        panelAccesos.updateUI();
        cambiarPanelesCambiante("scrollAccesos");
    }//GEN-LAST:event_item_AccesosActionPerformed

    private void item_RolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_RolesActionPerformed
        final FREP017 roles = new FREP017();
        roles.bt_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelRoles.removeAll();
                roles.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = roles.panelRol;
        panel.setLocation(0, 0);
        panelRoles.add(panel);
        panelRoles.updateUI();
        cambiarPanelesCambiante("scrollRoles");
    }//GEN-LAST:event_item_RolesActionPerformed

    private void item_ProgramasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ProgramasActionPerformed
        final FREP016 programas = new FREP016();
        programas.btn_Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelProgramas.removeAll();
                programas.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = programas.panelProgramas;
        panel.setLocation(0, 0);
        panelProgramas.add(panel);
        panelProgramas.updateUI();
        cambiarPanelesCambiante("scrollProgramas");
    }//GEN-LAST:event_item_ProgramasActionPerformed

    private void item_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_UsuarioActionPerformed
        final FREP015 usuarios = new FREP015();
        usuarios.bt_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelUsuario.removeAll();
                usuarios.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = usuarios.panelUsuarios;
        panel.setLocation(0, 0);
        panelUsuario.add(panel);
        panelUsuario.updateUI();
        cambiarPanelesCambiante("scrollUsuario");
    }//GEN-LAST:event_item_UsuarioActionPerformed

    private void item_SistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_SistemaActionPerformed
        final FREP014 sis = new FREP014();
        sis.botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSistema.removeAll();
                sis.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel p = sis.panelSistema;
        p.setLocation(0, 0);
        panelSistema.add(p);
        panelSistema.updateUI();
        cambiarPanelesCambiante("scrollSistema");
    }//GEN-LAST:event_item_SistemaActionPerformed

    private void item_ControlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ControlActionPerformed
        final FREP013 control = new FREP013();
        control.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelControl.removeAll();
                control.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = control.panelControlSistema;
        panel.setLocation(0, 0);
        panelControl.add(panel);
        panelControl.updateUI();
        cambiarPanelesCambiante("scrollControl");
    }//GEN-LAST:event_item_ControlActionPerformed

    private void item_TipoCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_TipoCambioActionPerformed
        final FREP011 t = new FREP011(null);
        t.btn_Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelTipoCambio.removeAll();
                t.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = t.panelTipoCambio;
        panel.setLocation(0, 0);
        panelTipoCambio.add(panel);
        panelTipoCambio.updateUI();
        cambiarPanelesCambiante("scrollTipoCambio");
    }//GEN-LAST:event_item_TipoCambioActionPerformed

    private void item_EstratificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_EstratificacionActionPerformed
        final FREP010 estratificacion = new FREP010();
        estratificacion.bt_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelEstratificacion.removeAll();
                estratificacion.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = estratificacion.panelEstratificacion;
        panel.setLocation(0, 0);
        panelEstratificacion.add(panel);
        panelEstratificacion.updateUI();
        cambiarPanelesCambiante("scrollEstratificacion");
    }//GEN-LAST:event_item_EstratificacionActionPerformed

    private void item_BancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_BancosActionPerformed
        final FREP009 bancos = new FREP009();
        bancos.bt_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelBanco.removeAll();
                bancos.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = bancos.panelBancos;
        panel.setLocation(0, 0);
        panelBanco.add(panel);
        panelBanco.updateUI();
        cambiarPanelesCambiante("scrollBanco");
    }//GEN-LAST:event_item_BancosActionPerformed

    private void item_PaquetesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_PaquetesActionPerformed
        final FREP008 paquetes = new FREP008();
        paquetes.btn_Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPaquetes.removeAll();
                paquetes.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = paquetes.panelPaquetes;
        panel.setLocation(0, 0);
        panelPaquetes.add(panel);
        panelPaquetes.updateUI();
        cambiarPanelesCambiante("scrollPaquetes");
    }//GEN-LAST:event_item_PaquetesActionPerformed

    private void item_ImportadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ImportadoresActionPerformed
        final FREP007 importadores = new FREP007();
        importadores.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelImportadores.removeAll();
                importadores.dispose();
                panelCentral.updateUI();
            }
        });

        JPanel panel = importadores.panelImportadores;
        panel.setLocation(0, 0);
        panelImportadores.add(panel);
        panelImportadores.updateUI();
        cambiarPanelesCambiante("scrollImportadores");
    }//GEN-LAST:event_item_ImportadoresActionPerformed

    private void item_VendedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_VendedoresActionPerformed
        final FREP006 vendedores = new FREP006();
        vendedores.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelVendedores.removeAll();
                vendedores.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = vendedores.panelVendedores;
        panel.setLocation(0, 0);
        panelVendedores.add(panel);
        panelVendedores.updateUI();
        cambiarPanelesCambiante("scrollVendedores");
    }//GEN-LAST:event_item_VendedoresActionPerformed

    private void item_TransportistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_TransportistasActionPerformed
        final FREP005 transportistas = new FREP005();
        transportistas.bt_salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelTransportistas.removeAll();
                transportistas.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = transportistas.panelTransportista;
        panel.setLocation(0, 0);
        panelTransportistas.add(panel);
        panelTransportistas.updateUI();
        cambiarPanelesCambiante("scrollTransportistas");
    }//GEN-LAST:event_item_TransportistasActionPerformed

    private void item_OperacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_OperacionesActionPerformed
        final FREP004 operaciones = new FREP004();
        operaciones.btn_Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelOperaciones.removeAll();
                operaciones.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = operaciones.panelOperaciones;
        panel.setLocation(0, 0);
        panelOperaciones.add(panel);
        panelOperaciones.updateUI();
        cambiarPanelesCambiante("scrollOperaciones");
    }//GEN-LAST:event_item_OperacionesActionPerformed

    private void item_CodigosEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_CodigosEquiposActionPerformed
        final FREP003 equipos = new FREP003();
        equipos.btnsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCodigoEquipos.removeAll();
                equipos.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = equipos.panelCodigoEquipos;
        panel.setLocation(0, 0);
        panelCodigoEquipos.add(panel);
        panelCodigoEquipos.updateUI();
        cambiarPanelesCambiante("scrollCodigoEquipos");
    }//GEN-LAST:event_item_CodigosEquiposActionPerformed

    private void item_ClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ClientesActionPerformed

        final FREP002 clientes = new FREP002();
        clientes.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelClientes.removeAll();
                clientes.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = clientes.panelClientes;
        panel.setLocation(0, 0);
        panelClientes.add(panel);
        panelClientes.updateUI();
        cambiarPanelesCambiante("scrollClientes");
    }//GEN-LAST:event_item_ClientesActionPerformed

    private void FREP001ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FREP001ActionPerformed
        final FREP001 maestro = new FREP001(nombreUsuario, rolElegido);
//        final FREP001 usuario = new FREP001(new UsuarioDAO().Obtener_Objeto_por_nombre(nombreUsuario));
        maestro.boton_Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelMaestroRepuestos.removeAll();
                maestro.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel p = maestro.panelMaestros;
        p.setLocation(0, 0);
        panelMaestroRepuestos.add(p);
        panelMaestroRepuestos.updateUI();
        cambiarPanelesCambiante("scrollMaestroRepuestos");
    }//GEN-LAST:event_FREP001ActionPerformed

    private void menu_TablasMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_TablasMouseMoved
        if (existetipocambio() == true) {
            menu_Tablas.setEnabled(true);
        } else {
            menu_Tablas.setEnabled(false);
        }
    }//GEN-LAST:event_menu_TablasMouseMoved

    private void item_Letras_ProtestadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_Letras_ProtestadasActionPerformed
        final FREP0100 bc = new FREP0100();
        panelSet.removeAll();
        bc.dispose();
        panelCentral.updateUI();
        
        bc.btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSet.removeAll();
                bc.dispose();
                panelCentral.updateUI();
            }
        });

        JPanel panel = bc.panelCanjexLeProt;
        panel.setLocation(0, 0);
        panelSet.add(panel);
        panelSet.updateUI();
        cambiarPanelesCambiante("scrollInicio");
    }//GEN-LAST:event_item_Letras_ProtestadasActionPerformed

    private void item_factelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_factelectActionPerformed
        final FREP054 sp = new FREP054();
        sp.btn_Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFactura_electronica.removeAll();
                sp.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = sp.panSelectFechas;
        panel.setLocation(0, 0);
        panelFactura_electronica.add(panel);
        panelFactura_electronica.updateUI();
        cambiarPanelesCambiante("scrollFactura_electronica");
    }//GEN-LAST:event_item_factelectActionPerformed

    private void item_flujo_comparativoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_flujo_comparativoActionPerformed
        final FREP055 sp = new FREP055();
        sp.btn_Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFlujo_comparativo.removeAll();
                sp.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = sp.panelFlujoCom;
        panel.setLocation(0, 0);
        panelFlujo_comparativo.add(panel);
        panelFlujo_comparativo.updateUI();
        cambiarPanelesCambiante("scrollFlujo_comparativo");
    }//GEN-LAST:event_item_flujo_comparativoActionPerformed

    private void item_ModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_ModeloActionPerformed
        // TODO add your handling code here:
        //final FREP0056 modelos = new FREP0056();
        //Reinicia la vista
        if(vista_modelos == null) {
            vista_modelos = new FREP0056();
            
            JPanel panel = vista_modelos.panelCodigoModelos;
            panel.setLocation(0, 0);
            panelModelos.add(panel);
            panelModelos.updateUI();
            
            /**panelModelos.removeAll();
            vista_modelos.dispose();
            panelModelos.updateUI();*/
            //System.out.print("hola");
        }
        
        vista_modelos.btnsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelModelos.removeAll();
                vista_modelos.dispose();
                panelModelos.updateUI();
            }
        });
        
        cambiarPanelesCambiante("scrollModelos");
                                                       
    }//GEN-LAST:event_item_ModeloActionPerformed

    private void item_MarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_MarcaActionPerformed
        // TODO add your handling code here:
        
         //Reinicia la vista si ya existe
        if(vista_marcas == null) {
            vista_marcas = new FREP0055();
            
            JPanel panel = vista_marcas.panelCodigoMarcas;
            panel.setLocation(0, 0);
            panelMarcas.add(panel);
            panelMarcas.updateUI();
            
            /*panelMarcas.removeAll();
            vista_marcas.dispose();
            panelMarcas.updateUI();*/
            //System.out.print("hola");
        }
        
        //final FREP0055 marcas = new FREP0055();
        
        vista_marcas.btnsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelMarcas.removeAll();
                vista_marcas.dispose();
                panelCentral.updateUI();
            }
        });
        
        cambiarPanelesCambiante("scrollMarcas");
    }//GEN-LAST:event_item_MarcaActionPerformed

    private void item_PuntosVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_item_PuntosVentaActionPerformed
        // TODO add your handling code here:
        final FREP0057 puntosventa = new FREP0057();
        puntosventa.btnsalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPuntos_venta.removeAll();
                puntosventa.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = puntosventa.panelPuntosVenta;
        panel.setLocation(0, 0);
        panelPuntos_venta.add(panel);
        panelPuntos_venta.updateUI();
        cambiarPanelesCambiante("scrollPuntos_venta");
    }//GEN-LAST:event_item_PuntosVentaActionPerformed

    public void validarHabilitacion(JButton boton) {

        if (existetipocambio() == true) {
            boton.setEnabled(true);

        } else {
            boton.setEnabled(false);
        }
    }

    public void cambiarPanelesCambiante(String nombre) {
        ((CardLayout) panelCentral.getLayout()).show(panelCentral, nombre);
    }

    public void validar() {
        final FREP011 cambio = new FREP011(this);
        cambio.btn_Salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelSet.removeAll();
                cambio.dispose();
                panelCentral.updateUI();
            }
        });
        JPanel panel = cambio.panelTipoCambio;
        panel.setLocation(0, 0);
        panelSet.add(panel);
        panelSet.updateUI();
        panelCentral.updateUI();
        cambiarPanelesCambiante("scrollInicio");

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JMenuItem FREP001;
    public javax.swing.JButton botonDemandas;
    public javax.swing.JButton botonLineas;
    public javax.swing.JButton botonVendedores;
    public javax.swing.JMenu boton_Salir_Sistema;
    public javax.swing.JButton btnClientes;
    public javax.swing.JButton btnRepuestos;
    public javax.swing.JMenuItem item_Accesos;
    public javax.swing.JMenuItem item_Anulacion_Documentos;
    public javax.swing.JMenuItem item_Bancos;
    public javax.swing.JMenuItem item_Canje_Fact_Letras;
    public javax.swing.JMenuItem item_Clientes;
    public javax.swing.JMenuItem item_CodigosEquipos;
    public javax.swing.JMenuItem item_ConsultaMaestroRep;
    public javax.swing.JMenuItem item_Control;
    public javax.swing.JMenuItem item_CuentasCobrar_Almacen;
    public javax.swing.JMenuItem item_CuentasCobrar_Facturas;
    public javax.swing.JMenuItem item_CuentasCobrar_Letras;
    public javax.swing.JMenuItem item_DiferenciaInvent;
    public javax.swing.JMenuItem item_DigitacionInvent;
    public javax.swing.JMenuItem item_Estratificacion;
    public javax.swing.JMenuItem item_Gener_LetrasVarias;
    public javax.swing.JMenuItem item_HistorialMovimiento;
    public javax.swing.JMenuItem item_Importadores;
    public javax.swing.JMenuItem item_Letras_Protestadas;
    public javax.swing.JMenuItem item_Marca;
    public javax.swing.JMenuItem item_Modelo;
    public javax.swing.JMenuItem item_Operaciones;
    public javax.swing.JMenuItem item_Paquetes;
    public javax.swing.JMenuItem item_Programas;
    public javax.swing.JMenuItem item_PuntosVenta;
    public javax.swing.JMenuItem item_ReImpresionFBN;
    public javax.swing.JMenuItem item_Rep_Estratificacion;
    public javax.swing.JMenuItem item_ResumCliente;
    public javax.swing.JMenuItem item_Roles;
    public javax.swing.JMenuItem item_Sistema;
    public javax.swing.JMenuItem item_StockMin;
    public javax.swing.JMenuItem item_TipoCambio;
    public javax.swing.JMenuItem item_Transportistas;
    public javax.swing.JMenuItem item_Usuario;
    public javax.swing.JMenuItem item_Vendedores;
    public javax.swing.JMenuItem item_cierreinventario;
    public javax.swing.JMenuItem item_comisiones;
    public javax.swing.JMenuItem item_consultaDem;
    public javax.swing.JMenuItem item_consultacli;
    public javax.swing.JMenuItem item_creacionarchivo;
    public javax.swing.JMenuItem item_emision_notas;
    public javax.swing.JMenuItem item_emision_planillas;
    public javax.swing.JMenuItem item_estrat_repuestos;
    public javax.swing.JMenuItem item_factelect;
    public javax.swing.JMenuItem item_facturacion;
    public javax.swing.JMenuItem item_flujo_comparativo;
    public javax.swing.JMenuItem item_guias_remision;
    public javax.swing.JMenuItem item_impPedido;
    public javax.swing.JMenuItem item_ingressalidas;
    public javax.swing.JMenuItem item_inventarioAl;
    public javax.swing.JMenuItem item_listaprecios;
    public javax.swing.JMenuItem item_modificaAnul_Salidas;
    public javax.swing.JMenuItem item_pedido;
    public javax.swing.JMenuItem item_reporteinventario;
    public javax.swing.JMenuItem item_valoracion;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenu3;
    public javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JMenuItem jMenuItem2;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JMenu menu_ConsultasReportes;
    public javax.swing.JMenu menu_CuentasCorrientes;
    public javax.swing.JMenu menu_FacturacionProforma;
    public javax.swing.JMenu menu_Importaciones;
    public javax.swing.JMenu menu_Inventario;
    public javax.swing.JMenu menu_Movimientos;
    public javax.swing.JMenu menu_Tablas;
    public javax.swing.JLabel nombreEmpresa;
    public javax.swing.JPanel panelAccesos;
    public javax.swing.JPanel panelAnulacion_Documentos;
    public javax.swing.JPanel panelBanco;
    public javax.swing.JPanel panelCambioPrecios;
    public javax.swing.JPanel panelCanje_facturas_letras;
    public javax.swing.JPanel panelCentral;
    public javax.swing.JPanel panelCierre_inventario;
    public javax.swing.JPanel panelClientes;
    public javax.swing.JPanel panelCodigoEquipos;
    public javax.swing.JPanel panelComisiones;
    public javax.swing.JPanel panelConsulta_Demanda;
    public javax.swing.JPanel panelConsulta_clientes;
    public javax.swing.JPanel panelConsulta_maestro_repuestos;
    public javax.swing.JPanel panelControl;
    public javax.swing.JPanel panelCreacion_archivo;
    public javax.swing.JPanel panelCuentas_cobrar_facturas;
    public javax.swing.JPanel panelCuentas_cobrar_letras;
    public javax.swing.JPanel panelCuentas_cobrar_salidas;
    public javax.swing.JPanel panelDiferencias_inventario;
    public javax.swing.JPanel panelDigitacion_inventario;
    public javax.swing.JPanel panelEmision_Notas;
    public javax.swing.JPanel panelEmision_planilla;
    public javax.swing.JPanel panelEstratificacion;
    public javax.swing.JPanel panelEstratificar_Repuestos;
    public javax.swing.JPanel panelFactura_electronica;
    public javax.swing.JPanel panelFacturacion;
    public javax.swing.JPanel panelFlujo_comparativo;
    public javax.swing.JPanel panelGeneracion_letras;
    public javax.swing.JPanel panelGenerarGR;
    public javax.swing.JPanel panelGuias_Remision;
    public javax.swing.JPanel panelHistorial_movimiento;
    public javax.swing.JPanel panelImportadores;
    public javax.swing.JPanel panelImpresion_Pedido;
    public javax.swing.JPanel panelInferior;
    public javax.swing.JPanel panelIngresos_Salidas;
    public javax.swing.JPanel panelInicio;
    public javax.swing.JPanel panelInventario_almacen;
    public javax.swing.JPanel panelLista_precios;
    public javax.swing.JPanel panelMaestroRepuestos;
    public javax.swing.JPanel panelMarcas;
    public javax.swing.JPanel panelModelos;
    public javax.swing.JPanel panelModifica_Anula_Almacen;
    public javax.swing.JPanel panelOperaciones;
    public javax.swing.JPanel panelPaquetes;
    public javax.swing.JPanel panelPedidos_Importacion;
    public javax.swing.JPanel panelProgramas;
    public javax.swing.JPanel panelPuntos_venta;
    public javax.swing.JPanel panelReImpresion;
    public javax.swing.JScrollPane panelReporte_inventario;
    public javax.swing.JPanel panelRepuestos_estratificacion;
    public javax.swing.JPanel panelResumen_anual_ventas;
    public javax.swing.JPanel panelResumen_cliente;
    public javax.swing.JPanel panelRoles;
    public javax.swing.JPanel panelSet;
    public javax.swing.JPanel panelSistema;
    public javax.swing.JPanel panelStocks_minimos;
    public javax.swing.JPanel panelSuperior;
    public javax.swing.JPanel panelTipoCambio;
    public javax.swing.JPanel panelTransportistas;
    public javax.swing.JPanel panelUsuario;
    public javax.swing.JPanel panelValoracion_Pedido;
    public javax.swing.JPanel panelVendedores;
    public javax.swing.JScrollPane scrollAccesos;
    public javax.swing.JScrollPane scrollAnulacion_Documentos;
    public javax.swing.JScrollPane scrollBanco;
    public javax.swing.JScrollPane scrollCambioPrecios;
    public javax.swing.JScrollPane scrollCanje_facturas_letras;
    public javax.swing.JScrollPane scrollCierre_inventario;
    public javax.swing.JScrollPane scrollClientes;
    public javax.swing.JScrollPane scrollCodigoEquipos;
    public javax.swing.JScrollPane scrollComisiones;
    public javax.swing.JScrollPane scrollConsulta_Demanda;
    public javax.swing.JScrollPane scrollConsulta_clientes;
    public javax.swing.JScrollPane scrollControl;
    public javax.swing.JScrollPane scrollCreacion_archivo;
    public javax.swing.JScrollPane scrollCuentas_cobrar_facturas;
    public javax.swing.JScrollPane scrollCuentas_cobrar_letras;
    public javax.swing.JScrollPane scrollCuentas_cobrar_salidas;
    public javax.swing.JScrollPane scrollDiferencias_inventario;
    public javax.swing.JScrollPane scrollDigitacion_inventario;
    public javax.swing.JScrollPane scrollEmision_Notas;
    public javax.swing.JScrollPane scrollEmision_planilla;
    public javax.swing.JScrollPane scrollEstratificacion;
    public javax.swing.JScrollPane scrollEstratificarRepuestos;
    public javax.swing.JScrollPane scrollFactura_electronica;
    public javax.swing.JScrollPane scrollFacturacion;
    public javax.swing.JScrollPane scrollFlujo_comparativo;
    public javax.swing.JScrollPane scrollGeneracion_letras;
    public javax.swing.JScrollPane scrollGenerarGR;
    public javax.swing.JScrollPane scrollGuias_Remision;
    public javax.swing.JScrollPane scrollHistorial_movimiento;
    public javax.swing.JScrollPane scrollImportadores;
    public javax.swing.JScrollPane scrollImpresion_Pedido;
    public javax.swing.JScrollPane scrollIngresos_Salidas;
    public javax.swing.JScrollPane scrollInicio;
    public javax.swing.JScrollPane scrollInventario_almacen;
    public javax.swing.JScrollPane scrollLista_precios;
    public javax.swing.JScrollPane scrollMaestroRepuestos;
    public javax.swing.JScrollPane scrollMarcas;
    public javax.swing.JScrollPane scrollModelos;
    public javax.swing.JScrollPane scrollModifica_Anula_Almacen;
    public javax.swing.JScrollPane scrollOperaciones;
    public javax.swing.JScrollPane scrollPaquetes;
    public javax.swing.JScrollPane scrollPedidos_Importacion;
    public javax.swing.JScrollPane scrollProgramas;
    public javax.swing.JScrollPane scrollPuntos_venta;
    public javax.swing.JScrollPane scrollReImpresion;
    public javax.swing.JScrollPane scrollRepuestos_estratificacion;
    public javax.swing.JScrollPane scrollResumenCliente;
    public javax.swing.JScrollPane scrollResumen_anual_ventas;
    public javax.swing.JScrollPane scrollRoles;
    public javax.swing.JScrollPane scrollSistema;
    public javax.swing.JScrollPane scrollStocks_minimos;
    public javax.swing.JScrollPane scrollTipoCambio;
    public javax.swing.JScrollPane scrollTransportistas;
    public javax.swing.JScrollPane scrollUsuario;
    public javax.swing.JScrollPane scrollValoracion_Pedido;
    public javax.swing.JScrollPane scrollVendedores;
    public javax.swing.JScrollPane scrollconsulta_maestro_repuestos;
    public javax.swing.JTextField txtFecha;
    public javax.swing.JTextField txtHora;
    public javax.swing.JTextField txtNombreUsuario;
    public javax.swing.JTextField txtRolUsuario;
    public javax.swing.JTextField txtValorCompra;
    public javax.swing.JTextField txtValorVenta;
    // End of variables declaration//GEN-END:variables
}