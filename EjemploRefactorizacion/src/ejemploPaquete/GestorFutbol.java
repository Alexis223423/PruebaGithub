package ejemploPaquete;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class GestorFutbol.
 */
public class GestorFutbol implements Comparable<GestorFutbol> {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(GestorFutbol.class.getName());

    /** The equipo nombre. */
    // Atributos del equipo
    private String equipoNombre;       // Nombre del equipo
    
    /**
     * Gets the equipo nombre.
     *
     * @return the equipo nombre
     */
    public String getEquipoNombre() {
		return equipoNombre;
	}

	/**
	 * Sets the equipo nombre.
	 *
	 * @param equipoNombre the new equipo nombre
	 */
	public void setEquipoNombre(String equipoNombre) {
		this.equipoNombre = equipoNombre;
	}
	
    /** The puntos. */
    private int puntos;                // Puntos acumulados por el equipo
    
	/**
	 * Gets the puntos.
	 *
	 * @return the puntos
	 */
	public int getPuntos() {
		return puntos;
	}

	/**
	 * Sets the puntos.
	 *
	 * @param puntos the new puntos
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	/** The partidos totales. */
	// Variable estática para contar los partidos jugados en total
    private static int partidosTotales = 0;
    
    /**
     * Gets the partidos totales.
     *
     * @return the partidos totales
     */
    public static int getPartidosTotales() {
		return partidosTotales;
	}

	/**
	 * Sets the partidos totales.
	 *
	 * @param partidosTotales the new partidos totales
	 */
	public static void setPartidosTotales(int partidosTotales) {
		GestorFutbol.partidosTotales = partidosTotales;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
        // Se crea una instancia del equipo principal con su nombre
        GestorFutbol equipoPrincipal = new GestorFutbol("Atlético Madrid");

        // Lista de resultados de partidos durante la temporada
        List<String> resultadosTemporada = Arrays.asList(
            "victoria local", "empate visitante", "derrota local",
            "victoria visitante!", "empate", "victoria!",
            "derrota", "empate local", "victoria local"
        );

        // Procesar los resultados y calcular puntos
        equipoPrincipal.procesarTemporada(resultadosTemporada);

        // Verificación de si hay resultados y salida del programa si se cumple
        if (!resultadosTemporada.isEmpty()) {
            System.exit(1);
        }

        // Se crea otro equipo para comparar con el principal
        GestorFutbol otroEquipo = new GestorFutbol("Real Madrid");

        // Comparación entre dos equipos (por nombre)
        logger.log(Level.INFO,"Comparación entre equipos: {0}", equipoPrincipal.compareTo(otroEquipo));
    }

    /**
     * Instantiates a new gestor futbol.
     *
     * @param nombreEquipo the nombre equipo
     */
    // Constructor que inicializa el equipo con su nombre y puntos en 0
    public GestorFutbol(String nombreEquipo) {
        this.equipoNombre = nombreEquipo;
        this.puntos = 0;
    }

    /**
     * Procesar temporada.
     *
     * @param resultados the resultados
     */
    // Procesa la lista de resultados y actualiza los puntos del equipo
    public void procesarTemporada(List<String> resultados) {
        for (String resultado : resultados) {

            // Se suman los puntos según el tipo de resultado
            sumapuntos(resultado);

            // Se muestra si el partido fue como local o visitante
            tipopartido(resultado);

            // Clasifica el resultado según su longitud
            switchresultados(resultado);

            // Detecta si el resultado tiene un signo de énfasis (!)
            if (resultado.endsWith("!")) {
            	logger.info("¡Resultado enfatizado!");
            }

            // Separador visual entre partidos
            logger.info("----------------------");
        }
    }

	/**
	 * Tipopartido.
	 *
	 * @param resultado the resultado
	 */
	private void tipopartido(String resultado) {
		if (resultado.contains("local")) {
			logger.log(Level.INFO,"Jugado como local.");
		    if (resultado.length() > 10) {
		    	logger.log(Level.INFO,"Detalle adicional: {0}", resultado);
		    }
		} else if (resultado.contains("visitante")) {
			logger.info("Jugado como visitante.");
		    if (resultado.length() > 8) {
		    	logger.log(Level.INFO,"Comentario: {0}", resultado);
		    }
		}
	}

	/**
	 * Sumapuntos.
	 *
	 * @param resultado the resultado
	 */
	private void sumapuntos(String resultado) {
		if (resultado.equals("victoria")) {
		    puntos += 3;
		    logger.log(Level.INFO,"Victoria. Puntos acumulados: {0}", puntos);
		} else if (resultado.equals("empate")) {
		    puntos += 1;
		    logger.log(Level.INFO,"Empate. Puntos acumulados: {0}", puntos);
		} else if (resultado.equals("derrota")) {
			logger.log(Level.INFO,"Derrota. Puntos acumulados: {0}", puntos);
		}
	}

	/**
	 * Switchresultados.
	 *
	 * @param resultado the resultado
	 */
	private void switchresultados(String resultado) {
		switch (resultado.length()) {
		    case 7:
		    	logger.info("Resultado corto.");
		        break;
		    case 14:
		    	logger.info("Resultado medio.");
		        break;
		    default:
		    	logger.info("Resultado de longitud estándar.");
		        break;
		}
	}

    /**
     * Equals.
     *
     * @param obj the obj
     * @return true, if successful
     */
    // Método para comparar si dos objetos GestorFutbol representan el mismo equipo
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GestorFutbol)) return false;
        GestorFutbol otro = (GestorFutbol) obj;
        return this.equipoNombre.equals(otro.equipoNombre);
    }
    
    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
		return puntos;
        
    }

    /**
     * Instantiates a new gestor futbol.
     *
     * @param original the original
     */
    // Constructor de copia
    public GestorFutbol(GestorFutbol original) {
        this.equipoNombre = original.equipoNombre;
       
    }

    /**
     * Compare to.
     *
     * @param otro the otro
     * @return the int
     */
    // Compara dos objetos GestorFutbol por su nombre de equipo
    @Override
    public int compareTo(GestorFutbol otro) {
        if (this.equipoNombre == null || otro.equipoNombre == null) {
            return -1;
        }
        return this.equipoNombre.compareTo(otro.equipoNombre);
    }
}
