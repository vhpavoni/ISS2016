package modelo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Iten")
public class Iten implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "idProd")
    private Long idProd;
    @Column(name = "qtde")
    private Long qtde;
    @ManyToOne
    @JoinColumn(name = "prodVenda_iten")
    private ProdutoVenda produtoVenda;
    
    public Iten() {
    }

    public Iten(Long idProd, Long qtde, ProdutoVenda produtoVenda) {
        this.idProd = idProd;
        this.qtde = qtde;
        this.produtoVenda = produtoVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }

    public Long getQtde() {
        return qtde;
    }

    public void setQtde(Long qtde) {
        this.qtde = qtde;
    }

    public ProdutoVenda getProdutoVenda() {
        return produtoVenda;
    }

    public void setProdutoVenda(ProdutoVenda produtoVenda) {
        this.produtoVenda = produtoVenda;
    }
        
}
