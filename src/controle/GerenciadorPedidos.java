/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.SQLException;
import java.util.List;
import persistencia.FachadaPersistencia;
import persistencia.entidades.ItensPedido;
import persistencia.entidades.Pedido;
import persistencia.entidades.Produto;

/**
 *
 * @author Bruno
 */
class GerenciadorPedidos {
    
    private FachadaPersistencia banco;
    
    public GerenciadorPedidos(){
        this.banco = new FachadaPersistencia();
    }
    
 
    
    public Pedido salvarPedito(Pedido pedido) throws SQLException, Exception{
        
        if(FachadaControle.getFachadaControle().getCaixa() == null){
            throw new Exception("Caixa ainda não já foi aberto hoje!");
        }
        
        //String[] dp = pedido.getDataPedido().split("/");
        //String[] dv = pedido.getDataPrevista().split("/");
        
        //pedido.setDataPedido(dp[2]+"-"+dp[1]+"-"+dp[0]);
        //pedido.setDataPrevista(dv[2]+"-"+dv[1]+"-"+dv[0]);
                
        Pedido temp = this.banco.cadastrarPedido(pedido);

        return temp;
    }
    
    
    public List<Pedido> consultaPedidos() throws SQLException, Exception{
        List<Pedido> lista = this.banco.consultaPedidos();
        
        /*for(Pedido ped : lista){
            String[] dp = ped.getDataPedido().split("-");
            String[] dv = ped.getDataPrevista().split("-");
        
            ped.setDataPedido(dp[2]+"/"+dp[1]+"/"+dp[0]);
            ped.setDataPrevista(dv[2]+"/"+dv[1]+"/"+dv[0]);
        }*/
        
        return lista;
    }
    
    public List<Pedido> consultaPedidosPorStatus(String status) throws SQLException, Exception{
        List<Pedido> lista = this.banco.consultaPedidosPorStatus(status);
        
        /*for(Pedido ped : lista){
            String[] dp = ped.getDataPedido().split("-");
            String[] dv = ped.getDataPrevista().split("-");
        
            ped.setDataPedido(dp[2]+"/"+dp[1]+"/"+dp[0]);
            ped.setDataPrevista(dv[2]+"/"+dv[1]+"/"+dv[0]);
        }*/
        
        return lista;
    }
    
    public List<Pedido> consultaPedidosAtrasados(String data) throws SQLException, Exception{
        
        String[] dia = data.split("/");
        
        List<Pedido> lista = this.banco.consultaPedidosAtrasados(dia[2]+"-"+dia[1]+"-"+dia[0]);
        
        /*for(Pedido ped : lista){
            String[] dp = ped.getDataPedido().split("-");
            String[] dv = ped.getDataPrevista().split("-");
        
            ped.setDataPedido(dp[2]+"/"+dp[1]+"/"+dp[0]);
            ped.setDataPrevista(dv[2]+"/"+dv[1]+"/"+dv[0]);
        }*/
        
        return lista;
    }
    
    public Pedido getPedido(int codigo) throws SQLException{
        Pedido ped = this.banco.getPedido(codigo);
        /*String[] dp = ped.getDataPedido().split("-");
        String[] dv = ped.getDataPrevista().split("-");
        
        ped.setDataPedido(dp[2]+"/"+dp[1]+"/"+dp[0]);
        ped.setDataPrevista(dv[2]+"/"+dv[1]+"/"+dv[0]);*/
        
        return ped;
    }
    
    
    
    public Pedido modificarStatus(Pedido pedido) throws SQLException, Exception{
        if(FachadaControle.getFachadaControle().getCaixa() == null){
            throw new Exception("Caixa ainda não já foi aberto hoje!");
        }
        
        /*String[] dp = pedido.getDataPedido().split("/");
        String[] dv = pedido.getDataPrevista().split("/");

        pedido.setDataPedido(dp[2]+"-"+dp[1]+"-"+dp[0]);
        pedido.setDataPrevista(dv[2]+"-"+dv[1]+"-"+dv[0]);*/
        
        if(pedido.getStatus().equals(Pedido.CONCLUIDO)){
            for (ItensPedido item : pedido.getItens()) {
                Produto produto = item.getProduto();
                produto.setEstoqueAtual(item.getQuantidade()+produto.getEstoqueAtual());
                this.banco.editarProduto(produto);
            }
        }
        
        return this.banco.editarPedido(pedido);
    }
    
}//Fim da Classe