package persistencia.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto_fornecedor.class)
public abstract class Produto_fornecedor_ {

	public static volatile SingularAttribute<Produto_fornecedor, String> codigoFornecedor;
	public static volatile SingularAttribute<Produto_fornecedor, Integer> codigo;
	public static volatile SingularAttribute<Produto_fornecedor, Produto> produto;
	public static volatile SingularAttribute<Produto_fornecedor, Fornecedor> fornecedor;

}

