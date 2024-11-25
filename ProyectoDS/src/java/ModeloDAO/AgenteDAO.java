
package ModeloDAO;

import Config.Conexion;
import Modelo.Agente;
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
    Agente ag = new Agente();

    // Crear un agente
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
    public List<Agente> listar() {
        List<Agente> lista = new ArrayList<>();
        String sql = "SELECT * FROM agente";
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
                ag.setIdDepartamento(rs.getInt("id_Departamento"));
                lista.add(ag);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Actualizar un agente
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
}

