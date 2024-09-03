/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import javax.swing.JOptionPane;

/**
 *
 * @author Cers
 */
public class TipoMensaje {

    // Nuevas Variables
    public int SI = JOptionPane.YES_OPTION;
    public int NO = JOptionPane.NO_OPTION;
    
    public String SELECCION_REGISTRO = "Primero selecciona un registro";
    public String EXITO_MODIFICAR = "Se modifico con exito el registro";
    
    public String EXITO_GENERAR_TXT = "Se generaron los txt con éxito";
    
    public String ERROR_MODIFICAR = "Ocurrió error al modificar el registro";
    public String EXITO_AGREGAR = "Guardado exitoso";
    public String ERROR_AGREGAR = "Ocurrió un error al Guardar";
    public String EXITO_ELIMINAR = "Se elimino el registro con exito";
    public String ERROR_ELIMINAR = "Ocurió un error al eliminar el registro";
    public String VALIDO = "VALIDO";
    public String CAMPOS_INCOMPLETOS = "CAMPOS INCOMPLETOS (Seleccione fecha de \"Vencimiento\")";
    public String CAMPOS_INCOMPLETOS_ = "CAMPOS INCOMPLETOS";
    public String RUC_LONGITUD_INSUFICIENTE = "RUC_LONGITUD_INSUFICIENTE";
    public String NO_SELECCIONADO = "NO_SELECCIONADO";
    public String NO_SELECCIONADO_LINEAS = "NO_SELECCIONADO_LINEAS";
    public String NO_SELECCIONADO_MARCA = "NO_SELECCIONADO_MARCAS";
    public String NO_SELECCIONADO_MODELO = "NO_SELECCIONADO_MODELOS";
    public String NO_SELECCIONADO_PUNTOS_VENTA = "NO_SELECCIONADO_PUNTOS_VENTA";
    public String NO_SELECCIONADO_USUARIO = "NO_SELECCIONADO_USUARIO";
    public String NO_SELECCIONADO_SISTEMA = "NO_SELECCIONADO_SISTEMA";
    public String ERROR_EXPORTAR = "ERROR_EXPORTAR";
    public String ERROR_NUMERO = "ERROR_NUMERO";
    public String ERROR_GUARDAR = "ERROR_GUARDAR";
    public String TABLA_VACIA = "TABLA_VACIA";
    public String EXITO_EXPORTAR = "EXITO_EXPORTAR";
    public String TELEFONO_ERROR = "TELEFONO_ERROR";
    public String RUC_TIPOINCORRECTO = "RUC_TIPOINCORRECTO";
    public String INGRESAR_NO_ENTERO = "INGRESAR_NO_ENTERO";
    public String INGRESAR_COMA = "INGRESAR_COMA";
    public String EMPRESA_NO_INGRESADA = "EMPRESA_NO_INGRESADA";
    public String DIRECCION_NO_INGRESADA = "DIRECCION_NO_INGRESADA";
    public String RUC_NO_INGRESADO = "RUC_NO_INGRESADO";
    public String PAIS_NO_INGRESADO = "PAIS_NO_INGRESADO";
    public String NOMBRE_NO_INGRESADO = "NOMBRE_NO_INGRESADO";
    public String COMISIÓN_LIMA_NO_INGRESADA = "COMISIÓN_LIMA_NO_INGRESADA";
    public String COMISIÓN_PROV_NO_INGRESADA = "COMISIÓN_PROV_NO_INGRESADA";
    public String DOCUMENTO_NO_INGRESADO = "DOCUMENTO_NO_INGRESADO";
    public String NUMERO_ERRONEO = "Ingrese un numero Correcto";
    public String PREGUNTA_OPERACION = "¿Desea continuar con la Operación?";
    public String NO_SELECCIONADO_TIPO_CAMBIO = "Primero seleccione un Tipo de Cambio";
    
// Nuevos Metodos
    public void Mensaje(String val) {
        JOptionPane.showMessageDialog(null, val, "MENSAJE", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void Error(String val){
        JOptionPane.showMessageDialog(null, val,"ERROR",JOptionPane.ERROR_MESSAGE);
    }
    
    public int Confirmacion(String val){
        return JOptionPane.showConfirmDialog(null, val, "CONFIRMACION", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
    
    public void manejarMensajes(String val) {
//        System.out.println("val:" + val);
        switch (val) {
            case "RUC_LONGITUD_INSUFICIENTE":
                JOptionPane.showMessageDialog(null, "Longitud de RUC incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                break;
                
            case "CAMPOS_INCOMPLETOS":
                JOptionPane.showMessageDialog(null, "Los Campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                break;
           
            case "CAMPOS INCOMPLETOS":
                JOptionPane.showMessageDialog(null, "Los Campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                break;
                
            case "RUC_TIPOINCORRECTO":
                JOptionPane.showMessageDialog(null, "Ingrese RUC valido", "Error", JOptionPane.ERROR_MESSAGE);
                break;
                
            case "NO_SELECCIONADO":
                JOptionPane.showMessageDialog(null, "Primero Seleccione un Transportista", "Error", JOptionPane.ERROR_MESSAGE);
                break;
                
            case "NO_SELECCIONADO_LINEAS":
                JOptionPane.showMessageDialog(null, "SELECCIONE UNA LINEA", "Error", JOptionPane.ERROR_MESSAGE);
                break;
                
            case "NO_SELECCIONADO_MARCAS":
                JOptionPane.showMessageDialog(null, "Seleccione una marca", "Error", JOptionPane.ERROR_MESSAGE);
                break;
                
            case "NO_SELECCIONADO_MODELOS":
                JOptionPane.showMessageDialog(null, "Seleccione un modelo", "Error", JOptionPane.ERROR_MESSAGE);
                break;    
            
            case "NO_SELECCIONADO_PUNTOS_VENTA":
                JOptionPane.showMessageDialog(null, "Seleccione un punto de venta", "Error", JOptionPane.ERROR_MESSAGE);
                break;     
                
            case "NO_SELECCIONADO_USUARIO":
                JOptionPane.showMessageDialog(null, "Primero seleccione un Usuario", "Error", JOptionPane.ERROR_MESSAGE);
                break;
                
            case "NO_SELECCIONADO_TIPO_CAMBIO":
                JOptionPane.showMessageDialog(null, "Primero seleccione un Tipo de Cambio", "Error", JOptionPane.ERROR_MESSAGE);
                break;

            case "ERROR_NUMERO":
                JOptionPane.showMessageDialog(null, "Ingrese un numero Válido", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                break;

            case "DOCUMENTO_NO_INGRESADO":
                JOptionPane.showMessageDialog(null, "Debe ingresesar numero de documento", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                break;

            case "NO_SELECCIONADO_SISTEMA":
                JOptionPane.showMessageDialog(null, "Primero seleccione un Sistema", "Error", JOptionPane.ERROR_MESSAGE);
                break;

            case "ERROR_EXPORTAR":
                JOptionPane.showMessageDialog(null, "Error al exportar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                break;
                
            case "ERROR_GUARDAR":
                JOptionPane.showMessageDialog(null, "Error al guardar", "Exportar tabla", JOptionPane.ERROR_MESSAGE);
                break;
                
            case "TABLA_VACIA":
                JOptionPane.showMessageDialog(null, "La Tabla esta vacia", "Vacia", JOptionPane.WARNING_MESSAGE);
                break;
                
            case "EXITO_EXPORTAR":
                JOptionPane.showMessageDialog(null, "Tabla exportada con exito", "Exportar tabla", JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "TELEFONO_ERROR":
                JOptionPane.showMessageDialog(null, "Numero de Telefono Incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                break;
                
            case "INGRESAR_NO_ENTERO":
                JOptionPane.showMessageDialog(null, "Debe ingresar número", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "INGRESAR_COMA":
                JOptionPane.showMessageDialog(null, "Debe usar punto(.)", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "EMPRESA_NO_INGRESADA":
                JOptionPane.showMessageDialog(null, "Debe ingresar el nombre de la Empresa", "Error", JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "DIRECCION_NO_INGRESADA":
                JOptionPane.showMessageDialog(null, "Debe ingresar la dirección de la Empresa", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "RUC_NO_INGRESADO":
                JOptionPane.showMessageDialog(null, "Debe ingresar el RUC de la Empresa", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "PAIS_NO_INGRESADO":
                JOptionPane.showMessageDialog(null, "Debe ingresar el país de la Empresa", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "NOMBRE_NO_INGRESADO":
                JOptionPane.showMessageDialog(null, "Debe ingresar el nombre", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "COMISIÓN_LIMA_NO_INGRESADA":
                JOptionPane.showMessageDialog(null, "Debe ingresar su comisión de Lima. No puede ser 0", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                break;
                
            case "COMISIÓN_PROV_NO_INGRESADA":
                JOptionPane.showMessageDialog(null, "Debe ingresar su comisión de provincia. No puede ser 0", "VALIDACIÓN", JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(null, val, "Error", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
}
