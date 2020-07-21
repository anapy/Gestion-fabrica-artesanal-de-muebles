
/**
 * Ficha contiene información relativa a una orden de fabricación para el departamento de producción
 * 
 * @Ana Belén
 * @version 1
 */
public class Ficha extends Tarea
{
    //Recoge el pedido al que pertenece la orden de fabricación
    private Pedido pedido;
    //Artesano que fabrica el mueble
    private Artesano artesano;
    //Incidencias de la ficha
    private Incidencia incidencia;
    /**
     * Constructor para objetos de la clase Ficha
     *
     */
    public Ficha(String ID, Pedido pedido)
    {
        super(ID);
        modificarMueble(pedido.getMueble());
        modificarCant(pedido.getCantidad());
        cambiarEstado(Estado.PDTASIGNACION);
        this.pedido = pedido;
        incidencia = Incidencia.NINGUNA;
    }
    
    /**
     * Imprime los datos de la ficha
     */
    public String toString(){
        StringBuilder ficha = new StringBuilder();
        ficha.append(super.toString());
        if(!incidencia.equals(Incidencia.NINGUNA)){
            ficha.append("\nTipo de incidencias " + incidencia.toString());
        }
        String info = ficha.toString(); 
        return info;
    }

    public void modificarArtesano(Artesano artesano) {
        this.artesano = artesano;
    }
    
    public void registrarIncidencia(Incidencia nIncidencia) {
        incidencia = nIncidencia;
    }
    
    public Incidencia getIncidencia() {
        return incidencia;
    }
    
    public Pedido getPedido() {
        return pedido;
    }
}
