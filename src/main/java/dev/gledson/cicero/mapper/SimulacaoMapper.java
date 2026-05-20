package dev.gledson.cicero.mapper;

import dev.gledson.cicero.dto.MemoriaCalculoResponse;
import dev.gledson.cicero.dto.SimulacaoResponse;
import dev.gledson.cicero.entity.SimulacaoEntity;

import java.util.List;

public final class SimulacaoMapper {

    private SimulacaoMapper() {
    }

    public static SimulacaoResponse toResponse(SimulacaoEntity entity) {

        List<MemoriaCalculoResponse> memorias =
                entity.memoriaCalculo.stream()
                        .map(memoria -> new MemoriaCalculoResponse(
                                memoria.mes,
                                memoria.saldoInicial,
                                memoria.juros,
                                memoria.saldoFinal
                        ))
                        .toList();

        return new SimulacaoResponse(
                entity.id,
                entity.valorInicial,
                entity.taxaJurosMensal,
                entity.prazoMeses,
                entity.valorTotalFinal,
                entity.valorTotalJuros,
                entity.dataCriacao,
                memorias
        );
    }
}