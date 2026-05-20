package dev.gledson.cicero.service;

import dev.gledson.cicero.dto.SimulacaoRequest;
import dev.gledson.cicero.dto.SimulacaoResponse;
import dev.gledson.cicero.entity.MemoriaCalculoEntity;
import dev.gledson.cicero.entity.SimulacaoEntity;
import dev.gledson.cicero.mapper.SimulacaoMapper;
import dev.gledson.cicero.repository.SimulacaoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@ApplicationScoped
public class SimulacaoService {

    private static final int ESCALA_MONETARIA = 2;
    private static final int ESCALA_TAXA = 10;
    private static final RoundingMode ARREDONDAMENTO = RoundingMode.HALF_UP;
    private static final BigDecimal CEM = new BigDecimal("100");

    private final SimulacaoRepository repository;

    public SimulacaoService(SimulacaoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public SimulacaoResponse criar(SimulacaoRequest request) {
        SimulacaoEntity simulacao = calcular(request);

        repository.persist(simulacao);

        return SimulacaoMapper.toResponse(simulacao);
    }

    SimulacaoEntity calcular(SimulacaoRequest request) {

        BigDecimal taxaDecimal = request.taxaJurosMensal()
                .divide(CEM, ESCALA_TAXA, ARREDONDAMENTO);

        BigDecimal saldoAtual = dinheiro(request.valorInicial());

        SimulacaoEntity simulacao = new SimulacaoEntity();

        simulacao.valorInicial = dinheiro(request.valorInicial());
        simulacao.taxaJurosMensal = request.taxaJurosMensal();
        simulacao.prazoMeses = request.prazoMeses();
        simulacao.dataCriacao = LocalDateTime.now();

        for (int mes = 1; mes <= request.prazoMeses(); mes++) {

            BigDecimal saldoInicial = dinheiro(saldoAtual);

            BigDecimal juros = dinheiro(
                    saldoInicial.multiply(taxaDecimal)
            );

            BigDecimal saldoFinal = dinheiro(
                    saldoInicial.add(juros)
            );

            MemoriaCalculoEntity memoria = new MemoriaCalculoEntity();

            memoria.mes = mes;
            memoria.saldoInicial = saldoInicial;
            memoria.juros = juros;
            memoria.saldoFinal = saldoFinal;

            simulacao.adicionarMemoria(memoria);

            saldoAtual = saldoFinal;
        }

        simulacao.valorTotalFinal = dinheiro(saldoAtual);

        simulacao.valorTotalJuros = dinheiro(
                simulacao.valorTotalFinal.subtract(simulacao.valorInicial)
        );

        return simulacao;
    }

    private BigDecimal dinheiro(BigDecimal valor) {
        return valor.setScale(ESCALA_MONETARIA, ARREDONDAMENTO);
    }
}