package managedBeans;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import mantenimiento.mantenimiento_proveedor;
import persistencia.Proveedor;

/**
 *
 * @author victor.rosalesUSAM
 */
@ManagedBean
@RequestScoped
public class beanProveedor {

    //LLAMADA AL METODO PARA LISTAR DATOS
    public List<Proveedor> listaProveedor;//VARIABLE PUBLICA PARA OBTENER DATOS

    //GETTER DE LA VARIABLE listaProducto PARA RETORNAR LA LISTA DE DATOS
    public List<Proveedor> getListaProveedor() {
        mantenimiento_proveedor m = new mantenimiento_proveedor();
        return m.consultarTodosProveedor();
    }

    public beanProveedor() {
    }

    private int id_proveedor;
    private String telefono;
    private String tipo_persona;
    private String contacto;
    private String fecha_ingreso;
    private String ubicacion_proveedor;

    //Metodo para guardar un proveedor
    public void guardar() throws IOException, ParseException {

        mantenimiento_proveedor man = new mantenimiento_proveedor();
        Proveedor pro = new Proveedor();
        String advertencia = "";
        SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");

        Date Today = new Date();
        pro.setIdProveedor(getId_proveedor());
        pro.setTelefono(getTelefono());
        pro.setTipoPersona(getTipo_persona());
        pro.setContacto(getContacto());
        pro.setFechaIngreso(Today);
        pro.setUbicacionProveedor(getUbicacion_proveedor());

        if (man.guardarDatos(pro) == 1) {

            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaProveedores.xhtml");
            advertencia = "Datos Ingresados Correctamente";
        } else {
            advertencia = "Error al ingresar informacion";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    //Metodo para actualizar un proveedor
    public void actualizarProveedor(int id) throws IOException, ParseException {
        mantenimiento_proveedor man = new mantenimiento_proveedor();
        Proveedor pro = new Proveedor();
        String advertencia = "";
        SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");
        Date Today = new Date();

        pro.setTelefono(getTelefono());
        pro.setTipoPersona(getTipo_persona());
        pro.setContacto(getContacto());
        pro.setFechaIngreso(formato.parse(getFecha_ingreso()));
        pro.setUbicacionProveedor(getUbicacion_proveedor());
        pro.setIdProveedor(getId_proveedor());

        if (man.ActualizarDatosProveedor(pro) == 1) {
            advertencia = "Correcto";
            FacesContext.getCurrentInstance().getExternalContext().redirect("../vistasConsultasAdmin/vistaProveedores.xhtml");
        } else {
            advertencia = "Incorrecto";
        }
        FacesMessage msg = new FacesMessage(advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

//  Metodo para eliminar
    public void delete(int id) {
        mantenimiento_proveedor man = new mantenimiento_proveedor();
        man.eliminarDatos(id);
    }

    public void consultarProveedorporID(int id) {
        mantenimiento_proveedor m = new mantenimiento_proveedor();
        Proveedor p = m.consultarProveedorporID(id);//SE OBTIENEN DATOS DEL PRODUCTO Y SE GUARDA EN UNA VARIABLE DEL TIPO TabProductos(PERSISTENCIA)
        String advertencia = "";
        SimpleDateFormat formato = new SimpleDateFormat("yy-MM-dd");
        if (p != null) { //VERIFICA SI LA VARIABLE d TRAE DATOS Y SE ASIGNAN LOS VALORES A LAS VARIABLES PRIVADAS
            this.id_proveedor = p.getIdProveedor();
            this.telefono = p.getTelefono();
            this.tipo_persona = p.getTipoPersona();
            this.contacto = p.getContacto();
            this.fecha_ingreso = formato.format(p.getFechaIngreso());
            this.ubicacion_proveedor = p.getUbicacionProveedor();
        } else {
            advertencia = "Datos no encontrados";
            FacesMessage msg = new FacesMessage(advertencia);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(String tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getUbicacion_proveedor() {
        return ubicacion_proveedor;
    }

    public void setUbicacion_proveedor(String ubicacion_proveedor) {
        this.ubicacion_proveedor = ubicacion_proveedor;
    }

}
