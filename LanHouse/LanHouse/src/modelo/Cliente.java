
package modelo;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;


@Entity
@Table(name = "CLIENTE")
    
public class Cliente implements Serializable{
    
    @Id   
    @Column(name = "login",length=20,nullable=false)
    private String login;
    @Column(name = "senha",length=20,nullable=false)
    private String senha;
    @Column(name = "nome",length=50,nullable=false)
    private String nome;
    @Column(name = "telefone",length=20,nullable=false)
    private String telefone;
    @Column(name = "rg",length=20,nullable=false)
    private String rg;
    @Column(name = "endereco",length=60,nullable=false)
    private String endereco;
    @Column(name = "data_nascimento",length=20,nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_nascimento;
    @Column(name = "reais")
    private float credito;
    @Column(name = "removido")
    private boolean removido;
  

    public Cliente() {
    }

    public Cliente(String login, String senha, String nome, String telefone, String rg, String endereco, Date data_nascimento) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.telefone = telefone;
        this.rg = rg;
        this.endereco = endereco;
        this.data_nascimento = data_nascimento;
        this.credito = 0;
    }

    public void addCredito(float credito){
        this.credito += credito;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public boolean isRemovido() {
        return removido;
    }

    public void setRemovido(boolean removido) {
        this.removido = removido;
    }

    public float getCredito() {
        return credito;
    }

    public void setCredito(float credito) {
        this.credito = credito;
    }
    
}

    
