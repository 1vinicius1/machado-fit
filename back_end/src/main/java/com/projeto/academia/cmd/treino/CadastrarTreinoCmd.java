package com.projeto.academia.cmd.treino;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.projeto.academia.enumeradores.DiaSemanaEnum;

import lombok.Getter;

@Getter
public class CadastrarTreinoCmd {

    @NotNull(message = "O dia da semana é obrigatório")
    private DiaSemanaEnum diaSemana;

    @NotBlank(message = "O nome do treino é obrigatório")
    private String nomeTreino;

    @NotNull(message = "O ID do Aluno é obrigatório")
    private Long idAluno;

    @SuppressWarnings("unused")
    private CadastrarTreinoCmd(){}

    public CadastrarTreinoCmd(DiaSemanaEnum diaSemana, String nomeTreino, Long idAluno) {

        this.diaSemana = diaSemana;
        this.nomeTreino = nomeTreino;
        this.idAluno = idAluno;
    }


}
