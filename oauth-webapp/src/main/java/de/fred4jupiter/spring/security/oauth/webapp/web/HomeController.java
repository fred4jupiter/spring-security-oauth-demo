package de.fred4jupiter.spring.security.oauth.webapp.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home(Model model, @AuthenticationPrincipal OidcUser user) {
        model.addAttribute("username", user.getUserInfo().getClaimAsString("preferred_username"));
        model.addAttribute("authorities", user.getAuthorities());
        model.addAttribute("name", user.getUserInfo().getClaimAsString("name"));
        return "home";
    }
}
