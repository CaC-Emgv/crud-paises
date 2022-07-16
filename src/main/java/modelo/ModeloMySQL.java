package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ModeloMySQL implements Modelo{
    

    
    private static final String GET_ALL_QUERY = "SELECT * FROM paises";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM paises WHERE id = ?";
    private static final String ADD_QUERY = "INSERT INTO paises VALUES (null, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE paises SET nombre=?, poblacion=?, superficie = ?, fechaIndependencia=?, fotoBandera=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM paises WHERE id = ?";

    @Override
    public List<Pais> getPaises() {
        List<Pais> paises = new ArrayList<>();
        try ( Connection con = Conexion.getConnection();  PreparedStatement ps = con.prepareStatement(GET_ALL_QUERY);  ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                paises.add(rsToPais(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al leer los paises en la Base de Datos", ex);
        }
        return paises;
    }

    @Override
    public Pais getPais(int id) {
        Pais pa = null;
        try ( Connection con = Conexion.getConnection();  PreparedStatement ps = con.prepareStatement(GET_BY_ID_QUERY);) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery();) {
                rs.next();
                pa = rsToPais(rs);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al obtener el pais con la ID " + id + " de la Base de Datos", ex);
        }
        return pa;
    }

    @Override
    public int addPais(Pais pais) {
        int regsAgregados = 0;
        try ( Connection con = Conexion.getConnection();  PreparedStatement ps = con.prepareStatement(ADD_QUERY);) {
            fillPreparedStatement(ps, pais);
            regsAgregados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al agregar pais " + pais.getNombre(), ex);
        }
        return regsAgregados;
    }

    @Override
    public int updatePais(Pais pais) {
        int regsActualizados = 0;
        try ( Connection con = Conexion.getConnection();  PreparedStatement ps = con.prepareStatement(UPDATE_QUERY);) {
            fillPreparedStatement(ps, pais);
            ps.setInt(6, pais.getId());
            regsActualizados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al editar pais " + pais.getNombre(), ex);
        }
        return regsActualizados;
    }

    @Override
    public int removePais(int id) {
        int regsBorrados = 0;
        try ( Connection con = Conexion.getConnection();  PreparedStatement ps = con.prepareStatement(DELETE_QUERY);) {
            ps.setInt(1, id);
            regsBorrados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al borrar pais con la ID " + id, ex);
        }
        return regsBorrados;
    }


    private Pais rsToPais(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        int poblacion = rs.getInt("poblacion");
        int superficie = rs.getInt("superficie");
        String fechaIndependencia = rs.getString("fechaIndependencia");
        String fotoBandera = rs.getString("fotoBandera");
        return new Pais(id, nombre, poblacion, superficie, fechaIndependencia, fotoBandera);
    } 
    
        private void fillPreparedStatement(PreparedStatement ps, Pais pais) throws SQLException {
        ps.setString(1, pais.getNombre());
        ps.setInt(2, pais.getPoblacion());
        ps.setInt(3, pais.getSuperficie());
        ps.setString(4, pais.getFechaIndependencia());
        ps.setString(5, pais.getBandera());
    }
    
    
    
    
}
