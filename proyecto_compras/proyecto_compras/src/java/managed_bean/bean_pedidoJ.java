///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package managed_bean;
//
//import java.util.Date;
//import java.util.List;
//import javax.faces.application.FacesMessage;
//import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
//import javax.faces.context.FacesContext;
//import mantenimiento.mantenimiento_pedido;
//import persistencia.TabGerencia;
//import persistencia.TabPedido;
//import persistencia.TabProducto;
//import persistencia.TabProyeccion;
//
///**
// *
// * @author jonathan.rodriguez
// */
//@ManagedBean
//@RequestScoped
//public class bean_pedidoJ {
//    
//    private int codigo_pedido;
//    private int id_proyeccion;
//    private int id_gerencia;
//    private Date fecha_pedido;
//    private double iva;
//    private double total;
//    
//    private String cod_producto;
//    
//    private List<TabPedido> listapedido;
//    private List<TabProyeccion> listaproyeccion;
//    private List<TabGerencia> listagerencia;
//    private List<TabProducto> listaproducto;
//    
//    public void guardar() {
//        mantenimiento_pedido pe = new mantenimiento_pedido();
//        TabPedido tab = new TabPedido();
//        TabProyeccion pro = new TabProyeccion();
//        TabGerencia ge = new TabGerencia();
//        
//        TabProducto prod = pe.consultarId(cod_producto);
//        
//        String advertencia = "";
//        
//        tab.setCodigoPedido(codigo_pedido);
//        
//        pro.setIdProyeccion(id_proyeccion);
//        tab.setProyeccion(pro);
//        
//        ge.setIdGerencia(id_gerencia);
//        tab.setGerencia(ge);
//        
//        Date hoy = new Date();
//        tab.setFechaPedido(hoy);
//        
//        Double precio = prod.getPrecio();
//        
//        this.iva = precio * 0.13;
//        
//        tab.setIva(iva);
//        
//        tab.setTotal(precio + iva);
//        
//        if (pe.guardar(tab) == 1) {
//            advertencia = "Datos guardados correctamente";
//        } else {
//            advertencia = "Error al guardar la informacion";
//        }
//        FacesMessage msg = new FacesMessage(advertencia);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
//
//    /**
//     * @return the codigo_pedido
//     */
//    public int getCodigo_pedido() {
//        return codigo_pedido;
//    }
//
//    /**
//     * @param codigo_pedido the codigo_pedido to set
//     */
//    public void setCodigo_pedido(int codigo_pedido) {
//        this.codigo_pedido = codigo_pedido;
//    }
//
//    /**
//     * @return the id_proyeccion
//     */
//    public int getId_proyeccion() {
//        return id_proyeccion;
//    }
//
//    /**
//     * @param id_proyeccion the id_proyeccion to set
//     */
//    public void setId_proyeccion(int id_proyeccion) {
//        this.id_proyeccion = id_proyeccion;
//    }
//
//    /**
//     * @return the id_gerencia
//     */
//    public int getId_gerencia() {
//        return id_gerencia;
//    }
//
//    /**
//     * @param id_gerencia the id_gerencia to set
//     */
//    public void setId_gerencia(int id_gerencia) {
//        this.id_gerencia = id_gerencia;
//    }
//
//    /**
//     * @return the fecha_pedido
//     */
//    public Date getFecha_pedido() {
//        return fecha_pedido;
//    }
//
//    /**
//     * @param fecha_pedido the fecha_pedido to set
//     */
//    public void setFecha_pedido(Date fecha_pedido) {
//        this.fecha_pedido = fecha_pedido;
//    }
//
//    /**
//     * @return the iva
//     */
//    public double getIva() {
//        return iva;
//    }
//
//    /**
//     * @param iva the iva to set
//     */
//    public void setIva(double iva) {
//        this.iva = iva;
//    }
//
//    /**
//     * @return the total
//     */
//    public double getTotal() {
//        return total;
//    }
//
//    /**
//     * @param total the total to set
//     */
//    public void setTotal(double total) {
//        this.total = total;
//    }
//
//    /**
//     * @return the listapedido
//     */
//    public List<TabPedido> getListapedido() {
//        mantenimiento_pedido man = new mantenimiento_pedido();
//        listapedido = man.mostrar();
//        return listapedido;
//    }
//
//    /**
//     * @return the listaproyeccion
//     */
//    public List<TabProyeccion> getListaproyeccion() {
//        mantenimiento_pedido man = new mantenimiento_pedido();
//        listaproyeccion = man.mostrarproyeccion();
//        return listaproyeccion;
//    }
//
//    /**
//     * @return the listagerencia
//     */
//    public List<TabGerencia> getListagerencia() {
//        mantenimiento_pedido man = new mantenimiento_pedido();
//        listagerencia = man.mostrargerente();
//        return listagerencia;
//    }
//
//    /**
//     * @return the listaproducto
//     */
//    public List<TabProducto> getListaproducto() {
//        mantenimiento_pedido man = new mantenimiento_pedido();
//        listaproducto = man.consultarTodo();
//        return listaproducto;
//    }
//
//    /**
//     * @return the cod_producto
//     */
//    public String getCod_producto() {
//        return cod_producto;
//    }
//
//    /**
//     * @param cod_producto the cod_producto to set
//     */
//    public void setCod_producto(String cod_producto) {
//        this.cod_producto = cod_producto;
//    }
//    
//}
