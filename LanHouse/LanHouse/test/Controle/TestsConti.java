/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import junit.framework.TestCase;

/**
 *
 * @author GCD
 */
public class TestsConti extends TestCase {
    
    public TestsConti(String testName) {
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
     * Test of validarAcesso method, of class GerenciarAcesso.
     */
    public void testValidarAcesso() {
        System.out.println("validarAcesso");
        String login = "";
        String senha = "";
        String expResult = "invalido";
        String result = GerenciarAcesso.validarAcesso(login, senha);
        assertEquals(expResult, result);
        // -----
       
        login = "conti";
        senha = "";
        expResult = "invalido";
        result = GerenciarAcesso.validarAcesso(login, senha);
        assertEquals(expResult, result);
        // -----
        login = "";
        senha = "1234";
        expResult = "invalido";
        result = GerenciarAcesso.validarAcesso(login, senha);
        assertEquals(expResult, result);
        // -----
        login = "conti";
        senha = "1234";
        expResult = "propri";
        result = GerenciarAcesso.validarAcesso(login, senha);
        assertEquals(expResult, result);
        // -----
        login = "conti";
        senha = "12";
        expResult = "invalido";
        result = GerenciarAcesso.validarAcesso(login, senha);
        assertEquals(expResult, result);
        // -----
        login = "conti";
        senha = "123456";
        expResult = "invalido";
        result = GerenciarAcesso.validarAcesso(login, senha);
        assertEquals(expResult, result);
        // -----
        login = "conti12";
        senha = "12";
        expResult = "invalido";
        result = GerenciarAcesso.validarAcesso(login, senha);
        assertEquals(expResult, result);
        // -----
        
    }
}
