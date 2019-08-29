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
@Table(name = "tab_departamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabDepartamento.findAll", query = "SELECT t FROM TabDepartamento t")
    , @NamedQuery(name = "TabDepartamento.findByIdDepartamento", query = "SELECT t FROM TabDepartamento t WHERE t.idDepartamento = :idDepartamento")
    , @NamedQuery(name = "TabDepartamento.findByNombreDepartamento", query = "SELECT t FROM TabDepartamento t WHERE t.nombreDepartamento = :nombreDepartamento")})
public class TabDepartamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_departamento")
    private Integer idDepartamento;
    @Column(name = "nombre_departamento")
    private String nombreDepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "departamento")
    private Collection<TabGerencia> tabGerenciaCollection;

    public TabDepartamento() {
    }

    public TabDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    @XmlTransient
    public Collection<TabGerencia> getTabGerenciaCollection() {
        return tabGerenciaCollection;
    }

    public void setTabGerenciaCollection(Collection<TabGerencia> tabGerenciaCollection) {
        this.tabGerenciaCollection = tabGerenciaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDepartamento != null ? idDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabDepartamento)) {
            return false;
        }
        TabDepartamento other = (TabDepartamento) object;
        if ((this.idDepartamento == null && other.idDepartamento != null) || (this.idDepartamento != null && !this.idDepartamento.equals(other.idDepartamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TabDepartamento[ idDepartamento=" + idDepartamento + " ]";
    }
    
}
