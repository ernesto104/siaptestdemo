package Presentacion.CuentasxCobrarSalidas;

import Presentacion.CuentasxCobrarFacturas.*;
import Mantenimiento.ControlDAO;
import Servicios.CuentasxCobrar.Sv_Impresion;
import Servicios.Servicio_Control;
import Servicios.TipoMensaje;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Keily Mendiolaza
 */
public class IU_ImprimirCuentasxCobrarSK extends javax.swing.JFrame {

    FREP070 lc;
    DefaultTableModel mod;
    int fila;

    public IU_ImprimirCuentasxCobrarSK(DefaultTableModel t, int fila, FREP070 lc) {
        initComponents();
        mod = t;
        this.lc = lc;
        this.fila = fila;
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        boton_aceptar = new javax.swing.JButton();
        boton_salir = new javax.swing.JButton();
        rbseleccionados = new javax.swing.JRadioButton();
        rbtodo = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        boton_aceptar.setText("Aceptar");
        boton_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_aceptarActionPerformed(evt);
            }
        });

        boton_salir.setText("Salir");
        boton_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_salirActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbseleccionados);
        rbseleccionados.setText("Seleccionados");

        buttonGroup1.add(rbtodo);
        rbtodo.setText("Todo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(boton_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(boton_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rbseleccionados)
                    .addComponent(rbtodo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(rbtodo)
                .addGap(18, 18, 18)
                .addComponent(rbseleccionados)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boton_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boton_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_aceptarActionPerformed
        TipoMensaje tm = new TipoMensaje();
        
        if ( tm.Confirmacion(tm.PREGUNTA_OPERACION) == tm.SI ) {
            dispose();
            Servicios.Importaciones.Servicio_Control sc = new Servicios.Importaciones.Servicio_Control();
            String rutaReportes = sc.getControlUnico().getRutareportes();
            String url1, url2;
            
            if ( rbtodo.isSelected() ) {
                HashMap parametros = SetParametros();
                ArrayList listado = SetLista();
                
                url1 = rutaReportes + "\\cuentasCorrientes\\Impresion_SK.jrxml";
                url2 = rutaReportes + "\\cuentasCorrientes\\Impresion_SK.jasper";
                
                try {
                    convertPedidoToPDF(url1, url2, parametros, listado);

                } catch ( Exception ex ) {
                    System.out.println("Error(IU_ResumenGeneral):" + ex.getMessage());
                }
//                String ruta = new ControlDAO().Obtener_Objeto(1).getRutareportes();
//                Sv_Impresion.exporta(4, lista, parametros, "reportes/Impresion_Factura.pdf");
            }

            if ( rbseleccionados.isSelected() ) {
                HashMap parametros = SetParametros();
                ArrayList listado = SetListaSeleccionados();
                
                url1 = rutaReportes + "\\cuentasCorrientes\\Impresion_SK.jrxml";
                url2 = rutaReportes + "\\cuentasCorrientes\\Impresion_SK.jasper";
                try {
                    convertPedidoToPDF(url1, url2, parametros, listado);

                } catch ( Exception ex ) {
                    System.out.println("Error(IU_ResumenGeneral):" + ex.getMessage());
                }
//                Sv_Impresion.exporta(4, lista, parametros, "reportes/Impresion_Factura.pdf");
            }
        }
    }//GEN-LAST:event_boton_aceptarActionPerformed

    public void convertPedidoToPDF(String url1, String url2,
                                   HashMap parametros,
                                   ArrayList listado) throws Exception{
        
        JasperCompileManager.compileReportToFile(url1,url2);
        JasperReport reporte = (JasperReport) JRLoader.loadObject(url2);  
        
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte,
                                                               parametros,
                                                               new JRBeanCollectionDataSource(listado));
        
        JasperViewer.viewReport(jasperPrint, false, Locale.getDefault());
    }
    
    private void boton_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_salirActionPerformed
        dispose();
    }//GEN-LAST:event_boton_salirActionPerformed

    private ArrayList SetListaSeleccionados() {
        ArrayList<ListaFacturas> lista = new ArrayList<>();
        for (int i = 0; i < mod.getRowCount(); i++) {
            if ((boolean) mod.getValueAt(i, 10) == true) {
                /* String tipdoc = tipoDoc(String.valueOf(mod.getValueAt(i, 0)));
                 String numdoc = String.valueOf(mod.getValueAt(i, 1));
                 String fecha = String.valueOf(mod.getValueAt(i, 3));
                 String ruc = String.valueOf(mod.getValueAt(i, 4));
                 String cliente = String.valueOf(mod.getValueAt(i, 5));
                 String facturado = String.valueOf(mod.getValueAt(i, 6));
                 String saldo = String.valueOf(mod.getValueAt(i, 8));*/
                String tipdoc = tipoDoc(String.valueOf(mod.getValueAt(i, 0)));
                String numdoc = String.valueOf(mod.getValueAt(i, 1));
                String fecha = String.valueOf(mod.getValueAt(i, 3));
                String ruc = String.valueOf(mod.getValueAt(i, 4));
                String cliente = String.valueOf(mod.getValueAt(i, 5));
                String facturado = String.valueOf(mod.getValueAt(i, 6)) + " " + String.valueOf(mod.getValueAt(i, 7));
                String saldo = String.valueOf(mod.getValueAt(i, 9));
                lista.add(new ListaFacturas(tipdoc, numdoc, fecha, ruc, cliente, facturado, saldo));
            }
        }
        return lista;
    }

    private ArrayList SetLista() {
        ArrayList<ListaFacturas> lista = new ArrayList<>();
        for (int i = 0; i < mod.getRowCount(); i++) {
            //String tipdoc = tipoDoc(String.valueOf(mod.getValueAt(i, 0)));
            String tipdoc = tipoDoc(String.valueOf(mod.getValueAt(i, 0)));
            String numdoc = String.valueOf(mod.getValueAt(i, 1));
            String fecha = String.valueOf(mod.getValueAt(i, 3));
            String ruc = String.valueOf(mod.getValueAt(i, 4));
            String cliente = String.valueOf(mod.getValueAt(i, 5));
            String facturado = String.valueOf(mod.getValueAt(i, 6)) + " " + String.valueOf(mod.getValueAt(i, 7));
            String saldo = String.valueOf(mod.getValueAt(i, 9));
            lista.add(new ListaFacturas(tipdoc, numdoc, fecha, ruc, cliente, facturado, saldo));
        }
        return lista;
    }

    private HashMap SetParametros() {
        Servicio_Control sc = new Servicio_Control();
        HashMap mapa = new HashMap<>();
        String titulo = lc.TextoFacturas.getText();
        String empresa = sc.listar_control().get(0).getNombrempresa();
        mapa.put("titulo", titulo);
        mapa.put("empresa", empresa);
        return mapa;
    }

    public String tipoDoc(String doc) {
        String tipo = "";
        switch (doc) {
            case "01":
                tipo = "F";
                break;
            case "02":
                tipo = "B";
                break;
            case "03":
                tipo = "NC";
                break;
            case "04":
                tipo = "ND";
                break;
            case "12":
                tipo = "SK";
                break;
            default:
                tipo = "Entrada no valida";
                break;
        }

        return tipo;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton_aceptar;
    private javax.swing.JButton boton_salir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton rbseleccionados;
    private javax.swing.JRadioButton rbtodo;
    // End of variables declaration//GEN-END:variables
}
