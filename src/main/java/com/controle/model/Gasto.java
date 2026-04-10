package com.controle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private BigDecimal valor;
    private LocalDate dataVencimento;

    @Enumerated(EnumType.STRING)
    private TipoRecorrencia recorrencia; // UNICO, MENSAL, ANUAL

    private boolean ativo = true; // Para o "inativar" que você pediu

}
