package modeloDAO;


import BancoDeDados.HibernateUtil;
import BancoDeDados.Persistencia;
import java.util.Iterator;
import java.util.List;
import modelo.Cliente;
import modelo.Produto;
import modelo.ProdutoVenda;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProdutoDAO {

    public boolean salvar(Produto produto) {
        return Persistencia.salvar(produto);
    }
    
    public boolean atualizar(Produto produto) {
        return Persistencia.atualizar(produto);
    }

    public boolean excluir(Produto produto) {
        produto.setRemovido(true);
        return Persistencia.atualizar(produto);
    }
    
    public boolean excluirPermanente(Produto produto) {
        return Persistencia.excluir(produto);
    }

    public List<Produto> listar() {
        return Persistencia.listar(new Produto());
    }

    public Produto buscarPorChave(Long primaryKey){
        Session sessao = null;
        Transaction transacao = null;
        Criteria criteria = null;
        List resultado = null;
        Produto prod = new Produto();
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            criteria = sessao.createCriteria(prod.getClass());
            resultado = criteria.list();
            transacao.commit();
            for (Object o : resultado){
                Produto pv = (Produto)o;
                if (pv.getCod().equals(primaryKey)){
                    return pv;
                }
            }
            return (Produto)resultado.get(0);

        } catch (HibernateException e) {
            System.out.println("Não foi possível selecionar consultas. Erro: "
                    + e.getMessage());
            throw new HibernateException(e);
        } finally {
            try {
                sessao.close();
            } catch (Throwable e) {
                System.out.println("Erro ao fechar operação de consulta. Mensagem: "
                        + e.getMessage());
            }
        }
    }
    
    
    
}