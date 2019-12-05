package persistencia.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Produto.class)
public abstract class Produto_ {

	public static volatile SingularAttribute<Produto, String> codigoproduto;
	public static volatile SingularAttribute<Produto, Integer> codigo;
	public static volatile SingularAttribute<Produto, Double> precoVenda;
	public static volatile SingularAttribute<Produto, Double> precoCusto;
	public static volatile ListAttribute<Produto, Grupo> grupo;
	public static volatile SingularAttribute<Produto, String> descricao;
	public static volatile SingularAttribute<Produto, String> codigoBarra;
	public static volatile SingularAttribute<Produto, Integer> estoqueAtual;
	public static volatile SetAttribute<Produto, Produto> similares;
	public static volatile SingularAttribute<Produto, byte[]> foto;
	public static volatile ListAttribute<Produto, Produto_fornecedor> fornecedor;
	public static volatile SingularAttribute<Produto, Fabricante> fabricante;
	public static volatile SingularAttribute<Produto, Integer> estoqueMinimo;
	public static volatile SingularAttribute<Produto, String> status;

}

