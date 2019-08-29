/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import persistencia.TabGerencia;

/**
 *
 * @author jonathan.rodriguez
 */
@ManagedBean
@RequestScoped
public class sessionValidate implements Serializable {

    public void validarSesion() {

        try {
            TabGerencia ge = (TabGerencia) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("a");

            if (ge == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error403.xhtml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
