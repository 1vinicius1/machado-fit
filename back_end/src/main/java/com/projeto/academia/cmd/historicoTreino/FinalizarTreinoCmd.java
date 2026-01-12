package com.projeto.academia.cmd.historicoTreino;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FinalizarTreinoCmd {

    @NotNull(message = "ID do aluno é obrigatório")
    private Long idAluno;

    @NotNull(message = "ID do treino é obrigatório")
    private Long idTreino;

    private LocalDate dataFinalizacao;

}
