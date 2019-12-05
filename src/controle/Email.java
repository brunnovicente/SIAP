/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Bruno
 */
class Email {
    
    /**
     *
     * @param remetente
     * @param senha
     * @param destinatario
     * @param assunto
     * @param mensagem
     * @throws MessagingException
     */
    public static void Enviar(final String remetente,final String senha,String destinatario,String assunto,String mensagem) throws MessagingException{

        Properties p = new Properties();
        p.put("mail.smtp.transport.protocol", "smtp");
        p.put("mail.host", "smtp.bcsolutions.com.br");
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.port", "465");
        p.put("mail.smtp.socketFactory.port", "465");
        p.put("mail.smtp.socketFactory.fallback", "false");
        p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        };

        Session session = Session.getInstance(p, auth);
        MimeMessage msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(remetente));
        msg.setRecipient(Message.RecipientType.TO,new InternetAddress(destinatario));
        msg.setSentDate(new Date());
        msg.setSubject(assunto);
        msg.setText(mensagem);
        Transport.send(msg);

    }
    
}//Fim da Classe
