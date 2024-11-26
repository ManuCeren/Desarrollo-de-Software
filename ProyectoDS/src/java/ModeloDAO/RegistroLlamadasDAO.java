
package ModeloDAO;

import Config.Conexion;
import Modelo.RegistroLlamadas;
import Interfaces.CRUDRegistro;
import Modelo.Seguimiento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroLlamadasDAO implements CRUDRegistro {

    // Conexión a la base de datos
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    
    // Listar todas las llamadas
    @Override
    public List<RegistroLlamadas> listar() {
        List<RegistroLlamadas> lista = new ArrayList<>();
        String sql = "SELECT * FROM registroLlamadas"; 
        try {
            con = cn.getConnection();
             ps = con.prepareStatement(sql);
             rs = ps.executeQuery();
            while (rs.next()) {
                RegistroLlamadas llamada = new RegistroLlamadas();
                llamada.setIdLlamada(rs.getInt("id_Llamada"));
                llamada.setFechaHoraLlamada(rs.getTimestamp("fecha_Hora_Llamada"));
                llamada.setHoraInicio(rs.getTime("hora_Inicio"));
                llamada.setHoraFinal(rs.getTime("hora_Final"));
                llamada.setMotivoLlamada(rs.getString("motivoLlamada"));
                llamada.setSolucion(rs.getString("solucion"));
                llamada.setEstado(rs.getString("estado"));
                llamada.setIdCliente(rs.getInt("id_Cliente"));
                llamada.setIdAgente(rs.getInt("id_Agente"));
                llamada.setIdCategoria(rs.getInt("id_Categoria"));
                lista.add(llamada);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    @Override
    public List<Seguimiento> listarPorLlamada(int idLlamada) {
        List<Seguimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM seguimiento WHERE id_Llamada = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idLlamada);
            rs = ps.executeQuery();
            while (rs.next()) {
                Seguimiento seguimiento = new Seguimiento();
                seguimiento.setIdSeguimiento(rs.getInt("id_Seguimiento"));
                seguimiento.setIdLlamada(rs.getInt("id_Llamada"));
                seguimiento.setAcciones(rs.getString("acciones"));
                seguimiento.setResultadoFinal(rs.getString("resultadoFinal"));
                seguimiento.setFechaSeguimiento(rs.getTimestamp("fecha_Seguimiento"));
                lista.add(seguimiento);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }



    @Override
    public int contarLlamadas() {
        String sql = "SELECT COUNT(*) AS total FROM registroLlamadas";
        int totalLlamadas = 0;
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                totalLlamadas = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalLlamadas;
    }

    // Agregar una nueva llamada
    @Override
public boolean agregar(RegistroLlamadas llamada) {
    String sql = "INSERT INTO registroLlamadas (fecha_Hora_Llamada, hora_Inicio, hora_Final, motivoLlamada, solucion, estado, id_Cliente, id_Agente, id_Categoria) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try  {
        
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setTimestamp(1, llamada.getFechaHoraLlamada());
        ps.setTime(2, llamada.getHoraInicio());
        ps.setTime(3, llamada.getHoraFinal());
        ps.setString(4, llamada.getMotivoLlamada());
        ps.setString(5, llamada.getSolucion());
        ps.setString(6, llamada.getEstado());
        ps.setInt(7, llamada.getIdCliente());
        ps.setInt(8, llamada.getIdAgente());
        ps.setInt(9, llamada.getIdCategoria());
        ps.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    // Eliminar una llamada por ID
    @Override
    public boolean eliminar(int idLlamada) {
        String sql = "DELETE FROM registroLlamadas WHERE id_Llamada = ?";
        try  {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, idLlamada);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtener una llamada por ID
    @Override
    public RegistroLlamadas buscarPorId(int idLlamada) {
        RegistroLlamadas llamada = null;
        String sql = "SELECT * FROM registroLlamadas WHERE id_Llamada = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, idLlamada);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    llamada = new RegistroLlamadas();
                    llamada.setIdLlamada(rs.getInt("id_Llamada"));
                    llamada.setFechaHoraLlamada(rs.getTimestamp("fecha_Hora_Llamada"));
                    llamada.setHoraInicio(rs.getTime("hora_Inicio"));
                    llamada.setHoraFinal(rs.getTime("hora_Final"));
                    llamada.setMotivoLlamada(rs.getString("motivoLlamada"));
                    llamada.setSolucion(rs.getString("solucion"));
                    llamada.setEstado(rs.getString("estado"));
                    llamada.setIdCliente(rs.getInt("id_Cliente"));
                    llamada.setIdAgente(rs.getInt("id_Agente"));
                    llamada.setIdCategoria(rs.getInt("id_Categoria"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return llamada;
    }

    // Actualizar una llamada
    @Override
    public boolean actualizar(RegistroLlamadas llamada) {
        String sql = "UPDATE registroLlamadas SET estado = ? WHERE id_Llamada = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            
            ps.setString(1, llamada.getEstado());
            ps.setInt(2, llamada.getIdLlamada());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public List<RegistroLlamadas> listarPorAgente(int idAgente) {
        List<RegistroLlamadas> lista = new ArrayList<>();
        String sql = "SELECT * FROM registroLlamadas WHERE id_Agente = ?"; // Asumiendo que la columna es id_Agente
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idAgente);
            rs = ps.executeQuery();
            while (rs.next()) {
                RegistroLlamadas llamada = new RegistroLlamadas();
                llamada.setIdLlamada(rs.getInt("id_Llamada"));
                llamada.setFechaHoraLlamada(rs.getTimestamp("fecha_Hora_Llamada"));
                llamada.setHoraInicio(rs.getTime("hora_Inicio"));
                llamada.setHoraFinal(rs.getTime("hora_Final"));
                llamada.setMotivoLlamada(rs.getString("motivoLlamada"));
                llamada.setSolucion(rs.getString("solucion"));
                llamada.setEstado(rs.getString("estado"));
                llamada.setIdCliente(rs.getInt("id_Cliente"));
                llamada.setIdAgente(rs.getInt("id_Agente"));
                llamada.setIdCategoria(rs.getInt("id_Categoria"));
                lista.add(llamada);
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
        return lista;
    }


}




