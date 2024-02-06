package Presentacion.CuentasxCobrarSalidas;

import Presentacion.CuentasxCobrarFacturas.*;
import Entidades.Bancos;
import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Cabecsalvar;
import Entidades.Pagos;
import Entidades.Usuarios;
import Presentacion.MENU001;
import Servicios.CuentasxCobrar.Tabla_CobrarFacturas;
import Servicios.Servicio_Pagos;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Servicios.CuentasxCobrar.Editor;
import Servicios.CuentasxCobrar.NroFacturas;
import Servicios.CuentasxCobrar.Render_Letra;
import Servicios.CuentasxCobrar.Servicio_CobrarSalidasVarias;
import Servicios.Servicio_Banco;
import Servicios.Servicio_Cabeces;
import com.toedter.calendar.JDateChooser;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Keily Mendiolaza
 */
public final class IU_PagarSalidasVarxCobrar extends javax.swing.JFrame {

    DefaultTableModel tabla;
    Servicio_Banco sb = new Servicio_Banco(null);
    List listabancos = sb.listar_Bancos();
    JComboBox banco = new JComboBox(listabancos.toArray());
    int filaseleccionada;
    int idPagoSeleccionado;
    JTable tt;
    Tabla_CobrarFacturas modelo;
    Editor edit;
    Render_Letra rend;
    MENU001 m;
    FREP070 lc;
    int tam;
    int selectRowPago;
    private Usuarios usuario;
    boolean selectPago;
    Cabecsalvar sal;
    int idSalida;

    public IU_PagarSalidasVarxCobrar(DefaultTableModel t, int fila, MENU001 m, Usuarios usuario, JTable ta,
                                     String rol, boolean botonPagar) {
        sal = new Cabecsalvar();
        modelo = new Tabla_CobrarFacturas();
        initComponents();
        setLocationRelativeTo(null);
        this.usuario = usuario;
        tabla = t;
        this.m = m;
        tt = ta;
        filaseleccionada = fila;
        lc = new FREP070(null, rol, botonPagar);
        
        iniciarDatosCliente();
        iniciarBanco();
        
        fechaSistema();
        
        edit = new Editor();
        rend = new Render_Letra();
        tablaPagos.getColumnModel().getColumn(0).setPreferredWidth(30);
        tablaPagos.getColumnModel().getColumn(1).setPreferredWidth(30);
        tablaPagos.getColumnModel().getColumn(2).setPreferredWidth(10);
        tablaPagos.getColumnModel().getColumn(3).setPreferredWidth(90);
        tablaPagos.getColumnModel().getColumn(4).setPreferredWidth(40);
        tablaPagos.setDefaultEditor(JDateChooser.class, edit);
        tablaPagos.setDefaultRenderer(JDateChooser.class, rend);
        this.tablaPagos.getColumn("Banco").setCellEditor(new DefaultCellEditor(banco));
        tablaPagos.setCellSelectionEnabled(true);
        mostrarPagos();
        System.out.println("inicializando salvar");
        
    }

    public void Elegir_Numero_pagos() {
    }

    public void fechaSistema() {
        Date ahora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(ahora);
        JlFecha.setText(fecha);
    }

    public final void mostrarPagos() {

        /*
         String tdoc = tabla.getValueAt(filaseleccionada, 0).toString();
         System.out.println(tdoc);
         String ndoc = tabla.getValueAt(filaseleccionada, 1).toString();
         String nserie = tabla.getValueAt(filaseleccionada, 2).toString();
         CabecesId id = new CabecesId("V", tdoc, nserie, ndoc);

         */
        int codSal = Integer.parseInt(txt_numDoc.getText());
        Servicio_CobrarSalidasVarias scsal = new Servicio_CobrarSalidasVarias();

        sal = scsal.obtenerPorCodSal(codSal);
        idSalida = sal.getIdsalida();
        Servicio_Pagos sp = new Servicio_Pagos();
        System.out.println("LISTANDOOO:: " + sal.getIdsalida());
        if (!sp.listarPagosxCabSalVar(idSalida).isEmpty()) {

            int n = sp.listarPagosxCabSalVar(idSalida).size();
            tam = n;
            for (int i = 0; i < n; i++) {
                NroFacturas nf = new NroFacturas();

                nf.setNumero(i);
                nf.setIdFactura(sp.listarPagosxCabSalVar(idSalida).get(i).getIdpago());
                //System.out.println(" iddd: " + sp.listarPagosxCabecera(id).get(i).getIdpago());

                nf.setImporte(sp.listarPagosxCabSalVar(idSalida).get(i).getImporte());
                nf.setFechaPago(sp.listarPagosxCabSalVar(idSalida).get(i).getFecha());
                nf.setModo(sp.listarPagosxCabSalVar(idSalida).get(i).getForma());
                if (sp.listarPagosxCabSalVar(idSalida).get(i).getBancos() != null) {
                    nf.setBanco(sp.listarPagosxCabSalVar(idSalida).get(i).getBancos().getNombre());
                }

                nf.setNrocheque(sp.listarPagosxCabSalVar(idSalida).get(i).getNrocheque());
                boolean[] editFilas = {false, false, false, false, false};
                modelo.setEditFilas(editFilas);
                modelo.getNf().add(nf);

            }
        }

        System.out.println("id salvar::1" + sal.getIdsalida());

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
            default:
                tipo = "Entrada no valida";
                break;
        }
        return tipo;
    }

    public void iniciarDatosCliente() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaEm = sdf.format(tabla.getValueAt(filaseleccionada, 3));
        String tip = tipoDoc(tabla.getValueAt(filaseleccionada, 0).toString());
        txt_tipodoc.setText("SK");
        txt_numDoc.setText((String) tabla.getValueAt(filaseleccionada, 1));
        txt_numserie.setText((String) tabla.getValueAt(filaseleccionada, 2));
        txt_fechaEmi.setText(fechaEm);

        txt_cliente.setText((String) tabla.getValueAt(filaseleccionada, 5));

        String moneda = tabla.getValueAt(filaseleccionada, 6).toString();
        txt_totalFactura.setText(moneda + " " + String.valueOf(tabla.getValueAt(filaseleccionada, 7)));
        //txt_Banco.setText((String) tabla.getValueAt(filaseleccionada, 10));

        txtsaldodeuda.setText(moneda + " " + tabla.getValueAt(filaseleccionada, 9).toString());

        /*
         CabecesId idd = new CabecesId();
         idd.setTipotra("V");
         idd.setTipodoc(tabla.getValueAt(filaseleccionada, 0).toString());
         idd.setNrorserie(tabla.getValueAt(filaseleccionada, 2).toString());
         idd.setNrodocumento((String) tabla.getValueAt(filaseleccionada, 1));

         Servicio_Cabeces scab = new Servicio_Cabeces();
         Cabeces cabeces = scab.obtenerCabecera_id(idd);

         if (cabeces.getNroguia() != null) {
         txt_nguia.setText(String.valueOf(cabeces.getNroguia()));
         }
         if (cabeces.getTipoperacion() != null || cabeces.getTipoperacion().equals("")) {
         cbOp.setSelectedItem(cabeces.getTipoperacion());
         } else {
         cbOp.setSelectedIndex(0);
         }
         if (cabeces.getFechavencimiento() != null) {
         txtfechavencimiento.setDate(cabeces.getFechavencimiento());
         }
         */
    }

    public String validarEntradas() {

        String mensaje = "";

        if (combmodo.getSelectedIndex() == 0) {
            if (txtimp.getText().equals("")) {
                mensaje = mensaje + "FALTA IMPORTE";
            }
            if (txtfec.getDate() == null) {
                mensaje = mensaje + "\nFALTA FECHA";
            }

        } else {
            if (txtimp.getText().equals("")) {
                mensaje = mensaje + "FALTA IMPORTE";
            }
            if (txtfec.getDate() == null) {
                mensaje = mensaje + "\nFALTA FECHA";
            }
            if (txtcheque.getText().toString().equals("")) {
                mensaje = mensaje + "\nFALTA CHEQUE";
            }
        }
        return mensaje;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        JlFecha = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btn_Aceptar = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPagos = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtimp = new javax.swing.JTextField();
        txtfec = new com.toedter.calendar.JDateChooser();
        combmodo = new javax.swing.JComboBox();
        combbanco = new javax.swing.JComboBox();
        lbl_Concepto = new javax.swing.JLabel();
        txtcheque = new javax.swing.JTextField();
        cbOp = new javax.swing.JComboBox();
        txtfechavencimiento = new com.toedter.calendar.JDateChooser();
        boton2 = new javax.swing.JButton();
        boton1 = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        txt_tipodoc = new javax.swing.JTextField();
        txt_numDoc = new javax.swing.JTextField();
        txt_numserie = new javax.swing.JTextField();
        txt_fechaEmi = new javax.swing.JTextField();
        txt_nguia = new javax.swing.JTextField();
        txt_cliente = new javax.swing.JTextField();
        txt_totalFactura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtsaldodeuda = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btn_Salir1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("SALIDAS VARIAS PENDIENTES POR COBRAR AL ");

        JlFecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel3.setText("Tipo de Documento:");

        jLabel4.setText("Número Documento:");

        jLabel5.setText("Fecha de Emisión: ");

        jLabel6.setText("Nombre de Cliente: ");

        jLabel7.setText("Total Factura: ");

        jLabel9.setText("( SK = Salidas Varias)");

        jLabel10.setText("Nº Guía: ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setText("PAGOS REALIZADOS");

        btn_Aceptar.setText("Aceptar");
        btn_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AceptarActionPerformed(evt);
            }
        });

        btn_Salir.setText("Salir");
        btn_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SalirActionPerformed(evt);
            }
        });

        tablaPagos.setModel(modelo);
        tablaPagos.setColumnSelectionAllowed(true);
        tablaPagos.getTableHeader().setReorderingAllowed(false);
        tablaPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPagosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaPagosMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaPagos);
        tablaPagos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel12.setText("Nº Serie : ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("CANCELACIONES");

        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseReleased(evt);
            }
        });

        jLabel15.setText("Importe ");

        jLabel16.setText("Fecha");

        jLabel17.setText("Modo");

        jLabel18.setText("Banco");

        txtimp.setEnabled(false);

        txtfec.setEnabled(false);

        combmodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "E = EFECTIVO", "C = CHEQUE", "CxL = CANJE X LETRAS" }));
        combmodo.setEnabled(false);
        combmodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combmodoActionPerformed(evt);
            }
        });

        combbanco.setEnabled(false);
        combbanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combbancoActionPerformed(evt);
            }
        });

        lbl_Concepto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_Concepto.setText("Nro Cheque");

        txtcheque.setEnabled(false);
        txtcheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtchequeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtchequeKeyTyped(evt);
            }
        });

        cbOp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1", "2", "3" }));
        cbOp.setEnabled(false);

        txtfechavencimiento.setEnabled(false);

        boton2.setText("Modificar");
        boton2.setEnabled(false);
        boton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton2ActionPerformed(evt);
            }
        });

        boton1.setText("Modificar");
        boton1.setEnabled(false);
        boton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Tipo de Operación: ");
        jCheckBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCheckBox2MouseReleased(evt);
            }
        });

        jCheckBox3.setText("Fecha de Vencimiento: ");
        jCheckBox3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCheckBox3MouseReleased(evt);
            }
        });

        txt_tipodoc.setEditable(false);
        txt_tipodoc.setBackground(new java.awt.Color(255, 255, 255));

        txt_numDoc.setEditable(false);
        txt_numDoc.setBackground(new java.awt.Color(255, 255, 255));
        txt_numDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_numDocActionPerformed(evt);
            }
        });

        txt_numserie.setEditable(false);
        txt_numserie.setBackground(new java.awt.Color(255, 255, 255));

        txt_fechaEmi.setEditable(false);
        txt_fechaEmi.setBackground(new java.awt.Color(255, 255, 255));

        txt_nguia.setEditable(false);
        txt_nguia.setBackground(new java.awt.Color(255, 255, 255));

        txt_cliente.setEditable(false);
        txt_cliente.setBackground(new java.awt.Color(255, 255, 255));

        txt_totalFactura.setEditable(false);
        txt_totalFactura.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Saldo de la Deuda: ");

        txtsaldodeuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsaldodeudaActionPerformed(evt);
            }
        });

        jButton1.setText("Modificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_Salir1.setText("Eliminar");
        btn_Salir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Salir1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_numDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txt_fechaEmi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(6, 6, 6))
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txt_nguia, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(txt_numserie, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbOp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87)
                                .addComponent(boton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtfechavencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(boton2))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel1)
                        .addGap(44, 44, 44)
                        .addComponent(JlFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_tipodoc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_totalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtsaldodeuda, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCheckBox1)
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(jLabel15)
                                                .addGap(63, 63, 63)
                                                .addComponent(jLabel16))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtimp, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtfec, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(105, 105, 105)
                                        .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(combmodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(combbanco, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addGap(128, 128, 128)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(lbl_Concepto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtcheque, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn_Salir1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(42, 42, 42)
                                        .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JlFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_tipodoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbOp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boton1)
                            .addComponent(jCheckBox2))
                        .addGap(7, 7, 7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txt_numDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_numserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(txt_nguia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(txt_fechaEmi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(boton2)
                            .addComponent(txtfechavencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jCheckBox3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_totalFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jLabel11)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtsaldodeuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(lbl_Concepto))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addComponent(txtimp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combmodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combbanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtcheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtfec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_Aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Salir1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void btn_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SalirActionPerformed
        dispose();
    }//GEN-LAST:event_btn_SalirActionPerformed

    private void btn_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AceptarActionPerformed
        
        
        String validacion = validarEntradas();
        if (!validacion.equals("")) {
            JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);
        } else {
            
            
            //sal= 
            
            int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro de realizar los cambios?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                 Pagos p = new Pagos();
                 Servicio_Pagos sp = new Servicio_Pagos();
                 Bancos ba = new Bancos();
//                 CabecesId cb = new CabecesId();
                 Servicio_Cabeces sv = new Servicio_Cabeces();

//                 cb.setTipotra("V");
//                 cb.setTipodoc("12");
//                 cb.setNrorserie("2");
//                 cb.setNrodocumento(txt_numDoc.getText());
//
//                 Cabeces cabecera = sv.obtenerCabecera_id(cb);
                
                double a = Double.parseDouble(txtimp.getText());
                Date b = txtfec.getDate();
                String c;

                if (combmodo.getSelectedIndex() == 0) {
                    c = "E";
                } else {
                    if (combmodo.getSelectedIndex() == 1) {
                        c = "C";
                    } else {
                        c = "CxL";
                    }
                }

                String d = combbanco.getSelectedItem().toString();
                String e = txtcheque.getText().trim().replaceAll("( )+", " ");
// Agregue f y g (jsp)                
                String f = "12";  // tipodoc
                String g = txt_numDoc.getText();

                System.out.println("F : " + f);
                System.out.println("G : " + g);
                
                
                int id = sb.getBanco_Nombre(d).getIdbanco();
                ba.setIdbanco(id);
                p.setUsuarios(usuario);
                p.setBancos(ba);
//              p.setCabeces(cabecera);
                System.out.println("id salvar::3" + sal.getIdsalida());
                p.setCabecsalvar(sal);
                System.out.println("Voy a hacer setTipodoc y setNrodocumento");
// He agregado estos 2 elementos
                System.out.println("F : " + f);
                System.out.println("G : " + g);                
                p.setTipodoc(f);
                p.setNrodocumento(g);
//-------------------------------------------------------
                p.setFecha(b);
                p.setImporte(a);
                p.setForma(c);
                p.setNrocheque(e);

                if (sp.GuardarPagos(p)) {

                    iniciarDatosCliente();
                    iniciarBanco();
                    //fechaSistema();

                    modelo = new Tabla_CobrarFacturas();
                    tablaPagos.setModel(modelo);
                    mostrarPagos();

                    JOptionPane.showMessageDialog(null, "El pago se realizó con éxito", "PAGOS", JOptionPane.INFORMATION_MESSAGE);
                    tabla.getDataVector().removeAllElements();
                    tabla.fireTableDataChanged();

                    ((AbstractTableModel) tablaPagos.getModel()).fireTableDataChanged();
                    lc.iniciarTabla(tt);

                    String moneda = tabla.getValueAt(filaseleccionada, 6).toString();
                    txtsaldodeuda.setText(moneda + " " + tabla.getValueAt(filaseleccionada, 9).toString());

                    limpiarDatos();
                    //dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar pago", "PAGOS", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        selectPago = false;
    }//GEN-LAST:event_btn_AceptarActionPerformed

    public void limpiarDatos() {
        jCheckBox1.setSelected(false);
        txtimp.setText("");
        txtfec.setDate(null);
        combmodo.setSelectedIndex(0);
        combbanco.setSelectedItem("CAJA");
        txtcheque.setText("");

        txtimp.setEnabled(false);
        txtfec.setEnabled(false);
        combmodo.setEnabled(false);
        combbanco.setEnabled(false);
        txtcheque.setEnabled(false);

    }


    private void combbancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combbancoActionPerformed

    }//GEN-LAST:event_combbancoActionPerformed

    private void jCheckBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseClicked
    }//GEN-LAST:event_jCheckBox1MouseClicked

    private void jCheckBox1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseReleased

        if (jCheckBox1.isSelected() == true) {
            txtimp.setEnabled(true);
            txtfec.setEnabled(true);
            combmodo.setEnabled(true);
            combbanco.setEnabled(true);
            txtcheque.setEnabled(true);
        } else {
            txtimp.setEnabled(false);
            txtfec.setEnabled(false);
            combmodo.setEnabled(false);
            combbanco.setEnabled(false);
            txtcheque.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox1MouseReleased

    private void jCheckBox2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox2MouseReleased
        if (jCheckBox2.isSelected()) {
            cbOp.setEnabled(true);
            boton1.setEnabled(true);
        } else {
            cbOp.setEnabled(false);
            boton1.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox2MouseReleased

    private void jCheckBox3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox3MouseReleased
        if (jCheckBox3.isSelected()) {
            txtfechavencimiento.setEnabled(true);
            boton2.setEnabled(true);
        } else {
            txtfechavencimiento.setEnabled(false);
            boton2.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox3MouseReleased

    private void boton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton1ActionPerformed
        if (cbOp.getSelectedIndex() != 0) {
            int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro de realizar los cambios?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String tip = tabla.getValueAt(filaseleccionada, 0).toString();
                CabecesId cid = new CabecesId("V", tip, tabla.getValueAt(filaseleccionada, 2).toString(), tabla.getValueAt(filaseleccionada, 1).toString());
                Servicio_Cabeces scab = new Servicio_Cabeces();
                Cabeces cabeces = scab.obtenerCabecera_id(cid);
                cabeces.setTipoperacion(cbOp.getSelectedItem().toString());
                if (scab.ActualizarCabecera(cabeces)) {
                    JOptionPane.showMessageDialog(null, "Operación exitosa");
                } else {
                    JOptionPane.showMessageDialog(null, "No se actualizo los datos");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "FALTA TIPO OPERACION", "Falta datos", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_boton1ActionPerformed

    private void boton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton2ActionPerformed
        if (txtfechavencimiento.getDate() != null) {
            int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro de realizar los cambios?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                String tip = tabla.getValueAt(filaseleccionada, 0).toString();
                CabecesId cid = new CabecesId("V", tip, tabla.getValueAt(filaseleccionada, 2).toString(), tabla.getValueAt(filaseleccionada, 1).toString());
                Servicio_Cabeces scab = new Servicio_Cabeces();
                Cabeces cabeces = scab.obtenerCabecera_id(cid);
                cabeces.setFechavencimiento(txtfechavencimiento.getDate());
                if (scab.ActualizarCabecera(cabeces)) {
                    JOptionPane.showMessageDialog(null, "Operación exitosa");
                } else {
                    JOptionPane.showMessageDialog(null, "No se actualizo los datos");
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "FALTA FECHA DE VENCIMIENTO", "Falta datos", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_boton2ActionPerformed

    private void combmodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combmodoActionPerformed
        if (combmodo.getSelectedIndex() == 0) {
            combbanco.setSelectedItem("CAJA");
//            lbl_Concepto.setText("Monto");
            lbl_Concepto.setText("Nro Cheque");
        } else {
            txtcheque.setEnabled(true);
            combbanco.setSelectedIndex(0);
            lbl_Concepto.setText("Nro Cheque");
        }
    }//GEN-LAST:event_combmodoActionPerformed

    private void txtchequeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtchequeKeyTyped
        /* String cadena = Character.toString(evt.getKeyChar());
         Pattern p = Pattern.compile("^[a-zA-Z0-9]*$"); // Alfanumerico y espacio 
         Matcher m = p.matcher(cadena);
         if (!m.find()) {
         System.out.println("no encontro");
         evt.consume();
         }else{
         System.out.println("encontro");
         }
         */
    }//GEN-LAST:event_txtchequeKeyTyped

    private void txtchequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtchequeKeyReleased
        char c = evt.getKeyChar();

        String aux = txtcheque.getText();

        if (String.valueOf(c).matches("(\\w| |\\-)*")) {
            System.out.println("Válido");
        } else {
            System.out.println("No Válido");
            if (aux.length() != 0) {
                aux = aux.substring(0, aux.length() - 1);
            } else {
                aux = "";
            }
        }
        txtcheque.setText(aux.toUpperCase());

    }//GEN-LAST:event_txtchequeKeyReleased

    private void tablaPagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPagosMouseClicked
        // TODO add your handling code here:
        selectRowPago = tablaPagos.getSelectedRow();
        //Pagos pago= modelo.getNf().get(x);
        Pagos pa = new Pagos();

        for (int i = 0; i < tablaPagos.getColumnCount(); i++) {
            // result[i] = table.getModel().getValueAt(row, col);
            //System.out.println(": " + tablaPagos.getValueAt(x, i));
            jCheckBox1.setSelected(true);
            switch (i) {
                case 0:
                    txtimp.setText(String.valueOf(tablaPagos.getValueAt(selectRowPago, i)));
                    txtimp.setEnabled(true);
                    break;
                case 1:
                    txtfec.setDate(((JDateChooser) tablaPagos.getValueAt(selectRowPago, i)).getDate());
                    txtfec.setEnabled(true);
                    break;
                case 2:

                    if ((String.valueOf(tablaPagos.getValueAt(selectRowPago, i))).equals("E")) {
                        combmodo.setSelectedIndex(0);
                    } else {
                        if ((String.valueOf(tablaPagos.getValueAt(selectRowPago, i))).equals("C")) {
                            combmodo.setSelectedIndex(1);
                        } else {
                            combmodo.setSelectedIndex(2);
                        }
                    }
                    combmodo.setEnabled(true);
                    break;
                case 3:

                    combbanco.setSelectedItem(tablaPagos.getValueAt(selectRowPago, i));
                    combbanco.setEnabled(true);
                    break;
                case 4:
                    txtcheque.setText(String.valueOf(tablaPagos.getValueAt(selectRowPago, i)));
                    txtcheque.setEnabled(true);
                    break;

            }

        }


    }//GEN-LAST:event_tablaPagosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (selectPago) {
            String validacion = validarEntradas();
            if (!validacion.equals("")) {
                JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);
            } else {
                int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro de modificar el pago?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION);
                if (r == JOptionPane.YES_OPTION) {
                    Pagos p = new Pagos();
                    Servicio_Pagos sp = new Servicio_Pagos();
                    Bancos ba = new Bancos();
/*
                    CabecesId cb = new CabecesId();
                    Servicio_Cabeces sv = new Servicio_Cabeces();

                    cb.setTipotra("V");
                    cb.setTipodoc(tabla.getValueAt(filaseleccionada, 0).toString());
                    cb.setNrorserie(txt_numserie.getText());
                    cb.setNrodocumento(txt_numDoc.getText());

                    Cabeces cabecera = sv.obtenerCabecera_id(cb);*/
                    double a = Double.parseDouble(txtimp.getText());
                    Date b = txtfec.getDate();
                    String c;

                    if (combmodo.getSelectedIndex() == 0) {
                        c = "E";
                    } else {
                        if (combmodo.getSelectedIndex() == 1) {
                            c = "C";
                        } else {
                            c = "CxL";
                        }
                    }

                    String d = combbanco.getSelectedItem().toString();
                    String e = txtcheque.getText().trim().replaceAll("( )+", " ");

                    int id = sb.getBanco_Nombre(d).getIdbanco();

                    int idPago = modelo.getNf().get(selectRowPago).getIdFactura();

                    System.out.println("id pago en modif: " + idPago);

                    ba.setIdbanco(id);
                    p.setUsuarios(usuario);
                    p.setBancos(ba);
                    p.setCabecsalvar(sal); 
                    p.setFecha(b);
                    p.setImporte(a);
                    p.setForma(c);
                    p.setNrocheque(e);
                    p.setIdpago(idPago);

                    if (sp.modificarPagos(p)) {

                        iniciarBanco();
                        modelo = new Tabla_CobrarFacturas();
                        tablaPagos.setModel(modelo);
                        mostrarPagos();
                        iniciarDatosCliente();

                        JOptionPane.showMessageDialog(null, "El pago se modificó con éxito", "PAGOS", JOptionPane.INFORMATION_MESSAGE);
                        //tabla.getDataVector().removeAllElements();
                        //tabla.fireTableDataChanged();

                        tabla.getDataVector().removeAllElements();
                        tabla.fireTableDataChanged();

                        ((AbstractTableModel) tablaPagos.getModel()).fireTableDataChanged();

                        lc.iniciarTabla(tt);
                        limpiarDatos();

                        String moneda = tabla.getValueAt(filaseleccionada, 6).toString();
                        txtsaldodeuda.setText(moneda + " " + tabla.getValueAt(filaseleccionada, 9).toString());

                        //dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al modificar pago", "PAGOS", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un pago", "PAGOS", JOptionPane.ERROR_MESSAGE);
        }
        selectPago = false;
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaPagosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPagosMouseReleased

        idPagoSeleccionado = tablaPagos.getSelectedRow();
        selectPago = true;

    }//GEN-LAST:event_tablaPagosMouseReleased

    private void btn_Salir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Salir1ActionPerformed
        if (selectPago) {
            Servicio_Pagos sp = new Servicio_Pagos();

            int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el Pago?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {

                Pagos p = new Pagos();
                int idPago = modelo.getNf().get(selectRowPago).getIdFactura();
                p.setIdpago(idPago);

                if (sp.eliminarPagos(p)) {
                    iniciarDatosCliente();
                    iniciarBanco();
                    modelo = new Tabla_CobrarFacturas();
                    tablaPagos.setModel(modelo);
                    mostrarPagos();

                    JOptionPane.showMessageDialog(null, "El pago ha sido eliminado con éxito", "PAGOS", JOptionPane.INFORMATION_MESSAGE);
                    tabla.getDataVector().removeAllElements();
                    tabla.fireTableDataChanged();
                    ((AbstractTableModel) tablaPagos.getModel()).fireTableDataChanged();

                    lc.iniciarTabla(tt);
                    limpiarDatos();
                    String moneda = tabla.getValueAt(filaseleccionada, 6).toString();
                    txtsaldodeuda.setText(moneda + " " + tabla.getValueAt(filaseleccionada, 9).toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar pago", "PAGOS", JOptionPane.ERROR_MESSAGE);
                }

            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un pago", "PAGOS", JOptionPane.ERROR_MESSAGE);
        }
        selectPago = false;

    }//GEN-LAST:event_btn_Salir1ActionPerformed

    private void txtsaldodeudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsaldodeudaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsaldodeudaActionPerformed

    private void txt_numDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_numDocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_numDocActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JlFecha;
    private javax.swing.JButton boton1;
    private javax.swing.JButton boton2;
    private javax.swing.JButton btn_Aceptar;
    private javax.swing.JButton btn_Salir;
    private javax.swing.JButton btn_Salir1;
    private javax.swing.JComboBox cbOp;
    private javax.swing.JComboBox combbanco;
    private javax.swing.JComboBox combmodo;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_Concepto;
    private javax.swing.JTable tablaPagos;
    private javax.swing.JTextField txt_cliente;
    private javax.swing.JTextField txt_fechaEmi;
    private javax.swing.JTextField txt_nguia;
    private javax.swing.JTextField txt_numDoc;
    private javax.swing.JTextField txt_numserie;
    private javax.swing.JTextField txt_tipodoc;
    private javax.swing.JTextField txt_totalFactura;
    private javax.swing.JTextField txtcheque;
    private com.toedter.calendar.JDateChooser txtfec;
    private com.toedter.calendar.JDateChooser txtfechavencimiento;
    private javax.swing.JTextField txtimp;
    private javax.swing.JTextField txtsaldodeuda;
    // End of variables declaration//GEN-END:variables

    private void iniciarBanco() {

        Servicio_Banco sba = new Servicio_Banco(null);
        Iterator it = sba.getList().iterator();
        while (it.hasNext()) {
            Bancos b = (Bancos) it.next();
            combbanco.addItem(b.getNombre());
        }

    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
