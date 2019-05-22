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
 * @author william.fuentesusam
 */
@Entity
@Table(name = "pedido_proyeccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoProyeccion.findAll", query = "SELECT p FROM PedidoProyeccion p")
    , @NamedQuery(name = "PedidoProyeccion.findByIdInventario", query = "SELECT p FROM PedidoProyeccion p WHERE p.idInventario = :idInventario")
    , @NamedQuery(name = "PedidoProyeccion.findByCodigo", query = "SELECT p FROM PedidoProyeccion p WHERE p.codigo = :codigo")
    , @NamedQuery(name = "PedidoProyeccion.findByNombre", query = "SELECT p FROM PedidoProyeccion p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "PedidoProyeccion.findByStock", query = "SELECT p FROM PedidoProyeccion p WHERE p.stock = :stock")})
public class PedidoProyeccion implements Serializable {

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
    @Column(name = "stock")
    private Integer stock;

    public PedidoProyeccion() {
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
    
}
