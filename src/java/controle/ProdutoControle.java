/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import br.com.junior.rest.EmpresaRest;
import br.com.junior.rest.ProdutoRest;
import dao.EmpresaDao;
import dao.EmpresaDaoImpl;
import dao.HibernateUtil;
import dao.ProdutoDao;
import dao.ProdutoDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import modelo.Empresa;
import modelo.Produto;
import modelo.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Silvio
 */
@ManagedBean(name = "produtoC")
@ViewScoped
public class ProdutoControle {

    private Produto produto;
    private ProdutoDao produtoDao;
    private Empresa empresa;
    private Session sessao;
    private DataModel<Produto> modelProdutos;
    private ProdutoRest produtoRest;
    private int numeroAba = 0;
    private List<SelectItem> itensEmpresas;
    private EmpresaRest empresaRest;
    
    public ProdutoControle() {
        carregarComboboxEmpresa();
        
    }
    
     public void onTabChange(TabChangeEvent event) {
        if (event.getTab().getTitle().equals("Novo")) {
            if (itensEmpresas == null) {
                carregarComboboxEmpresa();
            }
        }
    }
     
     public void limpar(){
        produto = new Produto();
        empresa = new Empresa();
    }

    public void carregarComboboxEmpresa() {
        EmpresaDao empresaDao = new EmpresaDaoImpl();
        sessao = HibernateUtil.abrirSessao();
        itensEmpresas = new ArrayList<>();
        try {
            List<Empresa> listaEmpresas = empresaDao.pesquisarTodo(sessao);
            for (Empresa emp : listaEmpresas) {
                itensEmpresas.add(new SelectItem(emp.getId(), emp.getNome()));
            }
        } catch (HibernateException e) {
            System.out.println("Erro ao carregar a lista");
        } finally {
            sessao.close();
        }
    }
    

    public void pesquisarPorNome() {
        try {
            produtoDao = new ProdutoDaoImpl();
            sessao = HibernateUtil.abrirSessao();
            List<Produto> produtos = produtoDao.pesquisarPorNome(produto.getNome(), sessao);
            modelProdutos = new ListDataModel(produtos);            
        } catch (HibernateException e) {
            System.err.println("Erro ao pesquisar por nome - controle " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void excluir() {
        produto = modelProdutos.getRowData();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            sessao = HibernateUtil.abrirSessao();
            produtoDao = new ProdutoDaoImpl();
            produtoDao.remover(produto, sessao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído com sucesso!", ""));
            limpar();
            produto.setNome(null);
            modelProdutos = null;
        } catch (HibernateException e) {
            System.out.println("Erro ao excluir " + e.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir!", ""));
        } finally {
            sessao.close();
        }
    }

    public void alterar() {
        produto = modelProdutos.getRowData();
        empresa = produto.getEmpresa();
        numeroAba = 1;
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        produtoDao = new ProdutoDaoImpl();
        sessao = HibernateUtil.abrirSessao();      
        try {           
            produto.setEmpresa(empresa);
            produtoDao.salvarOuAlterar(produto, sessao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", ""));
            limpar();            
            numeroAba = 0;
            modelProdutos = null;
        } catch (HibernateException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", ""));
        } finally {
            sessao.close();
        }
        
        produto = null;
    }

//    getters e setters 

    public Session getSessao() {
        return sessao;
    }

    public void setSessao(Session sessao) {
        this.sessao = sessao;
    }

    public DataModel<Produto> getModelProdutos() {
        return modelProdutos;
    }

    public void setModelProdutos(DataModel<Produto> modelProdutos) {
        this.modelProdutos = modelProdutos;
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

    public int getNumeroAba() {
        return numeroAba;
    }

    public void setNumeroAba(int numeroAba) {
        this.numeroAba = numeroAba;
    }

    public Empresa getEmpresa() {
        if (empresa == null) {
            empresa = new Empresa();
        }
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public ProdutoRest getProdutoRest() {
        return produtoRest;
    }

    public void setProdutoRest(ProdutoRest produtoRest) {
        this.produtoRest = produtoRest;
    }

    public List<SelectItem> getItensEmpresas() {
        return itensEmpresas;
    }    

    public EmpresaRest getEmpresaRest() {
        return empresaRest;
    }

    public void setEmpresaRest(EmpresaRest empresaRest) {
        this.empresaRest = empresaRest;
    }

    public ProdutoDao getProdutoDao() {
        return produtoDao;
    }

    public void setProdutoDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }
    
    

}
