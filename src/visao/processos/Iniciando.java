/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.processos;

import controle.FachadaControle;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import visao.TelaLogin;

/**
 *
 * @author Bruno
 */
public class Iniciando extends Thread{
    
    private boolean vida = false;
    
    @Override
    public void run(){
        vida = true;
        System.out.println("INICANDO O SISTEMA...............................................");
        iniciandoSistema();
        vida = false;
    }
    
    public void iniciandoSistema(){
        
        try {
            FachadaControle.getFachadaControle().criarUsuarioPadrao();
            //jlogin.select(0, jsenha.getText().length());
            
        } catch (SQLException ex) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean taViva(){
        return vida;
    }
    
    
}//Fim da Classe
