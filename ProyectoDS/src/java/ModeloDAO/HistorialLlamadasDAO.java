
package ModeloDAO;

import Config.Conexion;
import Modelo.HistorialLlamadas;
import Interfaces.CRUDHistorial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class HistorialLlamadasDAO implements CRUDHistorial{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    HistorialLlamadas hs = new HistorialLlamadas();
    
    // Crear un historial de llamada
    public boolean agregar(HistorialLlamadas historial) {
        String sql = "INSERT INTO historialLlamada (fecha_Registro, comentarios, id_Llamada) VALUES (?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(historial.getFechaRegistro().getTime()));
            ps.setString(2, historial.getComentarios());
            ps.setInt(3, historial.getIdLlamada());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Leer todos los historiales
    @Override
    public List<HistorialLlamadas> listar() {
        List<HistorialLlamadas> lista = new ArrayList<>();
        String sql = "SELECT * FROM historialLlamada";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                HistorialLlamadas his = new HistorialLlamadas();
                
                his.setIdHistorial(rs.getInt("id_Historial"));
                his.setFechaRegistro(rs.getDate("fecha_Registro"));
                his.setComentarios(rs.getString("comentarios"));
                his.setIdLlamada(rs.getInt("id_Llamada"));
                lista.add(his);
            }
                  
        } catch (Exception e) {
            e.printStackTrace();  
        }
        return lista;
    }

    // Actualizar un historial
    @Override
    public boolean actualizar(HistorialLlamadas historial) {
        String sql = "UPDATE historialLlamada SET fecha_Registro = ?, comentarios = ?, id_Llamada = ? WHERE id_Historial = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(historial.getFechaRegistro().getTime()));
            ps.setString(2, historial.getComentarios());
            ps.setInt(3, historial.getIdLlamada());
            ps.setInt(4, historial.getIdHistorial());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Eliminar un historial
    @Override
    public boolean eliminar(int idHistorial) {
        String sql = "DELETE FROM historialLlamada WHERE id_Historial = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idHistorial);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
