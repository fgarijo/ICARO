/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.gestorAcciones;

import icaro.infraestructura.patronAgenteCognitivo.procesadorObjetivos.gestorTareas.*;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Tarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

/**
 *
 * @author carf
 */
public interface ItfGestorAcciones {
    public Tarea crearTarea(Class clase) throws Exception;
    public TareaSincrona crearTareaSincrona(Class clase) throws Exception;
    public TareaSincrona crearTareaAsincrona(Class clase) throws Exception;
    void ejecutar(Object... params) throws Exception;
}
