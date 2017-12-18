package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Registro implements ActionListener, WindowListener {

    modelo.RegistroDataBase registroDB;
    modelo.BDMAlumno modBDM;
    vista.RegistroForm registroForm;
    modelo.Alumno ma; 
    int id_alumno;
    JFrame callWindow;
    public Registro(){
        
    }
    
    public Registro(vista.RegistroForm registroForm,modelo.RegistroDataBase registroDB,modelo.BDMAlumno modBDM,JFrame callWindow) throws IOException {
        this.registroDB = registroDB;
        this.registroForm = registroForm;
        this.callWindow = callWindow;
        this.modBDM = modBDM;
        this.registroForm.buscarButton.addActionListener(this);
        this.registroForm.btnGuardar.addActionListener(this);
        this.registroForm.modificarButton.addActionListener(this);
        this.registroForm.eliminarButton.addActionListener(this);
        this.registroForm.limpiarButton.addActionListener(this);
        this.registroForm.addWindowListener(this);
        this.registroForm.setVisible(true);
        this.registroForm.txtApellidoAlumno.setEditable(false);
        this.registroForm.txtNombreAlum.setEditable(false);
        this.registroForm.txtNombreApoderado.setEditable(false);
        this.registroForm.txtApellidoApoderado.setEditable(false);
        llenarProfesional();
    }
    public int llenarProfesional(){
        String[] profesional;
        profesional = registroDB.buscarProfesional();
        
        for(int i=0;i<profesional.length;i++)
           this.registroForm.cmbProfesional.addItem(profesional[i]);
        return -1;
    }
   
    @Override
    public void actionPerformed(ActionEvent ae) {
        String cmd = ae.getActionCommand();
        modelo.Registro auxReg = new modelo.Registro();
        modelo.Alumno ma; 
        switch (cmd) {
            case "CMD_GUARDAR":
                System.out.println("Guardar");
                if (this.registroForm.cmbProfesional.getSelectedIndex()>0) {
                    String string = this.registroForm.cmbProfesional.getSelectedItem().toString();
                    String[] parts = string.split("-");
                    int id_profesional = registroDB.buscarProfesional(parts[0]);
                    if(id_profesional>0)
                        auxReg.setId_profesional(id_profesional);
                    else {
                        System.err.println("error id profesional");
                        break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un profesional",
                             "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                auxReg.setId_alumno(id_alumno);
                auxReg.setFecha(this.registroForm.txtFecha.getText());
                auxReg.setTipo_actividad(this.registroForm.txtActividad.getText());
                auxReg.setObjetivo(this.registroForm.txtObjetivo.getText());
                auxReg.setResultado(this.registroForm.txtResultado.getText());
                auxReg.setAcuerdo(this.registroForm.txtAcuerdo.getText());
                this.registroDB.agregarRegistro(auxReg);
                break;
            case "CMD_BUSCAR":
                System.out.println("CMD_BUSCAR");
                ma=modBDM.buscar(this.registroForm.txtRutAlum.getText());
                this.registroForm.txtNombreAlum.setText(ma.getNombre());
                this.registroForm.txtApellidoAlumno.setText(ma.getApellido_paterno());
                this.registroForm.txtNombreApoderado.setText(ma.getNombre_apoderado());
                this.registroForm.txtApellidoApoderado.setText(ma.getApellido_apoderado());
                id_alumno=ma.getId_alumno();
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("closing");
        callWindow.setEnabled(true);
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
