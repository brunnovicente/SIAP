/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.processos;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import persistencia.entidades.Grupo;

/**
 *
 * @author brunn_000
 */
public class BuscaGrupo extends Thread{
    
    private JTable tabela;
    private List<Grupo> lista;
    private JComboBox combo;
    private int tipo;
    
    public BuscaGrupo(JTable tabela, List<Grupo> lista){
        this.tabela = tabela;
        this.lista = lista;
        tipo = 1;
    }
    
    public BuscaGrupo(JComboBox combo, List<Grupo> lista){
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
        for (Grupo grupo : lista) {
            //combo.addItem(grupo.getCodigo()+"-"+grupo.getNome());
            combo.addItem(grupo);
            
        }
    }
    
    private void adicionarTabela(){
        
            DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
            //Limpa a tabela antes de adicionar os novos dados
            int tam = modelo.getRowCount();
            for(int i=0;i<tam;i++){
                modelo.removeRow(0);
            }
            
            for(Grupo g : lista){
                String[] linha = new String[4];
                linha[0] = g.getCodigo()+"";
                linha[1] = g.getNome();
                modelo.addRow(linha);
            }
    }
    
}
