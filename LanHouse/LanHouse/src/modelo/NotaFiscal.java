package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "notaFiscal")
public class NotaFiscal implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "numero", unique = true, nullable = false)
    private Long numero;
    @Column(name = "idVenda")
    private Long venda;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dataemissao")
    private Date dataEmissao;
    @Column(name = "removido")
    private boolean removido;

    public NotaFiscal(Long venda) {
        this.venda = venda;
        this.dataEmissao = new Date();
        this.removido = false;
    }

    public NotaFiscal() {
    }

    
    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getVenda() {
        return venda;
    }

    public void setVenda(Long venda) {
        this.venda = venda;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public boolean isRemovido() {
        return removido;
    }

    public void setRemovido(boolean removido) {
        this.removido = removido;
    }
    
}
