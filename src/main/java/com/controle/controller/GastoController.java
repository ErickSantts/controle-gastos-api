package com.controle.controller;

import com.controle.model.Gasto;
import com.controle.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gastos")
public class GastoController {

    @Autowired
    private GastoService service;

    @PostMapping
    public Gasto criar(@RequestBody Gasto gasto) {
        return service.salvar(gasto);
    }

    @GetMapping
    public List<Gasto> listar() {
        return service.listarTodosAtivos();
    }

    @PutMapping("/{id}/inativarAtivar")
    public ResponseEntity<Gasto> inativar(@PathVariable Long id) {
        Gasto gastoAtualizado = service.inativarAtivar(id);
        return ResponseEntity.ok(gastoAtualizado);
    }

}
