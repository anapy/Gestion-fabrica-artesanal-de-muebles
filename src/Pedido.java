import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
* La clase Pedido guarda toda la infomación de los pedidos realizados en la fábrica.
* 
* @author Ana Belén
* @version 1
*/
   
public class Pedido extends Tarea {
    //Cliente que hace el pedido
    private Cliente cliente;
    //Comercial que recibe el pedido
    private Comercial comercial;
    //Precio total pedido
    private int precio;
    //Ficha de fabricación asociada al pedido
    private Ficha ficha;
    //Color de los muebles
    private Color peticionColor;
    //Importe del cambio de color
    private int extras;
    /**
     * Constructor para objetos de la clase Pedido
     * @ID Identificador del pedido será el nº de pedidos hechos por el cliente más el nombre del cliente separados por una C
     * estado los estados del pedido posibles solo serán: 
     */
    public Pedido(String ID, Cliente cliente, Mueble mueble, int cant) {
        super(ID);
        this.cliente = cliente;
        cambiarEstado(Estado.REGISTRADO);
        modificarMueble(mueble);
        modificarCant(cant);
        precio = mueble.getPrecio() * getCantidad();
        peticionColor = getMueble().getColor();
        ficha = null;
        extras = 0;
    }
    
    /**
     * Actualiza el precio dependiendo del color
     */
    public void actualizarExtras(){
        switch(peticionColor){
            case MARRON:
                extras = 50;
                break;
            case AZUL:
                extras = 30;
                break;   
            case BLANCO:    
                extras = 20;
                break;     
        }
    }
    
    /**
     * Devuelve un String de los datos del pedido sin el precio
     */
    public String toStringSinPrecio() {
        StringBuilder ped = new StringBuilder();
        ped.append(super.toString());
        ped.append("\nColor: " + getPeticionColor() + "\n");
        ped.append("Precio total por confirmar");

        return ped.toString();
    }
    
    /**
     * Devuelve un String con los datos del pedido
     */
    public String toString(){
        StringBuilder ped = new StringBuilder();
        ped.append(super.toString());
        ped.append("\nColor: " + getPeticionColor() + "\n");
        ped.append("Total: " + precio + "€ + " + extras + "€(extras) = " + (precio + extras) + "€");         
        String info = ped.toString(); 
        return info;
    }
    
    /**
     * Devuelve un String con los datos del pedido y de sus fichas
     */
    public String toStringFicha(){
        StringBuilder ped = new StringBuilder();    
        ped.append(toString());
        if(ficha != null){
            ped.append(ficha.getID() + " -> " + ficha.getEstado() + " -> Incidencias:" + ficha.getIncidencia());
        }
        String info = ped.toString(); 
        return info;
    }
    
    public Cliente getCliente(){
        return cliente;
    }
    
    public void actPrecio() {
        precio = getMueble().getPrecio() * getCantidad();
    }
    
    public int getPrecio(){
        return precio;
    }    
    
    public int getExtras(){
        return extras;
    }
    
    public void definirComercial(Comercial comercial) {
        this.comercial = comercial;
    }
    
    public Comercial getComercial() {
        return comercial;
    }
    
    public Ficha getFicha() {
        return ficha;
    }
    
    public void asignarFicha(Ficha ficha) {
        this.ficha = ficha;
    }
    
    /**
     * Devuelve las peticiones especiales del pedido
     */
    public Color getPeticionColor() {
        return peticionColor;
    }
    
    /**
     * Cambiar las peticiones especiales del pedido
     */
    public void addPeticion(String nPeticion) {
        peticionColor = peticionColor.valueOf(nPeticion);
    }
}
   
