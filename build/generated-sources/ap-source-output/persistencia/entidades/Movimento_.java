package persistencia.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Movimento.class)
public abstract class Movimento_ {

	public static volatile SingularAttribute<Movimento, Integer> codigo;
	public static volatile SingularAttribute<Movimento, String> tipo;
	public static volatile SingularAttribute<Movimento, Produto> produto;
	public static volatile SingularAttribute<Movimento, Date> data;
	public static volatile SingularAttribute<Movimento, Usuario> criadopor;
	public static volatile SingularAttribute<Movimento, Integer> quantidade;

}

