/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import persistencia.entidades.Caixa;
import persistencia.entidades.Cliente;
import persistencia.entidades.Conserto;
import persistencia.entidades.Empresa;
import persistencia.entidades.Fabricante;
import persistencia.entidades.Fornecedor;
import persistencia.entidades.Grupo;
import persistencia.entidades.Movimento;
import persistencia.entidades.Pedido;
import persistencia.entidades.Produto;
import persistencia.entidades.Produto_fornecedor;
import persistencia.entidades.Usuario;
import persistencia.entidades.Veiculo;
import persistencia.entidades.Venda;

/**
 *
 * @author User
 */
public class FachadaPersistencia {

    private static FachadaPersistencia fpstSingleton;
    EntityManagerFactory factory;// = Persistence.createEntityManagerFactory("bancopecas");
    EntityManager em;// = factory.createEntityManager();

    public FachadaPersistencia() {
        this.abrirInstancia();
    }//Fim do Construtor

//    public static FachadaPersistencia getFachadaPersistencia() {
//        if (fpstSingleton == null) {
//            
//            fpstSingleton = new FachadaPersistencia();
//        }
//        return fpstSingleton;
//    }
    
   public void abrirInstancia(){
       Properties config = new Properties();
        try {  
            
            config.load(new FileInputStream("propriedades.ini"));
            //System.out.println("IP DO COMPUTADOR: "+config.getProperty("IP"));
            
            Map propriedades = new HashMap<String,String>();
            propriedades.put("javax.persistence.jdbc.url", "jdbc:mysql://"+config.getProperty("IP")+":3306/bancopecas?zeroDateTimeBehavior=convertToNull");
            propriedades.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
            propriedades.put("javax.persistence.jdbc.user",config.getProperty("USUARIO"));
            propriedades.put("javax.persistence.jdbc.password",config.getProperty("SENHA"));
            
            factory = Persistence.createEntityManagerFactory("bancopecas",propriedades);
            em = factory.createEntityManager();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FachadaPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FachadaPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
   }
    
    /**
     * busca um usuário no banco de dados
     *
     * @param login login do usuário buscado
     * @return retorna o usuário caso seja encontrado, null caso não seja
     * encontrado
     */
    public Usuario buscarUsuario(String login) throws SQLException, Exception {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();

        List<Usuario> us = em.createQuery("select u from Usuario as u WHERE u.login = '" + login + "'").getResultList();

        if (us.isEmpty()) {
            return null; //caso não encontre usuário
        }
        
        em.clear();
        em.close();

        return us.get(0);
    }

    public Usuario cadastrarUsuario(Usuario usuario) throws SQLException {
        em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

        em.clear();
        em.close();
        return usuario;
    }

    public Usuario editarUsuario(Usuario usuario) throws SQLException {
        this.fecharInstancia();
        this.abrirInstancia();
        em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();

        em.clear();
        em.close();
        return usuario;
    }

    public Cliente cadastrarCliente(Cliente cliente) throws SQLException {
        em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();

        em.clear();
        em.close();

        return cliente;
    }

    /**
     * busca os produtos por uma chave textual, a busca é feita em cima da
     * descrição do produto
     *
     * @param chave chave para busca dos produtos
     * @return retorna uma lista de produtos encontrados, caso a chave seja ""
     * retorna uma lista com todos os produtos, os produtos da lista possuem
     * apenas os campos: nome e código.
     * @throws java.sql.SQLException
     */
//    public List<Produto> buscaProduto(String chave) throws SQLException {
//        this.fecharInstancia();
//        this.abrirInstancia();
//        
//        em = factory.createEntityManager();
//        
//        List<Produto> res = null;
//        Query query = em.createQuery("select p from Produto as p where (((p.codigoBarra = '"+chave+"') or (p.codigoproduto LIKE '%"+chave+"%') or (p.descricao LIKE '%" + chave + "%')) and p.status = '"+Produto.ATIVO+"') order by p.descricao asc");
//        //query.setParameter("chave", chave);
//        res = query.getResultList();
//        em.clear();
//        em.close();
//        
//        return res;
//    }
    
    

    public List<Caixa> buscarCaixas() {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        List<Caixa> res = null;
        Query query = em.createQuery("select c from Caixa as c");
        //query.setParameter("chave", chave);
        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }

    public List<Caixa> buscarCaixas(String inicio, String fim) {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        List<Caixa> res = null;
        Query query = em.createQuery("select c from Caixa as c WHERE (c.datainicial >= '" + inicio + "' and c.datafinal <= '" + fim + "')");
        //query.setParameter("chave", chave);
        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }

    public List buscaProdutoMinimos() {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        List res = null;
        Query query = em.createQuery("select "
                + "p.codigo,"
                + "p.codigoproduto,"
                + "p.descricao,"
                + "p.precoVenda,"
                + "p.estoqueAtual,"
                + "p.fabricante "
                + "from Produto as p "
                + "where p.estoqueAtual <= p.estoqueMinimo and p.status = '"+Produto.ATIVO+"'");
        //query.setParameter("chave", chave);
        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }

    public List<Produto> buscaProdutoZero() {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        List<Produto> res = null;
        Query query = em.createQuery("select p from Produto as p where p.estoqueAtual = 0 and p.status = '"+Produto.ATIVO+"'");
        //query.setParameter("chave", chave);
        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }

    public List buscaProdutoTerminal() throws SQLException {
        em = factory.createEntityManager();
        List res = null;

        res = em.createQuery("SELECT "
                + "p.codigo,"
                + "p.codigoproduto,"
                + "p.descricao,"
                + "p.precoVenda,"
                + "p.estoqueAtual,"
                + "p.fabricante "
                + "FROM Produto as p "
                + "WHERE p.estoqueAtual <= p.estoqueMinimo and p.status = '"+Produto.ATIVO+"'").getResultList();
        em.clear();
        em.close();
        return res;
    }

    public Produto buscaProdutoBarra(String codigo) throws SQLException {
        em = factory.createEntityManager();
        Produto res = null;
        Query query = em.createQuery("SELECT p FROM Produto AS p WHERE p.codigoBarra = '" + codigo + "' or p.codigoproduto = '"+codigo+"' and p.status = '"+Produto.ATIVO+"'");
        //query.setParameter("chave", chave);
        res = (Produto) query.getResultList().get(0);
        em.clear();
        em.close();
        return res;
    }

    /**
     * Busca uma lista de produtos pertencentes ao grupo cujo ID é passado pro
     * parâmetro
     *
     * @param grupo
     * @return
     * @throws SQLException
     */
    public List<Produto> buscaProduto(int grupo) throws SQLException {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        List<Produto> res = null;

        res = em.createQuery("SELECT p FROM Produto as p "
                + "LEFT JOIN p.grupo as g "
                + "where g.codigo = " + grupo+" and p.status = '"+Produto.ATIVO+"'").getResultList();
        em.clear();
        em.close();
        return res;
    }
    
    public List buscaProduto(String chave) throws SQLException {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        
        List res = null;
        Query query = em.createQuery("select "
                + "p.codigo,"
                + "p.codigoproduto,"
                + "p.descricao,"
                + "p.precoVenda,"
                + "p.estoqueAtual,"
                + "p.fabricante "
                + "from Produto as p where (((p.codigoBarra = '"+chave+"') or (p.codigoproduto LIKE '%"+chave+"%') or (p.descricao LIKE '%" + chave + "%')) and p.status = '"+Produto.ATIVO+"') order by p.descricao asc");
        //query.setParameter("chave", chave);
        res = query.getResultList();
        em.clear();
        em.close();
        
        return res;
    }

    /**
     * Busca uma lista de produtos referêntes ao fornecedor passado como
     * parâmetro
     *
     * @param profor
     * @param fornecedor
     * @return
     * @throws SQLException
     */
    public List<Produto_fornecedor> buscaProduto(Produto_fornecedor profor) throws SQLException {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        List<Produto_fornecedor> res = null;

        res = em.createQuery("SELECT pf FROM Produto_fornecedor as pf"
                + " where pf.codigoFornecedor = '" + profor.getCodigoFornecedor() +"'").getResultList();
        em.clear();
        em.close();
        return res;
    }

    /**
     * retorna um Produto com todos os campos preenchidos
     *
     * @param codigo código do produto a ser obtido
     * @return o Produto com todos os campos
     */
    public Produto getProduto(int codigo) throws SQLException {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        List<Produto> res = null;

        res = em.createQuery("SELECT p FROM Produto as p WHERE p.codigo = " + codigo +" and p.status = '"+Produto.ATIVO+"'").getResultList();

        if (res.isEmpty()) {
            em.clear();
            em.close();
            return null;
        }
        em.clear();
        em.close();
        return res.get(0);
    }
    
    public Produto getProduto(String codigo) throws SQLException {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        List<Produto> res = null;

        res = em.createQuery("SELECT p FROM Produto as p WHERE p.codigoBarra = '" + codigo +"' and p.status = '"+Produto.ATIVO+"'").getResultList();

        if (res.isEmpty()) {
            em.clear();
            em.close();
            return null;
        }
        em.clear();
        em.close();
        return res.get(0);
    }
    
    public Cliente getCliente(int codigo) throws SQLException {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        List<Cliente> res = null;

        res = em.createQuery("SELECT c FROM Cliente as c WHERE c.codigo = " + codigo).getResultList();

        if (res.isEmpty()) {
            em.clear();
            em.close();
            return null;
        }
        em.clear();
        em.close();
        return res.get(0);
    }

    /**
     * Salva um novo produto no banco de dados
     *
     * @param novoProduto Produto a ser salvo, o código deste objeto poderá ser
     * qualquer valor inteiro mas será desprezado e recalculado para evitar
     * conflitos
     * @return o Produto salvo com seu código verdadeiro
     */
    public Produto salvaProduto(Produto novoProduto) throws SQLException {
        em = factory.createEntityManager();
        em.getTransaction().begin();

        if(novoProduto.getCodigo()==0){
            em.persist(novoProduto);
        }else{
            em.merge(novoProduto);
        }
        em.getTransaction().commit();
        em.clear();
        em.close();
        
        
        
        return novoProduto;
    }

   

    public Cliente excluirCliente(int codigo) throws SQLException {
        em = factory.createEntityManager();

        Cliente c = null;
        c = getCliente(codigo);
        c.setStatus(Cliente.INATIVO);

        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
        em.clear();
        em.close();
        return c;
    }

    /**
     * Busca uma lista de frabricantes cadastrados no sistema por nome
     *
     * @param chave
     * @return
     * @throws SQLException
     */
    public List<Fabricante> buscaFabricante(String chave) throws SQLException {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();

        List<Fabricante> result;
        Query query;
        if (chave != null && !chave.isEmpty()) {
            query = em.createQuery("select f from Fabricante as f where f.nome LIKE '%" + chave + "%' and f.status = 'Ativo' order by f.nome asc");
        } else { // chave vazia
            query = em.createQuery("select f FROM Fabricante as f where f.status = 'Ativo' order by f.nome asc");
        }

        result = query.getResultList();
        em.clear();
        em.close();
        return result;
    }

    /**
     * Busca uma lista de fornecedores por nome
     *
     * @param chave
     * @return
     * @throws SQLException
     */
    public List<Fornecedor> buscaFornecedor(String chave) throws SQLException {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        List<Fornecedor> res = null;
        Query query = em.createQuery("select f from Fornecedor as f where f.nome LIKE '%" + chave + "%' and f.status = 'Ativo' order by f.nome asc");
        //query.setParameter("chave", chave);
        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }

    /**
     * Busca uma lista de fornecedores de um determinado produto
     *
     * @param produto
     * @return
     * @throws SQLException
     */
    public List<Produto_fornecedor> buscaFornecedor(int produto) throws SQLException {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        Produto p = getProduto(produto);
        em.clear();
        em.close();
        return p.getFornecedor();
    }

    /**
     * Busca grupos por nome de acordo com a chave passada
     *
     * @param chave
     * @return
     * @throws SQLException
     */
    public List<Grupo> buscaGrupo(String chave) throws SQLException {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        List<Grupo> res;
        Query query = em.createQuery("select g from Grupo as g where g.nome LIKE '%" + chave + "%' and g.status = 'Ativo' order by g.nome asc");

        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }

    /**
     * Cadastra um novo fabricante no banco de dados
     *
     * @param nome
     * @return
     * @throws SQLException
     */
    public Fabricante salvarFabricante(String nome) throws SQLException {
        em = factory.createEntityManager();
        //return this.banco.cadastrarFabricante(nome);
        Fabricante novo = new Fabricante();
        novo.setNome(nome);
        novo.setStatus("Ativo");
        em.getTransaction().begin();
        em.persist(novo);
        em.getTransaction().commit();
        em.clear();
        em.close();
        return novo;
    }

    /**
     * Eclui um fabricante do banco de dados
     *
     * @param codigo
     * @return
     * @throws SQLException
     */
    public Fabricante excluirFabricante(int codigo) throws SQLException {
        em = factory.createEntityManager();
        //return this.banco.excluirFabricante(codigo);
        
        Fabricante fab;
        List<Fabricante> lF = em.createQuery("SELECT f FROM Fabricante as f WHERE f.codigo = " + codigo).getResultList();
        if (lF.isEmpty()) {
            fab = null;
        } else {
            fab = lF.get(0);
        }
        
        fab.setStatus("Inativo");
        this.editarFabricante(fab);
        
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNativeQuery("UPDATE Produtos SET fabricante = null WHERE fabricante = "+codigo);
        query.executeUpdate();
        em.getTransaction().commit();
        
        return fab;
    }

    /**
     * Cria um novo fornecedor no banco de dados
     *
     * @param nome
     * @return
     * @throws SQLException
     */
    public Fornecedor salvarFornecedor(String nome) throws SQLException {
        em = factory.createEntityManager();
        Fornecedor novo = new Fornecedor();
        novo.setNome(nome);
        novo.setStatus("Ativo");
        em.getTransaction().begin();
        em.persist(novo);
        em.getTransaction().commit();
        em.clear();
        em.close();
        return novo;
    }

    public Venda salvarVenda(Venda venda) throws SQLException {
        em = factory.createEntityManager();
        em.getTransaction().begin();
        if(venda.getCodigo()==0){        
            em.persist(venda);        
        }else{
            em.merge(venda);        
        }
        em.getTransaction().commit();
        em.clear();
        em.close();
        return venda;
    }

//    public boolean backupDB(String path) {
//        return this.banco.backupDB(path);
//    }

//    public boolean restoreDB(String source) {
//        return this.banco.restoreDB(source);
//    }

    /**
     * Este método irá salvar no banco de dados uma nova instância de caixa com
     * status aberto
     *
     * @param caixa
     * @return
     * @throws SQLException
     */
    public Caixa abrirCaixa(Caixa caixa) throws SQLException {
        em = factory.createEntityManager();
        //return this.banco.salvarCaixa(caixa);
        em.getTransaction().begin();
        em.persist(caixa);
        em.getTransaction().commit();
        em.clear();
        em.close();
        return caixa;
    }

    /**
     * Feixa o caixa aberto atualmente, isso edita o status do caixa no Backup
     * de Dados
     *
     * @param caixa
     * @return
     * @throws SQLException
     */
    public Caixa fecharCaixa(Caixa caixa) throws SQLException {
        em = factory.createEntityManager();
        //return this.banco.editarCaixa(caixa);
        Caixa caixaOld = em.find(Caixa.class, caixa.getCodigo());

        em.getTransaction().begin();
        em.merge(caixa);
        em.getTransaction().commit();
        em.clear();
        em.close();
        return caixa;
    }

    public Caixa getCaixaAberto() throws SQLException {
        em = factory.createEntityManager();
        //return this.banco.getCaixaAberto();
        Caixa res = null;
        List<Caixa> caixas = null;
        caixas = em.createQuery("select c from Caixa as c where c.status = 'aberto'").getResultList();
        if (caixas.isEmpty()) {
            em.clear();
            em.close();
            return null;
        }

        res = caixas.get(0);
        em.clear();
        em.close();
        return res;
    }

    /**
     * Salva um novo grupo na base de dados
     *
     * @param nome
     * @return
     * @throws SQLException
     */
    public Grupo salvarGrupo(String nome) throws SQLException {
        em = factory.createEntityManager();
        
        Grupo novo = new Grupo(nome);
        novo.setStatus("Ativo");
        
        em.getTransaction().begin();
        em.persist(novo);
        em.getTransaction().commit();
        em.clear();
        em.close();
        return novo;
    }

    /**
     *
     * @param caixa
     * @return
     * @throws SQLException
     */
    public Caixa editarCaixa(Caixa caixa) throws SQLException {
        em = factory.createEntityManager();
        Caixa caixaOld = em.find(Caixa.class, caixa.getCodigo());

        em.getTransaction().begin();
        em.merge(caixa);
        em.getTransaction().commit();
        em.clear();
        em.close();
        return caixa;
    }

    public Empresa salvarEmpresa(Empresa empresa){
        em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.persist(empresa);
        em.getTransaction().commit();
        em.clear();
        em.close();       
        
        return empresa;
    }
    
    public Empresa editarEmpresa(Empresa empresa){
        em = factory.createEntityManager();
        
        em.getTransaction().begin();
        em.merge(empresa);
        em.getTransaction().commit();
        em.clear();
        em.close();       
        
        return empresa;
    }
    
    public Empresa getEmpresa(){
        em = factory.createEntityManager();
        
        List<Empresa> empresa = null;
        empresa = em.createQuery("select e from Empresa as e").getResultList();
        
        if(empresa.isEmpty()){
            em.clear();
            em.close();
            return null;
        }
        
        em.clear();
        em.close();       
        return empresa.get(0);
    }
    
    /**
     * Retorna todos os usuários cadastrados no sistema
     *
     * @return
     * @throws SQLException
     */
    public List<Usuario> consultaUsuario() throws SQLException {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        //return this.banco.consultaUsuarios();
        List<Usuario> usuarios = null;
        usuarios = em.createQuery("select u from Usuario as u").getResultList();
        em.clear();
        em.close();
        return usuarios;
    }

    /**
     * Exclui um usuário do banco de dados
     *
     * @param login
     * @throws SQLException
     */
    public void excluirUSuario(String login) throws SQLException {
        em = factory.createEntityManager();
        Usuario usuarioOld = em.find(Usuario.class, login);

        em.getTransaction().begin();
        em.remove(usuarioOld);
        em.getTransaction().commit();
        em.clear();
        em.close();
    }

    /**
     * Método utilizado para fazer edição dos dados de um fabricante no banco de
     * dados
     *
     * @param fabricante
     * @return
     * @throws SQLException
     */
    public Fabricante editarFabricante(Fabricante fabricante) throws SQLException {
        em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(fabricante);
        em.getTransaction().commit();
        em.clear();
        em.close();
        return fabricante;
    }

    /**
     * Método utilizado para fazer edição dos dados de um fornecedor no banco de
     * dados
     *
     * @param fornecedor
     * @return
     * @throws SQLException
     */
    public Fornecedor editarFornecedor(Fornecedor fornecedor) throws SQLException {
        em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(fornecedor);
        em.getTransaction().commit();
        em.clear();
        em.close();
        return fornecedor;
    }

    /**
     * Método utilizado para fazer edição dos dados de um grupo no banco de
     * dados
     *
     * @param fornecedor
     * @return
     * @throws SQLException
     */
    public Grupo editarGrupo(Grupo grupo) throws SQLException {
        em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(grupo);
        em.getTransaction().commit();
        em.clear();
        em.close();
        return grupo;
    }

    /**
     * Este método exclui um fornecedor da base de dados
     *
     * @param codigo
     * @return
     * @throws SQLException
     */
    public Fornecedor excluirFornecedor(int codigo) throws SQLException {
        
        Fornecedor f = getFornecedor(codigo);
        
        //em = factory.createEntityManager();
        //em.createQuery("DELETE FROM produtos_fornecedores WHERE fornecedor = "+codigo);
        //em.clear();
        //em.close();
        
        em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(f);
        em.getTransaction().commit();
        em.clear();
        em.close();
        
        //this.editarFornecedor(f);
        return f;
    }

    /**
     * Este método exclui um grupo da base de dados
     *
     * @param codigo
     * @return
     * @throws SQLException
     */
    public Grupo excluirGrupo(int codigo) throws SQLException {
        
        Grupo g = getGrupo(codigo);
        g.setStatus("Inativo");
        this.editarGrupo(g);
        
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNativeQuery("DELETE FROM produtos_grupos WHERE grupo = "+codigo);
        query.executeUpdate();
        em.getTransaction().commit();
        
        return g;
    }

    /**
     * Busca um usuário cadastrado na base de dados de acordo com um email
     *
     * @param email
     * @return
     * @throws SQLException
     */
    public Usuario getUsuario(String email) throws SQLException {
        em = factory.createEntityManager();
        List<Usuario> res = null;

        res = em.createQuery("SELECT u FROM Usuario as u WHERE u.email = " + email).getResultList();

        if (res.isEmpty()) {
            em.clear();
            em.close();
            return null;
        }
        em.clear();
        em.close();
        return res.get(0);
    }

    /**
     * Consulta as vendas realizadas entre as datas de inicio e fim passadas
     * como parametro
     *
     * @param inicio
     * @param fim
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public List<Venda> consultaVendas(String inicio, String fim) throws SQLException, Exception {
        em = factory.createEntityManager();

        List<Venda> res = null;
        String sql = "SELECT v FROM Venda as v WHERE v.data >= '" + inicio + "' AND v.data <= '" + fim + "'";
        Query query = em.createQuery(sql);

        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }
    
    
    
    public List<Venda> consultaVendas()throws SQLException, Exception{
        em = factory.createEntityManager();
        List<Venda> res = null;
        String sql = "SELECT v FROM Venda as v WHERE v.status = 'Orçamento'";
        Query query = em.createQuery(sql);
        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }
    
    /**
     * consulta as vendas realizadas filtrando pela forma de pagamento
     *
     * @param forma
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public List<Venda> consultaVendas(String forma) throws SQLException, Exception {
        em = factory.createEntityManager();
        List<Venda> res = null;

        Query query = em.createQuery("SELECT v FROM Venda as v WHERE v.formaPagamento = " + forma);

        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }

    public List<Cliente> consultaClientes(String chave) throws SQLException, Exception {
        em = factory.createEntityManager();
        List<Cliente> res = null;

        Query query = em.createQuery("SELECT c FROM Cliente as c WHERE (c.nome LIKE '%" + chave + "%' or c.apelido LIKE '%" + chave + "%')and (c.status = 'Ativo')");

        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }

    public List<Cliente> consultaClientesPendentes() {
        em = factory.createEntityManager();
        List<Cliente> res = null;

        Query query = em.createQuery("SELECT c FROM Cliente as c WHERE c.temPendencia = 1 and (c.status = 'Ativo')");

        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }

    public void excluirVeiculoCliente(int codigo) {
        em = factory.createEntityManager();
        try {
            Cliente c = this.getCliente(codigo);
            for (Veiculo veiculo : c.getVeiculos()) {
                em.getTransaction().begin();
                em.remove(veiculo);
                em.getTransaction().commit();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FachadaPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.clear();
        em.close();
    }

    public Cliente editarCliente(Cliente cliente) {
        em = factory.createEntityManager();

        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
        em.clear();
        em.close();

        return cliente;
    }

    /**
     * Este método cria um usuário padrão no sistema login: admin senha: admin
     *
     * @throws SQLException
     */
    public void criarUsuarioPadrao(String senha) throws SQLException {

        em = factory.createEntityManager();

        Usuario bc = new Usuario();
        bc.setLogin("admin");
        bc.setNome("administrador");
        bc.setEmail("bcsolution@gmail.com");
        bc.setSenha(senha);
        bc.setTipo(Usuario.TIPO_GERENTE);

        em.getTransaction().begin();
        em.persist(bc);
        em.getTransaction().commit();
        em.clear();
        em.close();
    }
    
    public void criarLogoPadrao(){
        File arquivo = new File("logo.png");
        byte[] imagem = new byte[(int) arquivo.length()];
        
        try {
            FileInputStream fileInputStream = new FileInputStream(arquivo);
            fileInputStream.read(imagem);
            fileInputStream.close();
            System.out.println(imagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Empresa empresa = new Empresa();
        empresa.setLogo(imagem);
        empresa.setNome("Minha Empresa");
        empresa.setNomelogo("logo.jpg");
        
        
        
        em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(empresa);
        em.getTransaction().commit();
        em.clear();
        em.close();
        
    }

    public void criarClientePadrao() {
        em = factory.createEntityManager();
        Cliente cliente = new Cliente();
        cliente.setNome("PADRÃO");
        cliente.setApelido("PADRÃO");
        cliente.setStatus("Ativo");
        cliente.setNumero(1111);
        cliente.setTemPendencia(false);

        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.clear();
        em.close();
    }

    /**
     * Este método salva um novo pedido no sistema
     *
     * @param pedido
     * @return
     * @throws SQLException
     */
    public Pedido cadastrarPedido(Pedido pedido) throws SQLException {
        em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(pedido);
        em.getTransaction().commit();
        em.clear();
        em.close();
        return pedido;
    }

    public Movimento cadastrarMovimento(Movimento movimento) {
        em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(movimento);
        em.getTransaction().commit();
        em.clear();
        em.close();
        return movimento;
    }

    /**
     * busca um pedido cadastrado na base de dados através do seu código
     *
     * @param codigo
     * @return
     * @throws SQLException
     */
    public Pedido getPedido(int codigo) throws SQLException {
        em = factory.createEntityManager();
        List<Pedido> res = null;

        res = em.createQuery("SELECT p FROM Pedido as p WHERE p.codigo = " + codigo).getResultList();

        if (res.isEmpty()) {
            em.clear();
            em.close();
            return null;
        }
        em.clear();
        em.close();
        return res.get(0);
    }

    public Venda getVenda(int codigo){
        em = factory.createEntityManager();
        List<Venda> res = null;
        
        res = em.createQuery("SELECT v FROM Venda as v WHERE v.codigo = "+codigo).getResultList();
        
        em.clear();
        em.close();
        return res.get(0);
    }//Fim da Venda
    
    /**
     * busca um fornecedor cadastrado na base de dados através do seu código
     *
     * @param codigo
     * @return
     * @throws SQLException
     */
    public Fornecedor getFornecedor(int codigo) throws SQLException {
        em = factory.createEntityManager();
        List<Fornecedor> res = null;

        res = em.createQuery("SELECT f FROM Fornecedor as f WHERE f.codigo = " + codigo +" and f.status = 'Ativo'").getResultList();

        if (res.isEmpty()) {
            em.clear();
            em.close();
            return null;
        }
        em.clear();
        em.close();
        return res.get(0);
    }

    /**
     * recupera um grupo salvo no banco de dados de acordo com o código passado
     *
     * @param codigo
     * @return
     * @throws SQLException
     */
    private Grupo getGrupo(int codigo) throws SQLException {
        em = factory.createEntityManager();
        List<Grupo> res = null;

        res = em.createQuery("SELECT g FROM Grupo as g WHERE g.codigo = " + codigo +" and g.status = 'Ativo'").getResultList();

        if (res.isEmpty()) {
            em.clear();
            em.close();
            return null;
        }
        em.clear();
        em.close();
        return res.get(0);
    }

    /**
     * Retorna os pedidos cadastrados no banco de dados
     *
     * @return
     * @throws SQLException
     */
    public List<Pedido> consultaPedidos() throws SQLException {
        em = factory.createEntityManager();
        List<Pedido> res = null;

        Query query = em.createQuery("SELECT p FROM Pedido as p");

        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }

    /**
     * Retorna os pedidos cadastrados no banco de dados filtrando por status
     *
     * @param status
     * @return
     * @throws SQLException
     */
    public List<Pedido> consultaPedidosPorStatus(String status) throws SQLException {
        em = factory.createEntityManager();
        List<Pedido> res = null;

        Query query = em.createQuery("SELECT p FROM Pedido as p WHERE p.status = '" + status+"'");

        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }

    /**
     * Edita um pedido salvo no banco de dados
     *
     * @param pedido
     * @return
     * @throws SQLException
     */
    public Pedido editarPedido(Pedido pedido) throws SQLException {
        em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(pedido);
        em.getTransaction().commit();
        em.clear();
        em.close();
        return pedido;
    }

    /**
     * Retorna todos os pedidos cadastrados no banco de dados cuja data prevista
     * é anterior à data atual e o status do pedido ainda é pendente
     *
     * @param dataAtual
     * @return
     * @throws SQLException
     */
    public List<Pedido> consultaPedidosAtrasados(String dataAtual) throws SQLException {
        em = factory.createEntityManager();
        List<Pedido> res = null;

        Query query = em.createQuery("SELECT p FROM Pedido as p WHERE p.dataPrevista < " + dataAtual + " AND p.status = '" + Pedido.PENDENTE+"'");

        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }

    /**
     * Edita um produto cadastrado no banco de dados
     *
     * @param produto
     * @return
     * @throws SQLException
     */
    public Produto editarProduto(Produto produto) throws SQLException {
        em = factory.createEntityManager();
        em.getTransaction().begin();
        em.merge(produto);
        em.getTransaction().commit();
        em.clear();
        em.close();
        return produto;
    }

    /**
     * retorna as Vendas cujo status é de ORÇAMENTO
     *
     * @return
     */
    public List<Venda> getOrcamentos() {
        em = factory.createEntityManager();
        List<Venda> res;
        Query query = em.createQuery("SELECT v FROM Venda as v WHERE v.status = \'" + Venda.ORCAMENTO+ "\'");

        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }

    public List<Movimento> getMovimentos() {
        em = factory.createEntityManager();
        List<Movimento> res;

        Query query = em.createQuery("SELECT m FROM Movimento AS m");

        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }
    
    public List<Movimento> consultaMovimentos(String inicio, String fim){
        em = factory.createEntityManager();
        List<Movimento> res;

        Query query = em.createQuery("SELECT m FROM Movimento AS m WHERE m.data >= '"+inicio+"' AND m.data <= '"+fim+"'");

        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }
    
    public List<Movimento> consultaMovimentos(String inicio, String fim, String tipo){
        em = factory.createEntityManager();
        List<Movimento> res;

        Query query = em.createQuery("SELECT m FROM Movimento AS m WHERE m.data >= '"+inicio+"' AND m.data <= '"+fim+"' AND m.tipo = '"+tipo+"'");

        res = query.getResultList();
        em.clear();
        em.close();
        return res;
    }
    
    public void removerVeiculo(Veiculo v) {
        em = factory.createEntityManager();
        em.getTransaction().begin();
        em.remove(v);
        em.getTransaction().commit();
        em.clear();
        em.close();
    }

    public void salvarVeiculo(Veiculo v) {
        em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(v);
        em.getTransaction().commit();
        em.clear();
        em.close();
    }
    
     public Veiculo getVeiculo(String placa) throws SQLException {
        em = factory.createEntityManager();
        List<Veiculo> res = null;

        res = em.createQuery("SELECT v FROM Veiculo as v WHERE v.placa = '" + placa +"'").getResultList();

        if (res.isEmpty()) {
            em.clear();
            em.close();
            return null;
        }
        em.clear();
        em.close();
        return res.get(0);
    }

    public void salvarConserto(Conserto conserto) {
        em = factory.createEntityManager();
        if (conserto.getCodigo() == 0) { // novo conserto
            em.getTransaction().begin();
            em.persist(conserto);
            em.getTransaction().commit();
        } else { //atualizar conserto
            em.getTransaction().begin();
            em.merge(conserto);
            em.getTransaction().commit();
        }
        em.clear();
        em.close();
        
        this.fecharInstancia();
        this.abrirInstancia();
    }

    public List<Conserto> getCarrosNoPatio() {
        this.fecharInstancia();
        this.abrirInstancia();
        
        em = factory.createEntityManager();
        List<Conserto> res;
        Query query = em.createQuery("SELECT c FROM Conserto AS c WHERE c.venda.status = \'" + Venda.PATIO + "\'");
        //Query query = em.createQuery("SELECT v FROM Venda AS v WHERE v.status = '"+Venda.ORCAMENTO+"'");
        
        res = query.getResultList();

        em.clear();
        em.close();
        return res;
    }
    
    public List<Produto> buscarProdutosPorGrupo(Grupo g){
        em = factory.createEntityManager();
        List<Produto> res;
        Grupo grupo = (Grupo) em.createQuery("SELECT g FROM Grupo as g WHERE g.codigo = "+g.getCodigo()).getSingleResult();
        Query query = em.createQuery("SELECT p FROM Produto as p WHERE :grupo MEMBER OF p.grupo ORDER BY p.descricao");
        query.setParameter("grupo", grupo);

        res = query.getResultList();

        em.clear();
        em.close();
        return res;
    
    }
    
    public List<Cliente> consultaClientes(Veiculo v){
        em = factory.createEntityManager();
        List<Cliente> res;
        Veiculo veiculo = (Veiculo) em.createQuery("SELECT v FROM Veiculo as v WHERE v.codigo = "+v.getCodigo()).getSingleResult();
        Query query = em.createQuery("SELECT c FROM Cliente as c WHERE :veiculo MEMBER OF c.veiculos ORDER BY c.nome");
        query.setParameter("veiculo", veiculo);

        res = query.getResultList();

        em.clear();
        em.close();
        return res;
    
    }
    
    public Venda excluirVenda(Venda venda){
        em = factory.createEntityManager();
        
        Venda aux = (Venda) em.createQuery("SELECT v FROM Venda as v WHERE v.codigo = " + venda.getCodigo()).getSingleResult();
        
        em.getTransaction().begin();
        em.remove(aux);
        em.getTransaction().commit();
        
        em.clear();
        em.close();
        
        return aux;
    }
    
     /**
     * Exclui um produto do banco de dados
     *
     * @param codigo código do produto a ser excluido
     * @return Produto excluído
     */
    public Produto excluirProduto(Produto p) throws SQLException {
        em = factory.createEntityManager();

        //Produto pro = (Produto) em.createQuery("SELECT p FROM Produto as p WHERE p.codigo = "+p.getCodigo()).getSingleResult();
        
        //pro.setDescricao("XXXXXXX");
        
        p.setStatus("Inativo");
        
        //em.createQuery("DELETE FROM similares WHERE produto = "+pro.getCodigo()+" OR similar = "+pro.getCodigo());
        
        this.editarProduto(p);
        return p;
    }
    
    public void fecharInstancia(){
        this.factory.close();
        
    }


}//Fim da Classe
