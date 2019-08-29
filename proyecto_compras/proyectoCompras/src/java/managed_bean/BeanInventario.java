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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import mantenimiento.mantenimiento_inventario;
import mantenimiento.mantenimiento_producto;
import persistencia.TabInventario;
import persistencia.TabProducto;

/**
 *
 * @author roberto.hernandezUSA
 */
@ManagedBean(name = "BeanInventario")
@RequestScoped
public class BeanInventario {

    private TabInventario inventario;

    private List<TabInventario> listaInventario;

    mantenimiento_inventario man = new mantenimiento_inventario();

    @PostConstruct
    public void init() {
        this.inventario = new TabInventario(0);
    }

    public void guardar() {
        if (man.guardar(inventario)) {
            respuesta("guardando..");
            limpiar();
        } else {
            respuesta("no guardar");
        }
    }

    public void limpiar() {
        this.inventario = null;

    }

    public void borrar(int id) {
        if (man.borrarId(id)) {
            respuesta("borrando..");
        } else {
            respuesta("no borra");
        }
    }

    public void consultarId(int id) {
        this.inventario = man.consultarId(id);
    }

    public void actualizar() throws IOException {
        if (man.actualizar(inventario)) {

            // FacesContext.getCurrentInstance().getExternalContext().redirect("inventario.xhtml");
            respuesta("actualizando..");
        } else {
            respuesta("no actualiza");
        }
    }

    public BeanInventario() {
    }

    public TabInventario getInventario() {
        return inventario;
    }

    public void setInventario(TabInventario inventario) {
        this.inventario = inventario;
    }

    private void respuesta(String respuesta) {
        FacesMessage msg = new FacesMessage(respuesta);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<TabInventario> getListaInventario() {
        this.listaInventario = new ArrayList<>();
        this.listaInventario = man.consultarTodo();
        return listaInventario;
    }

    public void setListaInventario(List<TabInventario> listaInventario) {
        this.listaInventario = listaInventario;
    }

    public void imprimir() {

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            PdfWriter.getInstance(document, baos);
            document.open();

            PdfPTable table = new PdfPTable(6);

            mantenimiento_inventario mans = new mantenimiento_inventario();
            List<TabInventario> allist = mans.consultarTodo();

            PdfPCell cell = new PdfPCell(new Paragraph("\n" + "Inventario" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            14, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setColspan(6);
            table.addCell(cell);

            PdfPCell cellid = new PdfPCell(new Paragraph("\n" + "Id del inventario" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellid.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellid.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellid);

            PdfPCell cellCodigo = new PdfPCell(new Paragraph("\n" + "producto" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellCodigo.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellCodigo);

            PdfPCell cellNombre = new PdfPCell(new Paragraph("\n" + "producto" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellNombre.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellNombre.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellNombre);

            PdfPCell cellCategoria = new PdfPCell(new Paragraph("\n" + "Categoria" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK
                    )));
            cellCategoria.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellCategoria.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellCategoria);

            PdfPCell cellPrecio = new PdfPCell(new Paragraph("\n" + "Proveedor" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellPrecio.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellPrecio.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellPrecio);

            PdfPCell cellProveedor = new PdfPCell(new Paragraph("\n" + "Stock" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellProveedor.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellProveedor.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellProveedor);

            for (TabInventario tabInventario : allist) {
                table.addCell(tabInventario.getIdInventario().toString());
                table.addCell(tabInventario.getProducto().getCodigo());
                table.addCell(tabInventario.getProducto().getNombre());
                table.addCell(tabInventario.getProducto().getCategoria().getNombreCategoria());
                table.addCell(tabInventario.getProducto().getProvedor().getNombreProveedor());
                table.addCell(tabInventario.getStock().toString());
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
            hsr.setHeader("Content-Disposition", "filename=" + "inventario.pdf");
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

}
