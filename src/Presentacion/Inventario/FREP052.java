package Presentacion.Inventario;

import Entidades.Cabeces;
import Entidades.CabecesId;
import Entidades.Detallees;
import Entidades.Repuestos;
import Entidades.Sistema;
import Mantenimiento.DetallesDAO;
import Mantenimiento.Facturacion.SistemaDAO;
import Mantenimiento.RepuestosDAO;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Lesly Aguilar
 */
public class FREP052 extends javax.swing.JFrame {

    RepuestosDAO repuestoDAO;
    List<Repuestos> listaRepuestos;
    Sistema sistema;
    SistemaDAO sistemaDAO;

    public FREP052() {
        initComponents();
        this.setLocationRelativeTo(null);
        repuestoDAO = new RepuestosDAO();
        sistema = new Sistema();
        sistemaDAO = new SistemaDAO();
        calcular();
        btnSi.requestFocus();
    }

    public void calcular() {
        listaRepuestos = repuestoDAO.Obtener_Lista_Objetos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelCierreInventario = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnSi = new javax.swing.JButton();
        btnNo = new javax.swing.JButton();
        btn_Salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("CIERRE DE INVENTARIO");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setText("¿Desea cerrar el Inventario?");

        btnSi.setText("SÍ");
        btnSi.setNextFocusableComponent(btnNo);
        btnSi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiActionPerformed(evt);
            }
        });

        btnNo.setText("NO");
        btnNo.setNextFocusableComponent(btnSi);
        btnNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoActionPerformed(evt);
            }
        });

        btn_Salir.setText("SALIR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelCierreInventarioLayout = new javax.swing.GroupLayout(panelCierreInventario);
        panelCierreInventario.setLayout(panelCierreInventarioLayout);
        panelCierreInventarioLayout.setHorizontalGroup(
            panelCierreInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCierreInventarioLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCierreInventarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(103, 103, 103))
        );
        panelCierreInventarioLayout.setVerticalGroup(
            panelCierreInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCierreInventarioLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCierreInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCierreInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoActionPerformed
    }//GEN-LAST:event_btnNoActionPerformed

    private void btnSiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiActionPerformed

        int stock = 0;
        int inventario = 0;
        Detallees detallees;
        Cabeces cabeces;
        CabecesId cabecesID;
        DetallesDAO detalleesDAO = new DetallesDAO();
        Repuestos repuestos;
        int nuevoStock=0;

        for (int i = 0; i < 4; i++) {
            stock = listaRepuestos.get(i).getStock();
            inventario = listaRepuestos.get(i).getInventario();
            detallees = new Detallees();
            cabeces = new Cabeces();
            cabecesID = new CabecesId();
            int cantidad=0;
            //SOBRANTE
            if (stock > inventario) {
                sistema = sistemaDAO.Obtener_Objeto(11);
                cantidad = stock - inventario;
                nuevoStock = stock - cantidad;
                detallees.setCantentregada(cantidad);
                detallees.setMotivo("Regularización de Inventario");
            }
            //FALTANTE
            else {
                sistema = sistemaDAO.Obtener_Objeto(12);
                cantidad = inventario - stock;
                nuevoStock = stock + cantidad;
                detallees.setCantentregada(cantidad);
                detallees.setMotivo("Regularización de Inventario");
            }
            cabecesID.setNrodocumento("" + (sistema.getUltimonumero() + 1));
            cabecesID.setNrorserie("" + sistema.getSerie());
            cabecesID.setTipodoc(sistema.getTipodoc());
            cabeces.setId(cabecesID);
            detallees.setCabeces(cabeces);
            Date diaCierre = new Date();
            detallees.setFecha(diaCierre);
            detallees.setRepuestos(listaRepuestos.get(i));
            detalleesDAO.Agregar_Objeto(detallees);
            
            repuestos = new Repuestos();
            repuestos = listaRepuestos.get(i);
            repuestos.setStock(nuevoStock);
            repuestoDAO.Modificar_Objeto(repuestos);
        }


    }//GEN-LAST:event_btnSiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnNo;
    public javax.swing.JButton btnSi;
    public javax.swing.JButton btn_Salir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel panelCierreInventario;
    // End of variables declaration//GEN-END:variables
}
