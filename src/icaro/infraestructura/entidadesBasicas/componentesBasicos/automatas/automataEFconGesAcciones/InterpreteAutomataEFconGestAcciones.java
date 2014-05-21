/*
    
  */
package icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones;


//import icaro.infraestructura.entidadesBasicas.componentesBasicos.automata.factoriaEInterfaces.imp.XMLParserTablaAutomataSinAcciones;
import icaro.infraestructura.entidadesBasicas.InfoTraza.NivelTraza;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas.TablaEstadosAutomataEFinputObjts;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas.TransicionAutomataEF;
import icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.gestorAcciones.ItfGestorAcciones;
import icaro.infraestructura.patronAgenteReactivo.control.AutomataEFE.ItfUsoAutomataEFE;
import icaro.infraestructura.patronAgenteReactivo.control.acciones.AccionesSemanticasAgenteReactivo;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.ItfUsoRecursoTrazas;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.ClaseGeneradoraRecursoTrazas;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.logging.Level;

import org.apache.log4j.Logger;


/**
 *  Clase que define automatas de estados finitos, sin acciones semanticas a ejecutar en las transiciones, 
 *  escritos en XML
 *
 *@author     Francisco J Garijo 
 *@modified  02 de Marzo de 2014
 */

//public abstract class InterpreteAutomataEFconGestAcciones implements ItfAutomataEFconGestAcciones,Serializable{
public abstract class InterpreteAutomataEFconGestAcciones implements ItfUsoAutomataEFE,Serializable{
	
	
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
	protected String estadoActual;

	/**
	 * Tabla que representa los estados del autmata
	 * @uml.property  name="interpTablaEstados"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
//	private InterpreteTablaEstadosAutomataSinAcciones interpTablaEstados;
    protected TablaEstadosAutomataEFinputObjts tablaEstadosAutomata;
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
	public ItfUsoRecursoTrazas trazas;
        protected ItfGestorAcciones itfGestAcciones;
	

	/**
	 *  Crea un autmata XML
	 *
	 *@param  NombreFicheroDescriptor  Nombre del fichero que contiene el autmata
	 *@param  nivelTraza               Profundidad de las trazas (usar constantes
	 *      definidas estticas en esta clase)
	 */
	public InterpreteAutomataEFconGestAcciones(TablaEstadosAutomataEFinputObjts intpTablaEstados, Boolean activarTrazas)
	{

    //    XMLParserTablaAutomataSinAcciones parser = new XMLParserTablaAutomataSinAcciones();
		//String ruta = tid.tecHabla.agentes.componentes.infraestructura.configuracion.Configuracion.obtenerParametro("RUTA_FICHEROS_DEFINICION_AUTOMATAS");

	//	interpTablaEstados = parser.extraeTablaEstadosDesdeFicheroXML(NombreFicheroDescriptor);

		// colocamos el autmata en el estado inicial
		this.tablaEstadosAutomata =intpTablaEstados;
                estadoActual = tablaEstadosAutomata.dameEstadoInicial();
                if (NombresPredefinidos.RECURSO_TRAZAS_OBJ==null){
                    try {
                        trazas = ClaseGeneradoraRecursoTrazas.instance();
                        NombresPredefinidos.RECURSO_TRAZAS_OBJ = trazas;
                    } catch (RemoteException ex) {
                        java.util.logging.Logger.getLogger(InterpreteAutomataEFconGestAcciones.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;
//                itfGestAcciones = gestAccionesitf;

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
	public boolean esEstadoFinal(String estadoId)
	{
		return (tablaEstadosAutomata.esEstadoFinal(estadoId));
	}


	/**
	 *  Admite un input y lo procesa segun la tabla de estados
	 *
	 *@param  input  Input a procesar
	 */
       
//        @Override
	public abstract boolean ejecutarTransicion(Object input, Object... params);
//            
          // Si el input es de tipo String Tomamos como input la cadena que representa el input
                
//            }else {// tomamos como input el nombre de la clase del input
//                
//            }
////           if( tablaEstadosAutomata.esInputValidoDeEstado(estadoActual, inputAutomata)){
//               TransicionAutomataEF transicion = tablaEstadosAutomata.getTransicion(estadoActual()+inputAutomata);
//               if (transicion == null){
//                   // mensaje de error el inputno es valido en el estado actual 
//           }else {
//                   // obtenemos el tipo de transción ************* Revisar
////                    if (tipoTransicion== NombresPredefinidos.TRANSICION_AUTOMATA_EF_SIN_ACCION){
////                        this.estadoActual = transita(input);
////                     }else {    
////                            if (transicion.getTipoTransicion()== NombresPredefinidos.TRANSICION_AUTOMATA_EF_ACCION_BLOQ)
////                            this.itfGestAcciones.crearAccionAsincrona(null)
//            }
//         return true;      
//        }
        public boolean ejecutarTransicion(Object input){
            
            String inputAutomata ;
           
            if( input instanceof String ){ // Si el input es de tipo String Tomamos como input la cadena que representa el input
                inputAutomata = (String)input;
            }else {// tomamos como input el nombre de la clase del input
                inputAutomata = input.getClass().getSimpleName();
            }
           if( !tablaEstadosAutomata.esInputValidoDeEstado(estadoActual, inputAutomata)){
               this.trazas.trazar (this.getClass().getSimpleName()," El input : " + inputAutomata + " No coincide con los inputs definidos en el automata "+
                       " en el estado  :" + estadoActual ,InfoTraza.NivelTraza.error );
               return false;
           }
           TransicionAutomataEF transicion = tablaEstadosAutomata.getTransicion(estadoActual()+inputAutomata);
           if (transicion == null){
                   // mensaje de error el inputno es valido en el estado actual
                    this.trazas.trazar (this.getClass().getSimpleName()," No existe transicion asociada al input : " + inputAutomata +
                       " en el estado  :" + estadoActual ,InfoTraza.NivelTraza.error );
                    return false;
            }
                   // obtenemos el tipo de transción ************* Revisar
             Integer tipoTransicion = transicion.getTipoTransicion();
             if (tipoTransicion== NombresPredefinidos.AUTOMATA_EF_TIPO_TRANSICION_SIN_ACCION){
                 this.estadoActual =transicion.getidentEstadoSiguiente();
                 return true;
              }  
              Class claseAEjecutar =  transicion.getClaseAccion();
//               if (claseAEjecutar.getSimpleName().startsWith(NombresPredefinidos.NOMBRE_ACCIONES_SEMANTICAS) ){
                    if (tipoTransicion== NombresPredefinidos.AUTOMATA_EF_TIPO_TRANSICION_METODO_AS_BLOQ)
                       try {
                            this.itfGestAcciones.ejecutarMetodo(claseAEjecutar, transicion.getIdentMetodoAccion(), (Object)null);
                            this.estadoActual =transicion.getidentEstadoSiguiente();
                            return true; 
                        } catch (Exception ex) {
                           java.util.logging.Logger.getLogger(InterpreteAutomataEFconGestAcciones.class.getName()).log(Level.SEVERE, null, ex);
                           return false;
                           }
                    else if (tipoTransicion== NombresPredefinidos.AUTOMATA_EF_TIPO_TRANSICION_METODO_AS_CONCURR){// ejecutar el metodo como thread 
                                 return false; // por no estar implementado
                                }
//                        else if (tipoTransicion== NombresPredefinidos.AUTOMATA_EF_TIPO_TRANSICION_METODO_AS_BLOQ)                        
                    else try {
                               this.itfGestAcciones.ejecutarAccion(claseAEjecutar, input);
                               this.estadoActual =transicion.getidentEstadoSiguiente();
                               return true; 
                             } catch (Exception ex) {
                                java.util.logging.Logger.getLogger(InterpreteAutomataEFconGestAcciones.class.getName()).log(Level.SEVERE, null, ex);
                                return false;
                                    }
        }
        
        @Override
        public void transita(String input){
                ejecutarTransicion(input);
//	{
//		String siguiente;
//		// comprobar que es un input reconocido por el estado actual
//		if (tablaEstadosAutomata.esInputValidoDeEstado(estadoActual, input))
//		{
//			estadoActual = tablaEstadosAutomata.dameEstadoSiguiente(estadoActual, input);
//			// cambiar al siguiente estado
////			logger.info("Transicion en el ciclo de vida usando input:" + input + "  :" + estadoActual + " -> " + siguiente);
//			cambiarEstado(estadoActual);
////                        return siguiente;
//		}
//		
//		
//			logger.info("AVISO: Input de ciclo de vida ignorado.El input: " + input + " no pertenece a los inputs vlidos para el estado actual: " + estadoActual);
			/*trazas.aceptaNuevaTraza(new InfoTraza("AutomataCicloVidaRecurso",
					"AVISO: Input de ciclo de vida ignorado.El input: " + input + " no pertenece a los inputs vlidos para el estado actual: " + estadoActual,
					InfoTraza.NivelTraza.info));*/
//		return estadoActual; // si el input no es valido no cambia de estado

	}

public void interpretarTransicion(TransicionAutomataEF transicion){
    // le manda la accion  al ejecutor de acciones para que ejecute y transita al estado que indica la transicion
    Integer tipoTransicion = transicion.getTipoTransicion();
    String input = transicion.getInput();
    if (tipoTransicion== NombresPredefinidos.AUTOMATA_EF_TIPO_TRANSICION_SIN_ACCION){
         transita(input);
    }else {    // ****** revisar 
//           if (transicion.getTipoTransicion()== NombresPredefinidos.TRANSICION_AUTOMATA_EF_ACCION_BLOQ)
//           this.itfGestAcciones.
            }
}

	/**
	 *  Imprime la tabla de estados y el estado actual del autmata
	 *
	 *@return    Cadena con la informacin
	 */
        @Override
     public boolean procesaInput(Object input){
           String inputAutomata ;
           Object[] params = null;
           
            if( input instanceof String){ // Si el input es de tipo String Tomamos como input la cadena que representa el input
                inputAutomata= (String)input;
            }else {// tomamos como input el nombre de la clase del input y añadimos el objeto como primer parametro
                inputAutomata = input.getClass().getSimpleName();
                params[0]= input;
            } 
          return ejecutarTransicion( inputAutomata);
      }
        @Override
     public  boolean procesaInput(String input, Object[] parametros){
         return ejecutarTransicion( input, parametros);
     }
  
           
        @Override
  public  boolean procesaInputObj(Object input, Object[] parametros){
      return ejecutarTransicion( input, (Object[]) parametros);
  }
        @Override
	public String toString()
	{
		String dev = tablaEstadosAutomata.toString();
		dev += "\nEstado actual= " + estadoActual;
		return dev;
	}


	/**
	 *  Devuelve el autmata a su estado inicial
	 */
        @Override
	public void volverAEstadoInicial()
	{
		this.cambiarEstado(this.tablaEstadosAutomata.dameEstadoInicial());
	}


	/**
	 *  Cambia el estado interno del autmata
	 *
	 *@param  estado  Estado del automata al que cambiamos
	 */
	public void cambiarEstado(String nuevoEstado)
	{
        if (!estadoActual.equals(nuevoEstado)){ 
            if (tablaEstadosAutomata.esEstadoValido(nuevoEstado))	
                estadoActual = nuevoEstado;
            else {
                trazas = NombresPredefinidos.RECURSO_TRAZAS_OBJ;
                if (trazas!= null)
                trazas.trazar(this.getClass().getSimpleName(), "El estado al que se debe transitar : "+nuevoEstado
                    +"  No es un estado valido del Automata. Revisar la tabla de estado y/o el identificador del estado ",
                    InfoTraza.NivelTraza.error);
                else 
                    System.out.println(this.getClass().getSimpleName()+ "  El estado al que se debe transitar : "+nuevoEstado
                    +"  No es un estado valido del Automata. Revisar la tabla de estado y/o el identificador del estado ");
        }
        }
	}

	/**
	 * 	Dice si el recurso est en estado activo, es decir, que puede
	 *  ejecutar mtodos
	 * 
	 * @return est en estado activo o no
	 */
//        @Override
	public boolean estadoActivo(){
		return this.estadoActual.equals("activo");
	}

	/**
	 * Dice el estado del autmata en el que se encuentra el recurso
	 * @return el estado en que se encuentra
	 */
//        @Override
	public String estadoActual(){
		return this.estadoActual;
	}
        public void setTrazas(ItfUsoRecursoTrazas itftrazas){
            this.trazas = itftrazas;
            
        }
         public void setItfGestorAcciones(ItfGestorAcciones gestAccItf){
            this.itfGestAcciones = gestAccItf;
            
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
