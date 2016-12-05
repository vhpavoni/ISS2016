package modeloDAO;


import BancoDeDados.HibernateUtil;
import BancoDeDados.Persistencia;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import modelo.Iten;
import modelo.ProdutoVenda;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItenDAO {

    public boolean salvar(Iten nf) {
        return Persistencia.salvar(nf);
    }
    
    public boolean atualizar(Iten nf) {
        return Persistencia.atualizar(nf);
    }


    public List<Iten> listar() {
        return Persistencia.listar(new Iten());
    }

    public List<Iten> buscarPorProdutoVenda(Long id){
        Session sessao = null;
        Transaction transacao = null;
        Criteria criteria = null;
        List resultado = null;
        Iten iten = new Iten();
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            criteria = sessao.createCriteria(iten.getClass());
            resultado = criteria.list();
            transacao.commit();
            List<Iten> itens = new ArrayList<Iten>();
            for (Object o : resultado){
                Iten i = (Iten)o;
                if (i.getProdutoVenda().getId().equals(id)){
                    System.out.println(i.getProdutoVenda().getId()+ " 000000000 "+id);
                    itens.add(i);
                }
            }
            return itens;

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