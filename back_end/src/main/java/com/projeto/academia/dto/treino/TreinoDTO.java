package com.projeto.academia.dto.treino;

import com.projeto.academia.enumeradores.DiaSemanaEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TreinoDTO {

    private Long id;
    private String nome;
    private DiaSemanaEnum diaSemana;
}
