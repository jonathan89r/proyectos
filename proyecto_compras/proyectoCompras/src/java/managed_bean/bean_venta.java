/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import mantenimiento.mantenimiento_producto;
import mantenimiento.mantenimiento_venta;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import persistencia.DetVent;
import persistencia.TabProducto;
import persistencia.TabVenta;

/**
 *
 * @author omar.hernandezusam
 */
@ManagedBean
@RequestScoped
public class bean_venta implements Serializable {
    
    @PostConstruct
    public void init() {
        createChart();
    }
    
    private int id_venta;
    private String codigo;
    private int cantidad;
    private double total;
    private List listCodigo;
    private List listaVenta;
    
    public int getId_venta() {
        return id_venta;
    }
    
    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    
    public List getListCodigo() {
        return listCodigo;
    }
    
    public void setListCodigo(List listCodigo) {
        this.listCodigo = listCodigo;
    }
    
    public List<TabVenta> getListaVenta() {
        mantenimiento_venta m = new mantenimiento_venta();
        return m.consultar();
    }
    
    public void setListVenta(List listaVenta) {
        this.listaVenta = listaVenta;
    }
    
    public void guardarVenta() {
        mantenimiento_venta m = new mantenimiento_venta();
        TabVenta venta = new TabVenta();
        TabProducto producto = new TabProducto();
        String msg = null;
        
        venta.setIdVenta(id_venta);
        producto.setCodigo(codigo);
        venta.setCodigo(producto);
        venta.setCantidad(cantidad);
        venta.setTotal(total);
        
        if (m.guardar(venta) == 1) {
            msg = "Guardando Datos";
        } else {
            msg = "Error al guardar datos";
        }
        FacesMessage ms = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage(null, ms);
        
    }
    
    public void eliminarVenta(int id) {
        mantenimiento_venta m = new mantenimiento_venta();
        String msg = null;
        if (m.eliminar(id) == 1) {
            msg = "Eliminado";
        } else {
            msg = "Error al eliminar";
        }
        FacesMessage delete = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage(null, delete);
    }
    
    public void consultarVentaPorId(int id) {
        mantenimiento_venta m = new mantenimiento_venta();
        TabVenta v = null;
        v = m.consultarId(id);
        if (v != null) {
            this.id_venta = v.getIdVenta();
            this.codigo = v.getCodigo().getCodigo();
            this.cantidad = v.getCantidad();
            this.total = v.getTotal();
        }
    }
    
    public void actualizarVenta(int id) {
        mantenimiento_venta m = new mantenimiento_venta();
        TabVenta v = new TabVenta();
        TabProducto p = new TabProducto();
        String msg = null;
        
        v.setIdVenta(getId_venta());
        p.setCodigo(getCodigo());
        v.setCodigo(p);
        v.setCantidad(getCantidad());
        v.setTotal(getTotal());
        
        if (m.actualizar(v) == 1) {
            msg = "Datos Actualizados";
        } else {
            msg = "Error al Actualizar datos";
        }
        FacesMessage mensaje = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        
    }
    
    public void imprimir() {
        
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            
            PdfWriter.getInstance(document, baos);
            document.open();
            
            PdfPTable table = new PdfPTable(6);
//            float[] medida = {2.40f, 6.70f, 7.70f};
//            table.setWidths(medida);

            mantenimiento_venta mans = new mantenimiento_venta();
            List<DetVent> allist = mans.consultarVentas();
            
            PdfPCell cell = new PdfPCell(new Paragraph("\n" + "Ventas" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            14, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setColspan(6);
            table.addCell(cell);
            
            PdfPCell cellCodigo = new PdfPCell(new Paragraph("\n" + "Codigo del producto" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellCodigo.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellCodigo);
            
            PdfPCell cellNombre = new PdfPCell(new Paragraph("\n" + "Nombre del producto" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellNombre.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellNombre);
            
            PdfPCell cellCategoria = new PdfPCell(new Paragraph("\n" + "Comprador" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK
                    )));
            cellCategoria.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellCategoria.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellCategoria);
            
            PdfPCell cellPrecio = new PdfPCell(new Paragraph("\n" + "Precio" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellPrecio.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellPrecio.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellPrecio);
            
            PdfPCell cellProveedor = new PdfPCell(new Paragraph("\n" + "Stock" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellProveedor.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellProveedor.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellProveedor);
            
            PdfPCell cellCantidad = new PdfPCell(new Paragraph("\n" + "Cantidad vendida" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellCantidad.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellCantidad.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellCantidad);
            
            for (DetVent detVent : allist) {
                
                table.addCell(detVent.getCodigo());
                table.addCell(detVent.getNombre());
                table.addCell(detVent.getComprador());
                table.addCell("$" + detVent.getPrecio().toString());
                table.addCell(detVent.getStock().toString());
                table.addCell(detVent.getCantidad().toString());
            }
            
            document.add(table);
        } catch (Exception e) {
            System.err.println("Ocurrio un error al crear el archivo");
        }
        document.close();
        
        FacesContext context = FacesContext.getCurrentInstance();
        Object response = context.getExternalContext().getResponse();
        
        if (response instanceof HttpServletResponse) {
            HttpServletResponse hsr = (HttpServletResponse) response;
            hsr.setContentType("application/pdf;charset=UTF-8");
            hsr.setHeader("Content-Disposition", "filename=" + "ventas.pdf");
            hsr.setContentLength(baos.size());
            
            try {
                ServletOutputStream out = hsr.getOutputStream();
                baos.writeTo(out);
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            context.responseComplete();
        }
    }

//    //GRAFICA DE COMPRAS POR MES
//    private BarChartModel monthPurchaseChart;
//
//    public BarChartModel getMonthPurchaseChart() {
//        return monthPurchaseChart;
//    }
//
////GRAFICO DE BARRA COMPRAS POR MES
//    private BarChartModel initMonthPurchaseChart() {
//        BarChartModel model = new BarChartModel();
//        List<ViewMonthPurchase> Pm = mPurchaseEJB.findAll();
//
//        ChartSeries month = new ChartSeries();
//
//        for (int i = 0; i < Pm.size(); i++) {
//            month.set(Pm.get(i).getMonthName(), Pm.get(i).getMonthTotalPurchase());
//        }
//
//        model.addSeries(month);
//        return model;
//    }
////CREACIÓN DE GRAFICOS
//
//    private void createMonthPurchaseChart() {
//        monthPurchaseChart = initMonthPurchaseChart();
//
//        monthPurchaseChart.setTitle("COMPRAS 2019");
//
//        Axis xAxis = monthPurchaseChart.getAxis(AxisType.X);
//        xAxis.setLabel("MESES");
//
//        Axis yAxis = monthPurchaseChart.getAxis(AxisType.Y);
////        yAxis.setLabel("DINERO");
////        yAxis.setMin(0);
////        yAxis.setMax(200);
//    }
//    ------------------------------------------------------------------------------------------
    private BarChartModel monthPurchaseChart;

    /**
     * @return the monthPurchaseChart
     */
    public BarChartModel getMonthPurchaseChart() {
        return monthPurchaseChart;
    }

//    ------------------------------------------------------------------------------------------------
    private BarChartModel initmonBarChart() {
        BarChartModel model = new BarChartModel();
        mantenimiento_venta m = new mantenimiento_venta();
        List<TabVenta> pm = m.consultar();
        
        ChartSeries month = new ChartSeries();
        
        for (int i = 0; i < pm.size(); i++) {
            month.set(pm.get(i).getCodigo().getNombre(), pm.get(i).getCantidad());
        }
        
        model.addSeries(month);
        return model;
    }

    //    --------------------------------------------------------------------------------------------
    private void createChart() {
        monthPurchaseChart = initmonBarChart();
        
        getMonthPurchaseChart().setTitle("Ventas 2019");
        
        Axis xaxis = getMonthPurchaseChart().getAxis(AxisType.X);
        xaxis.setLabel("Productos");
        
        Axis yAxis = getMonthPurchaseChart().getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad de productos vendida");
    }
    
}
