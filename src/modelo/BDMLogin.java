/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static modelo.BD.con;

/**
 *
 * @author usuario 2
 */
public class BDMLogin extends BD {

    modelo.Hash Hash;

    public boolean bUsuario(String user, char[] pass) {

        try {
            PreparedStatement st = con.prepareStatement("select * from profesional where rut=? and password=?");
            st.setString(1, user);
            String pa = String.valueOf(pass);
            String h = Hash.sha1(pa);
            st.setString(2, Hash.sha1(String.valueOf(pass)));
            ResultSet rs = st.executeQuery();

            return rs.next();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        } catch (NullPointerException ne) {
            return false;
        }
    }
}
