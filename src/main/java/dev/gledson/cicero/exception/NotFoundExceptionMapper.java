package dev.gledson.cicero.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
public class NotFoundExceptionMapper
        implements ExceptionMapper<SimulacaoNaoEncontradaException> {

    @Override
    public Response toResponse(
            SimulacaoNaoEncontradaException exception
    ) {

        ApiError error = ApiError.of(
                Response.Status.NOT_FOUND.getStatusCode(),
                "Recurso não encontrado.",
                List.of(exception.getMessage())
        );

        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .build();
    }
}