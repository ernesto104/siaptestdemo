package Presentacion.Importaciones;
import Entidades.Importaciones.SugeridoRAM;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Servicios.Importaciones.FullSelectorListener;
import Servicios.Importaciones.Servicio_Repuestos;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
public class frmiBuscarSugerido extends javax.swing.JFrame {
    static private IU_Sugerido iusug;
    private Servicio_Repuestos sr;
    static private ArrayList<SugeridoRAM> tablaRam;
    private int limNumParte;
    private int limCodSec;
    private int limDescripcion;
    static private ArrayList<SugeridoRAM> tablaRAMFB;
    static private IU_ActualizarSugerido iuactsug;
    static private int opcion;
    public frmiBuscarSugerido(IU_Sugerido sug,ArrayList tabRam,ArrayList<SugeridoRAM> tabRAMFB) {
        initComponents();
        limNumParte=17;
        limCodSec=10;
        limDescripcion=40;
        iusug=sug;
        iusug.setEnabled(false);
        sr=new Servicio_Repuestos();
        txtNumParte.grabFocus();
        tablaRam=tabRam;
        tablaRAMFB=tabRAMFB;
        opcion=1;
    }
    public frmiBuscarSugerido(IU_ActualizarSugerido actsug,ArrayList tabRam,ArrayList<SugeridoRAM> tabRAMFB) {
        initComponents();
        limNumParte=17;
        limCodSec=10;
        limDescripcion=40;
        iuactsug=actsug;
        iuactsug.setEnabled(false);
        sr=new Servicio_Repuestos();
        txtNumParte.grabFocus();
        tablaRam=tabRam;
        tablaRAMFB=tabRAMFB;
        opcion=2;
    }
    public frmiBuscarSugerido() {
        initComponents();
        txtNumParte.grabFocus();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgTipoBusqueda = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbCodSecundario = new javax.swing.JRadioButton();
        txtNumParte = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        rbNumParte = new javax.swing.JRadioButton();
        txtCodSecundario = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        rbDescripcion = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("BUSCAR REPUESTOS");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        bgTipoBusqueda.add(rbCodSecundario);
        rbCodSecundario.setText("CODIGO SECUNDARIO");
        rbCodSecundario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCodSecundarioActionPerformed(evt);
            }
        });

        txtNumParte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumParteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumParteKeyTyped(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        bgTipoBusqueda.add(rbNumParte);
        rbNumParte.setSelected(true);
        rbNumParte.setText("NUMERO DE PARTE");
        rbNumParte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbNumParteActionPerformed(evt);
            }
        });

        txtCodSecundario.setEnabled(false);
        txtCodSecundario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodSecundarioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodSecundarioKeyTyped(evt);
            }
        });

        txtDescripcion.setEnabled(false);
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        bgTipoBusqueda.add(rbDescripcion);
        rbDescripcion.setText("DESCRIPCION");
        rbDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDescripcionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(rbCodSecundario)
                        .addComponent(rbNumParte, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(rbDescripcion, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNumParte)
                            .addComponent(txtCodSecundario)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbNumParte)
                    .addComponent(txtNumParte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbCodSecundario)
                    .addComponent(txtCodSecundario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbDescripcion)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
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
        dispose();
    }//GEN-LAST:event_formWindowClosing
    private void rbCodSecundarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCodSecundarioActionPerformed
        txtNumParte.setEnabled(false);
        txtCodSecundario.setEnabled(true);
        txtDescripcion.setEnabled(false);
        txtCodSecundario.grabFocus();
        txtNumParte.setText("");
        txtDescripcion.setText("");
    }//GEN-LAST:event_rbCodSecundarioActionPerformed
    private void txtNumParteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumParteKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            buscar();
        }
    }//GEN-LAST:event_txtNumParteKeyPressed
    private void txtNumParteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumParteKeyTyped
        if (txtNumParte.getText().length()==limNumParte){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtNumParteKeyTyped
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if(opcion==1){
            iusug.setEnabled(true);
        }else if(opcion==2){
            iuactsug.setEnabled(true);
        }
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed
    private void rbNumParteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbNumParteActionPerformed
        txtNumParte.setEnabled(true);
        txtCodSecundario.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtNumParte.grabFocus();
        txtCodSecundario.setText("");
        txtDescripcion.setText("");
    }//GEN-LAST:event_rbNumParteActionPerformed
    private void txtCodSecundarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodSecundarioKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            buscar();
        }
    }//GEN-LAST:event_txtCodSecundarioKeyPressed
    private void txtCodSecundarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodSecundarioKeyTyped
        if (txtCodSecundario.getText().length()==limCodSec){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtCodSecundarioKeyTyped
    private void txtDescripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            buscar();
        }
    }//GEN-LAST:event_txtDescripcionKeyPressed
    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        if (txtDescripcion.getText().length()==limDescripcion){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtDescripcionKeyTyped
    private void rbDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDescripcionActionPerformed
        txtNumParte.setEnabled(false);
        txtCodSecundario.setEnabled(false);
        txtDescripcion.setEnabled(true);
        txtDescripcion.grabFocus();
        txtNumParte.setText("");
        txtCodSecundario.setText("");
    }//GEN-LAST:event_rbDescripcionActionPerformed
    private void buscar(){
        String valor="";
        JOptionPane jop = new JOptionPane();
        int opc=0;
        if(rbNumParte.isSelected()){
            valor=txtNumParte.getText();
            if(!valor.equals("")){
                opc=1;
                if(insertarRepuestosATabla(valor,opc)>0){
                    if(opcion==1){
                        iusug.setEnabled(true);
                    }else if(opcion==2){
                        iuactsug.setEnabled(true);
                    }
                    this.dispose();
                }else{
                    jop.showMessageDialog(null, "No se encontraron repuestos con número de parte "+valor+" en la BD",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
                    txtNumParte.grabFocus();
                    txtNumParte.addFocusListener( new FullSelectorListener() );
                }
            }else{
                jop.showMessageDialog(null, "Ingrese el número de parte del repuesto","ERROR", JOptionPane.ERROR_MESSAGE);
                txtNumParte.grabFocus();
            }
        }else if(rbCodSecundario.isSelected()){
            valor=txtCodSecundario.getText();
            if(!valor.equals("")){
                opc=2;
                if(insertarRepuestosATabla(valor,opc)>0){
                    if(opcion==1){
                        iusug.setEnabled(true);
                    }else if(opcion==2){
                        iuactsug.setEnabled(true);
                    }
                    this.dispose();
                }else{
                    jop.showMessageDialog(null, "No se encontraron repuestos con codigo secundario "+valor+" en la BD",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
                    txtCodSecundario.grabFocus();
                    txtCodSecundario.addFocusListener( new FullSelectorListener() );
                }
            }else{
                jop.showMessageDialog(null, "Ingrese el código secundario del repuesto","ERROR", JOptionPane.ERROR_MESSAGE);
                txtCodSecundario.grabFocus();
            }
        }else if(rbDescripcion.isSelected()){
            valor=txtDescripcion.getText();
            if(!valor.equals("")){
                opc=3;
                if(insertarRepuestosATabla(valor,opc)>0){
                    if(opcion==1){
                        iusug.setEnabled(true);
                    }else if(opcion==2){
                        iuactsug.setEnabled(true);
                    }
                    this.dispose();
                }else{
                    jop.showMessageDialog(null, "No se encontraron repuestos con descripción "+valor+" en la BD",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
                    txtDescripcion.grabFocus();
                    txtDescripcion.addFocusListener( new FullSelectorListener() );
                }
            }else{
                jop.showMessageDialog(null, "Ingrese la descripción del repuesto","ERROR", JOptionPane.ERROR_MESSAGE);
                txtDescripcion.grabFocus();
            }
        }
    }
    public int insertarRepuestosATabla(String valor,int opcBuscar){
        int num=0;
        limpiarTabla();
        if(opcion==1){
            switch(opcBuscar){
                case 1: num=buscarEnListaRAMxNumParte(valor);break;
                case 2: num=buscarEnListaRAMxCodSecundario(valor);break;
                case 3: num=buscarEnListaRAMxDescripcion(valor);break;
            }
            if(num!=0){
                if(opcion==1){
                    iusug.activarFiltrado();
                }else if(opcion==2){
                    iuactsug.activarFiltrado();
                }
            }
        }else if(opcion==2){
            switch(opcBuscar){
                case 1:num=buscarEnRAMBuscarFiltrarxNumParte(valor);break;
                case 2:num=buscarEnRAMBuscarFiltrarxCodSecundario(valor);break;
                case 3:num=buscarEnRAMBuscarFiltrarxDescripcion(valor);break;
            }
        }
        return num;
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
    public int buscarEnListaRAMxNumParte(String numParte){
        List lista=new ArrayList();
        lista=sr.buscaRepuestosXNombreIncompleto(numParte);
        Iterator iteRep = lista.iterator();
        DefaultTableModel temp = null;
        if(opcion==1){
            temp=(DefaultTableModel) iusug.tb_sugerido.getModel();
        }else if(opcion==2){
            temp=(DefaultTableModel) iuactsug.tb_sugerido.getModel();
        }
        int contadorHallazgo=0;
        double fobTot=0.00;
        while(iteRep.hasNext()){
            Object [] objRep = (Object []) iteRep.next();
            int idRepuesto=Integer.parseInt(String.valueOf(objRep[0]));///clave1
            int idLinea=Integer.parseInt(String.valueOf(objRep[1]));///clave2
            SugeridoRAM sug;
            Iterator<SugeridoRAM> puntero=tablaRam.iterator();
            while(puntero.hasNext()){
                sug=puntero.next();
                if(idRepuesto==sug.getIdrepuesto() && idLinea==sug.getIdlinea()){
                    double totfob=sug.getCantPedida()*sug.getFob();
                    Object nuevo[]={sug.getNumParte(),sug.getCodSecundario(),
                    sug.getDescripcion(),sug.getAplicacion(),getComaMillar(sug.getCantPedida()),
                    getComaMillar(sug.getStockFisico()),getComaMillar(sug.getDemanda()),
                    getComaMillar(sug.getStockSugerido()),sug.getFob(),totfob};
                    temp.addRow(nuevo);
                    fobTot+=Double.parseDouble(String.valueOf(sug.getCantPedida()))*sug.getFob();
                    double valor=Double.parseDouble(String.valueOf(sug.getFob()));
                    formatearColumnaDerecha(valor,contadorHallazgo,8);
                    formatearColumnaDerecha(totfob,contadorHallazgo,9);
                    contadorHallazgo++;
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
        return contadorHallazgo;
    }
    public int buscarEnListaRAMxCodSecundario(String numSecundario){
        List lista=new ArrayList();
        lista=sr.buscaRepuestosXNumSecundario(numSecundario);
        Iterator iteRep = lista.iterator();
        DefaultTableModel temp = null;
        if(opcion==1){
            temp=(DefaultTableModel) iusug.tb_sugerido.getModel();
        }else if(opcion==2){
            temp=(DefaultTableModel) iuactsug.tb_sugerido.getModel();
        }
        int contadorHallazgo=0;
        double fobTot=0.00;
        while(iteRep.hasNext()){
            Object [] objRep = (Object []) iteRep.next();
            int idRepuesto=Integer.parseInt(String.valueOf(objRep[0]));
            int idLinea=Integer.parseInt(String.valueOf(objRep[1]));
            SugeridoRAM sug;
            Iterator<SugeridoRAM> puntero=tablaRam.iterator();
            while(puntero.hasNext()){
                sug=puntero.next();
                if(idRepuesto==sug.getIdrepuesto() && idLinea==sug.getIdlinea()){
                    double totfob=sug.getCantPedida()*sug.getFob();
                    Object nuevo[]={sug.getNumParte(),sug.getCodSecundario(),
                    sug.getDescripcion(),sug.getAplicacion(),getComaMillar(sug.getCantPedida()),
                    getComaMillar(sug.getStockFisico()),getComaMillar(sug.getDemanda()),
                    getComaMillar(sug.getStockSugerido()),sug.getFob(),totfob};
                    temp.addRow(nuevo);
                    fobTot+=Double.parseDouble(String.valueOf(sug.getCantPedida()))*sug.getFob();
                    double valor=Double.parseDouble(String.valueOf(sug.getFob()));
                    formatearColumnaDerecha(valor,contadorHallazgo,8);
                    formatearColumnaDerecha(totfob,contadorHallazgo,9);
                    contadorHallazgo++;
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
        return contadorHallazgo;
    }
    private int buscarEnListaRAMxDescripcion(String descripcion) {
        List lista=new ArrayList();
        lista=sr.buscaRepuestosXDescripcion(descripcion);
        Iterator iteRep = lista.iterator();
        DefaultTableModel temp = null;
        if(opcion==1){
            temp=(DefaultTableModel) iusug.tb_sugerido.getModel();
        }else if(opcion==2){
            temp=(DefaultTableModel) iuactsug.tb_sugerido.getModel();
        }
        int contadorHallazgo=0;
        double fobTot=0.00;
        while(iteRep.hasNext()){
            Object [] objRep = (Object []) iteRep.next();
            int idRepuesto=Integer.parseInt(String.valueOf(objRep[0]));
            int idLinea=Integer.parseInt(String.valueOf(objRep[1]));
            SugeridoRAM sug;
            Iterator<SugeridoRAM> puntero=tablaRam.iterator();
            while(puntero.hasNext()){
                sug=puntero.next();
                if(idRepuesto==sug.getIdrepuesto() && idLinea==sug.getIdlinea()){
                    double totfob=sug.getCantPedida()*sug.getFob();
                    Object nuevo[]={sug.getNumParte(),sug.getCodSecundario(),
                    sug.getDescripcion(),sug.getAplicacion(),getComaMillar(sug.getCantPedida()),
                    getComaMillar(sug.getStockFisico()),getComaMillar(sug.getDemanda()),
                    getComaMillar(sug.getStockSugerido()),sug.getFob(),totfob};
                    temp.addRow(nuevo);
                    fobTot+=Double.parseDouble(String.valueOf(sug.getCantPedida()))*sug.getFob();
                    double valor=Double.parseDouble(String.valueOf(sug.getFob()));
                    formatearColumnaDerecha(valor,contadorHallazgo,8);
                    formatearColumnaDerecha(totfob,contadorHallazgo,9);
                    contadorHallazgo++;
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
        return contadorHallazgo;
    }
    private void cargarTabRAMFilBusActSug(){
        SugeridoRAM sugRam=new SugeridoRAM();
        //iuactsug.mostrarListaRAM();
        for(int j=0;j<tablaRam.size();j++){
            for(int k=0;k<tablaRAMFB.size();k++){
                if(tablaRam.get(j).getIdrepuesto().equals(tablaRAMFB.get(k).getIdrepuesto()) &&
                   tablaRam.get(j).getIdlinea().equals(tablaRAMFB.get(k).getIdlinea())){
                    sugRam=tablaRAMFB.get(k);
                    sugRam.setCantPedida(tablaRam.get(j).getCantPedida());
                    sugRam.setCantEntregada(tablaRam.get(j).getCantEntregada());
                    sugRam.setCantPendiente(tablaRam.get(j).getCantPendiente());
                    sugRam.setDemanda(tablaRam.get(j).getDemanda());
                    sugRam.setFob(tablaRam.get(j).getFob());
                    tablaRAMFB.set(k,sugRam);
                    break;
                }
            }
        }
    }
    public int buscarEnRAMBuscarFiltrarxNumParte(String numParte){
        cargarTabRAMFilBusActSug();
        List lista=new ArrayList();
        lista=sr.buscaRepuestosXNombreIncompleto(numParte);
        Iterator iteRep = lista.iterator();
        DefaultTableModel temp = null;
        if(opcion==1){
            temp=(DefaultTableModel) iusug.tb_sugerido.getModel();
        }else if(opcion==2){
            temp=(DefaultTableModel) iuactsug.tb_sugerido.getModel();
        }
        int contadorHallazgo=0;
        double fobTot=0.00,totfob=0.00;
        while(iteRep.hasNext()){
            Object [] objRep = (Object []) iteRep.next();
            int idRepuesto=Integer.parseInt(String.valueOf(objRep[0]));///clave1
            int idLinea=Integer.parseInt(String.valueOf(objRep[1]));///clave2
            SugeridoRAM sug;
            Iterator<SugeridoRAM> puntero=tablaRAMFB.iterator();
            while(puntero.hasNext()){
                sug=puntero.next();
                if(idRepuesto==sug.getIdrepuesto() && idLinea==sug.getIdlinea()){
                    Object nuevo[]={sug.getNumParte(),sug.getCodSecundario(),sug.getDescripcion(),
                    sug.getAplicacion(),getComaMillar(sug.getCantPedida()),getComaMillar(sug.getCantEntregada()),
                    getComaMillar(sug.getCantPendiente()),getComaMillar(sug.getStockFisico()),
                    getComaMillar(sug.getDemanda()),getComaMillar(sug.getStockSugerido()),sug.getFob()};
                    temp.addRow(nuevo);
                    totfob=Double.parseDouble(String.valueOf(sug.getCantEntregada()*sug.getFob()));
                    fobTot+=totfob;
                    double valor=Double.parseDouble(String.valueOf(sug.getFob()));
                    formatearColumnaDerecha(valor,contadorHallazgo,10);
                    formatearColumnaDerecha(totfob,contadorHallazgo,11);
                    contadorHallazgo++;
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
        return contadorHallazgo;
    }
    public int buscarEnRAMBuscarFiltrarxCodSecundario(String codSecundario){
        cargarTabRAMFilBusActSug();
        List lista=new ArrayList();
        lista=sr.buscaRepuestosXNumSecundario(codSecundario);
        Iterator iteRep = lista.iterator();
        DefaultTableModel temp = null;
        if(opcion==1){
            temp=(DefaultTableModel) iusug.tb_sugerido.getModel();
        }else if(opcion==2){
            temp=(DefaultTableModel) iuactsug.tb_sugerido.getModel();
        }
        int contadorHallazgo=0;
        double fobTot=0.00,totfob=0.00;
        while(iteRep.hasNext()){
            Object [] objRep = (Object []) iteRep.next();
            int idRepuesto=Integer.parseInt(String.valueOf(objRep[0]));
            int idLinea=Integer.parseInt(String.valueOf(objRep[1]));
            SugeridoRAM sug;
            Iterator<SugeridoRAM> puntero=tablaRAMFB.iterator();
            while(puntero.hasNext()){
                sug=puntero.next();
                if(idRepuesto==sug.getIdrepuesto() && idLinea==sug.getIdlinea()){
                    Object nuevo[]={sug.getNumParte(),sug.getCodSecundario(),sug.getDescripcion(),
                    sug.getAplicacion(),getComaMillar(sug.getCantPedida()),getComaMillar(sug.getCantEntregada()),
                    getComaMillar(sug.getCantPendiente()),getComaMillar(sug.getStockFisico()),
                    getComaMillar(sug.getDemanda()),getComaMillar(sug.getStockSugerido()),sug.getFob()};
                    temp.addRow(nuevo);
                    totfob=Double.parseDouble(String.valueOf(sug.getCantEntregada()))*sug.getFob();
                    fobTot+=totfob;
                    double valor=Double.parseDouble(String.valueOf(sug.getFob()));
                    formatearColumnaDerecha(valor,contadorHallazgo,10);
                    formatearColumnaDerecha(totfob,contadorHallazgo,11);
                    contadorHallazgo++;
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
        return contadorHallazgo;
    }
    public int buscarEnRAMBuscarFiltrarxDescripcion(String descripcion) {
        cargarTabRAMFilBusActSug();
        List lista=new ArrayList();
        lista=sr.buscaRepuestosXDescripcion(descripcion);
        Iterator iteRep = lista.iterator();
        DefaultTableModel temp = null;
        if(opcion==1){
            temp=(DefaultTableModel) iusug.tb_sugerido.getModel();
        }else if(opcion==2){
            temp=(DefaultTableModel) iuactsug.tb_sugerido.getModel();
        }
        int contadorHallazgo=0;
        double fobTot=0.00,totfob=0.00;
        while(iteRep.hasNext()){
            Object [] objRep = (Object []) iteRep.next();
            int idRepuesto=Integer.parseInt(String.valueOf(objRep[0]));
            int idLinea=Integer.parseInt(String.valueOf(objRep[1]));
            SugeridoRAM sug;
            Iterator<SugeridoRAM> puntero=tablaRAMFB.iterator();
            while(puntero.hasNext()){
                sug=puntero.next();
                if(idRepuesto==sug.getIdrepuesto() && idLinea==sug.getIdlinea()){
                    Object nuevo[]={sug.getNumParte(),sug.getCodSecundario(),sug.getDescripcion(),
                    sug.getAplicacion(),getComaMillar(sug.getCantPedida()),getComaMillar(sug.getCantEntregada()),
                    getComaMillar(sug.getCantPendiente()),getComaMillar(sug.getStockFisico()),
                    getComaMillar(sug.getDemanda()),getComaMillar(sug.getStockSugerido()),sug.getFob()};
                    temp.addRow(nuevo);
                    totfob=Double.parseDouble(String.valueOf(sug.getCantEntregada()))*sug.getFob();
                    fobTot+=totfob;
                    double valor=Double.parseDouble(String.valueOf(sug.getFob()));
                    formatearColumnaDerecha(valor,contadorHallazgo,10);
                    formatearColumnaDerecha(totfob,contadorHallazgo,11);
                    contadorHallazgo++;
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
        return contadorHallazgo;
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
            for (int i=0;i<filas;i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgTipoBusqueda;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rbCodSecundario;
    private javax.swing.JRadioButton rbDescripcion;
    private javax.swing.JRadioButton rbNumParte;
    private javax.swing.JTextField txtCodSecundario;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNumParte;
    // End of variables declaration//GEN-END:variables
}