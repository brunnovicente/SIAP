package persistencia.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Caixa.class)
public abstract class Caixa_ {

	public static volatile SingularAttribute<Caixa, Double> totalDinheiro;
	public static volatile SingularAttribute<Caixa, Integer> codigo;
	public static volatile SingularAttribute<Caixa, Double> caixaInicial;
	public static volatile SingularAttribute<Caixa, Date> datafinal;
	public static volatile SingularAttribute<Caixa, Date> datainicial;
	public static volatile SingularAttribute<Caixa, Double> totalCartao;
	public static volatile SingularAttribute<Caixa, Double> caixaFinal;
	public static volatile SingularAttribute<Caixa, String> status;

}

