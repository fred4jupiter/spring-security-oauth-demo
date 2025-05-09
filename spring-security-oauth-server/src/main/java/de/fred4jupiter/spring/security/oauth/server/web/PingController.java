package de.fred4jupiter.spring.security.oauth.server.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PingController {

    @GetMapping("/ping")
    public PingResult ping() {
        return new PingResult("ping " + LocalDateTime.now());
    }
}
