
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Profesional implements ActionListener, WindowListener{
    vista.frmProfesional profesionalForm;
    modelo.BDProfesional modBD;
    JFrame windowCall;
    public Profesional(vista.frmProfesional profesionalForm,modelo.BDProfesional bdp, JFrame windowCall) {
        this.profesionalForm = profesionalForm;
        this.modBD = bdp;
        this.profesionalForm.btnGuardar.addActionListener(this);
        this.profesionalForm.btnBuscar.addActionListener(this);
        this.profesionalForm.btnEliminar.addActionListener(this);
        this.profesionalForm.btnLimpiar.addActionListener(this);
        this.profesionalForm.btnModificar.addActionListener(this);
        this.profesionalForm.addWindowListener(this);
        this.windowCall = windowCall;
        this.profesionalForm.setVisible(true);
    }
     @Override
    public void actionPerformed(ActionEvent e) {
       
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "CMD_SAVE":
                System.out.println("Guardar");
                System.out.println(this.profesionalForm.txtRut.getText());
                modelo.Profesional ml = new modelo.Profesional();
                ml.setRut(profesionalForm.txtRut.getText());
                ml.setNombre(profesionalForm.txtNombre.getText());
                modBD.guardar(ml);
                break;
            case "CMD_MOD":
                System.out.println("Modificar"); 
                String buscar = profesionalForm.txtBuscar.getText();
                String rut1 = profesionalForm.txtRut.getText();
                String nombre = profesionalForm.txtNombre.getText();
                modelo.Profesional modP = new modelo.Profesional(rut1, nombre);
                if(!modBD.modificar(buscar,modP)){
                    JOptionPane.showMessageDialog(null, "Nose pudo modificar profesional" + " " 
                        + profesionalForm.txtRut.getText()+"?", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
                }else
                     JOptionPane.showMessageDialog(null, "Modificado", "Aceptar", JOptionPane.OK_OPTION);  
                break;
            case "CMD_DEL":
                int op = JOptionPane.showConfirmDialog(null, "Desea eliminar el profesional" + " " 
                        + profesionalForm.txtRut.getText()+"?", "Mensaje del sistema", JOptionPane.YES_NO_OPTION);
                System.out.println("Delete?");
                if (op!=1){
                    modelo.Profesional l = new modelo.Profesional();
                    l.setRut(profesionalForm.txtRut.getText());
                    System.out.println(this.profesionalForm.txtRut.getText());
                    modBD.eliminar(l);     
                }
                break;
            case "CMD_CLR":
                System.out.println("Clear");
                this.profesionalForm.txtRut.setText("");
                this.profesionalForm.txtNombre.setText("");
             
                break;
            case "CMD_FIND":
                System.out.println("Buscar");
                String rut = profesionalForm.txtBuscar.getText();
                modelo.Profesional mod = modBD.buscar(rut);
                if (mod!=null){
                    System.out.println(mod.getRut());
                    profesionalForm.txtRut.setText(mod.getRut());
                    profesionalForm.txtNombre.setText(mod.getNombre());
                }else{
                    JOptionPane.showMessageDialog(null, "No se encontró", "Error", JOptionPane.ERROR_MESSAGE);  
                }
                break;
        }
        
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        windowCall.setEnabled(true);
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
