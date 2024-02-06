package Presentacion.IngresoSalidas;

import Entidades.Clientes;
import Entidades.Ubigeo;
import Servicios.Servicio_Cliente;
import Servicios.Servicio_Ubigeo;
import Servicios.TipoMensaje;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class IU_NuevoProv extends javax.swing.JFrame {

    TipoMensaje tm = new TipoMensaje();
    Servicio_Cliente servicioC = new Servicio_Cliente(null);
    public Clientes prov;
    
    //Ubigeo
    private Servicio_Ubigeo servicioUbigeo;
    private DefaultComboBoxModel modelDptoCombo;
    private DefaultComboBoxModel modelProvCombo;
    private DefaultComboBoxModel modelDistCombo;
    private List listaDptos;
    private List listaProv;
    private List listaDist;

    public IU_NuevoProv() {
        initComponents();
        inicializarUbigeo();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void inicializarUbigeo() {
        servicioUbigeo = new Servicio_Ubigeo();
        Listar_Departamentos();
        Listar_Provincias("");
        Listar_Distritos("", "");
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
    
    private void mostrarUbigeo(Clientes cli) {
        Ubigeo ubigeo = cli.getUbigeo();
        String dpto = ubigeo.getDepartamento();
        String prov = ubigeo.getProvincia();
        String dist = ubigeo.getDistrito();
        cbDepartamentos.setSelectedItem(dpto);
        cbProvincias.setSelectedItem(prov);
        cbDistritos.setSelectedItem(dist);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tx_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tx_direccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tx_ruc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tx_telefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tx_email = new javax.swing.JTextField();
        bt_registrar = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbDepartamentos = new javax.swing.JComboBox();
        cbProvincias = new javax.swing.JComboBox();
        cbDistritos = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nuevo Proveedor");

        jLabel2.setText("Nombre (*)");

        jLabel3.setText("Direccion (*)");

        jLabel4.setText("RUC (*)");

        jLabel5.setText("Telefono");

        jLabel6.setText("Email");

        bt_registrar.setText("Registrar");
        bt_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_registrarActionPerformed(evt);
            }
        });

        bt_cancelar.setText("Salir");
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        jLabel7.setText("Departamento (*)");

        jLabel8.setText("Provincia (*)");

        jLabel9.setText("Distrito (*)");

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(57, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7))
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbDepartamentos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbProvincias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbDistritos, 0, 182, Short.MAX_VALUE))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(tx_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(tx_email))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(tx_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(tx_nombre))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(tx_direccion)))
                        .addGap(54, 54, 54))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(bt_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_ruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(cbDepartamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbProvincias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cbDistritos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_registrarActionPerformed
        String valido = validar();
        
        if ( valido.equals(tm.VALIDO) ) {
            prov = new Clientes();
            prov.setNombre(tx_nombre.getText());
            prov.setDireccion(tx_direccion.getText());
            prov.setRuc(tx_ruc.getText());
            prov.setTelefonofijo(tx_telefono.getText());
            prov.setEmail(tx_email.getText());
            prov.setTipo("P");
            prov.setCredito(0.00); // Valor considerado por defecto
            prov.setDescuento1(0.00);
            prov.setDescuento2(0.00);
            prov.setDescuento3(0.00);
            prov.setDescuento4(0.00);
            
            String dpto = String.valueOf(cbDepartamentos.getSelectedItem());
            String provincia = String.valueOf(cbProvincias.getSelectedItem());
            String distrito = String.valueOf(cbDistritos.getSelectedItem());
            Ubigeo ubigeo = servicioUbigeo.buscarUbigeo(dpto, provincia, distrito);
            prov.setUbigeo(ubigeo);

            Clientes Cliente_Nuevo = servicioC.getCliente_x_RUC(tx_ruc.getText());
            
            if ( Cliente_Nuevo == null ) {
                if ( servicioC.insertar_Cliente(prov) ) {
                    tm.Mensaje(tm.EXITO_AGREGAR);
                    dispose();
                    
                } else {
                    prov = null;
                    tm.Error(tm.ERROR_AGREGAR);
                }
                
            } else {
                Cliente_Nuevo.setTipo("A");
                
                if ( servicioC.actualizar_Cliente(Cliente_Nuevo) ) {
                    tm.Mensaje(tm.EXITO_MODIFICAR);
                    dispose();
                    
                } else {
                    prov = null;
                    tm.Error(tm.ERROR_AGREGAR);
                }
            }

            this.setVisible(false);
            IU_SeleccionProveedor sp = new IU_SeleccionProveedor();
            sp.setVisible(true);

        } else {
            tm.Error(valido);
        }
    }//GEN-LAST:event_bt_registrarActionPerformed

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed
        this.setVisible(false);
        IU_SeleccionProveedor sp = new IU_SeleccionProveedor();
        sp.setVisible(true);
    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void cbDepartamentosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbDepartamentosItemStateChanged
        String dpto = String.valueOf(cbDepartamentos.getSelectedItem());
//        System.out.println("(Dpto)dpto:" + dpto);
        Listar_Provincias(dpto);
        cbProvincias.setSelectedItem("");
        cbDistritos.setSelectedItem("");
    }//GEN-LAST:event_cbDepartamentosItemStateChanged

    private void cbProvinciasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProvinciasItemStateChanged
        String dpto = String.valueOf(cbDepartamentos.getSelectedItem());
        String prov = String.valueOf(cbProvincias.getSelectedItem());
//        System.out.println("(Prov)dpto:" + dpto);
//        System.out.println("(Prov)prov:" + prov);
        Listar_Distritos(dpto, prov);
        cbDistritos.setSelectedItem("");
    }//GEN-LAST:event_cbProvinciasItemStateChanged

    private String validar() {
        String error = "ERROR";
        if (tx_nombre.getText().equals("")) {
            error += "\n-  COMPLETE EL CAMPO NOMBRE";
        }
        if ( "".equals(cbDepartamentos.getSelectedItem()) ) {
            error += "\n-  SELECCIONE EL DEPARTAMENTO";
        }
        if ( "".equals(cbProvincias.getSelectedItem()) ) {
            error += "\n-  SELECCIONE LA PROVINCIA";
        }
        if ( "".equals(cbDistritos.getSelectedItem()) ) {
            error += "\n-  SELECCIONE EL DISTRITO";
        }
        if (tx_direccion.getText().equals("")) {
            error += "\n-  COMPLETE EL CAMPO DIRECCION";
        }
        if (tx_ruc.getText().equals("")) {
            error += "\n-  COMPLETE EL CAMPO RUC";
        } else {
            try {
                double number = Double.parseDouble(tx_ruc.getText());
                if (tx_ruc.getText().length() != 11) {
                    error += "\n-  LONGITUD DEL NUMERO DE RUC INCORRECTA";
                }
            } catch (NumberFormatException e) {
                error += "\n-  INGRESE UN NUMERO RUC CORRECTO";
            }

        }
        if (error.equals("ERROR")) {
            return tm.VALIDO;
        } else {
            return error;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_registrar;
    private javax.swing.JComboBox cbDepartamentos;
    private javax.swing.JComboBox cbDistritos;
    private javax.swing.JComboBox cbProvincias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tx_direccion;
    private javax.swing.JTextField tx_email;
    private javax.swing.JTextField tx_nombre;
    private javax.swing.JTextField tx_ruc;
    private javax.swing.JTextField tx_telefono;
    // End of variables declaration//GEN-END:variables
}
