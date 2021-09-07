/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.HibernateUtil;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Empresa;
import modelo.Usuario;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author User
 */
@ManagedBean(name = "usuarioLogadoC")
@SessionScoped
public class UsuarioLogadoControle {
    private static Usuario usuarioLogado;    
    private UsuarioDao usuarioDao;    
    private Session sessao;
    
    public UsuarioLogadoControle(){       
        usuarioLogado = new Usuario();
        SecurityContext context = SecurityContextHolder.getContext();
        if(context instanceof SecurityContext){
            Authentication authentication = context.getAuthentication();
            if(authentication instanceof Authentication){
                usuarioLogado.setLogin(((User)authentication.getPrincipal()).getUsername());
            }
        }
        pesquisarUsuario();
    }
    
    public static Usuario getUserConected(){
        return usuarioLogado;
    }
    
    private void pesquisarUsuario() {
        sessao = HibernateUtil.abrirSessao();
        try {
            usuarioDao = new UsuarioDaoImpl();
            usuarioLogado = usuarioDao.pesquisarPorLogin(usuarioLogado.getLogin(), sessao);
        } catch (Exception e) {
        }
    }
    
    
    
    
    
    
    
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    
    
    
}
