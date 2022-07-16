package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;




public class ModeloHC implements Modelo {

    private List<Pais> paisesGuardados;

    public ModeloHC() {
        this.paisesGuardados = new ArrayList<>();
        crearpaisesFake();
    }

    @Override
    public List<Pais> getPaises() {
        return new ArrayList(this.paisesGuardados);
    }

    @Override
    public Pais getPais(int id) {
        int i = 0;
        Pais encontrado = null;
        while (i < this.paisesGuardados.size() && encontrado == null) {
            Pais a = this.paisesGuardados.get(i);
            if (a.getId() == id) {
                encontrado = a;
            } else {
                i++;
            }
        }
        if (encontrado == null) {
            throw new RuntimeException("No se encontró pais con ID " + id);
        }
        return encontrado;
    }

    @Override
    public int addPais(Pais pais) {
        this.paisesGuardados.add(pais);
        return 0;
    }

    @Override
    public int updatePais(Pais a) {
        Pais target = getPais(a.getId());
        int idx = this.paisesGuardados.indexOf(target);
        this.paisesGuardados.set(idx, a);
        return 0;
    }

    @Override
    public int removePais(int id) {
        Pais target = getPais(id);
        this.paisesGuardados.remove(target);
        return 0;
    }

    private void crearpaisesFake() {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("banderas.properties")) {
            Properties props = new Properties();
            props.load(is);
            //this.paisesGuardados.add(new Pais(1, 10000, 2000, "China", "1999-06-22", ""));
            //this.paisesGuardados.add(new Pais(2, 500000, 40000, "Japon", "1991-02-28", ""));
        } catch (IOException ex) {
            throw new RuntimeException("No se pudieron cargar las  banderas");
        }
    }
}
