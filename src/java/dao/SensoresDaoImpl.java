/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.Sensor;
import modelo.SensorGraficoGasToxico;
import modelo.SensorGraficoHumidade;
import modelo.SensorGraficoLuminosidade;
import modelo.SensorGraficoMonoxidoCarbono;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author User
 */
public class SensoresDaoImpl implements SensoresDao, Serializable{

    @Override
    public List<Sensor> listarSensores(Session sessao) {
        
        Query consulta = sessao.createQuery("from Sensor");
        return consulta.list();
    }

    @Override
    public List<SensorGraficoHumidade> listarTodos(Session sessao) throws HibernateException {
        //select AVG(humidade), day(dt_momento) as dia from sensores WHERE MONTH(dt_momento) =  
        //month(now()) group by day(dt_momento) order by dia;
        Query consulta = sessao.createQuery("Select AVG(humidade), day(dt_momento) as dia from Sensor WHERE month(dt_momento) = "
                + "month(current_date()) group by day(dt_momento) order by dia");        
        List listaObjeto = consulta.list();
        Object[] itens;
        List<SensorGraficoHumidade> graficos = new ArrayList<>();
        SensorGraficoHumidade sensorGrafico;
        for (Object object : listaObjeto) {
            itens = (Object[]) object;
            sensorGrafico = new SensorGraficoHumidade();
            sensorGrafico.setMediaHumidade((Double) itens[0]);
            sensorGrafico.setDia((int) itens[1]);
            graficos.add(sensorGrafico);
        }
        
        
        return graficos;
    }

    @Override
    public void alterar(Sensor sensor, Session sessao) throws HibernateException {        
        Transaction transacao = sessao.beginTransaction();
        sessao.update(sensor);
        try {
            transacao.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getSimpleName() + " " + sensor + " " + e.getMessage());
            transacao.rollback();
        }
    }

    @Override
    public List<SensorGraficoMonoxidoCarbono> listarTodosM(Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("Select AVG(monoxidoDeCarbono), day(dt_momento) as dia from Sensor WHERE month(dt_momento) = "
                + "month(current_date()) group by day(dt_momento) order by dia");        
        List listaObjeto = consulta.list();
        Object[] itens;
        List<SensorGraficoMonoxidoCarbono> graficosM = new ArrayList<>();
        SensorGraficoMonoxidoCarbono sensorGraficoM;
        for (Object object : listaObjeto) {
            itens = (Object[]) object;
            sensorGraficoM = new SensorGraficoMonoxidoCarbono();
            sensorGraficoM.setMediaMonoxidoCarbono((double) itens[0]);
            sensorGraficoM.setDia((int) itens[1]);
            graficosM.add(sensorGraficoM);
        }
        
        
        return graficosM;
    }

    @Override
    public List<SensorGraficoGasToxico> listarTodosT(Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("Select AVG(gasToxico), day(dt_momento) as dia from Sensor WHERE month(dt_momento) = "
                + "month(current_date()) group by day(dt_momento) order by dia");        
        List listaObjeto = consulta.list();
        Object[] itens;
        List<SensorGraficoGasToxico> graficosT = new ArrayList<>();
        SensorGraficoGasToxico sensorGraficoT;
        for (Object object : listaObjeto) {
            itens = (Object[]) object;
            sensorGraficoT = new SensorGraficoGasToxico();
            sensorGraficoT.setMediaGasToxico((double) itens[0]);
            sensorGraficoT.setDia((int) itens[1]);
            graficosT.add(sensorGraficoT);
        }
        
        
        return graficosT;
    }

    @Override
    public List<SensorGraficoLuminosidade> listarTodosI(Session sessao) throws HibernateException {
        Query consulta = sessao.createQuery("Select AVG(luminosidade), day(dt_momento) as dia from Sensor WHERE month(dt_momento) = "
                + "month(current_date()) group by day(dt_momento) order by dia");        
        List listaObjeto = consulta.list();
        Object[] itens;
        List<SensorGraficoLuminosidade> graficosI = new ArrayList<>();
        SensorGraficoLuminosidade sensorGraficoI;
        for (Object object : listaObjeto) {
            itens = (Object[]) object;
            sensorGraficoI = new SensorGraficoLuminosidade();
            sensorGraficoI.setMediaLuminosidade((double) itens[0]);
            sensorGraficoI.setDia((int) itens[1]);
            graficosI.add(sensorGraficoI);
        }
        
        
        return graficosI;
    }
    
}
