/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "tab_proyeccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabProyeccion.findAll", query = "SELECT t FROM TabProyeccion t")
    , @NamedQuery(name = "TabProyeccion.findByIdProyeccion", query = "SELECT t FROM TabProyeccion t WHERE t.idProyeccion = :idProyeccion")
    , @NamedQuery(name = "TabProyeccion.findByProyeccionmensual", query = "SELECT t FROM TabProyeccion t WHERE t.proyeccionmensual = :proyeccionmensual")
    , @NamedQuery(name = "TabProyeccion.findByNecesidadcompra", query = "SELECT t FROM TabProyeccion t WHERE t.necesidadcompra = :necesidadcompra")})
public class TabProyeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proyeccion")
    private Integer idProyeccion;
    @Column(name = "proyeccionmensual")
    private Integer proyeccionmensual;
    @Column(name = "necesidadcompra")
    private Integer necesidadcompra;
    @JoinColumn(name = "inventario", referencedColumnName = "id_inventario")
    @ManyToOne
    private TabInventario inventario;
    @JoinColumn(name = "venta", referencedColumnName = "id_detalle")
    @ManyToOne
    private TabDetalleVenta venta;
    @OneToMany(mappedBy = "proyeccion")
    private Collection<TabPedido> tabPedidoCollection;

    public TabProyeccion() {
    }

    public TabProyeccion(Integer idProyeccion) {
        this.idProyeccion = idProyeccion;
    }

    public Integer getIdProyeccion() {
        return idProyeccion;
    }

    public void setIdProyeccion(Integer idProyeccion) {
        this.idProyeccion = idProyeccion;
    }

    public Integer getProyeccionmensual() {
        return proyeccionmensual;
    }

    public void setProyeccionmensual(Integer proyeccionmensual) {
        this.proyeccionmensual = proyeccionmensual;
    }

    public Integer getNecesidadcompra() {
        return necesidadcompra;
    }

    public void setNecesidadcompra(Integer necesidadcompra) {
        this.necesidadcompra = necesidadcompra;
    }

    public TabInventario getInventario() {
        return inventario;
    }

    public void setInventario(TabInventario inventario) {
        this.inventario = inventario;
    }

    public TabDetalleVenta getVenta() {
        return venta;
    }

    public void setVenta(TabDetalleVenta venta) {
        this.venta = venta;
    }

    @XmlTransient
    public Collection<TabPedido> getTabPedidoCollection() {
        return tabPedidoCollection;
    }

    public void setTabPedidoCollection(Collection<TabPedido> tabPedidoCollection) {
        this.tabPedidoCollection = tabPedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyeccion != null ? idProyeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabProyeccion)) {
            return false;
        }
        TabProyeccion other = (TabProyeccion) object;
        if ((this.idProyeccion == null && other.idProyeccion != null) || (this.idProyeccion != null && !this.idProyeccion.equals(other.idProyeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TabProyeccion[ idProyeccion=" + idProyeccion + " ]";
    }
    
}
