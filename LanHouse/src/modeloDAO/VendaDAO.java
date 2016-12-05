package modeloDAO;


import BancoDeDados.Persistencia;
import java.util.List;
import modelo.Venda;

public class VendaDAO {

    public boolean salvar(Venda venda) {
        return Persistencia.salvar(venda);
    }
    
    public boolean atualizar(Venda venda) {
        return Persistencia.atualizar(venda);
    }

    public boolean excluir(Venda venda) {
        venda.setRemovido(true);
        return Persistencia.atualizar(venda);
    }

    public List<Venda> listar() {
        return Persistencia.listar(new Venda());
    }

    public Venda buscarPorChave(String cod){
        return (Venda)Persistencia.buscarProChave("produto", cod);
    }
}