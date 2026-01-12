package com.projeto.academia.cmd.exercicio;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarExercicioCmd {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O número de repetições é obrigatório")
    private String repeticoes;

    @NotBlank(message = "A carga é obrigatória")
    private String carga;

    private String observacoes;

    @NotNull(message = "O ID do Exercício é obrigatório")
    private Long idExercicio;

}
