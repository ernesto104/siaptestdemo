package Presentacion.RepuestosSegunEstratificacion;

import Entidades.Repuestos;
import Presentacion.Facturacion.IU_Demanda;
import java.text.SimpleDateFormat;

public class IU_DatosRepuesto extends javax.swing.JFrame {

    Repuestos rep;

    public IU_DatosRepuesto(Repuestos r,int tipo) { // tipo 0 : repuestos segun estratificacion
                                                    // tipo 1 : lista de precios
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        rep = r;
        if( tipo == 0 ) bt_demanda.setVisible(false);
        LLenarDatos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tx_codRep = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tx_codSeg = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tx_descripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tx_modelo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tx_Lista = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tx_aplicacion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tx_marca = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tx_inventario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tx_unidadVenta = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tx_stockMin = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tx_stock = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tx_nroAlter = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tx_linea = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tx_precioLista = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        tx_costoProm = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        tx_ultimoCosto = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tx_FechaPrec = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        tx_CostoTemp = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        tx_ubicAlmacen = new javax.swing.JTextField();
        tx_fob = new javax.swing.JTextField();
        tx_partidaAr = new javax.swing.JTextField();
        tx_unidadMed = new javax.swing.JTextField();
        tx_margen = new javax.swing.JTextField();
        tx_idAnt = new javax.swing.JTextField();
        tx_estr = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        pn_foto = new javax.swing.JPanel();
        bt_salir = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        bt_demanda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SALIR(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Repuesto"));

        jLabel1.setText("Cod Repuesto");

        tx_codRep.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_codRep.setEnabled(false);

        jLabel2.setText("Cod Segundo");

        tx_codSeg.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_codSeg.setEnabled(false);

        jLabel3.setText("Descripcion");

        tx_descripcion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_descripcion.setEnabled(false);

        jLabel4.setText("Desc Modelo");

        tx_modelo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_modelo.setEnabled(false);

        jLabel5.setText("Desc Lista");

        tx_Lista.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_Lista.setEnabled(false);

        jLabel6.setText("Aplicacion");

        tx_aplicacion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_aplicacion.setEnabled(false);

        jLabel7.setText("Marca");

        tx_marca.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_marca.setEnabled(false);

        jLabel8.setText("Inventario");

        tx_inventario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_inventario.setEnabled(false);

        jLabel9.setText("Unidad de Venta");

        tx_unidadVenta.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_unidadVenta.setEnabled(false);

        jLabel10.setText("Stock Minimo");

        tx_stockMin.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_stockMin.setEnabled(false);

        jLabel11.setText("Stock ");

        tx_stock.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_stock.setEnabled(false);

        jLabel12.setText("Nro Alternativo");

        tx_nroAlter.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_nroAlter.setEnabled(false);

        jLabel13.setText("Linea");

        tx_linea.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_linea.setEnabled(false);

        jLabel14.setText("Precio Lista");

        tx_precioLista.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_precioLista.setEnabled(false);

        jLabel15.setText("Costo Promedio");

        tx_costoProm.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_costoProm.setEnabled(false);

        jLabel16.setText("Ultimo Costo");

        tx_ultimoCosto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_ultimoCosto.setEnabled(false);

        jLabel17.setText("Fecha Precio Costo ");

        tx_FechaPrec.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_FechaPrec.setEnabled(false);

        jLabel18.setText("Precio Costo Temp");

        tx_CostoTemp.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_CostoTemp.setEnabled(false);

        jLabel19.setText("Estrato");

        jLabel20.setText("ID Anterior");

        jLabel21.setText("Margen Util");

        jLabel22.setText("Unidad Medida");

        jLabel23.setText("Partida Arancel");

        jLabel24.setText("FOB Ultimo");

        jLabel25.setText("Ubic Almacen");

        tx_ubicAlmacen.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_ubicAlmacen.setEnabled(false);

        tx_fob.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_fob.setEnabled(false);

        tx_partidaAr.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_partidaAr.setEnabled(false);

        tx_unidadMed.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_unidadMed.setEnabled(false);

        tx_margen.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_margen.setEnabled(false);

        tx_idAnt.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_idAnt.setEnabled(false);

        tx_estr.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tx_estr.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tx_codRep, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tx_codSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tx_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(tx_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tx_aplicacion)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(tx_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tx_Lista, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(30, 30, 30)
                                .addComponent(tx_inventario))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(tx_stockMin)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(tx_stock))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tx_unidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tx_CostoTemp, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                        .addComponent(tx_FechaPrec)
                        .addComponent(tx_precioLista)
                        .addComponent(tx_costoProm)
                        .addComponent(tx_ultimoCosto)
                        .addComponent(tx_nroAlter))
                    .addComponent(tx_linea, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jLabel23)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21)
                    .addComponent(jLabel19)
                    .addComponent(jLabel25)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tx_ubicAlmacen, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(tx_fob)
                    .addComponent(tx_margen)
                    .addComponent(tx_unidadMed)
                    .addComponent(tx_partidaAr)
                    .addComponent(tx_idAnt)
                    .addComponent(tx_estr)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_estr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_idAnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_margen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_unidadMed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_partidaAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_fob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_ubicAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_codRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_codSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_nroAlter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_linea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_precioLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_Lista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_costoProm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_aplicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_ultimoCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_unidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_FechaPrec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tx_stock, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tx_stockMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tx_CostoTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Foto"));

        javax.swing.GroupLayout pn_fotoLayout = new javax.swing.GroupLayout(pn_foto);
        pn_foto.setLayout(pn_fotoLayout);
        pn_fotoLayout.setHorizontalGroup(
            pn_fotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 145, Short.MAX_VALUE)
        );
        pn_fotoLayout.setVerticalGroup(
            pn_fotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_foto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pn_foto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("DATOS DEL REPUESTO");

        bt_demanda.setText("Ver Demanda");
        bt_demanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_demandaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(392, 392, 392)
                        .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(bt_demanda, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_demanda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void SALIR(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SALIR
        if( evt.getKeyChar() == 27 ){
            bt_salir.doClick();
        }
    }//GEN-LAST:event_SALIR

    private void bt_demandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_demandaActionPerformed
        IU_Demanda demanda = new IU_Demanda(rep);
        demanda.setLocationRelativeTo(null);
        demanda.setVisible(true);
    }//GEN-LAST:event_bt_demandaActionPerformed

    private void LLenarDatos() {
        try {
            tx_codRep.setText(rep.getCodrepuesto());
            tx_codSeg.setText(rep.getCodigoseg());
            tx_descripcion.setText(rep.getDescripcion());
            tx_modelo.setText(rep.getDescrmodelo());
            tx_Lista.setText(rep.getDesclista());
            tx_aplicacion.setText(rep.getAplicacion());
            tx_marca.setText(rep.getMarca());
            tx_inventario.setText(String.valueOf(rep.getInventario()));
            tx_unidadVenta.setText(rep.getUnidadventa());
            tx_stockMin.setText(String.valueOf(rep.getStockminimo()));
            tx_stock.setText(String.valueOf(rep.getStock()));
            tx_nroAlter.setText(String.valueOf(rep.getNroalternativo()));
            tx_linea.setText(rep.getEquipos().getDescripcion());
            tx_precioLista.setText(String.valueOf(rep.getPreciolista()));
            tx_costoProm.setText(String.valueOf(rep.getCostopromedio()));
            tx_ultimoCosto.setText(String.valueOf(rep.getPcostoultimo()));
            tx_FechaPrec.setText(new SimpleDateFormat("dd/MM/yyyy").format(rep.getFechapcosto()));
            tx_CostoTemp.setText(String.valueOf(rep.getPcostotemporal()));
            tx_linea.setText(rep.getEquipos().getDescripcion());
            tx_estr.setText(rep.getEstratificacion().getCodigoestratificacion());
            tx_idAnt.setText(rep.getIdrepuestoant());
            tx_margen.setText(String.valueOf(rep.getMargenutil()));
            tx_unidadMed.setText(rep.getUnidadmedida());
            tx_partidaAr.setText(rep.getPartidarancel());
            tx_fob.setText(String.valueOf(rep.getFobultimo()));
            tx_ubicAlmacen.setText(rep.getUbicalmacen());
        } catch (Exception e) {
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_demanda;
    private javax.swing.JButton bt_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel pn_foto;
    private javax.swing.JTextField tx_CostoTemp;
    private javax.swing.JTextField tx_FechaPrec;
    private javax.swing.JTextField tx_Lista;
    private javax.swing.JTextField tx_aplicacion;
    private javax.swing.JTextField tx_codRep;
    private javax.swing.JTextField tx_codSeg;
    private javax.swing.JTextField tx_costoProm;
    private javax.swing.JTextField tx_descripcion;
    private javax.swing.JTextField tx_estr;
    private javax.swing.JTextField tx_fob;
    private javax.swing.JTextField tx_idAnt;
    private javax.swing.JTextField tx_inventario;
    private javax.swing.JTextField tx_linea;
    private javax.swing.JTextField tx_marca;
    private javax.swing.JTextField tx_margen;
    private javax.swing.JTextField tx_modelo;
    private javax.swing.JTextField tx_nroAlter;
    private javax.swing.JTextField tx_partidaAr;
    private javax.swing.JTextField tx_precioLista;
    private javax.swing.JTextField tx_stock;
    private javax.swing.JTextField tx_stockMin;
    private javax.swing.JTextField tx_ubicAlmacen;
    private javax.swing.JTextField tx_ultimoCosto;
    private javax.swing.JTextField tx_unidadMed;
    private javax.swing.JTextField tx_unidadVenta;
    // End of variables declaration//GEN-END:variables
}
