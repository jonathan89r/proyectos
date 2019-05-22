package persistencia;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistencia.Vendedor;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-02-20T11:48:52")
@StaticMetamodel(Sucursal.class)
public class Sucursal_ { 

    public static volatile SingularAttribute<Sucursal, Integer> idSucursal;
    public static volatile SingularAttribute<Sucursal, Integer> cantidadVendedores;
    public static volatile SingularAttribute<Sucursal, String> emailSucursal;
    public static volatile SingularAttribute<Sucursal, String> nombreSucursal;
    public static volatile SingularAttribute<Sucursal, String> ubicacionSucursal;
    public static volatile SingularAttribute<Sucursal, String> telefono;
    public static volatile CollectionAttribute<Sucursal, Vendedor> vendedorCollection;

}