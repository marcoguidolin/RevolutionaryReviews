package utils;

import com.sun.mail.smtp.SMTPTransport;
import java.security.Security;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author matte
 */
public class MailUtils
{

    public static void Send(String address, String title, String message) throws AddressException, MessagingException
    {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Properties props = System.getProperties();
        props.setProperty("mail.smtps.host", "smtp.libero.it");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtps.auth", "true");

        /*
        If set to false, the QUIT command is sent and the connection is immediately closed. If set 
        to true (the default), causes the transport to wait for the response to the QUIT command.

        ref :   http://java.sun.com/products/javamail/javadocs/com/sun/mail/smtp/package-summary.html
                http://forum.java.sun.com/thread.jspa?threadID=5205249
                smtpsend.java - demo program from javamail
         */
        props.put("mail.smtps.quitwait", "false");

        Session session = Session.getInstance(props, null);
        
        final MimeMessage msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress("webcommunity2017@libero.it"));
        msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(address, false));
        msg.setSubject(title);
        msg.setText(message, "utf-8");
        msg.setSentDate(new Date());

        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

        t.connect("smtp.libero.it", "webcommunity2017@libero.it", "pebvatabta26");
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
    }

}
