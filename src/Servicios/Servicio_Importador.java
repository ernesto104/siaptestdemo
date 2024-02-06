
package Servicios;

import Entidades.Importadores;
import Mantenimiento.ImportadoresDAO;
import java.util.Iterator;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lesly Aguilar
 */
public class Servicio_Importador {
    
    ImportadoresDAO importarDAO;
    JTable tblClientes;
    DefaultTableModel modelo;
    int ultimo_id;

    public int getUltimo_id() {
        return ultimo_id;
    }

    public void setUltimo_id(int ultimo_id) {
        this.ultimo_id = ultimo_id;
    }
    
    

    public Servicio_Importador(JTable tblClientes) {
        importarDAO = new ImportadoresDAO();
        this.tblClientes = tblClientes;
    }

  public int Listar_importadores(DefaultTableModel modelo) {
        
        this.modelo=modelo;
        List<Importadores> listaImportadores = importarDAO.Obtener_Lista_Objetos_OrderNombre();

        for (int i = 0; i < listaImportadores.size(); i++) {
            
            if(i==(listaImportadores.size()-1)){
                ultimo_id = listaImportadores.get(i).getIdimportador();
            }
            
            String nombre = listaImportadores.get(i).getNombre();
            String contacto = listaImportadores.get(i).getContacto();
            String pais = listaImportadores.get(i).getPais();
            String email = listaImportadores.get(i).getEmail();
            String telefono1 = listaImportadores.get(i).getTelefono();
            String telefono2= listaImportadores.get(i).getTelefono2();            
            
            Object[] lineas = {nombre,contacto,pais,email,telefono1,telefono2};
            modelo.addRow(lineas);
        }

        return listaImportadores.size();

    }

    public Importadores obtener_Importador(int id) {
        Importadores importador;
        importador = importarDAO.Obtener_Objeto(id);
        return importador;
    }

    public boolean insertar_Importador(Importadores importador) {
        importarDAO.Agregar_Objeto(importador);
        return true;
    }
    
    public boolean actualizar_Importador(Importadores importador){
        importarDAO.Modificar_Objeto(importador);
        return true;
    }
    
    public boolean eliminar_Importador(Importadores importador){
        importarDAO.Eliminar_Objeto(importador);
        return true;
    }
    
    public Importadores Obtener_Importador_Por_Nombre(String nombre){
        importarDAO = new ImportadoresDAO();
        Importadores transp = new Importadores();
        transp= importarDAO.obtener_Importador_Por_Nombre(nombre);
        
        return transp;
    }
    
      public void ListarxNombre(DefaultTableModel modelo, String nombre) {
        ImportadoresDAO c = new ImportadoresDAO();
        llenarTabla(c.getLista_nombre(nombre), modelo);
    }
    
    private void llenarTabla(List lista, DefaultTableModel table) {
        Iterator ite = lista.iterator();
        while (ite.hasNext()) {
            Object[] row = new Object[6];
            Importadores temp = (Importadores) ite.next();
            row[0] = temp.getNombre();
            row[1] = temp.getContacto();
            row[2] = temp.getPais();
            row[3] = temp.getEmail();
            row[4] = temp.getTelefono();
            row[5] = temp.getTelefono2();
            table.addRow(row);
        }
    }
    
    
    
    
}
