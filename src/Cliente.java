import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Cliente engloba los campos y métodos comunes de los clientes tanto particulares como empresa
 * 
 * @author Ana Belen
 * @version 1
 */
abstract public class Cliente extends Persona
{
    //Contiene la dirección del cliente
    private String direccion;
    //Contiene un listado de todos los pedidos realizados por el cliente
    private ArrayList <Pedido> pedidos;
    //Guarda el teléfono de contacto del cliente
    private int telefono;
    //Guarda el email del cliente
    private String email;
    //Notificaciones 
    private ArrayList<String> notificaciones;
    /**
     * Constructor para objetos de la clase Cliente
     */
    public Cliente(String n, String apell, int ID, String direccion, int tel, String email) {
        super(n, apell, ID);
        this.direccion = direccion;
        this.direccion = direccion;
        this.telefono = tel;
        this.email = email;
        user = "C" + ID;
        pedidos = new ArrayList<>();
        notificaciones = new ArrayList<>();
    }
    
    public String toString() {
        StringBuilder p = new StringBuilder();
        p.append(super.toString());
        p.append("Direccion: " + direccion + "\n");
        p.append("Teléfono: " + telefono + "\n");
        p.append("Email: " + email + "\n");
        p.append("Número de pedidos: " + pedidos.size() + "\n");
        return p.toString();
    }
    
    /**
     * Devuelve la dirección
     */
    public String getDireccion() {
        return direccion;
    }
    
    /**
     * Permite cambiar la dirección
     */
    public void modificarDireccion(String nDireccion) {
        this.direccion = nDireccion;
    }
    
    /**
     * Devuelve la dirección
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Permite cambiar la dirección
     */
    public void modificarEmail(String nEmail) {
        this.email = nEmail;
    }
    
    /**
     * Añade un nuevo pedido
     */
    public void addPedidos(Pedido pedido) {
        pedidos.add(pedido);
    }
    
    /**
     * Devuelve información de todos los pedidos
     */
    public void infoPedidos() {
        for(Pedido pedido : pedidos) {
            System.out.println(pedido.toString());
        }        
    }
    
    public List<String> getNotificaciones() {
        return notificaciones;
    }

    public void addNotificacion(String notificacion) {
        notificaciones.add(notificacion);
    }
}
