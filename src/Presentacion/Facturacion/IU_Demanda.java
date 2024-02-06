package Presentacion.Facturacion;

import Entidades.Repuestos;
import Servicios.Servicio_Demanda;
import java.util.GregorianCalendar;
import javax.swing.JLabel;

public class IU_Demanda extends javax.swing.JFrame {

    private JLabel[] lab;
    private JLabel[] txt;
    private Servicio_Demanda servicio;

    public IU_Demanda(Repuestos rep) {
        this();
        servicio = new Servicio_Demanda();
        setCampos(rep);
    }

    private IU_Demanda() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
        Componentes();
        setLabels();
    }   

    private void Componentes() {
        lab = new JLabel[12];
        lab[0] = lb_mes0;
        lab[1] = lb_mes1;
        lab[2] = lb_mes2;
        lab[3] = lb_mes3;
        lab[4] = lb_mes4;
        lab[5] = lb_mes5;
        lab[6] = lb_mes6;
        lab[7] = lb_mes7;
        lab[8] = lb_mes8;
        lab[9] = lb_mes9;
        lab[10] = lb_mes10;
        lab[11] = lb_mes11;
        txt = new JLabel[12];
        txt[0] = lb_val_mes0;
        txt[1] = lb_val_mes1;
        txt[2] = lb_val_mes2;
        txt[3] = lb_val_mes3;
        txt[4] = lb_val_mes4;
        txt[5] = lb_val_mes5;
        txt[6] = lb_val_mes6;
        txt[7] = lb_val_mes7;
        txt[8] = lb_val_mes8;
        txt[9] = lb_val_mes9;
        txt[10] = lb_val_mes10;
        txt[11] = lb_val_mes11;
        
    }

    private void setLabels() {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
            "Setiembre", "Octubre", "Noviembre", "Diciembre"};
        GregorianCalendar g = new GregorianCalendar();
        int anio = g.get(GregorianCalendar.YEAR);
        int mes = g.get(GregorianCalendar.MONTH) - 1;
        for (int i = 0; i < 12; i++) {
            if (mes < 0) {
                mes = 11;
                anio--;
            }
            lab[i].setText(meses[mes] + " " + anio);
            mes--;
        }
    }  

    private void setCampos(Repuestos r) {
        double[] demanda = servicio.demandaMensualRepuesto(r.getCodrepuesto());
        double d = 0;
        double sum = 0;
        for (int i = 0 ; i < demanda.length; i++) {
            d = demanda[i];
            sum += d;
            txt[i].setText(String.valueOf(d));
        }
        lb_totalAnual.setText(String.valueOf(sum));
        lb_promedioMensual.setText(String.valueOf(Math.round((sum / 12)*100.0)/100.0));
        lb_nroParte.setText(r.getCodrepuesto());
        lb_linea.setText(r.getEquipos().getDescripcion());
        lb_descripcion.setText(r.getDescripcion());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        stat = new javax.swing.JPanel();
        bt_salir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lb_totalAnual = new javax.swing.JLabel();
        lb_promedioMensual = new javax.swing.JLabel();
        din = new javax.swing.JPanel();
        lb_mes11 = new javax.swing.JLabel();
        lb_mes10 = new javax.swing.JLabel();
        lb_mes9 = new javax.swing.JLabel();
        lb_mes8 = new javax.swing.JLabel();
        lb_mes7 = new javax.swing.JLabel();
        lb_mes6 = new javax.swing.JLabel();
        lb_mes5 = new javax.swing.JLabel();
        lb_mes4 = new javax.swing.JLabel();
        lb_mes3 = new javax.swing.JLabel();
        lb_mes2 = new javax.swing.JLabel();
        lb_mes1 = new javax.swing.JLabel();
        lb_mes0 = new javax.swing.JLabel();
        lb_val_mes0 = new javax.swing.JLabel();
        lb_val_mes1 = new javax.swing.JLabel();
        lb_val_mes2 = new javax.swing.JLabel();
        lb_val_mes3 = new javax.swing.JLabel();
        lb_val_mes7 = new javax.swing.JLabel();
        lb_val_mes6 = new javax.swing.JLabel();
        lb_val_mes5 = new javax.swing.JLabel();
        lb_val_mes4 = new javax.swing.JLabel();
        lb_val_mes11 = new javax.swing.JLabel();
        lb_val_mes10 = new javax.swing.JLabel();
        lb_val_mes8 = new javax.swing.JLabel();
        lb_val_mes9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lb_promedioMensual1 = new javax.swing.JLabel();
        lb_promedioMensual2 = new javax.swing.JLabel();
        lb_nroParte = new javax.swing.JLabel();
        lb_linea = new javax.swing.JLabel();
        lb_descripcion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        stat.setBorder(javax.swing.BorderFactory.createTitledBorder("Totales"));

        bt_salir.setText("Salir");
        bt_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Promedio Mensual");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Total Anual");

        lb_totalAnual.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lb_promedioMensual.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout statLayout = new javax.swing.GroupLayout(stat);
        stat.setLayout(statLayout);
        statLayout.setHorizontalGroup(
            statLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bt_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lb_promedioMensual, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lb_totalAnual, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        statLayout.setVerticalGroup(
            statLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_promedioMensual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(statLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_totalAnual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(bt_salir, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        din.setBorder(javax.swing.BorderFactory.createTitledBorder("Demanda"));

        lb_mes11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_mes11.setText("jLabel3");

        lb_mes10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_mes10.setText("jLabel3");

        lb_mes9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_mes9.setText("jLabel3");

        lb_mes8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_mes8.setText("jLabel3");

        lb_mes7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_mes7.setText("jLabel3");

        lb_mes6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_mes6.setText("jLabel3");

        lb_mes5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_mes5.setText("jLabel3");

        lb_mes4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_mes4.setText("jLabel3");

        lb_mes3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_mes3.setText("jLabel3");

        lb_mes2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_mes2.setText("jLabel3");

        lb_mes1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_mes1.setText("jLabel3");

        lb_mes0.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_mes0.setText("jLabel3");

        lb_val_mes0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_val_mes1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_val_mes2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_val_mes3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_val_mes7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_val_mes6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_val_mes5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_val_mes4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_val_mes11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_val_mes10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_val_mes8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        lb_val_mes9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout dinLayout = new javax.swing.GroupLayout(din);
        din.setLayout(dinLayout);
        dinLayout.setHorizontalGroup(
            dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_mes11, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lb_mes8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                        .addComponent(lb_mes9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb_mes10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(25, 25, 25)
                .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_val_mes11, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_val_mes10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_val_mes9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_val_mes8, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lb_mes7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_mes6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_mes5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_mes4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lb_val_mes7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lb_val_mes6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lb_val_mes5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_val_mes4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lb_mes0, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(lb_mes1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_mes2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_mes3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_val_mes0, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_val_mes1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_val_mes2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_val_mes3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        dinLayout.setVerticalGroup(
            dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dinLayout.createSequentialGroup()
                        .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_mes3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_val_mes3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_mes2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_val_mes2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_mes1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_val_mes1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_mes0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_val_mes0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(dinLayout.createSequentialGroup()
                        .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_mes7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_val_mes7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lb_mes6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_val_mes6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_mes5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_val_mes5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_mes4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_val_mes4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(dinLayout.createSequentialGroup()
                        .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_mes11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_val_mes11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lb_mes10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_val_mes10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(dinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(dinLayout.createSequentialGroup()
                                .addComponent(lb_mes9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lb_mes8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(dinLayout.createSequentialGroup()
                                .addComponent(lb_val_mes9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lb_val_mes8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DEMANDA DE REPUESTO");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Nro Parte");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Linea");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Descripcion");

        lb_promedioMensual1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lb_promedioMensual2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lb_nroParte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lb_linea.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lb_descripcion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(stat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(din, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_nroParte, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_linea, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(303, 303, 303)
                    .addComponent(lb_promedioMensual1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(304, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(313, Short.MAX_VALUE)
                    .addComponent(lb_promedioMensual2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(294, 294, 294)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel4))
                            .addComponent(lb_nroParte, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addComponent(lb_linea, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(lb_descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(din, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(stat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(237, 237, 237)
                    .addComponent(lb_promedioMensual1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(237, 237, 237)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(247, 247, 247)
                    .addComponent(lb_promedioMensual2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(227, 227, 227)))
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

    private void bt_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salirActionPerformed
        dispose();
    }//GEN-LAST:event_bt_salirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_salir;
    private javax.swing.JPanel din;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lb_descripcion;
    private javax.swing.JLabel lb_linea;
    private javax.swing.JLabel lb_mes0;
    private javax.swing.JLabel lb_mes1;
    private javax.swing.JLabel lb_mes10;
    private javax.swing.JLabel lb_mes11;
    private javax.swing.JLabel lb_mes2;
    private javax.swing.JLabel lb_mes3;
    private javax.swing.JLabel lb_mes4;
    private javax.swing.JLabel lb_mes5;
    private javax.swing.JLabel lb_mes6;
    private javax.swing.JLabel lb_mes7;
    private javax.swing.JLabel lb_mes8;
    private javax.swing.JLabel lb_mes9;
    private javax.swing.JLabel lb_nroParte;
    private javax.swing.JLabel lb_promedioMensual;
    private javax.swing.JLabel lb_promedioMensual1;
    private javax.swing.JLabel lb_promedioMensual2;
    private javax.swing.JLabel lb_totalAnual;
    private javax.swing.JLabel lb_val_mes0;
    private javax.swing.JLabel lb_val_mes1;
    private javax.swing.JLabel lb_val_mes10;
    private javax.swing.JLabel lb_val_mes11;
    private javax.swing.JLabel lb_val_mes2;
    private javax.swing.JLabel lb_val_mes3;
    private javax.swing.JLabel lb_val_mes4;
    private javax.swing.JLabel lb_val_mes5;
    private javax.swing.JLabel lb_val_mes6;
    private javax.swing.JLabel lb_val_mes7;
    private javax.swing.JLabel lb_val_mes8;
    private javax.swing.JLabel lb_val_mes9;
    private javax.swing.JPanel stat;
    // End of variables declaration//GEN-END:variables
}
