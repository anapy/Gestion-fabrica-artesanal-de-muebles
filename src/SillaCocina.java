/**
 * Contiene las características de la silla de cocina.
 * 
 * @author Ana Belen
 * @version 1
 * 
 */
public class SillaCocina extends Silla
{    
    /**
     * Constructor para objetos de la clase SillaCocina
     * La silla de cocina tiene un precio y material fijado por este constructor
     */
    public SillaCocina()
    {
        super(120, Material.MADERA);
        setModelo("Silla de cocina");
        cambiarRef("REF5");
    }    
    /**
     * Sustituye el método de registrar piezas de Mueble
     */
    @Override
    public void registrarPiezas() {
        piezas.put(Pieza.ASIENTOCOCINA, 1);
        piezas.put(Pieza.PATACOCINA, 1);
        piezas.put(Pieza.RESPALDOCOCINA, 1);
        piezas.put(Pieza.TORNILLO, 20);
    }
}
