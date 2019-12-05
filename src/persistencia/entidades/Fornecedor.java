/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Bruno
 */
@Entity
@Table(name="fornecedores")
public class Fornecedor {
    
    @Id
    @GeneratedValue
    private int codigo;
    private String nome;
    private String status;
    
    public Fornecedor(String nome){
        this.nome = nome;
    }
    public Fornecedor(int codigo){
        this.codigo = codigo;
    }
    public Fornecedor(int codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }
    public Fornecedor(){
    
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString(){
        return this.nome;
    }
}
