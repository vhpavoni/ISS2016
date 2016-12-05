/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import BancoDeDados.Persistencia;
import java.util.List;
import modelo.Proprietario;

public class ProprietarioDAO {
        public boolean salvar(Proprietario proprietario) {
        return Persistencia.salvar(proprietario);
    }
        public boolean atualizar(Proprietario proprietario) {
        return Persistencia.atualizar(proprietario);
    }

        public List<Proprietario> listar() {
        return Persistencia.listar(new Proprietario());
    }   
}
