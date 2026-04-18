package com.controle.repository;

import com.controle.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Long> {
    // Busca apenas os gastos que não foram "excluídos" logicamente

            @Query("SELECT g FROM Gasto g " +
                    "WHERE g.ativo = true " +
                    "AND EXTRACT(YEAR FROM g.dataRealizadoGasto) = EXTRACT(YEAR FROM CURRENT_DATE)" +
                    "AND EXTRACT(MONTH FROM g.dataRealizadoGasto) = EXTRACT(MONTH from CURRENT_DATE)" +
                    "ORDER BY g.dataRealizadoGasto ASC")
    List<Gasto> findAllByData();

    //List<Gasto> findByDescricaoContainingIgnoreCaseAndAtivoTrue(String descricao);

    @Query("SELECT g FROM Gasto g " +
            "WHERE (:desc IS NULL OR LOWER(g.descricao) LIKE LOWER(CONCAT('%', :desc, '%'))) " +
            "AND g.ativo = true " +
            "AND EXTRACT(YEAR FROM g.dataRealizadoGasto) = :mes " +
            "AND EXTRACT(MONTH FROM g.dataRealizadoGasto) = :ano " +
            "ORDER BY g.dataRealizadoGasto ASC")
    List<Gasto> buscarGastosAtivosPorDescricao(@Param("desc") String desc,
                                               @Param("mes") Integer mes,
                                               @Param("ano") Integer ano);
}

