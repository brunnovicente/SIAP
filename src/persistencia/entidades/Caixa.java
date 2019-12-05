/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bruno
 */
@Entity
@Table(name="caixas")
public class Caixa {
    
    @Id
    @GeneratedValue
    private int codigo;
    private Date datainicial;
    private Date datafinal;
    private double caixaInicial;
    private double caixaFinal;
    private double totalCartao;
    private double totalDinheiro;
    private String status;

    public int getCodigo() {
        return codigo;
    }

    public Date getDataInicial() {
        return datainicial;
    }
    
    public Date getDataFinal(){
        return datafinal;
    }

    public double getCaixaInicial() {
        return caixaInicial;
    }

    public double getCaixaFinal() {
        return caixaFinal;
    }

    public String getStatus() {
        return status;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDataInicial(Date data) {
        this.datainicial = data;
    }
    
    public void setDataFinal(Date data){
        this.datafinal = data;
    }
    
    public void setCaixaInicial(double caixaInicial) {
        this.caixaInicial = caixaInicial;
    }

    public void setCaixaFinal(double caixaFinal) {
        this.caixaFinal = caixaFinal;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalCartao() {
        return totalCartao;
    }

    public double getTotalDinheiro() {
        return totalDinheiro;
    }

    public void setTotalCartao(double totalCartao) {
        this.totalCartao = totalCartao;
    }

    public void setTotalDinheiro(double totalDinheiro) {
        this.totalDinheiro = totalDinheiro;
    }
    
    

}
