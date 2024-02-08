package Presentacion.Importaciones;
import Entidades.Importaciones.SugeridoRAM;
import Entidades.Repuestos;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Servicios.Importaciones.FullSelectorListener;
import Servicios.Importaciones.Servicio_Equipos;
import Servicios.Importaciones.Servicio_Repuestos;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
public class IU_NuevoRepuesto extends javax.swing.JFrame {
    static private IU_Sugerido iusug;
    private Servicio_Equipos sl;
    private Repuestos r;
    private Servicio_Repuestos sr;
    private int limNombre;
    private int limDescripcion;
    static private int opcion;
    static IU_ActualizarSugerido iuactsug;
    public IU_NuevoRepuesto(IU_Sugerido sug) {
        initComponents();
        sl=new Servicio_Equipos();
        sl.listarEquipos(cboLineas);
        r=new Repuestos();
        sr=new Servicio_Repuestos();
        iusug=sug;
        iusug.setEnabled(false);
        limNombre=17;
        limDescripcion=40;
        txtNombre.addFocusListener( new FullSelectorListener() );
        txtDescr.addFocusListener( new FullSelectorListener() );
        txtPrecList.addFocusListener( new FullSelectorListener() );
        opcion=1;
    }
    public IU_NuevoRepuesto(IU_ActualizarSugerido actSug) {
        initComponents();
        sl=new Servicio_Equipos();
        sl.listarEquipos(cboLineas);
        r=new Repuestos();
        sr=new Servicio_Repuestos();
        iuactsug=actSug;
        iuactsug.setEnabled(false);
        limNombre=17;
        limDescripcion=40;
        txtNombre.addFocusListener( new FullSelectorListener() );
        txtDescr.addFocusListener( new FullSelectorListener() );
        txtPrecList.addFocusListener( new FullSelectorListener() );
        opcion=2;
    }
    public void agregar(){
        String name=txtNombre.getText();
        String line=(String)cboLineas.getSelectedItem();
        String descr=txtDescr.getText();
        String price=txtPrecList.getText();
        JOptionPane jop = new JOptionPane();
        if(validarNuevoRepuesto(name,descr,price)){
            r=sr.prepararNuevoRepuesto(name,line,descr,price);
            if (JOptionPane.showConfirmDialog(this, "¿Está seguro de registrar el repuesto?", 
                "Confirmacion", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION) {
                if(sr.registrarRepuesto(r)){
                    agregarFilaATabla(r);
                    ArrayList tablaRAM = new ArrayList();
                    if(opcion==1){
                        tablaRAM=this.iusug.getTablaRAM();
                        iusug.setEnabled(true);
                    }else if(opcion==2){
                        tablaRAM=this.iuactsug.getTablaRAM();
                        iuactsug.setEnabled(true);
                    }
                    SugeridoRAM sugTabla=new SugeridoRAM();
                    sugTabla.setNumParte(name);
                    sugTabla.setCodSecundario("");
                    sugTabla.setDescripcion(descr);
                    sugTabla.setAplicacion("");
                    sugTabla.setCantPedida(0);
                    if(opcion==2){
                        sugTabla.setCantEntregada(0);
                        sugTabla.setCantPendiente(0);
                    }
                    sugTabla.setStockFisico(0);
                    sugTabla.setDemanda(0);
                    sugTabla.setStockSugerido(0);
                    sugTabla.setFob(0.00);
                    
                    Repuestos rep=sr.getRepuesto(sugTabla.getNumParte());
                    sugTabla.setIdrepuesto(rep.getId().getIdrepuesto());
                    sugTabla.setIdlinea(rep.getId().getIdequipo());
                    tablaRAM.add(sugTabla);
                    if(opcion==1){
                        this.iusug.setTablaRAM(tablaRAM);
                    }else if(opcion==2){//Actualizar tablaRAMFB:
                        this.iuactsug.setTablaRAM(tablaRAM);
                        //iuactsug.mostrarListaRAM();
                        ArrayList tablaRAMFB=new ArrayList();
                        tablaRAMFB=this.iuactsug.getTablaRAMBuscarFiltrar();
                        tablaRAMFB.add(sugTabla);
                        this.iuactsug.setTablaRAMFB(tablaRAMFB);
                    }
                    dispose();
                }else{
                    jop.showMessageDialog(null, "No se pudo registrar el repuesto en la BD",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                if(opcion==1){
                    iusug.setEnabled(true);
                }else if(opcion==2){
                    iuactsug.setEnabled(true);
                }
                dispose();
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtDescr = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        cboLineas = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPrecList = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("AGREGAR REPUESTO");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtDescr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescrKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescrKeyTyped(evt);
            }
        });

        jLabel4.setText("PRECIO LISTA");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        cboLineas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboLineasKeyPressed(evt);
            }
        });

        jLabel2.setText("LINEA:");

        jLabel3.setText("DESCRIPCION:");

        txtPrecList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecListKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecListKeyTyped(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel1.setText("NOMBRE:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cboLineas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNombre)
                                    .addComponent(txtPrecList, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescr))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboLineas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDescr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
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
    private void txtDescrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescrKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPrecList.grabFocus();
        }
    }//GEN-LAST:event_txtDescrKeyPressed
    private void txtDescrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescrKeyTyped
        if (txtDescr.getText().length()== limDescripcion){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtDescrKeyTyped
    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            cboLineas.grabFocus();
        }
    }//GEN-LAST:event_txtNombreKeyPressed
    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        if (txtNombre.getText().length()== limNombre){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_txtNombreKeyTyped
    private void cboLineasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboLineasKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtDescr.grabFocus();
        }
    }//GEN-LAST:event_cboLineasKeyPressed
    private void txtPrecListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecListKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            agregar();
        }
    }//GEN-LAST:event_txtPrecListKeyPressed
    private void txtPrecListKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecListKeyTyped
        char caracter = evt.getKeyChar();
        if(((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter!='.')){
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecListKeyTyped
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        if(opcion==1){
            iusug.setEnabled(true);
        }else if(opcion==2){
            iuactsug.setEnabled(true);
        }
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private boolean validarNuevoRepuesto(String name, String descr, String precio){
        boolean val=false;
        Servicio_Repuestos sr=new Servicio_Repuestos();
        JOptionPane jop = new JOptionPane();
        if(name.equals("")){
            jop.showMessageDialog(null, "Por favor, ingrese el nombre del repuesto","CAMPO INCOMPLETO", JOptionPane.OK_OPTION);
            txtNombre.grabFocus();
        }else if(descr.equals("")){
            jop.showMessageDialog(null, "Por favor, ingrese la descripción del repuesto","CAMPO INCOMPLETO", JOptionPane.OK_OPTION);
            txtDescr.grabFocus();
        }else if(precio.equals("")){
            jop.showMessageDialog(null, "Por favor, ingrese el precio del repuesto","CAMPO INCOMPLETO", JOptionPane.OK_OPTION);
            txtPrecList.grabFocus();
        }else if(sr.getRepuesto(name)!=null){
            jop.showMessageDialog(null, "Nombre del repuesto \""+name+"\" ya fue registrado antes.\n"+
                    "Ingrese otro NOMBRE para el repuesto.","ERROR", JOptionPane.ERROR_MESSAGE);
            txtNombre.grabFocus();
        }else{
            val=true;
        }
        return val;
    }    
    private void agregarFilaATabla(Repuestos r) {
        Object row[]=null;
        if(opcion==1){
            row=new Object[10];
        }else if(opcion==2){
            row=new Object[12];
        }
        DefaultTableModel temp = null;
        row[0]=r.getCodrepuesto();
        row[1]="";
        row[2]=r.getDescripcion();
        row[3]="";
        row[4]=0;
        row[5]=0;
        row[6]=0;
        row[7]=0;
        if(opcion==1){
            row[8]=0.0;
            row[9]=0.0;
            temp=(DefaultTableModel) iusug.tb_sugerido.getModel();
            temp.addRow(row);
            formatearColumnaDerecha(Double.parseDouble(String.valueOf(row[8])),temp.getRowCount()-1,8);
            formatearColumnaDerecha(Double.parseDouble(String.valueOf(row[9])),temp.getRowCount()-1,9);
        }else if(opcion==2){
            row[8]=0;
            row[9]=0;
            row[10]=0.00;
            row[11]=0.00;
            temp=(DefaultTableModel) iuactsug.tb_sugerido.getModel();
            temp.addRow(row);
            formatearColumnaDerecha(Double.parseDouble(String.valueOf(row[10])),temp.getRowCount()-1,10);
            formatearColumnaDerecha(Double.parseDouble(String.valueOf(row[11])),temp.getRowCount()-1,11);
        }
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnSalir;
    public javax.swing.JComboBox cboLineas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtDescr;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecList;
    // End of variables declaration//GEN-END:variables
}