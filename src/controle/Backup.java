/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import com.itextpdf.text.log.SysoLogger;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.mail.Session;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;
import org.hibernate.EntityMode;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;





/**
 *
 * @author brunn_000
 */
public class Backup {
    
    private SessionFactory sessions;
    
    public Backup(){
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
        applySettings(configuration.getProperties());
        sessions = configuration.buildSessionFactory(builder.build());
    }
        
    
    public void dump(String backupFolder ,String table) throws IOException {
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement(table);

        Iterator<Element> it = queryDOM4J("SELECT p From Produto As p").iterator();
        while (it.hasNext()) {
            Element element = it.next();
            rootElement.add(element);
        }
        saveDocument(document, backupFolder, table);
}
 
public List queryDOM4J(String hsql) {
    Transaction tx = null;
    
    org.hibernate.Session session = sessions.openSession();
    SessionFactory dom4jSession = session.getSessionFactory();
    List list = null;
    System.out.println(hsql);
    try {
        tx = session.beginTransaction();
        list = dom4jSession.openSession().createQuery(hsql).list();//createQuery(hsql).list();
        tx.commit();
    } catch (Exception e) {
        if (tx != null) {
            tx.rollback();
        }
        e.printStackTrace();
    } finally {
        session.close();
    }
    return list;
    }

    protected void saveDocument(Document document, String path, String table) throws IOException
    {
        if (canWrite(path)) {
            File file = new File(getDumpFile(path, table));
            XMLWriter writer = new XMLWriter(new FileWriter(file));
            writer.write(document);
            writer.close();
        }
    }

    protected boolean canWrite(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            boolean makeDir = dir.mkdirs();
            if (!makeDir) {
                System.out.println("Unable to create directory: " + path);
            }
        }

        return dir.exists();
    }

    protected String getDumpFile(String path, String tableName)
    {
        return path + "/" + tableName + ".xml";
    }
    
}//FIM DA CLASSE
