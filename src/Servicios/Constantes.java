/*
 * /
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

/**
 *
 * @author CRS
 */
public interface Constantes {
    
    //Constantes utilizadas para facturacion, canje letras, ingresos y salidas, generacion de letras
    // varias
    
    //Tipo de Operaciones
    String CONTADO = "1";
    String CREDITO = "2";
    String LETRAS = "3";
    
    // Tipo Moneda para guardar en Base de Datos
    String SOLES = "1";
    String DOLARES = "2";
    
    //Tipo de Transaccion
    String VENTA = "V";
    String COMPRA = "C";
    
    //Forma de Pago
    String EFECTIVO = "E";
    String CHEQUE = "C";
    
    String GLOSA_DOLARES = "$";
    String GLOSA_SOLES = "S/";
    
    //constantes para lo correspondiente a Clientes
    
    String CLIENTE = "C";
    String PROVEEDOR = "P";
    String AMBOS = "A";
    
    String SALIDAS_VARIAS = "Salidas Varias";
    
    String OBJETO_COMERCIALIZADOR = "ARTÍCULO"; // REPUESTO
    String OBJETOS_COMERCIALIZADORES = "ARTÍCULOS"; // REPUESTOS
}
