/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author usuario
 */
public class HoraSistema extends Thread{
    
    private JTextField hora;
    private JTextField fecha;
    
    public HoraSistema(JTextField reloj, JTextField fecha){
        this.hora = reloj;
        this.fecha = fecha;
               
    }
    
    public void run(){
        while(true){
            String lafecha = Calendario();
            fecha.setText(lafecha);            
            Date hoy = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
            hora.setText(formato.format(hoy));
            try{sleep(1000);}catch(Exception e){
                System.out.println("Error"+e);
            }
        }
    }
    
    public String Calendario(){
        GregorianCalendar fecha =new GregorianCalendar();
        int dia= fecha.get(fecha.DAY_OF_MONTH);
        int mes = fecha.get(fecha.MONTH)+1;
        int año = fecha.get(fecha.YEAR);        
        String fechaactual = String.valueOf(dia) + "-"+String.valueOf(mes)+"-"+String.valueOf(año);
        return fechaactual;
    }
    
}
