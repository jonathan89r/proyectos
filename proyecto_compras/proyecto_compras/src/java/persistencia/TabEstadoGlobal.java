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
@Table(name = "tab_estado_global")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabEstadoGlobal.findAll", query = "SELECT t FROM TabEstadoGlobal t")
    , @NamedQuery(name = "TabEstadoGlobal.findByIdEstadoGlobal", query = "SELECT t FROM TabEstadoGlobal t WHERE t.idEstadoGlobal = :idEstadoGlobal")
    , @NamedQuery(name = "TabEstadoGlobal.findByNombre", query = "SELECT t FROM TabEstadoGlobal t WHERE t.nombre = :nombre")})
public class TabEstadoGlobal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_global")
    private Integer idEstadoGlobal;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "estadoGlobal")
    private Collection<TabProvedor> tabProvedorCollection;

    public TabEstadoGlobal() {
    }

    public TabEstadoGlobal(Integer idEstadoGlobal) {
        this.idEstadoGlobal = idEstadoGlobal;
    }

    public TabEstadoGlobal(Integer idEstadoGlobal, String nombre) {
        this.idEstadoGlobal = idEstadoGlobal;
        this.nombre = nombre;
    }

    public Integer getIdEstadoGlobal() {
        return idEstadoGlobal;
    }

    public void setIdEstadoGlobal(Integer idEstadoGlobal) {
        this.idEstadoGlobal = idEstadoGlobal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<TabProvedor> getTabProvedorCollection() {
        return tabProvedorCollection;
    }

    public void setTabProvedorCollection(Collection<TabProvedor> tabProvedorCollection) {
        this.tabProvedorCollection = tabProvedorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoGlobal != null ? idEstadoGlobal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabEstadoGlobal)) {
            return false;
        }
        TabEstadoGlobal other = (TabEstadoGlobal) object;
        if ((this.idEstadoGlobal == null && other.idEstadoGlobal != null) || (this.idEstadoGlobal != null && !this.idEstadoGlobal.equals(other.idEstadoGlobal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TabEstadoGlobal[ idEstadoGlobal=" + idEstadoGlobal + " ]";
    }
    
}
