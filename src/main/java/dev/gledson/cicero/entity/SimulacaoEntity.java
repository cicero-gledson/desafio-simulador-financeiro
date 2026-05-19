package dev.gledson.cicero.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "simulacoes")
public class SimulacaoEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false, precision = 19, scale = 2)
    public BigDecimal valorInicial;

    @Column(nullable = false, precision = 10, scale = 4)
    public BigDecimal taxaJurosMensal;

    @Column(nullable = false)
    public Integer prazoMeses;

    @Column(nullable = false, precision = 19, scale = 2)
    public BigDecimal valorTotalFinal;

    @Column(nullable = false, precision = 19, scale = 2)
    public BigDecimal valorTotalJuros;

    @Column(nullable = false)
    public LocalDateTime dataCriacao;

    @OneToMany(
            mappedBy = "simulacao",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("mes ASC")
    public List<MemoriaCalculoEntity> memoriaCalculo = new ArrayList<>();

    public void adicionarMemoria(MemoriaCalculoEntity memoria) {
        memoria.simulacao = this;
        this.memoriaCalculo.add(memoria);
    }
}