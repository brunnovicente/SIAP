package persistencia.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile SingularAttribute<Pedido, Double> preco;
	public static volatile SingularAttribute<Pedido, Integer> codigo;
	public static volatile SingularAttribute<Pedido, Double> total;
	public static volatile ListAttribute<Pedido, ItensPedido> itens;
	public static volatile SingularAttribute<Pedido, Date> dataPedido;
	public static volatile SingularAttribute<Pedido, Date> dataPrevista;
	public static volatile SingularAttribute<Pedido, Fornecedor> fornecedor;
	public static volatile SingularAttribute<Pedido, Integer> quantidade;
	public static volatile SingularAttribute<Pedido, String> status;

}

