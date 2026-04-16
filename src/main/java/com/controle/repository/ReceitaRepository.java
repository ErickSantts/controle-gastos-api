package com.controle.repository;

import com.controle.model.Receitas;
import com.controle.model.Receitas;
import com.controle.model.dto.ResumoReceitaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receitas, Long> {

    List<Receitas> findAll();

    @Query("SELECT new com.controle.model.dto.ResumoReceitaDTO(" +
            "  (SELECT COALESCE(SUM(r.valor), 0) FROM Receitas r), " + // Soma sem o filtro de ativo
            "  (SELECT COALESCE(SUM(g.valor), 0) FROM Gasto g WHERE g.ativo = true), " +
            "  ((SELECT COALESCE(SUM(r.valor), 0) FROM Receitas r) - " +
            "   (SELECT COALESCE(SUM(g.valor), 0) FROM Gasto g WHERE g.ativo = true))" +
            ") FROM Gasto g")
    List<ResumoReceitaDTO> buscarResumoFinanceiro();
}
