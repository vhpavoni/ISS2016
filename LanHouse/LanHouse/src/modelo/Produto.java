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
@Table(name = "produto")

public class Produto implements Serializable{
   
    @Id
    @GeneratedValue
    @Column(name = "cod", unique = true, nullable = false)
    private Long cod;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "descricao")
    private String descricao;    
    @Column(name = "preco")
    private float preco;
    @Column(name = "estoque")
    private int estoque;
    @Column(name = "dataCadastro")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;
    @Column(name = "removido")
    private boolean removido;
    
    public Produto() {
    }

    public Produto(String categoria, String marca, String modelo, String descricao,
            int estoque, float preco) {
        this.data =  new Date();
        this.categoria = categoria;
        this.marca = marca;
        this.modelo = modelo;
        this.descricao = descricao;
        this.estoque = estoque;
        this.preco = preco;
        this.removido = false;
    }

    public Long getCod() {
        return cod;
    }

    public void setCod(Long cod) {
        this.cod = cod;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public boolean isRemovido() {
        return removido;
    }

    public void setRemovido(boolean removido) {
        this.removido = removido;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }    
}
