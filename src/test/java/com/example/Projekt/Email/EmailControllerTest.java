package com.example.Projekt.Email;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmailControllerTest {

    @Mock
    private EmailService emailService;

    @Mock
    private Model model;

    @InjectMocks
    private EmailController emailController;

    @Test
    void sendEmail_shouldAddSuccessMessageToModel() throws MessagingException {
        String viewName = emailController.sendEmail("Jan", "Kowalski", "jan@example.com", "Reksio", "Chciałbym adoptować psa", model);

        verify(emailService, times(1)).sendHtmlEmail(anyString());
        verify(model, times(1)).addAttribute(eq("success"), anyString());
        assertEquals("adopt", viewName);
    }

    @Test
    void sendEmail_shouldAddErrorMessageToModelWhenExceptionThrown() throws MessagingException {
        doThrow(new MessagingException("Błąd SMTP")).when(emailService).sendHtmlEmail(anyString());

        String viewName = emailController.sendEmail("Jan", "Kowalski", "jan@example.com", "Reksio", "Chciałbym adoptować psa", model);

        verify(model, times(1)).addAttribute(eq("error"), contains("Błąd podczas wysyłania e-maila"));
        assertEquals("adopt", viewName);
    }
}
