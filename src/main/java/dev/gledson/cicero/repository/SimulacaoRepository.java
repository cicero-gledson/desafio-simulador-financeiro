package dev.gledson.cicero.repository;

import dev.gledson.cicero.entity.SimulacaoEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SimulacaoRepository implements PanacheRepository<SimulacaoEntity> {
}