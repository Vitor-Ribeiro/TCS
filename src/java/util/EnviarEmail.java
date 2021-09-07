/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import modelo.Usuario;

/**
 *
 * @author User
 */
public class EnviarEmail {

    private Session session;

    private void configuracaoDeConta() {
        Properties props = new Properties();
        /**
         * Parâmetros de conexão com servidor Gmail
         */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vdsribeiro21@gmail.com",
                        "123151232Vt");
            }
        });

        /**
         * Ativa Debug para sessão
         */
        session.setDebug(true);
    }

    public boolean enviarEmailMensagem(String mens, Usuario usuario) {
        boolean enviou = false;
        StringBuilder mensagem = new StringBuilder();
        try {
            configuracaoDeConta();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("vdsribeiro21@gmail.com"));
            //Remetente

            //Destinatário(s)
            Address[] toUser = InternetAddress.parse(usuario.getLogin());

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Alerta de Ar Condicionado Ligado");//Assunto
            mensagem.append("Segue os Aparelhos ligados");
            mensagem.append("\n\n\n");
            mensagem.append(mens).append("\n");            
            mensagem.append("");
            mensagem.append("\n\n\n");
            mensagem.append("Atenciosamente Administração SMAC");
            
            
            message.setText(mensagem.toString());
            /**
             * Método para enviar a mensagem criada
             */
            Transport.send(message);
            enviou = true;
            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return enviou;
    }

}
