package edu.platform.controller;

import edu.platform.entity.CurriculoEntity;
import edu.platform.service.CurriculoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/matriz-curricular")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CurriculoController {

    @Inject
    CurriculoService curriculoService;

    @GET
    @Path("/curso/{cursoId}")
    @RolesAllowed({"ADMIN", "COORDENADOR", "PROFESSOR", "ALUNO"})
    public Response findByCurso(@PathParam("cursoId") Long cursoId) {
        return Response.ok(curriculoService.findByCurso(cursoId)).build();
    }

    @GET
    @Path("/curso/{cursoId}/semestre/{semestreId}")
    @RolesAllowed({"ADMIN", "COORDENADOR", "PROFESSOR", "ALUNO"})
    public Response findByCursoAndSemestre(
            @PathParam("cursoId") Long cursoId,
            @PathParam("semestreId") Long semestreId) {
        return Response.ok(curriculoService.findByCursoAndSemestre(cursoId, semestreId)).build();
    }

    @POST
    @RolesAllowed({"ADMIN", "COORDENADOR"})
    public Response addDisciplina(CurriculoEntity item) {
        return Response.ok(curriculoService.addDisciplina(item)).build();
    }

    @PUT
    @Path("/{id}/posicao/{novaPosicao}")
    @RolesAllowed({"ADMIN", "COORDENADOR"})
    public Response updatePosicao(
            @PathParam("id") UUID id,
            @PathParam("novaPosicao") Integer novaPosicao) {
        return Response.ok(curriculoService.updatePosicao(id, novaPosicao)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"ADMIN", "COORDENADOR"})
    public Response removeDisciplina(@PathParam("id") UUID id) {
        curriculoService.removeDisciplina(id);
        return Response.noContent().build();
    }
}