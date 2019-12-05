package persistencia.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Conserto.class)
public abstract class Conserto_ {

	public static volatile SingularAttribute<Conserto, Veiculo> veiculo;
	public static volatile SingularAttribute<Conserto, Cliente> cliente;
	public static volatile SingularAttribute<Conserto, Integer> codigo;
	public static volatile SingularAttribute<Conserto, Venda> venda;
	public static volatile SingularAttribute<Conserto, Date> data;

}

