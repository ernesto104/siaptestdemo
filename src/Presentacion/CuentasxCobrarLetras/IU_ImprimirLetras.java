package Presentacion.CuentasxCobrarLetras;

import Entidades.Clientes;
import Entidades.Control;
import static Entidades.Otros.Constante.OPC_SELECCIONADOS;
import static Entidades.Otros.Constante.OPC_TODA_CTA_CTE;
import static Entidades.Otros.Constante.OPC_UN_CLIENTE;
import Entidades.Otros.ImpresionExcel;
import Mantenimiento.ControlDAO;
import Servicios.CuentasxCobrar.Sv_Impresion;
import Servicios.Servicio_Cliente;
import Servicios.Servicio_Control;
import Servicios.Servicio_Excel;
import Servicios.TipoMensaje;
import Servicios.Util;
import Servicios.facturacion.Billete;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

/**
 *
 * @author keily
 */
public class IU_ImprimirLetras extends javax.swing.JFrame {

    FREP043 lc;
    DefaultTableModel mod;
    int fila;
    double importeTotal;
    
    double totalEmitidoDolares;
    double totalSaldoDolares;
    double totalEmitidoSoles;
    double totalSaldoSoles;
    
    Util util;
    Servicio_Excel servicio_Excel;

    public IU_ImprimirLetras(DefaultTableModel t, int fila, FREP043 lc) {
        initComponents();
        mod = t;
        this.lc = lc;
        this.fila = fila;
        iniciarCombos();
        this.setLocationRelativeTo(null);
        
        importeTotal = 0.00;
        
        totalEmitidoDolares = 0.00;
        totalSaldoDolares = 0.00;
        totalEmitidoSoles = 0.00;
        totalSaldoSoles = 0.00;
        
        util = new Util();
    }

    public void iniciarCombos() {
        Servicio_Cliente scli = new Servicio_Cliente(null);
        for ( Object cli : scli.Consulta_Nombre_Cliente() ) {
            if ( cli != null ) {
                comboCliente.addItem(cli.toString());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbCtaCte = new javax.swing.JRadioButton();
        rbCliente = new javax.swing.JRadioButton();
        comboCliente = new javax.swing.JComboBox();
        rbSeleccionados = new javax.swing.JRadioButton();
        rb1SolaLetra = new javax.swing.JRadioButton();
        botonImprimir = new javax.swing.JButton();
        botonsalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Seleccionar Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        buttonGroup1.add(rbCtaCte);
        rbCtaCte.setText("Toda Cta. Cte");
        rbCtaCte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbCtaCteMouseReleased(evt);
            }
        });

        buttonGroup1.add(rbCliente);
        rbCliente.setText("1 Cliente");
        rbCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbClienteMouseReleased(evt);
            }
        });

        comboCliente.setEnabled(false);

        buttonGroup1.add(rbSeleccionados);
        rbSeleccionados.setText("Seleccionados");
        rbSeleccionados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                rbSeleccionadosMouseReleased(evt);
            }
        });

        buttonGroup1.add(rb1SolaLetra);
        rb1SolaLetra.setText("1 Sola Letra");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rbCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbCtaCte, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboCliente, 0, 225, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rb1SolaLetra)
                            .addComponent(rbSeleccionados))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(rbCtaCte)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbCliente)
                    .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(rbSeleccionados)
                .addGap(18, 18, 18)
                .addComponent(rb1SolaLetra)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        botonImprimir.setText("Aceptar");
        botonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonImprimirActionPerformed(evt);
            }
        });

        botonsalir.setText("Salir");
        botonsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(botonsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botonsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbCtaCteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbCtaCteMouseReleased
        comboCliente.setEnabled(false);
    }//GEN-LAST:event_rbCtaCteMouseReleased

    private void rbClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbClienteMouseReleased
        comboCliente.setEnabled(true);
    }//GEN-LAST:event_rbClienteMouseReleased

    private void rbSeleccionadosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbSeleccionadosMouseReleased
        comboCliente.setEnabled(false);
    }//GEN-LAST:event_rbSeleccionadosMouseReleased

    private void botonsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonsalirActionPerformed
        dispose();
    }//GEN-LAST:event_botonsalirActionPerformed

    private ArrayList SetLista(){
        ArrayList<ListaLetras> lista = new ArrayList<>();
        for ( int i = 0; i < mod.getRowCount(); i++ ) {
            String cliente = String.valueOf(mod.getValueAt(i, 0));
            String numLetra = String.valueOf(mod.getValueAt(i, 1));
            String fechaEmision = String.valueOf(mod.getValueAt(i, 4));
            String fechaVence = String.valueOf(mod.getValueAt(i, 5));
            String emitido = String.valueOf(mod.getValueAt(i, 6)) + " " + String.valueOf(mod.getValueAt(i, 7));
            String saldo = String.valueOf(mod.getValueAt(i, 6)) + " " + String.valueOf(mod.getValueAt(i, 8));
            String diasVencido = String.valueOf(mod.getValueAt(i, 9));
            String facturaRef = String.valueOf(mod.getValueAt(i, 12));
            String numRenovacion = String.valueOf(mod.getValueAt(i, 2));
            
            String numLetraBanco = String.valueOf(mod.getValueAt(i, 10));
            String bancoLetra = String.valueOf(mod.getValueAt(i, 11));
            
            lista.add(new ListaLetras(cliente, numLetra, fechaEmision, fechaVence, emitido, saldo, diasVencido, facturaRef,
                                      numRenovacion, numLetraBanco, bancoLetra));
            
            double totalEmitido = Double.parseDouble(String.valueOf(mod.getValueAt(i, 7)));
            double totalSaldo = Double.parseDouble(String.valueOf(mod.getValueAt(i, 8)));
            
            if ( "US$".equals(mod.getValueAt(i, 6)) ) {
                totalEmitidoDolares += totalEmitido;
                totalSaldoDolares += totalSaldo;
                
            } else {
                totalEmitidoSoles += totalEmitido;
                totalSaldoSoles += totalSaldo;
            }
        }
        return lista;
    }
    
//    private ArrayList SetLista() {
//        ArrayList<ListaLetras> lista = new ArrayList<>();
//        Servicio_Cliente cl = new Servicio_Cliente(null);
//        
//        importeTotal = 0.00;
//        
//        for (int i = 0; i < mod.getRowCount(); i++) {
//            Clientes cli = cl.Obtener_Cliente_Por_Nombre(String.valueOf(mod.getValueAt(i, 0)));
//
//            String numdoc = String.valueOf(mod.getValueAt(i, 1));
//            String letra_facol = String.valueOf(mod.getValueAt(i, 11));
//            String lugar_giro = "LIMA";
//            String fecha = formatearFecha(String.valueOf(mod.getValueAt(i, 4))) 
//                        + "   " + formatearFecha(String.valueOf(mod.getValueAt(i, 5)));
//            
//            String moneda = String.valueOf(mod.getValueAt(i, 6));
//            String importe = moneda + " " + String.valueOf(mod.getValueAt(i, 7));
//            String cliente = String.valueOf(mod.getValueAt(i, 0));
//            String direccion = cli.getDireccion();
//            if ( cli.getPlaza() != null ) {
//                direccion = direccion + " - " + cli.getPlaza();
//            }
//            String ruc = cli.getRuc();
//            String telefijo = ( cli.getTelefonofijo() == null ) ? "" : cli.getTelefonofijo();
//            
//            String monedaTexto = moneda.equals("US$") ? " DOLARES AMERICANOS" : " NUEVOS SOLES";
//            double importeTotal = Double.parseDouble(String.valueOf(mod.getValueAt(i, 7)));
//            String importeEnTexto = Billete.BilleteX(importeTotal, monedaTexto);
//            
//            Servicio_Control sc = new Servicio_Control();
//            String empresa = sc.listar_control().get(0).getNombrempresa();
//            System.out.println("empresa:" + empresa);
//        
//            lista.add(new ListaLetras(numdoc, letra_facol, lugar_giro, fecha, 
//                    importe, cliente,
//                    direccion, ruc, telefijo, importeEnTexto, empresa));
//        }
//        return lista;
//    }

    private HashMap SetParametros() {
        Servicio_Control sc = new Servicio_Control();
        HashMap mapa = new HashMap<>();
        String titulo = lc.textoLetras.getText();
        String empresa = sc.listar_control().get(0).getNombrempresa();
        mapa.put("titulo", titulo);
        mapa.put("empresa", empresa);
        
        double totEmiSoles = util.Redondear2Decimales(totalEmitidoSoles);
        double totSaldoSoles = util.Redondear2Decimales(totalSaldoSoles);
        double totEmiDolar = util.Redondear2Decimales(totalEmitidoDolares);
        double totSaldoDolar = util.Redondear2Decimales(totalSaldoDolares);
        
        mapa.put("totEmisionSoles", formatearCeros(String.valueOf(totEmiSoles), 2));
        mapa.put("totSaldoSoles", formatearCeros(String.valueOf(totSaldoSoles), 2));
        mapa.put("totEmisionDolar", formatearCeros(String.valueOf(totEmiDolar), 2));
        mapa.put("totSaldoDolar", formatearCeros(String.valueOf(totSaldoDolar), 2));
        
        System.out.println("total Emitido S/. " + totEmiSoles);
        System.out.println("total Saldo S/. " + totSaldoSoles);
        System.out.println("total Emitido US $ " + totEmiDolar);
        System.out.println("total Saldo US $ " + totSaldoDolar);
        return mapa;
    }
    
    private static String formatearCeros(String numero, int cantidadDigitos) {
        String formateado = numero;
        int longitudCeros = cantidadDigitos - numero.length();
        for ( int i = 0; i < longitudCeros; i++ ) {
            formateado = "0" + formateado;
        }
        return formateado;
    }
    
    private ArrayList SetListaSeleccionados() {
        ArrayList<ListaLetras> lista = new ArrayList<>();

        for (int i = 0; i < mod.getRowCount(); i++) {
            if ((boolean) mod.getValueAt(i, 13) == true) {
                String cliente = String.valueOf(mod.getValueAt(i, 0));
                String numLetra = String.valueOf(mod.getValueAt(i, 1));
                String fechaEmision = String.valueOf(mod.getValueAt(i, 4));
                String fechaVence = String.valueOf(mod.getValueAt(i, 5));
                
                String emitido = String.valueOf(mod.getValueAt(i, 6)) + " " + String.valueOf(mod.getValueAt(i, 7));
                String saldo = String.valueOf(mod.getValueAt(i, 6)) + " " + String.valueOf(mod.getValueAt(i, 8));
                
                String diasVencido = String.valueOf(mod.getValueAt(i, 9));
                String facturaRef = String.valueOf(mod.getValueAt(i, 12));
                
                String numRenovacion = String.valueOf(mod.getValueAt(i, 2));
                
                String numLetraBanco = String.valueOf(mod.getValueAt(i, 10));
                String bancoLetra = String.valueOf(mod.getValueAt(i, 11));
                        
                lista.add(new ListaLetras(cliente, numLetra, fechaEmision, fechaVence, emitido, saldo, diasVencido, facturaRef,
                                          numRenovacion, numLetraBanco, bancoLetra));
                
                double totalEmitido = Double.parseDouble(String.valueOf(mod.getValueAt(i, 7)));
                double totalSaldo = Double.parseDouble(String.valueOf(mod.getValueAt(i, 8)));

                if ( "US$".equals(mod.getValueAt(i, 6)) ) {
                    totalEmitidoDolares += totalEmitido;
                    totalSaldoDolares += totalSaldo;

                } else {
                    totalEmitidoSoles += totalEmitido;
                    totalSaldoSoles += totalSaldo;
                }
            }
        }
        return lista;
    }
    
    
     private ArrayList SetListaXCliente() {
        ArrayList<ListaLetras> lista = new ArrayList<>();

        for (int i = 0; i < mod.getRowCount(); i++) {
            String clienteBrowse = String.valueOf(mod.getValueAt(i, 0));
            
            if ( rbCliente.isSelected() ) {
                String clienteSeleccionado = String.valueOf(comboCliente.getSelectedItem());
                
                if ( clienteSeleccionado.equals(clienteBrowse) ) {
                    String cliente = clienteBrowse;
                    String numLetra = String.valueOf(mod.getValueAt(i, 1));
                    String fechaEmision = String.valueOf(mod.getValueAt(i, 4));
                    String fechaVence = String.valueOf(mod.getValueAt(i, 5));
                    String emitido = String.valueOf(mod.getValueAt(i, 6)) + " " + String.valueOf(mod.getValueAt(i, 7));
                    String saldo = String.valueOf(mod.getValueAt(i, 6)) + " " + String.valueOf(mod.getValueAt(i, 8));
                    String diasVencido = String.valueOf(mod.getValueAt(i, 9));
                    String facturaRef = String.valueOf(mod.getValueAt(i, 12));
                    
                    String numRenovacion = String.valueOf(mod.getValueAt(i, 2));
                    
                    String numLetraBanco = String.valueOf(mod.getValueAt(i, 10));
                    String bancoLetra = String.valueOf(mod.getValueAt(i, 11));
                    
                    lista.add(new ListaLetras(cliente, numLetra, fechaEmision, fechaVence, emitido, saldo, diasVencido, facturaRef,
                                              numRenovacion, numLetraBanco, bancoLetra));
                    
                    double totalEmitido = Double.parseDouble(String.valueOf(mod.getValueAt(i, 7)));
                    double totalSaldo = Double.parseDouble(String.valueOf(mod.getValueAt(i, 8)));

                    if ( "US$".equals(mod.getValueAt(i, 6)) ) {
                        totalEmitidoDolares += totalEmitido;
                        totalSaldoDolares += totalSaldo;

                    } else {
                        totalEmitidoSoles += totalEmitido;
                        totalSaldoSoles += totalSaldo;
                    }
                }
            }
        }
        return lista;
     }

    private ArrayList SetLista1Letra() {
        ArrayList<Letra> lista = new ArrayList<>();
        Control control = new Servicio_Control().listar_control().get(0);

        for ( int i = 0; i < mod.getRowCount(); i++ ) {
            
            if ( (boolean) mod.getValueAt(i, 13) == true ) {
                Servicio_Cliente cl = new Servicio_Cliente(null);
                Clientes cli = cl.Obtener_Cliente_Por_Nombre(String.valueOf(mod.getValueAt(i, 0)));

                String numdoc = String.valueOf(mod.getValueAt(i, 1));
                String letTemp[] = String.valueOf(mod.getValueAt(i, 12)).split("/");
                String ocholetTemp[] = get8Letras(letTemp);
                
//                String letras[] = formatearLetras(letTemp, 2);
                String letras[] = formatearLetras(ocholetTemp, 2);
                
                String letra_facbol = formatearCadenaNula(letras[0]);
                String letra_facbol2 = formatearCadenaNula(letras[1]);
                System.out.println("letra_facbol:::" + letra_facbol);
                System.out.println("letra_facbol2:::" + letra_facbol2);
                
                String lugar_giro = control.getUbidpto();
//                String fecha = formatearFecha(String.valueOf(mod.getValueAt(i, 4)));
//                String fechavenc = formatearFecha(String.valueOf(mod.getValueAt(i, 5)));
                
                String fechaGiro = formatearFecha(String.valueOf(mod.getValueAt(i, 4)));
                String fechaVencimiento = formatearFecha(String.valueOf(mod.getValueAt(i, 5)));
                
                
//                String fg[] = new String[3];
//                fg = fechaGiro.split("-");
//                System.out.println("Fecha de Giro");
//                System.out.println("fg[0]:" + fg[0]);
//                System.out.println("fg[1]:" + fg[1]);
//                System.out.println("fg[2]:" + fg[2]);
//                fechaGiro = fg[2] + "/" + fg[1] + "/" + fg[0];
//                System.out.println("fecha giro:" + fechaGiro);
                
//                fg = fechaVencimiento.split("-");
//                fechaVencimiento = fg[2] + "/" + fg[1] + "/" + fg[0];
//                System.out.println("Fecha Vencimiento");
//                System.out.println("fg[0]:" + fg[0]);
//                System.out.println("fg[1]:" + fg[1]);
//                System.out.println("fg[2]:" + fg[2]);
//                System.out.println("fecha Vencimiento:" + fechaVencimiento);
//                String fecha = formatearFecha(String.valueOf(mod.getValueAt(i, 4))) 
//                        + "         " + formatearFecha(String.valueOf(mod.getValueAt(i, 5)));
                
                String moneda = String.valueOf(mod.getValueAt(i, 6));
                String importe = moneda + " " + String.valueOf(mod.getValueAt(i, 7));
                String cliente = String.valueOf(mod.getValueAt(i, 0));
                String direccion = cli.getDireccion();
                
                String localidad = cli.getPlaza();
//                String localidad = cli.getUbigeo().getProvincia();
                if ( cli.getPlaza() != null ) {
                    direccion = direccion + " - " + cli.getPlaza();
                }
                String ruc = cli.getRuc();
                String telefijo = ( cli.getTelefonofijo() == null ) ? "" : cli.getTelefonofijo();
                
                String monedaTexto = moneda.equals("US$") ? " DOLARES AMERICANOS" : " SOLES";
                double importeTotal = Double.parseDouble(String.valueOf(mod.getValueAt(i, 7)));
                String importeEnTexto = Billete.BilleteX(importeTotal, monedaTexto);
                String empresa = control.getNombrempresa();

                // Impresión Jasper (1° Versión de M&D)
//                lista.add(new Letra(numdoc, letra_facbol, letra_facbol2, lugar_giro, fecha, importe, cliente,
//                                    direccion, ruc, telefijo, importeEnTexto, empresa));
                
                // Impresión Excel (2° Versión de C&S)
                lista.add(new Letra(numdoc, letra_facbol, letra_facbol2, lugar_giro, fechaGiro, fechaVencimiento, importe, cliente,
                                    direccion, localidad, ruc, telefijo, importeEnTexto, empresa));
            }
        }
        return lista;
    }
    
    private String[] get8Letras(String letTemp[]) {
        int tam = letTemp.length;
        if ( tam > 8 ) {
            tam = 8;
        }
        String[] ochoLetras = new String[tam];
        for ( int i = 0; i < tam; i++ ) {
            ochoLetras[i] = letTemp[i];
        }
        return ochoLetras;
    }
    
    private String formatearCadenaNula(String cadena) {
        return ( cadena == null ) ? "" : cadena;
    }
    
    private String[] formatearLetras(String[] let, int tamaño) {
        String[] letras = new String[tamaño];
        String cadenaLetras = "";
        int j = 0;
        boolean faltaConcatenar = false;
        for ( int i = 0; i < let.length; i++ ) {
            if ( i == 0 ) {
                cadenaLetras = cadenaLetras + let[i];
                
            } else {
                if ( esMultiplo4(i) ) {
                    cadenaLetras = cadenaLetras + let[i];
                } else {
                    cadenaLetras = cadenaLetras + "/" + let[i];
                }
            }
            
            if ( esMultiplo4(i+1) ) {
                letras[j] = cadenaLetras;
                j++;
                faltaConcatenar = false;
                cadenaLetras = "";
            } else {
                faltaConcatenar = true;
            }
        }
        if ( faltaConcatenar ) {
            letras[j] = cadenaLetras;
        }
        return letras;
    }
    
    private boolean esMultiplo4(int numero) {
        return ( numero % 4 == 0 );
    }
    
    private String formatearFecha(String fechaString) {
        String[] fecha = fechaString.split("-");
//        String año = fecha[0].substring(2, 4);
        String año = fecha[0].substring(0, 4);
        String mes = fecha[1].substring(0, 2);
        String dia = fecha[2].substring(0, 2);
//        return año + "    " + mes + "    " + dia;
        return dia + "/" + mes + "/" + año;
    }
    
    private String formatearFechaInversa(String fechaString) {
        String[] fecha = fechaString.split("-");
        String año = fecha[0].substring(0, 4);
        String mes = fecha[1].substring(0, 2);
        String dia = fecha[2].substring(0, 2);
//        return año + "    " + mes + "    " + dia;
        return año + "/" + mes + "/" + dia;
    }

    private void botonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonImprimirActionPerformed
        TipoMensaje tm = new TipoMensaje();
        
        if ( tm.Confirmacion(tm.PREGUNTA_OPERACION) == tm.SI ) {
            dispose();
            
            // Impresión por Excel (2° Versisón de C&S)
            Control control = new ControlDAO().Obtener_Objeto(1);
            String rutaBDLetra = control.getRutaprogramas();
            String rutaExcel = rutaBDLetra.replace("/", "\\");
            
            // 1. Toda la Cuenta Corriente
            if ( rbCtaCte.isSelected() ) {
                try {
                    HashMap parametros = SetParametros();
                    ArrayList lista = SetLista();
                    
                    // Impresion por Jasper (1° Versión M&D)
//                    Sv_Impresion.exporta(5, lista, parametros, "plantillas/variasLetras.png");
                    
                    // Impresión por Excel (2° Versión C&S)
                    String nombreExcelLetras = generarExcelLetras(lista, parametros, control, OPC_TODA_CTA_CTE);
                    nombreExcelLetras = rutaExcel + "\\" + nombreExcelLetras;
                    new ImpresionExcel().imprimirEliminarExcel(nombreExcelLetras);
                    
                } catch (Exception ex) {
                    Logger.getLogger(IU_ImprimirLetras.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // 2. Por 1 cliente
            if ( rbCliente.isSelected() ) {
                ArrayList lista = SetListaXCliente();
                
                if ( lista.isEmpty() ) {
                    JOptionPane.showMessageDialog(null, "No hay letras pendientes para este cliente", 
                                                  "Letras Pendientes de Cobranza", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
//                        Sv_Impresion.exporta(5, lista, parametros, "plantillas/variasLetras.png");
                        Map<String, Object> parametros = SetParametros();
                        
                        // Impresión por Excel (2° Versión de C&S)
                        String nombreExcelLetras = generarExcelLetras(lista, parametros, control, OPC_UN_CLIENTE);
                        nombreExcelLetras = rutaExcel + "\\" + nombreExcelLetras;
                        new ImpresionExcel().imprimirEliminarExcel(nombreExcelLetras);
                        
                    } catch (Exception ex) {
                        Logger.getLogger(IU_ImprimirLetras.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            // 3. Los seleccionados
            if ( rbSeleccionados.isSelected() ) {
//                Sv_Impresion.exporta(5, lista, parametros, "plantillas/variasLetras.png");
                try {
                    HashMap parametros = SetParametros();
                    ArrayList lista = SetListaSeleccionados();
                    
                    // Impresión por Excel (2° Versión de C&S)
                    String nombreExcelLetras = generarExcelLetras(lista, parametros, control, OPC_SELECCIONADOS);
                    nombreExcelLetras = rutaExcel + "\\" + nombreExcelLetras;
                    new ImpresionExcel().imprimirEliminarExcel(nombreExcelLetras);

                } catch (Exception ex) {
                    Logger.getLogger(IU_ImprimirLetras.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            // 4. Solo 1 letra
            if ( rb1SolaLetra.isSelected() ) {
                ArrayList lista = SetLista1Letra(); // lista
                
                if ( lista.size() == 1 ) {
                    try {
                        HashMap parametros = SetParametros(); // parametros
//                        Sv_Impresion.exporta(2, lista, parametros, "plantillas/letra.png");
                        
                        // Primero se genera excel para letra y luego se envía para impresión en background usando excel                        
                        // Impresión por Excel (2° Versión C&S)
                        String nombreExcelLetra = generarExcelLetra(lista, parametros, control);
                        nombreExcelLetra = rutaExcel + "\\" + nombreExcelLetra;
                        new ImpresionExcel().imprimirDesdeExcel(nombreExcelLetra);
                        
                    } catch ( Exception ex ) {
                        Logger.getLogger(IU_ImprimirLetras.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Excepcion:" + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione SOLO 1 letra ", "Imprimir 1 letra", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_botonImprimirActionPerformed
    
    // Genera excel para imprimir 1 LETRA
    private String generarExcelLetra(ArrayList lista, Map<String, Object> parametros, Control control)
                                     throws FileNotFoundException, BiffException, IOException, WriteException {
        String nombreExcelLetra = new Servicio_Excel(null, null).Exportar_Excel_CabecDet_Letra(lista, parametros, control);
        return nombreExcelLetra;
    }
    
    // Genera excel para imprimir REPORTE DE LETRAS
    private String generarExcelLetras(ArrayList lista, Map<String, Object> parametros, Control control, int opcionReporte)
                                      throws FileNotFoundException, BiffException, IOException, WriteException {
        String nombreExcelLetras = new Servicio_Excel(null, null).Exportar_Excel_CabecDet_Letras(lista, parametros, control, opcionReporte);
        return nombreExcelLetras;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonImprimir;
    private javax.swing.JButton botonsalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox comboCliente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton rb1SolaLetra;
    private javax.swing.JRadioButton rbCliente;
    private javax.swing.JRadioButton rbCtaCte;
    private javax.swing.JRadioButton rbSeleccionados;
    // End of variables declaration//GEN-END:variables
}