/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_venta;
import persistencia.TabProducto;
import persistencia.TabVenta;

/**
 *
 * @author omar.hernandezusam
 */
@ManagedBean
@RequestScoped
public class bean_venta {

    /**
     * Creates a new instance of bean_venta
     */
    public bean_venta() {
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
    
    public void guardarVenta(){
        mantenimiento_venta m = new mantenimiento_venta();
        TabVenta venta = new TabVenta();
        TabProducto producto = new TabProducto();
        String msg = null;
        
        venta.setIdVenta(id_venta);
        producto.setCodigo(codigo);
        venta.setCodigo(producto);
        venta.setCantidad(cantidad);
        venta.setTotal(total);
                
        if(m.guardar(venta) == 1){
            msg = "Guardando Datos";
        }else{
            msg = "Error al guardar datos";
        }
        FacesMessage ms = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage(null, ms);
          
    }
    
    public void eliminarVenta(int id){
        mantenimiento_venta m = new mantenimiento_venta();
        String msg = null;
        if(m.eliminar(id) == 1){
            msg = "Eliminado";
        }else{
            msg = "Error al eliminar";
        }        
        FacesMessage delete = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage(null, delete);        
    }
    
    public void consultarVentaPorId(int id){
        mantenimiento_venta m = new mantenimiento_venta();
        TabVenta v = null;
        v = m.consultarId(id);
        if(v != null){
            this.id_venta = v.getIdVenta();
            this.codigo = v.getCodigo().getCodigo();
            this.cantidad = v.getCantidad();
            this.total = v.getTotal();            
        }
    }
    
    public void actualizarVenta(int id){
        mantenimiento_venta m = new mantenimiento_venta();
        TabVenta v = new TabVenta();
        TabProducto p = new TabProducto();
        String msg = null;
        
        v.setIdVenta(getId_venta());
        p.setCodigo(getCodigo());
        v.setCodigo(p);
        v.setCantidad(getCantidad());
        v.setTotal(getTotal());
        
        if(m.actualizar(v) == 1 ) {
            msg = "Datos Actualizados";
        }else{
            msg = "Error al Actualizar datos";
        }
        FacesMessage mensaje = new FacesMessage(msg);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        
        
        
        
    }

    
    
    
    
    
}
