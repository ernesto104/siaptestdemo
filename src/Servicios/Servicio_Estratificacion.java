package Servicios;

import Entidades.Control;
import Entidades.Detallees;
import Entidades.Estratificacion;
import Entidades.Repuestos;
import Mantenimiento.ControlDAO;
import Mantenimiento.DetallesDAO;
import Mantenimiento.EstratificacionDAO;
import Mantenimiento.RepuestosDAO;
import Presentacion.FREP010;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fabrica2
 */
public class Servicio_Estratificacion {

    EstratificacionDAO eDAO;
    FREP010 it;

    public Servicio_Estratificacion() {
        eDAO = new EstratificacionDAO();

    }

    public Servicio_Estratificacion(FREP010 it) {
        eDAO = new EstratificacionDAO();
        this.it = it;
    }

    public Long getSize() {
        return eDAO.Tamaño_Lista();
    }

    public Estratificacion getEstratificacion(int id) {
        Estratificacion estratificacion;
        estratificacion = eDAO.Obtener_Objeto(id);
        return estratificacion;
    }

    public boolean addEstratificacion(Estratificacion t) {
        return eDAO.Agregar_Objeto(t);

    }

    public List ListarEstratificacion() {
        return eDAO.Obtener_Lista_Objetos();
    }

    public void listar() {
        DefaultTableModel table = (DefaultTableModel) it.tb_estr.getModel();
        Iterator ite = eDAO.Obtener_Lista_Objetos().iterator();
        while (ite.hasNext()) {
            Object[] row = new Object[5];
            Estratificacion temp = (Estratificacion) ite.next();
            row[0] = temp.getCodigoestratificacion();
            row[1] = temp.getDescripcion();
            row[2] = temp.getVentasdesde();
            row[3] = temp.getVentashasta();
            row[4] = temp.getNromeses();
            table.addRow(row);
        }
    }
    
    public boolean actualizarEstratificacion(Estratificacion t) {
        return eDAO.Modificar_Objeto(t);
    }

    public boolean borrarEstratificacion(Estratificacion t) {
        return eDAO.Eliminar_Objeto(t);
    }

    public Estratificacion getEstratificacion_por_nombre(String codigo) {
        Estratificacion estratificacion=null;
        estratificacion = eDAO.Obtener_Objeto_por_nombre(codigo);
        return estratificacion;
    }

    public Estratificacion getEstratificacion_por_descripcion(String descripcion) {
        Estratificacion estratificacion;
        estratificacion = eDAO.Obtener_Objeto_por_descripcion(descripcion);
        return estratificacion;
    }

    public List getList() {
        return eDAO.Obtener_Lista_Objetos();
    }

    public void realizarEstratificacion() {
        
        EstratificacionDAO eDAO = new EstratificacionDAO();
        Date fechaActual = new Date();
        int mesActual = fechaActual.getMonth();
        int añoActual = fechaActual.getYear();
        mesActual++;
        RepuestosDAO rDAO = new RepuestosDAO();
        List<Repuestos> listaRepuest = rDAO.Obtener_Lista_Objetos();
        List<Estratificacion> listaEstrat = eDAO.Obtener_Lista_Objetos();
        DetallesDAO dDAO = new DetallesDAO();
        List<Detallees> listaDetalles;
        boolean saltar;
        ControlDAO cDAO = new ControlDAO();
        Control control = new Control();
        control = cDAO.Obtener_Objeto(1);        
        int nroMeses = control.getMesdemanda();
        int ventasHastaC = listaEstrat.get(2).getVentashasta();        
        int ventasHastaB = listaEstrat.get(1).getVentashasta();

        for (int i = 0; i < listaRepuest.size(); i++) {
            saltar = false;
            Date fechaRegist = listaRepuest.get(i).getFecharegistro();
            if (fechaRegist != null) {
                int mesRegistro = fechaRegist.getMonth();
                mesRegistro++;
                int añoRegistro = fechaRegist.getYear();
                int restaMeses = mesActual - mesRegistro;
                
                if ( listaRepuest.get(i).getEstratificacion() != null ) {
                    //ESTRATIFICACION K
                    if (listaRepuest.get(i).getEstratificacion().getCodigoestratificacion().equals("K")) {                    
                        //No hace nada si es K
                    } else {
                        //ESTRATIFICACION F
                        if (listaRepuest.get(i).getEstratificacion().getCodigoestratificacion().equals("F")) {                        
                            //No hace nada si es F
                        } else {

                            //ESTRATIFICACION N
                            if (añoActual == añoRegistro) {
                                if (restaMeses == 1) {
                                    Repuestos r = listaRepuest.get(i);
                                    r.setEstratificacion(listaEstrat.get(6));
                                    rDAO.Modificar_Objeto(r);                               
                                    saltar = true;
                                }
                            }
                        }
                    }
                }
            }

            if (saltar == true) {
                //No hace nada
            } else {
                listaDetalles = dDAO.getListaDetalleEs_IdRepuesto(listaRepuest.get(i).getId().getIdrepuesto());
                int cantidad = 0;
                int mesOperacion = 0;
                int añoOperacion = 0;
                
//                if (listaDetalles.isEmpty()) { // Si no tiene detalle el Repuesto se considera "N" su codigo de estratificacion (Nuevo)
//                    Repuestos r = listaRepuest.get(i);
//                    r.setEstratificacion(listaEstrat.get(6));
//                    rDAO.Modificar_Objeto(r);
//                    
//                } else {
                    for (int j = 0; j < listaDetalles.size(); j++) {

                        mesOperacion = listaDetalles.get(j).getFecha().getMonth();
                        añoOperacion = listaDetalles.get(j).getFecha().getYear();
                        mesOperacion++;
                        cantidad = cantidad + listaDetalles.get(j).getCantpedida();
                        int restaM = 0;
                        if (añoActual == añoOperacion) {
                            restaM = mesActual - mesOperacion;
                        } else {
                            restaM = 12 * (añoActual - añoOperacion) - mesOperacion + mesActual;
                        }
                        if (restaM <= nroMeses) {                       
                            if (cantidad <= ventasHastaC) {
                                Repuestos r = listaRepuest.get(i);
                                r.setEstratificacion(listaEstrat.get(2));
                                rDAO.Modificar_Objeto(r);

                            } else {
                                //ESTRATIFICACION B
                                if (cantidad <= ventasHastaB) {
                                    Repuestos r = listaRepuest.get(i);
                                    r.setEstratificacion(listaEstrat.get(1));
                                    rDAO.Modificar_Objeto(r);

                                } else {
                                    //ESTRATIFICACION A
                                    Repuestos r = listaRepuest.get(i);
                                    r.setEstratificacion(listaEstrat.get(0));
                                    rDAO.Modificar_Objeto(r);

                                }
                            }

                        } else {
                            //ESTRATIFICACION D - 1 año
                            if (cantidad == 0) {
                                Repuestos r = listaRepuest.get(i);
                                r.setEstratificacion(listaEstrat.get(3));
                                rDAO.Modificar_Objeto(r);                            
                            } else {
                                //ESTRATIFICACION E - 6 MESES
                                Repuestos r = listaRepuest.get(i);
                                r.setEstratificacion(listaEstrat.get(4));
                                rDAO.Modificar_Objeto(r);                            
                            }
                        }
                    }
//                }
            }
        }
    }
}
