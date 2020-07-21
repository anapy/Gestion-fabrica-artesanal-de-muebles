/**
 * Contiene las características de la silla de escritorio sin ruedas.
 * 
 * @author Ana Belen
 * @version 1
 */
public class SillaEscritorioSRuedas extends SillaEscritorio
{
    /**
     * Constructor para objetos de la clase SillaEscritorioSRuedas por defecto     
     * La silla de escritorio sin ruedas tiene un precio y referencia fijados por este constructor
     * 
     */
    public SillaEscritorioSRuedas() {
        super(130);
        setModelo("Silla de escritorio sin ruedas");
        cambiarRef("REF7");
    }
}
