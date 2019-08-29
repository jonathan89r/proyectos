/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rodriguez
 */
@Entity
@Table(name = "tab_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabPedido.findAll", query = "SELECT t FROM TabPedido t")
    , @NamedQuery(name = "TabPedido.findByCodigoPedido", query = "SELECT t FROM TabPedido t WHERE t.codigoPedido = :codigoPedido")
    , @NamedQuery(name = "TabPedido.findByFechaPedido", query = "SELECT t FROM TabPedido t WHERE t.fechaPedido = :fechaPedido")
    , @NamedQuery(name = "TabPedido.findByIva", query = "SELECT t FROM TabPedido t WHERE t.iva = :iva")
    , @NamedQuery(name = "TabPedido.findByTotal", query = "SELECT t FROM TabPedido t WHERE t.total = :total")})
public class TabPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_pedido")
    private Integer codigoPedido;
    @Column(name = "fecha_pedido")
    @Temporal(TemporalType.DATE)
    private Date fechaPedido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "iva")
    private Double iva;
    @Column(name = "total")
    private Double total;
    @JoinColumn(name = "proyeccion", referencedColumnName = "id_proyeccion")
    @ManyToOne
    private TabProyeccion proyeccion;
    @JoinColumn(name = "gerencia", referencedColumnName = "id_gerencia")
    @ManyToOne
    private TabGerencia gerencia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoPedido")
    private Collection<TabEstadoPedido> tabEstadoPedidoCollection;

    public TabPedido() {
    }

    public TabPedido(Integer codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Integer getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Integer codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public TabProyeccion getProyeccion() {
        return proyeccion;
    }

    public void setProyeccion(TabProyeccion proyeccion) {
        this.proyeccion = proyeccion;
    }

    public TabGerencia getGerencia() {
        return gerencia;
    }

    public void setGerencia(TabGerencia gerencia) {
        this.gerencia = gerencia;
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
        hash += (codigoPedido != null ? codigoPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabPedido)) {
            return false;
        }
        TabPedido other = (TabPedido) object;
        if ((this.codigoPedido == null && other.codigoPedido != null) || (this.codigoPedido != null && !this.codigoPedido.equals(other.codigoPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TabPedido[ codigoPedido=" + codigoPedido + " ]";
    }
    
}
