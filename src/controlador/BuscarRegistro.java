
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import modelo.BDMBuscarRegistro;

public class BuscarRegistro implements ActionListener,WindowListener,MouseListener {
    vista.BuscarRegistro BRegForm;
    modelo.BDMBuscarRegistro BuscaReg;
    modelo.BDMAlumno BDMAlumno;
    JFrame windowCall;

    public BuscarRegistro(vista.BuscarRegistro BRegForm, BDMBuscarRegistro BuscaReg,modelo.BDMAlumno BDMAlumno, JFrame windowCall) throws IOException {
        this.BRegForm = BRegForm;
        this.BuscaReg = BuscaReg;
        this.BDMAlumno = BDMAlumno;
        this.windowCall = windowCall;
        this.BRegForm.tblAlumno.addMouseListener(this);
        this.BRegForm.tblRegistro.addMouseListener(this);
        this.BRegForm.addWindowListener(this);
        this.BRegForm.setVisible(true);
        cargaForm();
        this.BRegForm.cmdCurso.addActionListener(this);
    }
    public boolean cargaForm(){         
        String[] curso = BDMAlumno.bCurso();
        for (int i = 0; i < curso.length; i++) {
            BRegForm.cmdCurso.addItem(curso[i]);
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "CMD_CURSO":
                System.out.println("CMD_CURSO");
                DefaultTableModel modeloTabla = (DefaultTableModel) BRegForm.tblAlumno.getModel();
                Object[] fila = new Object[4];
                String curso = BRegForm.cmdCurso.getSelectedItem().toString();
                int id_curso = BDMAlumno.buscarCurso(curso);
                Iterator<modelo.Alumno> itr = BDMAlumno.buscarAlumnoCurso(id_curso).iterator();
                modeloTabla.setRowCount(0);
                while (itr.hasNext()) {
                    modelo.Alumno alumno = itr.next();
                    fila[0] = alumno.getRut();
                    fila[1] = alumno.getNombre();
                    fila[2] = alumno.getApellido_paterno();
                    fila[3] = alumno.getDireccion();
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
        this.windowCall.setEnabled(true);
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
    public void mouseClicked(MouseEvent e) {
        int row = BRegForm.tblAlumno.getSelectedRow();
        System.out.println(BRegForm.tblAlumno.getModel().getValueAt(row, 0)+e.paramString());
        String rut =   (String)BRegForm.tblAlumno.getModel().getValueAt(row, 0);
        DefaultTableModel modeloTabla = (DefaultTableModel) BRegForm.tblRegistro.getModel();
        Object[] fila = new Object[4];
        Iterator<modelo.Registro> itr = BuscaReg.BuscarRegistro(rut).iterator();
        modeloTabla.setRowCount(0);
        while (itr.hasNext()) {
            modelo.Registro re = itr.next();
            fila[0] = re.getTipo_actividad();
            fila[1] = re.getApoderado();
            fila[2] = re.getProfesional();
            fila[3] = re.getFecha();
            modeloTabla.addRow(fila);
        } 
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
}
