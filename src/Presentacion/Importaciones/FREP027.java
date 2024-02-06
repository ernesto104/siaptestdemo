package Presentacion.Importaciones;

import Entidades.Importaciones.OperacionesTabla;
import Entidades.Importaciones.Ordenacion;
import Entidades.Repuestos;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import Servicios.Importaciones.Servicio_Repuestos;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JViewport;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class FREP027 extends javax.swing.JFrame {
    
    // Rango de fechas para obtener Demanda Anual (12 meses hacia atrás, hasta el anterior mes del presente)
    private Date fechaInicio;
    private Date fechaFin;
    
    private int mes;
    private int año;
    private int mesFinSQL;
    private int mesIniSQL;
    private int añoFinal;
    
    private int vectorMesXPosicion[];
    
    public FREP027() {
        
        initComponents();
        Ordenacion ord=new Ordenacion();
        ord.eventoTabla(this);
        
        setRangoFechasDemandaAnual();
        setCabeceraMesesDemanda();
        
        OperacionesTabla op=new OperacionesTabla(this);
        op.limpiarTabla();
 
        JOptionPane jop = new JOptionPane();
        
        if(op.insertarRepuestosATabla("",6)<=0){
            jop.showMessageDialog(null, "No se encontraron repuestos con cantidad distinta de cero",
                "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        ajustarAnchoColumnas();
        alinearCeldasADerecha();
    }
    
    public final void ajustarAnchoColumnas() {
        JViewport scroll = (JViewport) tbRepuestos.getParent();
        int ancho = scroll.getWidth();
        int anchoColumna = 0;
        TableColumnModel modeloColumna = tbRepuestos.getColumnModel();
        TableColumn columnaTabla;

        for (int i = 0; i < tbRepuestos.getColumnCount(); i++) {
            columnaTabla = modeloColumna.getColumn(i);
            switch (i) {
                case 0: // N° Parte
                    anchoColumna = (8 * ancho) / 100;
                    break;
                case 1: // Cod. Secundario
                    anchoColumna = (0 * ancho) / 100;
                    break;
                case 2: // Descripcion
                    anchoColumna = (9 * ancho) / 100;
                    break;
                case 3: // Aplicacion
                    anchoColumna = (14 * ancho) / 100;
                    break;
                case 4: // Stock
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 5: // Precio
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 6: // Marca
                    anchoColumna = (5 * ancho) / 100;
                    break;
                case 7: // Mes 1
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 8: // Mes 2
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 9: // Mes 3
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 10: // Mes 4
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 11: // Mes 5
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 12: // Mes 6
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 13: // Mes 7
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 14: // Mes 8
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 15: // Mes 9
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 16: // Mes 10
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 17: // Mes 11
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 18: // Mes 12
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 19: // Demanda Total
                    anchoColumna = (4 * ancho) / 100;
                    break;
                case 20: // Demanda Promedio
                    anchoColumna = (4 * ancho) / 100;
                    break;
            }
            columnaTabla.setMaxWidth(anchoColumna);
            columnaTabla.setMinWidth(anchoColumna);
            columnaTabla.setPreferredWidth(anchoColumna);
        }
    }
    
    // 2° Asignar la etiqueta en cabecera de JTable por cada mes para la demanda anual
    private void setCabeceraMesesDemanda() {
        int m = mesIniSQL;
        int c = 0;
        vectorMesXPosicion = new int[12];
        boolean llego = false;
        
// jsp hizo esto
//          mes = mes + 1;
        mes = mes;
//        mes = mes - 1;        
        
        while ( !llego ) {
            if ( mes == 12 ) {
                mes = 1;
                año = añoFinal;
            } else {
                mes++;
            }
            
            System.out.println("Aqui mes : " + mes);
            System.out.println("Aqui mesFinSQL : " + mesFinSQL);
            if ( mes == mesFinSQL ) {
                llego = true;
            }
            vectorMesXPosicion[c] = mes;
            setLabel(mes, c, año);
            c++;
        }
    }
    
    private void setLabel(int mes,int control,int año){

//        System.out.println("Mes Titulo al comienzo : " + mes);
//        System.out.println("Año Titulo al comienzo : " + año);
//        System.out.println("Control al comienzo : " + control);        
        
         mes = mes - 1;
//        String cadena[]={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio",
//            "Agosto","Setiembre","Octubre","Noviembre","Diciembre"};
        String cadena[] = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"};
        //String lblmes = cadena[mes] + String.valueOf(" " + año);
        String lblmes = "<html><center>" + cadena[mes] + "</center><center>" + String.valueOf(año) + "</center></html>";

        //System.out.println("lblMes:" + lblmes);
//        mes = mes - 1;
        System.out.println("Mes Titulo : " + mes);
        System.out.println("Año Titulo : " + año);
        System.out.println("Control : " + control);        
        System.out.println("lblmes : " + lblmes);
        
        
        switch ( control ) {
            case 0: setCabeceraDinamica(7, lblmes);break;
            case 1: setCabeceraDinamica(8, lblmes);break;
            case 2: setCabeceraDinamica(9, lblmes);break;
            case 3: setCabeceraDinamica(10, lblmes);break;
            case 4: setCabeceraDinamica(11, lblmes);break;
            case 5: setCabeceraDinamica(12, lblmes);break;
            case 6: setCabeceraDinamica(13, lblmes);break;
            case 7: setCabeceraDinamica(14, lblmes);break;
            case 8: setCabeceraDinamica(15, lblmes);break;
            case 9: setCabeceraDinamica(16, lblmes);break;
            case 10: setCabeceraDinamica(17, lblmes);break;
            case 11: setCabeceraDinamica(18, lblmes);break;
        }
    }
    
    private void setCabeceraDinamica(int numeroColumna, String valor) {
        JTableHeader tableHeader = tbRepuestos.getTableHeader();
        TableColumnModel tableColumnModel = tableHeader.getColumnModel();
        TableColumn tableColumn = tableColumnModel.getColumn(numeroColumna);
        tableColumn.setHeaderValue( valor );
        tableHeader.repaint();
    }
    
    // 1° Obtener rango de fechas hasta la fecha actual (corriendo para atrás 12 meses, desde mes anterior al actual)
    private void setRangoFechasDemandaAnual() {
        Calendar ahoraCal = Calendar.getInstance();
        año = ahoraCal.get(Calendar.YEAR);
// Se sumo 1 al mes
        mes = ahoraCal.get(Calendar.MONTH) + 1;
        añoFinal = año;
        int mesFinal = mes;
        int añoIni = 0;
        int mesIni = 0;
        
        if ( mes < 12 ) {
            añoIni = año - 1;
            mesIni = mes + 1;
        } else {
            añoIni = año;
            mesIni = 01;
        }
        
        int diaIni = 1;
        int diaFinal = getDiaFinal(mesFinal - 1);
        mesIniSQL = mesIni;
        mesFinSQL = mesFinal;
        año = añoIni;
        fechaInicio = formatearAFechaSQL(añoIni,mesIniSQL,diaIni);
        fechaFin = formatearAFechaSQL(añoFinal,mesFinSQL,diaFinal);
        
    }
    
    private Date formatearAFechaSQL(int año, int mes,int dia){
        java.sql.Date sqlDate=null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String date = String.valueOf(año) + "/" + String.valueOf(mes) + "/" + String.valueOf(dia);
            java.util.Date utilDate = formatter.parse(date);
            sqlDate= new java.sql.Date(utilDate.getTime());
            return sqlDate;
            
        } catch (ParseException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return sqlDate;
    }
    
    private int getDiaFinal(int mesFinal){
        int dias=0;
        switch(mesFinal){
            case 0: dias=31; break;
            case 1: dias=29; break;
            case 2: dias=31; break;
            case 3: dias=30; break;
            case 4: dias=31; break;
            case 5: dias=30; break;
            case 6: dias=31; break;
            case 7: dias=31; break;
            case 8: dias=30; break;
            case 9: dias=31; break;
            case 10: dias=30;break;
            case 11: dias=31;break;
        }
        return dias;
    }
    
    private void alinearCeldasADerecha(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        tbRepuestos.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tbRepuestos.getColumnModel().getColumn(5).setCellRenderer(tcr);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMaestro = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        btnFiltrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRepuestos = new javax.swing.JTable();
        btnDemanda = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panelMaestro.setPreferredSize(new java.awt.Dimension(1244, 716));

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnFiltrar.setText("FILTRAR");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        tbRepuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº PARTE ▲▼", "COD. SECUNDARIO ▲▼", "DESCRIPCION ▲▼", "APLICACION", "STOCK", "PRECIO", "MARCA", "MES1", "MES2", "MES3", "MES4", "MES5", "MES6", "MES7", "MES8", "MES9", "MES10", "MES11", "MES12", "TOT", "PROM"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbRepuestos);

        btnDemanda.setText("VER DEMANDA");
        btnDemanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDemandaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONSULTA DE DEMANDAS");

        javax.swing.GroupLayout panelMaestroLayout = new javax.swing.GroupLayout(panelMaestro);
        panelMaestro.setLayout(panelMaestroLayout);
        panelMaestroLayout.setHorizontalGroup(
            panelMaestroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelMaestroLayout.createSequentialGroup()
                .addGroup(panelMaestroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMaestroLayout.createSequentialGroup()
                        .addGap(490, 490, 490)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMaestroLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1322, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 22, Short.MAX_VALUE))
        );
        panelMaestroLayout.setVerticalGroup(
            panelMaestroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMaestroLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 591, Short.MAX_VALUE)
                .addGap(14, 14, 14)
                .addGroup(panelMaestroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDemanda, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 1354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMaestro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    }//GEN-LAST:event_formWindowClosing
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        frmiBuscarRepuestos frmiBuscar=new frmiBuscarRepuestos(this);
        frmiBuscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed
    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        frmiFiltrarRepuestos frmiFiltrar=new frmiFiltrarRepuestos(this);
        frmiFiltrar.setVisible(true);
    }//GEN-LAST:event_btnFiltrarActionPerformed
    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
       setVisible(false);
    }//GEN-LAST:event_btnSalirActionPerformed
    private void btnDemandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDemandaActionPerformed
        int filaSeleccionada = tbRepuestos.getSelectedRow();
        
        if ( filaSeleccionada != -1 ) {
            Servicio_Repuestos sr = new Servicio_Repuestos();
            String nameRep=String.valueOf(tbRepuestos.getModel().getValueAt(filaSeleccionada,0));
            Repuestos rep=new Repuestos();
            rep=sr.buscaRepuestoXNombre(nameRep);
            IU_Demanda iudemanda = null;
            try {
                iudemanda = new IU_Demanda(this,rep);
                iudemanda.setVisible(true);
            } catch (ParseException ex) {
                Logger.getLogger(FREP027.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane jop = new JOptionPane();
            jop.showMessageDialog(null, "Seleccione un repuesto de la tabla",
                "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnDemandaActionPerformed

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int[] getVectorMesXPosicion() {
        return vectorMesXPosicion;
    }

    public void setVectorMesXPosicion(int[] vectorMesXPosicion) {
        this.vectorMesXPosicion = vectorMesXPosicion;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnDemanda;
    public javax.swing.JButton btnFiltrar;
    public javax.swing.JButton btnSalir;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JPanel panelMaestro;
    public javax.swing.JTable tbRepuestos;
    // End of variables declaration//GEN-END:variables
}