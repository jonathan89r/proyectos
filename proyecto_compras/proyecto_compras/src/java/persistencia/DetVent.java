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
@Table(name = "det_vent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetVent.findAll", query = "SELECT d FROM DetVent d")
    , @NamedQuery(name = "DetVent.findByIdDetalle", query = "SELECT d FROM DetVent d WHERE d.idDetalle = :idDetalle")
    , @NamedQuery(name = "DetVent.findByCodigo", query = "SELECT d FROM DetVent d WHERE d.codigo = :codigo")
    , @NamedQuery(name = "DetVent.findByNombre", query = "SELECT d FROM DetVent d WHERE d.nombre = :nombre")
    , @NamedQuery(name = "DetVent.findByComprador", query = "SELECT d FROM DetVent d WHERE d.comprador = :comprador")
    , @NamedQuery(name = "DetVent.findByPrecio", query = "SELECT d FROM DetVent d WHERE d.precio = :precio")
    , @NamedQuery(name = "DetVent.findByStock", query = "SELECT d FROM DetVent d WHERE d.stock = :stock")
    , @NamedQuery(name = "DetVent.findByCantidad", query = "SELECT d FROM DetVent d WHERE d.cantidad = :cantidad")})
public class DetVent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id_detalle")
    @Id
    private int idDetalle;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "comprador")
    private String comprador;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "cantidad")
    private Integer cantidad;

    public DetVent() {
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
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

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
}
