package modelo;


import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BDMAlumno extends BD {

    public BDMAlumno() {
        
    }

    public String[] bGnero() {
        String[] genero; // = new String[3];
        int i = 1;
        try {
            PreparedStatement st = con.prepareStatement("select * from genero");
            ResultSet rs = st.executeQuery();
            rs.last();
            int t = rs.getRow();
            rs.beforeFirst();
            genero = new String[t+1];            
            genero[0] = "Seleccione genero";
            while (rs.next()) {
                genero[i++] = rs.getString("genero");
            }
            st.close();
            return genero;

        } catch (SQLException ex) {
            return null;
        }

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
            st.setInt(7, u.getGenero());
            st.setString(8, u.getDireccion());
            st.setString(9, u.getComuna());
            st.setString(10, u.getNombre_apoderado());
            st.setString(11, u.getApellido_apoderado());
            st.setString(12, u.getTelefono_apoderado());
            st.setString(13, u.getDiagnostico());
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
                    + "JOIN genero ON alumno.id_genero = genero.id_genero\n"
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
                a.setGenero(rs.getInt("id_genero"));
                a.setCurso(rs.getInt("curso"));
                a.setDireccion(rs.getString("direccion"));
                a.setComuna(rs.getString("comuna"));
                a.setNombre_apoderado(rs.getString("nombre_apoderado"));
                a.setApellido_apoderado(rs.getString("apellido_apoderado"));
                a.setTelefono_apoderado(rs.getString("telefono_apoderado"));
                a.setDiagnostico(rs.getString("diagnostico"));

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
            if (res > 0) {
                return true;
            }
            return false;

        } catch (SQLException ex) {
            return false;
        }

    }
  public ArrayList<Alumno> buscarAlumnoCurso(int curso) {

        try {
            PreparedStatement st = con.prepareStatement("SELECT rut,nombre,apellido_paterno,curso from alumno "
                    + "JOIN curso ON alumno.id_curso = curso.id_curso where alumno.id_curso=?");
            st.setInt(1,curso );
            System.out.println(st);
            ResultSet rs = st.executeQuery();
            ArrayList<Alumno> resultado= new ArrayList<>();
            while (rs.next()) {
                Alumno a = new Alumno();
                a.setRut(rs.getString("rut"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido_paterno(rs.getString("apellido_paterno"));
                a.setDireccion(rs.getString("curso"));
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
                    + "apellido_materno=?, fecha_nacto=?,id_curso=?,id_genero=?,direccion=?,comuna=?,nombre_apoderado=?,"
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
            st.setInt(7, u.getGenero());
            st.setString(8, u.getDireccion());
            st.setString(9, u.getComuna());
            st.setString(10, u.getNombre_apoderado());
            st.setString(11, u.getApellido_apoderado());
            st.setString(12, u.getTelefono_apoderado());
            st.setString(13, u.getDiagnostico());
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

    public int buscarGenero(String genero) {

        try {
            PreparedStatement st = con.prepareStatement("SELECT id_genero FROM genero where genero=?");

            st.setString(1, genero);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                int id_genero = rs.getInt("id_genero");
                return id_genero;

            }
        } catch (SQLException ex) {
            System.out.println("Error select " + ex.getMessage());
        }
        return -1;
    }

    public String buscarGenero(int id_genero) {

        try {
            PreparedStatement st = con.prepareStatement("SELECT genero FROM genero where id_genero=?");

            st.setInt(1, id_genero);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                String genero = rs.getString("genero");
                return genero;

            }
        } catch (SQLException ex) {
            System.out.println("Error select " + ex.getMessage());
        }
        return null;
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
