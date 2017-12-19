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
public class BDMLogin extends BD{
    
     public boolean bUsuario(String user ,char[] pass) {
      
        try {
            PreparedStatement st = con.prepareStatement("select * from profesional where rut=?");
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            
            
           rs.next();
           
           String usuario = rs.getString("rut");
           String password = rs.getString("contraseña");
           st.close();
           if (user.equals(usuario) && pass==password){
               return true;
           }else{
               return false;
           }
           
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }catch(NullPointerException ne){
            return false;
        }
    }
}
