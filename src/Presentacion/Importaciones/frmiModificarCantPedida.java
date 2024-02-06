package Presentacion.Importaciones;
import Entidades.Cabecsugerido;
import Entidades.Detallesugerido;
import Entidades.Importaciones.SugeridoRAM;
import Entidades.Repuestos;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Servicios.Importaciones.FullSelectorListener;
import Servicios.Importaciones.Servicio_CabSugerido;
import Servicios.Importaciones.Servicio_DetSugerido;
import Servicios.Importaciones.Servicio_Repuestos;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.Locale;
import javax.swing.JTable;
public class frmiModificarCantPedida extends javax.swing.JFrame {
    static private IU_Sugerido iusug;
    static private String nombreRep;
    Servicio_Repuestos sr;
    static private int filaSel;
    private int limCantPedir;
    private int limUltFob;
    static private ArrayList tablaRam;
    static private int opcion;
    static private IU_ActualizarSugerido iuactsug;
    static private int tipoActualizacion;
    public frmiModificarCantPedida(IU_Sugerido sug,String numParte,int filaSelec,ArrayList tabRam) {
        initComponents();
        opcion=1;
        filaSel=filaSelec;
        iusug=sug;
        iusug.setEnabled(false);
        nombreRep=numParte;
        mostrarCantPedida();
        mostrarFobUltimo();
        limCantPedir=6;
        limUltFob=9;
        tablaRam=tabRam;
    }
    public frmiModificarCantPedida(IU_ActualizarSugerido actsug,String numParte,int filaSelec,ArrayList tabRam,int tipoActualiza) {
        initComponents();
        opcion=2;
        filaSel=filaSelec;
        iuactsug=actsug;
        iuactsug.setEnabled(false);
        nombreRep=numParte;
        tipoActualizacion=tipoActualiza;
        mostrarCantPedida();
        mostrarFobUltimo();
        limCantPedir=6;
        limUltFob=9;
        tablaRam=tabRam;
        this.setTitle("MODIFICAR CANTIDAD ENTREGADA");
        lblCantidad.setText("CANTIDAD ENTREGADA:");
    }
    private void mostrarFobUltimo(){
        String fobUlt="";
        if(opcion==1){
            fobUlt=quitaComa(String.valueOf(iusug.tb_sugerido.getValueAt(filaSel,8)));
        }else if(opcion==2){
            fobUlt=quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,10)));
        }
        if(fobUlt==null){  fobUlt="0.00"; }
        txtUltFob.setText(String.valueOf(fobUlt));
        txtUltFob.addFocusListener( new FullSelectorListener() );
    }
    private void mostrarCantPedida(){
        String cant="";
        if(opcion==1){
            cant=quitaComa(String.valueOf(iusug.tb_sugerido.getValueAt(filaSel,4)));
        }else if(opcion==2){
            cant=quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,5)));
        }
        txtCantPedir.setText(cant);
        txtCantPedir.addFocusListener( new FullSelectorListener() );
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblCantidad = new javax.swing.JLabel();
        txtUltFob = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtCantPedir = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("MODIFICAR CANTIDAD PEDIDA");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCantidad.setText("CANTIDAD A PEDIR:");

        txtUltFob.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUltFobKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUltFobKeyTyped(evt);
            }
        });

        jLabel2.setText("ULTIMO FOB:");

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtCantPedir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantPedirKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantPedirKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(lblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCantPedir, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                            .addComponent(txtUltFob)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCantidad)
                    .addComponent(txtCantPedir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUltFob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        dispose();
    }//GEN-LAST:event_formWindowClosing
    private void txtUltFobKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUltFobKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            guardar();
        }
    }//GEN-LAST:event_txtUltFobKeyPressed
    private void txtUltFobKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUltFobKeyTyped
        if (txtUltFob.getText().length()== limUltFob){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        char caracter = evt.getKeyChar();
        if(((caracter < '0') || (caracter > '9')) && (caracter != '\b') && caracter!='.'){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtUltFobKeyTyped
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if(opcion==1){
            iusug.setEnabled(true);
        }else if(opcion==2){
            iuactsug.setEnabled(true);
        }
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed
    private void txtCantPedirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantPedirKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtUltFob.grabFocus();
        }
    }//GEN-LAST:event_txtCantPedirKeyPressed
    private void txtCantPedirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantPedirKeyTyped
        if (txtCantPedir.getText().length()== limCantPedir){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        char caracter = evt.getKeyChar();
        if(((caracter < '0') || (caracter > '9') || (caracter=='.')) && (caracter != '\b')){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtCantPedirKeyTyped
    public void actualizarTablaRAM(String numParte,String cant,String fobUlt,int filaSel){
        SugeridoRAM sugTabla=new SugeridoRAM();
        int size=0;
        int cantidad=0;
        if(!cant.equals("")){
            cantidad=Integer.parseInt(cant);
        }
        if(opcion==1){
            size=iusug.tb_sugerido.getRowCount();
        }else if(opcion==2){
            size=iuactsug.tb_sugerido.getRowCount();
        }
        int contSugCantEnt=0;
        boolean enRam=false;
        for(int i=0;i<size;i++){
            String nameRepTabla="";
            int cantEntTabla=0;
            if(opcion==1){
                nameRepTabla=String.valueOf(iusug.tb_sugerido.getValueAt(i,0));
            }else if(opcion==2){
                nameRepTabla=String.valueOf(iuactsug.tb_sugerido.getValueAt(i,0));
                cantEntTabla=Integer.parseInt(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(i,5))));
            }
            if(cantEntTabla>0){
                contSugCantEnt++;
            }
            int cantPedida=0;
            for(int j=0;j<tablaRam.size();j++){
                sugTabla=(SugeridoRAM) tablaRam.get(j);
                String nameRepRam=sugTabla.getNumParte();
                if(nameRepTabla.equals(nameRepRam)){
                    double fob=0.00;
                    if(opcion==1){
                        cantPedida=Integer.parseInt(quitaComa(String.valueOf(iusug.tb_sugerido.getValueAt(i,4))));
                        fob=Double.parseDouble(quitaComa(String.valueOf(iusug.tb_sugerido.getValueAt(i,8))));
                        sugTabla.setCantPedida(cantPedida);
                    }else if(opcion==2){
                        cantPedida=Integer.parseInt(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(i,4))));
                        int cantEntregada=Integer.parseInt(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(i,5))));
                        int cantPendiente=cantPedida-cantEntregada;
                        fob=Double.parseDouble(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(i,10))));
                        sugTabla.setCantPedida(cantPedida);
                        sugTabla.setCantEntregada(cantEntregada);
                        sugTabla.setCantPendiente(cantPendiente);
                    }
                    sugTabla.setFob(fob);
                    tablaRam.set(j,sugTabla);
                    enRam=true;
                }
            }
        }
        if(opcion==2){
            if(cantidad==0){//Si cant es 0, remover de tablaRAM y de jtable
                DefaultTableModel modelo=(DefaultTableModel) iuactsug.tb_sugerido.getModel();
                modelo.removeRow(filaSel);
                ArrayList<SugeridoRAM> lista=new ArrayList<SugeridoRAM>();//Eliminar de lista RAM el sugerido = 0
                for(int j=0;j<tablaRam.size();j++){
                    SugeridoRAM sug=(SugeridoRAM) tablaRam.get(j);
                    if(sug.getCantEntregada()!=0){
                        lista.add(sug);
                    }
                }
                iuactsug.setTablaRAM(lista);
            }
            if(tablaRam.size()!=contSugCantEnt && cantidad!=0){
                //Registrar sugerido k esta en tablaRAMFB a tablaRAM
                SugeridoRAM nuevo=new SugeridoRAM();
                String nameRep=String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,0));
                nuevo.setNumParte(nameRep);
                nuevo.setCodSecundario(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,1)));
                nuevo.setDescripcion(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,2)));
                nuevo.setAplicacion(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,3)));
                nuevo.setCantPedida(Integer.parseInt(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,4)))));
                nuevo.setCantEntregada(Integer.parseInt(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,5)))));
                nuevo.setCantPendiente(Integer.parseInt(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,6)))));
                nuevo.setStockFisico(Integer.parseInt(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,7)))));
                nuevo.setDemanda(Integer.parseInt(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,8)))));
                nuevo.setStockSugerido(Integer.parseInt(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,9)))));
                nuevo.setFob(Double.parseDouble(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,10)))));
                Servicio_Repuestos sr=new Servicio_Repuestos();
                Repuestos repuesto=new Repuestos();
                repuesto=sr.getRepuesto(nameRep);
                nuevo.setIdlinea(repuesto.getId().getIdlinea());
                nuevo.setIdrepuesto(repuesto.getId().getIdrepuesto());
                if(!enRam){
                    tablaRam.add(nuevo);
                }
            }
        }
    }
    public String quitaComa(String Nombre1){
        int c= Nombre1.length();
        String cad="";
        for (int contador =0; contador<c; contador++){
            if (Nombre1.charAt(contador)==','){
                Nombre1.replace(',',' ');
            }else{
                cad+=Nombre1.charAt(contador);
            }
        }
        return cad;
    }
    public void actualizarTablaRAMFB(String numParte,String cant,String fobUlt,int filaSel){
        SugeridoRAM sugTabla=new SugeridoRAM();
        ArrayList<SugeridoRAM>listaRAMFB=new ArrayList<SugeridoRAM>();
        listaRAMFB=iuactsug.getTablaRAMBuscarFiltrar();
        int size=listaRAMFB.size();
        for(int i=0;i<size;i++){
            String nameRepTabla=String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,0));
            sugTabla=(SugeridoRAM) listaRAMFB.get(i);
            String nameRepRam=sugTabla.getNumParte();
            if(nameRepTabla.equals(nameRepRam)){
                double fob=0.00;
                int cantEntregada=Integer.parseInt(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,5))));
                int cantPendiente=Integer.parseInt(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,6))));
                fob=Double.parseDouble(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,10))));
                sugTabla.setCantEntregada(cantEntregada);
                sugTabla.setCantPendiente(cantPendiente);
                sugTabla.setFob(fob);
                break;
            }
        }
    }
    private void accionDetalleSugerido(int cantPedir,double fob){
        sr=new Servicio_Repuestos();
        Repuestos rep=new Repuestos();
        String numParte="";
        if(opcion==1){
            numParte=String.valueOf(iusug.tb_sugerido.getValueAt(filaSel,0));
        }else if(opcion==2){
            numParte=String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,0));
        }
        rep=sr.getRepuesto(numParte);
        int idRep=rep.getId().getIdrepuesto();
        int idLin=rep.getId().getIdlinea();
        int idsugerido=0;
        if(opcion==1){
            idsugerido=Integer.parseInt(iusug.tx_id.getText());
        }else if(opcion==2){
            idsugerido=Integer.parseInt(iuactsug.tx_id.getText());
        }
        Servicio_DetSugerido sds=new Servicio_DetSugerido();
        Detallesugerido ds=new Detallesugerido();
        ds=sds.buscaDetSugerido(idsugerido,idRep,idLin);

        Cabecsugerido cs=new Cabecsugerido();
        int idSugerido=0;
        if(opcion==1){
            idSugerido=Integer.parseInt(iusug.tx_id.getText());
        }else if(opcion==2){
            idSugerido=Integer.parseInt(iuactsug.tx_id.getText());
        }
        Servicio_CabSugerido scs=new Servicio_CabSugerido();
        cs=new Cabecsugerido();
        cs=scs.getCabSugerido(idSugerido);
        if(ds==null){//DetalleSugerido no registrado en BD
            ds=new Detallesugerido();
            int idDetSugerido=sds.nextId();
            ds.setIddetallesugerido(idDetSugerido);
            rep=sr.getRepuesto(numParte);
            ds.setRepuestos(rep);//idrepuesto, idlinea

            if(opcion==1){
                ds.setCantpedida(cantPedir);
                ds.setCantpendiente(0);
            }else if(opcion==2){
                int cantPed=Integer.parseInt(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,4)));
                ds.setCantpedida(cantPed);
                if(cantPed==0){
                    ds.setCantpedida(cantPedir);
                }
                ds.setCantpendiente(ds.getCantpedida()-cantPedir);
            }
            ds.setCantentregada(cantPedir);
            ds.setValorfob(fob);
            //ds.setValorcosto();
            SugeridoRAM sug=getSugeridoRAM(numParte);
            
            
            System.out.println("Stock Sugerido : " + sug.getStockSugerido());
            ds.setStocksugerido(sug.getStockSugerido());
            ds.setDemandaprom(sug.getDemanda());
            if(cantPedir!=0 && fob!=0.00){
                if(cs==null){
                    cs=new Cabecsugerido();
                    cs.setIdsugerido(idSugerido);
                    cs.setImportadores(null);
                    if(!scs.registrarCabecSugerido(cs)){
                        System.out.println("Problema al registrar cabecSugerido");
                    }else{
                        JOptionPane jop = new JOptionPane();
                        jop.showMessageDialog(null,"Sugerido registrado con éxito","AVISO", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("Registrando cabecSugerido con idSug:"+cs.getIdsugerido());
                    }
                }
                ds.setCabecsugerido(cs);//idsugerido 
                if(!sds.registrarDetalleSugerido(ds)){
                    System.out.println("Error al registrar detallesugerido");
                }else{
                    System.out.println("Registrar detallesugerido cantPedida="+cantPedir+" y fob="+fob);
                    int indice=buscarEnRAM(ds);
                    if(indice==-1){
                        registrarNuevoRepEnRAM();
                    }else{
                        sug=new SugeridoRAM();
                        sug=getSugeridoRAM(numParte);
                        sug.setCantPedida(cantPedir);
                        sug.setFob(fob);
                        tablaRam.set(indice,sug);
                    }
                    if(opcion==1){
                        iusug.mostrarListaRAM();
                    }else if(opcion==2){
                        iuactsug.mostrarListaRAM();
                    }
                }
            }
        }else{
            if(cs==null){
                cs=new Cabecsugerido();
                cs.setIdsugerido(idSugerido);
                cs.setImportadores(null);
                if(!scs.registrarCabecSugerido(cs)){
                    System.out.println("Problema al registrar cabecSugerido");
                }else{
                    System.out.println("Registrando cabecSugerido con idSug:"+cs.getIdsugerido());
                }
            }
            ds.setCabecsugerido(cs);
            if(opcion==1){
                ds.setCantpedida(cantPedir);
                ds.setCantentregada(cantPedir);
                ds.setCantpendiente(ds.getCantpedida()-cantPedir);
            }else if(opcion==2){
                int cantPedAct=Integer.parseInt(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,4)));
                if(tipoActualizacion==1){
                    ds.setCantpedida(cantPedAct);
                    ds.setCantentregada(cantPedir);
                    ds.setCantpendiente(cantPedAct-cantPedir);
                }else if(tipoActualizacion==2){
                    ds.setCantpedida(cantPedir);
                    ds.setCantentregada(cantPedir);
                    ds.setCantpendiente(0);
                }
            }
            ds.setValorfob(fob);
            if(cantPedir!=0 && fob!=0.00){
                sds=new Servicio_DetSugerido();
                if(sds.actualizarDetSugerido(ds)){
                    System.out.println("Actualizar detallesugerido con cantEntregada:"+cantPedir+" y fob:"+fob);
                }else{
                    System.out.println("Error al actualizar detallesugerido");
                }
            }else{
                sds.eliminarDetalleSugerido(idsugerido,idRep,idLin);
                System.out.println("Eliminar detallesugerido idSug:"+idsugerido+" IdRep:"+idRep+" IdLinea:"+idLin);
            }
        }
    }
    private int buscarEnRAM(Detallesugerido ds){
        int indice=-1;//no encontrado
        for(int i=0;i<tablaRam.size();i++){
            if( ((SugeridoRAM)tablaRam.get(i)).getIdrepuesto().equals(ds.getRepuestos().getId().getIdrepuesto())
             && ((SugeridoRAM)tablaRam.get(i)).getIdlinea().equals(ds.getRepuestos().getId().getIdlinea())){
                indice=i;
                break;
            }
        }
        return indice;
    }
    private void registrarNuevoRepEnRAM(){//Registrar sugerido k esta en tablaRAMFB a tablaRAM
        SugeridoRAM nuevo=new SugeridoRAM();
        JTable table=null;
        if(opcion==1){
            table=iusug.tb_sugerido;
        }else if(opcion==2){
            table=iuactsug.tb_sugerido;
        }
        String nameRep=String.valueOf(table.getValueAt(filaSel,0));
        nuevo.setNumParte(nameRep);
        nuevo.setCodSecundario(String.valueOf(table.getValueAt(filaSel,1)));
        nuevo.setDescripcion(String.valueOf(table.getValueAt(filaSel,2)));
        nuevo.setAplicacion(String.valueOf(table.getValueAt(filaSel,3)));
        nuevo.setCantPedida(Integer.parseInt(quitaComa(String.valueOf(table.getValueAt(filaSel,4)))));
        if(opcion==1){
            nuevo.setStockFisico(Integer.parseInt(quitaComa(String.valueOf(table.getValueAt(filaSel,5)))));
            nuevo.setDemanda(Integer.parseInt(quitaComa(String.valueOf(table.getValueAt(filaSel,6)))));
            nuevo.setStockSugerido(Integer.parseInt(quitaComa(String.valueOf(table.getValueAt(filaSel,7)))));
            nuevo.setFob(Double.parseDouble(quitaComa(String.valueOf(table.getValueAt(filaSel,8)))));
        }else if(opcion==2){
            nuevo.setCantEntregada(Integer.parseInt(quitaComa(String.valueOf(table.getValueAt(filaSel,5)))));
            nuevo.setCantPendiente(Integer.parseInt(quitaComa(String.valueOf(table.getValueAt(filaSel,6)))));
            nuevo.setStockFisico(Integer.parseInt(quitaComa(String.valueOf(table.getValueAt(filaSel,7)))));
            nuevo.setDemanda(Integer.parseInt(quitaComa(String.valueOf(table.getValueAt(filaSel,8)))));
            nuevo.setStockSugerido(Integer.parseInt(quitaComa(String.valueOf(table.getValueAt(filaSel,9)))));
            nuevo.setFob(Double.parseDouble(quitaComa(String.valueOf(table.getValueAt(filaSel,10)))));
        }
        Servicio_Repuestos sr=new Servicio_Repuestos();
        Repuestos repuesto=new Repuestos();
        repuesto=sr.getRepuesto(nameRep);
        nuevo.setIdlinea(repuesto.getId().getIdlinea());
        nuevo.setIdrepuesto(repuesto.getId().getIdrepuesto());
        tablaRam.add(nuevo);
    }
    private SugeridoRAM getSugeridoRAM(String numParte){
        Iterator<SugeridoRAM> puntero=null;
        if(opcion==1){
            puntero=iusug.getTablaRAM().iterator();
        }else if(opcion==2){
            puntero=iuactsug.getTablaRAM().iterator();
        }
        int i=0;
        SugeridoRAM sug=null;
        while(puntero.hasNext()){
            sug=puntero.next();
            if(sug.getNumParte().equals(numParte)){
                break;
            }
            i++;
        }
        return sug;
    }
    private void guardar(){
        String cant=txtCantPedir.getText();
        String ultFob=txtUltFob.getText();
        if(validarDatos(cant,ultFob)){
            

            
            if (JOptionPane.showConfirmDialog(this,"¿Está seguro de guardar cambios?","Confirmacion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
    
                System.out.println("Confirmación : " + opcion);
                System.out.println("Cant : " + cant + "   Ult.Fob : " + ultFob);
                
                if(opcion==1){
                    int cantPedir=Integer.parseInt(cant);
                    double fob=Double.parseDouble(txtUltFob.getText());
                    accionDetalleSugerido(cantPedir,fob);
                    iusug.tb_sugerido.setValueAt(getComaMillar(cant),filaSel,4);
                    iusug.tb_sugerido.setValueAt(ultFob,filaSel,8);
                    String fobTot=String.valueOf(Integer.parseInt(cant)*Double.parseDouble(ultFob));
                    iusug.tb_sugerido.setValueAt(fobTot,filaSel,9);
                    formatearColumnaDerecha(Double.parseDouble(ultFob),filaSel,8);
                    formatearColumnaDerecha(Double.parseDouble(fobTot),filaSel,9);
                    iusug.actualizarFobTotal();
                    iusug.setEnabled(true);
                }else if(opcion==2){
                    int cantPedir=Integer.parseInt(cant);
                    double fob=Double.parseDouble(txtUltFob.getText());
                    accionDetalleSugerido(cantPedir,fob);
                    iuactsug.tb_sugerido.setValueAt(getComaMillar(cant),filaSel,5);
                    
                    int cantPedida=Integer.parseInt(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,4))));
                    int cantEntregada=Integer.parseInt(quitaComa(String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,5))));
                    if(cantPedida==0){
                        cantPedida=cantEntregada;
                    }
                    if(tipoActualizacion==2){
                        cantPedida=cantEntregada;
                    }
                    int cantPendiente=cantPedida-cantEntregada;
                    iuactsug.tb_sugerido.setValueAt(getComaMillar(cantPedida),filaSel,4);
                    iuactsug.tb_sugerido.setValueAt(getComaMillar(cantPendiente),filaSel,6);
                    iuactsug.tb_sugerido.setValueAt(ultFob,filaSel,10);
                    double fobTotal=Integer.parseInt(cant)*Double.parseDouble(ultFob);
                    String fobTot=String.valueOf(fobTotal);
                    iuactsug.tb_sugerido.setValueAt(fobTot,filaSel,11);
                    formatearColumnaDerecha(Double.parseDouble(ultFob),filaSel,10);
                    formatearColumnaDerecha(fobTotal,filaSel,11);
                    iuactsug.actualizarFobTotal();
                    iuactsug.setEnabled(true);
                }
                if(opcion==1){
                    actualizarTablaRAM("","","",0);
                }else if(opcion==2){//ACTUALIZAR SUGERIDO
                    String numParte=String.valueOf(iuactsug.tb_sugerido.getValueAt(filaSel,0));

                    System.out.println("Num Parte: " + numParte + " Cant: : " + cant + "   Ult.Fob : " + ultFob);
                    
                    actualizarTablaRAMFB(numParte,cant,ultFob,filaSel);
                    actualizarTablaRAM(numParte,cant,ultFob,filaSel);
                    iuactsug.mostrarListaRAM();
                }
                dispose();
            }
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
            iuactsug.tb_sugerido.setValueAt(moneyCad,fila,columna);
        }
    }
    public boolean validarDatos(String cantidad, String fobUlt){
        boolean val=false;
        JOptionPane jop = new JOptionPane();
        if(cantidad.equals("")){
            jop.showMessageDialog(null, "Por favor, ingrese la cantidad del repuesto","CAMPO INCOMPLETO", JOptionPane.OK_OPTION);
            txtCantPedir.grabFocus();
        }else if(fobUlt.equals("")){
            jop.showMessageDialog(null, "Por favor, ingrese el FOB último del repuesto","CAMPO INCOMPLETO", JOptionPane.OK_OPTION);
            txtUltFob.grabFocus();
        }else{
            val=true;
        }
        return val;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCantidad;
    public javax.swing.JTextField txtCantPedir;
    private javax.swing.JTextField txtUltFob;
    // End of variables declaration//GEN-END:variables
}