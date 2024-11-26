
package ModeloDAO;

import Config.Conexion;
import Interfaces.CRUDCliente;
import Modelo.Cliente;
import java.sql.*;
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
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombreCliente());
            ps.setString(2, cliente.getApellidoCliente());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getFechaRegistro());
            int filasAfectadas = ps.executeUpdate(); // Verificación de la cantidad de filas afectadas
            return filasAfectadas > 0; // Si se afectó alguna fila, el cliente se insertó
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    @Override
    public Cliente list(int id) {
        Cliente cli = new Cliente(); // Creación de nuevo objeto
        String sql = "SELECT * FROM cliente WHERE id_Cliente=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                cli.setIdCliente(rs.getInt("id_Cliente"));
                cli.setNombreCliente(rs.getString("nombre_Cliente"));
                cli.setApellidoCliente(rs.getString("apellido_Cliente"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setCorreo(rs.getString("correo"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setFechaRegistro(rs.getString("fecha_Registro"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return cli;
    }

    
    @Override
    public boolean eliminar(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean eliminado = false;

        try {
            con = cn.getConnection();
            // Cambié 'clientes' por 'cliente'
            String sql = "DELETE FROM cliente WHERE id_Cliente=?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                eliminado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return eliminado;
    }
    
    @Override
    public boolean edit(Cliente cli) {
            String sql = "UPDATE cliente SET nombre_Cliente=?, apellido_Cliente=?, telefono=?, correo=?, direccion=?, fecha_Registro=? WHERE id_Cliente=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNombreCliente());
            ps.setString(2, cli.getApellidoCliente());
            ps.setString(3, cli.getTelefono());
            ps.setString(4, cli.getCorreo());
            ps.setString(5, cli.getDireccion());
            ps.setString(6, cli.getFechaRegistro());
            ps.setInt(7, cli.getIdCliente());

            int filasActualizadas = ps.executeUpdate();
            return filasActualizadas > 0; // Devuelve true si se actualizó al menos una fila
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Devuelve false si ocurre un error
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

