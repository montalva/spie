
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistroDataBase extends BD {

    public RegistroDataBase() {

    }
public boolean agregarRegistro(Registro reg ){
     try {            
                     
            PreparedStatement st = con.prepareStatement("insert into registro (id_alumno, id_profesional,fecha,"
                    + "tipo_actividad,objetivo,resultado,acuerdo) values (?,?,?,?,?,?,?)");
            st.setInt(1, reg.getId_alumno());
            st.setInt(2, reg.getId_profesional());
            st.setString(3, reg.getFecha());
            st.setString(4, reg.getTipo_actividad());
            st.setString(5, reg.getObjetivo());
            st.setString(6, reg.getResultado());
            st.setString(7, reg.getAcuerdo());            
            System.out.println(st);
            int res = st.executeUpdate();
            st.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Error insert " + ex.getMessage());
            return false;
        }
    
}
    public int buscarProfesional(String nombre) {
        try {
            PreparedStatement st = con.prepareStatement("select id_profesional from profesional where nombre_prof=?");
            st.setString(1, nombre);
            System.err.println(st);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id_profesional = rs.getInt("id_profesional");
                System.err.println("" + id_profesional);
                return id_profesional;
            }
            return -1;

        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return -1;
        }
       
    }

    public String[] buscarProfesional() {
        String[] profesional;
        int i = 1;
        try {
            PreparedStatement st = con.prepareStatement("select * from profesional");
            ResultSet rs = st.executeQuery();
           
            rs.last();
            int t = rs.getRow();
            rs.beforeFirst();
            profesional = new String[t+1]; 
            profesional[0] = "Seleccione profesional";
            while (rs.next()) {
                profesional[i++] = rs.getString("nombre_prof") + "-" + rs.getString("profesion");
            }
            st.close();
            return profesional;

        } catch (SQLException ex) {
            return null;
        }

    }





 
    
}
