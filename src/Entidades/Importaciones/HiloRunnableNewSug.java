package Entidades.Importaciones;
import Presentacion.Importaciones.FREP024;
import Presentacion.Importaciones.IU_Sugerido;
import javax.swing.JFrame;
public class HiloRunnableNewSug implements Runnable{
    private JFrame frmProgressBar;
    private FREP024 menu;
    IU_Sugerido iuSugerido;
    public HiloRunnableNewSug(JFrame frmBarra,FREP024 men){
        super();
        frmProgressBar=frmBarra;
        menu=men;
    }
    public void run() {
        iuSugerido=new IU_Sugerido(menu);
        iuSugerido.setVisible(true);
        frmProgressBar.dispose();
    }
}