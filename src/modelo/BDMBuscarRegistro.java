
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BDMBuscarRegistro extends BD{

    String rut;
    public BDMBuscarRegistro() {

    }
    public ArrayList<Registro> BuscarRegistro(String rut) {
        this.rut = rut;
        try {
            PreparedStatement st = con.prepareStatement("SELECT * FROM registro, alumno,profesional where alumno.rut=? and "
                    + "alumno.id_alumno=registro.id_alumno and registro.id_profesional=profesional.id_profesional");
            st.setString(1, rut);
            System.out.println(st);
            ResultSet rs = st.executeQuery();
            ArrayList<Registro> resultado = new ArrayList<>();
            while (rs.next()) {
                Registro r = new Registro();
                r.setTipo_actividad(rs.getString("tipo_actividad"));
                r.setApoderado(rs.getString("nombre_apoderado") + " " + rs.getString("apellido_apoderado"));
                r.setProfesional(rs.getString("nombre_prof") + " " + rs.getString("apellido_pat_prof"));
                r.setFecha(rs.getString("fecha"));
                resultado.add(r);
            }
            rs.close();
            st.close();
            return resultado;
        } catch (SQLException ex) {
            System.out.println("Error select " + ex.getMessage());
        }
        return null;
    }
}
