/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_productos;
import mantenimiento.mantenimiento_proveedor;
import mantenimiento.mantenimiento_proveedor_producto;
import persistencia.Proveedor;
import persistencia.ProveedorProducto;
import persistencia.Productos;

/**
 *
 * @author victor.rosalesUSAM
 */
@ManagedBean
@RequestScoped
public class beanProveedor_Producto {

    //LLAMADA AL METODO PARA LISTAR DATOS
    public List<ProveedorProducto> listaProveedorProducto;//VARIABLE PUBLICA PARA OBTENER DATOS

    private List<Proveedor> listaProveedor;
    private List<Productos> listaProductos;
    private int id_proveedor_producto;
    private int id_proveedor;
    private int id__producto;

    private List<Proveedor> listaProve;
    private List<Productos> listaProdu;

    private final mantenimiento_proveedor_producto man = new mantenimiento_proveedor_producto();
    private final mantenimiento_proveedor mprove = new mantenimiento_proveedor();
    private final mantenimiento_productos mprodu = new mantenimiento_productos();

    //Metodo para guardar proveedor_producto
    public void guardar() throws IOException, ParseException {

        ProveedorProducto propro = new ProveedorProducto();

        Proveedor prove = new Proveedor();
        Productos produ = new Productos();
        String advertencia = "";

        prove.setIdProveedor(id_proveedor);
        produ.setIdProducto(id__producto);

        propro.setIdProveedor(prove);
        propro.setIdProducto(produ);

        if (man.guardarDatos(propro) == 1) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaProveedorProducto.xhtml");
            advertencia = "Datos Ingresados Correctamente";
        } else {
            advertencia = "Error al ingresar informacion";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo para actualizar
    public void actualizar(int id) throws IOException, ParseException {
        mantenimiento_proveedor_producto men = new mantenimiento_proveedor_producto();
        ProveedorProducto propro = new ProveedorProducto();

        Proveedor prove = new Proveedor();
        Productos produ = new Productos();
        String advertencia = "";

        propro.setIdProveedorProducto(getId_proveedor_producto());

        prove.setIdProveedor(getId_proveedor());
        propro.setIdProveedor(prove);

        produ.setIdProducto(getId__producto());
        propro.setIdProducto(produ);

        if (men.ActualizarDatosProveedor(propro) == 1) {
            advertencia = "Informacion ingresada Correctamente";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaProveedorProducto.xhtml");

        } else {
            advertencia = "Error al ingresar informacion";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

//    public void actualizarProveedor(int id) throws IOException, ParseException {
//        mantenimiento_proveedor man = new mantenimiento_proveedor();
//        Proveedor pro = new Proveedor();
//        String advertencia = "";
//         SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");
//         Date Today = new Date();
//         
//        pro.setTelefono(getTelefono());
//        pro.setTipoPersona(getTipo_persona());
//        pro.setContacto(getContacto());
//        pro.setFechaIngreso(formato.parse(getFecha_ingreso()));
//        pro.setUbicacionProveedor(getUbicacion_proveedor());
//        pro.setIdProveedor(getId_proveedor());
//  
//
//        if (man.ActualizarDatosProveedor(pro) == 1) {
//            advertencia = "Correcto";
//            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaProveedores.xhtml");
//        } else {
//            advertencia = "Incorrecto";
//        }
//        FacesMessage msg = new FacesMessage(advertencia);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//
    //Metodo para eliminar 
    public void delete(int id) {
        mantenimiento_proveedor_producto man = new mantenimiento_proveedor_producto();
        man.eliminarDatos(id);
    }

    //Metodo para consultar por id
    public void consultarProveedorporID(int id) {
        mantenimiento_proveedor_producto m = new mantenimiento_proveedor_producto();
//        mantenimiento_proveedor ma = new mantenimiento_proveedor();
//        mantenimiento_productos man = new mantenimiento_productos();
//        Productos pr = man.consultarPorIDPRODUCTOS(id);
        ProveedorProducto p = m.consultarProveedorporID(id);
//        Proveedor po = ma.consultarProveedorporID(id);
//       
//        String advertencia = "";
//
//        if (p != null) { //VERIFICA SI LA VARIABLE d TRAE DATOS Y SE ASIGNAN LOS VALORES A LAS VARIABLES PRIVADAS
//            this.id_proveedor_producto = p.getIdProveedorProducto();
//            this.id_proveedor = po.getIdProveedor();           
//            this.id__producto = pr.getIdProducto();
//
//        } else {
//            advertencia = "Datos no encontrados";
//            FacesMessage msg = new FacesMessage(advertencia);
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//        }

        this.id_proveedor_producto = p.getIdProveedorProducto();
        this.id_proveedor = p.getIdProveedor().getIdProveedor();
        this.id__producto = p.getIdProducto().getIdProducto();
    }

    /**
     * @return the listaProductos
     */
    public List<Productos> getListaProductos() {
        mantenimiento_productos man = new mantenimiento_productos();
        return man.consultarTodosProductos();
    }
    //GETTER DE LA VARIABLE listaProducto PARA RETORNAR LA LISTA DE DATOS

    public List<ProveedorProducto> getListaProveedor() {
        mantenimiento_proveedor_producto m = new mantenimiento_proveedor_producto();
        return m.consultarTodosProveedor();
    }

    public beanProveedor_Producto() {
    }

    public List<Proveedor> getListaProve() {
        this.listaProve = new ArrayList<>();
        listaProve = mprove.consultarTodosProveedor();
        return listaProve;
    }

    public void setListaProve(List<Proveedor> listaProve) {
        this.listaProve = listaProve;
    }

    public List<Productos> getListaProdu() {
        this.listaProdu = new ArrayList<>();
        listaProdu = mprodu.consultarTodosProductos();
        return listaProdu;
    }

    public void setListaProdu(List<Productos> listaProdu) {
        this.listaProdu = listaProdu;
    }

    public int getId_proveedor_producto() {
        return id_proveedor_producto;
    }

    public void setId_proveedor_producto(int id_proveedor_producto) {
        this.id_proveedor_producto = id_proveedor_producto;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId__producto() {
        return id__producto;
    }

    public void setId__producto(int id__producto) {
        this.id__producto = id__producto;
    }
}
