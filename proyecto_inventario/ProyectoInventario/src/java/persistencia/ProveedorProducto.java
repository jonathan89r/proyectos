/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jonathan.rodriguez
 */
@Entity
@Table(name = "proveedor_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProveedorProducto.findAll", query = "SELECT p FROM ProveedorProducto p")
    , @NamedQuery(name = "ProveedorProducto.findByIdProveedorProducto", query = "SELECT p FROM ProveedorProducto p WHERE p.idProveedorProducto = :idProveedorProducto")})
public class ProveedorProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proveedor_producto")
    private Integer idProveedorProducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProveedorProducto")
    private Collection<Inventario> inventarioCollection;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Productos idProducto;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor")
    @ManyToOne(optional = false)
    private Proveedor idProveedor;

    public ProveedorProducto() {
    }

    public ProveedorProducto(Integer idProveedorProducto) {
        this.idProveedorProducto = idProveedorProducto;
    }

    public Integer getIdProveedorProducto() {
        return idProveedorProducto;
    }

    public void setIdProveedorProducto(Integer idProveedorProducto) {
        this.idProveedorProducto = idProveedorProducto;
    }

    @XmlTransient
    public Collection<Inventario> getInventarioCollection() {
        return inventarioCollection;
    }

    public void setInventarioCollection(Collection<Inventario> inventarioCollection) {
        this.inventarioCollection = inventarioCollection;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedorProducto != null ? idProveedorProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProveedorProducto)) {
            return false;
        }
        ProveedorProducto other = (ProveedorProducto) object;
        if ((this.idProveedorProducto == null && other.idProveedorProducto != null) || (this.idProveedorProducto != null && !this.idProveedorProducto.equals(other.idProveedorProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.ProveedorProducto[ idProveedorProducto=" + idProveedorProducto + " ]";
    }
    
}
