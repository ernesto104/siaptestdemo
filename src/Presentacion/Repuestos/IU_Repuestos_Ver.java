package Presentacion.Repuestos;

import Entidades.Repuestos;
import Servicios.Servicio_Excel;
import Servicios.Servicio_Repuesto;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author Lesly Aguilar
 */
public class IU_Repuestos_Ver extends javax.swing.JFrame {

    private DefaultTableModel modelo;
    String marca;
    String grupo;
    int idL;
    private JTableHeader header;
    private JTableHeader header2;
    private JTableHeader header3;    
    private JPopupMenu renamePopup;
    private JPopupMenu renamePopup2;
    private JPopupMenu renamePopup3;    
    private JTextField text;
    private JTextField text2;
    private JTextField text3;    
    private TableColumn column;
    private TableColumn column2;
    private TableColumn column3;    
    Servicio_Repuesto s;
    Servicio_Excel servicio_Excel;

    public IU_Repuestos_Ver(String marc, String grup, int idL) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.marca = marc;
        this.grupo = grup;
        this.idL = idL;

        modelo = (DefaultTableModel) tblRepuestos.getModel();
        ((JLabel) tblRepuestos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        s = new Servicio_Repuesto();

        if (!marc.equals("") && grup.equals("") && idL == 0) {
          //  System.out.println("entr");
            s.getListaPorMarca(marc, modelo);
        }
        if (!grup.equals("") && marc.equals("") && idL == 0) {
            s.getListaPorGrupo(grup, modelo);
        }
        if (idL != 0 && grup.equals("") && marc.equals("")) {
            s.getListaPorIDLinea(idL, modelo);
        }
        if (marc.equals("") && grup.equals("") && idL == 0) {
            s.getListaTodos(modelo);
        }

        SetParametrosTableHeader();
        SetParametrosTableHeader2();
        SetParametrosTableHeader3();        

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                setVisible(false);
            }
        });
        
        servicio_Excel = new Servicio_Excel(tblRepuestos, this);
        
        btnMasDatos.requestFocus();
    }

    private void SetParametrosTableHeader() {
        header = tblRepuestos.getTableHeader();
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    editColumnAt(event.getPoint());
                }
            }

            private void editColumnAt(Point point) {
                int columnIndex = header.columnAtPoint(point);

                if (columnIndex == 1) {
                    column = header.getColumnModel().getColumn(columnIndex);
                    Rectangle columnRectangle = header.getHeaderRect(columnIndex);

                    text.setText(column.getHeaderValue().toString());
                    renamePopup.setPreferredSize(
                            new Dimension(columnRectangle.width, columnRectangle.height - 1));
                    renamePopup.show(header, columnRectangle.x, 0);

                    text.requestFocusInWindow();
                    text.selectAll();
                }
            }
        });

        text = new JTextField();
        text.setBorder(null);
        text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c >= 'a' && c <= 'z') {
                    e.setKeyChar((char) (c - 32));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarxNombre();
            }

            private void BuscarxNombre() {
                String nombre = text.getText();
                if (text.getText().equals("")) {
                    text.setText("Nombre (Buscar)");
                }
                column.setHeaderValue(text.getText());
                renamePopup.setVisible(false);
                header.repaint();
                BorrarTabla();

                s.ListarxMarca(modelo, nombre);

            }
        });

        renamePopup = new JPopupMenu();
        renamePopup.setBorder(new MatteBorder(0, 1, 1, 1, Color.DARK_GRAY));
        renamePopup.add(text);

    }

    private void SetParametrosTableHeader2() {
        header2 = tblRepuestos.getTableHeader();
        header2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    editColumnAt(event.getPoint());
                }
            }

            private void editColumnAt(Point point) {
                int columnIndex = header2.columnAtPoint(point);

                if (columnIndex == 3) {
                    column2 = header2.getColumnModel().getColumn(columnIndex);
                    Rectangle columnRectangle = header2.getHeaderRect(columnIndex);

                    text2.setText(column2.getHeaderValue().toString());
                    renamePopup2.setPreferredSize(
                            new Dimension(columnRectangle.width, columnRectangle.height - 1));
                    renamePopup2.show(header2, columnRectangle.x, 0);

                    text2.requestFocusInWindow();
                    text2.selectAll();
                }
            }
        });

        text2 = new JTextField();
        text2.setBorder(null);
        text2.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c >= 'a' && c <= 'z') {
                    e.setKeyChar((char) (c - 32));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        text2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarxNombre();
            }

            private void BuscarxNombre() {
                String nombre = text2.getText();
                if (text2.getText().equals("")) {
                    text2.setText("Descripción (Buscar)");
                }
                column2.setHeaderValue(text2.getText());
                renamePopup2.setVisible(false);
                header2.repaint();
                BorrarTabla();

                s.ListarxDescripcion(modelo, nombre);

            }
        });

        renamePopup2 = new JPopupMenu();
        renamePopup2.setBorder(new MatteBorder(0, 1, 1, 1, Color.DARK_GRAY));
        renamePopup2.add(text2);

    }
   

/////
    private void SetParametrosTableHeader3() {
        header3 = tblRepuestos.getTableHeader();
        header3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    editColumnAt(event.getPoint());
                }
            }

            private void editColumnAt(Point point) {
                int columnIndex = header3.columnAtPoint(point);

                if (columnIndex == 4) {
                    column3 = header3.getColumnModel().getColumn(columnIndex);
                    Rectangle columnRectangle = header3.getHeaderRect(columnIndex);

                    text3.setText(column3.getHeaderValue().toString());
                    renamePopup3.setPreferredSize(
                            new Dimension(columnRectangle.width, columnRectangle.height - 1));
                    renamePopup3.show(header3, columnRectangle.x, 0);

                    text3.requestFocusInWindow();
                    text3.selectAll();
                }
            }
        });

        text3 = new JTextField();
        text3.setBorder(null);
        text3.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c >= 'a' && c <= 'z') {
                    e.setKeyChar((char) (c - 32));
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        text3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarxNombre();
            }

            private void BuscarxNombre() {
                String nombre = text3.getText();
                if (text3.getText().equals("")) {
                    text3.setText("Aplicacion (Buscar)");
                }
                column3.setHeaderValue(text3.getText());
                renamePopup3.setVisible(false);
                header3.repaint();
                BorrarTabla();

                s.ListarxDescrmodelo(modelo, nombre);

            }
        });

        renamePopup3 = new JPopupMenu();
        renamePopup3.setBorder(new MatteBorder(0, 1, 1, 1, Color.DARK_GRAY));
        renamePopup3.add(text3);

    }
    
////    
    
    
    private void BorrarTabla() {
        int numRows = modelo.getRowCount();
        for (int i = 0; i < numRows; i++) {
            modelo.removeRow(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMostrar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRepuestos = new javax.swing.JTable();
        btnMasDatos = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("MOSTRAR REPUESTOS");

        tblRepuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod. Fab.", "Nro. Parte (Buscar)", "Cod. Sec.", "Descripción (Buscar)", "Aplicacion (Buscar)", "Stock", "Precio", "Marca"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRepuestos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblRepuestos);
        if (tblRepuestos.getColumnModel().getColumnCount() > 0) {
            tblRepuestos.getColumnModel().getColumn(0).setMinWidth(18);
            tblRepuestos.getColumnModel().getColumn(0).setPreferredWidth(18);
            tblRepuestos.getColumnModel().getColumn(3).setMinWidth(250);
            tblRepuestos.getColumnModel().getColumn(3).setPreferredWidth(250);
            tblRepuestos.getColumnModel().getColumn(3).setMaxWidth(250);
            tblRepuestos.getColumnModel().getColumn(4).setMinWidth(200);
            tblRepuestos.getColumnModel().getColumn(4).setPreferredWidth(200);
            tblRepuestos.getColumnModel().getColumn(4).setMaxWidth(200);
            tblRepuestos.getColumnModel().getColumn(5).setMinWidth(10);
            tblRepuestos.getColumnModel().getColumn(5).setPreferredWidth(10);
            tblRepuestos.getColumnModel().getColumn(6).setMinWidth(12);
            tblRepuestos.getColumnModel().getColumn(6).setPreferredWidth(12);
        }

        btnMasDatos.setText("Mas Datos");
        btnMasDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasDatosActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.setNextFocusableComponent(btnMasDatos);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMostrarLayout = new javax.swing.GroupLayout(panelMostrar);
        panelMostrar.setLayout(panelMostrarLayout);
        panelMostrarLayout.setHorizontalGroup(
            panelMostrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMostrarLayout.createSequentialGroup()
                .addGroup(panelMostrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMostrarLayout.createSequentialGroup()
                        .addGap(335, 335, 335)
                        .addComponent(jLabel1))
                    .addGroup(panelMostrarLayout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(btnMasDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(343, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        panelMostrarLayout.setVerticalGroup(
            panelMostrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMostrarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelMostrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMasDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMasDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasDatosActionPerformed

        if (tblRepuestos.getSelectedRow() != -1) {
            String nombre = (String) tblRepuestos.getValueAt(tblRepuestos.getSelectedRow(), 1);
            Servicio_Repuesto servicio = new Servicio_Repuesto();
            Repuestos r = servicio.obtenerRepuestoPorNombre(nombre);

            IU_Repuestos_MasDatos iu = new IU_Repuestos_MasDatos(r);
            iu.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un repuesto de la tabla", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnMasDatosActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMasDatos;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelMostrar;
    private javax.swing.JTable tblRepuestos;
    // End of variables declaration//GEN-END:variables
}
