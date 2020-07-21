import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/**
 * La clase GestiónUsuarios funciona como puente entre la aplicación y todas las gestiones de creación, validación y modificación de usuarios.
 * 
 * @author Ana Belén
 * @version 1
 */
public class GestionUsuarios implements GestionUsuariosI  
{
    //La lista de usuarios contiene todos los usuarios de la aplicación clientes y empleados
    private HashMap <String, Persona> listaUsuarios;    
    /**
     * Constructor for objects of class GestionClientes
     */
    public GestionUsuarios()
    {
        listaUsuarios = new HashMap <String, Persona>();
    }
    
    /**
     * Registrar nuevo usuario (cliente o empleado) 
     * @persona es el nuevo usuario a registrar
     */
    public void nuevoUser(Persona persona) {
        listaUsuarios.put(persona.getUser(), persona);
    }
        
    /**
     * Crea una lista de usuarios para poder comenzar la aplicación
     */
    public void crearUsuariosBase() {
        Particular particular1 = new Particular("Maria", "Pérez", 25777888, "Calle Sol Nº 3 Madrid", 956212121, "mperez@gmail.com" );
        listaUsuarios.put(particular1.getUser(), particular1);
        Empresa empresa1 = new Empresa("Lola", "Sánchez", 78444555, "Calle Maria N4 Madrid", 959142145, "lsanchez@gmail.com", "Lola SA");
        listaUsuarios.put(empresa1.getUser(), empresa1);
        Plantilla plantill1 = new Plantilla("Lucía", "Gonzalez", 87541425, 8);
        listaUsuarios.put(plantill1.getUser(),plantill1);
        Plantilla plantill2 = new Plantilla("Mariana", "Gonzalez", 78541262, 4);
        listaUsuarios.put(plantill2.getUser(), plantill2);
        PorHoras porHoras1 = new PorHoras("Pepe", "López", 25142154, 6);
        listaUsuarios.put(porHoras1.getUser(), porHoras1);
        Comercial comercial1 = new Comercial("Jose", "Pérez", 25888655, 666222333, "jose@fabrica.com", 8);
        listaUsuarios.put(comercial1.getUser(), comercial1);
        Jefe jefe1 = new Jefe ("Alfredo", "López", 27444555, 8);
        listaUsuarios.put(jefe1.getUser(), jefe1);
    }
    
    /**
     * Este método permite modificar varios campos del usuario
     * Permite cambiar dirección y teléfono para clientes
     */
    public boolean modificarDatosUser(Cliente cliente, String opc, String nDato){
        //Modifica el dato indicado en la opción   
        boolean cambiado = true;
        if(opc.equals("D")){
            cliente.modificarDireccion(nDato);
        }else if(opc.equals("E")){
            cliente.modificarEmail(nDato);
        }else {
            cambiado = false;
        }
        return cambiado;
    }
    
    /**
     * Este método buscar por user a los usuarios y devuelve el usuario correspondiente
     * 
     */
    public Persona buscarUser(String user){
        Persona persona = null;
        for(String person : listaUsuarios.keySet()) {
            if(person.equals(user)){
                persona = listaUsuarios.get(person);
            }
        }
        return persona;
    }
    
    /**
     * Comprueba si el usuario está en activo y devuelve un boolean con la respuesta
     * 
     */
    public boolean confirmarActividad (String user) {
        boolean activo = buscarUser(user).getActividad();
        return activo;
    }
    
    /**
     * Este método buscar por user a los usuarios y devuelve el usuario correspondiente
     * 
     */
    public Cliente buscarClientePed(Pedido pedido){
        Cliente cliente = null;
        for(Persona persona : listaUsuarios.values()) {
            if(pedido.getCliente().equals(persona)){
                cliente = (Cliente)persona;
            }
        }
        return cliente;
    }
    
    /**
     * Devuelve una lista de todos los usuarios artesanos 
     */
    public List<Persona> getArtesanos() {
        return buscarUsuarioCategoria(Categoria.ARTESANO);
    }
    
    /**
     * Devuelve una lista de los usuarios clientes
     */
    public List<Persona> getClientes() {
        List<Persona> clientes = new ArrayList<>();
        clientes.addAll(buscarUsuarioCategoria(Categoria.PARTICULAR));
        clientes.addAll(buscarUsuarioCategoria(Categoria.EMPRESA));
        return clientes;
    }
    
    /**
     * Devuelve una lista de los usuarios clientes
     */
    public List<Persona> getEmpleados() {
        List<Persona> empleados = new ArrayList<>();
        empleados.addAll(getArtesanos());
        empleados.addAll(buscarUsuarioCategoria(Categoria.ARTESANO));
        empleados.addAll(buscarUsuarioCategoria(Categoria.COMERCIAL));
        empleados.addAll(buscarUsuarioCategoria(Categoria.JEFE));
        return empleados;
    }
    
    /**
     * Devuelve a los usuarios de una determinada clase
     */
    public List<Persona> buscarUsuarioCategoria(Categoria cat) {
        List<Persona> usuarios = new ArrayList<>();
        for(Persona usuario : listaUsuarios.values()) {
            if(usuario.getCategoria().equals(cat)){
                usuarios.add(usuario);
            }
        }
        return usuarios;    
    }
    
    /**
     * Modifica a inactivo un usuario
     */
    public void darBajaUser(String user) {
        buscarUser(user).darBaja();
    }
    
    /**
     * Reactiva un usuario
     */
    public void darAltaUser(String user) {
        buscarUser(user).darAlta();
    }
}
