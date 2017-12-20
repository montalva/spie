package controlador;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;

public class Acerca implements WindowListener{
    vista.Acerca acercade;
    JFrame callWindow;
    public Acerca(vista.Acerca acercade, JFrame callWindow) {
        this.acercade = acercade;
        this.callWindow = callWindow;
        this.acercade.setVisible(true);
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
