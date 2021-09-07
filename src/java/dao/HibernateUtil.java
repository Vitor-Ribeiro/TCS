/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Empresa;
import modelo.Endereco;
import modelo.Perfil;
import modelo.Periodo;
import modelo.Produto;
import modelo.Sensor;
import modelo.SensorGraficoGasToxico;
import modelo.SensorGraficoMonoxidoCarbono;
import modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Admin
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    //Singleton
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(Sensor.class);
            cfg.addAnnotatedClass(Empresa.class);
            cfg.addAnnotatedClass(Endereco.class);
            cfg.addAnnotatedClass(Produto.class);
            cfg.addAnnotatedClass(Periodo.class);
            cfg.addAnnotatedClass(Usuario.class);
            cfg.addAnnotatedClass(Perfil.class);
            
            
            
            
            
            
            cfg.configure("/dao/hibernate.cfg.xml");
            ServiceRegistry servico = new StandardServiceRegistryBuilder().
                    applySettings(cfg.getProperties()).build();
            
            sessionFactory = cfg.buildSessionFactory(servico);
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session abrirSessao() {
        return sessionFactory.openSession();
    }
}
