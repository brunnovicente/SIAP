/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.processos;

import controle.FachadaControle;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import persistencia.entidades.Cliente;
import persistencia.entidades.Produto;
import persistencia.entidades.Venda;
import visao.JanelaGerandoRelatorio;
import visao.auxiliar.Arquivo;

/**
 *
 * @author Bruno
 */
public class GerandoRelatorio extends Thread{
    
    private JanelaGerandoRelatorio janela;
    private List<Produto> produtos = null;
    private List<Venda> vendas = null;
    private List<Cliente> clientes = null;
    
    private Venda venda = null;    
    private String inicio;
    private String fim;
    private String tipo;
            
    public GerandoRelatorio(JanelaGerandoRelatorio janela, List<Produto> produtos){
        this.janela = janela;
        this.produtos = produtos;
    }
    
    public GerandoRelatorio(JanelaGerandoRelatorio janela, List<Cliente> clientes,String tipo){
        this.janela = janela;
        this.clientes = clientes;
        this.tipo = tipo;
    }
    
    public GerandoRelatorio(JanelaGerandoRelatorio janela, List<Venda> vendas, String inicio, String fim){
        this.janela = janela;
        this.vendas = vendas;
        this.inicio = inicio;
        this.fim = fim;
    }
    
    public GerandoRelatorio(JanelaGerandoRelatorio janela, Venda venda){
        this.janela = janela;
        this.venda = venda;
    }
    
    
    
    @Override
    public void run(){
        try{
        if(produtos != null){
            FachadaControle.getFachadaControle().gerarRelatorioProdutos((ArrayList)produtos);
        }else if(vendas != null){
            FachadaControle.getFachadaControle().gerarRelatorioVendas((ArrayList)vendas, inicio, fim);
        }else if(venda != null){
            FachadaControle.getFachadaControle().gerarRelatorioOrcamento(this.venda);
        }else if(clientes != null){
            FachadaControle.getFachadaControle().gerarReltorioClientes((ArrayList)this.clientes,this.tipo);
        }
        janela.dispose();
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
            janela.dispose();
        }
    }

    
}//Fim da classe
