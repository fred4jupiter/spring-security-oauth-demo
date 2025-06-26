package de.fred4jupiter.spring.security.oauth.client.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "client")
public class OauthClientProperties {

    boolean keycloakClientEnabled;

    @NestedConfigurationProperty
    private KeycloakAdminClientProperties keycloakAdminClient;

    public boolean isKeycloakClientEnabled() {
        return keycloakClientEnabled;
    }

    public void setKeycloakClientEnabled(boolean keycloakClientEnabled) {
        this.keycloakClientEnabled = keycloakClientEnabled;
    }

    public KeycloakAdminClientProperties getKeycloakAdminClient() {
        return keycloakAdminClient;
    }

    public void setKeycloakAdminClient(KeycloakAdminClientProperties keycloakAdminClient) {
        this.keycloakAdminClient = keycloakAdminClient;
    }
}
