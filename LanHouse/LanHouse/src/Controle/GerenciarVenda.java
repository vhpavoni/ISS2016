package Controle;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.Iten;
import modelo.NotaFiscal;
import modelo.Produto;
import modelo.ProdutoVenda;
import modelo.Venda;
import modeloDAO.NotaFiscalDAO;
import modeloDAO.ProdutoDAO;
import modeloDAO.ProdutoVendaDAO;
import modeloDAO.VendaDAO;

public class GerenciarVenda {
    
    public static boolean finalizarVenda(String login, ProdutoVenda pv){
        ProdutoVendaDAO pvd = new ProdutoVendaDAO();
        ProdutoDAO pd = new ProdutoDAO();
        NotaFiscalDAO nfd = new NotaFiscalDAO();
        VendaDAO vd = new VendaDAO();
        
        float precoTotal = 0;
        for(int i = 0; i<pv.getItens().size(); i++){
            precoTotal += GerenciarProduto.buscarProduto(pv.getItens().get(i).getIdProd()).getPreco()*pv.getItens().get(i).getQtde();
            Produto p = GerenciarProduto.buscarProduto(pv.getItens().get(i).getIdProd());
            p.setEstoque((int)(p.getEstoque()-pv.getItens().get(i).getQtde()));
            pd.atualizar(p);
        }
        
        pvd.salvar(pv);
        
        Venda venda = new Venda(login, pv.getId(), precoTotal);
        vd.salvar(venda);
        NotaFiscal nf = new NotaFiscal(venda.getIdVenda());
        nfd.salvar(nf);
        venda.setNotaFiscal(nf.getNumero());
        vd.atualizar(venda);
        
        String nomeArquivo = "NF-venda_"+venda.getIdVenda();
        
        GerarPDF(nomeArquivo, venda, nf);
        return true;
    }
    
    public static void GerarPDF(String nomeArquivo, Venda venda, NotaFiscal nf) {
        
        Document doc = null;
        OutputStream os = null;
        String conteudo = "";
        ProdutoVendaDAO pvd = new ProdutoVendaDAO();
        ProdutoDAO pd = new ProdutoDAO();
        
        try{
            doc = new Document(PageSize.A4, 72, 72, 72, 72);
            os = new FileOutputStream(nomeArquivo+".pdf");
            PdfWriter.getInstance(doc, os);
            doc.open();
            Paragraph p = new Paragraph("Nota Fiscal\n\n");
            doc.add(p);
            
            conteudo+= "Data de emissão: "+EscreveData(nf.getDataEmissao())+"               ";
            conteudo+= "Nº: "+nf.getNumero()+"\n\n";
            
            conteudo += String.format ("%-10.10s %-50.50s %-10.10s %15.10s",  
            "Qtde", "Descrição", "Preco unit", "Total");
            ProdutoVenda pv = pvd.buscarPorChave(venda.getProdutoVenda());
            for (Iten iten : pv.getItens()){
                conteudo += "\n";
                Produto prod = pd.buscarPorChave(iten.getIdProd());
                conteudo+= String.format ("%-10.10s %-50.50s %-10.10s %10.10s",
            iten.getQtde(), (prod.getCategoria()+" "+prod.getMarca()+" "+prod.getModelo()), prod.getPreco(), prod.getPreco()*iten.getQtde()+"0");
            }
            p = new Paragraph(conteudo);
            doc.add(p);
            doc.close();
            os.close();
            Desktop.getDesktop().open(new File(nomeArquivo+".pdf"));
        }catch(DocumentException e){
        } catch (IOException ex) {
        }
    }

    public static String EscreveData(Date data){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String dataConvertida = sdf.format(data);
        return dataConvertida;
    }
}
