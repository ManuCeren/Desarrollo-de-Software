
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDCliente;
import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Manuel Ceren
 */

public class ClienteDAO implements CRUDCliente{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Cliente c=new Cliente();
    
    @Override
    public List listar() {
        ArrayList<Cliente>list=new ArrayList<>();
        String sql="select * from cliente";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Cliente cli=new Cliente();
                cli.setId_Cliente(rs.getInt("id_Cliente"));
                cli.setNombre_Cliente(rs.getString("nombre_Cliente"));
                cli.setApellido_Cliente(rs.getString("apellido_Cliente"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setCorreo(rs.getString ("correo"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setFecha_Registro(rs.getString("fecha_Registro"));
                list.add(cli);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
