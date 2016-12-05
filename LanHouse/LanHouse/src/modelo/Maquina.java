package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "maquina")
    
public class Maquina implements Serializable{
   
    @Id
    @Column(name="id")
    private int id;
    @Column(name="ip")
    private String ip;
    @Column(name = "removido")
    private boolean removido;
    @Column(name= "usado")
    private Long usado;
//    @OneToMany(mappedBy = "maquina", targetEntity = UsoMaquina.class)
//    List<UsoMaquina> usos;
//    
     public Maquina(){
        
    }

    public Maquina(String ip, int id) {
        this.ip = ip;
        this.id = id;
        this.usado=new Long(0);
    }
    
    public String getIp(){
        return ip;
    }
    
    public void setIp(String ip){
        this.ip = ip;
    }
    
    public String getId(){
        return Integer.toString(id);
    }

    public void setId (int id){
        this.id = id;
    }

    public boolean isRemovido() {
        return removido;
    }

    public void setRemovido(boolean removido) {
        this.removido = removido;
    }

    public Long getUsado() {
        return usado;
    }

    public void setUsado(Long usado) {
        this.usado = usado;
    }
    

}
