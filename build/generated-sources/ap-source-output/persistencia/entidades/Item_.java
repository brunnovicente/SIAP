package persistencia.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Item.class)
public abstract class Item_ {

	public static volatile SingularAttribute<Item, Double> preco;
	public static volatile SingularAttribute<Item, Integer> codigo;
	public static volatile SingularAttribute<Item, Double> total;
	public static volatile SingularAttribute<Item, Venda> venda;
	public static volatile SingularAttribute<Item, Produto> produto;
	public static volatile SingularAttribute<Item, Double> desconto;
	public static volatile SingularAttribute<Item, Integer> quantidade;
	public static volatile SingularAttribute<Item, String> status;

}

