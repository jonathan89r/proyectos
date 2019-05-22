/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_productos;
import persistencia.Productos;

/**
 *
 * @author jonathan.rodriguez
 */
@ManagedBean
@RequestScoped
public class beanProductos {

    private int id_producto;
    private String codigo;
    private String nombre_producto;
    private String descripcion;
    private Double precio;
    private String tipo_producto;
    private String fecha_vencimiento;
    private List<Productos> listaproductos;

    //Metodo para guardar productos
    public void guardarProductos() throws IOException, ParseException {
        mantenimiento_productos man = new mantenimiento_productos();
        Productos pro = new Productos();
        String advertencia = "";
        SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");
        pro.setCodigoProducto(codigo);
        pro.setNombreProducto(getNombre_producto());
        pro.setDescripcionProducto(descripcion);
        pro.setPrecio(getPrecio());
        pro.setTipoProducto(getTipo_producto());
        pro.setFechaVencimiento(formato.parse(getFecha_vencimiento()));
        pro.setIdProducto(getId_producto());

        if (man.guardarProductos(pro) == 1) {

            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasIngresarAdmin/ingresarProductos.xhtml");
            advertencia = "Datos Ingresados Correctamente";

        } else {
            advertencia = "Error al Ingresar Datos";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo para eliminar productos
    public void eliminar(int id) {
        mantenimiento_productos man = new mantenimiento_productos();
        man.eliminar(id);
    }

    //Metodo para consultar por id los productos
    public void consultarPorID(int id) {
        mantenimiento_productos man = new mantenimiento_productos();
        Productos pro = man.consultarPorIDPRODUCTOS(id);
        String advertencia = "";
        SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");
        if (pro != null) {
            codigo = pro.getCodigoProducto();
            setNombre_producto(pro.getNombreProducto());
            descripcion = pro.getDescripcionProducto();
            setPrecio((Double) pro.getPrecio());
            setTipo_producto(pro.getTipoProducto());
            setFecha_vencimiento(formato.format(pro.getFechaVencimiento()));
            setId_producto((int) pro.getIdProducto());
            advertencia = "Datos encontrados";
        } else {
            advertencia = "Datos no encontrados";
            FacesMessage msg = new FacesMessage(advertencia);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    //Metodo para actualizar productos
    public void actualizarProductos(int id_producto) throws IOException, ParseException {
        mantenimiento_productos man = new mantenimiento_productos();
        Productos pro = new Productos();

        String advertencia = "";
        SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");
        pro.setCodigoProducto(getCodigo());
        pro.setNombreProducto(getNombre_producto());
        pro.setDescripcionProducto(getDescripcion());
        pro.setPrecio(getPrecio());
        pro.setTipoProducto(getTipo_producto());
        pro.setFechaVencimiento(formato.parse(getFecha_vencimiento()));
        pro.setIdProducto(getId_producto());

        if (man.actualizarProductos(pro) == 1) {
            advertencia = "Actualizacion exitosa";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaProductos.xhtml");
        } else {
            advertencia = "Error";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the id_producto
     */
    public int getId_producto() {
        return id_producto;
    }

    /**
     * @param id_producto the id_producto to set
     */
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nombre_producto
     */
    public String getNombre_producto() {
        return nombre_producto;
    }

    /**
     * @param nombre_producto the nombre_producto to set
     */
    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the tipo_producto
     */
    public String getTipo_producto() {
        return tipo_producto;
    }

    /**
     * @param tipo_producto the tipo_producto to set
     */
    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    /**
     * @return the fecha_vencimiento
     */
    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    /**
     * @param fecha_vencimiento the fecha_vencimiento to set
     */
    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public List<Productos> getListaproductos() {
        mantenimiento_productos man = new mantenimiento_productos();
        return man.consultarTodosProductos();
    }
}
