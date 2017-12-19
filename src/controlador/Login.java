/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario 2
 */
public class Login implements ActionListener {

    vista.Login login;
    modelo.BDMLogin bdmlogin;
    modelo.Hash Hash;

    public Login(vista.Login vista) {
        this.login = vista;

        this.login.btnLogin.addActionListener(this);
        this.login.jmnuconf.addActionListener(this);
        bdmlogin = new modelo.BDMLogin();
        try {
            modelo.BD.conectar();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(Hash.sha1("profesor"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       String cmd = e.getActionCommand();
        switch (cmd) {
            case "CMD_ENTER":
                String tipo_usuario=bdmlogin.bUsuario(login.txtUsuario.getText(), login.txtPassword.getPassword());
                if (tipo_usuario!=null) {
                    vista.FrmMain v0 = new vista.FrmMain();
                    controlador.Main c0 = new controlador.Main(v0,tipo_usuario);
                    v0.setVisible(true);
                    this.login.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }

                break;
            case "CMD_CONF":
                 vista.frmConfiguracion vc = new vista.frmConfiguracion();
                modelo.Configuracion mc = new modelo.Configuracion();
                 
                    try {
                        controlador.Configuracion cc = new controlador.Configuracion(vc, mc, login);
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 
                break;
        }
    }

}
