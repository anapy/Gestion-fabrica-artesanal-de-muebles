
/**
 * Es la clase para mesa de dormitorio.
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MesaDormitorio extends Mesa
{
    /**
     * Constructor para objetos de la clase MesaDormitorio
     * La mesa de de dormitorio tiene un precio y material fijado por este constructor.
     */
    public MesaDormitorio()
    {
        super(150, Material.MADERA);
        setModelo("Mesa de dormitorio");
        cambiarRef("REF3");
    }

    @Override
    public void registrarPiezas() {
        piezas.put(Pieza.TABLON40, 1);
        piezas.put(Pieza.PATAMESITA, 4);
        piezas.put(Pieza.TORNILLO, 20);
    }
    
}
