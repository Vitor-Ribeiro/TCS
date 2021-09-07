/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public interface UsuarioDao extends BaseDao<Usuario, Long>{
    
    List<Usuario> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
    
    Usuario pesquisarPorLogin(String login, Session sessao) throws HibernateException;
    
    List<Usuario> pesquisarTodo(Session sessao)throws HibernateException;
    
    
}
