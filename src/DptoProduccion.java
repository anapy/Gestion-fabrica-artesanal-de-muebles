import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Date;
/**
 * La clase DptoProduccion implementa a DptoProduccionI y desarrolla los métodos para gestionar las opciones de los jefes y artesanos.
 * 
 * @author Ana Belén 
 * @version 1
 */ 
public class DptoProduccion implements DptoProduccionI
{ 
    //La lista de fichas recoge las tareas del departamento
    private ArrayList<Ficha> listaFichas;
    //Lista de materiales por pedir
    private HashMap<Pieza, Integer> listaMateriales;
    //Recoge los últimos materiales pedidos
    private String stock;
    /**
     * Constructor para objetos de la clase DptoProduccion
     */
    public DptoProduccion() 
    {
        listaFichas = new ArrayList<Ficha>();
        listaMateriales = new HashMap<Pieza, Integer>();
    }
    
    /**
     * Ver fichas pendientes 
     * Devuelve como String las fichas en estado PDTASIGNACION
     */
    public String verFichasPendientes(){ 
        //Se imprimen los pedidos que se encuentran en estado REGISTRADO
        List<Ficha> fichaRecibido = buscarFichaEstado(Estado.PDTASIGNACION);
        StringBuilder fichas = new StringBuilder();
        for(Ficha e : fichaRecibido){                
            fichas.append(e.toString() + "\n");
        }
        return fichas.toString(); 
    }
    
    /**
     * Asignar una ficha a un artesano
     */ 
    public void asignarArtesano(Persona persona, Ficha ficha) {
        ficha.modificarArtesano((Artesano)persona);
        ficha.cambiarEstado(Estado.ENCOLA);
        actArtesanoFichas((Artesano)persona, ficha);
    }
    
    /**
     * Seleccionar nueva ficha de trabajo
     */
    public String seleccionarFicha(String ID) { 
        buscarFichaID(ID).cambiarEstado(Estado.ENFABRICACION);
        return buscarFichaID(ID).toString();
    }
    
    /**
     * Buscar ficha y cambiar estado a ENPAUSA
     * @ID ID de la ficha
     */
    public Ficha pausarFicha(String ID) { 
        Ficha ficha = buscarFichaID(ID);
        ficha.cambiarEstado(Estado.ENPAUSA);
        return ficha;
    }
    
    /**
     * Buscar ficha y cambiar estado a ENFABRICACIÓN
     * @ID ID de la ficha
     */
    public void reactivarFicha(String ID) {
        buscarFichaID(ID).cambiarEstado(Estado.ENFABRICACION);
    }    
    
    /**
     * Buscar ficha y cambiar estado a ENPRUEBAS
     * @ID ID de la ficha
     */
    public void pasarPruebasFicha(String ID) {
        buscarFichaID(ID).cambiarEstado(Estado.ENPRUEBAS);
    }
    
    /**
     * Buscar ficha y cambiar estado a TERMINADO
     * @ID ID de la ficha
     */
    public void terminarFicha(String ID) {
        buscarFichaID(ID).cambiarEstado(Estado.TERMINADO);
    }
    
    /**
     * Crea un nuevo hito en el historial del artesano
     */
    public String actualizarTrabajoArtesano(Ficha ficha) { 
        Date fecha  = new Date();
        StringBuilder historial = new StringBuilder();
        historial.append(fecha.toString() + "\n");
        historial.append("La ficha nº " + ficha.getID());
        switch(ficha.getEstado()){
            case ENCOLA:
                historial.append(" entró en cola\n");
                historial.append(ficha.toString() + "\n");
                historial.append("----------------\n");
                break;
            case ENFABRICACION:
                historial.append(" pasó a fabricación\n");
                historial.append(ficha.toString() + "\n");
                historial.append("----------------\n");                
                break;
            case ENPAUSA: 
                historial.append(" se pausó\n");
                historial.append(ficha.toString() + "\n");
                historial.append("----------------\n");
                break;
            case ENPRUEBAS: 
                historial.append(" pasó a pruebas\n");
                historial.append(ficha.toString() + "\n");
                historial.append("----------------\n");
                break;                
            case TERMINADO:
                historial.append("se terminó\n");
                historial.append(ficha.toString() + "\n");
                historial.append("----------------\n");
                break;
        }
        return historial.toString();
    }
    
    /**
     * Añade el tipo de incidencia a la ficha
     */
    public void registrarIncidencia(Ficha ficha, String incidencia) {
        if(incidencia.equals("P")) {
            ficha.registrarIncidencia(Incidencia.PIEZAS);
        } else if (incidencia.equals("F")){
            ficha.registrarIncidencia(Incidencia.FALLOCALIDAD);
        }
    }
    
    /**
     * Muestra las piezas necesarias para fabricar un mueble asociado a una ficha
     */
    public String mostrarPiezas(Ficha ficha) {
        Mueble mueble = ficha.getMueble();
        String piezas = mueble.piezasToString();
        return piezas;
    }
    
    /**
     * Añadir piezas a la lista de materiales por pedir
     * Si la pieza ya existe la cantidad se aumenta sumando el valor antiguo más el nuevo
     */
    public void informarFaltaMaterial(Pieza pieza, int cantidad) {
        if(listaMateriales.containsKey(pieza) ){
            listaMateriales.replace(pieza, listaMateriales.get(pieza) + cantidad);
        }else {
            listaMateriales.put(pieza, cantidad);
        } 
    }
    
    /**
     * Devuelve un String con la lista de materiales pendientes de pedir
     */
    public String listarMateriales() {
        StringBuilder lista = new StringBuilder();
        for(Pieza pieza: listaMateriales.keySet()) {
            lista.append(pieza.toString() + " x " + listaMateriales.get(pieza) + "\n");
        }
        return lista.toString();
    }
    
    /**
     * Hacer pedido de materiales faltantes genera un aviso al Artesano y actualizar los materiales
     */
    public void pedirMateriales() {
        StringBuilder nStock = new StringBuilder();
        nStock.append("Nuevo materiales en stock \n");
        nStock.append(listarMateriales());
        this.stock = nStock.toString(); 
        listaMateriales.clear();
    }
    
    /**
     * Buscar ficha por estado
     */
    public List<Ficha> buscarFichaEstado(Estado estado) { 
        List<Ficha> fichaEstado = new ArrayList<>();
        for(Ficha f : listaFichas) {
            if(f.getEstado().equals(estado)) {                
                fichaEstado.add(f);
            }
        }
        return fichaEstado;        
    }
    
    /**
     * Buscar ficha ID
     */
    public Ficha buscarFichaID(String ID) {
        Ficha fID = null;
        for(Ficha ficha: listaFichas){
            if(ficha.getID().equals(ID)) {
                fID = ficha;
            }
        }
        return fID;
    }
 
    /**
     * Crea una nueva ficha a partir de un pedido
     */
    public Ficha crearFicha(Pedido pedido) {
        int number = listaFichas.size() + 1;
        Ficha f = new Ficha("F"+ number, pedido); 
        listaFichas.add(f);
        return f;
    }
    
    /**
     * Añade una ficha a un Artesano
     */
    public void actArtesanoFichas(Artesano artesano, Ficha ficha) {
        artesano.addFicha(ficha);
    }
    
    /**
     * Buscar pieza por su nombre
     */
    public Pieza buscarPieza(String nombre) {        
        return Pieza.valueOf(nombre);
    }
    
    /**
     * Devuelve un String con el stock
     */
    public String getStock() {
        return stock;
    }
    
    /**
     * Devuelve la lista de fichas
     */
    public List<Ficha> getListaFichas() {
        return listaFichas;
    }
}
