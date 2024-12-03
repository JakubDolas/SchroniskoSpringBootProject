package com.example.Projekt;

import jakarta.mail.MessagingException;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text, RedirectAttributes redirectAttributes) {
        try {
            emailService.sendHtmlEmail(to, subject, text);
            redirectAttributes.addFlashAttribute("success", "E-mail wysłany pomyślnie!");
        } catch (MailException | MessagingException e) {
            redirectAttributes.addFlashAttribute("error", "Wystąpił błąd przy wysyłaniu e-maila.");
        }
        return "redirect:/"; // Przekierowanie na stronę główną
    }

    @Entity
    @Table(name = "zwierzeta") // Nazwa tabeli w bazie danych
    public static class Animal {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        // Gettery i settery
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
