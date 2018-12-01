import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException, MessagingException {
        final Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src/main/resources/mail.properties");
        properties.load(fileInputStream);

        Session mailSesion = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSesion);
        message.setFrom(new InternetAddress("sender email"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("recipient email"));
        message.setSubject("Тема письма");
        message.setText("Mail body");

        Transport transport = mailSesion.getTransport();
        transport.connect(null, "ioannst12345");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
