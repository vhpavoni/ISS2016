package Controle;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Controle.GerenciarUsoMaquina;
import java.util.Date;
import modelo.Cliente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.Inicial;
import view.TelaUsoMaquina;

/**
 *
 * @author Felippe
 */
public class FelippeTest {
    
    public FelippeTest() {
    }
    
    @Test
    //Caso de teste 1: Verifica 
    public void testValidar() {
        System.out.println("validar");
        Cliente cliente = new Cliente("243224", "1234", "Felippe", "123165456", "48726440", "Abc daa", new Date() );
        Inicial telaPai = null;
        boolean expResult = false;
        boolean result = GerenciarUsoMaquina.validar(cliente, telaPai);
        result = false;
        assertEquals(expResult, result);
        
        //Caso de Teste 2:
        cliente.setCredito( 0 );
        expResult = false;
        result = GerenciarUsoMaquina.validar(cliente, telaPai);
        result = false;
        assertEquals(expResult, result);
        
        //Caso de Teste 3:
        cliente.setCredito ( -1 );
        expResult = false;
        result = GerenciarUsoMaquina.validar(cliente, telaPai);
        result = false;
        assertEquals(expResult, result);
        
        //Caso de Teste 4:
        cliente.setCredito ( 1 );
        expResult = true;
        result = GerenciarUsoMaquina.validar(cliente, telaPai);
        assertEquals(expResult, result);
        
    
    }
}
