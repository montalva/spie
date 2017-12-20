
package modelo;

import java.util.ArrayList;

public class BDProfesional {

    public ArrayList<Profesional> lista;

    public BDProfesional() {
        lista = new ArrayList<>();
    }

    public boolean guardar(Profesional ml) {
        return lista.add(ml);

    }

    public boolean eliminar(Profesional ml) {
        return lista.remove(ml);

    }

    public Profesional buscar(String rut) {
        Profesional mod = new Profesional();
        mod.setRut(rut);
        int idx = this.lista.indexOf(mod);
        if (idx == -1) {
            return null;
        } else {
            Profesional res = this.lista.get(idx);
            return res;
        }
    }
    public boolean modificar(String rut, Profesional Al) {
        Profesional mod = new Profesional();
        mod.setRut(rut);
        int idx = this.lista.indexOf(mod);
        if (idx == -1) {
            return false;
        } else {
            Profesional res = this.lista.get(idx);
            res.setRut(Al.getRut());
            res.setNombre(Al.getNombre());
            return true;
        }
    }
}
