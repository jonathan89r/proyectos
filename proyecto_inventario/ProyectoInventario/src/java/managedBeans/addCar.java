/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import java.io.IOException;
import java.io.Serializable;
import static java.nio.file.Files.list;
import java.text.DecimalFormat;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_factura;
import mantenimiento.mantenimiento_vendedores;
import persistencia.Cliente;
import persistencia.Factura;
import persistencia.Inventario;
import persistencia.Productos;
import persistencia.TipoPago;
import persistencia.Vendedor;

/**
 *
 * @author jonathan.rodriguez
 */
@ManagedBean
@ViewScoped
public class addCar implements Serializable {

    private int id;
    private String nombre_producto;
    private double precio;
    private int cantidad;
    private String codigo;
    private double acumulador;
    private int acumuladorProductos;
    private List<detallesFactura> ListaCarrito = new ArrayList<>();
    private List<Vendedor> listaVendedores;
    private int cantidadProductos;

    private int id_vendedor;
    private int id_cliente;
    private int id_inventario;
    private Date fecha_facturacion;
    private int tipo_pago;

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public List<Vendedor> getListaVendedores() {
        mantenimiento_vendedores man = new mantenimiento_vendedores();
        listaVendedores = man.consultarTodosVendedores();
        return listaVendedores;
    }

    public void setListaVendedores(List<Vendedor> listaVendedores) {
        this.listaVendedores = listaVendedores;
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

    public Date getFecha_facturacion() {
        return fecha_facturacion;
    }

    public void setFecha_facturacion(Date fecha_facturacion) {
        this.fecha_facturacion = fecha_facturacion;
    }

    public int getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(int tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public addCar() {
    }

    //Agregar datos al carrito
    public void añadirAlCarrito(Productos pro, int cant) {

        detallesFactura det = new detallesFactura();

        det.setId(pro.getIdProducto());
        det.setCodigo(pro.getCodigoProducto());
        det.setNombre(pro.getNombreProducto());
        det.setPrecio(pro.getPrecio());
        det.setCantidad(cant);

//        Calculo para sacar el iva de un producto individual
        double iva = ((pro.getPrecio() * cant) * 0.13);
        det.setTotales(((pro.getPrecio() * cant)) + iva);

//        Total de todos los productos seleccionados $
        acumulador = det.getTotales() + acumulador;

//        Total de los productos
        acumuladorProductos = det.getCantidad() + acumuladorProductos;

//        Conversion
        DecimalFormat formato = new DecimalFormat("#.000");
        formato.format(det.getTotales());
        formato.format(this.acumulador);

//        Se agregan los datos a la lista
        ListaCarrito.add(det);

    }

    //Remover productos seleccionados
    public void Remover(int objeto) {
        for (int i = 0; i < ListaCarrito.size(); i++) {
            if (ListaCarrito.get(i).getId() == objeto) {
                acumuladorProductos = acumuladorProductos - ListaCarrito.get(id).getCantidad();
                acumulador = acumulador - ListaCarrito.get(i).getTotales();
                ListaCarrito.remove(i);
            }
        }
    }

    public void guardarFacturaVendedor() throws IOException {
        mantenimiento_factura man = new mantenimiento_factura();

        Vendedor ven = new Vendedor();
        Factura fac = new Factura();
        Cliente cli = new Cliente();
        Inventario in = new Inventario();
        TipoPago tip = new TipoPago();
        addCar lista = new addCar();

        ven.setIdVendedor(this.id_vendedor);
        fac.setIdVendedor(ven);

        cli.setIdCliente(this.id_cliente);
        fac.setIdCliente(cli);

        in.setIdInventario(this.id_inventario);
        fac.setIdInventario(in);

        Date hoy = new Date();
        fac.setFechaFacturacion(hoy);

        tip.setIdTipoPago(this.tipo_pago);
        fac.setTipoPago(tip);

        fac.setTotalProductos(this.acumuladorProductos);

        fac.setTotalVentas(this.acumulador);

        int respuesta = man.guardarFactura(fac);
        String advertencia = "";
        if (respuesta == 1) {
            advertencia = "Correcto";

        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * @return the acumuladorProductos
     */
    public int getAcumuladorProductos() {
        return acumuladorProductos;
    }

    /**
     * @param acumuladorProductos the acumuladorProductos to set
     */
    public void setAcumuladorProductos(int acumuladorProductos) {
        this.acumuladorProductos = acumuladorProductos;
    }

    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public double getAcumulador() {
        return acumulador;
    }

    public void setAcumulador(double acumulador) {
        this.acumulador = acumulador;
    }

    /**
     * @return the nombre_producto
     */
    public String getNombre_producto() {
        return nombre_producto;
    }

    /**
     * @param nombre_producto the nombre_producto to set
     */
    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    /**
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the ListaCarrito
     */
    public List<detallesFactura> getListaCarrito() {
        return ListaCarrito;
    }

    /**
     * @param ListaCarrito the ListaCarrito to set
     */
    public void setListaCarrito(List<detallesFactura> ListaCarrito) {
        this.ListaCarrito = ListaCarrito;
    }

    /**
     * @return the cantidadProductos
     */
    public int getCantidadProductos() {
        return cantidadProductos;
    }

    /**
     * @param cantidadProductos the cantidadProductos to set
     */
    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

}
