package com.example.Projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Locale;
@Controller
public class HelloController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    @GetMapping("/")
    public String hello() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        String helloMessage = messageSource.getMessage("home", null, LocaleContextHolder.getLocale());
        model.addAttribute("helloMessage", helloMessage);
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/adopt")
    public String adoptPage() {
        return "adopt";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @GetMapping("/donate")
    public String donatePage() {
        return "donate";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/lang")
    public String changeLanguage(@RequestParam("lang") String lang, HttpServletRequest request, HttpServletResponse response) {
        if (lang == null || lang.isEmpty()) {
            lang = "pl";
        }

        Locale locale = new Locale(lang);
        localeResolver.setLocale(request, response, locale);
        return "redirect:/";
    }
}

