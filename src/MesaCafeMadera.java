/**
 * Contiene las características de la mesa de cafe de madera
 * 
 * @author Ana Belen
 * @version 1
 */
public class MesaCafeMadera extends MesaCafe
{    
    /**
     * Constructor para objetos de la clase MesaCafeMadera por defecto
     * La mesa de café de madera tiene un precio y referencia fijados por este constructor
     */
    public MesaCafeMadera() {
        super(180);
        cambiarRef("REF1");
        setModelo("Mesa de café de madera");
    }
}
