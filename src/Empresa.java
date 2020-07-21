
/**
 * La clase empresa contiene los métodos y constructor que expanden los de la clase padre cliente
 * 
 * @Author Ana Belen
 * @version 1
 */
public class Empresa extends Cliente
{
    private String nombreEmpresa;
    /**
     * Constructor de objetos de la clase Empresa
     * En el caso de empresa los parámetros
     * @nombre Nombre del responsable de la empresa
     * @apellido Apellido del responsable de la empresa
     */
    public Empresa(String n, String apell, int ID, String direccion, int tel, String email, String nombreEmpresa)
    {
        super(n, apell, ID, direccion, tel, email);
        this.nombreEmpresa = nombreEmpresa;
        modificarCategoria(Categoria.EMPRESA);
    }
    
    /**
     * Imprime por pantalla los datos de los clientes de tipo Empresa
     */
    public String toString() {
        StringBuilder p = new StringBuilder();
        p.append(super.toString());
        p.append("Nombre de empresa: " + nombreEmpresa + "\n");
        return p.toString();
    }
    
    /**
     * Devuelve el nombre de la empresa 
     */
    private String getNombreEmpresa() {
        return nombreEmpresa;
    }
        
    /**
     * Permite cambiar el nombre de la empresa
     */
    private void modificarNombreEmpresa(String nNombreempresa) {
        this.nombreEmpresa = nNombreempresa;
    }
}