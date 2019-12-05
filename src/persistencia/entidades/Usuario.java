/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name="usuarios")
public class Usuario {
    
    private String nome;
    
    private String senha;
    
    private String tipo;
    
    @Id
    private String login;
    
    private String email;
    
    private String status;
    
    public final static String TIPO_GERENTE = "Gerente";
    public final static String TIPO_VENDEDOR = "Vendedor";
    public final static String TIPO_ADMNISTRADOR = "Admin";
    
    public Usuario(){
    
    }
    
    public Usuario(String nome,String login,String senha,String tipo){
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}//Fim da Classe
