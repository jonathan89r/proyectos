/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_sucursal;
import persistencia.Sucursal;

/**
 *
 * @author josue.esquivelUSAM
 */
@ManagedBean
@RequestScoped
public class beanSucursal {

    //LLAMADA AL METODO PARA LISTAR DATOS
    public List<Sucursal> listaSucursal;//VARIABLE PUBLICA PARA OBTENER DATOS

    //GETTER DE LA VARIABLE listaProducto PARA RETORNAR LA LISTA DE DATOS
    public List<Sucursal> getListaSucursal() {
        mantenimiento_sucursal m = new mantenimiento_sucursal();
        return m.consultarTodosSucursal();
    }

    private int id_sucursal;
    private String nombre_sucursal;
    private String ubicacion_sucursal;
    private String telefono;
    private String email_sucursal;
    private int cantidad_vendedores;

    //Metodo para guardar una sucursal
    public void guardar() throws IOException {

        mantenimiento_sucursal man = new mantenimiento_sucursal();
        Sucursal sucu = new Sucursal();
        String advertencia = "";

        sucu.setIdSucursal(getId_sucursal());
        sucu.setNombreSucursal(getNombre_sucursal());
        sucu.setUbicacionSucursal(getUbicacion_sucursal());
        sucu.setTelefono(getTelefono());
        sucu.setEmailSucursal(getEmail_sucursal());
        sucu.setCantidadVendedores(getCantidad_vendedores());

        if (man.guardarDatos(sucu) == 1) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaSucursal.xhtml");
            advertencia = "Datos Ingresados Correctamente";

        } else {
            advertencia = "Error al ingresar informacion";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo para actualizar
    public void actualizarSucursal(int id) throws IOException {
        mantenimiento_sucursal man = new mantenimiento_sucursal();
        Sucursal sucu = new Sucursal();
        String advertencia = "";

        sucu.setNombreSucursal(getNombre_sucursal());
        sucu.setUbicacionSucursal(getUbicacion_sucursal());
        sucu.setTelefono(getTelefono());
        sucu.setEmailSucursal(getEmail_sucursal());
        sucu.setCantidadVendedores(getCantidad_vendedores());
        sucu.setIdSucursal(getId_sucursal());

        if (man.ActualizarDatosSucursal(sucu) == 1) {
            advertencia = "Correcto";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaSucursal.xhtml");
        } else {
            advertencia = "Incorrecto";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo para eliminar sucursal
    public void delete(int id) {
        mantenimiento_sucursal man = new mantenimiento_sucursal();
        man.eliminarDatos(id);
    }

    //Metodo para consultar por id
    public void consultarSucursalporID(int id) {
        mantenimiento_sucursal m = new mantenimiento_sucursal();
        Sucursal s = m.consultarSucursalporID(id);//SE OBTIENEN DATOS DEL PRODUCTO Y SE GUARDA EN UNA VARIABLE DEL TIPO TabProductos(PERSISTENCIA)
        String advertencia = "";
        if (s != null) { //VERIFICA SI LA VARIABLE d TRAE DATOS Y SE ASIGNAN LOS VALORES A LAS VARIABLES PRIVADAS
            this.id_sucursal = s.getIdSucursal();
            this.nombre_sucursal = s.getNombreSucursal();
            this.ubicacion_sucursal = s.getUbicacionSucursal();
            this.telefono = s.getTelefono();
            this.email_sucursal = s.getEmailSucursal();
            this.cantidad_vendedores = s.getCantidadVendedores();
        } else {
            advertencia = "Datos no encontrados";
            FacesMessage msg = new FacesMessage(advertencia);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public beanSucursal() {

    }

    public int getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public String getNombre_sucursal() {
        return nombre_sucursal;
    }

    public void setNombre_sucursal(String nombre_sucursal) {
        this.nombre_sucursal = nombre_sucursal;
    }

    public String getUbicacion_sucursal() {
        return ubicacion_sucursal;
    }

    public void setUbicacion_sucursal(String ubicacion_sucursal) {
        this.ubicacion_sucursal = ubicacion_sucursal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail_sucursal() {
        return email_sucursal;
    }

    public void setEmail_sucursal(String email_sucursal) {
        this.email_sucursal = email_sucursal;
    }

    public int getCantidad_vendedores() {
        return cantidad_vendedores;
    }

    public void setCantidad_vendedores(int cantidad_vendedores) {
        this.cantidad_vendedores = cantidad_vendedores;
    }

}
