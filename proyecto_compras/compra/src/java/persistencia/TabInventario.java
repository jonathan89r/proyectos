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
@Table(name = "tab_inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabInventario.findAll", query = "SELECT t FROM TabInventario t")
    , @NamedQuery(name = "TabInventario.findByIdInventario", query = "SELECT t FROM TabInventario t WHERE t.idInventario = :idInventario")
    , @NamedQuery(name = "TabInventario.findByStock", query = "SELECT t FROM TabInventario t WHERE t.stock = :stock")})
public class TabInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_inventario")
    private Integer idInventario;
    @Column(name = "stock")
    private Integer stock;
    @JoinColumn(name = "producto", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TabProducto producto=new TabProducto();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inventario")
    private Collection<TabProyeccion> tabProyeccionCollection;

    public TabInventario() {
    }

    public TabInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public TabProducto getProducto() {
        return producto;
    }

    public void setProducto(TabProducto producto) {
        this.producto = producto;
    }

    @XmlTransient
    public Collection<TabProyeccion> getTabProyeccionCollection() {
        return tabProyeccionCollection;
    }

    public void setTabProyeccionCollection(Collection<TabProyeccion> tabProyeccionCollection) {
        this.tabProyeccionCollection = tabProyeccionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInventario != null ? idInventario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabInventario)) {
            return false;
        }
        TabInventario other = (TabInventario) object;
        if ((this.idInventario == null && other.idInventario != null) || (this.idInventario != null && !this.idInventario.equals(other.idInventario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idInventario.toString();
    }
    
}
