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
@Table(name = "tab_estado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabEstado.findAll", query = "SELECT t FROM TabEstado t")
    , @NamedQuery(name = "TabEstado.findByIdEstado", query = "SELECT t FROM TabEstado t WHERE t.idEstado = :idEstado")
    , @NamedQuery(name = "TabEstado.findByNombre", query = "SELECT t FROM TabEstado t WHERE t.nombre = :nombre")})
public class TabEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado")
    private Integer idEstado;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private Collection<TabEstadoPedido> tabEstadoPedidoCollection;

    public TabEstado() {
    }

    public TabEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<TabEstadoPedido> getTabEstadoPedidoCollection() {
        return tabEstadoPedidoCollection;
    }

    public void setTabEstadoPedidoCollection(Collection<TabEstadoPedido> tabEstadoPedidoCollection) {
        this.tabEstadoPedidoCollection = tabEstadoPedidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabEstado)) {
            return false;
        }
        TabEstado other = (TabEstado) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TabEstado[ idEstado=" + idEstado + " ]";
    }
    
}
