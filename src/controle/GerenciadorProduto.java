/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.FachadaPersistencia;
import persistencia.entidades.Fabricante;
import persistencia.entidades.Fornecedor;
import persistencia.entidades.Grupo;
import persistencia.entidades.Movimento;
import persistencia.entidades.Produto;
import persistencia.entidades.Produto_fornecedor;
import persistencia.entidades.Usuario;

/**
 *
 * @author User
 */
class GerenciadorProduto {
    
    private FachadaPersistencia banco;
    
    public GerenciadorProduto(){
        this.banco = new FachadaPersistencia();
    }
    
    /**
     * busca os produtos por uma chave textual
     * @param chave chave para busca dos produtos
     * @return retorna uma lista de produtos encontrados, caso a chave seja "" retorna uma lista com todos os produtos, os produtos da lista possuem apenas os campos: nome e código.
     */
    public List buscaProduto(String chave) throws SQLException, SQLException, SQLException, SQLException, SQLException, SQLException, SQLException, SQLException{
        return this.banco.buscaProduto(chave);
    }
    
    public List<Produto> buscaProdutoMinimos() throws SQLException, SQLException, SQLException, SQLException, SQLException, SQLException, SQLException, SQLException{
        return this.banco.buscaProdutoMinimos();
    }
    
    public List<Produto> buscaProdutoZero() throws SQLException, SQLException, SQLException, SQLException, SQLException, SQLException, SQLException, SQLException{
        return this.banco.buscaProdutoZero();
    }
    
    public List<Produto> buscaProduto(Grupo grupo) throws SQLException, SQLException, SQLException, SQLException, SQLException, SQLException, SQLException, SQLException{
        return this.banco.buscarProdutosPorGrupo(grupo);
    }
    
    public List<Produto> buscaProduto(Produto_fornecedor profor) throws SQLException{
        List<Produto_fornecedor> lista = this.banco.buscaProduto(profor);
        List<Produto> produtos = new ArrayList();
        for (Produto_fornecedor pf : lista) {
            produtos.add(pf.getProduto());
        }
        
        return produtos;
    }
    
    public List<Produto> buscaProdutoTerminal() throws SQLException{
        return this.banco.buscaProdutoTerminal();
    }
    
    public Produto buscaProdutoBarra(String codigo)throws SQLException{
        return this.banco.buscaProdutoBarra(codigo);
    }
    /**
     * retorna um Produto com todos os campos preenchidos
     * @param codigo código do produto a ser obtido
     * @return o Produto com todos os campos
     */
    public Produto getProduto(int codigo) throws SQLException{
        return this.banco.getProduto(codigo);
    }
    
    public Produto getProduto(String codigo) throws SQLException{
        return this.banco.getProduto(codigo);
    }
    
    /**
     * Salva um novo produto no banco de dados
     * 
     * @param novoProduto Produto a ser salvo, o código deste objeto poderá ser qualquer valor inteiro mas será desprezado e recalculado para evitar conflitos
     * @return o Produto salvo com seu código verdadeiro
     */
    public Produto salvaProduto(Produto novoProduto) throws Exception{
         if(GerenciadorUsuarios.getUsuarioLogado() == null) {
             throw new Exception("não existe nenhum usuário logado");
        }
         if(GerenciadorUsuarios.getUsuarioLogado().getTipo().equals(Usuario.TIPO_VENDEDOR)) {
             throw new Exception("Usuário logado não tem autoridade para realizar esta operação.");
        } if(novoProduto.getEstoqueAtual() < 0 || novoProduto.getEstoqueMinimo() < 0){
            throw new Exception("Estoque não pode ser negativo.");
        } if(novoProduto.getPrecoCusto() < 0 || novoProduto.getPrecoVenda() < 0){
            throw new Exception("Preço não pode ser negativo.");
        }
        
        Produto novo = this.banco.salvaProduto(novoProduto);
        return novo;
    }
    
    /**
     * Exclui um produto do banco de dados
     * @param codigo código do produto a ser excluido
     * @return Produto excluído
     */
    public Produto excluirProduto(Produto produto) throws Exception{
        if(GerenciadorUsuarios.getUsuarioLogado() == null) {
            throw new Exception("não existe nenhum usuário logado");
        }
        if(GerenciadorUsuarios.getUsuarioLogado().getTipo().equals(Usuario.TIPO_VENDEDOR)) {
            throw new Exception("usuário logado não tem autoridade para realizar esta operação");
        }
        return this.banco.editarProduto(produto);
    }
    
    public List<Fabricante> buscaFabricante(String chave) throws SQLException{
        return this.banco.buscaFabricante(chave);
    }
    
    public List<Grupo> buscaGrupo(String chave)throws SQLException{
        return this.banco.buscaGrupo(chave);
    }
    
    public Fabricante salvarFabricante(String chave) throws SQLException{
        return this.banco.salvarFabricante(chave);
    }
    
    public Fornecedor salvarFornecedor(String nome) throws SQLException{
        return this.banco.salvarFornecedor(nome);
    }
    
    public List<Fornecedor> buscaFornecedores(String chave) throws SQLException{
        return this.banco.buscaFornecedor(chave);
    }
    
    public List<Produto_fornecedor> buscaFornecedores(int produto) throws SQLException{
        return this.banco.buscaFornecedor(produto);
    }
    
    public Fornecedor getFornecedor(int codigo) throws SQLException{
        return this.banco.getFornecedor(codigo);
    }
    
    public Grupo salvarGrupo(String nome) throws SQLException{
        return this.banco.salvarGrupo(nome);
    }
    
   
    public Fabricante excluirFabricante(int codigo) throws SQLException, Exception{
        if(GerenciadorUsuarios.getUsuarioLogado().getTipo().equals(Usuario.TIPO_VENDEDOR)) {
            throw new Exception("O usuário logado não tem autoridade para realizar esta operação");
        }
        return this.banco.excluirFabricante(codigo);
    }
    
    public Fornecedor excluirFornecedor(int codigo) throws SQLException, Exception{
        if(GerenciadorUsuarios.getUsuarioLogado().getTipo().equals(Usuario.TIPO_VENDEDOR)) {
            throw new Exception("O usuário logado não tem autoridade para realizar esta operação");
        }
        return this.banco.excluirFornecedor(codigo);
    }
    
    public Fabricante editarFabricante(Fabricante fabricante)throws SQLException, Exception{
        return this.banco.editarFabricante(fabricante);
    }
    
    public Grupo excluirGrupo(int codigo) throws SQLException, Exception{
        if(GerenciadorUsuarios.getUsuarioLogado().getTipo().equals(Usuario.TIPO_VENDEDOR)) {
            throw new Exception("O usuário logado não tem autoridade para realizar esta operação");
        }
        return this.banco.excluirGrupo(codigo);
    }
    
    public Fornecedor editarFornecedor(Fornecedor fornecedor)throws SQLException, Exception{
        return this.banco.editarFornecedor(fornecedor);
    }
    
    public Grupo editarGrupo(Grupo grupo)throws SQLException, Exception{
        return this.banco.editarGrupo(grupo);
    }
    
    public Produto editarProduto(Produto produto) throws SQLException{
        return this.banco.editarProduto(produto);
    }
    
    public Movimento cadastrarMovimento(Movimento movimento) throws Exception{
        if(movimento.getProduto().getCodigo() == 0){
            throw new Exception("Selecione um produto!");
        }
        if((movimento.getTipo().equals(Movimento.SAIDA))&&
                (movimento.getProduto().getEstoqueAtual() < movimento.getQuantidade())){
            throw new Exception("Não há produtos suficiente para a SAÍDA!");
        }
        
        if(movimento.getTipo().equals(Movimento.SAIDA)){
            movimento.getProduto().setEstoqueAtual(
                    movimento.getProduto().getEstoqueAtual() - movimento.getQuantidade()
            );
        }else{
            movimento.getProduto().setEstoqueAtual(
                    movimento.getProduto().getEstoqueAtual() + movimento.getQuantidade()
            );
        }
        this.banco.editarProduto(movimento.getProduto());
        
        return this.banco.cadastrarMovimento(movimento);
    }

    public List<Movimento> getMovimentos(){
        return this.banco.getMovimentos();
    }
    
    public List<Movimento> consultaMovimentos(String inicio, String fim){
        String[] antes = inicio.split("/");
        String[] depois = fim.split("/");
        return this.banco.consultaMovimentos(antes[2]+"-"+antes[1]+"-"+antes[0], 
                depois[2]+"-"+depois[1]+"-"+depois[0]);
    }
    
    public List<Movimento> consultaMovimentos(String inicio, String fim, String tipo){
        String[] antes = inicio.split("/");
        String[] depois = fim.split("/");
        return this.banco.consultaMovimentos(antes[2]+"-"+antes[1]+"-"+antes[0], 
                depois[2]+"-"+depois[1]+"-"+depois[0],tipo);
    }
    
}//Fim da Classe
