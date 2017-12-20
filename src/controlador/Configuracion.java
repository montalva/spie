
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Configuracion implements ActionListener, WindowListener {

    JFrame callWindow;
    vista.frmConfiguracion vc;
    modelo.Configuracion mc;
    modelo.ArchivoConf ac;

    public Configuracion(vista.frmConfiguracion vista, modelo.Configuracion mc, JFrame callWindow) throws IOException {
        this.callWindow = callWindow;
        this.mc = mc;
        this.vc = vista;
        this.vc.btnGuardar.addActionListener(this);
        String[] conf = mc.lee();
        this.vc.txtDireccion.setText(conf[0]);
        this.vc.txtPuerto.setText(conf[1]);
        this.vc.txtBD.setText(conf[2]);
        this.vc.txtUsuario.setText(conf[3]);
        this.vc.txtPassword.setText(conf[4]);
        this.vc.addWindowListener(this);
        this.vc.setVisible(true);       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "CMD_GUARDAR":
                ac = new modelo.ArchivoConf();
                ac.setIp(vc.txtDireccion.getText());
                ac.setPuerto(Integer.parseInt(vc.txtPuerto.getText()));
                ac.setBd(vc.txtBD.getText());
                ac.setUsuario(vc.txtUsuario.getText());
                ac.setPassword(vc.txtPassword.getText());
                try {
                    mc.guardar(ac);
                } catch (IOException ex) {
                    Logger.getLogger(Configuracion.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("closing");
        this.callWindow.setEnabled(true);
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
