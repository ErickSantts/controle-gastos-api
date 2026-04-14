package com.controle.service;

import com.controle.model.Gasto;
import com.controle.model.TipoRecorrencia;
import com.controle.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class GastoService {
    @Autowired
    private GastoRepository repository;

    public Gasto salvar(Gasto gasto) {
        if (TipoRecorrencia.UNICO.equals(gasto.getRecorrencia()) || gasto.getQtdParcelas() == 1) {
            return repository.save(gasto);
        } else {
            BigDecimal valorParcela = gasto.getValor().divide(BigDecimal.valueOf(gasto.getQtdParcelas()), 2, RoundingMode.HALF_UP);
            System.out.println(gasto);
            for (int i = 1; i <= gasto.getQtdParcelas(); i++) {
                Gasto novaParcela = new Gasto(gasto, i, valorParcela);
                repository.save(novaParcela);
            }
        }
        return gasto;
    }

    public List<Gasto> listarTodosAtivos() {
        return repository.findByAtivoTrue();
    }

    public Gasto inativarAtivar(Long id) {
        Gasto gasto = repository.findById(id).orElseThrow(() -> new RuntimeException("Gasto não encontrado"));
        boolean status = gasto.isAtivo();
        gasto.setAtivo(!status);
        return repository.save(gasto);
    }

    public Gasto alterarStatus(Long id) {
        Gasto gasto = repository.findById(id).orElseThrow(() -> new RuntimeException("Gasto não encontrado"));
        boolean status = gasto.isPago();
        gasto.setPago(!status);
        return repository.save(gasto);
    }

    public List<Gasto> findByDescricaoContainingIgnoreCase(String descricao) {
        return repository.buscarGastosAtivosPorDescricao(descricao);
    }

    public Gasto atualizar(Long id, Gasto gastoAtualizado) {
        return repository.findById(id)
                .map(gasto -> {
                    gasto.setDescricao(gastoAtualizado.getDescricao());
                    gasto.setValor(gastoAtualizado.getValor());
                    gasto.setPago(gastoAtualizado.isPago());
                    gasto.setAtivo(gastoAtualizado.isAtivo());
                    gasto.setQtdParcelas(gastoAtualizado.getQtdParcelas());
                    gasto.setRecorrencia(gastoAtualizado.getRecorrencia());
                    gasto.setDataRealizadoGasto(gastoAtualizado.getDataRealizadoGasto());
                    return ResponseEntity.ok(repository.save(gasto));
                })
                .orElse(ResponseEntity.notFound().build()).getBody();
    }

    // Métodos para editar e excluir (delete real) seguem a mesma lógica
}