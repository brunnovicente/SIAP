/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Bruno
 */
@Entity
@Table(name="vendas")
public class Venda {
    
    public static final String CARTAO = "Cartão";
    public static final String DINHEIRO = "Dinheiro";
    
    public static final String ORCAMENTO = "Orçamento";
    public static final String PATIO = "Pátio";
    public static final String CANCELADA = "Cancelada";
    public static final String FINALIZADA = "Finalizada";
    
    @Id
    @GeneratedValue
    private int codigo;
    
    @OneToMany(cascade = (CascadeType.ALL), fetch = FetchType.EAGER)
    @JoinColumn(name = "venda")
    private List<Item> itens;
    
    private Date data;
    
    private double total;
    
    @ManyToOne
    @JoinColumn(name = "vendedor")
    private Usuario vendedor;
    
    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "caixa")
    private Caixa caixa;
    
    private String formaPagamento;
    
    private double desconto;
    
    private String status;
    
    public Venda(){
        this.itens = new ArrayList<Item>();
        this.status = Venda.ORCAMENTO;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public Date getData() {
        return data;
    }

    public List<Item> getItens() {
        return itens;
    }

    public double getTotal() {
        return total;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
    
    public void setItem(Item item){
        this.itens.add(item);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public void setCaixa(Caixa caixa) {
        this.caixa = caixa;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }
    
    @Override
    @SuppressWarnings("CloneDoesntCallSuperClone")
    public Venda clone() throws CloneNotSupportedException{
        Venda venda = new Venda();
        venda.caixa = this.caixa;
        venda.codigo = this.codigo;
        venda.data = this.data;
        venda.desconto = this.desconto;
        venda.formaPagamento = this.formaPagamento;
        venda.total = this.total;
        venda.vendedor = this.vendedor;
        venda.cliente = this.cliente;
        
        for (Item item : itens) {
            venda.getItens().add(item.clone());
        }
        return venda;
    }

    /**
     * retorna o status da venda
     * olhar os atributos finais estáticos da classe
     * @return 
     */
    public String getStatus() {
        return status;
    }

    /**
     * seta o status da venda
     * olhar os atributos finais estáticos da classe
     * @param status 
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    

}//Fim da Classe
