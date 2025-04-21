package edu.platform.controller;

import edu.platform.dtos.CredentialsDTO;
import edu.platform.dtos.RegistrationDTO;
import edu.platform.service.AuthService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    AuthService authService;

    @Path("/login")
    @POST
    public Response login(CredentialsDTO credentials) {
        return authService.login(credentials);
    }

    @Path("/register")
    @POST
    public Response register(RegistrationDTO registration) {
        return authService.register(registration);
    }

}
