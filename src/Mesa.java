
/**
 * Es la clase padre para todos los objetos mesa disponibles y crea el constructor común.
 * Extiende la clase padre Mueble que a su vez es la subclase de la que deriva Mesa
 * @author Ana Belen
 * @version 1
 */
public abstract class Mesa extends Mueble
{
    /**
     * Constructor de objetos de la clase Mesa
     * Dependiendo de los tipos de mesa el material de la mesa será madera o madera y cristal.
     * 
     */
    public Mesa(int precio,  Material material)
    {
        super(precio, material);        
    }
}
