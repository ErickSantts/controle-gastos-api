package com.controle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "receita")
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao; // Ex: "Salário - Meu", "Salário - Esposa", "Bônus"
    private BigDecimal valor;
    private LocalDate dataRecebimento;
    private Categoria categoria; // "SALARIO"
    private Boolean ativo = true;

}
