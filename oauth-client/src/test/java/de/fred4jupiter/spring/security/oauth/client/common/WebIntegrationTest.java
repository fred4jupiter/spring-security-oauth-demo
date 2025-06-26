package de.fred4jupiter.spring.security.oauth.client.common;

import de.fred4jupiter.spring.security.oauth.client.ClientApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = ClientApplication.class)
@ActiveProfiles("integrationtest")
public @interface WebIntegrationTest {
}

