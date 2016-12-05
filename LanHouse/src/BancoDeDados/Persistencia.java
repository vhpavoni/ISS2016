package BancoDeDados;

import java.util.List;
import org.hibernate.*;

public class Persistencia {
   
    public static boolean salvar(Object obj) {
        Session sessao = null;
        Transaction transacao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.save(obj);
            transacao.commit();
        } catch (HibernateException e) {
            System.out.println("Não foi possível inserir. Erro: "
                    + e.getMessage());
        } finally {
            try {
                sessao.close();
                return true;
            } catch (Throwable e) {
                System.out.println("Erro ao fechar operação de inserção, Mensagem: "
                        + e.getMessage());
            }
        }
        return false;
    }
    
    public static boolean atualizar(Object obj) {
        Session sessao = null;
        Transaction transacao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.merge(obj);
            transacao.commit();
        } catch (HibernateException e) {
            System.out.println("Não foi possível inserir. Erro: "
                    + e.getMessage());
        } finally {
            try {
                sessao.close();
                return true;
            } catch (Throwable e) {
                System.out.println("Erro ao fechar operação de inserção, Mensagem: "
                        + e.getMessage());
            }
        }
        return false;
    }

    public static boolean excluir(Object obj) {
        Session sessao = null;
        Transaction transacao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            sessao.delete(obj);
            transacao.commit();
        } catch (HibernateException e) {
            System.out.println("Não foi possível excluir. Erro: "
                    + e.getMessage());
        } finally {
            try {
                sessao.close();
                return true;
            } catch (Throwable e) {

                System.out.println("Erro ao fechar operação de exclusão. Mensagem: "
                        + e.getMessage());
            }
        }
        return false;
    }
    
    public static List listar(Object o) {
        Session sessao = null;
        Transaction transacao = null;
        Criteria criteria = null;
        List resultado = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            criteria = sessao.createCriteria(o.getClass());
            resultado = criteria.list();
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
    
    
    
    public static Object buscarProChave(String classe, String PrimaryKey) {
        Object obj = null;
        Session sessao = null;
        Transaction transacao = null;
        Query consulta = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from "+classe+" where login :parametro");
            consulta.setString("parametro", PrimaryKey);
            obj = consulta.uniqueResult();
            transacao.commit();
            return obj;
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
        return obj;
    }
}
