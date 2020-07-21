/**
 * La clase personas es la superclase común de los clientes y empleados.
 * 
 * @Author Ana Belen
 * @version 1
 */
public abstract class Persona
{
    private String nombre;
    private String apellido;
    //ID recogerá el DNI o NIF dependiendo de si identifica a una persona o a un cliente empresa.
    private int ID;
    //User identifica con una letra el tipo de usuario al que se registra y se une al DNI
    protected String user;
    //Perfil del usuario: artesano, jefe, comercial, particular, empresa
    private Categoria categoria;
    //Si es true, el usuario está en uso, si es false ha sido dado de baja
    private boolean actividad;
    /**
     * Constructor para objetos de la clase persona
     */
    public Persona(String n, String apell,  int ID)
    {
        this.nombre = n;
        this.apellido = apell;
        this.ID = ID;   
        actividad = true;
    }
    
    /**
     * Devuelve el ID
     * 
     */
    public int getID() {
        return ID;
    }
    
    /**
     * Permite cambiar el ID
     */
    public void modificarID(int nuevoID) {
        this.ID = nuevoID;
    }
    
    /**
     * Devuelve el nombre 
     */
    public String getNombre() {
        return nombre;
    }
   
    /**
     * Devuelve el apellido 
     */
    public String getApellido() {
        return apellido;
    }        
    
    /**
     * Acumula toda la información de la persona en un solo String y la devuelve para usarla posteriormente al consultar sus datos
     */
    public String toString() {
        StringBuilder p = new StringBuilder();
        if(actividad){
            p.append("Usuario activo\n");
        }else{
            p.append("Usuario inactivo\n");
        }
        p.append("ID: " + ID + "\n");  
        p.append("Nombre: " + nombre + "\n");
        p.append("Apellido: " + apellido + "\n"); 
        return p.toString();
    }   
    
    /**
     * Consultar usuario
     */
    public String getUser() {
        return user; 
        }
        
    /**
     * Modificar el usuario
     */    
    public void setUser(String nUser) {
        this.user = nUser;
    }
    
    /**
     * Devuelve el perfil de la persona
     */
    public Categoria getCategoria(){
        return categoria;
    }
    
    /**
     * Modifica el perfil de la persona
     */
    public void modificarCategoria(Categoria nCategoria){
        this.categoria = nCategoria;
    }
    
    /**
     * Devuelve true o false dependiendo de si el usuario está activo o de baja
     */
    public boolean getActividad() {
        return actividad;
    }
    
    /**
     * Desactivar la persona
     */
    public void darBaja() {
        actividad = false;
    }
    
    /**
     * Reactivar la persona
     */
    public void darAlta() {
        actividad = true;
    }
}
