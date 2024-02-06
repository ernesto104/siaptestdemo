package Servicios;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Cabecsalvar;
import Entidades.Sistema;
import Entidades.Vendedores;
import Mantenimiento.CabecesDAO;
import Mantenimiento.Facturacion.SistemaDAO;
import Presentacion.FacturacionElectronica.CabDocElectronico;
import Presentacion.FacturacionElectronica.DetDocElectronico;
import Presentacion.FacturacionElectronica.UI_Generar_DocElectronicos;
import Servicios.facturacion.Billete;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author maverick225
 */
public class Servicio_Cabeces {

    private CabecesDAO dao;
    SistemaDAO sisDAO;
    
    Sistema sistNC;
    Sistema sistND;
    Sistema sistFact;
    Sistema sistBol;

    public Servicio_Cabeces() {
        sisDAO = new SistemaDAO();
        dao = new CabecesDAO();
        
        getDocumentosSistema();
    }
    
    private void getDocumentosSistema() {
        sistNC = new SistemaDAO().getActualNroSerie("03");
        sistND = new SistemaDAO().getActualNroSerie("04");

        sistFact = new SistemaDAO().getActualNroSerie("01");
        sistBol = new SistemaDAO().getActualNroSerie("02");
    }
    
    public boolean ActualizarTodasCabecera(List<CabDocElectronico> listaCab) {
        return dao.ActualizarTodasCabecera(listaCab);
    }
    
    private void mostrarLista(Cabeces newCab) {
        System.out.println("newCab::" + newCab.toString());
    }
    
//    public boolean ActualizarTodasCabecera(List<Cabeces> listaCab) {
//        boolean valor = false;
//        Session session = dao.getHibernateTemplate();
//        System.out.println("sesion :" + session);
//        Transaction tx = null;
//
//        try {
//            if ( session != null ) {
//                tx = session.beginTransaction();
//                System.out.println("tx:" + tx);
//
//                for ( Cabeces c : listaCab ) {
//                    Cabeces newCab = (Cabeces) session.merge(c);
//                    session.update(newCab);
//                    session.flush();
//                    session.clear();
//                }
//                tx.commit();
//                valor = true;   
//            }
//
//        } catch ( HibernateException e ) {
//            if ( tx != null ) {
//                tx.rollback();
//                e.printStackTrace();
//            }
//
//        } finally {
//           session.close(); 
//        }
//        return valor;
//    }

    public Cabeces obtenerCabecera_id(CabecesId id) {
        return dao.obtenerCabecera_Id(id);
    }
    
    public Cabeces obtenerCabecera(String tipoTra, String tipoDoc, String nroSerie, String nroDocumento) {
        return dao.obtenerCabecera(tipoTra, tipoDoc, nroSerie, nroDocumento);
    }

    public Cabeces obtenerCabecera(CabecesId doc) {
        return dao.obtenerCabecera(doc);
    }

    public boolean modificar_Cabec(Cabeces c) {
        return dao.Modificar_Objeto(c);
    }
    
    public boolean agregar_cabanulado(Cabeces c){
        return dao.agregarcabcanulada(c);
    }

    public List<Cabeces> obtenerPlanillaVentas(Date desde, Date hasta) {
        return dao.obtenerPlanillaVentas(desde, hasta);
    }

    public List<Cabeces> obtenerPlanillaCompras(Date desde, Date hasta) {
        return dao.obtenerPlanillaCompras(desde, hasta);
    }

    public List<Cabecsalvar> obtenerPlanillaVarias(Date desde, Date hasta) {
        return dao.obtenerPlanillaVarias(desde, hasta);
    }

    /*  Comisiones     */
    public List Obtener_Comisiones_Pendientes(String vendedor) {
        return vendedor.equals("") ? dao.Comisiones_Pendientes() : dao.Comisiones_Pendientes(vendedor);
    }

    public List<Cabeces> obtenerGuiasRemision() {
        return dao.obtenerGuiasRemision();
    }

    public boolean guardarGuiaRemision(Cabeces cab) {
        return dao.Modificar_Objeto(cab);
    }
    
    public boolean agregarcabecera(Cabeces cab){
        return dao.Agregar_Objeto(cab);
    }

    public Sistema getSis(String nombre) {
        return sisDAO.obtener_por_nombre(nombre);
    }

    public boolean actualizarSis(Sistema s) {
//        return sisDAO.Modificar_Objeto(s);
        return sisDAO.Modificar_Objeto_Merge(s);
    }

    /* Canje x letras*/
    public List ObtenerDeudas(int id_cliente) {
        return dao.ObtenerDeudas(id_cliente);
    }
    
    /* Canje de Letras protestadas x nuevas letras */
    public List ObtenerLetrasPendientesCancelar(int id_cliente) {
        return dao.ObtenerLetrasPendientesCancelar(id_cliente);
    }

    /* Canje de Documentos(Factura, Nota de Crédito, Nota de Débito y Letra) por nuevas letras */
    public List ObtenerDeudasPendientesCancelar(int id_cliente) {
        return dao.ObtenerDeudasPendientesCancelar(id_cliente);
    }
    
    public String ObtenerDescripcion_Documento(String id) {
        return sisDAO.obtener_por_Codigo(id);
    }

    /*  Comisiones     */
    public List Obtener_Comisiones_Pagadas(String vendedor) {
        return vendedor.equals("") ? dao.Comisiones_Pagadas() : dao.Comisiones_Pagadas(vendedor);
    }

    public List Obtener_Comisiones(String vendedor) {
        return vendedor.equals("") ? dao.Comisiones() : dao.Comisiones(vendedor);
    }

    public boolean pagarComisiones(ArrayList<Cabeces> seleccionados) {
        return dao.PagarComisiones(seleccionados);
    }

    public boolean ActualizarCabecera_Vendedor(Cabeces c, Vendedores v) {
        return dao.ActualizarCab_Vendedor(c, v);

    }

    /*Planillas*/
    public boolean GuardarDocumentoAnulado(Cabeces c) {
        return dao.Agregar_Objeto(c);
    }
    
    public boolean registrarFactAnulada(Cabeces c) {
        return dao.registrarFactAnulada(c);
    }

    //////////Resumen General de clientes
    public List ObtenerDeudas_FacBol(int id_cliente, Date Inicio, Date Fin) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fecha_inicio = sdf.format(Inicio);
        String fecha_fin = sdf.format(Fin);
        return dao.ObtenerDeudas_FacBol(id_cliente, fecha_inicio, fecha_fin);
    }

    public List ObtenerDeudas_Letras(int id_cliente, Date Inicio, Date Fin) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fecha_inicio = sdf.format(Inicio);
        String fecha_fin = sdf.format(Fin);
        return dao.ObtenerDeudas_Letras(id_cliente, fecha_inicio, fecha_fin);
    }

    public List ObtenerDeudas_SK(int id_cliente, Date Inicio, Date Fin) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fecha_inicio = sdf.format(Inicio);
        String fecha_fin = sdf.format(Fin);
        return dao.ObtenerDeudas_SK(id_cliente, fecha_inicio, fecha_fin);
    }

    public boolean actualizarDoc(Cabeces cabeces, Vendedores vendedor) {
        return dao.ActualizarCab_Vendedor(cabeces, vendedor);
    }
        
    public boolean ActualizarCabecera(Cabeces c) {
        return dao.ActualizarCabecera(c);
    }
    
    public Cabeces obtenerCabecera(int nroguia) {
        return dao.obtenerCabecera(nroguia);
    }
    
    private String convertirTipDocBDSiar_Sunat(String tipDoc) {
        System.out.println("convertir:" + tipDoc);
        if ( "01".equals(tipDoc) ) {
            tipDoc = sistFact.getCodDocElect();
            System.out.println("resultado01(after convertion):" + tipDoc);
            return tipDoc;
            
        } else if ( "02".equals(tipDoc) ) {
            tipDoc = sistBol.getCodDocElect();
            System.out.println("resultado02(after convertion):" + tipDoc);
            return tipDoc;
            
        } else if ( "03".equals(tipDoc) ) {
            tipDoc = sistNC.getCodDocElect();
            System.out.println("resultado03(after convertion):" + tipDoc);
            return tipDoc;
            
        } else if ( "04".equals(tipDoc) ) {
            tipDoc = sistND.getCodDocElect();
            System.out.println("resultado04(after convertion):" + tipDoc);
            return tipDoc;
            
        } else {
            return null;
        }
    }
    
    // Servicio para cargar Cabeces y Detallees de Documentos Electronicos
    public List<CabDocElectronico> ObtenerDocumentosElectronicos(UI_Generar_DocElectronicos iu, String fecha_inicio, String fecha_fin) {
        List lst = dao.obtenerPlanillaDocElectronicos(fecha_inicio, fecha_fin);
        List<CabDocElectronico> lstCDE = null;
        int cont = 1;
        if ( lst.size() != 0 ) {
            lstCDE = new ArrayList<CabDocElectronico>();
            // I. Obtener datos del 1º registro (Cabeces del Documento Electrónico)
            Object[] obj = (Object[]) lst.get(0);
            String fechaAnt     = String.valueOf(obj[0]);
            
            String tipDocAnt    = String.valueOf(obj[1]);
            String prefijoAnt   = String.valueOf(obj[2]);
            String nroSerAnt    = String.valueOf(obj[3]);
            String nroDocAnt    = String.valueOf(obj[4]);
            
            String tdelectAnt   = String.valueOf(obj[28]);
            
            System.out.println("Nº cont:" + cont);
            System.out.println("tipDocAnt:" + tipDocAnt);
            System.out.println("prefijoAnt:" + prefijoAnt);
            System.out.println("nroSerAnt:" + nroSerAnt);
            System.out.println("nroDocAnt:" + nroDocAnt);
            System.out.println("(RIV)tdelectAnt::" + tdelectAnt);
            
            String nda              = String.valueOf(obj[5]);
            String arsa             = String.valueOf(obj[7]);
            String m                = String.valueOf(obj[8]); // CONSULTAR A JAVIER (Debe decir USD, en vez de US$)
            
            String tog              = String.valueOf(obj[9]);
            String mti              = String.valueOf(obj[10]);
            String mp               = String.valueOf(obj[11]);
                
            String txtGen           = String.valueOf(obj[12]);
            String fechaEnvio       = String.valueOf(obj[22]);
            String diasVenceGenTxt  = String.valueOf(obj[23]);
            String esAnulado        = String.valueOf(obj[24]);
//            String fecha            = String.valueOf(obj[25]);
            
            String guiaRemision     = String.valueOf(obj[26]);
            String direccionCliente = String.valueOf(obj[27]);
            
            
            if ( "ANULADO".equals(esAnulado) ) {
                tog = "0.00";
                mti = "0.00";
                mp = "0.00";
            }
            
            // 1. Construir 1º Cabecera Doc. Elect.
            CabDocElectronico cde = new CabDocElectronico();
            cde.setFec_ed(fechaAnt);
            
            //tipDocAnt = convertirTipDocBDSiar_Sunat(tipDocAnt);
            
            cde.setTip_doc(tipDocAnt);
            cde.setTipo_doc_electronico(tdelectAnt);
            
            String fns = formatearNroSerie(prefijoAnt, nroSerAnt);
            cde.setNro_serie(fns); // F001
            cde.setNro_serie_BD(nroSerAnt);
            
            String numeroDocumentoAnt = formatearNroDoc(nroDocAnt, 7);
            cde.setNro_doc(numeroDocumentoAnt);
            cde.setNro_documento_BD(nroDocAnt);
            
            cde.setNro_doc_adquiriente(nda);
            cde.setApamno_razon_social_adquiriente(arsa);
            cde.setMoneda(m);
            cde.setTotal_operaciones_grav(tog);
            cde.setMonto_total_igv(mti);
            cde.setMonto_pagar(mp);
            
            String alt = getLeyenda(mp, "US$");
            System.out.println("(LED)alt:" + alt);
            cde.setLeyenda(alt);
            //cde.setLeyenda(getLeyenda(mp));
            
            cde.setEstadoTxtGenerado(txtGen);
            cde.setFechaEnvio(fechaEnvio);
            cde.setDiasVencidosGenTxt(diasVenceGenTxt);
            
            cde.setGuia_remision(guiaRemision);
            if ( "null".equals(guiaRemision) ) {
                cde.setGuia_remision("-");
            } else {
                cde.setGuia_remision(formatearNroDoc(guiaRemision, 7));
            }
            
            cde.setDireccion_cliente(direccionCliente);
            if ( "null".equals(direccionCliente) ) {
                cde.setDireccion_cliente("");
            }
            
            // 2. Construir 1º Detalle Doc. Elect.
            List<DetDocElectronico> lstDDE = new ArrayList<DetDocElectronico>();
            DetDocElectronico dde = new DetDocElectronico();
            
            int numeroItem = 1;
            String ii = String.valueOf(numeroItem);
            String cps = String.valueOf(obj[13]);
            String di = String.valueOf(obj[14]);

            String vvu = String.valueOf(obj[15]);
            String pvui = String.valueOf(obj[16]);
            String ci = String.valueOf(obj[17]);
            
//            if ( "ANULADO".equals(esAnulado) ) {
//                ci = "1";
//            }

            String vi = String.valueOf(obj[19]);
            String iti = String.valueOf(obj[20]);
            String ti = String.valueOf(obj[21]);
            
            if ( "ANULADO".equals(esAnulado) ) {
                vvu = "0.00";
                pvui = "0.00";
                vi = "0.00";
                iti = "0.00";
                ti = "0.00";
            }
            dde.setId_item(ii);
            
            dde.setCod_prod_serv_item(cps);
            dde.setCantidad_item(ci);
            
            if ("null".equals(cps)) {
                dde.setCod_prod_serv_item("");
                dde.setCantidad_item("1");
            }
            
            dde.setDesrip_item(di);

            dde.setValor_venta_unitaria(vvu);
            dde.setPrecio_venta_unitario_item(pvui);

            dde.setValor_item(vi);
            dde.setIgv_total_item(iti);
            dde.setTotal_item(ti);

            lstDDE.add(dde);
            
            // 3. Agregar 1º detalle a cabecera del documento electronico
            cde.setLstDetDocElect(lstDDE);
            
            // 4. Agregar Cabecera del Doc. Elect. a listado
            lstCDE.add(cde);
            
            // II. Verificar si existen más de 1 elemento (Cabecera) de documentos electronicos.
            if ( lst.size() > 1 ) {
                
                for ( int i = 1; i < lst.size(); i++ ) {
                    obj = (Object[]) lst.get(i);
                    // obj = (Object[]) ite.next();

                    // 2.1. Parametros que identifican de forma UNICA al Documento Electronico
                    String fechaSig     = String.valueOf(obj[0]);
                    
                    String tipDocSig    = String.valueOf(obj[1]);
                    //Sistema sist = new SistemaDAO().getActualNroSerie(tipDocAnt);
                    
//                    if ( "01".equals(tipDocSig) ) {
//                        tipDocSig = sistFact.getCodDocElect();
//                    }
//                    if ( "02".equals(tipDocSig) ) {
//                        tipDocSig = sistBol.getCodDocElect();
//                    }
//                    
//                    if ( "03".equals(tipDocSig) ) {
//                        tipDocSig = sistNC.getCodDocElect();
//                    }
//                    if ( "04".equals(tipDocSig) ) {
//                        tipDocSig = sistND.getCodDocElect();
//                    }
                    //tipDocSig = convertirTipDocBDSiar_Sunat(tipDocSig);
                    
                    String prefijoSig   = String.valueOf(obj[2]);
                    String nroSerSig    = String.valueOf(obj[3]);
                    String nroDocSig    = String.valueOf(obj[4]);
                    
                    String tdelectSig   = String.valueOf(obj[28]);
                    
                    System.out.println("Nº cont:" + cont);
                    System.out.println("tipDocSig:" + tipDocSig);
                    System.out.println("prefijoSig:" + prefijoSig);
                    System.out.println("nroSerSig:" + nroSerSig);
                    System.out.println("nroDocSig:" + nroDocSig);
                    System.out.println("(RIV2)tdelectSig::" + tdelectSig);
                    
                    // Datos del Detalle del documento electronico
                    cps = String.valueOf(obj[13]);
                    di = String.valueOf(obj[14]);

                    vvu = String.valueOf(obj[15]);
                    pvui = String.valueOf(obj[16]);
                    ci = String.valueOf(obj[17]);

                    vi = String.valueOf(obj[19]);
                    iti = String.valueOf(obj[20]);
                    ti = String.valueOf(obj[21]);

                    // Si Cabeces son iguales, es el mismo documento, con un detalle mas diferente.
                    System.out.println("tipDocAnt:" + tipDocAnt);
                    System.out.println("tipDocSig:" + tipDocSig);
                    
                    System.out.println("tipoDocElectronicoAnt:" + tdelectAnt);
                    System.out.println("tipoDocElectronicoSig:" + tdelectSig);
                    if ( tipDocAnt.equals(tipDocSig) && prefijoAnt.equals(prefijoSig) && nroSerAnt.equals(nroSerSig) && nroDocAnt.equals(nroDocSig) ) {
                        
                        // 2.2.A. Crear nuevo Detalle de documento electronico
                        dde = new DetDocElectronico();
                        
                        numeroItem++;
                        ii = String.valueOf(numeroItem);
                        
                        if ( "ANULADO".equals(esAnulado) ) {
                            vvu = "0.00";
                            pvui = "0.00";
                            vi = "0.00";
                            iti = "0.00";
                            ti = "0.00";
                        }
                        
                        dde.setId_item(ii);
                        
                        dde.setCod_prod_serv_item(cps);
                        dde.setCantidad_item(ci);
                        
                        if ( "null".equals(cps) ) {
                            dde.setCod_prod_serv_item("");
                            dde.setCantidad_item("1");
                        }
                        
                        dde.setDesrip_item(di);
                        dde.setValor_venta_unitaria(vvu);
                        dde.setPrecio_venta_unitario_item(pvui);
                        dde.setValor_item(vi);
                        dde.setIgv_total_item(iti);
                        dde.setTotal_item(ti);
                        
                        lstCDE.get(lstCDE.size()-1).getLstDetDocElect().add(dde);
                        //lstDDE.add(dde);
                        
                    } else {
                        numeroItem = 1;
                        ii = String.valueOf(numeroItem);
                        
                        // 2.2.B.1. Agregar nueva Cabecera de documento electronico
                        cde = new CabDocElectronico();
                        cde.setFec_ed(fechaSig);
                        //cde.setFec_ed(String.valueOf(obj[0]));
                        
                        // imprimir tipDocSig
//                        if ( "03".equals(tipDocSig) ) {
//                            tipDocSig = sistNC.getCodDocElect();
//                        }
//                        if ( "04".equals(tipDocSig) ) {
//                            tipDocSig = sistND.getCodDocElect();
//                        }
                        //System.out.println("(LED)tipDocSig:" + tipDocSig);
                        //tipDocSig = convertirTipDocBDSiar_Sunat(tipDocSig);
                        cde.setTip_doc(tipDocSig);
                        cde.setTipo_doc_electronico(tdelectSig);
                        
                        fns = formatearNroSerie(prefijoSig, nroSerSig);
                        cde.setNro_serie(fns); // F001
                        cde.setNro_serie_BD(nroSerSig);
                        
                        String numeroDocumentoSig = formatearNroDoc(nroDocSig, 7);
                        cde.setNro_doc(numeroDocumentoSig);
                        cde.setNro_documento_BD(nroDocSig);
                        
                        nda             = String.valueOf(obj[5]);
                        arsa            = String.valueOf(obj[7]);
                        m               = String.valueOf(obj[8]); // CONSULTAR A JAVIER (Debe decir USD, en vez de US$)
                        tog             = String.valueOf(obj[9]);
                        mti             = String.valueOf(obj[10]);
                        mp              = String.valueOf(obj[11]);
                        txtGen          = String.valueOf(obj[12]);
                        fechaEnvio      = String.valueOf(obj[22]);
                        diasVenceGenTxt = String.valueOf(obj[23]);
                        esAnulado       = String.valueOf(obj[24]);
                        
                        guiaRemision    = String.valueOf(obj[26]);
                        direccionCliente = String.valueOf(obj[27]);
                        
                        if ( "ANULADO".equals(esAnulado) ) {
                            tog = "0.00";
                            mti = "0.00";
                            mp = "0.00";
                            
                            vvu = "0.00";
                            pvui = "0.00";
                            vi = "0.00";
                            iti = "0.00";
                            ti = "0.00";
                        }
            
                        cde.setNro_doc_adquiriente(nda);
                        cde.setApamno_razon_social_adquiriente(arsa);
                        cde.setMoneda(m);
                        cde.setTotal_operaciones_grav(tog);
                        cde.setMonto_total_igv(mti);
                        cde.setMonto_pagar(mp);
                        
                        //cde.setLeyenda(getLeyenda(mp, m));
                        cde.setLeyenda(getLeyenda(mp, "US$"));
                        //cde.setLeyenda(getLeyenda(mp));
                        
                        cde.setEstadoTxtGenerado(txtGen);
                        cde.setFechaEnvio(fechaEnvio);
                        cde.setDiasVencidosGenTxt(diasVenceGenTxt);
                        
                        cde.setGuia_remision(guiaRemision);
                        if ( "null".equals(guiaRemision) ) {
                            cde.setGuia_remision("-");
                        } else {
                            cde.setGuia_remision(formatearNroDoc(guiaRemision, 7));
                        }
                        
                        cde.setDireccion_cliente(direccionCliente);
                        if ( "null".equals(direccionCliente) ) {
                            cde.setDireccion_cliente("");
                        }
                        
                        // 2.2.B.2. Agregar nuevo Detalle de documento electronico
                        dde = new DetDocElectronico();
                        dde.setId_item(ii);
                        
                        dde.setCod_prod_serv_item(cps);
                        dde.setCantidad_item(ci);
                        
                        if ( "null".equals(cps) ) {
                            dde.setCod_prod_serv_item("");
                            dde.setCantidad_item("1");
                        }
                        
                        dde.setDesrip_item(di);
                        dde.setValor_venta_unitaria(vvu);
                        dde.setPrecio_venta_unitario_item(pvui);    
                        
                        dde.setValor_item(vi);
                        dde.setIgv_total_item(iti);
                        dde.setTotal_item(ti);
                        
                        lstDDE = new ArrayList<DetDocElectronico>();
                        lstDDE.add(dde);
                        
                        cde.setLstDetDocElect(lstDDE);
                        lstCDE.add(cde);
                        
                        // Reasignar la cabecera Sig (actual) al nuevo Ant
                        tipDocAnt   = tipDocSig;
                        prefijoAnt  = prefijoSig;
                        nroSerAnt   = nroSerSig;
                        nroDocAnt   = nroDocSig;
                        
                        tdelectAnt  = tdelectSig;
                    }
                    cont++;
                }
            }
        }
        mostrarListado(lstCDE);
        return lstCDE;
    }
    
    private void mostrarListado(List<CabDocElectronico> lstCDE) {
        int indCab = 1;
        for ( CabDocElectronico cde : lstCDE ) {
            String c1 = cde.getFec_ed();
            String c2 = cde.getTip_doc();
            String c3 = cde.getNro_serie();
            String c4 = cde.getNro_doc();
            String c5 = cde.getNro_doc_adquiriente();
            String c6 = cde.getTip_doc_adquiriente();
            String c7 = cde.getApamno_razon_social_adquiriente();
            String c8 = cde.getMoneda();
            String c9 = cde.getTotal_operaciones_grav();
            String c10 = cde.getTotal_operaciones_inf();
            String c11 = cde.getTotal_operaciones_exoneradas();
            String c12 = cde.getTotal_operaciones_exportacion();
            String c13 = cde.getMonto_total_operaciones_grat();
            String c14 = cde.getMonto_descuentos_globales();
            String c15 = cde.getMonto_total_igv();
            String c16 = cde.getMonto_pagar();
            String c17 = cde.getMonto_percepcion();
            String c18 = cde.getMonto_total_percep();
            String c19 = cde.getLeyenda();
            String c20 = cde.getCorreo_cliente();
            String c21 = cde.getEstadoTxtGenerado();
            String c22 = cde.getFechaEnvio();
            String c23 = cde.getDiasVencidosGenTxt();
            String c24 = cde.getNro_serie_BD();
            String c25 = cde.getNro_documento_BD();
            String c26 = cde.getTipo_doc_electronico();
            
            System.out.println("Nº Cab:" + indCab);
            System.out.println(c1);
            System.out.println(c2);
            System.out.println(c3);
            System.out.println(c4);
            System.out.println(c5);
            
            System.out.println(c6);
            System.out.println(c7);
            System.out.println(c8);
            System.out.println(c9);
            System.out.println(c10);

            System.out.println(c11);
            System.out.println(c12);
            System.out.println(c13);
            System.out.println(c14);
            System.out.println(c15);
            
            System.out.println(c16);
            System.out.println(c17);
            System.out.println(c18);
            System.out.println(c19);
            System.out.println(c20);
            
            System.out.println(c21);
            System.out.println(c22);
            System.out.println(c23);
            System.out.println(c24);
            System.out.println(c25);
            System.out.println(c26);
            
            List<DetDocElectronico> lstDet = cde.getLstDetDocElect();
            int indDet = 1;
            
            for ( DetDocElectronico d : lstDet ) {
                System.out.println("Nº Detalle:" + indDet);
                String d1 = d.getId_item();
                String d2 = d.getCod_prod_serv_item();
                String d3 = d.getDesrip_item();
                String d4 = d.getCod_unidad_medida_item();
                String d5 = d.getIndicador_ps_item();
                String d6 = d.getIndicador_trans_grat();
                String d7 = d.getIndicador_afectacion_item();
                String d8 = d.getValor_venta_unitaria();
                String d9 = d.getPrecio_venta_unitario_item();
                String d10 = d.getCantidad_item();
                String d11 = d.getDescuento_item();
                String d12 = d.getValor_item();
                String d13 = d.getIgv_total_item();
                String d14 = d.getTotal_item();
                
                System.out.println(d1);
                System.out.println(d2);
                System.out.println(d3);
                System.out.println(d4);
                System.out.println(d5);
                System.out.println(d6);
                System.out.println(d7);
                
                System.out.println(d8);
                System.out.println(d9);
                System.out.println(d10);
                System.out.println(d11);
                System.out.println(d12);
                System.out.println(d13);
                System.out.println(d14);
                
                indDet++;
                System.out.println("..................");
            }
            indCab++;
            System.out.println("*****************************");
        }
    }
    
    private static String formatearNroDoc(String numCorrelativo, int numDigitos) {
        return formatearCeros(numCorrelativo, numDigitos);
    }
    
//    private static String formatearNroDoc(String numSerie, String numCorrelativo, int numDigitos) {
//        return formatearCeros(numSerie, 3) + "-" + formatearCeros(numCorrelativo, numDigitos);
//    }
    
    private static String formatearCeros(String numero, int cantidadDigitos) {
        String formateado = numero;
        int longitudCeros = cantidadDigitos - numero.length();
        for ( int i = 0; i < longitudCeros; i++ ) {
            formateado = "0" + formateado;
        }
        return formateado;
    }
    
    // prefijo: F, B, NC, ND
    private String formatearNroSerie(String prefijo, String nroSerie) {
        return prefijo + formatearNroSer(prefijo, nroSerie);
        // String nroDoc = iuf.tx_doc.getText(); // N° Factura
        //guia.setNroFactura(tipDoc + formatearNroSerie(nroSerie) + "-" + nroDoc);
    }
    
    private String formatearNroSer(String prefijo, String serie) {
        String nroSerie = "";
        int largo = 3 - serie.length();;
        
        // Se agregó este if para poder obtener FC01(Nota de credito a partir de Factura) y F001 (Factura)
        if ( prefijo.length() == 2 ) {
            largo = 2 - serie.length();
        }
        
        for ( int i = 0; i < largo; i++ ) {
            nroSerie = nroSerie + "0";
        }
        nroSerie = nroSerie + serie;
        return nroSerie;
    }
    
    private String getLeyenda(String montoPagar, String moneda) {
        String glosa = "PEN";
        if ( "S/.".equals(moneda) ) {
            glosa = "PEN";
        } else if ( "US$".equals(moneda) ) {
            glosa = "USD";
        }
        double mp = Double.parseDouble(montoPagar);
        return Billete.BilleteX(mp, "USD".equals(glosa) ? "DÓLARES AMERICANOS" : " SOLES");
    }
    
    private String getLeyenda(String montoPagar) {
        double mp = Double.parseDouble(montoPagar);
        System.out.println("leyenda:" + mp);
        String bill = Billete.BilleteX(mp);
        System.out.println("bill:" + bill);
        return Billete.BilleteX(mp);
    }
    
    public List Listar_AniosReporteFlujoComparativo() {
        return dao.Listar_AniosReporteFlujoComparativo();
    }
    
}
