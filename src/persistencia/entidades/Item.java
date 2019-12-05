/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Bruno
 */
@Entity
@Table(name="itens")
public class Item {
    
    //status do item para o carro no pátio
    public static final String STATUS_UTILIZADO = "utilizado";
    public static final String STATUS_NAOUTILIZADO = "nao_utilizado";
    public static final String STATUS_NAOAPLICAVEL = "nao_aplicavel";
    
    @Id
    @GeneratedValue
    private int codigo;
    
    @OneToOne @JoinColumn(name = "produto")
    private Produto produto;
    private double preco;
    private int quantidade;
    private double desconto;
    private double total;
    
    //status do item para o carro no pátio
    private String status = STATUS_NAOAPLICAVEL;
    
    @ManyToOne @JoinColumn(name = "venda")
    private Venda venda;

    public double getDesconto() {
        return desconto;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getPreco() {
        return preco;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public void setCodigo(int id) {
        this.codigo = id;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public Item clone() throws CloneNotSupportedException{
        Item item = new Item();
        item.codigo = this.codigo;
        item.desconto = this.desconto;
        item.preco = this.preco;
        item.produto = this.produto;
        item.quantidade = this.quantidade;
        item.total = this.total;
        item.venda = this.venda;
        return item;
    }
    
}//Fim da classe