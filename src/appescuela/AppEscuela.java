
package appescuela;

public class AppEscuela {

    public static void main(String[] args) {
//        modelo.Alumno modelo = new modelo.Alumno();
//        vista.FormIngresarAlumno vista = new vista.FormIngresarAlumno();
//        controlador.Alumno controlador = new controlador.Alumno(vista);
//        
//         modelo.Profesional modelo1 = new modelo.Profesional();
//        vista.frmProfesional vista1 = new vista.frmProfesional();
//        controlador.Profesional controlador1 = new controlador.Profesional(vista1);
        
        vista.FrmMain v0 = new vista.FrmMain();
        controlador.Main c0 = new controlador.Main(v0);
        v0.setVisible(true);
    }
    
}
