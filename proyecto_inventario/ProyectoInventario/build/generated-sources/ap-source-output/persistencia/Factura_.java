package persistencia;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistencia.Cliente;
import persistencia.Inventario;
import persistencia.TipoPago;
import persistencia.Vendedor;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-02-20T11:48:52")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, Date> fechaFacturacion;
    public static volatile SingularAttribute<Factura, Cliente> idCliente;
    public static volatile SingularAttribute<Factura, Integer> idFactura;
    public static volatile SingularAttribute<Factura, Vendedor> idVendedor;
    public static volatile SingularAttribute<Factura, Double> totalVentas;
    public static volatile SingularAttribute<Factura, TipoPago> tipoPago;
    public static volatile SingularAttribute<Factura, Integer> totalProductos;
    public static volatile SingularAttribute<Factura, Inventario> idInventario;

}