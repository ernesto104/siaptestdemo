package Presentacion.Importaciones;

import Entidades.Importaciones.Ordenacion;
import Entidades.Importaciones.SugeridoRAM;
import Entidades.Otros.Monetario;
import Servicios.Importaciones.Servicio_Repuestos;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import Entidades.Repuestos;
import java.util.Iterator;
import javax.swing.SwingConstants;
import Servicios.Importaciones.Servicio_CabSugerido;
import Servicios.Importaciones.Servicio_Control;
import Servicios.Importaciones.Servicio_DetalleES;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class IU_Sugerido extends javax.swing.JFrame {
    static private FREP024 menu;
    private Servicio_Repuestos sr;
    private Servicio_CabSugerido scs;
    private Servicio_DetalleES sdetEs;
    private ArrayList tablaRAM;
    SugeridoRAM sugTabla;
    private boolean filtrarActivo = true;
    private int opcion;
    private ArrayList tablaRAMBuscarFiltrar;
    
    public IU_Sugerido(FREP024 m) {
        initComponents();
        Ordenacion ord = new Ordenacion();
        ord.eventoTabla(this);
        menu = m;
        opcion = 1;
        alinearColumnasDerecha();
        cargarIdSugerido();
        sdetEs = new Servicio_DetalleES(this);
        tablaRAM = new ArrayList();
        tablaRAM = sdetEs.cargaTablaASugerido(tb_sugerido,this);
        
        asignarMoneda();
    }
    
    private void asignarMoneda() {
        Servicio_Control sc = new Servicio_Control();
        String moneda = Monetario.asignarMoneda(sc.getControlUnico().getMonedarepuestos());
        lblMoneda.setText(moneda);
    }
    
    public ArrayList getTablaRAMBuscarFiltrar() {
        return tablaRAMBuscarFiltrar;
    }
    
    public void formatearDecimalDerechaEnTextField(JTextField txt, double valor) {
        String moneyCad = "";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##", simbolos);
        moneyCad = formateador.format(valor);
        int indice = moneyCad.indexOf(".");
        
        if ( indice == -1 ) {
            moneyCad = moneyCad + ".00";
            
        } else if( moneyCad.length() - indice == 2 ) {
            moneyCad = moneyCad + "0";
        }
        txt.setText(moneyCad);
    }
    
    private void alinearColumnasDerecha() {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tb_sugerido.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tb_sugerido.getColumnModel().getColumn(5).setCellRenderer(tcr);
        tb_sugerido.getColumnModel().getColumn(6).setCellRenderer(tcr);
        tb_sugerido.getColumnModel().getColumn(7).setCellRenderer(tcr);
        tb_sugerido.getColumnModel().getColumn(8).setCellRenderer(tcr);
        tb_sugerido.getColumnModel().getColumn(9).setCellRenderer(tcr);
    }
    
    public ArrayList getTablaRAM() {
        return tablaRAM;
    }
    
    public void setTablaRAM(ArrayList tabla) {
        tablaRAM = tabla;
    }
    
    public void setTablaRAMFB(ArrayList tabla) {
        tablaRAMBuscarFiltrar = tabla;
    }
    
    public void actualizarFobTotal() {
        double fobTot = 0.00;
        
        for ( int i = 0; i < tb_sugerido.getRowCount(); i++ ) {
            int cantPed;
            
            if ( tb_sugerido.getValueAt(i,4) == null ) {
                cantPed = 0;
            } else {
                cantPed = Integer.parseInt(quitaComa(String.valueOf(tb_sugerido.getValueAt(i,4))));
            }
            double fob;
            
            if ( tb_sugerido.getValueAt(i, 8) == null ) {
                fob = 0.00;
            } else {
                fob = Double.parseDouble(String.valueOf(tb_sugerido.getValueAt(i, 8)));
            }
            formatearColumnaDerecha(fob, i, 8);
            formatearColumnaDerecha(cantPed * fob, i, 9);
            fobTot += cantPed * fob;
        }
        formatearDecimalDerechaEnTextField(txt_Tot_Fob, fobTot);
    }
    
    public String quitaComa(String Nombre1) {
        int c = Nombre1.length();
        String cad = "";
        
        for ( int contador = 0; contador < c; contador++ ) {
            if ( Nombre1.charAt(contador) == ',' ) {
                Nombre1.replace(',',' ');
            } else {
                cad += Nombre1.charAt(contador);
            }
        }
        return cad;
    }
    
    private void formatearColumnaDerecha(double valor, int fila, int columna) {
        String moneyCad = "";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##", simbolos);
        moneyCad = formateador.format(valor);
        int indice = moneyCad.indexOf(".");
        
        if ( indice == -1 ) {
            moneyCad = moneyCad + ".00";
            
        } else if ( moneyCad.length() - indice == 2 ) {
            moneyCad = moneyCad + "0";
        }
        tb_sugerido.setValueAt(moneyCad, fila, columna);
    }
    
    public void llenarListaRAM() {
        tablaRAM = new ArrayList();
        sugTabla = new SugeridoRAM();
        String[] cad = new String[9];
        sr = new Servicio_Repuestos();
        Repuestos rep = new Repuestos();
        
        for ( int i = 0; i < tb_sugerido.getRowCount(); i++ ) {
            for ( int j = 0; j < tb_sugerido.getColumnCount(); j++ ) {
                cad[j] = String.valueOf(tb_sugerido.getValueAt(i, j));
                System.out.println(cad[j]);
            }
            sugTabla.setNumParte(cad[0]);
            sugTabla.setCodSecundario(cad[1]);
            sugTabla.setDescripcion(cad[2]);
            sugTabla.setAplicacion(cad[3]);
            sugTabla.setCantPedida(Integer.parseInt(cad[4]));
            
            if ( cad[5].equals("null") ) {
                cad[5] = "0";
            }
            sugTabla.setStockFisico(Integer.parseInt(cad[5]));
            
            if ( cad[6].equals("null") ) {
                cad[6] = "0";
                
            } else if ( cad[6].indexOf(".") != -1 ) {
                cad[6] = "0";
            }
            sugTabla.setDemanda(Integer.parseInt(cad[6]));
            if ( cad[7].equals("null") ) {
                cad[7] = "0";
            }
            sugTabla.setStockSugerido(Integer.parseInt(cad[7]));
            sugTabla.setFob(Double.parseDouble(cad[8]));

            rep = sr.getRepuesto(sugTabla.getNumParte());
            sugTabla.setIdrepuesto(rep.getId().getIdrepuesto());
            sugTabla.setIdlinea(rep.getId().getIdequipo());
            tablaRAM.add(sugTabla);
        }
    }
    
    public void mostrarListaRAM() {
        System.out.println("mostrarListaRAM...");
        Iterator<SugeridoRAM> puntero = tablaRAM.iterator();
        int i = 0;
        
        while ( puntero.hasNext() ) {
            SugeridoRAM sug = puntero.next();
            System.out.println("i:" + (i+1) + "-" + sug.getNumParte() + "-" + sug.getCodSecundario() + "-"
            + sug.getDescripcion() + "-" + sug.getAplicacion() + "-" + sug.getCantPedida() + "-"
            + sug.getStockFisico() + "-" + sug.getDemanda() + "-" + sug.getStockSugerido() + "-"
            + sug.getFob() + "-" + sug.getIdrepuesto() + "-" + sug.getIdlinea());
            i++;
        }
    }
    
    public void mostrarListaRAMFB() {
        System.out.println("mostrarListaRAMFB");
        Iterator<SugeridoRAM> puntero = tablaRAMBuscarFiltrar.iterator();
        int i = 0;
        
        while ( puntero.hasNext() ) {
            SugeridoRAM sug = puntero.next();
            System.out.println("i:" + (i + 1) + "-" + sug.getNumParte() + "-" + sug.getCodSecundario() + "-"
            + sug.getDescripcion() + "-" + sug.getAplicacion() + "-" + sug.getCantPedida() + "-"
            + sug.getStockFisico() + "-" + sug.getDemanda() + "-" + sug.getStockSugerido() + "-"
            + sug.getFob() + "-" + sug.getIdrepuesto() + "-" + sug.getIdlinea());
            i++;
        }
    }
    
    public FREP024 getMenuOrigen() {
        return menu;
    }
    
    public int getOpcion() {
        return this.opcion;
    }
    
    private void cargarIdSugerido() {
        scs = new Servicio_CabSugerido(this);
        tx_id.setText(String.valueOf(scs.nextId()));
    }   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        btnFiltrar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
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
        lblMoneda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CREAR SUGERIDO DE PEDIDO DE IMPORTACIÓN");
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

        jLabel9.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel9.setText("SUGERIDO DE COMPRAS - PEDIDO:");

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
                "Nº PARTE▲▼", "COD. SEC.▲▼", "DESCRIPCION▲▼", "APLICACION", "CANT. PEDIDA", "STOCK FISICO", "DEMANDA", "SUGERIDO", "FOB", "FOB TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_sugerido);

        txt_Tot_Fob.setEditable(false);
        txt_Tot_Fob.setBackground(new java.awt.Color(255, 255, 255));
        txt_Tot_Fob.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txt_Tot_Fob.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        lblMoneda.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblMoneda.setText("S/.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx_id, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMesesDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_mesProy, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_Tot_Fob, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tx_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_mesProy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMesesDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                                .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txt_Tot_Fob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMoneda)))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dispose();
    }//GEN-LAST:event_formWindowClosing
    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        frmiFiltrarSugerido filtrar = new frmiFiltrarSugerido(this,getTablaRAM());
        filtrar.setVisible(true);
    }//GEN-LAST:event_btnFiltrarActionPerformed
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        IU_NuevoRepuesto iu = new IU_NuevoRepuesto(this);
        iu.setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed
    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int filaSeleccionada = tb_sugerido.getSelectedRow();
        
        if ( filaSeleccionada != -1 ) {
            String numParte = String.valueOf(tb_sugerido.getModel().getValueAt(filaSeleccionada, 0));
            frmiModificarCantPedida modificar = new frmiModificarCantPedida(this, numParte, filaSeleccionada, tablaRAM);
            modificar.setVisible(true);
            
            if ( tablaRAM.size() == tb_sugerido.getRowCount() ) {
                filtrarActivo = true;
                habilitarFiltrar();
                
            } else {
                filtrarActivo = false;
            }
        } else {
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(null, "Seleccione un repuesto de la tabla", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
        }
        //actualizarFobTotal();
    }//GEN-LAST:event_btnModificarActionPerformed
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        frmiBuscarSugerido hijoBuscar = new frmiBuscarSugerido(this, getTablaRAM(), this.tablaRAMBuscarFiltrar);
        hijoBuscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed
    
    public void actualizaTotal(double total) {
        this.txt_Tot_Fob.setText(String.valueOf(total));
    }
    
    public void habilitarFiltrar() {
        DefaultTableModel temp = (DefaultTableModel) tb_sugerido.getModel();        
        
        if ( temp.getRowCount() == 0 ) {
            btnFiltrar.setEnabled(false);
            
        } else {
            btnFiltrar.setEnabled(true);
        }
    }
    
    public void activarFiltrado() {
        filtrarActivo = true;
    }
    
    public int contarFilasSugeridoConCantPedida() {
        int contador = 0;
        SugeridoRAM sug;
        Iterator<SugeridoRAM> puntero = tablaRAM.iterator();
        
        while ( puntero.hasNext() ) {
            sug = puntero.next();
            
            if ( sug.getCantPedida() != 0 ) {
                contador++;
            }
        }
        return contador;
    }
    
    public void limpiaTabla() {
       try {
           DefaultTableModel temp = (DefaultTableModel) tb_sugerido.getModel();
           int a = temp.getRowCount();
           
           for ( int i = 0; i < a; i++ )
               temp.removeRow(0);
           
       } catch ( Exception e ) {
           System.out.println("Error:"+e.getMessage());
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
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMoneda;
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