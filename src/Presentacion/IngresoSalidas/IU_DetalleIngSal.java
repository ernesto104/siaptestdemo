package Presentacion.IngresoSalidas;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Clientes;
import Entidades.Control;
import Entidades.Otros.DetalleFactura;
import Entidades.Detallees;
import static Entidades.Otros.Constante.COMPRA_LOCAL;
import static Entidades.Otros.Constante.DOLAR_COMBO;
import static Entidades.Otros.Constante.DOLAR_STANDARD;
import static Entidades.Otros.Constante.INGRESO_POR_AJUSTES;
import static Entidades.Otros.Constante.INGRESO_POR_IMPORTACION;
import static Entidades.Otros.Constante.TIPODOC_ING_COMPRA_LOCAL;
import static Entidades.Otros.Constante.TIPODOC_ING_REGULARIZACION;
import static Entidades.Otros.Constante.TIPODOC_SAL_REGULARIZACION;
import static Entidades.Otros.Constante.INGRESO_POR_REGULARIZACION;
import static Entidades.Otros.Constante.ING_COMPRA_LOCAL;
import static Entidades.Otros.Constante.ING_REGULARIZACION;
import static Entidades.Otros.Constante.SALIDA_POR_AJUSTES;
import static Entidades.Otros.Constante.SALIDA_POR_REGULARIZACION;
import static Entidades.Otros.Constante.ING_IMPORTACION;
import static Entidades.Otros.Constante.SAL_REGULARIZACION;
import static Entidades.Otros.Constante.SOL;
import static Entidades.Otros.Constante.SOL_COMBO;
import static Entidades.Otros.Constante.TIPODOC_ING_IMPORTACION;
import Entidades.Otros.ImpresionExcel;
import Entidades.Sistema;
import Entidades.Tipocambio;
import Mantenimiento.ControlDAO;
import Presentacion.Facturacion.IU_Descuentos;
import Presentacion.Facturacion.IU_FMaestro;
import Servicios.Constantes;
import Servicios.Editor_Celdas;
import Servicios.IngresoSalidas.Servicio_IngresoSalida;
import Servicios.IngresoSalidas.tabla_IngSal;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Sistema;
import Servicios.TipoMensaje;
import java.awt.FontFormatException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

public class IU_DetalleIngSal extends javax.swing.JFrame implements Constantes {

    public tabla_IngSal tabla;
    int tipo;
    String importe;
    int MaxLineas;
    Servicio_IngresoSalida sis;
    public Clientes Proveedor;
    boolean selec;
    int fila;
    TipoMensaje tm;
    private String Moneda;
    Tipocambio t;
    private String numFactura;
    private Date Fecha;
    private String motivo;
    Sistema sistema;
    IU_FMaestro maestros;
    IU_Descuentos descuentos;
    Editor_Celdas editor;
    JTextField edit_field;
    Servicio_Sistema ser_sis;
    String serie;
    
    private String motivoIngresoCompraLocal;
    private String nroSerieProveedor;
    private String nroDocProveedor;
    
    Control control;

    public IU_DetalleIngSal(int tipo, String motivo) {
        InitOtherComponents();
        this.tipo = tipo;
        tabla = new tabla_IngSal(tipo);
        tabla.setCont(this);
        tabla.setT(t);
        initComponents();
        tb_detalle.setCellSelectionEnabled(true);
//        InitOtherComponents();
        importe = "0.00";
        System.out.println("Tipo : " + tipo);
        lb_tipotrans.setText(tipo == ING_COMPRA_LOCAL ? COMPRA_LOCAL : ( tipo == ING_REGULARIZACION ? INGRESO_POR_AJUSTES : ( tipo == SAL_REGULARIZACION ? SALIDA_POR_AJUSTES : INGRESO_POR_IMPORTACION) ));
        ser_sis = new Servicio_Sistema();
        motivoIngresoCompraLocal = motivo;
        control = new ControlDAO().Obtener_Objeto(1);
        Cambios();
        alinearColumnasDerecha();
    }
    
    private void alinearColumnasDerecha() {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tb_detalle.getColumnModel().getColumn(7).setCellRenderer(tcr);      // Stock antes 4
        tb_detalle.getColumnModel().getColumn(8).setCellRenderer(tcr);      // Precio Costo antes 5
        tb_detalle.getColumnModel().getColumn(9).setCellRenderer(tcr);      // Cantidad antes 6
        tb_detalle.getColumnModel().getColumn(10).setCellRenderer(tcr);      // Dcto 1 antes 7
        tb_detalle.getColumnModel().getColumn(11).setCellRenderer(tcr);      // Dcto 2 antes 8
        tb_detalle.getColumnModel().getColumn(12).setCellRenderer(tcr);      // Dcto 3 antes 9
        tb_detalle.getColumnModel().getColumn(13).setCellRenderer(tcr);     // Dcto 4 antes 10
        tb_detalle.getColumnModel().getColumn(14).setCellRenderer(tcr);     // Total antes 11
    }

    public IU_DetalleIngSal(int tipo, String importeFac, Clientes Proveedor, String moneda, String motivo,
                            String nroSerieProveedor, String nroDocProveedor) { 
        // tipo = 1 Compra local
        InitOtherComponents();
        this.tipo = tipo;
        this.importe = importeFac;
        this.Moneda = moneda;

        tabla = new tabla_IngSal(tipo, importeFac);
        tabla.setCont(this);
        tabla.setT(t);

        initComponents();

        tb_detalle.setCellSelectionEnabled(true);
        lb_proveedor.setText(Proveedor.getNombre());
        lb_tipotrans.setText(COMPRA_LOCAL);
        motivoIngresoCompraLocal = motivo;

        MaxLineas = sis.getMaxLineas();
        Cambios();
        this.Proveedor = Proveedor;
        SetEditor();
        this.nroSerieProveedor = nroSerieProveedor;
        this.nroDocProveedor = nroDocProveedor;
    }

    private void InitOtherComponents() {
        tm = new TipoMensaje();
        sis = new Servicio_IngresoSalida();
        t = sis.getTipoCambio(GregorianCalendar.getInstance().getTime());
        MaxLineas = sis.getMaxLineas();
    }
    
    
    private void ocultarColumnas () {
         //tb_detalle.getColumnModel().getColumn(7).set
    }

    private void SetEditor() {
        edit_field = new JTextField();
        editor = new Editor_Celdas(edit_field);
        editor.setClickCountToStart(2);
        
        for ( int i = 0; i < tabla.getColumnCount() - 1; i++ ) {
            tb_detalle.getColumnModel().getColumn(i).setCellEditor(editor);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lb_prov = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_detalle = new javax.swing.JTable();
        bt_eliminar = new javax.swing.JButton();
        bt_maestro = new javax.swing.JButton();
        bt_imprimir = new javax.swing.JButton();
        bt_descuentos = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        lb_imp = new javax.swing.JLabel();
        lb_tot = new javax.swing.JLabel();
        lb_titulo = new javax.swing.JLabel();
        lb_tipotrans = new javax.swing.JLabel();
        lb_proveedor = new javax.swing.JLabel();
        lb_total = new javax.swing.JLabel();
        lb_importe = new javax.swing.JLabel();
        lb_mensaje = new javax.swing.JLabel();
        lb_glosa1 = new javax.swing.JLabel();
        lb_glosa2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Tipo de Transaccion");

        lb_prov.setText("Proveedor");

        tb_detalle.setModel(tabla);
        tb_detalle.getTableHeader().setReorderingAllowed(false);
        tb_detalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_detalleMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_detalle);

        bt_eliminar.setText("Eliminar");
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });

        bt_maestro.setText("Maestro");
        bt_maestro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_maestroActionPerformed(evt);
            }
        });

        bt_imprimir.setText("Imprimir");
        bt_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imprimirActionPerformed(evt);
            }
        });

        bt_descuentos.setText("Descuentos");
        bt_descuentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_descuentosActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        lb_imp.setText("Importe");

        lb_tot.setText("Total");

        lb_titulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lb_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_titulo.setText("DETALLE DE TRANSACCION");

        lb_tipotrans.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_proveedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_total.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lb_importe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lb_importe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lb_mensaje.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        lb_glosa1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_glosa2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_maestro, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_descuentos, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_imp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_glosa1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_glosa2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lb_importe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lb_total, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                            .addComponent(lb_prov, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lb_tipotrans, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                                .addGap(211, 211, 211))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lb_proveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(lb_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(lb_titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_mensaje, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_tipotrans, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_proveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_prov, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_total, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_maestro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_descuentos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_glosa1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_imp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_importe, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_glosa2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
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

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        if ( tabla.getRowCount() > 0 ) {
            int op = tm.Confirmacion("ADVERTENCIA\nSe perdera todo el contenido, ¿Desea salir?");
            
            if ( op == tm.SI ) {
                dispose();
            }
        } else {
            dispose();
        }
    }//GEN-LAST:event_bt_salirActionPerformed

    private void bt_maestroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_maestroActionPerformed
        tb_detalle.clearSelection();
        
        if ( maestros == null ) {
            maestros = new IU_FMaestro(this, false);
            maestros.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    maestros = null;
                }
            });
            maestros.setMaxLineas(MaxLineas, null);
        } else {
            maestros.setVisible(true);
        }
    }//GEN-LAST:event_bt_maestroActionPerformed

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminarActionPerformed
        if ( selec ) {
            int op = tm.Confirmacion("¿Desea quitar el repuesto de la lista?");
            
            if ( op == tm.SI ) {
                tabla.borrar(fila);
                selec = false;
                tb_detalle.clearSelection();
            }
        } else {
            tm.Error("Ningún repuesto seleccionado");
        }
    }//GEN-LAST:event_bt_eliminarActionPerformed

    private void tb_detalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_detalleMouseClicked
        fila = tb_detalle.rowAtPoint(evt.getPoint());
        selec = true;
    }//GEN-LAST:event_tb_detalleMouseClicked

    private void bt_descuentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_descuentosActionPerformed
        if ( descuentos == null ) {
            descuentos = new IU_Descuentos();
            descuentos.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (descuentos.isAplicar()) {
                        aplicarDct(descuentos.getDes1(), descuentos.getDes2(), descuentos.getDes3(), descuentos.getDes4());
                    }
                    descuentos = null;
                }
            });
        } else {
            descuentos.setVisible(true);
        }
    }//GEN-LAST:event_bt_descuentosActionPerformed

    private boolean hayCantidadMenorIgualCero() {
        boolean cantidad0 = false;
        
        for ( int fila = 0; fila < tb_detalle.getRowCount(); fila++ ) {
            if ( (int) tb_detalle.getValueAt(fila, 9) <= 0 ) {
                cantidad0 = true;
                break;
            }
        }
        return cantidad0;
    }
    
    private int filaConCantidadMayorStock() {
    //private boolean hayCantidadMayorStock() {
        int filaInvalida = -1;
        //boolean cantidad0 = false;
        
        for ( int fila = 0; fila < tb_detalle.getRowCount(); fila++ ) {
            int cant = (int) tb_detalle.getValueAt(fila, 9);
            int stock = (int) tb_detalle.getValueAt(fila, 7);
            
            if ( cant > stock ) {
                //cantidad0 = true;
                filaInvalida = fila + 1;
                return filaInvalida;
                //break;
            }
        }
        //return cantidad0;
        return filaInvalida;
    }
    
    private void bt_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imprimirActionPerformed
        String validacion = validar();
        
        if ( tb_detalle.getRowCount() == 0 ) {
            tm.Error("TABLA VACÍA, Seleccione items");
            
        } else {
            
            if ( hayCantidadMenorIgualCero() ) {
                tm.Error("Ingrese una Cantidad MAYOR a 0 para cada item."); // 1ra validacion para salida

            } else {
                boolean pasar = true;
                if ( tipo == 3 ) {
                    int fila = filaConCantidadMayorStock();
                    System.out.println("fila:" + fila);
                    if ( fila != -1 ) {
                        pasar = false;
                        tm.Error("Ingrese Cantidad MENOR o IGUAL a STOCK en item N° " +  fila);
                    } 
                }
                
                System.out.println("pasar:" + pasar);
                System.out.println("validacion.equals(tm.VALIDO)::" + validacion.equals(tm.VALIDO));
                if ( pasar ) {
                    if ( validacion.equals(tm.VALIDO) ) {
                        int opcion = tm.Confirmacion(tm.PREGUNTA_OPERACION);

                        if ( opcion == tm.SI ) {
                            try {
                                switch ( tipo ) {
                                    case ING_COMPRA_LOCAL:
                                        GuardarCompraLocal();
                                        break;
                                    case ING_REGULARIZACION:
                                    case SAL_REGULARIZACION:
                                    case ING_IMPORTACION:
                                        GuardarIngSalVarias();
                                        break;
                                }
                                System.out.println("tipo:" + tipo);
                                ImprimirReporte(tipo);

                            } catch ( BiffException ex ) {
                                Logger.getLogger(IU_DetalleIngSal.class.getName()).log(Level.SEVERE, null, ex);

                            } catch ( IOException ex ) {
                                Logger.getLogger(IU_DetalleIngSal.class.getName()).log(Level.SEVERE, null, ex);

                            } catch ( WriteException ex ) {
                                Logger.getLogger(IU_DetalleIngSal.class.getName()).log(Level.SEVERE, null, ex);

                            } catch ( ParseException ex ) {
                                Logger.getLogger(IU_DetalleIngSal.class.getName()).log(Level.SEVERE, null, ex);

                            } catch ( FontFormatException ex ) {
                                Logger.getLogger(IU_DetalleIngSal.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    } else {
                        tm.Error(validacion);
                    }
                }
            }
        }
    }//GEN-LAST:event_bt_imprimirActionPerformed

    private void aplicarDct(double d1, double d2, double d3, double d4) {
        tabla.aplicarDctos(d1, d2, d3, d4);
    }

    private ArrayList SetLista() {
        ArrayList<DetalleIngSal> lista = new ArrayList<>();
        
        for ( int i = 0; i < tb_detalle.getRowCount(); i++ ) {
            String nroParte = String.valueOf(tabla.getValueAt(i, 4)); //antes 1
            
            String codSecundario = String.valueOf(tabla.getValueAt(i, 5));   //antes 2
            String codSec = ( "null".equals(codSecundario) ? "" : codSecundario);
            
            String equipo = String.valueOf(tabla.getValueAt(i, 1));
            String marca = String.valueOf(tabla.getValueAt(i, 2));
            String modelo = String.valueOf(tabla.getValueAt(i, 3));
            
            String descripcion = String.valueOf(tabla.getValueAt(i, 6));    //antes 3
            String cantidad = String.valueOf(tabla.getValueAt(i, 9));          //antes 6

            String precioLista = String.valueOf(tabla.getValueAt(i, 8));        //antes 5
            String total = String.valueOf(tabla.getValueAt(i, 14));             //antes 11
            
            String descrModeloText = String.valueOf(tabla.getValueAt(i, 15));   //antes 12
            String descrModelo = ( "null".equals(descrModeloText) ? "" : descrModeloText);
            
//            lista.add(new DetalleIngSal(nroParte, codSec, descripcion, descrModelo, cantidad, precioLista, total));
            lista.add(new DetalleIngSal(nroParte, 
                                        codSec,
                                        descripcion, cantidad, precioLista, total, descrModelo, equipo, marca, modelo));
        }
        return lista;
    }

    private Map SetParametros(Control control) {
        Map<String, Object> mapa = new HashMap<>();
        String ultnumero = "";
        ser_sis = new Servicio_Sistema();
        
//        String empresa = new ControlDAO().Obtener_Objeto(1).getNombrempresa();
        String empresa = control.getNombrempresa();
        mapa.put("titulo", tipo == ING_COMPRA_LOCAL ? "Ingresos al Almacén" : (tipo == ING_REGULARIZACION ? "Ingresos al Almacén" : (tipo == SAL_REGULARIZACION ? "Salidas del Almacén" : "Ingresos Importación")));
        mapa.put("empresa", empresa);
        mapa.put("transaccion", lb_tipotrans.getText());
        
        mapa.put("rutaProgramas", control.getRutaprogramas());
        mapa.put("rutaTemplate", control.getRutareportes());
        
        Sistema sistema = null;
        if ( tipo == ING_COMPRA_LOCAL ) { // Ingreso por Compra Local
//            ultnumero = String.valueOf(ser_sis.Obtener_Sistema_TipoDoc("11").getUltimonumero() + 1);
            sistema = ser_sis.Obtener_Sistema_TipoDoc(TIPODOC_ING_COMPRA_LOCAL);
            ultnumero = String.valueOf(sistema.getUltimonumero());
            
//            mapa.put("nroDoc", ""); // SE DEBERÍA BORRAR, PUESTO K NO SE SETEA EL NROINGRESO para Compra Local
            // N° Factura o documento dentro de la Empresa(que compra SIAR)
            mapa.put("nroserie", formatearCeros(String.valueOf(sistema.getSerie()), 3));
            mapa.put("nroDoc", ultnumero);
            
            // N° Factura o documento del proveedor
            mapa.put("nroSerieProv", formatearCeros(nroSerieProveedor, 3));
            mapa.put("nroDocProv", nroDocProveedor);
            mapa.put("proveedor", Proveedor == null ? " " : Proveedor.getNombre());
            
        } else if ( tipo == ING_REGULARIZACION ) { // Ingresos por Ajuste
            sistema = ser_sis.Obtener_Sistema_TipoDoc(TIPODOC_ING_REGULARIZACION);
            ultnumero = String.valueOf(sistema.getUltimonumero());
//            ultnumero = String.valueOf(num);
            System.out.println("ultimo num:" + ultnumero);
            mapa.put("nroDoc", ultnumero);
            
        } else if ( tipo == SAL_REGULARIZACION ) { //Salidas por Ajuste
            sistema = ser_sis.Obtener_Sistema_TipoDoc(TIPODOC_SAL_REGULARIZACION);
            ultnumero = String.valueOf(sistema.getUltimonumero());
//            ultnumero = String.valueOf(ser_sis.Obtener_Sistema_TipoDoc("12").getUltimonumero() + 1);
            mapa.put("nroDoc", ultnumero);
        } else if ( tipo == ING_IMPORTACION ) { // Ingresos por Importacion (nuevo jsp)
            sistema = ser_sis.Obtener_Sistema_TipoDoc(TIPODOC_ING_IMPORTACION);
            ultnumero = String.valueOf(sistema.getUltimonumero());
//            ultnumero = String.valueOf(num);
            System.out.println("ultimo num:" + ultnumero);
            mapa.put("nroDoc", ultnumero);
        }
        
        System.out.println("ultimo num: xxxxxx " + ultnumero);        
        mapa.put("motivo", motivoIngresoCompraLocal);
        mapa.put("glosa", lb_glosa1.getText());
        mapa.put("total", lb_total.getText());
        return mapa;
    }
    
    private static String formatearNroDoc(String numSerie, String numCorrelativo) {
        return formatearCeros(numSerie, 3) + "-" + formatearCeros(numCorrelativo, 8);
    }
    
    private static String formatearCeros(String numero, int cantidadDigitos) {
        String formateado = numero;
        int longitudCeros = cantidadDigitos - numero.length();
        
        for ( int i = 0; i < longitudCeros; i++ ) {
            formateado = "0" + formateado;
        }
        return formateado;
    }

    private void ImprimirReporte(int tipo) throws BiffException, IOException, FileNotFoundException, WriteException, ParseException, FontFormatException {
        Control control = new ControlDAO().Obtener_Objeto(1);
        String rutaBDIngSal = control.getRutaprogramas();
        
        String rutaExcel = rutaBDIngSal.replace("/", "\\");
        String excelIngSalReg = "", excelIngCompraLocal = "";
        
        Map<String, Object> parametros = SetParametros(control);
        ArrayList<DetalleIngSal> lista = SetLista();
        
        switch ( tipo ) {
            case ING_COMPRA_LOCAL :
//                Servicio_Impresion.exporta(28, lista, parametros, "plantillas/Ingresos_Por_Compra_Local.pdf");
                excelIngCompraLocal = new Servicio_Excel(tb_detalle, this).generarExcelDocumento(parametros, lista);
                excelIngCompraLocal = rutaExcel + "\\" + excelIngCompraLocal;
                new ImpresionExcel().imprimirDesdeExcel(excelIngCompraLocal);
                break;
                
            case ING_REGULARIZACION :
            case SAL_REGULARIZACION :
            case ING_IMPORTACION :
                
                System.out.println("Entro a rutina de impresion In x Importacion...");
//                Servicio_Impresion.exporta(4, lista, parametros, "plantillas/Ingresos_Y_Salidas_Por_Ajustes.pdf");
                excelIngSalReg = new Servicio_Excel(tb_detalle, this).generarExcelDocumento(parametros, lista, tipo);
                excelIngSalReg = rutaExcel + "\\" + excelIngSalReg;
                new ImpresionExcel().imprimirDesdeExcel(excelIngSalReg);
                break;
        }
        dispose();
    }

    private void GuardarCompraLocal() {
        Cabeces cab = new Cabeces();
        sistema = sis.getSis("Ingreso por Compra");
        String numSerie = String.valueOf(sistema.getSerie());
        cab.setId(new CabecesId(COMPRA, sistema.getTipodoc(), numSerie, numFactura));//sucursal por ahora 001
        cab.setClientes(Proveedor);
        cab.setTotal(Double.parseDouble(lb_total.getText()));
        cab.setTipocambio(t);
        cab.setTipocambio_1(t.getValorventa());
        cab.setMoneda(Moneda);
        ArrayList<DetalleFactura> detallesFactura = tabla.getDetalles();
        ArrayList<Detallees> detalles = SetDetalles(detallesFactura, cab);
        
        if ( sis.guardarCompraLocal(detalles, cab) ) {
//        int ultNum = sistema.getUltimonumero();
//        sistema.setUltimonumero(ultNum);
//        sis.actualizarSistema(sistema);
            tm.Mensaje("ÉXITO EN LA OPERACIÓN");
            
        } else {
            tm.Mensaje("ERROR EN LA OPERACIÓN");
        }
    }

    private void GuardarIngSalVarias() {
        System.out.println("tipo:" + tipo);
        switch ( tipo ) {
            case ING_REGULARIZACION:
                sistema = sis.getSis(INGRESO_POR_REGULARIZACION);
                
                System.out.println("Que tiene en sistema 1111 : " + sistema);
//                System.out.println("sistema(GuardarIngSalVarias):" + sistema);
                if ( sistema != null ) {
                    System.out.println("ultimo numero desde BD:::" + sistema.getUltimonumero());
                }
                
                if ( sis.guardarIngreso(SetDetalles(tabla.getDetalles())) ) {
                    sis.actualizarSistema(sistema);
                    System.out.println("ultimo numero despues de ACTUALIZADO:" + sistema.getUltimonumero());
                    tm.Mensaje("ÉXITO EN LA OPERACIÓN");
                    
                } else {
                    System.out.println("Error INGRESO GuardarIngSalVarias(IU_DetalleIngSal.java)");
                    tm.Mensaje("ERROR EN LA OPERACIÓN");
                }
                break;
                
            case SAL_REGULARIZACION:
                sistema = sis.getSis(SALIDA_POR_REGULARIZACION);
                System.out.println("case SAL_REGULARIZACION");
                
                if ( sis.guardarSalida(SetDetalles(tabla.getDetalles())) ) {
                    sis.actualizarSistema(sistema);
                    tm.Mensaje("ÉXITO EN LA OPERACIÓN");
                    
                } else {
                    System.out.println("Error SALIDA al guardar SAL_REGULARIZACION(IU_DetalleIngSal.java)...");
                    tm.Mensaje("ERROR EN LA OPERACIÓN");
                }
                break;
                
            case ING_IMPORTACION:
                sistema = sis.getSis(INGRESO_POR_IMPORTACION);
//                System.out.println("sistema(GuardarIngSalVarias):" + sistema);
                
                if ( sistema != null ) {
                    System.out.println("ultimo numero desde BD:::" + sistema.getUltimonumero());
                }
                
                if ( sis.guardarIngreso(SetDetalles(tabla.getDetalles())) ) {
                    sis.actualizarSistema(sistema);
                    System.out.println("ultimo numero despues de ACTUALIZADO:" + sistema.getUltimonumero());
                    tm.Mensaje("ÉXITO EN LA OPERACIÓN");
                    
                } else {
                    System.out.println("Error INGRESO GuardarIngSalVarias(IU_DetalleIngSal.java)");
                    tm.Mensaje("ERROR EN LA OPERACIÓN");
                }
                break;
                
        }
    }

    public void setNumFactura(String numFactura) {
        this.numFactura = numFactura;
    }

    private ArrayList<Detallees> SetDetalles(ArrayList<DetalleFactura> detallesFactura) {
        ArrayList<Detallees> d = new ArrayList();
        System.out.println("Numero de nota ingreso x importacion : " + sistema.getUltimonumero());
        
        int nroIngreso = sistema.getUltimonumero() + 1;
        
        for ( DetalleFactura df : detallesFactura ) {
            Detallees detalle = df.getD();
            detalle.setFecha(Fecha);
            detalle.setOperaciones(sistema.getOperaciones());
            detalle.setNroingreso(nroIngreso);
            d.add(detalle);
            detalle.setMotivo(motivo);
        }
        sistema.setUltimonumero(nroIngreso); // ACTUALIZA SISTEMA para "Ingresos por Regularización"
        return d;
    }

    // En este caso no existe aumento del N° documento en tabla Sistema ????
    private ArrayList<Detallees> SetDetalles(ArrayList<DetalleFactura> detallesFactura, Cabeces cabe) {
        ArrayList<Detallees> d = new ArrayList();
        
        for ( DetalleFactura df : detallesFactura ) {
            Detallees detalle = df.getD();
            detalle.setCabeces(cabe);
            detalle.setPreciolista(df.getPrecioRepuesto());
            detalle.setOperaciones(sis.getOperacion("IA"));
            detalle.setClientes(Proveedor);
            detalle.setMotivo(motivo);
            detalle.setFecha(Fecha);  
            d.add(detalle);
        }
//        SE AGREGÓ DESPUÉS
//            sistema.setUltimonumero(sistema.getUltimonumero() + 1); // ACTUALIZA SISTEMA para "Ingresos por Regularización"
        return d;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public final void Cambios() {
        lb_total.setText("0.00");
        
        if ( tipo == ING_COMPRA_LOCAL ) {//compras locales
            lb_importe.setText(String.valueOf(importe));
            
            if ( Moneda.equals("2") ) {
                lb_glosa1.setText("$");
                lb_glosa2.setText("$");
            } else {
                lb_glosa1.setText("S/");
                lb_glosa2.setText("S/");
            }
            
        } else {
            inicializarGlosa();
            if ( tipo == ING_REGULARIZACION || tipo == ING_IMPORTACION) { // compras varias
                lb_prov.setVisible(false);
                lb_proveedor.setVisible(false);
                lb_imp.setVisible(false);
                lb_glosa1.setVisible(true);
                
                lb_importe.setVisible(false);
                lb_glosa2.setVisible(false);
            
            } else if ( tipo == SAL_REGULARIZACION ) {//salida varias   
                lb_prov.setVisible(false);
                lb_proveedor.setVisible(false);
                lb_imp.setVisible(false);
                lb_glosa1.setVisible(true);
                
                lb_importe.setVisible(false);
                lb_glosa2.setVisible(false);
            }
        }
    }
    
    private void inicializarGlosa() {
        int moneda = control.getMonedarepuestos();
        
        if ( moneda == DOLAR_COMBO ) {
            lb_glosa1.setText(DOLAR_STANDARD);
            lb_glosa2.setText(DOLAR_STANDARD);
            
        } else if ( moneda == SOL_COMBO ) {
            lb_glosa1.setText(SOL);
            lb_glosa2.setText(SOL);
        }
    }

    private String validar() {
        if ( tabla.getRowCount() <= 0 ) {
            return tm.TABLA_VACIA;
        }
        double fac;
        double total = Double.parseDouble(lb_total.getText());
        
        if ( lb_importe.isVisible() ) {
            fac = Double.parseDouble(lb_importe.getText());
            double res = fac - total;

            if ( Math.abs(res) > 1 ) {
                return "NO CONCUERDA EL IMPORTE DE LA FACTURA CON EL TOTAL";
            }
        }
        return tm.VALIDO;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }
    
    public int getTipo() {
        return tipo;
    }
    
    public String getMotivoIngresoCompraLocal() {
        return motivoIngresoCompraLocal;
    }

    public void setMotivoIngresoCompraLocal(String motivoIngresoCompraLocal) {
        this.motivoIngresoCompraLocal = motivoIngresoCompraLocal;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_descuentos;
    private javax.swing.JButton bt_eliminar;
    private javax.swing.JButton bt_imprimir;
    private javax.swing.JButton bt_maestro;
    private javax.swing.JButton bt_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_glosa1;
    private javax.swing.JLabel lb_glosa2;
    private javax.swing.JLabel lb_imp;
    public javax.swing.JLabel lb_importe;
    public javax.swing.JLabel lb_mensaje;
    private javax.swing.JLabel lb_prov;
    private javax.swing.JLabel lb_proveedor;
    private javax.swing.JLabel lb_tipotrans;
    public javax.swing.JLabel lb_titulo;
    private javax.swing.JLabel lb_tot;
    public javax.swing.JLabel lb_total;
    private javax.swing.JTable tb_detalle;
    // End of variables declaration//GEN-END:variables
}