package BancoDeDados;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import modelo.Cliente;
import modelo.Produto;
import modelo.Venda;
import modeloDAO.ProdutoDAO;

public class Teste {
    
    public static void main(String args[]) throws ParseException {
        ProdutoDAO pd = new ProdutoDAO();
        
        ArrayList<String> marca = new ArrayList<String>();
        
        marca.add("3KJ-00002");
        marca.add("RVF-00052");
        marca.add("3LR-00013");
        marca.add("ZJA-00004");
        marca.add("6PL-00003");
        marca.add("U5K-00005");
        marca.add("ZJA-00004");
        
        Random randQtde = new Random();
        Random randPreco = new Random();
        Random randDia = new Random();
        Random randMes = new Random();
        for (String s : marca){
            Produto p = new Produto("Mouse", "Microsoft", s, "USB Wireless", randQtde.nextInt(50), randPreco.nextInt(150)+150);
            int dia = randDia.nextInt(28)+1;
            int mes = randMes.nextInt(12);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date data = sdf.parse(dia+"."+mes+"."+"2013");
            p.setData(data);
            pd.salvar(p);
        }

        marca = new ArrayList<String>();
        marca.add("ABC-00001");
        marca.add("CDE-00052");
        marca.add("ADK-00013");
        marca.add("AEO-00004");
        marca.add("QKJ-00003");
        marca.add("ADI-00005");
        marca.add("DAL-00004");
        
        for (String s : marca){
            Produto p = new Produto("Teclado", "Clone", s, "PS/2 Wired", randQtde.nextInt(30), randPreco.nextInt(40)+15);
            int dia = randDia.nextInt(28)+1;
            int mes = randMes.nextInt(12);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date data = sdf.parse(dia+"."+mes+"."+"2013");
            p.setData(data);
            pd.salvar(p);
        }
        
        marca = new ArrayList<String>();
        marca.add("ABC-001");
        marca.add("CEF-052");
        marca.add("FGH-013");
        marca.add("HIJ-004");
        marca.add("JKL-003");
        marca.add("LMN-005");
        marca.add("NOP-004");
        
        for (String s : marca){
            Produto p = new Produto("HeadSet", "Razor", s, "Gamer", randQtde.nextInt(60), randPreco.nextInt(100)+330);
            int dia = randDia.nextInt(28)+1;
            int mes = randMes.nextInt(12);
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            Date data = sdf.parse(dia+"."+mes+"."+"2013");
            p.setData(data);
            pd.salvar(p);
        }
    }
}
