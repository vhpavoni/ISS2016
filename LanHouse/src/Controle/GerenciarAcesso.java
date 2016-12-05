/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import BancoDeDados.Persistencia;
import java.util.List;
import modelo.Funcionario;
import modelo.Proprietario;

/**
 *
 * @author GCD
 */
public class GerenciarAcesso {
    
    public static String validarAcesso(String login, String senha){
            
         List<Funcionario> funcionarios = Persistencia.listar(new Funcionario());
            for(Funcionario funcionario: funcionarios){
                if (funcionario.getLogin().equals(login)&&funcionario.getSenha().equals(senha)){
                    return "func";
                }
            }
            
               List<Proprietario> props = Persistencia.listar(new Proprietario());
            for(Proprietario proprietario: props){
                if (proprietario.getLogin().equals(login)&&proprietario.getSenha().equals(senha)){
                    return "propri";
                }
            }
                    
        return "invalido";
        
        
    }
}
