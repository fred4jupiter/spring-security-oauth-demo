package de.fred4jupiter.spring.security.oauth.server.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PingController {

    private static final Logger LOG = LoggerFactory.getLogger(PingController.class);

    @GetMapping("/ping")
    public PingResult ping() {
        LOG.info("ping endpoint was called");
        return new PingResult("ping " + LocalDateTime.now());
    }
}
