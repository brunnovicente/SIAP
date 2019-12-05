package persistencia.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItensPedido.class)
public abstract class ItensPedido_ {

	public static volatile SingularAttribute<ItensPedido, Double> preco;
	public static volatile SingularAttribute<ItensPedido, Integer> codigo;
	public static volatile SingularAttribute<ItensPedido, Double> total;
	public static volatile SingularAttribute<ItensPedido, Produto> produto;
	public static volatile SingularAttribute<ItensPedido, Pedido> pedido;
	public static volatile SingularAttribute<ItensPedido, Integer> quantidade;

}

