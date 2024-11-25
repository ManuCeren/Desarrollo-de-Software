
package ModeloDAO;

import Config.Conexion;
import Modelo.Encuesta;
import Interfaces.CRUDEncuesta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EncuestaDAO implements CRUDEncuesta{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Encuesta enc =new Encuesta();

    // Método para listar encuestas
    @Override
    public List<Encuesta> listar() {
        List<Encuesta> lista = new ArrayList<>();
        String sql = "SELECT e.id_Encuesta, e.calificacion, e.comentarios, l.fecha_Hora_Llamada " +
                     "FROM encuesta e " +
                     "JOIN registroLlamadas l ON e.id_Llamada = l.id_Llamada";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Encuesta encuesta = new Encuesta();
                encuesta.setIdEncuesta(rs.getInt("id_Encuesta"));
                encuesta.setCalificacion(rs.getInt("calificacion"));
                encuesta.setComentarios(rs.getString("comentarios"));
                encuesta.setFechaHoraLlamada(rs.getString("fecha_Hora_Llamada"));
                lista.add(encuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    @Override
    public boolean agregar(Encuesta encuesta) {
        String sql = "INSERT INTO encuesta (calificacion, comentarios, id_Llamada) VALUES (?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, encuesta.getCalificacion());
            ps.setString(2, encuesta.getComentarios());
            ps.setInt(3, encuesta.getIdLlamada());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

