/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author monta
 */
public class ArchivoConf {
    private String ip;
    private int puerto;
    private String bd;
    private String usuario;
    private String Password;

    public ArchivoConf(String ip, int puerto, String bd, String usuario, String Password) {
        this.ip = ip;
        this.puerto = puerto;
        this.bd = bd;
        this.usuario = usuario;
        this.Password = Password;
    }

    public ArchivoConf() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getBd() {
        return bd;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
