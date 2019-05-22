package persistencia;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistencia.Factura;
import persistencia.Login;
import persistencia.Sucursal;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-02-20T11:48:52")
@StaticMetamodel(Vendedor.class)
public class Vendedor_ { 

    public static volatile SingularAttribute<Vendedor, String> apellidoVendedor;
    public static volatile SingularAttribute<Vendedor, String> nitVendedor;
    public static volatile SingularAttribute<Vendedor, String> residencia;
    public static volatile SingularAttribute<Vendedor, String> duiVendedor;
    public static volatile CollectionAttribute<Vendedor, Factura> facturaCollection;
    public static volatile SingularAttribute<Vendedor, String> nombreVendedor;
    public static volatile SingularAttribute<Vendedor, Sucursal> idSucursal;
    public static volatile SingularAttribute<Vendedor, String> fechaContratacion;
    public static volatile SingularAttribute<Vendedor, Integer> idVendedor;
    public static volatile SingularAttribute<Vendedor, String> emailVendedor;
    public static volatile SingularAttribute<Vendedor, String> celular;
    public static volatile SingularAttribute<Vendedor, Login> idLogin;
    public static volatile SingularAttribute<Vendedor, String> telefono;
    public static volatile SingularAttribute<Vendedor, String> referencia;

}