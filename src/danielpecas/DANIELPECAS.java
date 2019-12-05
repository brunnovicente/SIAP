/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package danielpecas;

import controle.FachadaControle;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import visao.JanelaBarra;
import visao.TelaLogin;

/**
 *
 * @author Bruno
 */
public class DANIELPECAS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        TelaLogin telalogin = new TelaLogin();
        //login.setVisible(true);
        
        JanelaBarra janelabarra = new JanelaBarra(telalogin);
        janelabarra.setVisible(true);

        
    }
    
    
       
    
    private SessionFactory montaSessao() {
        Object server = "localhost";
        Object database = "bancopecas";
        //Object port = login;
        String serverUsername = "root";
        String serverPassword = "neural";
        String url = "jdbc:mysql://" + server + ":3306/" + database;

        Configuration conf = new Configuration();
        conf.configure().setProperty("hibernate.connection.url", url);
        conf.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
        conf.setProperty("hibernate.connection.username", serverUsername);
        conf.setProperty("hibernate.connection.password", serverPassword);
        //conf.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
        //conf.setProperty("hibernate.connection.datasource","java:/"+  server + ":" + port + "/" + database); 
        SessionFactory sf = conf.buildSessionFactory(); 
        return sf;
    }
    
}//Fim da Main
