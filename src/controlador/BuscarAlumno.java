
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BuscarAlumno implements ActionListener, WindowListener {

    vista.BuscarAlumno buscaAlumno;
    JFrame callWindow;
    modelo.BDMAlumno modBDM;

    public BuscarAlumno(vista.BuscarAlumno buscaAlumno, modelo.BDMAlumno modBDM, JFrame callWindow) {
        this.buscaAlumno = buscaAlumno;
        this.callWindow = callWindow;
        this.modBDM = modBDM;
        buscaAlumno.setVisible(true);
        buscaAlumno.addWindowListener(this);
        String[] curso = modBDM.bCurso();
        if (curso != null) {
            for (int i = 0; i < curso.length; i++) {
               
                buscaAlumno.cmbCurso.addItem(curso[i]);
            }
         }
         buscaAlumno.cmbCurso.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "CMD_LISTAR":
                System.out.println("CMD_LISTAR");
                DefaultTableModel modeloTabla = (DefaultTableModel) buscaAlumno.tableListar.getModel();
                Object[] fila = new Object[13];
                String curso = buscaAlumno.cmbCurso.getSelectedItem().toString();
                int id_curso = modBDM.buscarCurso(curso);
                Iterator<modelo.Alumno> itr = modBDM.buscarAlumnoCurso(id_curso).iterator();
                modeloTabla.setRowCount(0);
                while (itr.hasNext()) {
                    modelo.Alumno alumno = itr.next();
                    fila[0] = alumno.getRut();
                    fila[1] = alumno.getNombre();
                    fila[2] = alumno.getApellido_paterno();
                    fila[3] = alumno.getApellido_materno();
                    fila[4] = alumno.getFecha_nacimiento();
                    fila[5] = alumno.getGenero();
                    fila[6] = alumno.getDireccion();
                    fila[7] = alumno.getComuna();
                    fila[8] = alumno.getRegion();
                    fila[9] = alumno.getNombre_apoderado();
                    fila[10] = alumno.getApellido_apoderado();
                    fila[11] = alumno.getTelefono_apoderado();
                    fila[12] = alumno.getID_Diagnostico();
                    modeloTabla.addRow(fila);
                }
                break;
        }
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
    
}
