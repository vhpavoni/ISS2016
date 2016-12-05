package Controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Cliente;
import modeloDAO.ClienteDAO;

public class GerenciarCliente {
  
    public static Cliente buscarCliente(String login){
        Cliente cliente = null;
        ClienteDAO cd = new ClienteDAO();
        List<Cliente> clientes = cd.listar();
            
            for (Cliente cli : clientes){
                if (cli.getLogin().equals(login)){
                    cliente = cli;
                }
            }
            
        return cliente;
    }

    
    public static Cliente buscarClientePorNome(String nome){
        Cliente cliente = null;
        ClienteDAO cd = new ClienteDAO();
        List<Cliente> clientes = cd.listar();

        for (Cliente cli : clientes){
            if (cli.getNome().equalsIgnoreCase(nome)){
                cliente = cli;
            }
        }
        return cliente;
    }

    public static List<Cliente> buscarTodosClientes(){
        ClienteDAO cd = new ClienteDAO();
        List<Cliente> clientes = new ArrayList<Cliente>();
        for(Cliente cliente : cd.listar()){
            if(!cliente.isRemovido()){
                clientes.add(cliente);
            }
        }
        return clientes;
    }
    
    public static boolean incluirCredito(Cliente cliente, float credito){
        ClienteDAO cd = new ClienteDAO();
        cliente.addCredito(credito);
        return cd.atualizar(cliente);
    }

}
