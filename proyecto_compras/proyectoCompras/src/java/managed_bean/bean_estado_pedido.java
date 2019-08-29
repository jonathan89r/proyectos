package managed_bean;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mante_estado_pedido;
import persistencia.TabEstadoPedido;
import persistencia.TabEstado;
import persistencia.TabPedido;

/**
 *
 * @author sergio.torresusam
 */
@ManagedBean
@RequestScoped
public class bean_estado_pedido {

    @PostConstruct
    public void init() {
        this.ess = new TabEstadoPedido();
    }

    public int getId_estado_pedido() {
        return id_estado_pedido;
    }

    public void setId_estado_pedido(int id_estado_pedido) {
        this.id_estado_pedido = id_estado_pedido;
    }

    public int getCodigo_pedido() {
        return codigo_pedido;
    }

    public void setCodigo_pedido(int codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

    public String getFecha_estado() {
        return fecha_estado;
    }

    public void setFecha_estado(String fecha_estado) {
        this.fecha_estado = fecha_estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<TabEstadoPedido> getListaesped() {
        mantenimiento.mante_estado_pedido man = new mante_estado_pedido();
        Listaesped = man.mostrarEsped();
        return Listaesped;
    }

    public List<TabEstado> getListestado() {
        mante_estado_pedido man = new mante_estado_pedido();
        Listestado = man.mostrarEstado();
        return Listestado;
    }

    private int id_estado_pedido;
    private int codigo_pedido;
    private String fecha_estado;
    private int estado;
    private List<TabEstadoPedido> Listaesped;
    private List<TabEstado> Listestado;

    private TabEstadoPedido ess;

    public bean_estado_pedido() {
    }

//    public void guardaresped() {
//        
//        mante_estado_pedido man = new mante_estado_pedido();
//        TabEstadoPedido pro = new TabEstadoPedido();
//        TabEstado es = new TabEstado();
//        TabPedido p = new TabPedido();
//        
//        pro.setIdEstadoPedido(id_estado_pedido);
//        p.setCodigoPedido(codigo_pedido);
//        pro.setCodigoPedido(p);
//        
//        Date hoy = new Date();
//        SimpleDateFormat formato = new SimpleDateFormat();
//        pro.setFechaEstado(formato.format(hoy));
//        es.setIdEstado(estado);
//        pro.setEstado(es);
//        
//        String advertencia = "";
//        
//        if (man.guardar(pro) == 1) {
//            advertencia = "correcto";
//            this.fecha_estado = "";
//            
//        } else {
//            advertencia = "Error";
//        }
//        FacesMessage msg = new FacesMessage(advertencia);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
    public void eliminar(int id) {
        mante_estado_pedido man = new mante_estado_pedido();
        man.eliminar(id);

    }

//    public void mostrarPorID(int id) {
//        mante_estado_pedido man = new mante_estado_pedido();
//        ess = man.mostrarPorID(id);
//        String advertencia = "";
//        if (ess != null) {
//            advertencia = "datos encontrados";
//        } else {
//            advertencia = "datos no encontrados";
//        }
//        FacesMessage msg = new FacesMessage(advertencia);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
    public void mostrarPorID(int id) {
        mante_estado_pedido man = new mante_estado_pedido();
        TabEstadoPedido c = man.mostrarPorID(id);
        String advertencia = "";
        if (c != null) {
            advertencia = "datos encontrados";
            this.id_estado_pedido = c.getIdEstadoPedido();
            this.codigo_pedido = c.getCodigoPedido().getCodigoPedido();
            this.fecha_estado = c.getFechaEstado();
            this.estado = c.getEstado().getIdEstado();
        } else {
            advertencia = "datos no encontrados";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void actualizar(int id) throws IOException {
        mante_estado_pedido man = new mante_estado_pedido();
        TabEstadoPedido pro = new TabEstadoPedido();
        TabEstado es = new TabEstado();
        TabPedido p = new TabPedido();

        pro.setIdEstadoPedido(id_estado_pedido);
        p.setCodigoPedido(codigo_pedido);
        pro.setCodigoPedido(p);
        pro.setFechaEstado(fecha_estado);
        es.setIdEstado(estado);
        pro.setEstado(es);

        String mensaje = "";
        if (man.actualizar(pro) == 1) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("vistapedidoproducto.xhtml");
        } else {
            mensaje = "Error";
        }
        FacesMessage msg = new FacesMessage(mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the ess
     */
    public TabEstadoPedido getEss() {
        return ess;
    }

    /**
     * @param ess the ess to set
     */
    public void setEss(TabEstadoPedido ess) {
        this.ess = ess;
    }

}
