
package modelo;

import java.util.List;


public interface Modelo {

    public List<Pais> getPaises();
    

    public Pais getPais(int id);
    

    public int addPais(Pais pais);
    

    public int updatePais(Pais pais);
    

    public int removePais(int id);
}
