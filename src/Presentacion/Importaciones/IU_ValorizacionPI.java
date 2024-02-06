package Presentacion.Importaciones;

import Entidades.Control;
import Entidades.Detallesugerido;
import Entidades.Otros.Monetario;
import Entidades.Repuestos;
import Servicios.Importaciones.Servicio_Control;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Servicios.Importaciones.Servicio_DetSugerido;
import Servicios.Importaciones.Servicio_DetalleES;
import Servicios.Importaciones.Servicio_Repuestos;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JTable;

public class IU_ValorizacionPI extends javax.swing.JFrame {
    static IU_Valorizacion iuvaloriza;
    private double valorTotalImportacion;
    private double pedidoValorizado;
    private int idSugerido;
    private List lista;
    private double []costosNuevos;
    private double [] valoresFob;
    double factorValorizacion;

    public IU_ValorizacionPI(IU_Valorizacion iuval) {
        initComponents();
        valorTotalImportacion = 0.00;
        pedidoValorizado = 0.00;
        factorValorizacion = 0.00;
        iuvaloriza = iuval;
        cargarTabla();
        iuvaloriza.setEnabled(false);
        idSugerido = Integer.parseInt(iuvaloriza.txtNumPedido.getText());
        txtFacturaImportacion.setText(iuval.txtNumFactImportacion.getText());
        txtPedidoValorizado.setText("0.00");
        inicializarValores(idSugerido);
        asignarMoneda();
    }
    
    private void asignarMoneda() {
        Servicio_Control sc = new Servicio_Control();
        Control c = sc.getControlUnico();
        String moneda = Monetario.asignarMoneda(c.getMonedarepuestos());
        lblTotCostos.setText(moneda);
        lblTotImp.setText(moneda);
        lblTotal.setText(moneda);
    }
    
    private void alinearColumnasDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tbValorizacionPI.getColumnModel().getColumn(1).setCellRenderer(tcr);
        tbValorizacionPI.getColumnModel().getColumn(2).setCellRenderer(tcr);
        tbValorizacionPI.getColumnModel().getColumn(3).setCellRenderer(tcr);
        tbValorizacionPI.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tbValorizacionPI.getColumnModel().getColumn(5).setCellRenderer(tcr);
    }
    
    public int getIdSugerido(){
        return idSugerido;
    }
    
    public void inicializarValores(int idsugerido){
//        System.out.println("idsugerido:" + idsugerido);
        String tiString = iuvaloriza.txtTotalImportacion.getText();
        String vfiString = iuvaloriza.txtValorFobImp.getText();
        
        double ti = Double.parseDouble(iuvaloriza.quitaComa(tiString));
        double vfi = Double.parseDouble(iuvaloriza.quitaComa(vfiString));
        double sumaImpuestos = ti - vfi;
//        if ( iuvaloriza.rbGastosAdmin.isSelected() ) {
//            Servicio_Tramites st = new Servicio_Tramites();
//            sumaImpuestos = st.getImporteTotal(idsugerido);
//            
//        } else if ( iuvaloriza.rbFactorCosto.isSelected() ) {
//            sumaImpuestos = st.getImporteTotal(idsugerido);
//        }
        
        Servicio_DetSugerido sds = new Servicio_DetSugerido();
        lista = sds.getDetalleSugParaValorizar(idsugerido);
        if ( !lista.isEmpty() ) {
            System.out.println("tamaño de lista:" + lista);
        }
        Iterator ite = lista.iterator();
        Object[] row = new Object[5];
        double sumaCantPedidaXValorFob = 0.00;
        valoresFob = new double[lista.size()];
        int [] cantPedidas = new int[lista.size()];
        int i = 0;
        double sumaTotAnt = 0.0;
        
        while ( ite.hasNext() ) {
            Object [] objRep = (Object []) ite.next();
            if ( objRep[1].equals("null") ) {   objRep[1] = 0;    }
            if ( objRep[2].equals("null") ) {   objRep[2] = "";   }
            if ( objRep[3].equals("null") ) {   objRep[3] = 0.0;    }
            row[0] = objRep[1];
            row[1] = getComaMillar(objRep[2]);
            valoresFob[i] = objADouble(objRep[3]);
            row[2] = valoresFob[i];
            cantPedidas[i] = objAInteger(objRep[2]);
            sumaCantPedidaXValorFob += cantPedidas[i] * valoresFob[i];
            
            double totAnt = Double.parseDouble(String.valueOf(objRep[2])) * Double.parseDouble(String.valueOf(row[2]));
            row[3] = totAnt;
            sumaTotAnt += totAnt;
            DefaultTableModel table = (DefaultTableModel) tbValorizacionPI.getModel();
            table.addRow(row);
            formatearColumnaDerecha(valoresFob[i], i, 2);
            formatearColumnaDerecha(totAnt, i, 3);
            i++;
        }
        valorTotalImportacion = sumaCantPedidaXValorFob + sumaImpuestos;
        formatearDecimalDerechaEnTextField(txtTotImpuestos, sumaImpuestos);
        formatearDecimalDerechaEnTextField(txtTotCostos, sumaTotAnt);
        double tot = sumaImpuestos + sumaTotAnt;
        formatearDecimalDerechaEnTextField(txtTotal, tot);
        factorValorizacion = 0.00;
        
        if ( sumaCantPedidaXValorFob != 0 ) {
            factorValorizacion = valorTotalImportacion / sumaCantPedidaXValorFob;
        }
        //System.out.println("Factor de valorizacion:"+factorValorizacion);
        double costoNuevo = 0.00;
        costosNuevos = new double[lista.size()];
        double costoTotal = 0.00;
        alinearColumnasDerecha();
        i = 0;
        lista = sds.getDetalleSugParaValorizar(idsugerido);
        ite = lista.iterator();
        List<Detallesugerido> lstDetSug = new ArrayList<Detallesugerido>();
        
        while ( ite.hasNext() ) {
            Object [] objRep = (Object []) ite.next();
            costoNuevo = redondearA4Decimales(valoresFob[i] * factorValorizacion);
            costosNuevos[i] = costoNuevo;
            Detallesugerido ds = new Detallesugerido();
            ds = sds.buscaDetSugerido(Integer.parseInt(String.valueOf(objRep[5])));
            ds.setValorcosto(costoNuevo);
//            if(!sds.actualizarDetSugerido(ds)){
//                System.out.println("Error al actualizar detalleSugerido");
//            }
            lstDetSug.add(ds);
            costoTotal = cantPedidas[i] * costoNuevo;
            tbValorizacionPI.setValueAt(redondearA2Decimales(costoNuevo), i, 4);
            pedidoValorizado += redondearA2Decimales(costoNuevo * cantPedidas[i]);
            formatearColumnaDerecha(redondearA2Decimales(costoNuevo), i, 4);
            formatearColumnaDerecha(costoTotal, i, 5);//COSTOS TOTALES
            i++;
        }
        System.out.println("TAMAÑO DE LISTA DE SUGERIDO : " + lstDetSug.size());
        if ( !lstDetSug.isEmpty() ) {
            sds.actualizarListaDetSugerido(lstDetSug);
        }
        formatearDecimalDerechaEnTextField(txtPedidoValorizado, pedidoValorizado);
        verDiferencia();
    }
    
    public String getTotalCostos(){
        return txtTotCostos.getText();
    }
    
    public JTable getTablaValorizcionPI(){
        return this.tbValorizacionPI;
    }
    
    private void formatearDecimalDerechaEnTextField(JTextField txt,double valor){
        String moneyCad = "";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        moneyCad = formateador.format(valor);
        int indice = moneyCad.indexOf(".");
        
        if ( indice == -1 ) {
            moneyCad = moneyCad + ".00";
            
        } else if ( moneyCad.length() - indice == 2 ) {
            moneyCad = moneyCad + "0";
        }
        txt.setText(moneyCad);
    }
    
    private String getComaMillar(Object valor){
        String cantStr = String.valueOf(valor);
        int cantInt = Integer.parseInt(cantStr);
        int cifMillar, cifResto;
        
        if ( cantStr.length() > 3 && Integer.parseInt(cantStr) > 0 ) {
            cifMillar = cantInt / 1000;
            cifResto = cantInt % 1000;
            
            if ( cifResto == 0 && cifMillar != 0 ) {
                cantStr = cifMillar + ",000";
                
            } else if ( cifResto / 100 != 0 ) {
                cantStr = cifMillar + "," + cifResto;
                
            } else if ( cifResto / 10 != 0 ) {
                cantStr = cifMillar + ",0" + cifResto;
                
            } else {
                cantStr = cifMillar + ",00" + cifResto;
            }
        } else if ( cantStr.length() > 4 && Integer.parseInt(cantStr) < 0 ) {
            cifMillar = cantInt / 1000;
            cifResto = cantInt % 1000;
            
            if ( cifResto == 0 && cifMillar != 0 ) {
                cantStr = cifMillar + ",000";
                
            } else if ( cifResto / 100 != 0 ) {
                cantStr = cifMillar + "," + Math.abs(cifResto);
                
            } else if ( cifResto / 10 != 0 ) {
                cantStr = cifMillar + ",0" + Math.abs(cifResto);
                
            } else {
                cantStr = cifMillar + ",00" + Math.abs(cifResto);
            }
        }
        return cantStr;
    }
    
    public double getFactorValorizacion(){
        return factorValorizacion;
    }
    
    private void formatearColumnaDerecha(double valor, int fila, int columna){
        String moneyCad = "";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        moneyCad = formateador.format(valor);
        int indice = moneyCad.indexOf(".");
        
        if ( indice == -1 ) {
            moneyCad = moneyCad + ".00";
            
        } else if ( moneyCad.length() - indice == 2 ) {
            moneyCad = moneyCad + "0";
        }
        tbValorizacionPI.setValueAt(moneyCad,fila,columna);
    }
    
    private double redondearA4Decimales(double numero){
        double newNumber = Math.round(numero * 10000.00) / 10000.00;
        return newNumber;
    }
    
    private double redondearA2Decimales(double numero){
        double newNumber = Math.round(numero * 100.00) / 100.00;
        return newNumber;
    }
    
    public void setCostosPromedios(List lista,double[] costoNuevo,double[] costoUnitario){
        System.out.println("setCostosPromedios...");
        Iterator iteDet = lista.iterator();
        int i = 0;
        
        while ( iteDet.hasNext() ) {
            Object [] objRep = (Object []) iteDet.next();
            int idrepuesto = Integer.parseInt(String.valueOf(objRep[0]));
            int idlinea = Integer.parseInt(String.valueOf(objRep[4]));
            Servicio_Repuestos sr = new Servicio_Repuestos();
            Repuestos repAnt = sr.getRepuesto(idlinea,idrepuesto);
            int stock = 0;
            
            if ( repAnt.getStock() != null ) {
                stock = getValida(repAnt.getStock());
            }
            
            double costoPromedio = 0.0;
            
            if ( repAnt.getCostopromedio() != null ) {
                costoPromedio = getValida(repAnt.getCostopromedio());
            }
            int cantPedida = getValida(Integer.parseInt(String.valueOf(objRep[2])));
            double costoPromNew = getCostoPromedio(stock,costoPromedio,cantPedida,costoNuevo[i]);
            costoNuevo[i] = redondearA2Decimales(costoNuevo[i]);
            actualizarRepuesto(repAnt, costoNuevo[i], costoUnitario[i], costoPromNew);
            i++;
        }
    }
    
    private void actualizarRepuesto(Repuestos rep,double costNew, double costUnit, double costProm){
        Servicio_Repuestos sr = new Servicio_Repuestos();
        sr.actualizarRepuestos(rep, costNew, costUnit, costProm);//Pcostoultimo, Fobultimo,Costopromedio
    }
    
    public void setListaCostosPromedios(List lista,double[] costoNuevo,double[] costoUnitario){
        System.out.println("setListaCostosPromedios...");
        Iterator iteDet = lista.iterator();
        int i = 0;
        
        List<Repuestos> lstRepActualizados = new ArrayList<Repuestos>();
        
        while ( iteDet.hasNext() ) {
            Object [] objRep = (Object []) iteDet.next();
            int idrepuesto = Integer.parseInt(String.valueOf(objRep[0]));
            int idlinea = Integer.parseInt(String.valueOf(objRep[4]));
            Servicio_Repuestos sr = new Servicio_Repuestos();
            Repuestos repAnt = sr.getRepuesto(idlinea,idrepuesto);
            int stock = 0;
            
            if ( repAnt.getStock() != null ) {
                stock = getValida(repAnt.getStock());
            }
            
            double costoPromedio = 0.0;
            
            if ( repAnt.getCostopromedio() != null ) {
                costoPromedio = getValida(repAnt.getCostopromedio());
            }
            int cantPedida = getValida(Integer.parseInt(String.valueOf(objRep[2])));
            double costoPromNew = getCostoPromedio(stock,costoPromedio,cantPedida,costoNuevo[i]);
            costoNuevo[i] = redondearA2Decimales(costoNuevo[i]);
            
            repAnt.setPcostoultimo(costoNuevo[i]);
            repAnt.setFobultimo(costoUnitario[i]);
            repAnt.setCostopromedio(costoPromNew);
//            actualizarRepuesto(repAnt, costoNuevo[i], costoUnitario[i], costoPromNew);
            lstRepActualizados.add(repAnt);
            i++;
        }
        if ( actualizarListaRepuestos(lstRepActualizados) ) {
            System.out.println("lista de repuestos actualizada...");
        } else {
            System.out.println("error al actualizar lista de precios");
        }
    }
    
    private boolean actualizarListaRepuestos(List<Repuestos> listaRep){
        Servicio_Repuestos sr = new Servicio_Repuestos();
        return sr.actualizarListaRepuestos(listaRep);
    }
    
    private double getCostoPromedio(int stock, double costoPromedio, int cantPedida, double newCost){
        double costProm = 0.0;
        costProm = ( (stock * costoPromedio) + ( cantPedida * newCost ) ) / ( stock + cantPedida );
        costProm = redondearA2Decimales(costProm);
        return costProm;
    }
    
    private int getValida(int num){
        if ( String.valueOf(num).equals("null") ) {
            return 0;
        } else {
            return num;
        }
    }
    
    private double getValida(double num){
        if ( String.valueOf(num).equals("null") ) {
            return 0.0;
        } else {
            return num;
        }
    }
    
    public void verDiferencia(){
        if ( verificarDiferencia(valorTotalImportacion, pedidoValorizado) ) {
            btnIngresar.setEnabled(true);
            
        } else {
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(null, "Existe una diferencia mayor a la esperada, verifique impuestos",
                                  "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
            //existe una diferencia mayor a la esperada, verifique las cantidades.
            //System.out.println("Mayor a +1 ó -1");
            btnIngresar.setEnabled(false);
        }
    }
    
    private boolean verificarDiferencia(double v1, double v2){
        double dif = v1 - v2;
        boolean valor = false;
        System.out.println("v1:" + v1);
        System.out.println("v2:" + v2);
        System.out.println("diferencia:" + dif);
        /*
        if ( dif <= 1 && dif >= -1 ) {
            System.out.println("La diferencia de " + dif + " esta dentro de los valores permitidos");
        
        } else {
            System.out.println("La diferencia de " + dif + " NO CUMPLE los valores permitidos");
        }*/
        //dif = 1.3;
        if ( dif <= 1 && dif >= -1 ) {
            valor = true;
        }
        return valor;
    }
    
    private double objADouble(Object obj){
        return Double.parseDouble(String.valueOf(obj));
    }
    
    private int objAInteger(Object obj){
        return Integer.parseInt(String.valueOf(obj));
    }
    
    private void cargarTabla(){
        Servicio_DetSugerido sds = new Servicio_DetSugerido();
        int idsugerido = Integer.parseInt(iuvaloriza.txtNumPedido.getText());
        sds.cargarATablaValorizacion(idsugerido);
    }
    
    public List getLista(){
        return lista;
    }
    
    public double[] getCostosNuevos(){
        return costosNuevos;
    }
    
    public int getNumCostNews(){
        return costosNuevos.length;
    }
    
    public double[] getValoresFob(){
        return valoresFob;
    }
    
    public IU_Valorizacion getIU_Valorizacion(){
        return iuvaloriza;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtFacturaImportacion = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTotImpuestos = new javax.swing.JTextField();
        txtPedidoValorizado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbValorizacionPI = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };
        jLabel2 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        txtTotCostos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblTotImp = new javax.swing.JLabel();
        lblTotCostos = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("VALORIZACIÓN DE PEDIDOS DE IMPORTACIÓN");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtFacturaImportacion.setEditable(false);
        txtFacturaImportacion.setBackground(new java.awt.Color(255, 255, 255));
        txtFacturaImportacion.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel3.setText("TOTAL DE COSTOS:");

        jLabel1.setText("N° FACTURA DE IMPORTACIÓN:");

        txtTotImpuestos.setEditable(false);
        txtTotImpuestos.setBackground(new java.awt.Color(255, 255, 255));
        txtTotImpuestos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtPedidoValorizado.setEditable(false);
        txtPedidoValorizado.setBackground(new java.awt.Color(255, 255, 255));
        txtPedidoValorizado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtPedidoValorizado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel4.setText("TOTAL IMPUESTOS:");

        tbValorizacionPI.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N° PARTE", "CANTIDAD", "FOB ANT.", "COST. TOT. ANT.", "COSTO NUEVO", "COST. TOT. NUEVO"
            }
        ));
        jScrollPane1.setViewportView(tbValorizacionPI);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("PEDIDO VALORIZADO:");

        txtTotal.setEditable(false);
        txtTotal.setBackground(new java.awt.Color(255, 255, 255));
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnIngresar.setText("NOTA DE INGRESO");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        txtTotCostos.setEditable(false);
        txtTotCostos.setBackground(new java.awt.Color(255, 255, 255));
        txtTotCostos.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("TOTAL:");

        lblTotImp.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotImp.setText("S/.");

        lblTotCostos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotCostos.setText("S/.");

        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("S/.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFacturaImportacion, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnIngresar)
                                .addGap(18, 18, 18)
                                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTotImp, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblTotCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtTotImpuestos)
                                            .addComponent(txtTotCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(49, 49, 49)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPedidoValorizado, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 208, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtFacturaImportacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtTotCostos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtTotImpuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTotImp)))
                            .addComponent(lblTotCostos))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPedidoValorizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotal))
                        .addContainerGap(55, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        iuvaloriza.setVisible(true);
        iuvaloriza.setEnabled(true);
        dispose();
    }//GEN-LAST:event_formWindowClosing
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        iuvaloriza.setVisible(true);
        iuvaloriza.setEnabled(true);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        Servicio_DetalleES sdet = new Servicio_DetalleES();
        int opc = 1;
        int idSuger = Integer.parseInt(this.iuvaloriza.txtNumPedido.getText());
        IU_GeneracionNotaIngreso notaIng;
        
        if ( !sdet.seRegistro(idSuger) ) { //registro
            System.out.println("Nota de Ingreso en proceso...");
            notaIng = new IU_GeneracionNotaIngreso(this, opc);
            notaIng.setVisible(true);
            this.dispose();
            
        } else {//Informativo
            opc = 2;
            System.out.println("Informativo en ValorizacionPI");
            notaIng = new IU_GeneracionNotaIngreso(this, opc);
            notaIng.setVisible(true);
            notaIng.txtMotivo.setText(sdet.getMotivo(idSuger));
            notaIng.txtMotivo.setEditable(false);
            this.dispose();
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTotCostos;
    private javax.swing.JLabel lblTotImp;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tbValorizacionPI;
    private javax.swing.JTextField txtFacturaImportacion;
    private javax.swing.JTextField txtPedidoValorizado;
    private javax.swing.JTextField txtTotCostos;
    private javax.swing.JTextField txtTotImpuestos;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}