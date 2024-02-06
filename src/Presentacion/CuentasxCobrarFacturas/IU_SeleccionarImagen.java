package Presentacion.CuentasxCobrarFacturas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 */
public class IU_SeleccionarImagen extends javax.swing.JFrame {

    private static final long serialVersionUID = -708703562328002253L;
    
    File fileImg;
    Image imagen;
    private final JPanelConFondo jPanelConFondo2 = new JPanelConFondo();
    
//    private ImageProcessing_OpenImage ObjOpenImage;
//    private BufferedImage bufferImageTemp;

    public IU_SeleccionarImagen(File fileImg) {
        initComponents();
//        setLocationRelativeTo(null);
        this.fileImg = fileImg;
        this.imagen = imagen;
        iniciarImagen();
    }

    public void iniciarImagen() {
//        System.out.println("iniciando imagen...");
//        System.out.println("Nombre de imagen:" + fileImg.getName());
        jLabel1.setText(fileImg.getName());
        
        String path = fileImg.getPath();
//        System.out.println("path:" + path);
        
        String ruta = "C:\\SIAR\\IMAGENES\\NroDoc.4380.IdPago.2.Fech.04052016.jpg";
        Image imagenExterna = new ImageIcon(ruta).getImage();
        
        jPanelConFondo2.setImagen(imagenExterna);
        repaint();
        
//        ImageIcon imagen = new ImageIcon();
//        File imageFile=this.selectorImage.getSelectedFile();
//        this.openImage = ImageIO.read(this.selectorImage.getSelectedFile());
        
//        this.bufferImageTemp = ObjOpenImage.openFile(ImageProcessing.imageFormat.all_images);
//        if ( this.bufferImageTemp != null ) {
//            this.lblImagen.setIcon(new ImageIcon(this.bufferImageTemp));
//        }
        
//        Imagen Imagen = new Imagen();
        
//        lblImagen.removeAll();
//        lblImagen.add(Imagen);
//        lblImagen.repaint();
//        lblImagen.updateUI();
//        lblImagen.revalidate();
        
//        pimg.removeAll();
//        pimg.add(Imagen);
//        pimg.repaint();
//        pimg.updateUI();
//        pimg.revalidate();
    }

    public class Imagen extends javax.swing.JPanel {

        public Imagen() {
//            this.setSize(jPanel1.getWidth(), jPanel1.getHeight()); //se selecciona el tamaño del panel
        }
        
        //Se crea un mÃ©todo cuyo parámetro debe ser un objeto Graphics
        public void paint(Graphics grafico) {
            System.out.println("inicio pintar...");

            //Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
            ImageIcon Img = new ImageIcon(imagen.toString());

            //se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
//            grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height, this);
            grafico.drawImage(Img.getImage(), 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
//            super.paintComponent(grafico);
//            repaint();
//            updateUI();
            super.paint(grafico);
            System.out.println("fin pintar");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImagen = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("jLabel1");

        jLabel2.setText("Imagen de cheque:");

        javax.swing.GroupLayout panelImagenLayout = new javax.swing.GroupLayout(panelImagen);
        panelImagen.setLayout(panelImagenLayout);
        panelImagenLayout.setHorizontalGroup(
            panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImagenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(500, Short.MAX_VALUE))
        );
        panelImagenLayout.setVerticalGroup(
            panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImagenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(485, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panelImagen;
    // End of variables declaration//GEN-END:variables
}
