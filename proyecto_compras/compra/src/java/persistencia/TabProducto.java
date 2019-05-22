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
@Table(name = "tab_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabProducto.findAll", query = "SELECT t FROM TabProducto t")
    , @NamedQuery(name = "TabProducto.findByCodigo", query = "SELECT t FROM TabProducto t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "TabProducto.findByNombre", query = "SELECT t FROM TabProducto t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TabProducto.findByPrecio", query = "SELECT t FROM TabProducto t WHERE t.precio = :precio")})
public class TabProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "nombre")
    private String nombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @OneToMany(mappedBy = "codigo")
    private Collection<TabVenta> tabVentaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "producto")
    private Collection<TabInventario> tabInventarioCollection;
    @JoinColumn(name = "categoria", referencedColumnName = "id_categoria")
    @ManyToOne(optional = false)
    private TabCategoria categoria=new TabCategoria();
    @JoinColumn(name = "provedor", referencedColumnName = "id_provedor")
    @ManyToOne(optional = false)
    private TabProvedor provedor= new TabProvedor();

    public TabProducto() {
    }

    public TabProducto(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @XmlTransient
    public Collection<TabVenta> getTabVentaCollection() {
        return tabVentaCollection;
    }

    public void setTabVentaCollection(Collection<TabVenta> tabVentaCollection) {
        this.tabVentaCollection = tabVentaCollection;
    }

    @XmlTransient
    public Collection<TabInventario> getTabInventarioCollection() {
        return tabInventarioCollection;
    }

    public void setTabInventarioCollection(Collection<TabInventario> tabInventarioCollection) {
        this.tabInventarioCollection = tabInventarioCollection;
    }

    public TabCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(TabCategoria categoria) {
        this.categoria = categoria;
    }

    public TabProvedor getProvedor() {
        return provedor;
    }

    public void setProvedor(TabProvedor provedor) {
        this.provedor = provedor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabProducto)) {
            return false;
        }
        TabProducto other = (TabProducto) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigo;
    }
    
}
