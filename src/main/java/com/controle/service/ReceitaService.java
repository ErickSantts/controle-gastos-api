package com.controle.service;

import com.controle.model.Receitas;
import com.controle.model.Receitas;
import com.controle.model.dto.ResumoReceitaDTO;
import com.controle.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository repository;

    public List<Receitas> listar() {
        return this.repository.findAll();
    }

    public ResumoReceitaDTO obterResumoFinanceiro() {
        return repository.buscarResumoFinanceiro().stream()
                .findFirst()
                .orElse(new ResumoReceitaDTO(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));
    }

    public Receitas salvar(Receitas receita){
        return repository.save(receita);
    }
}
