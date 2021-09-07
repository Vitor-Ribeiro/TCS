/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.io.Serializable;
import java.util.List;
import modelo.Empresa;
import modelo.Periodo;
import modelo.Produto;
import modelo.Sensor;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class EmpresaDaoImpl extends BaseDaoImpl<Empresa, Long> implements EmpresaDao, Serializable{
    
    @Override
    public Empresa pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Empresa) sessao.get(Empresa.class, id);
    }

    @Override
    public List<Empresa> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("FROM Empresa WHERE nome LIKE :nomeHQL");
        consulta.setParameter("nomeHQL", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public List<Empresa> pesquisarTodo(Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("FROM Empresa");
        return consulta.list();
    }

    @Override
    public Empresa pesquisarMensagemAlerta(Periodo periodo, Session sessao) throws HibernateException {
        
        Query consulta = sessao.createQuery("SELECT DISTINCT(p) FROM Produto p join fetch p.sensores s WHERE "
                + "s.dt_momento NOT BETWEEN  :m_inicial and :m_final "
                + " and "
                + "s.dt_momento NOT BETWEEN :v_inicial and :v_final "
                + " and "
                + "s.dt_momento NOT BETWEEN :n_inicial and :n_final"                             
                + " and s.leitura_alerta = false");  
        consulta.setParameter("m_inicial", periodo.getMatutinoInicial());
        consulta.setParameter("m_final", periodo.getMatutinoFinal());
        consulta.setParameter("v_inicial", periodo.getVespertinoInicial());
        consulta.setParameter("v_final", periodo.getVespertinoFinal());
        consulta.setParameter("n_inicial", periodo.getNoturnoInicial());
        consulta.setParameter("n_final", periodo.getNoturnoFinal());
        
//         (curtime() BETWEEN horario_inicial AND horario_final) 
//          and day(CURDATE()) = day(dt_momento) and leitura_alerta = false;
        Empresa empresa = new Empresa();
        List<Produto> produtos = consulta.list();
        empresa.setProdutos(produtos);
        return empresa;
    }
    
}
