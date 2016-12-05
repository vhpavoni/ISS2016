package BancoDeDados;

import org.hibernate.Session;

public class ConectaHibernateBD {

    public static void main(String[] args) {

        Session sessao = null;
        try {
            sessao = HibernateUtil.getSessionFactory().openSession();
            System.out.println("Conectou!");
        } finally {
            if (sessao!=null) sessao.close();
        }
    }
}