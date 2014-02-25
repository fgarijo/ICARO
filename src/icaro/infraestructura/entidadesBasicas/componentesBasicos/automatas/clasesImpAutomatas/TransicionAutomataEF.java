/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.clasesImpAutomatas;

import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;

/**
 *
 * @author FGarijo
 */
public class TransicionAutomataEF {
    private String identEstadoOrigen;
    private String identEstadoSiguiente;
    private Integer tipoEstadoSiguiente = 2 ; // estado intermedio por defecto
    private String input;
    private String identAccion;
    private Integer tipoTransicion;
    
    /**
     * Crea una transición a partir de los datos extraidos del fichero XML
     * @param idEstadoOrigen 
     * @param input  identificador del input. En el caso de que se procesen objetos input debe indicar el nombre de la clase 
     *               a la que pertenece el input
     * @param idEstadoSiguiente
     * @param idAccion  Nombre de la acción definida en el automata , puede ser null o "vacia"
     * @param modalidadAccion  definida en el automata puede ser ( bloqueante o paralela )
     */
    public  TransicionAutomataEF (String idEstadoOrigen,String input,
           String idEstadoSiguiente,String idAccion, String modalidadAccion){
       this.identEstadoOrigen =  idEstadoOrigen; 
       this.input = input;
       this.identAccion = idAccion;
       this.identEstadoSiguiente=idEstadoSiguiente;
       if (idAccion == null | idAccion.equals(NombresPredefinidos.ACCION_VACIA_AUTOMATA_EF))
           this.tipoTransicion =NombresPredefinidos.TRANSICION_AUTOMATA_EF_SIN_ACCION;
       else if (modalidadAccion.equals(NombresPredefinidos.NOMBRE_MODO_CONCURRENTE_AUTOMATA_EF_SIN_ACCION))
                this.tipoTransicion =NombresPredefinidos.TRANSICION_AUTOMATA_EF_ACCION_CONCUR;
            else this.tipoTransicion =NombresPredefinidos.TRANSICION_AUTOMATA_EF_ACCION_BLOQ;
}
    /**
     * 
     * @return
     */
    public String  getidentEstadoOrigen ( ){
       return this.identEstadoOrigen;
   }
    public void setidentEstadoOrigen ( String idEstadoOrigen){
       this.identEstadoOrigen= idEstadoOrigen;
   }
    /**
     *
     * @return
     */
    public String  getidentEstadoSiguiente ( ){
       return this.identEstadoSiguiente;
   }
    /**
     *
     * @param tipoEst puede ser estado intermedio o estado final . Habría que controlar el tipo
     */
    public void setTipoEstadoSiguiente ( Integer tipoEst){
       this.tipoEstadoSiguiente= tipoEst;
   }
    /**
     *
     * @return
     */
    public Integer  getTipoEstadoSiguiente ( ){
       return this.tipoEstadoSiguiente;
   }
    
   public String  getInput ( ){
       return this.input;
   }
    /**
     *
     * @param input
     */
    public void setInput ( String input){
       this.input= input;
   }
  
//   public void setidentAccion ( String idAccion, String){
//      
//       this.identAccion= idAccion;
//   }
    /**
     *
     * @return
     */
    public String  getidentAccion ( ){
       return this.identAccion;
   }
    /**
     *
     * @return Los tipos de transición estan definidos como Integer en NombresPredefinidos
     */
    public Integer  getTipoTransicion ( ){
       return this.tipoTransicion;
   }
}
