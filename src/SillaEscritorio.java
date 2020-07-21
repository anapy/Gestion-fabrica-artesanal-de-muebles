/**
 * Contiene las características de la silla de escritorio.
 * 
 * @author Ana Belen
 * @version 1
 * 
 */
public abstract class SillaEscritorio extends Silla
{
    /**
     * Constructor para objetos de la clase SillaPlegable por defecto
     * La silla plegable tiene un precio, tiempo de fabricación y material fijado por este constructor
     */
    public SillaEscritorio(int precio)
    {
        super(precio, Material.METAL);
        setModelo("Silla de escritorio");
    }
    /**
     * Sustituye el método de registrar piezas de Mueble
     */
    @Override
    public void registrarPiezas() {
        piezas.put(Pieza.ASIENTOESCRITORIO, 1);
        piezas.put(Pieza.PATASESCRITORIO, 1);
        piezas.put(Pieza.RESPALDOESCRITORIO, 1);
        piezas.put(Pieza.TORNILLO, 20);
    }
}
