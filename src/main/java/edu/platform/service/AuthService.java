package edu.platform.service;

import edu.platform.dtos.CredentialsDTO;
import edu.platform.dtos.TokenResponseDTO;
import io.quarkus.oidc.client.OidcClient;
import io.quarkus.oidc.client.Tokens;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;


@ApplicationScoped
public class AuthService {

    @Inject
    OidcClient oidcClient;

    public Response login(CredentialsDTO credentials) {
        try {
            Tokens tokens = oidcClient.getTokens().await().indefinitely();

            TokenResponseDTO tokenResponse = new TokenResponseDTO(
                    tokens.getAccessToken(),
                    tokens.getRefreshToken(),
                    tokens.getAccessTokenExpiresAt()
            );

            return Response.ok(tokenResponse).build();

        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário ou senha inválidos").build();
        }
    }

}
