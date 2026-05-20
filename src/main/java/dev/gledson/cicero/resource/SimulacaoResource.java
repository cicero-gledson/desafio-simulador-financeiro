package dev.gledson.cicero.resource;

import dev.gledson.cicero.dto.SimulacaoRequest;
import dev.gledson.cicero.dto.SimulacaoResponse;
import dev.gledson.cicero.exception.ApiError;
import dev.gledson.cicero.service.SimulacaoService;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/simulacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(
        name = "Simulações",
        description = "Operações para simulação de financiamentos"
)
public class SimulacaoResource {

    private final SimulacaoService service;

    public SimulacaoResource(SimulacaoService service) {
        this.service = service;
    }

    @POST
    @Operation(
            summary = "Cria uma simulação de financiamento com juros compostos"
    )
    @APIResponse(
            responseCode = "201",
            description = "Simulação criada com sucesso",
            content = @Content(
                    schema = @Schema(implementation = SimulacaoResponse.class)
            )
    )
    @APIResponse(
            responseCode = "400",
            description = "Dados inválidos",
            content = @Content(
                    schema = @Schema(implementation = ApiError.class)
            )
    )
    public Response criar(@Valid SimulacaoRequest request) {

        SimulacaoResponse response = service.criar(request);

        return Response.status(Response.Status.CREATED)
                .entity(response)
                .build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Consulta uma simulação pelo ID")
    @APIResponse(
            responseCode = "200",
            description = "Simulação encontrada",
            content = @Content(
                    schema = @Schema(implementation = SimulacaoResponse.class)
            )
    )
    @APIResponse(
            responseCode = "404",
            description = "Simulação não encontrada",
            content = @Content(
                    schema = @Schema(implementation = ApiError.class)
            )
    )
    public SimulacaoResponse buscarPorId(
            @PathParam("id") Long id
    ) {

        return service.buscarPorId(id);
    }
}