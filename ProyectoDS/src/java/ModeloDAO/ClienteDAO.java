
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDCliente;
import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                cli.setIdCliente(rs.getInt("id_Cliente"));
                cli.setNombreCliente(rs.getString("nombre_Cliente"));
                cli.setApellidoCliente(rs.getString("apellido_Cliente"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setCorreo(rs.getString ("correo"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setFechaRegistro(rs.getString("fecha_Registro"));
                list.add(cli);
            }
        } catch (Exception e) {
        }
        return list;
    }
    @Override
    public int contarClientes() {
    String sql = "SELECT COUNT(*) AS total FROM cliente";
    int totalClientes = 0;
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        if (rs.next()) {
            totalClientes = rs.getInt("total");
        }
    } catch (Exception e) {
        e.printStackTrace(); // Mostrar el error en consola
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    return totalClientes;
}
    @Override
    public boolean add(Cliente cliente) {
        String sql = "INSERT INTO cliente (nombre_Cliente, apellido_Cliente, telefono, correo, direccion, fecha_Registro) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection(); // Establece conexión
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombreCliente());
            ps.setString(2, cliente.getApellidoCliente());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getFechaRegistro());
            ps.executeUpdate(); // Ejecuta la consulta
            return true; // Indica que la operación fue exitosa
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
            return false; // Indica que hubo un error
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

