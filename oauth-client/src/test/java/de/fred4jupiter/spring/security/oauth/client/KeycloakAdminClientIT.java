package de.fred4jupiter.spring.security.oauth.client;

import de.fred4jupiter.spring.security.oauth.client.common.WebIntegrationTest;
import de.fred4jupiter.spring.security.oauth.client.keycloak.KeycloakAdminClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@WebIntegrationTest
public class KeycloakAdminClientIT {

    @Autowired
    private KeycloakAdminClient keycloakAdminClient;

    @Test
    void createClient() {
        assertThat(keycloakAdminClient).isNotNull();
    }

    @Test
    void findClientUUID() {
        Optional<String> clientUUID = keycloakAdminClient.findClientUUID("keycloak-admin-client");
        assertThat(clientUUID).isPresent();
        assertThat(clientUUID.get()).isNotBlank();
    }
}
