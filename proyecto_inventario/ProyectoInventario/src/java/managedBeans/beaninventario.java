/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_inventario;
import persistencia.Inventario;
import persistencia.ProveedorProducto;

/**
 *
 * @author jonathan.rodriguez
 */
@ManagedBean
@RequestScoped
public class beaninventario {

    private int id_inventario;
    private int stock_minimo;
    private int existencias;
    private String estado;
    private int id_proveedor_producto;

    private List<ProveedorProducto> listaProveedorProducto;

    public List<ProveedorProducto> getListaProveedorProducto() {
        mantenimiento_inventario man = new mantenimiento_inventario();

        return man.consultarTodosproveedorProducto();
    }

    //Metodo para guardar inventario
    public void guardar() throws IOException {
        mantenimiento_inventario man = new mantenimiento_inventario();
        Inventario pro = new Inventario();
        ProveedorProducto proo = new ProveedorProducto();

        pro.setStockMinimo(stock_minimo);
        pro.setExistencias(existencias);
        pro.setEstado(estado);

        proo.setIdProveedorProducto(id_proveedor_producto);
        pro.setIdProveedorProducto(proo);

        String advertencia = "";
        if (man.guardarDatosinventario(pro) == 1) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaInventario.xhtml");
            advertencia = "Datos Ingresados Correctamente";
        } else {
            advertencia = "Error al Ingresar Datos";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo para eliminar
    public void eliminar(int id) {
        mantenimiento_inventario man = new mantenimiento_inventario();
        man.eliminarDatos(id);
    }

    //Metodo para actualizar
    public void actualizar(int id) throws IOException {
        mantenimiento_inventario man = new mantenimiento_inventario();
        Inventario in = new Inventario();
        ProveedorProducto pro = new ProveedorProducto();

        in.setIdInventario(id_inventario);
        in.setStockMinimo(stock_minimo);
        in.setExistencias(existencias);
        in.setEstado(estado);

        pro.setIdProveedorProducto(id_proveedor_producto);
        in.setIdProveedorProducto(pro);

        String advertencia = "";
        if (man.ActualizarDatos(in) == 1) {
            advertencia = "correcto";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaInventario.xhtml");
        } else {
            advertencia = "incorrecto";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo para mostrar por id 
    public void mostrarporID(int id) {
        mantenimiento_inventario man = new mantenimiento_inventario();

        Inventario in = man.consultarInventarioporID(id);

        String respuesta = "";
        if (in != null) {
            this.id_inventario = in.getIdInventario();
            this.stock_minimo = in.getStockMinimo();
            this.existencias = in.getExistencias();
            this.estado = in.getEstado();
            this.id_proveedor_producto = in.getIdProveedorProducto().getIdProveedorProducto();

            respuesta = "datos encontrados";
        } else {
            respuesta = "error";
        }
        FacesMessage msg = new FacesMessage(respuesta);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the id_inventario
     */
    public int getId_inventario() {
        return id_inventario;
    }

    /**
     * @param id_inventario the id_inventario to set
     */
    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    /**
     * @return the stock_minimo
     */
    public int getStock_minimo() {
        return stock_minimo;
    }

    /**
     * @param stock_minimo the stock_minimo to set
     */
    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    /**
     * @return the existencias
     */
    public int getExistencias() {
        return existencias;
    }

    /**
     * @param existencias the existencias to set
     */
    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the id_proveedor_producto
     */
    public int getId_proveedor_producto() {
        return id_proveedor_producto;
    }

    /**
     * @param id_proveedor_producto the id_proveedor_producto to set
     */
    public void setId_proveedor_producto(int id_proveedor_producto) {
        this.id_proveedor_producto = id_proveedor_producto;
    }

    /**
     * @return the listaProveedorProducto
     */
    private List<Inventario> listaInventario;

    /**
     * @return the listaInventario
     */
    public List<Inventario> getListaInventario() {
        mantenimiento_inventario man = new mantenimiento_inventario();
        return man.consultarTodosInventario();
    }

}
