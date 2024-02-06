package Presentacion.Repuestos;

import Entidades.Repuestos;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import javax.swing.WindowConstants;

/**
 *
 * @author Lesly Aguilar
 */
public class IU_Repuestos_MasDatos extends javax.swing.JFrame {

    Repuestos repuesto;

    public IU_Repuestos_MasDatos(Repuestos r) {
        initComponents();        
        this.repuesto = r;        
        iniciar();
        llenarDatos();
        this.setLocationRelativeTo(null);
        
        setDefaultCloseOperation (WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(false);
            }
        });
        btnVerDemanda.requestFocus();
    }

    public void iniciar() {
        txtAplicacionRepuesto.setEditable(false);
        txtCantParaInvent.setEditable(false);
        txtCodEstratificacion.setEditable(false);
        txtCodImportacion.setEditable(false);
        txtCodReducido.setEditable(false);
        txtCodReemplazoAnt.setEditable(false);
        txtCostoPromed.setEditable(false);
        txtDescripDetallada.setEditable(false);
        txtDescripRepuesto.setEditable(false);
        txtLinea.setEditable(false);
        txtLineaDetallada.setEditable(false);
        txtMarcaProveed.setEditable(false);
        txtNroParte.setEditable(false);
        txtPartidaArancelada.setEditable(false);
        txtPrecioCostoTemp.setEditable(false);
        txtPrecioLista.setEditable(false);
        txtStock.setEditable(false);
        txtStockMinimo.setEditable(false);
        txtUbicacionFisica.setEditable(false);
        txtUltimaFechaCost.setEditable(false);
        txtUltimoCosto.setEditable(false);
        txtUltimoValorFOB.setEditable(false);
        txtUnidadMedida.setEditable(false);
        txtUnidadVenta.setEditable(false);
    }

    public void llenarDatos() {
        txtAplicacionRepuesto.setText(repuesto.getAplicacion());
        if(repuesto.getStockminimo()==null){
            txtCantParaInvent.setText("");
        }
        else{
            txtCantParaInvent.setText("" + repuesto.getStockminimo());
        }
        if (repuesto.getEstratificacion() != null) {
            txtCodEstratificacion.setText(repuesto.getEstratificacion().getCodigoestratificacion());
        }
        if(repuesto.getCodigoseg()==null){
            txtCodImportacion.setText("");
        }
        else{
            txtCodImportacion.setText(repuesto.getCodigoseg());
        }
        
        if(repuesto.getMargenutil()==null){
            txtCodReducido.setText("");
        }
        else{
            txtCodReducido.setText("" + repuesto.getMargenutil());
        }
        if(repuesto.getIdrepuestoant()==null){
            txtCodReemplazoAnt.setText("");
        }
        else{
            txtCodReemplazoAnt.setText(repuesto.getIdrepuestoant());
        }
        
        if(repuesto.getCostopromedio()==null){
            txtCostoPromed.setText("");
        }
        else{
            txtCostoPromed.setText("" + repuesto.getCostopromedio());
        }
        if(repuesto.getDescrmodelo()==null){
            txtDescripDetallada.setText("");
        }
        else{
            txtDescripDetallada.setText(repuesto.getDescrmodelo());
        }
        if(repuesto.getDescripcion()==null){
            txtDescripRepuesto.setText("");
        }
        else{
            txtDescripRepuesto.setText(repuesto.getDescripcion());
        }
        
        txtLinea.setText("" + repuesto.getEquipos().getIdequipo());
        txtLineaDetallada.setText(repuesto.getEquipos().getDescripcion());
        if(repuesto.getMarca()==null){
            txtMarcaProveed.setText("");
        }
        else{
            txtMarcaProveed.setText(repuesto.getMarca());
        }
        
        txtNroParte.setText(repuesto.getCodrepuesto());
        if(repuesto.getPartidarancel()==null){
            txtPartidaArancelada.setText("");
        }
        else{
            txtPartidaArancelada.setText(repuesto.getPartidarancel());
        }
        
        if(repuesto.getPcostotemporal()==null){
            txtPrecioCostoTemp.setText("");
        }
        else{
            txtPrecioCostoTemp.setText("" + repuesto.getPcostotemporal());
        }
        
        txtPrecioLista.setText("" + repuesto.getPreciolista());
        txtStock.setText("" + repuesto.getStock());
        if(repuesto.getStockminimo()==null){
            txtStockMinimo.setText("");
        }
        else{
            txtStockMinimo.setText("" + repuesto.getStockminimo());
        }
        
        txtUbicacionFisica.setText(repuesto.getUbicalmacen());

        if (repuesto.getFechapcosto() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            txtUltimaFechaCost.setText(sdf.format(repuesto.getFechapcosto()));
        }

        if(repuesto.getPcostoultimo()==null){
            txtUltimoCosto.setText("");
        }
        else{
            txtUltimoCosto.setText("" + repuesto.getPcostoultimo());
        }
        if(repuesto.getFobultimo()==null){
            txtUltimoValorFOB.setText("");
        }
        else{
            txtUltimoValorFOB.setText("" + repuesto.getFobultimo());
        }
        
        txtUnidadMedida.setText(repuesto.getUnidadmedida());
        txtUnidadVenta.setText(repuesto.getUnidadventa());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtNroParte = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtLinea = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtCodEstratificacion = new javax.swing.JTextField();
        txtCodImportacion = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtCodReducido = new javax.swing.JTextField();
        txtDescripRepuesto = new javax.swing.JTextField();
        txtDescripDetallada = new javax.swing.JTextField();
        txtLineaDetallada = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtMarcaProveed = new javax.swing.JTextField();
        txtPrecioLista = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtCostoPromed = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtUltimoCosto = new javax.swing.JTextField();
        txtPrecioCostoTemp = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtUltimoValorFOB = new javax.swing.JTextField();
        txtCodReemplazoAnt = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtPartidaArancelada = new javax.swing.JTextField();
        txtUltimaFechaCost = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtUbicacionFisica = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtStockMinimo = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtCantParaInvent = new javax.swing.JTextField();
        txtUnidadVenta = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtUnidadMedida = new javax.swing.JTextField();
        txtAplicacionRepuesto = new javax.swing.JTextField();
        btnVerDemanda = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("DATOS DEL REPUESTO");

        jLabel2.setText("Nro. Parte");

        jLabel3.setText("Código de Importación");

        jLabel4.setText("Descripción del repuesto");

        jLabel5.setText("Descripción detallada");

        jLabel6.setText("Linea detallada adicional");

        jLabel7.setText("Precio Lista");

        jLabel8.setText("Precio de Costo Temporal");

        jLabel9.setText("Código del reemplazo anterior");

        jLabel10.setText("Ultima fecha de costo");

        jLabel11.setText("Stock");

        jLabel12.setText("Unidad de Venta");

        jLabel13.setText("Aplicación del Repuesto");

        jLabel14.setText("Linea");

        jLabel15.setText("Código de Estratificación");

        jLabel16.setText("Código Reducido");

        jLabel17.setText("Marca del Proveedor");

        jLabel18.setText("Costo Promedio");

        jLabel19.setText("Ultimo costo");

        jLabel20.setText("Ultimo valor FOB");

        jLabel21.setText("Partida Arancelada");

        jLabel22.setText("Ubicación Física");

        jLabel23.setText("Stock Mínimo");

        jLabel24.setText("Cant. para Inventario");

        jLabel25.setText("Unidad de Medida");

        btnVerDemanda.setText("Ver Demanda");
        btnVerDemanda.setNextFocusableComponent(btnSalir);
        btnVerDemanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDemandaActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.setNextFocusableComponent(btnVerDemanda);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtNroParte, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodEstratificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCodImportacion, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCodReducido, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDescripRepuesto)
                            .addComponent(txtDescripDetallada)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtLineaDetallada, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMarcaProveed, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPrecioCostoTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUltimoValorFOB, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPrecioLista, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCostoPromed, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUltimoCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCodReemplazoAnt, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPartidaArancelada, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtUltimaFechaCost, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUbicacionFisica, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCantParaInvent))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtUnidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtAplicacionRepuesto)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(btnVerDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(94, 94, 94)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNroParte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtCodEstratificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCodImportacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtCodReducido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDescripRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDescripDetallada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtLineaDetallada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtMarcaProveed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtPrecioLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txtCostoPromed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txtUltimoCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrecioCostoTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
                        .addComponent(txtUltimoValorFOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCodReemplazoAnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(txtPartidaArancelada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtUltimaFechaCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(txtUbicacionFisica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(txtStockMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(txtCantParaInvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtUnidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(txtUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtAplicacionRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
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

    private void btnVerDemandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDemandaActionPerformed
        IU_Demanda iu = new IU_Demanda(repuesto.getCodrepuesto());
        iu.setVisible(true);
    }//GEN-LAST:event_btnVerDemandaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed

        this.setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVerDemanda;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtAplicacionRepuesto;
    private javax.swing.JTextField txtCantParaInvent;
    private javax.swing.JTextField txtCodEstratificacion;
    private javax.swing.JTextField txtCodImportacion;
    private javax.swing.JTextField txtCodReducido;
    private javax.swing.JTextField txtCodReemplazoAnt;
    private javax.swing.JTextField txtCostoPromed;
    private javax.swing.JTextField txtDescripDetallada;
    private javax.swing.JTextField txtDescripRepuesto;
    private javax.swing.JTextField txtLinea;
    private javax.swing.JTextField txtLineaDetallada;
    private javax.swing.JTextField txtMarcaProveed;
    private javax.swing.JTextField txtNroParte;
    private javax.swing.JTextField txtPartidaArancelada;
    private javax.swing.JTextField txtPrecioCostoTemp;
    private javax.swing.JTextField txtPrecioLista;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtStockMinimo;
    private javax.swing.JTextField txtUbicacionFisica;
    private javax.swing.JTextField txtUltimaFechaCost;
    private javax.swing.JTextField txtUltimoCosto;
    private javax.swing.JTextField txtUltimoValorFOB;
    private javax.swing.JTextField txtUnidadMedida;
    private javax.swing.JTextField txtUnidadVenta;
    // End of variables declaration//GEN-END:variables
}
