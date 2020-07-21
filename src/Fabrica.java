import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * La fábrica crea los departamentos que componen la fábrica e inicia la app.
 * 
 * @author Ana Belen
 * @version 1
 */
public class Fabrica
{   
    /**
     * Inicia la consola
     */
    public static void main (String[] args) {
        GestionUsuariosI gestorUser = new GestionUsuarios();
        DptoProduccionI dptProduccion = new DptoProduccion();
        DptoComercialI dptComercial = new DptoComercial(dptProduccion);
        Consola consola = new Consola(gestorUser, dptComercial, dptProduccion);        
        consola.startApp();
        consola.showLogin();    
    }

}
