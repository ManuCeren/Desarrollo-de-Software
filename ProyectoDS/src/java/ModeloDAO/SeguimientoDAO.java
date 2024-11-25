
package ModeloDAO;

import Config.Conexion;
import Modelo.Seguimiento;
import Interfaces.CRUDSeguimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeguimientoDAO implements CRUDSeguimiento{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Seguimiento seg =new Seguimiento();

    // Método para listar seguimientos
    public List<Seguimiento> listar() {
        List<Seguimiento> lista = new ArrayList<>();
        String sql = "SELECT * FROM seguimientos";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Seguimiento seg = new Seguimiento();
                seg.setIdSeguimiento(rs.getInt("id_Seguimiento"));
                seg.setFechaSeguimiento(rs.getDate("fecha_Seguimiento"));
                seg.setAcciones(rs.getString("acciones"));
                seg.setResultadoFinal(rs.getString("resultado_Final"));
                seg.setIdLlamada(rs.getInt("id_Llamada"));
                lista.add(seg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}

