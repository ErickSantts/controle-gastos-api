package com.controle.controller;

import com.controle.model.Receita;
import com.controle.model.dto.ResumoReceitaDTO;
import com.controle.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/receitas")
public class ReceitaController {
    @Autowired
    private ReceitaService service;

    @GetMapping
    private List<Receita> listar() {
        return this.service.listar();
    }

    @GetMapping("resumo")
    private ResumoReceitaDTO obterResumoFinanceiro(){
        return this.service.obterResumoFinanceiro();
    }


}
