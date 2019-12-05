/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia.entidades;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="consertos")
public class Conserto {
    
    @Id
    @GeneratedValue
    private int codigo;
    private Date data;
    
    @ManyToOne @JoinColumn(name = "veiculo")
    private Veiculo veiculo;
    
    @ManyToOne @JoinColumn(name = "cliente")
    private Cliente cliente;
    
    @OneToOne
    @JoinColumn(name = "venda")
    private Venda venda;

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }      
    public int getCodigo() {
        return codigo;
    }

    public Date getData() {
        return data;
    }


    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setData(Date data) {
        this.data = data;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

       
}
