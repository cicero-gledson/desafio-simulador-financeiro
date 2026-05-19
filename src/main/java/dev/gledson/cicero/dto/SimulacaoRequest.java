package dev.gledson.cicero.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SimulacaoRequest(

        @NotNull(message = "valorInicial é obrigatório")
        @DecimalMin(value = "0.01", message = "valorInicial deve ser maior que zero")
        BigDecimal valorInicial,

        @NotNull(message = "taxaJurosMensal é obrigatória")
        @DecimalMin(value = "0.00", message = "taxaJurosMensal não pode ser negativa")
        BigDecimal taxaJurosMensal,

        @NotNull(message = "prazoMeses é obrigatório")
        @Min(value = 1, message = "prazoMeses deve ser maior ou igual a 1")
        Integer prazoMeses
) {
}