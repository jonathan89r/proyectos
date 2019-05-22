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
import mantenimiento.mantenimiento_proveedor;
import persistencia.TabEstado;
import persistencia.TabEstadoGlobal;
import persistencia.TabGiro;
import persistencia.TabProvedor;

/**
 *
 * @author jonathan.rodriguez
 */
@ManagedBean
@RequestScoped
public class bean_proveedor {

    private int id_proveedor;
    private String nombre_proveedor;
    private String telefono;
    private int id_giro;
    private List<TabGiro> listaGiros;
    private List<TabProvedor> listaProveedor;
    private List<TabEstadoGlobal> listaEstados;
    private int id_estado;

    public bean_proveedor() {
    }

//    funcion para guardar datos
    public void guardar() {
        mantenimiento_proveedor man = new mantenimiento_proveedor();
        TabProvedor pro = new TabProvedor();
        TabGiro gi = new TabGiro();
        pro.setIdProvedor(id_proveedor);
        pro.setNombreProveedor(nombre_proveedor);
        pro.setTelefono(telefono);

        TabEstadoGlobal glo = new TabEstadoGlobal();
        glo.setIdEstadoGlobal(id_estado);
        pro.setEstadoGlobal(glo);

        gi.setIdGiro(id_giro);
        pro.setGiros(gi);

        String advertencia = "";

        TabProvedor Object = man.validar(nombre_proveedor);

        if (Object != null) {
            advertencia = "proveedor ya existe";
        } else {
            if (man.guardar(pro) == 1) {
                advertencia = "Datos guardados";
            } else {
                advertencia = "error al guardar";
            }

        }

        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

//    funcion para eliminar}
    public void eliminar(int id) {
        mantenimiento_proveedor man = new mantenimiento_proveedor();
        String advertencia = "";

        if (man.eliminar(id) != null) {
            advertencia = "Borrando..";
        } else {
            advertencia = "Este proveedor";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

//    funcion para mostrar por un id
    public void mostrarPorID(int id) {
        mantenimiento_proveedor man = new mantenimiento_proveedor();
        TabProvedor prov = man.mostrarPorID(id);
        String advertencia = "";
        if (prov != null) {
            advertencia = "datos encontrados";
            this.id_proveedor = prov.getIdProvedor();
            this.nombre_proveedor = prov.getNombreProveedor();
            this.telefono = prov.getTelefono();
            this.id_giro = prov.getGiros().getIdGiro();
            this.id_estado = prov.getEstadoGlobal().getIdEstadoGlobal();
        } else {
            advertencia = "datos no encontrados";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

//    funcion para actualizar datos
    public void actualizar(int id) throws IOException {
        mantenimiento_proveedor man = new mantenimiento_proveedor();
        TabProvedor pro = new TabProvedor();
        TabGiro gi = new TabGiro();

        pro.setIdProvedor(getId_proveedor());
        pro.setNombreProveedor(getNombre_proveedor());
        pro.setTelefono(getTelefono());
        gi.setIdGiro(getId_giro());
        pro.setGiros(gi);

        TabEstadoGlobal glo = new TabEstadoGlobal();
        glo.setIdEstadoGlobal(getId_estado());
        pro.setEstadoGlobal(glo);

        String mensaje = "";
        if (man.actualizar(pro) == 1) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("proveedor.xhtml");
        } else {
            mensaje = "Error";
        }
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
//psicologo2cds@usam.edu.sv

    /**
     * @return the id_proveedor
     */
    public int getId_proveedor() {
        return id_proveedor;
    }

    /**
     * @param id_proveedor the id_proveedor to set
     */
    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    /**
     * @return the nombre_proveedor
     */
    public String getNombre_proveedor() {
        return nombre_proveedor;
    }

    /**
     * @param nombre_proveedor the nombre_proveedor to set
     */
    public void setNombre_proveedor(String nombre_proveedor) {
        this.nombre_proveedor = nombre_proveedor;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the id_giro
     */
    public int getId_giro() {
        return id_giro;
    }

    /**
     * @param id_giro the id_giro to set
     */
    public void setId_giro(int id_giro) {
        this.id_giro = id_giro;
    }

    /**
     * @return the listaGiros
     */
    public List<TabGiro> getListaGiros() {
        mantenimiento_proveedor man = new mantenimiento_proveedor();
        listaGiros = man.mostrarGiro();
        return listaGiros;
    }

    /**
     * @return the listaProveedor
     */
    public List<TabProvedor> getListaProveedor() {
        mantenimiento_proveedor man = new mantenimiento_proveedor();
        listaProveedor = man.mostrarProveedores();
        return listaProveedor;
    }

    /**
     * @return the listaEstados
     */
    public List<TabEstadoGlobal> getListaEstados() {
        mantenimiento_proveedor man = new mantenimiento_proveedor();
        listaEstados = man.mostrarEstado();
        return listaEstados;
    }

    /**
     * @return the id_estado
     */
    public int getId_estado() {
        return id_estado;
    }

    /**
     * @param id_estado the id_estado to set
     */
    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

}
