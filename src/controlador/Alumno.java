package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Alumno implements ActionListener, WindowListener, KeyListener, MouseListener {

    vista.FormIngresarAlumno vista;
   
    modelo.BDMAlumno modBDM;
    JFrame callWindow;
    String[] genero;
    String[] curso;
    int id_alumno = 0;

    public Alumno(vista.FormIngresarAlumno vista, modelo.BDMAlumno bdm, JFrame callWindow) throws IOException {
        this.vista = vista;
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        
        this.vista.addWindowListener(this);
        this.vista.setVisible(true);
        this.callWindow = callWindow;
   

        modBDM = bdm;
        if (cargaForm()) {
            this.vista.cmbCurso.addActionListener(this);
            this.vista.cmbRegion.addActionListener(this);
        } else {
            JOptionPane.showMessageDialog(null, "Error curso en BD",
                    "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }

    }

    public boolean cargaForm() {

        
        vista.cmbGenero.addItem("Seleccione Genero");
        vista.cmbGenero.addItem("Femenino");
        vista.cmbGenero.addItem("Masculino");
        String[] curso = modBDM.bCurso();
        String[] region = modBDM.bRegion();
        String[] diagnostico = modBDM.bDiagnostico();
        if (curso != null && region!=null && diagnostico!=null) {
            for (int i = 0; i < curso.length; i++) {
                vista.cmbCurso.addItem(curso[i]);
            }
            for (int i = 0; i < region.length; i++) {

                vista.cmbRegion.addItem(region[i]);
            }

            for (int i = 0; i < diagnostico.length; i++) {

                vista.cmbDiagnostico.addItem(diagnostico[i]);
            }
            return true;
        }
        
       
        return false;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "CMD_SAVE":
                System.out.println("Guardar");
                System.out.println(this.vista.txtRut.getText());
                modelo.Alumno ml = new modelo.Alumno();
                if (vista.cmbGenero.getSelectedIndex() > 0 && vista.cmbCurso.getSelectedIndex() > 0) {
                    
                    
                    ml.setGenero(vista.cmbGenero.getSelectedItem().toString());
                    
                    int id_curso = modBDM.buscarCurso(vista.cmbCurso.getSelectedItem().toString());
                    if (id_curso > 0) {
                        ml.setCurso(id_curso);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un género y curso",
                            "Mensaje del sistema", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                ml.setRut(vista.txtRut.getText());
                ml.setNombre(vista.txtNombre.getText());
                ml.setApellido_paterno(vista.txtApellidoPaterno.getText());
                ml.setApellido_materno(vista.txtApellidoMaterno.getText());
                ml.setFecha_nacimiento(vista.txtFNacimiento.getText());
                ml.setDireccion(vista.txtDireccion.getText());
                ml.setNombre_apoderado(vista.txtNombreApoderado.getText());
                ml.setApellido_apoderado(vista.txtApellidoApoderado.getText());
                ml.setTelefono_apoderado(vista.txtTelefonoApoderado.getText());
                
                if (!isTexto()) {
                    JOptionPane.showMessageDialog(null, "NO se aceptan numeros en los campos" + " "
                            + vista.txtNombre.getText(), "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                if (modBDM.agregar(ml) > 0) {

                    JOptionPane.showMessageDialog(null, "Alumno agregado!", "Aceptar", JOptionPane.OK_OPTION);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al agregar alumno" + " "
                            + vista.txtRut.getText(), "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
                }

                break;
            case "CMD_MOD":
                System.out.println("Modificar");
                modelo.Alumno modAl = new modelo.Alumno();
                if (vista.cmbGenero.getSelectedIndex() > 0 && vista.cmbCurso.getSelectedIndex() > 0) {
                    
                    modAl.setGenero(vista.cmbGenero.getModel().getSelectedItem().toString());
                    
                    int id_curso = modBDM.buscarCurso(vista.cmbCurso.getSelectedItem().toString());
                    if (id_curso > 0) {
                        modAl.setCurso(id_curso);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un género y curso",
                            "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                modAl.setRut(vista.txtRut.getText());
                modAl.setNombre(vista.txtNombre.getText());
                modAl.setApellido_paterno(vista.txtApellidoPaterno.getText());
                modAl.setApellido_materno(vista.txtApellidoMaterno.getText());
                modAl.setFecha_nacimiento(vista.txtFNacimiento.getText());
                modAl.setDireccion(vista.txtDireccion.getText());

                modAl.setNombre_apoderado(vista.txtNombreApoderado.getText());
                modAl.setApellido_apoderado(vista.txtApellidoApoderado.getText());
                modAl.setTelefono_apoderado(vista.txtTelefonoApoderado.getText());

                if (!isTexto()) {
                    JOptionPane.showMessageDialog(null, "NO se aceptan numeros en los campos" + " "
                            + vista.txtNombre.getText(), "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                if (modBDM.modificar(id_alumno, modAl) > 0) {
                    JOptionPane.showMessageDialog(null, "Modificado", "Aceptar", JOptionPane.OK_OPTION);
                } else {
                    JOptionPane.showMessageDialog(null, "Nose pudo modificar alumno" + " "
                            + vista.txtRut.getText() + "?", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "CMD_DEL":
                int op = JOptionPane.showConfirmDialog(null, "Desea eliminar el alumno" + " "
                        + vista.txtRut.getText() + "?", "Mensaje del sistema", JOptionPane.YES_NO_OPTION);
                System.out.println("Delete?");
                if (op != 1) {
                    System.out.println(this.vista.txtRut.getText());
                    modBDM.eliminar(vista.txtRut.getText());
                    limpiar();
                }
                break;
            case "CMD_CLR":
                System.out.println("Clear");
                limpiar();
                break;
            case "CMD_FIND":
                System.out.println("Buscar");
                 vista.BuscarAlumno vba= new vista.BuscarAlumno();
                controlador.BuscarAlumno cba = new controlador.BuscarAlumno(vba,modBDM, vista);
                break;
           
            case "CMD_REGION":
                System.out.println("region cb");
                vista.cmbComuna.removeAllItems();
               
                String region = vista.cmbRegion.getSelectedItem().toString();
                String [] comuna = modBDM.bComuna(region);
                for (int i = 0; i < comuna.length; i++) {
                    vista.cmbComuna.addItem(comuna[i]);
                }
                break;
        }
    }

    public void limpiar() {

        this.vista.txtRut.setText("");
        this.vista.txtNombre.setText("");
        this.vista.txtApellidoPaterno.setText("");
        this.vista.txtApellidoMaterno.setText("");
        this.vista.txtFNacimiento.setText("");
        this.vista.cmbGenero.setSelectedIndex(0);
        this.vista.cmbCurso.removeActionListener(this);
        this.vista.cmbCurso.setSelectedIndex(0);
        this.vista.cmbCurso.addActionListener(this);
        this.vista.txtDireccion.setText("");

        this.vista.txtNombreApoderado.setText("");
        this.vista.txtApellidoApoderado.setText("");
        this.vista.txtTelefonoApoderado.setText("");

      

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
      
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        int row = vista.tableListar.getSelectedRow();
//        System.out.println(vista.tableListar.getModel().getValueAt(row, 0) + e.paramString());
//        String rut = (String) vista.tableListar.getModel().getValueAt(row, 0);
//        String nombre = (String) vista.tableListar.getModel().getValueAt(row, 1);
//        String apellido_paterno = (String) vista.tableListar.getModel().getValueAt(row, 2);
//        vista.txtRut.setText(rut);
//        vista.txtNombre.setText(nombre);
//        vista.txtApellidoPaterno.setText(apellido_paterno);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println(e.paramString());
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public boolean isTexto() {
        if (isNumeric(vista.txtNombre.getText()) || isNumeric(vista.txtApellidoPaterno.getText())
                || isNumeric(vista.txtApellidoMaterno.getText()) || isNumeric(vista.txtNombreApoderado.getText())
                || isNumeric(vista.txtApellidoApoderado.getText())) {

            return false;
        }
        return true;
    }
}
