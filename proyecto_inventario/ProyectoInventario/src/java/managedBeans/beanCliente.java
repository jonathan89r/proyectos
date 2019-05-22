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
import mantenimiento.mantenimiento_cliente;
import persistencia.Cliente;

/**
 *
 * @author jonathan.rodriguez
 */
@ManagedBean
@RequestScoped
public class beanCliente {

    private int id_cliente;
    private String nombre_cliente;
    private String apellido_cliente;
    private String email_cliente;
    private String dui_cliente;
    private String nit_cliente;
    private String telefono;
    private String celular;
    private List<Cliente> listaClientes;

    //Metodo para guardar clientes
    public void guardarClientes() throws IOException {
        mantenimiento_cliente man = new mantenimiento_cliente();
        Cliente cl = new Cliente();

        cl.setNombreCliente(nombre_cliente);
        cl.setApellidoCliente(apellido_cliente);
        cl.setEmailCliente(email_cliente);
        cl.setDuiCliente(dui_cliente);
        cl.setNitCliente(nit_cliente);
        cl.setTelefono(telefono);
        cl.setCelular(celular);
        cl.setIdCliente(id_cliente);
        String advertencia = "";

        if (man.guardarDatos(cl) == 1) {
            advertencia = "Informacion Ingresada Correctamente";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaCliente.xhtml");

        } else {
            advertencia = "Error al ingresar Informacion";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo para eliminar clientes
    public void eliminarClientes(int id) {
        mantenimiento_cliente man = new mantenimiento_cliente();
        man.eliminarDatos(id);
    }

    //Metodo para actualizar clientes
    public void actualizar(int id_cliente) throws IOException {
        mantenimiento_cliente man = new mantenimiento_cliente();
        Cliente cli = new Cliente();

        cli.setNombreCliente(nombre_cliente);
        cli.setApellidoCliente(apellido_cliente);
        cli.setEmailCliente(email_cliente);
        cli.setDuiCliente(dui_cliente);
        cli.setNitCliente(nit_cliente);
        cli.setTelefono(telefono);
        cli.setCelular(celular);
        cli.setIdCliente(this.id_cliente);
        String advertencia = "";
        if (man.ActualizarDatos(cli) == 1) {
            advertencia = "correcto";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaCliente.xhtml");
        } else {
            advertencia = "incorrecto";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo para mostrar por id los clientes
    public void mostrarPorid(int id) {
        mantenimiento_cliente man = new mantenimiento_cliente();
        Cliente cli = man.consultarClientesporID(id);
        String advertencia = "";

        if (cli != null) {
            id_cliente = cli.getIdCliente();
            nombre_cliente = cli.getNombreCliente();
            apellido_cliente = cli.getApellidoCliente();
            email_cliente = cli.getEmailCliente();
            dui_cliente = cli.getDuiCliente();
            nit_cliente = cli.getNitCliente();
            telefono = cli.getTelefono();
            celular = cli.getCelular();
        } else {
            advertencia = "datos no encontrados";
            FacesMessage msg = new FacesMessage(advertencia);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    /**
     * @return the id_cliente
     */
    public int getId_cliente() {
        return id_cliente;
    }

    /**
     * @param id_cliente the id_cliente to set
     */
    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    /**
     * @return the nombre_cliente
     */
    public String getNombre_cliente() {
        return nombre_cliente;
    }

    /**
     * @param nombre_cliente the nombre_cliente to set
     */
    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    /**
     * @return the apellido_cliente
     */
    public String getApellido_cliente() {
        return apellido_cliente;
    }

    /**
     * @param apellido_cliente the apellido_cliente to set
     */
    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    /**
     * @return the email_cliente
     */
    public String getEmail_cliente() {
        return email_cliente;
    }

    /**
     * @param email_cliente the email_cliente to set
     */
    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }

    /**
     * @return the dui_cliente
     */
    public String getDui_cliente() {
        return dui_cliente;
    }

    /**
     * @param dui_cliente the dui_cliente to set
     */
    public void setDui_cliente(String dui_cliente) {
        this.dui_cliente = dui_cliente;
    }

    /**
     * @return the nit_cliente
     */
    public String getNit_cliente() {
        return nit_cliente;
    }

    /**
     * @param nit_cliente the nit_cliente to set
     */
    public void setNit_cliente(String nit_cliente) {
        this.nit_cliente = nit_cliente;
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
     * @return the listaClientes
     */
    public List<Cliente> getListaClientes() {
        mantenimiento_cliente man = new mantenimiento_cliente();

        return man.consultarTodosClientes();
    }
}
