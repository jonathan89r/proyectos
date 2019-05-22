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
@Table(name = "tab_gerencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabGerencia.findAll", query = "SELECT t FROM TabGerencia t")
    , @NamedQuery(name = "TabGerencia.findByIdGerencia", query = "SELECT t FROM TabGerencia t WHERE t.idGerencia = :idGerencia")
    , @NamedQuery(name = "TabGerencia.findByNombre", query = "SELECT t FROM TabGerencia t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TabGerencia.findByApellido", query = "SELECT t FROM TabGerencia t WHERE t.apellido = :apellido")
    , @NamedQuery(name = "TabGerencia.findByCodEmpleado", query = "SELECT t FROM TabGerencia t WHERE t.codEmpleado = :codEmpleado")
    , @NamedQuery(name = "TabGerencia.findByUsuario", query = "SELECT t FROM TabGerencia t WHERE t.usuario = :usuario")
    , @NamedQuery(name = "TabGerencia.findByPass", query = "SELECT t FROM TabGerencia t WHERE t.pass = :pass")})
public class TabGerencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_gerencia")
    private Integer idGerencia;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "cod_empleado")
    private String codEmpleado;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "pass")
    private String pass;
    @JoinColumn(name = "departamento", referencedColumnName = "id_departamento")
    @ManyToOne(optional = false)
    private TabDepartamento departamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gerencia")
    private Collection<TabPedido> tabPedidoCollection;

    public TabGerencia() {
    }

    public TabGerencia(Integer idGerencia) {
        this.idGerencia = idGerencia;
    }

    public Integer getIdGerencia() {
        return idGerencia;
    }

    public void setIdGerencia(Integer idGerencia) {
        this.idGerencia = idGerencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(String codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public TabDepartamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(TabDepartamento departamento) {
        this.departamento = departamento;
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
        hash += (idGerencia != null ? idGerencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabGerencia)) {
            return false;
        }
        TabGerencia other = (TabGerencia) object;
        if ((this.idGerencia == null && other.idGerencia != null) || (this.idGerencia != null && !this.idGerencia.equals(other.idGerencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TabGerencia[ idGerencia=" + idGerencia + " ]";
    }
    
}
