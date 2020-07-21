import java.util.List;
/**
 * La interfaz DptoProduccionI contiene los métodos para gestionar las opciones de los jefes y artesanos.
 * 
 * @author Ana Belén 
 * @version 1
 */

public interface DptoProduccionI
{
    String verFichasPendientes();
    void asignarArtesano(Persona persona, Ficha ficha);
    Ficha crearFicha(Pedido pedido);
    List<Ficha> buscarFichaEstado(Estado estado);
    Ficha buscarFichaID(String ID);
    String seleccionarFicha(String ID);
    Ficha pausarFicha(String ID);
    void pasarPruebasFicha(String ID);
    void terminarFicha(String ID);
    String mostrarPiezas(Ficha ficha);
    void registrarIncidencia(Ficha ficha, String incidencia);
    void informarFaltaMaterial(Pieza pieza, int cantidad);
    Pieza buscarPieza(String nombre);
    String listarMateriales();
    void pedirMateriales();
    String getStock();
    void reactivarFicha(String ID);
    List<Ficha> getListaFichas();
    String actualizarTrabajoArtesano(Ficha ficha);
}
