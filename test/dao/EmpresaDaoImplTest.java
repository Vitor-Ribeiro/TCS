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
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class EmpresaDaoImplTest {
    private Session sessao;
    private Empresa empresa;
    private EmpresaDao empresaDao;
    
    public EmpresaDaoImplTest() {
        empresaDao = new EmpresaDaoImpl();
    }
    
    //@Test
    public void testSalvar() {
    }

//    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
        Long id = null;
        Session sessao = null;
        EmpresaDaoImpl instance = new EmpresaDaoImpl();
        Empresa expResult = null;
        Empresa result = instance.pesquisarPorId(id, sessao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

//    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        String nome = "";
        Session sessao = null;
        EmpresaDaoImpl instance = new EmpresaDaoImpl();
        List<Empresa> expResult = null;
        List<Empresa> result = instance.pesquisarPorNome(nome, sessao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

//    @Test
    public void testPesquisarTodo() {
        System.out.println("pesquisarTodo");
        Session sessao = null;
        EmpresaDaoImpl instance = new EmpresaDaoImpl();
        List<Empresa> expResult = null;
        List<Empresa> result = instance.pesquisarTodo(sessao);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPesquisarMensagemAlerta() {
        System.out.println("pesquisarMensagemAlerta");
        buscarEmpresaBd();
        sessao = HibernateUtil.abrirSessao();        
        Empresa empresaResultado = empresaDao.pesquisarMensagemAlerta(empresa.getPeriodo(), sessao);
        System.out.println(empresaResultado);
    }
    
    public Empresa buscarEmpresaBd() {
        sessao = HibernateUtil.abrirSessao();
        try {
            Query consulta = sessao.createQuery("FROM Empresa");
            List<Empresa> empresas = consulta.list();
            sessao.close();
            if (empresas.isEmpty()) {
                testSalvar();
            } else {
               empresa = empresas.get(0);                              
            }
        } catch (HibernateException e) {
            System.err.println("Erro ao pesquisar buscarEmpresaBd " + e.getMessage());
        }
        return empresa;
    }
    
}
