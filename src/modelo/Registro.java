
package modelo;

public class Registro {

    private int id_registro;
    private int id_alumno;
    private int id_profesional;

    private String tipo_actividad;
    private String apoderado;
    private String profesional;

    private String fecha;
    private String objetivo;
    private String resultado;
    private String acuerdo;

    public Registro() {

    }

    public Registro(int id_registro, int id_alumno, int id_profesional, String tipo_actividad, String apoderado, String profesional, String fecha, String objetivo, String resultado, String acuerdo) {
        this.id_registro = id_registro;
        this.id_alumno = id_alumno;
        this.id_profesional = id_profesional;
        this.fecha = fecha;
        this.tipo_actividad = tipo_actividad;
        this.apoderado = apoderado;
        this.profesional = profesional;
        this.objetivo = objetivo;
        this.resultado = resultado;
        this.acuerdo = acuerdo;
    }

    public int getId_registro() {
        return id_registro;
    }

    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public int getId_profesional() {
        return id_profesional;
    }

    public void setId_profesional(int id_profesional) {
        this.id_profesional = id_profesional;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo_actividad() {
        return tipo_actividad;
    }

    public void setTipo_actividad(String tipo_actividad) {
        this.tipo_actividad = tipo_actividad;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
    }

    public String getApoderado() {
        return apoderado;
    }

    public void setApoderado(String apoderado) {
        this.apoderado = apoderado;
    }

    public String getProfesional() {
        return profesional;
    }

    public void setProfesional(String profesional) {
        this.profesional = profesional;
    }
}
