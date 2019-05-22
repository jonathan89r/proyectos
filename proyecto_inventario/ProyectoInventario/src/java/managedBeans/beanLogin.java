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
import mantenimiento.mantenimiento_login;
import persistencia.Login;

/**
 *
 * @author jonathan.rodriguez
 */
@ManagedBean
@RequestScoped
public class beanLogin {

    private int id_usuario;
    private String user;
    private String pass;
    private int tipo_usuario;
    private String respuesta;
    private String pregunta;

    //LLAMADA AL METODO PARA LISTAR DATOS
    public List<Login> listaProducto;//VARIABLE PUBLICA PARA OBTENER DATOS

    //GETTER DE LA VARIABLE listaProducto PARA RETORNAR LA LISTA DE DATOS
    public List<Login> getListaProducto() {
        mantenimiento_login m = new mantenimiento_login();
        return m.consultarTodosLogin();
    }

    //Metodo para consultar por id los usuarios 
    public void consultarLoginporID(int id) {
        mantenimiento_login m = new mantenimiento_login();
        Login l = m.consultarLoginporID(id);//SE OBTIENEN DATOS DEL PRODUCTO Y SE GUARDA EN UNA VARIABLE DEL TIPO TabProductos(PERSISTENCIA)
        String advertencia = "";
        if (l != null) { //VERIFICA SI LA VARIABLE d TRAE DATOS Y SE ASIGNAN LOS VALORES A LAS VARIABLES PRIVADAS
            this.id_usuario = l.getIdUsuario();
            this.user = l.getUser();
            this.pass = l.getPass();
            this.tipo_usuario = l.getTipoUsuario();
            this.respuesta = l.getRespuesta();
            this.pregunta = l.getPregunta();
        } else {
            advertencia = "Datos no encontrados";
            FacesMessage msg = new FacesMessage(advertencia);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    //Metodo para guardar usuario como administrador
    public void guardarComoAdmin() throws IOException {

        mantenimiento_login man = new mantenimiento_login();
        Login log = new Login();
        String advertencia = "";

        log.setUser(getUser());
        log.setPass(getPass());
        log.setRespuesta(getRespuesta());
        log.setTipoUsuario(getTipo_usuario());
        log.setPregunta(getPregunta());

        if (man.guardarDatos(log) == 1) {
            advertencia = "Informacion Ingresada Correctamente";

            this.setUser("");
            this.setPass("");
            this.setRespuesta("");
            this.setPregunta("Seleccione una pregunta");
            this.setTipo_usuario(0);

        } else {
            advertencia = "Error al ingresar Informacion";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo para actualizar usuarios
    public void actualizar(int id_usuario) throws IOException {

        mantenimiento_login man = new mantenimiento_login();
        Login log = new Login();
        String advertencia = "";

        log.setUser(getUser());
        log.setPass(getPass());
        log.setTipoUsuario(getTipo_usuario());

        log.setIdUsuario(getId_usuario());

        if (man.actualizarUserss(log) == 1) {
            advertencia = "Informacion actualizada Correctamente";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaUserLogin.xhtml");

        } else {
            advertencia = "Error al ingresar informacion";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo para eliminar usuarios
    public void delete(int id) {
        mantenimiento_login man = new mantenimiento_login();
        man.eliminarDatos(id);
    }

    /**
     * @return the id_usuario
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the tipo_usuario
     */
    public int getTipo_usuario() {
        return tipo_usuario;
    }

    /**
     * @param tipo_usuario the tipo_usuario to set
     */
    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    /**
     * @return the respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

}
