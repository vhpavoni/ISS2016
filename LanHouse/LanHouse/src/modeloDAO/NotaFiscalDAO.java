package modeloDAO;


import BancoDeDados.Persistencia;
import java.util.List;
import modelo.NotaFiscal;

public class NotaFiscalDAO {

    public boolean salvar(NotaFiscal nf) {
        return Persistencia.salvar(nf);
    }
    
    public boolean atualizar(NotaFiscal nf) {
        return Persistencia.atualizar(nf);
    }

    public boolean excluir(NotaFiscal nf) {
        nf.setRemovido(true);
        return Persistencia.atualizar(nf);
    }

    public List<NotaFiscal> listar() {
        return Persistencia.listar(new NotaFiscal());
    }

    public NotaFiscal buscarPorChave(String cod){
        return (NotaFiscal)Persistencia.buscarProChave("produto", cod);
    }
}