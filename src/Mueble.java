import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.List;
/**
 * Mueble es la clase padre para todos los muebles disponibles.
 * 
 * @author Ana Belen
 * @version 1
 */

public abstract class Mueble
{
    //Nombre del mueble
    private String modelo;
    //Precio del mueble
    private int precio;
    //Tiempo de fabricación
    private Material material;
    // Material secundario
    private Material material2;
    //Referencia para manejar el pedido de los muebles
    private String ref;
    //Piezas contiene las piezas necesarias para fabricar cada mueble
    protected HashMap <Pieza, Integer> piezas;
    //Color del mueble
    private Color color;    
    /**
     * Constructor para objetos de la clase Mueble
     */
    public Mueble(int precio, Material material) {   
        this.precio = precio;        
        this.material = material;
        material2 = null;
        ref = "Ref";
        piezas = new HashMap<>();
        registrarPiezas();
        color = Color.NEGRO;
    }    
    
    /**
     * Añade al ArrayList las piezas necesarias para crear cada mueble
     */
    abstract public void registrarPiezas();
    
    /**
     * Devuelve un int con el nº de piezas de cada mueble
     */
    private int getNºPiezas(Pieza pieza) {
        return piezas.get(pieza);
    }   
    
    /**
     * Devuelve el listado de piezas que componen el mueble
     */
    public List<Pieza> getPiezas() {
        List<Pieza> lista = new ArrayList<>();
        for(Pieza pieza: piezas.keySet()) {
            lista.add(pieza);
        }
        return lista;
    }  
    
    /**
     * Devuelve un String con el nombre del modelo de mueble
     */
    public String getModelo() {
        return modelo;
    } 
    
    /**
     * Cambia el nombre del modelo del mueble
     * @String nombre del modelo del mueble
     */
    public void setModelo(String nModelo) {
        modelo = nModelo;
    }  
    
    /**
     * Cambiar precio del mueble
     */
    private void cambiarPrecio(int nuevoPrecio) {
        precio = nuevoPrecio;
    }   
    
    /**
     * Mostrar precio del mueble
     */
    public int getPrecio() {
        return precio;
    }  
    
    /**
     * Mostrar el material del mueble
     */
    public Material getMaterial() {
        return material;
    }   
    
    /**
     * Cambiar el material del que está hecho el mueble
     */
    private void cambiarMaterial(Material nMaterial) {
        material = nMaterial;
    }
    
    /**
     * Mostrar el material del mueble
     */
    public Material getMaterial2() {
        return material2;
    }
    
    /**
     * Cambiar el material del que está hecho el mueble
     */
    public void cambiarMaterial2(Material nMaterial2) {
        material2 = nMaterial2;
    }
    
    /**
     * Mostrar la referencia del mueble
     */
    public String getRef() {
        return ref;
    }
    
    /**
     * Cambiar la referencia del mueble
     */
    public void cambiarRef(String nRef) {
        ref = nRef;
    }
    
    /**
     * Cambiar el color del mueble
     */
    public void cambiarColor(Color nColor) {
        this.color = nColor;
    }

    /**
     * Devuelve el color del mueble
     */
    public Color getColor() {
        return color;
    }
    
    public String toString() {
        StringBuilder m = new StringBuilder();
        m.append(ref + " -> ");  
        m.append(modelo + " -> ");
        m.append(material + " -> ");
        m.append(precio + "€");
        m.append(" -> " + color);
        return m.toString();
    }
    
    /**
     * Devuelve un String con la composición en piezas del mueble
     */
    public String piezasToString() {
        StringBuilder materiales = new StringBuilder();
        for(Pieza pieza: piezas.keySet()){
            materiales.append(pieza.toString() + " x " + piezas.get(pieza) + "\n");
        }
        return materiales.toString();
    }
}
