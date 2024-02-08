package Presentacion.Importaciones;
import Entidades.Importaciones.Ordenacion;
import Entidades.Importaciones.SugeridoRAM;
import Entidades.Cabecsugerido;
import Servicios.Importaciones.Servicio_Repuestos;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Entidades.Detallesugerido;
import Entidades.Repuestos;
import Servicios.Importaciones.Servicio_CabSugerido;
import java.util.Iterator;
import javax.swing.SwingConstants;
import Servicios.Importaciones.Servicio_Control;
import Servicios.Importaciones.Servicio_DetSugerido;
import Servicios.Importaciones.Servicio_DetalleES;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
public class IU_ActualizarSugerido extends javax.swing.JFrame {
    static private FREP024 menu;
    private Servicio_Repuestos sr;
    private Servicio_CabSugerido scs;
    private Servicio_DetalleES sdetEs;
    private ArrayList<SugeridoRAM> tablaRAM;
    SugeridoRAM sugTabla;
    private boolean filtrarActivo=true;
    static private int opcion;
    private int tipoActualizacion;
    private ArrayList<SugeridoRAM> tablaRAMBuscarFiltrar;
    static private int idCabSugerido;
    public IU_ActualizarSugerido(FREP024 m){
        initComponents();
        Ordenacion ord=new Ordenacion();
        ord.eventoTabla(this);
        menu=m;
        alinearColumnasDerecha();
        opcion=2;
    }
    public IU_ActualizarSugerido(FREP024 m,int idCabSug) {
        initComponents();
        Ordenacion ord=new Ordenacion();
        ord.eventoTabla(this);
        menu=m;
        opcion=2;
        alinearColumnasDerecha();
        idCabSugerido=idCabSug;
        scs=new Servicio_CabSugerido();
        if(scs.esSugerido(idCabSug)){
            setTitle("ACTUALIZAR SUGERIDO");
            lblSugerido.setText("SUGERIDO DE COMPRAS");
            lblNumSug.setText("N° Sugerido:");
            tipoActualizacion=1;
        }else if(scs.esPedido(idCabSug)){
            setTitle("ACTUALIZAR PEDIDO");
            lblSugerido.setText("PEDIDO DE COMPRAS");
            lblNumSug.setText("N° Pedido:");
            tipoActualizacion=2;
        }
        setVisible(true);
        tx_id.setText(String.valueOf(idCabSug));
        Servicio_Control sc=new Servicio_Control();
        txt_mesProy.setText(String.valueOf(sc.getControlUnico().getMesproyeccion()));
        txtMesesDemanda.setText(String.valueOf(sc.getControlUnico().getMesdemanda()));
        tablaRAMBuscarFiltrar=new ArrayList();
        sdetEs=new Servicio_DetalleES(this);
        
        tablaRAMBuscarFiltrar=sdetEs.cargaTablaRAMBF(this);
        Servicio_DetSugerido sds=new Servicio_DetSugerido(this);
        setTablaRAM(sds.cargaTabla(idCabSug));
    }
    public ArrayList getTablaRAMBuscarFiltrar(){
        return tablaRAMBuscarFiltrar;
    }
    public void formatearDecimalDerechaEnTextField(JTextField txt,double valor){
         String moneyCad="";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        moneyCad=formateador.format(valor);
        int indice=moneyCad.indexOf(".");
        if(indice==-1){
            moneyCad=moneyCad+".00";
        }else if(moneyCad.length()-indice==2){
            moneyCad=moneyCad+"0";
        }
        txt.setText(moneyCad);
    }
    private void alinearColumnasDerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tb_sugerido.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tb_sugerido.getColumnModel().getColumn(5).setCellRenderer(tcr);
        tb_sugerido.getColumnModel().getColumn(6).setCellRenderer(tcr);
        tb_sugerido.getColumnModel().getColumn(7).setCellRenderer(tcr);
        tb_sugerido.getColumnModel().getColumn(8).setCellRenderer(tcr);
        tb_sugerido.getColumnModel().getColumn(9).setCellRenderer(tcr);
        tb_sugerido.getColumnModel().getColumn(10).setCellRenderer(tcr);
        tb_sugerido.getColumnModel().getColumn(11).setCellRenderer(tcr);
    }
    public ArrayList getTablaRAM(){
        return tablaRAM;
    }
    public void setTablaRAM(ArrayList tabla){
        tablaRAM=tabla;
    }
    public void setTablaRAMFB(ArrayList tabla){
        tablaRAMBuscarFiltrar=tabla;
    }
    public void actualizarFobTotal(){
        double fobTot=0.00;
        for(int i=0;i<tb_sugerido.getRowCount();i++){
            int cantEntregada;
            if(tb_sugerido.getValueAt(i,4)==null){
                cantEntregada=0;
            }else{
                cantEntregada=Integer.parseInt(quitaComa(String.valueOf(tb_sugerido.getValueAt(i,5))));
            }
            double fob;
            if(tb_sugerido.getValueAt(i,10)==null){
                fob=0.00;
            }else{
                fob=Double.parseDouble(String.valueOf(tb_sugerido.getValueAt(i,10)));
            }

            formatearColumnaDerecha(fob, i,10);
            
            fobTot+=cantEntregada*fob;
        }
        formatearDecimalDerechaEnTextField(txt_Tot_Fob,fobTot);
        
    }
    public String quitaComa(String numero){
        int c= numero.length();
        String cad="";
        for (int contador =0; contador<c; contador++){
            if (numero.charAt(contador)==','){
                numero.replace(',',' ');
            }else{
                cad+=numero.charAt(contador);
            }
        }
        return cad;
    }
    private void formatearColumnaDerecha(double valor, int fila, int columna){
        String moneyCad="";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        moneyCad=formateador.format(valor);
        int indice=moneyCad.indexOf(".");
        if(indice==-1){
            moneyCad=moneyCad+".00";
        }else if(moneyCad.length()-indice==2){
            moneyCad=moneyCad+"0";
        }
        tb_sugerido.setValueAt(moneyCad,fila,columna);
    }
    public void llenarListaRAM(){
        tablaRAM = new ArrayList();
        sugTabla=new SugeridoRAM();
        String[] cad=new String[9];
        sr=new Servicio_Repuestos();
        Repuestos rep=new Repuestos();
        for(int i=0;i<tb_sugerido.getRowCount();i++){
            for(int j=0;j<tb_sugerido.getColumnCount();j++){
                cad[j]=String.valueOf(tb_sugerido.getValueAt(i, j));
                System.out.println(cad[j]);
            }
            sugTabla.setNumParte(cad[0]);
            sugTabla.setCodSecundario(cad[1]);
            sugTabla.setDescripcion(cad[2]);
            sugTabla.setAplicacion(cad[3]);
            sugTabla.setCantPedida(Integer.parseInt(cad[4]));
            if(cad[5].equals("null")){   cad[5]="0";    }
            sugTabla.setStockFisico(Integer.parseInt(cad[5]));
            if(cad[6].equals("null")){   cad[6]="0";   }
            else if(cad[6].indexOf(".")!=-1){ cad[6]="0"; }
            sugTabla.setDemanda(Integer.parseInt(cad[6]));
            if(cad[7].equals("null")){   cad[7]="0";   }
            sugTabla.setStockSugerido(Integer.parseInt(cad[7]));
            sugTabla.setFob(Double.parseDouble(cad[8]));

            rep=sr.getRepuesto(sugTabla.getNumParte());
            sugTabla.setIdrepuesto(rep.getId().getIdrepuesto());
            sugTabla.setIdlinea(rep.getId().getIdequipo());
            tablaRAM.add(sugTabla);
        }
    }
    public void mostrarListaRAM(){
        if(tablaRAM!=null){
            Iterator<SugeridoRAM> puntero=tablaRAM.iterator();
            int i=0;
            while(puntero.hasNext()){
                SugeridoRAM sug=puntero.next();
                i++;
            }
        }
    }
    public void mostrarListaRAMFB(){
        Iterator<SugeridoRAM> puntero=tablaRAMBuscarFiltrar.iterator();
        int i=0;
        while(puntero.hasNext()){
            SugeridoRAM sug=puntero.next();
            i++;
        }
    }
    public FREP024 getMenuOrigen(){  return menu;  }
    public int getOpcion(){ return this.opcion; }
    private void cargarIdSugerido(){
        scs=new Servicio_CabSugerido(this);
        tx_id.setText(String.valueOf(scs.nextId()));
    }   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnFiltrar = new javax.swing.JButton();
        lblSugerido = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        txt_mesProy = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        txtMesesDemanda = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tx_id = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_sugerido = new javax.swing.JTable();
        txt_Tot_Fob = new javax.swing.JTextField();
        lblNumSug = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CREAR SUGERIDO DE PEDIDO DE IMPORTACIÒN");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        lblSugerido.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        lblSugerido.setText("SUGERIDO DE COMPRAS");

        btnAgregar.setMnemonic('c');
        btnAgregar.setText("Agregar");
        btnAgregar.setToolTipText("Agrega nuevo Banco");
        btnAgregar.setPreferredSize(new java.awt.Dimension(92, 32));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel10.setText("Meses de Proyección:");

        btnModificar.setMnemonic('A');
        btnModificar.setText("Modificar");
        btnModificar.setToolTipText("Actualiza la informacion de Bancos");
        btnModificar.setPreferredSize(new java.awt.Dimension(92, 32));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        txt_mesProy.setEditable(false);
        txt_mesProy.setBackground(new java.awt.Color(255, 255, 255));
        txt_mesProy.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_mesProy.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        txtMesesDemanda.setEditable(false);
        txtMesesDemanda.setBackground(new java.awt.Color(255, 255, 255));
        txtMesesDemanda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMesesDemanda.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel1.setText("Meses de Demanda:");

        jLabel11.setText("TOTAL FOB:");

        tx_id.setEditable(false);
        tx_id.setBackground(new java.awt.Color(255, 255, 255));
        tx_id.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_id.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tb_sugerido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº PARTE▲▼", "COD. SEC.▲▼", "DESCRIPCION▲▼", "APLICACION", "CANT. PEDIDA", "ENTREGADA", "PENDIENTE", "STOCK FISICO", "DEMANDA", "SUGERIDO", "FOB", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_sugerido);
        if (tb_sugerido.getColumnModel().getColumnCount() > 0) {
            tb_sugerido.getColumnModel().getColumn(0).setPreferredWidth(30);
            tb_sugerido.getColumnModel().getColumn(1).setPreferredWidth(50);
            tb_sugerido.getColumnModel().getColumn(2).setPreferredWidth(50);
            tb_sugerido.getColumnModel().getColumn(3).setPreferredWidth(50);
            tb_sugerido.getColumnModel().getColumn(4).setPreferredWidth(10);
            tb_sugerido.getColumnModel().getColumn(5).setPreferredWidth(10);
            tb_sugerido.getColumnModel().getColumn(6).setPreferredWidth(10);
            tb_sugerido.getColumnModel().getColumn(7).setPreferredWidth(10);
            tb_sugerido.getColumnModel().getColumn(8).setPreferredWidth(10);
            tb_sugerido.getColumnModel().getColumn(9).setPreferredWidth(10);
            tb_sugerido.getColumnModel().getColumn(10).setPreferredWidth(10);
            tb_sugerido.getColumnModel().getColumn(11).setPreferredWidth(15);
        }

        txt_Tot_Fob.setEditable(false);
        txt_Tot_Fob.setBackground(new java.awt.Color(255, 255, 255));
        txt_Tot_Fob.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_Tot_Fob.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblNumSug.setText("N° Sugerido:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblSugerido, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNumSug, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx_id, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(31, 31, 31)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(35, 35, 35)
                            .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_Tot_Fob, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMesesDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(61, 61, 61)
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_mesProy, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSugerido)
                    .addComponent(tx_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_mesProy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMesesDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10)
                    .addComponent(lblNumSug))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_Tot_Fob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dispose();
    }//GEN-LAST:event_formWindowClosing
    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        frmiFiltrarSugerido filtrar=new frmiFiltrarSugerido(this,getTablaRAM());
        filtrar.setVisible(true);
    }//GEN-LAST:event_btnFiltrarActionPerformed
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        IU_NuevoRepuesto iu=new IU_NuevoRepuesto(this);
        iu.setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int filaSeleccionada=tb_sugerido.getSelectedRow();
        if(filaSeleccionada!=-1){
            String numParte=String.valueOf(tb_sugerido.getModel().getValueAt(filaSeleccionada,0));
            frmiModificarCantPedida modificar=new frmiModificarCantPedida(this,numParte,filaSeleccionada,tablaRAM,tipoActualizacion);
            modificar.setVisible(true);
            if(tablaRAM.size()==tb_sugerido.getRowCount()){
                filtrarActivo=true;
                habilitarFiltrar();
            }else{
                filtrarActivo=false;
            }
        }else{
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(null, "Seleccione un repuesto de la tabla","ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
        }
        actualizarFobTotal();
    }//GEN-LAST:event_btnModificarActionPerformed
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        frmiBuscarSugerido hijoBuscar=new frmiBuscarSugerido(this,getTablaRAM(),this.tablaRAMBuscarFiltrar);
        hijoBuscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed
    public void actualizaTotal(double total){
        this.txt_Tot_Fob.setText(String.valueOf(total));
    }
    public void habilitarFiltrar(){
        DefaultTableModel temp = (DefaultTableModel) tb_sugerido.getModel();        
        if(temp.getRowCount()==0){
            btnFiltrar.setEnabled(false);
        }else{
            btnFiltrar.setEnabled(true);
        }
    }
    public void activarFiltrado(){
        filtrarActivo=true;
    }
    public int contarFilasSugeridoConCantPedida(){
        int contador=0;
        SugeridoRAM sug;
        Iterator<SugeridoRAM> puntero=tablaRAM.iterator();
        while(puntero.hasNext()){
            sug=puntero.next();
            if(sug.getCantPedida()!=0){
                contador++;
            }
        }
        return contador;
    }
    public boolean registrarDetalleSugeridos(Cabecsugerido cs){
        boolean registro=true;
        Servicio_DetSugerido sds=new Servicio_DetSugerido();
        SugeridoRAM sug;
        Iterator<SugeridoRAM> puntero=tablaRAM.iterator();
        while(puntero.hasNext()){
            sug=puntero.next();
            if(sug.getCantPedida()!=0){
                Detallesugerido ds=new Detallesugerido();
                Repuestos rep=new Repuestos();
                rep=sr.getRepuesto(sug.getNumParte());
                int idDetSugerido=sds.nextId();
                ds.setIddetallesugerido(idDetSugerido);
                ds.setRepuestos(rep);//idrepuesto, idlinea
                ds.setCabecsugerido(cs);//idsugerido
                ds.setCantpedida(sug.getCantPedida());
                ds.setValorfob(sug.getFob());
                ds.setStocksugerido(sug.getStockSugerido());
                ds.setDemandaprom(sug.getDemanda());
                ds.setTotDemanda(sug.getTotDemanda());
                if(!sds.registrarDetalleSugerido(ds)){
                    registro=false;break;
                }
            }
        }
        return registro;
    }
    public void registrarSugerido(){
        if(contarFilasSugeridoConCantPedida()!=0){
            if (JOptionPane.showConfirmDialog(this, "¿Está seguro de registrar el \nPedido de Sugerido ?", 
                    "Confirmacion", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION) {
                Cabecsugerido cs=new Cabecsugerido();
                int idSugerido=Integer.parseInt(tx_id.getText());
                cs.setIdsugerido(idSugerido);
                cs.setImportadores(null);
                if(scs.registrarCabecSugerido(cs)){
                    if(registrarDetalleSugeridos(cs)){
                        JOptionPane jop = new JOptionPane();
                        jop.showMessageDialog(null, "Sugerido registrado con éxito",
                        "EXITO", JOptionPane.INFORMATION_MESSAGE); 
                    }
                }
            }    
        }
        menu=new FREP024();
        menu.setVisible(true);
        dispose();
    }
    public void limpiaTabla(){
       try{
           DefaultTableModel temp = (DefaultTableModel) tb_sugerido.getModel();
           int a =temp.getRowCount();
           for(int i=0; i<a; i++)
               temp.removeRow(0);
       }catch(Exception e){
           System.out.println(e);
       }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnFiltrar;
    public javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    public javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNumSug;
    private javax.swing.JLabel lblSugerido;
    public javax.swing.JTable tb_sugerido;
    public javax.swing.JTextField tx_id;
    public javax.swing.JTextField txtMesesDemanda;
    public javax.swing.JTextField txt_Tot_Fob;
    public javax.swing.JTextField txt_mesProy;
    // End of variables declaration//GEN-END:variables
    public void limpiarTablaRAM() {
        tablaRAM.clear();
    }
}