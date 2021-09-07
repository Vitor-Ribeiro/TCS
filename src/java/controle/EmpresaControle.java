/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import br.com.junior.rest.CepRest;
import dao.EmpresaDao;
import dao.EmpresaDaoImpl;
import dao.HibernateUtil;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import modelo.Empresa;
import modelo.Endereco;
import modelo.Periodo;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Silvio
 */
@ManagedBean(name = "empresaC")
@ViewScoped
public class EmpresaControle {

    private Empresa empresa;
    private EmpresaDao empresaDao;
    private Session sessao;
    private Endereco endereco;
    private Periodo periodo;
    private DataModel<Empresa> modelEmpresas;
    private int numeroAba = 0;

    public void pesquisarPorNome() {
        try {
            empresaDao = new EmpresaDaoImpl();
            sessao = HibernateUtil.abrirSessao();
            List<Empresa> empresas = empresaDao.pesquisarPorNome(empresa.getNome(), sessao);
            modelEmpresas = new ListDataModel(empresas);
        } catch (HibernateException e) {
            System.err.println("Erro ao pesquisar por nome - controle " + e.getMessage());
        } finally {
            sessao.close();
        }
    }
    
    public void buscarCep(){
        CepRest cepRest = new CepRest();
        endereco = cepRest.pesquisarPorCep(endereco.getCep());
        if(endereco.getCep() == null){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Não foi encontrado ninguém com este cep!", ""));
        }
    }

    public void excluir() {
        empresa = modelEmpresas.getRowData();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            sessao = HibernateUtil.abrirSessao();
            empresaDao = new EmpresaDaoImpl();
            empresaDao.remover(empresa, sessao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído com sucesso!", ""));
            empresa.setNome(null);
            modelEmpresas = null;
        } catch (HibernateException e) {
            System.out.println("Erro ao excluir " + e.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir!", ""));
        } finally {
            sessao.close();
        }
    }

    public void alterar() {
        empresa = modelEmpresas.getRowData();
        endereco = empresa.getEndereco();
        periodo = empresa.getPeriodo();
        numeroAba = 1;

    }

    //caso der problema, colocar dentro do salvar estes dois atributos : 
    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        empresaDao = new EmpresaDaoImpl();
        sessao = HibernateUtil.abrirSessao();
        try {
            empresa.setEndereco(endereco);
            endereco.setEmpresa(empresa);
            empresa.setPeriodo(periodo);
            periodo.setEmpresa(empresa);
            empresaDao.salvarOuAlterar(empresa, sessao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", ""));
            empresa = new Empresa();
            numeroAba = 0;
            modelEmpresas = null;
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", ""));
        } finally {
            sessao.close();
        }
        endereco = null; 
        empresa = null;
    }

//    getters e setters 
    public Empresa getEmpresa() {
        if (empresa == null) {
            empresa = new Empresa();
        }
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Endereco getEndereco() {
        if (endereco == null) {
            endereco = new Endereco();
        }
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public DataModel<Empresa> getEmpresas() {
        return modelEmpresas;
    }

    public void setEmpresas(DataModel<Empresa> empresas) {
        this.modelEmpresas = empresas;
    }   

    public int getNumeroAba() {
        return numeroAba;
    }

    public void setNumeroAba(int numeroAba) {
        this.numeroAba = numeroAba;
    }

    public Periodo getPeriodo() {
        if (periodo == null) {
            periodo = new Periodo();
        }
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

}
