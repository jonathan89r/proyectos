/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rodriguez
 */
@Entity
@Table(name = "inventario_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InventarioProducto.findAll", query = "SELECT i FROM InventarioProducto i")
    , @NamedQuery(name = "InventarioProducto.findByIdInventario", query = "SELECT i FROM InventarioProducto i WHERE i.idInventario = :idInventario")
    , @NamedQuery(name = "InventarioProducto.findByCodigo", query = "SELECT i FROM InventarioProducto i WHERE i.codigo = :codigo")
    , @NamedQuery(name = "InventarioProducto.findByNombre", query = "SELECT i FROM InventarioProducto i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "InventarioProducto.findByNombreCategoria", query = "SELECT i FROM InventarioProducto i WHERE i.nombreCategoria = :nombreCategoria")
    , @NamedQuery(name = "InventarioProducto.findByNombreProveedor", query = "SELECT i FROM InventarioProducto i WHERE i.nombreProveedor = :nombreProveedor")
    , @NamedQuery(name = "InventarioProducto.findByPrecio", query = "SELECT i FROM InventarioProducto i WHERE i.precio = :precio")
    , @NamedQuery(name = "InventarioProducto.findByStock", query = "SELECT i FROM InventarioProducto i WHERE i.stock = :stock")})
public class InventarioProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id_inventario")
    @Id
    private int idInventario;
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "nombre_categoria")
    private String nombreCategoria;
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Column(name = "stock")
    private Integer stock;

    public InventarioProducto() {
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
}
