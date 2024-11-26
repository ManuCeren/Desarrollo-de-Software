
package ModeloDAO;

import Config.Conexion;
import Modelo.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    Conexion cn = new Conexion();

    public List<Ticket> listar() {
        List<Ticket> lista = new ArrayList<>();
        String sql = "SELECT * FROM ticket";
        try (Connection con = cn.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setIdTicket(rs.getInt("id_Ticket"));
                ticket.setFechaTicket(rs.getDate("fecha_Ticket"));
                ticket.setPrioridad(rs.getString("prioridad"));
                ticket.setDescripcion(rs.getString("descripcion"));
                ticket.setFechaFin(rs.getDate("fecha_Fin"));
                ticket.setEstado(rs.getString("estado"));
                ticket.setIdLlamada(rs.getInt("id_Llamada"));
                ticket.setIdCliente(rs.getInt("id_Cliente"));
                ticket.setIdAgente(rs.getInt("id_Agente"));
                lista.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregar(Ticket ticket) {
        String sql = "INSERT INTO ticket (fecha_Ticket, prioridad, descripcion, fecha_Fin, estado, id_Llamada, id_Cliente, id_Agente) "
                   + "VALUES (GETDATE(), ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = cn.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ticket.getPrioridad());
            ps.setString(2, ticket.getDescripcion());
            ps.setDate(3, new java.sql.Date(ticket.getFechaFin().getTime()));
            ps.setString(4, ticket.getEstado());
            ps.setInt(5, ticket.getIdLlamada());
            ps.setInt(6, ticket.getIdCliente());
            ps.setInt(7, ticket.getIdAgente());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}