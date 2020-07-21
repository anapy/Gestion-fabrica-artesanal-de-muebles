import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.lang.Integer;    
/**
 * La clase FabricaApp va a consistir en la interfaz textual del programa de la fábrica
 * Permitirá al usuario entrar con su identificador y tendrá varias posibilidades según el tipo de usuario
 * 
 * @author Ana Belén
 * @version 1
 */
public class Consola
{   
    private String user;
    private GestionUsuariosI gestorUser;
    private DptoComercialI dptComercial;
    private DptoProduccionI dptProduccion;
    /**
     * Constructor for objects of class FabricaAPP
     */
    public Consola(GestionUsuariosI gestorUser, DptoComercialI dptComercial, DptoProduccionI dptProduccion){
        this.gestorUser = gestorUser;
        this.dptProduccion = dptProduccion;
        this.dptComercial = dptComercial;     
    }
    
    /**
     * Este método llama al gestor de usuarios para iniciar la consola con varios usuarios ya en la base de datos
     */
    public void startApp() {
        gestorUser.crearUsuariosBase();
    }
         
    /**
     * Muestra el login de la aplicacion
     * Da opción de entrar con un usuario determinado o crear uno nuevo
     */
    public void showLogin() {
        System.out.println("Bienvenido a la web de la Fabrica Muebles Artesanos");
        System.out.println("Introduzca su usuario (Ej: 'C55555555') o Nuevo usuario (N) si no dispone de uno: ");
        String respuesta = scanString();       
        if(gestorUser.buscarUser(respuesta) != null || respuesta.equals("N") || respuesta.equals("ADMIN")){          
            if (respuesta.contains("C") || respuesta.contains("E")) {
                if(gestorUser.confirmarActividad(respuesta)){
                    user = respuesta;
                    dirigirIDMenu(user);
                } else {
                    System.out.println("Usuario inactivo \n");
                    showLogin();
                }              
            } else if (respuesta.equals ("N")) {
                Persona p;
                try{
                    p = pedirDatosPersonales();
                }catch (Exception e) {
                    System.out.println("Ha introducido un dato incorrecto. Vuelva a introducir sus datos \n");
                    p = pedirDatosPersonales();
                }
                gestorUser.nuevoUser(p);
                System.out.println("Su usuario es: " + p.getUser() + "\n");
                showLogin();            
            } else{
                showAdminMenu();
            }
        } else {
                System.out.println("Usuario incorrecto \n");
                showLogin();
        }        
    }
     
    /**
     * Muestra el menu para ADMIN
     */
    public void showAdminMenu() {               
        try{
            System.out.println("Menú Administrador");
            System.out.println("A - Ver listado de clientes");
            System.out.println("B - Ver listado de empleados");
            System.out.println("C - Ver listado de pedidos");
            System.out.println("D - Buscar usuario por user");
            System.out.println("E - Buscar pedido por ID");
            System.out.println("F - Dar de alta/baja usuario");
            System.out.println("G - Cerrar sesión");
            System.out.print("Indique una opción: ");
            String option = scanString();
            opcAdmin(option);
            showAdminMenu(); 
        }catch (Exception e){
            System.out.println("Se ha producido un error en la operación. Volviendo a menú... \n");
            showAdminMenu();
        }
    }
    
    /**
     * Redirige a la opción elegida por el artesano
     */
    public void opcAdmin(String opt) {
        switch (opt){
            //Ver listado de clientes
            case "A":
                imprimirListaPersona(gestorUser.getClientes());
                break;
            //Ver listado de empleados
            case "B":
                imprimirListaPersona(gestorUser.getEmpleados());
                break;
            //Ver listado de pedidos
            case "C":
                if(!dptComercial.infoPedidos().isEmpty()){
                    System.out.println(dptComercial.infoPedidos() + "\n");
                } else{
                    System.out.println("No hay ningún pedido aún \n");                       
                }    
                break;                
            //Buscar usuario por user
            case "D":
                System.out.println("Introduzca el usuario a consultar");
                System.out.println(gestorUser.buscarUser(scanString()).toString() + "\n");
                break;                
            //Buscar pedido por ID
            case "E":   
                System.out.println("Introduzca el ID del pedido a consultar");
                String ID = scanString();
                if(dptComercial.buscarPedID(ID) != null){
                    System.out.println(dptComercial.buscarPedID(ID).toString() + "\n");    
                } else{
                    System.out.println("Número de pedido incorrecto \n");    
                    
                }
                break;
            case "F":
            //Dar de alta/baja usuario
                System.out.println("Introduzca el usuario a modificar");
                try{
                    Persona persona = gestorUser.buscarUser(scanString());
                    System.out.println(persona.toString());
                    if(persona.getActividad()){
                        System.out.println("¿Dar de baja usuario?: (S) o (N)");
                        if(scanString().equals("S")){
                            persona.darBaja();
                            System.out.println("Usuario dado de baja");
                        }
                    }else if(!persona.getActividad()){
                        System.out.println("¿Activar usuario?: (S) o (N)");
                        if(scanString().equals("S")){
                            persona.darAlta();
                            System.out.println("Usuario dado de alta");
                        }
                    }
                }catch (Exception e){
                    System.out.println("Usuario incorrecto \n");
                }    
                break;
            case "G":
                showLogin();
                break;
            default:                
                System.out.println("No existe opción para su elección. Elija entre: A, B, C, D, E, F, G \n");
        }
    }
   
    /**
     * Da a elegir dos opciones a modificar
     */
    public String elegirOpc() {
        System.out.println("Modificar email(E) o dirección(D): ");
        String opc = scanString();
        return opc;    
    }
    
    /**
     * Pide los datos a modificar
     */
    public String nuevoDato() { 
        System.out.println("Nuevo dato: ");
        String dato = scanString();
        return dato;    
    }    
    
    /***
     * Devuelve el usuario actualmente operando
     */
    public String getUser() {
        return user;
    }
    
    /***
     * Devuelve la persona actualmente operando
     */
    public Persona getPersona() {
        return gestorUser.buscarUser(user); 
    }
    
    /**
     * Redirigir al Menú correspondiente según su usuario
     */
    public void dirigirIDMenu(String user) {
        if(user.startsWith("C")) {
            showCMenu();
        } else if (user.startsWith("EA")) {
            showEAMenu();
        } else if (user.startsWith("EJ")) {
            showEJMenu();   
        } else if(user.startsWith("EC")){
            showECMenu();
        }        
    }
        
    /**
     * Redirige a la opción elegida por el cliente
     */
    public void opcCliente(String opt, Cliente cliente) {
        switch (opt){
            //Hacer pedido
            case "A": 
                //Muestra el catálogo para que el cliente elija
                System.out.println(dptComercial.mostrarCatalogo()); 
                //Recoge la referencia y cantidad introducidos por pantalla
                System.out.println("Indique referencia (Ej: 'Ref 1'): ");
                Mueble mueble = dptComercial.buscarMuebleRef(scanString());
                System.out.println("Indique cantidad: ");
                int cant = scanInt();
                System.out.println("¿Tiene alguna petición diferente de color:  (S) o (N)?");
                String respuesta = scanString();
                Pedido ped = null;
                if(respuesta.equals("S")){
                    System.out.println("Elija un nuevo color: MARRON, BLANCO, AZUL");
                    respuesta = scanString();
                    mueble.cambiarColor(Color.valueOf(respuesta));
                    ped = hacerPedido(cliente, mueble, cant);
                    ped.addPeticion(respuesta);
                    dptComercial.addPedido(ped); 
                    System.out.println("Pedido realizado \n"+ ped.toStringSinPrecio() + "\n");
                }  else{
                    ped = hacerPedido(cliente, mueble, cant);
                    dptComercial.addPedido(ped); 
                    System.out.println("Pedido realizado \n"+ ped.toString() + "\n");  
                }              
                break;                
            //Aceptar pedidos pendientes
            case "B":
                List<Pedido> lista = dptComercial.buscarPedEstado(Estado.PDTCLIENTE); 
                if(lista.isEmpty()){
                    System.out.println("No hay pedidos pendientes");
                } else {
                    cambiarEstadoListaPed(lista, Estado.ACEPTADO);
                }
                break;
                
            case "C":   
            //Ver pedidos realizados 
                imprimirListaPedidos(dptComercial.buscarPedPorCliente(cliente));
                break;
                
            //Modificar datos personales
            case "D":
                if(gestorUser.modificarDatosUser(cliente, elegirOpc(), nuevoDato())){
                    System.out.println("Dato cambiado \n");
                } else {
                    System.out.println("Error. Inténtelo de nuevo \n");
                }
                
                break;
                
            //Buzón de notificaciones    
            case "E":
                imprimirListaString(cliente.getNotificaciones());
                break;
            //Cerrar sesión
            
            case "F":
                showLogin();
                break;
                
            default:                
                System.out.println("No existe opción para su elección. Elija entre: A, B, C, D, E o F \n");
        }
    }

    /**
     * Dar de alta nuevo pedido de un determinado cliente
     * @param cliente que realiza el pedido
     */
    public Pedido hacerPedido(Cliente cliente, Mueble mueble,Integer cantidad) {
        //El ID será el nº de pedidos hechos por el cliente más el nombre del cliente
        String ID = (dptComercial.getNoPedidos() + 1) + "C" + cliente.getID();        
        Pedido pedido = new Pedido(ID, cliente, mueble, cantidad);       
        return pedido;
    }
    
    /**
     * Muestra el menu para clientes
     */
    public void showCMenu() {
        try {
            Cliente cliente = (Cliente)getPersona();        
            System.out.println("Menú Cliente");
            System.out.println("A - Hacer pedido");
            System.out.println("B - Aceptar pedidos pendientes");
            System.out.println("C - Ver pedidos realizados");
            System.out.println("D - Modificar datos personales");
            System.out.println("E - Buzon de notificaciones");
            System.out.println("F - Cerrar sesión");
            System.out.print("Indique una opción: ");
            String opcion = scanString();            
            while(!opcion.equals("F")) {
                opcCliente(opcion, cliente);
                showCMenu();
            }  
            opcCliente(opcion, cliente);
        } catch (Exception e) {
            System.out.println("Se ha producido un error en la operación. Volviendo a menú... \n");
            showCMenu();
        } 
    }
    
    public void imprimirListaString(List<String> lista) {
        if(!lista.isEmpty()) {
            for(String dato: lista){
                System.out.println(dato.toString() + "\n");
            }
        } else {
            System.out.println("No hay datos para mostrar \n");
        }
    }
    
    /**
     * Muestra el menu para comerciales
     */
    public void showECMenu() {
        try{
            Comercial comercial = (Comercial)getPersona();
            System.out.println("Menú Comerciales");
            System.out.println("A - Ver nuevos pedidos registrados");
            System.out.println("B - Ver pedidos pendientes de cliente");
            System.out.println("C - Confirmar pedidos recibidos");
            System.out.println("D - Consultar pedidos en curso");
            System.out.println("E - Consultar pedidos terminados");
            System.out.println("F - Cerrar sesión");
            System.out.print("Indique una opción: ");
            String opcion = scanString();
            while(!opcion.equals("F")){
                opcComercial(opcion, comercial);
                showECMenu();
            }
            opcComercial(opcion, comercial);
        }catch (Exception e){
            System.out.println("Se ha producido un error en la operación. Volviendo a menú... \n");
            showECMenu();
        }
    }
    
    /**
     * Informar que no hay pedidos en la lista recibida
     */
    public void informarNoPedidos(List<Pedido> lista) {
        if(lista.isEmpty()) {
            System.out.println("No hay pedidos \n");
        }
    }
    
    public List<Pedido> cambiarEstadoListaPed(List<Pedido> lista, Estado estado){
        List<Pedido> nLista = new ArrayList<>();
        for(Pedido pedido: lista) {
            System.out.println(pedido.toString());
            System.out.println("¿Confirmar pedido: (S) o (N) ?");
            if(scanString().equals("S")){
                pedido.cambiarEstado(estado);
                nLista.add(pedido);
            } 
        }
        return nLista;
    }
            
    /**
    * Redirige a la opción elegida por el comercial
    */
    public void opcComercial(String opt, Comercial comercial) {
        switch (opt){
            //Ver nuevos pedidos registrados
            case "A":
                imprimirListaPedidos(dptComercial.consultarPedRegistrados());  
                break; 
                    
            case "B":
                imprimirListaPedidos(dptComercial.buscarPedEstado(Estado.PDTCLIENTE));  
                break;    
                    
            //Confirmar pedidos recibidos   
            case "C":
                List<Pedido> listaConfirmada = new ArrayList<>();
                //Confirma primero pedidos en estado Registrado
                for(Pedido pedido: dptComercial.buscarPedEstado(Estado.REGISTRADO)){
                    System.out.println(pedido.toString());
                    System.out.println("¿Confirmar pedido: (S) o (N) ?");
                    if(scanString().equals("S")){
                       pedido = dptComercial.confirmarRecibidos(comercial, pedido);
                    }
                    if(pedido.getEstado().equals(Estado.CONFIRMADO)){
                        listaConfirmada.add(pedido);
                    }
                }
                
                //Confirma los pedidos en estado Aceptado
                listaConfirmada.addAll(cambiarEstadoListaPed(dptComercial.buscarPedEstado(Estado.ACEPTADO), Estado.CONFIRMADO));                
                
                //Crea las nuevas fichas de los pedidos que han sido confirmados
                for(Pedido pedido: listaConfirmada ) {
                    Ficha ficha = dptProduccion.crearFicha(pedido); 
                    pedido.asignarFicha(ficha);
                    Cliente cliente = gestorUser.buscarClientePed(pedido);
                    cliente.addNotificacion(dptComercial.crearNotificacion(pedido)); 
                }
                informarNoPedidos(listaConfirmada);
                break;
                 
            //Consultar pedidos en curso    
            case "D":
                imprimirListaPedidos(dptComercial.mostrarPedEnCurso()); 
                break;
                
            //Consultar pedidos terminados  
            case "E":
                imprimirListaPedidos(dptComercial.mostrarPedTerminados());
                break;
                
            //Cerrar sesión    
            case "F":
                showLogin();
                break;
            default:
                System.out.println("No existe opción para su elección. Elija entre: A, B, C, D, E o F \n");
        }
    }
   
    /**
     * Muestra el menu para jefes
     */
    public void showEJMenu() {
        try{
            Jefe jefe = (Jefe)getPersona();
            System.out.println("Menú Empleado Jefe");
            System.out.println("A - Ver fichas pendientes");
            System.out.println("B - Asignar fichas");
            System.out.println("C - Ver estado fichas en fabricación");
            System.out.println("D - Consultar trabajo de un artesano");
            System.out.println("E - Revisar falta de Stock");
            System.out.println("F - Cerrar sesión");
            System.out.print("Indique una opción: ");
            String opcion = scanString();
            while(!opcion.equals("F")) {
                opcJefe(opcion, jefe);
                showEJMenu();
            }
            opcJefe(opcion, jefe);
        }catch(Exception e){
            System.out.println("Se ha producido un error en la operación. Volviendo a menú... \n");
            showEJMenu();
        }    
    }
    
    /**
     * Redirige a la opción elegida por el artesano
     */
    public void opcJefe(String opt, Jefe jefe) {
        switch (opt){
            // Ver fichas pendientes
            case "A":
                String fichas = dptProduccion.verFichasPendientes();
                if(fichas.isEmpty()){
                    System.out.println("No hay fichas pendientes de asignación"); 
                } else {
                    System.out.println(dptProduccion.verFichasPendientes()); 
                }
                break;
            //Asignar fichas
            case "B": 
                if(dptProduccion.buscarFichaEstado(Estado.PDTASIGNACION).isEmpty()) { 
                    System.out.println("No hay fichas por asignar \n");
                } else {
                    imprimirListaFichas(dptProduccion.buscarFichaEstado(Estado.PDTASIGNACION));                 
                    System.out.println("Indicar identificador de la ficha: ");
                    String fID = scanString();
                    imprimirListaPersona(gestorUser.getArtesanos()); 
                    System.out.println("Indicar ID del Artesano (unicamente el número): ");
                    Artesano artesano = (Artesano)gestorUser.buscarUser("EA" + scanString());                
                    Ficha ficha = dptProduccion.buscarFichaID(fID);
                    if(artesano != null && ficha != null){
                        dptProduccion.asignarArtesano(artesano, ficha);
                        dptComercial.cambiarEstadoPed(ficha.getPedido(), Estado.ENFABRICACION); 
                        artesano.addHistorialTrabajo(dptProduccion.actualizarTrabajoArtesano(ficha));
                        Cliente cliente = gestorUser.buscarClientePed(ficha.getPedido());
                        cliente.addNotificacion(dptComercial.crearNotificacion(ficha.getPedido())); 
                        System.out.println("Ficha asignada con exito\n");
                    } else {
                        System.out.println("Datos incorrectos. Vuelva a intentarlo");
                    }
                }
                break;
            //Ver estado fichas en fabricación
            case "C":
                imprimirListaFichas(dptProduccion.getListaFichas());
                break;
            //Consultar trabajo de un artesano
                case "D":
                imprimirListaPersona(gestorUser.getArtesanos()); 
                System.out.println("Indique el ID del artesano (únicamente el número): ");  
                Artesano artesano = (Artesano)gestorUser.buscarUser("EA" + scanString());
                if(!artesano.getHistorialTrabajo().isEmpty()){
                    System.out.println(artesano.getHistorialTrabajo() + "\n");
                } else {
                    System.out.println("El artesano seleccionado aún no tiene pedidos en proceso\n");    
                }
                break;    
                
            //Ver lista de materiales faltantes
            case "E":
                if(dptProduccion.listarMateriales().isEmpty()){
                    System.out.println("No hay materiales faltantes \n"); 
                } else {
                    System.out.println(dptProduccion.listarMateriales());
                    System.out.println("¿Hacer pedido: (S) o (N)?"); 
                    if(pedirRespuesta().equals("S")){
                        dptProduccion.pedirMateriales();
                    }
                } 
                break;
                
            //Cerrar Sesión
            case "F":
                showLogin();
                break;
            default:                
                System.out.println("No existe opción para su elección. Elija entre: A, B, C, D, E o F \n");
        }
    }
    
    /**
     * Pedir respuesta
     */
    public String pedirRespuesta() {
        String respuesta = scanString();
        return respuesta;
    }
    
    public void imprimirListaPersona(List<Persona> lista){
        for(Persona person: lista){
            System.out.println(person.toString() + "\n");
        }       
    }
    
    public void imprimirListaPedidos(List<Pedido> lista){
        if(lista.isEmpty()){
            System.out.println("No hay ningún pedido en este estado");
        }
        if(user.contains("E")){
            for(Pedido pedido: lista){
                System.out.println(pedido.toStringFicha() + "\n");
            }
        } else {
            for(Pedido pedido: lista){
                System.out.println(pedido.toString() + "\n");
            }    
        }
    }
    
    public void imprimirListaFichas(List<Ficha> lista){
        if(lista.isEmpty()){
            System.out.println("No hay ninguna ficha en este estado \n");
        } else {
            for(Ficha ficha: lista){
                System.out.println(ficha.toString() + "\n");
            }
        }
       
    }
    
    /**
     * Muestra el menu para artesanos
     */
    public void showEAMenu() {
        try{
            Artesano artesano = (Artesano)getPersona();
            System.out.println("Menú Empleado Artesano");
            System.out.println("A - Ver fichas asignadas");
            System.out.println("B - Seleccionar nueva ficha de trabajo");
            System.out.println("C - Actualizar fichas en producción");
            System.out.println("D - Revisar Stock");
            System.out.println("E - Cerrar sesión");
            System.out.print("Indique una opción: ");
            String option = scanString(); 
            while(!option.equals ("E")) {
                opcArtesano(option, artesano);
                showEAMenu();
            }
            opcArtesano(option, artesano);
        }catch (Exception e){
            System.out.println("Se ha producido un error en la operación. Volviendo a menú... \n");
            showEAMenu();
        }
    }

    /**
     * Redirige a la opción elegida por el artesano
     */
    public void opcArtesano(String opt, Artesano artesano) {
        switch (opt){
            //Ver fichas asignadas
            case "A":
                List<Ficha> fichas = artesano.getFichas();
                if(fichas.isEmpty()){
                    System.out.println("No tiene fichas asignadas \n"); 
                } else {
                    imprimirListaFichas(fichas);
                }
                break;
            //Seleccionar nueva ficha de trabajo
            case "B":
                if(!artesano.getFichas().isEmpty()){
                    for(Ficha ficha: artesano.getFichas()) {
                        if(ficha.getEstado().equals(Estado.ENCOLA)){
                            System.out.println(ficha.toString());
                            System.out.print("Introducir ID de la ficha: \n");
                            String ID = scanString();
                            ficha = dptProduccion.buscarFichaID(ID);
                            System.out.println(dptProduccion.seleccionarFicha(ID) + "\n");
                            artesano.addHistorialTrabajo(dptProduccion.actualizarTrabajoArtesano(ficha));
                        } else {
                            System.out.println("No hay fichas pendientes");
                        }
                    }
                } else {
                    System.out.println("No hay fichas pendientes");
                }
                

                break;
            //Actualizar fichas en producción
            case "C":
                showEASubMenu(artesano);
                break;                
            //Revisar Stock
            case "D":
                if(dptProduccion.getStock() != null){
                    System.out.println(dptProduccion.getStock()); 
                    System.out.println("Reactivar alguna ficha: (S) o (N)");
                    if(pedirRespuesta().equals("S")) {
                        opcArtesanoSub("B", artesano);
                    }
                } else {
                    System.out.println("No hay nuevo stock");
                }

                break;                
            //Cerrar sesión
            case "E":
                showLogin();
                break;
            default:                
                System.out.println("No existe opción para su elección. Elija entre: A, B, C, D o E \n");
        }
    }
   
    /**
     * Muestra el subMenú de los artesanos
     */
    public void showEASubMenu(Artesano artesano) {    
        try{    
            System.out.println("Menú fichas en producción");
            System.out.println("A - Registrar incidencia");
            System.out.println("B - Reactivar pedido en Pausa");
            System.out.println("C - Pasar ficha a pruebas");
            System.out.println("D - Finalizar ficha");
            System.out.println("E - Volver Menú principal");
            System.out.print("Indique una opción: ");
            String option = scanString();
            while(option.equals("E")){
                opcArtesanoSub(option, artesano);
                showEAMenu();
            }
            opcArtesanoSub(option, artesano);
        }catch(Exception e){
            System.out.println("Se ha producido un error en la operación. Volviendo a menú... \n");
            showEASubMenu(artesano);
        }
    }
        
    /**
     * Redirige a la opción elegida por el artesano
     */
    public void opcArtesanoSub(String opt, Artesano artesano) {
        switch (opt){
            //Registrar incidencia
            case "A":
            if(!artesano.getFichas().isEmpty()) {  
                   for(Ficha ficha: artesano.getFichas()) {
                       if(ficha.getEstado().equals(Estado.ENFABRICACION) || ficha.getEstado().equals(Estado.ENPAUSA) || ficha.getEstado().equals(Estado.ENPRUEBAS)){
                            imprimirListaFichas(artesano.getFichas()); 
                            System.out.print("Introducir ID de la ficha: ");
                            String ID = scanString(); 
                            ficha = dptProduccion.pausarFicha(ID);
                            System.out.print("Incidencia:  Falta de piezas(P) o  Fallo calidad(F)");
                            String incidencia = scanString();
                            dptProduccion.registrarIncidencia(ficha, incidencia);
                            if(incidencia.equals("P")) {
                                System.out.println(dptProduccion.mostrarPiezas(dptProduccion.buscarFichaID(ID)));
                                introducirMateriales(ficha);
                            }
                            artesano.addHistorialTrabajo(dptProduccion.actualizarTrabajoArtesano(ficha));
                       }
                   }   
                } else {
                    System.out.println("No hay fichas para registrar incidencias\n");
                } 
    
            break;
            //Reactivar pedido en Pausa    
        case "B":  
            if(!artesano.getFichas().isEmpty()) {  
                for(Ficha ficha: artesano.getFichas()) {
                    if(ficha.getEstado().equals(Estado.ENPAUSA)){
                        imprimirListaFichas(dptProduccion.buscarFichaEstado(Estado.ENPAUSA));
                        System.out.print("Introducir ID de la ficha: ");
                        String ID = scanString(); 
                        ficha = dptProduccion.buscarFichaID(ID);
                        dptProduccion.reactivarFicha(ID);
                        artesano.addHistorialTrabajo(dptProduccion.actualizarTrabajoArtesano(ficha));
                        break;
                    }
                }   
            } else {
                System.out.println("No hay fichas para pasa reactivar");
            } 
        
        //Pasar a prueba
        case "C":
        if(!artesano.getFichas().isEmpty()) {  
                for(Ficha ficha: artesano.getFichas()) {
                    if(ficha.getEstado().equals(Estado.ENFABRICACION) || ficha.getEstado().equals(Estado.ENPAUSA)){
                        imprimirListaFichas(artesano.getFichas()); 
                        System.out.print("Introducir ID de la ficha: ");
                        String ID = scanString(); 
                        ficha = dptProduccion.buscarFichaID(ID);
                        dptProduccion.pasarPruebasFicha(ID);     
                        artesano.addHistorialTrabajo(dptProduccion.actualizarTrabajoArtesano(ficha));
                        break;
                    }
                }   
            } else {
                System.out.println("No hay fichas para pasar a pruebas");
            } 
    
         //Finalizar ficha            
        case "D":
            if(!artesano.getFichas().isEmpty()) {
                for(Ficha ficha: artesano.getFichas()) {
                    if(ficha.getEstado().equals(Estado.ENFABRICACION) || ficha.getEstado().equals(Estado.ENPAUSA) || ficha.getEstado().equals(Estado.ENPRUEBAS)){
                        imprimirListaFichas(artesano.getFichas()); 
                        System.out.print("Introducir ID de la ficha: ");
                        String ID = scanString(); 
                        dptProduccion.terminarFicha(ID);
                        ficha = dptProduccion.buscarFichaID(ID);
                        ficha.getPedido().cambiarEstado(Estado.TERMINADO);
                        Cliente cliente = gestorUser.buscarClientePed(ficha.getPedido());
                        cliente.addNotificacion(dptComercial.crearNotificacion(ficha.getPedido())); 
                        artesano.addHistorialTrabajo(dptProduccion.actualizarTrabajoArtesano(ficha));
                    } else {
                        System.out.println("No hay fichas por finalizar");
                    }
                }
            }else {
                System.out.println("No hay fichas por finalizar");
            }
            break;
        
        //Volver Menú principal
        case "E":
            showEAMenu();
            break;
        default:                
            System.out.println("No existe opción para su elección. Elija entre: A, B, C, D o E");
        }
    }   
    
    /**
     * Permite introducir los materiales faltantes por pantalla y los guarda en la lista de materiales faltantes
     */
    public void introducirMateriales(Ficha ficha) {
        String respuesta;
        do {
            System.out.println("Indique pieza: ");
            String pieza = scanString();
            System.out.println("Indique cantidad faltante: ");
            int cantidad = scanInt();   
            dptProduccion.informarFaltaMaterial(dptProduccion.buscarPieza(pieza), cantidad);
            System.out.println("¿Pedir más materiales (S) o (N): ?");
            respuesta = scanString();
        }while(respuesta.equals("S"));       
    }

    /**
     * Permite introducir String por terminal
     * Pasa todo a mayúsculas y borra los espacios para evitar errores 
     * Devuelve un String
     */
    public String scanString() {
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine().toUpperCase().trim();
        sc.close();
        string = string.replaceAll("\\s","");
        return string;
    }

    /**
     * Permite introducir número de tipo int por terminal elimina los espacios
     * Devuelve un int
     */
    public Integer scanInt() {
        Scanner sc = new Scanner(System.in);
        if(sc.hasNextInt()) {
            String string = sc.nextLine().trim();
            sc.close();
            int number = Integer.parseInt(string); 
            return number;
        } else {
           sc.nextLine();
           return null;
        }
    }

    /**
     * Pide todos los datos personales para crear un nuevo usuario
     */
    public Persona pedirDatosPersonales(){
        Persona persona = null; 
        try {
            System.out.println("DNI sin letra");
            int ID = 0;
            try {
                ID = scanInt();
            } catch (Exception e) {
                System.out.println("Dato incorrecto \n");
                pedirDatosPersonales();
            }
            System.out.println("Nombre");
            String n = scanString();
            System.out.println("Apellido");
            String apell = scanString();
            System.out.println("Cliente(C) o Empleado(E): ");
            String perfil = scanString();
            while(!perfil.equals("C") && !perfil.equals("E")){
                System.out.println("Opción incorrecta.\nIntroduzca Cliente(C) o Empleado(E)");
                perfil = scanString();
            }
            //Datos solo para clientes
            if(perfil.equals("C")) {
                System.out.println("Particular(P) o Empresa(E) ");
                String perfil2 = scanString();
                while(!perfil2.equals("P") && !perfil2.equals("E")){
                    System.out.println("Opción incorrecta.\n Introduzca Particular(P) o Empresa(E)\n");
                    perfil2 = scanString();                
                }
                System.out.println("Dirección");
                String dir = scanString();
                System.out.println("Teléfono");
                int tel = scanInt();
                System.out.println("Email");
                String email = scanString();
                if(perfil2.equals("P")){
                    Particular particular = new Particular(n, apell, ID, dir, tel, email);
                    persona =  particular;
                } else {
                    System.out.println("Nombre de empresa: ");
                    String nEmp = scanString();
                    Empresa empresa = new Empresa(n, apell, ID, dir, tel, email, nEmp);
                    persona =  empresa;
                }
            //Datos solo para empleados    
            } else {
                System.out.println("Comercial(EC) o Jefe(EJ) o Artesano(EA): ");
                String perfil2 = scanString();
                while(!perfil2.equals("EC") && !perfil2.equals("EJ") && !perfil2.equals("EA")){
                    System.out.println("Opción incorrecta.\nIntroduzca EC(Comercial) o EJ(Jefe) o EA(Artesano): ");
                    perfil2 = scanString(); 
                }
                System.out.println("Horas");
                int horas = 8;
                try {
                    horas = scanInt();
                } catch (Exception e) {
                    System.out.println("Dato incorrecto. Introduzca horas en número");
                    pedirDatosPersonales();
                }            
                if(perfil2.equals("EC")) {
                    System.out.println("Teléfono");
                    int tel = scanInt();
                    System.out.println("Email");
                    String email = scanString();
                    Comercial comercial = new Comercial(n, apell, ID, tel, email, horas);
                    persona =  comercial;
                } else if(perfil2.equals("EJ")) {
                    Jefe jefe = new Jefe(n, apell, ID, horas);
                    persona = jefe;
                } else{
                    if(horas != 8 && horas != 4) {
                        PorHoras phoras = new PorHoras(n, apell, ID, horas);
                        persona =  phoras;
                    }else{
                        Plantilla plantilla = new Plantilla(n, apell, ID, horas);
                        persona =  plantilla;
                    }
                }
            }
            return persona;
        } catch(Exception e) {
            System.out.println("Datos incorrectos. Introduzca de nuevo sus datos\n");
            pedirDatosPersonales();
        }
        return persona;
    }    
}


