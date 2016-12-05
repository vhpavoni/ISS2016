package Controle;

import java.util.GregorianCalendar;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import view.Inicial;

/**
 *
 * @author Pedro
 */
public class PedroTest extends TestCase {
    
    public PedroTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of preencheTabelaUsoMaquina method, of class Inicial.
     */
   

    /**
    /**
     * Test of tempoEntreDatas method, of class Inicial.
     */
    @Test// em segundos
    public void testTempoEntreDatas() {
        System.out.println("tempoEntreDatas");
        GregorianCalendar a = new GregorianCalendar();
        GregorianCalendar b = new GregorianCalendar();
        Inicial instance = new Inicial();
        
        
        long expResult = 0L;
        long result = instance.tempoEntreDatas(a, b);        
        assertEquals(expResult, result);
        
        a.set(2013, 11, 03, 18, 40, 10);
        b.set(2013, 11, 03, 18, 40, 00 );
        result = instance.tempoEntreDatas(a, b);  
        expResult = 10L;
        assertEquals(expResult, result);
        
        a.set(2013, 11, 03, 18, 45, 00);
        b.set(2013, 11, 03, 18, 40, 00 );
        result = instance.tempoEntreDatas(a, b);  
        expResult = 300L;
        assertEquals(expResult, result);
        
        a.set(2013, 11, 03, 18, 44, 59);
        b.set(2013, 11, 03, 18, 40, 00 );
        result = instance.tempoEntreDatas(a, b);  
        expResult = 299L;
        assertEquals(expResult, result);
        
        a.set(2013, 11, 03, 20, 40, 00);
        b.set(2013, 11, 03, 18, 40, 00 );
        result = instance.tempoEntreDatas(a, b);  
        expResult = 7200L;
        assertEquals(expResult, result);
        
        a.set(2013, 11, 03, 18, 45, 00);
        b.set(2013, 11, 03, 18, 40, 00 );
        result = instance.tempoEntreDatas(b, a);  
        expResult = -300L;
        assertEquals(expResult, result);
        
        a.set(2013, 11, 03, 18, 40, 01);
        b.set(2013, 11, 03, 18, 40, 00 );
        result = instance.tempoEntreDatas(a, b);  
        expResult = 1L;
        assertEquals(expResult, result);

    }

    /**
     * Test of preencheTabelaProd method, of class Inicial.
     */

    /**
     * Test of dinheiro method, of class Inicial.
     */
    @Test
    public void testDinheiro() {
        System.out.println("dinheiro");
        
        float f = (float) 0.0;
        String expResult = "R$ 00,00";
        String result = Inicial.dinheiro(f);
        assertEquals(expResult, result);
        
        f = (float) 1.0;
        expResult = "R$ 01,00";
        result = Inicial.dinheiro(f);
        assertEquals(expResult, result);
        
        f = (float) 0.1;
        expResult = "R$ 00,10";
        result = Inicial.dinheiro(f);
        assertEquals(expResult, result);
        
        f = (float) 0.56;
        expResult = "R$ 00,56";
        result = Inicial.dinheiro(f);
        assertEquals(expResult, result);

        f = (float) 11.801;
        expResult = "R$ 11,80";
        result = Inicial.dinheiro(f);
        assertEquals(expResult, result);
        
        f = (float) 111.00;
        expResult = "R$ 111,00";
        result = Inicial.dinheiro(f);
        assertEquals(expResult, result);
        

        f = (float) 11.75;
        expResult = "R$ 11,75";
        result = Inicial.dinheiro(f);
        assertEquals(expResult, result);
        
        f = (float) 0.01;
        expResult = "R$ 00,01";
        result = Inicial.dinheiro(f);
        assertEquals(expResult, result);
    }

    /**
     * Test of encerrarUso method, of class Inicial.
     */
}