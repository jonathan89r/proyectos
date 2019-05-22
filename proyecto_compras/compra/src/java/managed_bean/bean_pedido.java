/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_pedido;
import persistencia.TabGerencia;
import persistencia.TabInventario;
import persistencia.TabPedido;
import persistencia.TabProducto;
import persistencia.TabProyeccion;

/**
 *
 * @author william.fuentesusam
 */
@ManagedBean
@RequestScoped
public class bean_pedido {

    /**
     * Creates a new instance of bean_pedido
     */
    public bean_pedido() {
    }

    private int codigo_pedido;
    private int gerencia;
    private int proyeccion;
    private Date fecha_pedido;
    private Double iva;
    private Double total;
    private List<TabPedido> lista;
    private List<TabGerencia> listage;
    private List<TabProyeccion> listapro;
    private Double precio;

    mantenimiento_pedido m = new mantenimiento_pedido();
    TabProducto pro = new TabProducto();
    TabInventario i = new TabInventario();
    TabPedido p = new TabPedido();
    TabGerencia g = new TabGerencia();
    TabProyeccion pr = new TabProyeccion();
    String adv = "";

    public int getCodigo_pedido() {
        return codigo_pedido;
    }

    public void setCodigo_pedido(int codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    public int getGerencia() {
        return gerencia;
    }

    public void setGerencia(int gerencia) {
        this.gerencia = gerencia;
    }

    public int getProyeccion() {
        return proyeccion;
    }

    public void setProyeccion(int proyeccion) {
        this.proyeccion = proyeccion;
    }

    public Date getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(Date fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public List<TabPedido> getLista() {
        return m.mostrar();
    }

    public void setLista(List<TabPedido> lista) {
        this.lista = lista;
    }

    public List<TabGerencia> getListage() {
        listage = new ArrayList<>();
        listage = m.mostrargerente();
        return listage;
    }

    public void setListage(List<TabGerencia> listage) {
        this.listage = listage;
    }

    public List<TabProyeccion> getListapro() {
        listapro = new ArrayList<>();
        listapro = m.mostrarproyeccion();
        return listapro;
    }

    public void setListapro(List<TabProyeccion> listapro) {
        this.listapro = listapro;
    }

    public void eliminar(int id) {
        m.eliminar(id);
    }

    public void guardar() {
        p.setCodigoPedido(codigo_pedido);
        pro.setPrecio(precio);
        i.setProducto(pro);
        pr.setInventario(i);
        p.setProyeccion(pr);
        pr.setNecesidadcompra(proyeccion);
        p.setProyeccion(pr);
        g.setIdGerencia(gerencia);
        p.setGerencia(g);
        p.setFechaPedido(new Date());

        iva = proyeccion * 0.13;

        p.setIva(iva);

        total = iva * ( proyeccion * precio);

        p.setTotal(total);

        if (m.guardar(p) == 1) {

            adv = "DATOS INGRESADOS DE FORMA CORRECTA";
        } else {
            adv = "DATOS NO PUDIERON SER INGRESADOS";
        }
        FacesMessage msj = new FacesMessage(adv);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }

    public void mostrarporID(int id) {
        p = m.mostrarporID(id);

        if (p != null) {
            this.codigo_pedido = p.getCodigoPedido();
            this.proyeccion = p.getProyeccion().getIdProyeccion();
            this.gerencia = p.getGerencia().getIdGerencia();
            this.fecha_pedido = p.getFechaPedido();
            this.iva = p.getIva();
            this.total = p.getIva();
        } else {
            adv = "DATOS NO FUERON ENCONTRADOS";
            FacesMessage msj = new FacesMessage(adv);
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
    }

    public void actualizar() {
        p.setCodigoPedido(getCodigo_pedido());
        pr.setIdProyeccion(getProyeccion());
        p.setProyeccion(pr);
        g.setIdGerencia(getGerencia());
        p.setGerencia(g);
        p.setFechaPedido(getFecha_pedido());
        p.setIva(getIva());
        p.setTotal(getTotal());

        if (m.actualizar(p) == 1) {
            adv = "DATOS ACTUALIZADOS DE MANERA CORRECTA";
        } else {
            adv = "DATOS NO PUDIERON SER ACTUALIZADOS";
        }
        FacesMessage msj = new FacesMessage(adv);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }
}
