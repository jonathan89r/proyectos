/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed_bean;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;
import mantenimiento.mantenimiento_gerencia;
import mantenimiento.mantenimiento_producto;
import persistencia.TabDepartamento;
import persistencia.TabGerencia;
import persistencia.TabProducto;

/**
 *
 * @author william.fuentesusam
 */
@ManagedBean
@RequestScoped
public class bean_gerencia {

    /**
     * Creates a new instance of bean_gerencia
     */
    public bean_gerencia() {
    }

    private int id_gerencia;
    private int departamento;
    private String nombre;
    private String apellido;
    private String cod_empleado;
    private String usuario;
    private String pass;
    private List<TabGerencia> lista;
    private List<TabDepartamento> listadepa;

    mantenimiento_gerencia m = new mantenimiento_gerencia();
    TabGerencia g = new TabGerencia();
    TabDepartamento d = new TabDepartamento();
    String adv = null;

    public int getId_gerencia() {
        return id_gerencia;
    }

    public void setId_gerencia(int id_gerencia) {
        this.id_gerencia = id_gerencia;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
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

    public String getCod_empleado() {
        return cod_empleado;
    }

    public void setCod_empleado(String cod_empleado) {
        this.cod_empleado = cod_empleado;
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

    public List<TabGerencia> getLista() {
        return m.mostrar();
    }

    public void setLista(List<TabGerencia> lista) {
        this.lista = lista;
    }

    public List<TabDepartamento> getListadepa() {
        listadepa = new ArrayList<>();
        listadepa = m.mostrardepa();
        return listadepa;
    }

    public void setListadepa(List<TabDepartamento> listadepa) {
        this.listadepa = listadepa;
    }

    public void eliminar(int id) {
        g.setIdGerencia(id);
        m.metodos(3, g);
    }

    public void guardar() {

        g.setIdGerencia(id_gerencia);
        d.setIdDepartamento(departamento);
        g.setDepartamento(d);
        g.setNombre(nombre);
        g.setApellido(apellido);
        g.setCodEmpleado(cod_empleado);
        g.setUsuario(usuario);
        g.setPass(pass);

        TabGerencia object = m.validar(cod_empleado);
        TabGerencia us = m.validarUser(usuario);

        if (object != null) {
            adv = "CodEmpleado ya existe";

            if (us != null) {
                adv = "Usuario ya existe";
            }

        } else {
            if (m.metodos(1, g) == true) {
                adv = "DATOS INGRESADOS DE FORMA CORRECTA";
            } else {
                adv = "DATOS NO PUDIERON SER INGRESADOS";
            }
        }

        FacesMessage msj = new FacesMessage(adv);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }

    public void mostrarporID(int id) {

        g = m.mostrarporID(id);

        if (g != null) {

            this.id_gerencia = g.getIdGerencia();
            this.departamento = g.getDepartamento().getIdDepartamento();
            this.nombre = g.getNombre();
            this.apellido = g.getApellido();
            this.cod_empleado = g.getCodEmpleado();
            this.usuario = g.getUsuario();
            this.pass = g.getPass();

        } else {
            adv = "DATOS NO FUERON ENCONTRADOS";
            FacesMessage msj = new FacesMessage(adv);
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }

    }

    public void actualizar() {

        g.setNombre(getNombre());
        g.setApellido(getApellido());
        g.setCodEmpleado(getCod_empleado());
        g.setUsuario(getUsuario());
        g.setPass(getPass());
        d.setIdDepartamento(getDepartamento());
        g.setDepartamento(d);
        g.setIdGerencia(getId_gerencia());

        TabGerencia object = m.validarUser(usuario);

        if (m.metodos(2, g) != true) {
            adv = "DATOS ACTUALIZADOS DE MANERA CORRECTA";
        } else {
            adv = "DATOS NO PUDIERON SER ACTUALIZADOS";
        }

        FacesMessage msj = new FacesMessage(adv);
        FacesContext.getCurrentInstance().addMessage(null, msj);
    }

    public void imprimir() {

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            PdfWriter.getInstance(document, baos);
            document.open();

            PdfPTable table = new PdfPTable(6);

            mantenimiento_gerencia mans = new mantenimiento_gerencia();
            List<TabGerencia> allist = mans.mostrar();

            PdfPCell cell = new PdfPCell(new Paragraph("\n" + "Usuarios registrados" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            14, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setColspan(6);
            table.addCell(cell);

            PdfPCell cellCodigo = new PdfPCell(new Paragraph("\n" + "Departamento" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellCodigo.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellCodigo);

            PdfPCell cellNombre = new PdfPCell(new Paragraph("\n" + "Nombres" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellNombre.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellNombre);

            PdfPCell cellCategoria = new PdfPCell(new Paragraph("\n" + "Aepllidos" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK
                    )));
            cellCategoria.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellCategoria.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellCategoria);

            PdfPCell cellPrecio = new PdfPCell(new Paragraph("\n" + "Codigo de empleado" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellPrecio.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellPrecio.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellPrecio);

            PdfPCell cellProveedor = new PdfPCell(new Paragraph("\n" + "Usuario" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellProveedor.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellProveedor.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellProveedor);

            PdfPCell cellPass = new PdfPCell(new Paragraph("\n" + "Password" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellPass.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellPass.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellPass);

            for (TabGerencia tabGerencia : allist) {
                table.addCell(tabGerencia.getDepartamento().getNombreDepartamento());
                table.addCell(tabGerencia.getNombre());
                table.addCell(tabGerencia.getApellido());
                table.addCell(tabGerencia.getCodEmpleado());
                table.addCell(tabGerencia.getUsuario());
                table.addCell(tabGerencia.getPass());
            }

            document.add(table);
        } catch (Exception e) {
            System.err.println("Ocurrio un error al crear el archivo");
        }
        document.close();

        FacesContext context = FacesContext.getCurrentInstance();
        Object response = context.getExternalContext().getResponse();

        if (response instanceof HttpServletResponse) {
            HttpServletResponse hsr = (HttpServletResponse) response;
            hsr.setContentType("application/pdf;charset=UTF-8");
            hsr.setHeader("Content-Disposition", "filename=" + "gerencia.pdf");
            hsr.setContentLength(baos.size());

            try {
                ServletOutputStream out = hsr.getOutputStream();
                baos.writeTo(out);
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            context.responseComplete();
        }
    }

    public void guardar2() {
        
        
    }
}
