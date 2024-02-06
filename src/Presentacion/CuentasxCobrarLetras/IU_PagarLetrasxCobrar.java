package Presentacion.CuentasxCobrarLetras;

import Entidades.Bancos;
import Entidades.Cabeces;
import Entidades.CabecesId;
import static Entidades.Otros.Constante.ABR_LI;
import static Entidades.Otros.Constante.COBRANZA;
import static Entidades.Otros.Constante.DESCUENTO;
import static Entidades.Otros.Constante.GARANTIA;
import static Entidades.Otros.Constante.LIBRE;
import static Entidades.Otros.Constante.SELECCIONAR;
import Entidades.Pagos;
import Entidades.Usuarios;
import Presentacion.MENU001;
import Servicios.CuentasxCobrar.Editor;
import Servicios.CuentasxCobrar.NroFacturas;
import Servicios.CuentasxCobrar.Render_Letra;
import Servicios.CuentasxCobrar.Servicio_CobrarLetras;
import Servicios.CuentasxCobrar.Tabla_CobrarFacturas;
import Servicios.Servicio_Banco;
import Servicios.Servicio_Pagos;
import com.toedter.calendar.JDateChooser;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Keily Mendiolaza
 */
public class IU_PagarLetrasxCobrar extends javax.swing.JFrame {

    Editor edit;
    Render_Letra rend;
    Servicio_Banco sb = new Servicio_Banco(null);
    List listabancos = sb.listar_Bancos();
    JComboBox banco = new JComboBox(listabancos.toArray());
    Tabla_CobrarFacturas modeloPagos;
    DefaultTableModel tblLetras;
    int filaseleccionada;
    FREP043 lc;
    private Usuarios usuario;
    int tam;
    JTable tt;
    int selectRowPago;
    boolean selectPago;

    String[] facturasReferencia;
    FREP043 frep043;

    public IU_PagarLetrasxCobrar(DefaultTableModel tblLetras, int fila, MENU001 m, Usuarios usuario, JTable ta,
                                 String rol, boolean botonPagar,
                                 FREP043 frep043) {
        modeloPagos = new Tabla_CobrarFacturas();
        initComponents();
        setDefaultCloseOperation(0);
        this.frep043 = frep043;
        setLocationRelativeTo(null);
        this.usuario = usuario;
        fechaSistema();
        this.tblLetras = tblLetras;
        lc = new FREP043(null, rol, botonPagar);
        tt = ta;
        filaseleccionada = fila;
        edit = new Editor();
        rend = new Render_Letra();
        
        iniciarBanco();
        iniciarBancoLetra();
        iniciarTipoLetra();
        iniciarDatosCliente();

        tablaPagos.setDefaultEditor(JDateChooser.class, edit);
        tablaPagos.setDefaultRenderer(JDateChooser.class, rend);
        this.tablaPagos.getColumn("Banco").setCellEditor(new DefaultCellEditor(banco));
        tablaPagos.setCellSelectionEnabled(true);
        mostrarPagos();
    }
    
    private void iniciarBancoLetra() {
        cbBancoLetra.addItem("<Seleccionar>");
        Servicio_Banco sba = new Servicio_Banco(null);
        List lista = sba.getBancoParaLetra();
        
        for ( Object banco : lista ) {
            if ( banco != null ) {
                cbBancoLetra.addItem(banco.toString());
            }
        }
//        Iterator it = sba.getList().iterator();
//        while ( it.hasNext() ) {
//            Bancos b = (Bancos) it.next();
//            cbBancoLetra.addItem(b.getBanco());
//        }
    }
    
    private void iniciarTipoLetra() {
        cbTipoLetra.addItem("LI  = Libre");
        cbTipoLetra.addItem("CO = Cobranza");
        cbTipoLetra.addItem("DE = Descuento");
        cbTipoLetra.addItem("GA = Garantía");
    }

    private void llenarTablaFacturasReferencia() {
//        System.out.println("tamaño: " + facturasReferencia.length);
        DefaultTableModel dtm = (DefaultTableModel) tb_facturasReferencia.getModel();
        dtm.setNumRows(facturasReferencia.length);
//        System.out.println("Nª filas: " + dtm.getRowCount());

        for ( int i = 0; i < facturasReferencia.length; i++ ) {
            dtm.setValueAt((i + 1), i, 0);
            dtm.setValueAt(facturasReferencia[i], i, 1);
        }
    }

    public void iniciarDatosCliente() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txttipodoc.setText("L");
        txtnumdoc.setText(tblLetras.getValueAt(filaseleccionada, 1).toString());
        
        if ( (tblLetras.getValueAt(filaseleccionada, 2)) != null ) {
            txtnumren.setText(tblLetras.getValueAt(filaseleccionada, 2).toString());
        }

        String fechaEm = sdf.format(tblLetras.getValueAt(filaseleccionada, 4));
        txtfechaemision.setText(fechaEm);
        
        if ( ((tblLetras.getValueAt(filaseleccionada, 12))) != null ) {
            String cadenaFactRef = tblLetras.getValueAt(filaseleccionada, 12).toString();
            int numeroFactRef = obtenerNumeroOcurrencias(cadenaFactRef, "/") + 1;
//            System.out.println("Nº de ocurrencias:" + numeroFactRef);

            facturasReferencia = new String[numeroFactRef];
            facturasReferencia = cadenaFactRef.split("/");
            llenarTablaFacturasReferencia();
        }
        txtfechavencimiento.setText(tblLetras.getValueAt(filaseleccionada, 5).toString());
        if ( tblLetras.getValueAt(filaseleccionada, 0) != null ) {
            txtnombrecliente.setText(tblLetras.getValueAt(filaseleccionada, 0).toString());
        }
        Servicio_CobrarLetras sl = new Servicio_CobrarLetras();
        txtvaloromitido.setText(String.valueOf(sl.Buscar_Cuenta_Letra(tblLetras.getValueAt(filaseleccionada, 1).toString()).getTotal()));

        if ( (tblLetras.getValueAt(filaseleccionada, 10)) != null ) {
            txtletrabanco.setText(tblLetras.getValueAt(filaseleccionada, 10).toString());
        }
        
        System.out.println("banco-letra ELEGIDO:" + tblLetras.getValueAt(filaseleccionada, 11));
        cbBancoLetra.setSelectedItem(tblLetras.getValueAt(filaseleccionada, 11));
        
        System.out.println("tipo-letra ELEGIDO:" + tblLetras.getValueAt(filaseleccionada, 14));
        String tipoLetra = String.valueOf(tblLetras.getValueAt(filaseleccionada, 14));
        System.out.println("tipoLetra(de JTable):" + tipoLetra);
        
        switch (tipoLetra) {
            case "LI":
                tipoLetra = LIBRE;
                break;
                
            case "CO":
                tipoLetra = COBRANZA;
                break;
                
            case "DE":
                tipoLetra = DESCUENTO;
                break;
                
            case "GA":
                tipoLetra = GARANTIA;
                break;
        }
        System.out.println("tipoLetra:::" + tipoLetra);
        cbTipoLetra.setSelectedItem(tipoLetra);
    }

    private int obtenerNumeroOcurrencias(String texto, String textoBuscado) {
        int contador = 0;
        while ( texto.indexOf(textoBuscado) > -1 ) {
            texto = texto.substring(texto.indexOf(textoBuscado) + textoBuscado.length(), texto.length());
            contador++;
        }
        return contador;
    }

    public void mostrarPagos() {
        System.out.println("mostrar pagos");
        String ndoc = tblLetras.getValueAt(filaseleccionada, 1).toString();
        CabecesId id = new CabecesId("V", "06", ndoc);
        Servicio_CobrarLetras sp = new Servicio_CobrarLetras();

        if ( !sp.listarPagosxLetras(id).isEmpty() ) {
            int n = sp.listarPagosxLetras(id).size();
            tam = n;
            
            for ( int i = 0; i < n; i++ ) {
                NroFacturas nf = new NroFacturas();
                nf.setNumero(i);
                nf.setIdFactura(sp.listarPagosxLetras(id).get(i).getIdpago());
                //System.out.println(" iddd: " + sp.listarPagosxLetras(id).get(i).getIdpago());
                nf.setImporte(sp.listarPagosxLetras(id).get(i).getImporte());
                nf.setFechaPago(sp.listarPagosxLetras(id).get(i).getFecha());
                nf.setModo(sp.listarPagosxLetras(id).get(i).getForma());
                
                if ( sp.listarPagosxLetras(id).get(i).getBancos() != null ) {
                    nf.setBanco(sp.listarPagosxLetras(id).get(i).getBancos().getNombre());
                }

                nf.setNrocheque(sp.listarPagosxLetras(id).get(i).getNrocheque());
                boolean[] editFilas = {false, false, false, false, false, false};
                modeloPagos.setEditFilas(editFilas);
                modeloPagos.getNf().add(nf);
            }
        }
    }

    public String validarEntradas() {
        String mensaje = "";
        if ( txtimporte.getText().equals("") ) {
            mensaje = mensaje + "FALTA IMPORTE";
        }
        if ( txtfecha.getDate() == null ) {
            mensaje = mensaje + "\nFALTA FECHA";
        }
        System.out.println("mensaje:" + mensaje + ".");
        return mensaje;
    }

    public void fechaSistema() {
        Date ahora = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(ahora);
        JlFecha.setText(fecha);
    }

    private void iniciarBanco() {
        Servicio_Banco sba = new Servicio_Banco(null);
        Iterator it = sba.getList().iterator();
        while ( it.hasNext() ) {
            Bancos b = (Bancos) it.next();
            cbbanco.addItem(b.getNombre());
        }
    }

    public String[] getFacturasReferencia() {
        return facturasReferencia;
    }

    public void setFacturasReferencia(String[] facturasReferencia) {
        this.facturasReferencia = facturasReferencia;
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
        jLabel8 = new javax.swing.JLabel();
        txttipodoc = new javax.swing.JTextField();
        txtnumdoc = new javax.swing.JTextField();
        txtfechaemision = new javax.swing.JTextField();
        txtfechavencimiento = new javax.swing.JTextField();
        txtnombrecliente = new javax.swing.JTextField();
        txtvaloromitido = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtimporte = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txtnumren = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtletrabanco = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPagos = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        txtfecha = new com.toedter.calendar.JDateChooser();
        cbmodo = new javax.swing.JComboBox();
        cbbanco = new javax.swing.JComboBox();
        txtcheque = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_facturasReferencia = new javax.swing.JTable();
        btnAceptar1 = new javax.swing.JButton();
        btnSalir1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        cbBancoLetra = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        cbTipoLetra = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("LETRAS PENDIENTES DE COBRANZA AL");

        JlFecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel3.setText("Tipo de Documento:");

        jLabel4.setText("Número Documento:");

        jLabel5.setText("Fecha de Emisión: ");

        jLabel6.setText("Fecha de Vencimiento:");

        jLabel7.setText("Nombre de Cliente: ");

        jLabel8.setText("Valor de Letra Emitida: ");

        txttipodoc.setEditable(false);
        txttipodoc.setBackground(new java.awt.Color(255, 255, 255));

        txtnumdoc.setEditable(false);
        txtnumdoc.setBackground(new java.awt.Color(255, 255, 255));

        txtfechaemision.setEditable(false);
        txtfechaemision.setBackground(new java.awt.Color(255, 255, 255));

        txtfechavencimiento.setEditable(false);
        txtfechavencimiento.setBackground(new java.awt.Color(255, 255, 255));
        txtfechavencimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfechavencimientoActionPerformed(evt);
            }
        });

        txtnombrecliente.setEditable(false);
        txtnombrecliente.setBackground(new java.awt.Color(255, 255, 255));
        txtnombrecliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreclienteActionPerformed(evt);
            }
        });

        txtvaloromitido.setEditable(false);
        txtvaloromitido.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText(" L= Letra");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("PAGOS REALIZADOS");

        jLabel12.setText("IMPORTE");

        jLabel13.setText("FECHA");

        jLabel14.setText("MODO");

        jLabel15.setText("BANCO");

        jLabel16.setText("NRO CHEQUE");

        txtimporte.setEnabled(false);

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        jLabel2.setText("R#= Renovación");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel31.setText("<<Doc. de Referencia de Letra >>");

        jLabel10.setText("Letra Banco : ");

        tablaPagos.setModel(modeloPagos);
        tablaPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPagosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaPagosMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPagos);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("CANCELACIONES");

        jCheckBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jCheckBox1MouseReleased(evt);
            }
        });

        txtfecha.setEnabled(false);

        cbmodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "E", "C" }));
        cbmodo.setEnabled(false);
        cbmodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmodoActionPerformed(evt);
            }
        });

        cbbanco.setEnabled(false);

        txtcheque.setEnabled(false);
        txtcheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtchequeActionPerformed(evt);
            }
        });
        txtcheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtchequeKeyReleased(evt);
            }
        });

        tb_facturasReferencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null}
            },
            new String [] {
                "Nº", "Factura de Referencia"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tb_facturasReferencia);
        if (tb_facturasReferencia.getColumnModel().getColumnCount() > 0) {
            tb_facturasReferencia.getColumnModel().getColumn(0).setResizable(false);
            tb_facturasReferencia.getColumnModel().getColumn(0).setPreferredWidth(3);
            tb_facturasReferencia.getColumnModel().getColumn(1).setPreferredWidth(170);
        }

        btnAceptar1.setText("Modificar");
        btnAceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar1ActionPerformed(evt);
            }
        });

        btnSalir1.setText("Eliminar");
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });

        jLabel17.setText("Banco:");

        jLabel19.setText("Tipo Letra:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel13)
                                        .addGap(85, 85, 85)
                                        .addComponent(jLabel14))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jCheckBox1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtimporte, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbmodo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cbbanco, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(122, 122, 122)
                                        .addComponent(jLabel15)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtcheque, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(jLabel16))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JlFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtfechavencimiento, javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txttipodoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtnumdoc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                                    .addComponent(txtfechaemision, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addGap(19, 19, 19)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(txtnumren, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel2)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtnombrecliente)
                                                .addGap(33, 33, 33)))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtvaloromitido, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel19))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtletrabanco, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                            .addComponent(cbTipoLetra, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbBancoLetra, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane1))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JlFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txttipodoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtnumdoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnumren, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtfechaemision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtfechavencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtnombrecliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtvaloromitido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtletrabanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(cbBancoLetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTipoLetra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel12)
                        .addComponent(jLabel14)
                        .addComponent(jLabel16)
                        .addComponent(jLabel15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtimporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbmodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbbanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtcheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBox1))))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfechavencimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfechavencimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechavencimientoActionPerformed

    private void txtnombreclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreclienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreclienteActionPerformed

    private void cbmodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmodoActionPerformed

    private void jCheckBox1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBox1MouseReleased
        if ( jCheckBox1.isSelected() == true ) {
            txtimporte.setEnabled(true);
            txtfecha.setEnabled(true);
            cbmodo.setEnabled(true);
            cbbanco.setEnabled(true);
            txtcheque.setEnabled(true);
            
        } else {
            txtimporte.setEnabled(false);
            txtfecha.setEnabled(false);
            cbbanco.setEnabled(false);
            cbmodo.setEnabled(false);
            txtcheque.setEnabled(false);
        }
    }//GEN-LAST:event_jCheckBox1MouseReleased

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
//        String validacion = validarEntradas();
        int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro de realizar los cambios?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION);
        if ( r == JOptionPane.YES_OPTION ) {                
            String validacionPago = validarEntradas();

            Pagos p = new Pagos();
            Servicio_Pagos sp = new Servicio_Pagos();
            Bancos ba = new Bancos();

            CabecesId cb = new CabecesId();
            Servicio_CobrarLetras sv = new Servicio_CobrarLetras();

            cb.setTipotra("V");
            cb.setTipodoc("06");
            cb.setNrodocumento(txtnumdoc.getText());
            Cabeces cabecera = sv.obtenerCabecera_Id(cb);
            String letraTipoCompleto = String.valueOf(cbTipoLetra.getSelectedItem());

            if ( validacionPago.equals("") ) {
                System.out.println("VALIDO PAGO OK");

                // Campos para guardar pago
                Date b = txtfecha.getDate();
                double a = Double.parseDouble(txtimporte.getText());
                String c = cbmodo.getSelectedItem().toString();
                String e = txtcheque.getText();
                String d = cbbanco.getSelectedItem().toString();
                int id = sb.getBanco_Nombre(d).getIdbanco();
                ba.setIdbanco(id);

                p.setUsuarios(usuario);
                p.setBancos(ba);
                p.setCabeces(cabecera);
                p.setFecha(b);
                p.setImporte(a);
                p.setForma(c);
                p.setNrocheque(e);
                
                System.out.println("txtLetraBanco con valor:" + "".equals(txtletrabanco.getText()));
                System.out.println("txtNumRenovacion con valor:" + "".equals(txtnumren.getText()));

                cabecera = actualizarCabeceraFactura(cabecera);
                p.setCabeces(cabecera);
                
                if ( sp.GuardarPagos(p) ) {
                    System.out.println("Guarda SOLO pago");
                    iniciarDatosCliente();
//                    iniciarBanco();
                    modeloPagos = new Tabla_CobrarFacturas();
                    tablaPagos.setModel(modeloPagos);
                    mostrarPagos();
                    txtnumren.setText(String.valueOf(cabecera.getNrorenovacion()));
                    setearComboLetraBancoYTipo(cabecera.getLetraBanco(), letraTipoCompleto);
                    setearRenovacion(cabecera);

                    JOptionPane.showMessageDialog(null, "El pago se realizó con éxito", "PAGOS", JOptionPane.INFORMATION_MESSAGE);

                    tblLetras.getDataVector().removeAllElements();
                    tblLetras.fireTableDataChanged();
                    ((AbstractTableModel) tablaPagos.getModel()).fireTableDataChanged();
                    lc.iniciarTabla(tt);
                    limpiarDatos();
//                        String moneda = tabla.getValueAt(filaseleccionada, 6).toString();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar pago", "PAGOS", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                System.out.println("no se valido pago ok");
            }

            ////////////////////////
            // aki estaba el anterior BLOQUE de codigo
            ////////////////////////

            cabecera = actualizarCabeceraFactura(cabecera);
            setearComboLetraBancoYTipo(cabecera.getLetraBanco(), letraTipoCompleto);
            setearRenovacion(cabecera);
            
            if ( sp.ActualizaCab(cabecera) ) {
                System.out.println("ACTUALIZA solo Cabecera");
//                System.out.println("LETRA TIPO::" + cabecera.getLetraTipo());
//                System.out.println("BANCO LETRA::"+ cabecera.getLetraBanco());
//                System.out.println("actualiza cabecera");
//                    iniciarDatosCliente();
//                    iniciarBanco();
//                    modelo = new Tabla_CobrarFacturas();
//                    tablaPagos.setModel(modelo);
//                    mostrarPagos();
//                    JOptionPane.showMessageDialog(null, "El pago se realizó con éxito", "PAGOS", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "Se actualizó con éxito", "PAGOS", JOptionPane.INFORMATION_MESSAGE);
                
                    tblLetras.getDataVector().removeAllElements();
                    tblLetras.fireTableDataChanged();
//
//                    ((AbstractTableModel) tablaPagos.getModel()).fireTableDataChanged();
//
                    lc.iniciarTabla(tt);
//                    limpiarDatos();
//
//                    String moneda = tabla.getValueAt(filaseleccionada, 6).toString();
//                    txtsaldodeuda.setText(moneda + " " + tabla.getValueAt(filaseleccionada, 9).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar pago", "PAGOS", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println("n° renovacion(***) : " + cabecera.getNrorenovacion());
            if ( cabecera.getNrorenovacion() == 0 ) {
                txtnumren.setText("");

            } else {
                txtnumren.setText(String.valueOf(cabecera.getNrorenovacion()));
            }
        }
    }//GEN-LAST:event_btnAceptarActionPerformed
    
    private void setearRenovacion(Cabeces cabecera) {
        if ( cabecera.getNrorenovacion() == 0 ) {
            txtnumren.setText("");
        } else {
            txtnumren.setText(String.valueOf(cabecera.getNrorenovacion()));
        }
        System.out.println("NUM renova en txtnumren:::" + txtnumren.getText());
    }
    
    private Cabeces actualizarCabeceraFactura(Cabeces cabecera) {
        cabecera.setLetraNrobanco(txtletrabanco.getText());

        if ( "".equals(txtnumren.getText()) ) {
            cabecera.setNrorenovacion(0);
        } else {
            cabecera.setNrorenovacion(Integer.parseInt(txtnumren.getText()));
        }
        System.out.println("set N° renovacion = " + cabecera.getNrorenovacion());

        String bancoLetra = String.valueOf(cbBancoLetra.getSelectedItem());
        System.out.println("bancoLetra del combo en PAGOS:" + bancoLetra);
        if ( SELECCIONAR.equals(bancoLetra) ) {
            cabecera.setLetraBanco(null);
        } else {
            cabecera.setLetraBanco(String.valueOf(cbBancoLetra.getSelectedItem()));
        }
        System.out.println("LetraBanco guardado:" + cabecera.getLetraBanco());
        String letraTipo2Char = String.valueOf(cbTipoLetra.getSelectedItem()).substring(0, 2);
        System.out.println("letraTipo2Char:" + letraTipo2Char);

        if ( ABR_LI.equals(letraTipo2Char) ) {
            cabecera.setLetraTipo(null);
        } else {
            String letraTipo[] = String.valueOf(cbTipoLetra.getSelectedItem()).split(" ");
            cabecera.setLetraTipo(letraTipo[0]);
        }
        System.out.println("LETRA TIPO guardado:" + cabecera.getLetraTipo());
        return cabecera;
    }
    
    public void setearComboLetraBancoYTipo(String letraBanco, String letraTipo) {
        // Setear valores seleccionados de LETRA_TIPO y LETRA_BANCO
        System.out.println("VALORES NUEVOS:");
        System.out.println("LetraBanco::" + letraBanco);
        System.out.println("LetraTipo::" + letraTipo);
        cbBancoLetra.setSelectedItem(letraBanco);
        cbTipoLetra.setSelectedItem(letraTipo);
    }
    
    public void limpiarDatos() {
        jCheckBox1.setSelected(false);
        txtimporte.setText("");
        txtfecha.setDate(null);
        cbmodo.setSelectedIndex(0);
        cbbanco.setSelectedItem("CAJA");
        txtcheque.setText("");
        txtimporte.setEnabled(false);
        txtfecha.setEnabled(false);
        cbmodo.setEnabled(false);
        cbbanco.setEnabled(false);
        txtcheque.setEnabled(false);
    }

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
        limpiarTabla();
        iniciarTabla(tt);
    }//GEN-LAST:event_btnSalirActionPerformed

    public void limpiarTabla(){
        try {
            DefaultTableModel modelo = (DefaultTableModel) frep043.tablaLetras.getModel();
            int filas = frep043.tablaLetras.getRowCount();
            
            for ( int i = 0; filas > i; i++ ) {
                modelo.removeRow(0);
            }
        } catch ( Exception e ) {
            System.out.println("Excepcion:" + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    public DefaultTableModel iniciarTabla(JTable tabla) {
//        textoLetras.setText("LETRAS PENDIENTES DE COBRANZA AL " + fechaSistema());
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        Servicio_CobrarLetras sc = new Servicio_CobrarLetras();
        List li1 = sc.Listar_Cuentas_x_Cobrar_Letras();
        int tam = li1.size();
        
        if ( tam != 0 ) {
            for ( int i = 0; i < tam; i++ ) {
                Object[] a = (Object[]) li1.get(i);
                
                if ( a[5].equals("US$") ) {
                    frep043.cont_Emi_D = frep043.cont_Emi_D.add(BigDecimal.valueOf(Double.parseDouble(a[6].toString())));
                    frep043.cont_Sal_D = frep043.cont_Sal_D.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                    
                } else {
                    frep043.cont_Emi_C = frep043.cont_Emi_C.add(BigDecimal.valueOf(Double.parseDouble(a[6].toString())));
                    frep043.cont_Sal_C = frep043.cont_Sal_C.add(BigDecimal.valueOf(Double.parseDouble(a[7].toString())));
                }
                String valorEmision = formatearMonetario(String.valueOf(a[6]), 2);
                String saldoXPagar = formatearMonetario(String.valueOf(a[7]), 2);
                
                String renovacion = "";
                if ( a[2] != null ) {
                    if ( "0".equals(String.valueOf(a[2])) ) {
                        renovacion = "";
                    } else {
                        renovacion = String.valueOf(a[2]);
                    }
                }
                
                String bancoLetra = String.valueOf(a[12]);
                if ( a[12] == null ) {
//                    bancoLetra = SELECCIONAR; // DEBERIA SER VACÍO
                    bancoLetra = "";
                }
                
                String tipoLetra = String.valueOf(a[13]);
                if ( a[13] == null ) {
                    tipoLetra = "LI";
                }
                
                Object[] fila = {a[0], a[1], renovacion, a[11], a[3], a[4], a[5], valorEmision, saldoXPagar, a[8], a[9], bancoLetra, a[10], false, tipoLetra};
//                Object[] fila = {a[0], a[1], a[2], a[11], a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10], false};
                modelo.addRow(fila);
            }
            String valorInicial = "0.00";
            frep043.txtemisionS.setText(valorInicial);
            frep043.txtsaldopagarS.setText(valorInicial);
            frep043.txtemisionD.setText(valorInicial);
            frep043.txtsaldopagarD.setText(valorInicial);

        } else {
            JOptionPane.showMessageDialog(null, "No existen registros");
        }
        return modelo;
    }
    
    private String formatearMonetario(String valorMonetario, int numeroDecimales) {
        int indicePunto = valorMonetario.indexOf(".") + 1;
        
        if ( indicePunto == 0 ) {
            valorMonetario = valorMonetario + ".00";
            
        } else {
            int longitud = valorMonetario.length();
            int decimales = longitud - indicePunto;
            int diferenciaCeros = numeroDecimales - decimales;
            for ( int i = 0; i < diferenciaCeros; i++ ) {
                valorMonetario = valorMonetario + "0";
            }
        }
        return valorMonetario;
    }
    
    private void txtchequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtchequeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtchequeActionPerformed

    private void txtchequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtchequeKeyReleased
        char c = evt.getKeyChar();
        String aux = txtcheque.getText();

        if ( String.valueOf(c).matches("(\\w| |\\-)*") ) {
            System.out.println("Válido");
            
        } else {
            System.out.println("No Válido");
            
            if ( aux.length() != 0 ) {
                aux = aux.substring(0, aux.length() - 1);
                
            } else {
                aux = "";
            }
        }
        txtcheque.setText(aux.toUpperCase());
    }//GEN-LAST:event_txtchequeKeyReleased

    private void tablaPagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPagosMouseClicked
        selectRowPago = tablaPagos.getSelectedRow();
        //Pagos pago= modelo.getNf().get(x);
        Pagos pa = new Pagos();
        jCheckBox1.setSelected(true);
        
        for ( int i = 0; i < tablaPagos.getColumnCount(); i++ ) {
            // result[i] = table.getModel().getValueAt(row, col);
            //System.out.println(": " + tablaPagos.getValueAt(x, i));
            switch (i) {
                case 0:
                    txtimporte.setText(String.valueOf(tablaPagos.getValueAt(selectRowPago, i)));
                    txtimporte.setEnabled(true);
                    break;
                    
                case 1:
                    txtfecha.setDate(((JDateChooser) tablaPagos.getValueAt(selectRowPago, i)).getDate());
                    txtfecha.setEnabled(true);
                    break;
                    
                case 2:
                    if ( (String.valueOf(tablaPagos.getValueAt(selectRowPago, i))).equals("E") ) {
                        cbmodo.setSelectedIndex(0);
                        
                    } else {
                        if ( (String.valueOf(tablaPagos.getValueAt(selectRowPago, i))).equals("C") ) {
                            cbmodo.setSelectedIndex(1);
                            
                        } else {
                            cbmodo.setSelectedIndex(2);
                        }
                    }
                    cbmodo.setEnabled(true);
                    break;
                    
                case 3:
                    cbbanco.setSelectedItem(tablaPagos.getValueAt(selectRowPago, i));
                    cbbanco.setEnabled(true);
                    break;
                    
                case 4:
                    txtcheque.setText(String.valueOf(tablaPagos.getValueAt(selectRowPago, i)));
                    if ( tablaPagos.getValueAt(selectRowPago, i) == null ) {
                        txtcheque.setText("");
                    }
                    txtcheque.setEnabled(true);
                    break;
            }
        }
    }//GEN-LAST:event_tablaPagosMouseClicked

    private void tablaPagosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPagosMouseReleased
        selectPago = true;
    }//GEN-LAST:event_tablaPagosMouseReleased

    private void btnAceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar1ActionPerformed
        if ( selectPago ) {
            String validacion = validarEntradas();
            
            if ( !validacion.equals("") ) {
                JOptionPane.showMessageDialog(null, validacion, "Falta ingresar datos", JOptionPane.ERROR_MESSAGE);
                
            } else {
                int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro de modificar el pago?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION);
                
                if ( r == JOptionPane.YES_OPTION)  {
                    Pagos p = new Pagos();
                    Servicio_Pagos sp = new Servicio_Pagos();
                    Bancos ba = new Bancos();
                    CabecesId cb = new CabecesId();
                    Servicio_CobrarLetras sv = new Servicio_CobrarLetras();

                    cb.setTipotra("V");
                    cb.setTipodoc("06");
                    cb.setNrodocumento(txtnumdoc.getText());
                    Cabeces cabecera = sv.obtenerCabecera_Id(cb);
                    double a = Double.parseDouble(txtimporte.getText());
                    Date b = txtfecha.getDate();
                    String c = cbmodo.getSelectedItem().toString();
                    String d = cbbanco.getSelectedItem().toString();
                    String e = txtcheque.getText().trim().replaceAll("( )+", " ");

                    int id = sb.getBanco_Nombre(d).getIdbanco();
                    int idPago = modeloPagos.getNf().get(selectRowPago).getIdFactura();
                    System.out.println("id pago letras en modif: " + idPago);

                    ba.setIdbanco(id);
                    p.setUsuarios(usuario);
                    p.setBancos(ba);
                    p.setCabeces(cabecera);
                    p.setFecha(b);
                    p.setImporte(a);
                    p.setForma(c);
                    p.setNrocheque(e);
                    p.setIdpago(idPago);

                    if ( sp.modificarPagos(p) ) {
                        iniciarDatosCliente();
                        iniciarBanco();
                        modeloPagos = new Tabla_CobrarFacturas();
                        tablaPagos.setModel(modeloPagos);
                        mostrarPagos();

                        JOptionPane.showMessageDialog(null, "El pago se modificó con éxito", "PAGOS", JOptionPane.INFORMATION_MESSAGE);
                        tblLetras.getDataVector().removeAllElements();
                        tblLetras.fireTableDataChanged();

                        ((AbstractTableModel) tablaPagos.getModel()).fireTableDataChanged();

                        lc.iniciarTabla(tt);
                        limpiarDatos();
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
    }//GEN-LAST:event_btnAceptar1ActionPerformed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        if ( selectPago ) {
            Servicio_Pagos sp = new Servicio_Pagos();
            int r = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar el Pago?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION);
            
            if ( r == JOptionPane.YES_OPTION ) {
                Pagos p = new Pagos();
                int idPago = modeloPagos.getNf().get(selectRowPago).getIdFactura();
                p.setIdpago(idPago);
                
                if ( sp.eliminarPagos(p) ) {
                    iniciarDatosCliente();
                    iniciarBanco();
                    modeloPagos = new Tabla_CobrarFacturas();
                    tablaPagos.setModel(modeloPagos);
                    mostrarPagos();

                    JOptionPane.showMessageDialog(null, "El pago ha sido eliminado con éxito", "PAGOS", JOptionPane.INFORMATION_MESSAGE);
                    tblLetras.getDataVector().removeAllElements();
                    tblLetras.fireTableDataChanged();
                    ((AbstractTableModel) tablaPagos.getModel()).fireTableDataChanged();

                    lc.iniciarTabla(tt);
                    limpiarDatos();

                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar pago", "PAGOS", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un pago", "PAGOS", JOptionPane.ERROR_MESSAGE);
        }
        selectPago = false;
    }//GEN-LAST:event_btnSalir1ActionPerformed
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PagarFacturasxCobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PagarFacturasxCobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PagarFacturasxCobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PagarFacturasxCobrar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PagarLetrasxCobrar.setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JlFecha;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAceptar1;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JComboBox cbBancoLetra;
    private javax.swing.JComboBox cbTipoLetra;
    private javax.swing.JComboBox cbbanco;
    private javax.swing.JComboBox cbmodo;
    private javax.swing.JCheckBox jCheckBox1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaPagos;
    private javax.swing.JTable tb_facturasReferencia;
    private javax.swing.JTextField txtcheque;
    private com.toedter.calendar.JDateChooser txtfecha;
    private javax.swing.JTextField txtfechaemision;
    private javax.swing.JTextField txtfechavencimiento;
    private javax.swing.JTextField txtimporte;
    private javax.swing.JTextField txtletrabanco;
    private javax.swing.JTextField txtnombrecliente;
    private javax.swing.JTextField txtnumdoc;
    private javax.swing.JTextField txtnumren;
    private javax.swing.JTextField txttipodoc;
    private javax.swing.JTextField txtvaloromitido;
    // End of variables declaration//GEN-END:variables
}