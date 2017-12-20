package modelo;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDMAlumno extends BD {

    public BDMAlumno() {

    }

    public String[] bCurso() {
        String[] curso;
        int i = 1;
        try {
            PreparedStatement st = con.prepareStatement("select * from curso");
            ResultSet rs = st.executeQuery();
            rs.last();
            int t = rs.getRow();
            rs.beforeFirst();
            curso = new String[t+1];
            curso[0] = "Seleccione curso";
            while (rs.next()) {
                curso[i++] = rs.getString("curso");
            }
            st.close();
            return curso;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }catch(NullPointerException ne){
            return null;
        }
    }
  public String[] bRegion() {
        String[] region;
        int i = 1;
        try {
            PreparedStatement statement= con.prepareStatement("select * from region");
            ResultSet resultSet= statement.executeQuery();
            resultSet.last();
            int t = resultSet.getRow();
            resultSet.beforeFirst();
            region = new String[t + 1];
            region[0] = "Seleccione región";
            while (resultSet.next()) {
                region[i++] = resultSet.getString("region_nombre");
            }
            statement.close();
            return region;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (NullPointerException ne) {
            return null;
        }
    }
  public String[] bComuna(String region) {
        String[] comuna;
        int i = 1;
        try {
            PreparedStatement statement = con.prepareStatement("Select comuna.comuna_nombre from comuna \n"
                    + "INNER JOIN provincia ON provincia.provincia_id=comuna.provincia_id \n"
                    + "inner join region on region.region_id=provincia.region_id\n"
                    + "where region_nombre=?");
            statement.setString(1, region);
            ResultSet resultSet= statement.executeQuery();
            resultSet.last();
            int t = resultSet.getRow();
            resultSet.beforeFirst();
            comuna = new String[t + 1];
            comuna[0] = "Seleccione comuna";
            while (resultSet.next()) {
                comuna[i++] = resultSet.getString("comuna_nombre");
            }
            statement.close();
            return comuna;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (NullPointerException ne) {
            return null;
        }
    }
    
    public String[] bDiagnostico() {
        String[] diagnostico;
        int i = 1;
        try {
            PreparedStatement statement = con.prepareStatement("select * from diagnostico");
            ResultSet resultSet = statement.executeQuery();
            resultSet.last();
            int t = resultSet.getRow();
            resultSet.beforeFirst();
            diagnostico = new String[t + 1];
            diagnostico[0] = "Seleccione diagnóstico";
            while (resultSet.next()) {
                diagnostico[i++] = resultSet.getString("diagnostico");
            }
            statement.close();
            return diagnostico;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (NullPointerException ne) {
            return null;
        }
    }
    public int agregar(Alumno u) {
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO alumno (rut,nombre,apellido_paterno,"
                    + "apellido_materno, fecha_nacto,id_curso,id_genero,direccion,comuna,nombre_apoderado,"
                    + "apellido_apoderado,telefono_apoderado,diagnostico) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            if (!u.getRut().isEmpty()) {
                st.setString(1, u.getRut());
            } else {
                return -1;
            }
            st.setString(2, u.getNombre());
            st.setString(3, u.getApellido_paterno());
            st.setString(4, u.getApellido_materno());
            st.setString(5, u.getFecha_nacimiento());
            st.setInt(6, u.getCurso());
            st.setString(7, u.getGenero());
            st.setString(8, u.getDireccion());
            st.setString(9, u.getComuna());
            st.setString(10, u.getNombre_apoderado());
            st.setString(11, u.getApellido_apoderado());
            st.setString(12, u.getTelefono_apoderado());
            st.setInt(13, u.getID_Diagnostico());
            System.out.println(st);
            int res = st.executeUpdate();
            st.close();
            return res;
        } catch (SQLException ex) {
            System.out.println("Error insert " + ex.getMessage());
            return -1;
        }
    }

    public Alumno buscar(String rut) {
        try {
            PreparedStatement st = con.prepareStatement("SELECT *\n"
                    + "FROM alumno\n"
                    + "JOIN curso ON alumno.id_curso = curso.id_curso WHERE rut=?");
            st.setString(1, rut);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Alumno a = new Alumno();
                a.setId_alumno(rs.getInt("id_alumno"));
                a.setRut(rs.getString("rut"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido_paterno(rs.getString("apellido_paterno"));
                a.setApellido_materno(rs.getString("apellido_materno"));
                a.setFecha_nacimiento(rs.getString("fecha_nacto"));
                a.setGenero(rs.getString("genero"));
                a.setCurso(rs.getInt("curso"));
                a.setDireccion(rs.getString("direccion"));
                a.setComuna(rs.getString("comuna"));
                a.setNombre_apoderado(rs.getString("nombre_apoderado"));
                a.setApellido_apoderado(rs.getString("apellido_apoderado"));
                a.setTelefono_apoderado(rs.getString("telefono_apoderado"));
                a.setID_Diagnostico(rs.getInt("id_diagnostico"));
                st.close();
                return a;
            }
        } catch (SQLException ex) {
            System.out.println("Error select " + ex.getMessage());
        }
        return null;
    }

    public boolean eliminar(String rut) {
        try {
            PreparedStatement st = con.prepareStatement("DELETE FROM alumno WHERE rut=?");

            st.setString(1, rut);
            int res = st.executeUpdate();
            st.close();
            return res > 0;
        } catch (SQLException ex) {
            return false;
        }

    }
  public ArrayList<Alumno> buscarAlumnoCurso(int curso) {

        try {
            PreparedStatement st = con.prepareStatement("SELECT * from alumno JOIN curso ON alumno.id_curso = curso.id_curso where alumno.id_curso=?");
            st.setInt(1,curso );
            System.out.println(st);
            ResultSet rs = st.executeQuery();
            ArrayList<Alumno> resultado= new ArrayList<>();
            while (rs.next()) {
                Alumno a = new Alumno();
                a.setRut(rs.getString("rut"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido_paterno(rs.getString("apellido_paterno"));
                a.setApellido_materno(rs.getString("apellido_materno"));
                a.setFecha_nacimiento(rs.getString("fecha_nacto"));
                a.setGenero(rs.getString("genero"));
                a.setDireccion(rs.getString("direccion"));
                a.setNombre_apoderado(rs.getString("nombre_apoderado"));
                a.setApellido_apoderado(rs.getString("apellido_apoderado"));
                a.setTelefono_apoderado(rs.getString("telefono_apoderado"));
                a.setID_Diagnostico(rs.getInt("id_diagnostico"));
                                      
//                 comuna
//                region      
//                      
                resultado.add(a);                
            }
            rs.close();
            st.close();
            return resultado;
        } catch (SQLException ex) {
            System.out.println("Error select " + ex.getMessage());
        }
        return null;
    }

    public int modificar(int id_alumno, Alumno u) {

        try {
            PreparedStatement st = con.prepareStatement("UPDATE alumno SET rut=?,nombre=?,apellido_paterno=?,"
                    + "apellido_materno=?, fecha_nacto=?,id_curso=?,genero=?,direccion=?,comuna=?,nombre_apoderado=?,"
                    + "apellido_apoderado=?,telefono_apoderado=?,diagnostico=? where id_alumno=?");
            if (!u.getRut().isEmpty()) {
                st.setString(1, u.getRut());
            } else {
                return -1;
            }
            st.setString(2, u.getNombre());
            st.setString(3, u.getApellido_paterno());
            st.setString(4, u.getApellido_materno());
            st.setString(5, u.getFecha_nacimiento());
            st.setInt(6, u.getCurso());
            st.setString(7, u.getGenero());
            st.setString(8, u.getDireccion());
            st.setString(9, u.getComuna());
            st.setString(10, u.getNombre_apoderado());
            st.setString(11, u.getApellido_apoderado());
            st.setString(12, u.getTelefono_apoderado());
            st.setInt(13, u.getID_Diagnostico());
            st.setInt(14, id_alumno);
            System.out.println(st);
            int res = st.executeUpdate();
            st.close();
            return res;
        } catch (SQLException ex) {
            System.out.println("Error update " + ex.getMessage());
            return -1;
        }
    }
    
    public int buscarCurso(String curso) {

        try {
            PreparedStatement st = con.prepareStatement("SELECT id_curso FROM curso where curso=?");
            st.setString(1, curso);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id_curso = rs.getInt("id_curso");
                return id_curso;
            }
        } catch (SQLException ex) {
            System.out.println("Error select " + ex.getMessage());
        }
        return -1;
    }

    public String buscarCurso(int id_curso) {
        try {
            PreparedStatement st = con.prepareStatement("SELECT curso FROM curso where id_curso=?");
            st.setInt(1, id_curso);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String curso = rs.getString("curso");
                return curso;
            }
        } catch (SQLException ex) {
            System.out.println("Error select " + ex.getMessage());
        }
        return null;
    }

}
