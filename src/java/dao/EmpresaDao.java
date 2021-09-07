/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Empresa;
import modelo.Periodo;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public interface EmpresaDao extends BaseDao<Empresa, Long>{
    
    List<Empresa> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
    
    List<Empresa> pesquisarTodo(Session sessao)throws HibernateException;
    
    Empresa pesquisarMensagemAlerta(Periodo periodo, Session sessao)throws HibernateException;
}
