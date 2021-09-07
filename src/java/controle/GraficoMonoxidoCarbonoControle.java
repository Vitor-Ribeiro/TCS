/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.HibernateUtil;
import dao.SensoresDao;
import dao.SensoresDaoImpl;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.SensorGraficoMonoxidoCarbono;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

/**
 *
 * @author User
 */
@ManagedBean
@ViewScoped
public class GraficoMonoxidoCarbonoControle {
   
    private BarChartModel barModelM;    
    private HorizontalBarChartModel horizontalBarModel;   
    private List<SensorGraficoMonoxidoCarbono> sensoresM;   
    private SensorGraficoMonoxidoCarbono sensorGraficoM;      
    private SensoresDao sensoresDao;
    private Session sessao;

    @PostConstruct
    public void init() {  
        sensoresDao = new SensoresDaoImpl();
//        createBarModels();
               
    }

    public BarChartModel getBarModelM() {
        return barModelM;
    }       

    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }        
    
//    private BarChartModel initBarModelM(){
//        BarChartModel modelM = new BarChartModel();
//
////        ChartSeries boys = new ChartSeries();
////        boys.setLabel("Boys");
////        boys.set("2004", 200);
////        boys.set("2005", 100);
////        boys.set("2006", 44);
////        boys.set("2007", 150);
////        boys.set("2008", 25);
//        ChartSeries sensorChart = new ChartSeries();
//        sensorChart.setLabel("Medições");
//        sessao = HibernateUtil.abrirSessao();
//        try {
//            sensoresM = sensoresDao.listarTodosM(sessao);           
//        } catch (HibernateException he) {
//            System.out.println("erro ao pesquisar grafico " + he.getMessage());
//        }
//        sensoresM.forEach((sensor) -> {
//            sensorChart.set(sensor.getDia(), sensor.getMediaMonoxidoCarbono());
//        });
//
////        model.addSeries(boys);
//        modelM.addSeries(sensorChart);
//
//        return modelM;
//    }

//    private void createBarModels() {      
//        createBarModelM();
//
//    }
     
//    private void createBarModelM(){
//        barModelM = initBarModelM();      
//
//        barModelM.setTitle("Gráfico de Barra");
//        barModelM.setLegendPosition("ne");
//
//        Axis xAxis = barModelM.getAxis(AxisType.X);
//        xAxis.setLabel("Dia");
//        xAxis.setMax(31);
//
//        Axis yAxis = barModelM.getAxis(AxisType.Y);
//        yAxis.setLabel("Média de Monóxido de Carbono");
//        yAxis.setMin(0);
//        yAxis.setMax(50);
//    }    

    
}
