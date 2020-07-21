import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
/**
 * La clase DptoComercial se ocupa de la gestión de las opciones que involucran a los pedidos, los comerciales y los clientes.
 * 
 * @author Ana Belén
 * @version 1
 */
public class DptoComercial implements DptoComercialI 
{
    //Listado de pedidos que se crean por los clientes
    private HashMap<String, Pedido> listaPedidos;
    //Catalogo de muebles que se fabrican
    private ArrayList<Mueble> catalogo;     
    private DptoProduccionI dptProduccion; 
    /**
     * Constructor for objects of class DptoComercial
     */
    public DptoComercial(DptoProduccionI dptProduccion)
    {
        this.dptProduccion = dptProduccion;
        listaPedidos = new HashMap<String, Pedido>();
        catalogo = new ArrayList <Mueble>();
        crearCatalogo();
    }
    
    /**
     * Crea un mueble de cada modelo y lo añade al catálogo
     * 
     */
    private void crearCatalogo() {
        MesaCafeMadera mesaCafeMadera = new MesaCafeMadera();
        catalogo.add(mesaCafeMadera);
        MesaCafeCristal mesaCafeCristal = new MesaCafeCristal();
        catalogo.add(mesaCafeCristal);
        MesaDormitorio mesaDormitorio = new MesaDormitorio();
        catalogo.add(mesaDormitorio);
        MesaComedor mesaComedor = new MesaComedor();
        catalogo.add(mesaComedor);
        SillaCocina sillaCocina = new SillaCocina();
        catalogo.add(sillaCocina);
        SillaEscritorioRuedas sillaEscritorioRuedas = new SillaEscritorioRuedas();
        catalogo.add(sillaEscritorioRuedas);
        SillaEscritorioSRuedas sillaEscritorioSinRuedas = new SillaEscritorioSRuedas();
        catalogo.add(sillaEscritorioSinRuedas);
        SillaPlegable sillaPlegable = new SillaPlegable();
        catalogo.add(sillaPlegable);
    }
    
    /**
     * Muestra los muebles que se fabrican
     */
    public String mostrarCatalogo() {        
        String muestra = null;
        StringBuilder a = new StringBuilder(); 
        a.append("Catálogo\n");
        a.append("-------------\n");
        for(Mueble mueble: catalogo) {
            a.append(mueble.toString() + "\n");
            
        }    
        muestra = a.toString();
        return muestra;
    }

    /**
     * Buscar pedido por ID
     */
    public Pedido buscarPedID(String ID) {
        return listaPedidos.get(ID);
    } 
    
    /**
     * Buscar pedido por estado
     */
    public List<Pedido> buscarPedEstado(Estado estado) { 
        List<Pedido> pedEstado = new ArrayList<>();
        for(Pedido e : listaPedidos.values()) {
            if(e.getEstado().equals(estado)) {                
                pedEstado.add(e);
            }
        }
        return pedEstado;        
    }
    
    /**
     * Buscar pedidos por usuario
     */
    public List<Pedido> buscarPedPorCliente(Cliente cliente){
        List<Pedido> lista = new ArrayList();
        for(Pedido ped : listaPedidos.values()){
            if(ped.getCliente().getID() == (cliente.getID())){
                lista.add(ped);
            }
        }
        return lista;
    }
    
    /**
     * Opción A del comercial, muestra los pedidos, el ID con el cliente y la información del pedido y 
     * Actualiza los gastos extras en el caso de que hubiera
     * 
     */
    public List<Pedido> consultarPedRegistrados(){  
        //Se imprimen los pedidos que se encuentran en estado REGISTRADO
        List<Pedido> pedPendientes = buscarPedEstado(Estado.REGISTRADO);
        for(Pedido pedido: pedPendientes) {
            pedido.actualizarExtras();
        }
        return pedPendientes;   
    }  
  
    
    /** 
     * Cambia el estado del pedido dependiendo de si hay o no peticiones de cambio de color
     */ 
    public Pedido confirmarRecibidos(Comercial comercial, Pedido pedido) {  
        pedido.definirComercial(comercial);
        if(pedido.getPeticionColor().equals(Color.NEGRO)){
            cambiarEstadoPed(pedido, Estado.CONFIRMADO);   
            comercial.addPedido(pedido);
        } else if(!pedido.getPeticionColor().equals(Color.NEGRO) && pedido.getEstado().equals(Estado.REGISTRADO)){
            pedido.actualizarExtras();
            pedido.actPrecio(); 
            cambiarEstadoPed(pedido, Estado.PDTCLIENTE);   
            comercial.addPedido(pedido);   
        }
        return pedido;     
    }
    
    /**
     * Devuelve información de todos los pedidos
     */
    public String infoPedidos() {
        StringBuilder ped = new StringBuilder();
        for(Pedido pedido : listaPedidos.values()) {
            ped.append(pedido.toStringFicha() + "\n");
        }        
        return ped.toString();
    } 
    
    /**
     * Cambia de estado un pedido
     */
    public void cambiarEstadoPed(Pedido pedido, Estado nEstado) {
        pedido.cambiarEstado(nEstado); 
    }
    
    /**
     * Dar orden de fabricación a los pedidos confirmados
     */
    public void ordenarFabricacion(){
        List<Pedido> pedConfirmados = buscarPedEstado(Estado.CONFIRMADO);
        for(Pedido p : pedConfirmados){
            dptProduccion.crearFicha(p);
        }        
    }
    
    /**
     * Muestra los pedidos en estado confirmado y enFabricacion.
     * 
     */
    public List<Pedido> mostrarPedEnCurso() {
        List<Pedido> listado = new ArrayList();
        //Se listan los pedidos que se encuentran en fabricación o terminados
        listado.addAll(buscarPedEstado(Estado.CONFIRMADO));
        listado.addAll(buscarPedEstado(Estado.ENFABRICACION));
        return listado;
    }
    
    /**
     * Opción D de comerciales devuelve los pedidos terminados
     */
    public List<Pedido> mostrarPedTerminados() {
        List<Pedido> listado = new ArrayList();
        listado.addAll(buscarPedEstado(Estado.TERMINADO));
        return listado;
    }
    
    /**
     * Opción D de comerciales Informar cliente
     */
    public String crearNotificacion(Pedido pedido) { 
        Date fecha  = new Date();
        StringBuilder notificacion = new StringBuilder();
        notificacion.append(fecha.toString() + "\n");
        notificacion.append("Su pedido nº " + pedido.getID());
        switch(pedido.getEstado()){
            case PDTCLIENTE:
                notificacion.append(" está pendiente de aceptar");
                break;
            case CONFIRMADO:
                notificacion.append(" ha sido confirmado");
                break;
            case ENFABRICACION: 
                notificacion.append("ha pasado a fabricación");
                break;
            case TERMINADO:
                notificacion.append("su pedido está listo para recoger");
                break;
        }
        return notificacion.toString();
    }
    
    /**
     * Actualiza los listados de pedidos del cliente, y la fábrica
     */
    public void actualizarPed(Pedido pedido, Cliente cliente) {
        //El nuevo pedido creado se añade al listado de
        listaPedidos.put(pedido.getID(), pedido);
        //Se añade el nuevo pedido a los pedidos del cliente
        cliente.addPedidos(pedido);
    }
    
    /**
     * Crea un nuevo pedido 
     */
    public void addPedido(Pedido pedido) {
        listaPedidos.put(pedido.getID(), pedido);
    }

    /**
     * Buscar mueble por referencia
     */
    public Mueble buscarMuebleRef (String ref) {
        Mueble mueble = null;
            for(Mueble m : catalogo){
                if(m.getRef().equals(ref)){
                    mueble = m;
                }            
            }    
        return mueble;
    }

    /**
     * 
     */
    public int getNoPedidos() {
        return listaPedidos.size();
    }
}
