/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author brunn_000
 */
@Entity
@Table(name="movimentos")
public class Movimento {
    
    public static final String ENTRADA = "Entrada";
    public static final String SAIDA = "Saída";
    
    @Id
    @GeneratedValue
    private int codigo;
    
    @ManyToOne
    @JoinColumn(name = "produto")
    private Produto produto;
    
    private String tipo;
    private Date data;
    private int quantidade;
    
    @ManyToOne
    @JoinColumn(name = "criadopor")
    private Usuario criadopor;
            
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public static String getSAIDA() {
        return SAIDA;
    }

    public int getCodigo() {
        return codigo;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getTipo() {
        return tipo;
    }

    public Date getData() {
        return data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Usuario getCriadopor() {
        return criadopor;
    }

    public void setCriadopor(Usuario criadopor) {
        this.criadopor = criadopor;
    }
    
    
    
        
    
}//Fim da Classe
