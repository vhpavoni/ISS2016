package modeloDAO;

import BancoDeDados.HibernateUtil;
import BancoDeDados.Persistencia;
import java.util.ArrayList;
import java.util.List;
import modelo.Maquina;
import modelo.UsoMaquina;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Felippe
 */
public class UsoMaquinaDAO {
    
    @SuppressWarnings("empty-statement")
    public boolean salvar(UsoMaquina uso) {
        MaquinaDAO md=new MaquinaDAO();
        UsoMaquina aux=null;
        Maquina maq=uso.getMaquina();
        if(maq.getUsado()==0){
            if(Persistencia.salvar(uso)){
                aux=buscarPorChave(uso.getId());
                maq.setUsado(aux.getId());
                if(md.atualizar(maq)){
                    return true;
                }
            }
         }else{
                 return false;
         }
        return false;
        
    }
       public boolean atualizar(UsoMaquina uso) {
        return Persistencia.atualizar(uso);
    }

    public boolean excluir(UsoMaquina uso) {
        return Persistencia.excluir(uso);
    }
    
    public List<UsoMaquina> listarAtivas() {
        Session sessao = null;

        Transaction transacao = null;

        Query consulta = null;

        List<UsoMaquina> resultado = null;

        try {

            sessao = HibernateUtil.getSessionFactory().openSession();

            transacao = sessao.beginTransaction();

            consulta = sessao.createQuery("from usomaquina where ativo :sim");
            
            consulta.setInteger("sim", 1);

            resultado = consulta.list();

            transacao.commit();

            return (List<UsoMaquina>) resultado;

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

    public UsoMaquina buscarPorChave(Long cod){
        List<UsoMaquina> usos=new ArrayList();
        usos= listar();
        if(!usos.isEmpty()){
            for (UsoMaquina uso : usos){
                if(uso.getId().equals(cod)){
                    return uso;
                }
            }
        }
        return null;
    }
    public UsoMaquina buscar(UsoMaquina uso){
        Object obj = null;
        Session sessao = null;
        Transaction transacao = null;
        Query consulta = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            transacao = sessao.beginTransaction();
            consulta = sessao.createQuery("from usomaquina where maquina :idmaquina");
            consulta.setString("idmaquina", uso.getMaquina().getId());
            obj = consulta.uniqueResult();
            transacao.commit();
            return (UsoMaquina) obj;
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
        return (UsoMaquina) obj;
    }
    public List<UsoMaquina> listar() {
        return Persistencia.listar(new UsoMaquina());
    }
}
