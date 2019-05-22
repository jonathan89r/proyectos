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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.rosalesUSAM
 */
@Entity
@Table(name = "login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l")
    , @NamedQuery(name = "Login.findByIdUsuario", query = "SELECT l FROM Login l WHERE l.idUsuario = :idUsuario")
    , @NamedQuery(name = "Login.findByTipoUsuarioandPass", query = "SELECT l FROM Login l WHERE l.user = :user AND l.pass = :pass")
    , @NamedQuery(name = "Login.user_respuesta", query = "SELECT l FROM Login l WHERE l.user = :user AND l.respuesta = :respuesta AND l.pregunta = :pregunta")
    , @NamedQuery(name = "Login.findByUser", query = "SELECT l FROM Login l WHERE l.user = :user")
    , @NamedQuery(name = "Login.findByPass", query = "SELECT l FROM Login l WHERE l.pass = :pass")
    , @NamedQuery(name = "Login.findByTipoUsuario", query = "SELECT l FROM Login l WHERE l.tipoUsuario = :tipoUsuario")
    , @NamedQuery(name = "Login.findByRespuesta", query = "SELECT l FROM Login l WHERE l.respuesta = :respuesta")
    , @NamedQuery(name = "Login.findByPregunta", query = "SELECT l FROM Login l WHERE l.pregunta = :pregunta")})
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "user")
    private String user;
    @Basic(optional = false)
    @Column(name = "pass")
    private String pass;
    @Basic(optional = false)
    @Column(name = "tipo_usuario")
    private int tipoUsuario;
    @Column(name = "respuesta")
    private String respuesta;
    @Column(name = "pregunta")
    private String pregunta;

    public Login() {
    }

    public Login(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Login(Integer idUsuario, String user, String pass, int tipoUsuario) {
        this.idUsuario = idUsuario;
        this.user = user;
        this.pass = pass;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Login[ idUsuario=" + idUsuario + " ]";
    }

}
