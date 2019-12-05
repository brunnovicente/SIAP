/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visao.processos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 *
 * @author brunn_000
 */
public class BarraEntrada extends Thread{
    
    private JProgressBar jbarra;
    private boolean rodar;
    private boolean acabou;
    private JFrame janela;
    private JFrame janelalogin;

    private Iniciando iniciando;
    
    public BarraEntrada(JProgressBar jbarra, JFrame janela, JFrame janelalogin, Iniciando iniciando){
        this.jbarra = jbarra;
        this.rodar = true;
        this.janela = janela;
        this.janelalogin = janelalogin;
        this.iniciando = iniciando;
        this.iniciando.start();
        
    }

   
    @Override
    public void run(){
        
        int valor = jbarra.getValue();
        
        while(rodar){
            //jbarra.setIndeterminate(true);
            try {
                Thread.sleep(600);
            } catch (InterruptedException ex) {
                Logger.getLogger(BarraEntrada.class.getName()).log(Level.SEVERE, null, ex);
            }            
            
            
            if(iniciando.taViva()){
                if(jbarra.getValue() < 100){
                    valor += 10;
                    jbarra.setValue(valor);
                }
            }else{
                    if(!iniciando.taViva()){
                        this.parar();
                        this.janelalogin.setVisible(true);
                        this.janela.dispose();
                        
                    }
            }
            System.out.println("Executando...");
        }
        
    }//Fim do RUN
    
    public void parar(){
        this.rodar = false;
    }
    
}//Fim da barra
