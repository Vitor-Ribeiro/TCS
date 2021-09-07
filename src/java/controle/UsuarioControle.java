/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import br.com.junior.rest.EmpresaRest;
import dao.EmpresaDao;
import dao.EmpresaDaoImpl;
import dao.HibernateUtil;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
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
import modelo.Perfil;
import modelo.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author User
 */
@ManagedBean(name = "usuarioC")
@ViewScoped
public class UsuarioControle {
    private Usuario usuario;    
    private UsuarioDao usuarioDao;
    private Empresa empresa;
    private Session sessao;
    private DataModel<Usuario> modelUsuarios;
    private int numeroAba = 0;
    private List<SelectItem> itensEmpresas;
    private EmpresaRest empresaRest;
    
    public UsuarioControle(){
        carregarComboboxEmpresa();              
    }  
    
    public void limpar(){
        usuario = new Usuario();
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
            usuarioDao = new UsuarioDaoImpl();
            sessao = HibernateUtil.abrirSessao();
            List<Usuario> usuarios = usuarioDao.pesquisarPorNome(usuario.getNome(), sessao);
            modelUsuarios = new ListDataModel(usuarios);           
        } catch (HibernateException e) {
            System.err.println("Erro ao pesquisar por nome - controle " + e.getMessage());
        } finally {
            sessao.close();
        }
    }

    public void excluir() {
        usuario = modelUsuarios.getRowData();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            sessao = HibernateUtil.abrirSessao();
            usuarioDao = new UsuarioDaoImpl();
            usuarioDao.remover(usuario, sessao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluído com sucesso!", ""));
            limpar();
            modelUsuarios = null;
        } catch (HibernateException e) {
            System.out.println("Erro ao excluir " + e.getMessage());
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir!", ""));
        } finally {
            sessao.close();
        }
    }

    public void alterar() {
        usuario = modelUsuarios.getRowData();
        empresa = usuario.getEmpresa();        
        numeroAba = 1;
    }

    public void salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        usuarioDao = new UsuarioDaoImpl();
        sessao = HibernateUtil.abrirSessao();        
        try {           
            usuario.setEmpresa(empresa);
            Perfil perfil = new Perfil();
            perfil.setId(2L);
            usuario.setPerfil(perfil);
            usuario.setEnable(true);
            usuarioDao.salvarOuAlterar(usuario, sessao);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Salvo com sucesso!", ""));
            limpar();
            numeroAba = 0;
            modelUsuarios = null;
        } catch (HibernateException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao salvar!", ""));
        } finally {
            sessao.close();
        }
        
        
    }
    
    public Usuario getUsuario() {
        if(usuario == null){
         usuario = new Usuario();
        }
            
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    public Empresa getEmpresa() {
        if(empresa == null){
            empresa = new Empresa();
        }
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Session getSessao() {
        return sessao;
    }

    public void setSessao(Session sessao) {
        this.sessao = sessao;
    }

    public DataModel<Usuario> getModelUsuarios() {
        return modelUsuarios;
    }

    public void setModelUsuarios(DataModel<Usuario> modelUsuarios) {
        this.modelUsuarios = modelUsuarios;
    }

    public int getNumeroAba() {
        return numeroAba;
    }

    public void setNumeroAba(int numeroAba) {
        this.numeroAba = numeroAba;
    }

    public List<SelectItem> getItensEmpresas() {
        return itensEmpresas;
    }

    public void setItensEmpresas(List<SelectItem> itensEmpresas) {
        this.itensEmpresas = itensEmpresas;
    }

    public EmpresaRest getEmpresaRest() {
        return empresaRest;
    }

    public void setEmpresaRest(EmpresaRest empresaRest) {
        this.empresaRest = empresaRest;
    }
    
    
}
