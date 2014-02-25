/*
    Copyright 2001 Telefnica I+D. All rights reserved
  */
package icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones;


//import icaro.infraestructura.entidadesBasicas.componentesBasicos.automata.factoriaEInterfaces.imp.XMLParserTablaAutomataSinAcciones;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones.imp.*;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFsinAcciones.imp.*;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFsinAcciones.ItfUsoAutomataEFsinAcciones;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas.TablaEstadosAutomataEFinputObjts;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas.TransicionAutomataEF;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.gestorAcciones.ItfGestorAcciones;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.ItfUsoRecursoTrazas;
import java.io.Serializable;

import org.apache.log4j.Logger;


/**
 *  Clase que define autmatas de estados finitos, sin acciones semanticas a ejecutar en las transiciones, 
 *  escritos en XML
 *
 *@author     Francisco J Garijo 
 *@modified  02 de Marzo de 2014
 */

public abstract class InterpreteAutomataEFconGestAcciones implements ItfAutomataEFconGestAcciones,Serializable{
	
	
//	public static final String ESTADO_CREADO = "creado";
//	public static final String ESTADO_ARRANCADO = "arrancado";
//	public static final String ESTADO_FALLO_ARRANQUE = "falloArranque";
//	public static final String ESTADO_ERROR = "error";
//	public static final String ESTADO_ACTIVO = "activo";
//	public static final String ESTADO_FALLO_TEMPORAL = "falloTemporal";
//	public static final String ESTADO_PARADO = "parado";
//	public static final String ESTADO_TERMINANDO = "terminando";
//	public static final String ESTADO_TERMINADO = "terminado";

	/**
	 * Indica si se deben mostrar mensajes de depuracin o no
	 * @uml.property  name="dEBUG"
	 */
	public boolean DEBUG = false;

	/**
	 * Controla la profundidad de las trazas
	 * @uml.property  name="traza"
	 */
	protected int traza = 0;

	/**
	 * @uml.property  name="estadoActual"
	 */
	private String estadoActual;

	/**
	 * Tabla que representa los estados del autmata
	 * @uml.property  name="interpTablaEstados"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
//	private InterpreteTablaEstadosAutomataSinAcciones interpTablaEstados;
    private TablaEstadosAutomataEFinputObjts tablaEstadosAutomata;
	/**
	 *  No se muestra traza
	 */
	public final static int NIVEL_TRAZA_DESACTIVADO = 0;
	/**
	 *  Slo se muestra una indicacin cuando existe una transicin de estados
	 */
	public final static int NIVEL_TRAZA_SOLO_TRANSICIONES = 1;
	/**
	 *  Se muestra todo lo posible en la traza
	 */
	public final static int NIVEL_TRAZA_TODO = 2;

	/**
	 * @uml.property  name="logger"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private transient Logger logger = Logger.getRootLogger();
	
	/**
	 * @uml.property  name="trazas"
	 * @uml.associationEnd  readOnly="true"
	 */
	protected ItfUsoRecursoTrazas trazas;
	

	/**
	 *  Crea un autmata XML
	 *
	 *@param  NombreFicheroDescriptor  Nombre del fichero que contiene el autmata
	 *@param  nivelTraza               Profundidad de las trazas (usar constantes
	 *      definidas estticas en esta clase)
	 */
	public InterpreteAutomataEFconGestAcciones(TablaEstadosAutomataEFinputObjts intpTablaEstados, ItfGestorAcciones itfGestAcciones,Boolean activarTrazas)
	{

    //    XMLParserTablaAutomataSinAcciones parser = new XMLParserTablaAutomataSinAcciones();
		//String ruta = tid.tecHabla.agentes.componentes.infraestructura.configuracion.Configuracion.obtenerParametro("RUTA_FICHEROS_DEFINICION_AUTOMATAS");

	//	interpTablaEstados = parser.extraeTablaEstadosDesdeFicheroXML(NombreFicheroDescriptor);

		// colocamos el autmata en el estado inicial
		this.tablaEstadosAutomata =intpTablaEstados;
        estadoActual = tablaEstadosAutomata.dameEstadoInicial();

		// actualizamos el DEBUG para las trazas
//		if (nivelTraza == 2)
//			DEBUG = true;
//		else
//			DEBUG = false;
//
//		traza = nivelTraza;
//        String NombreFicheroDescriptor = interpTablaEstados.dameFicheroDefEstados();

//		logger.debug("Usando el automata de ciclo de vida del fichero: " + NombreFicheroDescriptor);
//		logger.debug(this.toString());
		/*try{
	    	trazas = (ItfUsoRecursoTrazas)RepositorioInterfaces.instance().obtenerInterfaz(
	    			NombresPredefinidos.ITF_USO+NombresPredefinidos.RECURSO_TRAZAS);
	    }catch(Exception e){
	    	System.out.println("No se pudo usar el recurso de trazas");
	    }*/
	}


	/**
	 *  Dice si el automata se encuentra en un estado final o no
	 *
	 *@return    est en estado final o no
	 */
	public boolean esEstadoFinal()
	{
		return (tablaEstadosAutomata.esEstadoFinal(estadoActual));
	}


	/**
	 *  Admite un input y lo procesa segun la tabla de estados
	 *
	 *@param  input  Input a procesar
	 */
	public boolean transita(String input)
	{
		String siguiente;
		// comprobar que es un input reconocido por el estado actual
		if (tablaEstadosAutomata.esInputValidoDeEstado(estadoActual, input))
		{
			siguiente = tablaEstadosAutomata.dameEstadoSiguiente(estadoActual, input);
			// cambiar al siguiente estado
//			logger.info("Transicion en el ciclo de vida usando input:" + input + "  :" + estadoActual + " -> " + siguiente);
			cambiaEstado(siguiente);
                        return true;
		}
		
		
			logger.info("AVISO: Input de ciclo de vida ignorado.El input: " + input + " no pertenece a los inputs vlidos para el estado actual: " + estadoActual);
			/*trazas.aceptaNuevaTraza(new InfoTraza("AutomataCicloVidaRecurso",
					"AVISO: Input de ciclo de vida ignorado.El input: " + input + " no pertenece a los inputs vlidos para el estado actual: " + estadoActual,
					InfoTraza.NivelTraza.info));*/
		return false;

	}

public void interpretarTransicion(TransicionAutomataEF transicion){
    // le manda la accion  al ejecutor de acciones para que ejecute y transita al estado que indica la transicion
        this.itfGestAcciones.
    
}

	/**
	 *  Imprime la tabla de estados y el estado actual del autmata
	 *
	 *@return    Cadena con la informacin
	 */
	public String toString()
	{
		String dev = tablaEstadosAutomata.toString();
		dev += "\nEstado actual= " + estadoActual;
		return dev;
	}


	/**
	 *  Devuelve el autmata a su estado inicial
	 */
	public void volverAEstadoInicial()
	{
		this.cambiaEstado(this.tablaEstadosAutomata.dameEstadoInicial());
	}


	/**
	 *  Cambia el estado interno del autmata
	 *
	 *@param  estado  Estado del automata al que cambiamos
	 */
	private void cambiaEstado(String estado)
	{
		estadoActual = estado;
	}

	/**
	 * 	Dice si el recurso est en estado activo, es decir, que puede
	 *  ejecutar mtodos
	 * 
	 * @return est en estado activo o no
	 */
	public boolean estadoActivo(){
		return this.estadoActual.equals("activo");
	}

	/**
	 * Dice el estado del autmata en el que se encuentra el recurso
	 * @return el estado en que se encuentra
	 */
	public String estadoActual(){
		return this.estadoActual;
	}
        public void setTrazas(ItfUsoRecursoTrazas itftrazas){
            this.trazas = itftrazas;
            
        }
	/**
	 *  Programa de pruebas del componente
	 *
	 *@param  args  The command line arguments
	 */
	/*
	public static void main(String[] args)
	{
		// ejemplo de uso del automata de control

		// 1. Creo las acciones semnticas
		tid.tecHabla.agentes.componentes.infraestructura.automata.AutomataControl.funciones fun = new tid.tecHabla.agentes.componentes.infraestructura.automata.AutomataControl.funciones();
		// 1.5 Creo objeto de acciones semnticas
		AccionesSemanticas as = new AccionesSemanticas(fun);

		// 2. Creo el autmata
		AutomataControl automata = new AutomataControl("TablaEstadosPruebaDeTablaEstados.xml", as, tid.tecHabla.agentes.componentes.infraestructura.automata.AutomataControl.NIVEL_TRAZA_TODO);

		// 3. Tengo el autmata disponible para usar

		automata.toString();
		automata.procesaInput("inicia");
		automata.procesaInput("inputU");

		automata.toString();
	}
	*/

	/**
	 *  Clase de pruebas para el componente, slo para pruebas de funcionamiento
	 *  bsicas
	 *
	 *@author     Jorge Gonzlez
	 *@created    26 de septiembre de 2001
	 */
	public static class funciones {
		/**
		 */
		public void accionU()
		{
			System.out.println(">> Comienzo a ejecutar mtodo accionU()");
		}


		/**
		 *  Mtodo del usuario del componente
		 */
		public void inicial()
		{
			System.out.println(">> Comienzo a ejecutar metodo inicial()");
			boolean repite = true;
			while (repite)
			{
			}
		}

	}
	/**
	 * @param logger
	 * @uml.property  name="logger"
	 */
	public void setLogger(Logger logger){
		this.logger = logger;
	}
	/**
	 * @return
	 * @uml.property  name="logger"
	 */
	public Logger getLogger(){
		return logger;
	}

}
