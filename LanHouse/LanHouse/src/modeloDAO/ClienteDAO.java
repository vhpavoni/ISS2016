package modeloDAO;


import BancoDeDados.HibernateUtil;
import BancoDeDados.Persistencia;
import java.util.Iterator;
import java.util.List;
import modelo.Cliente;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClienteDAO {

    public boolean salvar(Cliente cliente) {
        return Persistencia.salvar(cliente);
    }
      public boolean atualizar(Cliente cliente) {
        return Persistencia.atualizar(cliente);
    }
    public boolean excluir(Cliente cliente) {
         cliente.setRemovido(true);
        return Persistencia.atualizar(cliente);
    }

    public List<Cliente> listar() {
        return Persistencia.listar(new Cliente());
    }

    public Cliente buscarPorChave(String primaryKey){
        Session sessao = null;
        Transaction transacao = null;
        Criteria criteria = null;
        List resultado = null;
        Cliente cli = new Cliente();
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            criteria = sessao.createCriteria(cli.getClass());
            resultado = criteria.list();
            transacao.commit();
            for (Object o : resultado){
                Cliente pv = (Cliente)o;
                if (pv.getLogin().equals(primaryKey)){
                    return (Cliente)o;
                }
            }
            return (Cliente)resultado.get(0);

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