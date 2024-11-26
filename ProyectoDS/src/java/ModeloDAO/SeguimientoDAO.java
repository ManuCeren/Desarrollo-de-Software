
package ModeloDAO;

import Config.Conexion;
import Modelo.Seguimiento;
import Interfaces.CRUDSeguimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeguimientoDAO implements CRUDSeguimiento{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Seguimiento  seg =new Seguimiento();

 // Método para agregar un seguimiento
    @Override
    public boolean agregar(Seguimiento seguimiento) {
        boolean resultado = false;
        String sql = "INSERT INTO seguimientos (id_Llamada, fecha_Seguimiento, acciones, resultado_Final) VALUES (?, ?, ?, ?)";

        try  {
            con = cn.getConnection(); // Establece conexión
            ps = con.prepareStatement(sql);
            ps.setInt(1, seguimiento.getIdLlamada());
            ps.setTimestamp(2, seguimiento.getFechaSeguimiento());
            ps.setString(3, seguimiento.getAcciones());
            ps.setString(4, seguimiento.getResultadoFinal());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                resultado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    // Método para listar los seguimientos de una llamada
    @Override
    public List<Seguimiento> listarPorLlamada(int idLlamada) {
        List<Seguimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM seguimientos WHERE id_Llamada = ?";

        try  {
            con = cn.getConnection(); // Establece conexión
            ps = con.prepareStatement(sql);
            ps.setInt(1, idLlamada);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Seguimiento seguimiento = new Seguimiento();
                seguimiento.setIdSeguimiento(rs.getInt("id_Seguimiento"));
                seguimiento.setIdLlamada(rs.getInt("id_Llamada"));
                seguimiento.setAcciones(rs.getString("acciones"));
                seguimiento.setResultadoFinal(rs.getString("resultado_Final"));
                seguimiento.setFechaSeguimiento(rs.getTimestamp("fecha_Seguimiento"));
                lista.add(seguimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}

