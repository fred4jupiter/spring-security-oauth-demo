package de.fred4jupiter.spring.security.oauth.webapp.web;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home(Model model, OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        model.addAttribute("username", oAuth2AuthenticationToken.getPrincipal().getAttribute("preferred_username"));
        model.addAttribute("authorities", oAuth2AuthenticationToken.getAuthorities());
        model.addAttribute("name", oAuth2AuthenticationToken.getPrincipal().getAttribute("name"));
        return "home";
    }
}
