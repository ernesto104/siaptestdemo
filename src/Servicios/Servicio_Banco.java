package Servicios;

import Entidades.Bancos;
import Mantenimiento.BancoDAO;
import java.util.List;
import Presentacion.FREP009;
import java.util.Iterator;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lesly Aguilar
 */
public class Servicio_Banco {

    BancoDAO BDao;
    FREP009 it;

    public Servicio_Banco(FREP009 it) {
        BDao = new BancoDAO();
        this.it = it;
    }

    public Bancos getBanco_Nombre(String nombre) {
        return BDao.getBanco_Nombre(nombre);
    }

    public boolean borrarBanco(Bancos t) {
        return BDao.Eliminar_Objeto(t);
    }

    public Bancos getBanco_Siglas(String siglas) {
        return BDao.getBanco_Siglas(siglas);
    }

    public DefaultListModel Listar_Banco(DefaultListModel model) {
        List<Bancos> listaBancos = BDao.Obtener_Lista_Objetos();
        for ( int i = 0; i < listaBancos.size(); i++ ) {
            model.addElement(listaBancos.get(i).getNombre());
        }
        return model;
    }

    public boolean actualizarBanco(Bancos t) {
        return BDao.Modificar_Objeto(t);
    }

    public long getSize() {
        return BDao.Tamaño_Lista();
    }

    public Bancos getBanco(int id) {
        Bancos Banco;
        Banco = BDao.Obtener_Objeto(id);
        return Banco;
    }

    public boolean addBanco(Bancos t) {
        BDao.Agregar_Objeto(t);
        return true;
    }

    public void listar() {
        DefaultTableModel table = (DefaultTableModel) it.tb_bancos.getModel();
        Iterator ite = BDao.Obtener_Lista_Objetos().iterator();
        
        while ( ite.hasNext() ) {
            Object[] row = new Object[6];
            Bancos temp = (Bancos) ite.next();
            row[0] = temp.getNombre();
            row[1] = temp.getSiglas();
            row[2] = temp.getFuncionario() == null ? "" : temp.getFuncionario();
            row[3] = temp.getTelefono() == null ? "" : temp.getTelefono();
            row[4] = temp.getCelular() == null ? "" : temp.getCelular();
            row[5] = temp.getBanco();
            table.addRow(row);
        }
    }

    public int nextId() {
        return (int) BDao.nextId();
    }

    public List<Bancos> listar_Bancos() {
        return BDao.listar_Bancos();
    }

    public List getList() {
        return BDao.Obtener_Lista_Objetos();
    }
    
    public List getBancoParaLetra() {
        return BDao.getBancoParaLetra();
    }
}