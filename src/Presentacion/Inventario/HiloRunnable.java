package Presentacion.Inventario;
import Presentacion.Importaciones.IU_ActualizarSugerido;
import Servicios.Servicio_Repuesto;
import javax.swing.JFrame;
public class HiloRunnable implements Runnable{
    private JFrame frmProgressBar;
    IU_ActualizarSugerido iuActSug;
    FREP048 menu;
    int idCabSugerido;
    public HiloRunnable(JFrame frmBarra,FREP048 men){
        super();
        frmProgressBar=frmBarra;
        menu=men;
    }
    @Override
    public void run() {
        Servicio_Repuesto s = new Servicio_Repuesto();
        s.prepararTomaInventario();
        frmProgressBar.dispose();
    }
}