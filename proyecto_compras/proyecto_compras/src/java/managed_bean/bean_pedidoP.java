/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_departamento;
import mantenimiento.mantenimiento_pedido;
import mantenimiento.mantenimiento_venta;
import persistencia.PedidoProyeccion;
import persistencia.TabDepartamento;
import persistencia.TabGerencia;
import persistencia.TabPedido;
import persistencia.TabProducto;
import persistencia.TabProyeccion;
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
    private int codigo_pedido;
    private int proyeccion;
    private int cantidad;
    private int gerencia;
    private double iva;
    private double precio;
    private double total;
    private List<TabGerencia> listage;
    private List<TabProyeccion> listapro;
    private List<TabPedido> listapedido;
    
    public bean_pedidoP() {
    }
    
    public List<PedidoProyeccion> getLista() {
        mantenimiento_pedido man = new mantenimiento_pedido();
        lista = man.consultarDatos();
        return lista;
    }
    
    public List<TabGerencia> getListage() {
        mantenimiento_pedido m = new mantenimiento_pedido();
        listage = new ArrayList<>();
        listage = m.mostrargerente();
        return listage;
    }
    
    public void setListage(List<TabGerencia> listage) {
        this.listage = listage;
    }
    
    public List<TabProyeccion> getListapro() {
        mantenimiento_pedido m = new mantenimiento_pedido();
        listapro = new ArrayList<>();
        listapro = m.mostrarproyeccion();
        return listapro;
    }
    
    public void setListapro(List<TabProyeccion> listapro) {
        this.listapro = listapro;
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

    /**
     * @return the codigo_pedido
     */
    public int getCodigo_pedido() {
        return codigo_pedido;
    }

    /**
     * @param codigo_pedido the codigo_pedido to set
     */
    public void setCodigo_pedido(int codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    /**
     * @return the proyeccion
     */
    public int getProyeccion() {
        return proyeccion;
    }

    /**
     * @param proyeccion the proyeccion to set
     */
    public void setProyeccion(int proyeccion) {
        this.proyeccion = proyeccion;
    }

    /**
     * @return the gerencia
     */
    public int getGerencia() {
        return gerencia;
    }

    /**
     * @param gerencia the gerencia to set
     */
    public void setGerencia(int gerencia) {
        this.gerencia = gerencia;
    }

    /**
     * @return the iva
     */
    public double getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(double iva) {
        this.iva = iva;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public void guardar() {
        mantenimiento_pedido m = new mantenimiento_pedido();
        TabPedido p = new TabPedido();
        TabGerencia g = new TabGerencia();
        TabProyeccion pr = new TabProyeccion();
        TabProducto pro = new TabProducto();
        TabProyeccion py = new TabProyeccion();
        String advertencia;
        
        TabGerencia gg = (TabGerencia) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("a");
        p.setCodigoPedido(codigo_pedido);
        pr.setIdProyeccion(proyeccion);
        py.setNecesidadcompra(cantidad);
        p.setProyeccion(pr);
        p.setFechaPedido(fechaHoy);
        g.setIdGerencia(gg.getIdGerencia());
        p.setGerencia(g);
        pro.setPrecio(precio);
        iva = precio * 0.13;
        p.setIva(iva);
        total = cantidad * iva;
        p.setTotal(total);
        
        if (m.guardar(p) == 1) {
            advertencia = "Datos Guardados Correctamente";
        } else {
            advertencia = "Datos no pudieron ser ingresados";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the listapedido
     */
    public List<TabPedido> getListapedido() {
        mantenimiento_pedido m = new mantenimiento_pedido();
        return m.consultarDatosPedido();
    }

    /**
     * @param listapedido the listapedido to set
     */
    public void setListapedido(List<TabPedido> listapedido) {
        this.listapedido = listapedido;
    }
    
}
