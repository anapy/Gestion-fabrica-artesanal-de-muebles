import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Tarea es la superclase de Ficha y Pedido puesto que comparten métodos muy similares
 * 
 * @author Ana Belén
 * @version 1
 */
public class Tarea
{
    //Estados posibles del la tarea: 
    private Estado estado;
    //Identificador
    private String ID;
    //Fecha de creacion
    private Date fCreacion;
    //Mueble a fabricar
    private Mueble mueble;
    //Cantidad de artículos a fabricar del mismo modelo
    private int cant;
    /**
     * Constructor for objects of class Tarea
     */
    public Tarea(String ID)
    {
        this.ID = ID;
        this.estado = Estado.REGISTRADO;
        this.fCreacion = new Date();        
    }

    /**
     * Este método modifica el estado de la tarea al estado nuevo
     * Estados disponibles: REGISTRADO, CONFIRMADO, PDTCLIENTE, PDTASIGNACION, ENFABRICACION, ENPAUSA, TERMINADO, ENTREGADO
     */
    public void cambiarEstado(Estado eNuevo) {
        this.estado = eNuevo;
    }
    
    /**
     * Devuelve el estado del pedido
     */
    public Estado getEstado() {
        return estado;
    }
    
    /**
     * Devuelve el ID de la tarea
     */
    public String getID() {
        return ID;
    }
    
    /**
     * Devuelve el mueble 
     */
    public Mueble getMueble() {
        return mueble;
    }
    
    public void modificarMueble(Mueble nMueble){
        this.mueble = nMueble;
    }
    
    public void modificarCant(int nCant){
        this.cant = nCant;
    }
    
    /**
     * Devuelve la cantidad 
     */
    public int getCantidad() {
        return cant;
    }
    
    /**
     * Devuelve la fecha en la que se ha creado el pedido con los detalles: hora, día, mes y año
     */
    public String getfCreacion() {
        return fCreacion.toString();
    }
    
    /**
     * Imprime los datos de la tarea
     */
    public String toString(){
       StringBuilder t = new StringBuilder();
       t.append("Identificador: "+ ID + "\n");
       t.append("Estado: " + estado + "\n");
       t.append(getCantidad() + " x ");
       t.append(getMueble().getModelo());
       String info = t.toString(); 
       return info; 
    }
}

