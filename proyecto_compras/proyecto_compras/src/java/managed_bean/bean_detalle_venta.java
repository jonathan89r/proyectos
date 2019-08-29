/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_detalle_venta;
import persistencia.TabDetalleVenta;
import persistencia.TabVenta;

/**
 *
 * @author jonathan.rodriguez
 */
@ManagedBean
@RequestScoped
public class bean_detalle_venta {

    private int id_detalle_venta;
    private int id_venta;
    private String fecha;
    private String comprador;

    private List<TabDetalleVenta> listadeta;
    private List<TabVenta> listaVentas;

    public bean_detalle_venta() {
    }

    //    funcion para guardar datos
    public void guardar() {
        mantenimiento_detalle_venta man = new mantenimiento_detalle_venta();
        TabDetalleVenta det = new TabDetalleVenta();
        TabVenta ve = new TabVenta();

        det.setIdDetalle(id_detalle_venta);
        ve.setIdVenta(id_venta);
        det.setIdVenta(ve);

        det.setFecha(fecha);
        det.setComprador(comprador);

        String advertencia = "";

        if (man.guardar(det) == 1) {
            advertencia = "correcto";
            this.comprador = "";
            this.fecha = "";
        } else {
            advertencia = "Error";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

//    funcion para eliminar dato
    public void eliminar(int id_detalle) {
        mantenimiento_detalle_venta men = new mantenimiento_detalle_venta();
        men.eliminar(id_detalle);
    }

    //    funcion para mostrar por un id
    public void mostrarPorID(int idd) {
        mantenimiento_detalle_venta man = new mantenimiento_detalle_venta();
        TabDetalleVenta prov = man.mostrarPorID(idd);
        String advertencia = "";
        if (prov != null) {
            advertencia = "datos encontrados";
            this.id_detalle_venta = prov.getIdDetalle();
            this.id_venta = prov.getIdVenta().getIdVenta();
            this.fecha = prov.getFecha();
            this.comprador = prov.getComprador();
        } else {
            advertencia = "datos no encontrados";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //    funcion para actualizar datos
    public void actualizar(int id) throws IOException {
        mantenimiento_detalle_venta man = new mantenimiento_detalle_venta();
        TabDetalleVenta det = new TabDetalleVenta();
        TabVenta ve = new TabVenta();

        det.setIdDetalle(id_detalle_venta);
        ve.setIdVenta(id_venta);
        det.setIdVenta(ve);

        det.setFecha(fecha);
        det.setComprador(comprador);

        String advertencia = "";

        if (man.actualizar(det) == 1) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("detalle_venta.xhtml");
        } else {
            advertencia = "Error";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the id_detalle_venta
     */
    public int getId_detalle_venta() {
        return id_detalle_venta;
    }

    /**
     * @param id_detalle_venta the id_detalle_venta to set
     */
    public void setId_detalle_venta(int id_detalle_venta) {
        this.id_detalle_venta = id_detalle_venta;
    }

    /**
     * @return the id_venta
     */
    public int getId_venta() {
        return id_venta;
    }

    /**
     * @param id_venta the id_venta to set
     */
    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the comprador
     */
    public String getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    /**
     * @return the listadeta
     */
    public List<TabDetalleVenta> getListadeta() {
        mantenimiento_detalle_venta man = new mantenimiento_detalle_venta();
        listadeta = man.mostrarDetalle();
        return listadeta;
    }

    /**
     * @param listadeta the listadeta to set
     */
    public void setListadeta(List<TabDetalleVenta> listadeta) {
        this.listadeta = listadeta;
    }

    /**
     * @return the listaVentas
     */
    public List<TabVenta> getListaVentas() {
        mantenimiento_detalle_venta man = new mantenimiento_detalle_venta();
        listaVentas = man.mostrarVentas();
        return listaVentas;
    }

    /**
     * @param listaVentas the listaVentas to set
     */
    public void setListaVentas(List<TabVenta> listaVentas) {
        this.listaVentas = listaVentas;
    }

}
