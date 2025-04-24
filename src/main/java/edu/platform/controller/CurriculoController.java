package edu.platform.controller;
/*
import edu.platform.entity.CurriculoEntity;
import edu.platform.service.CurriculoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/api/curriculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CurriculoController {

    @Inject
    CurriculoService curriculoService;

    @GET
    @RolesAllowed({"admin", "coordenador", "professor", "aluno"})
    public Response listarTodos() {
        return Response.ok(curriculoService.listarTodos()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin", "coordenador", "professor", "aluno"})
    public Response buscarPorId(@PathParam("id") UUID id) {
        return Response.ok(curriculoService.buscarPorId(id)).build();
    }

    @GET
    @Path("/curso/{cursoId}")
    @RolesAllowed({"admin", "coordenador", "professor", "aluno"})
    public Response listarPorCurso(@PathParam("cursoId") UUID cursoId) {
        return Response.ok(curriculoService.listarPorCurso(cursoId)).build();
    }

    @GET
    @Path("/semestre/{semestreId}")
    @RolesAllowed({"admin", "coordenador", "professor", "aluno"})
    public Response listarPorSemestre(@PathParam("semestreId") UUID semestreId) {
        return Response.ok(curriculoService.listarPorSemestre(semestreId)).build();
    }

    @GET
    @Path("/curso/{cursoId}/semestre/{semestreId}")
    @RolesAllowed({"admin", "coordenador", "professor", "aluno"})
    public Response listarPorCursoESemestre(
            @PathParam("cursoId") UUID cursoId,
            @PathParam("semestreId") UUID semestreId) {
        return Response.ok(curriculoService.listarPorCursoESemestre(cursoId, semestreId)).build();
    }

    @POST
    @RolesAllowed({"admin", "coordenador"})
    public Response criar(CurriculoRequest request) {
        CurriculoEntity curriculo = curriculoService.criar(request);
        return Response.status(Response.Status.CREATED).entity(curriculo).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"admin", "coordenador"})
    public Response atualizar(@PathParam("id") UUID id, CurriculoRequest request) {
        CurriculoEntity curriculo = curriculoService.atualizar(id, request);
        return Response.ok(curriculo).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"admin", "coordenador"})
    public Response excluir(@PathParam("id") UUID id) {
        curriculoService.excluir(id);
        return Response.noContent().build();
    }
}*/