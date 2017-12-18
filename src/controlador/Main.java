
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main implements ActionListener{
    vista.FrmMain main;

    modelo.BDProfesional bdp;
    modelo.BDMAlumno bdm;
    modelo.RegistroDataBase bdr;
    modelo.BDMBuscarRegistro mbr;
    
    public Main(vista.FrmMain vista) {
        this.main = vista;
        
        main.mnuAcerca.addActionListener(this);
        main.mnuSalir.addActionListener(this);
        main.btnAgregarAlumno.addActionListener(this);
   //     main.mnuAlumno.addActionListener(this);
        main.btnAgregarProfesional.addActionListener(this);
       // main.mnuProfesional.addActionListener(this); 
        //main.mnuAddRegistro.addActionListener(this);
        main.btnAgregarRegistro.addActionListener(this);
        
        main.mnuConf.addActionListener(this);
        main.btnBuscarRegistro.addActionListener(this);
        
        //main.mnuBReg.addActionListener(this);

        bdp = new modelo.BDProfesional();
        bdm = new modelo.BDMAlumno();
        bdr = new modelo.RegistroDataBase();
        mbr = new modelo.BDMBuscarRegistro();
        try {
            modelo.BD.conectar();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
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
                vista.frmConfiguracion vc = new vista.frmConfiguracion();
                modelo.Configuracion mc = new modelo.Configuracion();
                 
                    try {
                        controlador.Configuracion cc = new controlador.Configuracion(vc, mc, main);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                main.setEnabled(false);     
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

}
