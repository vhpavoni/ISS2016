/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
/**
 *
 * @author Pedro
 */
@Entity
@Table(name="ordemservico")
public class OrdemServico {
     
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;
    @Column(name = "obs")
    private String obs;
    @Column(name="removido")
    private boolean removido;
    @Column(name="maquina")
    private String maquina;
    @Column(name="datasolicitada")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date solicitada;
    
    @Column(name="dataterminada")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date terminada;
    

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }

    public Date getSolicitada() {
        return solicitada;
    }

    public void setSolicitada(Date solicitada) {
        this.solicitada = solicitada;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public boolean isRemovido() {
        return removido;
    }

    public void setRemovido(boolean removido) {
        this.removido = removido;
    }

    public Date getTerminada() {
        return terminada;
    }

    public void setTerminada(Date terminada) {
        this.terminada = terminada;
    }
    
    
    
}
