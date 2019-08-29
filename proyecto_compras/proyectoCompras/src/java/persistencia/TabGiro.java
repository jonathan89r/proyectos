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
@Table(name = "tab_giro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabGiro.findAll", query = "SELECT t FROM TabGiro t")
    , @NamedQuery(name = "TabGiro.findByIdGiro", query = "SELECT t FROM TabGiro t WHERE t.idGiro = :idGiro")
    , @NamedQuery(name = "TabGiro.findByNombreGiro", query = "SELECT t FROM TabGiro t WHERE t.nombreGiro = :nombreGiro")})
public class TabGiro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_giro")
    private Integer idGiro;
    @Column(name = "nombre_giro")
    private String nombreGiro;
    @OneToMany(mappedBy = "giros")
    private Collection<TabProvedor> tabProvedorCollection;

    public TabGiro() {
    }

    public TabGiro(Integer idGiro) {
        this.idGiro = idGiro;
    }

    public Integer getIdGiro() {
        return idGiro;
    }

    public void setIdGiro(Integer idGiro) {
        this.idGiro = idGiro;
    }

    public String getNombreGiro() {
        return nombreGiro;
    }

    public void setNombreGiro(String nombreGiro) {
        this.nombreGiro = nombreGiro;
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
        hash += (idGiro != null ? idGiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabGiro)) {
            return false;
        }
        TabGiro other = (TabGiro) object;
        if ((this.idGiro == null && other.idGiro != null) || (this.idGiro != null && !this.idGiro.equals(other.idGiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TabGiro[ idGiro=" + idGiro + " ]";
    }
    
}
