package edu.platform.controller;

import edu.platform.entity.UserEntity;
import edu.platform.service.UserService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    @RolesAllowed("USER")
    public Response findAllUsers(@QueryParam("page") @DefaultValue("0") Integer page,
                                 @QueryParam("pageSize") @DefaultValue("5") Integer pageSize) {
        var users = userService.findAllUsers(page, pageSize);
        return Response.ok(users).build();
    }

    @Path("/{id}")
    @GET
    @RolesAllowed("USER")
    public Response findUserById(@PathParam("id") UUID id) {
        return Response.ok(userService.findById(id)).build();
    }

    @POST
    @Transactional
    @RolesAllowed("ADMIN")
    public Response createUser(UserEntity user) {
        return Response.ok(userService.createUser(user)).build();
    }

    @Path("/{id}")
    @PUT
    @Transactional
    @RolesAllowed("ADMIN")
    public Response updateUser(@PathParam("id") UUID id, UserEntity user) {
        return Response.ok(userService.updateUser(id, user)).build();
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    @RolesAllowed("ADMIN")
    public Response deleteUserById(@PathParam("id") UUID id) {
        userService.deleteUserById(id);
        return Response.noContent().build();
    }

}
