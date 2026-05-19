package dev.gledson.cicero.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record SimulacaoResponse(
        Long id,
        BigDecimal valorInicial,
        BigDecimal taxaJurosMensal,
        Integer prazoMeses,
        BigDecimal valorTotalFinal,
        BigDecimal valorTotalJuros,
        LocalDateTime dataCriacao,
        List<MemoriaCalculoResponse> memoriaCalculo
) {
}