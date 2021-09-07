/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import br.com.junior.rest.MedicaoRest;
import br.com.junior.rest.ProdutoRest;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import modelo.Medicao;
import modelo.Produto;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Silvio
 */
@ManagedBean(name = "medicaoC")
@ViewScoped
public class MedicaoControle {

    private Medicao medicao;
    private Produto produto;
    private DataModel<Medicao> medicoes;
    private MedicaoRest medicaoRest;
    private int numeroAba = 0;
    private List<SelectItem> itensProdutos;
    private ProdutoRest produtoRest;

    public MedicaoControle() {
        carregarComboboxProduto();
        
    }
    
    

    public String pesquisarPorMedicao() {
        medicaoRest = new MedicaoRest();
        medicoes = new ListDataModel(medicaoRest.listar());
        return "";
    }
    
    

    public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getTitle().equals("Novo")) {
            if (itensProdutos == null) {
                carregarComboboxProduto();
            }
        }
    }

    public void carregarComboboxProduto() {
        produtoRest = new ProdutoRest();
        itensProdutos = new ArrayList<>();
        try {
            List<Produto> listaProdutos = produtoRest.listar();
            for (Produto prod : listaProdutos) {
                itensProdutos.add(new SelectItem(prod.getId(), prod.getNome()));
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar a lista");

        }
    }

    public void excluir() {
        medicao = medicoes.getRowData();
        medicaoRest = new MedicaoRest();
        medicaoRest.excluir(medicao);
        medicao = null;
        medicoes = null;
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluída com sucesso!", ""));
    }

    public void alterar() {
        medicao = medicoes.getRowData();
        produto = medicao.getProduto();
        numeroAba = 1;
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        medicaoRest = new MedicaoRest();
        medicao.setProduto(produto);
        try {
            if (medicao.getId() == null) {               
                medicaoRest.salvar(medicao);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salva com sucesso!", ""));
            } else {
                medicaoRest.atualizar(medicao);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterada com sucesso!", ""));
            }

            numeroAba = 0;
            // } catch (SQLIntegrityConstraintViolationException e) {
        } catch (Exception e) {
            if (e.getMessage().contains("empresa.nome_UNIQUE")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alterado com sucesso!", ""));
            }
        }

        medicao = null;
    }

//    getters e setters 
    public Medicao getMedicao() {
        if (medicao == null) {
            medicao = new Medicao();
        }
        return medicao;
    }

    public void setMedicao(Medicao medicao) {
        this.medicao = medicao;
    }

    public Produto getProduto() {
        if (produto == null) {
            produto = new Produto();
        }
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public DataModel<Medicao> getMedicoes() {
        return medicoes;
    }

    public void setMedicoes(DataModel<Medicao> medicoes) {
        this.medicoes = medicoes;
    }

    public MedicaoRest getMedicaoRest() {
        return medicaoRest;
    }

    public void setMedicaoRest(MedicaoRest medicaoRest) {
        this.medicaoRest = medicaoRest;
    }

    public int getNumeroAba() {
        return numeroAba;
    }

    public void setNumeroAba(int numeroAba) {
        this.numeroAba = numeroAba;
    }

    public List<SelectItem> getItensProdutos() {
        return itensProdutos;
    }

    public ProdutoRest getProdutoRest() {
        return produtoRest;
    }

    public void setProdutoRest(ProdutoRest produtoRest) {
        this.produtoRest = produtoRest;
    }
    
    

}
