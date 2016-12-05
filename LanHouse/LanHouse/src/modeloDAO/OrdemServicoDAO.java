/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import BancoDeDados.Persistencia;
import java.util.ArrayList;
import java.util.List;
import modelo.Maquina;
import modelo.OrdemServico;

/**
 *
 * @author Pedro
 */
public class OrdemServicoDAO {
     public boolean salvar(OrdemServico maquina) {
        return Persistencia.salvar(maquina);
    }
       public boolean atualizar(OrdemServico maquina) {
        return Persistencia.atualizar(maquina);
    }

    public boolean excluir(OrdemServico maquina) {
        return Persistencia.excluir(maquina);
    }
    
    public List<OrdemServico> listar() {
        return Persistencia.listar(new OrdemServico());
    }
    public OrdemServico busca(Long id){
         List<OrdemServico> usos=new ArrayList();
        usos= listar();
        if(!usos.isEmpty()){
            for (OrdemServico uso : usos){
                if(uso.getId().equals(id)){
                    return uso;
                }
            }
        }
        return null;
    }
}
