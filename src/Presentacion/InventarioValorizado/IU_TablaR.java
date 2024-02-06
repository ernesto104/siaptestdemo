package Presentacion.InventarioValorizado;

import Entidades.Repuestos;
import Mantenimiento.InventarioValorizadoDAO;
import Mantenimiento.MaestrosDAO;
import Servicios.Servicio_InventarioRepuestos;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Keily Mendiolaza
 */
public class IU_TablaR extends javax.swing.JFrame {

    String tip;
    String val;
    String fecha;
    private Servicio_InventarioRepuestos siv;
    InventarioValorizadoDAO idao;
    MaestrosDAO mDAO;
    
    public IU_TablaR(String tipo, String val, String fecha) {
        initComponents();
        tip = tipo;
        mDAO = new MaestrosDAO();
        this.val = val;
        this.fecha = fecha;
        siv = new Servicio_InventarioRepuestos(null);
        idao = new InventarioValorizadoDAO();
    }
    
    public void seleccionado(){
        if (tip.equals("A la fecha")) {            
            siv.listarRepuestos(val);
        }
        if (tip.equals("A una fecha")) {            
            siv.listar_Inventario_aUnaFecha(val, fecha);
            siv.listar_Inventario_aUnaFecha(val, fecha);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaR = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Linea", "Nº Parte", "Codigo Secundario", "Descripcion", "Aplicacion", "Stock", "ddddd", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaR.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaR);
        tablaR.getColumnModel().getColumn(0).setResizable(false);
        tablaR.getColumnModel().getColumn(1).setResizable(false);
        tablaR.getColumnModel().getColumn(2).setResizable(false);
        tablaR.getColumnModel().getColumn(3).setResizable(false);
        tablaR.getColumnModel().getColumn(4).setResizable(false);
        tablaR.getColumnModel().getColumn(5).setResizable(false);
        tablaR.getColumnModel().getColumn(6).setResizable(false);
        tablaR.getColumnModel().getColumn(7).setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(130, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    public BigDecimal listar_Inventario_alaFecha(String valorizacion, String fecha) {
//        BigDecimal contador = new BigDecimal(0);
//        DefaultTableModel table = (DefaultTableModel) tablaR.getModel();        
//        List lista = idao.listar_Inventario_alaFecha_x_Linea(valorizacion, fecha);
//        int tam = lista.size();
//        BigDecimal stock = new BigDecimal(0);
//        
//        for ( int i = 0; i < tam; i++ ) {
//            if ( i != tam - 1 ) {
//                Object[] r = (Object[]) lista.get(i);
//                Object[] s = (Object[]) lista.get(i + 1);
//                if ( r[1].equals(s[1]) ) {
//                    if ( stock.equals(0) ) {
//                        stock = (BigDecimal) r[5];
//                    }
//                    Integer b = new  Integer(r[9].toString());                    
//                    double x = b.doubleValue();
//                    stock = calcularstock(stock,BigDecimal.valueOf(x), r[8].toString()).setScale(0);
//                }
//
//                if ( !r[1].equals(s[1]) ) {
//                    Integer c = new  Integer(r[9].toString()); 
//                    double y = c.doubleValue();
//                    
//                    stock = calcularstock(stock,BigDecimal.valueOf(y), r[8].toString()).setScale(0);
//                    double z = Double.parseDouble(r[6].toString());
//                    Object[] fila = {r[0], r[1], r[2], r[3], r[4], stock, r[6], (stock.multiply(BigDecimal.valueOf(z)).setScale(2))};
//                    contador = contador.add(stock.multiply(BigDecimal.valueOf(z)).setScale(2));
//                    table.addRow(fila);
//                    Integer g = new  Integer(s[5].toString());  
//                    double d = g.doubleValue();
//                    stock = BigDecimal.valueOf(d);
//                }
//            }
//        }
//        return contador;
//    }

//    public BigDecimal listarRepuestos(String valorizacion) {
//        BigDecimal numero1 = new BigDecimal(100);
//        BigDecimal contador = new BigDecimal(0);
//        DefaultTableModel table = (DefaultTableModel) tablaR.getModel();
//        Iterator ite = idao.getlistaRepuestos().iterator();
//
//        while ( ite.hasNext() ) {
//            BigDecimal costo = new BigDecimal(0);
//            BigDecimal stock;
//
//            Object[] fila = new Object[8];
//            Repuestos rep = (Repuestos) ite.next();
//
//            if ( rep.getLineas() != null ) {
//                fila[0] = rep.getLineas().getIdlinea();
//            }
//            
//            if ( !rep.getCodrepuesto().equals(" ") ) {
//                fila[1] = rep.getCodrepuesto();
//            }
//            
//            if ( rep.getCodigoseg() != null ) {
//                fila[2] = rep.getCodigoseg();
//            }
//            
//            if ( !rep.getDescripcion().equals(" ") ) {
//                fila[3] = rep.getDescripcion();
//            }
//            
//            if ( rep.getAplicacion() != null ) {
//                fila[4] = rep.getAplicacion();
//            }
//
//            fila[5] = rep.getStock();
//            double st = rep.getStock().doubleValue();
//
//            stock = BigDecimal.valueOf(st);
//
//            double cos; // variable de costo ara convertir a bigdecimal
//
//            if ( valorizacion.equals("Costo Promedio") ) {
//                if ( rep.getCostopromedio() != null ) {
//                    fila[6] = rep.getCostopromedio();
//                    cos = rep.getCostopromedio();
//                    
//                } else {
//                    fila[6] = 0.0;
//                    cos = 0.0;
//                }
//                costo = BigDecimal.valueOf(cos);
//            }
//            if ( valorizacion.equals("Ultimo Costo") ) {
//                if ( rep.getPcostoultimo() != null ) {
//                    fila[6] = rep.getPcostoultimo();
//                    cos = rep.getPcostoultimo();
//                    
//                } else {
//                    fila[6] = 0.0;
//                    cos = 0.0;
//                }
//                costo = BigDecimal.valueOf(cos);
//            }
//            if ( valorizacion.equals("Precio Lista") ) {
//                if ( rep.getPreciolista() != null ) {
//                    fila[6] = rep.getPreciolista();
//                    cos = rep.getPreciolista();
//                    
//                } else {
//                    fila[6] = 0.0;
//                    cos = 0.0;
//                }
//                costo = BigDecimal.valueOf(cos);
//            }
//
//            BigDecimal total = costo.multiply(stock);
//            fila[7] = total;
//            contador = (contador.add(total).multiply(numero1)).divide(numero1).setScale(2);
//            table.addRow(fila);
//        }
//        return contador;
//    }
    
    public BigDecimal calcularstock(BigDecimal stock, BigDecimal cant_entregada, String codop) {
        BigDecimal stockfinal = new BigDecimal(0);
        
        if ( "I".equals(codop) ) {
            stockfinal = stock.subtract(cant_entregada);
        }        
        if ( "S".equals(codop) ) {
            stockfinal = stock.add(cant_entregada);
        }
        return stockfinal;
    }   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tablaR;
    // End of variables declaration//GEN-END:variables
}