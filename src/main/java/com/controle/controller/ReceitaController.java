package com.controle.controller;

import com.controle.model.Receitas;
import com.controle.model.dto.ResumoReceitaDTO;
import com.controle.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/receitas")
public class ReceitaController {
    @Autowired
    private ReceitaService service;

    @GetMapping
    private List<Receitas> listar() {
        return this.service.listar();
    }

    @GetMapping("resumo")
    private ResumoReceitaDTO obterResumoFinanceiro(){
        return this.service.obterResumoFinanceiro();
    }

    @PostMapping
    private Receitas salvar(@RequestBody Receitas receita){
        return this.service.salvar(receita);
    }


}
