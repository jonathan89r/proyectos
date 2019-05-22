/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_factura;
import persistencia.Cliente;
import persistencia.Factura;
import persistencia.Inventario;
import persistencia.TipoPago;
import persistencia.Vendedor;

/**
 *
 * @author jonathan.rodriguez
 */
@ManagedBean
@RequestScoped
public class beanFactura {

    private int id_factura;
    private int id_vendedor;
    private int id_cliente;
    private int id_inventario;
    private String fecha_facturacion;
    private int tipo_pago;
    private int total_productos;
    private double total_ventas;

    private List<Vendedor> listaVendedor;
    private List<Cliente> listaCliente;
    private List<Inventario> listaInventario;
    private List<TipoPago> listaTipoPago;
    private List<Factura> listaFactura;

    //Metodo para eliminar una factura
    public void eliminarDatos(int id) {
        mantenimiento_factura man = new mantenimiento_factura();
        man.eliminarFactura(id);
    }

    //Metodo para guardar una factura con nivel de acceso (Admin)
    public void guardarDatosFactura() throws IOException {

        mantenimiento_factura man = new mantenimiento_factura();

        Vendedor ven = new Vendedor();
        Cliente cli = new Cliente();
        Inventario in = new Inventario();
        TipoPago tip = new TipoPago();
        Factura fac = new Factura();

        String advertencia = "";

        ven.setIdVendedor(id_vendedor);
        fac.setIdVendedor(ven);

        cli.setIdCliente(id_cliente);
        fac.setIdCliente(cli);

        in.setIdInventario(id_inventario);
        fac.setIdInventario(in);

        Date hoy = new Date();
        fac.setFechaFacturacion(hoy);

        tip.setIdTipoPago(tipo_pago);
        fac.setTipoPago(tip);

        fac.setTotalProductos(getTotal_productos());
        fac.setTotalVentas(getTotal_ventas());

        int respuesta = man.guardarFactura(fac);

        if (respuesta == 1) {
            advertencia = "Datos Ingresados Correctamente";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaFactura.xhtml");
        } else {
            advertencia = "Error al Ingresar Datos";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    //Metodo para mostrar por un id una factura
    public void mostrarPorId(int id) {
        mantenimiento_factura man = new mantenimiento_factura();
        Factura fac = man.ConsultarId1(id);
        SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");

        String advertencia = "";
        if (fac != null) {
            this.id_factura = fac.getIdFactura();
            this.id_vendedor = fac.getIdVendedor().getIdVendedor();
            this.id_cliente = fac.getIdCliente().getIdCliente();
            this.id_inventario = fac.getIdInventario().getIdInventario();
            this.tipo_pago = fac.getTipoPago().getIdTipoPago();
            this.setFecha_facturacion(formato.format(fac.getFechaFacturacion()));
            this.total_productos = fac.getTotalProductos();
            this.total_ventas = fac.getTotalVentas();

            advertencia = "datos encontrados";
        } else {
            advertencia = "error";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo para actualizar una factura
    public void actualizarFactura(int id_factura) throws IOException, ParseException {
        mantenimiento_factura man = new mantenimiento_factura();

        Vendedor ven = new Vendedor();
        Cliente cli = new Cliente();
        Inventario in = new Inventario();
        TipoPago tipo = new TipoPago();
        Factura fac = new Factura();
        SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");

        fac.setIdFactura(getId_factura());

        ven.setIdVendedor(getId_vendedor());
        fac.setIdVendedor(ven);

        cli.setIdCliente(getId_cliente());
        fac.setIdCliente(cli);

        in.setIdInventario(getId_inventario());
        fac.setIdInventario(in);

        tipo.setIdTipoPago(getTipo_pago());
        fac.setTipoPago(tipo);

        fac.setFechaFacturacion(formato.parse(getFecha_facturacion()));

        fac.setTotalProductos(getTotal_productos());

        fac.setTotalVentas(getTotal_ventas());

        String advertencia = "";

        if (man.actualizarFactura(fac) == 1) {
            advertencia = "correcto";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaFactura.xhtml");
        } else {
            advertencia = "Error";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the fecha_facturacion
     */
    public String getFecha_facturacion() {
        return fecha_facturacion;
    }

    /**
     * @param fecha_facturacion the fecha_facturacion to set
     */
    public void setFecha_facturacion(String fecha_facturacion) {
        this.fecha_facturacion = fecha_facturacion;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public int getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(int tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public int getTotal_productos() {
        return total_productos;
    }

    public void setTotal_productos(int total_productos) {
        this.total_productos = total_productos;
    }

    public double getTotal_ventas() {
        return total_ventas;
    }

    public void setTotal_ventas(double total_ventas) {
        this.total_ventas = total_ventas;
    }

    /**
     * @return the listaVendedor
     */
    public List<Vendedor> getListaVendedor() {
        mantenimiento_factura man = new mantenimiento_factura();
        return man.consultarTodoVendedor();
    }

    /**
     * @return the listaCliente
     */
    public List<Cliente> getListaCliente() {
        mantenimiento_factura man = new mantenimiento_factura();
        return man.consultarTodoCliente();
    }

    /**
     * @return the listaInventario
     */
    public List<Inventario> getListaInventario() {
        mantenimiento_factura man = new mantenimiento_factura();
        return man.consultarTodoInventario();
    }

    /**
     * @return the listaTipoPago
     */
    public List<TipoPago> getListaTipoPago() {
        mantenimiento_factura man = new mantenimiento_factura();
        return man.consultarTodoTipoPago();
    }

    /**
     * @return the listaFactura
     */
    public List<Factura> getListaFactura() {
        mantenimiento_factura man = new mantenimiento_factura();
        return man.consultarTodoFactura();
    }
}
