package modeloDAO;


import BancoDeDados.HibernateUtil;
import BancoDeDados.Persistencia;
import java.util.List;
import modelo.ProdutoVenda;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ProdutoVendaDAO {

    public boolean salvar(ProdutoVenda produto) {
        return Persistencia.salvar(produto);
    }
    
    public boolean atualizar(ProdutoVenda produto) {
        return Persistencia.atualizar(produto);
    }

    public boolean excluir(ProdutoVenda produto) {
        produto.setRemovido(true);
        return Persistencia.atualizar(produto);
    }

    public List<ProdutoVenda> listar() {
        return Persistencia.listar(new ProdutoVenda());
    }

    public ProdutoVenda buscarPorChave(Long primaryKey){
        Session sessao = null;
        Transaction transacao = null;
        Criteria criteria = null;
        List resultado = null;
        ProdutoVenda prodV = new ProdutoVenda();
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            criteria = sessao.createCriteria(prodV.getClass());
            resultado = criteria.list();
            transacao.commit();
            for (Object o : resultado){
                ProdutoVenda pv = (ProdutoVenda)o;
                if (pv.getId().equals(primaryKey)){
                    return (ProdutoVenda)o;
                }
            }
            return (ProdutoVenda)resultado.get(0);

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