/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Produto;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public interface ProdutoDao extends BaseDao<Produto, Long>{
    
    List<Produto> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
    
    List<Produto> pesquisarTodo(Session sessao)throws HibernateException;
}
