package Controle;

import javax.swing.JOptionPane;
import modelo.Cliente;
import modeloDAO.ValorHoraDAO;
import view.Inicial;
import view.TelaUsoMaquina;

/**
 *
 * @author Felippe
 */
public class GerenciarUsoMaquina {
    
    public static boolean validar(Cliente cliente, Inicial telaPai, TelaUsoMaquina telausomaquina){
        
    ValorHoraDAO vhd=new ValorHoraDAO();        
    
    if(cliente.getCredito()>=(vhd.valorhora()/4)){
       telaPai.liberarMaquina(cliente);
       telausomaquina.dispose();
       
       return true;
       
       
    }else{
       JOptionPane.showMessageDialog(null, "O cliente não tem saldo para usar a máquina");
        }
                        
    
        return false;

        
        
        
        
}
    
    
    public static boolean validar(Cliente cliente, Inicial telaPai){
        return true;

}
}