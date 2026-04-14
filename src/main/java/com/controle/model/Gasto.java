package com.controle.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "gasto")
@AllArgsConstructor
@NoArgsConstructor
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private BigDecimal valor;
    private LocalDate dataRealizadoGasto;
    private boolean pago = true;
    private Integer qtdParcelas;
    private boolean ativo = true;
    @Enumerated(EnumType.STRING)
    private TipoRecorrencia recorrencia; // UNICO, MENSAL, ANUAL




    // Construtor de Cópia para Gerar Parcelas
    public Gasto(Gasto molde, Integer numeroParcela, BigDecimal valorParcela) {
        this.descricao = molde.getDescricao() + " (" + numeroParcela + "/" + molde.getQtdParcelas() + ")";
        this.valor = valorParcela;
        this.recorrencia = molde.getRecorrencia();
        this.qtdParcelas = (molde.getQtdParcelas() != null) ? molde.getQtdParcelas() : 1;        this.pago = false; // Novas parcelas sempre começam pendentes

        // Lógica de data embutida no construtor
        if (molde.getRecorrencia().equals(TipoRecorrencia.ANUAL)) {
            this.dataRealizadoGasto = molde.getDataRealizadoGasto().plusYears(numeroParcela - 1);
        } else {
            this.dataRealizadoGasto = molde.getDataRealizadoGasto().plusMonths(numeroParcela - 1);
        }
    }

}
