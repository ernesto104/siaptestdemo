package Presentacion.ActualizarPrecios;

import Entidades.Importaciones.Ordenacion;
import Entidades.Repuestos;
import static Presentacion.ActualizarPrecios.IU_Precios.iuMantenimientoRep;
import Servicios.Importaciones.Servicio_Repuestos;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class IU_MantenimientoRepuestos extends javax.swing.JFrame {
    static FREP031 filtro;
    private Object[] row;
    private int tipoFiltro;
    private String valorFiltro;
    private boolean estado = false;
    
    public IU_MantenimientoRepuestos(FREP031 fil) {
        initComponents();
        Ordenacion ord = new Ordenacion();
        ord.eventoTabla(this);
        filtro = fil;
        row = new Object[8];
        tipoFiltro = filtro.getTipoFiltro();
        valorFiltro = filtro.getValorFiltro();
        cargarRepuestosXFiltro();
        alinearCeldasADerecha();
        filtro.setEnabled(false);
    }
    
    public void setEstado(boolean est){
        estado = est;
    }
    
    private void alinearCeldasADerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tbRepuestos.getColumnModel().getColumn(0).setCellRenderer(tcr);
        tbRepuestos.getColumnModel().getColumn(1).setCellRenderer(tcr);
        tbRepuestos.getColumnModel().getColumn(3).setCellRenderer(tcr);
        tbRepuestos.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tbRepuestos.getColumnModel().getColumn(5).setCellRenderer(tcr);
    }
    
    public int getTipoFiltro(){
        return tipoFiltro;
    }
    
    public String getValorFiltro(){
        return valorFiltro;
    }
    
    public FREP031 getIUActPrecios(){
        return filtro;
    }
    
    public void cargarRepuestosXFiltro(){
        btnRestaurar.setEnabled(false);
        Servicio_Repuestos sr = new Servicio_Repuestos();
        List lista = null;
        
        if ( filtro.getTipoFiltro() == 1 ) {
            lista = sr.repuestosXLineas(Integer.parseInt(filtro.getValorFiltro().trim()));
            
        } else if ( filtro.getTipoFiltro() == 2 ) {
            lista = sr.buscarRepuestosXMarca(filtro.getValorFiltro());
        }
        
        if ( lista.size() == 0 ) {
            btnPrecios.setEnabled(false);
        } else {
            btnPrecios.setEnabled(true);
        }
        Iterator ite = lista.iterator();
        DefaultTableModel table = (DefaultTableModel) tbRepuestos.getModel();
        int i = 0;
        
        while ( ite.hasNext() ) {
            Repuestos rep = (Repuestos) ite.next();
            row[0] = rep.getId().getIdrepuesto();
            row[1] = rep.getId().getIdlinea();
            row[2] = rep.getDescripcion();
            row[3] = rep.getPreciolista();
            row[4] = 0;
            row[5] = rep.getStock();
            row[6] = rep.getMarca();
            row[7] = rep.getDescrmodelo();
            table.addRow(row);
            formatearColumnaDerechaSugerido(Double.parseDouble(String.valueOf(row[3])),i,3);
            i++;
        }
    }
    
    private void formatearColumnaDerechaSugerido(double valor, int fila, int columna){
        String price = "";
        DecimalFormatSymbols simbolos = DecimalFormatSymbols.getInstance(Locale.ENGLISH);
        DecimalFormat formateador = new DecimalFormat("###,###.##",simbolos);
        price=formateador.format(valor);
        int indice = price.indexOf(".");
        
        if ( indice == -1 ) {
            price = price + ".00";
            
        } else if ( price.length() - indice == 2 ) {
            price = price + "0";
        }
        tbRepuestos.setValueAt(price,fila,columna);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbRepuestos = new javax.swing.JTable();
        btnPrecios = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnRestaurar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("ACTUALIZACION DE PRECIOS EN REPUESTOS");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        tbRepuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID REPUESTO ▲▼", "ID LINEA▲▼", "DESCRIPCION▲▼", "PRECIO LISTA", "PRECIO ANTERIOR", "STOCK", "MARCA", "DESCRIP. MODELO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbRepuestos);
        if (tbRepuestos.getColumnModel().getColumnCount() > 0) {
            tbRepuestos.getColumnModel().getColumn(0).setPreferredWidth(40);
            tbRepuestos.getColumnModel().getColumn(1).setPreferredWidth(20);
            tbRepuestos.getColumnModel().getColumn(2).setPreferredWidth(120);
            tbRepuestos.getColumnModel().getColumn(3).setPreferredWidth(30);
            tbRepuestos.getColumnModel().getColumn(4).setPreferredWidth(40);
            tbRepuestos.getColumnModel().getColumn(5).setPreferredWidth(12);
            tbRepuestos.getColumnModel().getColumn(6).setPreferredWidth(30);
        }

        btnPrecios.setText("PRECIOS");
        btnPrecios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreciosActionPerformed(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnRestaurar.setText("RESTAURAR");
        btnRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnPrecios, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRestaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 907, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrecios, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRestaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        filtro.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    private void btnPreciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreciosActionPerformed
        IU_Precios precios = new IU_Precios(this);
        precios.setVisible(true);
    }//GEN-LAST:event_btnPreciosActionPerformed
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        filtro.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing
    private void btnRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarActionPerformed
        boolean exito = true;
        Servicio_Repuestos sr = new Servicio_Repuestos();
        
        if ( sr.restauraPrecios(iuMantenimientoRep.tbRepuestos.getModel()) ) {
            estado = false;
            JOptionPane.showMessageDialog(null, "Restauración Exitosa","EXITO", JOptionPane.INFORMATION_MESSAGE);
            
        } else {
            estado = true;
            JOptionPane.showMessageDialog(null, "No se pudo restaurar precios de repuestos en la BD","ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRestaurarActionPerformed
    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        if ( estado ) {
            btnRestaurar.setEnabled(true);
        } else {
            btnRestaurar.setEnabled(false);
        }
    }//GEN-LAST:event_formWindowActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnPrecios;
    private javax.swing.JButton btnRestaurar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tbRepuestos;
    // End of variables declaration//GEN-END:variables
}