package Presentacion.CuentasxCobrarFacturas;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @author Ledis Rivera Changra
 */
public class CargaImagenes extends javax.swing.JFrame {

    private static final long serialVersionUID = -2175359093678346508L;
    private String rutaImagenes;

    public CargaImagenes(String rutaImagenes) {
        initComponents();
        this.rutaImagenes = rutaImagenes;
        cargarImagenes();
    }

    private void cargarImagenes() {
//        System.out.println("rutaImagenes(CargaImagenes):" + rutaImagenes);
        Image imagenExterna = new ImageIcon(rutaImagenes).getImage();
        jPanelConFondo2.setImagen2(imagenExterna);
        jPanelConFondo2.revalidate();
        jPanelConFondo2.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        final javax.swing.JLabel lbExterna = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Carga de Imagenes");

        jPanelConFondo2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbExterna.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbExterna.setForeground(new java.awt.Color(255, 204, 102));
        lbExterna.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbExterna.setText("EXTERNA");

        javax.swing.GroupLayout jPanelConFondo2Layout = new javax.swing.GroupLayout(jPanelConFondo2);
        jPanelConFondo2.setLayout(jPanelConFondo2Layout);
        jPanelConFondo2Layout.setHorizontalGroup(
            jPanelConFondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConFondo2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbExterna, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelConFondo2Layout.setVerticalGroup(
            jPanelConFondo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelConFondo2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbExterna, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConFondo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelConFondo2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//
//            public void run() {
//                CargaImagenes ci = new CargaImagenes();
//                ci.setLocationRelativeTo(null);
//                ci.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final ar.lefunes.jpanelconfondo.JPanelConFondo jPanelConFondo2 = new ar.lefunes.jpanelconfondo.JPanelConFondo();
    // End of variables declaration//GEN-END:variables
}
