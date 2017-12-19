
package modelo;

public class Alumno{
    private String rut;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String fecha_nacimiento;
    private int curso;
    private String genero;
    private String direccion;
    private String comuna;
    private String nombre_apoderado;
    private String apellido_apoderado;
    private String telefono_apoderado;
    private String diagnostico;
    private int id_alumno;
    
    public Alumno() {
        this.rut = null;
        this.nombre = null;
        this.apellido_paterno= null;
        this.apellido_materno= null;
        this.fecha_nacimiento= null;
        this.curso= 0;
        this.genero= null;
        this.direccion= null;
        this.comuna= null;
        this.nombre_apoderado= null;
        this.apellido_apoderado= null;
        this.telefono_apoderado= null;
        this.diagnostico= null;
        this.id_alumno= 0;
    }
    public Alumno(String rut, String nombre) {
        this.rut = rut;
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
   

    public String getNombre() {
        return nombre;
    }
        
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getNombre_apoderado() {
        return nombre_apoderado;
    }

    public void setNombre_apoderado(String nombre_apoderado) {
        this.nombre_apoderado = nombre_apoderado;
    }

    public String getApellido_apoderado() {
        return apellido_apoderado;
    }

    public void setApellido_apoderado(String apellido_apoderado) {
        this.apellido_apoderado = apellido_apoderado;
    }

    public String getTelefono_apoderado() {
        return telefono_apoderado;
    }

    public void setTelefono_apoderado(String telefono_apoderado) {
        this.telefono_apoderado = telefono_apoderado;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }
   
    
}
