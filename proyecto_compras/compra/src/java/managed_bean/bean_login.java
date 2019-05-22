/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mantenimiento.mantenimiento_login;
import persistencia.TabDepartamento;
import persistencia.TabGerencia;

/**
 *
 * @author william.fuentesusam
 */
@ManagedBean
@RequestScoped
public class bean_login {

    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    HttpSession httpsession = request.getSession();

    /**
     * Creates a new instance of bean_login
     */
    public bean_login() {
    }

    private static final long serialVersionUID = 1094801825228386363L;
    private String usuario;
    private String pass;
    private int l;
    private String codEmpleado;
    private String pass1, pass2;
    private String nombre;
    private int id_gerencia;
    private int departamento;
    private String apellido;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(String codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
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

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    mantenimiento_login m = new mantenimiento_login();
    TabGerencia g = null;

    public void validar() {
        TabGerencia g = m.consultarTodo(usuario, pass);

        l = g.getDepartamento().getIdDepartamento();
    }

    public String ir() {
        TabGerencia g = m.consultarTodo(usuario, pass);
        TabGerencia e = m.existenciaUsuario(usuario);
        TabGerencia vrf = null;
        String adv = "";
        String ruta = "";

        if (e != null) {

            if (g != null) {
                int d = Integer.parseInt(g.getIdGerencia().toString());

                vrf = m.consultarID(d);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("a", vrf);
                TabGerencia datosUsuario = new TabGerencia();
                datosUsuario = (TabGerencia) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("a");
                departamento = datosUsuario.getDepartamento().getIdDepartamento();
                codEmpleado = datosUsuario.getCodEmpleado();
                nombre = datosUsuario.getNombre();
                apellido = datosUsuario.getApellido();
                pass = datosUsuario.getPass();
                usuario = datosUsuario.getUsuario();
                id_gerencia = datosUsuario.getIdGerencia();

                String depa = vrf.getDepartamento().toString();

                nombre = vrf.getNombre();
                apellido = vrf.getApellido();

                if (depa.equalsIgnoreCase("logistica")) {
                    ruta = "principal_logistica.xhtml?faces-redirect-true";
                } else if (depa.equalsIgnoreCase("compras")) {
                    ruta = "principal.xhtml?faces-redirect-true";
                } else if (depa.equalsIgnoreCase("ventas")) {
                    ruta = "ventas.xhtml?faces-redirect-true";
                } else if (depa.equalsIgnoreCase("gerencia")) {
                    ruta = "principal.xhtml?faces-redirect-true";
                }
            } else {
                adv = "SU CONTRASEÑA ES INCORRECTA";
                FacesMessage msj = new FacesMessage(adv);
                FacesContext.getCurrentInstance().addMessage(null, msj);
            }

        } else {
            adv = "EL USUARIO INGRESADO NO EXISTE";
            FacesMessage msj = new FacesMessage(adv);
            FacesContext.getCurrentInstance().addMessage(null, msj);

        }

        return ruta;
    }

    public String usuariovalidar() {
        g = m.existenciaUsuario(usuario);
        String adv = "";
        String ruta = "";
        TabGerencia vrf = null;

        if (g != null) {
            int id = Integer.parseInt(g.getIdGerencia().toString());
            vrf = m.consultarID(id);

            if (vrf.getCodEmpleado().equals(this.codEmpleado)) {
                httpsession.setAttribute("usuario", g.getIdGerencia());
                ruta = "/CambioClave.xhtml?faces-redirect=true";
            } else {
                adv = "SU CODIGO DE EMPLEADO ES INCORRECTO";
            }
        } else {
            adv = "EL USUARIO INGRESADO NO EXISTE";
        }

        FacesMessage msj = new FacesMessage(adv);
        FacesContext.getCurrentInstance().addMessage(null, msj);

        return ruta;

    }

    public String cambioContra() {
        String adv = "";
        String ruta = "";

        if (this.pass1.equals(this.pass2)) {
            int id = Integer.parseInt(httpsession.getAttribute("usuario").toString());
            int res = m.actualizarpass(pass1, id);

            if (res == 1) {
                httpsession.removeAttribute("usuario");
                FacesMessage msj = new FacesMessage("Contraseña Actualizada Correctamente");
                msj.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage(null, msj);
                ruta = "/index.xhtml?faces-redirect=true";
            } else {
                adv = "ERROR AL ACTUALIZAR CONTRASEÑA";
            }
        } else {
            adv = "CONTRASEÑAS NO COINCIDEN";
        }

        FacesMessage msj = new FacesMessage(adv);
        FacesContext.getCurrentInstance().addMessage(null, msj);

        return ruta;

    }

    public void consultarDatos() {
        TabGerencia datosUsuario = new TabGerencia();
        datosUsuario = (TabGerencia) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("a");
        departamento = datosUsuario.getDepartamento().getIdDepartamento();
        codEmpleado = datosUsuario.getCodEmpleado();
        nombre = datosUsuario.getNombre();
        apellido = datosUsuario.getApellido();
        pass = datosUsuario.getPass();
        usuario = datosUsuario.getUsuario();
        id_gerencia = datosUsuario.getIdGerencia();

    }

    public void cerrarSession() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("a");
        FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");

    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public void actualizar() {
         
         TabGerencia g = new TabGerencia();
         TabDepartamento d = new TabDepartamento();
         String adv = null;

        g.setNombre(getNombre());
        g.setApellido(getApellido());
        g.setCodEmpleado(getCodEmpleado());
        g.setUsuario(getUsuario());
        g.setPass(getPass());      
        d.setIdDepartamento(getDepartamento());
        g.setDepartamento(d);
        g.setIdGerencia(getId_gerencia());

        if (m.actualizar(g)==1) {
            adv = "DATOS ACTUALIZADOS DE MANERA CORRECTA";
        } else {
            adv = "DATOS NO PUDIERON SER ACTUALIZADOS";
        }

        FacesMessage msj = new FacesMessage(adv);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }
    
}
