package Presentacion.Importaciones;

import Entidades.Importaciones.SugeridoRAM;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class frmiFiltrarSugerido extends javax.swing.JFrame {
    static private IU_Sugerido iusug;
    static private IU_ActualizarSugerido iuactsug;
    static private ArrayList<SugeridoRAM> tablaRam;
    static private int opcion;
    
    frmiFiltrarSugerido(IU_Sugerido sug, ArrayList<SugeridoRAM> tabRam) {
        initComponents();
        iusug=sug;
        iusug.setEnabled(false);
        opcion=1;
        tablaRam=tabRam;
    }
    frmiFiltrarSugerido(IU_ActualizarSugerido actSug, ArrayList<SugeridoRAM> tabRam) {
        initComponents();
        iuactsug=actSug;
        iuactsug.setEnabled(false);
        opcion=2;
        tablaRam=tabRam;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgFiltro = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbCantidad = new javax.swing.JRadioButton();
        rbTodos = new javax.swing.JRadioButton();
        btnFiltrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("FILTRAR SUGERIDO");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bgFiltro.add(rbCantidad);
        rbCantidad.setSelected(true);
        rbCantidad.setText("POR CANTIDAD");
        rbCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbCantidadKeyPressed(evt);
            }
        });

        bgFiltro.add(rbTodos);
        rbTodos.setText("TODOS");
        rbTodos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rbTodosKeyPressed(evt);
            }
        });

        btnFiltrar.setText("FILTRAR");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbTodos)
                            .addComponent(rbCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(rbCantidad)
                .addGap(18, 18, 18)
                .addComponent(rbTodos)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
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
        if(opcion==1){
            iusug.setEnabled(true);
        }else if(opcion==2){
            iuactsug.setEnabled(true);
        }
        this.dispose();
    }//GEN-LAST:event_formWindowClosing
    private void rbCantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbCantidadKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            filtrar();
        }
    }//GEN-LAST:event_rbCantidadKeyPressed
    private void rbTodosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rbTodosKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            filtrar();
        }
    }//GEN-LAST:event_rbTodosKeyPressed
    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        filtrar();
        if(opcion==1){
            iusug.setEnabled(true);
        }else if(opcion==2){
            iuactsug.setEnabled(true);
        }
        dispose();
    }//GEN-LAST:event_btnFiltrarActionPerformed
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if(opcion==1){
            iusug.setEnabled(true);
        }else if(opcion==2){
            iuactsug.setEnabled(true);
        }
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    public ArrayList getTablaRAM(){
        return tablaRam;
    }
    
    public void filtrar(){
        String valor="";
        JOptionPane jop = new JOptionPane();
        int opc=0;
        if(rbCantidad.isSelected()){
            DefaultTableModel temp=null;
            Object obj[]=null;
            if(opcion==1){
                temp = (DefaultTableModel) iusug.tb_sugerido.getModel();
                obj=new Object[10];
            }
            if(opcion==2){
                temp = (DefaultTableModel) iuactsug.tb_sugerido.getModel();
                obj=new Object[12];
            }
            ArrayList tabRam=getTablaRAM();
            SugeridoRAM sug;
            Iterator<SugeridoRAM> puntero=tabRam.iterator();
            limpiarTabla();
            int i=0;
            double fobTot=0.00;
            int cantidad=0;
            while(puntero.hasNext()){
                sug=puntero.next();
                if(opcion==1){
                    cantidad=sug.getCantPedida();
                }else if(opcion==2){
                    cantidad=sug.getCantEntregada();
                }
                if(cantidad!=0){// ó sug.getFob()==0; //FALTA
                    obj[0]=sug.getNumParte();
                    if(sug.getCodSecundario().equals("null")){
                        obj[1]="";
                    }else{
                        obj[1]=sug.getCodSecundario();
                    }
                    obj[2]=sug.getDescripcion();
                    if(sug.getAplicacion().equals("null")){
                        obj[3]="";
                    }else{
                        obj[3]=sug.getAplicacion();
                    }
                    obj[4]=getComaMillar(sug.getCantPedida());
                    double totfob=0.00;
                    if(opcion==1){
                        obj[5]=getComaMillar(sug.getStockFisico());
                        obj[6]=getComaMillar(sug.getDemanda());
                        obj[7]=getComaMillar(sug.getStockSugerido());
                        obj[8]=sug.getFob();
                        totfob=Double.parseDouble(String.valueOf(sug.getCantPedida()))*sug.getFob();
                        obj[9]=totfob;
                    }else if(opcion==2){
                        obj[5]=getComaMillar(sug.getCantEntregada());
                        obj[6]=getComaMillar(sug.getCantPendiente());
                        obj[7]=getComaMillar(sug.getStockFisico());
                        obj[8]=getComaMillar(sug.getDemanda());
                        obj[9]=getComaMillar(sug.getStockSugerido());
                        obj[10]=sug.getFob();
                        totfob=Double.parseDouble(String.valueOf(sug.getCantEntregada()))*sug.getFob();
                        obj[11]=totfob;
                    }
                    temp.addRow(obj);
                    fobTot+=totfob;
                    double fob=Double.parseDouble(String.valueOf(sug.getFob()));
                    if(opcion==1){
                        formatearColumnaDerecha(fob,i,8);
                        formatearColumnaDerecha(totfob,i,9);
                    }else if(opcion==2){
                        formatearColumnaDerecha(fob,i,10);
                        formatearColumnaDerecha(totfob,i,11);
                    }
                    i++;
                }
            }
            if(opcion==1){
                iusug.txt_Tot_Fob.setText(String.valueOf(fobTot));
                formatearDecimalDerechaEnTextField(iusug.txt_Tot_Fob,fobTot);
            }else if(opcion==2){
                iuactsug.txt_Tot_Fob.setText(String.valueOf(fobTot));
                formatearDecimalDerechaEnTextField(iuactsug.txt_Tot_Fob,fobTot);
                ArrayList<SugeridoRAM> listaNew=new ArrayList<SugeridoRAM>();
                for(int j=0;j<iuactsug.getTablaRAM().size();j++){
                    ArrayList<SugeridoRAM> lista=iuactsug.getTablaRAM();
                    if(lista.get(j).getCantEntregada()!=0){
                        listaNew.add(lista.get(j));
                    }
                }
                iuactsug.setTablaRAM(listaNew);
            }
        }else if(rbTodos.isSelected()){
            opc=4;
            if(insertarRepuestosATabla(valor,opc)>0){
                if(opcion==1){
                    iusug.setEnabled(true);
                }else if(opcion==2){
                    iuactsug.setEnabled(true);
                }
            }else{
                jop.showMessageDialog(null, "No se encontraron repuestos en la BD","ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
//    public void filtrar(){
//        String valor = "";
//        JOptionPane jop = new JOptionPane();
//        int opc = 0;
//        
//        if ( rbCantidad.isSelected() ) {
//            DefaultTableModel temp = null;
//            Object obj[] = null;
//            
//            if ( opcion == 1 ) {
//                temp = (DefaultTableModel) iusug.tb_sugerido.getModel();
//                obj = new Object[10];
//            }
//            if ( opcion == 2 ) {
//                temp = (DefaultTableModel) iuactsug.tb_sugerido.getModel();
//                obj=new Object[12];
//            }
//            ArrayList tabRam = getTablaRAM();
//            SugeridoRAM sug;
//            Iterator<SugeridoRAM> puntero = tabRam.iterator();
//            limpiarTabla();
//            int i = 0;
//            double fobTot = 0.00;
//            int cantidad = 0;
//            
//            while ( puntero.hasNext() ) {
//                sug = puntero.next();
//                if ( opcion == 1 ) {
//                    cantidad = sug.getCantPedida();
//                    
//                } else if ( opcion == 2 ) {
//                    cantidad = sug.getCantEntregada();
//                }
//                double totfob = 0.00;
//                if ( opcion == 1 ) {
//                    if ( sug.getStockSugerido() > 0 ) {
//                        obj[0] = sug.getNumParte();
//
//                        if ( sug.getCodSecundario().equals("null") ) {
//                            obj[1] = "";
//
//                        } else {
//                            obj[1] = sug.getCodSecundario();
//                        }
//                        obj[2] = sug.getDescripcion();
//                        if ( sug.getAplicacion().equals("null") ) {
//                            obj[3] = "";
//
//                        } else {
//                            obj[3] = sug.getAplicacion();
//                        }
//                        obj[4] = getComaMillar(sug.getCantPedida());
//                        obj[5] = getComaMillar(sug.getStockFisico());
//                        obj[6] = getComaMillar(sug.getDemanda());
//                        obj[7] = getComaMillar(sug.getStockSugerido());
//                        obj[8] = sug.getFob();
//                        totfob = Double.parseDouble(String.valueOf(sug.getCantPedida())) * sug.getFob();
//                        obj[9] = totfob;
//
//                        temp.addRow(obj);
//                        fobTot += totfob;
//                        double fob = Double.parseDouble(String.valueOf(sug.getFob()));
//                        formatearColumnaDerecha(fob,i,8);
//                        formatearColumnaDerecha(totfob,i,9);
//                        i++;
//                    }
//
//                } else if ( opcion == 2 ) {
//
//                    if ( cantidad != 0 ) {// ó sug.getFob()==0; //FALTA
//                        obj[0] = sug.getNumParte();
//
//                        if ( sug.getCodSecundario().equals("null") ) {
//                            obj[1] = "";
//
//                        } else {
//                            obj[1] = sug.getCodSecundario();
//                        }
//                        obj[2] = sug.getDescripcion();
//                        if ( sug.getAplicacion().equals("null") ) {
//                            obj[3] = "";
//                        } else {
//                            obj[3] = sug.getAplicacion();
//                        }
//                        obj[4]=getComaMillar(sug.getCantPedida());
//                        obj[5] = getComaMillar(sug.getCantEntregada());
//                        obj[6] = getComaMillar(sug.getCantPendiente());
//                        obj[7] = getComaMillar(sug.getStockFisico());
//                        obj[8] = getComaMillar(sug.getDemanda());
//                        obj[9] = getComaMillar(sug.getStockSugerido());
//                        obj[10] = sug.getFob();
//                        totfob = Double.parseDouble(String.valueOf(sug.getCantEntregada())) * sug.getFob();
//                        obj[11] = totfob;
//
//                        temp.addRow(obj);
//                        fobTot += totfob;
//                        double fob = Double.parseDouble(String.valueOf(sug.getFob()));
//
//                        formatearColumnaDerecha(fob,i,10);
//                        formatearColumnaDerecha(totfob,i,11);
//
//                        i++;
//                    }
//                }
//            }
//            if ( opcion == 1 ) {
//                iusug.txt_Tot_Fob.setText(String.valueOf(fobTot));
//                formatearDecimalDerechaEnTextField(iusug.txt_Tot_Fob,fobTot);
//                
//            } else if ( opcion == 2 ) {
//                iuactsug.txt_Tot_Fob.setText(String.valueOf(fobTot));
//                formatearDecimalDerechaEnTextField(iuactsug.txt_Tot_Fob,fobTot);
//                ArrayList<SugeridoRAM> listaNew=new ArrayList<SugeridoRAM>();
//                
//                for ( int j = 0; j < iuactsug.getTablaRAM().size(); j++ ) {
//                    ArrayList<SugeridoRAM> lista=iuactsug.getTablaRAM();
//                    
//                    if ( lista.get(j).getCantEntregada() != 0 ) {
//                        listaNew.add(lista.get(j));
//                    }
//                }
//                iuactsug.setTablaRAM(listaNew);
//            }
//        } else if ( rbTodos.isSelected() ) {
//            opc = 4;
//            
//            if ( insertarRepuestosATabla(valor,opc) > 0 ) {
//                if ( opcion == 1 ) {
//                    iusug.setEnabled(true);
//                    
//                } else if ( opcion == 2 ) {
//                    iuactsug.setEnabled(true);
//                }
//            } else {
//                jop.showMessageDialog(null, "No se encontraron repuestos en la BD","ERROR", JOptionPane.ERROR_MESSAGE);
//            }
//        }
//    }
    
    public int insertarRepuestosATabla(String valor,int opc){
        int num=0;
        limpiarTabla();
        num=getTodosRepuestos();
        if(num!=0){
            if(opcion==1){
                iusug.activarFiltrado();
            }else if(opcion==2){
                iuactsug.activarFiltrado();
            }
        }
        return num;
    }
    public int getTodosRepuestos(){
        int contadorHallazgo=0;
        ArrayList<SugeridoRAM> tablaRAM=new ArrayList<SugeridoRAM>();
        Object [] row=null;
        if(opcion==1){
            tablaRAM=iusug.getTablaRAM();
            row=new Object[10];
        }else if(opcion==2){
            tablaRAM=iuactsug.getTablaRAM();
        }
        contadorHallazgo=tablaRAM.size();
        DefaultTableModel table=null;
        double totfob=0.00,fobTotal=0.00,fob=0.00;
        if(opcion==1){
            table = (DefaultTableModel) iusug.tb_sugerido.getModel();
            for(int i=0;i<tablaRAM.size();i++){
                row[0]=tablaRAM.get(i).getNumParte();
                row[1]=tablaRAM.get(i).getCodSecundario();
                row[2]=tablaRAM.get(i).getDescripcion();
                if(tablaRAM.get(i).getAplicacion().equals("null")){
                    row[3]="";
                }else{
                    row[3]=tablaRAM.get(i).getAplicacion();
                }
                int canPed=tablaRAM.get(i).getCantPedida();
                row[4]=getComaMillar(canPed);
                row[5]=getComaMillar(tablaRAM.get(i).getStockFisico());
                row[6]=getComaMillar(tablaRAM.get(i).getDemanda());
                row[7]=getComaMillar(tablaRAM.get(i).getStockSugerido());
                row[8]=tablaRAM.get(i).getFob();
                fob=Double.parseDouble(String.valueOf(row[8]));
                totfob=Integer.parseInt(String.valueOf(canPed))*Double.parseDouble(String.valueOf(row[8]));
                row[9]=totfob;
                fobTotal+=totfob;
                table.addRow(row);
                formatearColumnaDerecha(fob,i,8);
                formatearColumnaDerecha(totfob,i,9);
            }
            iusug.txt_Tot_Fob.setText(String.valueOf(fobTotal));
            formatearDecimalDerechaEnTextField(iusug.txt_Tot_Fob,fobTotal);
        }
        if(opcion==2){
            cargarTabRAMFilBusActSug();
        }
        return contadorHallazgo;
    }
    private void cargarTabRAMFilBusActSug(){
        ArrayList<SugeridoRAM> tabRAMFB=new ArrayList<SugeridoRAM>();
        DefaultTableModel table=null;
        Object [] row=null;
        double fobtemp=0.00;
        if(opcion==1){
            table = (DefaultTableModel) iusug.tb_sugerido.getModel();
            tabRAMFB=iusug.getTablaRAMBuscarFiltrar();
            row=new Object[10];
        }else if(opcion==2){
            tabRAMFB=iuactsug.getTablaRAMBuscarFiltrar();
            table = (DefaultTableModel) iuactsug.tb_sugerido.getModel();
            row=new Object[12];
        }
        for(int i=0;i<tabRAMFB.size();i++){
            row[0]=tabRAMFB.get(i).getNumParte();
            row[1]=tabRAMFB.get(i).getCodSecundario();
            row[2]=tabRAMFB.get(i).getDescripcion();
            if(tabRAMFB.get(i).getAplicacion().equals("null")){
                row[3]="";
            }else{
                row[3]=tabRAMFB.get(i).getAplicacion();
            }
            row[4]=getComaMillar(tabRAMFB.get(i).getCantPedida());
            if(opcion==1){
                row[5]=getComaMillar(tabRAMFB.get(i).getStockFisico());
                row[6]=getComaMillar(tabRAMFB.get(i).getDemanda());
                row[7]=getComaMillar(tabRAMFB.get(i).getStockSugerido());
                row[8]=tabRAMFB.get(i).getFob();
                row[9]=tabRAMFB.get(i).getCantPedida()*tabRAMFB.get(i).getFob();
            }else if(opcion==2){
                row[5]=getComaMillar(tabRAMFB.get(i).getCantEntregada());
                row[6]=getComaMillar(tabRAMFB.get(i).getCantPendiente());
                row[7]=getComaMillar(tabRAMFB.get(i).getStockFisico());
                row[8]=getComaMillar(tabRAMFB.get(i).getDemanda());
                row[9]=getComaMillar(tabRAMFB.get(i).getStockSugerido());
                row[10]=tabRAMFB.get(i).getFob();
                row[11]=tabRAMFB.get(i).getCantEntregada()*tabRAMFB.get(i).getFob();
            }
            table.addRow(row);
            fobtemp=0.00;
            if(opcion==1){
                fobtemp=Double.parseDouble(String.valueOf(row[8]));
                formatearColumnaDerecha(fobtemp,i,8);
            }else if(opcion==2){
                fobtemp=Double.parseDouble(String.valueOf(row[10]));
                formatearColumnaDerecha(fobtemp,i,10);
                formatearColumnaDerecha(Double.parseDouble(String.valueOf(row[11])),i,11);
            }
        }
        double fobTot=0.00;
        for(int j=0;j<tablaRam.size();j++){
            for(int k=0;k<tabRAMFB.size();k++){
                if(tablaRam.get(j).getIdrepuesto().equals(tabRAMFB.get(k).getIdrepuesto()) &&
                   tablaRam.get(j).getIdlinea().equals(tabRAMFB.get(k).getIdlinea())){
                    int cantPed=tablaRam.get(j).getCantPedida();
                    row[4]=getComaMillar(cantPed);
                    table.setValueAt(row[4], k,4);
                    int canEnt=0,dem=0;
                    if(opcion==1){
                        row[8]=tablaRam.get(j).getFob();
                        table.setValueAt(row[8], k,8);
                    }else if(opcion==2){
                        canEnt=tablaRam.get(j).getCantEntregada();
                        row[5]=getComaMillar(canEnt);
                        row[6]=getComaMillar(tablaRam.get(j).getCantPendiente());
                        row[7]=getComaMillar(tablaRam.get(j).getStockFisico());
                        dem=tablaRam.get(j).getDemanda();
                        row[8]=getComaMillar(dem);
                        row[9]=tablaRam.get(j).getStockSugerido();
                        row[10]=tablaRam.get(j).getFob();
                        table.setValueAt(row[5], k,5);
                        table.setValueAt(row[6], k,6);
                        table.setValueAt(row[7], k,7);
                        table.setValueAt(row[8], k,8);
                        table.setValueAt(row[9], k,9);
                        table.setValueAt(row[10], k,10);
                        fobtemp=tablaRam.get(j).getCantEntregada()*tablaRam.get(j).getFob();
                        table.setValueAt(fobtemp,k,11);
                    }
                    if(opcion==1){
                        fobTot+=Double.parseDouble(String.valueOf(cantPed))*Double.parseDouble(String.valueOf(dem));
                        formatearColumnaDerecha(Double.parseDouble(String.valueOf(dem)),k,8);
                    }else if(opcion==2){
                        fobTot+=Double.parseDouble(String.valueOf(canEnt))*Double.parseDouble(String.valueOf(row[10]));
                        formatearColumnaDerecha(Double.parseDouble(String.valueOf(row[10])),k,10);
                        formatearColumnaDerecha(fobtemp,k,11);
                    }
                    break;
                }
            }
        }
        if(opcion==1){
            iusug.txt_Tot_Fob.setText(String.valueOf(fobTot));
            formatearDecimalDerechaEnTextField(iusug.txt_Tot_Fob,fobTot);
        }else if(opcion==2){
            iuactsug.txt_Tot_Fob.setText(String.valueOf(fobTot));
            formatearDecimalDerechaEnTextField(iuactsug.txt_Tot_Fob,fobTot);
        }
    }
    private String getComaMillar(Object valor){
        String cantStr=String.valueOf(valor);
        int cantInt=Integer.parseInt(cantStr);
        int cifMillar,cifResto;
        if(cantStr.length()>3 && Integer.parseInt(cantStr)>0){
            cifMillar=cantInt/1000;
            cifResto=cantInt%1000;
            if(cifResto==0 && cifMillar!=0){
                cantStr=cifMillar+",000";
            }else if(cifResto/100!=0){
                cantStr=cifMillar+","+cifResto;
            }else if(cifResto/10!=0){
                cantStr=cifMillar+",0"+cifResto;
            }else{
                cantStr=cifMillar+",00"+cifResto;
            }
        }else if(cantStr.length()>4 && Integer.parseInt(cantStr)<0){
            cifMillar=cantInt/1000;
            cifResto=cantInt%1000;
            if(cifResto==0 && cifMillar!=0){
                cantStr=cifMillar+",000";
            }else if(cifResto/100!=0){
                cantStr=cifMillar+","+Math.abs(cifResto);
            }else if(cifResto/10!=0){
                cantStr=cifMillar+",0"+Math.abs(cifResto);
            }else{
                cantStr=cifMillar+",00"+Math.abs(cifResto);
            }
        }
        return cantStr;
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
        if(opcion==1){
            iusug.tb_sugerido.setValueAt(moneyCad,fila,columna);
        }else if(opcion==2){
            iuactsug.tb_sugerido.setValueAt(moneyCad, fila,columna);
        }
    }
    private void limpiarTabla(){
        try {
            DefaultTableModel modelo=null;
            int filas=0;
            if(opcion==1){
                modelo=(DefaultTableModel) iusug.tb_sugerido.getModel();
                filas=iusug.tb_sugerido.getRowCount();
            }else if(opcion==2){
                modelo=(DefaultTableModel) iuactsug.tb_sugerido.getModel();
                filas=iuactsug.tb_sugerido.getRowCount();
            }
            for(int i=0;i<filas;i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgFiltro;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbCantidad;
    private javax.swing.JRadioButton rbTodos;
    // End of variables declaration//GEN-END:variables
}