/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_proyeccion;
import persistencia.TabDetalleVenta;
import persistencia.TabInventario;
import persistencia.TabProducto;
import persistencia.TabProyeccion;
import persistencia.TabVenta;

/**
 *
 * @author william.fuentesusam
 */
@ManagedBean
@RequestScoped
public class bean_proyeccion {

    /**
     * Creates a new instance of bean_proyeccion
     */
    public bean_proyeccion() {
    }
    private int id_proyeccion;
    private int inventario;
    private int venta;
    private int proyeccionmensual;
    private int necesidadcompra;
    private List<TabProyeccion> lista;
    private List<TabDetalleVenta> deta;
    private List<TabInventario> inven;
    private int cantidad;
    private int total;

    mantenimiento_proyeccion m = new mantenimiento_proyeccion();
    TabProyeccion pro = new TabProyeccion();
    TabDetalleVenta de = new TabDetalleVenta();
    TabInventario in = new TabInventario();
    TabInventario inv = new TabInventario();
    TabProducto p = new TabProducto();
    TabVenta ve = new TabVenta();
    String adv = null;

    public int getId_proyeccion() {
        return id_proyeccion;
    }

    public void setId_proyeccion(int id_proyeccion) {
        this.id_proyeccion = id_proyeccion;
    }

    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    public int getProyeccionmensual() {
        return proyeccionmensual;
    }

    public void setProyeccionmensual(int proyeccionmensual) {
        this.proyeccionmensual = proyeccionmensual;
    }

    public int getNecesidadcompra() {
        return necesidadcompra;
    }

    public void setNecesidadcompra(int necesidadcompra) {
        this.necesidadcompra = necesidadcompra;
    }

    public List<TabProyeccion> getLista() {
        return m.mostrar();
    }

    public void setLista(List<TabProyeccion> lista) {
        this.lista = lista;
    }

    public List<TabDetalleVenta> getDeta() {
        deta = new ArrayList<>();
        deta = m.mostrarventa();
        return deta;
    }

    public void setDeta(List<TabDetalleVenta> deta) {
        this.deta = deta;
    }

    public List<TabInventario> getInven() {
        inven = new ArrayList<>();
        inven = m.mostrarinventario();
        return inven;
    }

    public void setInven(List<TabInventario> inven) {
        this.inven = inven;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void eliminar(int id) {
        m.eliminar(id);
    }

    public void guardar() {
        pro.setIdProyeccion(id_proyeccion);

        in.setIdInventario(inventario);
        pro.setInventario(in);

        ve.setCantidad(total);
        de.setIdDetalle(venta);
        pro.setVenta(de);

        pro.setProyeccionmensual(proyeccionmensual);

        inv.setStock(cantidad);

        necesidadcompra = proyeccionmensual - cantidad - total;

        pro.setNecesidadcompra(necesidadcompra);

        if (m.guardar(pro) == 1) {
            adv = "DATOS INGRESADOS DE FORMA CORRECTA";
        } else {
            adv = "DATOS NO PUDIERON SER INGRESADOS";
        }
        FacesMessage msj = new FacesMessage(adv);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }

}
