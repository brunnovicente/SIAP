/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author brunn_000
 */

@Entity
@Table(name="empresa")
public class Empresa {
    
    
    @Id
    @GeneratedValue
    private int codigo;
    
    
    private String nome;
    
    
    @Column(name = "nomelogo", unique = false, nullable = true)
    private String nomelogo;
    
    @Column(name = "logo", unique = false, nullable = true, length = 500000)
    private byte[] logo;
    
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public byte[] getLogo() {
        return logo;
    }

    public String getNomelogo() {
        return nomelogo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public void setNomelogo(String nomelogo) {
        this.nomelogo = nomelogo;
    }

}
