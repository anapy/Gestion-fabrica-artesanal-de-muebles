import java.util.ArrayList;
import java.util.List;

/**
 * La clase Artesano será la clase padre para los dos subdivisiones: Plantilla y Por horas
 * 
 * @author Ana Belén
 * @version 1
 */
public abstract class Artesano extends Empleado
{    
    //Lista de fichas
    private ArrayList<Ficha> fichas;
    //Historial de trabajo
    private ArrayList<String> historialTrabajo;
    /**
    * Constructor for objects of class Artesano
    */
    public Artesano(String n, String apell, int ID, int horas)
    {
        super(n, apell, ID, horas);
        modificarCategoria(Categoria.ARTESANO);
        setUser("EA" + ID);
        fichas = new ArrayList<>();
        historialTrabajo = new ArrayList<>();
    }
    
    /**
     * Imprime todos los datos del empleado ampliando las características de empleado
     */
    public String toString() {
        StringBuilder a = new StringBuilder();
        a.append(super.toString());
        a.append("Nº de fichas: " + fichas.size() + "\n");
        return a.toString();
    }
     
    /**
     * Añadir una nueva ficha
     */
    public void addFicha(Ficha ficha) {
        fichas.add(ficha);
    }
    
    /**
     * Devuelve las fichas manejadas por el artesano
     */
    public List<Ficha> getFichas() {
        return fichas;
    }
    
    public List<String> getHistorialTrabajo() {
        return historialTrabajo;
    }

    public void addHistorialTrabajo(String nHistorialTrabajo) {
        historialTrabajo.add(nHistorialTrabajo);
    }
    
}
