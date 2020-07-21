
/**
 * Clase jefe es la clase para las instancias de tipo deje
 * 
 * @author Ana Belén 
 * @version 1
 */
public class Jefe extends Empleado
{
    /**
     * Constructor para objetos de la clase Jefe
     */
    public Jefe(String n, String apell, int ID, int horas)
    {
        super(n, apell, ID, 8);
        modificarCategoria(Categoria.JEFE);
        setUser("EJ" + ID);

    }
}
