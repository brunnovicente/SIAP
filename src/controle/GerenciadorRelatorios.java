/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.itextpdf.text.DocumentException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.FachadaPersistencia;
import persistencia.entidades.Caixa;
import persistencia.entidades.Cliente;
import persistencia.entidades.Movimento;
import persistencia.entidades.Pedido;
import persistencia.entidades.Produto;
import persistencia.entidades.Venda;
import visao.auxiliar.Arquivo;

/**
 *
 * @author Fenix
 */
class GerenciadorRelatorios {
    
    private RelatoriosPDF gerenciadorRelatorio;
    
    public GerenciadorRelatorios(){
        this.gerenciadorRelatorio = new RelatoriosPDF();
    }
    
//     public boolean backupDB(String path) {
//        return FachadaPersistencia.getFachadaPersistencia().backupDB(path);
//    }
    
//    public boolean restoreDB(String source) {
//        return FachadaPersistencia.getFachadaPersistencia().restoreDB(source);
//    }
    
    public void gerarRelatorioOrcamento(Venda venda) throws Exception{
        try {
            if(FachadaControle.getFachadaControle().getEmpresa() == null){
                throw new Exception("Adiciona os Dados da Empresa.");
            }
            File arquivo = gerenciadorRelatorio.gerarRelatorioNota(venda, Arquivo.salvaArquivo());
            Desktop.getDesktop().open(arquivo);
        } catch (DocumentException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (MalformedURLException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gerarRelatorioCaixa(List<Caixa> lista) throws Exception{
        try {
            if(FachadaControle.getFachadaControle().getEmpresa() == null){
                throw new Exception("Adiciona os Dados da Empresa.");
            }
            File arquivo = gerenciadorRelatorio.gerarRelatorioCaixa(lista);
            Desktop.getDesktop().open(arquivo);
        } catch (DocumentException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (MalformedURLException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gerarRelatorioMovimento(List<Movimento> lista) throws Exception{
        try {
            if(FachadaControle.getFachadaControle().getEmpresa() == null){
                throw new Exception("Adiciona os Dados da Empresa.");
            }
            File arquivo = gerenciadorRelatorio.gerarRelatorioMovimentos(lista);
            Desktop.getDesktop().open(arquivo);
        } catch (DocumentException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (MalformedURLException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gerarRelatorioClientes(ArrayList<Cliente> clientes,String tipo) throws Exception{
        try {
            if(FachadaControle.getFachadaControle().getEmpresa() == null){
                throw new Exception("Adiciona os Dados da Empresa.");
            }
            File arquivo = gerenciadorRelatorio.gerarRelatorioClientes(clientes,tipo);
            Desktop.getDesktop().open(arquivo);
        } catch (DocumentException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }  catch (MalformedURLException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gerarRelatorioVendas(ArrayList<Venda> vendas, String inicio, String fim) throws Exception{
        try {
            if(FachadaControle.getFachadaControle().getEmpresa() == null){
                throw new Exception("Adiciona os Dados da Empresa.");
            }
            File  arquivo = gerenciadorRelatorio.gerarRelatorioVendas(vendas, inicio, fim, Arquivo.salvaArquivo());
            Desktop.getDesktop().open(arquivo);
        } catch (DocumentException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gerarRelatorioProdutos(ArrayList<Produto> lista) throws Exception{
        try {
            if(FachadaControle.getFachadaControle().getEmpresa() == null){
                throw new Exception("Adiciona os Dados da Empresa.");
            }
            File arquivo = gerenciadorRelatorio.gerarRelatorioProdutos(lista);
            Desktop.getDesktop().open(arquivo);
        } catch (DocumentException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void gerarRelatorioPedidos(List<Pedido> lista) throws Exception{
        try {
            if(FachadaControle.getFachadaControle().getEmpresa() == null){
                throw new Exception("Adiciona os Dados da Empresa.");
            }
            File arquivo = gerenciadorRelatorio.gerarRelatorioPedidos((ArrayList<Pedido>)lista);
            Desktop.getDesktop().open(arquivo);
        } catch (DocumentException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GerenciadorRelatorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
