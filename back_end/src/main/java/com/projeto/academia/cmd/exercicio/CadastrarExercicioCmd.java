package com.projeto.academia.cmd.exercicio;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class CadastrarExercicioCmd {

    @NotNull(message = "O ID do treino é obrigatório")
    private Long treinoId;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O número de repetições é obrigatório")
    private String repeticoes;

    @NotBlank(message = "A carga é obrigatória")
    private String carga;

    private String observacoes;

    @SuppressWarnings("unused")
    private CadastrarExercicioCmd(){}

    public CadastrarExercicioCmd(Long treinoId, String nome, String repeticoes, String carga, String observacoes) {
        this.treinoId = treinoId;
        this.nome = nome;
        this.repeticoes = repeticoes;
        this.carga = carga;
        this.observacoes = observacoes;
    }

}
