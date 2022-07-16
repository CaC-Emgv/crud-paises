package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Pais {

    private int id;
    private String nombre;
    private int poblacion;
    private int superficie;
    private LocalDate fechaIndependencia;
    private String bandera;
    
    public Pais(){
        setId(0);
    }


    public Pais(int id, String nombre, int poblacion, int superficie, String fechaIndependencia, String bandera) {
        setId(id);
        setPoblacion(poblacion);
        setSuperficie(superficie);
        setNombre(nombre);
        setfechaIndependencia(fechaIndependencia);
        setBandera(bandera);
    }


    public void setId(int id) {
        if (id < 0) {
            throw new RuntimeException("Valor para ID inconsistente");
        }
        this.id = id;
    }

   public void setPoblacion(int poblacion){
       
       if (poblacion == 0){
       
       throw new RuntimeException("No puede haber país con menos de 1 habitante");
   }
       this.poblacion = poblacion;
   }
    
      public void setSuperficie(int superficie){
       
       if (superficie == 0){
       
       throw new RuntimeException("No puede haber país con 0km2 de superficie");
   }
       this.superficie = superficie;
   }
      
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new RuntimeException("No se ha provisto un nombre");
        }
        this.nombre = nombre.trim();
    }

    public void setfechaIndependencia(String fechaIndependencia) {
        if (fechaIndependencia == null || fechaIndependencia.trim().isEmpty()) {
            throw new RuntimeException("No se ha provisto una fecha de independencia");
        }
        try {
            LocalDate posibleFecha = LocalDate.parse(fechaIndependencia.trim());
            if (posibleFecha.isAfter(LocalDate.now())) {
                throw new RuntimeException("La fecha de independencia provista es posterior al día de hoy");
            }
            this.fechaIndependencia = posibleFecha;
        } catch (DateTimeParseException ex) {
            throw new RuntimeException("La fecha de independencia provista no es válida", ex);
        }
    }

    public void setBandera(String foto) {
        if (bandera == null || bandera.trim().isEmpty()) {
            bandera = "assets/fictional-flag.png";
        }
        if (!bandera.contains("no-flag") || this.bandera == null || this.bandera.contains("no-face")) {
            this.bandera = bandera.trim();
        }
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaIndependencia() {
        return fechaIndependencia.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public int getEdad() {
        return Period.between(fechaIndependencia, LocalDate.now()).getYears();
    }

    public String getBandera() {
        return bandera;
    }
    
    public int getPoblacion(){
        return poblacion;
    }
   
    public int getSuperficie(){
        return superficie;
    }

    @Override
    public String toString() {
        return "Pais{" + "id=" + id + ", nombre=" + nombre + ", poblacion=" + poblacion + ", superficie=" + superficie + ", fechaIndependencia" + fechaIndependencia + ", bandera=" + bandera + '}';
    }

}