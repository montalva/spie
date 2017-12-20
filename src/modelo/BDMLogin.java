
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static modelo.BD.con;

public class BDMLogin extends BD {

    modelo.Hash Hash;

    public String bUsuario(String user, char[] pass) {
        try {
            PreparedStatement st = con.prepareStatement("select * from profesional where rut=? and password=?");
            st.setString(1, user);
            String pa = String.valueOf(pass);
            String h = Hash.sha1(pa);
            st.setString(2, Hash.sha1(String.valueOf(pass)));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("tipo_usuario");
            }                  
            return null;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (NullPointerException ne) {
            return null;
        }
    }
}
