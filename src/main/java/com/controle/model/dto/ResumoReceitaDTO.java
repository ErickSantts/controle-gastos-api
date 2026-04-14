package com.controle.model.dto;

import java.math.BigDecimal;

public record ResumoReceitaDTO(
        BigDecimal totalReceitas,
        BigDecimal totalGastos,
        BigDecimal saldo
) {}
