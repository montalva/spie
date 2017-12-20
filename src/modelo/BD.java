
package modelo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BD {

    public static Connection con;
    static modelo.Configuracion conf;
    
    public static boolean conectar() throws IOException{
        conf=new modelo.Configuracion();
        String[] param = conf.lee();
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + param[0] + ":" + param[1] + "/" + param[2], param[3], param[4]);
            return true;
        } catch (SQLException ex) {

            System.out.println("Error de conexion: " + ex.getStackTrace());
        }
        return false;
    }
}
