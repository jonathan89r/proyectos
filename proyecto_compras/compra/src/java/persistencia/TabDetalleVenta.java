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
@Table(name = "tab_detalle_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabDetalleVenta.findAll", query = "SELECT t FROM TabDetalleVenta t")
    , @NamedQuery(name = "TabDetalleVenta.findByIdDetalle", query = "SELECT t FROM TabDetalleVenta t WHERE t.idDetalle = :idDetalle")
    , @NamedQuery(name = "TabDetalleVenta.findByFecha", query = "SELECT t FROM TabDetalleVenta t WHERE t.fecha = :fecha")
    , @NamedQuery(name = "TabDetalleVenta.findByComprador", query = "SELECT t FROM TabDetalleVenta t WHERE t.comprador = :comprador")})
public class TabDetalleVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle")
    private Integer idDetalle;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "comprador")
    private String comprador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "venta")
    private Collection<TabProyeccion> tabProyeccionCollection;
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    @ManyToOne(optional = false)
    private TabVenta idVenta;

    public TabDetalleVenta() {
    }

    public TabDetalleVenta(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    @XmlTransient
    public Collection<TabProyeccion> getTabProyeccionCollection() {
        return tabProyeccionCollection;
    }

    public void setTabProyeccionCollection(Collection<TabProyeccion> tabProyeccionCollection) {
        this.tabProyeccionCollection = tabProyeccionCollection;
    }

    public TabVenta getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(TabVenta idVenta) {
        this.idVenta = idVenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabDetalleVenta)) {
            return false;
        }
        TabDetalleVenta other = (TabDetalleVenta) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TabDetalleVenta[ idDetalle=" + idDetalle + " ]";
    }
    
}
