
package appescuela;

public class AppEscuela {

    public static void main(String[] args) {


        vista.Login l = new vista.Login();
        controlador.Login1 cl = new controlador.Login1(l);
        l.setVisible(true);
    }
    
}
