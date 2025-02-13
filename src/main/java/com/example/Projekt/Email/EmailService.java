package com.example.Projekt.Email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private final String recipientEmail = "exampleTO@gmail.com";
    private final String recipientSubject = "Wniosek o adopcję";

    public void sendHtmlEmail(String message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setTo(recipientEmail);
        helper.setSubject(recipientSubject);
        helper.setText(message, true);

        mailSender.send(mimeMessage);
    }
}
