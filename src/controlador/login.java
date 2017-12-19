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
public class login implements ActionListener {

    vista.Login login;
    modelo.BDMLogin bdmlogin;
    modelo.Hash Hash;

    public login(vista.Login vista) {
        this.login = vista;

        this.login.btnLogin.addActionListener(this);
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
              
                if (bdmlogin.bUsuario(login.txtUsuario.getText(), login.txtPassword.getPassword())) {
                    vista.FrmMain v0 = new vista.FrmMain();
                    controlador.Main c0 = new controlador.Main(v0);
                    v0.setVisible(true);
                    this.login.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró usuario", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                
                
                break;
        }
    }

}
