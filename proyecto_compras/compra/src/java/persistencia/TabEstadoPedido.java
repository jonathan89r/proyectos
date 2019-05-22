/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jonathan.rodriguez
 */
@Entity
@Table(name = "tab_estado_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabEstadoPedido.findAll", query = "SELECT t FROM TabEstadoPedido t")
    , @NamedQuery(name = "TabEstadoPedido.findByIdEstadoPedido", query = "SELECT t FROM TabEstadoPedido t WHERE t.idEstadoPedido = :idEstadoPedido")
    , @NamedQuery(name = "TabEstadoPedido.findByFechaEstado", query = "SELECT t FROM TabEstadoPedido t WHERE t.fechaEstado = :fechaEstado")})
public class TabEstadoPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_pedido")
    private Integer idEstadoPedido;
    @Column(name = "fecha_estado")
    private String fechaEstado;
    @JoinColumn(name = "codigo_pedido", referencedColumnName = "codigo_pedido")
    @ManyToOne(optional = false)
    private TabPedido codigoPedido;
    @JoinColumn(name = "estado", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private TabEstado estado;

    public TabEstadoPedido() {
    }

    public TabEstadoPedido(Integer idEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
    }

    public Integer getIdEstadoPedido() {
        return idEstadoPedido;
    }

    public void setIdEstadoPedido(Integer idEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
    }

    public String getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(String fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public TabPedido getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(TabPedido codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public TabEstado getEstado() {
        return estado;
    }

    public void setEstado(TabEstado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoPedido != null ? idEstadoPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TabEstadoPedido)) {
            return false;
        }
        TabEstadoPedido other = (TabEstadoPedido) object;
        if ((this.idEstadoPedido == null && other.idEstadoPedido != null) || (this.idEstadoPedido != null && !this.idEstadoPedido.equals(other.idEstadoPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TabEstadoPedido[ idEstadoPedido=" + idEstadoPedido + " ]";
    }
    
}
