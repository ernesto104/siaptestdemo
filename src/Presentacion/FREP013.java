package Presentacion;

import Entidades.Control;
import static Entidades.Otros.Constante.DOLARES;
import static Entidades.Otros.Constante.DOLAR_COMBO;
import static Entidades.Otros.Constante.SOLES;
import static Entidades.Otros.Constante.SOL_COMBO;
import Entidades.Ubigeo;
import Mantenimiento.Navegacion;
import Servicios.Comision.Validar_Mayusculas;
import Servicios.Servicio_Control_Sistema;
import Servicios.Servicio_Ubigeo;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Lesly Aguilar
 */
public class FREP013 extends javax.swing.JFrame {

    Servicio_Control_Sistema servicioControl;
    public JTextField[] componentes;
    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo;
    private final ArrayList<String> tipoDato;
    
    private Servicio_Ubigeo servicioUbigeo;
    private List listaDptos;
    private List listaProv;
    private List listaDist;
    private DefaultComboBoxModel modelDptoCombo;
    private DefaultComboBoxModel modelProvCombo;
    private DefaultComboBoxModel modelDistCombo;

    public FREP013() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtRutaBD.setEditable(false);
        txtRutaImagenes.setEditable(false);
        txtRutaProgramas.setEditable(false);
        txtRutaReportes.setEditable(false);

        servicioControl = new Servicio_Control_Sistema(this);
        tx_stock0.setDocument(new Validar_Mayusculas(tx_stock0, 1));
        
        cargarTipoMoneda();
        cargarTipoDocPorDefecto();
        
        mostrarControl();
        crearArrayComponente();
        numMaximo = new ArrayList<>();
        crearArrayNumMax();
        tipoDato = new ArrayList<>();
        crearArrayTipoDato();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, btnModificar);
        asignarEvento();
    }
    
    private void cargarTipoMoneda() {
        cbTipoMoneda.addItem("");
        cbTipoMoneda.addItem("Soles");
        cbTipoMoneda.addItem("Dolares");
        cbTipoMoneda.setSelectedItem("");
    }
    
    private void cargarTipoDocPorDefecto() {
        cbTipoDoc.addItem("");
        cbTipoDoc.addItem("Factura");
        cbTipoDoc.addItem("Boleta");
        cbTipoDoc.addItem("Proforma");
        cbTipoDoc.addItem("Salidas Varias");
        cbTipoDoc.setSelectedItem("");
    }
    
    private void Listar_Departamentos() {
        modelDptoCombo = new DefaultComboBoxModel();
        listaDptos = servicioUbigeo.Listar_Departamentos_Order_Asc_Nombre();
        for (int i = 0; i < listaDptos.size(); i++) {
            modelDptoCombo.addElement(listaDptos.get(i));
        }
        modelDptoCombo.addElement("");
        cbDepartamentos.setModel(modelDptoCombo);
    }
    
    private void Listar_Provincias(String departamento) {
        modelProvCombo = new DefaultComboBoxModel();
        listaProv = servicioUbigeo.Listar_Provincias_Order_Asc_Nombre(departamento);
        for (int i = 0; i < listaProv.size(); i++) {
            modelProvCombo.addElement(listaProv.get(i));
        }
        modelProvCombo.addElement("");
        cbProvincias.setModel(modelProvCombo);
    }

    private void Listar_Distritos(String departamento, String provincia) {
        modelDistCombo = new DefaultComboBoxModel();
        listaDist = servicioUbigeo.Listar_Distritos_Order_Asc_Nombre(departamento, provincia);
        for (int i = 0; i < listaDist.size(); i++) {
            modelDistCombo.addElement(listaDist.get(i));
        }
        modelDistCombo.addElement("");
        cbDistritos.setModel(modelDistCombo);
    }

    private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    private void crearArrayComponente() {
        componentes = new JTextField[8];
        componentes[0] = txtNombre;
        componentes[1] = txtAlmacen;
        componentes[2] = txtLineaFactura;
        componentes[3] = txtLineaBoleta;
        componentes[4] = txtLineaNotaCred;
        componentes[5] = txtLineaNotaDeb;
        componentes[6] = txtIGV;
        componentes[7] = txtRuc;
    }

    private void crearArrayNumMax() {
        numMaximo.add(50); //nombre
        numMaximo.add(50); //almacen
        numMaximo.add(2); //linea factu
        numMaximo.add(2); //linea bolet
        numMaximo.add(2); //linea note cred
        numMaximo.add(2); //linea nota deb
        numMaximo.add(2); //igv
        numMaximo.add(11); //ruc
    }

    private void crearArrayTipoDato() {
        tipoDato.add("S"); //nombre
        tipoDato.add("S"); //almacen
        tipoDato.add("I"); //linea fact
        tipoDato.add("I"); //linea bolet
        tipoDato.add("I"); //linea nota cred
        tipoDato.add("I"); //linea nota deb
        tipoDato.add("I"); //igv
        tipoDato.add("I"); //ruc
    }

    public void mostrarControl() {
        Control c = new Control();
        c = servicioControl.obtener_Unico_Control();
        txtNombre.setText(c.getNombrempresa());
        txtAlmacen.setText(c.getNombrealmacen());
        txtAlmacen2.setText(c.getNombrealmacen2());
        txtTelefonos.setText(c.getTelefonos());
        txtIGV.setText("" + c.getImpuestoigv());
        txtRutaBD.setText(c.getRutadb());
        txtRutaImagenes.setText(c.getRutaimagenes());
        txtRutaProgramas.setText(c.getRutaprogramas());
        txtRutaReportes.setText(c.getRutareportes());
        txtLineaBoleta.setText("" + c.getNrolineabol());
        txtLineaFactura.setText("" + c.getNrolineafac());
        txtLineaNotaCred.setText("" + c.getNrolineanc());
        txtLineaNotaDeb.setText("" + c.getNrolineand());
        txtRuc.setText("" + c.getRuc());
        tx_stock0.setText(String.valueOf(c.getStock0()));
        combBoxDemanda.setSelectedIndex((c.getMesdemanda() - 1));
        combBoxProyeccion.setSelectedIndex((c.getMesproyeccion() - 1));
        txtCorreo.setText(c.getCorreo());
        
        if ( c.getMonedarepuestos() == 0 ) {
            cbTipoMoneda.setSelectedItem(DOLARES);
        } else if ( c.getMonedarepuestos() == 1 ) {
            cbTipoMoneda.setSelectedItem(SOLES);
        }
        
        cbTipoDoc.setSelectedItem(c.getDocfactdefecto());
        
        servicioUbigeo = new Servicio_Ubigeo();
        Listar_Departamentos();
        
        Ubigeo ubigeo = c.getUbigeo();
        Listar_Provincias(ubigeo.getProvincia());
        Listar_Distritos(ubigeo.getDepartamento(), ubigeo.getProvincia());
        
        if ( ubigeo != null ) {
            cbDepartamentos.setSelectedItem(ubigeo.getDepartamento());
            cbProvincias.setSelectedItem(ubigeo.getProvincia());
            cbDistritos.setSelectedItem(ubigeo.getDistrito());
            
        } else {
            servicioUbigeo = new Servicio_Ubigeo();
            Listar_Departamentos();
            cbDepartamentos.setSelectedIndex(listaDptos.size());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelControlSistema = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtAlmacen = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        combBoxDemanda = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        combBoxProyeccion = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txtIGV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtRutaBD = new javax.swing.JTextField();
        btnRutaBD = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtRutaProgramas = new javax.swing.JTextField();
        btnRutaProgramas = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtRutaReportes = new javax.swing.JTextField();
        btnRutaReportes = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtRutaImagenes = new javax.swing.JTextField();
        btnRutaImagenes = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtLineaFactura = new javax.swing.JTextField();
        txtLineaBoleta = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtLineaNotaCred = new javax.swing.JTextField();
        txtLineaNotaDeb = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        tx_stock0 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtTelefonos = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtAlmacen2 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        cbDepartamentos = new javax.swing.JComboBox();
        cbProvincias = new javax.swing.JComboBox();
        cbDistritos = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        cbTipoMoneda = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        cbTipoDoc = new javax.swing.JComboBox();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TABLA DE CONTROL");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos"));

        jLabel2.setText("Nombre de la Empresa:");

        txtNombre.setNextFocusableComponent(txtAlmacen);

        jLabel3.setText("Dirección 1:");

        txtAlmacen.setNextFocusableComponent(txtLineaFactura);

        jLabel4.setText("Mes de Demanda:");

        combBoxDemanda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        combBoxDemanda.setNextFocusableComponent(combBoxProyeccion);
        combBoxDemanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combBoxDemandaActionPerformed(evt);
            }
        });

        jLabel5.setText("Mes de Proyección:");

        combBoxProyeccion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        combBoxProyeccion.setNextFocusableComponent(txtIGV);

        jLabel6.setText("Impuesto IGV:");

        txtIGV.setNextFocusableComponent(btnRutaBD);

        jLabel7.setText("Ruta de Base de Datos:");

        btnRutaBD.setText("...");
        btnRutaBD.setNextFocusableComponent(btnRutaProgramas);
        btnRutaBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRutaBDActionPerformed(evt);
            }
        });

        jLabel8.setText("Ruta de Programas:");

        btnRutaProgramas.setText("...");
        btnRutaProgramas.setNextFocusableComponent(btnRutaReportes);
        btnRutaProgramas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRutaProgramasActionPerformed(evt);
            }
        });

        jLabel9.setText("Ruta de Reportes:");

        btnRutaReportes.setText("...");
        btnRutaReportes.setNextFocusableComponent(btnRutaImagenes);
        btnRutaReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRutaReportesActionPerformed(evt);
            }
        });

        jLabel10.setText("Ruta de Imagenes:");

        btnRutaImagenes.setText("...");
        btnRutaImagenes.setNextFocusableComponent(btnModificar);
        btnRutaImagenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRutaImagenesActionPerformed(evt);
            }
        });

        jLabel11.setText("Número de Linea Boleta:");

        jLabel12.setText("Número de Linea Factura:");

        txtLineaFactura.setNextFocusableComponent(txtLineaBoleta);

        txtLineaBoleta.setNextFocusableComponent(txtLineaNotaCred);

        jLabel13.setText("Número de Linea Nota de Crédito:");

        txtLineaNotaCred.setNextFocusableComponent(txtLineaNotaDeb);

        txtLineaNotaDeb.setNextFocusableComponent(combBoxDemanda);
        txtLineaNotaDeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLineaNotaDebActionPerformed(evt);
            }
        });

        jLabel14.setText("Número de Linea Nota de Débito:");

        jLabel15.setText("(*)");

        jLabel17.setText("(*)");

        jLabel18.setText("(*)");

        jLabel19.setText("(*)");

        jLabel20.setText("(*)");

        jLabel21.setText("(*)");

        jLabel22.setText("(*)");

        jLabel23.setText("(*)");

        jLabel24.setText("(*)");

        jLabel25.setText("(*)");

        jLabel26.setText("(*)");

        jLabel27.setText("(*)");

        jLabel28.setText("Mostrar Articulos con Stock 0 en Facturacion ? (S,N)");

        tx_stock0.setNextFocusableComponent(txtLineaNotaCred);

        jLabel29.setText("Teléfono(s)/Celular(es):");

        jLabel30.setText("RUC:");

        jLabel31.setText("Correo:");

        jLabel32.setText("Dirección 2:");

        txtAlmacen2.setNextFocusableComponent(txtLineaFactura);

        jLabel33.setText("Departamento:");

        jLabel34.setText("Provincia:");

        jLabel35.setText("Distrito:");

        cbDepartamentos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbDepartamentosItemStateChanged(evt);
            }
        });

        cbProvincias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProvinciasItemStateChanged(evt);
            }
        });

        jLabel16.setText("Tipo Moneda para Repuestos:");

        jLabel36.setText("Tipo Documento(por defecto):");

        jLabel37.setText("(*)");

        jLabel38.setText("(*)");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel32))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtAlmacen, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                                                    .addComponent(txtNombre)
                                                    .addComponent(txtAlmacen2)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(522, 522, 522)
                                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel29)
                                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTelefonos, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtLineaBoleta, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                    .addComponent(txtLineaFactura))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel19))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(combBoxProyeccion, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combBoxDemanda, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tx_stock0, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel16))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtLineaNotaCred, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtLineaNotaDeb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18)
                                            .addComponent(jLabel17)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(cbTipoMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel37)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtRutaBD, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtRutaProgramas, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtRutaReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtRutaImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel33)
                                            .addComponent(jLabel34)
                                            .addComponent(jLabel35)
                                            .addComponent(jLabel30))
                                        .addGap(90, 90, 90)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbDepartamentos, 0, 288, Short.MAX_VALUE)
                                            .addComponent(cbProvincias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(cbDistritos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel15)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnRutaImagenes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnRutaReportes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnRutaProgramas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnRutaBD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(cbTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24)
                                            .addComponent(jLabel25)
                                            .addComponent(jLabel26)
                                            .addComponent(jLabel27)
                                            .addComponent(jLabel38))))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(cbDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtAlmacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(cbProvincias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtAlmacen2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(cbDistritos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtTelefonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(combBoxDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))
                                .addGap(14, 14, 14)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(combBoxProyeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtIGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel6)))
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTipoDoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel28)
                                .addComponent(tx_stock0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel36)
                                .addComponent(jLabel38))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtLineaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtLineaBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtLineaNotaCred, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtLineaNotaDeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(cbTipoMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel37))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtRutaBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRutaBD)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtRutaProgramas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRutaProgramas)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtRutaReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRutaReportes)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtRutaImagenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRutaImagenes))
                    .addComponent(jLabel27))
                .addContainerGap())
        );

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelControlSistemaLayout = new javax.swing.GroupLayout(panelControlSistema);
        panelControlSistema.setLayout(panelControlSistemaLayout);
        panelControlSistemaLayout.setHorizontalGroup(
            panelControlSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlSistemaLayout.createSequentialGroup()
                .addGap(292, 292, 292)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(575, Short.MAX_VALUE))
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelControlSistemaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelControlSistemaLayout.setVerticalGroup(
            panelControlSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelControlSistemaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(panelControlSistemaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelControlSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelControlSistema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnRutaBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutaBDActionPerformed
        String rutaBD = servicioControl.obtener_Ruta_Carpeta();
        txtRutaBD.setText(rutaBD);
    }//GEN-LAST:event_btnRutaBDActionPerformed

    private void btnRutaProgramasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutaProgramasActionPerformed
        String rutaProgramas = servicioControl.obtener_Ruta_Carpeta();
        txtRutaProgramas.setText(rutaProgramas);
    }//GEN-LAST:event_btnRutaProgramasActionPerformed

    private void btnRutaReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutaReportesActionPerformed
        String rutaReportes = servicioControl.obtener_Ruta_Carpeta();
        txtRutaReportes.setText(rutaReportes);
    }//GEN-LAST:event_btnRutaReportesActionPerformed

    private void btnRutaImagenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRutaImagenesActionPerformed
        String rutaImagenes = servicioControl.obtener_Ruta_Carpeta();
        txtRutaImagenes.setText(rutaImagenes);
    }//GEN-LAST:event_btnRutaImagenesActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

        Control control = new Control();
        String mensaje = "";

        if ((txtNombre.getText().equals(""))) {
            mensaje = mensaje + "\n" + "Debe ingresar el nombre de la Empresa";
        }
        if ((txtRutaBD.getText().equals(""))) {
            mensaje = mensaje + "\n" + "Debe ingresar el la ruta de la base de datos";
        }

        if ((txtRutaProgramas.getText().equals(""))) {
            mensaje = mensaje + "\n" + "Debe ingresar el la ruta de los programas";
        }
        if ((txtRutaReportes.getText().equals(""))) {
            mensaje = mensaje + "\n" + "Debe ingresar el la ruta de los reportes";
        }
        if ((txtRutaImagenes.getText().equals(""))) {
            mensaje = mensaje + "\n" + "Debe ingresar el la ruta de las imagenes";
        }

//        if ((txtIGV.getText().equals("")) || ((Double.parseDouble(txtIGV.getText())) <= 0)) {
        if ((txtIGV.getText().equals("")) || ((Double.parseDouble(txtIGV.getText())) < 0)) {
            mensaje = mensaje + "Debe ingresar IGV y ser mayor o igual a 0";
        }
        if ((txtAlmacen.getText().equals(""))) {
            mensaje = mensaje + "\n" + "Debe ingresar el nombre del Almacén";
        }
        if ((txtLineaBoleta.getText().equals("")) || ((Integer.parseInt(txtLineaBoleta.getText())) > 40)) {
            mensaje = mensaje + "\n" + "Debe ingresar la Linea de Boleta y no ser mayor que 40";
        }
        if ((txtLineaFactura.getText().equals("")) || ((Integer.parseInt(txtLineaFactura.getText())) > 40)) {
            mensaje = mensaje + "\n" + "Debe ingresar la Linea de Factura y no ser mayor que 40";
        }
        if ((txtLineaNotaDeb.getText().equals("")) || ((Integer.parseInt(txtLineaNotaDeb.getText())) <= 0)) {
            mensaje = mensaje + "\n" + "Debe ingresar la Linea de la Nota de Debito y ser mayor a 0";
        }
        if ((txtLineaNotaCred.getText().equals("")) || ((Integer.parseInt(txtLineaNotaCred.getText())) <= 0)) {
            mensaje = mensaje + "\n" + "Debe ingresar la Linea de la Nota de Credito y ser mayor a 0";
        }
        
        if (tx_stock0.getText().equals("") || (!tx_stock0.getText().equals("S") && !tx_stock0.getText().equals("N"))) {
            mensaje = mensaje + "\n" + "Escoja una opcion correcta (S,N) en el campo Mostrar Stock 0";
        }
        
        String dpto = String.valueOf(cbDepartamentos.getSelectedItem());
        String prov = String.valueOf(cbProvincias.getSelectedItem());
        String dist = String.valueOf(cbDistritos.getSelectedItem());
        if ( "".equals(dpto) ) {
            mensaje = mensaje + "\n" + "Escoja una departamento como Punto de Partida";
        }
        if ( "".equals(prov) ) {
            mensaje = mensaje + "\n" + "Escoja una provincia como Punto de Partida";
        }
        if ( "".equals(dist) ) {
            mensaje = mensaje + "\n" + "Escoja un distrito como Punto de Partida";
        }
        
        String tipoMoneda = String.valueOf(cbTipoMoneda.getSelectedItem());
        if ( "".equals(tipoMoneda) ) {
            mensaje = mensaje + "\n" + "Escoja un tipo de moneda para los precios de los Repuestos";
        }
        
        String tipDocPorDefecto = String.valueOf(cbTipoDoc.getSelectedItem());
        if ( "".equals(tipDocPorDefecto) ) {
            mensaje = mensaje + "\n" + "Escoja un tipo de documento por defecto para cargar en Facturación";
        }
        
        if (!mensaje.equals("")) {
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int verif;

            verif = JOptionPane.showConfirmDialog(null, "¿Desea actualizar el control del sistema?", "CONFIRMACIÓN", 0);
            if (verif == 0) {
                control.setIdcontrol(1);
                control.setImpuestoigv(Integer.parseInt(txtIGV.getText()));
                control.setMesdemanda(combBoxDemanda.getSelectedIndex() + 1);
                control.setMesproyeccion(combBoxProyeccion.getSelectedIndex() + 1);
                control.setNombrealmacen(txtAlmacen.getText());
                control.setNombrealmacen2(txtAlmacen2.getText());
                control.setTelefonos(txtTelefonos.getText());
                control.setNombrempresa(txtNombre.getText());
                control.setNrolineabol(Integer.parseInt(txtLineaBoleta.getText()));
                control.setNrolineafac(Integer.parseInt(txtLineaFactura.getText()));
                control.setNrolineanc(Integer.parseInt(txtLineaNotaCred.getText()));
                control.setNrolineand(Integer.parseInt(txtLineaNotaDeb.getText()));
                control.setRutadb(txtRutaBD.getText());
                control.setRutaimagenes(txtRutaImagenes.getText());
                control.setRutaprogramas(txtRutaProgramas.getText());
                control.setRutareportes(txtRutaReportes.getText());
                control.setStock0(tx_stock0.getText().toCharArray()[0]);
                control.setRuc(txtRuc.getText());
                control.setCorreo(txtCorreo.getText());
                control.setUbidpto(dpto);
                control.setUbiprov(prov);
                control.setUbidist(dist);
                
                String moneda = String.valueOf(cbTipoMoneda.getSelectedItem());
                int tipMon = ( "Dolares".equals(moneda) ? DOLAR_COMBO : SOL_COMBO );
                control.setMonedarepuestos(tipMon);
                
                 // 0 = Dolares, 1 = Soles (Eterno, no se debe cambiar en BD para Tbl Repuestos)
                String tipoDocPorDef = String.valueOf(cbTipoDoc.getSelectedItem());
                control.setDocfactdefecto(tipoDocPorDef);
                
                Ubigeo ubigeo = new Ubigeo();
                ubigeo = new Servicio_Ubigeo().buscarUbigeo(dpto, prov, dist);
                control.setUbigeo(ubigeo);
                
                control.setVercontoperfact("S");
                control.setOpedef("R");
                control.setColor(1);
                
//                control.setOblCodSec("S");
//                control.setCondConLet("S");
//                control.setVerColLin("S");
//                control.setVerSin("N");

                boolean verific;

                verific = servicioControl.modificar_Control(control);
                if (verific == true) {
                    JOptionPane.showMessageDialog(null, "Actualización del Control del Sistema");
                } else {
                    JOptionPane.showMessageDialog(null, "Error en la actualización");
                }
            }
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtLineaNotaDebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLineaNotaDebActionPerformed

    }//GEN-LAST:event_txtLineaNotaDebActionPerformed

    private void combBoxDemandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combBoxDemandaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combBoxDemandaActionPerformed

    private void cbDepartamentosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDepartamentosItemStateChanged
        String dpto = String.valueOf(cbDepartamentos.getSelectedItem());
//        System.out.println("departamento seleccionado:" + dpto);
        Listar_Provincias(dpto);
        cbProvincias.setSelectedItem("");
        cbDistritos.setSelectedItem("");
    }//GEN-LAST:event_cbDepartamentosItemStateChanged

    private void cbProvinciasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProvinciasItemStateChanged
        String dpto = String.valueOf(cbDepartamentos.getSelectedItem());
        String prov = String.valueOf(cbProvincias.getSelectedItem());
//        System.out.println("departamento seleccionado:" + dpto);
//        System.out.println("provincia seleccionada:" + prov);
        Listar_Distritos(dpto, prov);
        cbDistritos.setSelectedItem("");
    }//GEN-LAST:event_cbProvinciasItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRutaBD;
    public javax.swing.JButton btnRutaImagenes;
    public javax.swing.JButton btnRutaProgramas;
    public javax.swing.JButton btnRutaReportes;
    public javax.swing.JButton btnSalir;
    public javax.swing.JComboBox cbDepartamentos;
    public javax.swing.JComboBox cbDistritos;
    public javax.swing.JComboBox cbProvincias;
    public javax.swing.JComboBox cbTipoDoc;
    public javax.swing.JComboBox cbTipoMoneda;
    public javax.swing.JComboBox combBoxDemanda;
    public javax.swing.JComboBox combBoxProyeccion;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel12;
    public javax.swing.JLabel jLabel13;
    public javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    public javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel20;
    public javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel22;
    public javax.swing.JLabel jLabel23;
    public javax.swing.JLabel jLabel24;
    public javax.swing.JLabel jLabel25;
    public javax.swing.JLabel jLabel26;
    public javax.swing.JLabel jLabel27;
    public javax.swing.JLabel jLabel28;
    public javax.swing.JLabel jLabel29;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel30;
    public javax.swing.JLabel jLabel31;
    public javax.swing.JLabel jLabel32;
    public javax.swing.JLabel jLabel33;
    public javax.swing.JLabel jLabel34;
    public javax.swing.JLabel jLabel35;
    public javax.swing.JLabel jLabel36;
    public javax.swing.JLabel jLabel37;
    public javax.swing.JLabel jLabel38;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel panelControlSistema;
    public javax.swing.JTextField tx_stock0;
    public javax.swing.JTextField txtAlmacen;
    public javax.swing.JTextField txtAlmacen2;
    public javax.swing.JTextField txtCorreo;
    public javax.swing.JTextField txtIGV;
    public javax.swing.JTextField txtLineaBoleta;
    public javax.swing.JTextField txtLineaFactura;
    public javax.swing.JTextField txtLineaNotaCred;
    public javax.swing.JTextField txtLineaNotaDeb;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtRuc;
    public javax.swing.JTextField txtRutaBD;
    public javax.swing.JTextField txtRutaImagenes;
    public javax.swing.JTextField txtRutaProgramas;
    public javax.swing.JTextField txtRutaReportes;
    public javax.swing.JTextField txtTelefonos;
    // End of variables declaration//GEN-END:variables
}
