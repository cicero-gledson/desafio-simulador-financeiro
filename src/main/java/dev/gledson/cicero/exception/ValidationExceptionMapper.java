package dev.gledson.cicero.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
public class ValidationExceptionMapper
        implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(
            ConstraintViolationException exception
    ) {

        List<String> erros = exception.getConstraintViolations()
                .stream()
                .map(violacao ->
                        violacao.getPropertyPath()
                                + ": "
                                + violacao.getMessage()
                )
                .toList();

        ApiError error = ApiError.of(
                Response.Status.BAD_REQUEST.getStatusCode(),
                "Dados inválidos.",
                erros
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(error)
                .build();
    }
}