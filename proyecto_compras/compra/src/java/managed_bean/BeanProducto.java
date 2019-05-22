/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_categoria;
import mantenimiento.mantenimiento_producto;
import mantenimiento.mantenimiento_proveedor;
import persistencia.TabCategoria;
import persistencia.TabProducto;
import persistencia.TabProvedor;

/**
 *
 * @author roberto.hernandezUSA
 */
@ManagedBean(name = "BeanProducto")
@RequestScoped
public class BeanProducto {

    private TabProducto producto;

    private List<TabCategoria> listaCategoria;
    private List<TabProvedor> listaProveedor;
    private List<TabProducto> listaProducto;
    
    
    mantenimiento_producto man=new mantenimiento_producto();
    mantenimiento_categoria man2=new mantenimiento_categoria();
    mantenimiento_proveedor manP=new mantenimiento_proveedor();

    @PostConstruct
    public void init() {
        this.producto = new TabProducto();
        
    }

    public BeanProducto() {
    }
    
    
    public void guardar(){
        if (man.guardar(producto)) {
            respuesta("guardando..");
            this.producto.setCodigo("");
            this.producto.setPrecio(0.0);
            this.producto.setNombre("");
        } else {
            respuesta("no guarda");
        }
    }
    
    public void consultar(String id){
        this.producto=man.consultarId(id);
    }
    
    public void borrar(String id){
        if (man.borrarId(id)) {
            respuesta("borrando..");
        } else {
            respuesta("no borra");
        }
    }
    
    public void actualizar(){
        if (man.actualizar(producto)) {
            respuesta("actualizando..");
        } else {
            respuesta("no actualiza");
        }
    }

    public TabProducto getProducto() {
        return producto;
    }

    public void setProducto(TabProducto producto) {
        this.producto = producto;
    }

    public List<TabCategoria> getListaCategoria() {
        this.listaCategoria=new ArrayList<>();
        this.listaCategoria=man2.consultarCategoria();
        return listaCategoria;
    }

    public void setListaCategoria(List<TabCategoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public List<TabProvedor> getListaProveedor() {
        this.listaProveedor=new ArrayList<>();
        this.listaProveedor=manP.mostrarProveedores();
        return listaProveedor;
    }

    public void setListaProveedor(List<TabProvedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public List<TabProducto> getListaProducto() {
        this.listaProducto=new ArrayList<>();
        this.listaProducto=man.consultarTodo();
        return listaProducto;
    }

    public void setListaProducto(List<TabProducto> listaProducto) {
        this.listaProducto = listaProducto;
    }
    
    private void respuesta(String respuesta){
        FacesMessage msg=new FacesMessage(respuesta);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
