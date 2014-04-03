/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.infraestructura.entidadesBasicas.componentesBasicos.automatas.automataEFconGesAcciones.imp;

import junit.framework.TestCase;
import org.apache.log4j.Logger;

/**
 *
 * @author FGarijo
 */
public class InterpreteAutomataEFconGestAccioneslImpTest extends TestCase {
    
    public InterpreteAutomataEFconGestAccioneslImpTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of ejecutarTransicion method, of class InterpreteAutomataEFconGestAccioneslImp.
     */
    public void testEjecutarTransicion() {
        System.out.println("ejecutarTransicion con el automata definido en ");
        Object input = null;
        Object[] params = null;
        InterpreteAutomataEFconGestAccionesImp instance = null;
        boolean expResult = false;
        boolean result = instance.ejecutarTransicion(input, params);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of transita method, of class InterpreteAutomataEFconGestAccioneslImp.
     */
    public void testTransita() {
        System.out.println("transita");
        Object input = null;
        InterpreteAutomataEFconGestAccionesImp instance = null;
        boolean expResult = false;
        boolean result = instance.transita(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of estasEnEstadoFinal method, of class InterpreteAutomataEFconGestAccioneslImp.
     */
    public void testEstasEnEstadoFinal() {
        System.out.println("estasEnEstadoFinal");
        InterpreteAutomataEFconGestAccionesImp instance = null;
        boolean expResult = false;
        boolean result = instance.estasEnEstadoFinal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class InterpreteAutomataEFconGestAccioneslImp.
     */
    public void testToString() {
        System.out.println("toString");
        InterpreteAutomataEFconGestAccionesImp instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of volverAEstadoInicial method, of class InterpreteAutomataEFconGestAccioneslImp.
     */
    public void testVolverAEstadoInicial() {
        System.out.println("volverAEstadoInicial");
        InterpreteAutomataEFconGestAccionesImp instance = null;
        instance.volverAEstadoInicial();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of estadoActivo method, of class InterpreteAutomataEFconGestAccioneslImp.
     */
    public void testEstadoActivo() {
        System.out.println("estadoActivo");
        InterpreteAutomataEFconGestAccionesImp instance = null;
        boolean expResult = false;
        boolean result = instance.estadoActivo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of estadoActual method, of class InterpreteAutomataEFconGestAccioneslImp.
     */
    public void testEstadoActual() {
        System.out.println("estadoActual");
        InterpreteAutomataEFconGestAccionesImp instance = null;
        String expResult = "";
        String result = instance.estadoActual();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLogger method, of class InterpreteAutomataEFconGestAccioneslImp.
     */
    public void testSetLogger() {
        System.out.println("setLogger");
        Logger logger = null;
        InterpreteAutomataEFconGestAccionesImp instance = null;
        instance.setLogger(logger);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogger method, of class InterpreteAutomataEFconGestAccioneslImp.
     */
    public void testGetLogger() {
        System.out.println("getLogger");
        InterpreteAutomataEFconGestAccionesImp instance = null;
        Logger expResult = null;
        Logger result = instance.getLogger();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
