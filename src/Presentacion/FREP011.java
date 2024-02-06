package Presentacion;

import Entidades.Tipocambio;
import Mantenimiento.Navegacion;
import Servicios.Servicio_Excel;
import Servicios.Servicio_TipoCambio;
import Servicios.TipoMensaje;
import java.awt.Desktop;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Keily I.
 */
public class FREP011 extends javax.swing.JFrame {

    String cambio;
    private Servicio_TipoCambio servicio;
    private Navegacion navegacion;
    DefaultTableModel modelo;
    private JTextField[] componentes;
    private ArrayList<Integer> numMaximo;
    private ArrayList<String> tipoDato;
    private DateFormat df;
    private Servicio_Excel excel;
    int ultimo_id;
    int num_camb;
    int filaseleccionada;
    private boolean seleccionada;
    TipoMensaje tm;
    double valorcompra;
    double valoventa;
    MENU001 m;

    public FREP011(MENU001 menu) {
        initComponents();
        setLocationRelativeTo(null);
        excel = new Servicio_Excel(tablaTipoCambio, this);

        servicio = new Servicio_TipoCambio(tablaTipoCambio);

        modelo = (DefaultTableModel) tablaTipoCambio.getModel();

        txtFecha.setEnabled(false);
        tm = new TipoMensaje();
        Listar_Tipo_Cambio();
        m = menu;
        crearArrayComponente();
        crearArrayNumMax();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, btn_Agregar);
        asignarEvento();

    }

    public double getValorcompra() {
        return valorcompra;
    }

    public void setValorcompra(double valorcompra) {
        this.valorcompra = valorcompra;
    }

    public double getValoventa() {
        return valoventa;
    }

    public void setValoventa(double valoventa) {
        this.valoventa = valoventa;
    }

    public String fechasistema() {
        Date ahora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(ahora);

    }

    private void Listar_Tipo_Cambio() {
        txtFecha.setText(fechasistema());
        textoFecha.setText("Tipo de Cambio hasta el " + fechasistema());
        num_camb = servicio.Listar_TipoCambio(modelo);
        num_camb++;
    }

    private void crearArrayTipoDato() {
        tipoDato = new ArrayList();
        tipoDato.add("S");
        tipoDato.add("D");
        tipoDato.add("D");
    }

    private void crearArrayComponente() {
        componentes = new JTextField[3];
        componentes[0] = txtFecha;
        componentes[1] = txtCompra;
        componentes[2] = txtVenta;

    }

    private void crearArrayNumMax() {
        numMaximo = new ArrayList();
        numMaximo.add(10);
        numMaximo.add(5);
        numMaximo.add(5);
    }

    private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    public void mostrar(Tipocambio tipoCambio) {
        txtFecha.setText(df.format(tipoCambio.getFecha()));
        txtCompra.setText(String.valueOf(tipoCambio.getValorcompra()));
        txtVenta.setText(String.valueOf(tipoCambio.getValorventa()));
    }

    private String validarEntradas() {
        String mensaje = "";

        if (txtCompra.getText().equals("")) {
            mensaje = mensaje + "Ingrese el valor de Compra";
        }
        if (txtVenta.getText().equals("")) {
            mensaje = mensaje + "\n Ingrese el valor de venta";
        }

        return mensaje;
    }

    public boolean existeFecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fe = null;
        try {
            fe = sdf.parse(txtFecha.getText());
        } catch (Exception ex) {
            System.out.println("error al convertir" + ex);
        }

        SimpleDateFormat sdg = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = sdg.format(fe);

        boolean resultado = servicio.buscarFecha_TipoCambio(fecha);

        return resultado;
    }

    public String validarCompraVenta() {
        double co = Double.parseDouble(txtCompra.getText());
        double ve = Double.parseDouble(txtVenta.getText());

        if (co > ve) {
            return "INCORRECTO";
        } else {
            return "CORRECTO";
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTipoCambio = new javax.swing.JPanel();
        btn_Agregar = new javax.swing.JButton();
        btn_Modificar = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();
        btn_Exportar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaTipoCambio = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtfechadesde = new com.toedter.calendar.JDateChooser();
        txtfechahasta = new com.toedter.calendar.JDateChooser();
        boton_Mostrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtVenta = new javax.swing.JTextField();
        txtCompra = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textoFecha = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btnCargarTipoCambio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_Agregar.setText("Agregar");
        btn_Agregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_AgregarMouseClicked(evt);
            }
        });
        btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AgregarActionPerformed(evt);
            }
        });

        btn_Modificar.setText("Modificar");
        btn_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModificarActionPerformed(evt);
            }
        });

        botonLimpiar.setText("Limpiar");
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });

        btn_Exportar.setText("Exportar");
        btn_Exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExportarActionPerformed(evt);
            }
        });

        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("TIPO DE CAMBIO");

        tablaTipoCambio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Valor Compra", "Valor Venta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaTipoCambio.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaTipoCambio.getTableHeader().setReorderingAllowed(false);
        tablaTipoCambio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaTipoCambioMouseReleased(evt);
            }
        });
        tablaTipoCambio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaTipoCambioKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaTipoCambio);
        if (tablaTipoCambio.getColumnModel().getColumnCount() > 0) {
            tablaTipoCambio.getColumnModel().getColumn(0).setResizable(false);
            tablaTipoCambio.getColumnModel().getColumn(0).setPreferredWidth(30);
            tablaTipoCambio.getColumnModel().getColumn(1).setResizable(false);
            tablaTipoCambio.getColumnModel().getColumn(1).setPreferredWidth(30);
            tablaTipoCambio.getColumnModel().getColumn(2).setResizable(false);
            tablaTipoCambio.getColumnModel().getColumn(2).setPreferredWidth(30);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Tipo de Cambio por Fecha", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel8.setText("Desde :");

        jLabel9.setText("Hasta :");

        txtfechadesde.setDateFormatString("dd-MM-yyyy");

        txtfechahasta.setDateFormatString("dd-MM-yyyy");
        txtfechahasta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfechahastaKeyPressed(evt);
            }
        });

        boton_Mostrar.setText("Mostrar");
        boton_Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_MostrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtfechadesde, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtfechahasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(boton_Mostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtfechadesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtfechahasta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(boton_Mostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de Tipo de Cambio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel2.setText("Fecha");

        jLabel3.setText("Valor Compra");

        jLabel4.setText("Valor Venta");

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("( * )");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("( * )");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(txtVenta))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)))
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        textoFecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        textoFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoFecha.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/sunatt.gif"))); // NOI18N
        jButton1.setToolTipText("Tipo de Cambio en el Banco de la Nación");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tipocambioBN.png"))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton2MouseReleased(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnCargarTipoCambio.setText("<html><center>Cargar Tipo Cambio<br />del último día registrado</center></html>");
        btnCargarTipoCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarTipoCambioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTipoCambioLayout = new javax.swing.GroupLayout(panelTipoCambio);
        panelTipoCambio.setLayout(panelTipoCambioLayout);
        panelTipoCambioLayout.setHorizontalGroup(
            panelTipoCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTipoCambioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(203, 203, 203))
            .addGroup(panelTipoCambioLayout.createSequentialGroup()
                .addGroup(panelTipoCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTipoCambioLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(panelTipoCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelTipoCambioLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTipoCambioLayout.createSequentialGroup()
                                .addComponent(btn_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_Exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTipoCambioLayout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(btnCargarTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13))))
                    .addGroup(panelTipoCambioLayout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelTipoCambioLayout.setVerticalGroup(
            panelTipoCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTipoCambioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTipoCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTipoCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnCargarTipoCambio)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTipoCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTipoCambioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Exportar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AgregarActionPerformed
        if (seleccionada == false) {
            String validacion = validarEntradas();

            if (!validacion.equals("")) {
                JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);
            } else {
                if (existeFecha() == false) {
                    String mensaje = validarCompraVenta();
                    if (mensaje.equals("CORRECTO")) {

                        int verif;
                        verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operacion?", "CONFIRMACIÓN", 0);
                        if (verif == 0) {

                            String fech = txtFecha.getText();

                            Date a = null;
                            try {
                                a = servicio.convertiraDate(txtFecha.getText());
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Formato de ingreso incorrecto",
                                        "Error de conversión:" + ex.getMessage(),
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            double b = Double.parseDouble(txtCompra.getText());
                            double c = Double.parseDouble(txtVenta.getText());


                            Tipocambio tip = new Tipocambio();
                            tip.setFecha(a);
                            tip.setValorcompra(b);
                            tip.setValorventa(c);

                            if (servicio.addTipoCambio(tip)) {
                                m.txtValorCompra.setText(String.valueOf(b));
                                m.txtValorVenta.setText(String.valueOf(c));
                                JOptionPane.showMessageDialog(null, "Operacion exitosa");
                                panelTipoCambio.removeAll();                                
                                m.panelCentral.updateUI();
                                btn_Agregar.setEnabled(false);
                                botonLimpiar.setEnabled(false);
                                Object[] row = {fech, b, c};
                                modelo.insertRow(0, row);
                                valorcompra = b;
                                valoventa = c;

                            } else {
                                JOptionPane.showMessageDialog(null, "Error en la inserción");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "EL VALOR DE COMPRA NO PUEDE SER MAYOR \n              QUE EL VALOR DE VENTA", "Error al insertar", 0);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ESTA FECHA YA ESTA REGISTRADA", "Duplicación de Datos", 0);
                }

            }
        }

    }//GEN-LAST:event_btn_AgregarActionPerformed

    public String gettipocambio() {
        return cambio;

    }

    private void btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModificarActionPerformed

        if (seleccionada == true) {
            String validacion = validarEntradas();
            if (!validacion.equals("")) {
                JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);
            } else {
                String mensaje = validarCompraVenta();
                if (mensaje.equals("CORRECTO")) {

                    int verif;
                    verif = JOptionPane.showConfirmDialog(null, "¿Desea continuar con la operacion?", "CONFIRMACIÓN", 0);
                    if (verif == 0) {
                        String fecha = txtFecha.getText();
                        Date fec = null;
                        try {
                            fec = servicio.convertiraDate(fecha);
                        } catch (ParseException ex) {
                            Logger.getLogger(FREP011.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        double compra = Double.parseDouble(txtCompra.getText());
                        double venta = Double.parseDouble(txtVenta.getText());

                        Tipocambio tip = new Tipocambio(compra, venta);
                        tip.setFecha(fec);
                        servicio.actualizarTipoCambio(tip);
                        JOptionPane.showMessageDialog(null, "Se modificó el Tipo de Cambio");
                        DefaultTableModel mod = (DefaultTableModel) tablaTipoCambio.getModel();
                        mod.setValueAt(fecha, filaseleccionada, 0);
                        mod.setValueAt(compra, filaseleccionada, 1);
                        mod.setValueAt(venta, filaseleccionada, 2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "EL VALOR DE COMPRA NO PUEDE SER MAYOR QUE EL VALOR DE VENTA", "Error al modificar", 0);
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Primero seleccione un Tipo de Cambio", "Error", JOptionPane.ERROR_MESSAGE);
        }



    }//GEN-LAST:event_btn_ModificarActionPerformed

    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarActionPerformed
        btn_Agregar.setEnabled(true);
        txtFecha.setText(fechasistema());
        txtCompra.setText("");
        txtVenta.setText("");
        tablaTipoCambio.clearSelection();
        seleccionada = false;

    }//GEN-LAST:event_botonLimpiarActionPerformed

    private void btn_ExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExportarActionPerformed
        excel.Exportar_Excel(1);
    }//GEN-LAST:event_btn_ExportarActionPerformed

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void btn_AgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_AgregarMouseClicked
    }//GEN-LAST:event_btn_AgregarMouseClicked

    private void tablaTipoCambioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaTipoCambioKeyReleased
        btn_Agregar.setEnabled(false);
        filaseleccionada = tablaTipoCambio.getSelectedRow();
        String valorcompra;
        String valorventa;
        String fecha = tablaTipoCambio.getValueAt(filaseleccionada, 0).toString();
        if (tablaTipoCambio.getValueAt(filaseleccionada, 1) == null) {
            valorcompra = "";
        } else {
            valorcompra = tablaTipoCambio.getValueAt(filaseleccionada, 1).toString();
        }

        if (tablaTipoCambio.getValueAt(filaseleccionada, 2) == null) {
            valorventa = "";
        } else {
            valorventa = tablaTipoCambio.getValueAt(filaseleccionada, 2).toString();
        }

        txtFecha.setText(fecha);
        txtCompra.setText(valorcompra);
        txtVenta.setText(valorventa);

        seleccionada = true;
    }//GEN-LAST:event_tablaTipoCambioKeyReleased

    private void boton_MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_MostrarActionPerformed
        String des = servicio.convertirFecha(txtfechadesde.getDate());
        String has = servicio.convertirFecha(txtfechahasta.getDate());


        textoFecha.setText("Tipo de cambio desde el " + des + " hasta el " + has);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fDesde = sdf.format(txtfechadesde.getDate());
        String fHasta = sdf.format(txtfechahasta.getDate());

        int cant = servicio.buscarTipoCambioxFecha(fDesde, fHasta).size();
        if (cant != 0) {
            modelo.getDataVector().removeAllElements();
            modelo.fireTableDataChanged();
        }
        for (int i = 0; i < cant; i++) {

            Date d = servicio.buscarTipoCambioxFecha(fDesde, fHasta).get(i).getFecha();
            String a = servicio.convertirFecha(d);
            double b = servicio.buscarTipoCambioxFecha(fDesde, fHasta).get(i).getValorcompra();
            double c = servicio.buscarTipoCambioxFecha(fDesde, fHasta).get(i).getValorventa();
            Object[] lineas = {a, b, c};

            DefaultTableModel mod = (DefaultTableModel) tablaTipoCambio.getModel();
            mod.addRow(lineas);

        }

    }//GEN-LAST:event_boton_MostrarActionPerformed

    private void txtfechahastaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechahastaKeyPressed
    }//GEN-LAST:event_txtfechahastaKeyPressed

    private void tablaTipoCambioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTipoCambioMouseReleased
        btn_Agregar.setEnabled(false);
        filaseleccionada = tablaTipoCambio.getSelectedRow();
        String valorcompra;
        String valorventa;
        String fecha = tablaTipoCambio.getValueAt(filaseleccionada, 0).toString();
        if (tablaTipoCambio.getValueAt(filaseleccionada, 1) == null) {
            valorcompra = "";
            JOptionPane.showMessageDialog(null, "ACTUALIZE EL TIPO DE CAMBIO", "Actualizar tipo de cambio", JOptionPane.WARNING_MESSAGE);
        } else {
            valorcompra = tablaTipoCambio.getValueAt(filaseleccionada, 1).toString();

        }

        if (tablaTipoCambio.getValueAt(filaseleccionada, 2) == null) {
            valorventa = "";
        } else {
            valorventa = tablaTipoCambio.getValueAt(filaseleccionada, 2).toString();
        }

        txtFecha.setText(fecha);
        txtCompra.setText(valorcompra);
        txtVenta.setText(valorventa);

        seleccionada = true;
    }//GEN-LAST:event_tablaTipoCambioMouseReleased

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased

        try {
            String dir = "http://www.sunat.gob.pe/cl-at-ittipcam/tcS01Alias";
            Desktop.getDesktop().browse(new URI(dir));
        } catch (Exception e) {
            System.out.println("La direccion web no existe o ha sido cambiada");
        }
    }//GEN-LAST:event_jButton1MouseReleased

    private void jButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseReleased
        try {
            String dir = "https://zonasegura1.bn.com.pe/TipoCambio/?KeepThis=true&TB_iframe=true&height=500&width=860";
            Desktop.getDesktop().browse(new URI(dir));
        } catch (Exception e) {
//            System.out.println("Excepcion:" + e.getMessage());
            System.out.println("La direccion web no existe o ha sido cambiada");
        }
    }//GEN-LAST:event_jButton2MouseReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnCargarTipoCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarTipoCambioActionPerformed
        if ( tablaTipoCambio.getRowCount() > 0 ) {
            String vCompra = String.valueOf(tablaTipoCambio.getModel().getValueAt(0, 1));
            String vVenta =  String.valueOf(tablaTipoCambio.getModel().getValueAt(0, 2));
            txtCompra.setText(vCompra);
            txtVenta.setText(vVenta);
        } else {
            JOptionPane.showMessageDialog(null, "NO EXISTE TIPO DE CAMBIO ANTERIOR", "Cargar Tipo de cambio", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCargarTipoCambioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton botonLimpiar;
    public javax.swing.JButton boton_Mostrar;
    public javax.swing.JButton btnCargarTipoCambio;
    public javax.swing.JButton btn_Agregar;
    public javax.swing.JButton btn_Exportar;
    public javax.swing.JButton btn_Modificar;
    public javax.swing.JButton btn_Salir;
    public javax.swing.JButton jButton1;
    public javax.swing.JButton jButton2;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JPanel panelTipoCambio;
    public javax.swing.JTable tablaTipoCambio;
    public javax.swing.JLabel textoFecha;
    public javax.swing.JTextField txtCompra;
    public javax.swing.JTextField txtFecha;
    public javax.swing.JTextField txtVenta;
    public com.toedter.calendar.JDateChooser txtfechadesde;
    public com.toedter.calendar.JDateChooser txtfechahasta;
    // End of variables declaration//GEN-END:variables
}
