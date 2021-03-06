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
import mantenimiento.mantenimiento_logistica;
import mantenimiento.mantenimiento_producto;
import persistencia.InventarioProducto;
import persistencia.PedidoProyeccion;
import persistencia.TabCategoria;
import persistencia.TabInventario;
import persistencia.TabProducto;
import persistencia.TabProvedor;

/**
 *
 * @author william.fuentesusam
 */
@ManagedBean
@RequestScoped
public class bean_logistica {

    /**
     * Creates a new instance of bean_logistica
     */
    public bean_logistica() {
    }

    private String codigo;
    private String nombre;
    private int categoria;
    private int proveedor;
    private double precio;
    private int id_inventario;
    private int stock;
    private List<InventarioProducto> lista;
    private List<PedidoProyeccion> lista2;
    private List<TabProvedor> listapro;
    private List<TabCategoria> listacat;

    mantenimiento_logistica m = new mantenimiento_logistica();

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

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<InventarioProducto> getLista() {
        mantenimiento_logistica m = new mantenimiento_logistica();
        lista = new ArrayList<>();
        lista = m.lista();
        return lista;
    }

    public void setLista(List<InventarioProducto> lista) {
        this.lista = lista;
    }

    public List<TabProvedor> getListapro() {
        listapro = new ArrayList<>();
        listapro = m.listapro();
        return listapro;
    }

    public void setListapro(List<TabProvedor> listapro) {
        this.listapro = listapro;
    }

    public List<TabCategoria> getListacat() {
        listacat = new ArrayList<>();
        listacat = m.listacat();
        return listacat;
    }

    public void setListacat(List<TabCategoria> listacat) {
        this.listacat = listacat;
    }

    /**
     * @return the lista2
     */
    public List<PedidoProyeccion> getLista2() {
        lista2 = m.lista2();
        return lista2;
    }

    /**
     * @param lista2 the lista2 to set
     */
    public void setLista2(List<PedidoProyeccion> lista2) {
        this.lista2 = lista2;
    }

//    public void eliminar(String codigo, int id) {
//
//        TabInventario in = new TabInventario();
//        TabProducto pro = new TabProducto();
//        String Advertencia = null;
//
//        pro.setCodigo(codigo);
//        in.setIdInventario(id);
//
//        if (m.eliminar(pro, in) == true) {
//            Advertencia = "Datos Eliminados Correctamente";
//        } else {
//            Advertencia = "Datos no pudieron ser eliminados";
//
//        }
//
//        FacesMessage msg = new FacesMessage(Advertencia);
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//
//    }
    public void eliminar(int id, String Codigo) {
        m.eliminar1(id);
        m.eliminar(Codigo);
    }

    public void guardar() {

        TabInventario in = new TabInventario();
        TabProducto pro = new TabProducto();
        TabProvedor p = new TabProvedor();
        TabCategoria c = new TabCategoria();

        String Advertencia = null;

        pro.setCodigo(codigo);
        in.setProducto(pro);
        pro.setNombre(nombre);
        p.setIdProvedor(proveedor);
        pro.setProvedor(p);
        c.setIdCategoria(categoria);
        pro.setCategoria(c);
        pro.setPrecio(precio);
        in.setStock(stock);
        in.setIdInventario(id_inventario);

        if (m.guardar(pro, in) == 1) {
            Advertencia = "Datos correctamente guardados";
        } else {
            Advertencia = "Error la ingresar datos";
        }
        FacesMessage msg = new FacesMessage(Advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void mostrarporID(String codigo, int id) {
        TabInventario in = new TabInventario();
        TabProducto pro = new TabProducto();

        in = m.mostrarporID2(id);
        pro = m.mostrarporID(codigo);

        if (in != null && pro != null) {

            this.codigo = pro.getCodigo();
            this.nombre = pro.getNombre();
            this.categoria = pro.getCategoria().getIdCategoria();
            this.proveedor = pro.getProvedor().getIdProvedor();
            this.precio = pro.getPrecio();
            this.stock = in.getStock();
            this.id_inventario = in.getIdInventario();

        } else {
            String Advertencia = "Datos no encontrados";
            FacesMessage msj = new FacesMessage(Advertencia);
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }

    }

    public void actualizar() {
        TabInventario in = new TabInventario();
        TabProducto pro = new TabProducto();
        TabProvedor p = new TabProvedor();
        TabCategoria c = new TabCategoria();

        String Advertencia = null;

        pro.setNombre(getNombre());
        c.setIdCategoria(getCategoria());
        pro.setCategoria(c);
        p.setIdProvedor(getProveedor());
        pro.setProvedor(p);
        pro.setPrecio(getPrecio());
        in.setStock(getStock());
        pro.setCodigo(getCodigo());
        in.setIdInventario(getId_inventario());

        if (m.actualizar(pro, in) == 1) {
            Advertencia = "Datos Actualizados correctamente";
        } else {
            Advertencia = "Error al Actualizar datos";
        }
        FacesMessage msg = new FacesMessage(Advertencia);
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void imprimir() {

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            PdfWriter.getInstance(document, baos);
            document.open();

            PdfPTable table = new PdfPTable(6);
//            float[] medida = {2.40f, 6.70f, 7.70f};
//            table.setWidths(medida);

            mantenimiento_logistica man = new mantenimiento_logistica();
            List<InventarioProducto> allist = man.lista();

            PdfPCell cell = new PdfPCell(new Paragraph("\n" + "PRODUCTOS REGISTRADOS " + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            14, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            cell.setColspan(7);
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

            PdfPCell cellProveedor = new PdfPCell(new Paragraph("\n" + "Proveedor" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellProveedor.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellProveedor.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellProveedor);

            PdfPCell cellPrecio = new PdfPCell(new Paragraph("\n" + "Precio" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellPrecio.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellPrecio.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellPrecio);

            PdfPCell cellStock = new PdfPCell(new Paragraph("\n" + "Stock" + "\n\n",
                    FontFactory.getFont("arial", // fuente
                            8, // tamaño
                            Font.NORMAL, // estilo
                            BaseColor.BLACK)));
            cellStock.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellStock.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cellStock);

            for (InventarioProducto lista : allist) {
                table.addCell(lista.getCodigo());
                table.addCell(lista.getNombre());
                table.addCell(lista.getNombreCategoria());
                table.addCell(lista.getNombreProveedor());
                table.addCell("$" + lista.getPrecio().toString());
                table.addCell(lista.getStock().toString());
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
            hsr.setHeader("Content-Disposition", "filename=" + "productos en inventario.pdf");
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
