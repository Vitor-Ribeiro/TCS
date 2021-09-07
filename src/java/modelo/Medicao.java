package modelo;

import java.io.Serializable;
import java.util.Date;



public class Medicao implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private double valor;
    
    private String unidade;
    
    
    private Date dt_medicao;
    
    
    private Produto produto;
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Date getDt_medicao() {
        return dt_medicao;
    }

    public void setDt_medicao(Date dt_medicao) {
        this.dt_medicao = dt_medicao;
    }        

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

   

}
