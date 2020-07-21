
/**
 * Particular contiene los objetos de la clase cliente particular
 * 
 * @author Ana Belen
 * @version 1
 */
public class Particular extends Cliente
{
    /**
     * Constructor for objects of class Particular
     */
    public Particular(String n, String apell, int ID, String direccion, int tel, String email)
    {
        super(n, apell, ID, direccion,tel, email);
        modificarCategoria(Categoria.PARTICULAR);
    }
}
