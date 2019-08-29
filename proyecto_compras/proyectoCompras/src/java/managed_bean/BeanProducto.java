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
import mantenimiento.mantenimiento_categoria;
import mantenimiento.mantenimiento_producto;
import mantenimiento.mantenimiento_proveedor;
import persistencia.TabCategoria;
import persistencia.TabProducto;
import persistencia.TabProvedor;

/**
 *
 * @author roberto.hernandezUSA
 */
@ManagedBean(name = "BeanProducto")
@RequestScoped
public class BeanProducto {

    private TabProducto producto;
    private TabProducto c;

    private List<TabCategoria> listaCategoria;
    private List<TabProvedor> listaProveedor;
    private List<TabProducto> listaProducto;

    mantenimiento_producto man = new mantenimiento_producto();
    mantenimiento_categoria man2 = new mantenimiento_categoria();
    mantenimiento_proveedor manP = new mantenimiento_proveedor();

    @PostConstruct
    public void init() {
        this.producto = new TabProducto();

    }
    private int contador = 1;

    public BeanProducto() {
    }

    public void guardar() throws IOException {
        TabProducto vali = man.consultarProducto(producto.getNombre());
        TabProducto pro = man.consultarId(producto.getCodigo());

        if (vali != null || pro != null) {
            if (vali != null) {
                respuesta("nombre ya existe");
                limpiar();
            }
            if (pro != null) {
                respuesta("codigo ya existe");
                limpiar();

            }
        } else {
            if (man.guardar(producto)) {
                respuesta("exito");
                limpiar();

            } else {
                respuesta("no guarda.");
                limpiar();
            }
        }
    }

    public void limpiar() {
        this.producto.setCodigo("");
        this.producto.setNombre("");
        this.producto.setPrecio(0.0);

    }

    public void consultar(String id) {
        this.producto = man.consultarId(id);
    }

    public void borrar(String id) {
        if (man.borrarId(id)) {
            respuesta("borrando..");
        } else {
            respuesta("no borra");
        }
    }

    public void actualizar() throws IOException {
        TabProducto vali = man.consultarProducto(producto.getNombre());
        TabProducto pro = man.consultarId(producto.getCodigo());

        boolean baria = false;
        baria = producto.getCodigo().equals(pro.getCodigo());

        if (baria) {
            if (producto.getNombre().equals(pro.getNombre())) {
                if (man.actualizar(producto)) {
                    respuesta("actualizando");
                }

            } else {
                if (vali != null) {
                    respuesta("error datos ya existen");
                } else {
                    if (man.actualizar(producto)) {
                        respuesta("actualizando....");
                    }
                }
            }

        }

    }

    public TabProducto getProducto() {
        return producto;
    }

    public void setProducto(TabProducto producto) {
        this.producto = producto;
    }

    public List<TabCategoria> getListaCategoria() {
        this.listaCategoria = new ArrayList<>();
        this.listaCategoria = man2.consultarCategoria();
        return listaCategoria;
    }

    public void setListaCategoria(List<TabCategoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public List<TabProvedor> getListaProveedor() {
        this.listaProveedor = new ArrayList<>();
        this.listaProveedor = manP.mostrarProveedores();
        return listaProveedor;
    }

    public void setListaProveedor(List<TabProvedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public List<TabProducto> getListaProducto() {
        this.listaProducto = new ArrayList<>();
        this.listaProducto = man.consultarTodo();
        return listaProducto;
    }

    public void setListaProducto(List<TabProducto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    private void respuesta(String respuesta) {
        FacesMessage msg = new FacesMessage(respuesta);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void imprimir() {

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            PdfWriter.getInstance(document, baos);
            document.open();

            PdfPTable table = new PdfPTable(5);
//            float[] medida = {2.40f, 6.70f, 7.70f};
//            table.setWidths(medida);

            mantenimiento_producto mans = new mantenimiento_producto();
            List<TabProducto> allist = mans.consultarTodo();

            PdfPCell cell = new PdfPCell(new Paragraph("\n" + "Productos registrados" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            14, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setColspan(6);
            table.addCell(cell);

            PdfPCell cellCodigo = new PdfPCell(new Paragraph("\n" + "Codigo del producto" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellCodigo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellCodigo.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellCodigo);

            PdfPCell cellNombre = new PdfPCell(new Paragraph("\n" + "Nombre del producto" + "\n\n",
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

            PdfPCell cellPrecio = new PdfPCell(new Paragraph("\n" + "Precio" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellPrecio.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellPrecio.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellPrecio);

            PdfPCell cellProveedor = new PdfPCell(new Paragraph("\n" + "Proveedor" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellProveedor.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellProveedor.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellProveedor);

            for (TabProducto tabProducto : allist) {
                table.addCell(tabProducto.getCodigo());
                table.addCell(tabProducto.getNombre());
                table.addCell(tabProducto.getCategoria().getNombreCategoria());
                table.addCell("$" + tabProducto.getPrecio().toString());
                table.addCell(tabProducto.getProvedor().getNombreProveedor());
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
            hsr.setHeader("Content-Disposition", "filename=" + "productos.pdf");
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

    /**
     * @return the contador
     */
    public int getContador() {
        return contador;
    }

    /**
     * @param contador the contador to set
     */
    public void setContador(int contador) {
        this.contador = contador;
    }

    /**
     * @return the c
     */
    public TabProducto getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(TabProducto c) {
        this.c = c;
    }

}
