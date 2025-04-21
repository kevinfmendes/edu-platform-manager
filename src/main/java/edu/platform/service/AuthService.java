package edu.platform.service;

import edu.platform.dtos.CredentialsDTO;
import edu.platform.dtos.TokenResponseDTO;
import io.quarkus.oidc.client.OidcClient;
import io.quarkus.oidc.client.Tokens;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class AuthService {

    @Inject
    OidcClient oidcClient;

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

}
