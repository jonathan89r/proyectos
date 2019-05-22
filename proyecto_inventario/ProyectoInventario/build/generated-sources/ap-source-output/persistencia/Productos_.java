package persistencia;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistencia.ProveedorProducto;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-02-20T11:48:52")
@StaticMetamodel(Productos.class)
public class Productos_ { 

    public static volatile SingularAttribute<Productos, Double> precio;
    public static volatile CollectionAttribute<Productos, ProveedorProducto> proveedorProductoCollection;
    public static volatile SingularAttribute<Productos, String> tipoProducto;
    public static volatile SingularAttribute<Productos, Date> fechaVencimiento;
    public static volatile SingularAttribute<Productos, String> descripcionProducto;
    public static volatile SingularAttribute<Productos, Integer> idProducto;
    public static volatile SingularAttribute<Productos, String> codigoProducto;
    public static volatile SingularAttribute<Productos, String> nombreProducto;

}