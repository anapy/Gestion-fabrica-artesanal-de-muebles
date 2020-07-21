/**
 * Es la clase para mesa de café de cristal. 
 * @author Ana Belen
 * @version 1
 */
public class MesaCafeCristal extends MesaCafe
{
    /**
     * Constructor para objetos de la clase MesaCafeCristal
     * La mesa de café de cristal tiene un precio, referencia y material2 fijados por este constructor.
     * Como detalle tendrá un 2º material que será el cristal que irá por encima de la madera.
     */
    public MesaCafeCristal()
    {
        super(210);
        cambiarMaterial2(Material.CRISTAL);
        setModelo("Mesa de café de cristal");
        cambiarRef("REF2");

    }    
    public void registrarPiezas() {
        super.registrarPiezas(); 
        piezas.put(Pieza.CRISTAL, 1);
    }    
    /**
     * Este método toString() sustituye al método principal para mantener el orden solo incluyendo el 2º material
     */
    @Override
    public String toString() {
        StringBuilder m = new StringBuilder();
        m.append(getRef() + " -> ");  
        m.append(getModelo() + " -> ");
        m.append(getMaterial() + " -> ");
        m.append(getMaterial2() + " -> ");
        m.append(getPrecio() + "€" + " -> ");
        m.append(getColor());
        return m.toString();
    }
}
