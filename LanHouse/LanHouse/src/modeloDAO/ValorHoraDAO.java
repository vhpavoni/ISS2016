/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDAO;

import BancoDeDados.Persistencia;
import java.util.List;

import modelo.ValorHora;


/**
 *
 * @author GCD
 */
public class ValorHoraDAO {
        public boolean salvar(ValorHora hora) {
        return Persistencia.salvar(hora);
        }
        public boolean atualizar(ValorHora hora) {
        return Persistencia.atualizar(hora);
    }
    public List<ValorHora> listar() {
        return Persistencia.listar(new ValorHora());
    }
    public Float valorhora(){
        List<ValorHora> l=listar();
        ValorHora v=new ValorHora();
        for(ValorHora h:l){
            v=h;
        }
        return v.getValorHora();
    }
}
