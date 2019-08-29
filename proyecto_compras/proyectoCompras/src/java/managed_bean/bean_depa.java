/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import mantenimiento.mantenimiento_departamento;
import persistencia.TabDepartamento;

/**
 *
 * @author sergio.torresusam
 */
@ManagedBean
@RequestScoped
public class bean_depa {

    /**
     * Creates a new instance of bean_depa
     */
    public bean_depa() {
    }
    private List<TabDepartamento> lista;
    mantenimiento_departamento m = new mantenimiento_departamento();

    public List<TabDepartamento> getLista() {
        return m.mostrar();
    }

    public void setLista(List<TabDepartamento> lista) {
        this.lista = lista;
    }
    
}
