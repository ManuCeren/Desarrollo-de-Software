
package ModeloDAO;

import Config.Conexion;
import Modelo.Agente;
import Modelo.RegistroLlamadas;
import Interfaces.CRUDAgente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class AgenteDAO implements CRUDAgente{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    RegistroLlamadasDAO daoLlamadas = new RegistroLlamadasDAO();

    // Crear un agente
    @Override
    public boolean agregar(Agente agente) {
        String sql = "INSERT INTO agente (nombre_Agente, apellido_Agente, turno, id_Departamento) VALUES (?, ?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, agente.getNombreAgente());
            ps.setString(2, agente.getApellidoAgente());
            ps.setString(3, agente.getTurno());
            ps.setInt(4, agente.getIdDepartamento());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Leer todos los agentes
    @Override
    public List<Agente> listar() {
    List<Agente> lista = new ArrayList<>();
    String sql = "SELECT a.id_Agente, a.nombre_Agente, a.apellido_Agente, a.turno, d.nombre_Departamento " +
                 "FROM agente a " +
                 "JOIN departamento d ON a.id_Departamento = d.id_Departamento";
    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Agente ag = new Agente();
            ag.setIdAgente(rs.getInt("id_Agente"));
            ag.setNombreAgente(rs.getString("nombre_Agente"));
            ag.setApellidoAgente(rs.getString("apellido_Agente"));
            ag.setTurno(rs.getString("turno"));
            ag.setNombreDepartamento(rs.getString("nombre_Departamento")); // Campo adicional para el nombre del departamento
            lista.add(ag);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return lista;
}


    // Actualizar un agente
    @Override
    public boolean actualizar(Agente agente) {
        String sql = "UPDATE agente SET nombre_Agente = ?, apellido_Agente = ?, turno = ?, id_Departamento = ? WHERE id_Agente = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, agente.getNombreAgente());
            ps.setString(2, agente.getApellidoAgente());
            ps.setString(3, agente.getTurno());
            ps.setInt(4, agente.getIdDepartamento());
            ps.setInt(5, agente.getIdAgente());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Eliminar un agente
    @Override
    public boolean eliminar(int idAgente) {
        String sql = "DELETE FROM agente WHERE id_Agente = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idAgente);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public Agente buscarPorId(int idAgente) {
        Agente agente = null;
        String sql = "SELECT * FROM agente WHERE id_Agente = ?";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, idAgente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    agente = new Agente();
                    agente.setIdAgente(rs.getInt("id_Agente"));
                    agente.setNombreAgente(rs.getString("nombre"));
                    agente.setApellidoAgente(rs.getString("apellido"));
                    agente.setTurno(rs.getString("turno"));
                    agente.setNombreDepartamento(rs.getString("departamento"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agente;
    }
    @Override
    public List<RegistroLlamadas> listarPorAgente(int idAgente) {
        List<RegistroLlamadas> lista = new ArrayList<>();
        String sql = "SELECT * FROM registroLlamadas WHERE id_Agente = ?";
        try (Connection con = cn.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAgente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RegistroLlamadas llamada = new RegistroLlamadas();
                    llamada.setIdLlamada(rs.getInt("id_Llamada"));
                    llamada.setFechaHoraLlamada(rs.getTimestamp("fecha_Hora_Llamada"));
                    llamada.setHoraInicio(rs.getTime("hora_Inicio"));
                    llamada.setHoraFinal(rs.getTime("hora_Final"));
                    llamada.setMotivoLlamada(rs.getString("motivoLlamada"));
                    llamada.setSolucion(rs.getString("solucion"));
                    llamada.setEstado(rs.getString("estado"));
                    lista.add(llamada);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}

