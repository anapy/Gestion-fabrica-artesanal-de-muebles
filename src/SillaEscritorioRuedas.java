/**
 * Contiene las características de la silla de escritorio con ruedas 
 * 
 * @author Ana Belen
 * @version 1
 */
public class SillaEscritorioRuedas extends SillaEscritorio
{    
    /** 
     * Constructor para objetos de la clase SillaEscritorioRuedas
     * La silla de escritorio con ruedas tiene un precio y referencia fijados por este constructor
     * 
     */
    public SillaEscritorioRuedas()
    {
        super(150); 
        setModelo("Silla de escritorio con ruedas");
        cambiarRef("REF6");
    }    
    @Override
    public void registrarPiezas() {
        super.registrarPiezas();
        piezas.put(Pieza.RUEDA, 8);
    }
}
