package ModeloDAO;

import Config.Conexion;
import Modelo.Categoria;
import Interfaces.CRUDCategoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements CRUDCategoria{
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Categoria cat= new Categoria();

    // Método para listar categorías
    public List<Categoria> listar() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categorias";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("id_Categoria"));
                cat.setDescripcion(rs.getString("descripcion"));
                lista.add(cat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Método para agregar una categoría
    public boolean agregar(Categoria categoria) {
        String sql = "INSERT INTO categorias (descripcion) VALUES (?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getDescripcion());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
