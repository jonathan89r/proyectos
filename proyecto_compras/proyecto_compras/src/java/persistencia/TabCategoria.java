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
@Table(name = "tab_categoria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabCategoria.findAll", query = "SELECT t FROM TabCategoria t")
    , @NamedQuery(name = "TabCategoria.findByIdCategoria", query = "SELECT t FROM TabCategoria t WHERE t.idCategoria = :idCategoria")
    , @NamedQuery(name = "TabCategoria.findByNombreCategoria", query = "SELECT t FROM TabCategoria t WHERE t.nombreCategoria = :nombreCategoria")
    , @NamedQuery(name = "TabCategoria.findByDescripcion", query = "SELECT t FROM TabCategoria t WHERE t.descripcion = :descripcion")})
public class TabCategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria")
    private Integer idCategoria;
    @Column(name = "nombre_categoria")
    private String nombreCategoria;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private Collection<TabProducto> tabProductoCollection;

    public TabCategoria() {
    }

    public TabCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<TabProducto> getTabProductoCollection() {
        return tabProductoCollection;
    }

    public void setTabProductoCollection(Collection<TabProducto> tabProductoCollection) {
        this.tabProductoCollection = tabProductoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabCategoria)) {
            return false;
        }
        TabCategoria other = (TabCategoria) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TabCategoria[ idCategoria=" + idCategoria + " ]";
    }
    
}
