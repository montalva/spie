
package modelo;


public class Profesional {
    private String rut;
    private String nombre;
    public Profesional() {
        this.rut = null;
        this.nombre = null;
    }
    public Profesional(String rut, String nombre) {
        this.rut = rut;
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        @Override
    public boolean equals(Object o){
        if(((Alumno)o).getRut().equals(this.getRut())){ 
            return true;
        }else{
            return false;
        }
    }
    
}
