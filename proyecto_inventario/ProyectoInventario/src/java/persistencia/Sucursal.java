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
 * @author jonathan.rodriguez
 */
@Entity
@Table(name = "sucursal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sucursal.findAll", query = "SELECT s FROM Sucursal s")
    , @NamedQuery(name = "Sucursal.findByIdSucursal", query = "SELECT s FROM Sucursal s WHERE s.idSucursal = :idSucursal")
    , @NamedQuery(name = "Sucursal.findByNombreSucursal", query = "SELECT s FROM Sucursal s WHERE s.nombreSucursal = :nombreSucursal")
    , @NamedQuery(name = "Sucursal.findByUbicacionSucursal", query = "SELECT s FROM Sucursal s WHERE s.ubicacionSucursal = :ubicacionSucursal")
    , @NamedQuery(name = "Sucursal.findByTelefono", query = "SELECT s FROM Sucursal s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "Sucursal.findByEmailSucursal", query = "SELECT s FROM Sucursal s WHERE s.emailSucursal = :emailSucursal")
    , @NamedQuery(name = "Sucursal.findByCantidadVendedores", query = "SELECT s FROM Sucursal s WHERE s.cantidadVendedores = :cantidadVendedores")})
public class Sucursal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sucursal")
    private Integer idSucursal;
    @Basic(optional = false)
    @Column(name = "nombre_sucursal")
    private String nombreSucursal;
    @Basic(optional = false)
    @Column(name = "ubicacion_sucursal")
    private String ubicacionSucursal;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "email_sucursal")
    private String emailSucursal;
    @Basic(optional = false)
    @Column(name = "cantidad_vendedores")
    private int cantidadVendedores;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSucursal")
    private Collection<Vendedor> vendedorCollection;

    public Sucursal() {
    }

    public Sucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Sucursal(Integer idSucursal, String nombreSucursal, String ubicacionSucursal, String telefono, String emailSucursal, int cantidadVendedores) {
        this.idSucursal = idSucursal;
        this.nombreSucursal = nombreSucursal;
        this.ubicacionSucursal = ubicacionSucursal;
        this.telefono = telefono;
        this.emailSucursal = emailSucursal;
        this.cantidadVendedores = cantidadVendedores;
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getUbicacionSucursal() {
        return ubicacionSucursal;
    }

    public void setUbicacionSucursal(String ubicacionSucursal) {
        this.ubicacionSucursal = ubicacionSucursal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmailSucursal() {
        return emailSucursal;
    }

    public void setEmailSucursal(String emailSucursal) {
        this.emailSucursal = emailSucursal;
    }

    public int getCantidadVendedores() {
        return cantidadVendedores;
    }

    public void setCantidadVendedores(int cantidadVendedores) {
        this.cantidadVendedores = cantidadVendedores;
    }

    @XmlTransient
    public Collection<Vendedor> getVendedorCollection() {
        return vendedorCollection;
    }

    public void setVendedorCollection(Collection<Vendedor> vendedorCollection) {
        this.vendedorCollection = vendedorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSucursal != null ? idSucursal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sucursal)) {
            return false;
        }
        Sucursal other = (Sucursal) object;
        if ((this.idSucursal == null && other.idSucursal != null) || (this.idSucursal != null && !this.idSucursal.equals(other.idSucursal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Sucursal[ idSucursal=" + idSucursal + " ]";
    }
    
}
