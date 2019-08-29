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
 * @author Rodriguez
 */
@Entity
@Table(name = "tab_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabVenta.findAll", query = "SELECT t FROM TabVenta t")
    , @NamedQuery(name = "TabVenta.findByIdVenta", query = "SELECT t FROM TabVenta t WHERE t.idVenta = :idVenta")
    , @NamedQuery(name = "TabVenta.findByCantidad", query = "SELECT t FROM TabVenta t WHERE t.cantidad = :cantidad")
    , @NamedQuery(name = "TabVenta.findByTotal", query = "SELECT t FROM TabVenta t WHERE t.total = :total")})
public class TabVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_venta")
    private Integer idVenta;
    @Column(name = "cantidad")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    @ManyToOne
    private TabProducto codigo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVenta")
    private Collection<TabDetalleVenta> tabDetalleVentaCollection;

    public TabVenta() {
    }

    public TabVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
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

    public TabProducto getCodigo() {
        return codigo;
    }

    public void setCodigo(TabProducto codigo) {
        this.codigo = codigo;
    }

    @XmlTransient
    public Collection<TabDetalleVenta> getTabDetalleVentaCollection() {
        return tabDetalleVentaCollection;
    }

    public void setTabDetalleVentaCollection(Collection<TabDetalleVenta> tabDetalleVentaCollection) {
        this.tabDetalleVentaCollection = tabDetalleVentaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVenta != null ? idVenta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabVenta)) {
            return false;
        }
        TabVenta other = (TabVenta) object;
        if ((this.idVenta == null && other.idVenta != null) || (this.idVenta != null && !this.idVenta.equals(other.idVenta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TabVenta[ idVenta=" + idVenta + " ]";
    }
    
}
