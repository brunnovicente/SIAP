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
 * @author User
 */
@Entity
@Table(name="fabricante")
public class Fabricante {
    
    @Id
    @GeneratedValue
    private int codigo;
    private String nome;
    private String status;
    
    public Fabricante(String nome){
        this.nome = nome;
    }
    public Fabricante(int codigo){
        this.codigo = codigo;
    }
    public Fabricante(int codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }
    public Fabricante(){
    
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
}//Fim da Classe
