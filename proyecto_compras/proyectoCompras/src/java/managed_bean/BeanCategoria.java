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
import mantenimiento.mantenimiento_categoria;
import persistencia.TabCategoria;

/**
 *
 * @author roberto.hernandezUSA
 */
@ManagedBean(name = "BeanCategoria")
@RequestScoped
public class BeanCategoria {

    private TabCategoria categoria;
    
    mantenimiento_categoria man=new mantenimiento_categoria();
    
    private List<TabCategoria> listaCategoria;
    
    /*creacion del metodo pos*/
    @PostConstruct
    public void init(){
        this.categoria=new TabCategoria(0);
    }
    
    public void guardar(){
        
        if (man.guardar(categoria)) {
            respuesta("guardando..");
            this.categoria=null;
        } else {
            respuesta("no guarda");
        }
    }
    
    public void borrar(int id){
        if (man.borrar(id)) {
            respuesta("borrando");
        } else {
            respuesta("no borra");
        }
    }
    
    public void actualizar() throws IOException{
        if (man.actualizar(categoria)) {
            respuesta("actualizando..");
            FacesContext.getCurrentInstance().getExternalContext().redirect("categoria.xhtml");
        } else {
            respuesta("no actualiza");
        }
    }
    
    public void consultarId(int id){
        this.categoria=man.consultarId(id);
    }
    
    
    public BeanCategoria() {
    }

    public TabCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(TabCategoria categoria) {
        this.categoria = categoria;
    }

    public List<TabCategoria> getListaCategoria() {
        this.listaCategoria=new ArrayList<>();
        this.listaCategoria=man.consultarCategoria();
        return listaCategoria;
    }

    public void setListaCategoria(List<TabCategoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }
    
    
    
    private void respuesta(String respuesta){
        FacesMessage msg=new FacesMessage(respuesta);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
