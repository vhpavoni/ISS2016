/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;



import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.Entity;

/**
 *
 * @author Pedro
 */
@Entity
@Table(name = "usomaquina")
public class UsoMaquina {
    @Id
    @GeneratedValue
    @Column(name = "Id", unique = true, nullable = false)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "login")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "maquina")
    private Maquina maquina;
    @Column(name="inicio")
    private GregorianCalendar inicio;
    @Column(name="fim")
    private GregorianCalendar fim;
    @Column(name="ativo")
    private boolean ativo;
    @Column(name="aux")
    private int aux=0;
    
    
    public void incAux(){
        aux++;
    }
    public int getAux(){
        return aux;
    }
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public GregorianCalendar getInicio() {
        return inicio;
    }

    public void setInicio(GregorianCalendar inicio) {
        this.inicio = inicio;
    }

    public GregorianCalendar getFim() {
        return fim;
    }

    public void setFim(GregorianCalendar fim) {
        this.fim = fim;
    }

   public String tempo(){
        return this.inicio.get(Calendar.HOUR_OF_DAY)+":"+this.inicio.get(Calendar.MINUTE)+":"+this.inicio.get(Calendar.SECOND);
       
   }


    
}
