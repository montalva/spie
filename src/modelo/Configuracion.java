
package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Configuracion {

    ArchivoConf ac;

    public Configuracion() {
    }

    public boolean guardar(ArchivoConf ac) throws IOException {
        this.ac = ac;
        String ruta = "C:\\java\\ejemplo.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(archivo));
        bw.write(ac.getIp() + "," + ac.getPuerto() + "," + ac.getBd() + "," + ac.getUsuario() + "," + ac.getPassword());
        bw.close();
        return true;
    }

    public String[] lee() throws IOException {
        String ip;
        String port;
        String bd;
        String user;
        String password;
        File archivo = new File("C:\\java\\ejemplo.txt");

        if (archivo.exists()) {
            Scanner lector = new Scanner(archivo);
            String str[] = lector.nextLine().split(",");
            return str;
        } else {
            ip = "localhost";
            port = "3306";
            bd = "spie";
            user = "user";
            password = "password";
            crearDefault(ip, port, bd, user, password);
            String[] str2 = {ip, port, bd, user, password};
            return str2;
        }
    }

    public boolean crearDefault(String ip, String port, String bd, String user, String password) throws IOException {
        String ruta = "C:\\java\\ejemplo.txt";
        File archivo = new File(ruta);
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(archivo));
        bw.write(ip + "," + port + "," + bd + "," + user + "," + password);
        bw.close();
        return true;
    }
}
