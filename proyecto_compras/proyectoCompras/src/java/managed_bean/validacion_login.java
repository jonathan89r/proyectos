/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_gerencia;
import mantenimiento.mantenimiento_login;
import persistencia.TabDepartamento;
import persistencia.TabGerencia;

/**
 *
 * @author jonathan.rodriguez
 */
@ManagedBean
@SessionScoped
@RequestScoped
public class validacion_login {
    
    private TabGerencia gerencia;
    private TabGerencia ger;
    private mantenimiento_gerencia log = new mantenimiento_gerencia();
    private mantenimiento_login m = new mantenimiento_login();
    
    @PostConstruct
    public void init() {
        this.gerencia = new TabGerencia();
        this.setGer((TabGerencia) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("a"));
        
    }

    /**
     * @return the gerencia
     */
    public TabGerencia getGerencia() {
        return gerencia;
    }

    /**
     * @param gerencia the gerencia to set
     */
    public void setGerencia(TabGerencia gerencia) {
        this.gerencia = gerencia;
    }
    
    public void validar() throws IOException {
        TabGerencia ges = log.validarLogin(gerencia.getUsuario(), gerencia.getPass());
        String mensaje = "";
        if (ges != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("a", ges);
            if (ges.getUsuario() != null && ges.getPass() != null && ges.getDepartamento().getIdDepartamento() == 1) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/principal_logistica.xhtml");
            }
            if (ges.getUsuario() != null && ges.getPass() != null && ges.getDepartamento().getIdDepartamento() == 2) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/principal.xhtml");
            }
            if (ges.getUsuario() != null && ges.getPass() != null && ges.getDepartamento().getIdDepartamento() == 3) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/principal_ventas.xhtml");
            }
            if (ges.getUsuario() != null && ges.getPass() != null && ges.getDepartamento().getIdDepartamento() == 4) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/principal.xhtml");
            }
            
        } else {
            mensaje = "Error, datos no coinciden";
        }
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void actualizar() {
        
        TabGerencia g = new TabGerencia();
        TabDepartamento d = new TabDepartamento();
        String adv = null;
        
        g.setNombre(ger.getNombre());
        g.setApellido(ger.getApellido());
        g.setCodEmpleado(ger.getCodEmpleado());
        g.setUsuario(ger.getUsuario());
        g.setPass(ger.getPass());
        d.setIdDepartamento(ger.getDepartamento().getIdDepartamento());
        g.setDepartamento(d);
        g.setIdGerencia(ger.getIdGerencia());
        g.setAvatar(ger.getAvatar());
        
        if (m.actualizar(g) == 1) {
            adv = "DATOS ACTUALIZADOS DE MANERA CORRECTA";
        } else {
            adv = "DATOS NO PUDIERON SER ACTUALIZADOS";
        }
        
        FacesMessage msj = new FacesMessage(adv);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }
    
    public String usuariovalidar2() {
        
        String adv = "";
        String ruta = "";
        mantenimiento_login m = new mantenimiento_login();
        ger = m.existenciaUsuario(gerencia.getUsuario());
        
        if (ger != null) {
            
            if (ger.getCodEmpleado().equals(gerencia.getCodEmpleado())) {
                ruta = "/CambioClave.xhtml?faces-redirect=true";
            } else {
                adv = "Codigo de empleado es erroneo";
            }
        } else {
            adv = "Datos no existe";
        }
        
        FacesMessage msj = new FacesMessage(adv);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        
        return ruta;
        
    }

    /**
     * @return the ger
     */
    public TabGerencia getGer() {
        return ger;
    }

    /**
     * @param ger the ger to set
     */
    public void setGer(TabGerencia ger) {
        this.ger = ger;
    }
    
}
