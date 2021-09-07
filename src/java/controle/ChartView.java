/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.EmpresaDao;
import dao.EmpresaDaoImpl;
import dao.HibernateUtil;
import dao.SensoresDao;
import dao.SensoresDaoImpl;
import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Empresa;
import modelo.Produto;
import modelo.Sensor;
import modelo.SensorGraficoGasToxico;
import modelo.SensorGraficoHumidade;
import modelo.SensorGraficoLuminosidade;
import modelo.SensorGraficoMonoxidoCarbono;
import modelo.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import util.EnviarEmail;

/**
 *
 * @author User
 */
@ManagedBean
@ViewScoped
public class ChartView {

    private BarChartModel barModel;
    private BarChartModel barModelM;
    private BarChartModel barModelI;
    private HorizontalBarChartModel horizontalBarModel;
    private List<SensorGraficoGasToxico> sensores;    
    private List<SensorGraficoMonoxidoCarbono> sensoresM; 
    private List<SensorGraficoLuminosidade> sensoresI; 
    private SensorGraficoGasToxico sensorGrafico;     
    private Usuario usuario;
    private EmpresaDao empresaDao;
    private String mensagem;
    private SensoresDao sensoresDao;
    private Session sessao;

    @PostConstruct
    public void init() {  
        sensoresDao = new SensoresDaoImpl();
        createBarModels();
        empresaDao = new EmpresaDaoImpl();       
    }
    
    
    
    public void pesquisarAlerta() {
        mensagem = "";
        try {
            usuario = UsuarioLogadoControle.getUserConected();
            sessao = HibernateUtil.abrirSessao();
            Empresa empresa = empresaDao.pesquisarMensagemAlerta(usuario.getEmpresa().getPeriodo(), sessao);
            if (!empresa.getProdutos().isEmpty()) {
                mensagem = "Ar Condicionado ligado: ";
                for (Produto produto : empresa.getProdutos()) {
                    mensagem += " - " + produto.getLocal();
                    for (Sensor sensor : produto.getSensores()) {
                        sensor.setLeitura_alerta(true);
                        sensoresDao.alterar(sensor, sessao);
                    }

                }
                if (usuario.isHabilitadoEmail()) {
                    EnviarEmail enviarEmail = new EnviarEmail();
                    enviarEmail.enviarEmailMensagem(mensagem, usuario);
                }

            }

        } catch (HibernateException e) {
            System.out.println("Erro ao pesquisar alerta! " + e.getMessage());
        } finally {
            sessao.close();
        }

    }

    public String getMensagem() {
        return mensagem;
    }
    
    public void habilitarEnvioDeEmail(){
        sessao = HibernateUtil.abrirSessao();
        try {
            usuario.setHabilitadoEmail(usuario.isHabilitadoEmail());
            UsuarioDao usuarioDao = new UsuarioDaoImpl();
            usuarioDao.salvarOuAlterar(usuario, sessao);
        } catch (HibernateException e) {
            System.out.println("Erro ao habilitar E-mail" + e.getMessage());
        } finally{
            sessao.close();
        }
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public BarChartModel getBarModelM() {
        return barModelM;
    }  

    public BarChartModel getBarModelI() {
        return barModelI;
    }  
    
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

//        ChartSeries boys = new ChartSeries();
//        boys.setLabel("Boys");
//        boys.set("2004", 200);
//        boys.set("2005", 100);
//        boys.set("2006", 44);
//        boys.set("2007", 150);
//        boys.set("2008", 25);
        ChartSeries sensorChart = new ChartSeries();
        sensorChart.setLabel("Medições");
        Session sessao = HibernateUtil.abrirSessao();
        try {
            sensores = sensoresDao.listarTodosT(sessao);
        } catch (HibernateException he) {
            System.out.println("erro ao pesquisar grafico " + he.getMessage());
        }
        sensores.forEach((sensor) -> {
            sensorChart.set(sensor.getDia(), sensor.getMediaGasToxico());
        });

//        model.addSeries(boys);
        model.addSeries(sensorChart);

        return model;
    }
    
    private BarChartModel initBarModelM() {
        BarChartModel modelM = new BarChartModel();

//        ChartSeries boys = new ChartSeries();
//        boys.setLabel("Boys");
//        boys.set("2004", 200);
//        boys.set("2005", 100);
//        boys.set("2006", 44);
//        boys.set("2007", 150);
//        boys.set("2008", 25);
        ChartSeries sensorChart = new ChartSeries();
        sensorChart.setLabel("Medições");
        Session sessao = HibernateUtil.abrirSessao();
        try {
            sensoresM = sensoresDao.listarTodosM(sessao);
        } catch (HibernateException he) {
            System.out.println("erro ao pesquisar grafico " + he.getMessage());
        }
        sensoresM.forEach((sensor) -> {
            sensorChart.set(sensor.getDia(), sensor.getMediaMonoxidoCarbono());
        });

//        model.addSeries(boys);
        modelM.addSeries(sensorChart);

        return modelM;
    }
    
    private BarChartModel initBarModelI() {
        BarChartModel modelI = new BarChartModel();

//        ChartSeries boys = new ChartSeries();
//        boys.setLabel("Boys");
//        boys.set("2004", 200);
//        boys.set("2005", 100);
//        boys.set("2006", 44);
//        boys.set("2007", 150);
//        boys.set("2008", 25);
        ChartSeries sensorChart = new ChartSeries();
        sensorChart.setLabel("Medições");
        Session sessao = HibernateUtil.abrirSessao();
        try {
            sensoresI = sensoresDao.listarTodosI(sessao);
        } catch (HibernateException he) {
            System.out.println("erro ao pesquisar grafico " + he.getMessage());
        }
        sensoresI.forEach((sensor) -> {
            sensorChart.set(sensor.getDia(), sensor.getMediaLuminosidade());
        });

//        model.addSeries(boys);
        modelI.addSeries(sensorChart);

        return modelI;
    }

    private void createBarModels() {
        createBarModel();
        createBarModelM();
        createBarModelI();
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Gráfico de Barra");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Dia");
        xAxis.setMax(31);

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Média de Gases Tóxicos");
        yAxis.setMin(0);
        yAxis.setMax(500);
    }
    
    private void createBarModelM() {
        barModelM = initBarModelM();

        barModelM.setTitle("Gráfico de Barra");
        barModelM.setLegendPosition("ne");

        Axis xAxis = barModelM.getAxis(AxisType.X);
        xAxis.setLabel("Dia");
        xAxis.setMax(31);

        Axis yAxis = barModelM.getAxis(AxisType.Y);
        yAxis.setLabel("Média de Monóxido de Carbono");
        yAxis.setMin(0);
        yAxis.setMax(500);
    }
    private void createBarModelI() {
        barModelI = initBarModelI();

        barModelI.setTitle("Gráfico de Barra");
        barModelI.setLegendPosition("ne");

        Axis xAxis = barModelI.getAxis(AxisType.X);
        xAxis.setLabel("Dia");
        xAxis.setMax(31);

        Axis yAxis = barModelI.getAxis(AxisType.Y);
        yAxis.setLabel("Média de Luminosidade");
        yAxis.setMin(0);
        yAxis.setMax(700);
    }
    

    public Usuario getUsuario() {
        if(usuario == null){
            usuario = UsuarioLogadoControle.getUserConected();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    
}
