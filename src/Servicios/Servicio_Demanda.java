/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Mantenimiento.DetallesDAO;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author fabrica2
 */
public class Servicio_Demanda {


    private DetallesDAO dao;
    private Calendar cal = new GregorianCalendar();

    public Servicio_Demanda() {
        dao = new DetallesDAO();
    }

     public double[] demandaMensualRepuesto(String cod) {
        double[] mes = new double[12];
        int mesInicio = cal.get(Calendar.MONTH);
        int añoInicio = cal.get(Calendar.YEAR);
        for (int i = 0; i < mes.length; i++) {
            if (mesInicio == 0) {
                mesInicio = 12;
                añoInicio--;
            }
            mes[i] = dao.demandaMensualRepuesto(cod, mesInicio, añoInicio);
            mesInicio--;
        }
        return mes;
    }
}
