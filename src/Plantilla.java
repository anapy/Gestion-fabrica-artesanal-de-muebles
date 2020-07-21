/**
 * Plantilla es la subclase de Artesano que tienen contrato de media jornada o jornada completa.
 * 
 */
public class Plantilla extends Artesano
{
    /**
     * Constructor para objetos de la clase Plantilla
     */
    public Plantilla(String n, String apell, int ID, int horas) {
        super(n, apell, ID, horas);
        modificarHoras(horas);
        setJornada(horas);
    }
}
