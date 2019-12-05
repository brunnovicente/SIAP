/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import persistencia.FachadaPersistencia;
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
public class FachadaControle {
    
    private static FachadaControle fpstSingleton;
    private FachadaPersistencia banco;
    private GerenciadorUsuarios gusers;
    private GerenciadorProduto gprod;
    private GerenciadorVendas gvend;
    private GerenciadorRelatorios grelat;
    private GerenciadorPedidos gped;
    private GerenciadorClientes gclient;
    
    private FachadaControle(){
        this.gusers = new GerenciadorUsuarios();
        this.gprod = new GerenciadorProduto();
        this.gvend = new GerenciadorVendas();
        this.grelat = new GerenciadorRelatorios();
        this.gped = new GerenciadorPedidos();
        this.gclient = new GerenciadorClientes();
        this.banco = new FachadaPersistencia();
    }
    
    public static FachadaControle getFachadaControle(){
        if(fpstSingleton == null) {
            fpstSingleton = new FachadaControle();
        }
        return fpstSingleton;    
    }
    
    /**
     * método para fazer login no sistema
     * @param login login do usuário
     * @param senha senha do usuário
     * @return retorna o Usuário caso o login tenha sucesso, retorna null caso o login falhe
     */
    public Usuario fazerLogin(String login, String senha) throws SQLException, Exception{
        return gusers.fazerLogin(login, senha);
    }
    
    public Usuario cadastrarUsuario(Usuario usuario) throws Exception{
        return gusers.cadastrarUsuario(usuario);
    }
    
    public Cliente cadastrarCliente(Cliente cliente) throws SQLException{
        return gclient.salvarCliente(cliente);
    }
    
    public Usuario excluirUsuario(String login) throws Exception{
        return gusers.excluirUsuario(login);
    }
    /**
     * busca os produtos por uma chave textual
     * @param chave chave para busca dos produtos
     * @return retorna uma lista de produtos encontrados, caso a chave seja "" retorna uma lista com todos os produtos, os produtos da lista possuem apenas os campos: nome e código.
     */
    public List buscaProduto(String chave) throws SQLException{
        return gprod.buscaProduto(chave);
    }
    
    public List<Produto> buscaProdutoMinimos() throws SQLException{
        return gprod.buscaProdutoMinimos();
    }
    
    public List<Produto> buscaProdutoZero() throws SQLException{
        return gprod.buscaProdutoZero();
    }
    
    public List<Produto> buscaProdutoTerminal() throws SQLException{
        return gprod.buscaProdutoTerminal();
    }
    
    public Produto buscaProdutoBarra(String codigo)throws SQLException{
        return gprod.buscaProdutoBarra(codigo);
    }
    
    public List<Produto> buscaProduto(Grupo grupo) throws SQLException{
        return gprod.buscaProduto(grupo);
    }
    
    public List<Produto> buscaProduto(Produto_fornecedor profor) throws SQLException{
        return gprod.buscaProduto(profor);
    }
    /**
     * retorna um Produto com todos os campos preenchidos
     * @param codigo código do produto a ser obtido
     * @return o Produto com todos os campos
     */
    public Produto getProduto(int codigo) throws SQLException{
        return gprod.getProduto(codigo);
    }
    
    public Produto getProduto(String codigo) throws SQLException{
        return gprod.getProduto(codigo);
    }
    
    /**
     * Salva um novo produto no banco de dados
     * 
     * @param novoProduto Produto a ser salvo, o código deste objeto poderá ser qualquer valor inteiro mas será desprezado e recalculado para evitar conflitos
     * @return o Produto salvo com seu código verdadeiro
     */
    public Produto salvaProduto(Produto novoProduto) throws Exception{
        return gprod.salvaProduto(novoProduto);
    }    
    
    /**
     * Exclui um produto do banco de dados
     * @param codigo código do produto a ser excluido
     * @return Produto excluído
     */
    public Produto excluirProduto(Produto produto) throws Exception{
        return gprod.excluirProduto(produto);
    }
    
    public List<Fabricante> buscaFabricante(String chave) throws SQLException{
        return gprod.buscaFabricante(chave);
    }
    
    public List<Fornecedor> buscaFornecedores(String chave) throws SQLException{
        return gprod.buscaFornecedores(chave);
    }
    
    public List<Produto_fornecedor> buscaFornecedores(int produto) throws SQLException{
        return gprod.buscaFornecedores(produto);
    }
    
    public Fornecedor salvarFornecedor(String nome) throws SQLException{
        return gprod.salvarFornecedor(nome);
    }
    
    public List<Grupo> buscaGrupo(String chave) throws SQLException{
        return gprod.buscaGrupo(chave);
    }
    
    public Fabricante salvarFabricante(String nome) throws SQLException{
        return gprod.salvarFabricante(nome);
    }
    
    public Venda efetuarVenda(Venda venda) throws SQLException, Exception{
        return gvend.efetuarVenda(venda);
    }
    
    public Venda getVenda(int codigo){
        return gvend.getVenda(codigo);
    }
    
    /**
     * Salvar um orçamento
     * um orçamento é uma venda ainda não concluída
     * @return 
     */
    public Venda salvarOrcamento(Venda venda) throws SQLException, Exception{
         return gvend.salvarOrcamento(venda);
    }
    
    public Venda salvarCarroPatio(Venda venda) throws Exception{
        return gvend.salvarCarroPatio(venda);
    }
    
//    public boolean backupDB(String path) {
//        return grelat.backupDB(path);
//    }
//    
//    public boolean restoreDB(String source) {
//        return grelat.restoreDB(source);
//    }
    
    
    
    public Pedido salvarPedido(Pedido pedido) throws Exception{
        return this.gped.salvarPedito(pedido);
    }
    
  
    
    
    public void abrirCaixa(double valorInicial) throws SQLException, Exception{
        this.gvend.abrirCaixa(valorInicial);
    }
    
    public Caixa encerrarCaixa() throws SQLException, Exception{
        return this.gvend.fecharCaixa();
    }
    
    public boolean isCaixaAberto(){
        boolean v = false;
        try {
            v = this.gvend.isCaixaAberto();
        } catch (SQLException ex) {
            Logger.getLogger(FachadaControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }
    
    public Caixa getCaixa(){
        return this.gvend.getCaixa();
    }
    public Grupo salvarGrupo(String nome) throws SQLException{
        return this.gprod.salvarGrupo(nome);
    }
    
    public void editarCaixa(Caixa caixa) throws SQLException, Exception{
        this.gvend.editarCaixa(caixa);
    }
    
  
    public List<Usuario> consultaUsuarios() throws SQLException{
        return this.gusers.consultaUusarios();
    }
    
    public List<Fabricante> consultaFabricantes(String chave) throws SQLException{
        return this.gprod.buscaFabricante(chave);
    }
    
    public Fabricante excluirFabricante(int codigo) throws SQLException, Exception{
        return this.gprod.excluirFabricante(codigo);
    }
    
    public Fabricante editarFabricante(Fabricante fabricante)throws SQLException,Exception{
        return this.gprod.editarFabricante(fabricante);
    }
    
    public Fornecedor excluirFornecedor(int codigo)throws SQLException,Exception{
         return this.gprod.excluirFornecedor(codigo);
    }
    
    public Cliente excluirCliente(int codigo)throws SQLException,Exception{
         return this.gclient.excluirCliente(codigo);
    }
    
    public Grupo excluirGrupo(int codigo)throws SQLException,Exception{
         return this.gprod.excluirGrupo(codigo);
    }
    
    public Fornecedor editarFornecedor(Fornecedor fornecedor)throws SQLException,Exception{
        return this.gprod.editarFornecedor(fornecedor);
    }
    
    public Grupo editarGrupo(Grupo grupo)throws SQLException,Exception{
        return this.gprod.editarGrupo(grupo);
    }
        
    public void gerarRelatorioOrcamento(Venda venda) throws Exception{
        this.grelat.gerarRelatorioOrcamento(venda);
    }
    
    public void gerarRelatorioMovimentos(List<Movimento> lista) throws Exception{
        this.grelat.gerarRelatorioMovimento(lista);
    }
    
    public void gerarReltorioClientes(ArrayList<Cliente> lista, String tipo) throws Exception{
        this.grelat.gerarRelatorioClientes(lista, tipo);
    }
    
    public Usuario editarUsuario(Usuario usuario, String senhaAntiga) throws SQLException, Exception{
        return gusers.editarUsuario(usuario,senhaAntiga);
    }
    
    public Usuario getUsuarioLogado(){
        return GerenciadorUsuarios.getUsuarioLogado();
    }
    
    public Usuario getUsuario(String login) throws Exception{
        return gusers.getUsuario(login);
    }
    
    public void recuperarSenha(String login) throws SQLException, MessagingException, Exception{
        this.gusers.recuperarSenha(login);
    }
    
    public List<Venda> consultaVendas(String inicio, String fim) throws SQLException, Exception{
        return this.gvend.consultaVendas(inicio, fim);
    }
    
    public List<Venda> consultaVendas(String forma) throws SQLException, Exception{
        return this.gvend.consultaVendas(forma);
    }
    
    public List<Venda> consultaVendas() throws Exception{
        return this.gvend.consultaVendas();
    }
    
    public void gerarRelatorioVendas(ArrayList<Venda> vendas, String inicio, String fim) throws Exception{
        this.grelat.gerarRelatorioVendas(vendas, inicio, fim);
    }
    
    public void gerarRelatorioProdutos(ArrayList<Produto> lista) throws Exception{//,String caminho){
        this.grelat.gerarRelatorioProdutos(lista);
    }
    
    public void gerarRelatorioCaixa(List<Caixa> lista) throws Exception{
        this.grelat.gerarRelatorioCaixa(lista);
    }
    
    public void gerarRelatorioPedidos(ArrayList<Pedido> lista) throws Exception{
        this.grelat.gerarRelatorioPedidos(lista);
    }
    
    public void criarUsuarioPadrao() throws SQLException, Exception{
        this.gusers.criarUsuarioPadrao();
    }
    
    public List<Pedido> consultaPedidos() throws SQLException, Exception{
        return this.gped.consultaPedidos();
    }
    
    public List<Pedido> consultaPedidosPorStatus(String status) throws SQLException, Exception{
        return this.gped.consultaPedidosPorStatus(status);
    }
    
    public List<Pedido> consultaPedidosAtrasados(String data) throws SQLException, Exception{
        return this.gped.consultaPedidosAtrasados(data);
    }
    
    public Pedido modificarStatus(Pedido pedido) throws SQLException, Exception{
        return this.gped.modificarStatus(pedido);
    }
    
    public Pedido getPedido(int codigo) throws SQLException{
        return this.gped.getPedido(codigo);
    }
    
    public Produto editarProduto(Produto produto) throws SQLException{
        return this.gprod.editarProduto(produto);
    }
    
    public Fornecedor getFornecedor(int codigo) throws SQLException{
        return this.gprod.getFornecedor(codigo);
    }
    
    public Cliente salvarCliente(Cliente cliente) throws SQLException{
        return this.gclient.salvarCliente(cliente);
    }
    
    public List<Cliente> consultaClientes(String chave) throws Exception{
        return this.gclient.consultaClientes(chave);
    }
    
    public List<Cliente> consultaClientes(Veiculo veiculo) throws Exception{
        return this.gclient.consultaClientes(veiculo);
    }
    
    public Veiculo getVeiculo(String placa) throws SQLException{
        return this.gclient.getVeiculo(placa);
    }
    
    public Cliente getCliente(int codigo) throws SQLException{
        return this.gclient.getCliente(codigo);
    }
    
    public List<Cliente> consultaClientePendentes() throws Exception{
        return this.gclient.consultaClientesPendentes();
    }
    
    
    
    public Cliente editarCliente(Cliente cliente){
        return this.gclient.editarCliente(cliente);
    }
    
    public Movimento cadastrarMovimento(Movimento movimento) throws Exception{
        return this.gprod.cadastrarMovimento(movimento);
    }
    
    public List<Movimento> getMovimentos(){
        return this.gprod.getMovimentos();
    }
    
    public List<Movimento> consultaMovimentos(String inicio, String fim){
        return this.gprod.consultaMovimentos(inicio, fim);
    }
    
    public List<Movimento> consultaMovimentos(String inicio, String fim, String tipo){
        return this.gprod.consultaMovimentos(inicio, fim, tipo);
    }
    
    public List<Caixa> buscarCaixas(){
        return this.gvend.buscarCaixas();
    }
    
    public List<Caixa> buscarCaixas(String inicio, String fim){
        return this.gvend.buscarCaixas(inicio, fim);
    }
    
    public void removerVeiculo(Veiculo v){
        this.gclient.removerVeiculo(v);
    }
    
    public void salvarVeiculo(Veiculo v){
        this.gclient.salvarVeiculo(v);
    }

    public void salvarConserto(Conserto conserto) {
        //FachadaPersistencia.getFachadaPersistencia().salvarConserto(conserto);
        banco.salvarConserto(conserto);
    }
    
    public Empresa salvarEmpresa(Empresa empresa){
        return this.gusers.salvarEmpresa(empresa);
    }
    
    public Empresa editarEmpresa(Empresa empresa){
        return this.gusers.editarEmpresa(empresa);
    }
    
    public Empresa getEmpresa(){
        return this.gusers.getEmpresa();
    }
    
    public void Fechar(){
        //FachadaPersistencia.getFachadaPersistencia().fecharInstancia();
        this.banco.fecharInstancia();
    }
    
    public void Abrir(){
        this.banco.abrirInstancia();
    }
        
    
}//Fim da classe