/**
 * Contiene todos los objetos silla disponibles y el constructor común.
 * 
 * @author Ana Belen
 * @version 1
 */
public abstract class Silla extends Mueble
{
    /**
     * Constructor para objetos de la clase Silla
     * Dependiendo de los tipos de silla el material será madera o metal.
     */
    public Silla(int precio, Material material)
    {
        super(precio, material);
        setModelo("Silla");
    }
}
