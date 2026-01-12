package com.projeto.academia.dto.exercicio;

import com.projeto.academia.enumeradores.DiaSemanaEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExerciciosAlunoDTO {

    private Long idTreino;
    private String nomeTreino;
    private DiaSemanaEnum diaSemana;
    private String nomeExercicio;
    private String repeticoes;
    private String carga;
    private String observacoes;
    private Long idAluno;
}
