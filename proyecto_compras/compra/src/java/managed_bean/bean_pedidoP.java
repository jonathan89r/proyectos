/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import mantenimiento.mantenimiento_departamento;
import mantenimiento.mantenimiento_pedido;
import mantenimiento.mantenimiento_venta;
import persistencia.PedidoProyeccion;
import persistencia.TabDepartamento;
import persistencia.TabVenta;

/**
 *
 * @author jonathan.rodriguez
 */
@ManagedBean
@RequestScoped
public class bean_pedidoP {

    private Date fechaHoy;
    private List<PedidoProyeccion> lista;
    private List<TabVenta> listaVenta;
    private List<TabDepartamento> listaDepa;

    public bean_pedidoP() {
    }

    public List<PedidoProyeccion> getLista() {
        mantenimiento_pedido man = new mantenimiento_pedido();
        lista = man.consultarDatos();
        return lista;
    }

    /**
     * @return the fechaHoy
     */
    public Date getFechaHoy() {
        Date hoy = new Date();
        SimpleDateFormat formato = new SimpleDateFormat();

        formato.format(hoy);
        return hoy;
    }

    /**
     * @param fechaHoy the fechaHoy to set
     */
    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    /**
     * @return the listaVenta
     */
    public List<TabVenta> getListaVenta() {
        mantenimiento_venta man = new mantenimiento_venta();
        listaVenta = man.consultar();
        return listaVenta;
    }

    /**
     * @param listaVenta the listaVenta to set
     */
    public void setListaVenta(List<TabVenta> listaVenta) {
        this.listaVenta = listaVenta;
    }

    /**
     * @return the listaDepa
     */
    public List<TabDepartamento> getListaDepa() {
        mantenimiento_departamento man = new mantenimiento_departamento();
        listaDepa = man.mostrar();
        return listaDepa;
    }

}
