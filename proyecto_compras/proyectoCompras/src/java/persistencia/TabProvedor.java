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
@Table(name = "tab_provedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabProvedor.findAll", query = "SELECT t FROM TabProvedor t")
    , @NamedQuery(name = "TabProvedor.findByIdProvedor", query = "SELECT t FROM TabProvedor t WHERE t.idProvedor = :idProvedor")
    , @NamedQuery(name = "TabProvedor.findByNombreProveedor", query = "SELECT t FROM TabProvedor t WHERE t.nombreProveedor = :nombreProveedor")
    , @NamedQuery(name = "TabProvedor.findByTelefono", query = "SELECT t FROM TabProvedor t WHERE t.telefono = :telefono")})
public class TabProvedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_provedor")
    private Integer idProvedor;
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;
    @Column(name = "telefono")
    private String telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "provedor")
    private Collection<TabProducto> tabProductoCollection;
    @JoinColumn(name = "estado_global", referencedColumnName = "id_estado_global")
    @ManyToOne
    private TabEstadoGlobal estadoGlobal;
    @JoinColumn(name = "giros", referencedColumnName = "id_giro")
    @ManyToOne
    private TabGiro giros;

    public TabProvedor() {
    }

    public TabProvedor(Integer idProvedor) {
        this.idProvedor = idProvedor;
    }

    public Integer getIdProvedor() {
        return idProvedor;
    }

    public void setIdProvedor(Integer idProvedor) {
        this.idProvedor = idProvedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @XmlTransient
    public Collection<TabProducto> getTabProductoCollection() {
        return tabProductoCollection;
    }

    public void setTabProductoCollection(Collection<TabProducto> tabProductoCollection) {
        this.tabProductoCollection = tabProductoCollection;
    }

    public TabEstadoGlobal getEstadoGlobal() {
        return estadoGlobal;
    }

    public void setEstadoGlobal(TabEstadoGlobal estadoGlobal) {
        this.estadoGlobal = estadoGlobal;
    }

    public TabGiro getGiros() {
        return giros;
    }

    public void setGiros(TabGiro giros) {
        this.giros = giros;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProvedor != null ? idProvedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabProvedor)) {
            return false;
        }
        TabProvedor other = (TabProvedor) object;
        if ((this.idProvedor == null && other.idProvedor != null) || (this.idProvedor != null && !this.idProvedor.equals(other.idProvedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TabProvedor[ idProvedor=" + idProvedor + " ]";
    }
    
}
