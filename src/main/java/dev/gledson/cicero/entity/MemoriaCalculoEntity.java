package dev.gledson.cicero.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "memorias_calculo")
public class MemoriaCalculoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public Integer mes;

    @Column(nullable = false, precision = 19, scale = 2)
    public BigDecimal saldoInicial;

    @Column(nullable = false, precision = 19, scale = 2)
    public BigDecimal juros;

    @Column(nullable = false, precision = 19, scale = 2)
    public BigDecimal saldoFinal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "simulacao_id", nullable = false)
    public SimulacaoEntity simulacao;
}