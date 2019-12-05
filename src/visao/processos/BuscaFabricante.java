/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.processos;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import persistencia.entidades.Fabricante;

/**
 *
 * @author brunn_000
 */
public class BuscaFabricante extends Thread{
    
    private JTable tabela;
    private List<Fabricante> lista;
    private JComboBox combo;
    private int tipo;
    
    public BuscaFabricante(JTable tabela, List<Fabricante> lista){
        this.tabela = tabela;
        this.lista = lista;
        tipo = 1;
    }
    
    public BuscaFabricante(JComboBox combo, List<Fabricante> lista){
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
        for (Fabricante fabricante : lista) {
            combo.addItem(fabricante);
        }
    }
    
    private void adicionarTabela(){
        
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            //Limpa a tabela antes de adicionar os novos dados
            int tam = modelo.getRowCount();
            for(int i=0;i<tam;i++){
                modelo.removeRow(0);
            }
            
            for(Fabricante f : lista){
                String[] linha = new String[4];
                linha[0] = f.getCodigo()+"";
                linha[1] = f.getNome();
                modelo.addRow(linha);
            }
    }
    
}
