package com.projeto.academia.dto.exercicio;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExercicioDTO {

    private Long id;
    private String nome;
    private String repeticoes;
    private String carga;
    private String observacoes;
    private Long treinoId;
}
