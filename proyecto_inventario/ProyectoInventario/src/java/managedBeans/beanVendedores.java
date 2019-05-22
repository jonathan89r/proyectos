/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_vendedores;
import persistencia.Login;
import persistencia.Sucursal;
import persistencia.Vendedor;

/**
 *
 * @author jonathan.rodriguez
 */
@ManagedBean
@RequestScoped
public class beanVendedores {

    private int id_vendedor;
    private String nombre_vendedor;
    private String apellido_vendedor;
    private String email_vendedor;
    private String dui_vendedor;
    private String nit_vendedor;
    private String residencia;
    private String telefono;
    private String celular;
    private String referencia;
    private String fecha_contratacion;

    private int id_login;
    private int id_sucursal;
    private List<Vendedor> ListaVendedores;
    private List<Sucursal> listaSucursal;
    private List<Login> listaLogin;

    public beanVendedores() {
    }

    public List<Vendedor> getListaVendedores() {
        mantenimiento_vendedores m = new mantenimiento_vendedores();
        return m.consultarTodosVendedores();
    }

    //Metodo para guardar vendedores
    public void guardarDatosVendedores() {
        mantenimiento_vendedores m = new mantenimiento_vendedores();
        Vendedor ven = new Vendedor();
        Login log = new Login();
        Sucursal suc = new Sucursal();

        String advertencia = "";

        ven.setNombreVendedor(getNombre_vendedor());
        ven.setApellidoVendedor(getApellido_vendedor());
        ven.setEmailVendedor(getEmail_vendedor());
        ven.setDuiVendedor(getDui_vendedor());
        ven.setNitVendedor(getNit_vendedor());
        ven.setResidencia(getResidencia());
        ven.setTelefono(getTelefono());
        ven.setCelular(getCelular());
        ven.setReferencia(getReferencia());
        ven.setFechaContratacion(getFecha_contratacion());

        log.setIdUsuario(getId_login());
        ven.setIdLogin(log);

        suc.setIdSucursal(getId_sucursal());
        ven.setIdSucursal(suc);

        int respuesta = m.guardarDatosVendedores(ven);
        if (respuesta == 1) {
            advertencia = "Datos ingresados correctamente";
        } else {
            advertencia = "Error";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo para actualizar vendedores
    public void actualizarVendedores(int id) throws IOException {
        mantenimiento_vendedores man = new mantenimiento_vendedores();
        Vendedor ven = new Vendedor();
        Sucursal sucu = new Sucursal();
        Login log = new Login();
        String advertencia = "";
        ven.setNombreVendedor(getNombre_vendedor());
        ven.setApellidoVendedor(getApellido_vendedor());
        ven.setEmailVendedor(getEmail_vendedor());
        ven.setDuiVendedor(getDui_vendedor());
        ven.setNitVendedor(getNit_vendedor());
        ven.setResidencia(getResidencia());
        ven.setTelefono(getTelefono());
        ven.setCelular(getCelular());
        ven.setReferencia(getReferencia());
        ven.setFechaContratacion(getFecha_contratacion());
        ven.setIdVendedor(getId_vendedor());

        log.setIdUsuario(id_login);
        ven.setIdLogin(log);

        sucu.setIdSucursal(id_sucursal);
        ven.setIdSucursal(sucu);

        if (man.ActualizarDatosVendedores(ven) == 1) {
            advertencia = "Correcto";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaVendedores.xhtml");
        } else {
            advertencia = "Incorrecto";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo para consultar por id
    public void consultarVendedoresPorid(int id) throws IOException {
        mantenimiento_vendedores man = new mantenimiento_vendedores();
        Vendedor ven = man.consultarVendedoresporID(id);
        String advertencia = "";
        if (ven != null) {
            this.id_vendedor = ven.getIdVendedor();
            this.nombre_vendedor = ven.getNombreVendedor();
            this.apellido_vendedor = ven.getApellidoVendedor();
            this.email_vendedor = ven.getEmailVendedor();
            this.dui_vendedor = ven.getDuiVendedor();
            this.nit_vendedor = ven.getNitVendedor();
            this.residencia = ven.getResidencia();
            this.telefono = ven.getTelefono();
            this.celular = ven.getCelular();
            this.referencia = ven.getReferencia();
            this.fecha_contratacion = ven.getFechaContratacion();
        }
    }

    @PostConstruct
    public void init() {
        Vendedor ven = new Vendedor();
        Login l = (Login) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
        mantenimiento_vendedores man = new mantenimiento_vendedores();
        ListaVendedores = man.consultarTodosVendedores();
        for (Vendedor ListaVendedore : ListaVendedores) {
            if (ListaVendedore.getIdLogin().equals(l)) {
                ven = ListaVendedore;
            }
        }
        this.id_vendedor = ven.getIdVendedor();
        this.nombre_vendedor = ven.getNombreVendedor();
        this.apellido_vendedor = ven.getApellidoVendedor();
        this.email_vendedor = ven.getEmailVendedor();
        this.dui_vendedor = ven.getDuiVendedor();
        this.nit_vendedor = ven.getNitVendedor();
        this.residencia = ven.getResidencia();
        this.telefono = ven.getTelefono();
        this.celular = ven.getCelular();
        this.referencia = ven.getReferencia();
        this.fecha_contratacion = ven.getFechaContratacion();
        this.id_login = ven.getIdLogin().getIdUsuario();
        this.id_sucursal = ven.getIdSucursal().getIdSucursal();
        ListaVendedores.clear();
    }

        //Metodo para eliminar
    public void eliminar(int id) {
        mantenimiento_vendedores m = new mantenimiento_vendedores();
        m.eliminarDatosVendedores(id);
    }

    /**
     * @return the id_vendedor
     */
    public int getId_vendedor() {
        return id_vendedor;
    }

    /**
     * @param id_vendedor the id_vendedor to set
     */
    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    /**
     * @return the nombre_vendedor
     */
    public String getNombre_vendedor() {
        return nombre_vendedor;
    }

    /**
     * @param nombre_vendedor the nombre_vendedor to set
     */
    public void setNombre_vendedor(String nombre_vendedor) {
        this.nombre_vendedor = nombre_vendedor;
    }

    /**
     * @return the apellido_vendedor
     */
    public String getApellido_vendedor() {
        return apellido_vendedor;
    }

    /**
     * @param apellido_vendedor the apellido_vendedor to set
     */
    public void setApellido_vendedor(String apellido_vendedor) {
        this.apellido_vendedor = apellido_vendedor;
    }

    /**
     * @return the email_vendedor
     */
    public String getEmail_vendedor() {
        return email_vendedor;
    }

    /**
     * @param email_vendedor the email_vendedor to set
     */
    public void setEmail_vendedor(String email_vendedor) {
        this.email_vendedor = email_vendedor;
    }

    /**
     * @return the dui_vendedor
     */
    public String getDui_vendedor() {
        return dui_vendedor;
    }

    /**
     * @param dui_vendedor the dui_vendedor to set
     */
    public void setDui_vendedor(String dui_vendedor) {
        this.dui_vendedor = dui_vendedor;
    }

    /**
     * @return the nit_vendedor
     */
    public String getNit_vendedor() {
        return nit_vendedor;
    }

    /**
     * @param nit_vendedor the nit_vendedor to set
     */
    public void setNit_vendedor(String nit_vendedor) {
        this.nit_vendedor = nit_vendedor;
    }

    /**
     * @return the residencia
     */
    public String getResidencia() {
        return residencia;
    }

    /**
     * @param residencia the residencia to set
     */
    public void setResidencia(String residencia) {
        this.residencia = residencia;
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
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the fecha_contratacion
     */
    public String getFecha_contratacion() {
        return fecha_contratacion;
    }

    /**
     * @param fecha_contratacion the fecha_contratacion to set
     */
    public void setFecha_contratacion(String fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }

    /**
     * @return the listaSucursal
     */
    public List<Sucursal> getListaSucursal() {
        mantenimiento_vendedores man = new mantenimiento_vendedores();
        return man.consultarTodosSucursal();
    }

    /**
     * @return the listaLogin
     */
    public List<Login> getListaLogin() {
        mantenimiento_vendedores man = new mantenimiento_vendedores();
        return man.consultarTodosLogin();
    }

    /**
     * @return the id_login
     */
    public int getId_login() {
        return id_login;
    }

    /**
     * @param id_login the id_login to set
     */
    public void setId_login(int id_login) {
        this.id_login = id_login;
    }

    /**
     * @return the id_sucursal
     */
    public int getId_sucursal() {
        return id_sucursal;
    }

    /**
     * @param id_sucursal the id_sucursal to set
     */
    public void setId_sucursal(int id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    /**
     * @return the ListaVendedores
     */
}
