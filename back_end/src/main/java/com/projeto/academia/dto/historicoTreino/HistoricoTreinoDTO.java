package com.projeto.academia.dto.historicoTreino;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HistoricoTreinoDTO {

    private LocalDate dataFinalizacao;
    private String nomeTreino;
}
