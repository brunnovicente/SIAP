/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.processos;

import java.awt.HeadlessException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author Bruno Vicente
 */
public class Servidor extends Thread{
    
    @Override
    public void run(){
         Process iniciaMySQL;  
  
        try {  
            
            Properties config = new Properties();
            config.load(new FileInputStream("parametros.ini"));
            
            System.out.println("SERVIDOR INICIADO...");
            // Comando para iniciar o MySQL Sever Portable:   
            iniciaMySQL = Runtime.getRuntime().exec(config.getProperty("MYSQL")+"/bin/mysqld.exe");  
  
            // Cria e inicializa a variavel "processComplete" do tipo int:  MYSQL
            int processComplete = iniciaMySQL.waitFor();  
  
  
  
            if (processComplete == 0) {// Se o valor da variavel "processComplete" for igual a "0":  
                System.out.println("MySQL Server foi iniciado com sucesso !");  
                //JOptionPane.showMessageDialog(null, "MySQL Server foi iniciado com sucesso !");
            } else {// Se o valor da variavel "processComplete" for diferente de "0":  
                //JOptionPane.showMessageDialog(null, "Falhou a iniciação do MySQL Server !");
                System.out.println("Falhou a iniciação do MySQL Server !");  
            }  
  
        } catch (IOException | InterruptedException | HeadlessException | Error ex) {// Trata as excessões:  
           System.out.println("Erro : " + ex.getCause());  
            //JOptionPane.showMessageDialog(null, ex.getCause());
        } 
    }
    
}
