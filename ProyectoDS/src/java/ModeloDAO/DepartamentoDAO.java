
package ModeloDAO;

import Config.Conexion;
import Modelo.Departamento;
import Interfaces.CRUDDepartamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO implements CRUDDepartamento{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Departamento dep = new Departamento();

    // Método para listar departamentos
    public List<Departamento> listar() {
        List<Departamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM departamento";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Departamento dep = new Departamento();
                dep.setIdDepartamento(rs.getInt("id_Departamento"));
                dep.setNombreDepartamento(rs.getString("nombre_Departamento"));
                lista.add(dep);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}

