package Entidades.Otros;

import Servicios.Constantes;
import java.io.File;

/**
 *
 * @author Administrador
 */
public final class Constante {
    
    public static final String NOMBRE_EMPRESA = "CORPORACION QF S.A.C.";
    
    public static final String DIRECCION_LISTA_PRECIOS = "Av. Arequipa N°1851 Oficina 508 Lince -  Lima – Perú\n" +
                                                         "Gerencia: 922-564-845 \n" +
                                                         "Ventas: 981-324-943 / 935-932-699 ";
    public static final String CORREO_EMPRESA = "deltapc2014@gmail.com";
    public static final String PUNTO_PARTIDA = "Av. Arequipa N°1851 Oficina 508 Lince -  Lima – Perú";
    //"JR.CANTA #332 - LA VICTORIA - LIMA"
    public static final String REPUESTO_NO_SELECCIONADO = "NINGÚN " + Constantes.OBJETO_COMERCIALIZADOR + " SELECCIONADO"; 
    public static final String TABLA_VACIA_REPUESTOS = "TABLA VACÍA, AGREGUE Y SELECCIONE ITEM";
    
    // se les aumentara a algunos 3 unidades por las coumnas Equipo, Marca Y Modelo
    public static final int COLUMNA_STOCK = 8;
    public static final int COLUMNA_CLIENTE = 16; // no se utiliza
    public static final int COLUMNA_DISPONIBILIDAD = 18;
    public static final int COLUMNA_DSCTO1 = 13;
    public static final int COLUMNA_DSCTO2 = 14;
    public static final int COLUMNA_DSCTO3 = 15;
    public static final int COLUMNA_DSCTO4 = 16;
    
//    public static final int COLUMNA_CODSEC = 3;
    public static final int COLUMNA_MODELO = 7; // Aplicacion 
    
    public static final int COLUMNA_ID_CLIENTE = 7;
    public static final String ESTADO_PROFORMA_PENDIENTE = "PENDIENTE";
    public static final int COLUMNA_CABECES = 8;
    public static final int COLUMNA_RUC = 9;
    public static final int COLUMNA_MONEDA = 10;
    public static final int COLUMNA_MONTO_TOTAL = 11;
    public static final int COLUMNA_VENDEDOR = 12;
    
    public static final String INGRESO_POR_REGULARIZACION = "Ingresos por Regularización";
    public static final String SALIDA_POR_REGULARIZACION = "Salidas por Regularización";
//    public static final String INGRESO_POR_IMPORTACION = "Ingreso por Importacion";    
    
    public static final String DOLAR = "$";
    public static final String SOL = "S/.";
    
    public static final String DOLAR_STANDARD = "US $";
    
    // Siar Desarrollo
    public static final String RUTA_IMAGENES = "C:" + File.separator + "SIAR" + File.separator + "IMAGENES" + File.separator;
    
    // Siar Consorcio
//    public static final String RUTA_IMAGENES = "D:" + File.separator + "SIAR" + File.separator + "IMAGENES" + File.separator;
    public static final int COLUMNA_ID_PAGO = 5;
    public static final int COLUMNA_RUTA_IMAGEN = 6;
    
    public static final int NUM_CARACT_DIR_TRUNCA = 45;
    public static final int FILA_INI_DET_BOL = 11;
    public static final int FILA_INI_DET_PROF = 14; // aumenta 1 por punto de venta (valor anterior: 13)
    public static final int FILA_INI_DET_FACT = 14;
//    public static final int FILA_INI_DET_GR = 19;
    public static final int FILA_INI_DET_GR = 20;
    public static final int FILA_INI_DET_NC = 14;
    public static final int FILA_INI_DET_LETRA = 1;
    public static final int FILA_INI_DET_SK = 14;
    public static final int FILA_INI_DET_SXR = 8;
    public static final int FILA_INI_DET_IXR = 8;
    public static final int FILA_INI_DET_IXCL = 9;
    public static final int FILA_INI_DET_IXA = 10;
    
    public static final int FILA_INI_DET_LETRAS = 7;
    
    public static final int A = 0;
    public static final int B = 1;
    public static final int C = 2;
    public static final int D = 3;
    public static final int EE = 4;
    public static final int F = 5;
    public static final int G = 6;
    public static final int H = 7;
    public static final int I = 8;
    public static final int J = 9;
    public static final int K = 10;
    public static final int L = 11;
    public static final int M = 12;
    public static final int N = 13;
    public static final int O = 14;
    public static final int P = 15;
    public static final int Q = 16;
    public static final int R = 17;
    public static final int S = 18;
    public static final int TT = 19;
    public static final int U = 20;
    
    public static final int MAX_REP_DET = 10;
    
    public static final int MONEDA_DOLAR_BD = 1;
    public static final int MONEDA_SOL_BD = 2;
    
    // EN BASE DE DATOS, EQUIVALENCIA DE NUERO CON TIPO DE MONEDA EN CABECES 
    public static final String SOL_BD = "1";
    public static final String DOLAR_BD = "2";
    
    // Valor Dolares, Soles (0, 1) en Combo de Facturación, para variable "opcPorDefectoComboMoneda == monedaRepuestos
    public static final int DOLAR_COMBO = 0;
    public static final int SOL_COMBO = 1;
    
    public static final int COLUMNA_ID_PROF = 0;
    
//    public static final String NAME_LOGO = "\\logoExcelProforma.png";
    public static final String NAME_LOGO = "\\logoExcelProformaDLN.png";
//    public static final String RUTA_PROGRAMAS = "\\\\LEDIS\\ExcelsProforma";
//    public static final String RUTA_LOGO_SIAR = RUTA_PROGRAMAS + "\\logoExcelProforma.png";
    
//    public static final String STOCK_MAXIMO = "999999";
    public static final String STOCK_MINIMO = "0";
    
    public static final int ORIGEN_PROFORMA = 0;
    public static final int ORIGEN_NEXT_FACTURA = 1;
    
    public static final int COL_MODELO_REPUESTOS = 3;
    
    // Truncamiento para imprimir
    public static final int MAX_CARACTERES_DESCRIPCION = 45;
    public static final int MAX_CARACTERES_VENDEDOR_FACT = 12;
    
    public static final int COLUMNA_ID_VENDEDOR = 8;
    public static final String DOLARES = "Dolares";
    public static final String SOLES = "Soles";
    
    public static final int FACTURA_NORMAL = 0;
    public static final int GENERAR_GR = 1;
    
    public static final String SISTEMA_FACT_FICTICIA = "FACTURA FICTICIA";
    public static final String SISTEMA_GR = "GUIA DE REMISION";
    
    public static final int FACTURA_BOLETA = 0;
    public static final int PROFORMA = 1;
    public static final int FACT_XGR = 2;
    public static final int GENERA_GR = 3;
    
    // Posiciones de las Columnas para el Browse de INVENTARIO VALORIZADO.
    public static final int COLUMNA_STOCK_IV = 4;
    public static final int COLUMNA_VALORIZACION = 5;
    public static final int COLUMNA_TOTAL = 6;
    public static final int COLUMNA_IDLINEA = 7;
    public static final int COLUMNA_COSTUNIT = 8;
    public static final int COLUMNA_PREVTA = 9;
    public static final String TIT_IVG = "Costo Unitario + Precio Venta";
    
    // Opciones del Menú : Movimientos -> Ingresos/Salidas Almacén
    public static final int ING_COMPRA_LOCAL = 1;
    public static final int ING_REGULARIZACION = 2;
    public static final int SAL_REGULARIZACION = 3;
    public static final int ING_DEVOLUCION = 4;
    public static final int ING_IMPORTACION = 5;    
    
    // Opciones del Menú : Consultas y Reportes -> Historial de Movimiento
    public static final int VISUALIZAR_TODO = 1;
    public static final int VISUALIZAR_POR_FECHA = 2;
    
    // Opciones de Combo para Reimprimir (Sólo Notas de Ingreso y Salida), últimas opciones del combo
    public static final String NOTA_ING_COMPRA_LOCAL = "5";
    public static final String NOTA_ING_AJUSTES = "6";
    public static final String NOTA_SALIDA = "7";
    
    // Columnas agregadas en el browse de Ingresos por Compra Local e Ing. x Ajuste(regularización)
    // no se muestran en browse, pero sí al reimprimir.
    public static final int REIMP_INGSAL_COL_CODSEC = 5;
    public static final int REIMP_INGSAL_COL_PRECLISTA = 6;
    public static final int REIMP_INGSAL_COL_MODELO = 7;
    
    // IDTRANSACCION (Tabla Sistema) 
    public static final String TIPODOC_ING_COMPRA_LOCAL = "08";
    public static final String TIPODOC_ING_REGULARIZACION = "11";
    public static final String TIPODOC_SAL_REGULARIZACION = "14";
    public static final String TIPODOC_ING_DEVOLUCION = "10";    
    public static final String TIPODOC_ING_IMPORTACION = "09";        
    
    
    // Tipo Documento en el DETALLE DE TRANSACCION de la generación de Ingreso o Salida y de la Reimpresión
    // y Tipo de transacción para la impresión y reimpresión de la Nota de Ingreso por COMPRA LOCAL
    public static final String COMPRA_LOCAL = "COMPRA LOCAL";
    public static final String INGRESO_POR_AJUSTES = "INGRESO POR AJUSTE";
    public static final String SALIDA_POR_AJUSTES = "SALIDA POR AJUSTE";
    public static final String INGRESO_POR_IMPORTACION = "Ingreso por Importacion";    
    
    // Título de Impresión y Reimpresión de Notas de Ingreso/Salida
    public static final String TIT_ICLyIR = "Ingresos al Almacén";
    public static final String TIT_SR = "Salidas del Almacén";
    
    // Opciones del combo "Documento"
    public static final int CBO_FACTURA = 0;
    public static final int CBO_BOLETA = 1;
    public static final int CBO_PROFORMA = 2;
    public static final int CBO_SALIDAS_VARIAS = 3; // SK
    
    // Proviene de realizar una:
    public static final int PROVIENE_FACTURA = 0;
    public static final int PROVIENE_PROFORMA = 1;
    public static final int PROVIENE_GR_DESPUES_DE_FACT = 2;
    public static final int PROVIENE_GR_ANTES_DE_FACT = 3;

    // Ingreso por tipo:
    public static final int ING_POR_COMPRA_LOCAL = 1;
    public static final int ING_POR_ANULACION = 2;
    
    // Exclusivo para la impresora de Giovanna (EPSON LX-300+ /II (Copiar 1))
    public static final String PRINT_LX_300 = "LX-300+";
    public static final String USUARIO_LUCY = "LUCY";
    
    // Cuenta Corriente Letras
    public static final String SELECCIONAR = "<Seleccionar>";
    public static final int OPC_TODA_CTA_CTE = 1;
    public static final int OPC_UN_CLIENTE = 2;
    public static final int OPC_SELECCIONADOS = 3;
    
    public static final int BANCO_LETRA = 11; //13
    public static final int TIPO_LETRA = 14;
    
    public static final String ABR_LI = "LI";
    public static final String LIBRE = "LI  = Libre";
    public static final String COBRANZA = "CO = Cobranza";
    public static final String DESCUENTO = "DE = Descuento";
    public static final String GARANTIA = "GA = Garantía";
    
    // Generación de Nota de Crédito y Nota de Débito
    public static final String NOTA_CREDITO = "NC";
    public static final String NOTA_DEBITO = "ND";
    
    public static final int NC_DEVOLUCION = 1;
    public static final int NC_DSCTO_ESPECIAL = 2;
    public static final int ND_MOD_UNICA = 3;
    
    public static final int COLUMNA_CANT_INI = 4;
    public static final int COLUMNA_VV_INI = 5;
    
    // Valores "opedef"(Tabla Control de BD) por defecto del Combo "Operacion" en Facturación
    public static final String BD_CONTADO = "O";
    public static final String BD_CREDITO = "R";
    public static final String BD_LETRA = "L";
    
    public static final String CBO_CREDITO = "Credito";
    public static final String CBO_CONTADO = "Contado";
    public static final String CBO_LETRA = "Letras";
    
    public static final String TIPO_IU_CANJE_LETRAS = "L"; // Sólo Letras Protestadas
    public static final String TIPO_IU_CANJE_DEUDAS = "D"; // Deudas de 1 Cliente (Facturas, ND, NC, Letras)
    
    public static final String DOLAR_US = "US$";
    public static final String CERO = "0.0";
    
    public static final int COLUMNA_ESTADO_TXT = 13;
    public static final String VALIDO = "valido";
    public static final int MAX_ITEM_PLAN_LET = 15;
    
    public static final String TODOS = "";
    
    
}