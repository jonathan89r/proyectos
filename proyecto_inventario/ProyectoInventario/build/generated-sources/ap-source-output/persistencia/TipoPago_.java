package persistencia;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import persistencia.Factura;

@Generated(value="EclipseLink-2.6.1.v20150605-rNA", date="2019-02-20T11:48:52")
@StaticMetamodel(TipoPago.class)
public class TipoPago_ { 

    public static volatile SingularAttribute<TipoPago, Integer> idTipoPago;
    public static volatile SingularAttribute<TipoPago, String> tipoPago;
    public static volatile CollectionAttribute<TipoPago, Factura> facturaCollection;

}