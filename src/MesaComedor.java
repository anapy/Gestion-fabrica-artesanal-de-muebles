
/**
 * Es la clase para mesa de comedor.
 * 
 * @author Ana Belén
 * @version 1
 */
public class MesaComedor extends Mesa
{
    /**
     * Constructor para objetos de la clase MesaComedor
     * La mesa de café de comedor tiene un precio y referencia fijados por este constructor
     */
    public MesaComedor()
    {
        super(300, Material.MADERA);
        setModelo("Mesa de comedor");
        cambiarRef("REF4");

    }
    
    /**
     * Sustituye el método de registrar piezas de Mueble
     */
    @Override
    public void registrarPiezas() {
        piezas.put(Pieza.TABLON120, 1);
        piezas.put(Pieza.PATAALTA, 4);
        piezas.put(Pieza.TORNILLO, 20);
    }  
}
