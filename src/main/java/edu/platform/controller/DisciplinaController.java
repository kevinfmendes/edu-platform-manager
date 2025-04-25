package edu.platform.controller;

import edu.platform.entity.DisciplinaEntity;
import edu.platform.service.DisciplinaService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/disciplinas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DisciplinaController {

    @Inject
    DisciplinaService disciplinaService;

    @GET
    @RolesAllowed({"ADMIN", "COORDENADOR", "PROFESSOR", "ALUNO"})
    public Response findAll() {
        return Response.ok(disciplinaService.findAll()).build();
    }

    @GET
    @Path("/curso/{cursoId}")
    @RolesAllowed({"ADMIN", "COORDENADOR", "PROFESSOR", "ALUNO"})
    public Response findByCurso(@PathParam("cursoId") Long cursoId) {
        return Response.ok(disciplinaService.findByCurso(cursoId)).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "COORDENADOR", "PROFESSOR", "ALUNO"})
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(disciplinaService.findById(id)).build();
    }

    @POST
    @RolesAllowed({"ADMIN", "COORDENADOR"})
    public Response create(DisciplinaEntity disciplina) {
        return Response.ok(disciplinaService.save(disciplina)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "COORDENADOR"})
    public Response update(@PathParam("id") Long id, DisciplinaEntity disciplina) {
        return Response.ok(disciplinaService.update(id, disciplina)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "COORDENADOR"})
    public Response delete(@PathParam("id") Long id) {
        disciplinaService.delete(id);
        return Response.noContent().build();
    }
}
