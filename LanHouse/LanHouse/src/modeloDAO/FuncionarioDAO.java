package modeloDAO;


import BancoDeDados.Persistencia;
import java.util.List;
import org.hibernate.*;

import BancoDeDados.HibernateUtil;
import modelo.Funcionario;

public class FuncionarioDAO {

    public boolean salvar(Funcionario funcionario) {
        return Persistencia.salvar(funcionario);
    }
 public boolean atualizar(Funcionario produto) {
        return Persistencia.atualizar(produto);
    }
    public boolean excluir(Funcionario funcionario) {
        funcionario.setRemovido(true);
        return Persistencia.atualizar(funcionario);
    }
    public List<Funcionario> listar() {
        return Persistencia.listar(new Funcionario());
    }
    /*
    public List<Funcionario> listar() {
        Session sessao = null;
        Transaction transacao = null;
        Query consulta = null;
        List<Funcionario> resultado = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from Funcionario");
            resultado = consulta.list();
            transacao.commit();
            return resultado;

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
*/
    public Funcionario buscarFuncionario(String login) {
        Funcionario funcionario = null;
        Session sessao = null;
        Transaction transacao = null;
        Query consulta = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from funcionario where login :parametro");
            consulta.setString("parametro", login);
            funcionario = (Funcionario) consulta.uniqueResult();
            transacao.commit();
            return funcionario;
        } catch (HibernateException e) {
            System.out.println("Não foi possível buscar consulta. Erro: "
                    + e.getMessage());
        } finally {
            try {
                sessao.close();
            } catch (Throwable e) {
                System.out.println("Erro ao fechar operação de buscar. Mensagem: "
                        + e.getMessage());
            }
        }
        return funcionario;
    }
}