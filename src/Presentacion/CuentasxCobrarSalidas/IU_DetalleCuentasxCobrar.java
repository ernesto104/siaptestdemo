package Presentacion.CuentasxCobrarSalidas;

import Presentacion.CuentasxCobrarFacturas.*;
import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Cabecsalvar;
import Entidades.Detallees;
import Servicios.Servicio_Cabeces;
import Servicios.Servicio_Control;
import Servicios.Servicio_DetalleEs;
import Servicios.Servicio_Salvar;
import Servicios.facturacion.Servicio_Documentos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Keily Mendiolaza
 */
public class IU_DetalleCuentasxCobrar extends javax.swing.JFrame {

    DefaultTableModel tabla, modtab;
    int filaseleccionada;
    Cabecsalvar cab;

    public IU_DetalleCuentasxCobrar(DefaultTableModel t, int fila) {
        initComponents();
        setLocationRelativeTo(null);
        tabla = t;
        modtab = (DefaultTableModel) tablaDetalles.getModel();
        filaseleccionada = fila;
        IniciarDetalles();

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

    public void IniciarDetalles() {
        Servicio_Documentos sc = new Servicio_Documentos();
        Servicio_Control sco = new Servicio_Control();
        int igv = sco.listar_control().get(0).getImpuestoigv();
        
        /*CabecesId cb = new CabecesId();
        cb.setTipotra("V");
        cb.setTipodoc(tabla.getValueAt(filaseleccionada, 0).toString());
        cb.setNrorserie(tabla.getValueAt(filaseleccionada, 2).toString());
        cb.setNrodocumento(tabla.getValueAt(filaseleccionada, 1).toString());

        cab = sc.obtenerCabecera_id(cb);*/
        String tip = tipoDoc(tabla.getValueAt(filaseleccionada, 0).toString());
        txt_tipodoc.setText(tip);
        txt_numdoc.setText(tabla.getValueAt(filaseleccionada, 1).toString());
        
        /*
        if (cab.getNroguia() == null) {
            txt_numguia.setText("");
        } else {
            txt_numguia.setText(String.valueOf(cab.getNroguia()));
        }*/
        cab= sc.getCabecSalvarCodSal(Integer.parseInt(tabla.getValueAt(filaseleccionada, 1).toString()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        txt_fecha.setText(sdf.format(cab.getFecha()));
        txt_nombrecl.setText(tabla.getValueAt(filaseleccionada, 5).toString());
        txt_valorventa.setText(String.valueOf(cab.getValorventa()));
        //txt_totalcosto.setText(String.valueOf(cab.getCostototal()));
        txt_impuesto.setText(String.valueOf(igv));
        txt_totfac.setText(String.valueOf(cab.getTotal()));

        Servicio_DetalleEs sd = new Servicio_DetalleEs();

        ArrayList<Detallees> lista = sd.listarPorSK(cab);

        for (Detallees det : lista) {
            String a = det.getRepuestos().getCodrepuesto();
            int b = det.getCantentregada();
            double c = det.getPreciolista();
            double d1 = det.getDescuento1();
            double d2 = det.getDescuento2();
            double d3 = det.getDescuento3();
            double d4 = det.getDescuento4();
            double totdesc = Math.rint((d1 + d2 + d3 + d4) * 100) / 100;
            double total = Math.rint(((b * c) - totdesc) * 100) / 100;
            Object[] lineas = {a, b, c, totdesc, total};
            modtab.addRow(lineas);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_tipodoc = new javax.swing.JTextField();
        txt_numdoc = new javax.swing.JTextField();
        txt_fecha = new javax.swing.JTextField();
        txt_nombrecl = new javax.swing.JTextField();
        txt_valorventa = new javax.swing.JTextField();
        txt_impuesto = new javax.swing.JTextField();
        txt_totfac = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_numguia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_totalcosto = new javax.swing.JTextField();
        txt_docref = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        botonMov = new javax.swing.JButton();
        botonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("CONSULTAS");

        jLabel2.setText("Tipo de Documento: ");

        jLabel3.setText("Número de Documento: ");

        jLabel4.setText("Fecha de Emisión: ");

        jLabel5.setText("Nombre del Cliente: ");

        jLabel6.setText("Valor de Venta:");

        jLabel7.setText("Impuesto (I.G.V):");

        jLabel8.setText("Total Factura: ");

        txt_tipodoc.setEditable(false);
        txt_tipodoc.setBackground(new java.awt.Color(255, 255, 255));

        txt_numdoc.setEditable(false);
        txt_numdoc.setBackground(new java.awt.Color(255, 255, 255));

        txt_fecha.setEditable(false);
        txt_fecha.setBackground(new java.awt.Color(255, 255, 255));

        txt_nombrecl.setEditable(false);
        txt_nombrecl.setBackground(new java.awt.Color(255, 255, 255));

        txt_valorventa.setEditable(false);
        txt_valorventa.setBackground(new java.awt.Color(255, 255, 255));

        txt_impuesto.setEditable(false);
        txt_impuesto.setBackground(new java.awt.Color(255, 255, 255));

        txt_totfac.setEditable(false);
        txt_totfac.setBackground(new java.awt.Color(255, 255, 255));
        txt_totfac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totfacActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("SK= Salidas Varias");

        jLabel10.setText("Nº Guía: ");

        txt_numguia.setEditable(false);
        txt_numguia.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Total Costos: ");

        jLabel12.setText("Doc. Referencia: ");

        txt_totalcosto.setEditable(false);
        txt_totalcosto.setBackground(new java.awt.Color(255, 255, 255));

        txt_docref.setEditable(false);
        txt_docref.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("DETALLLE DE SALIDA");

        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Parte", "Cantidad", "Precio", "Descuentos", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaDetalles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaDetalles);
        if (tablaDetalles.getColumnModel().getColumnCount() > 0) {
            tablaDetalles.getColumnModel().getColumn(0).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(1).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(2).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(3).setResizable(false);
            tablaDetalles.getColumnModel().getColumn(4).setResizable(false);
        }

        botonMov.setText("Movimiento");
        botonMov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMovActionPerformed(evt);
            }
        });

        botonSalir.setText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(147, 147, 147)
                                .addComponent(botonMov, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txt_tipodoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel9))
                                    .addComponent(txt_totfac, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txt_nombrecl, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_impuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_valorventa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11)
                                                .addComponent(jLabel12))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txt_totalcosto)
                                                .addComponent(txt_docref, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_numdoc, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_numguia, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(286, 286, 286)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_tipodoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_numdoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_numguia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_nombrecl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_valorventa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_impuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_docref, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txt_totalcosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txt_totfac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonMov, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        botonMov.getAccessibleContext().setAccessibleName("");
        botonSalir.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_totfacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totfacActionPerformed

    }//GEN-LAST:event_txt_totfacActionPerformed

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirActionPerformed
        dispose();
    }//GEN-LAST:event_botonSalirActionPerformed

    private void botonMovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMovActionPerformed


    }//GEN-LAST:event_botonMovActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonMov;
    private javax.swing.JButton botonSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTextField txt_docref;
    private javax.swing.JTextField txt_fecha;
    private javax.swing.JTextField txt_impuesto;
    private javax.swing.JTextField txt_nombrecl;
    private javax.swing.JTextField txt_numdoc;
    private javax.swing.JTextField txt_numguia;
    private javax.swing.JTextField txt_tipodoc;
    private javax.swing.JTextField txt_totalcosto;
    private javax.swing.JTextField txt_totfac;
    private javax.swing.JTextField txt_valorventa;
    // End of variables declaration//GEN-END:variables
}
