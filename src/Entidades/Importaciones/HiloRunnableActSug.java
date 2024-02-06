package Entidades.Importaciones;
import Presentacion.Importaciones.IU_ActualizarSugerido;
import Presentacion.Importaciones.FREP024;
import javax.swing.JFrame;
public class HiloRunnableActSug implements Runnable{
    private JFrame frmProgressBar;
    IU_ActualizarSugerido iuActSug;
    FREP024 menu;
    int idCabSugerido;
    public HiloRunnableActSug(JFrame frmBarra,FREP024 men,int idCabSug){
        super();
        frmProgressBar=frmBarra;
        idCabSugerido=idCabSug;
        menu=men;
    }
    public void run() {
        iuActSug=new IU_ActualizarSugerido(menu,idCabSugerido);
        iuActSug.setVisible(true);
        frmProgressBar.dispose();
    }
}