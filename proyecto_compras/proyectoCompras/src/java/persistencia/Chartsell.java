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
@Table(name = "chartsell")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chartsell.findAll", query = "SELECT c FROM Chartsell c")
    , @NamedQuery(name = "Chartsell.findByCodigo", query = "SELECT c FROM Chartsell c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Chartsell.findByNombre", query = "SELECT c FROM Chartsell c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Chartsell.findByCantidad", query = "SELECT c FROM Chartsell c WHERE c.cantidad = :cantidad")
    , @NamedQuery(name = "Chartsell.findByTotal", query = "SELECT c FROM Chartsell c WHERE c.total = :total")})
public class Chartsell implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "codigo")
    @Id
    private String codigo;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;

    public Chartsell() {
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

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
}
