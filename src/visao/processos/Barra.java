/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package visao.processos;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

/**
 *
 * @author brunn_000
 */
public class Barra extends Thread{
    
    private JProgressBar jbarra;
    private boolean rodar;
    private boolean acabou;
    private JDialog janela;
    private JTextField jtexto;
    private Process p;
    
    public Barra(JProgressBar jbarra, JDialog janela,JTextField jtexto, Process p){
        this.jbarra = jbarra;
        this.rodar = true;
        this.janela = janela;
        this.jtexto = jtexto;
        this.p = p;
    }

   
    @Override
    public void run(){
        int valor = jbarra.getValue();
        while(rodar){
            jtexto.setText(valor+"% Concluído...");
            //jbarra.setIndeterminate(true);
            try {
                Thread.sleep(2400);
            } catch (InterruptedException ex) {
                Logger.getLogger(Barra.class.getName()).log(Level.SEVERE, null, ex);
            }            
            
            if(!p.isAlive()) {
                valor = 100;
                jbarra.setValue(valor);
            } else {
                valor += 1;
                jbarra.setValue(valor);
            }
            
            
            //System.out.println("Executou..\n");
            if(valor == 100){
                JOptionPane.showMessageDialog(null, "Backup concluído com sucesso, por favor reinicie o SIAP!","Aviso Backup",JOptionPane.INFORMATION_MESSAGE);
                this.janela.dispose();
                //break;
            }
        }
    }
    
    public void parar(){
        this.rodar = false;
    }
    
}//Fim da barra
