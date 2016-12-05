package modeloDAO;

import BancoDeDados.Persistencia;
import java.util.ArrayList;
import java.util.List;
import modelo.Maquina;
import modelo.UsoMaquina;

/**
 *
 * @author Felippe
 */
public class MaquinaDAO {
    
    public boolean salvar(Maquina maquina) {
        return Persistencia.salvar(maquina);
    }
       public boolean atualizar(Maquina maquina) {
        return Persistencia.atualizar(maquina);
    }

    public boolean excluir(Maquina maquina) {
        return Persistencia.excluir(maquina);
    }
    
    public List<Maquina> listar() {
        return Persistencia.listar(new Maquina());
    }
    public List<Maquina> listarAtivas() {
        List<Maquina> maqs=new ArrayList<Maquina>();
        List<Maquina> aux=Persistencia.listar(new Maquina());
        for (Maquina maquina : aux){
            if(!maquina.isRemovido()){
                if(maquina.getUsado()!=0){
                    maqs.add(maquina);
                }
            }
        }
        return maqs;
    }

    public Maquina buscarPorChave(String cod){
        List<Maquina> maq=new ArrayList();
        maq= listar();
        if(!maq.isEmpty()){
            for (Maquina uso : maq){
                if(uso.getId().equals(cod)){
                    return uso;
                }
            }
        }
        return null;
    }


}
