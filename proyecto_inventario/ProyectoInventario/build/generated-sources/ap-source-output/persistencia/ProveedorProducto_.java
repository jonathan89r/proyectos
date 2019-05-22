package persistencia;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistencia.Inventario;
import persistencia.Productos;
import persistencia.Proveedor;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-02-20T11:48:52")
@StaticMetamodel(ProveedorProducto.class)
public class ProveedorProducto_ { 

    public static volatile SingularAttribute<ProveedorProducto, Proveedor> idProveedor;
    public static volatile SingularAttribute<ProveedorProducto, Integer> idProveedorProducto;
    public static volatile SingularAttribute<ProveedorProducto, Productos> idProducto;
    public static volatile CollectionAttribute<ProveedorProducto, Inventario> inventarioCollection;

}