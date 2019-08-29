/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import mantenimiento.mantenimiento_pedido;
import mantenimiento.mantenimiento_venta;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import persistencia.TabPedido;
import persistencia.TabVenta;

/**
 *
 * @author jonathan.rodriguez
 */
@ManagedBean
@RequestScoped
public class ClaseGrafico implements Serializable {

    private LineChartModel lineModel;

    LineChartModel model = new LineChartModel();
    LineChartSeries seriel = new LineChartSeries();
    private LineChartSeries seriel2 = new LineChartSeries();

    public LineChartModel getLineModel() {
        return lineModel;
    }

    @PostConstruct
    public void init() {
        crearLineasModelo();
    }

    private LineChartModel iniciarModeloLinear() {
        seriel.setLabel("producto 1");
        mantenimiento_venta m = new mantenimiento_venta();
        List<TabVenta> p = m.consultar();

        for (int i = 0; i < p.size(); i++) {
//            int intRandom = (int) (Math.random() * 50);
//            seriel.getData().put(i, intRandom);
            seriel.set(p.get(i).getIdVenta(), p.get(i).getCantidad());
        }
        model.addSeries(seriel);
        
        
        
        return model;
    }

    public void crearLineasModelo() {
        lineModel = iniciarModeloLinear();

        lineModel.setTitle("Ventas");
        lineModel.setLegendPosition("e");

        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setTickFormat("%d");
        yAxis.setLabel("Cantidad vendida");

        Axis xaAxis = lineModel.getAxis(AxisType.X);
        yAxis.setTickFormat("%d");
        

    }

    /**
     * @return the seriel2
     */
    public LineChartSeries getSeriel2() {
        return seriel2;
    }

    /**
     * @param seriel2 the seriel2 to set
     */
    public void setSeriel2(LineChartSeries seriel2) {
        this.seriel2 = seriel2;
    }

}
