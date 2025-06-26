package de.fred4jupiter.spring.security.oauth.webapp.web;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String home(Model model, @AuthenticationPrincipal OidcUser user) {
        model.addAttribute("username", user.getUserInfo().getClaimAsString("preferred_username"));
        model.addAttribute("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(", ")));
        model.addAttribute("name", user.getUserInfo().getClaimAsString("name"));

        List<ClaimEntry> claimEntries = user.getClaims().entrySet().stream().map(entry -> new ClaimEntry(entry.getKey(), entry.getValue())).toList();
        model.addAttribute("claimEntries", claimEntries);
        return "home";
    }
}
