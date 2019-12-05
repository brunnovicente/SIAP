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
@Table(name="pedidos")
public class Pedido {
    
    public static final String PENDENTE = "Pendente";
    public static final String CONCLUIDO = "Concluido";
    public static final String CANCELADO = "Cancelado";
    
    @Id
    @GeneratedValue
    private int codigo;
    private double preco;
    private int quantidade;
    private Date dataPedido;
    private Date dataPrevista;
    private double total;
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "fornecedor")
    private Fornecedor fornecedor;
    
    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido")
    private List<ItensPedido> itens;
    
    public Pedido(){
        this.itens = new ArrayList<ItensPedido>();
    }
    
    public int getCodigo() {
        return codigo;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public Date getDataPrevista() {
        return dataPrevista;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<ItensPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedido> itens) {
        this.itens = itens;
    }

    public void setItem(ItensPedido item) {
        this.itens.add(item);
    }
    
}
