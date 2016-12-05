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
@Table(name = "venda")
public class Venda implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "idVenda", unique = true, nullable = false)
    private Long idVenda;
    @Column(name = "cliente")
    private String cliente;
    @Column(name = "idProdutoVenda")
    private Long produtoVenda;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dataVenda")
    private Date data;
    @Column(name = "notaFiscal")
    private Long notaFiscal;
    @Column(name = "precoTotal")
    private float precoTotal;
    @Column(name = "removido")
    private boolean removido;

    public Venda() {
    }

    public Venda(String cliente, Long produtoVenda, float precoTotal) {
        this.cliente = cliente;
        this.produtoVenda = produtoVenda;
        this.data = new Date();
        this.precoTotal = precoTotal;
        this.removido = false;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Long getProdutoVenda() {
        return produtoVenda;
    }

    public void setProdutoVenda(Long produtoVenda) {
        this.produtoVenda = produtoVenda;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(Long notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(float precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public boolean isRemovido() {
        return removido;
    }

    public void setRemovido(boolean removido) {
        this.removido = removido;
    }
    
    
}
