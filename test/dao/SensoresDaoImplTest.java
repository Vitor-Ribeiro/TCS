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
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class SensoresDaoImplTest {

    SensoresDao sensoresDao;

    public SensoresDaoImplTest() {
        sensoresDao = new SensoresDaoImpl();
    }

   // @Test
    public void testListarSensores() {

        Session sessao = HibernateUtil.abrirSessao();
        List<Sensor> sensores = sensoresDao.listarSensores(sessao);
        System.out.println(sensores.get(0));
    }

    @Test
    public void testListarTodos() {
        System.out.println("listarTodos");
        Session sessao = HibernateUtil.abrirSessao();
        List<SensorGraficoHumidade> sensores = sensoresDao.listarTodos(sessao);
        System.out.println(sensores.get(0).getMediaHumidade());
    }

}
