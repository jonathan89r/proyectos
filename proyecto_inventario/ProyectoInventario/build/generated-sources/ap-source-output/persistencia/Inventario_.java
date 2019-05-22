package persistencia;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistencia.Factura;
import persistencia.ProveedorProducto;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-02-20T11:48:52")
@StaticMetamodel(Inventario.class)
public class Inventario_ { 

    public static volatile SingularAttribute<Inventario, Integer> stockMinimo;
    public static volatile SingularAttribute<Inventario, Integer> existencias;
    public static volatile SingularAttribute<Inventario, String> estado;
    public static volatile SingularAttribute<Inventario, ProveedorProducto> idProveedorProducto;
    public static volatile SingularAttribute<Inventario, Integer> idInventario;
    public static volatile CollectionAttribute<Inventario, Factura> facturaCollection;

}