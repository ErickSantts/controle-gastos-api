package com.controle.controller;

import com.controle.model.Gasto;
import com.controle.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
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

    @GetMapping("/pesquisar")
    public List<Gasto> pesquisar(@RequestParam String descricao, @RequestParam("data") String dataString) {

        LocalDate data = ZonedDateTime.parse(dataString).toLocalDate();
        System.out.println(dataString);
        System.out.println(descricao);
        return service.findByDescricaoContainingIgnoreCase(descricao, data);
    }

    @PutMapping("/inativarAtivar")
    public ResponseEntity<Gasto> inativar(@RequestParam Long id) {
        Gasto gastoAtualizado = service.inativarAtivar(id);
        return ResponseEntity.ok(gastoAtualizado);
    }

    @PutMapping("alterarStatus")
    public ResponseEntity<Gasto> alterarStatus(@RequestParam Long id) {
        Gasto gastoAtualizado = service.alterarStatus(id);
        return ResponseEntity.ok(gastoAtualizado);
    }

    @PutMapping("/atualizar/{id}") // O ID vai na rota
    public ResponseEntity<Gasto> atualizar(
            @PathVariable Long id,
            @RequestBody Gasto gastoAtualizado // O objeto vem do corpo do JSON
    ) {
        Gasto gasto = service.atualizar(id, gastoAtualizado);
        return ResponseEntity.ok(gasto);
    }
}
