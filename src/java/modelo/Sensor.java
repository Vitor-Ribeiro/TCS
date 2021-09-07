package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "sensores")
public class Sensor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dt_momento;

    private float temperatura;

    private float humidade;
    
    private float luminosidade;
    @Column(name = "gas_toxico")
    private float gasToxico;
    @Column(name = "monoxido_de_carbono")
    private float monoxidoDeCarbono;
    
    private String corrente;
    
    private boolean leitura_alerta;
    
    private String serial;
    
    
    @ManyToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDt_momento() {
        return dt_momento;
    }

    public void setDt_momento(Date dt_momento) {
        this.dt_momento = dt_momento;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float getHumidade() {
        return humidade;
    }

    public void setHumidade(float humidade) {
        this.humidade = humidade;
    }

    public float getLuminosidade() {
        return luminosidade;
    }

    public void setLuminosidade(float luminosidade) {
        this.luminosidade = luminosidade;
    }    

    public float getGasToxico() {
        return gasToxico;
    }

    public void setGasToxico(float gasToxico) {
        this.gasToxico = gasToxico;
    }

    public float getMonoxidoDeCarbono() {
        return monoxidoDeCarbono;
    }

    public void setMonoxidoDeCarbono(float monoxidoDeCarbono) {
        this.monoxidoDeCarbono = monoxidoDeCarbono;
    }
        
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public boolean isLeitura_alerta() {
        return leitura_alerta;
    }

    public void setLeitura_alerta(boolean leitura_alerta) {
        this.leitura_alerta = leitura_alerta;
    }

    public String getCorrente() {
        return corrente;
    }

    public void setCorrente(String corrente) {
        this.corrente = corrente;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sensor other = (Sensor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

    

}
