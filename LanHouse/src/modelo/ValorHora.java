/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author GCD
 */
@Entity
@Table(name = "VALORHORA")
public class ValorHora implements Serializable{

    @Id  
    @Column(name = "id")
    private int id;
    @Column(name = "valorHora")        
    private Float valorHora;
    
    public ValorHora(){
        
    }

    public ValorHora(Float valorHora) {
        this.id = 1;
        this.valorHora = valorHora;
    }

    public Float getValorHora() {
        return valorHora;
    }

    public void setValorHora(Float valorHora) {
        this.valorHora = valorHora;
    }
}
