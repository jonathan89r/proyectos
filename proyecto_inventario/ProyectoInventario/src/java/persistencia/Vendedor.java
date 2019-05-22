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
@Table(name = "vendedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendedor.findAll", query = "SELECT v FROM Vendedor v")
    , @NamedQuery(name = "Vendedor.findByIdVendedor", query = "SELECT v FROM Vendedor v WHERE v.idVendedor = :idVendedor")
    , @NamedQuery(name = "Vendedor.findByNombreVendedor", query = "SELECT v FROM Vendedor v WHERE v.nombreVendedor = :nombreVendedor")
    , @NamedQuery(name = "Vendedor.findByApellidoVendedor", query = "SELECT v FROM Vendedor v WHERE v.apellidoVendedor = :apellidoVendedor")
    , @NamedQuery(name = "Vendedor.findByEmailVendedor", query = "SELECT v FROM Vendedor v WHERE v.emailVendedor = :emailVendedor")
    , @NamedQuery(name = "Vendedor.findByDuiVendedor", query = "SELECT v FROM Vendedor v WHERE v.duiVendedor = :duiVendedor")
    , @NamedQuery(name = "Vendedor.findByNitVendedor", query = "SELECT v FROM Vendedor v WHERE v.nitVendedor = :nitVendedor")
    , @NamedQuery(name = "Vendedor.findByResidencia", query = "SELECT v FROM Vendedor v WHERE v.residencia = :residencia")
    , @NamedQuery(name = "Vendedor.findByTelefono", query = "SELECT v FROM Vendedor v WHERE v.telefono = :telefono")
    , @NamedQuery(name = "Vendedor.findByCelular", query = "SELECT v FROM Vendedor v WHERE v.celular = :celular")
    , @NamedQuery(name = "Vendedor.findByReferencia", query = "SELECT v FROM Vendedor v WHERE v.referencia = :referencia")
    , @NamedQuery(name = "Vendedor.findByFechaContratacion", query = "SELECT v FROM Vendedor v WHERE v.fechaContratacion = :fechaContratacion")})
public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vendedor")
    private Integer idVendedor;
    @Basic(optional = false)
    @Column(name = "nombre_vendedor")
    private String nombreVendedor;
    @Basic(optional = false)
    @Column(name = "apellido_vendedor")
    private String apellidoVendedor;
    @Basic(optional = false)
    @Column(name = "email_vendedor")
    private String emailVendedor;
    @Basic(optional = false)
    @Column(name = "dui_vendedor")
    private String duiVendedor;
    @Basic(optional = false)
    @Column(name = "nit_vendedor")
    private String nitVendedor;
    @Basic(optional = false)
    @Column(name = "residencia")
    private String residencia;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "celular")
    private String celular;
    @Basic(optional = false)
    @Column(name = "referencia")
    private String referencia;
    @Basic(optional = false)
    @Column(name = "fecha_contratacion")
    private String fechaContratacion;
    @JoinColumn(name = "id_login", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Login idLogin;
    @JoinColumn(name = "id_sucursal", referencedColumnName = "id_sucursal")
    @ManyToOne(optional = false)
    private Sucursal idSucursal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idVendedor")
    private Collection<Factura> facturaCollection;

    public Vendedor() {
    }

    public Vendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Vendedor(Integer idVendedor, String nombreVendedor, String apellidoVendedor, String emailVendedor, String duiVendedor, String nitVendedor, String residencia, String telefono, String celular, String referencia, String fechaContratacion) {
        this.idVendedor = idVendedor;
        this.nombreVendedor = nombreVendedor;
        this.apellidoVendedor = apellidoVendedor;
        this.emailVendedor = emailVendedor;
        this.duiVendedor = duiVendedor;
        this.nitVendedor = nitVendedor;
        this.residencia = residencia;
        this.telefono = telefono;
        this.celular = celular;
        this.referencia = referencia;
        this.fechaContratacion = fechaContratacion;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getApellidoVendedor() {
        return apellidoVendedor;
    }

    public void setApellidoVendedor(String apellidoVendedor) {
        this.apellidoVendedor = apellidoVendedor;
    }

    public String getEmailVendedor() {
        return emailVendedor;
    }

    public void setEmailVendedor(String emailVendedor) {
        this.emailVendedor = emailVendedor;
    }

    public String getDuiVendedor() {
        return duiVendedor;
    }

    public void setDuiVendedor(String duiVendedor) {
        this.duiVendedor = duiVendedor;
    }

    public String getNitVendedor() {
        return nitVendedor;
    }

    public void setNitVendedor(String nitVendedor) {
        this.nitVendedor = nitVendedor;
    }

    public String getResidencia() {
        return residencia;
    }

    public void setResidencia(String residencia) {
        this.residencia = residencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(String fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Login getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Login idLogin) {
        this.idLogin = idLogin;
    }

    public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }

    @XmlTransient
    public Collection<Factura> getFacturaCollection() {
        return facturaCollection;
    }

    public void setFacturaCollection(Collection<Factura> facturaCollection) {
        this.facturaCollection = facturaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVendedor != null ? idVendedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendedor)) {
            return false;
        }
        Vendedor other = (Vendedor) object;
        if ((this.idVendedor == null && other.idVendedor != null) || (this.idVendedor != null && !this.idVendedor.equals(other.idVendedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Vendedor[ idVendedor=" + idVendedor + " ]";
    }
    
}
