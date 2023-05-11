package webuild.esprit.tn.tunisiacampwebapplication.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Service
public class SendMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendmail(String reciver, String subject,String content) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("tabibiapp8@gmail.com");
        helper.setTo(reciver);
        helper.setSubject(subject);
        helper.setText("<h1>"+content+"</h1>", true);
        javaMailSender.send(message);
    }


}
