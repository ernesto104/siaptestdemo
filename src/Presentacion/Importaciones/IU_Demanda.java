package Presentacion.Importaciones;
import Entidades.Repuestos;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Servicios.Importaciones.Servicio_DetalleES;
public class IU_Demanda extends javax.swing.JFrame {
    static private FREP027 iumaestro;
    static private Repuestos repuesto;
    public IU_Demanda(FREP027 iuma,Repuestos rep) throws ParseException {
        initComponents();
        iumaestro=iuma;
        iumaestro.setEnabled(false);
        repuesto=rep;
        todoACero();
        inicializar();
    }
    public void todoACero(){
        txtMes1.setText("0");
        txtMes2.setText("0");
        txtMes3.setText("0");
        txtMes4.setText("0");
        txtMes5.setText("0");
        txtMes6.setText("0");
        txtMes7.setText("0");
        txtMes8.setText("0");
        txtMes9.setText("0");
        txtMes10.setText("0");
        txtMes11.setText("0");
        txtMes12.setText("0");
    }
    public void inicializar() throws ParseException{
        Calendar ahoraCal = Calendar.getInstance();
        int año=ahoraCal.get(Calendar.YEAR);
        int mes=ahoraCal.get(Calendar.MONTH)+1;
        int añoFinal=año;
        if(mes==0){
            añoFinal=año-1;
        }
        int añoIni=año-1;
        int mesIni=mes;
        int mesFinal=mes-1;
        if(mes-1==-1){
            mesFinal=11;
        }
        int diaIni=1;
        int diaFinal=getDiaFinal(mesFinal);
        int mesIniSQL=mesIni+1;
        int mesFinSQL=mesFinal+1;
        int idRepuesto=repuesto.getId().getIdrepuesto();
        int idLinea=repuesto.getId().getIdequipo();
        año=añoIni;
        Date fi=formatearAFechaSQL(añoIni,mesIniSQL,diaIni);
        Date ff=formatearAFechaSQL(añoFinal,mesFinSQL,diaFinal);
        int m=mesIniSQL;
        int c=0;
        int vectorMesXPosicion[]=new int[12];
        boolean llego=false;
        while(!llego){
            if(mes==12){
                mes=1;
                año=añoFinal;
            }else{
                mes++;
            }
            if(mes==mesFinSQL){
                llego=true;
            }
            vectorMesXPosicion[c]=mes;
            setLabel(mes,c,año);
            c++;
        }
        int total=0;
        Servicio_DetalleES sde=new Servicio_DetalleES();    
        List lista=sde.getCantEntregadaXAño(idRepuesto,idLinea,fi,ff);
        Iterator iteRep = lista.iterator();
        Object row[] = new Object[3];
        while(iteRep.hasNext()){
            Object [] objRep = (Object []) iteRep.next();
            for(int pos=0;pos<12;pos++){
                int temp=Integer.parseInt(String.valueOf(objRep[0]));
                if(temp==vectorMesXPosicion[pos]){
                    int cantidad=Integer.parseInt(String.valueOf(objRep[2]));
                    setText(cantidad,pos);
                    total+=cantidad;
                    break;
                }
            }
        }
        txtTotalAnual.setText(getComaMillar(String.valueOf(total)));
        double prom=Double.parseDouble(String.valueOf(total))/12.0;
        Object promedio=Math.round(prom);
        txtPromedioMensual.setText(getComaMillar(String.valueOf(promedio)));
    }
    private void setText(int cantidad, int posicion){
        String cant=String.valueOf(cantidad);
        switch(posicion){
            case 0: txtMes1.setText(getComaMillar(cant));break;
            case 1: txtMes2.setText(getComaMillar(cant));break;
            case 2: txtMes3.setText(getComaMillar(cant));break;
            case 3: txtMes4.setText(getComaMillar(cant));break;
            case 4: txtMes5.setText(getComaMillar(cant));break;
            case 5: txtMes6.setText(getComaMillar(cant));break;
            case 6: txtMes7.setText(getComaMillar(cant));break;
            case 7: txtMes8.setText(getComaMillar(cant));break;
            case 8: txtMes9.setText(getComaMillar(cant));break;
            case 9: txtMes10.setText(getComaMillar(cant));break;
            case 10: txtMes11.setText(getComaMillar(cant));break;
            case 11: txtMes12.setText(getComaMillar(cant));break;
        }
    }
    private String getComaMillar(Object valor){
        String cantStr=String.valueOf(valor);
        int cantInt=Integer.parseInt(cantStr);
        int cifMillar,cifResto;
        if(cantStr.length()>3 && Integer.parseInt(cantStr)>0){
            cifMillar=cantInt/1000;
            cifResto=cantInt%1000;
            if(cifResto==0 && cifMillar!=0){
                cantStr=cifMillar+",000";
            }else if(cifResto/100!=0){
                cantStr=cifMillar+","+cifResto;
            }else if(cifResto/10!=0){
                cantStr=cifMillar+",0"+cifResto;
            }else{
                cantStr=cifMillar+",00"+cifResto;
            }
        }else if(cantStr.length()>4 && Integer.parseInt(cantStr)<0){
            cifMillar=cantInt/1000;
            cifResto=cantInt%1000;
            if(cifResto==0 && cifMillar!=0){
                cantStr=cifMillar+",000";
            }else if(cifResto/100!=0){
                cantStr=cifMillar+","+Math.abs(cifResto);
            }else if(cifResto/10!=0){
                cantStr=cifMillar+",0"+Math.abs(cifResto);
            }else{
                cantStr=cifMillar+",00"+Math.abs(cifResto);
            }
        }
        return cantStr;
    }
    private void setLabel(int mes,int control,int año){
        mes=mes-1;
        String cadena[]={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio",
            "Agosto","Setiembre","Octubre","Noviembre","Diciembre"};
        String lblmes=cadena[mes]+String.valueOf(" "+año);
        switch(control){
            case 0: txtEti1.setText(lblmes);break;
            case 1: txtEti2.setText(lblmes);break;
            case 2: txtEti3.setText(lblmes);break;
            case 3: txtEti4.setText(lblmes);break;
            case 4: txtEti5.setText(lblmes);break;
            case 5: txtEti6.setText(lblmes);break;
            case 6: txtEti7.setText(lblmes);break;
            case 7: txtEti8.setText(lblmes);break;
            case 8: txtEti9.setText(lblmes);break;
            case 9: txtEti10.setText(lblmes);break;
            case 10: txtEti11.setText(lblmes);break;
            case 11: txtEti12.setText(lblmes);break;
        }
    }
    private Date formatearAFechaSQL(int año, int mes,int dia){
        java.sql.Date sqlDate=null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            String date = String.valueOf(año)+"/"+String.valueOf(mes)+"/"+String.valueOf(dia);
            java.util.Date utilDate = formatter.parse(date);
            sqlDate= new java.sql.Date(utilDate.getTime());
            return sqlDate;
        }catch (ParseException e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return sqlDate;
    }
    private int getDiaFinal(int mesFinal){
        int dias=0;
        switch(mesFinal){
            case 0: dias=31;break;
            case 1: dias=29;break;
            case 2: dias=31;break;
            case 3: dias=30;break;
            case 4: dias=31;break;
            case 5: dias=30;break;
            case 6: dias=31;break;
            case 7: dias=31;break;
            case 8: dias=30;break;
            case 9: dias=31;break;
            case 10: dias=30;break;
            case 11: dias=31;break;
        }
        return dias;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtEti8 = new javax.swing.JTextField();
        txtMes4 = new javax.swing.JTextField();
        txtEti3 = new javax.swing.JTextField();
        txtMes3 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtEti7 = new javax.swing.JTextField();
        txtMes8 = new javax.swing.JTextField();
        txtEti4 = new javax.swing.JTextField();
        txtMes2 = new javax.swing.JTextField();
        txtMes5 = new javax.swing.JTextField();
        txtMes1 = new javax.swing.JTextField();
        txtEti12 = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        txtMes6 = new javax.swing.JTextField();
        txtMes12 = new javax.swing.JTextField();
        txtEti9 = new javax.swing.JTextField();
        txtEti5 = new javax.swing.JTextField();
        txtPromedioMensual = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtEti11 = new javax.swing.JTextField();
        txtMes10 = new javax.swing.JTextField();
        txtMes7 = new javax.swing.JTextField();
        txtMes9 = new javax.swing.JTextField();
        txtEti10 = new javax.swing.JTextField();
        txtTotalAnual = new javax.swing.JTextField();
        txtEti2 = new javax.swing.JTextField();
        txtMes11 = new javax.swing.JTextField();
        txtEti1 = new javax.swing.JTextField();
        txtEti6 = new javax.swing.JTextField();

        jLabel5.setText("MARZO");

        jLabel6.setText("ABRIL");

        jLabel7.setText("FEBRERO");

        jLabel8.setText("ENERO");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("DEMANDA ANUAL DEL REPUESTO");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txtEti8.setEditable(false);
        txtEti8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtEti8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEti8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtMes4.setEditable(false);
        txtMes4.setBackground(new java.awt.Color(255, 255, 255));
        txtMes4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMes4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtEti3.setEditable(false);
        txtEti3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtEti3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEti3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtMes3.setEditable(false);
        txtMes3.setBackground(new java.awt.Color(255, 255, 255));
        txtMes3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMes3.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("TOTAL ANUAL");

        txtEti7.setEditable(false);
        txtEti7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtEti7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEti7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtMes8.setEditable(false);
        txtMes8.setBackground(new java.awt.Color(255, 255, 255));
        txtMes8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMes8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtEti4.setEditable(false);
        txtEti4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtEti4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEti4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtMes2.setEditable(false);
        txtMes2.setBackground(new java.awt.Color(255, 255, 255));
        txtMes2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMes2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtMes5.setEditable(false);
        txtMes5.setBackground(new java.awt.Color(255, 255, 255));
        txtMes5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMes5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtMes1.setEditable(false);
        txtMes1.setBackground(new java.awt.Color(255, 255, 255));
        txtMes1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMes1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtEti12.setEditable(false);
        txtEti12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtEti12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEti12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        txtMes6.setEditable(false);
        txtMes6.setBackground(new java.awt.Color(255, 255, 255));
        txtMes6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMes6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtMes12.setEditable(false);
        txtMes12.setBackground(new java.awt.Color(255, 255, 255));
        txtMes12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMes12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtEti9.setEditable(false);
        txtEti9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtEti9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEti9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtEti5.setEditable(false);
        txtEti5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtEti5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEti5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtPromedioMensual.setEditable(false);
        txtPromedioMensual.setBackground(new java.awt.Color(255, 255, 255));
        txtPromedioMensual.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtPromedioMensual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("PROMEDIO MENSUAL:");

        txtEti11.setEditable(false);
        txtEti11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtEti11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEti11.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtMes10.setEditable(false);
        txtMes10.setBackground(new java.awt.Color(255, 255, 255));
        txtMes10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMes10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtMes7.setEditable(false);
        txtMes7.setBackground(new java.awt.Color(255, 255, 255));
        txtMes7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMes7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtMes9.setEditable(false);
        txtMes9.setBackground(new java.awt.Color(255, 255, 255));
        txtMes9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMes9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtEti10.setEditable(false);
        txtEti10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtEti10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEti10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtTotalAnual.setEditable(false);
        txtTotalAnual.setBackground(new java.awt.Color(255, 255, 255));
        txtTotalAnual.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtTotalAnual.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtEti2.setEditable(false);
        txtEti2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtEti2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEti2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtMes11.setEditable(false);
        txtMes11.setBackground(new java.awt.Color(255, 255, 255));
        txtMes11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtMes11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtEti1.setEditable(false);
        txtEti1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtEti1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEti1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtEti6.setEditable(false);
        txtEti6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtEti6.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEti6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(txtPromedioMensual, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEti4, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(txtEti3)
                            .addComponent(txtEti2)
                            .addComponent(txtEti1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtMes3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMes2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMes1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMes4, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))))
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtEti6, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(txtEti7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEti8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEti5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMes6)
                            .addComponent(txtMes5)
                            .addComponent(txtMes7)
                            .addComponent(txtMes8, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTotalAnual, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEti9, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(txtEti10)
                    .addComponent(txtEti11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtEti12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                    .addComponent(txtMes9)
                    .addComponent(txtMes10)
                    .addComponent(txtMes11)
                    .addComponent(txtMes12))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMes1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEti1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEti5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEti9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMes2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEti2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEti6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEti10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMes3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEti3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEti7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEti11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMes4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMes12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEti4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEti8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEti12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtPromedioMensual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txtTotalAnual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        iumaestro.setEnabled(true);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        iumaestro.setEnabled(true);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtEti1;
    private javax.swing.JTextField txtEti10;
    private javax.swing.JTextField txtEti11;
    private javax.swing.JTextField txtEti12;
    private javax.swing.JTextField txtEti2;
    private javax.swing.JTextField txtEti3;
    private javax.swing.JTextField txtEti4;
    private javax.swing.JTextField txtEti5;
    private javax.swing.JTextField txtEti6;
    private javax.swing.JTextField txtEti7;
    private javax.swing.JTextField txtEti8;
    private javax.swing.JTextField txtEti9;
    private javax.swing.JTextField txtMes1;
    private javax.swing.JTextField txtMes10;
    private javax.swing.JTextField txtMes11;
    private javax.swing.JTextField txtMes12;
    private javax.swing.JTextField txtMes2;
    private javax.swing.JTextField txtMes3;
    private javax.swing.JTextField txtMes4;
    private javax.swing.JTextField txtMes5;
    private javax.swing.JTextField txtMes6;
    private javax.swing.JTextField txtMes7;
    private javax.swing.JTextField txtMes8;
    private javax.swing.JTextField txtMes9;
    private javax.swing.JTextField txtPromedioMensual;
    private javax.swing.JTextField txtTotalAnual;
    // End of variables declaration//GEN-END:variables
}