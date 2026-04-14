package com.controle.repository;

import com.controle.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
    // Busca apenas os gastos que não foram "excluídos" logicamente
    List<Gasto> findByAtivoTrue();

    //List<Gasto> findByDescricaoContainingIgnoreCaseAndAtivoTrue(String descricao);

    @Query("SELECT g FROM Gasto g " +
            "WHERE (:desc IS NULL OR LOWER(g.descricao) LIKE LOWER(CONCAT('%', :desc, '%'))) " +
            "AND g.ativo = true " +
            "ORDER BY g.dataRealizadoGasto ASC")
    List<Gasto> buscarGastosAtivosPorDescricao(@Param("desc") String desc);
}

