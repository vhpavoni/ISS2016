package Controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import modelo.Produto;
import modelo.ValorHora;
import modeloDAO.ProdutoDAO;
import modeloDAO.ValorHoraDAO;

/**
 *
 * @author Tadashi
 */
public class TestsTadashi extends TestCase {
    
    public TestsTadashi(String testName) {
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

    public void testCalcularPreco() throws ParseException {
        System.out.println("Teste calcularPreco");
        ValorHoraDAO vhd = new ValorHoraDAO();
        List<ValorHora> valores = vhd.listar();
        ValorHora vh = valores.get(0);
        // Alterando o valor (armazenado do BD) a ser cobrado por hora para 3 reais
        vh.setValorHora(3.00f);
        vhd.atualizar(vh);
        
        //caso de teste 1
        Date data = null;
        String expResult = "entrada inválida";
        String result = GerenciarCredito.calcularPreco(data);
        assertEquals("O resultado foi: "+ result +"Valor esperado: "+expResult, expResult, result);

        //caso de teste 2
        DateFormat sdf = new SimpleDateFormat("HH:mm");
        data = sdf.parse("02:00");
        expResult = "006,00";
        result = GerenciarCredito.calcularPreco(data);
        assertEquals("O resultado foi: "+ result +"Valor esperado: "+expResult, expResult, result);

        //caso de teste 3
        data = sdf.parse("000:00");
        expResult = "000,00";
        result = GerenciarCredito.calcularPreco(data);
        assertEquals("O resultado foi: "+ result +"Valor esperado: "+expResult, expResult, result);
        
        //caso de teste 4
        data = sdf.parse("00:01");
        expResult = "000,05";
        result = GerenciarCredito.calcularPreco(data);
        assertEquals("O resultado foi: "+ result +"Valor esperado: "+expResult, expResult, result);
        
        //caso de teste 5
        data = sdf.parse("99:98");
        expResult = "301,90";
        result = GerenciarCredito.calcularPreco(data);
        assertEquals("O resultado foi: "+ result +"Valor esperado: "+expResult, expResult, result);
        
        //caso de teste 6
        data = sdf.parse("99:99");
        expResult = "301,95";
        result = GerenciarCredito.calcularPreco(data);
        assertEquals("O resultado foi: "+ result +"Valor esperado: "+expResult, expResult, result);
        
        //caso de teste 7
        data = sdf.parse("100:00");
        expResult = "300,00";
        result = GerenciarCredito.calcularPreco(data);
        assertEquals("O resultado foi: "+ result +"Valor esperado: "+expResult, expResult, result);
    }

    public void testCalcularHora() {
        System.out.println("Teste calcularHora");
        ValorHoraDAO vhd = new ValorHoraDAO();
        List<ValorHora> valores = vhd.listar();
        ValorHora vh = valores.get(0);
        // Alterando o valor (armazenado do BD) a ser cobrado por hora para 3 reais
        vh.setValorHora(3.00f);
        vhd.atualizar(vh);

        //caso de teste 1
        String expResult = "entrada inválida";
        String result = GerenciarCredito.calcularHora(-1.00f);
        assertEquals("O resultado foi: "+ result +"Valor esperado "+expResult, expResult, result);

        //caso de teste 2
        expResult = "00:30";
        result = GerenciarCredito.calcularHora(01.50f);
        assertEquals("O resultado foi: "+ result +"Valor esperado: "+expResult, expResult, result);

        //caso de teste 3
        expResult = "entrada inválida";
        result = GerenciarCredito.calcularHora(-0.01f);
        assertEquals("O resultado foi: "+ result +" Valor esperado: "+expResult, expResult, result);
        
        //caso de teste 4
        expResult = "00:00";
        result = GerenciarCredito.calcularHora(0.00f);
        assertEquals("O resultado foi: "+ result +" Valor esperado: "+expResult, expResult, result);
        
        //caso de teste 5
        expResult = "00:01";
        result = GerenciarCredito.calcularHora(0.01f);
        assertEquals("O resultado foi: "+ result +" Valor esperado: "+expResult, expResult, result);
        
        //caso de teste 6
        expResult = "33:20";
        result = GerenciarCredito.calcularHora(99.98f);
        assertEquals("O resultado foi: "+ result +" Valor esperado: "+expResult, expResult, result);
        
        //caso de teste 7
        expResult = "33:20";
        result = GerenciarCredito.calcularHora(99.99f);
        assertEquals("O resultado foi: "+ result +" Valor esperado: "+expResult, expResult, result);
        
        //caso de teste 8
        expResult = "33:20";
        result = GerenciarCredito.calcularHora(100.00f);
        assertEquals("O resultado foi: "+ result +" Valor esperado: "+expResult, expResult, result);
    }
    
    public void testGerarRelatorioProdutos() {
        System.out.println("Gerar Relatorio Produtos");
        ProdutoDAO pd = new ProdutoDAO();
        List<Produto> produtos = pd.listar();
        
        //Caso de teste 1
        String dia = "04";
        String mes = "Novembro";
        String ano = "2013";
        String expResult = "";
        String result = GerenciarRelatorio.gerarRelatorioProdutos(dia, mes, ano);
        assertEquals(expResult, result);
        
        Produto p = new Produto("Mouse", "Microsoft", "RVF-00052", "USB Wireless", 20, 161f);
        p.setData(new Date(2013-1900, 10, 04));
        pd.salvar(p);
        
        //Caso de teste 2
        dia = "04";
        mes = "Novembro";
        ano = "2013";
        expResult = "Produto: 1\nCódigo: "+p.getCod()+"   Categoria: Mouse   Marca: Microsoft   Modelo: RVF-00052   Estoque: 20   Preço: 161.0\n\n";
        result = GerenciarRelatorio.gerarRelatorioProdutos(dia, mes, ano);
        assertEquals(expResult, result);
        
        //Caso de teste 3
        dia = null;
        mes = "Novembro";
        ano = "2013";
        expResult = "Produto: 1\nCódigo: "+p.getCod()+"   Categoria: Mouse   Marca: Microsoft   Modelo: RVF-00052   Estoque: 20   Preço: 161.0\n\n";
        result = GerenciarRelatorio.gerarRelatorioProdutos(dia, mes, ano);
        assertEquals(expResult, result);
        
        //Caso de teste 4
        dia = null;
        mes = "Outubro";
        ano = "2013";
        expResult = "";
        result = GerenciarRelatorio.gerarRelatorioProdutos(dia, mes, ano);
        assertEquals(expResult, result);
        
        //Caso de teste 5
        dia = null;
        mes = null;
        ano = "2013";
        expResult = "Produto: 1\nCódigo: "+p.getCod()+"   Categoria: Mouse   Marca: Microsoft   Modelo: RVF-00052   Estoque: 20   Preço: 161.0\n\n";
        result = GerenciarRelatorio.gerarRelatorioProdutos(dia, mes, ano);
        assertEquals(expResult, result);
        
        //Caso de teste 6
        dia = null;
        mes = null;
        ano = "2012";
        expResult = "";
        result = GerenciarRelatorio.gerarRelatorioProdutos(dia, mes, ano);
        assertEquals(expResult, result);
        
        pd.excluir(p);
        //Caso de teste 7
        dia = null;
        mes = null;
        ano = "2013";
        expResult = "";
        result = GerenciarRelatorio.gerarRelatorioProdutos(dia, mes, ano);
        assertEquals(expResult, result);
        
    }
}
