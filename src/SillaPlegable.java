/**
 * Contiene las características de la silla plegable.
 * 
 * @author Ana Belen
 * @version 1
 * 
 */
public class SillaPlegable extends Silla
{    
    /**
     * Constructor para objetos de la clase SillaPlegable por defecto
     * La silla plegable tiene un precio y referencia fijados por este constructor
     */
    public SillaPlegable()
    {
        super(95, Material.MADERA);
        setModelo("Silla plegable");
        cambiarRef("REF8");
    }    
    @Override
    public void registrarPiezas() {
        piezas.put(Pieza.ASIENTOPLEGABLE, 1);
        piezas.put(Pieza.PATASPLEGABLE, 1);
        piezas.put(Pieza.RESPALDOPLEGABLE, 1);
        piezas.put(Pieza.TORNILLO, 20);
    }
}
