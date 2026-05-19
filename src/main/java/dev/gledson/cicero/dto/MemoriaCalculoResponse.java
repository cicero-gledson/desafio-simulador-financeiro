package dev.gledson.cicero.dto;

import java.math.BigDecimal;

public record MemoriaCalculoResponse(
        Integer mes,
        BigDecimal saldoInicial,
        BigDecimal juros,
        BigDecimal saldoFinal
) {
}