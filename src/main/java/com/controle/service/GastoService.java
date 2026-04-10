package com.controle.service;

import com.controle.model.Gasto;
import com.controle.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GastoService {
    @Autowired
    private GastoRepository repository;

    public Gasto salvar(Gasto gasto) {
        return repository.save(gasto);
    }

    public List<Gasto> listarTodosAtivos() {
        return repository.findByAtivoTrue();
    }

    public Gasto inativarAtivar(Long id) {
        Gasto gasto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto não encontrado"));
        boolean status = gasto.isAtivo();
        gasto.setAtivo(!status);
       return repository.save(gasto);
    }

    // Métodos para editar e excluir (delete real) seguem a mesma lógica
}