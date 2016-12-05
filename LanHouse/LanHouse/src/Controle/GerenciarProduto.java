package Controle;

import java.util.ArrayList;
import modelo.Produto;
import modeloDAO.ProdutoDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import view.Inicial;

public class GerenciarProduto {
    
    public static String verificarDuplicidade(ArrayList lista){
        Produto produto = null;
        ProdutoDAO produtoDao = new ProdutoDAO();
        List<Produto> produtos = produtoDao.listar();
        
        for(Produto prod : produtos){
            if ((prod.getCategoria().equalsIgnoreCase((String)lista.get(1)) &&
                    prod.getMarca().equalsIgnoreCase((String)lista.get(2)) &&
                    prod.getModelo().equalsIgnoreCase((String)lista.get(3))) &&
                    (lista.get(0) == null || !lista.get(0).equals(prod.getCod()))){
                return String.valueOf(prod.getCod());
            }
        }
        if (lista.get(0) == null){
            produto = new Produto((String)lista.get(1), (String)lista.get(2), (String)lista.get(3),
                    (String)lista.get(4), Integer.parseInt((String)lista.get(5)), Float.parseFloat((String)lista.get(6)));
            produtoDao.salvar(produto);
            return null;
        }else{
            produto = new Produto((String)lista.get(1), (String)lista.get(2), (String)lista.get(3),
                    (String)lista.get(4), Integer.parseInt((String)lista.get(5)), Float.parseFloat((String)lista.get(6)));
            produto.setCod((Long)lista.get(0));
            produtoDao.atualizar(produto);
            return null;
        }
    }
    
    public static boolean confirmarExclusao(Inicial ini, Long cod){
        Produto produto = null;
        int op = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o Produto?");
        if (op == 0){
            ProdutoDAO pd = new ProdutoDAO();

            List<Produto> produtos = pd.listar();
            
            for (Produto prod : produtos){
                if (prod.getCod().equals(cod)){
                    produto = prod;
                }
            }

            pd.excluir(produto);
            ini.preencheTabelaProd();
            return true;
        }
        return false;
    }
    
    public static Produto buscarProduto(Long cod){
        Produto produto = null;
        ProdutoDAO pd = new ProdutoDAO();
        List<Produto> produtos = pd.listar();
            
            for (Produto prod : produtos){
                if (prod.getCod().equals(cod)){
                    produto = prod;
                }
            }
            
        return produto;
    }
    
    public static void buscarPorNome(Inicial ini, String chave, 
        DefaultTableModel tableModel, JTable tabelaProduto){
        ProdutoDAO pd = new ProdutoDAO();
        List<Produto> produtos = pd.listar();

        if (chave.equals("")){
            ini.preencheTabelaProd();
        }else {
            tableModel.setNumRows(0);
            Object[] linha = {1};
            int i = 0;
            for (Produto produto : produtos){
                if(!produto.isRemovido() && produto.getCategoria().equalsIgnoreCase(chave)){
                    tableModel.addRow(linha);
                    tabelaProduto.setValueAt(produto.getCod(), i, 0);
                    tabelaProduto.setValueAt(produto.getCategoria(), i, 1);
                    tabelaProduto.setValueAt(produto.getMarca(), i, 2);
                    tabelaProduto.setValueAt(produto.getModelo(), i, 3);
                    tabelaProduto.setValueAt(produto.getDescricao(), i, 4);
                    tabelaProduto.setValueAt(produto.getPreco(), i, 5);
                    tabelaProduto.setValueAt(produto.getEstoque(), i, 6);
                    i++;
                }
            }
            if (i < 30){
                for (i=i; i<30; i++){
                    tableModel.addRow(linha);
                    tabelaProduto.setValueAt(null, i, 0);
                }
            }
        }
    }
    
    public static List<Produto> buscarPorCategoria(String categoria){
        String nomeParcial = "";
        ProdutoDAO pd = new ProdutoDAO();
        List<Produto> produtos = new ArrayList<Produto>();
        if (categoria.length() > 0){
            for (Produto produto : pd.listar()){
                if (produto.getCategoria().length() >= categoria.length()){
                    nomeParcial = String.valueOf(produto.getCategoria().subSequence(0, categoria.length()));
                }
                if(!produto.isRemovido() && !nomeParcial.equals("") && categoria.equalsIgnoreCase(nomeParcial)){
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }
    
    public static List<Produto> buscarPorMarca(String marca){
        String nomeParcial = "";
        ProdutoDAO pd = new ProdutoDAO();
        List<Produto> produtos = new ArrayList<Produto>();
        if (marca.length() > 0){
            for (Produto produto : pd.listar()){
                if (produto.getMarca().length() >= marca.length()){
                    nomeParcial = String.valueOf(produto.getMarca().subSequence(0, marca.length()));
                }
                if(!produto.isRemovido() && !nomeParcial.equals("") && marca.equalsIgnoreCase(nomeParcial)){
                    produtos.add(produto);
                }
            }
        }
        return produtos;
    }
}
