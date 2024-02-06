package Servicios.Importaciones;
import Entidades.Control;
import Mantenimiento.Importaciones.ControlDAO;
import Presentacion.Importaciones.IU_Sugerido;
import java.util.Date;
import java.util.Iterator;
public class Servicio_Control {
    ControlDAO CDao;
    IU_Sugerido iuSugerido;
    public Servicio_Control(IU_Sugerido iusug) {
        iuSugerido=iusug;
    }
    public Servicio_Control(){
       
    }
    public Control getControlUnico(){
        CDao=new ControlDAO();
        Iterator ite = CDao.Obtener_Lista_Objetos().iterator();
        ite.hasNext();
        Control tempCon = (Control) ite.next();
        return tempCon;
    }
    public int[] getMesIniYFin(int mesesDemanda){
        int meses[]=new int[2];
        Date mesSistema=new java.util.Date();
        meses[1]=mesSistema.getMonth();
        //System.out.println("Mes actual:"+meses[1]);
        int diferencia=meses[1]-mesesDemanda+1;
        if(diferencia<=0){
            meses[0]=12+diferencia;//+Math.abs(diferencia);
            if(meses[1]==0){
                meses[1]=12;
            }
        }else{
            meses[0]=diferencia;
        }
        return meses;
    }
}