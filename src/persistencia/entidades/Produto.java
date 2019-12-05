/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.entidades;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name="produtos")
public class Produto {
    
    public static final String ATIVO = "Ativo";
    public static final String INATIVO = "Inativo";
    
    @Id
    @GeneratedValue
    private int codigo;
    
    
    private String codigoproduto;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "similares",
            joinColumns = @JoinColumn(name = "produto"), inverseJoinColumns = @JoinColumn(name = "similar")
    )
    private Set<Produto> similares;
    
   @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
		name = "produtos_grupos",
		joinColumns = @JoinColumn(name = "produto"), inverseJoinColumns = @JoinColumn(name = "grupo")
	)
    private List<Grupo> grupo;
    
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//		name = "produtos_fornecedores",
//		joinColumns = @JoinColumn(name = "produto"), inverseJoinColumns = @JoinColumn(name = "fornecedor")
//	)
//    private List<Fornecedor> fornecedor;
   
   @OneToMany(cascade = (CascadeType.ALL), fetch = FetchType.EAGER)
    @JoinColumn(name = "Produto_fornecedor")
   private List<Produto_fornecedor> fornecedor;
   
    
    @Column(unique=true)
    private String codigoBarra;
    
    private String descricao;
    
    private int estoqueAtual;
    
    private int estoqueMinimo;
    
    private double precoCusto;
    
    private double precoVenda;
    
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
//    @Column(name = "nomefoto", unique = false, nullable = true)
//    private String nomefoto;
    
    @Column(name = "foto", unique = false, nullable = true, length = 1000000)
    private byte[] foto;
    
    @ManyToOne
    @JoinColumn(name = "fabricante")
    private Fabricante fabricante;
    
    public Produto(){
        
        this.fornecedor = new ArrayList<Produto_fornecedor>();
        this.similares = new HashSet<Produto>();
        this.grupo = new ArrayList<Grupo>();
        
    }
    
    public Produto(int codigo, String codigoproduto,String descricao, double precoVenda, int estoqueAtual,Fabricante fabricante){
        this.codigo = codigo;
        this.codigoproduto = codigoproduto;
        this.estoqueAtual = estoqueAtual;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
        this.fabricante = fabricante;
        
        this.fornecedor = new ArrayList<Produto_fornecedor>();
        this.similares = new HashSet<Produto>();
        this.grupo = new ArrayList<Grupo>();
        
    }
    
    public String getCodigoproduto() {
        return codigoproduto;
    }

    public void setCodigoproduto(String codigoproduto) {
        this.codigoproduto = codigoproduto;
    }
    
//    public void setCodigoFor(String codigoFor) {
//        this.codigoFor = codigoFor;
//    }
//    
//    public String getCodigoFor(){
//        return this.codigoFor;
//    }

//    public void setNomefoto(String nomefoto) {
//        this.nomefoto = nomefoto;
//    }
    
    public Produto(int codigo){
        this.codigo = codigo;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public String getCodigoBarra(){
        return this.codigoBarra;
    }
    
    public String getDescricao() {
        return descricao;
    }

    public int getEstoqueAtual() {
        return estoqueAtual;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }
    
//    public String getNomefoto() {
//        return nomefoto;
//    }

    public byte[] getFoto() {
        return foto;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setEstoqueAtual(int estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }
    
    
    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public List<Produto_fornecedor> getFornecedor() {
        return this.fornecedor;
    }

    public void setFornecedor(Produto_fornecedor fornecedor) {
        this.fornecedor.add(fornecedor);
    }
    
    public void setFornecedor(List<Produto_fornecedor> fornecedor){
        this.fornecedor = fornecedor;
    }
    


    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Set<Produto> getSimilares() {
        return similares;
    }

    public void setSimilares(Set<Produto> similares) {
        this.similares = similares;
    }
    
    public void setGrupo(Grupo grupo) {
        this.grupo.add(grupo);
    }
    
    public void setGrupo(List<Grupo> grupo){
        this.grupo = grupo;
    }

    public List<Grupo> getGrupo(){
        return this.grupo;
    }
    
    
    @Override
    public boolean equals(Object p){
        
        if(!(p instanceof Produto)) return false;
        
        Produto prod = (Produto) p;
        
        return this.codigo == prod.codigo;
    
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.codigo;
        return hash;
    }
    
    @Override
    public String toString(){
        return this.descricao;
    }
    
    
}//Fim da Classe
