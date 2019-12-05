/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import persistencia.FachadaPersistencia;
import persistencia.entidades.Caixa;
import persistencia.entidades.Cliente;
import persistencia.entidades.Item;
import persistencia.entidades.Produto;
import persistencia.entidades.Usuario;
import persistencia.entidades.Venda;

/**
 *
 * @author Bruno
 */
class GerenciadorVendas {
    
    private Caixa caixa;
    private FachadaPersistencia banco;
    public GerenciadorVendas(){
        this.banco = new FachadaPersistencia();
        this.caixa = null;
    }
    
    
    
    public Venda efetuarVenda(Venda venda) throws SQLException, Exception{
        if(venda != null){
            if(venda.getItens().isEmpty()){
                throw new Exception("Adicione Item na venda!");
            }
            if(this.caixa == null){
                throw new Exception("Caixa ainda não foi aberto hoje!");
            }
            if(venda.getFormaPagamento().isEmpty()){
                throw new Exception("Escolha uma forma de pagamento!");
            }
            
            //Adiciona em DINHEIRO ou CARTÃO
            if(venda.getFormaPagamento().equals(Venda.CARTAO)){
                this.caixa.setTotalCartao(this.caixa.getTotalCartao()+venda.getTotal());
            }else{
                this.caixa.setTotalDinheiro(this.caixa.getTotalDinheiro()+venda.getTotal());
            }
            //String[] dia = this.venda.getData().split("/");
            //this.venda.setData(dia[2]+"-"+dia[1]+"-"+dia[0]);
            
            //Somente realiza a venda se não for um carro no pátio
            if(!venda.getStatus().equals(Venda.PATIO)){
                List<Item> itens = venda.getItens();
                for (Item item : itens) {
                    //if(item.getQuantidade()>item.getProduto().getEstoqueAtual()){//sem estoque
                        //throw new Exception("Venda não pode ser finalizada pois não há estoque disponível para algum dos itens!");
                    //}

                    Produto p = item.getProduto();
                    p.setEstoqueAtual(p.getEstoqueAtual() - item.getQuantidade());
                    this.banco.editarProduto(p);
                }
            }
            
            
            this.caixa.setCaixaFinal(this.caixa.getCaixaFinal()+venda.getTotal());
            this.banco.editarCaixa(caixa);
            
            venda.setStatus(Venda.FINALIZADA);
            Venda resultado = this.banco.salvarVenda(venda);
            
//            String[] dia = this.caixa.getData().split("/");
//            this.caixa.setData(dia[2]+"-"+dia[1]+"-"+dia[0]);
//            
//            this.caixa = this.banco.editarCaixa(this.caixa);
            venda = null;
            return resultado;
        }
        return null;
    }
   
    
    public Caixa getCaixa(){

        return this.caixa;
    }
    
    
    
    public void abrirCaixa(double valorInicial) throws SQLException, Exception{
        if(this.caixa != null){
            throw new Exception("Caixa já foi aberto hoje!");
        }
        if(valorInicial < 0){
            throw new Exception("O valor inicial deve ser pelo menos Zero!");
        }
        this.caixa = new Caixa();
        this.caixa.setCaixaInicial(valorInicial);
        this.caixa.setCaixaFinal(valorInicial);
        SimpleDateFormat data = new SimpleDateFormat("dd/MM/yyyy");
        this.caixa.setDataInicial(new Date());
        this.caixa.setStatus("Aberto");
        this.caixa.setTotalCartao(0);
        this.caixa.setTotalDinheiro(0);
        this.caixa = this.banco.abrirCaixa(this.caixa);
        this.caixa.setDataInicial(new Date());
    }
    
    public Caixa fecharCaixa() throws SQLException, Exception{
        if(!GerenciadorUsuarios.getUsuarioLogado().getTipo().equals(Usuario.TIPO_GERENTE)){
            throw new Exception("O usuário logado não tem autoridade para realizar esta operação!");
        }
       
        this.caixa.setDataFinal(new Date());
        this.caixa.setStatus("Fechado");

        this.caixa = this.banco.fecharCaixa(this.caixa);
        this.caixa = null;
        return null;
    }
    
    
    
    public Caixa editarCaixa(Caixa caixa)throws SQLException, Exception{
        if(!GerenciadorUsuarios.getUsuarioLogado().getTipo().equals(Usuario.TIPO_GERENTE)){
            throw new Exception("O usuário logado não tem autoridade para realizar esta operação!");
        }
        this.caixa = this.banco.editarCaixa(caixa);
        return this.caixa;
    }//Fim da Edição dos Valores do Caixa
    
    public boolean isCaixaAberto() throws SQLException{
        this.caixa = this.banco.getCaixaAberto();
        
        return this.caixa != null;
    }//Fim do is caixa Aberto
    
    public List<Venda> consultaVendas(String inicio, String fim) throws SQLException, Exception{
        String[] data_inicio = inicio.split("/");
        String[] data_fim = fim.split("/");
        
        List<Venda> vendas = this.banco.consultaVendas(data_inicio[2]+"-"+data_inicio[1]+"-"+data_inicio[0],data_fim[2]+"-"+data_fim[1]+"-"+data_fim[0]);
       
        return vendas;
        
    }
    
    public List<Venda> consultaVendas() throws Exception{
        List<Venda> vendas = this.banco.consultaVendas();
        return vendas;
    }
    
    public List<Caixa> buscarCaixas(){
        return this.banco.buscarCaixas();
    }
    
    public List<Caixa> buscarCaixas(String inicio, String fim){
         String[] data_inicio = inicio.split("/");
         String[] data_fim = fim.split("/");
         
         return this.banco.buscarCaixas(
             data_inicio[2]+"-"+data_inicio[1]+"-"+data_inicio[0],data_fim[2]+"-"+data_fim[1]+"-"+data_fim[0]
             );
    }
    
    public List<Venda> consultaVendas(String forma) throws SQLException, Exception{
        return this.banco.consultaVendas(forma);
        
    }

    /**
     * Salvar um orçamento
     * um orçamento é uma venda ainda não concluída
     * @return 
     */
    public Venda salvarOrcamento(Venda venda) throws Exception {
         
            if(venda.getItens().isEmpty()){
                throw new Exception("Adicione Item no orçamento!");
            }
            venda.setStatus(Venda.ORCAMENTO);
            Venda resultado = this.banco.salvarVenda(venda);
            
            return resultado;
    }
    
    public Venda salvarCarroPatio(Venda venda)throws Exception {
        if(venda.getItens().isEmpty()){
                throw new Exception("Adicione Item no orçamento!");
            }
            venda.setStatus(Venda.PATIO);
            Venda resultado = this.banco.salvarVenda(venda);
            
            return resultado;
    }
    
    public Venda getVenda(int codigo){
        return this.banco.getVenda(codigo);
    }
    
}//Fim da Classe