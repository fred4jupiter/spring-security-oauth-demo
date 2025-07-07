package de.fred4jupiter.spring.security.oauth.client;

import de.fred4jupiter.spring.security.oauth.client.common.WebIntegrationTest;
import de.fred4jupiter.spring.security.oauth.client.keycloak.KeycloakAdminClient;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebIntegrationTest
public class KeycloakAdminClientMT {

    private static final Logger LOG = LoggerFactory.getLogger(KeycloakAdminClientMT.class);

    @Autowired
    private KeycloakAdminClient keycloakAdminClient;

    @Test
    void createClient() {
        assertThat(keycloakAdminClient).isNotNull();
    }

    @Test
    void listAllClients() {
        List<String> allClients = keycloakAdminClient.listAllClients();
        assertThat(allClients).isNotEmpty();
        LOG.debug("allClients: {}", allClients);
    }
}
