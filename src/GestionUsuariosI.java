import java.util.List;

/**
 * La interfaz GestionUsuariosI gestiona la base de datos de usuarios y permite hacer cambios.
 * 
 * @author Ana Belén
 * @version 1
 */

public interface GestionUsuariosI
{  
    //Crea la base de datos de usuarios para iniciar la aplicación con algunos usuarios ya creados
    void crearUsuariosBase();
    //Devuelve true/false dependiendo si el usuario está activo o no
    boolean confirmarActividad (String user);
    //Permite crear un nuevo usuario 
    void nuevoUser(Persona persona);
    //Permite modificar datos de clientes (email y dirección) y devuelve true si el cambio se ha realizado
    boolean modificarDatosUser(Cliente cliente, String opc, String nDato);
    //Busca el usuario por user y devuelve la correspondencia
    Persona buscarUser(String user);
    //Busca el usuarios por categoriía y devuelve una lista
    List<Persona> buscarUsuarioCategoria(Categoria cat);
    //Devuelve el listado de artesanos
    List<Persona> getArtesanos();
    //Devuelve el listado de clientes
    List<Persona> getClientes();
    //Devuelve el listado de empleados    
    List<Persona> getEmpleados();
    //Busca un cliente por su pedido
    Cliente buscarClientePed(Pedido pedido);
}
