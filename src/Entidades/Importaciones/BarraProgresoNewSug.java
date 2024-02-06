package Entidades.Importaciones;
import Presentacion.Importaciones.FREP024;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class BarraProgresoNewSug extends JPanel {
    Thread hilo;
    Object objeto = new Object();
    JTextField texto;
    JProgressBar barra;
    JFrame frame;
    HiloRunnableNewSug hr;
    FREP024 menu;
    int tiempoTot;
    long numReg;
    float tiempoProm;
    public BarraProgresoNewSug(JFrame frm,FREP024 men) {//Nuevo Sugerido
        tiempoProm=3.154574f;
        //float tiempoProm=3.785489f;
        
        numReg=men.getNumRegASugerido();
        System.out.println("NumReg de Menu(3170):"+numReg);
        
        setLayout( new BorderLayout() );
        JPanel panelInferior = new JPanel();
        barra = new JProgressBar();
        barra.setStringPainted(true);
        
        panelInferior.setLayout( new GridLayout(0,1) );
        panelInferior.add( barra );
        panelInferior.add( new JLabel( "NUEVO SUGERIDO EN PROGRESO - Cargando..." ) );
        frame=frm;
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                iniciaCuenta();
                hr = new HiloRunnableNewSug(frame,menu);
                Thread t = new Thread(hr);
                t.start();
            }
        });
        add( panelInferior,BorderLayout.SOUTH );
    }
    public void iniciaCuenta() {
        if( hilo == null ) {
            tiempoTot=(int) Math.round(tiempoProm*numReg);
            hilo = new ThreadCarga(tiempoTot);
            hilo.start();
        }
    }
    class ThreadCarga extends Thread {
        int tiempoT;
        private ThreadCarga(int TT) {
            tiempoT=TT;
        }
        public void run() {
            int min = 0;
            System.out.println("tiempoT(12000):"+tiempoT);
            int max = tiempoT;//Tiempo Total
            barra.setValue( min );
            barra.setMinimum( min );
            barra.setMaximum( max );
            long tiempoI = System.currentTimeMillis();
            for( int i=min; i <= max; i=i+100 ) {
                barra.setValue( i );
                //int porcentaje=i*100/max;
                //texto.setText( ""+porcentaje+" %" );//i
                synchronized( objeto ) {
                    try {
                        objeto.wait( 100 );//avance barrita
                    } catch( InterruptedException e ) {
                    }
                }
            }
            long tiempoF = System.currentTimeMillis();
            calcularTiempoProceso(tiempoF, tiempoI);
            hilo = null;
        }
    }
    private void calcularTiempoProceso(long tiempoF,long tiempoI){
        long milisegundos = tiempoF - tiempoI;
        System.out.println("Milisegundos:"+milisegundos);
        milisegundos=milisegundos/3170;
        long hora = milisegundos/3600000;
        long restohora = milisegundos%3600000;
        long minuto = restohora/60000;
        long restominuto = restohora%60000;
        long segundo = restominuto/1000;
        long restosegundo = restominuto%1000;
        System.out.println("Tiempo promedio:"+hora + ":" + minuto + ":" + segundo + "." + restosegundo);
    }
}