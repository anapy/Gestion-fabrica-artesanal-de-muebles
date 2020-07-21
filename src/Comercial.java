import java.util.ArrayList;
import java.util.List;
/**
 * Comercial amplía la clase empleado en email, cliente y lista de pedidos
 * 
 * @author Ana Belén
 * @version 1
 */
public class Comercial extends Empleado
{
    // Telefono de contacto del comercial
    private int telefono;
    //Email de contacto del cliente
    private String email;
    //Lista de pedidos
    private ArrayList<Pedido> pedidos;
    /**
     * Constructor for objects of class Comercial
     */
    public Comercial(String n, String apell, int ID, int tel, String email, int horas) {
        super(n, apell, ID, horas);
        this.telefono = tel;
        this.email = email;
        setUser("EC" + ID);
        modificarCategoria(Categoria.COMERCIAL);
        pedidos = new ArrayList<Pedido>();
    }

    /**
     * Imprime todos los datos del empleado ampliando el constructor de empleado
     */
    public String toString() {
        StringBuilder c = new StringBuilder();
        c.append(super.toString());
        c.append("Teléfono: " + telefono + "\n");
        c.append("Email: " + email + "\n");
        c.append("Nº de pedidos: " + pedidos.size() + "\n");
        return c.toString();
    }
    
    /**
     * Añadir un nuevo pedido
     */
    public void addPedido(Pedido pedido) {
        pedidos.add(pedido);
    }
    
    /**
     * Devuelve las fichas manejadas por el artesano
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
