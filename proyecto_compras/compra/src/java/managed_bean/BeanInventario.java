/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_inventario;
import persistencia.TabInventario;

/**
 *
 * @author roberto.hernandezUSA
 */
@ManagedBean(name = "BeanInventario")
@RequestScoped
public class BeanInventario {

    private TabInventario inventario;
    
    private List<TabInventario> listaInventario;
    
    mantenimiento_inventario man=new mantenimiento_inventario();
    
    @PostConstruct
    public void init(){
        this.inventario=new TabInventario(0);
    }
    
    public void guardar(){
        if (man.guardar(inventario)) {
            respuesta("guardando..");
            limpiar();
        } else {
            respuesta("no guardar");
        }
    }
    
    public void limpiar(){
        this.inventario=null;
        
    }
    
    public void borrar(int id){
        if (man.borrarId(id)) {
            respuesta("borrando..");
        } else {
            respuesta("no borra");
        }
    }
    
    public void consultarId(int id){
        this.inventario=man.consultarId(id);
    }
    
    public void actualizar() throws IOException{
        if (man.actualizar(inventario)) {
            
           // FacesContext.getCurrentInstance().getExternalContext().redirect("inventario.xhtml");
            respuesta("actualizando..");
        } else {
            respuesta("no actualiza");
        }
    }
   
    
    public BeanInventario() {
    }

    public TabInventario getInventario() {
        return inventario;
    }

    public void setInventario(TabInventario inventario) {
        this.inventario = inventario;
    }
    
    private void respuesta(String respuesta){
        FacesMessage msg=new FacesMessage(respuesta);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<TabInventario> getListaInventario() {
        this.listaInventario=new ArrayList<>();
        this.listaInventario=man.consultarTodo();
        return listaInventario;
    }

    public void setListaInventario(List<TabInventario> listaInventario) {
        this.listaInventario = listaInventario;
    }
    
    
}
