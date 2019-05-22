/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_gerencia;
import persistencia.TabDepartamento;
import persistencia.TabGerencia;

/**
 *
 * @author william.fuentesusam
 */
@ManagedBean
@RequestScoped
public class bean_gerencia {

    /**
     * Creates a new instance of bean_gerencia
     */
    public bean_gerencia() {
    }

    private int id_gerencia;
    private int departamento;
    private String nombre;
    private String apellido;
    private String cod_empleado;
    private String usuario;
    private String pass;
    private List<TabGerencia> lista;
    private List<TabDepartamento> listadepa;

    mantenimiento_gerencia m = new mantenimiento_gerencia();
    TabGerencia g = new TabGerencia();
    TabDepartamento d = new TabDepartamento();
    String adv = null;

    public int getId_gerencia() {
        return id_gerencia;
    }

    public void setId_gerencia(int id_gerencia) {
        this.id_gerencia = id_gerencia;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCod_empleado() {
        return cod_empleado;
    }

    public void setCod_empleado(String cod_empleado) {
        this.cod_empleado = cod_empleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<TabGerencia> getLista() {
        return m.mostrar();
    }

    public void setLista(List<TabGerencia> lista) {
        this.lista = lista;
    }

    public List<TabDepartamento> getListadepa() {
        listadepa = new ArrayList<>();
        listadepa = m.mostrardepa();
        return listadepa;
    }

    public void setListadepa(List<TabDepartamento> listadepa) {
        this.listadepa = listadepa;
    }

    public void eliminar(int id) {
        g.setIdGerencia(id);
        m.metodos(3, g);
    }

    public void guardar() {

        g.setIdGerencia(id_gerencia);
        d.setIdDepartamento(departamento);
        g.setDepartamento(d);
        g.setNombre(nombre);
        g.setApellido(apellido);
        g.setCodEmpleado(cod_empleado);
        g.setUsuario(usuario);
        g.setPass(pass);

        TabGerencia object = m.validar(cod_empleado);
        TabGerencia us = m.validarUser(usuario);

        if (object != null) {
            adv = "CodEmpleado ya existe";

            if (us != null) {
                adv = "Usuario ya existe";
            }

        } else {
            if (m.metodos(1, g) == true) {
                adv = "DATOS INGRESADOS DE FORMA CORRECTA";
            } else {
                adv = "DATOS NO PUDIERON SER INGRESADOS";
            }
        }

        FacesMessage msj = new FacesMessage(adv);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }

    public void mostrarporID(int id) {

        g = m.mostrarporID(id);

        if (g != null) {

            this.id_gerencia = g.getIdGerencia();
            this.departamento = g.getDepartamento().getIdDepartamento();
            this.nombre = g.getNombre();
            this.apellido = g.getApellido();
            this.cod_empleado = g.getCodEmpleado();
            this.usuario = g.getUsuario();
            this.pass = g.getPass();

        } else {
            adv = "DATOS NO FUERON ENCONTRADOS";
            FacesMessage msj = new FacesMessage(adv);
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }

    }

    public void actualizar() {

        g.setNombre(getNombre());
        g.setApellido(getApellido());
        g.setCodEmpleado(getCod_empleado());
        g.setUsuario(getUsuario());
        g.setPass(getPass());      
        d.setIdDepartamento(getDepartamento());
        g.setDepartamento(d);
        g.setIdGerencia(getId_gerencia());

        TabGerencia object = m.validarUser(usuario);

        if (m.metodos(2, g) != true) {
            adv = "DATOS ACTUALIZADOS DE MANERA CORRECTA";
        } else {
            adv = "DATOS NO PUDIERON SER ACTUALIZADOS";
        }

        FacesMessage msj = new FacesMessage(adv);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }

}
