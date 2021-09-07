/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author User
 */
@Entity
@Table(name = "periodo")
public class Periodo implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date matutinoInicial;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date matutinoFinal;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date vespertinoInicial;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date vespertinoFinal;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date noturnoInicial;
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date noturnoFinal;
    
    @OneToOne
    @JoinColumn(name = "idEmpresa")
    private Empresa empresa;

    public Date getMatutinoInicial() {
        return matutinoInicial;
    }

    public void setMatutinoInicial(Date matutinoInicial) {
        this.matutinoInicial = matutinoInicial;
    }

    public Date getMatutinoFinal() {
        return matutinoFinal;
    }

    public void setMatutinoFinal(Date matutinoFinal) {
        this.matutinoFinal = matutinoFinal;
    }

    public Date getVespertinoInicial() {
        return vespertinoInicial;
    }

    public void setVespertinoInicial(Date vespertinoInicial) {
        this.vespertinoInicial = vespertinoInicial;
    }

    public Date getVespertinoFinal() {
        return vespertinoFinal;
    }

    public void setVespertinoFinal(Date vespertinoFinal) {
        this.vespertinoFinal = vespertinoFinal;
    }

    public Date getNoturnoInicial() {
        return noturnoInicial;
    }

    public void setNoturnoInicial(Date noturnoInicial) {
        this.noturnoInicial = noturnoInicial;
    }

    public Date getNoturnoFinal() {
        return noturnoFinal;
    }

    public void setNoturnoFinal(Date noturnoFinal) {
        this.noturnoFinal = noturnoFinal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final Periodo other = (Periodo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
