package Controle;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import modelo.Cliente;
import modelo.Funcionario;
import modelo.Iten;
import modelo.OrdemServico;
import modelo.Produto;
import modelo.ProdutoVenda;
import modelo.UsoMaquina;
import modelo.ValorHora;
import modelo.Venda;
import modeloDAO.ClienteDAO;
import modeloDAO.FuncionarioDAO;
import modeloDAO.ItenDAO;
import modeloDAO.OrdemServicoDAO;
import modeloDAO.ProdutoDAO;
import modeloDAO.ProdutoVendaDAO;
import modeloDAO.UsoMaquinaDAO;
import modeloDAO.ValorHoraDAO;
import modeloDAO.VendaDAO;

public class GerenciarRelatorio {
    
    public static String gerarRelatorioProdutos(String dia, String mes, String ano){
        String texto = "";
        ProdutoDAO pd = new ProdutoDAO();
        int i = 1, j = 0;
        List<Produto> produtos = pd.listar();
        Date data = new Date();
        
        while (j < produtos.size()){
            Produto p = produtos.get(j);
            if(!p.isRemovido()){
                if (dia != null && p.getData().equals(formatData(dia,mes,ano))){
                    texto +="Produto: "+(i++)+ 
                            "\nCódigo: "+p.getCod()+"   Categoria: "+p.getCategoria()+
                            "   Marca: "+p.getMarca()+"   Modelo: "+p.getModelo()+ 
                            "   Estoque: "+p.getEstoque()+ "   Preço: "+p.getPreco()+"\n\n";
                }else {
                    if (mes != null){
                        data.setMonth((Integer.parseInt(getMes(mes))-1));
                        data.setYear(Integer.parseInt(ano)-1900);
                        if (p.getData().getMonth() == data.getMonth() && p.getData().getYear()== data.getYear()){
                            texto +="Produto: "+(i++)+
                                "\nCódigo: "+p.getCod()+"   Categoria: "+p.getCategoria()+
                                "   Marca: "+p.getMarca()+"   Modelo: "+p.getModelo()+
                                "   Estoque: "+p.getEstoque()+ "   Preço: "+p.getPreco()+"\n\n";
                        }
                    }else{
                        data.setYear(Integer.parseInt(ano)-1900);
                        if (p.getData().getYear()== data.getYear()){
                            texto +="Produto: "+(i++)+
                                "\nCódigo: "+p.getCod()+"   Categoria: "+p.getCategoria()+
                                "   Marca: "+p.getMarca()+"   Modelo: "+p.getModelo()+
                                "   Estoque: "+p.getEstoque()+ "   Preço: "+p.getPreco()+"\n\n";
                        }
                    }
                }
            }
         j++;
        }
        return texto;
    }
    
    public static String gerarRelatorioVendas(String dia, String mes, String ano){
        String texto = "";
        VendaDAO vd = new VendaDAO();
        ProdutoDAO pd = new ProdutoDAO();
        ProdutoVendaDAO pvd = new ProdutoVendaDAO();
        ClienteDAO cd = new ClienteDAO();
        int i = 1;
        List<Venda> vendas = vd.listar();
        Date data = new Date();
        
        for (Venda v : vendas){
            if (dia != null && v.getData().equals(formatData(dia,mes,ano))){
                texto +="Venda: "+(i++)+ 
                        "\nCódigo: "+v.getIdVenda()+"   Cliente: "+cd.buscarPorChave(v.getCliente()).getNome()+
                        "   \nProdutos: \n";
                ProdutoVenda pv = pvd.buscarPorChave(v.getProdutoVenda());
                for (Iten iten : pv.getItens()){
                    Produto prod = pd.buscarPorChave(iten.getIdProd());
                    texto += prod.getCategoria()+"   "+prod.getMarca()+"   "+
                            prod.getModelo()+"   "+prod.getDescricao()+"   "+
                            iten.getQtde()+"   "+prod.getPreco()+"   Total: "+prod.getPreco()*iten.getQtde()+"\n";
                }
                texto += "\n";
            }else {
                if (mes != null){
                    data.setMonth((Integer.parseInt(getMes(mes))-1));
                    data.setYear(Integer.parseInt(ano)-1900);
                    if (v.getData().getMonth() == data.getMonth() && v.getData().getYear()== data.getYear()){
                        texto +="Venda: "+(i++)+ 
                        "\nCódigo: "+v.getIdVenda()+"   Cliente: "+cd.buscarPorChave(v.getCliente()).getNome()+
                        "   \nProdutos: \n";
                        ProdutoVenda pv = pvd.buscarPorChave(v.getProdutoVenda());
                        ItenDAO id = new ItenDAO();
                        pv.setItens(id.buscarPorProdutoVenda(pv.getId()));
                        
                        for (Iten iten : pv.getItens()){
                            Produto prod = pd.buscarPorChave(iten.getIdProd());
                            texto += prod.getCategoria()+"   "+prod.getMarca()+"   "+
                                    prod.getModelo()+"   "+prod.getDescricao()+"   "+
                                    iten.getQtde()+"   "+prod.getPreco()+"   Total: "+prod.getPreco()*iten.getQtde()+"\n";
                        }
                        texto += "\n";
                    }
                }else{
                    data.setYear(Integer.parseInt(ano)-1900);
                    if (v.getData().getYear()== data.getYear()){
                        texto +="Venda: "+(i++)+ 
                        "\nCódigo: "+v.getIdVenda()+"   Cliente: "+cd.buscarPorChave(v.getCliente()).getNome()+
                        "   \nProdutos: \n";
                        ProdutoVenda pv = pvd.buscarPorChave(v.getProdutoVenda());
                        for (Iten iten : pv.getItens()){
                            Produto prod = pd.buscarPorChave(iten.getIdProd());
                            texto += prod.getCategoria()+"   "+prod.getMarca()+"   "+
                                    prod.getModelo()+"   "+prod.getDescricao()+"   "+
                                    iten.getQtde()+"   "+prod.getPreco()+"   Total: "+prod.getPreco()*iten.getQtde()+"\n";
                        }
                    }
                }
            }
            texto += "\n";
        }
        return texto;
    }
    
    public static String gerarRelatorioAniversariantes(String dia, String mes, String ano){
        String texto = "";
        ClienteDAO cd = new ClienteDAO();
        int i = 1;
        List<Cliente> clientes = cd.listar();
        Date data = new Date();
        
        for (Cliente c : clientes){
            if(!c.isRemovido()){
                if (dia != null && c.getData_nascimento().equals(formatData(dia,mes,ano))){
                    texto +="Cliente: "+(i++)+ 
                            "\nNome: "+c.getNome()+"   Endereco: "+c.getEndereco()+
                            "   Telefone: "+c.getTelefone()+"   Data de Nascimento: "+c.getData_nascimento()+ 
                            "   Login: "+c.getLogin()+"\n\n";
                }else {
                    if (mes != null){
                        data.setMonth((Integer.parseInt(getMes(mes))-1));
                        data.setYear(Integer.parseInt(ano)-1900);
                        if (c.getData_nascimento().getMonth() == data.getMonth() && c.getData_nascimento().getYear()== data.getYear()){
                            texto +="Cliente: "+(i++)+ 
                                "\nNome: "+c.getNome()+"   Endereco: "+c.getEndereco()+
                                "   Telefone: "+c.getTelefone()+"   Data de Nascimento: "+c.getData_nascimento()+ 
                                "   Login: "+c.getLogin()+"\n\n";
                        }
                    }else{
                        data.setYear(Integer.parseInt(ano)-1900);
                        if (c.getData_nascimento().getYear()== data.getYear()){
                            texto +="Cliente: "+(i++)+ 
                                "\nNome: "+c.getNome()+"   Endereco: "+c.getEndereco()+
                                "   Telefone: "+c.getTelefone()+"   Data de Nascimento: "+c.getData_nascimento()+ 
                                "   Login: "+c.getLogin()+"\n\n";
                        }
                    }
                }
            }
        }
        return texto;
    }

    public static String gerarRelatorioClientes(){
        String texto = "";
        ClienteDAO cd = new ClienteDAO();
        int i = 1;
        List<Cliente> clientes = cd.listar();
        
        for (Cliente c : clientes){
            if(!c.isRemovido()){
                texto +="Cliente: "+(i++)+ 
                    "\nNome: "+c.getNome()+"   Endereco: "+c.getEndereco()+
                    "   Telefone: "+c.getTelefone()+"   Data de Nascimento: "+c.getData_nascimento()+ 
                    "   Login: "+c.getLogin()+"\n\n";
            }
        }
        return texto;
    }
    
    public static String gerarRelatorioFuncionarios(){
        String texto = "";
        FuncionarioDAO fd = new FuncionarioDAO();
        int i = 1;
        List<Funcionario> funcionarios = fd.listar();
        
        for (Funcionario f : funcionarios){
            if(!f.isRemovido()){
                texto +="Funcionario: "+(i++)+ 
                    "\nNome: "+f.getNome()+"   Endereco: "+f.getResidencial()+
                    "   Telefone: "+f.getCelular()+"   Data de Nascimento: "+f.getNascimento()+ 
                    "   E-mail: "+f.getEmail()+"   login: "+f.getLogin()+"\n\n";
            }
        }
        return texto;
    }

    private static Date formatData(String dia, String mes, String ano) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String mes1 = getMes(mes);
        
        Date data = null;
        try {
            data = sdf.parse(dia+"."+mes1+"."+ano);
        } catch (ParseException ex) {
        }
        return data;
    }

    public static String gerarRelatorioOrdemServico(String dia, String mes, String ano){
        String texto = "";
        OrdemServicoDAO osd = new OrdemServicoDAO();
        int i = 1;
        List<OrdemServico> ordemServicos = osd.listar();
        Date data = new Date();
        
        for (OrdemServico os : ordemServicos){
            if(!os.isRemovido()){
                if (dia != null && os.getSolicitada().equals(formatData(dia,mes,ano))){
                    texto +="Cliente: "+(i++)+
                            "\nID: "+os.getId()+"   Equipamento: "+os.getMaquina()+
                            "   Cliente: "+os.getCliente().getNome()+"   Data solicitada: "+os.getSolicitada()+"\n\n";
                }else {
                    if (mes != null){
                        data.setMonth((Integer.parseInt(getMes(mes))-1));
                        data.setYear(Integer.parseInt(ano)-1900);
                        if (os.getSolicitada().getMonth() == data.getMonth() && os.getSolicitada().getYear()== data.getYear()){
                            texto +="Cliente: "+(i++)+
                            "\nID: "+os.getId()+"   Equipamento: "+os.getMaquina()+
                            "   Cliente: "+os.getCliente().getNome()+"   Data solicitada: "+os.getSolicitada()+"\n\n";
                        }
                    }else{
                        data.setYear(Integer.parseInt(ano)-1900);
                        if (os.getSolicitada().getYear()== data.getYear()){
                            texto +="Cliente: "+(i++)+
                            "\nID: "+os.getId()+"   Equipamento: "+os.getMaquina()+
                            "   Cliente: "+os.getCliente().getNome()+"   Data solicitada: "+os.getSolicitada()+"\n\n";
                        }
                    }
                }
            }
        }
        return texto;
    }

    public static String gerarRelatorioUsoMaquina(String dia, String mes, String ano){
        String texto = "";
        UsoMaquinaDAO umd = new UsoMaquinaDAO();
        int i = 1;
        List<UsoMaquina> usoDeMaquinas = umd.listar();
        Date data = new Date();
        
        for (UsoMaquina um : usoDeMaquinas){
            if (dia != null && um.getInicio().getTime().equals(formatData(dia,mes,ano))){
                texto +="Uso de Maquina: "+(i++)+
                        "\nCliente: "+um.getCliente().getNome()+"   Data: "+EscreveData(um.getInicio().getTime())+
                        "   Hora inicio: "+EscreveHora(um.getInicio().getTime())+"   Hora fim: "+EscreveHora(um.getFim().getTime())+"\n\n";
            }else {
                if (mes != null){
                    data.setMonth((Integer.parseInt(getMes(mes))-1));
                    data.setYear(Integer.parseInt(ano)-1900);
                    if (um.getInicio().getTime().getMonth() == data.getMonth() && um.getInicio().getTime().getYear()== data.getYear()){
                        texto +="Uso de Maquina: "+(i++)+
                        "\nCliente: "+um.getCliente().getNome()+"   Data: "+EscreveData(um.getInicio().getTime())+
                        "   Hora inicio: "+EscreveHora(um.getInicio().getTime())+"   Hora fim: "+EscreveHora(um.getFim().getTime())+"\n\n";
                    }
                }else{
                    data.setYear(Integer.parseInt(ano)-1900);
                    if (um.getInicio().getTime().getYear()== data.getYear()){
                        texto +="Uso de Maquina: "+(i++)+
                        "\nCliente: "+um.getCliente().getNome()+"   Data: "+EscreveData(um.getInicio().getTime())+
                        "   Hora inicio: "+EscreveHora(um.getInicio().getTime())+"   Hora fim: "+EscreveHora(um.getFim().getTime())+"\n\n";
                    }
                }
            }
        }
        return texto;
    }

    public static String gerarRelatorioLucro(String dia, String mes, String ano){
        String texto = "";
        UsoMaquinaDAO umd = new UsoMaquinaDAO();
        VendaDAO vd = new VendaDAO();
        ClienteDAO cd = new ClienteDAO();
        ProdutoDAO pd = new ProdutoDAO();
        ProdutoVendaDAO pvd = new ProdutoVendaDAO();
        ValorHoraDAO vhd = new ValorHoraDAO();
        int i = 1;
        List<UsoMaquina> usoDeMaquinas = umd.listar();
        List<Venda> vendas = vd.listar();
        List<ValorHora> valorHora = vhd.listar();
        
        float preco = valorHora.get(0).getValorHora();
        Date data = new Date();
        long tempoTotal = 0;
        int countUsuario = 0;
        int countVendas = 0;
        
        for (UsoMaquina um : usoDeMaquinas){
            if (dia != null && um.getInicio().getTime().equals(formatData(dia,mes,ano))){
                tempoTotal += um.getFim().getTimeInMillis() - um.getInicio().getTimeInMillis();
                countUsuario++;
            }else {
                if (mes != null){
                    data.setMonth((Integer.parseInt(getMes(mes))-1));
                    data.setYear(Integer.parseInt(ano)-1900);
                    if (um.getInicio().getTime().getMonth() == data.getMonth() && um.getInicio().getTime().getYear()== data.getYear()){
                        tempoTotal += um.getFim().getTimeInMillis() - um.getInicio().getTimeInMillis();
                        countUsuario++;
                    }
                }else{
                    data.setYear(Integer.parseInt(ano)-1900);
                    if (um.getInicio().getTime().getYear()== data.getYear()){
                        tempoTotal += um.getFim().getTimeInMillis() - um.getInicio().getTimeInMillis();
                        countUsuario++;
                    }
                }
            }
        }
        Date tempo1 = new Date(tempoTotal);
        float tempo = (tempo1.getTime())/60000;
        float valorMin = preco/60;
        float LucroUsodeMaquina = tempo*valorMin;
        
        DecimalFormat df = new DecimalFormat("#0.00");
        texto += "Número de Usos de Maquina = "+countUsuario;
        texto += "\nNumero total de Horas = "+df.format((float)tempoTotal/3600000);
        texto += "\nLucro = R$ "+(df.format(LucroUsodeMaquina))+"\n\n";
        
        float precoTotalVenda = 0;
        int numProdutoVendidos = 0;
        for (Venda v : vendas){
            if (dia != null && v.getData().equals(formatData(dia,mes,ano))){
                countVendas++;
                ProdutoVenda pv = pvd.buscarPorChave(v.getProdutoVenda());
                for (Iten iten : pv.getItens()){
                    numProdutoVendidos += iten.getQtde();
                    precoTotalVenda += pd.buscarPorChave(iten.getIdProd()).getPreco()*iten.getQtde();
                }
            }else {
                if (mes != null){
                    data.setMonth((Integer.parseInt(getMes(mes))-1));
                    data.setYear(Integer.parseInt(ano)-1900);
                    if (v.getData().getMonth() == data.getMonth() && v.getData().getYear()== data.getYear()){
                        countVendas++;
                        ProdutoVenda pv = pvd.buscarPorChave(v.getProdutoVenda());
                        for (Iten iten : pv.getItens()){
                            numProdutoVendidos += iten.getQtde();
                            precoTotalVenda += pd.buscarPorChave(iten.getIdProd()).getPreco()*iten.getQtde();
                        }
                    }
                }else{
                    data.setYear(Integer.parseInt(ano)-1900);
                    if (v.getData().getYear()== data.getYear()){
                        countVendas++;
                        ProdutoVenda pv = pvd.buscarPorChave(v.getProdutoVenda());
                        for (Iten iten : pv.getItens()){
                            numProdutoVendidos += iten.getQtde();
                            precoTotalVenda += pd.buscarPorChave(iten.getIdProd()).getPreco()*iten.getQtde();
                        }
                    }
                }
            }
        }
        texto += "Número de Vendas = "+countVendas;
        texto += "\nNúmero de produtos vendidos = "+numProdutoVendidos;
        texto += "\nLucro = R$ "+(df.format(precoTotalVenda))+"\n\n";
        
        texto += "Lucros Totais = R$ "+(df.format(precoTotalVenda+LucroUsodeMaquina))+"\n\n";
        
        return texto;
    }    

    private static String getMes(String mes){
        String mes1 = null;
        switch (mes){
            case "Janeiro":
                mes1 = "01";
                break;
            case "Fevereiro":
                mes1 = "02";
                break;
            case "Março":
                mes1 = "03";
                break;
            case "Abril":
                mes1 = "04";
                break;
            case "Maio":
                mes1 = "05";
                break;
            case "Junho":
                mes1 = "06";
                break;
            case "Julho":
                mes1 = "07";
                break;
            case "Agosto":
                mes1 = "08";
                break;
            case "Setembro":
                mes1 = "09";
                break;
            case "Outubro":
                mes1 = "10";
                break;
            case "Novembro":
                mes1 = "11";
                break;
            case "Dezembro":
                mes1 = "12";
                break;
        }
        return mes1;
    }
    
    public static void GerarPDF(String nomeArquivo, String conteudo) {
        
        Document doc = null;
        OutputStream os = null;
        
        try{
            doc = new Document(PageSize.A4, 72, 72, 72, 72);
            os = new FileOutputStream(nomeArquivo+".pdf");
            PdfWriter.getInstance(doc, os);
            doc.open();
            Paragraph p = new Paragraph("Relatório\n\n");
            doc.add(p);
            
            p = new Paragraph(conteudo);
            doc.add(p);
            doc.close();
            os.close();
            Desktop.getDesktop().open(new File(nomeArquivo+".pdf"));
        }catch(Exception e){}
    }
    
    public static String EscreveData(Date data){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String dataConvertida = sdf.format(data);
        return dataConvertida;
    }
    
    public static String EscreveHora(Date hora){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String horaConvertida = sdf.format(hora);
        return horaConvertida;
    }
}
