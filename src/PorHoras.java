/**
 * La clase PorHoras amplia la clase Artesano e incluye aquellos trabajadores con horas diferentes a 8 o 4
 *
 */
public class PorHoras extends Artesano
{
    /**
     * Constructor para objetos de la clase PorHoras
     */
    public PorHoras(String n, String apell, int ID, int horas) {
        super(n, apell, ID, horas);  
        modificarHoras(horas);
        setJornada(horas);
    }
}














