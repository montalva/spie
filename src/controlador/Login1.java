
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class Login1 implements ActionListener {

    vista.Login login;
    modelo.BDMLogin bdmlogin;
    modelo.Hash Hash;

    public Login1(vista.Login vista) {
        this.login = vista;
        this.login.btnLogin.addActionListener(this);
        this.login.jmnuconf.addActionListener(this);
        bdmlogin = new modelo.BDMLogin();
        try {
            modelo.BD.conectar();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       String cmd = e.getActionCommand();
        switch (cmd) {
            case "CMD_ENTER":
                String tipo_usuario=bdmlogin.bUsuario(login.txtUsuario.getText(), login.txtPassword.getPassword());
                if (tipo_usuario!=null) {
                    vista.FrmMain v0 = new vista.FrmMain();
                    controlador.Main c0 = new controlador.Main(v0,tipo_usuario,login);
                    this.login.txtPassword.setText("");
                    this.login.txtUsuario.setText("");
                    this.login.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "CMD_CONF":
                JPanel panel = new JPanel();
                JLabel label = new JLabel("Ingrese Contraseña:");
                JPasswordField pass = new JPasswordField(10);
                panel.add(label);
                panel.add(pass);
                String[] options = new String[]{"Ok", "Cancelar"};
                int option = JOptionPane.showOptionDialog(null, panel, "Configuración",
                        JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, options[1]);
                if (option == 0) // pressing OK button
                {
                    char[] password = pass.getPassword();
                    if (String.valueOf(password).equals("cual")) {
                        vista.frmConfiguracion vc = new vista.frmConfiguracion();
                        modelo.Configuracion mc = new modelo.Configuracion();
                        login.setEnabled(false);    
                        try {
                            controlador.Configuracion cc = new controlador.Configuracion(vc, mc, login);
                        } catch (IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
                    }   
                }   
                break;
        }
    }
}
