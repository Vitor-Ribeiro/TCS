package controle;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name="GraficoAreaMB")
public class GraficoArea implements Serializable {

    private LineChartModel areaModel;

    @PostConstruct
    public void init() {
        createAreaModel();
    }

    public LineChartModel getAreaModel() {
        return areaModel;
    }

    private void createAreaModel() {
        areaModel = new LineChartModel();

        LineChartSeries reservatorio1 = new LineChartSeries();
        reservatorio1.setFill(true);
        reservatorio1.setLabel("Cantareira");
        reservatorio1.set("2011", 40);
        reservatorio1.set("2012", 38);
        reservatorio1.set("2013", 44);
        reservatorio1.set("2014", 40);
        reservatorio1.set("2015", 47);

        LineChartSeries reservatorio2 = new LineChartSeries();
        reservatorio2.setFill(true);
        reservatorio2.setLabel("Guarapiranga");
        reservatorio2.set("2011", 52);
        reservatorio2.set("2012", 55);
        reservatorio2.set("2013", 52);
        reservatorio2.set("2014", 57);
        reservatorio2.set("2015", 49);

        LineChartSeries reservatorio3 = new LineChartSeries();
        reservatorio3.setFill(true);
        reservatorio3.setLabel("Alto Tiête");
        reservatorio3.set("2011", 52);
        reservatorio3.set("2012", 55);
        reservatorio3.set("2013", 52);
        reservatorio3.set("2014", 57);
        reservatorio3.set("2015", 49);

        areaModel.addSeries(reservatorio1);
        areaModel.addSeries(reservatorio2);
        areaModel.addSeries(reservatorio3);

        areaModel.setTitle("Nível");
        areaModel.setLegendPosition("ne");
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(true);

        Axis xAxis = new CategoryAxis("Reservatório");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Nível");
        yAxis.setMin(0);
        yAxis.setMax(500);
    }

}