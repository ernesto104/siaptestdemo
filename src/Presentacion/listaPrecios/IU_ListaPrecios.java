package Presentacion.listaPrecios;

import Entidades.Control;
import static Entidades.Otros.Constante.CORREO_EMPRESA;
import static Entidades.Otros.Constante.DIRECCION_LISTA_PRECIOS;
import static Entidades.Otros.Constante.NOMBRE_EMPRESA;
import Entidades.Repuestos;
import Entidades.Tipocambio;
import Mantenimiento.ControlDAO;
import Mantenimiento.TipoCambioDAO;
import Presentacion.RepuestosSegunEstratificacion.IU_DatosRepuesto;
import Servicios.Comision.Validar_Mayusculas;
import Servicios.Constantes;
import Servicios.Importaciones.Servicio_Control;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Maestros;
import Servicios.TipoMensaje;
import Servicios.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author CRS, LRC
 */
public class IU_ListaPrecios extends javax.swing.JFrame implements Constantes {

    DefaultTableModel dtm;
    Servicio_Maestros servicio_maestro;
    Servicio_Excel excel;
    Tipocambio tipoCambio;
    TipoMensaje tm;
    int TipoOpcion;
    String Opcion;
    int TipoMoneda;
    int TipoStock;

    Util util;
    private String marca;

    public IU_ListaPrecios(int tipo, String opcion, int moneda, int stock) {
        tm = new TipoMensaje();
        TipoOpcion = tipo;
        TipoMoneda = moneda;
        TipoStock = stock;
//        this.opcion = opcion;
        Opcion = opcion;
        tipoCambio = new TipoCambioDAO().Obtener_Objeto(Calendar.getInstance().getTime());
        servicio_maestro = new Servicio_Maestros(null);
        initComponents();
        dtm = (DefaultTableModel) tb_repuestos.getModel();
        
        List Lista_Repuestos = servicio_maestro.ListarRepuestos_Opciones(TipoOpcion, Opcion, TipoStock);
        ListarRepuestos(Lista_Repuestos);
        setLocationRelativeTo(null);
        setVisible(true);
        setAnchoColumnas();
        lb_moneda.setText(TipoMoneda == 2 ? "SOLES" : "DOLARES");
        excel = new Servicio_Excel(tb_repuestos, this);

        tx_descripcion.setDocument(new Validar_Mayusculas(tx_descripcion, 30));
        tx_marca.setDocument(new Validar_Mayusculas(tx_marca, 15));
        tx_nroParte.setDocument(new Validar_Mayusculas(tx_nroParte, 20));
        alinearDerecha();
        ocultarMarca();
        ocultarColumnasAplAnot();
    }
    
    private void ocultarColumnasAplAnot() {
        ocultarColumna(7);
        ocultarColumna(8);
        ocultarColumna(9);
        ocultarColumna(10);
    }
    
    private void ocultarColumna(int numeroColumna) {
        tb_repuestos.getColumnModel().getColumn(numeroColumna).setMaxWidth(0);
        tb_repuestos.getColumnModel().getColumn(numeroColumna).setMinWidth(0);
        tb_repuestos.getColumnModel().getColumn(numeroColumna).setPreferredWidth(0);
    }
    
    private void ocultarMarca() {
//        tx_marca.setVisible(false);
//        jLabel5.setVisible(false);
    }
    
    private void alinearDerecha() {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tb_repuestos.getColumnModel().getColumn(5).setCellRenderer(tcr);
        tb_repuestos.getColumnModel().getColumn(6).setCellRenderer(tcr);
//        tb_repuestos.getColumnModel().getColumn(6).setCellRenderer(tcr);
    }

    private void ListarRepuestos(List lista_repuestos) {
        util = new Util();
        int i = 1;
        for ( Iterator it = lista_repuestos.iterator(); it.hasNext(); ) {
            Repuestos r = (Repuestos) it.next();
            String codigoseg = ( r.getCodigoseg() == null ? "" : r.getCodigoseg());            
            String descripcion = r.getDescripcion(); // + " " + descrModelo;
            
            //String descrModelo = ( r.getDescrmodelo() == null ? "" : r.getDescrmodelo());
            String aplicacion1 = ( r.getDescrmodelo() == null ? "" : r.getDescrmodelo()); // APLICACIÓN 1(descrModelo-BD)  + APLICACIÓN 2 (aplicacion-BD)
            String aplicacion2 = ( r.getAplicacion() == null ? "" : r.getAplicacion());
            String descrModelo = aplicacion1 + " " + aplicacion2;
            
            String anotacion1 = ( r.getDesclista() == null ? "" : r.getDesclista() );
            String anotacion2 = ( r.getDesclista2()== null ? "" : r.getDesclista2());
            String anotaciones = anotacion1 + " " + anotacion2; // ANOTACIÓN 1 + ANOTACIÓN 2
            
            String marca = ( r.getMarca() == null ? "" : r.getMarca() );
            int stock =  r.getStock();
//            String stock = ( r.getStock() == null ? "" : formatearMonetario(String.valueOf(r.getStock()), 0) );            
//            String descrLista2 = ( r.getDesclista2() == null ? "" : r.getDesclista2() );
//            String anotaciones = descrLista + " " + descrLista2;
            
//            String aplicacion = 
//                    "<html>" + descrModelo +
//                        "<br>" + descrLista + 
//                    "</html>";
//            String blanco1 = "";
//            String blanco2 = "";
//            String blanco3 = "";
//            String blanco4 = "";            
            
            Object[] fila = {
//                i++, // N° = Item
//                r.getLineas().getIdlinea(),
//                r.getLineas().getDescripcion(),
                r.getCodrepuesto(),
                codigoseg,
                descripcion,
                descrModelo,
                anotaciones,
//                anotaciones,
//                r.getAplicacion(),
//                r.getCodigoseg(),
//                r.getDescripcion(),
//                formatearMonetario(String.valueOf(r.getCostopromedio()), 2),
                marca,
                formatearMonetario(String.valueOf(r.getPreciolista()), 2),
   
// jsp 29.03.2023                
               aplicacion1,
               aplicacion2,
               anotacion1,
               anotacion2,
//                r.getStock()
//  hasta aqui                
                
                stock
//                TipoMoneda == 2 ? (Math.round(r.getPreciolista() * tipoCambio.getValorventa() * 100.0) / 100.0) : r.getPreciolista(),
//                r.getMarca()
//                blanco1,
//                blanco2,
//                blanco2,
//                blanco4
            };
//            System.out.println("CP:" + r.getCostopromedio());
//            System.out.println("PL:" + r.getPreciolista());
            dtm.addRow(fila);
        }
    }
    
//    public double roundTwoDecimals(double d) {
//        DecimalFormat twoDForm = new DecimalFormat("#,##");
//        return Double.valueOf(twoDForm.format(d));
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelListaPrecios = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_repuestos = new javax.swing.JTable();
        bt_Datos = new javax.swing.JButton();
        bt_excel = new javax.swing.JButton();
        bt_imprimir = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tx_descripcion = new javax.swing.JTextField();
        tx_nroParte = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tx_marca = new javax.swing.JTextField();
        bt_filtrar = new javax.swing.JButton();
        lb_moneda = new javax.swing.JLabel();
        bt_limpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LISTA DE PRECIOS");

        tb_repuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NRO PARTE", "CODIGO SEG", "DESCRIPCIÓN", "APLICACIÓN", "ANOTACIÓN", "MARCA", "PRECIO", "Aplicacion1", "Aplicacion2", "Anotacion1", "Anotacion2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_repuestos.setRowHeight(32);
        tb_repuestos.getTableHeader().setReorderingAllowed(false);
        tb_repuestos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_repuestosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_repuestos);
        if (tb_repuestos.getColumnModel().getColumnCount() > 0) {
            tb_repuestos.getColumnModel().getColumn(0).setHeaderValue("NRO PARTE");
            tb_repuestos.getColumnModel().getColumn(1).setHeaderValue("CODIGO SEG");
            tb_repuestos.getColumnModel().getColumn(2).setHeaderValue("DESCRIPCIÓN");
            tb_repuestos.getColumnModel().getColumn(3).setHeaderValue("APLICACIÓN");
            tb_repuestos.getColumnModel().getColumn(4).setHeaderValue("ANOTACIÓN");
            tb_repuestos.getColumnModel().getColumn(5).setHeaderValue("MARCA");
            tb_repuestos.getColumnModel().getColumn(6).setHeaderValue("PRECIO");
            tb_repuestos.getColumnModel().getColumn(7).setHeaderValue("Aplicacion1");
            tb_repuestos.getColumnModel().getColumn(8).setHeaderValue("Aplicacion2");
            tb_repuestos.getColumnModel().getColumn(9).setHeaderValue("Anotacion1");
            tb_repuestos.getColumnModel().getColumn(10).setHeaderValue("Anotacion2");
        }

        bt_Datos.setText("Mas Datos");
        bt_Datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_DatosActionPerformed(evt);
            }
        });

        bt_excel.setText("Exportar Excel");
        bt_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_excelActionPerformed(evt);
            }
        });

        bt_imprimir.setText("Imprimir");
        bt_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imprimirActionPerformed(evt);
            }
        });

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtrar Por", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel3.setText("Numero Parte");

        jLabel4.setText("Descripcion");

        tx_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Filtro_Repuestos(evt);
            }
        });

        tx_nroParte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Filtro_Repuestos(evt);
            }
        });

        jLabel5.setText("Marca");

        tx_marca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Filtro_Repuestos(evt);
            }
        });

        bt_filtrar.setText("Filtrar");
        bt_filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_filtrarActionPerformed(evt);
            }
        });

        lb_moneda.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lb_moneda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        bt_limpiar.setText("Limpiar Filtro");
        bt_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tx_nroParte, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(88, 88, 88)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
                        .addComponent(lb_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(bt_filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_nroParte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tx_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(40, Short.MAX_VALUE))
                    .addComponent(lb_moneda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_filtrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelListaPreciosLayout = new javax.swing.GroupLayout(panelListaPrecios);
        panelListaPrecios.setLayout(panelListaPreciosLayout);
        panelListaPreciosLayout.setHorizontalGroup(
            panelListaPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelListaPreciosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelListaPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelListaPreciosLayout.createSequentialGroup()
                        .addComponent(bt_Datos, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_excel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelListaPreciosLayout.setVerticalGroup(
            panelListaPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelListaPreciosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelListaPreciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_Datos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_excel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelListaPrecios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelListaPrecios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void bt_filtrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_filtrarActionPerformed
        String nroParte = tx_nroParte.getText();
        String descripcion = tx_descripcion.getText();
        String marca = tx_marca.getText();
        BorrarTabla();
        List lista = servicio_maestro.FiltrarLista_Repuestos(TipoOpcion, Opcion, TipoStock, nroParte, descripcion, marca);
        ListarRepuestos(lista);
    }//GEN-LAST:event_bt_filtrarActionPerformed

    private void Filtro_Repuestos(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Filtro_Repuestos
        if ( evt.getKeyChar() == 10 ) {
            bt_filtrar.doClick();
        }
    }//GEN-LAST:event_Filtro_Repuestos

    private void bt_DatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_DatosActionPerformed
        int fila = tb_repuestos.getSelectedRow();
        if ( fila >= 0 ) {
            Repuestos r = servicio_maestro.getRepuesto_CodRep((String.valueOf(dtm.getValueAt(fila, 0))));
            IU_DatosRepuesto datos = new IU_DatosRepuesto(r, 1);
            
        } else {
            tm.Error(tm.SELECCION_REGISTRO);
        }
    }//GEN-LAST:event_bt_DatosActionPerformed

    private void tb_repuestosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_repuestosMouseClicked
        if ( evt.getClickCount() == 2 ) {
            bt_Datos.doClick();
        }
    }//GEN-LAST:event_tb_repuestosMouseClicked

    private void bt_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excelActionPerformed
//        excel.Exportar_Excel();
        List listaCabecera = new ArrayList();
//        listaCabecera.add("ITEM"); // N°
//        listaCabecera.add("LINEA");
        listaCabecera.add("NRO PARTE");
        listaCabecera.add("COD.SEC.");        
        listaCabecera.add("DESCRIPCIÓN");
        listaCabecera.add("APLICACIÓN (JUNTOS)"); // MODELO
//        listaCabecera.add("ANOTACIONES");
        listaCabecera.add("ANOTACIÓN");

//        listaCabecera.add("COSTO UNIT.");
        listaCabecera.add("MARCA");
        listaCabecera.add("PRECIO");
//        listaCabecera.add("MARCA");
        listaCabecera.add("APLICACION1");
        listaCabecera.add("APLICACION2");
        listaCabecera.add("ANOTACION1");
//        listaCabecera.add("ANOTACION2");
//        listaCabecera.add("STOCK");        

        excel.exportarExcel("Lista de Precios", listaCabecera, 1);
    }//GEN-LAST:event_bt_excelActionPerformed

    private void bt_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imprimirActionPerformed
        TipoMensaje tm = new TipoMensaje();
        
        if ( tm.Confirmacion(tm.PREGUNTA_OPERACION) == tm.SI ) {
            HashMap parametros = SetParametros();
            ArrayList listado = SetLista();
            
            Servicio_Control sc = new Servicio_Control();
            String rutaReportes = sc.getControlUnico().getRutareportes();
//            String url1 = rutaReportes + "\\listaPrecios\\listaPrecios.jrxml";
//            String url2 = rutaReportes + "\\listaPrecios\\listaPrecios.jasper";
            String url1 = rutaReportes + "\\plantillas\\listaPrecios.jrxml";
            String url2 = rutaReportes + "\\plantillas\\listaPrecios.jasper";
            
            try {
                System.out.println("Listado : " + listado);
                convertPedidoToPDF(url1, url2, parametros, listado);

            } catch ( Exception ex ) {
                System.out.println("Error(imprimirListaPrecios):" + ex.getMessage());
            }
//            String ruta = new ControlDAO().Obtener_Objeto(1).getRutareportes();
//            Servicio_Impresion.exporta(2, lista, parametros, "plantillas/Lista_Precio.pdf");
        }
    }//GEN-LAST:event_bt_imprimirActionPerformed

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
    
    private void bt_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limpiarActionPerformed
        tx_descripcion.setText("");
        tx_marca.setText("");
        tx_nroParte.setText("");
        BorrarTabla();
        List lista = servicio_maestro.FiltrarLista_Repuestos(TipoOpcion, Opcion, TipoStock, "", "", "");
        ListarRepuestos(lista);
    }//GEN-LAST:event_bt_limpiarActionPerformed

    private ArrayList SetLista() {
        ArrayList<ListaPrecio> lista = new ArrayList<>();
        for (int i = 0; i < tb_repuestos.getRowCount(); i++) {
//            String linea = String.valueOf(dtm.getValueAt(i, 1));
//            String item = String.valueOf(i+1);
                System.out.println("i, 0 " + dtm.getValueAt(i, 0));
                System.out.println("i, 1 " + dtm.getValueAt(i, 1));
                System.out.println("i, 2 " + dtm.getValueAt(i, 2));
                System.out.println("i, 3 " + dtm.getValueAt(i, 3));
                System.out.println("i, 4 " + dtm.getValueAt(i, 4));
                System.out.println("i, 5 " + dtm.getValueAt(i, 5));
                System.out.println("i, 6 " + dtm.getValueAt(i, 6));
                System.out.println("i, 7 " + dtm.getValueAt(i, 7));
                System.out.println("i, 8 " + dtm.getValueAt(i, 8));
                System.out.println("i, 9 " + dtm.getValueAt(i, 9));
                System.out.println("i, 10 " + dtm.getValueAt(i, 10));
                
                System.out.println("-----------------");

            String nroParte = String.valueOf(dtm.getValueAt(i, 0));
            String codigoSeg = String.valueOf(dtm.getValueAt(i, 1));
            
            String descripcion = String.valueOf(dtm.getValueAt(i, 2));
            
            String descrModelo = String.valueOf(dtm.getValueAt(i, 3));  // Aplicación 1 + Aplicació 2
            String anotaciones = String.valueOf(dtm.getValueAt(i, 4));  // Anotación 1 + Anotación 2
            
            //System.out.println("anotaciones(" + anotaciones + ")???" + ( " ".equals(anotaciones) ));
            /*
            if ( !" ".equals(anotaciones) ) {
                descrModelo = descrModelo + "<br/>" + anotaciones;
            }
            */
            
            String aplicacion1  = String.valueOf(dtm.getValueAt(i, 7));
            String aplicacion2  = String.valueOf(dtm.getValueAt(i, 8));
            String anotacion1   = String.valueOf(dtm.getValueAt(i, 9));
            String anotacion2   = String.valueOf(dtm.getValueAt(i, 10));
            descrModelo = aplicacion1;
            //System.out.println("COD.REPUESTO -> " + nroParte);
            if ( !"".equals(aplicacion2) && !" ".equals(aplicacion2) ) {
                //System.out.println("...aplicacion2:" + aplicacion2);
                descrModelo = descrModelo + "<br/>" + aplicacion2;
            } 
//            else {
//                System.out.println("...aplicacion2 VACIA");
//            }
            
            if ( !"".equals(anotacion1)  && !" ".equals(anotacion1) ) {
                //System.out.println("...anotacion1:" + anotacion1);
                descrModelo = descrModelo + "<br/>" + anotacion1;
            }
//            else {
//                System.out.println("...anotacion1 VACIA");
//            }
            
            if ( !"".equals(anotacion2) && !" ".equals(anotacion2) ) {
                //System.out.println("...anotacion2:" + anotacion2);
                descrModelo = descrModelo + "<br/>" + anotacion2;
            }
//            else {
//                System.out.println("...anotacion2 VACIA");
//            }
            
            //System.out.println("Resultado --> descrModelo:" + descrModelo);
//            String stock = String.valueOf(dtm.getValueAt(i, 5));
            
//            String costUni = formatearMonetario(String.valueOf(dtm.getValueAt(i, 6)), 2);
//            String costo = TipoMoneda == 2 ? GLOSA_SOLES + " " + costUni : GLOSA_DOLARES + " " + costUni;
            
            String marca = String.valueOf(dtm.getValueAt(i, 5));
            if ( "null".equals(marca) ) {
                marca = "";
            }
            //System.out.println("MARCA:" + String.valueOf(dtm.getValueAt(i, 4)));
            
            String p = formatearMonetario(String.valueOf(dtm.getValueAt(i, 6)), 2);
            String precio = TipoMoneda == 2 ? GLOSA_SOLES + " " + p : GLOSA_DOLARES + " " + p;
            lista.add(new ListaPrecio(
//                    item, 
                    nroParte, codigoSeg, descripcion, descrModelo, 
                    // anotaciones, 
                    /*stock, costo, */precio, marca));
        }
        return lista;
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

    private HashMap SetParametros() {
        HashMap mapa = new HashMap<>();
        Control control = new ControlDAO().Obtener_Objeto(1);
        String empresa = control.getNombrempresa();
//        String direccion = control.getNombrealmacen();
//        String telefonos = control.getRutaprogramas(); // SE TOMA EL CAMPO DE CORREO DE BD, DADO QUE NO EXISTE CAMPO "TELEFONOS"
//        mapa.put("empresa", empresa);
        mapa.put("empresa", NOMBRE_EMPRESA);
//        mapa.put("direccion", direccion);
        
        mapa.put("direccion", DIRECCION_LISTA_PRECIOS);
        mapa.put("correo", CORREO_EMPRESA);
//        mapa.put("telefonos", telefonos);
//        mapa.put("moneda", TipoMoneda == 2 ? "SOLES" : "DOLARES");
        mapa.put("total", String.valueOf(tb_repuestos.getRowCount()));
        return mapa;
    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tb_repuestos.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tb_repuestos.getColumnModel();
        TableColumn columnaTabla;
        
        for ( int i = 0; i < tb_repuestos.getColumnCount(); i++ ) {
            columnaTabla = modeloColumna.getColumn(i);
            switch ( i ) {
//                case 0: // ITEM
//                    anchoColumna = (4 * ancho) / 100;
//                    break;
                case 0: // NRO PARTE
//                    anchoColumna = (16 * ancho) / 100;
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 1: // CODIGO SECUNDARIO
//                    anchoColumna = (8 * ancho) / 100;
                    anchoColumna = (6 * ancho) / 100;
                    break;

                case 2: // DESCRIPCION
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 3: // APLICACIÓN 1 y 2 (descrModelo, aplicacion en BD)
                    anchoColumna = (20 * ancho) / 100;
                    break;
                case 4: // ANOTACIÓN 1 y 2 (desclista, desclista2 en BD)
                    anchoColumna = (20 * ancho) / 100;
                    break;
//                case 5: // STOCK
//                    anchoColumna = (8 * ancho) / 100;
//                    break;
//                case 6: // COSTO UNIT.
//                    anchoColumna = (10 * ancho) / 100;
//                    break;
                case 5: // MARCA
                    anchoColumna = (8 * ancho) / 100;
                    break;
                    
                case 6: // PRECIO (DE VENTA)
                    anchoColumna = (8 * ancho) / 100;
                    break;
//                case 7:
//                    anchoColumna = (10 * ancho) / 100;
//                    break;
                    
                // Columnas adicionales (ocultas) para obtener por separado Aplicación1, Aplicación, Anotación1 y Anotación2.
                case 7: // Aplicación 1 ((descrModelo en BD)
                    anchoColumna = (0 * ancho) / 100;
                    break;
                    
                case 8: // Aplicació 2 (aplicacion en BD))
                    anchoColumna = (0 * ancho) / 100;
                    break;
                    
                case 9: // Anotación 1 (desclista en BD)
                    anchoColumna = (0 * ancho) / 100;
                    break;
                    
                case 10: // Anotación 2 (desclista2 en BD))
                    anchoColumna = (0 * ancho) / 100;
                    break;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    private void BorrarTabla() {
        int numRows = dtm.getRowCount();
        for ( int i = 0; i < numRows; i++ ) {
            dtm.removeRow(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Datos;
    private javax.swing.JButton bt_excel;
    private javax.swing.JButton bt_filtrar;
    private javax.swing.JButton bt_imprimir;
    private javax.swing.JButton bt_limpiar;
    private javax.swing.JButton bt_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_moneda;
    private javax.swing.JPanel panelListaPrecios;
    private javax.swing.JTable tb_repuestos;
    private javax.swing.JTextField tx_descripcion;
    private javax.swing.JTextField tx_marca;
    private javax.swing.JTextField tx_nroParte;
    // End of variables declaration//GEN-END:variables
}