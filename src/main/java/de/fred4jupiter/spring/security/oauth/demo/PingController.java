package de.fred4jupiter.spring.security.oauth.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class PingController {

    @GetMapping("/ping")
    public String ping() {
        return "ping " + LocalDateTime.now();
    }
}
