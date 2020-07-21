import java.util.List;

/**
 * La interfaz DptoComercialI gestiona el menú comercial.
 * 
 * @author Ana Belén 
 * @version 1
 */

public interface DptoComercialI
{
    List<Pedido> consultarPedRegistrados();
    Pedido confirmarRecibidos(Comercial comercial, Pedido pedido);
    List<Pedido> mostrarPedEnCurso();
    List<Pedido> mostrarPedTerminados();
    String crearNotificacion(Pedido pedido);
    String mostrarCatalogo();
    Mueble buscarMuebleRef (String ref);
    void addPedido(Pedido pedido);
    Pedido buscarPedID(String ID);
    List<Pedido> buscarPedEstado(Estado estado);
    List<Pedido> buscarPedPorCliente(Cliente cliente);
    int getNoPedidos();
    String infoPedidos();
    void cambiarEstadoPed(Pedido pedido, Estado nEstado);
}
