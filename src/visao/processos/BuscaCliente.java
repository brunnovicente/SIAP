/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.processos;

import controle.FachadaControle;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import persistencia.entidades.Produto;

/**
 *
 * @author brunn_000
 */
public class BuscaCliente extends Thread{
    
    
    private JTable tabela;
    private List<Produto> produtos;
    private String chave;
    private int grupo = -1;
    private List<Produto> lista;
    
    public BuscaCliente(JTable tabela, String chave, List<Produto> lista){
        this.tabela = tabela;
        this.chave = chave;
        this.lista = lista;
    }
    
    public BuscaCliente(JTable tabela, int grupo){
        this.tabela = tabela;
        this.grupo = grupo;
    }
    
    @Override
    public void run(){
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        //Limpa a tabela antes de adicionar os novos dados
        int tam = model.getRowCount();
        for(int i=0;i<tam;i++){
            model.removeRow(0);
        }
        
        try {
            if(grupo == -1){
                this.produtos = FachadaControle.getFachadaControle().buscaProduto(chave);
            }else{
                //this.produtos = FachadaControle.getFachadaControle().buscaProduto(grupo);
            }
            
            for (Produto produto : produtos) {
                DecimalFormat df = new DecimalFormat("#,###.00");  
                
                String[] linha = new String[4];
                linha[0] = produto.getCodigoBarra();
                linha[1] = produto.getDescricao();
                linha[2] = df.format(produto.getPrecoVenda());
                linha[3] = produto.getEstoqueAtual()+"";
                model.addRow(linha);
                if(this.lista != null)
                    this.lista.add(produto);
            }
            
            //this.lista = this.produtos;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//Fim do Run
    
}
