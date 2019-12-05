package persistencia.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Venda.class)
public abstract class Venda_ {

	public static volatile SingularAttribute<Venda, String> formaPagamento;
	public static volatile SingularAttribute<Venda, Cliente> cliente;
	public static volatile SingularAttribute<Venda, Integer> codigo;
	public static volatile ListAttribute<Venda, Item> itens;
	public static volatile SingularAttribute<Venda, Double> total;
	public static volatile SingularAttribute<Venda, Usuario> vendedor;
	public static volatile SingularAttribute<Venda, Date> data;
	public static volatile SingularAttribute<Venda, Caixa> caixa;
	public static volatile SingularAttribute<Venda, Double> desconto;
	public static volatile SingularAttribute<Venda, String> status;

}

