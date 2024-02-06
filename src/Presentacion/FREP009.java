
package Presentacion;

import Entidades.Bancos;
import Mantenimiento.Navegacion;
import Servicios.Centrar_Cabeceras;
import Servicios.Servicio_Banco;
import Servicios.TipoMensaje;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.RowSorter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Administrador
 */
public class FREP009 extends javax.swing.JFrame {

    public JTextField[] componentes;
    private DefaultTableModel table;
    private Servicio_Banco sb;
    TipoMensaje tm;
    Navegacion navegacion;
    private final ArrayList<Integer> numMaximo;
    private final ArrayList<String> tipoDato;
    boolean modificar;
    RowSorter<TableModel> sorter;

    public FREP009() {
        initComponents();
        numMaximo = new ArrayList<>();
        tipoDato = new ArrayList<>();
        table = (DefaultTableModel) tb_bancos.getModel();
        
        sorter = new TableRowSorter<TableModel>(table);
        tb_bancos.setRowSorter(sorter);

        sb = new Servicio_Banco(this);
        this.setLocationRelativeTo(null);
        crearArrayComponente();
        crearArrayNumMax();
        crearArrayTipoDato();
        tm = new TipoMensaje();
        navegacion = new Navegacion(componentes, numMaximo, tipoDato, bt_agregar);
        sb.listar();
        asignarEvento();
        setAnchoColumnas();
        modificar = false;
        centrarCabeceras();
        tx_siglas.setNextFocusableComponent(txtAbreviatura);
        txtAbreviatura.setNextFocusableComponent(tx_funcionario);
    }

    private void centrarCabeceras() {
        TableCellRenderer tcr = tb_bancos.getTableHeader().getDefaultRenderer();
        tb_bancos.getTableHeader().setDefaultRenderer(new Centrar_Cabeceras(tcr));

    }

    private void crearArrayComponente() {
        componentes = new JTextField[6];
        componentes[0] = tx_nombre;
        componentes[1] = tx_siglas;
        componentes[2] = tx_funcionario;
        componentes[3] = tx_telefono;
        componentes[4] = tx_celular;
        componentes[5] = txtAbreviatura;
    }

    private void crearArrayNumMax() {
        numMaximo.add(35);
        numMaximo.add(3);
        numMaximo.add(40);
        numMaximo.add(20);
        numMaximo.add(20);
        numMaximo.add(3);
    }

    private void crearArrayTipoDato() {
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("S");
        tipoDato.add("S");
    }

    private void asignarEvento() {
        for (int i = 0; i < componentes.length; i++) {
            componentes[i].addKeyListener(navegacion);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBancos = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tx_nombre = new javax.swing.JTextField();
        tx_funcionario = new javax.swing.JTextField();
        tx_telefono = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tx_celular = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tx_siglas = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtAbreviatura = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_bancos = new javax.swing.JTable();
        bt_agregar = new javax.swing.JButton();
        bt_Actualizar = new javax.swing.JButton();
        bt_eliminar = new javax.swing.JButton();
        bt_limpiar = new javax.swing.JButton();
        bt_salir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mantenimiento de Bancos");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Banco"));

        jLabel3.setText("Nombre");

        jLabel4.setText("Funcionario");

        jLabel5.setText("Telefono");

        jLabel6.setText("Celular");

        jLabel1.setText("(*)");

        jLabel7.setText("(*)");

        jLabel9.setText("Siglas");

        jLabel2.setText("(*)");

        jLabel10.setText("Abreviatura");

        txtAbreviatura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAbreviaturaKeyTyped(evt);
            }
        });

        jLabel11.setText("(*)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tx_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tx_celular, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tx_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tx_siglas, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtAbreviatura)
                        .addGap(4, 4, 4)
                        .addComponent(jLabel11)))
                .addGap(0, 21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_siglas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtAbreviatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tx_funcionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tx_celular, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tx_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );

        tb_bancos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Siglas", "Funcionario", "Telefono", "Celular", "Abreviatura"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_bancos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tb_bancos.getTableHeader().setReorderingAllowed(false);
        tb_bancos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tb_bancosMouseReleased(evt);
            }
        });
        tb_bancos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tb_bancosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tb_bancos);
        if (tb_bancos.getColumnModel().getColumnCount() > 0) {
            tb_bancos.getColumnModel().getColumn(2).setPreferredWidth(10);
            tb_bancos.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        bt_agregar.setMnemonic('c');
        bt_agregar.setText("Agregar");
        bt_agregar.setToolTipText("Agrega nuevo Banco");
        bt_agregar.setPreferredSize(new java.awt.Dimension(92, 32));
        bt_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_agregarActionPerformed(evt);
            }
        });

        bt_Actualizar.setMnemonic('A');
        bt_Actualizar.setText("Modificar");
        bt_Actualizar.setToolTipText("Actualiza la informacion de Bancos");
        bt_Actualizar.setPreferredSize(new java.awt.Dimension(92, 32));
        bt_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ActualizarActionPerformed(evt);
            }
        });

        bt_eliminar.setMnemonic('E');
        bt_eliminar.setText("Eliminar");
        bt_eliminar.setToolTipText("Elimina un Banco");
        bt_eliminar.setPreferredSize(new java.awt.Dimension(92, 32));
        bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_eliminarActionPerformed(evt);
            }
        });

        bt_limpiar.setMnemonic('E');
        bt_limpiar.setText("Limpiar");
        bt_limpiar.setToolTipText("Elimina un Banco");
        bt_limpiar.setPreferredSize(new java.awt.Dimension(92, 32));
        bt_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limpiarActionPerformed(evt);
            }
        });

        bt_salir.setMnemonic('S');
        bt_salir.setText("Salir");
        bt_salir.setToolTipText("Salir de la ventana");
        bt_salir.setPreferredSize(new java.awt.Dimension(92, 32));
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("BANCOS");

        javax.swing.GroupLayout panelBancosLayout = new javax.swing.GroupLayout(panelBancos);
        panelBancos.setLayout(panelBancosLayout);
        panelBancosLayout.setHorizontalGroup(
            panelBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBancosLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBancosLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBancosLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(bt_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelBancosLayout.setVerticalGroup(
            panelBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBancosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBancosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(bt_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBancos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBancos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ActualizarActionPerformed
        modificar = true;
        int fila = tb_bancos.getSelectedRow();
        if ( fila >= 0 ) {
            String validacion = validarEntradas();
            
            if ( validacion.equals(tm.VALIDO) ) {
                int opcion = tm.Confirmacion(tm.PREGUNTA_OPERACION);
                
                if ( opcion == tm.SI ) {
                    String nombre = tx_nombre.getText();
                    String func = tx_funcionario.getText();
                    String tel = tx_telefono.getText();
                    String cel = tx_celular.getText();
                    String sigla = tx_siglas.getText();
                    String abreviatura = txtAbreviatura.getText();
                    
                    Bancos t = sb.getBanco_Nombre(String.valueOf(table.getValueAt(fila, 0)));
                    t.setNombre(nombre);
                    t.setFuncionario(func);
                    t.setTelefono(tel);
                    t.setCelular(cel);
                    t.setSiglas(sigla);
                    t.setBanco(abreviatura);
                    
                    if ( sb.actualizarBanco(t) ) {
                        tm.Mensaje(tm.EXITO_MODIFICAR);
                        actualizarFila(fila);
                        clean();
                        
                    } else {
                        tm.Error(tm.ERROR_MODIFICAR);
                    }
                }
            } else {
                tm.Error(validacion);
            }
        } else {
            tm.Error(tm.SELECCION_REGISTRO);
        }
        modificar = false;
    }//GEN-LAST:event_bt_ActualizarActionPerformed

    private void bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_eliminarActionPerformed
        int fila = tb_bancos.getSelectedRow();
        if ( fila >= 0 ) {
            int opcion = tm.Confirmacion(tm.PREGUNTA_OPERACION);
            
            if ( opcion == tm.SI ) {
                if ( sb.borrarBanco(sb.getBanco_Nombre(String.valueOf(table.getValueAt(fila, 0)))) ) {
                    tm.Mensaje(tm.EXITO_ELIMINAR);
                    table.removeRow(fila);
                    tb_bancos.clearSelection();
                    clean();
                    
                } else {
                    tm.Error(tm.ERROR_ELIMINAR + "\n- BANCO ASOCIADO PAGOS Y/O FACTURA");
                }
            }
        } else {
            tm.Error(tm.SELECCION_REGISTRO);
        }
    }//GEN-LAST:event_bt_eliminarActionPerformed

    private void bt_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_agregarActionPerformed
        String validacion = validarEntradas();
        if ( validacion.equals(tm.VALIDO) ) {
            int opcion = tm.Confirmacion(tm.PREGUNTA_OPERACION);
            
            if ( opcion == tm.SI ) {
                String nombre = tx_nombre.getText();
                String func = tx_funcionario.getText();
                String tel = tx_telefono.getText();
                String cel = tx_celular.getText();
                String sigla = tx_siglas.getText();
                String abreviatura = txtAbreviatura.getText();
                
                Bancos t = new Bancos();
                t.setNombre(nombre);
                t.setFuncionario(func);
                t.setTelefono(tel);
                t.setCelular(cel);
                t.setSiglas(sigla);
                t.setBanco(abreviatura);
                
                if ( sb.addBanco(t) ) {
                    tm.Mensaje(tm.EXITO_AGREGAR);
                    Object[] row = {nombre, sigla, func, tel, cel, abreviatura};
                    table.addRow(row);
                    tx_nombre.requestFocus();
                    clean();
                    
                } else {
                    tm.Error(tm.ERROR_AGREGAR);
                }
            }
        } else {
            tm.Error(validacion);
        }
    }//GEN-LAST:event_bt_agregarActionPerformed

    private void bt_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limpiarActionPerformed
        clean();
        tb_bancos.clearSelection();
        tx_nombre.requestFocus();
    }//GEN-LAST:event_bt_limpiarActionPerformed

    private void tb_bancosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_bancosMouseReleased
        Informacion_Banco();
    }//GEN-LAST:event_tb_bancosMouseReleased

    private void tb_bancosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tb_bancosKeyReleased
        if ( evt.getKeyCode() == 38 || evt.getKeyCode() == 40 ) {
            Informacion_Banco();
        }
    }//GEN-LAST:event_tb_bancosKeyReleased

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    private void txtAbreviaturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAbreviaturaKeyTyped
        if ( txtAbreviatura.getText().length() >= 3 ) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAbreviaturaKeyTyped
    private void Informacion_Banco() {
        int fila = tb_bancos.getSelectedRow();
        fila = sorter.convertRowIndexToModel(fila);
        Bancos temp = sb.getBanco_Nombre(String.valueOf(table.getValueAt(fila, 0)));
        tx_nombre.setText(temp.getNombre());
        tx_siglas.setText(temp.getSiglas());
        tx_funcionario.setText(temp.getFuncionario());
        tx_telefono.setText(temp.getTelefono());
        tx_celular.setText(temp.getCelular() == null ? "" : temp.getCelular());
        txtAbreviatura.setText(temp.getBanco());
    }

    private void clean() {
        for ( JTextField tx : componentes ) {
            tx.setText("");
        }
    }

    private String validarEntradas() {
        String error = "ERROR";
        boolean a = true;
        if ( tx_nombre.getText().equals("") ) {
            error += "\n- COMPLETE EL CAMPO NOMBRE";
            a = false;
        }
        if ( tx_siglas.getText().equals("") ) {
            error += "\n- COMPLETE EL CAMPO SIGLAS";
        }
        if ( txtAbreviatura.getText().equals("") ) {
            error += "\n- COMPLETE EL CAMPO ABREVIATURA";
        }
        if ( tx_funcionario.getText().equals("") ) {
            error += "\n- COMPLETE EL CAMPO FUNCIONARIO";
        }
        if ( !tx_telefono.getText().equals("") && tx_telefono.getText().length() < 7 ) {
            error += "\n- INGRESE UN NUMERO DE TELEFONO CORRECTO";
        }
        if ( !tx_celular.getText().equals("") && tx_celular.getText().length() < 9 ) {
            error += "\n- INGRESE UN NUMERO CELULAR CORRECTO";
        }
        if ( !txtAbreviatura.getText().equals("") && txtAbreviatura.getText().length() > 3 ) {
            error += "\n- INGRESE UNA ABREVIATURA CORRECTA";
        }
        
        if ( a && !modificar ) {
            if ( sb.getBanco_Nombre(tx_nombre.getText()) != null ) {
                error += "\n- BANCO YA REGISTRADO, INGRESE OTRO";
            }
        }

        if ( error.equals("ERROR") ) {
            return tm.VALIDO;
            
        } else {
            return error;
        }
    }

    public void actualizarFila(int fila) {
        Object[] row = new Object[6];
        row[0] = tx_nombre.getText();
        row[1] = tx_siglas.getText();
        row[2] = tx_funcionario.getText();
        row[3] = tx_telefono.getText();
        row[4] = tx_celular.getText();
        row[5] = txtAbreviatura.getText();

        table.removeRow(fila);
        table.insertRow(fila, row);
        tb_bancos.clearSelection();
    }

    public final void setAnchoColumnas() {
        JViewport scroll = (JViewport) tb_bancos.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tb_bancos.getColumnModel();
        TableColumn columnaTabla;
        
        for ( int i = 0; i < tb_bancos.getColumnCount(); i++ ) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 1:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 2:
                    anchoColumna = (30 * ancho) / 100;
                    break;
                case 3:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 4:
                    anchoColumna = (10 * ancho) / 100;
                    break;
                case 5:
                    anchoColumna = (10 * ancho) / 100;
            }
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton bt_Actualizar;
    public javax.swing.JButton bt_agregar;
    public javax.swing.JButton bt_eliminar;
    public javax.swing.JButton bt_limpiar;
    public javax.swing.JButton bt_salir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    public javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel2;
    public javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    public javax.swing.JLabel jLabel7;
    public javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelBancos;
    public javax.swing.JTable tb_bancos;
    public javax.swing.JTextField tx_celular;
    public javax.swing.JTextField tx_funcionario;
    public javax.swing.JTextField tx_nombre;
    public javax.swing.JTextField tx_siglas;
    public javax.swing.JTextField tx_telefono;
    public javax.swing.JTextField txtAbreviatura;
    // End of variables declaration//GEN-END:variables
}