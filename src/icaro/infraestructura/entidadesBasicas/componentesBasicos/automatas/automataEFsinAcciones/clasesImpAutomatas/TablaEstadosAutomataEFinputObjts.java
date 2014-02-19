/*
 *  
 */
package icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFsinAcciones.clasesImpAutomatas;

import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;



/**
 *  Description of the Class
 *
 *@author     Francisco J Garijo
 *@created    11 de febrero de 2014
 *@modified   
 */
public class TablaEstadosAutomataEFinputObjts implements Cloneable, Serializable{

	/**
	 * Tabla que almacena si los estados son iniciales / finales / etc, indexa por estado
	 * @uml.property  name="clasificacionEstados"
	 * @uml.associationEnd  qualifier="identificador:java.lang.String java.lang.Integer"
         * Los inputs deben ser objetos de clases conocidas, en los atributos y métodos del input se pueden pasar valores que
         * utilizan las acciones definidas en la tabla
	 */
	private HashMap<String,ParTipoEstadoConjEstados> infoEstados = new HashMap<String,ParTipoEstadoConjEstados>();

	/**
	 * El estado inicial
	 * @uml.property  name="identificadorEstadoInicial"
	 */
//	private String identificadorEstadoInicial = "";

	/**
	 * Tabla de tablas que almacena las tablas input/estado siguiente y las indexa por estado
	 * @uml.property  name="inputsDeEstados"
	 * @uml.associationEnd  qualifier="input:java.lang.String java.lang.String"
	 */
//	private Hashtable<String,Hashtable<String,String>> inputsDeEstados = new Hashtable<String,Hashtable<String,String>>();
        private Hashtable<String,TransicionAutomataEF> tablaTransicionFromEstadoInput = new Hashtable<String,TransicionAutomataEF>();
        private Set<String> identsInputsEstado ;
	/**
	 *  Marca el tipo de estados finales
	 */
	public final static int TIPO_DE_ESTADO_FINAL = 3;

	/**
	 *  Marca el tipo del estado inicial
	 */
	public final static int TIPO_DE_ESTADO_INICIAL = 0;
	/**
	 *  Marca el tipo de estados intermedios
	 */
	public final static int TIPO_DE_ESTADO_INTERMEDIO = 2;

	/**
	 *  Constructor
	 */
	public TablaEstadosAutomataEFinputObjts() { }


	/**
	 *  Devuelve el estado inicial del autmata
	 *
	 *@return    Devuelve el estado inicial del autmata
	 */
	public String dameEstadoInicial()
	{
		return NombresPredefinidos.ESTADO_INICIAL;
	}

	/**
	 *  Devuelve el estado siguiente al estado Qi con el input i PRE: El
	 *  estado actual no es final. El input pertenece a los aceptados por ese
	 *  estado
	 *
	 *@param  estadoActual  Estado en el que se est
	 *@param  input         Input que se ha recibido
	 *@return               estado siguiente
	 */
	public String dameEstadoSiguiente(String estadoActual, String input)
	{
		
		return tablaTransicionFromEstadoInput.get(estadoActual+input).getidentEstadoSiguiente();
	}


	/**
	 *  Comprueba si el estado dado es final
	 *
	 *@param  estado  Estado a evaluar
	 *@return         Devuelve si cuando el estado es final, no e.o.c.
	 */
	public boolean esEstadoFinal(String estado)
	{
		return ( ((infoEstados.get(estado)).getTipoEstado()) == NombresPredefinidos.TIPO_ESTADO_FINAL_AUTOMATA_EF);
	}



	/**
	 *  Determina si el input dado es uno de los inputs que se esperan en el estado
	 *  dado
	 *
	 *@param  estado  Estado a evaluar
	 *@param  input   Input que se desea comprobar
	 *@return         Dice si es vlido o no
	 */
	public boolean esInputValidoDeEstado(String estado, String input)
	{
		
		return (tablaTransicionFromEstadoInput.containsKey(estado+input));
	}


	/**
	 *  Aade un nuevo estado a la tabla
	 *
	 *@param  identificador  Nombre del estado
	 *@param  tipo           Tipo del estado (ver enumerados de esta clase)
	 */
	public void putEstado(String identificador, int tipo)
	{
		// clasificar el estado
//		infoEstados.put(identificador, new ParTipoEstadoConjEstados(tipo, identsInputsEstado));
                if (infoEstados.containsKey(identificador) ){
                    
                } else {
                   Set<String> identsInputsEstado = new TreeSet();
                   if (identsInputsEstado.add(identificador))
                        infoEstados.put(identificador, new ParTipoEstadoConjEstados(tipo, identsInputsEstado));
            }
	//	inputsDeEstados.put(identificador, new Hashtable<String,String>());

		// aceleramos la recuperacin del estado inicial
		
	}

	/**
	 *  Aade una nueva transicion de estados a la tabla
	 *
	 *@param  estado           Estado desde el que parte la transicin
	 *@param  input            Input que activa la transicin
	 *@param  estadoSiguiente  Estado al que se pasa tras ejecutar la transicin
	 */
	public void addTransicion(String estadoOrigen, TransicionAutomataEF transicion) {

		// incorpora una transicion al estado origen
            String input = transicion.getInput();
            if (infoEstados.containsKey(estadoOrigen)){
                identsInputsEstado = infoEstados.get(estadoOrigen).getIdentsInputsEstado();
                
                if (identsInputsEstado.contains(input) ){}
                else {
                    tablaTransicionFromEstadoInput.put(estadoOrigen+input,transicion);
                    identsInputsEstado.add(input);
                }
            }
            else { // no existe el estado origen => se crea 
                identsInputsEstado = new TreeSet<>();
                identsInputsEstado.add(input);
                infoEstados.put(input, new ParTipoEstadoConjEstados(TIPO_DE_ESTADO_INTERMEDIO, identsInputsEstado));
                tablaTransicionFromEstadoInput.put(estadoOrigen+input, transicion);
            }
	}


	/**
	 *  Aade la transicin indicada como parmetro a todos los estados del
	 *  autmata PRE: El autmata debe estar completamente creado ( todos los
	 *  estados aadidos ) NOTA: En caso de existir inputs repetidos, se dejar
	 *  intacto el input ya existente
	 *
	 *@param  input      input de la transicin universal
	 *@param  estadoSig  estado al que llegamos tras la transicin universal
	 */
	public void putTransicionUniversal(String input, String estadoSig)
	{
		// esta operacin tenemos que aadrsela a todos los estados de la tabla
		// hay que tener cuidado de que no se repitan los inputs

		//recorremos todos los estados
		java.util.Iterator iteradorClaves = (infoEstados.keySet()).iterator();
		while (iteradorClaves.hasNext())
		{
			// obtenemos el siguiente estado
			String estadoPivote = (String) iteradorClaves.next();
			// aadimos la transicin si no es estado final y el input no estaba en ese estado
			if ((!esEstadoFinal(estadoPivote)) && (!esInputValidoDeEstado(estadoPivote, input)))
			{
				addTransicion(estadoPivote, input,NombresPredefinidos.ACCION_VACIA_AUTOMATA_EF, estadoSig);
			}
		}

	}
	/**
	 *  Expresa la tabla en texto
	 *
	 *@return    Texto con la tabla de estados
	 */
	public String toString()
	{
		String dev = "LEYENDA:   Estado: input -> estado siguiente";
		dev += "\n------------------------------------------------------";
//		java.util.Collection<String> claves = (java.util.TreeSet)tablaTransicionFromEstadoInput.keySet();
           
                java.util.Iterator claves = ((java.util.TreeSet)tablaTransicionFromEstadoInput.keySet()).iterator();

		String input = "";
		String estsig = "";
		String id = "";
                TransicionAutomataEF transicion =null;
		while (claves.hasNext())
		{
		 id = (String) claves.next();
                 transicion = tablaTransicionFromEstadoInput.get(id);
                if (transicion!=null)
                    dev += "\n" + id + ": Transicion : estado : " +transicion.getidentEstadoOrigen() + " accion : " + transicion.getidentAcction()+ " -> "+transicion.getidentEstadoSiguiente();
                else dev += "\n" + id + ": Transicion : null";
							
			}

		return dev += "\n------------------------------------------------------";
	}
    public Object clone(){
        Object obj=null;
        try{
            obj=super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
private class  ParTipoEstadoConjEstados{
    public String identEstadoOrigen;
    public Set<String> identsInputsEstado ;
    public Integer tipoEstado = 2 ; // estado intermedio por defecto
    
    public ParTipoEstadoConjEstados (Integer tipoEstado,Set<String> idsInputEstado){
        this.tipoEstado=tipoEstado;
        this.identsInputsEstado= idsInputEstado;
    }
    public Set<String> getIdentsInputsEstado(){
        return this.identsInputsEstado;
    }
    public boolean addInput (String inputId){
         return( this.identsInputsEstado.add(inputId));
    }
    public boolean setTipoEstado(Integer tipoEstado){
        if(tipoEstado >0 & tipoEstado>=3) return true;
        else return false;
    }
    public Integer getTipoEstado(){
        return this.tipoEstado;
    }
}
}
