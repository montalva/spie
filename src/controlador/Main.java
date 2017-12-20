
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Main implements ActionListener, WindowListener{
    vista.FrmMain main;
JFrame callWindow;
    modelo.BDProfesional bdp;
    modelo.BDMAlumno bdm;
    modelo.RegistroDataBase bdr;
    modelo.BDMBuscarRegistro mbr;
    String tipo_usuario;
    
    public Main(vista.FrmMain vista, String tipo_usuario,JFrame callWindow) {
        this.main = vista;
        this.callWindow= callWindow;
        this.tipo_usuario = tipo_usuario;
        main.mnuAcerca.addActionListener(this);
        main.mnuSalir.addActionListener(this);
        main.btnAgregarAlumno.addActionListener(this);
        main.addWindowListener(this);
        main.btnAgregarProfesional.addActionListener(this);
        this.main.setVisible(true);
        main.btnAgregarRegistro.addActionListener(this);
        
        main.mnuConf.addActionListener(this);
        main.btnBuscarRegistro.addActionListener(this);
        
        bdm = new modelo.BDMAlumno();
        bdr = new modelo.RegistroDataBase();
        mbr = new modelo.BDMBuscarRegistro();
        if (this.tipo_usuario.equals("administrador")){
            System.out.println("administrador");
                    
        }else if (this.tipo_usuario.equals("profesional")){
            System.out.println("profesional");
            main.btnAgregarAlumno.setEnabled(false);
            main.btnAgregarProfesional.setEnabled(false);
        }else if (this.tipo_usuario.equals("director")){
            System.out.println("director");
            main.btnAgregarAlumno.setEnabled(false);
            main.btnAgregarProfesional.setEnabled(false);
            main.btnAgregarRegistro.setEnabled(false);
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        System.out.println(cmd);
        switch (cmd) {
            case "CMD_AD":
                vista.Acerca vad = new vista.Acerca();
                controlador.Acerca cad = new controlador.Acerca(vad,main);
                main.setEnabled(false);
                break;
            case "CMD_SALIR":
                System.exit(0);
                break;
            case "CMD_ALUM":
                vista.FormIngresarAlumno va = new vista.FormIngresarAlumno();
                 {
                    try {
                        controlador.Alumno ca = new controlador.Alumno(va, bdm, main);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                main.setEnabled(false);
                break;
            case "CMD_PROF":
                vista.frmProfesional vp = new vista.frmProfesional();
                controlador.Profesional cp = new controlador.Profesional(vp, bdp, main);
                main.setEnabled(false);
                break;
            case "CMD_REG":
                vista.RegistroForm vr = new vista.RegistroForm();
                 {
                    try {
                        controlador.Registro rc = new controlador.Registro(vr, bdr, bdm, main);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                main.setEnabled(false);
                break;
            case "CMD_CONF":
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Enter a password:");
                JPasswordField pass = new JPasswordField(10);
                panel.add(label);
                panel.add(pass);
                String[] options = new String[]{"OK", "Cancel"};
                int option = JOptionPane.showOptionDialog(null, panel, "The title",
                        JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[1]);
                if (option == 0) // pressing OK button
                {
                    char[] password = pass.getPassword();
                    System.out.println("Your password is: " + new String(password));
                    if (String.valueOf(password).equals("cual")) {
                        vista.frmConfiguracion vc = new vista.frmConfiguracion();
                        modelo.Configuracion mc = new modelo.Configuracion();
                        main.setEnabled(false);    
                        try {
                            controlador.Configuracion cc = new controlador.Configuracion(vc, mc, main);
                        } catch (IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }
               
                 
                break;
            case "CMD_BREG":
                vista.BuscarRegistro vbr = new vista.BuscarRegistro();
        
            try {
                controlador.BuscarRegistro cbr = new controlador.BuscarRegistro(vbr, mbr,bdm, main);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        
                main.setEnabled(false);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
      
    }

    @Override
    public void windowClosing(WindowEvent e) {
      callWindow.setVisible(true);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
     
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
       
    }

    @Override
    public void windowActivated(WindowEvent e) {
      
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
     
    }

}
