package de.fred4jupiter.spring.security.oauth.server.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;

@RestController
public class PingController {

    private static final Logger LOG = LoggerFactory.getLogger(PingController.class);

    @GetMapping("/ping")
    public PingResult ping(Authentication authentication) {
        LOG.info("ping endpoint was called. authentication={}", authentication);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        LOG.info("authorities={}", authorities);

        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            LOG.debug("jwtAuthenticationToken={}", jwtAuthenticationToken);
            Jwt token = jwtAuthenticationToken.getToken();
            LOG.debug("token value={}", token.getTokenValue());
        }

        return new PingResult("ping " + LocalDateTime.now());
    }
}
