package com.projeto.academia.cmd.treino;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.projeto.academia.enumeradores.DiaSemanaEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarTreinoCmd {

    @NotNull(message = "O ID do treino é obrigatório")
    private Long idTreino;

    private DiaSemanaEnum diaSemana;

    @NotBlank(message = "O nome do treino é obrigatório")
    private String nomeTreino;

}
