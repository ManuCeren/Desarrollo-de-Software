
package ModeloDAO;

import Config.Conexion;
import Modelo.Ticket;
import Interfaces.CRUDTicket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements CRUDTicket{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Crear un ticket
    @Override
    public boolean agregar(Ticket ticket) {
        String sql = "INSERT INTO ticket (fecha_Ticket, prioridad, descripcion, fecha_Fin, estado, id_Llamada, id_Cliente, id_Agente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setTimestamp(1, new java.sql.Timestamp(ticket.getFechaTicket().getTime()));
            ps.setString(2, ticket.getPrioridad());
            ps.setString(3, ticket.getDescripcion());
            ps.setTimestamp(4, new java.sql.Timestamp(ticket.getFechaFin().getTime()));
            ps.setString(5, ticket.getEstado());
            ps.setInt(6, ticket.getIdLlamada());
            ps.setInt(7, ticket.getIdCliente());
            ps.setInt(8, ticket.getIdAgente());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Leer todos los tickets
    @Override
    public List<Ticket> listar() {
        List<Ticket> lista = new ArrayList<>();
        String sql = "SELECT * FROM ticket";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setIdTicket(rs.getInt("id_Ticket"));
                ticket.setFechaTicket(rs.getTimestamp("fecha_Ticket"));
                ticket.setPrioridad(rs.getString("prioridad"));
                ticket.setDescripcion(rs.getString("descripcion"));
                ticket.setFechaFin(rs.getTimestamp("fecha_Fin"));
                ticket.setEstado(rs.getString("estado"));
                ticket.setIdLlamada(rs.getInt("id_Llamada"));
                ticket.setIdCliente(rs.getInt("id_Cliente"));
                ticket.setIdAgente(rs.getInt("id_Agente"));
                lista.add(ticket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Actualizar un ticket
    @Override
    public boolean actualizar(Ticket ticket) {
        String sql = "UPDATE ticket SET fecha_Ticket = ?, prioridad = ?, descripcion = ?, fecha_Fin = ?, estado = ?, id_Llamada = ?, id_Cliente = ?, id_Agente = ? WHERE id_Ticket = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setTimestamp(1, new java.sql.Timestamp(ticket.getFechaTicket().getTime()));
            ps.setString(2, ticket.getPrioridad());
            ps.setString(3, ticket.getDescripcion());
            ps.setTimestamp(4, new java.sql.Timestamp(ticket.getFechaFin().getTime()));
            ps.setString(5, ticket.getEstado());
            ps.setInt(6, ticket.getIdLlamada());
            ps.setInt(7, ticket.getIdCliente());
            ps.setInt(8, ticket.getIdAgente());
            ps.setInt(9, ticket.getIdTicket());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Eliminar un ticket
    @Override
    public boolean eliminar(int idTicket) {
        String sql = "DELETE FROM ticket WHERE id_Ticket = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idTicket);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

