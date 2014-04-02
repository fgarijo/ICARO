/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package icaro.infraestructura.patronAgenteReactivo.factoriaEInterfaces.imp;

import icaro.infraestructura.entidadesBasicas.comunicacion.EventoRecAgte;
import icaro.infraestructura.entidadesBasicas.descEntidadesOrganizacion.DescInstanciaAgente;
import icaro.infraestructura.patronAgenteReactivo.factoriaEInterfaces.AgenteReactivoAbstracto;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author FGarijo
 */
public class FactoriaAgenteReactivoInputObjImp0NGTest {
    
    public FactoriaAgenteReactivoInputObjImp0NGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of instance method, of class FactoriaAgenteReactivoInputObjImp0.
     */
    @Test
    public void testInstance() throws Exception {
        System.out.println("instance");
//        FactoriaAgenteReactivoInputObjImp0 expResult = null;
//        FactoriaAgenteReactivoInputObjImp0 result = FactoriaAgenteReactivoInputObjImp0.instance();
//        assertEquals(result, expResult);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of crearAgenteReactivo method, of class FactoriaAgenteReactivoInputObjImp0.
     */
    @Test
    public void testCrearAgenteReactivo_DescInstanciaAgente() throws Exception {
        System.out.println("crearAgenteReactivo");
//        DescInstanciaAgente descInstanciaAgente = null;
//        FactoriaAgenteReactivoInputObjImp0 instance = new FactoriaAgenteReactivoInputObjImp0();
//        instance.crearAgenteReactivo(descInstanciaAgente);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crearAgenteReactivo method, of class FactoriaAgenteReactivoInputObjImp0.
     */
    @Test
    public void testCrearAgenteReactivo_3args() {
        System.out.println("crearAgenteReactivo");
        String rutaTabla = "/icaro/pruebas/automataPruebas.xml";
             String rutaAcciones = "icaro.pruebas";
             String agenteId = "Iniciador";
             String origen = "prueba1";
        FactoriaAgenteReactivoInputObjImp0 instance = new FactoriaAgenteReactivoInputObjImp0();
        AgenteReactivoAbstracto expResult = null;
        
        AgenteReactivoAbstracto result = instance.crearAgenteReactivo(rutaTabla, rutaAcciones, agenteId);
        EventoRecAgte eventoPrueba = new EventoRecAgte("comenzar", origen,agenteId);
        try {
            result.aceptaEvento(eventoPrueba);
        } catch (RemoteException ex) {
            Logger.getLogger(FactoriaAgenteReactivoInputObjImp0NGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertEquals(result.getEstado(), expResult) ;
     
            fail("The test case is a prototype.");
        
    }

    /**
     * Test of setParametrosLoggerAgReactivo method, of class FactoriaAgenteReactivoInputObjImp0.
     */
    @Test
    public void testSetParametrosLoggerAgReactivo() {
        System.out.println("setParametrosLoggerAgReactivo");
        String archivoLog = "";
        String nivelLog = "";
        FactoriaAgenteReactivoInputObjImp0 instance = new FactoriaAgenteReactivoInputObjImp0();
        instance.setParametrosLoggerAgReactivo(archivoLog, nivelLog);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class FactoriaAgenteReactivoInputObjImp0.
     */
    @Test
    public void testMain() {
        System.out.println("main");
//        String[] args = null;
//        FactoriaAgenteReactivoInputObjImp0.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}