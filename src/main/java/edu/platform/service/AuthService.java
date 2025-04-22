package edu.platform.service;

import edu.platform.dtos.CredentialsDTO;
import edu.platform.dtos.RegistrationDTO;
import edu.platform.dtos.TokenResponseDTO;
import edu.platform.entity.UserEntity;
import edu.platform.entity.enums.TipoUsuario;
import edu.platform.repository.UserRepository;
import io.quarkus.oidc.client.OidcClient;
import io.quarkus.oidc.client.Tokens;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.resource.spi.ConfigProperty;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jdk.javadoc.doclet.Reporter;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ApplicationScoped
public class AuthService {

    @Inject
    OidcClient oidcClient;

    @Inject
    Keycloak keycloakAdmin;

    @ConfigProperty(defaultValue = "keycloak.admin.realm")
    String adminRealm;

    @ConfigProperty(defaultValue = "quarkus.oidc.auth-server-url")
    String authServerUrl;

    @Inject
    UserRepository userRepository;

    public Response register(RegistrationDTO registration) {
        try {
            String appRealm = "app-quarkus-realm";

            if(userRepository.existsByEmail(registration.getEmail()))
                return Response.status(Response.Status.CONFLICT.getStatusCode(), "Já existe um usuário registrado com esse email").build();

            if(userRepository.existsByUsername(registration.getUsername()))
                return Response.status(Response.Status.CONFLICT.getStatusCode(), "Já existe um usuário com esse username.").build();

            // Criar usuário no Keycloak
            String keycloakId = createKeycloakUser(appRealm, registration);

            // Criar usuário local vinculado ao Keycloak
            UserEntity user = new UserEntity();
            user.setKeycloakId(keycloakId);
            user.setName(registration.getFirstName() + " " + registration.getLastName());
            user.setEmail(registration.getEmail());
            user.setUsername(registration.getUsername());
            user.setFirstName(registration.getFirstName());
            user.setLastName(registration.getLastName());

            //user.addRole("user");

            userRepository.persist(user);

            return login(new CredentialsDTO(registration.getUsername(), registration.getPassword()));

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro no registro: " + e.getMessage()).build();
        }
    }

    public Response login(CredentialsDTO credentials) {
        try {

            Map<String, String> params = new HashMap<>();
            params.put("username", credentials.username());
            params.put("password", credentials.password());

            Tokens tokens = oidcClient.getTokens(params).await().indefinitely();

            TokenResponseDTO tokenResponse = new TokenResponseDTO(
                    tokens.getAccessToken(),
                    tokens.getRefreshToken(),
                    tokens.getAccessTokenExpiresAt()
            );

            return Response.ok(tokenResponse).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("error:" +e.getMessage()).build();
        }
    }

    private String createKeycloakUser(String realm, RegistrationDTO registration) {

        UserRepresentation user = new UserRepresentation();
        user.setUsername(registration.getUsername());
        user.setEmail(registration.getEmail());
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEnabled(true);

        // Criar usuário no Keycloak
        UsersResource usersResource = keycloakAdmin.realm(realm).users();
        Response response = usersResource.create(user);

        if (response.getStatus() != 201) {
            throw new WebApplicationException("Erro ao criar usuário no Keycloak: " + response.getStatusInfo());
        }

        // Obter o ID gerado
        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

        // Definir senha
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setTemporary(false);
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(registration.getPassword());

        usersResource.get(userId).resetPassword(credential);

        // Atribuir role com base no código informado
        TipoUsuario tipo = Arrays.stream(TipoUsuario.values())
                .filter(t -> t.getCodigo().equals(registration.getCodigoRole()))
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("Código de role inválido"));

        RoleRepresentation role = keycloakAdmin.realm(realm).roles().get(tipo.getDescricao()).toRepresentation();
        usersResource.get(userId).roles().realmLevel().add(List.of(role));

        return userId;
    }


}
