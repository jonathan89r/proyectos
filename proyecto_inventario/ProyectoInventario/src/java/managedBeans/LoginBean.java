package managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import persistencia.Login;
import mantenimiento.Validacion_login;
import mantenimiento.mantenimiento_recuperarcontra;
import org.primefaces.context.RequestContext;

@ManagedBean
@RequestScoped
@SessionScoped
public class LoginBean {

    private final Validacion_login log = new Validacion_login();
    private final mantenimiento_recuperarcontra rc = new mantenimiento_recuperarcontra();
    private Login l;

    public Login getL() {
        return l;
    }

    public void setL(Login l) {
        this.l = l;
    }

    private int id_usuario;
    private String user;
    private String pass;
    private int tipo_usuario;
    private String respuesta;
    private String pregunta;

    public LoginBean() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(int tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String loginvalidar() {
        l = log.validar(user, pass, tipo_usuario);

        if (l != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", l);
            if (l.getPass() != null && l.getTipoUsuario() == 1) {
                return "vistasConsultasAdmin/PaginaPrincipal.xhtml?faces-redirect=true";

            } else if (l.getPass() != null && l.getTipoUsuario() == 2) {
                return "prueba.xhtml?faces-redirect=true";
            } else {
                RequestContext.getCurrentInstance().update("grow");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_FATAL, "Error en la contraseña", "Contraseña Erronea"));
            }
        } else {
            RequestContext.getCurrentInstance().update("grow");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "No se Encontro Usuario", "El usuario No existe"));
        }
        return null;
    }

    public String recuperarcontra() {
        l = rc.verificar(user, respuesta, pregunta);

        if (l != null) {
            if (l.getRespuesta() != null && l.getPregunta() != null) {
                return "faces/vistaNuevaContraseña.xhtml?faces-redirect=true&id=" + l.getIdUsuario();
            }
            RequestContext.getCurrentInstance().update("grow1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "Error en la Respuesta", "Respuesta Erronea"));
        }
        RequestContext.getCurrentInstance().update("grow1");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_FATAL, "No se Encontro Usuario", "El usuario No existe"));
        return null;
    }

    public String actualizarContra() {

        boolean res = rc.actualizarcontra(id_usuario, pass);

        if (res) {
            return "vistaLogin.xhtml?faces-redirect=true";
        }
        RequestContext.getCurrentInstance().update("grow1");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_FATAL, "Invalido", "no se actualizo"));
        return null;
    }
}
