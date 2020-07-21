import java.util.ArrayList;
import java.util.List;

/**
 * Empleado crea la clase padre para construir los diferentes roles de empleados
 * 
 * @author Ana Belén
 * @version 1
 */
public abstract class Empleado extends Persona
{
    //Número de horas trabajadas al dia
    private int horas;
    private Jornada jornada;
    /**
     * Constructor for objects of class Empleado
     */
    public Empleado(String n, String apell, int ID, int horas)
    {
       super(n, apell, ID);
       this.horas = horas;
       setJornada(horas);
       user = "E" + ID;
    }
    
    /**
     * Asocia el número de horas con la jornada a la que se refiere
     */
    public void setJornada(int horas) {
        if(horas == 8){
            this.jornada = Jornada.COMPLETA;
        }else if (horas == 4){
            this.jornada = Jornada.MEDIA; 
        }else {
            this.jornada = Jornada.PORHORAS;
        }
    }
    
    /**
     * Devuelve el tipo de jornada
     */
    public Jornada getJornada(){
        return jornada;
    }
    
    /**
     * Modifica el tipo de jornada
     */
    public void modificarJornada(Jornada njornada){
        this.jornada = njornada;
    }
    
    /**
     * Devuelve el número de horas al día
     */
    public int getHoras(){
        return horas;
    }
    
    /**
     * Modifica el tipo de jornada
     */
    public void modificarHoras(int nuevasHoras){
        this.horas = nuevasHoras;
    }
    
    /**
     * Imprime todos los datos del empleado ampliando el constructor de Persona
     */
    public String toString() {
        StringBuilder e = new StringBuilder();
        e.append(super.toString());
        e.append("Tipo de jornada: " + jornada + " (" + horas + " horas/día)");
        e.append("\nCategoria profesional: " + getCategoria() + "\n");
        return e.toString();
    }
}