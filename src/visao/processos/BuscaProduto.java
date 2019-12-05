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
import persistencia.entidades.Fabricante;
import persistencia.entidades.Fornecedor;
import persistencia.entidades.Grupo;
import persistencia.entidades.Produto;
import persistencia.entidades.Produto_fornecedor;

/**
 *
 * @author brunn_000
 */
public class BuscaProduto extends Thread {

    private JTable tabela;
    private List produtos;
    private String chave;
    private Grupo grupo = null;
    private List<Produto> lista;
    private boolean critico = false;
    private Produto_fornecedor profor = null;
    
    public BuscaProduto(JTable tabela, String chave, List<Produto> lista) {
        this.tabela = tabela;
        this.chave = chave;
        this.lista = lista;
    }

    public BuscaProduto(JTable tabela, String chave, List<Produto> lista, boolean critico) {
        this.tabela = tabela;
        this.chave = chave;
        this.lista = lista;
        this.critico = true;
    }

    public BuscaProduto(JTable tabela, Grupo grupo, List<Produto> lista) {
        this.tabela = tabela;
        this.grupo = grupo;
        this.lista = lista;
    }

    public BuscaProduto(JTable tabela, Produto_fornecedor profor, List<Produto> lista) {
        this.tabela = tabela;
        this.profor = profor;
        this.lista = lista;
    }
    
    @Override
    public void run() {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        //Limpa a tabela antes de adicionar os novos dados
        int tam = model.getRowCount();
        for (int i = 0; i < tam; i++) {
            model.removeRow(0);
        }
        try {
            if (critico) {
                this.produtos = FachadaControle.getFachadaControle().buscaProdutoMinimos();
                this.buscaChave();
            } else {
                if (grupo == null && profor == null) {
                    this.produtos = FachadaControle.getFachadaControle().buscaProduto(chave);
                    this.buscaChave();
                } else {
                    if(profor == null){
                        this.produtos = FachadaControle.getFachadaControle().buscaProduto(grupo);
                        this.outraBusca();
                    }else{
                        this.produtos = FachadaControle.getFachadaControle().buscaProduto(profor);
                        this.outraBusca();
                    }
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//Fim do Run
    
    public void buscaChave(){
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        //Limpa a tabela antes de adicionar os novos dados
        int tam = model.getRowCount();
        for (int i = 0; i < tam; i++) {
            model.removeRow(0);
        }
        
         //for (Produto produto : produtos) {
            for (int i=0; i < this.produtos.size();i++){
                
                model.addRow(new Object[6]);
                DecimalFormat df = new DecimalFormat("#,###.00");
                Object[] p = (Object[]) this.produtos.get(i);
                
                Produto produto = new Produto();
                produto.setCodigo((int) p[0]);
                produto.setCodigoproduto((String) p[1]);
                produto.setDescricao((String) p[2]);
                produto.setPrecoVenda((double) p[3]);
                produto.setEstoqueAtual((int) p[4]);
                produto.setFabricante((Fabricante) p[5]);
                //produto.setCodigoBarra((String) p[6]);
                
                tabela.setValueAt(produto.getCodigoproduto(), i, 0);
                tabela.setValueAt(produto, i, 1);
                tabela.setValueAt(df.format(produto.getPrecoVenda()), i, 2);
                tabela.setValueAt(produto.getEstoqueAtual()+"", i, 3);
                if(produto.getFabricante() != null)
                    tabela.setValueAt(produto.getFabricante().getNome(), i, 4);
                
                //model.addRow(linha);
                
                if (this.lista != null) {
                    this.lista.add(produto);
                }
            }
    }//Fim Busca CHave
    
    public void outraBusca(){
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        //Limpa a tabela antes de adicionar os novos dados
        int tam = model.getRowCount();
        for (int i = 0; i < tam; i++) {
            model.removeRow(0);
        }
        
         //for (Produto produto : produtos) {
            for (int i=0; i < this.produtos.size();i++){
                
                model.addRow(new Object[6]);
                DecimalFormat df = new DecimalFormat("#,###.00");
                //Object[] p = (Object[]) this.produtos.get(i);
                Produto p = (Produto) this.produtos.get(i);
                
                Produto produto = new Produto();
                produto.setCodigo(p.getCodigo());
                produto.setCodigoproduto(p.getCodigoproduto());
                produto.setDescricao(p.getDescricao());
                produto.setPrecoVenda(p.getPrecoVenda());
                produto.setEstoqueAtual(p.getEstoqueAtual());
                produto.setFabricante(p.getFabricante());
                //produto.setCodigoBarra((String) p[6]);
                
                tabela.setValueAt(produto.getCodigoproduto(), i, 0);
                tabela.setValueAt(produto, i, 1);
                tabela.setValueAt(df.format(produto.getPrecoVenda()), i, 2);
                tabela.setValueAt(produto.getEstoqueAtual()+"", i, 3);
                if(produto.getFabricante() != null)
                    tabela.setValueAt(produto.getFabricante().getNome(), i, 4);
                
                //model.addRow(linha);
                
                if (this.lista != null) {
                    this.lista.add(produto);
                }
            }
    }

}
