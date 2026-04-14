package com.controle.repository;

import com.controle.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
    // Busca apenas os gastos que não foram "excluídos" logicamente
    List<Gasto> findByAtivoTrue();

    List<Gasto> findByDescricaoContainingIgnoreCase(String descricao);
}

