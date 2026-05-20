package dev.gledson.cicero.exception;

public class SimulacaoNaoEncontradaException extends RuntimeException {
    public SimulacaoNaoEncontradaException(Long id) {
        super("Simulação não encontrada para o id: " + id);
    }
}