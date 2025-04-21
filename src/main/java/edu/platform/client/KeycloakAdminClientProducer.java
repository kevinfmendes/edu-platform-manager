package edu.platform.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class KeycloakAdminClientProducer {

    @ConfigProperty(name = "keycloak.admin.server-url")
    String serverUrl;

    @ConfigProperty(name = "keycloak.admin.realm")
    String realm;

    @ConfigProperty(name = "keycloak.admin.client-id")
    String clientId;

    @ConfigProperty(name = "keycloak.admin.client-secret")
    String clientSecret;

    @Produces
    @ApplicationScoped
    public Keycloak keycloakAdminClient() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType("client_credentials")
                .build();
    }
}
