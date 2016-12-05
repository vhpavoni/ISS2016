package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "produtoVenda")

public class ProdutoVenda implements Serializable {
    
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @OneToMany (mappedBy = "produtoVenda",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Iten> itens;
    @Column(name = "removido")
    private boolean removido;

    public ProdutoVenda() {
        this.itens = new ArrayList<Iten>();
        
    }

    public Iten getIten(Long cod){
        for(Iten iten : itens){
            if (iten.getIdProd().equals(cod)){
                return iten;
            }
        }
        return null;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Iten> getItens() {
        return itens;
    }

    public void setItens(List<Iten> produtos) {
        this.itens = produtos;
    }

    public boolean isRemovido() {
        return removido;
    }

    public void setRemovido(boolean removido) {
        this.removido = removido;
    }

}
