package dev.gledson.cicero.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        LocalDateTime timestamp,
        int status,
        String mensagem,
        List<String> erros
) {

    public static ApiError of(
            int status,
            String mensagem,
            List<String> erros
    ) {
        return new ApiError(
                LocalDateTime.now(),
                status,
                mensagem,
                erros
        );
    }
}