/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.processos;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import persistencia.entidades.Fornecedor;

/**
 *
 * @author brunn_000
 */
public class BuscaFornecedor extends Thread{
    private JTable tabela;
    private List<Fornecedor> lista;
    private JComboBox combo;
    private int tipo;
    
    public BuscaFornecedor(JTable tabela, List<Fornecedor> lista){
        this.tabela = tabela;
        this.lista = lista;
        tipo = 1;
    }
    
    public BuscaFornecedor(JComboBox combo, List<Fornecedor> lista){
        this.combo = combo;
        this.lista = lista;
        tipo = 2;
    }
    
    @Override
    public void run(){
        switch(tipo){
            case 1:
                adicionarTabela();
                break;
            case 2:
                adicionarCombo();
                break;
        }
    }
    
    private void adicionarCombo(){
        for (Fornecedor fornecedor : lista) {
            combo.addItem(fornecedor);
        }
    }
    
    private void adicionarTabela(){
        
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            //Limpa a tabela antes de adicionar os novos dados
            int tam = modelo.getRowCount();
            for(int i=0;i<tam;i++){
                modelo.removeRow(0);
            }
            
            for(Fornecedor f : lista){
                String[] linha = new String[4];
                linha[0] = f.getCodigo()+"";
                linha[1] = f.getNome();
                modelo.addRow(linha);
            }
    }
}
