package edu.platform.controller;

import edu.platform.entity.CursoEntity;
import edu.platform.service.CursoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/cursos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CursoController {

    @Inject
    CursoService cursoService;

    @GET
    @RolesAllowed({"ADMIN", "COORDENADOR"})
    public Response findAll() {
        return Response.ok(cursoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "COORDENADOR", "PROFESSOR", "ALUNO"})
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(cursoService.findById(id)).build();
    }

    @POST
    @RolesAllowed({"ADMIN", "COORDENADOR"})
    public Response create(CursoEntity curso) {
        return Response.ok(cursoService.save(curso)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "COORDENADOR"})
    public Response update(@PathParam("id") Long id, CursoEntity curso) {
        return Response.ok(cursoService.update(id, curso)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("ADMIN")
    public Response delete(@PathParam("id") Long id) {
        cursoService.delete(id);
        return Response.noContent().build();
    }

}
