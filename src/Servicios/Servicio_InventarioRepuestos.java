package Servicios;

import Entidades.Detallees;
import static Entidades.Otros.Constante.TIT_IVG;
import Entidades.Repuestos;
import Mantenimiento.InventarioValorizadoDAO;
import Mantenimiento.MaestrosDAO;
import Presentacion.FREP001;
import Presentacion.InventarioValorizado.IU_MostrarInventario;
import Presentacion.InventarioValorizado.InventarioBrowse;
import static Presentacion.NewMain.Redondear2Decimales;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Keily
 */
public class Servicio_InventarioRepuestos {

    MaestrosDAO mDAO;
    InventarioValorizadoDAO idao;
    FREP001 iu;
    IU_MostrarInventario iv;
    List listarep;
    List listadet;

    public Servicio_InventarioRepuestos(IU_MostrarInventario iv) {
    
        System.out.println("Estoy en Servicio_InventarioRepuestos");
        mDAO = new MaestrosDAO();

        System.out.println("Voy a entrar a InventarioValorizadoDAO");        
        idao = new InventarioValorizadoDAO();
        
//
//        mDAO = new MaestrosDAO();
//        
        
        this.iv = iv;
    }

    public void fecha(String fecha) {
    }

    public BigDecimal listar_Inventario_aUnaFecha(String valorizacion, String fecha) {
        
        DefaultTableModel table = (DefaultTableModel) iv.tablaRepuestos.getModel();
        
        BigDecimal totalValorizado = new BigDecimal(0);
        BigDecimal stock = new BigDecimal(0);
        double cantEntregada;
        String tipoTransaccion = "";
        List<InventarioBrowse> listaTmp = new ArrayList<InventarioBrowse>();

        List lstInventario = idao.listar_Inventario_aUnaFecha(valorizacion, fecha);
        int tamanyo = lstInventario.size();
        String repuestosMovidosDespuesFecha = "";
        
        Object[] repuesto = null;
        Object[] repuestoSiguiente = null;
        for ( int i = 0; i < tamanyo; i++ ) {
            System.out.println("i::" + i);
            if ( i != tamanyo - 1 ) {
                repuesto = (Object[]) lstInventario.get(i);
                repuestoSiguiente = (Object[]) lstInventario.get(i + 1);
                cantEntregada = (new  Integer(repuesto[8].toString())).doubleValue();
                tipoTransaccion = repuesto[7] != null ? repuesto[7].toString() : "";
                
                System.out.println("repuesto[0] : " + repuesto[0]);
                System.out.println("repuesto[7] : " + repuesto[7]);                
                System.out.println("repuesto[13] : " + repuesto[13]); //cod.sec.
                System.out.println("repuesto[14] : " + repuesto[14]); //aplicacion2
                System.out.println("repuesto[15] : " + repuesto[15]); //anotacion2
                
                
                if ( repuesto[0].equals(repuestoSiguiente[0]) ) { // Certeza de tener 2 codigoRepuesto iguales (de diferente movimiento)
                    if ( stock.compareTo(BigDecimal.ZERO) == 0 ) { // 1° ocurrencia de un codigoRepuesto (stock se inicializa desde BD)
                        stock = new BigDecimal(String.valueOf(repuesto[4]));
                    }
                    
                    stock = calcularStock(stock,BigDecimal.valueOf(cantEntregada), tipoTransaccion).setScale(0); //recalcula stock con ingresos/salidas
                
                    // Salvar el stock del último item (repuestoSiguiente)
                    if (llegaFinalInventario(i, tamanyo)) {
                        stock = new BigDecimal(String.valueOf(repuestoSiguiente[4]));
                        cantEntregada = (new  Integer(repuestoSiguiente[8].toString())).doubleValue();
                        tipoTransaccion = repuestoSiguiente[7] != null ? repuestoSiguiente[7].toString() : "";
                        stock = calcularStock(stock, BigDecimal.valueOf(cantEntregada), tipoTransaccion).setScale(0);
                    }
                } else {
                    System.out.println("Tipo Transaccion : " + tipoTransaccion);
                    System.out.println("Fecha : " + fecha);
                    System.out.println("repuesto[0] : " + repuesto[0]);
                    System.out.println("Stock : " + stock);
// javier hoy (puse este if comente lo de abajo)
                    if ( i == 0) { 
                       stock = new BigDecimal(String.valueOf(repuesto[4]));
                    }
                    
// jsp
                    if ( "5".equals(repuesto[7])) { // ventas con factura o boleta
                        tipoTransaccion = "V";
                    }
                    if ( "10".equals(repuesto[7]) ) { // salidas x regularizacion
                        tipoTransaccion = "V";
                    }
                    if ( "9".equals(repuesto[7]) ) { // ingresos x regularizacion
                        tipoTransaccion = "C";
                    }
                    if ( "3".equals(repuesto[7]) ) { // devoluciones
                        tipoTransaccion = "C";
                    }
                    if ( "7".equals(repuesto[7]) ) { //importaciones
                        tipoTransaccion = "C";
                    }
//                                        
                    
                    stock = calcularStock(stock, BigDecimal.valueOf(cantEntregada), tipoTransaccion).setScale(0);                    
                    totalValorizado = agregandoLista(repuesto, stock, totalValorizado, listaTmp);
                    repuestosMovidosDespuesFecha = repuestosMovidosDespuesFecha + "'" + String.valueOf(repuesto[0]) + "' ,";
                    
                    stock = BigDecimal.valueOf((new  Integer(repuestoSiguiente[4].toString())).doubleValue());
                }
            }
            
            // Salvar el stock del último item (repuestoSiguiente)
            if (llegaFinalInventario(i, tamanyo)) {
                System.out.println("elemento final::" + i + " - tamanyo::" + tamanyo);
                cantEntregada = (new  Integer(repuestoSiguiente[8].toString())).doubleValue();
                tipoTransaccion = repuestoSiguiente[7] != null ? repuestoSiguiente[7].toString() : "";
                System.out.println("cantEntregada(RepSig):" + cantEntregada);
                System.out.println("tipoTransaccion(RepSig)::" + tipoTransaccion);
                System.out.println("stock(RepSig)::" + tipoTransaccion);
                stock = calcularStock(stock, BigDecimal.valueOf(cantEntregada), tipoTransaccion).setScale(0);
                System.out.println("stock final (RepSig)::" + stock);

                totalValorizado = agregandoLista(repuestoSiguiente, stock, totalValorizado, listaTmp);
                repuestosMovidosDespuesFecha = repuestosMovidosDespuesFecha + "'" + String.valueOf(repuesto[0]) + "'";
            }
        }
        
        // Agregar los items que no se movieron su inventario (stock) desde "Fecha_Hasta + 1 día" y "Actualidad":
        System.out.println("repuestosMovidosDespuesFecha::" + repuestosMovidosDespuesFecha);
        
        // Obteniendo lista de codigos de repuestos movidos
        List lstCodRepNoRepetidos = idao.listar_CodigosInventarioSinRepetir(valorizacion, fecha);
        String codigosRepuestosSinRepetir = "''";
        if (lstCodRepNoRepetidos != null && !lstCodRepNoRepetidos.isEmpty()) {
            String codRepuesto = null;
            codigosRepuestosSinRepetir = "";

            for (int ind = 0; ind <lstCodRepNoRepetidos.size(); ind++) {
                codRepuesto = String.valueOf(lstCodRepNoRepetidos.get(ind));
                System.out.println("codRepuesto::" + codRepuesto);
                codigosRepuestosSinRepetir = codigosRepuestosSinRepetir + "'" + codRepuesto + "',";
            }
            codigosRepuestosSinRepetir = codigosRepuestosSinRepetir.substring(0, codigosRepuestosSinRepetir.length() - 1);
        }
        System.out.println("codigosRepuestosSinRepetir:::" + codigosRepuestosSinRepetir);
        
        // Agregar el resto de repuestos del MAESTRO a la listaTmp (solo los repuestos que fueron COMPRA/VENTA).
        List lstMaestroResto = mDAO.listar_RepuestosExceptuandoListaRepuestosMovidos(codigosRepuestosSinRepetir);
        Object[] repuestoMaestro = null;
        
        for (int j=0; j<lstMaestroResto.size(); j++) {
            repuestoMaestro = (Object[]) lstMaestroResto.get(j);
            String fechaMovimiento = "";
            
            if (repuestoMaestro[9] == null || repuestoMaestro[9].equals("")) {
                fechaMovimiento = String.valueOf(repuestoMaestro[9]);
            }
            
            double valoriza = repuestoMaestro[5] != null ? Double.parseDouble(repuestoMaestro[5].toString()) : 0.00;
            
            System.out.println("repuestoMaestro[4]::" + repuestoMaestro[4]);
            System.out.println("String.valueOf(repuestoMaestro[4])::" + String.valueOf(repuestoMaestro[4]));
            BigDecimal stockMaestro = new BigDecimal(String.valueOf(repuestoMaestro[4]));
            String tot = formatearMonetario((stockMaestro.multiply(BigDecimal.valueOf(valoriza)).setScale(2)) + "", 2);
            
            String costoUnit = formatearMonetario(String.valueOf(repuestoMaestro[11]), 2); //10
            String precioVta = formatearMonetario(String.valueOf(repuestoMaestro[12]), 2); //11

///////////////////////            
                System.out.println("Linea 10: " + repuestoMaestro[10]);   //idlinea          
                System.out.println("repuestoMaestro [10] : " + repuestoMaestro[10]); //
                System.out.println("repuestoMaestro [11] : " + repuestoMaestro[11]); //
                System.out.println("repuestoMaestro [12] : " + repuestoMaestro[12]); //
                System.out.println("repuestoMaestro [13] : " + repuestoMaestro[13]); //
                
                System.out.println("repuesto[13] : " + repuesto[13]); //cod.sec.
                System.out.println("repuesto[14] : " + repuesto[14]); //aplicacion2
                System.out.println("repuesto[15] : " + repuesto[15]); //anotacion2
                System.out.println("lstMaestroResto[13] : " + repuestoMaestro[13]); //cod.sec.
                System.out.println("lstMaestroResto[14] : " + repuestoMaestro[14]); //aplicacion2
                System.out.println("lstMaestroResto[15] : " + repuestoMaestro[15]); //anotacion2

                
            String codigoSec = repuestoMaestro[13] != null ? repuestoMaestro[13].toString() : null;
            String idLinea = repuestoMaestro[10] != null ? repuestoMaestro[10].toString() : null;            
//            String desclista2 = repuestoMaestro[14] != null ? repuestoMaestro[14].toString() : null;  
//            String aplicacion2 = repuestoMaestro[15] != null ? repuestoMaestro[15].toString() : null;
//            String codigoSec = repuesto[13] != null ? repuesto[13].toString() : null;
//            String desclista2 = repuesto[14] != null ? repuesto[14].toString() : null;
            
                    
            InventarioBrowse ib = new InventarioBrowse(
                                                       String.valueOf(repuestoMaestro[0]),
                                                       String.valueOf(repuestoMaestro[1]), String.valueOf(repuestoMaestro[2]), 
                                                       String.valueOf(repuestoMaestro[3]), stockMaestro + "", 
                                                       String.valueOf(repuestoMaestro[5]), 
                                                       String.valueOf(tot), 
//                                                       fechaMovimiento, 
                                                       idLinea,
                                                       costoUnit, 
                                                       precioVta, 
                                                       codigoSec,
                                                       String.valueOf(repuestoMaestro[14]), String.valueOf(repuestoMaestro[15]),idLinea);
            listaTmp.add(ib);
        }
        
        // 2. Ordenando lista temporal por 2 filtros (descripcion y aplicacion)
        Collections.sort(listaTmp);
//        mostrarListaOrdenadax2Filtros(listaTmp);
        
        // 3. Transferir los elementos ordenados de la lista temporal "listaTmp" a la lista que se mostrará en el Browse:
        for ( InventarioBrowse ib : listaTmp ) {
            String codigo = ib.getCodrepuesto();
            String descripcion = ib.getDescripcion();
//            String descrModelo = ib.getAplicacion();
            String descrLista = !"null".equals(ib.getAnotacion()) ? ib.getAnotacion() : "";
            
            String stockIB = ib.getStock();
            String costo = ib.getValorizacion();
            String total = ib.getTotal();
            String costoUnit = ib.getCostoUnit();
            String precioVta = ib.getPrecioVta();
            String codigoSec = ib.getCodigoSec();
            
//            String desclista2 = ib.getDesclista2();
            String xxxx = ib.getDesclista2();
            String yyyy = ib.getAnotacion();
            String zzzz = ib.getAplicacion();            
            String descrModelo = ib.getDescrModelo();
            
            String desclista2 = !"null".equals(ib.getDesclista2()) ? ib.getDesclista2() : "";
//            String aplicacion2 = !"null".equals(ib.getAplicacion2()) ? ib.getAplicacion2() : "";            
            String idLinea = ib.getIdlinea();
            
            Object[] fila = {codigo, descripcion, descrModelo, descrLista, stockIB, 
                             costo, total, idLinea,  
                             costoUnit, precioVta, codigoSec, desclista2, zzzz, idLinea};
            //                                                idLinea
            table.addRow(fila);
        }
        return totalValorizado;
    }
    
    private boolean llegaFinalInventario(int indice, int tamanyo) {
        return indice == tamanyo-1;
    }
    
    private BigDecimal agregandoLista(Object[] repuesto, BigDecimal stock, 
            BigDecimal totalValorizado, List<InventarioBrowse> listaTmp) {
        
        double valoriza = repuesto[5] != null ? Double.parseDouble(repuesto[5].toString()) : 0.00;
        String tot = formatearMonetario((stock.multiply(BigDecimal.valueOf(valoriza)).setScale(2)) + "", 2);

        String costoUnit = formatearMonetario(String.valueOf(repuesto[10]), 2);
        String precioVta = formatearMonetario(String.valueOf(repuesto[11]), 2);

        String codigoSec = repuesto[12] != null ? repuesto[12].toString() : null;
        String desclista2 = repuesto[13] != null ? repuesto[13].toString() : null;
              
        totalValorizado = totalValorizado.add(stock.multiply(BigDecimal.valueOf(valoriza)).setScale(2));

        ////////////////////////////////////////////////
        // Guardando fila en una Entidad y ésta en una lista para luego ordenarla por 2 filtros (Descripcion y Modelo[Aplicación])
        // 1.Guardando en lista temporal (lista desordenada)
        InventarioBrowse ib = new InventarioBrowse(
                                                   String.valueOf(repuesto[0]),
                                                   String.valueOf(repuesto[1]), String.valueOf(repuesto[2]), 
                                                   String.valueOf(repuesto[3]), stock + "", 

                                                   String.valueOf(repuesto[5]), String.valueOf(tot), 
                                                   String.valueOf(repuesto[9]), costoUnit, precioVta, codigoSec, 
                                                   desclista2, String.valueOf(repuesto[13]),"");
        listaTmp.add(ib);
        return totalValorizado;
    }
    
    private void mostrarListaOrdenadax2Filtros(List<InventarioBrowse> lista) {
//        System.out.println("Lista ordenada x 2 filtros(descripcion y aplicacion)::");
        for ( int i = 0; i < lista.size(); i++ ) {
            InventarioBrowse ib = (InventarioBrowse) lista.get(i);
            System.out.println(ib.getDescripcion() + "--" + ib.getAplicacion());
        }
    }
    
    public BigDecimal listarRepuestos(String valorizacion) {
        BigDecimal numero1 = new BigDecimal(100);
        BigDecimal contador = new BigDecimal(0);
        DefaultTableModel table = (DefaultTableModel) iv.tablaRepuestos.getModel();
//        Iterator ite = mDAO.Obtener_Lista_Objetos().iterator();
        Iterator ite = mDAO.repuestosOrdenadosPorLinea().iterator();

//        int item = 0;
        while ( ite.hasNext() ) {
            BigDecimal costo = new BigDecimal(0);
            BigDecimal stock;
            Object[] fila = new Object[15];
            Repuestos rep = (Repuestos) ite.next();

            if ( !"".equals(rep.getCodrepuesto()) ) {
                fila[0] = rep.getCodrepuesto();
            }

            if ( !"".equals(rep.getDescripcion()) ) {
                fila[1] = rep.getDescripcion();
            }
            if ( rep.getDescrmodelo()!= null) {
                fila[2] = rep.getDescrmodelo();
            }
            if ( rep.getDesclista()!= null) {
                fila[3] = rep.getDesclista();
            }

            fila[4] = rep.getStock();
            
            double st = Double.parseDouble(String.valueOf(rep.getStock()));
            stock = BigDecimal.valueOf(st);
            double cos; // variable de costo para convertir a bigdecimal

            if ( "Costo Unitario".equals(valorizacion) ) {
                if ( rep.getCostopromedio() != null ) {
                    cos = rep.getCostopromedio();
                } else {
                    cos = 0.0;
                }
                fila[5] = formatearMonetario(String.valueOf(cos), 2);
                costo = BigDecimal.valueOf(cos);
            }
            if ( "Ultimo Costo".equals(valorizacion) ) {
                if ( rep.getPcostoultimo() != null ) {
                    cos = rep.getPcostoultimo();
                } else {
                    cos = 0.0;
                }
                fila[5] = formatearMonetario(String.valueOf(cos), 2);
                costo = BigDecimal.valueOf(cos);
            }
            if ( "Precio Lista".equals(valorizacion) ) {
                if ( rep.getPreciolista() != null ) {
                    cos = rep.getPreciolista();
                } else {
                    cos = 0.0;
                }
                costo = BigDecimal.valueOf(cos);
                fila[5] = formatearMonetario(String.valueOf(cos), 2);
            }
            
            // (**) Valga que no se usará costo para este tipo de valorizacion igual para tener consistencia lógica se asigna a preciolista.
            if ( valorizacion.equals(TIT_IVG)) {
                if ( rep.getPreciolista() != null ) {
                    cos = rep.getPreciolista();
                } else {
                    cos = 0.0;
                }
                costo = BigDecimal.valueOf(cos);
                fila[5] = formatearMonetario(String.valueOf(cos), 2);
            }
            
            BigDecimal total = costo.multiply(stock);
            double totDou = Double.parseDouble(String.valueOf(total));
            double td = Redondear2Decimales(totDou);
            fila[6] = formatearMonetario(td + "", 2);
            fila[7] = rep.getEquipos().getIdequipo();
            
            fila[8] = formatearMonetario(String.valueOf(rep.getCostopromedio()), 2);
            fila[9] = formatearMonetario(String.valueOf(rep.getPreciolista()), 2);
            
            fila[10] = rep.getCodigoseg();
            fila[11] = rep.getDesclista2();
            fila[12] = rep.getAplicacion();
            fila[13] = rep.getFobultimo();
            fila[14] = rep.getMarca();
                        
            contador = (contador.add(total).multiply(numero1)).divide(numero1).setScale(2);
            table.addRow(fila);
        }
        return contador;
    }
    
    private String formatearMonetario(String valorMonetario, int numeroDecimales) {
        int indicePunto = valorMonetario.indexOf(".") + 1;
        
        if ( indicePunto == 0 ) {
            valorMonetario = valorMonetario + ".00";
            
        } else {
            int longitud = valorMonetario.length();
            int decimales = longitud - indicePunto;
            int diferenciaCeros = numeroDecimales - decimales;
            for ( int i = 0; i < diferenciaCeros; i++ ) {
                valorMonetario = valorMonetario + "0";
            }
        }
        return valorMonetario;
    }

    public ArrayList<Detallees> listar_repuestos_por_codigo(int codigo) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from detallees where idrepuesto = '" + codigo + "'");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }

    public ArrayList<Repuestos> listar_repuestos_por_fecha(String fecha) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from detallees where idrepuesto > '" + fecha + "'");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }

    public ArrayList<Detallees> lista_detalles_xRepuesto(int idRepuesto) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query q = (Query) session.createQuery("from Detalles where iddetalles = '" + idRepuesto + "'");
        ArrayList lista = (ArrayList) q.list();
        return lista;
    }

    public ArrayList<Repuestos> lista_de_Repuestos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ArrayList lista;
        try {
            Query q = (Query) session.createQuery("from Repuestos");
            lista = (ArrayList) q.list();
        } catch (HibernateException e) {
            return null;
        }
        return lista;
    }

    public void formatoTabla(final JTable tabla, String valorizacion) {
        DefaultTableModel table = (DefaultTableModel) tabla.getModel();

//        String[] tituloColumna = {"Línea", "Nº de Parte", "Cód. Secundario", "Descripción", 
//                                  "Aplicación", "Stock", valorizacion, "Total"};
//        String[] tituloColumna = {"Item", "Nº de Parte", "Descripción", 
//                                  "Modelo", "Descr.Lista", "Stock", valorizacion, "Total", "idLinea"};
        String[] tituloColumna = {
//                                  "Item", 
                                  "Nº de Parte", "Descripción", 
                                  "Aplicación",  // = Modelo
                                  "Anotación", "Stock", valorizacion, "Total", "idLinea",
                                  "Costo Unitario", "Precio Venta", "Cod Sec", "Desclista2", "Aplicacion2", "foxultimo", "marca"};

        table.setColumnIdentifiers(tituloColumna);
//        tabla.getColumnModel().getColumn(0).setPreferredWidth(70);  // Item
        
        tabla.getColumnModel().getColumn(0).setPreferredWidth(120); // N° de Parte
        tabla.getColumnModel().getColumn(1).setPreferredWidth(190); // Descripción
        tabla.getColumnModel().getColumn(2).setPreferredWidth(140); // Aplicación (Modelo)
        tabla.getColumnModel().getColumn(3).setPreferredWidth(230); // Descr.Lista
        tabla.getColumnModel().getColumn(4).setPreferredWidth(60);  // Stock
        
        if ( "Costo Unitario + Precio Venta".equals(valorizacion) ) {
            tabla.getColumnModel().getColumn(5).setPreferredWidth(0);  // Valorizacion (Costo Unitario, Ultimo Costo, Precio Lista)
            tabla.getColumnModel().getColumn(6).setPreferredWidth(0); // Total
            
            tabla.getColumnModel().getColumn(8).setPreferredWidth(90);   // Costo Unitario
            tabla.getColumnModel().getColumn(9).setPreferredWidth(100);  // Precio Venta
        } else {
            tabla.getColumnModel().getColumn(5).setPreferredWidth(90);  // Valorizacion (Costo Unitario, Ultimo Costo, Precio Lista)
            tabla.getColumnModel().getColumn(6).setPreferredWidth(100); // Total
            
            tabla.getColumnModel().getColumn(8).setPreferredWidth(0);   // Costo Unitario
            tabla.getColumnModel().getColumn(9).setPreferredWidth(0);  // Precio Venta
        }
        tabla.getColumnModel().getColumn(7).setPreferredWidth(0);   // idLinea
        tabla.getColumnModel().getColumn(10).setPreferredWidth(0);  // Cod Sec (Código secundario)
        
        tabla.getColumnModel().getColumn(11).setPreferredWidth(0);  // Desclista2
        tabla.getColumnModel().getColumn(12).setPreferredWidth(0);  // Aplicacion2

        tabla.getColumnModel().getColumn(13).setPreferredWidth(0);  // foxultimo
        tabla.getColumnModel().getColumn(14).setPreferredWidth(0);  // marca              
        
    }

    public BigDecimal calcularStock(BigDecimal stock, BigDecimal cant_entregada, String codop) {
        BigDecimal stockfinal = new BigDecimal(0);
        if ( "9".equals(codop) || "3".equals(codop) || "7".equals(codop) || "2".equals(codop) ) {
            stockfinal = stock.subtract(cant_entregada);
        }
        
        if ( "10".equals(codop) || "5".equals(codop) || "4".equals(codop) ) { 
            stockfinal = stock.add(cant_entregada);
        }
        System.out.println("Codigo Operacion: "+codop);
        System.out.println("Cant.Entregada : " + cant_entregada);
        System.out.println("Stock Final.... :" + stockfinal);
        return stockfinal;
    }
    
 //   public List<Repuestos> repuestosOrdenadosPorLinea() {
 //       return mDAO.repuestosOrdenadosPorLinea();
 //   }
}