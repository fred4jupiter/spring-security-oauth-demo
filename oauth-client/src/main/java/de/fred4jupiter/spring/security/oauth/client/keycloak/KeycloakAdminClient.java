package de.fred4jupiter.spring.security.oauth.client.keycloak;

import de.fred4jupiter.spring.security.oauth.client.prop.KeycloakAdminClientProperties;
import de.fred4jupiter.spring.security.oauth.client.prop.OauthClientProperties;
import jakarta.annotation.PreDestroy;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@ConditionalOnProperty(prefix = "client", value = "keycloak-client-enabled", havingValue = "true")
@Component
public class KeycloakAdminClient {

    private static final Logger LOG = LoggerFactory.getLogger(KeycloakAdminClient.class);

    private final Keycloak keycloak;

    private final OauthClientProperties oauthClientProperties;

    public KeycloakAdminClient(OauthClientProperties oauthClientProperties) {
        this.oauthClientProperties = oauthClientProperties;
        this.keycloak = createKeycloak();
    }

    @PreDestroy
    public void closeKeycloak() {
        this.keycloak.close();
    }

    private Keycloak createKeycloak() {
        KeycloakAdminClientProperties keycloakAdminClientProperties = this.oauthClientProperties.getKeycloakAdminClient();

        return KeycloakBuilder.builder()
                .serverUrl(keycloakAdminClientProperties.getServerUrl())
                .realm(keycloakAdminClientProperties.getRealm())
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(keycloakAdminClientProperties.getClientId())
                .clientSecret(keycloakAdminClientProperties.getClientSecret()).build();
    }

    private RealmResource getRealmResource() {
        return this.keycloak.realm(oauthClientProperties.getKeycloakAdminClient().getRealm());
    }

    public String createUser(Consumer<UserRepresentation> consumer, String password, boolean temporaryPassword) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        consumer.accept(userRepresentation);
        UsersResource usersRessource = getRealmResource().users();

        if (StringUtils.isNotBlank(password)) {
            CredentialRepresentation passwordCred = new CredentialRepresentation();
            passwordCred.setTemporary(temporaryPassword);
            passwordCred.setType(CredentialRepresentation.PASSWORD);
            passwordCred.setValue(password);
            userRepresentation.setCredentials(Collections.singletonList(passwordCred));
        }

        LOG.debug("try to create userRepresentation with username: {}", userRepresentation.getUsername());
        Response response = usersRessource.create(userRepresentation);

        LOG.debug("Response: status={}, statusInfo={}, location={}", response.getStatus(), response.getStatusInfo(), response.getLocation());
        return CreatedResponseUtil.getCreatedId(response);
    }

    public Optional<String> findClientUUID(String clientId) {
        try {
            List<ClientRepresentation> all = getRealmResource().clients().findByClientId(clientId);
            return all.stream().findFirst().map(ClientRepresentation::getId);
        } catch (NotFoundException e) {
            return Optional.empty();
        }
    }

    public List<String> listAllClients() {
        return getRealmResource().clients().findAll().stream().map(ClientRepresentation::getClientId).toList();
    }

}
