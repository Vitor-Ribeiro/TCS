/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Sensor;
import modelo.SensorGraficoGasToxico;
import modelo.SensorGraficoHumidade;
import modelo.SensorGraficoLuminosidade;
import modelo.SensorGraficoMonoxidoCarbono;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author User
 */
public interface SensoresDao {

    List<Sensor> listarSensores(Session sessao) throws HibernateException;

    List<SensorGraficoHumidade> listarTodos(Session sessao) throws HibernateException;
    
    List<SensorGraficoGasToxico> listarTodosT(Session sessao) throws HibernateException;
    
    List<SensorGraficoMonoxidoCarbono> listarTodosM(Session sessao) throws HibernateException;
    
    List<SensorGraficoLuminosidade> listarTodosI(Session sessao) throws HibernateException;
    
    void alterar(Sensor sensor, Session sessao) throws HibernateException;
    
    
}
