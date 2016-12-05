package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;


@SuppressWarnings("serial")  
@Entity
@Table(name = "funcionario") 
@AttributeOverrides({
    @AttributeOverride(name="nome", column=@Column(name="nome")),
    @AttributeOverride(name="email", column=@Column(name="email")),
    @AttributeOverride(name="celular", column=@Column(name="celular")),
    @AttributeOverride(name="residencial", column=@Column(name="residencial")),
    @AttributeOverride(name="nascimento", column=@Column(name="dataNascimento")),
    @AttributeOverride(name="sexo", column=@Column(name="sexo"))
})
public class Funcionario implements Serializable{
    @Id
    @Column(name = "login")
    private String login;
    @Column(name = "senha")
    private String senha;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "celular")
    private String celular;
    @Column(name = "residencial")
    private String residencial;
    @Column(name = "dataNascimento")
    private String nascimento;
    @Column(name = "removido")
    private boolean removido;

    public Funcionario() {
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getResidencial() {
        return residencial;
    }

    public void setResidencial(String residencial) {
        this.residencial = residencial;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public boolean isRemovido() {
        return removido;
    }

    public void setRemovido(boolean removido) {
        this.removido = removido;
    }

    public Funcionario(String login, String senha, String nome, String email, String celular, String residencial, String nascimento) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.residencial = residencial;
        this.nascimento = nascimento;
    }


    


}
