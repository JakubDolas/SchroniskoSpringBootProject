package com.example.Projekt.Email;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/adopt")
    public String showEmailForm() {
        return "adopt";
    }

    @PostMapping("/send-email")
    public String sendEmail(@RequestParam String firstName,
                            @RequestParam String lastName,
                            @RequestParam String userEmail,
                            @RequestParam String dogName,
                            @RequestParam String text,
                            Model model) {
        String message = "<h1>Wniosek o adopcję</h1>"
                + "<p><strong>Imię:</strong> " + firstName + "</p>"
                + "<p><strong>Nazwisko:</strong> " + lastName + "</p>"
                + "<p><strong>Email:</strong> " + userEmail + "</p>"
                + "<p><strong>Imię psa:</strong> " + dogName + "</p>"
                + "<p><strong>Treść wiadomości:</strong> " + text + "</p>";

        try {
            emailService.sendHtmlEmail(message);
            model.addAttribute("success", "E-mail wysłano pomyślnie!");
        } catch (MailException | MessagingException e) {
            model.addAttribute("error", "Błąd podczas wysyłania e-maila: " + e.getMessage());
        }
        return "adopt";
    }
}
