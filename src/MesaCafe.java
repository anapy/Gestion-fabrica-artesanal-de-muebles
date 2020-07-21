
/**
 * Contiene todos los objetos mesa disponibles y el constructor común.
 * 
 * @author Ana Belen
 * @version 1
 * 
 */
public abstract class MesaCafe extends Mesa
{
    /**
     * Constructor para objetos de la clase MesaCafe
     */
    public MesaCafe(int precio)
    {
        super(precio, Material.MADERA);
    }
    /**
     * Sustituye el método de registrar piezas de Mueble
     */
    @Override
    public void registrarPiezas() {
        piezas.put(Pieza.TABLON90, 1);
        piezas.put(Pieza.PATABAJA, 4);
        piezas.put(Pieza.TORNILLO, 20);
    }
}
